package com.miui.gallery.scanner;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.common.base.Joiner;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.scanner.ScanTaskManager;
import com.miui.gallery.service.ServiceBase;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GalleryDataCache;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.NotificationHelper;
import com.miui.gallery.util.StorageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MediaScannerService extends ServiceBase implements DefaultLifecycleObserver {
    private volatile int mCurrentStartId;
    /* access modifiers changed from: private */
    public ScanTaskManager mManager;
    private BroadcastReceiver mMediaUnmountedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction()) && !MiscUtil.isValid(StorageUtils.getMountedVolumePaths(context))) {
                MediaScannerService.this.mManager.cancelByPriority(7);
                Log.d("MediaScannerService", "All external storage are unmounted, cancel cleanup task");
            }
        }
    };

    private class CleanupTask extends ScanTask {
        private List<String> mAlbumIds;

        public CleanupTask(MediaScannerService mediaScannerService, boolean z, int i) {
            this(z, i, (List<String>) null);
        }

        public CleanupTask(boolean z, int i, List<String> list) {
            super(i, z);
            this.mAlbumIds = list;
            Log.i("MediaScannerService", toString());
        }

        /* access modifiers changed from: protected */
        public boolean canRunningInBackground() {
            return true;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof CleanupTask)) {
                return super.equals(obj);
            }
            CleanupTask cleanupTask = (CleanupTask) obj;
            if (cleanupTask.getPriority() > getPriority()) {
                return false;
            }
            List<String> albumIds = cleanupTask.getAlbumIds();
            if (!MiscUtil.isValid(this.mAlbumIds) && !MiscUtil.isValid(albumIds)) {
                return true;
            }
            if (this.mAlbumIds == null || albumIds == null || albumIds.size() < this.mAlbumIds.size()) {
                return false;
            }
            for (String contains : this.mAlbumIds) {
                if (!albumIds.contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public List<String> getAlbumIds() {
            return this.mAlbumIds;
        }

        public int hashCode() {
            List<String> list = this.mAlbumIds;
            if (list != null) {
                return list.hashCode();
            }
            return 1;
        }

        public Void run(ThreadPool.JobContext jobContext) {
            boolean isValid = MiscUtil.isValid(StorageUtils.getMountedVolumePaths(MediaScannerService.this));
            boolean isPrimaryStorageWritable = StorageUtils.isPrimaryStorageWritable(MediaScannerService.this);
            if (!isValid || !isPrimaryStorageWritable) {
                if (!isValid) {
                    MediaScannerService.this.recordStorageUnmounted();
                }
                if (isPrimaryStorageWritable) {
                    return null;
                }
                MediaScannerService.this.recordMainSDCardUnwritable();
                return null;
            }
            MediaScanner.cleanup(MediaScannerService.this, jobContext, this.mAlbumIds);
            return null;
        }

        public String toString() {
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(getPriority());
            objArr[1] = Boolean.valueOf(isForeground());
            objArr[2] = MiscUtil.isValid(this.mAlbumIds) ? Joiner.on(", ").skipNulls().join((Iterable<?>) this.mAlbumIds) : "all";
            return String.format(locale, "CleanupTask priority [%d], foreground [%s], album ids: [%s]", objArr);
        }
    }

    private class RefreshIgnoreAlbumsTask extends ScanTask {
        public RefreshIgnoreAlbumsTask(boolean z, int i) {
            super(i, z);
            Log.i("MediaScannerService", toString());
        }

        /* access modifiers changed from: protected */
        public boolean canRunningInBackground() {
            return false;
        }

        public Void run(ThreadPool.JobContext jobContext) {
            MediaScanner.refreshIgnoreList(MediaScannerService.this, jobContext);
            return null;
        }

        public String toString() {
            return String.format(Locale.US, "RefreshIgnoreAlbumsTask priority [%d], foreground [%s]", new Object[]{Integer.valueOf(getPriority()), Boolean.valueOf(isForeground())});
        }
    }

    private class ScanFileTask extends ScanTask {
        private String mPath;

        public ScanFileTask(String str, int i, boolean z, boolean z2) {
            super(i, z, z2);
            this.mPath = str;
            Log.i("MediaScannerService", toString());
        }

        public boolean equals(Object obj) {
            return (obj == null || !(obj instanceof ScanFileTask)) ? super.equals(obj) : TextUtils.equals(this.mPath, ((ScanFileTask) obj).getFilePath());
        }

        public String getFilePath() {
            return this.mPath;
        }

        public int hashCode() {
            if (TextUtils.isEmpty(this.mPath)) {
                return 1;
            }
            return this.mPath.hashCode();
        }

        public Void run(ThreadPool.JobContext jobContext) {
            MediaScanner.scanSingleFileForExternal(MediaScannerService.this, this.mPath);
            return null;
        }

        public String toString() {
            return String.format(Locale.US, "ScanFileTask priority %d, foreground %s, path %s", new Object[]{Integer.valueOf(getPriority()), Boolean.valueOf(isForeground()), this.mPath});
        }
    }

    private class ScanFilesTask extends ScanTask {
        private List<String> mFiles;
        private int mScanStartIndex;
        private int mScanType;
        private long mTaskStartTime;

        public ScanFilesTask(List<String> list, int i, boolean z, int i2, long j) {
            super(i, z);
            this.mFiles = list;
            this.mScanType = i2;
            this.mTaskStartTime = j;
            Log.i("MediaScannerService", toString());
        }

        /* access modifiers changed from: protected */
        public boolean canRunningInBackground() {
            return true;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ScanFilesTask)) {
                return super.equals(obj);
            }
            List<String> files = ((ScanFilesTask) obj).getFiles();
            if (this.mFiles == null || files == null || files.size() < this.mFiles.size()) {
                return false;
            }
            for (String contains : this.mFiles) {
                if (!files.contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public List<String> getFiles() {
            return this.mFiles;
        }

        public int hashCode() {
            List<String> list = this.mFiles;
            if (list == null) {
                return 1;
            }
            return list.hashCode();
        }

        public Void run(ThreadPool.JobContext jobContext) {
            if (this.mScanStartIndex >= this.mFiles.size()) {
                return null;
            }
            List<String> list = this.mFiles;
            List<String> subList = list.subList(this.mScanStartIndex, list.size());
            if (!MiscUtil.isValid(subList)) {
                return null;
            }
            Log.d("MediaScannerService", "%s %s", Thread.currentThread().toString(), subList);
            long currentTimeMillis = System.currentTimeMillis();
            int scanFiles = MediaScanner.scanFiles(MediaScannerService.this, subList, jobContext);
            this.mScanStartIndex += scanFiles;
            if (scanFiles == subList.size() || jobContext == null || !jobContext.isCancelled()) {
                int i = this.mScanType;
                if (i == 1) {
                    GalleryPreferences.MediaScanner.setLastImagesScanTime(this.mTaskStartTime);
                } else if (i == 2) {
                    GalleryPreferences.MediaScanner.setLastVideosScanTime(this.mTaskStartTime);
                }
            }
            Log.d("MediaScannerService", "ScanFilesTask [%s], scan [%d] files costs [%d]", (jobContext == null || !jobContext.isCancelled()) ? "finished" : "interrupted", Integer.valueOf(scanFiles), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return null;
        }

        public String toString() {
            return String.format(Locale.US, "ScanFilesTask priority %d, foreground %s, files %s", new Object[]{Integer.valueOf(getPriority()), Boolean.valueOf(isForeground()), this.mFiles});
        }
    }

    private class ScanFoldersTask extends ScanTask {
        private boolean mBulkNotify;
        private List<String> mPaths;
        private boolean mRecursiveScan;
        private int mScanStartIndex;

        public ScanFoldersTask(List<String> list, int i, boolean z, boolean z2) {
            super(i, z2);
            this.mBulkNotify = true;
            this.mPaths = list;
            this.mRecursiveScan = z;
            Log.i("MediaScannerService", toString());
        }

        public ScanFoldersTask(MediaScannerService mediaScannerService, List<String> list, int i, boolean z, boolean z2, boolean z3) {
            this(list, i, z, z2);
            this.mBulkNotify = z3;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ScanFoldersTask)) {
                return super.equals(obj);
            }
            ScanFoldersTask scanFoldersTask = (ScanFoldersTask) obj;
            if (scanFoldersTask.getPriority() > getPriority()) {
                return false;
            }
            List<String> paths = scanFoldersTask.getPaths();
            if (this.mPaths == null || paths == null || paths.size() < this.mPaths.size()) {
                return false;
            }
            for (String contains : this.mPaths) {
                if (!paths.contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public List<String> getPaths() {
            return this.mPaths;
        }

        public int hashCode() {
            List<String> list = this.mPaths;
            if (list == null) {
                return 1;
            }
            return list.hashCode();
        }

        public Void run(ThreadPool.JobContext jobContext) {
            if (this.mScanStartIndex >= this.mPaths.size()) {
                return null;
            }
            List<String> list = this.mPaths;
            List<String> subList = list.subList(this.mScanStartIndex, list.size());
            if (!MiscUtil.isValid(subList)) {
                return null;
            }
            Log.d("MediaScannerService", "%s %s", Thread.currentThread().toString(), subList);
            long currentTimeMillis = System.currentTimeMillis();
            int scanDirectories = MediaScanner.scanDirectories(MediaScannerService.this, subList, jobContext, this.mRecursiveScan, this.mBulkNotify);
            this.mScanStartIndex += scanDirectories;
            Log.d("MediaScannerService", "ScanFoldersTask [%s], scan [%d] directories costs [%d]", (jobContext == null || !jobContext.isCancelled()) ? "finished" : "interrupted", Integer.valueOf(scanDirectories), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return null;
        }

        public String toString() {
            return String.format(Locale.US, "ScanFoldersTask priority %d, recursiveScan %b, foreground %b, paths %s", new Object[]{Integer.valueOf(getPriority()), Boolean.valueOf(this.mRecursiveScan), Boolean.valueOf(isForeground()), this.mPaths});
        }
    }

    /* access modifiers changed from: private */
    public void recordMainSDCardUnwritable() {
        Log.e("MediaScannerService", "Main SDCard is unwritable, skip cleanup");
        GallerySamplingStatHelper.recordErrorEvent("media_scanner", "main_sdcard_unwritable", GallerySamplingStatHelper.generatorCommonParams());
    }

    /* access modifiers changed from: private */
    public void recordStorageUnmounted() {
        Log.e("MediaScannerService", "No external storage mounted, skip cleanup");
        GallerySamplingStatHelper.recordErrorEvent("media_scanner", "no_external_storage_mounted", GallerySamplingStatHelper.generatorCommonParams());
    }

    /* access modifiers changed from: protected */
    public Notification getNotification() {
        return NotificationHelper.getEmptyNotification(getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public int getNotificationId() {
        return 8;
    }

    public /* synthetic */ void lambda$onCreate$49$MediaScannerService() {
        Log.i("MediaScannerService", "onAllTasksExecuted, current startId: %d", (Object) Integer.valueOf(this.mCurrentStartId));
        stopSelf(this.mCurrentStartId);
    }

    public void onCreate() {
        super.onCreate();
        this.mManager = new ScanTaskManager(1, new ScanTaskManager.OnAllTasksExecutedListener() {
            public final void onAllTasksExecuted() {
                MediaScannerService.this.lambda$onCreate$49$MediaScannerService();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.mMediaUnmountedReceiver, intentFilter);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        Log.i("MediaScannerService", "onCreate");
    }

    public void onDestroy() {
        unregisterReceiver(this.mMediaUnmountedReceiver);
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
        this.mManager.shutdown();
        Log.d("MediaScannerService", "onDestroy");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0099 A[SYNTHETIC, Splitter:B:21:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b2 A[SYNTHETIC, Splitter:B:27:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c8 A[SYNTHETIC, Splitter:B:33:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01ad  */
    public int onStartCommand(Intent intent, int i, int i2) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList<String> arrayList3;
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        String str2;
        Intent intent2 = intent;
        super.onStartCommand(intent, i, i2);
        if (intent2 == null || intent.getExtras() == null) {
            return 2;
        }
        Log.i("MediaScannerService", "onStartCommand: %d", (Object) Integer.valueOf(i2));
        this.mCurrentStartId = i2;
        String stringExtra = intent2.getStringExtra("scan_file_path");
        if (intent2.hasExtra("scan_files_path")) {
            String stringExtra2 = intent2.getStringExtra("scan_files_path");
            if (!TextUtils.isEmpty(stringExtra2)) {
                arrayList = (ArrayList) GalleryDataCache.getInstance().remove(stringExtra2);
                if (intent2.hasExtra("scan_folder_paths")) {
                    String stringExtra3 = intent2.getStringExtra("scan_folder_paths");
                    if (!TextUtils.isEmpty(stringExtra3)) {
                        arrayList2 = (ArrayList) GalleryDataCache.getInstance().remove(stringExtra3);
                        String stringExtra4 = intent2.getStringExtra("scan_volume");
                        ArrayList<String> stringArrayListExtra = intent2.getStringArrayListExtra("cleanup_by_album_ids");
                        int intExtra = intent2.getIntExtra("key_scan_priority", 8);
                        boolean isAppProcessInForeground = MiscUtil.isAppProcessInForeground();
                        boolean booleanExtra = intent2.getBooleanExtra("key_force_scan", false);
                        if (intent2.getBooleanExtra("key_cancel_running", false)) {
                            this.mManager.cancelAll();
                        }
                        if (intent2.hasExtra("refresh_ignore_albums")) {
                            try {
                                this.mManager.submit(new RefreshIgnoreAlbumsTask(isAppProcessInForeground, 9));
                            } catch (Exception e) {
                                Log.w("MediaScannerService", (Throwable) e);
                            }
                        }
                        if (intent2.getBooleanExtra("key_trigger_full_cleanup", false)) {
                            try {
                                this.mManager.submit(new CleanupTask(this, isAppProcessInForeground, 7));
                            } catch (Exception e2) {
                                Log.w("MediaScannerService", (Throwable) e2);
                            }
                        }
                        if (!TextUtils.isEmpty(stringExtra)) {
                            try {
                                ScanTaskManager scanTaskManager = this.mManager;
                                str = stringExtra4;
                                ScanFileTask scanFileTask = r1;
                                arrayList3 = stringArrayListExtra;
                                z = false;
                                try {
                                    ScanFileTask scanFileTask2 = new ScanFileTask(stringExtra, intExtra, isAppProcessInForeground, booleanExtra);
                                    scanTaskManager.submit(scanFileTask);
                                } catch (Exception e3) {
                                    e = e3;
                                }
                            } catch (Exception e4) {
                                e = e4;
                                str = stringExtra4;
                                arrayList3 = stringArrayListExtra;
                                z = false;
                                Log.w("MediaScannerService", (Throwable) e);
                                if (!MiscUtil.isValid(arrayList)) {
                                }
                                if (MiscUtil.isValid(arrayList2)) {
                                }
                                if (MiscUtil.isValid(arrayList3)) {
                                }
                                str2 = str;
                                if (!"external".equals(str2)) {
                                }
                                return 3;
                            }
                        } else {
                            str = stringExtra4;
                            arrayList3 = stringArrayListExtra;
                            z = false;
                        }
                        if (!MiscUtil.isValid(arrayList)) {
                            try {
                                int intExtra2 = intent2.getIntExtra("key_scan_type", z ? 1 : 0);
                                long longExtra = intent2.getLongExtra("key_task_start_time", System.currentTimeMillis());
                                ScanTaskManager scanTaskManager2 = this.mManager;
                                z2 = isAppProcessInForeground;
                                try {
                                    ScanFilesTask scanFilesTask = new ScanFilesTask(arrayList, intExtra, isAppProcessInForeground, intExtra2, longExtra);
                                    scanTaskManager2.submit(scanFilesTask);
                                } catch (Exception e5) {
                                    e = e5;
                                }
                            } catch (Exception e6) {
                                e = e6;
                                z2 = isAppProcessInForeground;
                                Log.w("MediaScannerService", (Throwable) e);
                                if (MiscUtil.isValid(arrayList2)) {
                                }
                                if (MiscUtil.isValid(arrayList3)) {
                                }
                                str2 = str;
                                if (!"external".equals(str2)) {
                                }
                                return 3;
                            }
                        } else {
                            z2 = isAppProcessInForeground;
                        }
                        if (MiscUtil.isValid(arrayList2)) {
                            try {
                                boolean booleanExtra2 = intent2.getBooleanExtra("key_bulk_notify", true);
                                boolean booleanExtra3 = intent2.getBooleanExtra("key_recursive_scan", z);
                                if (intExtra == 3) {
                                    this.mManager.cancelByPriority(3);
                                }
                                this.mManager.submit(new ScanFoldersTask(this, arrayList2, intExtra, booleanExtra3, z2, booleanExtra2));
                            } catch (Exception e7) {
                                Log.w("MediaScannerService", (Throwable) e7);
                            }
                        }
                        if (MiscUtil.isValid(arrayList3)) {
                            if (intExtra == 4) {
                                try {
                                    this.mManager.cancelByPriority(4);
                                } catch (Exception e8) {
                                    e = e8;
                                    z3 = z2;
                                    Log.w("MediaScannerService", (Throwable) e);
                                    str2 = str;
                                    if (!"external".equals(str2)) {
                                    }
                                    return 3;
                                }
                            }
                            z3 = z2;
                            try {
                                this.mManager.submit(new CleanupTask(z3, intExtra, arrayList3));
                            } catch (Exception e9) {
                                e = e9;
                            }
                        } else {
                            z3 = z2;
                        }
                        str2 = str;
                        if (!"external".equals(str2)) {
                            Log.i("MediaScannerService", "scan external volume");
                            List<String> mountedVolumePaths = StorageUtils.getMountedVolumePaths(getApplicationContext());
                            if (MiscUtil.isValid(mountedVolumePaths)) {
                                this.mManager.submit(new ScanFoldersTask(mountedVolumePaths, intExtra, true, z3));
                            }
                        } else if ("internal".equals(str2)) {
                            Log.w("MediaScannerService", "Received a scan internal volume command, ignore it.");
                        }
                        return 3;
                    }
                }
                arrayList2 = null;
                String stringExtra42 = intent2.getStringExtra("scan_volume");
                ArrayList<String> stringArrayListExtra2 = intent2.getStringArrayListExtra("cleanup_by_album_ids");
                int intExtra3 = intent2.getIntExtra("key_scan_priority", 8);
                boolean isAppProcessInForeground2 = MiscUtil.isAppProcessInForeground();
                boolean booleanExtra4 = intent2.getBooleanExtra("key_force_scan", false);
                if (intent2.getBooleanExtra("key_cancel_running", false)) {
                }
                if (intent2.hasExtra("refresh_ignore_albums")) {
                }
                if (intent2.getBooleanExtra("key_trigger_full_cleanup", false)) {
                }
                if (!TextUtils.isEmpty(stringExtra)) {
                }
                if (!MiscUtil.isValid(arrayList)) {
                }
                if (MiscUtil.isValid(arrayList2)) {
                }
                if (MiscUtil.isValid(arrayList3)) {
                }
                str2 = str;
                if (!"external".equals(str2)) {
                }
                return 3;
            }
        }
        arrayList = null;
        if (intent2.hasExtra("scan_folder_paths")) {
        }
        arrayList2 = null;
        String stringExtra422 = intent2.getStringExtra("scan_volume");
        ArrayList<String> stringArrayListExtra22 = intent2.getStringArrayListExtra("cleanup_by_album_ids");
        int intExtra32 = intent2.getIntExtra("key_scan_priority", 8);
        boolean isAppProcessInForeground22 = MiscUtil.isAppProcessInForeground();
        boolean booleanExtra42 = intent2.getBooleanExtra("key_force_scan", false);
        if (intent2.getBooleanExtra("key_cancel_running", false)) {
        }
        if (intent2.hasExtra("refresh_ignore_albums")) {
        }
        if (intent2.getBooleanExtra("key_trigger_full_cleanup", false)) {
        }
        if (!TextUtils.isEmpty(stringExtra)) {
        }
        if (!MiscUtil.isValid(arrayList)) {
        }
        if (MiscUtil.isValid(arrayList2)) {
        }
        if (MiscUtil.isValid(arrayList3)) {
        }
        str2 = str;
        if (!"external".equals(str2)) {
        }
        return 3;
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        if (ProcessLifecycleOwner.get() == lifecycleOwner) {
            Log.i("MediaScannerService", "onProcessStopped");
            this.mManager.cancelForegroundTask();
        }
    }
}
