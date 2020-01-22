package com.cdv.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import com.cdv.utils.NvAndroidBitmap;

public class NvAndroidThumbnail {
    private static final String TAG = "NvAndroidThumbnail";

    public static Bitmap createThumbnail(Context context, String str, boolean z, int i, int i2) {
        Cursor cursor;
        ContentResolver contentResolver;
        long j;
        Context context2 = context;
        String str2 = str;
        boolean z2 = z;
        if (context2 == null || str2 == null || str.isEmpty()) {
            return null;
        }
        ContentResolver contentResolver2 = context.getContentResolver();
        if (contentResolver2 == null) {
            return null;
        }
        boolean startsWith = str2.startsWith("content://");
        Size size = new Size(i, i2);
        if (!startsWith) {
            cursor = contentResolver2.query(z2 ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str2}, (String) null);
        } else {
            cursor = contentResolver2.query(Uri.parse(str), new String[]{"_id"}, (String) null, (String[]) null, (String) null);
        }
        if (cursor == null || !cursor.moveToFirst()) {
            if (cursor != null) {
                cursor.close();
            }
            return createThumbnailFromFile(context2, str2, z2, size);
        }
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex < 0) {
            cursor.close();
            return createThumbnailFromFile(context2, str2, z2, size);
        }
        long j2 = cursor.getLong(columnIndex);
        cursor.close();
        if (Build.VERSION.SDK_INT < 29) {
            Uri uri = z2 ? MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI : MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
            String[] strArr = {"_data"};
            StringBuilder sb = new StringBuilder();
            sb.append(z2 ? "video_id" : "image_id");
            sb.append("=?");
            contentResolver = contentResolver2;
            j = j2;
            Cursor query = contentResolver2.query(uri, strArr, sb.toString(), new String[]{String.valueOf(j2)}, (String) null);
            if (query != null && query.moveToFirst()) {
                int columnIndex2 = z2 ? query.getColumnIndex("_data") : query.getColumnIndex("_data");
                if (columnIndex2 >= 0) {
                    String string = query.getString(columnIndex2);
                    if (!string.isEmpty()) {
                        query.close();
                        return transformSystemGeneratedBitmap(context2, NvAndroidBitmap.createRotatedBitmap(context2, string, size, 2, false), str2);
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } else {
            contentResolver = contentResolver2;
            j = j2;
        }
        Bitmap thumbnail = z2 ? MediaStore.Video.Thumbnails.getThumbnail(contentResolver, j, 1, (BitmapFactory.Options) null) : MediaStore.Images.Thumbnails.getThumbnail(contentResolver, j, 1, (BitmapFactory.Options) null);
        if (thumbnail != null) {
            return transformSystemGeneratedBitmap(context2, thumbnail, str2);
        }
        Log.w(TAG, String.format("Fail to get thumbnail file for media '%d'!", new Object[]{Long.valueOf(j)}));
        return createThumbnailFromFile(context2, str2, z2, size);
    }

    private static Bitmap createThumbnailFromFile(Context context, String str, boolean z, Size size) {
        if (!z) {
            return NvAndroidBitmap.createRotatedBitmap(context, str, size, 2, false);
        }
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 1);
        if (createVideoThumbnail == null) {
            Log.e(TAG, String.format("Failed to create video thumbnail bitmap for '%s'!", new Object[]{str}));
        }
        return createVideoThumbnail;
    }

    private static Bitmap transformSystemGeneratedBitmap(Context context, Bitmap bitmap, String str) {
        if (bitmap == null) {
            return null;
        }
        NvAndroidBitmap.ImageInfo imageInfo = NvAndroidBitmap.getImageInfo(context, str);
        if (imageInfo == null) {
            return bitmap;
        }
        int i = imageInfo.orientation;
        if (Build.VERSION.SDK_INT >= 29) {
            i = (i == 2 || i == 5 || i == 7) ? 2 : 1;
        }
        try {
            return NvAndroidBitmap.transformBitmap(bitmap, i);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }
}
