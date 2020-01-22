package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.data.DBItem;
import com.miui.gallery.util.SyncLog;

public abstract class SyncFromLocalBase {
    protected Account mAccount;
    protected Context mContext;
    protected GalleryExtendedAuthToken mExtendedAuthToken;

    public SyncFromLocalBase(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        this.mContext = context;
        this.mAccount = account;
        this.mExtendedAuthToken = galleryExtendedAuthToken;
    }

    private void syncOneBatch(Cursor cursor) throws Exception {
        if (cursor == null) {
            SyncLog.d("SyncFromLocalBase", "there is no item in local DB to sync.");
            return;
        }
        initRequestCloudItemList();
        while (cursor.moveToNext()) {
            putToRequestCloudItemList(generateDBImage(cursor));
        }
        handleRequestCloudItemList();
    }

    /* access modifiers changed from: protected */
    public abstract DBItem generateDBImage(Cursor cursor);

    /* access modifiers changed from: protected */
    public abstract Uri getBaseUri();

    /* access modifiers changed from: protected */
    public String getSelectionClause() {
        return String.format(" (%s) ", new Object[]{"localFlag != 0"});
    }

    /* access modifiers changed from: protected */
    public String getSortOrder() {
        return "dateModified DESC ";
    }

    /* access modifiers changed from: protected */
    public abstract void handleRequestCloudItemList() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void initRequestCloudItemList();

    /* access modifiers changed from: protected */
    public abstract void putToRequestCloudItemList(DBItem dBItem);

    public void sync() throws Exception {
        RegularPagingProvider regularPagingProvider = new RegularPagingProvider();
        long currentTimeMillis = System.currentTimeMillis();
        SyncLog.d("SyncFromLocalBase", "sync from local start");
        Cursor cursor = null;
        while (true) {
            try {
                String selectionClause = getSelectionClause();
                cursor = this.mContext.getContentResolver().query(CloudUtils.getLimitUri(getBaseUri(), regularPagingProvider.getQueryLimit(), regularPagingProvider.getOffset()), CloudUtils.getProjectionAll(), selectionClause, (String[]) null, getSortOrder());
                if (cursor == null) {
                    break;
                }
                SyncLog.d("SyncFromLocalBase", "start one batch, count=" + cursor.getCount());
                syncOneBatch(cursor);
                SyncLog.d("SyncFromLocalBase", "end one batch, count=" + cursor.getCount());
                if (!regularPagingProvider.updateShouldContinue(cursor)) {
                    break;
                } else if (cursor != null) {
                    cursor.close();
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        SyncLog.d("SyncFromLocalBase", "sync from local finish:" + (System.currentTimeMillis() - currentTimeMillis));
    }
}
