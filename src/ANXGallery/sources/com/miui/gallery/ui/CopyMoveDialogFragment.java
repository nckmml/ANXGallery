package com.miui.gallery.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.util.CheckDownloadOriginHelper;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.Utils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.ArrayList;
import miui.app.ProgressDialog;

public class CopyMoveDialogFragment extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public long mAlbumId;
    /* access modifiers changed from: private */
    public boolean mDeleteOrigin;
    /* access modifiers changed from: private */
    public int mDupType;
    /* access modifiers changed from: private */
    public MediaAndAlbumOperations.OnAddAlbumListener mListener;
    /* access modifiers changed from: private */
    public long[] mMediaIds;
    /* access modifiers changed from: private */
    public ArrayList<Uri> mMediaUris;
    /* access modifiers changed from: private */
    public boolean mOperateSync;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;

    class CopyMoveTask extends AsyncTask<Void, Void, long[]> {
        private int mCloudPhotoCount;

        CopyMoveTask() {
        }

        private long[] copyOrMove() {
            if (CopyMoveDialogFragment.this.mMediaIds != null) {
                return CopyMoveDialogFragment.this.mDeleteOrigin ? CloudUtils.move(CopyMoveDialogFragment.this.mActivity, CopyMoveDialogFragment.this.mDupType, CopyMoveDialogFragment.this.mAlbumId, CopyMoveDialogFragment.this.mOperateSync, CopyMoveDialogFragment.this.mMediaIds) : CloudUtils.copy(CopyMoveDialogFragment.this.mActivity, CopyMoveDialogFragment.this.mDupType, CopyMoveDialogFragment.this.mAlbumId, CopyMoveDialogFragment.this.mMediaIds);
            }
            if (CopyMoveDialogFragment.this.mMediaUris != null) {
                return CopyMoveDialogFragment.this.mDeleteOrigin ? CloudUtils.move(CopyMoveDialogFragment.this.mActivity, CopyMoveDialogFragment.this.mAlbumId, CopyMoveDialogFragment.this.mMediaUris) : CloudUtils.copy(CopyMoveDialogFragment.this.mActivity, CopyMoveDialogFragment.this.mAlbumId, CopyMoveDialogFragment.this.mMediaUris);
            }
            return null;
        }

        private int getCloudPhotoCount() {
            int i = 0;
            if (!(CopyMoveDialogFragment.this.mActivity == null || CopyMoveDialogFragment.this.mMediaIds == null)) {
                Cursor cursor = null;
                try {
                    cursor = CopyMoveDialogFragment.this.mActivity.getContentResolver().query(GalleryContract.Media.URI, new String[0], "_id IN (" + TextUtils.join(",", MiscUtil.arrayToList(CopyMoveDialogFragment.this.mMediaIds)) + ") AND " + InternalContract.Cloud.ALIAS_LOCAL_MEDIA, (String[]) null, (String) null);
                    if (cursor != null) {
                        i = CopyMoveDialogFragment.this.mMediaIds.length - cursor.getCount();
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    Utils.closeSilently((Cursor) null);
                    throw th;
                }
                Utils.closeSilently(cursor);
            }
            return i;
        }

        private String getFailReason(Resources resources, int i, int i2) {
            return i != -111 ? resources.getString(R.string.secret_reason_unknow_reasons) : resources.getQuantityString(R.plurals.fail_reason_processing_file, i2);
        }

        private void outputResult(long[] jArr) {
            if (jArr == null || jArr.length < 1) {
                ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (int) R.string.add_to_album_failed);
                return;
            }
            Resources resources = CopyMoveDialogFragment.this.mActivity.getResources();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (long j : jArr) {
                if (j > 0) {
                    i3++;
                } else if (j == -103 || j == -104) {
                    i4++;
                } else {
                    i++;
                    if (i2 == 0) {
                        i2 = (int) j;
                    }
                }
            }
            String failReason = i > 0 ? getFailReason(resources, i2, i) : "";
            if (jArr.length > 1) {
                if (i != 0) {
                    ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (CharSequence) resources.getQuantityString(R.plurals.add_to_album_failed_with_reason_and_count, i, new Object[]{failReason, Integer.valueOf(i)}));
                } else if (jArr.length == i4) {
                    ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (int) R.string.add_to_album_exist);
                } else {
                    ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (int) R.string.add_to_album_success);
                }
            } else if (i3 == 1) {
                ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (int) R.string.add_to_album_success);
            } else if (i4 == 1) {
                ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (int) R.string.add_to_album_exist);
            } else {
                ToastUtils.makeText((Context) CopyMoveDialogFragment.this.mActivity, (CharSequence) resources.getString(R.string.add_to_album_failed_with_reason, new Object[]{failReason}));
            }
        }

        private void showCloudPhotoCountDialog(int i) {
            if (CopyMoveDialogFragment.this.mActivity != null) {
                DialogUtil.showInfoDialog(CopyMoveDialogFragment.this.mActivity, CopyMoveDialogFragment.this.mActivity.getResources().getQuantityString(R.plurals.add_to_album_cloud_photo_count_tip, i, new Object[]{Integer.valueOf(i)}), CopyMoveDialogFragment.this.mActivity.getString(R.string.add_to_album_tip));
            }
        }

        /* access modifiers changed from: protected */
        public long[] doInBackground(Void... voidArr) {
            if (GalleryPreferences.LocalMode.isOnlyShowLocalPhoto()) {
                this.mCloudPhotoCount = getCloudPhotoCount();
            }
            return DocumentProviderUtils.needRequestExternalSDCardPermission(CopyMoveDialogFragment.this.getActivity()) ? new long[]{-112} : copyOrMove();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(long[] jArr) {
            if (jArr != null && jArr.length == 1 && jArr[0] == -112) {
                DocumentProviderUtils.startExtSDCardPermissionActivityForResult(CopyMoveDialogFragment.this.getActivity());
                CopyMoveDialogFragment.this.mProgressDialog.dismiss();
                return;
            }
            outputResult(jArr);
            CopyMoveDialogFragment.this.mProgressDialog.dismiss();
            int i = this.mCloudPhotoCount;
            if (i > 0) {
                showCloudPhotoCountDialog(i);
            }
            if (CopyMoveDialogFragment.this.mListener != null) {
                CopyMoveDialogFragment.this.mListener.onComplete(jArr, CopyMoveDialogFragment.this.mDeleteOrigin);
            }
        }
    }

    /* access modifiers changed from: private */
    public void cancelAndFinish() {
        Log.d("CopyMoveDialogFragment", "cancelAndFinish");
        MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener = this.mListener;
        if (onAddAlbumListener != null) {
            onAddAlbumListener.onComplete((long[]) null, this.mDeleteOrigin);
        }
        dismissAllowingStateLoss();
    }

    private void doCheckOrigin() {
        if (this.mMediaIds == null) {
            Log.d("CopyMoveDialogFragment", "media ids is null");
            doCopyMove();
            return;
        }
        CheckDownloadOriginHelper checkDownloadOriginHelper = new CheckDownloadOriginHelper(this.mActivity, getFragmentManager(), this.mAlbumId, this.mMediaIds);
        checkDownloadOriginHelper.setListener(new CheckDownloadOriginHelper.CheckDownloadOriginListener() {
            public void onCanceled() {
                CopyMoveDialogFragment.this.cancelAndFinish();
            }

            public void onComplete() {
                CopyMoveDialogFragment.this.doCopyMove();
            }

            public void onStartDownload() {
                CopyMoveDialogFragment.this.mProgressDialog.hide();
            }
        });
        checkDownloadOriginHelper.start();
    }

    /* access modifiers changed from: private */
    public void doCopyMove() {
        Activity activity = this.mActivity;
        if (activity != null && !activity.isFinishing()) {
            Log.d("CopyMoveDialogFragment", "doCopyMove");
            try {
                this.mProgressDialog.show();
            } catch (Exception e) {
                Log.w("CopyMoveDialogFragment", "show dialog ignore %s", e);
            }
            new CopyMoveTask().executeOnExecutor(CopyMoveTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public static void show(Activity activity, long j, ArrayList<Uri> arrayList, boolean z, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
        CopyMoveDialogFragment copyMoveDialogFragment = new CopyMoveDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("arg_album_id", j);
        bundle.putParcelableArrayList("arg_media_uris", arrayList);
        bundle.putBoolean("arg_delete_origin", z);
        copyMoveDialogFragment.setArguments(bundle);
        copyMoveDialogFragment.setOnCompleteListener(onAddAlbumListener);
        copyMoveDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "CopyMoveDialogFragment");
    }

    public static void show(Activity activity, long j, long[] jArr, int i, boolean z, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
        show(activity, j, jArr, i, z, onAddAlbumListener, false);
    }

    public static void show(Activity activity, long j, long[] jArr, int i, boolean z, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener, boolean z2) {
        CopyMoveDialogFragment copyMoveDialogFragment = new CopyMoveDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("arg_album_id", j);
        bundle.putLongArray("arg_media_ids", jArr);
        bundle.putBoolean("arg_delete_origin", z);
        bundle.putInt("arg_dup_type", i);
        bundle.putBoolean("arg_operate_sync", z2);
        copyMoveDialogFragment.setArguments(bundle);
        copyMoveDialogFragment.setOnCompleteListener(onAddAlbumListener);
        copyMoveDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "CopyMoveDialogFragment");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        doCheckOrigin();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlbumId = getArguments().getLong("arg_album_id");
        this.mMediaIds = getArguments().getLongArray("arg_media_ids");
        this.mDupType = getArguments().getInt("arg_dup_type", 0);
        this.mDeleteOrigin = getArguments().getBoolean("arg_delete_origin");
        this.mOperateSync = getArguments().getBoolean("arg_operate_sync", false);
        this.mMediaUris = getArguments().getParcelableArrayList("arg_media_uris");
        this.mActivity = getActivity();
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = this.mActivity;
        this.mProgressDialog = ProgressDialog.show(activity, "", activity.getString(R.string.adding_to_album), true, false);
        return this.mProgressDialog;
    }

    public void setOnCompleteListener(MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
        this.mListener = onAddAlbumListener;
    }
}
