package com.miui.gallery.share;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.ServerErrorCode;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import miui.app.ProgressDialog;

public class AlbumShareUIManager {
    /* access modifiers changed from: private */
    public static final JobStatusManager<Path, Integer> sAlbumShareManager = new JobStatusManager<Path, Integer>(new ThreadPool(1, 1)) {
        /* access modifiers changed from: protected */
        public Integer advanceStatus(Path path, Integer num, boolean z) {
            int advance = num != null ? AlbumShareState.advance(num.intValue(), z) : -1;
            if (!AlbumShareState.isValid(advance)) {
                advance = AlbumShareUIManager.readAlbumShareStateFromDB(path);
            }
            return Integer.valueOf(advance);
        }
    };
    private static final JobStatusManager<Void, Void> sDefaultJobManager = new JobStatusManager<>(new ThreadPool());
    static final Handler sHandler = new Handler(Looper.getMainLooper());

    public static final class BlockMessage {
        final WeakReference<Activity> mActivityRef;
        final boolean mCancelable;
        final DialogInterface.OnCancelListener mListener;
        final CharSequence mMessage;
        final CharSequence mTitle;

        private BlockMessage(Activity activity, CharSequence charSequence, CharSequence charSequence2, boolean z, DialogInterface.OnCancelListener onCancelListener) {
            if (activity != null) {
                this.mActivityRef = new WeakReference<>(activity);
                this.mTitle = charSequence;
                this.mMessage = charSequence2;
                this.mCancelable = z;
                this.mListener = onCancelListener;
                return;
            }
            throw new IllegalArgumentException("context cannot be null");
        }

        public static BlockMessage create(Activity activity, CharSequence charSequence, CharSequence charSequence2) {
            return create(activity, charSequence, charSequence2, true);
        }

        public static BlockMessage create(Activity activity, CharSequence charSequence, CharSequence charSequence2, boolean z) {
            return create(activity, charSequence, charSequence2, z, (DialogInterface.OnCancelListener) null);
        }

        public static BlockMessage create(Activity activity, CharSequence charSequence, CharSequence charSequence2, boolean z, DialogInterface.OnCancelListener onCancelListener) {
            return new BlockMessage(activity, charSequence, charSequence2, z, onCancelListener);
        }

        public ProgressDialog buildDialog() {
            Activity activity = (Activity) this.mActivityRef.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle(this.mTitle);
            progressDialog.setMessage(this.mMessage);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(this.mCancelable);
            progressDialog.setOnCancelListener(this.mListener);
            return progressDialog;
        }
    }

    static final class FutureCancelledListener<K, T> implements OnCompletionListener<K, T> {
        public final Dialog mDialog;
        public final OnCompletionListener<K, T> mListener;

        public FutureCancelledListener(OnCompletionListener<K, T> onCompletionListener, Dialog dialog) {
            this.mListener = onCompletionListener;
            this.mDialog = dialog;
        }

