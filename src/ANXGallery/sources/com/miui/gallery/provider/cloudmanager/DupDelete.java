package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.logger.TimingTracing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

class DupDelete extends DupMedia {
    private final String TRACE_TAG = String.format("CloudManager.DupDelete{%s}", new Object[]{Long.valueOf(Thread.currentThread().getId())});
    private int mDeleteReason;

    public DupDelete(Context context, ArrayList<Long> arrayList, long[] jArr, int i, int i2) {
        super(context, arrayList, jArr, i);
        this.mDeleteReason = i2;
    }

    /* access modifiers changed from: protected */
    public void executeBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData) {
        try {
            super.executeBatch(sQLiteDatabase, mediaManager, bundle, batchOperationData);
        } finally {
            TimingTracing.addSplit(this.TRACE_TAG, "executeBatch");
        }
    }

    /* access modifiers changed from: protected */
    public long[] executeDupItems(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long[] jArr) {
        return new DeleteOwner(getContext(), getDirtyBulk(), jArr, this.mDeleteReason).run(sQLiteDatabase, mediaManager);
    }

    /* access modifiers changed from: protected */
    public BatchCursorTask.BatchOperationData<Long> prepareBatch(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle) {
        try {
            return super.prepareBatch(sQLiteDatabase, mediaManager, bundle);
        } finally {
            TimingTracing.addSplit(this.TRACE_TAG, "prepareBatch");
        }
    }

    public long[] run(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
        TimingTracing.beginTracing(this.TRACE_TAG, String.format(Locale.US, "count{%s}, reason{%s}", new Object[]{Integer.valueOf(getTotalCount()), Integer.valueOf(this.mDeleteReason)}));
        try {
            return super.run(sQLiteDatabase, mediaManager);
        } finally {
            StringBuilder sb = new StringBuilder();
            long stopTracing = TimingTracing.stopTracing(this.TRACE_TAG, new StringBuilderPrinter(sb));
            if (getTotalCount() > 0 && stopTracing > ((long) getTotalCount()) * 100) {
                Log.w("CloudManager.DupDelete", "delete slowly: %s", sb.toString());
                HashMap hashMap = new HashMap();
                hashMap.put("cost", String.valueOf(stopTracing / ((long) getTotalCount())));
                hashMap.put("detail", sb.toString());
                GallerySamplingStatHelper.recordErrorEvent("delete_performance", "CloudManager.DupDelete", hashMap);
            }
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
            Log.w("CloudManager.DupDelete", "Album can't be deleted here, use DeleteAlbum instead");
            return -100;
        } else if (!ShareMediaManager.isOtherShareMediaId(cursor.getLong(0))) {
            return -1;
        } else {
            Log.w("CloudManager.DupDelete", "Share medias can't be deleted here, use DeleteSharer instead");
            return -100;
        }
    }
}
