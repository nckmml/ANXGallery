package com.miui.gallery.provider.cloudmanager;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.BatchCursorTask;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import java.util.ArrayList;
import java.util.Collection;

class OwnerFileHandleTask extends SubTaskSeparatorTask {
    public OwnerFileHandleTask(Context context, ArrayList<Long> arrayList, long[] jArr) {
        super(context, arrayList, jArr);
    }

    private long[] batchExecuteAlbumOperations(BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr) {
        long[] jArr2 = new long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(Long.valueOf(jArr[i]));
            if (batchItemData != null && batchItemData.result == -1 && batchItemData.cursorIndex >= 0) {
                batchOperationData.cursor.moveToPosition(batchItemData.cursorIndex);
                jArr2[i] = AlbumFileHandleJob.from(batchOperationData.cursor).run(getContext()) ? 1 : 0;
            }
        }
        return jArr2;
    }

    private long[] batchExecuteDeleteMedia(ContentResolver contentResolver, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr) {
        ContentResolver contentResolver2 = contentResolver;
        BatchCursorTask.BatchOperationData<Long> batchOperationData2 = batchOperationData;
        long[] jArr2 = jArr;
        long[] jArr3 = new long[jArr2.length];
        LongSparseArray longSparseArray = new LongSparseArray();
        int min = Math.min(jArr2.length, (jArr2.length / 2) + 1);
        ArrayList arrayList = new ArrayList(min);
        ArrayList arrayList2 = new ArrayList(min);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList(min);
        for (int i = 0; i < jArr2.length; i++) {
            BatchCursorTask.BatchItemData batchItemData = batchOperationData2.keyItemDataMap.get(Long.valueOf(jArr2[i]));
            if (batchItemData != null) {
                if (batchItemData.result == -1 && batchItemData.cursorIndex >= 0) {
                    batchOperationData2.cursor.moveToPosition(batchItemData.cursorIndex);
                    String albumDirectory = getAlbumDirectory(contentResolver2, batchOperationData2.cursor.getLong(3), longSparseArray);
                    String string = batchOperationData2.cursor.getString(8);
                    if (shouldDeleteFile(getContext(), string, albumDirectory)) {
                        arrayList2.add(string);
                        arrayList4.add(new DeleteRecord(40, string, "CloudManager.OwnerFileHandleTask"));
                    }
                    String string2 = batchOperationData2.cursor.getString(7);
                    if (shouldDeleteFile(getContext(), string2, albumDirectory)) {
                        arrayList.add(string2);
                        arrayList4.add(new DeleteRecord(40, string2, "CloudManager.OwnerFileHandleTask"));
                    }
                    if (batchOperationData2.cursor.getInt(2) == -1) {
                        arrayList3.add(ContentProviderOperation.newDelete(GalleryContract.Cloud.CLOUD_URI).withSelection("_id=?", new String[]{String.valueOf(jArr2[i])}).build());
                        jArr3[i] = 0;
                    } else {
                        jArr3[i] = 1;
                    }
                }
            }
        }
        if (MiscUtil.isValid(arrayList)) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.ORIGINAL, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        if (MiscUtil.isValid(arrayList2)) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.THUMBNAIL, (String[]) arrayList2.toArray(new String[arrayList2.size()]));
        }
        if (MiscUtil.isValid(arrayList4)) {
            DeleteRecorder.record((Collection<DeleteRecord>) arrayList4);
        }
        if (arrayList3.size() > 0) {
            try {
                contentResolver2.applyBatch("com.miui.gallery.cloud.provider", arrayList3);
                Log.d("CloudManager.OwnerFileHandleTask", "Done operate %d delete operations", (Object) Integer.valueOf(arrayList3.size()));
            } catch (Exception e) {
                Log.e("CloudManager.OwnerFileHandleTask", (Throwable) e);
            }
        }
        return jArr3;
    }

    private long[] batchExecuteOtherMediaOperations(ContentResolver contentResolver, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr) {
        long[] jArr2 = new long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(Long.valueOf(jArr[i]));
            if (batchItemData != null && batchItemData.result == -1 && batchItemData.cursorIndex >= 0) {
                batchOperationData.cursor.moveToPosition(batchItemData.cursorIndex);
                jArr2[i] = MediaFileHandleJob.from(contentResolver, batchOperationData.cursor, jArr[i], 40).run(getContext()) ? 1 : 0;
            }
        }
        return jArr2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    private String getAlbumDirectory(ContentResolver contentResolver, long j, LongSparseArray<String> longSparseArray) {
        Cursor cursor;
        String str;
        if (longSparseArray != null && longSparseArray.get(j) != null) {
            return longSparseArray.get(j);
        }
        String str2 = null;
        if (j == -1000) {
            str2 = StorageUtils.DIRECTORY_SECRET_ALBUM_PATH;
        } else {
            try {
                cursor = contentResolver.query(GalleryContract.Cloud.CLOUD_URI, CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(j)}, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            str = cursor.getString(7);
                            if (TextUtils.isEmpty(str)) {
                                str = CloudManager.genRelativePath(cursor.getString(6), false);
                            }
                            str2 = str;
                            MiscUtil.closeSilently(cursor);
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            Log.e("CloudManager.OwnerFileHandleTask", (Throwable) e);
                            MiscUtil.closeSilently(cursor);
                            if (str2 != null) {
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            MiscUtil.closeSilently(cursor);
                            throw th;
                        }
                    }
                }
                str = "";
                str2 = str;
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                Log.e("CloudManager.OwnerFileHandleTask", (Throwable) e);
                MiscUtil.closeSilently(cursor);
                if (str2 != null) {
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                MiscUtil.closeSilently(cursor);
                throw th;
            }
            MiscUtil.closeSilently(cursor);
        }
        if (str2 != null) {
            longSparseArray.put(j, str2);
        }
        return str2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r2 = com.miui.gallery.util.StorageUtils.getRelativePath(r2, new java.io.File(r3).getParent());
     */
    private static boolean shouldDeleteFile(Context context, String str, String str2) {
        String relativePath;
        return !TextUtils.isEmpty(str) && relativePath != null && relativePath.equalsIgnoreCase(str2);
    }

    /* access modifiers changed from: protected */
    public long[] executeType(int i, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, BatchCursorTask.BatchOperationData<Long> batchOperationData, long[] jArr) {
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (i) {
            case 10:
                return batchExecuteAlbumOperations(batchOperationData, jArr);
            case 11:
                return batchExecuteOtherMediaOperations(contentResolver, batchOperationData, jArr);
            case 12:
                return batchExecuteDeleteMedia(contentResolver, batchOperationData, jArr);
            default:
                throw new IllegalArgumentException("Type " + i + " is not supported!");
        }
    }

    /* access modifiers changed from: protected */
    public int getItemTaskType(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Bundle bundle, BatchCursorTask.BatchOperationData<Long> batchOperationData, long j) {
        BatchCursorTask.BatchItemData batchItemData = batchOperationData.keyItemDataMap.get(Long.valueOf(j));
        if (batchItemData == null || batchOperationData.cursor == null || batchItemData.cursorIndex < 0) {
            return -1;
        }
        batchOperationData.cursor.moveToPosition(batchItemData.cursorIndex);
        if (batchOperationData.cursor.getInt(2) == 0) {
            return -1;
        }
        if (batchOperationData.cursor.getInt(5) == 0) {
            return 10;
        }
        int i = batchOperationData.cursor.getInt(2);
        return (i == -1 || i == 2) ? 12 : 11;
    }

    /* access modifiers changed from: protected */
    public Cursor queryCursor(SQLiteDatabase sQLiteDatabase, Long[] lArr) {
        return getContext().getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, CloudManager.PROJECTION, String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", lArr)}), (String[]) null, (String) null);
    }
}
