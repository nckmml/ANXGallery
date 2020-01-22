package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

abstract class SubTaskSeparatorTask extends BatchTaskById {

    private static class OperationData extends BatchCursorTask.BatchOperationData<Long> {
        ArrayList<Pair<Integer, ArrayList<Long>>> typeIdsArrays = new ArrayList<>();

        OperationData(Long[] lArr) {
            super(lArr);
        }

        /* access modifiers changed from: package-private */
        public void putItemToType(long j, int i) {
            Pair pair;
            String str;
            if (this.typeIdsArrays.size() <= 0) {
                pair = null;
            } else {
                ArrayList<Pair<Integer, ArrayList<Long>>> arrayList = this.typeIdsArrays;
                pair = arrayList.get(arrayList.size() - 1);
            }
            if (pair == null || ((Integer) pair.first).intValue() != i) {
                Integer valueOf = Integer.valueOf(i);
                if (pair == null) {
                    str = "null";
                } else {
                    str = pair.first + ", count:" + ((ArrayList) pair.second).size();
                }
                Log.d("CloudManager.SubTaskSeparatorTask", "New type group of [%d] is created! Last group is [%s]", valueOf, str);
                pair = new Pair(Integer.valueOf(i), new ArrayList());
                this.typeIdsArrays.add(pair);
            }
            ((ArrayList) pair.second).add(Long.valueOf(j));
        }
    }

    public SubTaskSeparatorTask(Context context, ArrayList<Long> arrayList, long[] jArr) {
        super(context, arrayList, jArr);
    }

    private void applyResult(BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr, ArrayList<Long> arrayList) {
        for (int i = 0; i < jArr.length; i++) {
            BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(arrayList.get(i));
            if (batchItemData != null) {
                batchItemData.result = jArr[i];
            }
        }
    }

    /* access modifiers changed from: protected */
    public void executeBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        BatchCursorTask.BatchOperationData<Long> batchOperationData2 = batchOperationData;
        OperationData operationData = (OperationData) batchOperationData2;
        for (int i = 0; i < operationData.typeIdsArrays.size(); i++) {
            Pair pair = operationData.typeIdsArrays.get(i);
            if (pair.second != null && ((ArrayList) pair.second).size() > 0) {
                Log.d("CloudManager.SubTaskSeparatorTask", "[%s] Start execute type %d for ids [%s]", toString(), pair.first, TextUtils.join(",", (Iterable) pair.second));
                long[] executeType = executeType(((Integer) pair.first).intValue(), sQLiteDatabase, mediaManager, batchOperationData, Numbers.toArray((List) pair.second));
                Log.d("CloudManager.SubTaskSeparatorTask", "[%s] Result [%s]", toString(), StringUtils.join(",", executeType));
                applyResult(batchOperationData2, executeType, (ArrayList) pair.second);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract long[] executeType(int i, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr);

    /* access modifiers changed from: protected */
    public BatchCursorTask.BatchOperationData<Long> genBatchOperationData(Long[] lArr) {
        return new OperationData(lArr);
    }

    /* access modifiers changed from: protected */
    public abstract int getItemTaskType(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData, long j);

    /* access modifiers changed from: protected */
    public void verifyBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        int itemTaskType;
        BatchCursorTask.BatchOperationData<Long> batchOperationData2 = batchOperationData;
        super.verifyBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
        OperationData operationData = (OperationData) batchOperationData2;
        for (Long longValue : (Long[]) operationData.keys) {
            long longValue2 = longValue.longValue();
            BatchCursorTask.BatchItemData batchItemData = batchOperationData2.keyItemDataMap.get(Long.valueOf(longValue2));
            if (!(batchItemData == null || (itemTaskType = getItemTaskType(sQLiteDatabase, mediaManager, bundle, batchOperationData, longValue2)) == -1)) {
                operationData.putItemToType(longValue2, itemTaskType);
                batchItemData.result = -1;
            }
        }
    }
}
