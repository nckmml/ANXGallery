package com.miui.gallery.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.extraphoto.sdk.ExtraPhotoSDK;
import com.miui.gallery.BaseConfig;
import com.miui.gallery.R;
import com.miui.gallery.adapter.PhotoPageAdapter;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.loader.BaseLoader;
import com.miui.gallery.loader.PhotoLoaderManager;
import com.miui.gallery.loader.ProcessingMediaLoader;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.provider.ProcessingMedia;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.ChooserFragment;
import com.miui.gallery.ui.PhotoPageFragmentBase;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.ui.PhotoPagerHelper;
import com.miui.gallery.ui.ShareFilePrepareFragment;
import com.miui.gallery.util.BaseFileMimeUtil;
import com.miui.gallery.util.BulkDownloadHelper;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ProcessingMediaHelper;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.widget.ViewPager;
import com.miui.gallery.widget.slip.VerticalSlipLayout;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class PhotoPageFragmentBase extends BaseBottomMenuFragment implements PhotoPageAdapter.OnPreviewedListener, PhotoPagerHelper.OnImageLoadFinishListener, PhotoPagerHelper.OnPageChangedListener, PhotoPagerHelper.OnPageSettledListener {
    private boolean isEntering = true;
    /* access modifiers changed from: private */
    public boolean isExiting = false;
    protected PhotoPageAdapter mAdapter;
    private boolean mIsShareOngoing;
    private long mLastStopMillis;
    /* access modifiers changed from: private */
    public AlertDialog mLoadingDialog;
    protected boolean mNeedConfirmPassWord;
    /* access modifiers changed from: private */
    public boolean mNeedShowLoadingDialog = false;
    protected ViewPager mPager;
    protected PhotoPagerHelper mPagerHelper;
    /* access modifiers changed from: private */
    public boolean mPendingLoadPhotos;
    /* access modifiers changed from: private */
    public boolean mPendingUpdateItem;
    private PhotoPageLoaderCallBack mPhotosLoaderCallBack;
    /* access modifiers changed from: private */
    public ProcessingMediaLoaderCallback mProcessingMediaLoaderCallback;
    protected Map<String, ProcessingMedia> mProcessingMediaMap;
    /* access modifiers changed from: private */
    public Runnable mProcessingMediaPollingRunnable;
    /* access modifiers changed from: private */
    public Runnable mShowLoadingDialogRunnable;

    private static class BackgroundLoadListener implements BaseLoader.OnLoadCompleteListener {
        private WeakReference<PhotoPageLoaderCallBack> mCallbackRef;

        public BackgroundLoadListener(PhotoPageLoaderCallBack photoPageLoaderCallBack) {
            this.mCallbackRef = new WeakReference<>(photoPageLoaderCallBack);
        }

        public void onLoadComplete(BaseLoader baseLoader, BaseDataSet baseDataSet) {
            PhotoPageLoaderCallBack photoPageLoaderCallBack;
            WeakReference<PhotoPageLoaderCallBack> weakReference = this.mCallbackRef;
            if (!(weakReference == null || baseDataSet == null || (photoPageLoaderCallBack = (PhotoPageLoaderCallBack) weakReference.get()) == null)) {
                photoPageLoaderCallBack.onLoadFinished(baseLoader, baseDataSet);
            }
            baseLoader.setBackgroundLoadListener((BaseLoader.OnLoadCompleteListener) null);
        }
    }

    abstract class ChoiceManagerBase implements PhotoPageAdapter.MultiChoiceModeListener, ChooserFragment.OnIntentSelectedListener, ChooserFragment.OnMishareClickedListener, ChooserFragment.OnProjectClickedListener {
        protected PhotoPageAdapter.ChoiceMode mChoiceMode;
        private OnFilesProcessedListener mOnFilesProcessedListener;
        protected String mShareClassName;
        protected String mSharePackageName;
        protected Intent mSharePendingIntent;
        private String[] mSupportShareBurstItemPackages = {"com.miui.mishare.connectivity", "com.android.bluetooth"};

        ChoiceManagerBase() {
        }

        /* access modifiers changed from: private */
        public void doPrepareFiles(ArrayList<String> arrayList, ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList2, ArrayList<Uri> arrayList3, ArrayList<Uri> arrayList4, List<byte[]> list, ArrayList<Uri> arrayList5, ArrayList<Long> arrayList6) {
            final ArrayList<Uri> arrayList7 = arrayList5;
            final ArrayList<Uri> arrayList8 = arrayList4;
            final List<byte[]> list2 = list;
            final ArrayList<Long> arrayList9 = arrayList6;
            AnonymousClass1 r0 = new ShareFilePrepareFragment.OnPrepareListener() {
                /* JADX WARNING: type inference failed for: r8v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                public void onPrepareComplete(List<String> list, List<String> list2, Collection<Uri> collection, Collection<Uri> collection2, List<BulkDownloadHelper.BulkDownloadItem> list3) {
                    if (MiscUtil.isValid(list)) {
                        for (String file : list) {
                            arrayList7.add(Uri.fromFile(new File(file)));
                        }
                    }
                    if (MiscUtil.isValid(collection2)) {
                        for (Uri remove : collection2) {
                            arrayList7.remove(remove);
                        }
                    }
                    if (MiscUtil.isValid(list2) || MiscUtil.isValid(list3)) {
                        final ArrayList arrayList = new ArrayList();
                        final ArrayList arrayList2 = new ArrayList();
                        final ArrayList arrayList3 = new ArrayList();
                        if (MiscUtil.isValid(list2)) {
                            arrayList.addAll(list2);
                        }
                        if (MiscUtil.isValid(collection)) {
                            arrayList3.addAll(collection);
                        }
                        if (MiscUtil.isValid(list3)) {
                            arrayList2.addAll(list3);
                            DialogUtil.showInfoDialog((Context) PhotoPageFragmentBase.this.mActivity, PhotoPageFragmentBase.this.getResources().getQuantityString(R.plurals.download_retry_message, list3.size(), new Object[]{Integer.valueOf(list3.size())}), PhotoPageFragmentBase.this.getResources().getString(R.string.download_retry_title), (int) R.string.download_retry_text, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ChoiceManagerBase.this.doPrepareFiles(arrayList, arrayList2, arrayList3, arrayList8, list2, arrayList7, arrayList9);
                                }
                            }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ChoiceManagerBase.this.mSharePendingIntent = null;
                                }
                            }, (DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialogInterface) {
                                    ChoiceManagerBase.this.mSharePendingIntent = null;
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (MiscUtil.isValid(collection)) {
                        ToastUtils.makeText((Context) PhotoPageFragmentBase.this.getActivity(), (CharSequence) PhotoPageFragmentBase.this.getActivity().getResources().getQuantityString(R.plurals.fast_share_auto_render_failed, collection.size(), new Object[]{Integer.valueOf(collection.size())}));
                    }
                    ChoiceManagerBase.this.doShare(arrayList7, true ^ arrayList8.isEmpty());
                    PhotoPageFragmentBase.this.onContentChanged();
                }
            };
            ShareFilePrepareFragment newInstance = ShareFilePrepareFragment.newInstance(arrayList, arrayList2, arrayList3, arrayList4, list, arrayList5, arrayList6);
            newInstance.setOnDownloadListener(r0);
            newInstance.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ChoiceManagerBase.this.mSharePendingIntent = null;
                }
            });
            newInstance.setErrorListener(new ShareFilePrepareFragment.OnErrorListener() {
                public void onError() {
                    ChoiceManagerBase.this.mSharePendingIntent = null;
                }
            });
            newInstance.showAllowingStateLoss(PhotoPageFragmentBase.this.mActivity.getFragmentManager(), "DownloadFragment");
        }

        private void doShare(ArrayList<Uri> arrayList) {
            doShare(arrayList, false);
        }

        /* access modifiers changed from: private */
        public void doShare(ArrayList<Uri> arrayList, boolean z) {
            if (this.mSharePendingIntent != null) {
                if (z || GalleryOpenProvider.needReturnContentUri((Context) PhotoPageFragmentBase.this.getActivity(), this.mSharePendingIntent)) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        Uri translateToContent = GalleryOpenProvider.translateToContent(arrayList.get(i).getPath());
                        arrayList.set(i, translateToContent);
                        if (this.mSharePackageName != null) {
                            PhotoPageFragmentBase.this.mActivity.grantUriPermission(this.mSharePackageName, translateToContent, 1);
                            PhotoPageFragmentBase.this.mActivity.grantUriPermission("com.miui.xman", translateToContent, 1);
                        }
                    }
                }
                if ("com.miui.mishare.connectivity".equals(this.mSharePackageName)) {
                    this.mOnFilesProcessedListener.onFilesProcessed(arrayList);
                    this.mSharePendingIntent = null;
                    return;
                }
                Log.d("PhotoPageFragmentBase", "prepare to share: %s", (Object) arrayList);
                int size = arrayList.size();
                if (size > 0) {
                    if (size > 1) {
                        this.mSharePendingIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
                    } else {
                        this.mSharePendingIntent.setAction("android.intent.action.SEND");
                        this.mSharePendingIntent.putExtra("android.intent.extra.STREAM", arrayList.get(0));
                    }
                    this.mSharePendingIntent.addFlags(268435456);
                    this.mSharePendingIntent.addFlags(134742016);
                    Intent intent = new Intent("miui.intent.action.XMAN_SHARE_MANAGER");
                    intent.setPackage("com.miui.xman");
                    String str = this.mSharePackageName;
                    if (str == null || !str.equals("com.tencent.mm") || !IntentUtil.IS_XMAN_AVAILABLE.get(null).booleanValue()) {
                        PhotoPageFragmentBase.this.startActivityForResult(this.mSharePendingIntent, 1);
                    } else {
                        intent.putExtra("android.intent.extra.INTENT", this.mSharePendingIntent);
                        PhotoPageFragmentBase.this.startActivityForResult(intent, 1);
                    }
                    if (PhotoPageFragmentBase.this.mShowLoadingDialogRunnable == null) {
                        Runnable unused = PhotoPageFragmentBase.this.mShowLoadingDialogRunnable = new Runnable() {
                            /* JADX WARNING: type inference failed for: r2v4, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                            public void run() {
                                String str;
                                if (PhotoPageFragmentBase.this.mNeedShowLoadingDialog) {
                                    ChoiceManagerBase choiceManagerBase = ChoiceManagerBase.this;
                                    String access$1300 = choiceManagerBase.getShareDisplayLabel(choiceManagerBase.mSharePackageName, ChoiceManagerBase.this.mShareClassName);
                                    if (TextUtils.isEmpty(access$1300)) {
                                        str = PhotoPageFragmentBase.this.mActivity.getResources().getString(R.string.app_is_launching_common);
                                    } else {
                                        str = PhotoPageFragmentBase.this.mActivity.getResources().getString(R.string.app_is_launching, new Object[]{access$1300});
                                    }
                                    AlertDialog unused = PhotoPageFragmentBase.this.mLoadingDialog = DialogUtil.createLoadingDialog(PhotoPageFragmentBase.this.mActivity, str);
                                    if (PhotoPageFragmentBase.this.mLoadingDialog != null) {
                                        PhotoPageFragmentBase.this.mLoadingDialog.show();
                                    }
                                }
                            }
                        };
                    }
                    if (!ThreadManager.getMainHandler().hasCallbacksCompat(PhotoPageFragmentBase.this.mShowLoadingDialogRunnable)) {
                        boolean unused2 = PhotoPageFragmentBase.this.mNeedShowLoadingDialog = true;
                        ThreadManager.getMainHandler().postDelayed(PhotoPageFragmentBase.this.mShowLoadingDialogRunnable, 500);
                    }
                }
                this.mSharePendingIntent = null;
            }
        }

        /* access modifiers changed from: private */
        public String getShareDisplayLabel(String str, String str2) {
            try {
                Resources resources = PhotoPageFragmentBase.this.mActivity.getResources();
                int identifier = resources.getIdentifier(String.format(Locale.US, "%s_%s", new Object[]{str, str2}), "string", PhotoPageFragmentBase.this.mActivity.getPackageName());
                if (identifier != 0) {
                    return resources.getString(identifier);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        private boolean supportShareBurst(String str) {
            for (String equalsIgnoreCase : this.mSupportShareBurstItemPackages) {
                if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public void configTargetIntentBySelected(int i, Intent intent) {
            String str = "video/*";
            int i2 = 1;
            if (i <= 1) {
                intent.setAction("android.intent.action.SEND");
                BaseDataItem baseDataItem = null;
                if (i > 0) {
                    baseDataItem = PhotoPageFragmentBase.this.mAdapter.getDataItem(this.mChoiceMode.getSelectItems().get(0).intValue());
                }
                if (baseDataItem == null || !baseDataItem.isVideo()) {
                    str = "image/*";
                }
                intent.setType(str);
            } else {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                BaseDataItem dataItem = PhotoPageFragmentBase.this.mAdapter.getDataItem(this.mChoiceMode.getSelectItems().get(0).intValue());
                String str2 = (dataItem == null || !dataItem.isVideo()) ? "image/*" : str;
                while (true) {
                    if (i2 >= i) {
                        break;
                    }
                    BaseDataItem dataItem2 = PhotoPageFragmentBase.this.mAdapter.getDataItem(this.mChoiceMode.getSelectItems().get(i2).intValue());
                    if (!TextUtils.equals(str2, (dataItem2 == null || !dataItem2.isVideo()) ? "image/*" : str)) {
                        str2 = "*/*";
                        break;
                    }
                    i2++;
                }
                intent.setType(str2);
            }
            ShareStateRouter.getInstance().broadcastPrintState(getPrintStatus(i));
        }

        /* access modifiers changed from: protected */
        public abstract TextView getChoiceTitle();

        /* access modifiers changed from: protected */
        public abstract int getContainerId();

        public boolean getPrintStatus(int i) {
            BaseDataItem dataItem;
            if (i != 1 || (dataItem = PhotoPageFragmentBase.this.mAdapter.getDataItem(this.mChoiceMode.getSelectItems().get(0).intValue())) == null) {
                return false;
            }
            String mimeType = dataItem.getMimeType();
            if (TextUtils.isEmpty(mimeType)) {
                return false;
            }
            return BaseFileMimeUtil.isJpgFromMimeType(mimeType) || BaseFileMimeUtil.isJpegImageFromMimeType(mimeType) || BaseFileMimeUtil.isPngImageFromMimeType(mimeType) || BaseFileMimeUtil.isGifFromMimeType(mimeType) || BaseFileMimeUtil.isBmpFromMimeType(mimeType) || BaseFileMimeUtil.isWebpFromMimeType(mimeType) || BaseFileMimeUtil.isWbmpFromMimeType(mimeType);
        }

        public /* synthetic */ void lambda$onIntentSelected$5$PhotoPageFragmentBase$ChoiceManagerBase(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5, ArrayList arrayList6, ArrayList arrayList7, DialogInterface dialogInterface, int i) {
            doPrepareFiles(arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7);
        }

        public /* synthetic */ void lambda$onIntentSelected$6$PhotoPageFragmentBase$ChoiceManagerBase(DialogInterface dialogInterface, int i) {
            this.mSharePendingIntent = null;
        }

        public /* synthetic */ void lambda$onIntentSelected$7$PhotoPageFragmentBase$ChoiceManagerBase(DialogInterface dialogInterface) {
            this.mSharePendingIntent = null;
        }

        public final void onAllItemsCheckedStateChanged(boolean z) {
            updateSelectMode();
        }

        /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        /* JADX WARNING: type inference failed for: r21v0, types: [android.content.Context] */
        /* JADX WARNING: type inference failed for: r3v12, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        /* JADX WARNING: type inference failed for: r0v19, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0112  */
        public final void onIntentSelected(Intent intent) {
            List<Integer> list;
            Iterator it;
            int i;
            Iterator it2;
            Uri uri;
            Intent intent2 = intent;
            if (intent2 != null && this.mChoiceMode != null) {
                if (this.mSharePendingIntent != null) {
                    Log.w("PhotoPageFragmentBase", "already preparing for a pending intent, skip this");
                    return;
                }
                if (intent.getComponent() != null) {
                    GallerySamplingStatHelper.recordStringPropertyEvent("fast_share", "app_selected", intent.getComponent().getClassName());
                }
                List<Integer> selectItems = this.mChoiceMode.getSelectItems();
                if (selectItems.size() > 0) {
                    int i2 = 0;
                    if (selectItems.size() > 500) {
                        ToastUtils.makeText((Context) PhotoPageFragmentBase.this.mActivity, (CharSequence) PhotoPageFragmentBase.this.mActivity.getResources().getString(R.string.send_too_many_files_error, new Object[]{500}));
                        return;
                    }
                    this.mSharePendingIntent = intent2;
                    this.mSharePackageName = intent.getComponent().getPackageName();
                    this.mShareClassName = intent.getComponent().getClassName();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    ArrayList arrayList6 = new ArrayList();
                    ArrayList<BaseDataItem> arrayList7 = new ArrayList<>();
                    ArrayList<BaseDataItem> arrayList8 = new ArrayList<>();
                    List<Integer> originItems = this.mChoiceMode.getOriginItems();
                    List<Integer> renderItems = this.mChoiceMode.getRenderItems();
                    Iterator<Integer> it3 = selectItems.iterator();
                    boolean z = false;
                    boolean z2 = false;
                    int i3 = 0;
                    while (it3.hasNext()) {
                        int intValue = it3.next().intValue();
                        Iterator<Integer> it4 = it3;
                        BaseDataItem dataItem = PhotoPageFragmentBase.this.mAdapter.getDataItem(intValue);
                        ArrayList arrayList9 = arrayList2;
                        ArrayList arrayList10 = new ArrayList();
                        if (dataItem == null || !dataItem.isBurstItem()) {
                            list = selectItems;
                        } else {
                            list = selectItems;
                            if (supportShareBurst(this.mSharePackageName)) {
                                arrayList10.addAll(dataItem.getBurstGroup());
                                if (dataItem.getBurstGroup().size() > 1) {
                                    this.mSharePendingIntent.setAction("android.intent.action.SEND_MULTIPLE");
                                }
                                it = arrayList10.iterator();
                                while (it.hasNext()) {
                                    BaseDataItem baseDataItem = (BaseDataItem) it.next();
                                    if (baseDataItem == null) {
                                        it2 = it;
                                        i = i2;
                                    } else if (PhotoPageFragmentBase.this.isProcessingMedia(baseDataItem)) {
                                        i2++;
                                    } else {
                                        byte[] secretKey = baseDataItem.getSecretKey();
                                        boolean isSecret = baseDataItem.isSecret();
                                        long key = baseDataItem.getKey();
                                        it2 = it;
                                        if (originItems.contains(Integer.valueOf(intValue))) {
                                            if (!FileUtils.isFileExist(baseDataItem.getOriginalPath())) {
                                                uri = baseDataItem.getDownloadUri();
                                                if (uri != null) {
                                                    arrayList8.add(baseDataItem);
                                                }
                                                i = i2;
                                            } else {
                                                i = i2;
                                                uri = Uri.fromFile(new File(baseDataItem.getOriginalPath()));
                                                if (!isSecret) {
                                                    arrayList.add(uri);
                                                }
                                            }
                                            z = true;
                                        } else {
                                            i = i2;
                                            uri = baseDataItem.getContentUriForExternal();
                                            if (uri == null) {
                                                uri = baseDataItem.getDownloadUri();
                                                if (uri != null) {
                                                    arrayList7.add(baseDataItem);
                                                }
                                            } else if (!isSecret) {
                                                arrayList.add(uri);
                                            }
                                        }
                                        if (renderItems.contains(Integer.valueOf(intValue)) && uri != null) {
                                            arrayList3.add(uri);
                                            z2 = true;
                                        }
                                        if (isSecret) {
                                            arrayList4.add(uri);
                                            arrayList5.add(secretKey);
                                            arrayList6.add(Long.valueOf(key));
                                        }
                                        ExtraPhotoSDK.sendSharePhotoStatic(baseDataItem.getSpecialTypeFlags());
                                        if (ImageFeatureCacheManager.getInstance().isBestImage(key, false, false, (List<Long>) null)) {
                                            i3++;
                                        }
                                    }
                                    it = it2;
                                    i2 = i;
                                }
                                int i4 = i2;
                                it3 = it4;
                                arrayList2 = arrayList9;
                                selectItems = list;
                            }
                        }
                        arrayList10.add(dataItem);
                        it = arrayList10.iterator();
                        while (it.hasNext()) {
                        }
                        int i42 = i2;
                        it3 = it4;
                        arrayList2 = arrayList9;
                        selectItems = list;
                    }
                    List<Integer> list2 = selectItems;
                    ArrayList arrayList11 = arrayList2;
                    if (i2 > 0) {
                        ToastUtils.makeText((Context) PhotoPageFragmentBase.this.mActivity, (CharSequence) PhotoPageFragmentBase.this.getResources().getQuantityString(R.plurals.send_processing_files_error, i2, new Object[]{Integer.valueOf(i2)}));
                        HashMap hashMap = new HashMap();
                        hashMap.put("count", String.valueOf(i2));
                        GallerySamplingStatHelper.recordCountEvent("fast_share", "processing_media_checked", hashMap);
                    }
                    if (z) {
                        GallerySamplingStatHelper.recordCountEvent("fast_share", "origin_checked");
                    }
                    if (z2) {
                        GallerySamplingStatHelper.recordCountEvent("fast_share", "render_checked");
                    }
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("count", Integer.valueOf(list2.size()));
                    hashMap2.put("best_image_count", Integer.valueOf(i3));
                    GallerySamplingStatHelper.recordCountEvent("fast_share", "send", hashMap2);
                    PhotoPagerSamplingStatHelper.onImageShared(arrayList);
                    if (arrayList4.size() > 0 || arrayList11.size() > 0 || arrayList8.size() > 0 || arrayList7.size() > 0 || arrayList3.size() > 0) {
                        ArrayList arrayList12 = new ArrayList();
                        for (BaseDataItem baseDataItem2 : arrayList7) {
                            arrayList12.add(new BulkDownloadHelper.BulkDownloadItem(baseDataItem2.getDownloadUri(), DownloadType.THUMBNAIL, baseDataItem2.getSize()));
                        }
                        for (BaseDataItem baseDataItem3 : arrayList8) {
                            arrayList12.add(new BulkDownloadHelper.BulkDownloadItem(baseDataItem3.getDownloadUri(), DownloadType.ORIGIN_FORCE, baseDataItem3.getSize()));
                        }
                        if (arrayList8.size() <= 0 || !NetworkUtils.isActiveNetworkMetered()) {
                            doPrepareFiles(arrayList11, arrayList12, arrayList3, arrayList4, arrayList5, arrayList, arrayList6);
                            return;
                        }
                        DialogUtil.showInfoDialog(PhotoPageFragmentBase.this.mActivity, false, PhotoPageFragmentBase.this.mActivity.getString(R.string.download_with_metered_network_msg), PhotoPageFragmentBase.this.mActivity.getString(R.string.download_with_metered_network_title), 17039370, 17039360, new DialogInterface.OnClickListener(arrayList11, arrayList12, arrayList3, arrayList4, arrayList5, arrayList, arrayList6) {
                            private final /* synthetic */ ArrayList f$1;
                            private final /* synthetic */ ArrayList f$2;
                            private final /* synthetic */ ArrayList f$3;
                            private final /* synthetic */ ArrayList f$4;
                            private final /* synthetic */ ArrayList f$5;
                            private final /* synthetic */ ArrayList f$6;
                            private final /* synthetic */ ArrayList f$7;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                                this.f$3 = r4;
                                this.f$4 = r5;
                                this.f$5 = r6;
                                this.f$6 = r7;
                                this.f$7 = r8;
                            }

                            public final void onClick(DialogInterface dialogInterface, int i) {
                                PhotoPageFragmentBase.ChoiceManagerBase.this.lambda$onIntentSelected$5$PhotoPageFragmentBase$ChoiceManagerBase(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, dialogInterface, i);
                            }
                        }, new DialogInterface.OnClickListener() {
                            public final void onClick(DialogInterface dialogInterface, int i) {
                                PhotoPageFragmentBase.ChoiceManagerBase.this.lambda$onIntentSelected$6$PhotoPageFragmentBase$ChoiceManagerBase(dialogInterface, i);
                            }
                        }, new DialogInterface.OnCancelListener() {
                            public final void onCancel(DialogInterface dialogInterface) {
                                PhotoPageFragmentBase.ChoiceManagerBase.this.lambda$onIntentSelected$7$PhotoPageFragmentBase$ChoiceManagerBase(dialogInterface);
                            }
                        });
                        return;
                    }
                    doShare(arrayList);
                    return;
                }
                ToastUtils.makeText((Context) PhotoPageFragmentBase.this.mActivity, (CharSequence) PhotoPageFragmentBase.this.getResources().getString(miui.R.string.select_item));
            }
        }

        public final void onItemCheckedStateChanged(int i, long j, boolean z) {
            updateSelectMode();
        }

        public void onMishareClicked(OnFilesProcessedListener onFilesProcessedListener) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.mishare.connectivity", "MiShareGalleryTransferView"));
            this.mOnFilesProcessedListener = onFilesProcessedListener;
            onIntentSelected(intent);
        }

        public void onProjectedClicked() {
        }

        /* access modifiers changed from: protected */
        public void onShared() {
        }

        /* access modifiers changed from: protected */
        public void onVisibilityChanged(boolean z) {
            Fragment findFragmentByTag = PhotoPageFragmentBase.this.getChildFragmentManager().findFragmentByTag("ChooserFragment");
            if (findFragmentByTag != null) {
                ((ChooserFragment) findFragmentByTag).onVisibilityChanged(z);
            }
        }

        /* access modifiers changed from: protected */
        public void setUpChooserFragment(int i, boolean z, int i2) {
            Fragment findFragmentByTag = PhotoPageFragmentBase.this.getChildFragmentManager().findFragmentByTag("ChooserFragment");
            if (findFragmentByTag == null) {
                FragmentTransaction beginTransaction = PhotoPageFragmentBase.this.getChildFragmentManager().beginTransaction();
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/*");
                ChooserFragment newInstance = ChooserFragment.newInstance(intent, i, z, i2);
                newInstance.setOnIntentSelectedListener(this);
                newInstance.setOnProjectClickedListener(this);
                newInstance.setOnMishareClickedListener(this);
                beginTransaction.add(getContainerId(), newInstance, "ChooserFragment");
                beginTransaction.commitAllowingStateLoss();
                return;
            }
            ChooserFragment chooserFragment = (ChooserFragment) findFragmentByTag;
            chooserFragment.setOnIntentSelectedListener(this);
            chooserFragment.setOnProjectClickedListener(this);
            chooserFragment.setOnMishareClickedListener(this);
        }

        /* access modifiers changed from: protected */
        public void updateSelectMode() {
            int size = this.mChoiceMode.getSelectItems().size();
            if (getChoiceTitle() != null) {
                if (size == 0) {
                    getChoiceTitle().setText(PhotoPageFragmentBase.this.getResources().getString(R.string.custom_select_title_quickly_share));
                } else {
                    getChoiceTitle().setText(PhotoPageFragmentBase.this.getResources().getQuantityString(R.plurals.custom_select_title_items_selected, size, new Object[]{Integer.valueOf(size)}));
                }
            }
            Intent intent = new Intent();
            configTargetIntentBySelected(size, intent);
            Fragment findFragmentByTag = PhotoPageFragmentBase.this.getChildFragmentManager().findFragmentByTag("ChooserFragment");
            if (findFragmentByTag != null) {
                ((ChooserFragment) findFragmentByTag).requery(intent);
            }
        }
    }

    public interface OnFilesProcessedListener {
        void onFilesProcessed(List<Uri> list);
    }

    public interface PhotoPageInteractionListener {
        int getActionBarHeight();

        int getMenuBarHeight();

        void notifyDataChanged();

        void playVideo(BaseDataItem baseDataItem);
    }

    private class PhotoPageLoaderCallBack implements LoaderManager.LoaderCallbacks {
        private boolean isFirstLoad;

        private PhotoPageLoaderCallBack() {
            this.isFirstLoad = true;
        }

        private boolean isRtl() {
            return PhotoPageFragmentBase.this.getResources().getConfiguration().getLayoutDirection() == 1;
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            return PhotoLoaderManager.getInstance().getPhotoDataSet(PhotoPageFragmentBase.this.mActivity, Uri.parse(bundle.getString("photo_uri")), bundle);
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:0x0131  */
        public void onLoadFinished(Loader loader, Object obj) {
            BaseDataSet baseDataSet = (BaseDataSet) obj;
            if (baseDataSet != null && baseDataSet.getCount() != 0) {
                int currentItem = PhotoPageFragmentBase.this.mPager.getCurrentItem();
                boolean z = true;
                if (this.isFirstLoad) {
                    boolean z2 = PhotoPageFragmentBase.this.mAdapter.getCount() != baseDataSet.getCount() && isRtl();
                    ImageLoadParams imageLoadParams = PhotoPageFragmentBase.this.getImageLoadParams();
                    if (imageLoadParams != null) {
                        int indexOfItem = baseDataSet.getIndexOfItem(new BaseDataItem().setKey(imageLoadParams.getKey()), imageLoadParams.getPos());
                        if (indexOfItem >= 0 && indexOfItem < baseDataSet.getCount() && indexOfItem != imageLoadParams.getPos()) {
                            Log.i(PhotoPageFragmentBase.this.getTAG(), "data has changed after load finish %d, %d", Integer.valueOf(imageLoadParams.getPos()), Integer.valueOf(indexOfItem));
                            imageLoadParams.updatePosition(indexOfItem);
                            z2 |= true;
                            currentItem = indexOfItem;
                        }
                    } else if (currentItem != baseDataSet.getInitPosition()) {
                        currentItem = baseDataSet.getInitPosition();
                    }
                    PhotoPageFragmentBase.this.mAdapter.changeDataSet(baseDataSet, z2);
                    if (currentItem != PhotoPageFragmentBase.this.mPager.getCurrentItem()) {
                        PhotoPageFragmentBase.this.mPager.setCurrentItem(currentItem, false);
                    } else {
                        if (PhotoPageFragmentBase.this.mAdapter.isPreviewing()) {
                            boolean unused = PhotoPageFragmentBase.this.mPendingUpdateItem = true;
                        }
                        if (!PhotoPageFragmentBase.this.mPendingUpdateItem) {
                            if (z) {
                                PhotoPageFragmentBase.this.mPagerHelper.setPageChanged(PhotoPageFragmentBase.this.mPager.getCurrentItem());
                                PhotoPageFragmentBase.this.mPagerHelper.setPageSettled(PhotoPageFragmentBase.this.mPager.getCurrentItem());
                            }
                            PhotoPageFragmentBase.this.onDataSetLoaded(baseDataSet, this.isFirstLoad);
                        }
                        this.isFirstLoad = false;
                    }
                } else {
                    PhotoPageFragmentBase.this.mAdapter.changeDataSet(baseDataSet);
                    int currentItem2 = PhotoPageFragmentBase.this.mPager.getCurrentItem();
                    String string = PhotoPageFragmentBase.this.getArguments().getString("photo_focused_path", (String) null);
                    if (!TextUtils.isEmpty(string)) {
                        PhotoPageFragmentBase.this.getArguments().remove("photo_focused_path");
                        currentItem2 = baseDataSet.getIndexOfItem(string, PhotoPageFragmentBase.this.mPager.getCurrentItem());
                    } else {
                        long j = PhotoPageFragmentBase.this.getArguments().getLong("photo_focused_id", 0);
                        if (j > 0) {
                            PhotoPageFragmentBase.this.getArguments().remove("photo_focused_id");
                            currentItem2 = baseDataSet.getIndexOfItem(j, PhotoPageFragmentBase.this.mPager.getCurrentItem());
                        }
                    }
                    if (currentItem2 >= 0 && currentItem2 < baseDataSet.getCount() && currentItem2 != PhotoPageFragmentBase.this.mPager.getCurrentItem()) {
                        PhotoPageFragmentBase.this.mPager.setCurrentItem(currentItem2, false);
                    }
                    if (!PhotoPageFragmentBase.this.mPendingUpdateItem) {
                    }
                    this.isFirstLoad = false;
                }
                z = false;
                if (!PhotoPageFragmentBase.this.mPendingUpdateItem) {
                }
                this.isFirstLoad = false;
            } else if (PhotoPageFragmentBase.this.isAdded() && PhotoPageFragmentBase.this.isResumed()) {
                Log.w(PhotoPageFragmentBase.this.getTag(), "set is empty");
                PhotoPageFragmentBase.this.finish();
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private class ProcessingMediaLoaderCallback implements LoaderManager.LoaderCallbacks<List<ProcessingMedia>> {
        private boolean isFirstLoad;
        private long start;

        private ProcessingMediaLoaderCallback() {
            this.isFirstLoad = true;
        }

        public Loader<List<ProcessingMedia>> onCreateLoader(int i, Bundle bundle) {
            this.start = System.currentTimeMillis();
            return new ProcessingMediaLoader(PhotoPageFragmentBase.this.mActivity.getApplicationContext());
        }

        public void onLoadFinished(Loader<List<ProcessingMedia>> loader, List<ProcessingMedia> list) {
            if (MiscUtil.isValid(list)) {
                PhotoPageFragmentBase.this.mProcessingMediaMap = new HashMap();
                for (ProcessingMedia next : list) {
                    PhotoPageFragmentBase.this.mProcessingMediaMap.put(next.getPath(), next);
                }
                ThreadManager.getMainHandler().removeCallbacks(PhotoPageFragmentBase.this.mProcessingMediaPollingRunnable);
                ThreadManager.getMainHandler().postDelayed(PhotoPageFragmentBase.this.getProcessingMediaPollingRunnable(), 10000);
            } else {
                PhotoPageFragmentBase.this.mProcessingMediaMap = null;
                ThreadManager.getMainHandler().removeCallbacks(PhotoPageFragmentBase.this.mProcessingMediaPollingRunnable);
            }
            ProcessingMediaHelper.getInstance().calibrateCache(list);
            PhotoPageFragmentBase.this.mAdapter.setProcessingMedias(PhotoPageFragmentBase.this.mProcessingMediaMap);
            if (!this.isFirstLoad) {
                PhotoPageFragmentBase.this.mAdapter.notifyDataSetChanged();
            } else {
                Log.d("PhotoPageFragmentBase", "ProcessingMediaLoader first load costs [%d] ms", (Object) Long.valueOf(System.currentTimeMillis() - this.start));
            }
            if (PhotoPageFragmentBase.this.mPendingLoadPhotos) {
                boolean unused = PhotoPageFragmentBase.this.mPendingLoadPhotos = false;
                PhotoPageFragmentBase.this.startToLoadPhotos();
            }
            this.isFirstLoad = false;
        }

        public void onLoaderReset(Loader<List<ProcessingMedia>> loader) {
        }
    }

    private class ProcessingMediaPollingRunnable implements Runnable {
        private ProcessingMediaPollingRunnable() {
        }

        public void run() {
            Log.d("PhotoPageFragmentBase", "Query processing medias by polling, interval: %dms", (Object) 10000);
            PhotoPageFragmentBase.this.getLoaderManager().restartLoader(1, (Bundle) null, PhotoPageFragmentBase.this.mProcessingMediaLoaderCallback);
        }
    }

    private void cancelBackgroundLoad() {
        if (getActivity() != null) {
            Loader loader = getLoaderManager().getLoader(2);
            if (loader instanceof BaseLoader) {
                ((BaseLoader) loader).setBackgroundLoadListener((BaseLoader.OnLoadCompleteListener) null);
            }
        }
    }

    private void dismissLoadingDialog() {
        AlertDialog alertDialog = this.mLoadingDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public Runnable getProcessingMediaPollingRunnable() {
        if (this.mProcessingMediaPollingRunnable == null) {
            this.mProcessingMediaPollingRunnable = new ProcessingMediaPollingRunnable();
        }
        return this.mProcessingMediaPollingRunnable;
    }

    /* access modifiers changed from: private */
    public void startToLoadPhotos() {
        Log.d("PhotoPageFragmentBase", "startToLoadPhotos");
        getLoaderManager().initLoader(2, getArguments(), this.mPhotosLoaderCallBack);
    }

    private void startToLoadProcessingMedias() {
        Log.d("PhotoPageFragmentBase", "startToLoadProcessingMedias");
        if (this.mProcessingMediaLoaderCallback != null) {
            getLoaderManager().initLoader(1, (Bundle) null, this.mProcessingMediaLoaderCallback);
            this.mPendingLoadPhotos = true;
        }
    }

    /* access modifiers changed from: protected */
    public void configViewLayout(View view, int i) {
        Resources resources = getResources();
        VerticalSlipLayout verticalSlipLayout = (VerticalSlipLayout) view.findViewById(R.id.slip_layout);
        View findViewById = view.findViewById(R.id.child_container);
        int max = Math.max(BaseConfig.ScreenConfig.getRealScreenHeight(), BaseConfig.ScreenConfig.getRealScreenWidth());
        int min = Math.min(BaseConfig.ScreenConfig.getRealScreenHeight(), BaseConfig.ScreenConfig.getRealScreenWidth());
        if (ShareStateRouter.IS_MISHARE_AVAILABLE.get(this.mActivity).booleanValue()) {
            findViewById.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.fast_share_height);
        } else {
            findViewById.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.fast_share_height_without_mishare);
        }
        verticalSlipLayout.setFixedSideSlipDistance(i);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.photo_slip_horizontal_margin);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.viewpager_page_margin);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.viewpager_slip_page_margin);
        int i2 = (max - i) - findViewById.getLayoutParams().height;
        this.mAdapter.setSlippedRect(i, i2 + i);
        this.mPager.setHeightSlipRatio((((float) i2) * 1.0f) / ((float) max));
        this.mPager.setWidthSlipRatio(1.0f - ((((float) dimensionPixelSize) * 2.0f) / ((float) min)));
        this.mPager.setMarginSlipRatio((((float) dimensionPixelSize3) * 1.0f) / ((float) dimensionPixelSize2));
    }

    /* access modifiers changed from: protected */
    public void delayDoAfterTransit() {
    }

    /* access modifiers changed from: protected */
    public void doExit() {
        if (!this.isExiting) {
            this.isExiting = true;
            onExiting();
            if (!this.mPagerHelper.doExitTransition(getItemViewInfo(this.mPager.getCurrentItem()), new PhotoPageItem.TransitionListener() {
                public void onTransitEnd() {
                    boolean unused = PhotoPageFragmentBase.this.isExiting = false;
                    PhotoPageFragmentBase.this.finish();
                }
            })) {
                finish();
                this.isExiting = false;
            }
        }
    }

    public void finish() {
        if (this.mActivity != null && !this.mActivity.isFinishing()) {
            this.mActivity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public Loader getCurrentPhotoLoader() {
        return getLoaderManager().getLoader(2);
    }

    /* access modifiers changed from: protected */
    public ItemViewInfo getEnterViewInfo(int i) {
        if (!getArguments().getBoolean("photo_enter_transit", false)) {
            return null;
        }
        getArguments().remove("photo_enter_transit");
        return getItemViewInfo(i);
    }

    /* access modifiers changed from: protected */
    public ImageLoadParams getImageLoadParams() {
        ImageLoadParams imageLoadParams = (ImageLoadParams) getArguments().getParcelable("photo_transition_data");
        if (imageLoadParams != null) {
            boolean z = false;
            if (getArguments() != null && getArguments().getBoolean("from_MiuiCamera", false)) {
                z = true;
            }
            imageLoadParams.setFromCamera(z);
        }
        return imageLoadParams;
    }

    /* access modifiers changed from: protected */
    public int getInitCount() {
        int i = getArguments().getInt("photo_count", 0);
        if (i != 0 || !getArguments().getBoolean("from_MiuiCamera", false)) {
            return i;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public ItemViewInfo getItemViewInfo(int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract View getLayout(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public PhotoPageAdapter getPhotoAdapter() {
        return new PhotoPageAdapter(getInitCount(), getImageLoadParams(), getEnterViewInfo(getArguments().getInt("photo_init_position", 0)), this, getPhotoPageInteractionListener());
    }

    /* access modifiers changed from: protected */
    public PhotoPageInteractionListener getPhotoPageInteractionListener() {
        return new PhotoPageInteractionListener() {
            public int getActionBarHeight() {
                if (!PhotoPageFragmentBase.this.isAdded() || PhotoPageFragmentBase.this.getActionBar() == null) {
                    return 0;
                }
                return PhotoPageFragmentBase.this.getActionBar().getHeight();
            }

            public int getMenuBarHeight() {
                return 0;
            }

            public void notifyDataChanged() {
                PhotoPageFragmentBase.this.onContentChanged();
            }

            public void playVideo(BaseDataItem baseDataItem) {
                PhotoPageFragmentBase.this.onPlayVideo(baseDataItem);
            }
        };
    }

    /* access modifiers changed from: protected */
    public abstract String getTAG();

    /* access modifiers changed from: protected */
    public boolean isEntering() {
        return this.isEntering;
    }

    /* access modifiers changed from: protected */
    public boolean isExiting() {
        return this.isExiting;
    }

    /* access modifiers changed from: protected */
    public boolean isNeedConfirmPassWord(BaseDataItem baseDataItem) {
        if (this.mIsShareOngoing && this.mNeedConfirmPassWord && this.mLastStopMillis > 0) {
            this.mNeedConfirmPassWord = System.currentTimeMillis() - this.mLastStopMillis > 15000;
            this.mIsShareOngoing = false;
        }
        return baseDataItem != null && baseDataItem.isSecret() && this.mNeedConfirmPassWord;
    }

    /* access modifiers changed from: protected */
    public boolean isProcessingMedia(BaseDataItem baseDataItem) {
        Map<String, ProcessingMedia> map = this.mProcessingMediaMap;
        return (map == null || baseDataItem == null || !map.containsKey(baseDataItem.getOriginalPath())) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void loadInBackground() {
        Loader loader;
        if (getActivity() != null && (loader = getLoaderManager().getLoader(2)) != null) {
            if (loader.isStarted()) {
                loader.onContentChanged();
            } else if (loader instanceof BaseLoader) {
                BaseLoader baseLoader = (BaseLoader) loader;
                baseLoader.setBackgroundLoadListener(new BackgroundLoadListener(this.mPhotosLoaderCallBack));
                baseLoader.forceLoad();
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (TextUtils.isEmpty(getArguments().getString("photo_uri"))) {
            Log.e("PhotoPageFragmentBase", "photo uri should not be null %s", (Object) getArguments());
            finish();
            return;
        }
        this.mProcessingMediaLoaderCallback = new ProcessingMediaLoaderCallback();
        this.mPhotosLoaderCallBack = new PhotoPageLoaderCallBack();
        startToLoadProcessingMedias();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            onShared();
        }
    }

    /* access modifiers changed from: protected */
    public void onContentChanged() {
        if (getActivity() != null && getLoaderManager().getLoader(2) != null) {
            getLoaderManager().getLoader(2).onContentChanged();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            restoreState(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void onDataSetLoaded(BaseDataSet baseDataSet, boolean z) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        ThreadManager.getMainHandler().removeCallbacks(this.mProcessingMediaPollingRunnable);
        getLoaderManager().destroyLoader(1);
        getLoaderManager().destroyLoader(2);
        this.mPager.removeAllViews();
    }

    public void onDetach() {
        super.onDetach();
        if (this.mShowLoadingDialogRunnable != null) {
            ThreadManager.getMainHandler().removeCallbacks(this.mShowLoadingDialogRunnable);
        }
        dismissLoadingDialog();
    }

    /* access modifiers changed from: protected */
    public void onExiting() {
    }

    public void onImageLoadFinish(String str) {
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View layout = getLayout(layoutInflater, viewGroup);
        this.mPager = (ViewPager) layout.findViewById(R.id.photo_pager);
        this.mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.viewpager_page_margin));
        this.mAdapter = getPhotoAdapter();
        this.mPagerHelper = new PhotoPagerHelper(this.mPager);
        this.mPagerHelper.setOnPageChangedListener(this);
        this.mPagerHelper.setOnPageSettledListener(this);
        this.mPagerHelper.setOnImageLoadFinishListener(this);
        onViewInflated(layout);
        this.mPager.setAdapter(this.mAdapter);
        return layout;
    }

    /* access modifiers changed from: protected */
    public void onItemChanged(int i) {
    }

    /* access modifiers changed from: protected */
    public void onItemSettled(int i) {
    }

    public final void onPageChanged(int i) {
        if (!this.mPendingUpdateItem) {
            onItemChanged(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSettled(int i) {
        if (!this.mPendingUpdateItem) {
            onItemSettled(i);
        }
    }

    public void onPause() {
        super.onPause();
        dismissLoadingDialog();
        this.mNeedShowLoadingDialog = false;
        this.mPagerHelper.onPause();
    }

    /* access modifiers changed from: protected */
    public void onPlayVideo(BaseDataItem baseDataItem) {
    }

    public final void onPreviewed() {
        this.isEntering = false;
        delayDoAfterTransit();
        if (this.mPendingUpdateItem) {
            this.mPendingUpdateItem = false;
            this.mPagerHelper.setPageChanged(this.mPager.getCurrentItem());
            this.mPagerHelper.setPageSettled(this.mPager.getCurrentItem());
            onDataSetLoaded(this.mAdapter.getDataSet(), true);
        }
    }

    public void onResume() {
        super.onResume();
        this.mPagerHelper.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onShared() {
        this.mIsShareOngoing = true;
    }

    public void onStart() {
        super.onStart();
        this.mPagerHelper.onStart();
        cancelBackgroundLoad();
    }

    public void onStop() {
        super.onStop();
        this.mPagerHelper.onStop();
        this.mLastStopMillis = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onViewInflated(View view) {
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (getArguments().containsKey("photo_init_position")) {
            int i = getArguments().getInt("photo_init_position", 0);
            int i2 = getArguments().getInt("photo_count", 0);
            if (i >= 0 && i < i2) {
                this.mPager.setCurrentItem(i, false);
                getArguments().remove("photo_init_position");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void restoreState(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt("photo_init_position", bundle.getInt("photo_init_position"));
            arguments.putInt("photo_count", bundle.getInt("photo_count"));
            arguments.remove("photo_enter_transit");
            arguments.remove("photo_transition_data");
        }
    }

    /* access modifiers changed from: protected */
    public void saveState(Bundle bundle) {
        if (bundle != null) {
            ViewPager viewPager = this.mPager;
            if (viewPager != null) {
                bundle.putInt("photo_init_position", viewPager.getCurrentItem());
            }
            PhotoPageAdapter photoPageAdapter = this.mAdapter;
            if (photoPageAdapter != null) {
                bundle.putInt("photo_count", photoPageAdapter.getCount());
            }
        }
    }
}
