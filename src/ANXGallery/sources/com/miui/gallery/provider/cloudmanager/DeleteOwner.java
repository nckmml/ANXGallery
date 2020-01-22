package com.miui.gallery.provider.cloudmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import com.miui.gallery.util.logger.TimingTracing;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class DeleteOwner extends BatchTaskById {
    private final String TRACE_TAG = String.format("CloudManager.DeleteOwner{%s}", new Object[]{Long.valueOf(Thread.currentThread().getId())});
    private int mDeleteReason;
    private boolean mNotDeleteOriginFlag;

    public DeleteOwner(Context context, ArrayList<Long> arrayList, long[] jArr, int i) {
        super(context, arrayList, jArr);
        this.mDeleteReason = i;
    }

    public DeleteOwner(Context context, ArrayList<Long> arrayList, long[] jArr, boolean z, int i) {
        super(context, arrayList, jArr);
        this.mNotDeleteOriginFlag = z;
        this.mDeleteReason = i;
    }

    /* access modifiers changed from: protected */
    public void executeBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        BatchCursorTask.BatchOperationData<Long> batchOperationData2 = batchOperationData;
        String format = String.format("DeleteOwner.executeBatch{%s}", new Object[]{Long.valueOf(Thread.currentThread().getId())});
        TimingTracing.beginTracing(format, String.format("count{%s}", new Object[]{Integer.valueOf(batchOperationData2.keyItemDataMap.size())}));
        sQLiteDatabase.beginTransaction();
        TimingTracing.addSplit(format, "beginTransaction");
        try {
            ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            for (Map.Entry next : batchOperationData2.keyItemDataMap.entrySet()) {
                if (((BatchCursorTask.BatchItemData) next.getValue()).result != -1) {
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                } else if (((BatchCursorTask.BatchItemData) next.getValue()).cursorIndex >= 0) {
                    batchOperationData2.cursor.moveToPosition(((BatchCursorTask.BatchItemData) next.getValue()).cursorIndex);
                    ContentValues contentValues = new ContentValues();
                    if (this.mNotDeleteOriginFlag) {
                        contentValues.put("localFile", "");
                        Log.d("CloudManager.DeleteOwner", "DELETE ITEM: not delete origin file: %d", next.getKey());
                    }
                    if (batchOperationData2.cursor.isNull(4)) {
                        Log.d("CloudManager.DeleteOwner", "DELETE ITEM: no server id found, update to invalid record: %d", next.getKey());
                        contentValues.put("localFlag", -1);
                    } else {
                        Log.d("CloudManager.DeleteOwner", "DELETE ITEM: server id found, mark delete %d", next.getKey());
                        contentValues.put("localFlag", 2);
                    }
                    arrayList2.add(next.getKey());
                    try {
                        ((BatchCursorTask.BatchItemData) next.getValue()).result = (long) sQLiteDatabase.update("cloud", contentValues, "_id = ? ", new String[]{((Long) next.getKey()).toString()});
                        TimingTracing.addSplit(format, "update");
                        DeleteRecord createDeleteRecord = CloudManager.createDeleteRecord(this.mDeleteReason, batchOperationData2.cursor, "CloudManager.DeleteOwner");
                        if (createDeleteRecord != null) {
                            arrayList.add(createDeleteRecord);
                        }
                    } catch (Exception unused) {
                        try {
                            batchOperationData2.fillResult(-110);
                            sQLiteDatabase.endTransaction();
                            TimingTracing.addSplit(format, "endTransaction");
                        } catch (Throwable th) {
                            th = th;
                            sQLiteDatabase.endTransaction();
                            TimingTracing.addSplit(format, "endTransaction");
                            throw th;
                        }
                    }
                }
            }
            SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
            sQLiteDatabase.setTransactionSuccessful();
            TimingTracing.addSplit(format, "setTransactionSuccessful");
            if (MiscUtil.isValid(arrayList)) {
                DeleteRecorder.record((Collection<DeleteRecord>) arrayList);
                TimingTracing.addSplit(format, "deleteRecords");
            }
            if (MiscUtil.isValid(arrayList2)) {
                ThreadManager.getWorkHandler().post(new Runnable() {
                    public void run() {
                        ImageFeatureManager.getInstance().onImageBatchDelete(arrayList2);
                    }
                });
            }
            sQLiteDatabase.endTransaction();
            TimingTracing.addSplit(format, "endTransaction");
            ArrayList arrayList3 = new ArrayList();
            for (Map.Entry next2 : batchOperationData2.keyItemDataMap.entrySet()) {
                if (((BatchCursorTask.BatchItemData) next2.getValue()).result > 0) {
                    arrayList3.add(next2.getKey());
                }
            }
            if (arrayList3.size() > 0) {
                MediaManager mediaManager2 = mediaManager;
                mediaManager2.delete(String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", arrayList3)}), (String[]) null);
                markAsDirty((Collection<Long>) arrayList3);
            }
            TimingTracing.addSplit(format, "managerDelete");
            StringBuilder sb = new StringBuilder();
            long stopTracing = TimingTracing.stopTracing(format, new StringBuilderPrinter(sb));
            if (batchOperationData2.keyItemDataMap.size() > 0 && stopTracing > ((long) batchOperationData2.keyItemDataMap.size()) * 100) {
                Log.w("CloudManager.DeleteOwner", "delete slowly: %s", sb.toString());
                HashMap hashMap = new HashMap();
                hashMap.put("cost", String.valueOf(stopTracing / ((long) batchOperationData2.keyItemDataMap.size())));
                hashMap.put("detail", sb.toString());
                GallerySamplingStatHelper.recordErrorEvent("delete_performance", "CloudManager.DeleteOwner", hashMap);
            }
            TimingTracing.addSplit(this.TRACE_TAG, "executeBatch");
        } catch (Exception unused2) {
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase;
            batchOperationData2.fillResult(-110);
            sQLiteDatabase.endTransaction();
            TimingTracing.addSplit(format, "endTransaction");
        } catch (Throwable th2) {
            th = th2;
            SQLiteDatabase sQLiteDatabase5 = sQLiteDatabase;
            sQLiteDatabase.endTransaction();
            TimingTracing.addSplit(format, "endTransaction");
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public BatchCursorTask.BatchOperationData<Long> prepareBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle) {
        try {
            return super.prepareBatch(sQLiteDatabase, mediaManager, bundle);
        } finally {
            TimingTracing.addSplit(this.TRACE_TAG, "prepareBatch");
        }
    }

    /* access modifiers changed from: protected */
    public Cursor queryCursor(SQLiteDatabase sQLiteDatabase, Long[] lArr) {
        return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", lArr)}), (String[]) null, (String) null, (String) null, (String) null);
    }

    public long[] run(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
        TimingTracing.beginTracing(this.TRACE_TAG, String.format(Locale.US, "count{%s}, reason{%s}", new Object[]{Integer.valueOf(getTotalCount()), Integer.valueOf(this.mDeleteReason)}));
        try {
            return super.run(sQLiteDatabase, mediaManager);
        } finally {
            TimingTracing.stopTracing(this.TRACE_TAG, (Printer) null);
        }
    }

    /* access modifiers changed from: protected */
    public void verifyBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        try {
            super.verifyBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
        } finally {
            TimingTracing.addSplit(this.TRACE_TAG, "verifyBatch");
        }
    }

    /* access modifiers changed from: protected */
    public long verifyBatchItem(Cursor cursor) {
        if (cursor.getInt(5) == 0) {
            Log.w("CloudManager.DeleteOwner", "Album can't be deleted here, use DeleteAlbum instead");
            return -100;
        } else if (!ShareMediaManager.isOtherShareMediaId(cursor.getLong(0))) {
            return -1;
        } else {
            Log.w("CloudManager.DeleteOwner", "Share medias can't be deleted here, use DeleteSharer instead");
            return -100;
        }
    }
}
