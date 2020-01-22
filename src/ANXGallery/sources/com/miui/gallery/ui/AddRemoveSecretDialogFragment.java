package com.miui.gallery.ui;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.util.CheckDownloadOriginHelper;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import com.miui.privacy.LockSettingsHelper;
import java.util.ArrayList;
import miui.app.ProgressDialog;

public class AddRemoveSecretDialogFragment extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public long mAlbumId;
    /* access modifiers changed from: private */
    public boolean mHasVideo;
    /* access modifiers changed from: private */
    public MediaAndAlbumOperations.OnCompleteListener mListener;
    /* access modifiers changed from: private */
    public long[] mMediaIds;
    /* access modifiers changed from: private */
    public ArrayList<Uri> mMediaUris;
    /* access modifiers changed from: private */
    public int mOperationType;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;

    class AddRemoveTask extends AsyncTask<Void, Void, long[]> {
        AddRemoveTask() {
        }

        private long[] addOrRemove() {
            Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
            if (AddRemoveSecretDialogFragment.this.mOperationType == 1) {
                return AddRemoveSecretDialogFragment.this.mMediaIds == null ? CloudUtils.addToSecret(sGetAndroidContext, (ArrayList<Uri>) AddRemoveSecretDialogFragment.this.mMediaUris) : CloudUtils.addToSecret(sGetAndroidContext, AddRemoveSecretDialogFragment.this.mMediaIds);
            }
            if (AddRemoveSecretDialogFragment.this.mOperationType == 2) {
                return CloudUtils.removeFromSecret(sGetAndroidContext, AddRemoveSecretDialogFragment.this.mAlbumId, AddRemoveSecretDialogFragment.this.mMediaIds);
            }
            return null;
        }

        private String getAddFailTip(Resources resources, int i, int i2, int i3) {
            if (i == -107) {
                return resources.getString(R.string.secret_reason_video_not_support);
            }
            String failReason = getFailReason(resources, i, i3);
            if (i2 > 1) {
                return resources.getQuantityString(R.plurals.add_to_secret_failed_with_reason_and_count, i3, new Object[]{failReason, Integer.valueOf(i3)});
            }
            return resources.getString(R.string.add_to_secret_failed_with_reason, new Object[]{failReason});
        }

        private String getFailReason(Resources resources, int i, int i2) {
            if (i == -111) {
                return resources.getQuantityString(R.plurals.fail_reason_processing_file, i2);
            }
            switch (i) {
                case -108:
                    return resources.getString(R.string.secret_reason_too_large);
                case -107:
                    return resources.getString(R.string.secret_reason_type_not_support);
                case -106:
                    return resources.getString(R.string.secret_reason_space_full);
                default:
                    return resources.getString(R.string.secret_reason_unknow_reasons);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00aa, code lost:
            if (r13 > 0) goto L_0x00ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x014e, code lost:
            if (r13 <= 0) goto L_0x015c;
         */
        private Pair<Boolean, String> outputResult(long[] jArr) {
            boolean z;
            boolean z2;
            int i;
            String str;
            String str2;
            String string;
            long[] jArr2 = jArr;
            String str3 = null;
            int i2 = 0;
            if (AddRemoveSecretDialogFragment.this.getActivity() == null) {
                return new Pair<>(false, (Object) null);
            }
            Resources resources = AddRemoveSecretDialogFragment.this.getActivity().getResources();
            if (AddRemoveSecretDialogFragment.this.mOperationType == 1) {
                if (jArr2 == null || jArr2.length < 1) {
                    str3 = AddRemoveSecretDialogFragment.this.getString(R.string.add_to_secret_fail);
                } else {
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    for (long j : jArr2) {
                        if (j > 0) {
                            i3++;
                        } else if (j == -103 || j == -104) {
                            i5++;
                        } else {
                            i4++;
                            if (i2 == 0) {
                                i2 = (int) j;
                            }
                        }
                    }
                    int length = jArr2.length;
                    int i6 = R.string.first_time_add_video_to_secret_tips;
                    if (length > 1) {
                        if (i4 != 0) {
                            string = getAddFailTip(resources, i2, jArr2.length, i4);
                        } else {
                            if (!AddRemoveSecretDialogFragment.this.mHasVideo || !GalleryPreferences.Secret.isFirstAddSecretVideo()) {
                                i6 = R.string.add_to_secret_successful;
                            }
                            string = resources.getString(i6);
                        }
                    } else if (i3 == 1) {
                        if (!AddRemoveSecretDialogFragment.this.mHasVideo || !GalleryPreferences.Secret.isFirstAddSecretVideo()) {
                            i6 = R.string.add_to_secret_successful;
                        }
                        string = resources.getString(i6);
                    } else {
                        string = i5 == 1 ? resources.getString(R.string.add_to_secret_exist) : getAddFailTip(resources, i2, jArr2.length, i4);
                    }
                    str3 = string;
                }
                z = false;
                return new Pair<>(Boolean.valueOf(z), str3);
            }
            if (AddRemoveSecretDialogFragment.this.mOperationType != 2) {
                z2 = false;
            } else if (jArr2 == null || jArr2.length < 1) {
                z2 = false;
                str3 = resources.getString(R.string.remove_from_secret_fail);
            } else {
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                for (long j2 : jArr2) {
                    int i11 = i7;
                    if (j2 > 0) {
                        i9++;
                        i7 = i11;
                    } else {
                        if (j2 != -103) {
                            if (j2 != -104) {
                                i11++;
                                if (i8 == 0) {
                                    i8 = (int) j2;
                                }
                                i7 = i11;
                            }
                        }
                        i10++;
                        i7 = i11;
                    }
                }
                int i12 = i7;
                if (i12 > 0) {
                    i = i12;
                    str = getFailReason(resources, i8, i);
                } else {
                    i = i12;
                    str = "";
                }
                if (jArr2.length > 1) {
                    str2 = i != 0 ? resources.getQuantityString(R.plurals.remove_from_secret_failed_with_reason_and_count, i, new Object[]{str, Integer.valueOf(i)}) : resources.getString(R.string.remove_from_secret_successful);
                } else if (i9 == 1) {
                    str2 = resources.getString(R.string.remove_from_secret_successful);
                } else if (i10 == 1) {
                    str2 = resources.getString(R.string.add_to_album_exist);
                } else {
                    z2 = false;
                    str3 = resources.getString(R.string.remove_from_secret_failed_with_reason, new Object[]{str});
                }
                str3 = str2;
                z2 = false;
            }
            z = z2;
            return new Pair<>(Boolean.valueOf(z), str3);
            z = true;
            return new Pair<>(Boolean.valueOf(z), str3);
        }

        /* access modifiers changed from: protected */
        public long[] doInBackground(Void... voidArr) {
            return addOrRemove();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(long[] jArr) {
            String str;
            if (AddRemoveSecretDialogFragment.this.mListener != null) {
                AddRemoveSecretDialogFragment.this.mListener.onComplete(jArr);
            }
            if (AddRemoveSecretDialogFragment.this.getActivity() != null) {
                Pair<Boolean, String> outputResult = outputResult(jArr);
                String str2 = (String) outputResult.second;
                int i = 1;
                if (!((Boolean) outputResult.first).booleanValue() || AddRemoveSecretDialogFragment.this.mOperationType != 1) {
                    i = 0;
                } else if (GalleryPreferences.Secret.isFirstAddSecret()) {
                    if (!AddRemoveSecretDialogFragment.this.mHasVideo || !GalleryPreferences.Secret.isFirstAddSecretVideo()) {
                        i = 0;
                    } else {
                        str2 = AddRemoveSecretDialogFragment.this.getString(R.string.first_time_add_video_to_secret_tips);
                        GalleryPreferences.Secret.setFirstAddSecretVideo(false);
                    }
                    JumpDialogFragment.enterPrivateAlbum(AddRemoveSecretDialogFragment.this.getActivity());
                } else {
                    if (!TextUtils.isEmpty(str2)) {
                        str = String.format("%s%s%s", new Object[]{str2, AddRemoveSecretDialogFragment.this.getString(R.string.name_split), AddRemoveSecretDialogFragment.this.getString(R.string.alert_secret_album_message_lower_case)});
                    } else {
                        str = AddRemoveSecretDialogFragment.this.getString(R.string.alert_secret_album_message);
                    }
                    str2 = str;
                    if (AddRemoveSecretDialogFragment.this.mHasVideo && GalleryPreferences.Secret.isFirstAddSecretVideo()) {
                        GalleryPreferences.Secret.setFirstAddSecretVideo(false);
                    }
                }
                if (str2 != null) {
                    ToastUtils.makeText((Context) AddRemoveSecretDialogFragment.this.getActivity(), (CharSequence) str2, i);
                }
                AddRemoveSecretDialogFragment.this.dismissAllowingStateLoss();
            }
        }
    }

    public static void add(Activity activity, MediaAndAlbumOperations.OnCompleteListener onCompleteListener, boolean z, ArrayList<Uri> arrayList) {
        AddRemoveSecretDialogFragment addRemoveSecretDialogFragment = new AddRemoveSecretDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("arg_operation_type", 1);
        bundle.putParcelableArrayList("arg_media_uris", arrayList);
        bundle.putBoolean("arg_has_video", z);
        addRemoveSecretDialogFragment.setArguments(bundle);
        addRemoveSecretDialogFragment.setOnCompleteListener(onCompleteListener);
        addRemoveSecretDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddRemoveSecretDialogFragment");
    }

    public static void add(Activity activity, MediaAndAlbumOperations.OnCompleteListener onCompleteListener, boolean z, long... jArr) {
        AddRemoveSecretDialogFragment addRemoveSecretDialogFragment = new AddRemoveSecretDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("arg_operation_type", 1);
        bundle.putLongArray("arg_media_ids", jArr);
        bundle.putBoolean("arg_has_video", z);
        addRemoveSecretDialogFragment.setArguments(bundle);
        addRemoveSecretDialogFragment.setOnCompleteListener(onCompleteListener);
        addRemoveSecretDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddRemoveSecretDialogFragment");
    }

    private void addOrRemoveSecret() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("key_check_gallery_sync", true);
        bundle.putSerializable("cloud_guide_source", GalleryIntent.CloudGuideSource.SECRET);
        LoginAndSyncCheckFragment.checkLoginAndSyncState(this, bundle);
    }

    private void checkPrivateGalleryEnabled() {
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(getActivity());
        if (xiaomiAccount == null) {
            return;
        }
        if (!ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider") && !SyncUtil.setSyncAutomatically(getActivity(), true)) {
            return;
        }
        if (!new LockSettingsHelper(getActivity()).isPrivacyPasswordEnabled()) {
            GalleryPreferences.Secret.setIsFirstAddSecret(true);
            GalleryPreferences.Secret.setFirstAddSecretVideo(true);
            LockSettingsHelper.startSetPrivacyPasswordActivity(this, 28);
            return;
        }
        doAddOrRemoveSecret();
    }

    private void doAddOrRemoveSecret() {
        if (this.mOperationType != 2 || this.mMediaIds == null) {
            startAddOrRemoveSecretTask();
            return;
        }
        CheckDownloadOriginHelper checkDownloadOriginHelper = new CheckDownloadOriginHelper(getActivity(), getFragmentManager(), this.mAlbumId, this.mMediaIds);
        checkDownloadOriginHelper.setListener(new CheckDownloadOriginHelper.CheckDownloadOriginListener() {
            public void onCanceled() {
                if (AddRemoveSecretDialogFragment.this.mListener != null) {
                    AddRemoveSecretDialogFragment.this.mListener.onComplete((long[]) null);
                }
                AddRemoveSecretDialogFragment.this.dismissAllowingStateLoss();
            }

            public void onComplete() {
                AddRemoveSecretDialogFragment.this.startAddOrRemoveSecretTask();
            }

            public void onStartDownload() {
                AddRemoveSecretDialogFragment.this.mProgressDialog.hide();
            }
        });
        checkDownloadOriginHelper.start();
    }

    public static void remove(Activity activity, MediaAndAlbumOperations.OnCompleteListener onCompleteListener, long j, long... jArr) {
        AddRemoveSecretDialogFragment addRemoveSecretDialogFragment = new AddRemoveSecretDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("arg_operation_type", 2);
        bundle.putLongArray("arg_media_ids", jArr);
        bundle.putLong("arg_album_id", j);
        addRemoveSecretDialogFragment.setArguments(bundle);
        addRemoveSecretDialogFragment.setOnCompleteListener(onCompleteListener);
        addRemoveSecretDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddRemoveSecretDialogFragment");
    }

    /* access modifiers changed from: private */
    public void startAddOrRemoveSecretTask() {
        this.mProgressDialog.show();
        new AddRemoveTask().executeOnExecutor(AddRemoveTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        addOrRemoveSecret();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            MediaAndAlbumOperations.OnCompleteListener onCompleteListener = this.mListener;
            if (onCompleteListener != null) {
                onCompleteListener.onComplete((long[]) null);
            }
            dismissAllowingStateLoss();
        } else if (i == 28) {
            doAddOrRemoveSecret();
        } else if (i == 29) {
            checkPrivateGalleryEnabled();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mOperationType = getArguments().getInt("arg_operation_type");
        int i = this.mOperationType;
        if (i == 1) {
            this.mMediaIds = getArguments().getLongArray("arg_media_ids");
            this.mMediaUris = getArguments().getParcelableArrayList("arg_media_uris");
            if (this.mMediaIds == null && this.mMediaUris == null) {
                throw new IllegalArgumentException("ids or uris can't be null");
            }
            this.mHasVideo = getArguments().getBoolean("arg_has_video", false);
        } else if (i == 2) {
            this.mAlbumId = getArguments().getLong("arg_album_id");
            if (this.mAlbumId > 0) {
                this.mMediaIds = getArguments().getLongArray("arg_media_ids");
                if (this.mMediaIds == null) {
                    throw new IllegalArgumentException("ids can't be null");
                }
            } else {
                throw new IllegalArgumentException("albumId must > 0");
            }
        } else {
            throw new IllegalArgumentException("unsupport operation");
        }
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getActivity().getString(R.string.adding_to_album), true, false);
        return this.mProgressDialog;
    }

    public void setOnCompleteListener(MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        this.mListener = onCompleteListener;
    }
}
