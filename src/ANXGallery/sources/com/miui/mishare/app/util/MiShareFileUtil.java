package com.miui.mishare.app.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class MiShareFileUtil {
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035  */
    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        r2 = r1.lastIndexOf(46);
     */
    public static String getFileExtensionFromUri(Context context, Uri uri) {
        int lastIndexOf;
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            return "";
        }
        String uri2 = uri.toString();
        int lastIndexOf2 = uri2.lastIndexOf(35);
        if (lastIndexOf2 > 0) {
            uri2 = uri2.substring(0, lastIndexOf2);
        }
        int lastIndexOf3 = uri2.lastIndexOf(63);
        if (lastIndexOf3 > 0) {
            uri2 = uri2.substring(0, lastIndexOf3);
        }
        int lastIndexOf4 = uri2.lastIndexOf(47);
        if (lastIndexOf4 >= 0) {
            uri2 = uri2.substring(lastIndexOf4 + 1);
        }
        return (uri2.isEmpty() || !Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", uri2) || lastIndexOf < 0) ? "" : uri2.substring(lastIndexOf + 1).toLowerCase();
    }

    public static String getFileNameFromUri(Context context, Uri uri) {
        String path = getPath(context, uri);
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        File file = new File(path);
        return file.exists() ? file.getName() : "";
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, (String) null, (String[]) null);
        } else {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x000e  */
    public static boolean isAllImageOverview(Context context, List<Uri> list) {
        if (list == null) {
            return false;
        }
        for (Uri next : list) {
            if (next == null || !isFileImageOverview(context, next)) {
                return false;
            }
            while (r3.hasNext()) {
            }
        }
        return true;
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isFileImageOverview(Context context, Uri uri) {
        String fileExtensionFromUri = getFileExtensionFromUri(context, uri);
        return fileExtensionFromUri.equals("jpg") || fileExtensionFromUri.equals("jpeg") || fileExtensionFromUri.equals("png") || fileExtensionFromUri.equals("gif") || fileExtensionFromUri.equals("bmp") || fileExtensionFromUri.equals("webp") || fileExtensionFromUri.equals("wbmp");
    }

    public static boolean isFilePdf(Context context, Uri uri) {
        return getFileExtensionFromUri(context, uri).equals("pdf");
    }

    public static boolean isImageCanPrint(Context context, Uri uri) {
        String fileExtensionFromUri = getFileExtensionFromUri(context, uri);
        return fileExtensionFromUri.equals("jpg") || fileExtensionFromUri.equals("jpeg") || fileExtensionFromUri.equals("png") || fileExtensionFromUri.equals("gif") || fileExtensionFromUri.equals("bmp") || fileExtensionFromUri.equals("webp") || fileExtensionFromUri.equals("wbmp");
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
