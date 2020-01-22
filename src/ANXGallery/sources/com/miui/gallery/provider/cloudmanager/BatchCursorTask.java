package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.ArrayMap;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

abstract class BatchCursorTask<K> extends BatchTask<K, BatchOperationData<K>> {
    private Context mContext;
    private ArrayList<Long> mDirtyBulk;

    static class BatchItemData {
        int cursorIndex = -1;
        long result = -1;

        BatchItemData() {
        }
    }

    static class BatchOperationData<T> {
        Cursor cursor;
        ArrayMap<T, BatchItemData> keyItemDataMap = new ArrayMap<>(this.keys.length);
        T[] keys;

        BatchOperationData(T[] tArr) {
            this.keys = tArr;
        }

        /* access modifiers changed from: package-private */
        public void copyResultsTo(long[] jArr) {
            if (this.keyItemDataMap.size() == jArr.length) {
                int i = 0;
                while (true) {
                    T[] tArr = this.keys;
                    if (i < tArr.length) {
                        BatchItemData batchItemData = this.keyItemDataMap.get(tArr[i]);
                        jArr[i] = batchItemData == null ? -102 : batchItemData.result;
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                throw new IllegalArgumentException("Expect results length to be " + this.keyItemDataMap.size() + " instead of " + jArr.length);
            }
        }

        /* access modifiers changed from: package-private */
        public void fillResult(long j) {
            fillResult(j, false);
        }

        /* access modifiers changed from: package-private */
        public void fillResult(long j, boolean z) {
            for (BatchItemData next : this.keyItemDataMap.values()) {
                if (z || next.result == -1) {
                    next.result = j;
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        public boolean isAllInvalid() {
            ArrayMap<T, BatchItemData> arrayMap = this.keyItemDataMap;
            if (arrayMap != null && arrayMap.size() > 0) {
                for (BatchItemData next : this.keyItemDataMap.values()) {
                    if (next.result == -1 || next.result >= 0) {
                        return false;
                    }
                    while (r0.hasNext()) {
                    }
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void release() {
            MiscUtil.closeSilently(this.cursor);
            this.cursor = null;
        }
    }

    public BatchCursorTask(Context context, ArrayList<Long> arrayList) {
        this.mContext = context;
        this.mDirtyBulk = arrayList;
    }

    /* access modifiers changed from: protected */
    public BatchItemData genBatchItemData() {
        return new BatchItemData();
    }

    /* access modifiers changed from: protected */
    public BatchOperationData<K> genBatchOperationData(K[] kArr) {
        return new BatchOperationData<>(kArr);
    }

    /* access modifiers changed from: protected */
    public int getBatchCount() {
        return 100;
    }

    public Context getContext() {
        return this.mContext;
    }

    public ArrayList<Long> getDirtyBulk() {
        return this.mDirtyBulk;
    }

    /* access modifiers changed from: protected */
    public final void markAsDirty(long j) {
        this.mDirtyBulk.add(Long.valueOf(j));
    }

    /* access modifiers changed from: protected */
    public final void markAsDirty(Collection<Long> collection) {
        this.mDirtyBulk.addAll(collection);
    }

    /* access modifiers changed from: protected */
    public BatchOperationData<K> prepareBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle) {
        Object[] batchExecuteKeys = getBatchExecuteKeys(bundle);
        BatchOperationData<K> genBatchOperationData = genBatchOperationData(batchExecuteKeys);
        for (Object put : batchExecuteKeys) {
            genBatchOperationData.keyItemDataMap.put(put, genBatchItemData());
        }
        Cursor cursor = null;
        try {
            cursor = queryCursor(sQLiteDatabase, batchExecuteKeys);
        } catch (Exception e) {
            Log.w("CloudManager.BatchCursorTask", (Throwable) e);
        }
        genBatchOperationData.cursor = cursor;
        return genBatchOperationData;
    }

    /* access modifiers changed from: protected */
    public abstract Cursor queryCursor(SQLiteDatabase sQLiteDatabase, K[] kArr);

    /* access modifiers changed from: protected */
    public void release() {
        this.mContext = null;
    }

    /* access modifiers changed from: protected */
    public void releaseBatchBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.clear();
        }
    }

    public long[] run(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
        Bundle bundle;
        long[] jArr = new long[getTotalCount()];
        Arrays.fill(jArr, -101);
        Log.d("CloudManager.BatchCursorTask", "%s is running", (Object) toString());
        try {
            bundle = new Bundle();
            int i = 0;
            while (i < getTotalCount()) {
                int min = Math.min(getTotalCount() - i, getBatchCount());
                int i2 = i + min;
                bundle = getBatchBundle(i, min, bundle);
                long[] runBatch = runBatch(sQLiteDatabase, mediaManager, bundle);
                if (runBatch.length != min) {
                    Log.e("CloudManager.BatchCursorTask", "%s, Invalid batch result, expecting %d results, but actually is %d", toString(), Integer.valueOf(min), Integer.valueOf(runBatch.length));
                }
                int i3 = 0;
                while (i3 < runBatch.length && i3 < min) {
                    jArr[i + i3] = runBatch[i3];
                    i3++;
                }
                releaseBatchBundle(bundle);
                i = i2;
            }
            Log.d("CloudManager.BatchCursorTask", "%s finish", (Object) toString());
            release();
            return jArr;
        } catch (Throwable th) {
            Log.d("CloudManager.BatchCursorTask", "%s finish", (Object) toString());
            release();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r1 == null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        r1.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        com.miui.gallery.util.Log.d("CloudManager.BatchCursorTask", "%s done run batch for bundle %s", toString(), describeBundle(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002a, code lost:
        if (r1 != null) goto L_0x003a;
     */
    public long[] runBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle) {
        Log.d("CloudManager.BatchCursorTask", "%s run batch for bundle %s", toString(), describeBundle(bundle));
        long[] jArr = new long[getBatchExecuteKeys(bundle).length];
        BatchOperationData batchOperationData = null;
        try {
            batchOperationData = prepareBatch(sQLiteDatabase, mediaManager, bundle);
            verifyBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
            if (!batchOperationData.isAllInvalid()) {
                executeBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
            }
            batchOperationData.copyResultsTo(jArr);
        } catch (Exception e) {
            Log.w("CloudManager.BatchCursorTask", (Throwable) e);
            Arrays.fill(jArr, -101);
        } catch (Throwable th) {
            if (batchOperationData != null) {
                batchOperationData.release();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void verifyBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchOperationData<K> batchOperationData) {
        if (batchOperationData.cursor == null) {
            Log.d("CloudManager.BatchCursorTask", "cursor for %s is null, abort", (Object) toString());
            batchOperationData.fillResult(-101);
        } else if (batchOperationData.cursor.getCount() <= 0) {
            Log.d("CloudManager.BatchCursorTask", "cursor for %s has nothing, abort", (Object) toString());
            batchOperationData.fillResult(-102);
        } else {
            batchOperationData.fillResult(-1);
        }
    }
}
