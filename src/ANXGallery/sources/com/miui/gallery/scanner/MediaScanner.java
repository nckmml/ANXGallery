package com.miui.gallery.scanner;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Printer;
import com.google.common.base.Joiner;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.AlbumsStrategy;
import com.miui.gallery.cloudcontrol.strategies.HiddenAlbumsStrategy;
import com.miui.gallery.cloudcontrol.strategies.IgnoreAlbumsStrategy;
import com.miui.gallery.cloudcontrol.strategies.ServerReservedAlbumNamesStrategy;
import com.miui.gallery.cloudcontrol.strategies.ServerUnModifyAlbumsStrategy;
import com.miui.gallery.data.LocationManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.AlbumManager;
import com.miui.gallery.provider.ContentProviderBatchOperator;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.scanner.SaveToCloudUtil;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.ExtraTextUtils;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.SpecialTypeMediaUtils;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.util.logger.TimingTracing;
import com.miui.os.Rom;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class MediaScanner {
    private static final String[] ALBUM_NAME_CONFLICT_PROJECTION = {"count(*)"};
    private static final String[] ALBUM_PROJECTION = {"_id", "serverId", "dateModified", "localFlag", "serverStatus", "fileName", "attributes", "editedColumns"};
    private static final String[] CLEAN_UP_FILE_PATH_PROJECTION = {"_id", "fileName", "localFile", "thumbnailFile"};
    private static final String[] POST_SCAN_PROJECTION = {"_id", "sha1", "localFile", "localFlag", "localFile", "thumbnailFile"};
    private static final String[] SPECIAL_TYPE_FLAGS_PROJECTION = {"_id", "localFile"};
    private static final String SPECIAL_TYPE_FLAGS_WHERE = ("_id > ? AND serverType = 1 AND " + InternalContract.Cloud.ALIAS_ORIGIN_FILE_VALID + " AND " + "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))");
    private static final Object sLock = new Object();
    private static ServerReservedAlbumNamesStrategy sServerReservedAlbumNamesStrategy;
    private static ServerUnModifyAlbumsStrategy sServerUnModifyAlbumsStrategy;

    public static class AlbumEntry {
        boolean isModified;
        boolean isOnlyLinkFolder;
        boolean isShareAlbum;
        String mAlbumName;
        int mAttributes;
        long mDateModified;
        String mEditedColumns = "";
        long mID;
        int mLocalFlag;
        String mServerID;
        String mServerStatus;

        public static boolean isSyncable(int i) {
            return (((long) i) & 1) != 0;
        }

        public static boolean shouldScan(AlbumEntry albumEntry) {
            return albumEntry != null && albumEntry.mLocalFlag != 2 && !TextUtils.equals(albumEntry.mServerStatus, "deleted") && !TextUtils.equals(albumEntry.mServerStatus, "purged");
        }
    }

    private static class MediaScannerHelper {
        private static ArrayMap<Pattern, AlbumsStrategy.Attributes> sAlbumPatternMap;
        private static Comparator<File> sFileComparator;
        private static Pattern[] sHideAlbumPatterns;
        private static Pattern[] sIgnoreAlbumPatterns;
        private static List<String> sNonHiddenPathPrefixList;
        private static final Object sSyncLock = new Object();

        private static class FileComparator implements Comparator<File> {
            private FileComparator() {
            }

            public int compare(File file, File file2) {
                int i = (file2.lastModified() > file.lastModified() ? 1 : (file2.lastModified() == file.lastModified() ? 0 : -1));
                if (i > 0) {
                    return 1;
                }
                return i < 0 ? -1 : 0;
            }
        }

        private static class FileEntry {
            long mDateModified;
            long mFileSize;
            boolean mIsThumbnail;

            private FileEntry() {
            }
        }

        public static class MediaFileFilter implements FilenameFilter {
            private static final String[] PROJECTION = {"localFile", "thumbnailFile", "size", "dateModified"};
            private boolean mExcludeDirectory;
            private Map<String, FileEntry> mExistingFilesMap;
            private boolean mHasMediaFile;
            private ThreadPool.JobContext mJobContext;

            public MediaFileFilter(ThreadPool.JobContext jobContext, AlbumEntry albumEntry, boolean z) {
                this.mJobContext = jobContext;
                this.mExcludeDirectory = z;
                loadFileEntries(albumEntry);
            }

            private boolean isCancelled() {
                ThreadPool.JobContext jobContext = this.mJobContext;
                return jobContext != null && jobContext.isCancelled();
            }

            private void loadFileEntries(AlbumEntry albumEntry) {
                Cursor query;
                String str;
                boolean z;
                if (albumEntry != null && (query = MediaManager.getInstance().query(PROJECTION, "localGroupId = ?", new String[]{String.valueOf(albumEntry.mID)}, (String) null, (String) null, (Bundle) null)) != null && !query.isClosed() && query.getCount() > 0) {
                    this.mExistingFilesMap = new LinkedHashMap(query.getCount());
                    query.moveToFirst();
                    while (!query.isAfterLast()) {
                        String string = query.getString(0);
                        if (TextUtils.isEmpty(string)) {
                            String string2 = query.getString(1);
                            if (TextUtils.isEmpty(string2)) {
                                query.moveToNext();
                            } else {
                                str = string2;
                                z = true;
                            }
                        } else {
                            str = string;
                            z = false;
                        }
                        FileEntry fileEntry = new FileEntry();
                        fileEntry.mIsThumbnail = z;
                        fileEntry.mFileSize = query.getLong(2);
                        fileEntry.mDateModified = query.getLong(3);
                        this.mExistingFilesMap.put(str.toLowerCase(Locale.US), fileEntry);
                        query.moveToNext();
                    }
                }
            }

            public boolean accept(File file, String str) {
                FileEntry fileEntry;
                boolean z = false;
                if (!isCancelled()) {
                    File file2 = new File(file, str);
                    if (file2.isHidden()) {
                        return false;
                    }
                    if (file2.isDirectory()) {
                        return !this.mExcludeDirectory && MediaScanner.isScannableDirectory(file2);
                    }
                    if (file2.isFile()) {
                        Map<String, FileEntry> map = this.mExistingFilesMap;
                        if (map != null && (fileEntry = map.get(file2.getAbsolutePath().toLowerCase(Locale.US))) != null && (fileEntry.mFileSize == file2.length() || fileEntry.mDateModified == file2.lastModified())) {
                            return false;
                        }
                        String supportUploadMimeType = FileMimeUtil.getSupportUploadMimeType(file2.getAbsolutePath());
                        if (FileMimeUtil.isImageFromMimeType(supportUploadMimeType) || FileMimeUtil.isVideoFromMimeType(supportUploadMimeType)) {
                            z = true;
                        }
                        if (!this.mHasMediaFile && z) {
                            this.mHasMediaFile = true;
                        }
                    }
                }
                return z;
            }

            public boolean hasMediaFile() {
                return this.mHasMediaFile;
            }
        }

        private static ArrayMap<Pattern, AlbumsStrategy.Attributes> getAlbumPatternMap() {
            ArrayMap<Pattern, AlbumsStrategy.Attributes> arrayMap;
            synchronized (sSyncLock) {
                if (sAlbumPatternMap == null) {
                    sAlbumPatternMap = new ArrayMap<>();
                    List<AlbumsStrategy.AlbumPattern> albumPatterns = CloudControlStrategyHelper.getAlbumPatterns();
                    if (albumPatterns != null && albumPatterns.size() > 0) {
                        for (AlbumsStrategy.AlbumPattern next : albumPatterns) {
                            if (!TextUtils.isEmpty(next.getPattern()) && next.getAttributes() != null) {
                                try {
                                    sAlbumPatternMap.put(Pattern.compile(next.getPattern(), 2), next.getAttributes());
                                } catch (PatternSyntaxException e) {
                                    Log.e("MediaScanner", "Invalid album regex pattern: %s", (Object) e.getPattern());
                                }
                            }
                        }
                    }
                }
                arrayMap = sAlbumPatternMap;
            }
            return arrayMap;
        }

        public static AlbumsStrategy.Attributes getAttributesByPath(String str) {
            ArrayMap<Pattern, AlbumsStrategy.Attributes> albumPatternMap;
            if (!(str == null || StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH.equals(str) || (albumPatternMap = getAlbumPatternMap()) == null)) {
                for (Map.Entry next : albumPatternMap.entrySet()) {
                    if (((Pattern) next.getKey()).matcher(str).find()) {
                        return (AlbumsStrategy.Attributes) next.getValue();
                    }
                }
            }
            return null;
        }

        public static Comparator<File> getFileComparator() {
            if (sFileComparator == null) {
                sFileComparator = new FileComparator();
            }
            return sFileComparator;
        }

        private static Pattern[] getHideAlbumPatterns() {
            Pattern[] patternArr;
            HiddenAlbumsStrategy hiddenAlbums;
            int i;
            PatternSyntaxException e;
            synchronized (sSyncLock) {
                if (sHideAlbumPatterns == null && (hiddenAlbums = CloudControlStrategyHelper.getHiddenAlbums()) != null) {
                    List<String> patterns = hiddenAlbums.getPatterns();
                    if (MiscUtil.isValid(patterns)) {
                        sHideAlbumPatterns = new Pattern[patterns.size()];
                        int i2 = 0;
                        for (String next : patterns) {
                            try {
                                i = i2 + 1;
                                try {
                                    sHideAlbumPatterns[i2] = Pattern.compile(next, 2);
                                    i2 = i;
                                } catch (PatternSyntaxException e2) {
                                    e = e2;
                                    Log.e("MediaScanner", "Invalid hide album regex pattern: %s", (Object) e.getPattern());
                                    sHideAlbumPatterns[i] = null;
                                    i2 = i + 1;
                                }
                            } catch (PatternSyntaxException e3) {
                                i = i2;
                                e = e3;
                                Log.e("MediaScanner", "Invalid hide album regex pattern: %s", (Object) e.getPattern());
                                sHideAlbumPatterns[i] = null;
                                i2 = i + 1;
                            }
                        }
                    }
                }
                patternArr = sHideAlbumPatterns;
            }
            return patternArr;
        }

        private static Pattern[] getIgnoreAlbumPatterns() {
            Pattern[] patternArr;
            IgnoreAlbumsStrategy ignoreAlbums;
            int i;
            PatternSyntaxException e;
            synchronized (sSyncLock) {
                if (sIgnoreAlbumPatterns == null && (ignoreAlbums = CloudControlStrategyHelper.getIgnoreAlbums()) != null) {
                    List<String> patterns = ignoreAlbums.getPatterns();
                    if (MiscUtil.isValid(patterns)) {
                        sIgnoreAlbumPatterns = new Pattern[patterns.size()];
                        int i2 = 0;
                        for (String next : patterns) {
                            try {
                                i = i2 + 1;
                                try {
                                    sIgnoreAlbumPatterns[i2] = Pattern.compile(next, 2);
                                    i2 = i;
                                } catch (PatternSyntaxException e2) {
                                    e = e2;
                                    Log.e("MediaScanner", "Invalid ignore album regex pattern: %s", (Object) e.getPattern());
                                    sIgnoreAlbumPatterns[i] = null;
                                    i2 = i + 1;
                                }
                            } catch (PatternSyntaxException e3) {
                                i = i2;
                                e = e3;
                                Log.e("MediaScanner", "Invalid ignore album regex pattern: %s", (Object) e.getPattern());
                                sIgnoreAlbumPatterns[i] = null;
                                i2 = i + 1;
                            }
                        }
                    }
                }
                patternArr = sIgnoreAlbumPatterns;
            }
            return patternArr;
        }

        private static List<String> getNonHiddenPathPrefixList() {
            List<String> list;
            synchronized (sSyncLock) {
                if (sNonHiddenPathPrefixList == null) {
                    sNonHiddenPathPrefixList = new ArrayList();
                    HiddenAlbumsStrategy hiddenAlbums = CloudControlStrategyHelper.getHiddenAlbums();
                    if (hiddenAlbums != null && MiscUtil.isValid(hiddenAlbums.getNonHiddenPathPrefix())) {
                        sNonHiddenPathPrefixList.addAll(hiddenAlbums.getNonHiddenPathPrefix());
                    }
                }
                list = sNonHiddenPathPrefixList;
            }
            return list;
        }

        public static boolean isInHideList(String str) {
            if (!TextUtils.isEmpty(str) && !StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH.equals(str)) {
                int length = str.length();
                String str2 = str;
                do {
                    str2 = str2.substring(0, length);
                    AlbumsStrategy.Album albumByPath = CloudControlStrategyHelper.getAlbumByPath(str2);
                    if (albumByPath != null && albumByPath.getAttributes() != null && albumByPath.getAttributes().isHide()) {
                        return true;
                    }
                    length = str2.lastIndexOf(File.separatorChar);
                } while (length != -1);
                if (str.indexOf(File.separator) == -1) {
                    return false;
                }
                List<String> nonHiddenPathPrefixList = getNonHiddenPathPrefixList();
                if (MiscUtil.isValid(nonHiddenPathPrefixList)) {
                    for (String lowerCase : nonHiddenPathPrefixList) {
                        if (str.toLowerCase().startsWith(lowerCase.toLowerCase())) {
                            return false;
                        }
                    }
                }
                Pattern[] hideAlbumPatterns = getHideAlbumPatterns();
                if (hideAlbumPatterns != null && hideAlbumPatterns.length > 0) {
                    int length2 = hideAlbumPatterns.length;
                    int i = 0;
                    while (i < length2) {
                        Pattern pattern = hideAlbumPatterns[i];
                        if (pattern == null || !pattern.matcher(str).find()) {
                            i++;
                        } else {
                            MediaScanner.recordHiddenAlbum(str);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static boolean isInIgnoreList(String str) {
            if (TextUtils.isEmpty(str) || StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH.equals(str) || str.indexOf(File.separator) == -1) {
                return false;
            }
            List<String> nonHiddenPathPrefixList = getNonHiddenPathPrefixList();
            if (MiscUtil.isValid(nonHiddenPathPrefixList)) {
                for (String lowerCase : nonHiddenPathPrefixList) {
                    if (str.toLowerCase().startsWith(lowerCase.toLowerCase())) {
                        return false;
                    }
                }
            }
            str.toLowerCase();
            Pattern[] ignoreAlbumPatterns = getIgnoreAlbumPatterns();
            if (ignoreAlbumPatterns != null && ignoreAlbumPatterns.length > 0) {
                for (Pattern pattern : ignoreAlbumPatterns) {
                    if (pattern != null && pattern.matcher(str).find()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean isOnlyLinkFileFolder(String str) {
            return DownloadPathHelper.isShareFolderRelativePath(str);
        }

        public static MediaFileFilter newFileFilter(ThreadPool.JobContext jobContext, AlbumEntry albumEntry, boolean z) {
            return new MediaFileFilter(jobContext, albumEntry, z);
        }
    }

    private static class ScannerDirectoryClient {
        private final Printer PRINTER = $$Lambda$MediaScanner$ScannerDirectoryClient$d79RSwiYaZLaagVIaZUaZpBRk5Q.INSTANCE;
        private ScannerFileClient mFileClient;
        private ThreadPool.JobContext mJobContext;

        ScannerDirectoryClient(ScannerFileClient scannerFileClient, ThreadPool.JobContext jobContext) {
            this.mFileClient = scannerFileClient;
            this.mJobContext = jobContext;
        }

        private ArrayList<File> doScan(Context context, String str, AlbumEntry albumEntry, File[] fileArr, long j) {
            Context context2 = context;
            String str2 = str;
            File[] fileArr2 = fileArr;
            AlbumEntry access$200 = albumEntry == null ? MediaScanner.insertAlbum(context2, str2, (ContentValues) null) : albumEntry;
            if (access$200 != null && access$200.isOnlyLinkFolder) {
                return null;
            }
            boolean z = access$200 != null && access$200.isModified && AlbumEntry.shouldScan(access$200);
            if (z) {
                Log.i("MediaScanner", "do scan folder %s", (Object) str2);
            }
            ArrayList<File> arrayList = new ArrayList<>();
            boolean z2 = false;
            for (File file : fileArr2) {
                if (isCancelled()) {
                    return null;
                }
                if (!file.isFile()) {
                    arrayList.add(file);
                } else if (z) {
                    try {
                        System.currentTimeMillis();
                        try {
                            if (-1 == this.mFileClient.scanFile(context2, file, access$200)) {
                                Log.w("MediaScanner", "scan %s fail", file.getAbsolutePath());
                                z2 = true;
                            }
                        } catch (Exception e) {
                            e = e;
                            Log.w("MediaScanner", "scan file %s exception %s", file.getAbsolutePath(), e);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.w("MediaScanner", "scan file %s exception %s", file.getAbsolutePath(), e);
                    }
                }
            }
            if (z && !isCancelled() && !z2) {
                MediaScanner.updateAlbumDateModified(context2, access$200);
                if (Rom.IS_DEV) {
                    recordScanCost(context, str, j, access$200);
                }
            }
            return arrayList;
        }

        private boolean isCancelled() {
            ThreadPool.JobContext jobContext = this.mJobContext;
            return jobContext != null && jobContext.isCancelled();
        }

        static /* synthetic */ void lambda$new$52(String str) {
        }

        private void recordScanCost(Context context, String str, long j, AlbumEntry albumEntry) {
            Long l;
            try {
                l = Long.valueOf(Long.parseLong(albumEntry.mServerID));
            } catch (NumberFormatException unused) {
                l = null;
            }
            if (l == null) {
                return;
            }
            if (l.longValue() == 1 || l.longValue() == 2) {
                String relativePath = StorageUtils.getRelativePath(context, str);
                HashMap hashMap = new HashMap();
                hashMap.put("wait_for_scan_cost(s)", String.valueOf((System.currentTimeMillis() - albumEntry.mDateModified) / 1000));
                hashMap.put("scan_cost(ms)", String.valueOf(SystemClock.uptimeMillis() - j));
                GallerySamplingStatHelper.recordCountEvent("media_scanner", String.format("scan_directory_%s", new Object[]{relativePath}), hashMap);
            }
        }

        public void scanFolder(Context context, File file, boolean z) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (file != null && file.exists() && !file.isHidden()) {
                String absolutePath = file.getAbsolutePath();
                String relativePath = StorageUtils.getRelativePath(context, absolutePath);
                if (!MediaScannerHelper.isOnlyLinkFileFolder(relativePath)) {
                    String str = "ScannerDirectoryClient-" + relativePath;
                    TimingTracing.beginTracing(str, "scanFolder");
                    AlbumEntry albumEntry = null;
                    try {
                        AlbumEntry access$000 = MediaScanner.queryAndUpdateAlbum(context, absolutePath, (ContentValues) null);
                        TimingTracing.addSplit(str, "queryAndUpdateAlbum");
                        int i = 0;
                        if ((access$000 == null || (access$000.isModified && AlbumEntry.shouldScan(access$000))) || z) {
                            ThreadPool.JobContext jobContext = this.mJobContext;
                            if (!z) {
                                albumEntry = access$000;
                            }
                            MediaScannerHelper.MediaFileFilter newFileFilter = MediaScannerHelper.newFileFilter(jobContext, albumEntry, !z);
                            TimingTracing.addSplit(str, "newFileFilter");
                            File[] listFiles = file.listFiles(newFileFilter);
                            TimingTracing.addSplit(str, "listFiles");
                            if (listFiles != null && !isCancelled()) {
                                if (newFileFilter.hasMediaFile()) {
                                    Arrays.sort(listFiles, MediaScannerHelper.getFileComparator());
                                    TimingTracing.addSplit(str, "sortFiles");
                                    if (!isCancelled()) {
                                        ArrayList<File> doScan = doScan(context, absolutePath, access$000, listFiles, uptimeMillis);
                                        TimingTracing.addSplit(str, "doScan");
                                        if (z && doScan != null) {
                                            Iterator<File> it = doScan.iterator();
                                            while (it.hasNext()) {
                                                File next = it.next();
                                                if (!isCancelled()) {
                                                    scanFolder(context, next, true);
                                                }
                                            }
                                            TimingTracing.addSplit(str, "scanChildFolders");
                                        }
                                    }
                                } else {
                                    if (access$000 != null) {
                                        MediaScanner.updateAlbumDateModified(context, access$000);
                                        TimingTracing.addSplit(str, "updateAlbumDateModified");
                                    }
                                    if (z) {
                                        int length = listFiles.length;
                                        while (i < length) {
                                            File file2 = listFiles[i];
                                            if (!isCancelled()) {
                                                scanFolder(context, file2, true);
                                                i++;
                                            }
                                        }
                                        TimingTracing.addSplit(str, "scanChildFolders");
                                    }
                                }
                            }
                            TimingTracing.stopTracing(str, this.PRINTER);
                        }
                    } finally {
                        TimingTracing.stopTracing(str, this.PRINTER);
                    }
                }
            }
        }
    }

    private static class ScannerFileClient {
        private final ContentProviderBatchOperator mBatchOperator;
        private final boolean mBulkNotify;
        private final MediaBulkInserter mInserter;
        private final boolean mIsInternalScanRequest;
        private int mNonBulkInsertCount;
        private final boolean mTriggerSync;

        ScannerFileClient(boolean z, MediaBulkInserter mediaBulkInserter, ContentProviderBatchOperator contentProviderBatchOperator, boolean z2, boolean z3) {
            this.mBulkNotify = z;
            this.mTriggerSync = z2;
            this.mBatchOperator = contentProviderBatchOperator;
            this.mInserter = mediaBulkInserter;
            this.mIsInternalScanRequest = z3;
        }

        public long scanFile(Context context, File file, AlbumEntry albumEntry) {
            if (albumEntry.isOnlyLinkFolder) {
                return 0;
            }
            long uniformAlbumId = albumEntry.isShareAlbum ? ShareAlbumManager.getUniformAlbumId(albumEntry.mID) : albumEntry.mID;
            if (this.mInserter != null) {
                long saveToCloudDB = SaveToCloudUtil.saveToCloudDB(context, new SaveToCloudUtil.SaveParams(file, uniformAlbumId, this.mTriggerSync && AlbumEntry.isSyncable(albumEntry.mAttributes), 7, this.mBulkNotify, this.mIsInternalScanRequest), this.mInserter, this.mBatchOperator);
                if (-4 != saveToCloudDB) {
                    int i = this.mNonBulkInsertCount + 1;
                    this.mNonBulkInsertCount = i;
                    if (i > 20) {
                        this.mNonBulkInsertCount = 0;
                        this.mInserter.flush(context);
                    }
                }
                return saveToCloudDB;
            }
            return SaveToCloudUtil.saveToCloudDB(context, new SaveToCloudUtil.SaveParams(file, uniformAlbumId, this.mTriggerSync && AlbumEntry.isSyncable(albumEntry.mAttributes), 7, this.mBulkNotify, this.mIsInternalScanRequest));
        }
    }

    private static int checkAlbumNameConflict(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        if (getServerUnModifyAlbumsStrategy().containsName(str) || getServerReservedAlbumNamesStrategy().containsName(str)) {
            return 2;
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, ALBUM_NAME_CONFLICT_PROJECTION, "(serverType=0) AND fileName = ? COLLATE NOCASE AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
            if (cursor == null) {
                throw new IllegalStateException("query album cursor null");
            } else if (!cursor.moveToFirst() || cursor.getInt(0) <= 0) {
                MiscUtil.closeSilently(cursor);
                return 0;
            } else {
                return 3;
            }
        } finally {
            MiscUtil.closeSilently(cursor);
        }
    }

    private static void checkAndUpdateFileInfo(Context context, Cursor cursor, long j, ContentProviderBatchOperator contentProviderBatchOperator, ArrayList<DeleteRecord> arrayList) {
        ContentValues contentValues = new ContentValues();
        String string = cursor.getString(5);
        if (!TextUtils.isEmpty(string) && !new File(string).exists()) {
            contentValues.put("thumbnailFile", "");
            arrayList.add(new DeleteRecord(13, string, "MediaScanner"));
        }
        String string2 = cursor.getString(4);
        if (!TextUtils.isEmpty(string2) && !new File(string2).exists()) {
            contentValues.put("localFile", "");
            arrayList.add(new DeleteRecord(13, string2, "MediaScanner"));
        }
        if (contentValues.size() > 0) {
            contentProviderBatchOperator.add(context, ContentProviderOperation.newUpdate(GalleryContract.CloudWriteBulkNotify.CLOUD_WRITE_BULK_NOTIFY_URI).withSelection("_id=?", new String[]{String.valueOf(j)}).withValues(contentValues).build());
        }
    }

    private static void cleanFile(Context context, String str) {
        if (!FileUtils.isFileExist(str)) {
            Log.d("MediaScanner", "delete %s", (Object) str);
            SafeDBUtil.safeDelete(context, GalleryContract.Cloud.CLOUD_URI, "localFile = ? COLLATE NOCASE AND (serverType=1 OR serverType=2) AND (localFlag=7 OR localFlag=8) AND serverStatus IS NULL", new String[]{str});
            DeleteRecorder.record(new DeleteRecord(14, str, "MediaScanner"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d7  */
    public static void cleanup(Context context, ThreadPool.JobContext jobContext, List<String> list) {
        String str;
        boolean z;
        Context context2 = context;
        cleanupFilePathForDBFileName(context, jobContext);
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean z2 = true;
        if (MiscUtil.isValid(list)) {
            str = DatabaseUtils.concatenateWhere("_id>? AND (serverType=1 OR serverType=2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000)", String.format(Locale.US, "localGroupId IN (%s)", new Object[]{Joiner.on(",").skipNulls().join((Iterable<?>) list)}));
        } else {
            str = "_id>? AND (serverType=1 OR serverType=2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000)";
        }
        String[] strArr = {""};
        ContentProviderBatchOperator contentProviderBatchOperator = new ContentProviderBatchOperator("com.miui.gallery.cloud.provider");
        CloudMediaBulkDeleter cloudMediaBulkDeleter = new CloudMediaBulkDeleter(GalleryContract.Cloud.CLOUD_URI, "_id");
        boolean everCleanUnsupportUploadItems = GalleryPreferences.MediaScanner.getEverCleanUnsupportUploadItems();
        int i = Integer.MIN_VALUE;
        try {
            $$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM r1 = new SafeDBUtil.QueryHandler(cloudMediaBulkDeleter, context, everCleanUnsupportUploadItems, contentProviderBatchOperator) {
                private final /* synthetic */ CloudMediaBulkDeleter f$1;
                private final /* synthetic */ Context f$2;
                private final /* synthetic */ boolean f$3;
                private final /* synthetic */ ContentProviderBatchOperator f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final Object handle(Cursor cursor) {
                    return MediaScanner.lambda$cleanup$50(ThreadPool.JobContext.this, this.f$1, this.f$2, this.f$3, this.f$4, cursor);
                }
            };
            z = true;
            do {
                if (jobContext != null) {
                    try {
                        if (jobContext.isCancelled()) {
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (!z || !cloudMediaBulkDeleter.flushForResult(context2)) {
                            z2 = false;
                        }
                        if (z2) {
                            contentProviderBatchOperator.apply(context2);
                            if (!everCleanUnsupportUploadItems) {
                                GalleryPreferences.MediaScanner.setEverCleanUnsupportUploadItems();
                            }
                        }
                        throw th;
                    }
                }
                strArr[0] = String.valueOf(i);
                Integer num = (Integer) SafeDBUtil.safeQuery(context, UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, 1000), POST_SCAN_PROJECTION, str, strArr, POST_SCAN_PROJECTION[0], r1);
                if (num == null) {
                    i = Integer.MAX_VALUE;
                    z = false;
                    continue;
                } else {
                    i = num.intValue();
                    continue;
                }
            } while (i < Integer.MAX_VALUE);
            if (!z || !cloudMediaBulkDeleter.flushForResult(context2)) {
                z2 = false;
            }
            if (z2) {
                contentProviderBatchOperator.apply(context2);
                if (!everCleanUnsupportUploadItems) {
                    GalleryPreferences.MediaScanner.setEverCleanUnsupportUploadItems();
                }
            }
            scannerCorrectLogic(context, jobContext);
            fillSpecialTypeFlags(context, jobContext);
            Log.d("MediaScanner", "cleanup costs: %d", (Object) Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
        } catch (Throwable th2) {
            th = th2;
            z = true;
            z2 = false;
            if (z2) {
            }
            throw th;
        }
    }

    private static void cleanupFilePathForDBFileName(Context context, final ThreadPool.JobContext jobContext) {
        if (!GalleryPreferences.CleanFilePath.hasCleanFilePath()) {
            Context context2 = context;
            SafeDBUtil.safeQuery(context2, GalleryContract.Cloud.CLOUD_URI, CLEAN_UP_FILE_PATH_PROJECTION, "fileName NOT LIKE '%_BURST%' AND ((localFile IS NOT NULL AND localFile LIKE '%_BURST%') OR (thumbnailFile IS NOT NULL AND thumbnailFile LIKE '%_BURST%')) AND localFlag NOT IN (-1, 2) AND serverStatus = 'custom'", (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Void>() {
                public Void handle(Cursor cursor) {
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            ThreadPool.JobContext jobContext = jobContext;
                            if (jobContext != null && jobContext.isCancelled()) {
                                return null;
                            }
                            String string = cursor.getString(cursor.getColumnIndex("fileName"));
                            long j = cursor.getLong(cursor.getColumnIndex("_id"));
                            String str = "localFile";
                            String string2 = cursor.getString(cursor.getColumnIndex(str));
                            if (TextUtils.isEmpty(string2)) {
                                string2 = cursor.getString(cursor.getColumnIndex("thumbnailFile"));
                                str = "thumbnailFile";
                            }
                            if (!TextUtils.isEmpty(string2) && !TextUtils.equals(string, FileUtils.getFileName(string2)) && FileUtils.isFileExist(string2)) {
                                String concat = FileUtils.concat(FileUtils.getParentFolderPath(string2), string);
                                Uri fileMediaUri = MediaStoreUtils.getFileMediaUri(string2);
                                FileUtils.move(new File(string2), new File(concat));
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(str, concat);
                                GalleryUtils.safeUpdate(GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=?", new String[]{String.valueOf(j)});
                                MediaStoreUtils.update(fileMediaUri, concat);
                            }
                        }
                    }
                    GalleryPreferences.CleanFilePath.setHasCleanFilePath(true);
                    return null;
                }
            });
        }
    }

    private static void doScanSingleFile(Context context, String str, boolean z) {
        AlbumEntry queryOrInsertAlbum;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (!file.exists()) {
                cleanFile(context, str);
            } else if (file.isFile()) {
                String parent = file.getParent();
                if (parent == null || isScannableDirectory(parent)) {
                    String supportUploadMimeType = FileMimeUtil.getSupportUploadMimeType(str);
                    if ((FileMimeUtil.isImageFromMimeType(supportUploadMimeType) || FileMimeUtil.isVideoFromMimeType(supportUploadMimeType)) && (queryOrInsertAlbum = queryOrInsertAlbum(context, FileUtils.getParentFolderPath(str))) != null) {
                        long scanFile = new ScannerFileClient(false, (MediaBulkInserter) null, (ContentProviderBatchOperator) null, true, z).scanFile(context, new File(str), queryOrInsertAlbum);
                        cleanFile(context, str);
                        if (scanFile > 0) {
                            LocationManager.getInstance().loadLocationAsync(scanFile);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c5, code lost:
        r20 = r3;
        r3 = "MediaScanner";
        r13 = "fill special type flags [%s], costs [%d] ms";
        r4 = "cost(ms)";
        r17 = "succeed";
        r14 = "media_scanner";
        r5 = "failed";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d0, code lost:
        r3 = "MediaScanner";
        r13 = "fill special type flags [%s], costs [%d] ms";
        r19 = "failed";
        r4 = "cost(ms)";
        r17 = "succeed";
        r14 = "media_scanner";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        com.miui.gallery.preference.GalleryPreferences.MediaScanner.setEverFillSpecialTypeFlags();
        r1 = new java.util.HashMap();
        r1.put(r4, java.lang.String.valueOf(r6));
        com.miui.gallery.util.GallerySamplingStatHelper.recordCountEvent(r14, "fill_special_type_flags", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010c, code lost:
        r5 = r17;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cf A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:6:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010c  */
    private static void fillSpecialTypeFlags(Context context, ThreadPool.JobContext jobContext) {
        boolean z;
        Object obj;
        String str;
        String str2;
        Object obj2;
        Object obj3;
        String str3;
        final ThreadPool.JobContext jobContext2 = jobContext;
        if (!GalleryPreferences.MediaScanner.isEverFillSpecialTypeFlags()) {
            long uptimeMillis = SystemClock.uptimeMillis();
            boolean z2 = true;
            String[] strArr = {""};
            int i = Integer.MIN_VALUE;
            final Context context2 = context;
            AnonymousClass3 r12 = new SafeDBUtil.QueryHandler<Integer>() {
                public Integer handle(Cursor cursor) {
                    int i = Reader.READ_DONE;
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            ThreadPool.JobContext jobContext = jobContext2;
                            if (jobContext != null && jobContext.isCancelled()) {
                                return null;
                            }
                            int i2 = cursor.getInt(0);
                            String string = cursor.getString(1);
                            if (FileUtils.isFileExist(string)) {
                                long parseFlagsForImage = SpecialTypeMediaUtils.parseFlagsForImage(string);
                                if (parseFlagsForImage != 0) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("specialTypeFlags", Long.valueOf(parseFlagsForImage));
                                    SafeDBUtil.safeUpdate(context2, GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=?", new String[]{String.valueOf(i2)});
                                }
                            }
                            i = i2;
                        }
                    }
                    return Integer.valueOf(i);
                }
            };
            while (true) {
                boolean z3 = false;
                if (jobContext2 != null) {
                    try {
                        if (jobContext.isCancelled()) {
                            Log.d("MediaScanner", "fill special type flags [%s], costs [%d] ms", "failed", Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
                            return;
                        }
                    } catch (Exception e) {
                    } catch (Throwable th) {
                        th = th;
                        z = z2;
                        str3 = "MediaScanner";
                        str2 = "fill special type flags [%s], costs [%d] ms";
                        obj2 = "failed";
                        obj3 = "cost(ms)";
                        obj = "succeed";
                        str = "media_scanner";
                        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
                        if (z) {
                        }
                        if (z) {
                        }
                        Log.d(str3, str2, obj2, Long.valueOf(uptimeMillis2));
                        throw th;
                    }
                }
                strArr[0] = String.valueOf(i);
                z = z2;
                str3 = "MediaScanner";
                str2 = "fill special type flags [%s], costs [%d] ms";
                obj = "succeed";
                String[] strArr2 = strArr;
                String[] strArr3 = strArr;
                obj3 = "cost(ms)";
                Object obj4 = "failed";
                str = "media_scanner";
                try {
                    Integer num = (Integer) SafeDBUtil.safeQuery(context, UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, 1000), SPECIAL_TYPE_FLAGS_PROJECTION, SPECIAL_TYPE_FLAGS_WHERE, strArr2, "_id ASC", r12);
                    if (num == null) {
                        i = Integer.MAX_VALUE;
                    } else {
                        i = num.intValue();
                        z3 = z;
                    }
                    if (i >= Integer.MAX_VALUE) {
                        long uptimeMillis3 = SystemClock.uptimeMillis() - uptimeMillis;
                        if (z3) {
                            GalleryPreferences.MediaScanner.setEverFillSpecialTypeFlags();
                            HashMap hashMap = new HashMap();
                            hashMap.put(obj3, String.valueOf(uptimeMillis3));
                            GallerySamplingStatHelper.recordCountEvent(str, "fill_special_type_flags", hashMap);
                        }
                        Log.d(str3, str2, z3 ? obj : obj4, Long.valueOf(uptimeMillis3));
                        return;
                    }
                    Context context3 = context;
                    z2 = z3;
                    strArr = strArr3;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.e(str3, "Encounter error when fill special type flags: [%s]", (Object) e);
                        Log.d(str3, str2, obj4, Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        obj2 = obj4;
                        z = false;
                        long uptimeMillis22 = SystemClock.uptimeMillis() - uptimeMillis;
                        if (z) {
                        }
                        if (z) {
                        }
                        Log.d(str3, str2, obj2, Long.valueOf(uptimeMillis22));
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj2 = obj4;
                    long uptimeMillis222 = SystemClock.uptimeMillis() - uptimeMillis;
                    if (z) {
                    }
                    if (z) {
                    }
                    Log.d(str3, str2, obj2, Long.valueOf(uptimeMillis222));
                    throw th;
                }
            }
        }
    }

    private static AlbumEntry findAlbumByLocalPath(Context context, String str) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, ALBUM_PROJECTION, "(serverType=0) AND localFile = ? COLLATE NOCASE AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        AlbumEntry albumEntry = new AlbumEntry();
                        albumEntry.mID = cursor.getLong(0);
                        albumEntry.mServerID = cursor.getString(1);
                        albumEntry.mDateModified = cursor.getLong(2);
                        albumEntry.mLocalFlag = cursor.getInt(3);
                        albumEntry.mServerStatus = cursor.getString(4);
                        albumEntry.mAlbumName = cursor.getString(5);
                        albumEntry.mAttributes = cursor.getInt(6);
                        albumEntry.mEditedColumns = cursor.getString(7);
                        MiscUtil.closeSilently(cursor);
                        return albumEntry;
                    }
                    MiscUtil.closeSilently(cursor);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(cursor);
                    throw th;
                }
            } else {
                throw new IllegalStateException("query album cursor null");
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            MiscUtil.closeSilently(cursor);
            throw th;
        }
    }

    private static AlbumEntry findAlbumByName(Context context, String str) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, ALBUM_PROJECTION, "(serverType=0) AND fileName = ? COLLATE NOCASE AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        AlbumEntry albumEntry = new AlbumEntry();
                        albumEntry.mID = cursor.getLong(0);
                        albumEntry.mServerID = cursor.getString(1);
                        albumEntry.mDateModified = cursor.getLong(2);
                        albumEntry.mLocalFlag = cursor.getInt(3);
                        albumEntry.mServerStatus = cursor.getString(4);
                        albumEntry.mAlbumName = cursor.getString(5);
                        albumEntry.mAttributes = cursor.getInt(6);
                        albumEntry.mEditedColumns = cursor.getString(7);
                        MiscUtil.closeSilently(cursor);
                        return albumEntry;
                    }
                    MiscUtil.closeSilently(cursor);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(cursor);
                    throw th;
                }
            } else {
                throw new IllegalStateException("query album cursor null");
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            MiscUtil.closeSilently(cursor);
            throw th;
        }
    }

    private static AlbumEntry findAlbumByServerId(Context context, long j) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, ALBUM_PROJECTION, "serverId=?", new String[]{String.valueOf(j)}, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        AlbumEntry albumEntry = new AlbumEntry();
                        albumEntry.mID = cursor.getLong(0);
                        albumEntry.mServerID = cursor.getString(1);
                        albumEntry.mDateModified = cursor.getLong(2);
                        albumEntry.mLocalFlag = cursor.getInt(3);
                        albumEntry.mServerStatus = cursor.getString(4);
                        albumEntry.mAlbumName = cursor.getString(5);
                        albumEntry.mAttributes = cursor.getInt(6);
                        albumEntry.mEditedColumns = cursor.getString(7);
                        MiscUtil.closeSilently(cursor);
                        return albumEntry;
                    }
                    MiscUtil.closeSilently(cursor);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(cursor);
                    throw th;
                }
            } else {
                throw new IllegalStateException("query album cursor null");
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            MiscUtil.closeSilently(cursor);
            throw th;
        }
    }

    private static ContentValues generatorValuesForSpecialAlbum(Context context, String str) {
        if (CloudUtils.getCameraLocalFile().equalsIgnoreCase(str)) {
            return CloudUtils.getCameraRecordValues();
        }
        if (CloudUtils.getScreenshotsLocalFile().equalsIgnoreCase(str)) {
            return CloudUtils.getScreenshotsRecordValues();
        }
        if (!ExtraTextUtils.startsWithIgnoreCase(str, "MIUI/Gallery/cloud")) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        long j = 0;
        if (SyncUtil.existXiaomiAccount(context)) {
            j = 1;
        }
        contentValues.put("attributes", Long.valueOf(j));
        return contentValues;
    }

    /* access modifiers changed from: private */
    public static List<Long> getIgnoreAlbumsFromCursor(Cursor cursor, List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null && cursor.getCount() > 0) {
            list.clear();
            while (cursor.moveToNext()) {
                String string = cursor.getString(1);
                if (MediaScannerHelper.isInIgnoreList(string)) {
                    arrayList.add(Long.valueOf(cursor.getLong(0)));
                    list.add(string);
                }
            }
        }
        return arrayList;
    }

    private static ServerReservedAlbumNamesStrategy getServerReservedAlbumNamesStrategy() {
        ServerReservedAlbumNamesStrategy serverReservedAlbumNamesStrategy;
        synchronized (sLock) {
            if (sServerReservedAlbumNamesStrategy == null) {
                sServerReservedAlbumNamesStrategy = CloudControlStrategyHelper.getServerReservedAlbumNamesStrategy();
            }
            serverReservedAlbumNamesStrategy = sServerReservedAlbumNamesStrategy;
        }
        return serverReservedAlbumNamesStrategy;
    }

    private static ServerUnModifyAlbumsStrategy getServerUnModifyAlbumsStrategy() {
        ServerUnModifyAlbumsStrategy serverUnModifyAlbumsStrategy;
        synchronized (sLock) {
            if (sServerUnModifyAlbumsStrategy == null) {
                sServerUnModifyAlbumsStrategy = CloudControlStrategyHelper.getServerUnModifyAlbumsStrategy();
            }
            serverUnModifyAlbumsStrategy = sServerUnModifyAlbumsStrategy;
        }
        return serverUnModifyAlbumsStrategy;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0126 A[SYNTHETIC, Splitter:B:66:0x0126] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0163 A[SYNTHETIC, Splitter:B:75:0x0163] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01e2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01e4  */
    public static AlbumEntry insertAlbum(Context context, String str, ContentValues contentValues) {
        String str2;
        int i;
        boolean z;
        ContentValues generatorValuesForSpecialAlbum;
        Uri safeInsert;
        int i2;
        Context context2 = context;
        String str3 = str;
        ContentValues contentValues2 = contentValues;
        String relativePath = StorageUtils.getRelativePath(context, str);
        if (MediaScannerHelper.isOnlyLinkFileFolder(relativePath) || MediaScannerHelper.isInIgnoreList(relativePath)) {
            return null;
        }
        long lastModified = new File(str3).lastModified();
        long tryGetSystemAlbumServerId = tryGetSystemAlbumServerId(relativePath);
        AlbumEntry albumEntry = new AlbumEntry();
        AlbumsStrategy.Album albumByPath = CloudControlStrategyHelper.getAlbumByPath(StorageUtils.ensureCommonRelativePath(relativePath));
        long j = 64;
        if (albumByPath == null || albumByPath.getAttributes() == null) {
            AlbumsStrategy.Attributes attributesByPath = MediaScannerHelper.getAttributesByPath(relativePath);
            if (attributesByPath != null) {
                long j2 = (long) ((int) (((long) 0) | (attributesByPath.isAutoUpload() ? 1 : 0)));
                if (attributesByPath.isInWhiteList()) {
                    j = 0;
                }
                i2 = (int) (((long) ((int) (((long) ((int) (j2 | j))) | (attributesByPath.isShowInPhotosTab() ? 4 : 0)))) | (attributesByPath.isHide() ? 16 : 0));
                str2 = null;
            } else {
                str2 = null;
                i = (int) (((long) 0) | 64);
                z = true;
                if (z && MediaScannerHelper.isInHideList(relativePath)) {
                    i = (int) (((long) i) | 16);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH.equals(relativePath) ? "sdcard" : FileUtils.getFileName(relativePath);
                }
                if (contentValues2 != null && contentValues2.containsKey("fileName")) {
                    if (!TextUtils.equals(FileUtils.getFileName(relativePath), contentValues2.getAsString("fileName"))) {
                        str2 = contentValues2.getAsString("fileName");
                    }
                    contentValues2.remove("fileName");
                }
                String str4 = str2;
                if (tryGetSystemAlbumServerId <= 0) {
                    try {
                        String serverAlbumName = getServerUnModifyAlbumsStrategy().getServerAlbumName(tryGetSystemAlbumServerId);
                        AlbumEntry findAlbumByName = serverAlbumName != null ? findAlbumByName(context2, serverAlbumName) : null;
                        if (findAlbumByName != null) {
                            ContentValues contentValues3 = new ContentValues();
                            contentValues3.put("fileName", renameAlbum(context2, findAlbumByName.mAlbumName));
                            SafeDBUtil.safeUpdate(context2, GalleryContract.Cloud.CLOUD_URI, contentValues3, "_id=?", new String[]{String.valueOf(findAlbumByName.mID)});
                        }
                    } catch (Exception e) {
                        Log.w("MediaScanner", (Throwable) e);
                        return null;
                    }
                } else {
                    try {
                        if (checkAlbumNameConflict(context2, str4) != 0) {
                            str4 = renameAlbum(context2, str4);
                            Log.i("MediaScanner", "album name conflict %s, rename %s", relativePath, str4);
                            if (TextUtils.isEmpty(str4)) {
                                return null;
                            }
                        }
                    } catch (Exception e2) {
                        Log.w("MediaScanner", (Throwable) e2);
                        return null;
                    }
                }
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("fileName", str4);
                contentValues4.put("dateTaken", Long.valueOf(lastModified));
                contentValues4.put("localFile", relativePath.toLowerCase());
                contentValues4.put("serverType", 0);
                contentValues4.put("localFlag", 7);
                contentValues4.put("attributes", Integer.valueOf(i));
                generatorValuesForSpecialAlbum = generatorValuesForSpecialAlbum(context2, relativePath);
                if (generatorValuesForSpecialAlbum != null) {
                    contentValues4.putAll(generatorValuesForSpecialAlbum);
                }
                if (contentValues2 != null) {
                    contentValues4.putAll(contentValues2);
                }
                safeInsert = SafeDBUtil.safeInsert(context2, GalleryContract.Cloud.CLOUD_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(AlbumEntry.isSyncable(i))).build(), contentValues4);
                if (safeInsert != null) {
                    return null;
                }
                albumEntry.mID = ContentUris.parseId(safeInsert);
                if (albumEntry.mID <= 0) {
                    Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                    generatorCommonParams.put(nexExportFormat.TAG_FORMAT_PATH, str);
                    GallerySamplingStatHelper.recordCountEvent("media_scanner", "insert_album_error", generatorCommonParams);
                    return null;
                }
                albumEntry.mDateModified = lastModified;
                albumEntry.isModified = true;
                albumEntry.mAttributes = i;
                albumEntry.isOnlyLinkFolder = false;
                albumEntry.isShareAlbum = DownloadPathHelper.isShareFolderRelativePath(relativePath);
                return albumEntry;
            }
        } else {
            str2 = albumByPath.getBestName();
            long j3 = (long) ((int) (((long) 0) | (albumByPath.getAttributes().isAutoUpload() ? 1 : 0)));
            if (albumByPath.getAttributes().isInWhiteList()) {
                j = 0;
            }
            i2 = (int) (((long) ((int) (((long) ((int) (j3 | j))) | (albumByPath.getAttributes().isShowInPhotosTab() ? 4 : 0)))) | (albumByPath.getAttributes().isHide() ? 16 : 0));
        }
        i = i2;
        z = false;
        i = (int) (((long) i) | 16);
        if (TextUtils.isEmpty(str2)) {
        }
        if (!TextUtils.equals(FileUtils.getFileName(relativePath), contentValues2.getAsString("fileName"))) {
        }
        contentValues2.remove("fileName");
        String str42 = str2;
        if (tryGetSystemAlbumServerId <= 0) {
        }
        ContentValues contentValues42 = new ContentValues();
        contentValues42.put("fileName", str42);
        contentValues42.put("dateTaken", Long.valueOf(lastModified));
        contentValues42.put("localFile", relativePath.toLowerCase());
        contentValues42.put("serverType", 0);
        contentValues42.put("localFlag", 7);
        contentValues42.put("attributes", Integer.valueOf(i));
        generatorValuesForSpecialAlbum = generatorValuesForSpecialAlbum(context2, relativePath);
        if (generatorValuesForSpecialAlbum != null) {
        }
        if (contentValues2 != null) {
        }
        safeInsert = SafeDBUtil.safeInsert(context2, GalleryContract.Cloud.CLOUD_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(AlbumEntry.isSyncable(i))).build(), contentValues42);
        if (safeInsert != null) {
        }
    }

    public static boolean isScannableDirectory(File file) {
        if (file == null) {
            return false;
        }
        if (new File(file, ".nomedia").exists()) {
            Log.d("MediaScanner", "Directory [%s] contains .nomedia file, skip scan", (Object) file.getAbsolutePath());
            return false;
        } else if (file.isHidden()) {
            Log.d("MediaScanner", "Directory [%s] is hidden, skip scan", (Object) file.getAbsolutePath());
            return false;
        } else if (StorageUtils.isInExternalStorage(GalleryApp.sGetAndroidContext(), file.getAbsolutePath())) {
            return true;
        } else {
            Log.d("MediaScanner", "Directory [%s] is in internal storage, skip scan", (Object) file.getAbsolutePath());
            return false;
        }
    }

    public static boolean isScannableDirectory(String str) {
        return str != null && isScannableDirectory(new File(str));
    }

    static /* synthetic */ Integer lambda$cleanup$50(ThreadPool.JobContext jobContext, CloudMediaBulkDeleter cloudMediaBulkDeleter, Context context, boolean z, ContentProviderBatchOperator contentProviderBatchOperator, Cursor cursor) {
        int i = Reader.READ_DONE;
        if (cursor != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (cursor.moveToNext()) {
                if (jobContext != null && jobContext.isCancelled()) {
                    return null;
                }
                int i2 = cursor.getInt(0);
                int i3 = cursor.getInt(3);
                if (i3 == 7 || i3 == 8) {
                    String string = cursor.getString(2);
                    if (!FileUtils.isFileExist(string)) {
                        long j = (long) i2;
                        if (!cloudMediaBulkDeleter.deleteForResult(context, j)) {
                            return null;
                        }
                        Log.d("MediaScanner", "delete %s", (Object) string);
                        arrayList.add(new DeleteRecord(11, string, "MediaScanner"));
                        arrayList2.add(Long.valueOf(j));
                    } else if (!z && CloudUtils.canUpload(string, false) != 0) {
                        long j2 = (long) i2;
                        if (!cloudMediaBulkDeleter.deleteForResult(context, j2)) {
                            return null;
                        }
                        Log.d("MediaScanner", "delete couldn't upload items %s", (Object) string);
                        arrayList.add(new DeleteRecord(12, string, "MediaScanner"));
                        arrayList2.add(Long.valueOf(j2));
                    }
                } else if (i3 == 0) {
                    checkAndUpdateFileInfo(context, cursor, (long) i2, contentProviderBatchOperator, arrayList);
                }
                i = i2;
            }
            if (MiscUtil.isValid(arrayList)) {
                DeleteRecorder.record((Collection<DeleteRecord>) arrayList);
            }
            if (MiscUtil.isValid(arrayList2)) {
                ImageFeatureManager.getInstance().onImageBatchDelete(arrayList2);
            }
        }
        return Integer.valueOf(i);
    }

    private static long mapAttributeBit(AlbumsStrategy.Attributes attributes, long j) {
        if (attributes == null) {
            return -1;
        }
        if (j == 1) {
            return attributes.isAutoUpload() ? 1 : 0;
        }
        if (j == 4) {
            return attributes.isShowInPhotosTab() ? 4 : 0;
        }
        if (j == 16) {
            return attributes.isHide() ? 16 : 0;
        }
        if (j == 64) {
            return attributes.isInWhiteList() ? 0 : 64;
        }
        return -1;
    }

    private static long mergeAttribute(AlbumsStrategy.Attributes attributes, long j, long j2) {
        long mapAttributeBit = mapAttributeBit(attributes, j2);
        long longValue = AlbumManager.getAlbumAttributes().get(Long.valueOf(j2)).longValue();
        if (mapAttributeBit >= 0) {
            return (j & longValue) == longValue ? (j & j2) | longValue : mapAttributeBit;
        }
        return (j & j2) | (j & longValue);
    }

    private static void prescan(Context context) {
        if (!GalleryPreferences.MediaScanner.isAlbumPruneProtected()) {
            Log.i("MediaScanner", "delete empty albums %d", (Object) Integer.valueOf(SafeDBUtil.safeDelete(context, GalleryContract.Cloud.CLOUD_URI, "_id IN (SELECT _id FROM ((SELECT _id, localFlag FROM cloud WHERE serverType=0) AS Album LEFT JOIN (SELECT localGroupId, count(_id) AS media_count FROM cloud WHERE serverType!=0 GROUP BY localGroupId) AS MediaCount ON Album._id=MediaCount.localGroupId) WHERE localFlag=7 AND (media_count IS NULL OR media_count=0))", (String[]) null)));
        }
    }

    /* access modifiers changed from: private */
    public static AlbumEntry queryAndUpdateAlbum(Context context, String str, ContentValues contentValues) {
        String relativePath = StorageUtils.getRelativePath(context, str);
        if (TextUtils.isEmpty(relativePath)) {
            Log.w("MediaScanner", "Couldn't get relative path from %s", str);
            return null;
        }
        try {
            long tryGetSystemAlbumServerId = tryGetSystemAlbumServerId(relativePath);
            int i = (tryGetSystemAlbumServerId > 0 ? 1 : (tryGetSystemAlbumServerId == 0 ? 0 : -1));
            AlbumEntry findAlbumByServerId = i > 0 ? findAlbumByServerId(context, tryGetSystemAlbumServerId) : findAlbumByLocalPath(context, relativePath);
            if (findAlbumByServerId != null) {
                findAlbumByServerId.isOnlyLinkFolder = MediaScannerHelper.isOnlyLinkFileFolder(relativePath);
                findAlbumByServerId.isShareAlbum = DownloadPathHelper.isShareFolderRelativePath(relativePath);
                long lastModified = new File(str).lastModified();
                findAlbumByServerId.isModified = findAlbumByServerId.mDateModified != lastModified;
                findAlbumByServerId.mDateModified = lastModified;
                if (i <= 0) {
                    updateAlbumByServerConfig(context, findAlbumByServerId, relativePath, contentValues);
                } else if (contentValues != null) {
                    updateSystemAlbumConfig(context, tryGetSystemAlbumServerId, contentValues);
                }
            }
            return findAlbumByServerId;
        } catch (Exception e) {
            Log.w("MediaScanner", (Throwable) e);
            return null;
        }
    }

    private static AlbumEntry queryOrInsertAlbum(Context context, String str) {
        AlbumEntry queryAndUpdateAlbum = queryAndUpdateAlbum(context, str, (ContentValues) null);
        return queryAndUpdateAlbum == null ? insertAlbum(context, str, (ContentValues) null) : queryAndUpdateAlbum;
    }

    /* access modifiers changed from: private */
    public static void recordHiddenAlbum(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("album_relative_path", str);
        GallerySamplingStatHelper.recordCountEvent("media_scanner", "scanner_directory_regard_as_hidden", hashMap);
    }

    public static void refreshIgnoreList(Context context, ThreadPool.JobContext jobContext) {
        if (GalleryPreferences.MediaScanner.getLastRefreshedIgnoreListVersion() != CloudControlStrategyHelper.getIgnoreAlbumVersion()) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            int i = 1000;
            long j = 0;
            int i2 = 0;
            while (true) {
                if (jobContext == null || !jobContext.isCancelled()) {
                    List list = (List) SafeDBUtil.safeQuery(context, UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, i), new String[]{"_id", "localFile"}, "serverType=? AND attributes&64=64 AND _id>?", new String[]{String.valueOf(0), String.valueOf(j)}, "_id ASC", new SafeDBUtil.QueryHandler(arrayList) {
                        private final /* synthetic */ List f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final Object handle(Cursor cursor) {
                            return MediaScanner.getIgnoreAlbumsFromCursor(cursor, this.f$0);
                        }
                    });
                    if (list.size() > 0) {
                        i2 += list.size();
                        j = ((Long) list.get(list.size() - 1)).longValue();
                        SafeDBUtil.safeDelete(context, GalleryContract.Cloud.CLOUD_URI, "(localGroupId IN ('" + TextUtils.join("','", list) + "') AND " + "serverType" + "!=?)" + " OR " + "(" + "_id" + " IN (" + TextUtils.join(",", list) + ") AND " + "serverType" + "=?)", new String[]{String.valueOf(0), String.valueOf(0)});
                        DeleteRecorder.record(new DeleteRecord(15, TextUtils.join(";", arrayList), "MediaScanner"));
                    } else {
                        Context context2 = context;
                    }
                    if (list.size() <= 0) {
                        if (i2 > 0) {
                            Log.d("MediaScanner", "refresh %d ignore albums, cost %d ms", Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                        GalleryPreferences.MediaScanner.setLastRefreshedIgnoreListVersion(CloudControlStrategyHelper.getIgnoreAlbumVersion());
                        return;
                    }
                    i = 1000;
                } else {
                    return;
                }
            }
        }
    }

    private static String renameAlbum(Context context, String str) {
        String str2;
        int i = 0;
        while (true) {
            String valueOf = String.valueOf(System.currentTimeMillis());
            str2 = str + "_" + valueOf.substring(valueOf.length() - 2);
            if (checkAlbumNameConflict(context, str2) == 0) {
                break;
            }
            int i2 = i + 1;
            if (i >= 3) {
                i = i2;
                break;
            }
            i = i2;
        }
        if (i < 3) {
            return str2;
        }
        return str + "_" + System.currentTimeMillis();
    }

    public static int scanDirectories(Context context, List<String> list, ThreadPool.JobContext jobContext, boolean z, boolean z2) {
        Context context2 = context;
        List<String> list2 = list;
        ThreadPool.JobContext jobContext2 = jobContext;
        if (list2 == null || list.size() == 0) {
            return 0;
        }
        prescan(context);
        if (jobContext2 != null && jobContext.isCancelled()) {
            return 0;
        }
        CloudMediaBulkInserter cloudMediaBulkInserter = new CloudMediaBulkInserter(false);
        ContentProviderBatchOperator contentProviderBatchOperator = new ContentProviderBatchOperator("com.miui.gallery.cloud.provider");
        ScannerDirectoryClient scannerDirectoryClient = new ScannerDirectoryClient(new ScannerFileClient(z2, cloudMediaBulkInserter, contentProviderBatchOperator, false, false), jobContext2);
        int i = 0;
        while (i < list.size()) {
            String str = list2.get(i);
            if (jobContext2 == null || !jobContext.isCancelled()) {
                try {
                    if (!isScannableDirectory(str)) {
                        boolean z3 = z;
                    } else {
                        try {
                            long currentTimeMillis = System.currentTimeMillis();
                            try {
                                scannerDirectoryClient.scanFolder(context2, new File(str), z);
                                Log.d("MediaScanner", "scan folder [%s] costs [%d]", str, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            } catch (Exception e) {
                                e = e;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            boolean z4 = z;
                            Log.w("MediaScanner", "scan folder %s %s", str, e);
                            i++;
                        }
                    }
                    i++;
                } catch (Throwable th) {
                    cloudMediaBulkInserter.flush(context2);
                    contentProviderBatchOperator.apply(context2);
                    throw th;
                }
            } else {
                cloudMediaBulkInserter.flush(context2);
                contentProviderBatchOperator.apply(context2);
                return i;
            }
        }
        cloudMediaBulkInserter.flush(context2);
        contentProviderBatchOperator.apply(context2);
        return list.size();
    }

    public static int scanFiles(Context context, List<String> list, ThreadPool.JobContext jobContext) {
        String str;
        Context context2 = context;
        if (!MiscUtil.isValid(list)) {
            return 0;
        }
        if (jobContext != null && jobContext.isCancelled()) {
            return 0;
        }
        prescan(context);
        CloudMediaBulkInserter cloudMediaBulkInserter = new CloudMediaBulkInserter(false);
        ContentProviderBatchOperator contentProviderBatchOperator = new ContentProviderBatchOperator("com.miui.gallery.cloud.provider");
        ScannerFileClient scannerFileClient = new ScannerFileClient(true, cloudMediaBulkInserter, contentProviderBatchOperator, false, false);
        HashMap hashMap = new HashMap();
        int i = 0;
        boolean z = false;
        while (true) {
            try {
                boolean z2 = true;
                if (i >= list.size()) {
                    List<String> list2 = list;
                    cloudMediaBulkInserter.flush(context2);
                    if (z) {
                        SyncUtil.requestSync(context2, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(33).setDelayUpload(true).build());
                    }
                    cloudMediaBulkInserter.flush(context2);
                    contentProviderBatchOperator.apply(context2);
                    return list.size();
                } else if (jobContext == null || !jobContext.isCancelled()) {
                    str = list.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        File file = new File(str);
                        if (!file.exists()) {
                            cleanFile(context2, str);
                        } else if (file.isFile()) {
                            String parent = file.getParent();
                            if (parent != null) {
                                String supportUploadMimeType = FileMimeUtil.getSupportUploadMimeType(str);
                                if (!FileMimeUtil.isImageFromMimeType(supportUploadMimeType)) {
                                    if (!FileMimeUtil.isVideoFromMimeType(supportUploadMimeType)) {
                                        Log.w("MediaScanner", "Unsupported MimeType: [%s], path: [%s]", supportUploadMimeType, str);
                                    }
                                }
                                if (!MediaScannerHelper.isOnlyLinkFileFolder(StorageUtils.getRelativePath(context2, parent))) {
                                    if (!isScannableDirectory(parent)) {
                                        Log.w("MediaScanner", "Album directory is hidden or contains \".nomedia\" file: [%s]", parent);
                                    } else {
                                        AlbumEntry albumEntry = (AlbumEntry) hashMap.get(parent);
                                        if (albumEntry == null) {
                                            albumEntry = queryOrInsertAlbum(context2, parent);
                                            if (albumEntry == null) {
                                                Log.i("MediaScanner", "album query failed or insert ignored: [%s]", (Object) parent);
                                            } else {
                                                hashMap.put(parent, albumEntry);
                                            }
                                        }
                                        if (albumEntry.isModified && AlbumEntry.shouldScan(albumEntry)) {
                                            SystemClock.uptimeMillis();
                                            long scanFile = scannerFileClient.scanFile(context2, new File(str), albumEntry);
                                            if (-1 == scanFile) {
                                                Log.w("MediaScanner", "scan [%s] fail", str);
                                            }
                                            if (scanFile > 0 || -4 == scanFile || -7 == scanFile) {
                                                if ((((long) albumEntry.mAttributes) & 1) == 0) {
                                                    z2 = false;
                                                }
                                                z |= z2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i++;
                } else {
                    if (z) {
                        SyncUtil.requestSync(context2, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(33).setDelayUpload(true).build());
                    }
                    cloudMediaBulkInserter.flush(context2);
                    contentProviderBatchOperator.apply(context2);
                    return i;
                }
            } catch (Exception e) {
                Log.w("MediaScanner", "scan file [%s] failed with exception: [%s]", str, e);
            } catch (Throwable th) {
                cloudMediaBulkInserter.flush(context2);
                contentProviderBatchOperator.apply(context2);
                throw th;
            }
        }
    }

    public static void scanSingleFile(Context context, String str) {
        doScanSingleFile(context, str, true);
    }

    static void scanSingleFileForExternal(Context context, String str) {
        doScanSingleFile(context, str, false);
    }

    private static void scannerCorrectLogic(Context context, ThreadPool.JobContext jobContext) {
        File[] listFiles;
        Context context2 = context;
        if (ApplicationHelper.isSecretAlbumFeatureOpen() && GalleryPreferences.Sync.getSyncCompletelyFinish() && !GalleryPreferences.MediaScanner.isEverRestoreSecretItems() && !jobContext.isCancelled()) {
            String[] pathsInExternalStorage = StorageUtils.getPathsInExternalStorage(context2, StorageUtils.DIRECTORY_SECRET_ALBUM_PATH);
            if (pathsInExternalStorage == null || pathsInExternalStorage.length <= 0) {
                ThreadPool.JobContext jobContext2 = jobContext;
            } else {
                final Pattern compile = Pattern.compile("^[0-9a-zA-Z]+$");
                final ThreadPool.JobContext jobContext3 = jobContext;
                AnonymousClass2 r3 = new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        String[] split;
                        if (!jobContext3.isCancelled() && FileMimeUtil.isImageFromMimeType(FileMimeUtil.getMimeType(str)) && (split = str.split("\\.")) != null) {
                            return split.length <= 2 || (split.length > 2 && !compile.matcher(split[split.length - 2]).matches());
                        }
                        return false;
                    }
                };
                int length = pathsInExternalStorage.length;
                int i = 0;
                while (i < length) {
                    String str = pathsInExternalStorage[i];
                    if (!jobContext.isCancelled()) {
                        if (FileUtils.isFileExist(str) && (listFiles = new File(str).listFiles(r3)) != null) {
                            int length2 = listFiles.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                File file = listFiles[i2];
                                if (!jobContext.isCancelled()) {
                                    Log.d("MediaScanner", "secret item %s", (Object) file.getAbsolutePath());
                                    SaveToCloudUtil.SaveParams saveParams = r10;
                                    String str2 = "MediaScanner";
                                    SaveToCloudUtil.SaveParams saveParams2 = new SaveToCloudUtil.SaveParams(file, -1000, true, 8, true, true);
                                    if (SaveToCloudUtil.saveToCloudDB(context2, saveParams) > 0) {
                                        Log.d(str2, "secret item restore %s", (Object) file.getAbsolutePath());
                                    }
                                    i2++;
                                } else {
                                    return;
                                }
                            }
                            continue;
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
            if (!jobContext.isCancelled()) {
                GalleryPreferences.MediaScanner.setHasRestoredSecretItems();
            }
        }
    }

    private static long tryGetSystemAlbumServerId(String str) {
        if (CloudUtils.getCameraLocalFile().equalsIgnoreCase(str)) {
            return 1;
        }
        return CloudUtils.getScreenshotsLocalFile().equalsIgnoreCase(str) ? 2 : -1;
    }

    private static boolean tryRenameConflictAlbums(Context context, String str) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, ALBUM_PROJECTION, "(serverType=0) AND fileName = ? COLLATE NOCASE AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    long j = cursor.getLong(1);
                    if (j != 1) {
                        if (j != 2) {
                            int i = cursor.getInt(3);
                            long j2 = cursor.getLong(0);
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("fileName", renameAlbum(context, str));
                            if (i == 0) {
                                contentValues.put("localFlag", 10);
                            }
                            SafeDBUtil.safeUpdate(context, GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=?", new String[]{String.valueOf(j2)});
                            Log.d("MediaScanner", "Rename conflict album according to server config file: %s", (Object) str);
                            cursor.moveToNext();
                        }
                    }
                    return false;
                }
                MiscUtil.closeSilently(cursor);
                return true;
            }
            throw new IllegalStateException("query album cursor null");
        } finally {
            MiscUtil.closeSilently(cursor);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c4, code lost:
        if (r6.startsWith((r5 + "_").toLowerCase()) == false) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x015f, code lost:
        if (r6.startsWith((r5 + "_").toLowerCase()) == false) goto L_0x0161;
     */
    private static void updateAlbumByServerConfig(Context context, AlbumEntry albumEntry, String str, ContentValues contentValues) {
        boolean z;
        long j;
        Context context2 = context;
        AlbumEntry albumEntry2 = albumEntry;
        String str2 = str;
        ContentValues contentValues2 = contentValues;
        if (albumEntry2 != null) {
            ContentValues contentValues3 = new ContentValues();
            String fileName = StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH.equals(str2) ? "sdcard" : FileUtils.getFileName(str);
            boolean z2 = contentValues2 != null && contentValues2.containsKey("fileName") && !TextUtils.equals(fileName, contentValues2.getAsString("fileName"));
            AlbumsStrategy.Album albumByPath = CloudControlStrategyHelper.getAlbumByPath(StorageUtils.ensureCommonRelativePath(str));
            if (albumByPath != null) {
                z = albumByPath.getAttributes() == null;
                boolean z3 = albumByPath.getAttributes() != null && albumByPath.getAttributes().isManualRenameRestricted();
                if (z2 || fileName.equalsIgnoreCase(albumEntry2.mAlbumName) || z3) {
                    String str3 = null;
                    if (z3) {
                        str3 = albumByPath.getBestName();
                    }
                    if (TextUtils.isEmpty(str3) && z2) {
                        str3 = contentValues2.getAsString("fileName");
                    }
                    if (TextUtils.isEmpty(str3)) {
                        str3 = albumByPath.getBestName();
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        if (!TextUtils.isEmpty(albumEntry2.mAlbumName)) {
                            if (!str3.equalsIgnoreCase(albumEntry2.mAlbumName)) {
                                String lowerCase = albumEntry2.mAlbumName.toLowerCase();
                            }
                        }
                        Log.d("MediaScanner", "Rename album according to server config file: %s", (Object) str2);
                        int checkAlbumNameConflict = checkAlbumNameConflict(context2, str3);
                        if (checkAlbumNameConflict != 0) {
                            if (!z3 || checkAlbumNameConflict == 2) {
                                str3 = renameAlbum(context2, str3);
                            } else if (!tryRenameConflictAlbums(context2, str3)) {
                                str3 = renameAlbum(context2, str3);
                            }
                        }
                        if (!TextUtils.isEmpty(str3)) {
                            if (albumEntry2.mLocalFlag == 0) {
                                contentValues3.put("localFlag", 10);
                            }
                            contentValues3.put("fileName", str3);
                        }
                    }
                }
                j = 0;
                for (Iterator<Long> it = AlbumManager.getAlbumAttributes().keySet().iterator(); it.hasNext(); it = it) {
                    j |= mergeAttribute(albumByPath.getAttributes(), (long) albumEntry2.mAttributes, it.next().longValue());
                }
            } else {
                if (z2) {
                    String asString = contentValues2.getAsString("fileName");
                    if (!TextUtils.isEmpty(asString)) {
                        if (!TextUtils.isEmpty(albumEntry2.mAlbumName)) {
                            if (!asString.equalsIgnoreCase(albumEntry2.mAlbumName)) {
                                String lowerCase2 = albumEntry2.mAlbumName.toLowerCase();
                            }
                        }
                        Log.d("MediaScanner", "Rename album according to override values: %s", (Object) str2);
                        if (checkAlbumNameConflict(context2, asString) != 0) {
                            asString = renameAlbum(context2, asString);
                        }
                        if (!TextUtils.isEmpty(asString)) {
                            if (albumEntry2.mLocalFlag == 0) {
                                contentValues3.put("localFlag", 10);
                            }
                            contentValues3.put("fileName", asString);
                        }
                    }
                }
                AlbumsStrategy.Attributes attributesByPath = MediaScannerHelper.getAttributesByPath(str);
                if (attributesByPath != null) {
                    long j2 = 0;
                    for (Long longValue : AlbumManager.getAlbumAttributes().keySet()) {
                        j2 = j | mergeAttribute(attributesByPath, (long) albumEntry2.mAttributes, longValue.longValue());
                    }
                    z = false;
                } else {
                    j = (long) albumEntry2.mAttributes;
                    z = true;
                }
            }
            if (z) {
                if (!((j & 16) != 0)) {
                    if (!((32 & j) != 0) && MediaScannerHelper.isInHideList(str)) {
                        Log.d("MediaScanner", "update server config file: %s, set album as hidden.", (Object) str2);
                        j |= 16;
                    }
                }
            }
            if (j != ((long) albumEntry2.mAttributes)) {
                Log.d("MediaScanner", "update server config file: %s, attributes: %s", str2, Long.valueOf(j));
                contentValues3.put("attributes", Long.valueOf(j));
            }
            if (contentValues2 != null && contentValues.size() > 0) {
                contentValues2.remove("fileName");
                contentValues3.putAll(contentValues2);
                Log.d("MediaScanner", "Override config for: [%s], values: [%s]", str2, contentValues2);
            }
            if (contentValues3.size() > 0) {
                if (contentValues3.containsKey("attributes") && contentValues3.getAsLong("attributes").longValue() != ((long) albumEntry2.mAttributes)) {
                    contentValues3.put("editedColumns", GalleryCloudUtils.mergeEditedColumns(albumEntry2.mEditedColumns, GalleryCloudUtils.transformToEditedColumnsElement(68)));
                }
                SafeDBUtil.safeUpdate(context2, GalleryContract.Cloud.CLOUD_URI, contentValues3, "_id=?", new String[]{String.valueOf(albumEntry2.mID)});
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateAlbumDateModified(Context context, AlbumEntry albumEntry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dateModified", Long.valueOf(albumEntry.mDateModified));
        SafeDBUtil.safeUpdate(context, GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=?", new String[]{String.valueOf(albumEntry.mID)});
    }

    public static void updateOrInsertAlbum(Context context, String str, ContentValues contentValues) {
        if (queryAndUpdateAlbum(context, str, contentValues) == null) {
            insertAlbum(context, str, contentValues);
        }
    }

    private static void updateSystemAlbumConfig(Context context, long j, ContentValues contentValues) {
        contentValues.remove("fileName");
        if (contentValues.size() > 0) {
            SafeDBUtil.safeUpdate(context, GalleryContract.Cloud.CLOUD_URI, contentValues, "serverId=?", new String[]{String.valueOf(j)});
            Log.d("MediaScanner", "Override config for system album: %s, values: %s", Long.valueOf(j), contentValues);
        }
    }
}
