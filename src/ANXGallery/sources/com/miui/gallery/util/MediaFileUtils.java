package com.miui.gallery.util;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.LocalUbifocus;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MediaFileUtils {
    private static ComponentName sMediaComponent;

    public enum FileType {
        NONE,
        NORMAL,
        ORIGINAL,
        THUMBNAIL,
        MICRO_THUMBNAIL,
        UBI_SUB_FILE,
        FOLDER,
        TEMP
    }

    private static int deleteFile(int i, File... fileArr) {
        if (fileArr == null || fileArr.length <= 0) {
            return 0;
        }
        if ((i & 2) > 0) {
            for (File file : fileArr) {
                if (file != null) {
                    String absolutePath = file.getAbsolutePath();
                    if (LocalUbifocus.isUbifocusImage(absolutePath)) {
                        for (LocalUbifocus.SubFile next : LocalUbifocus.getUbifocusSubFiles(absolutePath)) {
                            if (!next.getFilePath().equals(absolutePath)) {
                                if (deleteFileType(FileType.UBI_SUB_FILE, next.getFilePath()) <= 0) {
                                    Log.e("MediaFileUtils", "Delete ubi sub file failed " + next.getFilePath());
                                }
                            }
                        }
                    }
                    File imageRelativeDngFile = FileUtils.getImageRelativeDngFile(absolutePath);
                    if (imageRelativeDngFile != null) {
                        if (deleteFile(0, imageRelativeDngFile) <= 0) {
                            Log.e("MediaFileUtils", "Delete dng file failed " + imageRelativeDngFile.getAbsolutePath());
                        }
                    }
                }
            }
        }
        return (i & 1) > 0 ? deleteFileFromMediaProvider(fileArr) : deleteFile(fileArr);
    }

    public static int deleteFile(File... fileArr) {
        int i = 0;
        for (File file : fileArr) {
            if (file != null) {
                i = !file.exists() ? i + 1 : i + (FileUtils.deleteFile(file) ? 1 : 0);
            }
        }
        return i;
    }

    private static int deleteFileFromMediaProvider(File... fileArr) {
        int i = 0;
        if (fileArr == null || fileArr.length <= 0) {
            return 0;
        }
        if (fileArr.length <= 100) {
            return deleteFileFromMediaProviderBatch(fileArr);
        }
        int i2 = 0;
        while (i < fileArr.length) {
            int min = Math.min(fileArr.length - i, 100);
            int i3 = i + min;
            Log.d("MediaFileUtils", "Execute from %d to %d, total %d", Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(min));
            i2 += deleteFileFromMediaProviderBatch((File[]) Arrays.copyOfRange(fileArr, i, i3));
            Log.d("MediaFileUtils", "Done execute from %d to %d, total %d", Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(min));
            i = i3;
        }
        return i2;
    }

    private static int deleteFileFromMediaProviderBatch(File... fileArr) {
        int i;
        File[] fileArr2 = fileArr;
        Log.d("MediaFileUtils", "Start batch deleting %s files from media provider", (Object) Integer.valueOf(fileArr2.length));
        ArrayList arrayList = new ArrayList();
        int deleteFile = deleteFile(fileArr);
        Uri contentUri = MediaStore.Files.getContentUri("external");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = fileArr2.length;
        char c = 1;
        boolean z = true;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            File file = fileArr2[i2];
            if (file != null && !file.exists()) {
                i3++;
                if (file.isDirectory()) {
                    ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(contentUri);
                    Object[] objArr = new Object[2];
                    objArr[0] = "_data";
                    objArr[c] = file.getAbsolutePath();
                    arrayList.add(newDelete.withSelection(String.format("%s LIKE '%s%'", objArr), (String[]) null).build());
                    sb2.append("directory:[");
                    sb2.append(file.getAbsolutePath());
                    sb2.append("],");
                } else {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append("'");
                    sb.append(file.getAbsolutePath());
                    sb.append("'");
                    sb2.append(file.getAbsolutePath());
                    sb2.append(",");
                }
            }
            i2++;
            c = 1;
        }
        if (sb.length() > 0) {
            sb.insert(0, "_data IN (").append(")");
            arrayList.add(ContentProviderOperation.newDelete(contentUri).withSelection(sb.toString(), (String[]) null).build());
        }
        if (arrayList.size() > 0) {
            try {
                Log.d("MediaFileUtils", "Start deleting %s", (Object) sb2.toString());
                ContentProviderResult[] applyBatch = GalleryApp.sGetAndroidContext().getContentResolver().applyBatch("media", arrayList);
                int i4 = 0;
                i = 0;
                while (i4 < applyBatch.length) {
                    try {
                        ContentProviderResult contentProviderResult = applyBatch[i4];
                        if (contentProviderResult.count.intValue() <= 0) {
                            Log.w("MediaFileUtils", "No rows affected while executing operation [%s]", arrayList.get(i4));
                        } else {
                            i += contentProviderResult.count.intValue();
                        }
                        i4++;
                    } catch (Exception e) {
                        e = e;
                        Log.e("MediaFileUtils", "delete from provider error %s, %s", fileArr2, e);
                        HashMap hashMap = new HashMap(2);
                        hashMap.put("deleteFileCount", String.valueOf(fileArr2.length));
                        hashMap.put("errorMsg", e.getMessage());
                        GallerySamplingStatHelper.recordErrorEvent("file_handle", "error_execute_media_provider", hashMap);
                        Log.d("MediaFileUtils", "Done deleting %s files from provider, deleted %s files, check delete result %s\nOperation count %s,  affected %s rows", Integer.valueOf(fileArr2.length), Integer.valueOf(deleteFile), Integer.valueOf(i3), Integer.valueOf(arrayList.size()), Integer.valueOf(i));
                        return i;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                i = 0;
                Log.e("MediaFileUtils", "delete from provider error %s, %s", fileArr2, e);
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("deleteFileCount", String.valueOf(fileArr2.length));
                hashMap2.put("errorMsg", e.getMessage());
                GallerySamplingStatHelper.recordErrorEvent("file_handle", "error_execute_media_provider", hashMap2);
                Log.d("MediaFileUtils", "Done deleting %s files from provider, deleted %s files, check delete result %s\nOperation count %s,  affected %s rows", Integer.valueOf(fileArr2.length), Integer.valueOf(deleteFile), Integer.valueOf(i3), Integer.valueOf(arrayList.size()), Integer.valueOf(i));
                return i;
            }
        } else {
            i = 0;
        }
        Log.d("MediaFileUtils", "Done deleting %s files from provider, deleted %s files, check delete result %s\nOperation count %s,  affected %s rows", Integer.valueOf(fileArr2.length), Integer.valueOf(deleteFile), Integer.valueOf(i3), Integer.valueOf(arrayList.size()), Integer.valueOf(i));
        return i;
    }

    public static int deleteFileType(FileType fileType, File... fileArr) {
        if (fileType == null) {
            fileType = FileType.NORMAL;
        }
        switch (fileType) {
            case ORIGINAL:
                return deleteFile(3, fileArr);
            case NORMAL:
            case THUMBNAIL:
            case MICRO_THUMBNAIL:
            case UBI_SUB_FILE:
            case FOLDER:
                return deleteFile(1, fileArr);
            case TEMP:
            case NONE:
                return deleteFile(0, fileArr);
            default:
                Log.w("MediaFileUtils", "Not supported file type " + fileType);
                return 0;
        }
    }

    public static int deleteFileType(FileType fileType, String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return 0;
        }
        File[] fileArr = new File[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty(strArr[i])) {
                fileArr[i] = new File(strArr[i]);
            }
        }
        return deleteFileType(fileType, fileArr);
    }

    private static ComponentName getMediaComponentName(Intent intent) {
        if (sMediaComponent == null) {
            intent.setPackage("com.android.providers.media");
            PackageManager packageManager = GalleryApp.sGetAndroidContext().getPackageManager();
            if (packageManager != null) {
                List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
                if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
                    sMediaComponent = new ComponentName("", "");
                } else {
                    sMediaComponent = new ComponentName("com.android.providers.media", queryBroadcastReceivers.get(0).activityInfo.name);
                }
            }
        }
        return sMediaComponent;
    }

    public static void triggerMediaScan(boolean z, File... fileArr) {
        if (fileArr != null && fileArr.length > 0) {
            for (File file : fileArr) {
                if (file != null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file));
                    intent.putExtra("com.miui.gallery.extra.trigger_scan", z);
                    ComponentName mediaComponentName = getMediaComponentName(intent);
                    if (mediaComponentName != null && !TextUtils.isEmpty(mediaComponentName.getClassName())) {
                        intent.setComponent(mediaComponentName);
                    }
                    GalleryApp.sGetAndroidContext().sendBroadcast(intent);
                    Log.i("MediaFileUtils", "Trigger media scan for file %s, on system scan %s", file.getAbsolutePath(), Boolean.valueOf(z));
                }
            }
        }
    }
}
