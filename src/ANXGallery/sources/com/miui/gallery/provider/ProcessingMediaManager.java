package com.miui.gallery.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.photosapi.PhotosOemApi;
import com.miui.gallery.photosapi.ProcessingMetadataQuery;
import com.miui.gallery.provider.ProcessingMedia;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ProcessingMediaManager {
    private static boolean isProcessingTimeout(Cursor cursor, int i) {
        if (i == -1) {
            return false;
        }
        if (System.currentTimeMillis() - cursor.getLong(i) <= 40000) {
            return false;
        }
        Log.w("ProcessingMediaManager", "Media process timeout: [%s]", cursor.getString(cursor.getColumnIndex("media_path")));
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        if (r9 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        if (r9 == null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        return r8;
     */
    public static List<Long> queryProcessingMediaIds() {
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        ContentResolver contentResolver = sGetAndroidContext.getContentResolver();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(PhotosOemApi.getQueryProcessingUri(sGetAndroidContext), new String[]{"media_store_id"}, (String) null, (String[]) null, (String) null);
            if (cursor != null) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("media_store_id");
                int columnIndex = cursor.getColumnIndex("start_time");
                while (cursor.moveToNext()) {
                    if (!isProcessingTimeout(cursor, columnIndex)) {
                        arrayList.add(Long.valueOf(cursor.getLong(columnIndexOrThrow)));
                    }
                }
            }
        } catch (Exception e) {
            Log.d("ProcessingMediaManager", (Throwable) e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        if (r9 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r9 == null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        return r8;
     */
    public static List<String> queryProcessingMediaPaths() {
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        ContentResolver contentResolver = sGetAndroidContext.getContentResolver();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(PhotosOemApi.getQueryProcessingUri(sGetAndroidContext), new String[]{"media_path"}, (String) null, (String[]) null, (String) null);
            if (cursor != null) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("media_path");
                int columnIndex = cursor.getColumnIndex("start_time");
                while (cursor.moveToNext()) {
                    if (!isProcessingTimeout(cursor, columnIndex)) {
                        String string = cursor.getString(columnIndexOrThrow);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d("ProcessingMediaManager", (Throwable) e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
    public static List<ProcessingMedia> queryProcessingMedias() {
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        ContentResolver contentResolver = sGetAndroidContext.getContentResolver();
        ProcessingMedia.Factory factory = new ProcessingMedia.Factory(sGetAndroidContext);
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor cursor2 = contentResolver.query(PhotosOemApi.getQueryProcessingUri(sGetAndroidContext), (String[]) null, (String) null, (String[]) null, (String) null);
            if (cursor2 != null) {
                try {
                    int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("media_store_id");
                    int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("media_path");
                    int columnIndexOrThrow3 = cursor2.getColumnIndexOrThrow("progress_status");
                    int columnIndexOrThrow4 = cursor2.getColumnIndexOrThrow("progress_percentage");
                    int columnIndex = cursor2.getColumnIndex("start_time");
                    while (cursor2.moveToNext()) {
                        if (!isProcessingTimeout(cursor2, columnIndex)) {
                            String string = cursor2.getString(columnIndexOrThrow2);
                            long j = cursor2.getLong(columnIndexOrThrow);
                            int i = cursor2.getInt(columnIndexOrThrow3);
                            arrayList.add(factory.build(j, string, 1, new ProcessingMedia.ProcessingMetadata(ProcessingMetadataQuery.ProgressStatus.fromIdentifier(i), cursor2.getInt(columnIndexOrThrow4))));
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    cursor = cursor2;
                    try {
                        Log.d("ProcessingMediaManager", (Throwable) e);
                        if (cursor != null) {
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e2) {
            e = e2;
            Log.d("ProcessingMediaManager", (Throwable) e);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        }
        return arrayList;
    }
}
