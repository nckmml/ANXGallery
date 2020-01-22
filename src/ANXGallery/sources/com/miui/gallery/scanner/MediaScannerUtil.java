package com.miui.gallery.scanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.common.collect.Lists;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.BackgroundServiceHelper;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryDataCache;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.UriUtil;
import com.nexstreaming.nexeditorsdk.nexProject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class MediaScannerUtil {
    private static final String[] FIND_NEW_MEDIA_PROJECTION = {"_data"};
    private static final String[] FIND_NEW_PROJECTION = {"_data"};
    private static final String[] FIND_OLD_PROJECTION = {"localFile"};
    private static Comparator<String> mDirectoryComparator = new Comparator<String>() {
        public int compare(String str, String str2) {
            File file = new File(str);
            File file2 = new File(str2);
            if (file.lastModified() > file2.lastModified()) {
                return -1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : 1;
        }
    };

    private static void addRelativePathsToScanList(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Integer> arrayList3) {
        if (MiscUtil.isValid(arrayList)) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String[] absolutePath = StorageUtils.getAbsolutePath(context, it.next());
                if (absolutePath != null) {
                    for (String str : absolutePath) {
                        File file = new File(str);
                        if (file.exists() && file.isDirectory()) {
                            int bucketID = FileUtils.getBucketID(str);
                            if (!arrayList3.contains(Integer.valueOf(bucketID))) {
                                arrayList2.add(str);
                                arrayList3.add(Integer.valueOf(bucketID));
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean checkStoragePermission(Context context) {
        if (PermissionUtils.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return true;
        }
        Log.w("MediaScannerUtil", "Can't access external storage, relate permission is ungranted");
        return false;
    }

    public static void cleanup(Context context) {
        if (context != null && checkStoragePermission(context)) {
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("key_trigger_full_cleanup", true));
        }
    }

    public static void cleanupByAlbumId(Context context, int i, ArrayList<String> arrayList) {
        if (context != null && checkStoragePermission(context)) {
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putStringArrayListExtra("cleanup_by_album_ids", arrayList).putExtra("key_scan_priority", i));
        }
    }

    static /* synthetic */ ArrayList lambda$scanMediaProvider$47(Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        return arrayList;
    }

    static /* synthetic */ ArrayList lambda$scanMediaProvider$48(Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        return arrayList;
    }

    private static void recordScannerException(String str, Exception exc) {
        Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
        generatorCommonParams.put("method", str);
        GallerySamplingStatHelper.recordErrorEvent("media_scanner", String.format(Locale.US, "scanner_%s", new Object[]{exc.getClass().getSimpleName()}), generatorCommonParams);
    }

    public static void refreshIgnoreListIfNeeded(Context context) {
        if (context != null) {
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("refresh_ignore_albums", ""));
        }
    }

    private static void scanAbsolutePaths(Context context, ArrayList<String> arrayList, int i, boolean z) {
        if (MiscUtil.isValid(arrayList)) {
            Collections.sort(arrayList, mDirectoryComparator);
            String valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
            GalleryDataCache.getInstance().put(valueOf, arrayList);
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("scan_folder_paths", valueOf).putExtra("key_scan_priority", i).putExtra("key_cancel_running", z).putExtra("key_bulk_notify", true));
        }
    }

    public static void scanAllAlbumDirectories(Context context, int i) {
        scanAllAlbumDirectories(context, i, false);
    }

    public static void scanAllAlbumDirectories(Context context, int i, boolean z) {
        String str;
        String str2;
        if (context != null && checkStoragePermission(context)) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            addRelativePathsToScanList(context, Lists.newArrayList((E[]) new String[]{"DCIM/Camera"}), arrayList2, arrayList);
            addRelativePathsToScanList(context, CloudControlStrategyHelper.getAlbumsInWhiteList(), arrayList2, arrayList);
            addRelativePathsToScanList(context, (ArrayList) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, FIND_OLD_PROJECTION, "serverType=0", (String[]) null, "dateModified DESC", new SafeDBUtil.QueryHandler<ArrayList<String>>() {
                public ArrayList<String> handle(Cursor cursor) {
                    if (cursor == null) {
                        return null;
                    }
                    ArrayList<String> arrayList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        arrayList.add(cursor.getString(0));
                    }
                    return arrayList;
                }
            }), arrayList2, arrayList);
            if (arrayList.size() > 0) {
                String join = TextUtils.join(",", arrayList);
                String format = String.format(Locale.US, "0=0) AND bucket_id NOT IN (%s) GROUP BY (bucket_id) ORDER BY (date_modified", new Object[]{join});
                str = String.format(Locale.US, "0=0) AND bucket_id NOT IN (%s) GROUP BY (bucket_id) ORDER BY (date_modified", new Object[]{join});
                str2 = format;
            } else {
                str2 = "0=0) GROUP BY (bucket_id) ORDER BY (date_modified";
                str = str2;
            }
            ArrayList arrayList3 = (ArrayList) SafeDBUtil.safeQuery(context, MediaStore.Images.Media.getContentUri("external"), FIND_NEW_PROJECTION, str2, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<ArrayList<String>>() {
                public ArrayList<String> handle(Cursor cursor) {
                    if (cursor == null) {
                        return null;
                    }
                    ArrayList<String> arrayList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        arrayList.add(FileUtils.getParentFolderPath(cursor.getString(0)));
                    }
                    return arrayList;
                }
            });
            ArrayList arrayList4 = (ArrayList) SafeDBUtil.safeQuery(context, MediaStore.Video.Media.getContentUri("external"), FIND_NEW_PROJECTION, str, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<ArrayList<String>>() {
                public ArrayList<String> handle(Cursor cursor) {
                    if (cursor == null) {
                        return null;
                    }
                    ArrayList<String> arrayList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        arrayList.add(FileUtils.getParentFolderPath(cursor.getString(0)));
                    }
                    return arrayList;
                }
            });
            if (MiscUtil.isValid(arrayList3)) {
                arrayList2.addAll(arrayList3);
            }
            if (MiscUtil.isValid(arrayList4)) {
                arrayList2.addAll(arrayList4);
            }
            scanAbsolutePaths(context, arrayList2, i, z);
        }
    }

    public static void scanDirectories(Context context, ArrayList<String> arrayList, int i, boolean z) {
        scanDirectories(context, arrayList, i, z, false);
    }

    public static void scanDirectories(Context context, ArrayList<String> arrayList, int i, boolean z, boolean z2) {
        if (MiscUtil.isValid(arrayList) && checkStoragePermission(context)) {
            String valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
            GalleryDataCache.getInstance().put(valueOf, arrayList);
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("scan_folder_paths", valueOf).putExtra("key_scan_priority", i).putExtra("key_bulk_notify", z).putExtra("key_recursive_scan", z2));
        }
    }

    public static void scanDirectory(Context context, String str, int i, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str) && checkStoragePermission(context)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            scanDirectories(context, arrayList, i, z, z2);
        }
    }

    public static void scanFiles(Context context, ArrayList<String> arrayList, int i) {
        if (MiscUtil.isValid(arrayList) && checkStoragePermission(context)) {
            String valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
            GalleryDataCache.getInstance().put(valueOf, arrayList);
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("scan_files_path", valueOf).putExtra("key_scan_priority", i));
        }
    }

    public static void scanMediaProvider(Context context) {
        Context context2 = context;
        if (context2 == null) {
            Log.w("MediaScannerUtil", "Context should not be null");
        } else if (checkStoragePermission(context)) {
            long lastImagesScanTime = GalleryPreferences.MediaScanner.getLastImagesScanTime();
            long currentTimeMillis = System.currentTimeMillis();
            Context context3 = context;
            ArrayList arrayList = (ArrayList) SafeDBUtil.safeQuery(context3, UriUtil.appendLimit(MediaStore.Images.Media.getContentUri("external"), nexProject.kAutoThemeTransitionDuration), FIND_NEW_MEDIA_PROJECTION, String.format(Locale.US, "date_added >= %s / 1000", new Object[]{String.valueOf(lastImagesScanTime)}), (String[]) null, "date_added DESC ", $$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls.INSTANCE);
            if (arrayList != null && arrayList.size() > 0) {
                try {
                    String valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
                    GalleryDataCache.getInstance().put(valueOf, arrayList);
                    BackgroundServiceHelper.startForegroundServiceIfNeed(context2, new Intent(context2, MediaScannerService.class).putExtra("scan_files_path", valueOf).putExtra("key_scan_priority", 2).putExtra("key_scan_type", 1).putExtra("key_task_start_time", currentTimeMillis));
                } catch (Exception e) {
                    GalleryPreferences.MediaScanner.setLastImagesScanTime(currentTimeMillis);
                    Log.e("MediaScannerUtil", (Throwable) e);
                    recordScannerException("scanMediaProvider", e);
                }
            }
            long lastVideosScanTime = GalleryPreferences.MediaScanner.getLastVideosScanTime();
            long currentTimeMillis2 = System.currentTimeMillis();
            String format = String.format(Locale.US, "date_added >= %s / 1000", new Object[]{String.valueOf(lastVideosScanTime)});
            Uri appendLimit = UriUtil.appendLimit(MediaStore.Video.Media.getContentUri("external"), nexProject.kAutoThemeTransitionDuration);
            Uri uri = appendLimit;
            String[] strArr = FIND_NEW_MEDIA_PROJECTION;
            String str = "scan_files_path";
            String str2 = "scanMediaProvider";
            String str3 = "key_scan_priority";
            String str4 = "key_scan_type";
            String str5 = "MediaScannerUtil";
            String str6 = "key_task_start_time";
            ArrayList arrayList2 = (ArrayList) SafeDBUtil.safeQuery(context, uri, strArr, format, (String[]) null, "date_added DESC ", $$Lambda$MediaScannerUtil$q3mZCPZjXtxjXQ9Q7STipN_0fog.INSTANCE);
            if (arrayList2 != null && arrayList2.size() > 0) {
                try {
                    String valueOf2 = String.valueOf(SystemClock.elapsedRealtimeNanos());
                    GalleryDataCache.getInstance().put(valueOf2, arrayList2);
                    BackgroundServiceHelper.startForegroundServiceIfNeed(context2, new Intent(context2, MediaScannerService.class).putExtra(str, valueOf2).putExtra(str3, 2).putExtra(str4, 2).putExtra(str6, currentTimeMillis2));
                } catch (Exception e2) {
                    GalleryPreferences.MediaScanner.setLastVideosScanTime(currentTimeMillis2);
                    Log.e(str5, (Throwable) e2);
                    recordScannerException(str2, e2);
                }
            }
        }
    }

    public static void scanSingleFile(Context context, String str, int i) {
        scanSingleFile(context, str, i, false);
    }

    public static void scanSingleFile(Context context, String str, int i, boolean z) {
        if (context != null && !TextUtils.isEmpty(str) && checkStoragePermission(context)) {
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("scan_file_path", str).putExtra("key_scan_priority", i).putExtra("key_force_scan", z));
        }
    }

    public static void scanVolume(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str) && checkStoragePermission(context)) {
            BackgroundServiceHelper.startForegroundServiceIfNeed(context, new Intent(context, MediaScannerService.class).putExtra("scan_volume", str));
        }
    }
}
