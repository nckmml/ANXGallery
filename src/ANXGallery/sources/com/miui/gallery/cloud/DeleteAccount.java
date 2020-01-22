package com.miui.gallery.cloud;

import android.accounts.Account;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.os.AsyncTask;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.push.GalleryPushManager;
import com.miui.gallery.util.DeleteDataUtil;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;

public class DeleteAccount {
    private static boolean sNeedDeleteAfterSync = false;
    private static int sWipeDataStrategyAfterSync = 2;

    public interface DeleteAccountListener {
        void onFinish();
    }

    private static class DeleteAccountTask extends AsyncTask<Void, Integer, Void> {
        private Activity mActivity;
        private ProgressDialog mDialog;
        private DeleteAccountListener mListener;
        private int mWipeDataStrategy;

        public DeleteAccountTask(Activity activity, int i, DeleteAccountListener deleteAccountListener) {
            this.mActivity = activity;
            this.mWipeDataStrategy = i;
            this.mListener = deleteAccountListener;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            DeleteAccount.executeDeleteAccount(this.mWipeDataStrategy);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            ProgressDialog progressDialog = this.mDialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
                this.mDialog = null;
            }
            DeleteAccountListener deleteAccountListener = this.mListener;
            if (deleteAccountListener != null) {
                deleteAccountListener.onFinish();
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            Activity activity = this.mActivity;
            if (activity != null) {
                this.mDialog = GalleryUtils.showProgressDialog(activity, R.string.initializing_cloud, R.string.initializing_cloud, 0, false);
            }
        }
    }

    public static void deleteAccountAfterSyncIfNeeded() {
        if (sNeedDeleteAfterSync) {
            executeDeleteAccount(sWipeDataStrategyAfterSync);
            sNeedDeleteAfterSync = false;
        }
    }

    public static void deleteAccountInTask(Activity activity, Account account, int i, DeleteAccountListener deleteAccountListener) {
        if (ContentResolver.isSyncActive(account, "com.miui.gallery.cloud.provider")) {
            sNeedDeleteAfterSync = true;
            sWipeDataStrategyAfterSync = i;
            SyncLog.d("DeleteAccount", "cancel sync...");
            ContentResolver.cancelSync(account, "com.miui.gallery.cloud.provider");
        }
        new DeleteAccountTask(activity, i, deleteAccountListener).execute(new Void[0]);
    }

    public static boolean executeDeleteAccount(int i) {
        GalleryPushManager.getInstance().onDeleteAccount(GalleryApp.sGetAndroidContext());
        AlbumShareOperations.resetAccountInfo();
        SyncLog.d("DeleteAccount", "reset AlbumShareOperations when account is deleted");
        DeleteRecorder.onDeleteAccount();
        GallerySyncAdapterImpl.resetAccountInfo((Account) null, (GalleryExtendedAuthToken) null);
        boolean delete = DeleteDataUtil.delete(GalleryApp.sGetAndroidContext(), i);
        SyncLog.d("DeleteAccount", "delete data result %s", (Object) Boolean.valueOf(delete));
        new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync();
        SyncLog.i("DeleteAccount", "finish deleting account, strategy=" + i + ", result=" + delete);
        return delete;
    }
}