        public void onCompletion(K k, T t, int i, boolean z) {
            try {
                this.mDialog.dismiss();
            } catch (IllegalArgumentException unused) {
            }
            OnCompletionListener<K, T> onCompletionListener = this.mListener;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(k, t, i, z);
            }
        }
    }

    static final class FutureListenerAdapter<K, T> implements FutureListener<AsyncResult<T>>, Runnable {
        private final K mId;
        private final JobStatusManager<K, ?> mJobManager;
        private final OnCompletionListener<K, T> mListener;
        private Future<AsyncResult<T>> mResult;

        public FutureListenerAdapter(K k, OnCompletionListener<K, T> onCompletionListener, JobStatusManager<K, ?> jobStatusManager) {
            this.mId = k;
            this.mListener = onCompletionListener;
            this.mJobManager = jobStatusManager;
        }

        public void onFutureDone(Future<AsyncResult<T>> future) {
            this.mResult = future;
            AlbumShareUIManager.sHandler.post(this);
        }

        public void run() {
            K k = this.mId;
            boolean isCancelled = this.mResult.isCancelled();
            AsyncResult asyncResult = this.mResult.get();
            int i = asyncResult != null ? asyncResult.mError : -2;
            T t = asyncResult != null ? asyncResult.mData : null;
            this.mJobManager.remove(k, i == 0);
            OnCompletionListener<K, T> onCompletionListener = this.mListener;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(k, t, i, isCancelled);
            }
        }
    }

    static class JobStatusManager<K, S> {
        private final List<OnStatusChangedListener<K, S>> mStatusChangedListeners = Lists.newArrayList();
        private final Map<K, S> mStatusMap = Maps.newHashMap();
        private final ThreadPool mThreadPool;

        public JobStatusManager(ThreadPool threadPool) {
            this.mThreadPool = threadPool;
        }

        private void notifyStatus(K k, S s) {
            for (OnStatusChangedListener<K, S> onAlbumStateChanged : this.mStatusChangedListeners) {
                onAlbumStateChanged.onAlbumStateChanged(k, s);
            }
        }

        public void add(K k, S s) {
            if (k != null) {
                this.mStatusMap.put(k, s);
                notifyStatus(k, s);
            }
        }

        /* access modifiers changed from: protected */
        public S advanceStatus(K k, S s, boolean z) {
            return null;
        }

        public S find(K k) {
            if (k != null) {
                return this.mStatusMap.get(k);
            }
            return null;
        }

        public ThreadPool getThreadPool() {
            return this.mThreadPool;
        }

        public void remove(K k, boolean z) {
            if (k != null) {
                notifyStatus(k, advanceStatus(k, this.mStatusMap.remove(k), z));
            }
        }
    }

    public interface OnCompletionListener<K, T> {
        void onCompletion(K k, T t, int i, boolean z);
    }

    public interface OnStatusChangedListener<K, S> {
        void onAlbumStateChanged(K k, S s);
    }

    /* access modifiers changed from: private */
    public static Future<?> acceptInvitationAsync(Path path, OnCompletionListener<Path, Long> onCompletionListener, BlockMessage blockMessage) {
        final CloudSharerMediaSet findSharerMediaSet = findSharerMediaSet(path);
        if (!allowOperation(findSharerMediaSet)) {
            notifyError(onCompletionListener);
            return null;
        } else if (TextUtils.isEmpty(findSharerMediaSet.getShareUrl())) {
            Log.e("AlbumShareUIManager", "acceptInvitationAsync: Invalid shareUrl, media set=" + findSharerMediaSet);
            notifyError(onCompletionListener);
            return null;
        } else {
            int accept = AlbumShareState.accept(readAlbumShareStateFromDB(findSharerMediaSet));
            if (!AlbumShareState.isValid(accept)) {
                Log.e("AlbumShareUIManager", "acceptInvitationAsync: Invalid state, media set=" + findSharerMediaSet);
                notifyError(onCompletionListener);
                return null;
            }
            final String shareUrl = findSharerMediaSet.getShareUrl();
            return submit(sAlbumShareManager, path, Integer.valueOf(accept), new ThreadPool.Job<AsyncResult<Long>>() {
                public AsyncResult<Long> run(ThreadPool.JobContext jobContext) {
                    return AlbumShareOperations.acceptInvitation(AlbumShareUIManager.getContentResolver(), shareUrl, findSharerMediaSet);
                }
            }, onCompletionListener, blockMessage);
        }
    }

    public static void addInvitationToDBAsync(final String str, OnCompletionListener<Void, Long> onCompletionListener) {
        submit(new ThreadPool.Job<AsyncResult<Long>>() {
            public AsyncResult<Long> run(ThreadPool.JobContext jobContext) {
                return AsyncResult.create(0, Long.valueOf(AlbumShareOperations.addInvitation(str)));
            }
        }, onCompletionListener);
    }

    static boolean allowOperation(CloudSharerMediaSet cloudSharerMediaSet) {
        if (cloudSharerMediaSet == null) {
            return false;
        }
        if (findAlbumShareStateFromCache(cloudSharerMediaSet.getPath()) == -1) {
            return true;
        }
        Log.e("AlbumShareUIManager", "Operation is in process, mediaSet=" + cloudSharerMediaSet);
        return false;
    }

    public static void applyToShareAlbum(Activity activity, final Path path, final OnCompletionListener<Path, Void> onCompletionListener, OnCompletionListener<Path, Long> onCompletionListener2, OnCompletionListener<Path, Void> onCompletionListener3, DialogInterface.OnCancelListener onCancelListener, final BlockMessage blockMessage, boolean z) {
        final WeakReference weakReference = new WeakReference(activity);
        final Path path2 = path;
        final boolean z2 = z;
        final OnCompletionListener<Path, Long> onCompletionListener4 = onCompletionListener2;
        final OnCompletionListener<Path, Void> onCompletionListener5 = onCompletionListener3;
        final DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        final OnCompletionListener<Path, Void> onCompletionListener6 = onCompletionListener;
        final AnonymousClass7 r0 = new OnCompletionListener<Path, String>() {
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
                r5 = com.miui.gallery.share.AlbumShareUIManager.findSharerMediaSet(r2);
             */
            public void onCompletion(Path path, String str, int i, boolean z) {
                final CloudSharerMediaSet findSharerMediaSet;
                final Activity activity = (Activity) weakReference.get();
                boolean z2 = true;
                if (i != 0 || z || activity == null || activity.isFinishing() || findSharerMediaSet == null) {
                    z2 = false;
                } else {
                    AlbumShareUIManager.doAfterCloudMediaSetSetReload(new Runnable() {
                        public void run() {
                            if (z2) {
                                AlbumShareUIManager.showDialogToAccept(activity, findSharerMediaSet, (OnCompletionListener<Path, Long>) onCompletionListener4, (OnCompletionListener<Path, Void>) onCompletionListener5, onCancelListener2);
                            } else {
                                AlbumShareUIManager.tryToAccept(findSharerMediaSet.getPath(), activity, onCompletionListener4, onCancelListener2, (Dialog) null);
                            }
                        }
                    }, path2, true);
                }
                if (!z2) {
                    if (i == -112) {
                        i = -1002;
                    }
                    AlbumShareUIManager.notifyError(onCompletionListener6, i);
                }
            }
        };
        doAfterCloudMediaSetSetReload(new Runnable() {
            public void run() {
                AlbumShareUIManager.updateShareUrlLongAuto(path, onCompletionListener, r0, blockMessage);
            }
        }, path, true);
    }

    public static Future<?> changePublicStatusAsync(final String str, final boolean z, OnCompletionListener<Void, String> onCompletionListener) {
        return submit(new ThreadPool.Job<AsyncResult<String>>() {
            public AsyncResult<String> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.changePublicStatus(str, z);
            }
        }, onCompletionListener);
    }

    private static Future<?> denyInvitationAsync(Path path, OnCompletionListener<Path, Void> onCompletionListener, BlockMessage blockMessage) {
        final CloudSharerMediaSet findSharerMediaSet = findSharerMediaSet(path);
        if (!allowOperation(findSharerMediaSet)) {
            notifyError(onCompletionListener);
            return null;
        } else if (TextUtils.isEmpty(findSharerMediaSet.getShareUrl())) {
            Log.e("AlbumShareUIManager", "denyInvitationAsync: Invalid shareUrl, media set=" + findSharerMediaSet);
            notifyError(onCompletionListener);
            return null;
        } else {
            int deny = AlbumShareState.deny(readAlbumShareStateFromDB(findSharerMediaSet));
            if (!AlbumShareState.isValid(deny)) {
                Log.e("AlbumShareUIManager", "denyInvitationAsync: Invalid state, media set=" + findSharerMediaSet);
                notifyError(onCompletionListener);
                return null;
            }
            final String shareUrl = findSharerMediaSet.getShareUrl();
            return submit(sAlbumShareManager, path, Integer.valueOf(deny), new ThreadPool.Job<AsyncResult<Void>>() {
                public AsyncResult<Void> run(ThreadPool.JobContext jobContext) {
                    return AlbumShareOperations.denyInvitation(AlbumShareUIManager.getContentResolver(), shareUrl, findSharerMediaSet);
                }
            }, onCompletionListener, blockMessage);
        }
    }

    public static void doAfterCloudMediaSetSetReload(Runnable runnable, Path path, boolean z) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public static void exitAlbumShareAsync(final Path path, final OnCompletionListener<Path, Void> onCompletionListener, final BlockMessage blockMessage) {
        doAfterCloudMediaSetSetReload(new Runnable() {
            public void run() {
                final CloudSharerMediaSet findSharerMediaSet = AlbumShareUIManager.findSharerMediaSet(path);
                if (!AlbumShareUIManager.allowOperation(findSharerMediaSet)) {
                    AlbumShareUIManager.notifyError(onCompletionListener);
                    return;
                }
                int exit = AlbumShareState.exit(AlbumShareUIManager.readAlbumShareStateFromDB(findSharerMediaSet));
                if (!AlbumShareState.isValid(exit)) {
                    Log.e("AlbumShareUIManager", "Invalid state, media set=" + findSharerMediaSet);
                    AlbumShareUIManager.notifyError(onCompletionListener);
                    return;
                }
                final String shareAlbumId = findSharerMediaSet.getShareAlbumId();
                AlbumShareUIManager.submit(AlbumShareUIManager.sAlbumShareManager, path, Integer.valueOf(exit), new ThreadPool.Job<AsyncResult<Void>>() {
                    public AsyncResult<Void> run(ThreadPool.JobContext jobContext) {
                        return AlbumShareOperations.exitAlbumShare(shareAlbumId, String.valueOf(findSharerMediaSet.getId()));
                    }
                }, onCompletionListener, blockMessage);
            }
        }, path, true);
    }

    static int findAlbumShareStateFromCache(Path path) {
        Integer find = sAlbumShareManager.find(path);
        if (find != null) {
            return find.intValue();
        }
        return -1;
    }

    public static CloudSharerMediaSet findSharerMediaSet(Path path) {
        return path.getMediaSet();
    }

    static ContentResolver getContentResolver() {
        return GalleryApp.sGetAndroidContext().getContentResolver();
    }

    public static AlbumShareOperations.IncomingInvitation getIncomingInvitation(CloudSharerMediaSet cloudSharerMediaSet) {
        return AlbumShareOperations.parseInvitation(cloudSharerMediaSet.getShareUrlLong());
    }

    public static Future<?> kickSharersAsync(final String str, final List<String> list, OnCompletionListener<Void, Pair<List<String>, List<String>>> onCompletionListener, BlockMessage blockMessage) {
        return submit(sDefaultJobManager, (Object) null, (Object) null, new ThreadPool.Job<AsyncResult<Pair<List<String>, List<String>>>>() {
            public AsyncResult<Pair<List<String>, List<String>>> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.deleteSharers(AlbumShareUIManager.getContentResolver(), str, list);
            }
        }, onCompletionListener, blockMessage);
    }

    static void notifyError(OnCompletionListener<?, ?> onCompletionListener) {
        notifyError(onCompletionListener, -110);
    }

    static void notifyError(OnCompletionListener<?, ?> onCompletionListener, int i) {
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(null, null, i, false);
        }
    }

    /* access modifiers changed from: private */
    public static void onNegativeClick(Activity activity, Path path, OnCompletionListener<Path, Void> onCompletionListener) {
        if (onCompletionListener == null) {
            onCompletionListener = new OnCompletionListener<Path, Void>() {
                public void onCompletion(Path path, Void voidR, int i, boolean z) {
                    if (z) {
                        UIHelper.toast((int) R.string.cancel_hint);
                    } else if (ServerErrorCode.isClientError(i)) {
                        UIHelper.toast((int) R.string.operation_failed);
                    } else {
                        UIHelper.toast((int) R.string.operation_successful);
                    }
                }
            };
        }
        denyInvitationAsync(path, onCompletionListener, BlockMessage.create(activity, (CharSequence) null, activity.getString(R.string.operation_in_process), false));
    }

    static int readAlbumShareStateFromDB(CloudSharerMediaSet cloudSharerMediaSet) {
        if (cloudSharerMediaSet == null) {
            return -1;
        }
        return cloudSharerMediaSet.getAlbumShareState((String) null);
    }

    static int readAlbumShareStateFromDB(Path path) {
        if (path == null) {
            return -1;
        }
        return readAlbumShareStateFromDB(path.getMediaSet());
    }

    public static Future<?> requestInvitationForSmsAsync(String str, String str2, String str3, String str4, String str5, OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation> onCompletionListener, BlockMessage blockMessage) {
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        return submit(sDefaultJobManager, (Object) null, (Object) null, new ThreadPool.Job<AsyncResult<AlbumShareOperations.OutgoingInvitation>>() {
            public AsyncResult<AlbumShareOperations.OutgoingInvitation> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.requestInvitationForSms(str6, str7, str8, str9, str10);
            }
        }, onCompletionListener, blockMessage);
    }

    private static Future<?> requestPublicUrlAsync(final String str, final boolean z, OnCompletionListener<Void, String> onCompletionListener) {
        return submit(new ThreadPool.Job<AsyncResult<String>>() {
            public AsyncResult<String> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.requestPublicUrl(str, z);
            }
        }, onCompletionListener);
    }

    public static Future<?> requestPublicUrlForOwnerAsync(String str, OnCompletionListener<Void, String> onCompletionListener) {
        return requestPublicUrlAsync(str, false, onCompletionListener);
    }

    public static Future<?> requestPublicUrlForSharerAsync(String str, OnCompletionListener<Void, String> onCompletionListener) {
        return requestPublicUrlAsync(str, true, onCompletionListener);
    }

    public static Future<?> requestUrlForBarcodeAsync(final String str, OnCompletionListener<Void, String> onCompletionListener) {
        return submit(new ThreadPool.Job<AsyncResult<String>>() {
            public AsyncResult<String> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.requestUrlForBarcode(str);
            }
        }, onCompletionListener);
    }

    private static void showDialogToAccept(Activity activity, AlbumShareOperations.IncomingInvitation incomingInvitation, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnClickListener onClickListener, DialogInterface.OnShowListener onShowListener) {
        String str = "";
        String ownerName = incomingInvitation != null ? incomingInvitation.getOwnerName() : str;
        if (incomingInvitation != null) {
            str = incomingInvitation.getAlbumName();
        }
        AlertDialog create = new AlertDialog.Builder(activity).setTitle(R.string.share_album).setMessage(activity.getString(R.string.accept_or_deny, new Object[]{ownerName, activity.getString(R.string.quotation, new Object[]{str})})).setPositiveButton(R.string.accept, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.deny, onClickListener).setOnCancelListener(onCancelListener).create();
        create.setOnShowListener(onShowListener);
        create.show();
    }

    private static void showDialogToAccept(Activity activity, AlbumShareOperations.SharerInfo sharerInfo, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnClickListener onClickListener, DialogInterface.OnShowListener onShowListener) {
        String str = sharerInfo.mAlbumNickName;
        String str2 = sharerInfo.mOwnerRelationText;
        String str3 = sharerInfo.mSharerRelationText;
        String[] stringArray = activity.getResources().getStringArray(R.array.owner_relation);
        String[] stringArray2 = activity.getResources().getStringArray(R.array.sharer_relation_addressive);
        int i = 0;
        while (true) {
            if (i >= stringArray.length) {
                break;
            } else if (stringArray[i].equalsIgnoreCase(str3)) {
                str3 = stringArray2[i];
                break;
            } else {
                i++;
            }
        }
        View inflate = activity.getLayoutInflater().inflate(R.layout.baby_album_share_invitation_dialog_layout, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.msg)).setText(activity.getString(R.string.baby_album_sharer_accept_dialog_msg, new Object[]{str, str3, str, str2, str}));
        AlertDialog create = new AlertDialog.Builder(activity, miui.R.style.Theme_Light_Dialog).setView(inflate).setPositiveButton(R.string.accept, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.deny, onClickListener).setOnCancelListener(onCancelListener).create();
        create.setOnShowListener(onShowListener);
        if (!TextUtils.isEmpty(sharerInfo.mThumbnailUrl)) {
            DisplayImageOptions build = new DisplayImageOptions.Builder().cacheOnDisc().considerExifParams(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).displayer(new PeopleItemBitmapDisplayer(false)).showStubImage(R.drawable.baby_album_sharer_default_small).usingRegionDecoderFace(true).build();
            ImageLoader.getInstance().resume();
            ImageLoader.getInstance().displayImage(sharerInfo.mThumbnailUrl, (ImageView) inflate.findViewById(R.id.baby_photo), build, (ImageSize) null, sharerInfo.mFaceRelativePos);
            create.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    ImageLoader.getInstance().pause();
                }
            });
        }
        create.show();
    }

    static void showDialogToAccept(final Activity activity, CloudSharerMediaSet cloudSharerMediaSet, final OnCompletionListener<Path, Long> onCompletionListener, final OnCompletionListener<Path, Void> onCompletionListener2, DialogInterface.OnCancelListener onCancelListener) {
        final Path path = cloudSharerMediaSet.getPath();
        AlbumShareOperations.IncomingInvitation incomingInvitation = getIncomingInvitation(cloudSharerMediaSet);
        AnonymousClass23 r2 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlbumShareUIManager.onNegativeClick(activity, path, onCompletionListener2);
            }
        };
        AnonymousClass24 r6 = new DialogInterface.OnShowListener() {
            public void onShow(final DialogInterface dialogInterface) {
                ((AlertDialog) dialogInterface).getButton(-1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        AlbumShareUIManager.tryToAccept(path, activity, onCompletionListener, (DialogInterface.OnCancelListener) null, (AlertDialog) dialogInterface);
                    }
                });
            }
        };
        AlbumShareOperations.SharerInfo sharerInfo = null;
        String sharerInfo2 = (incomingInvitation == null || !incomingInvitation.hasSharerInfo()) ? null : cloudSharerMediaSet.getSharerInfo();
        if (!TextUtils.isEmpty(sharerInfo2)) {
            sharerInfo = AlbumShareOperations.parseSharerInfo(sharerInfo2);
        }
        if (sharerInfo != null) {
            showDialogToAccept(activity, sharerInfo, onCancelListener, (DialogInterface.OnClickListener) r2, (DialogInterface.OnShowListener) r6);
        } else {
            showDialogToAccept(activity, incomingInvitation, onCancelListener, (DialogInterface.OnClickListener) r2, (DialogInterface.OnShowListener) r6);
        }
    }

    static <K, T, S> Future<AsyncResult<T>> submit(JobStatusManager<K, S> jobStatusManager, K k, S s, ThreadPool.Job<AsyncResult<T>> job, OnCompletionListener<K, T> onCompletionListener) {
        return submit(jobStatusManager, k, s, job, onCompletionListener, (BlockMessage) null);
    }

    static <K, T, S> Future<AsyncResult<T>> submit(JobStatusManager<K, S> jobStatusManager, K k, S s, ThreadPool.Job<AsyncResult<T>> job, OnCompletionListener<K, T> onCompletionListener, final BlockMessage blockMessage) {
        if (blockMessage == null) {
            jobStatusManager.add(k, s);
            return jobStatusManager.getThreadPool().submit(job, new FutureListenerAdapter(k, onCompletionListener, jobStatusManager));
        }
        ProgressDialog buildDialog = blockMessage.buildDialog();
        if (buildDialog == null) {
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(k, null, -2, false);
            }
            return null;
        }
        jobStatusManager.add(k, s);
        buildDialog.show();
        final Future<AsyncResult<T>> submit = jobStatusManager.getThreadPool().submit(job, new FutureListenerAdapter(k, new FutureCancelledListener(onCompletionListener, buildDialog), jobStatusManager));
        buildDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                submit.cancel();
                if (blockMessage.mListener != null) {
                    blockMessage.mListener.onCancel(dialogInterface);
                }
            }
        });
        return submit;
    }

    public static <T> Future<AsyncResult<T>> submit(ThreadPool.Job<AsyncResult<T>> job, OnCompletionListener<Void, T> onCompletionListener) {
        return submit(sDefaultJobManager, (Object) null, (Object) null, job, onCompletionListener);
    }

    public static Future<?> syncAllUserInfoFromNetworkAsync(OnCompletionListener<Void, Void> onCompletionListener) {
        return submit(new ThreadPool.Job<AsyncResult<Void>>() {
            public AsyncResult<Void> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.syncAllUserInfoFromNetwork();
            }
        }, onCompletionListener);
    }

    public static Future<?> syncUserListForAlbumAsync(final String str, final boolean z, OnCompletionListener<Void, Void> onCompletionListener) {
        return submit(new ThreadPool.Job<AsyncResult<Void>>() {
            public AsyncResult<Void> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.syncUserListForAlbum(str, z);
            }
        }, onCompletionListener);
    }

    public static void tryToAccept(final Path path, final Activity activity, final OnCompletionListener<Path, Long> onCompletionListener, DialogInterface.OnCancelListener onCancelListener, Dialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
        AnonymousClass26 r5 = new Runnable() {
            public void run() {
                Activity activity = activity;
                Future unused = AlbumShareUIManager.acceptInvitationAsync(path, onCompletionListener, BlockMessage.create(activity, (CharSequence) null, activity.getString(R.string.operation_in_process), false));
            }
        };
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(activity);
        if (!(xiaomiAccount != null && ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider"))) {
            onCompletionListener.onCompletion(path, null, -1003, false);
            Intent intent = new Intent(activity, LoginAndSyncForInvitationActivity.class);
            intent.putExtra("invitation_path", path);
            activity.startActivity(intent);
            return;
        }
        r5.run();
    }

    public static Future<?> tryToCreateCloudAlbumAsync(final String str, OnCompletionListener<Void, String> onCompletionListener) {
        return submit(sDefaultJobManager, (Object) null, (Object) null, new ThreadPool.Job<AsyncResult<String>>() {
            public AsyncResult<String> run(ThreadPool.JobContext jobContext) {
                return AlbumShareOperations.tryToCreateCloudAlbum(str);
            }
        }, onCompletionListener, (BlockMessage) null);
    }

    public static Future<?> updateInvitationAsync(Path path, OnCompletionListener<Path, String> onCompletionListener) {
        return updateInvitationAsync(path, onCompletionListener, (BlockMessage) null);
    }

    public static Future<?> updateInvitationAsync(Path path, OnCompletionListener<Path, String> onCompletionListener, BlockMessage blockMessage) {
        final CloudSharerMediaSet findSharerMediaSet = findSharerMediaSet(path);
        if (!allowOperation(findSharerMediaSet)) {
            notifyError(onCompletionListener);
            return null;
        } else if (TextUtils.isEmpty(findSharerMediaSet.getShareUrl())) {
            Log.e("AlbumShareUIManager", "updateInvitationAsync: Invalid shareUrl, media set=" + findSharerMediaSet);
            notifyError(onCompletionListener);
            return null;
        } else if (findSharerMediaSet.hasShareDetailInfo()) {
            Log.i("AlbumShareUIManager", "updateInvitationAsync: Already exist share detail info, media set=" + findSharerMediaSet);
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(path, findSharerMediaSet.getShareUrlLong(), 0, false);
            }
            return null;
        } else {
            int requestLongUrl = AlbumShareState.requestLongUrl(readAlbumShareStateFromDB(findSharerMediaSet));
            if (!AlbumShareState.isValid(requestLongUrl)) {
                Log.e("AlbumShareUIManager", "updateInvitationAsync: Invalid state, media set=" + findSharerMediaSet);
                notifyError(onCompletionListener);
                return null;
            }
            final String shareUrl = findSharerMediaSet.getShareUrl();
            final String shareUrlLong = findSharerMediaSet.getShareUrlLong();
            return submit(sAlbumShareManager, path, Integer.valueOf(requestLongUrl), new ThreadPool.Job<AsyncResult<String>>() {
                public AsyncResult<String> run(ThreadPool.JobContext jobContext) {
                    return AlbumShareOperations.updateInvitation(AlbumShareUIManager.getContentResolver(), shareUrl, shareUrlLong, findSharerMediaSet);
                }
            }, onCompletionListener, blockMessage);
        }
    }

    public static void updateShareUrlLongAuto(Path path, OnCompletionListener<Path, Void> onCompletionListener, OnCompletionListener<Path, String> onCompletionListener2) {
        updateShareUrlLongAuto(path, onCompletionListener, onCompletionListener2, (BlockMessage) null);
    }

    public static void updateShareUrlLongAuto(Path path, OnCompletionListener<Path, Void> onCompletionListener, OnCompletionListener<Path, String> onCompletionListener2, BlockMessage blockMessage) {
        CloudSharerMediaSet findSharerMediaSet = findSharerMediaSet(path);
        if (findSharerMediaSet != null) {
            if (!findSharerMediaSet.isNormalStatus()) {
                String shareUrlLong = findSharerMediaSet.getShareUrlLong();
                if (!TextUtils.isEmpty(shareUrlLong)) {
                    AlbumShareOperations.IncomingInvitation parseInvitation = AlbumShareOperations.parseInvitation(shareUrlLong);
                    String sharerInfo = findSharerMediaSet.getSharerInfo();
                    if ((parseInvitation != null && !parseInvitation.hasSharerInfo()) || !TextUtils.isEmpty(sharerInfo)) {
                        if (onCompletionListener2 != null) {
                            onCompletionListener2.onCompletion(path, shareUrlLong, 0, false);
                            return;
                        }
                        return;
                    }
                }
                updateInvitationAsync(path, onCompletionListener2, blockMessage);
            } else if (onCompletionListener != null) {
                onCompletionListener.onCompletion(path, null, -9, false);
            }
        } else if (onCompletionListener2 != null) {
            onCompletionListener2.onCompletion(path, null, -2, false);
        }
    }
}
