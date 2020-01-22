package com.miui.gallery.provider.cloudmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class DeleteShare extends BatchTaskById {
    private int mDeleteReason;
    private String mUserId = AccountCache.getAccount().name;

    public DeleteShare(Context context, ArrayList<Long> arrayList, long[] jArr, int i) {
        super(context, arrayList, jArr);
        this.mDeleteReason = i;
    }

    /* access modifiers changed from: protected */
    public void executeBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        sQLiteDatabase.beginTransaction();
        try {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : batchOperationData.keyItemDataMap.entrySet()) {
                if (((BatchCursorTask.BatchItemData) next.getValue()).result == -1) {
                    batchOperationData.cursor.moveToPosition(((BatchCursorTask.BatchItemData) next.getValue()).cursorIndex);
                    if (batchOperationData.cursor.isNull(4)) {
                        Log.d("CloudManager.DeleteShare", "DELETE ITEM: no server id found, update to invalid record: %d", next.getKey());
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("localFlag", -1);
                        ((BatchCursorTask.BatchItemData) next.getValue()).result = (long) sQLiteDatabase.update("shareImage", contentValues, "_id = ? ", new String[]{((Long) next.getKey()).toString()});
                    } else {
                        Log.d("CloudManager.DeleteShare", "DELETE ITEM: server id found, mark delete %d", next.getKey());
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("localFlag", 2);
                        ((BatchCursorTask.BatchItemData) next.getValue()).result = (long) sQLiteDatabase.update("shareImage", contentValues2, "_id = ? ", new String[]{((Long) next.getKey()).toString()});
                    }
                    DeleteRecord createDeleteRecord = CloudManager.createDeleteRecord(this.mDeleteReason, batchOperationData.cursor, "CloudManager.DeleteShare");
                    if (createDeleteRecord != null) {
                        arrayList.add(createDeleteRecord);
                    }
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (MiscUtil.isValid(arrayList)) {
                DeleteRecorder.record((Collection<DeleteRecord>) arrayList);
            }
            sQLiteDatabase.endTransaction();
            for (Map.Entry next2 : batchOperationData.keyItemDataMap.entrySet()) {
                if (((BatchCursorTask.BatchItemData) next2.getValue()).result > 0) {
                    markAsDirty(ShareMediaManager.convertToMediaId(((Long) next2.getKey()).longValue()));
                }
            }
        } catch (Exception unused) {
            batchOperationData.fillResult(-110);
            sQLiteDatabase.endTransaction();
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public Cursor queryCursor(SQLiteDatabase sQLiteDatabase, Long[] lArr) {
        return sQLiteDatabase.query("shareImage", CloudManager.PROJECTION, String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", lArr)}), (String[]) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public long verifyBatchItem(Cursor cursor) {
        if (cursor.getInt(5) == 0) {
            Log.w("CloudManager.DeleteShare", "Album can't be deleted here, use DeleteAlbum instead");
            return -100;
        } else if (TextUtils.isEmpty(this.mUserId)) {
            Log.w("CloudManager.DeleteShare", "Account doesn't exist!");
            return -100;
        } else if (TextUtils.isEmpty(cursor.getString(4)) || this.mUserId.equals(cursor.getString(51))) {
            return -1;
        } else {
            Log.w("CloudManager.DeleteShare", "User's deleting other's media item!");
            return -100;
        }
    }
}
