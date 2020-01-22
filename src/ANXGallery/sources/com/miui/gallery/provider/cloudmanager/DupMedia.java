package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class DupMedia extends BatchTaskById {
    private int mDupType;

    static class DupOperationData extends BatchCursorTask.BatchOperationData<Long> {
        Map<String, Long> sha1ToKeyMap = null;

        public DupOperationData(Long[] lArr) {
            super(lArr);
            this.sha1ToKeyMap = new HashMap(lArr.length);
        }
    }

    public DupMedia(Context context, ArrayList<Long> arrayList, long[] jArr, int i) {
        super(context, arrayList, jArr);
        this.mDupType = i;
    }

    /* access modifiers changed from: protected */
    public void executeBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        int i;
        DupOperationData dupOperationData = (DupOperationData) batchOperationData;
        LongSparseArray longSparseArray = new LongSparseArray(batchOperationData.cursor.getCount());
        batchOperationData.cursor.moveToFirst();
        while (true) {
            if (batchOperationData.cursor.isAfterLast()) {
                break;
            }
            Long l = dupOperationData.sha1ToKeyMap.get(batchOperationData.cursor.getString(9));
            if (l != null) {
                longSparseArray.put(batchOperationData.cursor.getLong(0), l);
            }
            batchOperationData.cursor.moveToNext();
        }
        if (longSparseArray.size() > 0) {
            long[] jArr = new long[longSparseArray.size()];
            for (int i2 = 0; i2 < jArr.length; i2++) {
                jArr[i2] = longSparseArray.keyAt(i2);
            }
            long[] executeDupItems = executeDupItems(sQLiteDatabase, mediaManager, jArr);
            for (i = 0; i < jArr.length; i++) {
                BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(longSparseArray.get(jArr[i]));
                long j = 0;
                if (batchItemData.result < 0) {
                    batchItemData.result = 0;
                }
                long j2 = batchItemData.result;
                if (executeDupItems[i] > 0) {
                    j = 1;
                }
                batchItemData.result = j2 + j;
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract long[] executeDupItems(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long[] jArr);

    /* access modifiers changed from: protected */
    public BatchCursorTask.BatchOperationData<Long> genBatchOperationData(Long[] lArr) {
        return new DupOperationData(lArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f5  */
    public Cursor queryCursor(SQLiteDatabase sQLiteDatabase, Long[] lArr) {
        Cursor cursor;
        Cursor cursor2;
        Long[] lArr2 = lArr;
        int i = this.mDupType;
        String str = "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND sha1 IN (SELECT sha1 FROM cloud WHERE _id IN (%s)) AND sha1 NOT NULL";
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            try {
                cursor = sQLiteDatabase.query("cloud", new String[]{"_id"}, "attributes & 4 != 0", (String[]) null, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            if (!cursor.isFirst()) {
                                sb.append(',');
                            }
                            sb.append(cursor.getLong(0));
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                str = String.format("(%s) AND %s IN (%s)", new Object[]{str, "localGroupId", sb});
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else if (i == 1) {
            try {
                cursor2 = sQLiteDatabase.query(true, "cloud", new String[]{"localGroupId"}, String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", lArr2)}), (String[]) null, (String) null, (String) null, (String) null, (String) null);
                if (cursor2 != null) {
                    try {
                        if (cursor2.getCount() > 1) {
                            Log.e("CloudManager.DupMedia", "Cannot handle dup in album with multiple albums!");
                        } else if (cursor2.moveToFirst()) {
                            str = String.format("(%s) AND %s IN (%s)", new Object[]{str, "localGroupId", Long.valueOf(cursor2.getLong(0))});
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                str = null;
                if (cursor2 != null) {
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = null;
                if (cursor2 != null) {
                }
                throw th;
            }
        } else if (i != 2) {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, String.format(str, new Object[]{TextUtils.join(",", lArr2)}), (String[]) null, (String) null, (String) null, "sha1 DESC,serverId DESC ");
    }

    /* access modifiers changed from: protected */
    public void verifyBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        super.verifyBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
        if (!batchOperationData.isAllInvalid()) {
            DupOperationData dupOperationData = (DupOperationData) batchOperationData;
            dupOperationData.fillResult(-102);
            Cursor cursor = batchOperationData.cursor;
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                long j = cursor.getLong(0);
                BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(Long.valueOf(j));
                if (batchItemData != null) {
                    dupOperationData.sha1ToKeyMap.put(cursor.getString(9), Long.valueOf(j));
                    batchItemData.cursorIndex = i;
                    batchItemData.result = verifyBatchItem(cursor);
                }
            }
        }
    }
}
