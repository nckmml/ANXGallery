package com.miui.gallery.provider.cloudmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import com.miui.gallery.Config;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.SpaceFullHandler;
import com.miui.gallery.data.DBCloud;
import com.miui.gallery.photosapi.PhotosOemApi;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.AlbumManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.scanner.SaveToCloudUtil;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.ExtraTextUtils;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryExifUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import com.miui.gallery.util.logger.TimingTracing;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import miui.util.IOUtils;
import miui.util.Pools;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudManager {
    private static final Set<String> METHODS = new HashSet();
    /* access modifiers changed from: private */
    public static final String[] PRIVATE_COPYABLE_PROJECTION = {"thumbnailFile", "downloadFile", "localFile", "microthumbfile"};
    static final String[] PROJECTION = {"_id", "groupId", "localFlag", "localGroupId", "serverId", "serverType", "fileName", "localFile", "thumbnailFile", "sha1", "ubiSubImageCount", "secretKey", "microthumbfile", "albumId", "description", "size", "dateModified", "mimeType", "title", "description", "dateTaken", "duration", "serverTag", "serverStatus", "downloadFile", "sortBy", "localImageId", "downloadFileStatus", "downloadFileTime", "mixedDateTime", "exifImageWidth", "exifImageLength", "exifOrientation", "exifGPSLatitude", "exifGPSLongitude", "exifMake", "exifModel", "exifFlash", "exifGPSLatitudeRef", "exifGPSLongitudeRef", "exifExposureTime", "exifFNumber", "exifISOSpeedRatings", "exifGPSAltitude", "exifGPSAltitudeRef", "exifGPSTimeStamp", "exifGPSDateStamp", "exifWhiteBalance", "exifFocalLength", "exifGPSProcessingMethod", "exifDateTime", "creatorId", "ubiFocusIndex", "ubiSubIndex", "editedColumns", "fromLocalGroupId", "location", "extraGPS", "address", "specialTypeFlags"};
    /* access modifiers changed from: private */
    public static final String[] PUBLIC_COPYABLE_PROJECTION = {"fileName", "dateTaken", "dateModified", "description", "serverType", "size", "mimeType", "title", "sha1", "duration", "appKey", "babyInfoJson", "mixedDateTime", "location", "extraGPS", "address", "exifImageWidth", "exifImageLength", "exifOrientation", "exifGPSLatitude", "exifGPSLongitude", "exifMake", "exifModel", "exifFlash", "exifGPSLatitudeRef", "exifGPSLongitudeRef", "exifExposureTime", "exifFNumber", "exifISOSpeedRatings", "exifGPSAltitude", "exifGPSAltitudeRef", "exifGPSTimeStamp", "exifGPSDateStamp", "exifWhiteBalance", "exifFocalLength", "exifGPSProcessingMethod", "exifDateTime", "ubiSubImageCount", "ubiFocusIndex", "ubiSubIndex", "specialTypeFlags"};
    private static final String[] QUERY_BY_PATH_PROJECTION = {"_id", "sha1", "size"};

    private static class AddRemoveFavoritesById extends AddRemoveFavoritesBySha1 {
        private long mId;

        public AddRemoveFavoritesById(Context context, ArrayList<Long> arrayList, int i, long j) {
            super(context, arrayList, i, (String) null);
            this.mId = j;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            Cursor cursor2 = null;
            try {
                cursor = sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(this.mId)}, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            this.mSha1 = cursor.getString(9);
                            MiscUtil.closeSilently(cursor);
                            return super.prepare(sQLiteDatabase);
                        }
                    } catch (Exception unused) {
                        MiscUtil.closeSilently(cursor);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        MiscUtil.closeSilently(cursor2);
                        throw th;
                    }
                }
                MiscUtil.closeSilently(cursor);
                return null;
            } catch (Exception unused2) {
                cursor = null;
                MiscUtil.closeSilently(cursor);
                return null;
            } catch (Throwable th2) {
                th = th2;
                MiscUtil.closeSilently(cursor2);
                throw th;
            }
        }

        public String toString() {
            Locale locale = Locale.US;
            Object[] objArr = new Object[2];
            objArr[0] = this.mOperationType == 1 ? "Add to" : "Remove from";
            objArr[1] = Long.valueOf(this.mId);
            return String.format(locale, "%s favorites by id: [%d]", objArr);
        }
    }

    private static class AddRemoveFavoritesByPath extends AddRemoveFavoritesBySha1 {
        private String mPath;

        public AddRemoveFavoritesByPath(Context context, ArrayList<Long> arrayList, int i, String str) {
            super(context, arrayList, i, (String) null);
            this.mPath = str;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = CloudManager.queryCloudItemByFilePath(this.mContext, sQLiteDatabase, this.mPath);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            boolean z = false;
                            if (cursor.getExtras() != null && cursor.getExtras().getBoolean("is_thumbnail", false)) {
                                z = true;
                            }
                            if (z || cursor.getLong(2) == FileUtils.getFileSize(this.mPath)) {
                                this.mSha1 = cursor.getString(1);
                                MiscUtil.closeSilently(cursor);
                                return super.prepare(sQLiteDatabase);
                            }
                            Log.e("CloudManager", "file size not equals, can not favorite: %s", (Object) this.mPath);
                            MiscUtil.closeSilently(cursor);
                            return null;
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            Log.e("CloudManager", (Throwable) e);
                            MiscUtil.closeSilently(cursor);
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            MiscUtil.closeSilently(cursor);
                            throw th;
                        }
                    }
                }
                MiscUtil.closeSilently(cursor);
                return null;
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                Log.e("CloudManager", (Throwable) e);
                MiscUtil.closeSilently(cursor);
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                MiscUtil.closeSilently(cursor);
                throw th;
            }
        }

        public String toString() {
            Locale locale = Locale.US;
            Object[] objArr = new Object[2];
            objArr[0] = this.mOperationType == 1 ? "Add to" : "Remove from";
            objArr[1] = this.mPath;
            return String.format(locale, "%s favorites by path: %s", objArr);
        }
    }

    private static class AddRemoveFavoritesBySha1 extends CursorTask {
        protected int mOperationType;
        protected String mSha1;

        public AddRemoveFavoritesBySha1(Context context, ArrayList<Long> arrayList, int i, String str) {
            super(context, arrayList);
            this.mOperationType = i;
            this.mSha1 = str;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0061 A[Catch:{ all -> 0x0045 }] */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            Cursor cursor;
            long j;
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("dateFavorite", Long.valueOf(currentTimeMillis));
            contentValues.put("isFavorite", Integer.valueOf(this.mOperationType == 1 ? 1 : 0));
            try {
                cursor = CloudManager.queryFavoritesTableBySha1(sQLiteDatabase, new String[]{"_id"}, this.mSha1);
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            j = (long) CloudManager.updateFavoritesBySha1(sQLiteDatabase, contentValues, this.mSha1);
                            if (j > 0) {
                                if (this.mOperationType == 1) {
                                    mediaManager.insertToFavorites(this.mSha1);
                                } else {
                                    mediaManager.removeFromFavorites(this.mSha1);
                                }
                                String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(-1);
                                sQLiteDatabase.execSQL(String.format("update %s set %s=coalesce(replace(%s, '%s', '') || '%s', '%s') where %s='%s'", new Object[]{"cloud", "editedColumns", "editedColumns", transformToEditedColumnsElement, transformToEditedColumnsElement, transformToEditedColumnsElement, "sha1", this.mSha1}));
                            }
                            MiscUtil.closeSilently(cursor);
                            return j;
                        }
                    } catch (Throwable th) {
                        th = th;
                        MiscUtil.closeSilently(cursor);
                        throw th;
                    }
                }
                contentValues.put("sha1", this.mSha1);
                contentValues.put("source", 1);
                j = sQLiteDatabase.insert("favorites", (String) null, contentValues);
                if (j > 0) {
                }
                MiscUtil.closeSilently(cursor);
                return j;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                MiscUtil.closeSilently(cursor);
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "sha1 = ? AND serverType IN (1,2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000)", new String[]{this.mSha1}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            Locale locale = Locale.US;
            Object[] objArr = new Object[2];
            objArr[0] = this.mOperationType == 1 ? "Add to" : "Remove from";
            objArr[1] = this.mSha1;
            return String.format(locale, "%s favorites by sha1: %s", objArr);
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            return verify != -1 ? verify : (!this.mCursor.isNull(4) || new CheckPostProcessing(this.mContext, this.mCursor.getString(7)).run(sQLiteDatabase, (MediaManager) null) != -111) ? -1 : -111;
        }
    }

    private static class AddToSecret extends CursorTask {
        private long mMediaId;

        public AddToSecret(Context context, ArrayList<Long> arrayList, long j) {
            super(context, arrayList);
            this.mMediaId = j;
        }

        private long addFilePath(ContentValues contentValues, boolean z) {
            String str;
            boolean z2;
            ContentValues contentValues2 = contentValues;
            contentValues2.put("microthumbfile", this.mCursor.getString(12));
            String addPostfixToFileName = z ? DownloadPathHelper.addPostfixToFileName(this.mCursor.getString(6), String.valueOf(System.currentTimeMillis())) : this.mCursor.getString(6);
            contentValues2.put("fileName", addPostfixToFileName);
            String string = this.mCursor.getString(7);
            String safePathInPriorStorage = StorageUtils.getSafePathInPriorStorage(StorageUtils.DIRECTORY_SECRET_ALBUM_PATH);
            if (FileUtils.isFileExist(string)) {
                String encodeFileName = CloudUtils.SecretAlbumUtils.encodeFileName(addPostfixToFileName, this.mCursor.getInt(5) == 2);
                if (!TextUtils.equals(addPostfixToFileName, encodeFileName) || z) {
                    File file = new File(FileUtils.getParentFolderPath(string), encodeFileName);
                    if (FileUtils.move(new File(string), file)) {
                        string = file.getAbsolutePath();
                    } else {
                        Log.e("CloudManager", "Failed to move name conflict item %s to %s", string, encodeFileName);
                        HashMap hashMap = new HashMap();
                        hashMap.put("detail", "local file rename conflict file");
                        hashMap.put("src", string);
                        hashMap.put("des", file.getAbsolutePath());
                        GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_failed_bc_file_operation_failed", hashMap);
                        return -113;
                    }
                }
                if (!FileUtils.contains(safePathInPriorStorage, string)) {
                    String moveImageToFolder = CloudUtils.moveImageToFolder(string, safePathInPriorStorage, true);
                    if (!FileUtils.contains(safePathInPriorStorage, moveImageToFolder)) {
                        Log.e("CloudManager", "Failed to move item %s to secret folder", (Object) string);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("detail", "local file move failed to secret folder");
                        hashMap2.put("src", string);
                        hashMap2.put("des", safePathInPriorStorage);
                        GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_failed_bc_file_operation_failed", hashMap2);
                        return -113;
                    }
                    string = moveImageToFolder;
                } else {
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("detail", "local file already exist in secret folder");
                    hashMap3.put("src", string);
                    hashMap3.put("des", safePathInPriorStorage);
                    GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap3);
                }
                contentValues2.put("localFile", string);
                String string2 = this.mCursor.getString(7);
                if (!FileUtils.isFileExist(string2)) {
                    MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, string2);
                } else {
                    HashMap hashMap4 = new HashMap();
                    hashMap4.put("detail", "local file still exist after move to secret");
                    hashMap4.put("src", string2);
                    GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap4);
                }
                str = string2;
                z2 = true;
            } else {
                str = string;
                z2 = false;
            }
            String string3 = this.mCursor.getString(8);
            if (!FileUtils.isFileExist(string3)) {
                return -1;
            }
            String encodeFileName2 = CloudUtils.SecretAlbumUtils.encodeFileName(addPostfixToFileName, false);
            if (z2) {
                HashMap hashMap5 = new HashMap();
                hashMap5.put("detail", "local & thumbnail both exist");
                hashMap5.put("localFile", str);
                hashMap5.put("thumbnailFile", string3);
                GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap5);
                contentValues2.putNull("thumbnailFile");
                MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, string3);
                return -1;
            }
            if (!TextUtils.equals(addPostfixToFileName, encodeFileName2) || z) {
                File file2 = new File(FileUtils.getParentFolderPath(string3), encodeFileName2);
                if (FileUtils.move(new File(string3), file2)) {
                    string3 = file2.getAbsolutePath();
                } else {
                    String str2 = encodeFileName2;
                    Log.e("CloudManager", "Failed to move name conflict item %s to %s", string3, str2);
                    HashMap hashMap6 = new HashMap();
                    hashMap6.put("detail", "thumbnail rename conflict file");
                    hashMap6.put("src", string3);
                    hashMap6.put("des", file2.getAbsolutePath());
                    GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_failed_bc_file_operation_failed", hashMap6);
                    return -113;
                }
            }
            if (!FileUtils.contains(safePathInPriorStorage, string3)) {
                string3 = CloudUtils.moveImageToFolder(string3, safePathInPriorStorage, true);
                if (!FileUtils.contains(safePathInPriorStorage, string3)) {
                    Log.e("CloudManager", "Failed to move item %s to secret folder", (Object) str);
                    HashMap hashMap7 = new HashMap();
                    hashMap7.put("detail", "thumbnail file move failed to secret folder");
                    hashMap7.put("src", string3);
                    hashMap7.put("des", safePathInPriorStorage);
                    GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_failed_bc_file_operation_failed", hashMap7);
                    return -113;
                }
            } else {
                HashMap hashMap8 = new HashMap();
                hashMap8.put("detail", "thumbnail file already exist in secret folder");
                hashMap8.put("src", string3);
                hashMap8.put("des", safePathInPriorStorage);
                GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap8);
            }
            contentValues2.put("thumbnailFile", string3);
            String string4 = this.mCursor.getString(8);
            if (!FileUtils.isFileExist(string4)) {
                MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, string4);
                return -1;
            }
            HashMap hashMap9 = new HashMap();
            hashMap9.put("detail", "thumbnail file still exist after move to secret");
            hashMap9.put("src", string4);
            GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap9);
            return -1;
        }

        static int canAddSecret(Context context, Cursor cursor) {
            if (SpaceFullHandler.isOwnerSpaceFull()) {
                return -106;
            }
            if (cursor.getInt(5) != 1 && (cursor.getInt(5) != 2 || !Config.SecretAlbumConfig.isVideoSupported())) {
                return -107;
            }
            if (cursor.isNull(4)) {
                String string = cursor.getString(7);
                if (!FileUtils.isFileExist(string)) {
                    return -102;
                }
                int canUpload = CloudUtils.canUpload(string);
                if (canUpload == 4) {
                    return -108;
                }
                if (canUpload != 0) {
                    return -107;
                }
            }
            return (!cursor.isNull(4) || new CheckPostProcessing(context, cursor.getString(7)).run((SQLiteDatabase) null, (MediaManager) null) != -111) ? 0 : -111;
        }

        private int checkConflict(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            int i;
            String string = this.mCursor.getString(9);
            String string2 = this.mCursor.getString(6);
            String encodeFileName = CloudUtils.SecretAlbumUtils.encodeFileName(string2, this.mCursor.getInt(5) == 2);
            if (!TextUtils.isEmpty(string)) {
                try {
                    Cursor query = sQLiteDatabase.query("cloud", new String[]{"sha1", "fileName", "localFlag", "localFile"}, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (sha1 = ? OR fileName = ? OR fileName = ?) AND localGroupId=? AND (serverId IS NOT NULL OR localFile IS NOT NULL)", new String[]{string, string2, encodeFileName, String.valueOf(-1000)}, (String) null, (String) null, (String) null);
                    if (query != null) {
                        boolean z = false;
                        while (true) {
                            try {
                                if (query.moveToNext()) {
                                    if (TextUtils.equals(string, query.getString(0))) {
                                        int i2 = query.getInt(2);
                                        String string3 = query.getString(3);
                                        if ((i2 == 7 || i2 == 8) && !FileUtils.isFileExist(string3)) {
                                            GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_sha1_conflict_with_unsynced_file_unexist_item");
                                        }
                                    }
                                    String string4 = query.getString(1);
                                    if (!z && (string2.equalsIgnoreCase(string4) || encodeFileName.equalsIgnoreCase(string4))) {
                                        z = true;
                                    }
                                } else if (z) {
                                    i = -105;
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor = query;
                                MiscUtil.closeSilently(cursor);
                                throw th;
                            }
                        }
                        i = -104;
                        MiscUtil.closeSilently(query);
                        return i;
                    }
                    MiscUtil.closeSilently(query);
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    MiscUtil.closeSilently(cursor);
                    throw th;
                }
            }
            return 0;
        }

        private void deleteAllWithSameSha1(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, boolean z) {
            String string = this.mCursor.getString(9);
            if (!TextUtils.isEmpty(string)) {
                String str = " AND _id!=" + this.mMediaId;
                String str2 = "sha1=? AND localGroupId!=-1000";
                if (!z) {
                    str2 = str2 + str;
                }
                Cursor cursor = null;
                try {
                    cursor = sQLiteDatabase.query("cloud", new String[]{"_id"}, str2, new String[]{string}, (String) null, (String) null, (String) null);
                    if (cursor != null && cursor.getCount() > 0) {
                        long[] jArr = new long[cursor.getCount()];
                        for (int i = 0; i < cursor.getCount(); i++) {
                            cursor.moveToPosition(i);
                            jArr[i] = cursor.getLong(0);
                        }
                        long[] unused = CloudManager.delete(this.mContext, sQLiteDatabase, mediaManager, getDirtyBulk(), jArr, 36);
                    }
                } catch (Exception e) {
                    Log.e("CloudManager", (Throwable) e);
                } catch (Throwable th) {
                    MiscUtil.closeSilently((Closeable) null);
                    throw th;
                }
                MiscUtil.closeSilently(cursor);
            }
        }

        private void postCheck(SQLiteDatabase sQLiteDatabase, long j, boolean z) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                cursor = sQLiteDatabase2.query("cloud", new String[]{"localFlag", "localFile", "serverStatus"}, "_id=?", new String[]{String.valueOf(j)}, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int i = cursor.getInt(0);
                        String string = cursor.getString(1);
                        String string2 = cursor.getString(2);
                        if (i != 8) {
                            if (i != 7) {
                                if (!(i == 2 || i == -1)) {
                                    if (i != 11) {
                                        if (TextUtils.equals("deleted", string2) || TextUtils.equals("purged", string2)) {
                                            HashMap hashMap = new HashMap();
                                            hashMap.put("result", String.valueOf(z));
                                            hashMap.put("detail", "record in invalid server state " + string2);
                                            GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap);
                                        }
                                        IOUtils.closeQuietly(cursor);
                                    }
                                }
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put("result", String.valueOf(z));
                                hashMap2.put("detail", "record in invalid local state " + i);
                                GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap2);
                                IOUtils.closeQuietly(cursor);
                            }
                        }
                        if (!FileUtils.isFileExist(string)) {
                            HashMap hashMap3 = new HashMap();
                            hashMap3.put("result", String.valueOf(z));
                            hashMap3.put("detail", "local file not exist with unsynced item");
                            hashMap3.put("localFile", string);
                            GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap3);
                        } else if (z && !string.contains(StorageUtils.DIRECTORY_SECRET_ALBUM_PATH)) {
                            HashMap hashMap4 = new HashMap();
                            hashMap4.put("result", "true");
                            hashMap4.put("detail", "local file not in secret folder");
                            hashMap4.put("localFile", string);
                            GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap4);
                        }
                        IOUtils.closeQuietly(cursor);
                    }
                }
                HashMap hashMap5 = new HashMap();
                hashMap5.put("result", String.valueOf(z));
                hashMap5.put("detail", "failed to retrieve record again");
                GallerySamplingStatHelper.recordErrorEvent("operation_abnormal", "add_secret_abnormal", hashMap5);
            } catch (Exception e) {
                Log.e("CloudManager", (Throwable) e);
            } catch (Throwable th) {
                IOUtils.closeQuietly(cursor);
                throw th;
            }
            IOUtils.closeQuietly(cursor);
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            boolean z;
            long j;
            long j2;
            long j3;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            int canAddSecret = canAddSecret(this.mContext, this.mCursor);
            if (canAddSecret < 0) {
                return (long) canAddSecret;
            }
            this.mMediaId = this.mCursor.getLong(0);
            int checkConflict = checkConflict(sQLiteDatabase);
            if (checkConflict == -104) {
                deleteAllWithSameSha1(sQLiteDatabase2, mediaManager2, true);
                return this.mMediaId;
            }
            int i = this.mCursor.getInt(2);
            if (i == 7 || i == 8) {
                ContentValues contentValues = new ContentValues();
                contentValues.putAll(CloudManager.copyOf(CloudManager.PUBLIC_COPYABLE_PROJECTION, this.mCursor));
                contentValues.put("localFlag", 8);
                j = addFilePath(contentValues, checkConflict == -105);
                if (j == -1) {
                    contentValues.put("localGroupId", -1000L);
                    j = sQLiteDatabase2.insert("cloud", (String) null, contentValues);
                    if (j > 0) {
                        mediaManager2.insert(j, contentValues);
                        z = false;
                        if (sQLiteDatabase2.delete("cloud", "_id=?", new String[]{String.valueOf(this.mMediaId)}) > 0) {
                            mediaManager2.delete("_id=?", new String[]{String.valueOf(this.mMediaId)});
                        }
                        j2 = 0;
                    }
                }
                z = false;
                j2 = 0;
            } else {
                if (i != 0) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("localGroupId", -1000L);
                    j3 = addFilePath(contentValues2, checkConflict == -105);
                    if (j3 == -1) {
                        if (sQLiteDatabase2.update("cloud", contentValues2, "_id=?", new String[]{String.valueOf(this.mMediaId)}) > 0) {
                            if (mediaManager2 != null) {
                                mediaManager2.update("_id=?", new String[]{String.valueOf(this.mMediaId)}, contentValues2);
                            }
                            j3 = this.mMediaId;
                        }
                    }
                } else {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("localFlag", 11);
                    ContentValues contentValues4 = new ContentValues();
                    contentValues4.put("localFlag", 5);
                    contentValues4.putAll(CloudManager.copyOf(CloudManager.PUBLIC_COPYABLE_PROJECTION, this.mCursor));
                    j3 = addFilePath(contentValues4, checkConflict == -105);
                    if (j3 == -1) {
                        contentValues4.put("fromLocalGroupId", Integer.valueOf(this.mCursor.getInt(3)));
                        contentValues4.put("localGroupId", -1000L);
                        contentValues4.put("localImageId", Long.valueOf(this.mMediaId));
                        j3 = sQLiteDatabase2.insert("cloud", (String) null, contentValues4);
                        if (j3 > 0) {
                            mediaManager2.insert(j3, contentValues4);
                            if (sQLiteDatabase2.update("cloud", contentValues3, "_id=?", new String[]{String.valueOf(this.mMediaId)}) > 0) {
                                mediaManager2.delete("_id=?", new String[]{String.valueOf(this.mMediaId)});
                            }
                        }
                    }
                }
                j = j3;
                j2 = 0;
                z = false;
            }
            if (j > j2) {
                postCheck(sQLiteDatabase2, j, true);
                deleteAllWithSameSha1(sQLiteDatabase2, mediaManager2, z);
            } else {
                postCheck(sQLiteDatabase2, this.mMediaId, z);
            }
            return j;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            if (this.mMediaId <= 0) {
                return null;
            }
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(this.mMediaId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "AddToSecret{%d}", new Object[]{Long.valueOf(this.mMediaId)});
        }
    }

    private static class AddToSecretByUri extends CursorTask {
        private String mTarPath;
        private Uri mUri;

        public AddToSecretByUri(Context context, ArrayList<Long> arrayList, Uri uri) {
            super(context, arrayList);
            this.mUri = uri;
        }

        private static int canAddSecret(Context context, String str) {
            if (SpaceFullHandler.isOwnerSpaceFull()) {
                return -106;
            }
            int canUpload = CloudUtils.canUpload(str);
            if (canUpload == 4) {
                return -108;
            }
            if (canUpload != 0) {
                return -107;
            }
            return new CheckPostProcessing(context, str).run((SQLiteDatabase) null, (MediaManager) null) == -111 ? -111 : 0;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            if (this.mCursor == null || !this.mCursor.moveToFirst()) {
                int canAddSecret = canAddSecret(this.mContext, this.mTarPath);
                if (canAddSecret < 0) {
                    return (long) canAddSecret;
                }
                String safePathInPriorStorage = StorageUtils.getSafePathInPriorStorage(StorageUtils.DIRECTORY_SECRET_ALBUM_PATH);
                String fileName = FileUtils.getFileName(this.mTarPath);
                File file = new File(safePathInPriorStorage, fileName);
                String str = this.mTarPath;
                if (file.exists()) {
                    File file2 = new File(FileUtils.getParentFolderPath(this.mTarPath), DownloadPathHelper.addPostfixToFileName(fileName, String.valueOf(System.currentTimeMillis())));
                    FileUtils.move(new File(this.mTarPath), file2);
                    str = file2.getAbsolutePath();
                }
                if (!ExtraTextUtils.startsWithIgnoreCase(this.mTarPath, safePathInPriorStorage)) {
                    str = CloudUtils.moveImageToFolder(this.mTarPath, safePathInPriorStorage, true);
                }
                long saveToCloudDB = SaveToCloudUtil.saveToCloudDB(this.mContext, new SaveToCloudUtil.SaveParams(new File(str), -1000, 8, true));
                if (saveToCloudDB == -2) {
                    if (MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, this.mTarPath) <= 0) {
                        return -103;
                    }
                    DeleteRecorder.record(new DeleteRecord(35, this.mTarPath, "AddToSecretByUri"));
                    return -103;
                } else if (saveToCloudDB == -1) {
                    return -101;
                } else {
                    if (MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, this.mTarPath) > 0) {
                        DeleteRecorder.record(new DeleteRecord(35, this.mTarPath, "AddToSecretByUri"));
                    }
                    return saveToCloudDB;
                }
            } else {
                long j = this.mCursor.getLong(0);
                if (j <= 0) {
                    return 0;
                }
                long run = new AddToSecret(this.mContext, getDirtyBulk(), j).run(sQLiteDatabase, mediaManager);
                if (run > 0) {
                    if (MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, this.mTarPath) > 0) {
                        DeleteRecorder.record(new DeleteRecord(35, this.mTarPath, "AddToSecretByUri"));
                    }
                }
                return run;
            }
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            Uri uri = this.mUri;
            if (uri == null) {
                return null;
            }
            if ("content".equals(uri.getScheme())) {
                this.mTarPath = (String) GalleryUtils.safeQuery(this.mUri, new String[]{"_data"}, (String) null, (String[]) null, (String) null, new GalleryUtils.QueryHandler<String>() {
                    public String handle(Cursor cursor) {
                        if (cursor == null || !cursor.moveToFirst()) {
                            return null;
                        }
                        return cursor.getString(0);
                    }
                });
            } else if ("file".equals(this.mUri.getScheme())) {
                this.mTarPath = this.mUri.getPath();
            }
            if (!TextUtils.isEmpty(this.mTarPath)) {
                return CloudManager.queryCloudItemByFilePath(this.mContext, sQLiteDatabase, this.mTarPath);
            }
            return null;
        }

        public String toString() {
            return String.format("%s{%s}", new Object[]{"AddToSecretByUri", this.mUri});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            return FileUtils.isFileExist(this.mTarPath) ? -1 : -100;
        }
    }

    private static abstract class BaseCopyOrMoveByUri extends CursorTask {
        protected long mAlbumId;
        protected String mTarPath;
        protected Uri mUri;

        public BaseCopyOrMoveByUri(Context context, ArrayList<Long> arrayList, Uri uri, long j) {
            super(context, arrayList);
            this.mUri = uri;
            this.mAlbumId = j;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            if ("content".equals(this.mUri.getScheme())) {
                this.mTarPath = (String) GalleryUtils.safeQuery(this.mUri, new String[]{"_data"}, (String) null, (String[]) null, (String) null, new GalleryUtils.QueryHandler<String>() {
                    public String handle(Cursor cursor) {
                        if (cursor == null || !cursor.moveToFirst()) {
                            return null;
                        }
                        return cursor.getString(0);
                    }
                });
            } else if ("file".equals(this.mUri.getScheme())) {
                this.mTarPath = this.mUri.getPath();
            }
            if (!TextUtils.isEmpty(this.mTarPath)) {
                return CloudManager.queryCloudItemByFilePath(this.mContext, sQLiteDatabase, this.mTarPath);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            if (TextUtils.isEmpty(this.mTarPath)) {
                return -100;
            }
            return new CheckPostProcessing(this.mContext, this.mTarPath).run(sQLiteDatabase, (MediaManager) null) == -111 ? -111 : -1;
        }
    }

    private static class CheckPostProcessing extends CursorTask {
        private String mFilePath;

        public CheckPostProcessing(Context context, String str) {
            super(context, (ArrayList<Long>) null);
            this.mFilePath = str;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            return -102;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            if (TextUtils.isEmpty(this.mFilePath) || !"DCIM/Camera".equalsIgnoreCase(StorageUtils.getRelativePath(this.mContext, FileUtils.getParentFolderPath(this.mFilePath)))) {
                return null;
            }
            long mediaStoreId = MediaStoreUtils.getMediaStoreId(this.mFilePath);
            if (mediaStoreId <= 0) {
                return null;
            }
            return this.mContext.getContentResolver().query(PhotosOemApi.getQueryProcessingUri(this.mContext, mediaStoreId), (String[]) null, (String) null, (String[]) null, (String) null);
        }

        public String toString() {
            return ".CheckPostProcessing{" + this.mFilePath + "}";
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            return (this.mCursor == null || this.mCursor.getCount() == 0) ? -1 : -111;
        }
    }

    private static class Copy extends Media {
        public Copy(Context context, ArrayList<Long> arrayList, long j, long j2) {
            super(context, arrayList, j, j2);
            Log.d("CloudManager", "Copy albumId %d mediaId %d", Long.valueOf(j2), Long.valueOf(j));
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x0187  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0199 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long j;
            long j2;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            Log.d("CloudManager", "%s execute", (Object) toString());
            ContentValues access$300 = CloudManager.copyOf(CloudManager.PUBLIC_COPYABLE_PROJECTION, this.mCursor);
            int i = this.mCursor.getInt(2);
            if (i == 0 || i == 5 || i == 6 || i == 9) {
                Log.d("CloudManager", "has server id, just insert as copy");
                if (i != 0) {
                    String string = this.mCursor.getString(26);
                    if (TextUtils.isEmpty(string)) {
                        Log.d("CloudManager", "not synced but localImageId is null");
                        return -101;
                    }
                    this.mMediaId = Long.parseLong(string);
                    if (i == 9) {
                        this.mMediaId = ShareMediaManager.convertToMediaId(this.mMediaId);
                    }
                    MiscUtil.closeSilently(this.mCursor);
                    this.mCursor = prepare(sQLiteDatabase);
                    long verify = verify(sQLiteDatabase);
                    if (verify != -1) {
                        return verify;
                    }
                    if (this.mCursor.getInt(2) != 0) {
                        Log.d("CloudManager", "backtrack not synced");
                        return -101;
                    } else if (ShareMediaManager.isOtherShareMediaId(this.mMediaId)) {
                        access$300.put("localFlag", 9);
                    } else {
                        access$300.put("localFlag", 6);
                    }
                } else if (ShareMediaManager.isOtherShareMediaId(this.mMediaId)) {
                    access$300.put("localFlag", 9);
                } else {
                    access$300.put("localFlag", 6);
                }
            } else {
                Log.d("CloudManager", "no server id, just insert as manual create");
                access$300.put("localFlag", -2);
            }
            access$300.putAll(CloudManager.copyOf(CloudManager.PRIVATE_COPYABLE_PROJECTION, this.mCursor));
            if (ShareMediaManager.isOtherShareMediaId(this.mMediaId)) {
                long originalMediaId = ShareMediaManager.getOriginalMediaId(this.mMediaId);
                if (ShareAlbumManager.isOtherShareAlbumId(this.mAlbumId)) {
                    access$300.put("localGroupId", Long.valueOf(ShareAlbumManager.getOriginalAlbumId(this.mAlbumId)));
                    access$300.put("localImageId", Long.valueOf(originalMediaId));
                    j = sQLiteDatabase2.insert("shareImage", (String) null, access$300);
                    Log.d("CloudManager", "Copy other share to other share id %d", (Object) Long.valueOf(j));
                    Log.d("CloudManager", "inserts: %s COPY RESULT: %d", CloudManager.desensitization(access$300), Long.valueOf(j));
                    if (j <= 0) {
                        return -101;
                    }
                    if (ShareAlbumManager.isOtherShareAlbumId(this.mAlbumId)) {
                        j = ShareMediaManager.convertToMediaId(j);
                    }
                    long j3 = j;
                    markAsDirty(j3);
                    return j3;
                }
                access$300.put("localGroupId", Long.valueOf(this.mAlbumId));
                access$300.put("localImageId", Long.valueOf(originalMediaId));
                j2 = sQLiteDatabase2.insert("cloud", (String) null, access$300);
                mediaManager2.insert(j2, access$300);
                Log.d("CloudManager", "Copy other share to owner id %d", (Object) Long.valueOf(j2));
            } else if (ShareAlbumManager.isOtherShareAlbumId(this.mAlbumId)) {
                access$300.put("localGroupId", Long.valueOf(ShareAlbumManager.getOriginalAlbumId(this.mAlbumId)));
                access$300.put("localImageId", Long.valueOf(this.mMediaId));
                j = sQLiteDatabase2.insert("shareImage", (String) null, access$300);
                Log.d("CloudManager", "Copy owner to other share id %d", (Object) Long.valueOf(j));
                Log.d("CloudManager", "inserts: %s COPY RESULT: %d", CloudManager.desensitization(access$300), Long.valueOf(j));
                if (j <= 0) {
                }
            } else {
                access$300.put("localGroupId", Long.valueOf(this.mAlbumId));
                access$300.put("localImageId", Long.valueOf(this.mMediaId));
                j2 = sQLiteDatabase2.insert("cloud", (String) null, access$300);
                mediaManager2.insert(j2, access$300);
                Log.d("CloudManager", "Copy owner to owner id %d", (Object) Long.valueOf(j2));
            }
            j = j2;
            Log.d("CloudManager", "inserts: %s COPY RESULT: %d", CloudManager.desensitization(access$300), Long.valueOf(j));
            if (j <= 0) {
            }
        }

        public String toString() {
            return String.format("Copy{%d, %d}", new Object[]{Long.valueOf(this.mMediaId), Long.valueOf(this.mAlbumId)});
        }
    }

    private static class CopyByUri extends BaseCopyOrMoveByUri {
        public CopyByUri(Context context, ArrayList<Long> arrayList, Uri uri, long j) {
            super(context, arrayList, uri, j);
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long j = (this.mCursor == null || !this.mCursor.moveToFirst()) ? 0 : this.mCursor.getLong(0);
            if (j > 0) {
                return new Copy(this.mContext, getDirtyBulk(), j, this.mAlbumId).run(sQLiteDatabase, mediaManager);
            }
            long saveToCloudDB = SaveToCloudUtil.saveToCloudDB(this.mContext.getApplicationContext(), new SaveToCloudUtil.SaveParams(new File(this.mTarPath), this.mAlbumId, -2, true));
            if (saveToCloudDB <= 0) {
                return saveToCloudDB == -2 ? -103 : -101;
            }
            markAsDirty(saveToCloudDB);
            return saveToCloudDB;
        }

        public String toString() {
            return String.format("CopyByUri{%s, %d}", new Object[]{this.mUri.toString(), Long.valueOf(this.mAlbumId)});
        }
    }

    private static class CreateAlbum extends CursorTask {
        private String mAlbumPath = CloudManager.genRelativePath(this.mName, false);
        private String mName;

        public CreateAlbum(Context context, ArrayList<Long> arrayList, String str) {
            super(context, arrayList);
            this.mName = str;
        }

        private static String regenerateAlbumPath(Context context, String str) {
            String str2;
            int i = 0;
            while (true) {
                String valueOf = String.valueOf(System.currentTimeMillis());
                str2 = str + "_" + valueOf.substring(valueOf.length() - 2);
                if (verifyAlbumPath(context, str2) == -1) {
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

        private static long verifyAlbumPath(Context context, String str) {
            long j;
            if (TextUtils.isEmpty(str)) {
                return -100;
            }
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, "localFile like ?  AND (serverType=0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
                if (cursor == null) {
                    Log.d("CloudManager", "cursor for albumPath(%s) is null, verify failed", (Object) str);
                    j = -101;
                } else if (cursor.moveToFirst()) {
                    long j2 = cursor.getLong(0);
                    MiscUtil.closeSilently(cursor);
                    return j2;
                } else {
                    j = -1;
                }
                return j;
            } finally {
                MiscUtil.closeSilently(cursor);
            }
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("serverType", 0);
            contentValues.put("fileName", this.mName);
            contentValues.put("dateModified", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("dateTaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("localFlag", 8);
            contentValues.put("localFile", this.mAlbumPath);
            contentValues.put("attributes", 1L);
            long insert = sQLiteDatabase.insert("cloud", (String) null, contentValues);
            Log.d("CloudManager", "album(%s) creation finish with a id(%s) and albumPath(%s)", this.mName, Long.valueOf(insert), this.mAlbumPath);
            if (insert <= 0) {
                return -101;
            }
            mediaManager.insertAttributes(insert, contentValues.getAsLong("attributes").longValue());
            markAsDirty(insert);
            return insert;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0039  */
        public long getConflictAlbumId(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = prepare(sQLiteDatabase);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            long j = cursor.getLong(0);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return j;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                long verifyAlbumPath = verifyAlbumPath(this.mContext, this.mAlbumPath);
                if (verifyAlbumPath >= 0) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return verifyAlbumPath;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return -102;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "fileName=? COLLATE NOCASE  AND (serverType=0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{this.mName}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format("CreateAlbum{%s}", new Object[]{this.mName});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            if (verify == -1) {
                Log.d("CloudManager", "album with name(%s) found, exist %d", this.mName, Integer.valueOf(this.mCursor.getCount()));
                return -103;
            }
            if (verify == -102) {
                verify = verifyAlbumPath(this.mContext, this.mAlbumPath);
                if (verify > 0) {
                    this.mAlbumPath = regenerateAlbumPath(this.mContext, this.mAlbumPath);
                    return -1;
                }
            }
            return verify;
        }
    }

    private static abstract class CursorTask {
        protected Context mContext;
        protected Cursor mCursor;
        private ArrayList<Long> mDirtyBulk;

        public CursorTask(Context context, ArrayList<Long> arrayList) {
            this.mContext = context;
            this.mDirtyBulk = arrayList;
        }

        private void release() {
            this.mContext = null;
            Cursor cursor = this.mCursor;
            if (cursor != null) {
                cursor.close();
            }
            this.mCursor = null;
        }

        /* access modifiers changed from: protected */
        public abstract long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager);

        public ArrayList<Long> getDirtyBulk() {
            return this.mDirtyBulk;
        }

        /* access modifiers changed from: protected */
        public final void markAsDirty(long j) {
            this.mDirtyBulk.add(Long.valueOf(j));
        }

        /* access modifiers changed from: protected */
        public abstract Cursor prepare(SQLiteDatabase sQLiteDatabase);

        public final long run(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            Log.d("CloudManager", "%s is running", (Object) toString());
            this.mCursor = prepare(sQLiteDatabase);
            try {
                long verify = verify(sQLiteDatabase);
                if (verify != -1) {
                    return verify;
                }
                long execute = execute(sQLiteDatabase, mediaManager);
                Log.d("CloudManager", "%s finish", (Object) toString());
                release();
                return execute;
            } finally {
                Log.d("CloudManager", "%s finish", (Object) toString());
                release();
            }
        }

        public abstract String toString();

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor = this.mCursor;
            if (cursor == null) {
                Log.d("CloudManager", "cursor for %s is null, abort", (Object) toString());
                return -101;
            } else if (cursor.moveToFirst()) {
                return -1;
            } else {
                Log.d("CloudManager", "cursor for %s has nothing, abort", (Object) toString());
                return -102;
            }
        }
    }

    private static class DeleteAlbum extends CursorTask {
        private long mAlbumId;
        private int mDeleteReason;

        public DeleteAlbum(Context context, ArrayList<Long> arrayList, long j, int i) {
            super(context, arrayList);
            this.mAlbumId = j;
            this.mDeleteReason = i;
        }

        private int deleteItem(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long j, Cursor cursor) {
            int i;
            if (cursor.isNull(4)) {
                Log.d("DeleteAlbum", "DELETE ITEM: no server id found, update to invalid record: %d", (Object) Long.valueOf(j));
                ContentValues contentValues = new ContentValues();
                contentValues.put("localFlag", -1);
                i = sQLiteDatabase.update("cloud", contentValues, "_id = ? ", new String[]{String.valueOf(j)});
            } else {
                Log.d("DeleteAlbum", "DELETE ITEM: server id found, mark delete %d", (Object) Long.valueOf(j));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("localFlag", 2);
                i = sQLiteDatabase.update("cloud", contentValues2, "_id = ? ", new String[]{String.valueOf(j)});
            }
            mediaManager.delete("_id = ? ", new String[]{String.valueOf(j)});
            Log.d("DeleteAlbum", "DELETE ITEM FINISH: deleted %d item", (Object) Integer.valueOf(i));
            if (i > 0) {
                markAsDirty(j);
            }
            return i;
        }

        private int deleteMediaInAlbum(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long j) {
            int i;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Log.d("DeleteAlbum", "deleting media in album(%d)", (Object) Long.valueOf(j));
            ArrayList arrayList = new ArrayList();
            Cursor query = sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "localGroupId=? AND serverId IS NULL", new String[]{String.valueOf(j)}, (String) null, (String) null, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    markAsDirty(query.getLong(0));
                    DeleteRecord createDeleteRecord = CloudManager.createDeleteRecord(this.mDeleteReason, query, "DeleteAlbum");
                    if (createDeleteRecord != null) {
                        arrayList.add(createDeleteRecord);
                    }
                }
                query.close();
                ContentValues contentValues = new ContentValues();
                contentValues.put("localFlag", -1);
                String[] strArr = {String.valueOf(j)};
                Log.d("DeleteAlbum", "marked local media in album(%d)", (Object) Long.valueOf(j));
                i = sQLiteDatabase2.update("cloud", contentValues, "localGroupId=? AND serverId IS NULL", strArr) + 0;
            } else {
                Log.e("DeleteAlbum", "delete local media in album(%d) returns a null cursor", (Object) Long.valueOf(j));
                i = 0;
            }
            Cursor query2 = sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND localGroupId = ? ", new String[]{String.valueOf(j)}, (String) null, (String) null, (String) null);
            if (query2 != null) {
                while (query2.moveToNext()) {
                    markAsDirty(query2.getLong(0));
                    DeleteRecord createDeleteRecord2 = CloudManager.createDeleteRecord(this.mDeleteReason, query2, "DeleteAlbum");
                    if (createDeleteRecord2 != null) {
                        arrayList.add(createDeleteRecord2);
                    }
                }
                query2.close();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("localFlag", 2);
                i += sQLiteDatabase2.update("cloud", contentValues2, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND localGroupId = ? ", new String[]{String.valueOf(j)});
                Log.d("DeleteAlbum", "marked cloud media in album(%d)", (Object) Long.valueOf(j));
            } else {
                Log.e("DeleteAlbum", "delete synced media in album(%d) returns a null cursor", (Object) Long.valueOf(j));
            }
            mediaManager.delete("localGroupId = ? ", new String[]{String.valueOf(j)});
            Log.d("DeleteAlbum", "DELETING items from album(%d), count: %d", Long.valueOf(j), Integer.valueOf(i));
            if (MiscUtil.isValid(arrayList)) {
                DeleteRecorder.record((Collection<DeleteRecord>) arrayList);
            }
            return i;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            Log.d("DeleteAlbum", "DELETING ALBUM:");
            long deleteMediaInAlbum = (long) deleteMediaInAlbum(sQLiteDatabase, mediaManager, this.mAlbumId);
            long deleteItem = (long) deleteItem(this.mContext, sQLiteDatabase, mediaManager, this.mAlbumId, this.mCursor);
            mediaManager.deleteAttributes(this.mAlbumId);
            return deleteItem < 0 ? deleteItem : deleteMediaInAlbum;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id = ?", new String[]{String.valueOf(this.mAlbumId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format("%s{%d}", new Object[]{"DeleteAlbum", Long.valueOf(this.mAlbumId)});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            if (verify != -1) {
                return verify;
            }
            if (this.mCursor.getInt(5) != 0) {
                Log.w("DeleteAlbum", "Image or video can't be deleted here");
                return -100;
            } else if (!CloudManager.isSystemAlbum(this.mCursor.getLong(4))) {
                return -1;
            } else {
                Log.w("DeleteAlbum", "system album can't be deleted");
                return -100;
            }
        }
    }

    private static class DeleteByPath extends CursorTask {
        private ArrayList<Long> mDeleteIds;
        private int mDeleteOptions;
        private int mDeleteReason;
        private String mPath;

        public DeleteByPath(Context context, ArrayList<Long> arrayList, String str, int i, ArrayList<Long> arrayList2, int i2) {
            super(context, arrayList);
            this.mPath = str;
            this.mDeleteOptions = i;
            this.mDeleteIds = arrayList2;
            this.mDeleteReason = i2;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            if (this.mCursor != null && this.mCursor.moveToFirst()) {
                long j = this.mCursor.getLong(0);
                if (j > 0) {
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                    MediaManager mediaManager2 = mediaManager;
                    long[] access$600 = CloudManager.delete(this.mContext, sQLiteDatabase2, mediaManager2, getDirtyBulk(), new long[]{j}, this.mDeleteOptions, this.mDeleteIds, this.mDeleteReason);
                    if (access$600.length > 0) {
                        return access$600[0];
                    }
                    return -101;
                }
            }
            int deleteFileType = MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, this.mPath);
            DeleteRecorder.record(new DeleteRecord(this.mDeleteReason, this.mPath, "DeleteByPath"));
            return (long) deleteFileType;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return CloudManager.queryCloudItemByFilePath(this.mContext, sQLiteDatabase, this.mPath);
        }

        public String toString() {
            return String.format("%s{%s}", new Object[]{"DeleteByPath", this.mPath});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            return TextUtils.isEmpty(this.mPath) ? -100 : -1;
        }
    }

    private static class DeleteBySha1 extends CursorTask {
        private int mDeleteReason;
        private boolean mKeepDup;
        private String mSha1;

        public DeleteBySha1(Context context, ArrayList<Long> arrayList, String str, boolean z, int i) {
            super(context, arrayList);
            this.mSha1 = str;
            this.mKeepDup = z;
            this.mDeleteReason = i;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            int i = 0;
            if (this.mCursor != null && (this.mCursor.getCount() == 1 || (this.mCursor.getCount() > 1 && !this.mKeepDup))) {
                long[] jArr = new long[this.mCursor.getCount()];
                for (int i2 = 0; i2 < this.mCursor.getCount(); i2++) {
                    this.mCursor.moveToPosition(i2);
                    jArr[i2] = this.mCursor.getLong(0);
                }
                i = CloudManager.getValidCount(CloudManager.delete(this.mContext, sQLiteDatabase, mediaManager, getDirtyBulk(), jArr, this.mDeleteReason));
            }
            return (long) i;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            if (TextUtils.isEmpty(this.mSha1)) {
                return null;
            }
            return sQLiteDatabase.query("cloud", new String[]{"_id"}, "sha1=? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{this.mSha1}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format("DeleteBySha1{%s}", new Object[]{this.mSha1});
        }
    }

    private static class DeleteCloudByPath extends CursorTask {
        private ArrayList<Long> mDeleteIds;
        private int mDeleteReason;
        private String mPath;

        public DeleteCloudByPath(Context context, ArrayList<Long> arrayList, String str, ArrayList<Long> arrayList2, int i) {
            super(context, arrayList);
            this.mPath = str;
            this.mDeleteIds = arrayList2;
            this.mDeleteReason = i;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            if (this.mCursor != null && this.mCursor.moveToFirst()) {
                long j = this.mCursor.getLong(0);
                if (j > 0) {
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                    MediaManager mediaManager2 = mediaManager;
                    long[] access$700 = CloudManager.cloudDelete(this.mContext, sQLiteDatabase2, mediaManager2, getDirtyBulk(), new long[]{j}, this.mDeleteIds, this.mDeleteReason);
                    if (access$700.length > 0) {
                        return access$700[0];
                    }
                    return -101;
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return CloudManager.queryCloudItemByFilePath(this.mContext, sQLiteDatabase, this.mPath);
        }

        public String toString() {
            return String.format("DeleteCloudByPath{%s}", new Object[]{this.mPath});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            return TextUtils.isEmpty(this.mPath) ? -100 : -1;
        }
    }

    private static class DeleteFile extends CursorTask {
        private final String TRACE_TAG = toString();
        private int mDeleteReason;
        private long mId;

        public DeleteFile(Context context, ArrayList<Long> arrayList, long j, int i) {
            super(context, arrayList);
            this.mId = j;
            this.mDeleteReason = i;
        }

        private int deleteAlbumFile(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, int i) {
            try {
                String[] strArr = CloudManager.PROJECTION;
                String[] strArr2 = new String[1];
                try {
                    int i2 = 0;
                    strArr2[0] = String.valueOf(this.mId);
                    Cursor query = sQLiteDatabase.query("cloud", strArr, "localGroupId=?", strArr2, (String) null, (String) null, (String) null);
                    if (query != null && query.moveToFirst()) {
                        int i3 = 0;
                        do {
                            i3 += deleteMediaFile(sQLiteDatabase, mediaManager, query, query.getLong(0), i);
                        } while (query.moveToNext());
                        i2 = i3;
                    }
                    MiscUtil.closeSilently(query);
                    return i2;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently((Closeable) null);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                MiscUtil.closeSilently((Closeable) null);
                throw th;
            }
        }

        private int deleteMediaFile(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, Cursor cursor, long j, int i) {
            if (cursor.isNull(2) || cursor.getInt(2) != 15) {
                MediaFileHandleJob.from(this.mContext.getContentResolver(), cursor, j, i).doDelete(this.mContext, false, false);
                ContentValues contentValues = new ContentValues();
                if (cursor.isNull(4)) {
                    contentValues.put("localFlag", -1);
                    mediaManager.delete("_id=?", new String[]{String.valueOf(j)});
                } else {
                    contentValues.put("thumbnailFile", "");
                    contentValues.put("localFile", "");
                    mediaManager.update("_id=?", new String[]{String.valueOf(j)}, contentValues);
                }
                int update = sQLiteDatabase.update("cloud", contentValues, "_id = ? ", new String[]{String.valueOf(j)});
                Log.d("CloudManager", "clear thumb and origin path");
                return update;
            }
            MediaFileHandleJob.from(this.mContext.getContentResolver(), cursor, j, i).doDelete(this.mContext, false, true);
            return mediaManager.delete("_id=?", new String[]{String.valueOf(j)});
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            int i;
            if (this.mCursor.getInt(5) == 0) {
                Log.d("CloudManager", "DELETING ALBUM FILE:");
                i = deleteAlbumFile(sQLiteDatabase, mediaManager, this.mDeleteReason);
                TimingTracing.addSplit(this.TRACE_TAG, String.format("deleteAlbum{%s}", new Object[]{Integer.valueOf(i)}));
            } else {
                Log.d("CloudManager", "DELETING MEDIA FILE");
                i = deleteMediaFile(sQLiteDatabase, mediaManager, this.mCursor, this.mId, this.mDeleteReason);
                TimingTracing.addSplit(this.TRACE_TAG, String.format("deleteFile{%s}", new Object[]{Integer.valueOf(i)}));
            }
            StringBuilder sb = new StringBuilder();
            long stopTracing = TimingTracing.stopTracing(this.TRACE_TAG, new StringBuilderPrinter(sb));
            if (i > 0) {
                long j = (long) i;
                if (stopTracing > 300 * j) {
                    Log.w(toString(), "delete slowly: %s", sb);
                    HashMap hashMap = new HashMap();
                    hashMap.put("cost", String.valueOf(stopTracing / j));
                    hashMap.put("detail", sb.toString());
                    GallerySamplingStatHelper.recordErrorEvent("delete_performance", toString(), hashMap);
                }
            }
            return (long) i;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            TimingTracing.beginTracing(this.TRACE_TAG, String.format("reason{%s}", new Object[]{Integer.valueOf(this.mDeleteReason)}));
            Cursor query = sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id = ?", new String[]{String.valueOf(this.mId)}, (String) null, (String) null, (String) null);
            TimingTracing.addSplit(this.TRACE_TAG, "prepare");
            return query;
        }

        public String toString() {
            return String.format("DeleteFile{%d}", new Object[]{Long.valueOf(this.mId)});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            if (verify != -1) {
                return verify;
            }
            if (!CloudManager.isSystemAlbum(this.mCursor.getLong(4))) {
                return -1;
            }
            Log.w("CloudManager", "system album can't be deleted");
            return -102;
        }
    }

    private static class DupCopy extends DupMedia {
        protected long mAlbumId;

        public DupCopy(Context context, ArrayList<Long> arrayList, long j, long j2, int i) {
            super(context, arrayList, j2, i);
            this.mAlbumId = j;
            Log.d("CloudManager", "DupCopy albumId %d mediaId %d", Long.valueOf(j), Long.valueOf(j2));
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long run;
            do {
                run = new Copy(this.mContext, getDirtyBulk(), this.mCursor.getLong(0), this.mAlbumId).run(sQLiteDatabase, mediaManager);
                if (run > 0 || run == -103 || run == -104 || !this.mCursor.moveToNext()) {
                    Log.d("CloudManager", "DUPLICATED COPY FINISH: id(%d)", (Object) Long.valueOf(run));
                }
            } while (!this.mCursor.moveToNext());
            Log.d("CloudManager", "DUPLICATED COPY FINISH: id(%d)", (Object) Long.valueOf(run));
            return run;
        }

        public String toString() {
            return String.format("DupCopy{%d, %d}", new Object[]{Long.valueOf(this.mMediaId), Long.valueOf(this.mAlbumId)});
        }
    }

    private static class DupDeleteFile extends DupMedia {
        private final String TRACE_TAG = toString();
        private int mDeleteReason;

        public DupDeleteFile(Context context, ArrayList<Long> arrayList, long j, int i, int i2) {
            super(context, arrayList, j, i);
            this.mDeleteReason = i2;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            int i = 0;
            do {
                if (new DeleteFile(this.mContext, getDirtyBulk(), this.mCursor.getLong(0), this.mDeleteReason).run(sQLiteDatabase, mediaManager) > 0) {
                    i++;
                }
            } while (this.mCursor.moveToNext());
            TimingTracing.addSplit(this.TRACE_TAG, String.format("execute{%s}", new Object[]{Integer.valueOf(i)}));
            TimingTracing.stopTracing(this.TRACE_TAG, (Printer) null);
            Log.d("CloudManager", "DUPLICATED DELETE FILE FINISH: %d items", (Object) Integer.valueOf(i));
            return (long) i;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            TimingTracing.beginTracing(this.TRACE_TAG, String.format("reason{%s}", new Object[]{Integer.valueOf(this.mDeleteReason)}));
            Cursor prepare = super.prepare(sQLiteDatabase);
            TimingTracing.addSplit(this.TRACE_TAG, "prepare");
            return prepare;
        }

        public String toString() {
            return String.format("DupDeleteFile{%d}", new Object[]{Long.valueOf(this.mMediaId)});
        }
    }

    private static abstract class DupMedia extends CursorTask {
        private int mDupType;
        protected long mMediaId;

        public DupMedia(Context context, ArrayList<Long> arrayList, long j, int i) {
            super(context, arrayList);
            this.mMediaId = j;
            this.mDupType = i;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x00af A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00b0  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00ce  */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            String str;
            Cursor cursor;
            String format;
            String str2;
            int i = this.mDupType;
            Cursor cursor2 = null;
            if (i != 0) {
                if (i == 1) {
                    try {
                        Cursor query = sQLiteDatabase.query("cloud", new String[]{"localGroupId"}, "_id=" + this.mMediaId, (String[]) null, (String) null, (String) null, (String) null);
                        if (query != null) {
                            try {
                                if (query.moveToFirst()) {
                                    str2 = String.format("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND sha1 = (SELECT sha1 FROM cloud WHERE _id=?) AND sha1 NOT NULL AND localGroupId IN (%s)", new Object[]{Long.valueOf(query.getLong(0))});
                                    if (query != null) {
                                        query.close();
                                    }
                                    str = str2;
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor2 = query;
                                if (cursor2 != null) {
                                }
                                throw th;
                            }
                        }
                        str2 = null;
                        if (query != null) {
                        }
                        str = str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } else if (i != 2) {
                    str = null;
                } else {
                    format = "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND sha1 = (SELECT sha1 FROM cloud WHERE _id=?) AND sha1 NOT NULL";
                }
                if (!TextUtils.isEmpty(str)) {
                    return null;
                }
                return sQLiteDatabase.query("cloud", new String[]{"_id"}, str, new String[]{String.valueOf(this.mMediaId)}, (String) null, (String) null, "serverId DESC ");
            }
            StringBuilder sb = new StringBuilder();
            try {
                cursor = sQLiteDatabase.query("cloud", new String[]{"_id"}, "attributes & 4 != 0", (String[]) null, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            if (!cursor.isFirst()) {
                                sb.append(',');
                            }
                            sb.append(cursor.getLong(0));
                        } catch (Throwable th3) {
                            th = th3;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                format = String.format("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND sha1 = (SELECT sha1 FROM cloud WHERE _id=?) AND sha1 NOT NULL AND localGroupId IN (%s)", new Object[]{sb});
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                if (cursor != null) {
                }
                throw th;
            }
            str = format;
            if (!TextUtils.isEmpty(str)) {
            }
        }
    }

    private static class DupMove extends DupMedia {
        protected long mAlbumId;

        public DupMove(Context context, ArrayList<Long> arrayList, long j, long j2, int i) {
            super(context, arrayList, j2, i);
            this.mAlbumId = j;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long j = -101;
            boolean z = false;
            while (true) {
                long j2 = this.mCursor.getLong(0);
                boolean z2 = true;
                if (!z) {
                    j = new Move(this.mContext, getDirtyBulk(), j2, this.mAlbumId).run(sQLiteDatabase, mediaManager);
                    if (j > 0) {
                        Log.d("CloudManager", "move success, delete items left.");
                    } else if (j == -103) {
                        Log.d("CloudManager", "item exist, skip this, delete items left");
                    } else {
                        if (j == -104) {
                            Log.d("CloudManager", "sha1 conflict, just delete");
                            long[] unused = CloudManager.delete(this.mContext, sQLiteDatabase, mediaManager, getDirtyBulk(), new long[]{j2}, 41);
                        } else if (j == -105) {
                            Log.d("CloudManager", "file name conflict, return");
                            break;
                        } else {
                            Log.w("CloudManager", "unknown err");
                        }
                        z2 = z;
                    }
                    z = z2;
                } else {
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                    MediaManager mediaManager2 = mediaManager;
                    long[] unused2 = CloudManager.delete(this.mContext, sQLiteDatabase, mediaManager, getDirtyBulk(), new long[]{j2}, 42);
                }
                if (!this.mCursor.moveToNext()) {
                    break;
                }
            }
            return j;
        }

        public String toString() {
            return String.format("DupMove{%d, %d}", new Object[]{Long.valueOf(this.mMediaId), Long.valueOf(this.mAlbumId)});
        }
    }

    private static class EditPhotoDateInfoById extends CursorTask {
        protected boolean isFavorites;
        protected String[] mBasicColumns = {"dateTaken", "localFile", "serverId", "localGroupId", "attributes"};
        protected int mBasicConditionStartPosition;
        protected String mBasicQuerySelection;
        protected ArrayList<Long> mDirtyBulk;
        protected String mIdQuerySelection;
        protected long mPhotoNewTime;
        protected long mSourcePhotoId;

        public EditPhotoDateInfoById(Context context, ArrayList<Long> arrayList, long j, long j2, boolean z) {
            super(context, arrayList);
            this.mPhotoNewTime = j2;
            this.mDirtyBulk = arrayList;
            this.isFavorites = z;
            this.mSourcePhotoId = j;
            StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
            sb.append("localFile");
            sb.append(" like '%s%c'");
            this.mBasicConditionStartPosition = sb.length();
            sb.append(" AND ");
            sb.append("localFlag");
            sb.append(" <> ");
            sb.append(15);
            this.mBasicQuerySelection = sb.toString();
            sb.replace(0, this.mBasicConditionStartPosition, "_id = '%s'");
            this.mIdQuerySelection = sb.toString();
            Pools.getStringBuilderPool().release(sb);
        }

        private void logStepEndTime() {
            Log.d("CloudManager", "[Edit FileInfo] step endTime %d", (Object) Long.valueOf(System.currentTimeMillis()));
        }

        private long scannerNewFile(long j, File file, int i) {
            return SaveToCloudUtil.saveToCloudDB(this.mContext, new SaveToCloudUtil.SaveParams.Builder().setAlbumId(j).setSaveFile(file).disableBulkNotify().enableInternalScanRequest().setFileLastModifyTime(this.mPhotoNewTime).setAlbumAttributes(i).setLocalFlag(7).enableForceParserPhotoDateTime().build());
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x0200 A[Catch:{ Exception -> 0x01f6 }] */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x020f A[Catch:{ Exception -> 0x021d }] */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            File file;
            File file2;
            boolean z;
            String[] strArr;
            long j;
            String str;
            long j2;
            boolean z2;
            boolean z3;
            long scannerNewFile;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            String str2 = "_id=?";
            try {
                String string = this.mCursor.getString(this.mCursor.getColumnIndex("localFile"));
                Log.d("CloudManager", "[Edit FileInfo] 1. query source record is success ,sourceFilePath: [%s]", (Object) string);
                logStepEndTime();
                boolean needUseDocumentProvider = DocumentProviderUtils.needUseDocumentProvider(string);
                boolean hasExif = FileMimeUtil.hasExif(string);
                Log.d("CloudManager", "[Edit FileInfo] 2. prepare edit PhotoDateTime , now sourceFilePath: [%s] ,isNeedUseDocumentProvider: [%b] ,isExifSupportModification: [%b]", string, Boolean.valueOf(needUseDocumentProvider), Boolean.valueOf(hasExif));
                logStepEndTime();
                if (hasExif) {
                    try {
                        Pair<Boolean, File> imageFileDataTime = GalleryExifUtils.setImageFileDataTime(this.mContext, string, this.mPhotoNewTime, true);
                        if (imageFileDataTime != null) {
                            z = ((Boolean) imageFileDataTime.first).booleanValue();
                            file2 = (File) imageFileDataTime.second;
                        } else {
                            file2 = null;
                            z = false;
                        }
                    } catch (Exception e) {
                        e = e;
                        str2 = "CloudManager";
                        file2 = null;
                        Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                        file = file2;
                        Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                        FileUtils.deleteFile(file);
                        return -16;
                    }
                } else {
                    file2 = new File(FileUtils.generateFileDateTimeName(new File(string), this.mPhotoNewTime));
                    try {
                        z = FileUtils.copyFile(new File(string), file2);
                        if (!z) {
                            FileUtils.deleteFile(file2);
                            return -4;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        str2 = "CloudManager";
                        Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                        file = file2;
                        Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                        FileUtils.deleteFile(file);
                        return -16;
                    }
                }
                try {
                    Log.d("CloudManager", "[Edit FileInfo] 3. edit Photo exif is [%b], now sourceFilePath: [%s] , newFilePath is: [%s]", Boolean.valueOf(z), string, file2 != null ? file2.getAbsoluteFile() : "edit failed");
                    if (!file2.exists()) {
                        Log.d("CloudManager", "[Edit FileInfo] 3. edit Photo exif is [%b],but newFile not exists", (Object) Boolean.valueOf(z));
                        return -4;
                    }
                    if (!hasExif || (hasExif && !z)) {
                        if (file2.exists()) {
                            if (!file2.setLastModified(this.mPhotoNewTime)) {
                            }
                        }
                        Log.d("CloudManager", "[Edit FileInfo] 3. edit Photo no exif info and edit lastModifytime is Failed,Android Rom version is [%d]", (Object) Integer.valueOf(Build.VERSION.SDK_INT));
                        FileUtils.deleteFile(file2);
                        return -4;
                    }
                    String absolutePath = file2.getAbsolutePath();
                    long j3 = this.mCursor.getLong(this.mCursor.getColumnIndex("serverId"));
                    Log.d("CloudManager", "[Edit FileInfo] 4. prepare edit source local_flag to LOCAL_FLAG_NOT_USE, now sourceFilePath: [%s] , source ServerId [%s]", string, Long.valueOf(j3));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("localFlag", 15);
                    if (((long) sQLiteDatabase2.update("cloud", contentValues, str2, new String[]{String.valueOf(this.mSourcePhotoId)})) <= 0) {
                        Log.e("CloudManager", "[Edit FileInfo]4. Failed edit Source Local_Flag , SourceCloudId Is [%s]", (Object) Long.valueOf(this.mSourcePhotoId));
                        FileUtils.deleteFile(file2);
                        return -16;
                    }
                    Log.d("CloudManager", "[Edit FileInfo] 4. Edit Source LocalFlag Is Success,SourceCloudId Is [%s] , ServerId is [%s]", Long.valueOf(this.mSourcePhotoId), Long.valueOf(j3));
                    logStepEndTime();
                    Log.d("CloudManager", "[Edit FileInfo] 5. prepare delete source File ,file is [%s]", (Object) string);
                    long run = new DeleteFile(this.mContext, new ArrayList(), this.mSourcePhotoId, 24).run(sQLiteDatabase2, mediaManager2);
                    if (run <= 0 || !file2.exists()) {
                        str2 = "CloudManager";
                        file = file2;
                        Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                        FileUtils.deleteFile(file);
                        return -16;
                    }
                    Log.d("CloudManager", "[Edit FileInfo] 5. delete source File is Success");
                    logStepEndTime();
                    Log.d("CloudManager", "[Edit FileInfo] 6. prepare query albumId And attributes");
                    long j4 = this.mCursor.getLong(this.mCursor.getColumnIndex("localGroupId"));
                    int i = this.mCursor.getInt(this.mCursor.getColumnIndex("attributes"));
                    if (j4 == 0) {
                        return -128;
                    }
                    if (j4 == -1) {
                        return -128;
                    }
                    Log.d("CloudManager", "[Edit FileInfo] 6. query albumId And attributes is Success");
                    Log.d("CloudManager", "[Edit FileInfo] 7. prepare scanner newFile [%s]", (Object) absolutePath);
                    long scannerNewFile2 = scannerNewFile(j4, file2, i);
                    if (scannerNewFile2 != -2 || !needUseDocumentProvider) {
                        str2 = "CloudManager";
                        str = absolutePath;
                        strArr = null;
                        file = file2;
                        j = scannerNewFile2;
                    } else {
                        Log.d("CloudManager", "[Edit FileInfo] 7. scanner sdcard File [%s] is exist,now delete record", (Object) absolutePath);
                        String str3 = "localFile = '" + file2 + "'";
                        long j5 = j4;
                        String str4 = "CloudManager";
                        String str5 = absolutePath;
                        strArr = null;
                        file = file2;
                        try {
                            Cursor query = sQLiteDatabase.query("cloud", new String[]{"_id"}, str3, (String[]) null, (String) null, (String) null, (String) null);
                            if (query != null) {
                                try {
                                    if (query.moveToFirst()) {
                                        z3 = false;
                                        long j6 = query.getLong(0);
                                        sQLiteDatabase2.delete("cloud", str3, (String[]) null);
                                        z2 = true;
                                        mediaManager2.delete(str2, new String[]{String.valueOf(j6)});
                                        if (query != null) {
                                            query.close();
                                        }
                                        scannerNewFile = scannerNewFile(j5, file, i);
                                        if (scannerNewFile != -2) {
                                            z3 = z2;
                                        }
                                        str = str5;
                                        str2 = str4;
                                        Log.d(str2, "[Edit FileInfo] 7.again scanner sdcard File [%s],result is [%b]", str, Boolean.valueOf(z3));
                                        j = scannerNewFile;
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    file2 = file;
                                    str2 = str4;
                                    Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                                    file = file2;
                                    Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                                    FileUtils.deleteFile(file);
                                    return -16;
                                }
                            }
                            z3 = false;
                            z2 = true;
                            if (query != null) {
                            }
                            scannerNewFile = scannerNewFile(j5, file, i);
                            if (scannerNewFile != -2) {
                            }
                            str = str5;
                            str2 = str4;
                            try {
                                Log.d(str2, "[Edit FileInfo] 7.again scanner sdcard File [%s],result is [%b]", str, Boolean.valueOf(z3));
                                j = scannerNewFile;
                            } catch (Exception e4) {
                                e = e4;
                                file2 = file;
                                Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                                file = file2;
                                Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                                FileUtils.deleteFile(file);
                                return -16;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            str2 = str4;
                            file2 = file;
                            Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                            file = file2;
                            Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                            FileUtils.deleteFile(file);
                            return -16;
                        }
                    }
                    Log.d(str2, "[Edit FileInfo] 7. Scanner newFile IsSuccess %s ", (Object) str);
                    logStepEndTime();
                    if (j > 0) {
                        if (j3 != 0) {
                            ContentValues contentValues2 = new ContentValues();
                            Log.d(str2, "[Edit FileInfo] 9. Edit newFile LocalImageId ,sourceFile ServerId  Is %s", (Object) Long.valueOf(j3));
                            contentValues2.put("localImageId", Long.valueOf(this.mSourcePhotoId));
                            j2 = (long) sQLiteDatabase2.update("cloud", contentValues2, getIdSelection(j), strArr);
                            if (j2 < 0) {
                                j2 = -32;
                            }
                        } else {
                            j2 = run;
                        }
                        if (this.isFavorites) {
                            long access$800 = CloudManager.addRemoveFavoritesById(GalleryApp.sGetAndroidContext(), sQLiteDatabase, mediaManager, this.mDirtyBulk, j, 1);
                            if (access$800 < 0) {
                                access$800 = -32;
                            }
                            Log.d(str2, "[Edit FileInfo] 10. addRemoveFavoritesByPath is Success,update or delete count %d", (Object) Long.valueOf(j2));
                        }
                        return j2;
                    }
                    Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                    FileUtils.deleteFile(file);
                    return -16;
                } catch (Exception e6) {
                    e = e6;
                    str2 = "CloudManager";
                    File file3 = file2;
                    Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                    file = file2;
                    Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                    FileUtils.deleteFile(file);
                    return -16;
                }
            } catch (Exception e7) {
                e = e7;
                str2 = "CloudManager";
                file2 = null;
                Log.e(str2, "[Edit FileInfo] Failed edit ,Error Message Is %s", (Object) e.getMessage());
                file = file2;
                Log.e(str2, "[Edit FileInfo] No NewFile And now Delete newFile");
                FileUtils.deleteFile(file);
                return -16;
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
            r0 = r3.lastIndexOf(".");
         */
        public String getFileName(String str) {
            int lastIndexOf;
            return (TextUtils.isEmpty(str) || lastIndexOf == -1) ? "" : str.substring(0, lastIndexOf);
        }

        /* access modifiers changed from: protected */
        public String getFilePathSelection(String str) {
            return String.format(this.mBasicQuerySelection, new Object[]{getFileName(str), '%'});
        }

        /* access modifiers changed from: protected */
        public String getIdSelection(long j) {
            return String.format(this.mIdQuerySelection, new Object[]{Long.valueOf(j)});
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", this.mBasicColumns, getIdSelection(this.mSourcePhotoId), (String[]) null, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "updatePhotoDateTime by id: [%s]", new Object[]{Long.valueOf(this.mSourcePhotoId)});
        }
    }

    private static class EditPhotoDateInfoByPath extends EditPhotoDateInfoById {
        protected String mSourceFilePath;

        public EditPhotoDateInfoByPath(Context context, ArrayList<Long> arrayList, long j, String str, boolean z) {
            super(context, arrayList, -1, j, z);
            this.mSourceFilePath = str;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", this.mBasicColumns, getFilePathSelection(this.mSourceFilePath), (String[]) null, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "updatePhotoDateTime by path: [%s]", new Object[]{this.mSourceFilePath});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            return verify == -1 ? this.mCursor.getCount() <= 1 ? -1 : -103 : verify;
        }
    }

    private static abstract class Media extends CursorTask {
        protected long mAlbumId;
        protected long mMediaId;

        public Media(Context context, ArrayList<Long> arrayList, long j, long j2) {
            super(context, arrayList);
            this.mMediaId = j;
            this.mAlbumId = j2;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            Log.d("CloudManager", "%s prepare", (Object) toString());
            if (ShareMediaManager.isOtherShareMediaId(this.mMediaId)) {
                long originalMediaId = ShareMediaManager.getOriginalMediaId(this.mMediaId);
                return sQLiteDatabase.query("shareImage", CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(originalMediaId)}, (String) null, (String) null, (String) null);
            }
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(this.mMediaId)}, (String) null, (String) null, (String) null);
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            Log.d("CloudManager", "%s verify", (Object) toString());
            long verify = super.verify(sQLiteDatabase);
            if (verify != -1) {
                return verify;
            }
            if (this.mCursor.isNull(4)) {
                long run = new CheckPostProcessing(this.mContext, this.mCursor.getString(7)).run(sQLiteDatabase, (MediaManager) null);
                if (run == -111) {
                    return run;
                }
            }
            long run2 = new MediaConflict(this.mContext, this.mCursor.getString(6), this.mAlbumId, this.mMediaId, this.mCursor.getString(9)).run(sQLiteDatabase, (MediaManager) null);
            if (run2 == -102) {
                return -1;
            }
            return run2;
        }
    }

    private static class MediaConflict extends CursorTask {
        private long mAlbumId;
        private String mFileName;
        private long mMediaId;
        private String mSha1;

        public MediaConflict(Context context, String str, long j, long j2, String str2) {
            super(context, (ArrayList<Long>) null);
            this.mFileName = str;
            this.mAlbumId = j;
            this.mMediaId = j2;
            this.mSha1 = str2;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long j = this.mMediaId;
            if (ShareMediaManager.isOtherShareMediaId(j)) {
                j = ShareMediaManager.getOriginalMediaId(j);
            }
            if (this.mCursor.getLong(0) == j) {
                return -103;
            }
            String string = this.mCursor.getString(1);
            return (!TextUtils.equals(string, this.mSha1) || TextUtils.isEmpty(string)) ? -105 : -104;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            if (ShareAlbumManager.isOtherShareAlbumId(this.mAlbumId)) {
                return sQLiteDatabase.query("shareImage", new String[]{"_id", "sha1"}, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND fileName = ? AND localGroupId=?", new String[]{this.mFileName, String.valueOf(ShareAlbumManager.getOriginalAlbumId(this.mAlbumId))}, (String) null, (String) null, (String) null);
            }
            return sQLiteDatabase.query("cloud", new String[]{"_id", "sha1"}, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND fileName = ? AND localGroupId=?", new String[]{this.mFileName, String.valueOf(this.mAlbumId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return ".Conflict{" + this.mFileName + "}";
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            if (verify != -1) {
                return verify;
            }
            if (!TextUtils.isEmpty(this.mFileName)) {
                return -1;
            }
            Log.w("CloudManager", "%s's fileName is empty, so no conflict", toString());
            return -102;
        }
    }

    private static class Move extends Media {
        public Move(Context context, ArrayList<Long> arrayList, long j, long j2) {
            super(context, arrayList, j, j2);
            Log.d("CloudManager", "Move mediaId %d albumId %d", Long.valueOf(j), Long.valueOf(j2));
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            int i;
            long j;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            int i2 = this.mCursor.getInt(2);
            Log.d("CloudManager", "current LOCAL_FLAG is %d", (Object) Integer.valueOf(i2));
            if (i2 != 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("localGroupId", Long.valueOf(this.mAlbumId));
                int update = sQLiteDatabase2.update("cloud", contentValues, "_id=?", new String[]{String.valueOf(this.mMediaId)});
                Log.d("CloudManager", "MOVE FINISH: local item, result(%d)", (Object) Integer.valueOf(update));
                if (update > 0) {
                    mediaManager2.update("_id=?", new String[]{String.valueOf(this.mMediaId)}, contentValues);
                    markAsDirty(this.mMediaId);
                }
                return this.mMediaId;
            }
            ContentValues contentValues2 = new ContentValues();
            if (CloudManager.isSystemAlbum((long) this.mCursor.getInt(1))) {
                Log.d("CloudManager", "system album:");
                contentValues2.put("localFlag", 5);
                contentValues2.put("fromLocalGroupId", Integer.valueOf(this.mCursor.getInt(3)));
                contentValues2.put("localGroupId", Long.valueOf(this.mAlbumId));
                contentValues2.putNull("groupId");
                contentValues2.putNull("localImageId");
                Log.d("CloudManager", "updates: %s", (Object) CloudManager.desensitization(contentValues2));
                int update2 = sQLiteDatabase2.update("cloud", contentValues2, "_id=?", new String[]{String.valueOf(this.mMediaId)});
                Log.d("CloudManager", "MOVE FINISH: system album item, result(%d)", (Object) Integer.valueOf(update2));
                if (update2 > 0) {
                    mediaManager2.update("_id=?", new String[]{String.valueOf(this.mMediaId)}, contentValues2);
                    markAsDirty(this.mMediaId);
                }
                return this.mMediaId;
            }
            Log.d("CloudManager", "cloud album:");
            contentValues2.put("localFlag", 11);
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("localFlag", 5);
            contentValues3.putAll(CloudManager.copyOf(CloudManager.PUBLIC_COPYABLE_PROJECTION, this.mCursor));
            contentValues3.putAll(CloudManager.copyOf(CloudManager.PRIVATE_COPYABLE_PROJECTION, this.mCursor));
            contentValues3.put("fromLocalGroupId", Integer.valueOf(this.mCursor.getInt(3)));
            contentValues3.put("localGroupId", Long.valueOf(this.mAlbumId));
            contentValues3.put("localImageId", Long.valueOf(this.mMediaId));
            Log.d("CloudManager", "inserts: %s", (Object) CloudManager.desensitization(contentValues3));
            long insert = sQLiteDatabase2.insert("cloud", (String) null, contentValues3);
            if (insert > 0) {
                mediaManager2.insert(insert, contentValues3);
                markAsDirty(insert);
                Log.d("CloudManager", "updates: %s", (Object) CloudManager.desensitization(contentValues2));
                i = sQLiteDatabase2.update("cloud", contentValues2, "_id=?", new String[]{String.valueOf(this.mMediaId)});
                if (i > 0) {
                    mediaManager2.delete("_id=?", new String[]{String.valueOf(this.mMediaId)});
                    markAsDirty(this.mMediaId);
                }
                j = insert;
            } else {
                j = -101;
                i = 0;
            }
            Log.d("CloudManager", "MOVE FINISH: cloud album item, results(update:%d; insert:%d)", Integer.valueOf(i), Long.valueOf(j));
            return j;
        }

        public String toString() {
            return String.format("Move{%d, %d}", new Object[]{Long.valueOf(this.mMediaId), Long.valueOf(this.mAlbumId)});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            if (!ShareMediaManager.isOtherShareMediaId(this.mMediaId)) {
                return super.verify(sQLiteDatabase);
            }
            Log.w("CloudManager", "Illegal operate: move share media");
            return -114;
        }
    }

    private static class MoveByUri extends BaseCopyOrMoveByUri {
        public MoveByUri(Context context, ArrayList<Long> arrayList, Uri uri, long j) {
            super(context, arrayList, uri, j);
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            long j = (this.mCursor == null || !this.mCursor.moveToFirst()) ? 0 : this.mCursor.getLong(0);
            if (j > 0) {
                long run = new Move(this.mContext, getDirtyBulk(), j, this.mAlbumId).run(sQLiteDatabase, mediaManager);
                if (run > 0) {
                    FileHandleService.dispatchTask(this.mContext, getDirtyBulk());
                    getDirtyBulk().clear();
                }
                return run;
            }
            long saveToCloudDB = SaveToCloudUtil.saveToCloudDB(this.mContext.getApplicationContext(), new SaveToCloudUtil.SaveParams(new File(this.mTarPath), this.mAlbumId, 8, true));
            if (saveToCloudDB > 0) {
                markAsDirty(saveToCloudDB);
                FileHandleService.dispatchTask(this.mContext, getDirtyBulk());
                getDirtyBulk().clear();
                return saveToCloudDB;
            } else if (saveToCloudDB == -2) {
                return -103;
            } else {
                if (saveToCloudDB == -1) {
                }
                return -101;
            }
        }

        public String toString() {
            return String.format("MoveByUri{%s, %d}", new Object[]{this.mUri.toString(), Long.valueOf(this.mAlbumId)});
        }
    }

    private static class RemoveFromSecret extends CursorTask {
        private long mAlbumId;
        private long mMediaId;

        public RemoveFromSecret(Context context, ArrayList<Long> arrayList, long j, long j2) {
            super(context, arrayList);
            this.mMediaId = j;
            this.mAlbumId = j2;
        }

        private void addFilePath(ContentValues contentValues, boolean z) {
            contentValues.put("localFile", this.mCursor.getString(7));
            contentValues.put("thumbnailFile", this.mCursor.getString(8));
            contentValues.put("microthumbfile", this.mCursor.getString(12));
            String addPostfixToFileName = z ? DownloadPathHelper.addPostfixToFileName(this.mCursor.getString(6), String.valueOf(System.currentTimeMillis())) : this.mCursor.getString(6);
            contentValues.put("fileName", addPostfixToFileName);
            byte[] blob = this.mCursor.getBlob(11);
            if (blob != null) {
                DBCloud dBCloud = new DBCloud(this.mCursor.getString(4));
                dBCloud.setFileName(addPostfixToFileName);
                dBCloud.setSecretKey(blob);
                dBCloud.setSha1(this.mCursor.getString(9));
                dBCloud.setLocalFile(this.mCursor.getString(7));
                dBCloud.setThumbnailFile(this.mCursor.getString(8));
                dBCloud.setMicroThumbFile(this.mCursor.getString(12));
                dBCloud.setServerType(this.mCursor.getInt(5));
                dBCloud.setTitle(this.mCursor.getString(18));
                CloudUtils.SecretAlbumUtils.decryptFiles(dBCloud, contentValues);
                return;
            }
            DBCloud dBCloud2 = new DBCloud(this.mCursor.getString(4));
            dBCloud2.setFileName(addPostfixToFileName);
            dBCloud2.setLocalFile(this.mCursor.getString(7));
            dBCloud2.setThumbnailFile(this.mCursor.getString(8));
            dBCloud2.setServerType(this.mCursor.getInt(5));
            dBCloud2.setTitle(this.mCursor.getString(18));
            CloudUtils.SecretAlbumUtils.decodeFileNames(dBCloud2, contentValues);
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            if (SpaceFullHandler.isOwnerSpaceFull()) {
                return -106;
            }
            long run = new MediaConflict(this.mContext, this.mCursor.getString(6), this.mAlbumId, this.mMediaId, this.mCursor.getString(9)).run(sQLiteDatabase2, (MediaManager) null);
            if (run == -104) {
                long[] unused = CloudManager.delete(this.mContext, sQLiteDatabase, mediaManager, getDirtyBulk(), new long[]{this.mMediaId}, 37);
                return -103;
            }
            int i = this.mCursor.getInt(2);
            if (i != 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("localGroupId", Long.valueOf(this.mAlbumId));
                contentValues.putNull("groupId");
                if (this.mCursor.isNull(4) && i == 7) {
                    contentValues.put("localFlag", 8);
                }
                addFilePath(contentValues, run == -105);
                if (sQLiteDatabase2.update("cloud", contentValues, "_id=?", new String[]{String.valueOf(this.mMediaId)}) <= 0) {
                    return -101;
                }
                if (mediaManager2 != null) {
                    mediaManager2.update("_id=?", new String[]{String.valueOf(this.mMediaId)}, contentValues);
                }
                long j = this.mMediaId;
                markAsDirty(j);
                return j;
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("localFlag", 11);
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("localFlag", 5);
            contentValues3.putAll(CloudManager.copyOf(CloudManager.PUBLIC_COPYABLE_PROJECTION, this.mCursor));
            addFilePath(contentValues3, run == -105);
            contentValues3.put("fromLocalGroupId", Integer.valueOf(this.mCursor.getInt(3)));
            contentValues3.put("localGroupId", Long.valueOf(this.mAlbumId));
            contentValues3.put("localImageId", Long.valueOf(this.mMediaId));
            long insert = sQLiteDatabase2.insert("cloud", (String) null, contentValues3);
            if (insert > 0) {
                mediaManager2.insert(insert, contentValues3);
                if (sQLiteDatabase2.update("cloud", contentValues2, "_id=?", new String[]{String.valueOf(this.mMediaId)}) > 0) {
                    mediaManager2.delete("_id=?", new String[]{String.valueOf(this.mMediaId)});
                }
                markAsDirty(insert);
            }
            return insert;
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?", new String[]{String.valueOf(this.mMediaId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "RemoveFromSecret{%d}", new Object[]{Long.valueOf(this.mMediaId)});
        }
    }

    private static class Rename extends CursorTask {
        private long mId;
        private String mNewName;

        public Rename(Context context, ArrayList<Long> arrayList, long j, String str) {
            super(context, arrayList);
            this.mId = j;
            this.mNewName = str;
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            String str;
            String str2;
            String str3 = null;
            if (!TextUtils.isEmpty(this.mCursor.getString(7))) {
                str = this.mCursor.getString(7);
                str2 = "localFile";
            } else if (!TextUtils.isEmpty(this.mCursor.getString(8))) {
                str = this.mCursor.getString(8);
                str2 = "thumbnailFile";
            } else {
                str = null;
                str2 = null;
            }
            if (FileUtils.isFileExist(str)) {
                str3 = FileUtils.concat(FileUtils.getParentFolderPath(str), this.mNewName);
                if (new File(str3).exists()) {
                    str3 = FileUtils.concat(FileUtils.getParentFolderPath(str), String.format("%s_%s.%s", new Object[]{FileUtils.getFileNameWithoutExtension(this.mNewName), Long.valueOf(System.currentTimeMillis()), FileUtils.getExtension(this.mNewName)}));
                }
            }
            if (!FileUtils.move(new File(str), new File(str3))) {
                return -113;
            }
            MediaStoreUtils.update(MediaStoreUtils.getFileMediaUri(str), str3);
            String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(7);
            GalleryUtils.safeExec(String.format("update %s set %s=coalesce(replace(%s, '%s', '') || '%s', '%s') where %s=%s", new Object[]{"cloud", "editedColumns", "editedColumns", transformToEditedColumnsElement, transformToEditedColumnsElement, transformToEditedColumnsElement, "_id", Long.valueOf(this.mId)}));
            ContentValues contentValues = new ContentValues();
            if (str3 != null) {
                contentValues.put(str2, str3);
            }
            contentValues.put("title", FileUtils.getFileTitle(this.mNewName));
            contentValues.put("fileName", this.mNewName);
            return (long) SafeDBUtil.safeUpdate(this.mContext, GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=?", new String[]{String.valueOf(this.mId)});
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?  AND (serverType!=0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{String.valueOf(this.mId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "Rename{id: %d}", new Object[]{Long.valueOf(this.mId)});
        }
    }

    private static class RenameAlbum extends CursorTask {
        private long mAlbumId;
        private String mNewName;

        public RenameAlbum(Context context, ArrayList<Long> arrayList, long j, String str) {
            super(context, arrayList);
            this.mAlbumId = j;
            this.mNewName = str;
        }

        private Cursor getConflictAlbumCursor(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "fileName=? COLLATE NOCASE  AND (serverType=0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{this.mNewName}, (String) null, (String) null, (String) null);
        }

        /* access modifiers changed from: protected */
        public long execute(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("fileName", this.mNewName);
            if (this.mCursor.getInt(2) == 0) {
                contentValues.put("localFlag", 10);
            }
            int update = sQLiteDatabase.update("cloud", contentValues, "_id=?", new String[]{String.valueOf(this.mAlbumId)});
            Log.d("CloudManager", "Album(id: %d) rename finished.", (Object) Long.valueOf(this.mAlbumId));
            if (update > 0) {
                return this.mAlbumId;
            }
            return -101;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
        public long getConflictAlbumId(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = getConflictAlbumCursor(sQLiteDatabase);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            long j = cursor.getLong(0);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return j;
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
                return -102;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public Cursor prepare(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.query("cloud", CloudManager.PROJECTION, "_id=?  AND (serverType=0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{String.valueOf(this.mAlbumId)}, (String) null, (String) null, (String) null);
        }

        public String toString() {
            return String.format(Locale.US, "RenameAlbum{id: %d}", new Object[]{Long.valueOf(this.mAlbumId)});
        }

        /* access modifiers changed from: protected */
        public long verify(SQLiteDatabase sQLiteDatabase) {
            long verify = super.verify(sQLiteDatabase);
            if (verify != -1) {
                return verify;
            }
            Cursor cursor = null;
            try {
                cursor = getConflictAlbumCursor(sQLiteDatabase);
                if (cursor == null) {
                    Log.d("CloudManager", "cursor is null, verify failed.");
                    if (cursor != null) {
                        MiscUtil.closeSilently(cursor);
                    }
                    return -101;
                } else if (cursor.moveToFirst()) {
                    Log.d("CloudManager", "Album name already exists.");
                    if (cursor != null) {
                        MiscUtil.closeSilently(cursor);
                    }
                    return -103;
                } else {
                    if (cursor != null) {
                        MiscUtil.closeSilently(cursor);
                    }
                    return -1;
                }
            } catch (Exception e) {
                Log.e("CloudManager", (Throwable) e);
                if (cursor != null) {
                    MiscUtil.closeSilently(cursor);
                }
                return -101;
            } catch (Throwable th) {
                if (cursor != null) {
                    MiscUtil.closeSilently(cursor);
                }
                throw th;
            }
        }
    }

    static {
        METHODS.add("create_album");
        METHODS.add("add_to_album");
        METHODS.add("delete");
        METHODS.add("set_album_attributes");
        METHODS.add("force_top");
        METHODS.add("unforce_top");
        METHODS.add("add_remove_secret");
        METHODS.add("rename_album");
        METHODS.add("delete_album");
        METHODS.add("add_remove_favorite");
        METHODS.add("update_photo_datetime");
        METHODS.add("rename");
    }

    /* access modifiers changed from: private */
    public static long addRemoveFavoritesById(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, int i) {
        try {
            return new AddRemoveFavoritesById(context, arrayList, i, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "Add or remove favorites by id with error: %s", (Object) Long.valueOf(j));
            return -100;
        }
    }

    private static long addRemoveFavoritesByPath(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String str, int i) {
        try {
            return new AddRemoveFavoritesByPath(context, arrayList, i, str).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "Add or remove favorites by path with error: %s", (Object) str);
            return -100;
        }
    }

    private static long addRemoveFavoritesSha1(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String str, int i) {
        try {
            return new AddRemoveFavoritesBySha1(context, arrayList, i, str).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "Add or remove favorites by sha1 with error: %s", (Object) str);
            return -100;
        }
    }

    private static long addToSecret(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j) {
        try {
            return new AddToSecret(context, arrayList, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception e) {
            Log.e("CloudManager", "add to secret error %d, %s", Long.valueOf(j), e);
            return -100;
        }
    }

    private static long addToSecret(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, Uri uri) {
        try {
            return new AddToSecretByUri(context, arrayList, uri).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "add to secret error %s", (Object) uri);
            return -100;
        }
    }

    /* JADX WARNING: type inference failed for: r5v15, types: [int] */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bd, code lost:
        if (r8 == true) goto L_0x00c1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x04fa  */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static Bundle call(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, String str, String str2, Bundle bundle) {
        Bundle bundle2;
        Context context2;
        boolean z;
        boolean z2;
        boolean z3;
        Context context3;
        long[] jArr;
        long j;
        boolean z4;
        Context context4;
        int i;
        int i2;
        boolean z5;
        boolean z6;
        long[] jArr2;
        long[] jArr3;
        boolean z7;
        boolean z8;
        Context context5 = context;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        String str3 = str;
        String str4 = str2;
        Bundle bundle3 = bundle;
        Bundle bundle4 = new Bundle();
        ArrayList arrayList = new ArrayList();
        boolean z9 = true;
        if ("add_to_album".equals(str3)) {
            long longValue = Numbers.parse(str4, -1L).longValue();
            boolean z10 = false;
            int i3 = bundle3.getInt("extra_src_type", 0);
            ? r5 = bundle3.getInt("extra_type", 0);
            if (i3 == 1) {
                ArrayList parcelableArrayList = bundle3.getParcelableArrayList("extra_src_uris");
                if (parcelableArrayList == null || isVirtualAlbum(longValue)) {
                    Log.e("CloudManager", "error, albumId is %s, uris is %s", str4, parcelableArrayList);
                    return bundle4;
                }
                long[] jArr4 = new long[parcelableArrayList.size()];
                Iterator it = parcelableArrayList.iterator();
                int i4 = 0;
                boolean z11 = r5;
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    if (!z11) {
                        z7 = z10;
                        z8 = z11;
                        jArr4[i4] = copyByUri(context, sQLiteDatabase, mediaManager, arrayList, longValue, uri);
                    } else {
                        z7 = z10;
                        boolean z12 = z9;
                        z8 = z11;
                        if (z8 == z12) {
                            jArr4[i4] = moveByUri(context, sQLiteDatabase, mediaManager, arrayList, longValue, uri);
                        }
                    }
                    i4++;
                    z11 = z8;
                    z10 = z7;
                    z9 = true;
                }
                boolean z13 = z10;
                bundle4.putLongArray("ids", jArr4);
            } else {
                boolean z14 = r5;
                if (i3 == 0) {
                    int i5 = bundle3.getInt("extra_dup_type", -1);
                    long[] longArray = bundle3.getLongArray("extra_src_media_ids");
                    if (longArray != null) {
                        boolean z15 = z14 ? true : true;
                        if (!isVirtualAlbum(longValue)) {
                            long[] jArr5 = new long[longArray.length];
                            int length = longArray.length;
                            int i6 = 0;
                            int i7 = 0;
                            while (i6 < length) {
                                long j2 = longArray[i6];
                                if (!z14) {
                                    i2 = i6;
                                    i = length;
                                    jArr2 = jArr5;
                                    jArr3 = longArray;
                                    z6 = z14;
                                    z5 = z15;
                                    jArr2[i7] = copy(context, sQLiteDatabase, mediaManager, arrayList, longValue, j2, i5);
                                } else {
                                    i2 = i6;
                                    z5 = z15;
                                    i = length;
                                    jArr2 = jArr5;
                                    jArr3 = longArray;
                                    z6 = z14;
                                    if (z6 == z5) {
                                        jArr2[i7] = move(context, sQLiteDatabase, mediaManager, arrayList, longValue, j2, i5);
                                    }
                                }
                                i7++;
                                i6 = i2 + 1;
                                longArray = jArr3;
                                jArr5 = jArr2;
                                z14 = z6;
                                z15 = z5;
                                length = i;
                                Context context6 = context;
                                SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
                                MediaManager mediaManager3 = mediaManager;
                                Bundle bundle5 = bundle;
                            }
                            z3 = z15;
                            bundle4.putLongArray("ids", jArr5);
                            context2 = context;
                            bundle2 = bundle;
                        }
                    }
                    Log.e("CloudManager", "error, albumId is %s, mediaIds is %s", str4, longArray);
                    return bundle4;
                }
            }
            z3 = true;
            context2 = context;
            bundle2 = bundle;
        } else {
            z3 = true;
            if ("delete_album".equals(str3)) {
                ArrayList arrayList2 = new ArrayList();
                bundle2 = bundle;
                deleteAlbum(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getLongArray("extra_album_ids"), bundle2.getInt("extra_delete_options", 0), arrayList2, bundle2.getInt("extra_delete_reason", 21));
                bundle4.putLongArray("ids", MiscUtil.ListToArray(arrayList2));
            } else {
                bundle2 = bundle;
                long[] jArr6 = null;
                if ("delete".equals(str3)) {
                    ArrayList arrayList3 = new ArrayList();
                    int i8 = bundle2.getInt("delete_by");
                    int i9 = bundle2.getInt("extra_delete_options", 0);
                    int i10 = bundle2.getInt("extra_delete_reason", 24);
                    if (i8 == 0) {
                        jArr6 = deleteById(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getLongArray("extra_ids"), bundle2.getInt("extra_dup_type", -1), i9, arrayList3, i10);
                        bundle4.putLongArray("ids", MiscUtil.ListToArray(arrayList3));
                    } else if (i8 == 1) {
                        jArr6 = deleteByPath(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getStringArray("extra_paths"), i9, arrayList3, i10);
                        bundle4.putLongArray("ids", MiscUtil.ListToArray(arrayList3));
                    } else if (i8 == 2) {
                        jArr6 = deleteBySha1(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getStringArray("extra_sha1s"), bundle2.getBoolean("extra_keep_dup"), i10);
                    } else if (i8 == 3) {
                        jArr6 = deleteCloudByPath(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getStringArray("extra_paths"), arrayList3, i10);
                        bundle4.putLongArray("ids", MiscUtil.ListToArray(arrayList3));
                    }
                    bundle4.putLong("count", (long) getValidCount(jArr6));
                } else {
                    if ("create_album".equals(str3)) {
                        Context context7 = context;
                        Bundle createAlbum = createAlbum(context7, sQLiteDatabase, mediaManager, arrayList, str4);
                        if (createAlbum != null) {
                            bundle4.putAll(createAlbum);
                        } else {
                            bundle4.putLong("id", -101);
                        }
                        context2 = context7;
                    } else {
                        Context context8 = context;
                        SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase;
                        MediaManager mediaManager4 = mediaManager;
                        if ("rename_album".equals(str3)) {
                            Context context9 = context8;
                            String str5 = "id";
                            Bundle renameAlbum = renameAlbum(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getLong("album_id"), str2);
                            if (renameAlbum != null) {
                                bundle4.putAll(renameAlbum);
                                if (renameAlbum.getLong(str5) > 0) {
                                    bundle4.putBoolean("should_request_sync", true);
                                }
                            } else {
                                bundle4.putLong(str5, -101);
                            }
                            context2 = context9;
                        } else {
                            Context context10 = context8;
                            if ("set_album_attributes".equals(str3)) {
                                long[] longArray2 = bundle2.getLongArray("album_id");
                                long j3 = bundle2.getLong("attributes_bit");
                                context2 = context10;
                                setAlbumAttributes(sQLiteDatabase, mediaManager, longArray2, j3, bundle2.getBoolean("set"), bundle2.getBoolean("manual"));
                                int length2 = longArray2.length;
                                int i11 = 0;
                                while (true) {
                                    if (i11 >= length2) {
                                        break;
                                    } else if (!ShareAlbumManager.isOtherShareAlbumId(longArray2[i11])) {
                                        List<Long> albumSyncAttributes = AlbumManager.getAlbumSyncAttributes();
                                        if (MiscUtil.isValid(albumSyncAttributes) && albumSyncAttributes.contains(Long.valueOf(j3))) {
                                            if (j3 == 1) {
                                                bundle4.putBoolean("should_request_sync", bundle2.getBoolean("set"));
                                            } else {
                                                bundle4.putBoolean("should_request_sync", true);
                                            }
                                        }
                                    } else {
                                        i11++;
                                    }
                                }
                            } else {
                                if ("force_top".equals(str3)) {
                                    forceTop(context10, sQLiteDatabase4, mediaManager4, bundle2.getLongArray("album_id"));
                                } else if ("unforce_top".equals(str3)) {
                                    unforceTop(context10, sQLiteDatabase4, mediaManager4, bundle2.getLongArray("album_id"));
                                } else if ("add_remove_secret".equals(str3)) {
                                    int i12 = bundle2.getInt("operation_type");
                                    if (i12 == 1) {
                                        long[] longArray3 = bundle2.getLongArray("extra_src_media_ids");
                                        if (longArray3 == null) {
                                            ArrayList parcelableArrayList2 = bundle2.getParcelableArrayList("extra_src_uris");
                                            if (parcelableArrayList2 != null) {
                                                long[] jArr7 = new long[parcelableArrayList2.size()];
                                                for (int i13 = 0; i13 < parcelableArrayList2.size(); i13++) {
                                                    jArr7[i13] = addToSecret(context10, sQLiteDatabase4, mediaManager4, (ArrayList<Long>) arrayList, (Uri) parcelableArrayList2.get(i13));
                                                }
                                                bundle4.putLongArray("ids", jArr7);
                                                longArray3 = jArr7;
                                            }
                                            context4 = context10;
                                        } else {
                                            int i14 = 0;
                                            while (i14 < longArray3.length) {
                                                longArray3[i14] = addToSecret(context, sQLiteDatabase, mediaManager, (ArrayList<Long>) arrayList, longArray3[i14]);
                                                i14++;
                                                context10 = context10;
                                            }
                                            context4 = context10;
                                            bundle4.putLongArray("ids", longArray3);
                                        }
                                        int length3 = longArray3.length;
                                        int i15 = 0;
                                        while (true) {
                                            if (i15 >= length3) {
                                                z4 = false;
                                                break;
                                            } else if (longArray3[i15] > 0) {
                                                z4 = true;
                                                break;
                                            } else {
                                                i15++;
                                            }
                                        }
                                        bundle4.putBoolean("should_request_sync", z4);
                                    } else {
                                        context2 = context10;
                                        if (i12 == 2) {
                                            long longValue2 = Numbers.parse(str4, -1L).longValue();
                                            long[] longArray4 = bundle2.getLongArray("extra_src_media_ids");
                                            if (longArray4 != null) {
                                                int i16 = 0;
                                                while (i16 < longArray4.length) {
                                                    int i17 = i16;
                                                    longArray4[i17] = removeFromSecret(context, sQLiteDatabase, mediaManager, arrayList, longValue2, longArray4[i16]);
                                                    i16 = i17 + 1;
                                                }
                                                bundle4.putLongArray("ids", longArray4);
                                            }
                                        }
                                    }
                                } else {
                                    context2 = context10;
                                    if ("add_remove_favorite".equals(str3)) {
                                        int i18 = bundle2.getInt("add_remove_by");
                                        int i19 = bundle2.getInt("operation_type");
                                        if (i18 != 1) {
                                            if (i18 == 2) {
                                                String[] stringArray = bundle2.getStringArray("extra_src_sha1");
                                                if (stringArray != null) {
                                                    long[] jArr8 = new long[stringArray.length];
                                                    int i20 = 0;
                                                    while (i20 < stringArray.length) {
                                                        int i21 = i20;
                                                        jArr8[i21] = addRemoveFavoritesSha1(context, sQLiteDatabase, mediaManager, arrayList, stringArray[i20], i19);
                                                        i20 = i21 + 1;
                                                    }
                                                    jArr6 = jArr8;
                                                }
                                            } else if (i18 == 3) {
                                                String[] stringArray2 = bundle2.getStringArray("extra_src_paths");
                                                if (stringArray2 != null) {
                                                    long[] jArr9 = new long[stringArray2.length];
                                                    int i22 = 0;
                                                    while (i22 < stringArray2.length) {
                                                        int i23 = i22;
                                                        long[] jArr10 = jArr9;
                                                        jArr10[i23] = addRemoveFavoritesByPath(context, sQLiteDatabase, mediaManager, arrayList, stringArray2[i22], i19);
                                                        i22 = i23 + 1;
                                                        jArr9 = jArr10;
                                                    }
                                                    jArr6 = jArr9;
                                                }
                                            }
                                            bundle4.putLongArray("ids", jArr6);
                                        }
                                        long[] longArray5 = bundle2.getLongArray("extra_src_media_ids");
                                        if (longArray5 != null) {
                                            long[] jArr11 = new long[longArray5.length];
                                            int i24 = 0;
                                            while (i24 < longArray5.length) {
                                                int i25 = i24;
                                                long[] jArr12 = jArr11;
                                                jArr12[i25] = addRemoveFavoritesById(context, sQLiteDatabase, mediaManager, arrayList, longArray5[i24], i19);
                                                i24 = i25 + 1;
                                                jArr11 = jArr12;
                                            }
                                            jArr6 = jArr11;
                                        }
                                        bundle4.putLongArray("ids", jArr6);
                                    } else {
                                        if ("update_photo_datetime".equals(str3)) {
                                            long j4 = bundle2.getLong("newtime");
                                            int i26 = bundle2.getInt("update_photo_by");
                                            long[] jArr13 = new long[1];
                                            if (i26 == 1) {
                                                jArr = jArr13;
                                                context3 = context2;
                                                j = editPhotoDateInfoById(context, sQLiteDatabase, mediaManager, arrayList, bundle2.getLong("photo_id"), j4, bundle2.getBoolean("is_favorites"));
                                            } else if (i26 != 2) {
                                                jArr = jArr13;
                                                context3 = context2;
                                                j = -1;
                                            } else {
                                                jArr = jArr13;
                                                j = editPhotoDateInfoByPath(context, sQLiteDatabase, mediaManager, arrayList, j4, bundle2.getString("photo_path"), bundle2.getBoolean("is_favorites"));
                                                context3 = context2;
                                            }
                                            if (j > 0) {
                                                jArr[0] = j;
                                                z = true;
                                                bundle4.putBoolean("should_request_sync", true);
                                                bundle4.putLongArray("ids", jArr);
                                            } else {
                                                z = true;
                                            }
                                            context2 = context3;
                                        } else {
                                            z = true;
                                            if ("rename".equals(str3)) {
                                                long j5 = bundle2.getLong("src_cloud_id");
                                                String str6 = "id";
                                                if (rename(context, sQLiteDatabase, mediaManager, arrayList, j5, str2) > 0) {
                                                    bundle4.putLong(str6, j5);
                                                } else {
                                                    bundle4.putLong(str6, -101);
                                                }
                                            }
                                        }
                                        if (!arrayList.isEmpty()) {
                                            if (bundle2 != null) {
                                                z2 = false;
                                                if (bundle2.getBoolean("should_operate_sync", false)) {
                                                    z2 = z;
                                                }
                                            } else {
                                                z2 = false;
                                            }
                                            startUpdater(context2, z2, arrayList);
                                        }
                                        return bundle4;
                                    }
                                }
                                context2 = context10;
                            }
                        }
                    }
                    z = true;
                    if (!arrayList.isEmpty()) {
                    }
                    return bundle4;
                }
            }
            context2 = context;
        }
        z = z3;
        if (!arrayList.isEmpty()) {
        }
        return bundle4;
    }

    public static boolean canHandle(String str) {
        return METHODS.contains(str);
    }

    /* access modifiers changed from: private */
    public static long[] cloudDelete(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, ArrayList<Long> arrayList2, int i) {
        long[] run = new DeleteOwner(context, arrayList, jArr, true, i).run(sQLiteDatabase, mediaManager);
        if (arrayList2 != null) {
            arrayList2.addAll(arrayList);
        }
        return run;
    }

    private static long copy(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, long j2, int i) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        try {
            Numbers.ensurePositive(j, j2);
            return i != -1 ? new DupCopy(context, arrayList, j, j2, i).run(sQLiteDatabase, mediaManager) : new Copy(context, arrayList, j2, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return -100;
        }
    }

    private static long copyByUri(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, Uri uri) {
        try {
            return new CopyByUri(context, arrayList, uri, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            return -100;
        }
    }

    /* access modifiers changed from: private */
    public static ContentValues copyOf(String[] strArr, Cursor cursor) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(strArr));
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            if (hashSet.contains(columnName)) {
                if ("babyInfoJson".equals(columnName)) {
                    Log.d("CloudManager", "catch column(%s), remove local_flag ", (Object) columnName);
                    try {
                        String string = cursor.getString(i);
                        if (!TextUtils.isEmpty(string)) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("localFlag")) {
                                jSONObject.remove("localFlag");
                            }
                            contentValues.put("babyInfoJson", jSONObject.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    int type = cursor.getType(i);
                    if (type == 0) {
                        contentValues.putNull(columnName);
                    } else if (type == 1) {
                        contentValues.put(columnName, Long.valueOf(cursor.getLong(i)));
                    } else if (type == 2) {
                        contentValues.put(columnName, Double.valueOf(cursor.getDouble(i)));
                    } else if (type == 3) {
                        contentValues.put(columnName, cursor.getString(i));
                    } else if (type == 4) {
                        contentValues.put(columnName, cursor.getBlob(i));
                    }
                }
            }
        }
        return contentValues;
    }

    private static Bundle createAlbum(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String str) {
        long j;
        Bundle bundle = new Bundle(2);
        if (TextUtils.isEmpty(str)) {
            j = -100;
        } else {
            CreateAlbum createAlbum = new CreateAlbum(context, arrayList, str);
            long run = createAlbum.run(sQLiteDatabase, mediaManager);
            if (run == -103 || run == -105) {
                long conflictAlbumId = createAlbum.getConflictAlbumId(sQLiteDatabase);
                if (conflictAlbumId >= 0) {
                    bundle.putLong("conflict_album_id", conflictAlbumId);
                }
            }
            j = run;
        }
        bundle.putLong("id", j);
        return bundle;
    }

    static DeleteRecord createDeleteRecord(int i, Cursor cursor, String str) {
        if (cursor == null) {
            return null;
        }
        String string = cursor.getString(7);
        if (TextUtils.isEmpty(string)) {
            string = cursor.getString(8);
        }
        if (!TextUtils.isEmpty(string)) {
            return new DeleteRecord(i, string, str);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static long[] delete(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, int i) {
        return delete(context, sQLiteDatabase, mediaManager, arrayList, jArr, 0, (ArrayList<Long>) null, i);
    }

    /* access modifiers changed from: private */
    public static long[] delete(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, int i, ArrayList<Long> arrayList2, int i2) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        ArrayList<Long> arrayList3 = arrayList;
        long[] jArr2 = jArr;
        ArrayList<Long> arrayList4 = arrayList2;
        if (i == 1) {
            String format = String.format("deleteLocal{%s}", new Object[]{Long.valueOf(Thread.currentThread().getId())});
            TimingTracing.beginTracing(format, String.format("count{%s}", new Object[]{Integer.valueOf(jArr2.length)}));
            long[] jArr3 = new long[jArr2.length];
            for (int i3 = 0; i3 < jArr2.length; i3++) {
                jArr3[i3] = new DeleteFile(context, arrayList, jArr2[i3], i2).run(sQLiteDatabase2, mediaManager2);
                if (jArr3[i3] > 0 && arrayList4 != null) {
                    arrayList4.add(Long.valueOf(jArr2[i3]));
                }
            }
            TimingTracing.stopTracing(format, (Printer) null);
            return jArr3;
        }
        Context context2 = context;
        long[] run = new Delete(context, arrayList3, jArr2, i2).run(sQLiteDatabase2, mediaManager2);
        if (arrayList4 != null) {
            arrayList4.addAll(arrayList3);
        }
        return run;
    }

    private static long[] deleteAlbum(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, int i, ArrayList<Long> arrayList2, int i2) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        long[] jArr2 = jArr;
        try {
            Numbers.ensurePositive(jArr);
            long[] jArr3 = new long[jArr2.length];
            for (int i3 = 0; i3 < jArr2.length; i3++) {
                if (i == 1) {
                    jArr3[i3] = new DeleteFile(context, arrayList, jArr2[i3], i2).run(sQLiteDatabase2, mediaManager2);
                } else {
                    jArr3[i3] = new DeleteAlbum(context, arrayList, jArr2[i3], i2).run(sQLiteDatabase2, mediaManager2);
                }
                if (jArr3[i3] > 0) {
                    arrayList2.add(Long.valueOf(jArr2[i3]));
                } else {
                    ArrayList<Long> arrayList3 = arrayList2;
                }
            }
            return jArr3;
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return new long[]{-100};
        }
    }

    private static long[] deleteById(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, int i, int i2, ArrayList<Long> arrayList2, int i3) {
        try {
            Numbers.ensurePositive(jArr);
            return i != -1 ? dupDelete(context, sQLiteDatabase, mediaManager, arrayList, jArr, i, i2, arrayList2, i3) : delete(context, sQLiteDatabase, mediaManager, arrayList, jArr, i2, arrayList2, i3);
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return new long[]{-100};
        }
    }

    private static long[] deleteByPath(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String[] strArr, int i, ArrayList<Long> arrayList2, int i2) {
        String[] strArr2 = strArr;
        try {
            long[] jArr = new long[strArr2.length];
            for (int i3 = 0; i3 < strArr2.length; i3++) {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                MediaManager mediaManager2 = mediaManager;
                jArr[i3] = new DeleteByPath(context, arrayList, strArr2[i3], i, arrayList2, i2).run(sQLiteDatabase, mediaManager);
            }
            return jArr;
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return new long[]{-100};
        }
    }

    private static long[] deleteBySha1(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String[] strArr, boolean z, int i) {
        String[] strArr2 = strArr;
        try {
            long[] jArr = new long[strArr2.length];
            for (int i2 = 0; i2 < strArr2.length; i2++) {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                MediaManager mediaManager2 = mediaManager;
                jArr[i2] = new DeleteBySha1(context, arrayList, strArr2[i2], z, i).run(sQLiteDatabase, mediaManager);
            }
            return jArr;
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return new long[]{-100};
        }
    }

    private static long[] deleteCloudByPath(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, String[] strArr, ArrayList<Long> arrayList2, int i) {
        String[] strArr2 = strArr;
        try {
            long[] jArr = new long[strArr2.length];
            for (int i2 = 0; i2 < strArr2.length; i2++) {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                MediaManager mediaManager2 = mediaManager;
                jArr[i2] = new DeleteCloudByPath(context, arrayList, strArr2[i2], arrayList2, i).run(sQLiteDatabase, mediaManager);
            }
            return jArr;
        } catch (Exception e) {
            Log.w("CloudManager", (Throwable) e);
            return new long[]{-100};
        }
    }

    /* access modifiers changed from: private */
    public static ContentValues desensitization(ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.remove("address");
        contentValues2.remove("exifGPSLatitude");
        contentValues2.remove("exifGPSLongitude");
        contentValues2.remove("extraGPS");
        contentValues2.remove("location");
        return contentValues2;
    }

    private static long[] dupDelete(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long[] jArr, int i, int i2, ArrayList<Long> arrayList2, int i3) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        long[] jArr2 = jArr;
        ArrayList<Long> arrayList3 = arrayList2;
        if (i2 == 1) {
            String format = String.format("dupDeleteLocal{%s}", new Object[]{Long.valueOf(Thread.currentThread().getId())});
            TimingTracing.beginTracing(format, String.format("count{%s}", new Object[]{Integer.valueOf(jArr2.length)}));
            long[] jArr3 = new long[jArr2.length];
            for (int i4 = 0; i4 < jArr2.length; i4++) {
                jArr3[i4] = new DupDeleteFile(context, arrayList, jArr2[i4], i, i3).run(sQLiteDatabase2, mediaManager2);
                if (jArr3[i4] > 0 && arrayList3 != null) {
                    arrayList3.add(Long.valueOf(jArr2[i4]));
                }
            }
            TimingTracing.stopTracing(format, (Printer) null);
            return jArr3;
        }
        long[] run = new DupDelete(context, arrayList, jArr, i, i3).run(sQLiteDatabase2, mediaManager2);
        if (arrayList3 != null) {
            arrayList3.addAll(arrayList);
        }
        return run;
    }

    private static long editPhotoDateInfoById(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, long j2, boolean z) {
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            MediaManager mediaManager2 = mediaManager;
            return new EditPhotoDateInfoById(context, arrayList, j, j2, z).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "update photo datetime error: id is %s", (Object) Long.valueOf(j));
            return -100;
        }
    }

    private static long editPhotoDateInfoByPath(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, String str, boolean z) {
        try {
            return new EditPhotoDateInfoByPath(context, arrayList, j, str, z).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "update photo datetime error: path is %s", (Object) str);
            return -100;
        }
    }

    private static void forceTop(Context context, SQLiteDatabase sQLiteDatabase, long j, MediaManager mediaManager, boolean z) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        long j2 = j;
        ContentValues contentValues = new ContentValues();
        if (z) {
            long nextForceTopTime = GalleryPreferences.Album.getNextForceTopTime();
            if (j2 == 2147483647L || j2 == 2147483646 || j2 == 2147483645 || j2 == 2147483644 || j2 == 2147483642) {
                GalleryPreferences.Album.setVirtualAlbumSortBy(j2, nextForceTopTime);
                return;
            }
            contentValues.put("sortBy", Long.valueOf(nextForceTopTime));
        } else if (j2 == 2147483647L) {
            GalleryPreferences.Album.setVirtualAlbumSortBy(j2, -998);
            return;
        } else if (j2 == 2147483646) {
            GalleryPreferences.Album.setVirtualAlbumSortBy(j2, -997);
            return;
        } else if (j2 == 2147483645) {
            GalleryPreferences.Album.setVirtualAlbumSortBy(j2, -994);
            return;
        } else if (j2 == 2147483644) {
            GalleryPreferences.Album.setVirtualAlbumSortBy(j2, -1001);
            return;
        } else if (j2 == 2147483642) {
            GalleryPreferences.Album.setVirtualAlbumSortBy(j2, -1000);
            return;
        } else {
            long j3 = j2 == 1 ? -999 : j2 == 2 ? -995 : 0;
            if (j3 == 0) {
                contentValues.putNull("sortBy");
            } else {
                contentValues.put("sortBy", Long.valueOf(j3));
            }
        }
        if (ShareAlbumManager.isOtherShareAlbumId(j)) {
            sQLiteDatabase2.update("shareAlbum", contentValues, String.format(Locale.US, "%s = ?", new Object[]{"_id"}), new String[]{String.valueOf(ShareAlbumManager.getOriginalAlbumId(j))});
        } else {
            sQLiteDatabase2.update("cloud", contentValues, String.format(Locale.US, "%s = ?", new Object[]{"_id"}), new String[]{String.valueOf(j)});
        }
    }

    private static void forceTop(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long[] jArr) {
        for (int length = jArr.length - 1; length >= 0; length--) {
            forceTop(context, sQLiteDatabase, jArr[length], mediaManager, true);
        }
    }

    static String genRelativePath(String str, boolean z) {
        return z ? DownloadPathHelper.getShareFolderRelativePathInCloud() : DownloadPathHelper.getFolderRelativePathInCloud(str);
    }

    static int getValidCount(long... jArr) {
        if (jArr == null) {
            return 0;
        }
        int length = jArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += jArr[i2] > 0 ? 1 : 0;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static boolean isSystemAlbum(long j) {
        return j == 1 || j == 2 || j == 4 || j == 3;
    }

    private static boolean isVirtualAlbum(long j) {
        for (int i : GalleryContract.Album.ALL_VIRTUAL_ALBUM_IDS) {
            if (j == ((long) i)) {
                return true;
            }
        }
        return false;
    }

    private static long move(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, long j2, int i) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        MediaManager mediaManager2 = mediaManager;
        try {
            Numbers.ensurePositive(j, j2);
            return i != -1 ? new DupMove(context, arrayList, j, j2, i).run(sQLiteDatabase, mediaManager) : new Move(context, arrayList, j2, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            return -100;
        }
    }

    private static long moveByUri(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, Uri uri) {
        try {
            return new MoveByUri(context, arrayList, uri, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            return -100;
        }
    }

    public static Cursor queryCloudItemByFilePath(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (FileUtils.isFileExist(str)) {
            try {
                ExifUtil.UserCommentData userCommentData = ExifUtil.getUserCommentData(str);
                String sha1 = userCommentData != null ? userCommentData.getSha1() : null;
                String relativePath = StorageUtils.getRelativePath(context, FileUtils.getParentFolderPath(str));
                if (TextUtils.isEmpty(relativePath)) {
                    Log.w("CloudManager", "Could't get album path for %s", str2);
                    return null;
                } else if (TextUtils.isEmpty(sha1)) {
                    return sQLiteDatabase.query("cloud", QUERY_BY_PATH_PROJECTION, "fileName LIKE ? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND localGroupId IN (SELECT _id FROM cloud WHERE localFile LIKE ?)", new String[]{FileUtils.getFileName(str), relativePath}, (String) null, (String) null, (String) null);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("is_thumbnail", true);
                    Cursor query = sQLiteDatabase.query("cloud", QUERY_BY_PATH_PROJECTION, "sha1=? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND localGroupId IN (SELECT _id FROM cloud WHERE localFile LIKE ?)", new String[]{sha1, relativePath}, (String) null, (String) null, (String) null);
                    if (query instanceof AbstractCursor) {
                        ((AbstractCursor) query).setExtras(bundle);
                    }
                    return query;
                }
            } catch (Exception e) {
                Log.w("CloudManager", "exif exifSha1 read fail %s", e);
                return null;
            }
        } else {
            return sQLiteDatabase.query("cloud", QUERY_BY_PATH_PROJECTION, "(localFile LIKE ? or thumbnailFile LIKE ?) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str2, str2}, (String) null, (String) null, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public static Cursor queryFavoritesTableBySha1(SQLiteDatabase sQLiteDatabase, String[] strArr, String str) {
        return sQLiteDatabase.query("favorites", strArr, "sha1 = ?", new String[]{str}, (String) null, (String) null, (String) null);
    }

    private static long removeFromSecret(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, long j2) {
        try {
            return new RemoveFromSecret(context, arrayList, j2, j).run(sQLiteDatabase, mediaManager);
        } catch (Exception unused) {
            Log.e("CloudManager", "remove from secret error %d", (Object) Long.valueOf(j2));
            return -100;
        }
    }

    private static long rename(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return -100;
        }
        try {
            return new Rename(context, arrayList, j, str).run(sQLiteDatabase, mediaManager);
        } catch (Exception e) {
            Log.e("CloudManager", (Throwable) e);
            return -100;
        }
    }

    private static Bundle renameAlbum(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, ArrayList<Long> arrayList, long j, String str) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Bundle bundle = new Bundle(2);
        long j2 = -100;
        if (!TextUtils.isEmpty(str)) {
            try {
                Numbers.ensurePositive(j);
                RenameAlbum renameAlbum = new RenameAlbum(context, arrayList, j, str);
                MediaManager mediaManager2 = mediaManager;
                long run = renameAlbum.run(sQLiteDatabase, mediaManager);
                if (run == -103 || run == -105) {
                    long conflictAlbumId = renameAlbum.getConflictAlbumId(sQLiteDatabase);
                    if (conflictAlbumId >= 0) {
                        bundle.putLong("conflict_album_id", conflictAlbumId);
                    }
                }
                j2 = run;
            } catch (Exception e) {
                Log.e("CloudManager", (Throwable) e);
            }
        }
        bundle.putLong("id", j2);
        return bundle;
    }

    private static void setAlbumAttributes(SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long[] jArr, long j, boolean z, boolean z2) {
        char c;
        int i;
        int i2;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        long[] jArr2 = jArr;
        long packageAttributeBit = AlbumManager.packageAttributeBit(j, z, z2);
        char c2 = 1;
        long packageAttributeBit2 = AlbumManager.packageAttributeBit(j, true, true);
        int length = jArr2.length;
        int i3 = 0;
        while (i3 < length) {
            long j2 = jArr2[i3];
            if (ShareAlbumManager.isOtherShareAlbumId(j2)) {
                long originalAlbumId = ShareAlbumManager.getOriginalAlbumId(j2);
                Locale locale = Locale.US;
                Object[] objArr = new Object[7];
                objArr[0] = "shareAlbum";
                objArr[c2] = "attributes";
                objArr[2] = "attributes";
                objArr[3] = Long.valueOf(packageAttributeBit2);
                objArr[4] = Long.valueOf(packageAttributeBit);
                objArr[5] = "_id";
                objArr[6] = String.valueOf(originalAlbumId);
                sQLiteDatabase2.execSQL(String.format(locale, "UPDATE %s SET %s = (%s & ~%d) | %d WHERE %s = %s", objArr));
                c = c2;
                i2 = i3;
                i = length;
            } else {
                List<Long> albumSyncAttributes = AlbumManager.getAlbumSyncAttributes();
                String transformToEditedColumnsElement = (!MiscUtil.isValid(albumSyncAttributes) || !albumSyncAttributes.contains(Long.valueOf(j))) ? "" : GalleryCloudUtils.transformToEditedColumnsElement(6);
                c = 1;
                sQLiteDatabase2.execSQL(String.format(Locale.US, "UPDATE %s SET %s = (%s & ~%d) | %d, %s=coalesce(replace(%s, '%s', '') || '%s', '%s') WHERE %s IN (%s)", new Object[]{"cloud", "attributes", "attributes", Long.valueOf(packageAttributeBit2), Long.valueOf(packageAttributeBit), "editedColumns", "editedColumns", transformToEditedColumnsElement, transformToEditedColumnsElement, transformToEditedColumnsElement, "_id", String.valueOf(j2)}));
                i2 = i3;
                i = length;
                mediaManager.updateAttributes(j2, j, z, z2);
            }
            i3 = i2 + 1;
            length = i;
            c2 = c;
        }
    }

    private static void startUpdater(Context context, boolean z, ArrayList<Long> arrayList) {
        FileHandleService.execute(context, z, arrayList);
    }

    private static void unforceTop(Context context, SQLiteDatabase sQLiteDatabase, MediaManager mediaManager, long[] jArr) {
        for (int length = jArr.length - 1; length >= 0; length--) {
            forceTop(context, sQLiteDatabase, jArr[length], mediaManager, false);
        }
    }

    /* access modifiers changed from: private */
    public static int updateFavoritesBySha1(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, String str) {
        return sQLiteDatabase.update("favorites", contentValues, "sha1 = ?", new String[]{str});
    }
}
