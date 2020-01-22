package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import java.util.ArrayList;

abstract class OwnerShareSeparatorTask extends SubTaskSeparatorTask {
    public OwnerShareSeparatorTask(Context context, ArrayList<Long> arrayList, long[] jArr) {
        super(context, arrayList, jArr);
    }

    protected static long[] toShareImageIds(long[] jArr) {
        if (jArr == null || jArr.length <= 0) {
            return jArr;
        }
        long[] jArr2 = new long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            jArr2[i] = ShareMediaManager.getOriginalMediaId(jArr[i]);
        }
        return jArr2;
    }

    /* access modifiers changed from: protected */
    public abstract long[] executeOwner(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr);

    /* access modifiers changed from: protected */
    public abstract long[] executeSharer(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr);

    /* access modifiers changed from: protected */
    public long[] executeType(int i, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr) {
        if (i == 0) {
            return executeOwner(sQLiteDatabase, mediaManager, batchOperationData, jArr);
        }
        if (i == 1) {
            return executeSharer(sQLiteDatabase, mediaManager, batchOperationData, toShareImageIds(jArr));
        }
        throw new IllegalArgumentException("Type " + i + " is not supported!");
    }

    /* access modifiers changed from: protected */
    public int getItemTaskType(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData, long j) {
        return ShareMediaManager.isOtherShareMediaId(j) ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public Cursor queryCursor(SQLiteDatabase sQLiteDatabase, Long[] lArr) {
        return null;
    }
}
