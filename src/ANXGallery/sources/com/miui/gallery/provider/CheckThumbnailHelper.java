package com.miui.gallery.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import java.util.HashMap;
import java.util.Map;

public class CheckThumbnailHelper {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        if (r2.moveToFirst() != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x010a, code lost:
        if (r2.moveToFirst() != false) goto L_0x004b;
     */
    private static boolean checkOriginalRecordExist(Context context, String str, Map<String, Long> map) throws Exception {
        Cursor cursor;
        Cursor cursor2;
        int i;
        String format;
        Map<String, Long> map2 = map;
        String userCommentSha1 = ExifUtil.getUserCommentSha1(str);
        boolean z = false;
        if (TextUtils.isEmpty(userCommentSha1)) {
            return false;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String lowerCase = FileUtils.getParentFolderPath(StorageUtils.getRelativePath(context, str)).toLowerCase();
            if (DownloadPathHelper.isShareFolderRelativePath(lowerCase)) {
                cursor2 = contentResolver.query(GalleryContract.ShareImage.SHARE_URI, new String[]{"_id"}, String.format("%s LIKE '%s'", new Object[]{"sha1", userCommentSha1}), (String[]) null, (String) null);
                if (cursor2 != null) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursor2;
                        MiscUtil.closeSilently(cursor);
                        throw th;
                    }
                }
                MiscUtil.closeSilently(cursor2);
                return z;
            }
            Long l = map2.get(lowerCase);
            if (l == null) {
                if (CloudUtils.getCameraLocalFile().equalsIgnoreCase(lowerCase)) {
                    format = String.format("serverId = %s", new Object[]{1L});
                } else if (CloudUtils.getScreenshotsLocalFile().equalsIgnoreCase(lowerCase)) {
                    format = String.format("serverId = %s", new Object[]{2L});
                } else {
                    format = String.format("%s LIKE '%s' AND %s AND %s", new Object[]{"localFile", lowerCase, "(serverType=0)", "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))"});
                }
                i = 4;
                Cursor query = contentResolver.query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, format, (String[]) null, (String) null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            Long valueOf = Long.valueOf(query.getLong(0));
                            map2.put(lowerCase, valueOf);
                            l = valueOf;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        MiscUtil.closeSilently(cursor);
                        throw th;
                    }
                }
                MiscUtil.closeSilently(query);
                cursor = query;
            } else {
                i = 4;
                cursor = null;
            }
            if (l != null) {
                try {
                    Object[] objArr = new Object[i];
                    objArr[0] = "localGroupId";
                    objArr[1] = String.valueOf(l);
                    objArr[2] = "sha1";
                    objArr[3] = userCommentSha1;
                    cursor2 = contentResolver.query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, String.format("%s=%s AND %s LIKE '%s'", objArr), (String[]) null, (String) null);
                    if (cursor2 != null) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    MiscUtil.closeSilently(cursor);
                    throw th;
                }
            } else {
                cursor2 = cursor;
            }
            MiscUtil.closeSilently(cursor2);
            return z;
            z = true;
            MiscUtil.closeSilently(cursor2);
            return z;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            MiscUtil.closeSilently(cursor);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (checkUserCommentSha1Exist(r10.getPath()) != false) goto L_0x0040;
     */
    public static Cursor checkThumbnail(Context context, String[] strArr, boolean z) {
        int i;
        if (strArr == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"check_thumbnail_result"}, strArr.length);
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            try {
                Uri parse = Uri.parse(str);
                if (parse != null) {
                    if (z) {
                        if (checkOriginalRecordExist(context, parse.getPath(), hashMap)) {
                        }
                    }
                    i = 0;
                    matrixCursor.addRow(new Integer[]{Integer.valueOf(i)});
                }
            } catch (Exception e) {
                Log.e("CheckThumbnailHelper", "Failed checking file %s\n %s", str, e);
            }
            i = 1;
            matrixCursor.addRow(new Integer[]{Integer.valueOf(i)});
        }
        Log.d("CheckThumbnailHelper", "Checked %d files, cost %ss", Integer.valueOf(strArr.length), String.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return matrixCursor;
    }

    private static boolean checkUserCommentSha1Exist(String str) {
        return !TextUtils.isEmpty(ExifUtil.getUserCommentSha1(str));
    }
}
