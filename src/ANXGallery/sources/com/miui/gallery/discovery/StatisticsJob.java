package com.miui.gallery.discovery;

import android.accounts.Account;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.syncstate.SyncStateUtil;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.keyguard.LockScreenHelper;
import java.io.Closeable;
import java.util.List;
import java.util.Locale;

public class StatisticsJob extends AbstractJob {
    private long formatProportion(float f) {
        return (long) (f * 100.0f);
    }

    private String formatSize(long j) {
        return String.format(Locale.US, "%.1fG", new Object[]{Float.valueOf((((float) (j / 500000000)) * 1.0f) / 2.0f)});
    }

    private boolean isEverSynced() {
        return safeQueryCount(GalleryDBHelper.getInstance().getReadableDatabase(), "SELECT COUNT(*)  FROM cloud WHERE serverType IN (1,2) AND (localFlag = 0 AND serverStatus = 'custom')", (String[]) null) > 0;
    }

    private int queryAlbumAttributes(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.rawQuery("SELECT attributes FROM cloud WHERE (serverType=0) AND localFile LIKE '" + str + "'", (String[]) null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    MiscUtil.closeSilently(cursor);
                    return 0;
                }
                MiscUtil.closeSilently(cursor);
                return -1;
            }
            int i = cursor.getInt(0);
            MiscUtil.closeSilently(cursor);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
    }

    private int queryAlbumCountOfCloudBackup(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE (serverType=0) AND (attributes & 1 != 0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null);
    }

    private int queryAlbumCountOfOtherShare(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*)FROM shareAlbum WHERE serverId IS NOT NULL", (String[]) null);
    }

    private int queryAlbumCountOfOwnerCreate(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE (serverType=0) AND localFile like '%MIUI/Gallery/cloud/owner%' AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null);
    }

    private int queryAlbumCountShowInAlbumPage(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE (serverType=0) AND (attributes & 64 <> 0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null);
    }

    private int queryAlbumCountShowInOtherAlbum(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE (serverType=0) AND (attributes & 64 != 0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null);
    }

    private int queryImageCountOf108MP(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE serverType = 1 AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND ((exifImageWidth >= 8900  AND exifImageLength >= 11900  AND exifImageWidth <= 9100  AND exifImageLength <= 12100) OR (exifImageLength >= 8900  AND exifImageWidth >= 11900  AND exifImageLength <= 9100  AND exifImageWidth <= 12100))", (String[]) null);
    }

    private int queryMediaCountByAlbumLocalFile(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM cloud WHERE localGroupId = (SELECT _id FROM cloud WHERE LOWER(localFile) = ? LIMIT 1) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))");
        sb.append(z ? " AND serverType=2" : "");
        return safeQueryCount(sQLiteDatabase, sb.toString(), new String[]{str.toLowerCase()});
    }

    private int queryMediaCountOfAllAlbums(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*)  FROM cloud WHERE serverType IN (1,2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))");
        sb.append(z ? "" : " AND attributes&16=0");
        return safeQueryCount(sQLiteDatabase, sb.toString(), (String[]) null);
    }

    private int queryMediaCountOfFavoritesAlbum(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM extended_cloud WHERE (isFavorite NOT NULL AND isFavorite > 0) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null);
    }

    private long queryMediaSpace(SQLiteDatabase sQLiteDatabase, String str) {
        String str2 = "SELECT SUM(size) FROM cloud WHERE serverType IN (1,2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId NOT IN (SELECT _id FROM cloud WHERE (serverType=0) AND attributes&64!=0))";
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + " AND (" + str + ")";
        }
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.rawQuery(str2, (String[]) null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    MiscUtil.closeSilently(cursor);
                    return 0;
                }
                MiscUtil.closeSilently(cursor);
                return -1;
            }
            long j = cursor.getLong(0);
            MiscUtil.closeSilently(cursor);
            return j;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
    }

    private int querySpecialMediaCountOfSortByModifyAlbum(SQLiteDatabase sQLiteDatabase) {
        List<String> albumPathsBySortDate = MediaSortDateHelper.getSortDateProvider().getAlbumPathsBySortDate(MediaSortDateHelper.SortDate.MODIFY_TIME);
        if (!MiscUtil.isValid(albumPathsBySortDate)) {
            return -1;
        }
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*) FROM cloud WHERE serverType IN (1,2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND STRFTIME('%Y%m%d',dateModified/1000, 'unixepoch', 'localtime')>STRFTIME('%Y%m%d', CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END /1000, 'unixepoch', 'localtime') AND localGroupId IN (   SELECT _id   FROM cloud   WHERE (serverType=0)   AND (attributes & 4 != 0)   AND " + InternalContract.Cloud.ALIAS_NON_SYSTEM_ALBUM + "   AND " + "localFile" + " COLLATE NOCASE IN ('" + TextUtils.join("', '", albumPathsBySortDate) + "')" + ")", (String[]) null);
    }

    private int queryVideoCountOfAll(SQLiteDatabase sQLiteDatabase) {
        return safeQueryCount(sQLiteDatabase, "SELECT COUNT(*)  FROM cloud WHERE serverType = 2 AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND attributes&16=0", (String[]) null);
    }

    private void recordAlbumsAndImagesCount() {
        int i;
        SQLiteDatabase readableDatabase = GalleryDBHelper.getInstance().getReadableDatabase();
        int queryMediaCountOfAllAlbums = queryMediaCountOfAllAlbums(readableDatabase, false);
        int queryVideoCountOfAll = queryVideoCountOfAll(readableDatabase);
        int queryMediaCountByAlbumLocalFile = queryMediaCountByAlbumLocalFile(readableDatabase, "DCIM/Camera", false);
        int queryMediaCountByAlbumLocalFile2 = queryMediaCountByAlbumLocalFile(readableDatabase, "DCIM/Camera", true);
        int queryMediaCountByAlbumLocalFile3 = queryMediaCountByAlbumLocalFile(readableDatabase, "DCIM/Screenshots", false);
        int queryMediaCountByAlbumLocalFile4 = queryMediaCountByAlbumLocalFile(readableDatabase, "Download", false);
        int queryAlbumCountOfCloudBackup = queryAlbumCountOfCloudBackup(readableDatabase);
        int queryAlbumCountOfOwnerCreate = queryAlbumCountOfOwnerCreate(readableDatabase);
        int queryAlbumCountOfOtherShare = queryAlbumCountOfOtherShare(readableDatabase);
        int queryAlbumCountShowInAlbumPage = queryAlbumCountShowInAlbumPage(readableDatabase);
        int queryAlbumCountShowInOtherAlbum = queryAlbumCountShowInOtherAlbum(readableDatabase);
        int queryMediaCountOfFavoritesAlbum = queryMediaCountOfFavoritesAlbum(readableDatabase);
        int querySpecialMediaCountOfSortByModifyAlbum = querySpecialMediaCountOfSortByModifyAlbum(readableDatabase);
        int queryImageCountOf108MP = queryImageCountOf108MP(readableDatabase);
        int i2 = -1;
        if (queryMediaCountOfAllAlbums != -1) {
            i = queryImageCountOf108MP;
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_all_albums", (long) queryMediaCountOfAllAlbums);
            i2 = -1;
        } else {
            i = queryImageCountOf108MP;
        }
        if (queryVideoCountOfAll != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "video_count_of_all_albums", (long) queryVideoCountOfAll);
        }
        if (queryMediaCountByAlbumLocalFile != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_camera_album", (long) queryMediaCountByAlbumLocalFile);
        }
        if (queryMediaCountByAlbumLocalFile2 != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "video_count_of_camera_album", (long) queryMediaCountByAlbumLocalFile2);
        }
        if (queryMediaCountByAlbumLocalFile3 != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_screenshot_album", (long) queryMediaCountByAlbumLocalFile3);
        }
        if (queryMediaCountByAlbumLocalFile4 != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_download_album", (long) queryMediaCountByAlbumLocalFile4);
        }
        if (queryAlbumCountOfCloudBackup != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "album_count_of_cloud_backup", (long) queryAlbumCountOfCloudBackup);
        }
        if (queryAlbumCountOfOwnerCreate != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "album_count_of_owner_create", (long) queryAlbumCountOfOwnerCreate);
        }
        if (queryAlbumCountOfOtherShare != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "album_count_of_other_share", (long) queryAlbumCountOfOtherShare);
        }
        if (queryAlbumCountShowInAlbumPage != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "album_count_show_in_album_page", (long) queryAlbumCountShowInAlbumPage);
        }
        if (queryAlbumCountShowInOtherAlbum != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "album_count_show_in_other_album", (long) queryAlbumCountShowInOtherAlbum);
        }
        if (queryMediaCountOfFavoritesAlbum != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_favorites_album", (long) queryMediaCountOfFavoritesAlbum);
        }
        if (querySpecialMediaCountOfSortByModifyAlbum != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "special_image_count_of_sort_by_modify_and_show_in_home_album", (long) querySpecialMediaCountOfSortByModifyAlbum);
        }
        int i3 = i;
        if (i3 != i2) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("albums_and_images_count", "image_count_of_108mp", (long) i3);
        }
    }

    private void recordGallerySettings() {
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(GalleryApp.sGetAndroidContext());
        boolean z = xiaomiAccount != null;
        GallerySamplingStatHelper.recordStringPropertyEvent("settings", "has_xiaomi_account", String.valueOf(z));
        if (z) {
            boolean syncAutomatically = ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider");
            boolean isOnlyShowLocalPhoto = GalleryPreferences.LocalMode.isOnlyShowLocalPhoto();
            boolean isAIAlbumEnabled = AIAlbumStatusHelper.isAIAlbumEnabled();
            boolean isFaceAlbumEnabled = AIAlbumStatusHelper.isFaceAlbumEnabled();
            boolean backupOnlyInWifi = GalleryPreferences.Sync.getBackupOnlyInWifi();
            boolean isLocalSearchOpen = AIAlbumStatusHelper.isLocalSearchOpen(true);
            boolean isCloudControlSearchBarOpen = AIAlbumStatusHelper.isCloudControlSearchBarOpen();
            boolean isCloudControlSearchAIAlbumOpen = AIAlbumStatusHelper.isCloudControlSearchAIAlbumOpen();
            boolean isAutoDownload = GalleryPreferences.Sync.isAutoDownload();
            DownloadType downloadType = GalleryPreferences.Sync.getDownloadType();
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_cloud_backup_on_c", String.valueOf(syncAutomatically));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_local_mode_on_c", String.valueOf(isOnlyShowLocalPhoto));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_face_album_on_c", String.valueOf(isFaceAlbumEnabled));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_backup_only_wifi_on_c", String.valueOf(backupOnlyInWifi));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_ai_album_on_c", String.valueOf(isAIAlbumEnabled));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_search_ai_album_enabled_c", String.valueOf(isCloudControlSearchAIAlbumOpen));
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_search_bar_enabled_c", String.valueOf(isCloudControlSearchBarOpen));
            if (isCloudControlSearchAIAlbumOpen) {
                GallerySamplingStatHelper.recordStringPropertyEvent("settings", "is_search_user_switch_on_c", String.valueOf(isLocalSearchOpen));
            }
            GallerySamplingStatHelper.recordStringPropertyEvent("settings", "auto_download_on_c", String.valueOf(isAutoDownload));
            if (isAutoDownload) {
                GallerySamplingStatHelper.recordStringPropertyEvent("settings", "download_type_c", String.valueOf(downloadType));
            }
        }
        GallerySamplingStatHelper.recordStringPropertyEvent("baby", "baby_lock_wallpaper_in_use", String.valueOf("com.miui.gallery.cloud.baby.wallpaper_provider".equals(LockScreenHelper.getLockWallpaperProvider(GalleryApp.sGetAndroidContext().getContentResolver()))));
    }

    private void recordMediasSpace() {
        SQLiteDatabase readableDatabase = GalleryDBHelper.getInstance().getReadableDatabase();
        long queryMediaSpace = queryMediaSpace(readableDatabase, (String) null);
        if (queryMediaSpace != -1) {
            GallerySamplingStatHelper.recordStringPropertyEvent("medias_space", "medias_space_of_all", formatSize(queryMediaSpace));
            long queryMediaSpace2 = queryMediaSpace(readableDatabase, "serverType=1 AND " + String.format(Locale.US, "localGroupId IN (SELECT _id FROM cloud WHERE localFile LIKE '%s')", new Object[]{"DCIM/Camera"}));
            if (queryMediaSpace2 != -1) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("medias_space", String.format(Locale.US, "medias_space_ratio_of_album_%s", new Object[]{"Camera-Image"}), formatProportion((((float) queryMediaSpace2) * 1.0f) / ((float) queryMediaSpace)));
            }
            long queryMediaSpace3 = queryMediaSpace(readableDatabase, "serverType=2 AND " + String.format(Locale.US, "localGroupId IN (SELECT _id FROM cloud WHERE localFile LIKE '%s')", new Object[]{"DCIM/Camera"}));
            if (queryMediaSpace3 != -1) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("medias_space", String.format(Locale.US, "medias_space_ratio_of_album_%s", new Object[]{"Camera-Video"}), formatProportion((((float) queryMediaSpace3) * 1.0f) / ((float) queryMediaSpace)));
            }
            long queryMediaSpace4 = queryMediaSpace(readableDatabase, "serverType=2");
            if (queryMediaSpace4 != -1) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("medias_space", String.format(Locale.US, "medias_space_ratio_of_album_%s", new Object[]{"Video"}), formatProportion((((float) queryMediaSpace4) * 1.0f) / ((float) queryMediaSpace)));
            }
            int queryAlbumAttributes = queryAlbumAttributes(readableDatabase, "tencent/micromsg/weixin");
            if (queryAlbumAttributes != -1 && (((long) queryAlbumAttributes) & 64) == 0) {
                long queryMediaSpace5 = queryMediaSpace(readableDatabase, String.format(Locale.US, "localGroupId IN (SELECT _id FROM cloud WHERE localFile LIKE '%s')", new Object[]{"tencent/micromsg/weixin"}));
                if (queryMediaSpace5 != -1) {
                    GallerySamplingStatHelper.recordNumericPropertyEvent("medias_space", String.format(Locale.US, "medias_space_ratio_of_album_%s", new Object[]{"weixin"}), formatProportion((((float) queryMediaSpace5) * 1.0f) / ((float) queryMediaSpace)));
                }
            }
        }
    }

    private void recordOtherProperties() {
        GallerySamplingStatHelper.recordStringPropertyEvent("Sync", "sync_auto_downloaded_property", String.valueOf(GalleryPreferences.Sync.isEverAutoDownloaded()));
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        boolean hasExternalSDCard = StorageUtils.hasExternalSDCard(sGetAndroidContext);
        GallerySamplingStatHelper.recordStringPropertyEvent("storage", "storage_has_external_sd_card", String.valueOf(hasExternalSDCard));
        boolean isDeviceSupportStoryFunction = ImageFeatureManager.isDeviceSupportStoryFunction();
        if (hasExternalSDCard) {
            GallerySamplingStatHelper.recordStringPropertyEvent("storage", "storage_is_external_sd_card_prior_storage", String.valueOf(StorageUtils.isExternalSDCardPriorStorage()));
            GallerySamplingStatHelper.recordStringPropertyEvent("storage", "storage_can_write_external_sd_card", String.valueOf(StorageUtils.canWrite(sGetAndroidContext, StorageUtils.getSecondaryStoragePath())));
        }
        if (isDeviceSupportStoryFunction) {
            GallerySamplingStatHelper.recordStringPropertyEvent("assistant", "assistant_card_function_status", String.valueOf(isDeviceSupportStoryFunction));
        }
    }

    private void recordPeopleProperties() {
        List<Long> queryPeopleIdOfRelation = FaceManager.queryPeopleIdOfRelation(GalleryApp.sGetAndroidContext(), PeopleContactInfo.Relation.myself.getRelationType());
        GallerySamplingStatHelper.recordNumericPropertyEvent("people", "people_count_under_myself", queryPeopleIdOfRelation == null ? 0 : (long) queryPeopleIdOfRelation.size());
    }

    private void recordSyncState() {
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(GalleryApp.sGetAndroidContext());
        boolean z = true;
        GallerySamplingStatHelper.recordStringPropertyEvent("sync_state_analysis", "login_account", String.valueOf(xiaomiAccount != null));
        if (xiaomiAccount != null) {
            boolean masterSyncAutomatically = ContentResolver.getMasterSyncAutomatically();
            boolean syncAutomatically = ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider");
            GallerySamplingStatHelper.recordStringPropertyEvent("sync_state_analysis", "master_sync", String.valueOf(masterSyncAutomatically));
            if (masterSyncAutomatically) {
                GallerySamplingStatHelper.recordStringPropertyEvent("sync_state_analysis", "gallery_sync", String.valueOf(syncAutomatically));
            }
            if (!syncAutomatically) {
                z = isEverSynced();
                GallerySamplingStatHelper.recordStringPropertyEvent("sync_state_analysis", "sync_off_ever_synced", String.valueOf(z));
                SyncStateUtil.CloudSpaceInfo cloudSpaceInfo = SyncStateUtil.getCloudSpaceInfo(GalleryApp.sGetAndroidContext());
                long total = cloudSpaceInfo.getTotal();
                if (total > 0) {
                    GallerySamplingStatHelper.recordNumericPropertyEvent("sync_state_analysis", "sync_off_cloud_space_state", (long) ((int) ((((float) (total - cloudSpaceInfo.getUsed())) * 100.0f) / ((float) total))));
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            GallerySamplingStatHelper.recordNumericPropertyEvent("sync_state_analysis", "sync_off_media_count", (long) queryMediaCountByAlbumLocalFile(GalleryDBHelper.getInstance().getReadableDatabase(), "DCIM/Camera", false));
            String primaryStoragePath = StorageUtils.getPrimaryStoragePath();
            long totalBytes = StorageUtils.getTotalBytes(primaryStoragePath);
            if (totalBytes > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("sync_state_analysis", "sync_off_local_space_state", (long) ((int) ((((float) StorageUtils.getAvailableBytes(primaryStoragePath)) * 100.0f) / ((float) totalBytes))));
            }
        }
    }

    private int safeQueryCount(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.rawQuery(str, strArr);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    MiscUtil.closeSilently(cursor);
                    return 0;
                }
                MiscUtil.closeSilently(cursor);
                return -1;
            }
            int i = cursor.getInt(0);
            MiscUtil.closeSilently(cursor);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
    }

    public Object execJob() {
        recordGallerySettings();
        recordAlbumsAndImagesCount();
        recordMediaTypes();
        recordMediasSpace();
        recordSyncState();
        recordOtherProperties();
        recordPeopleProperties();
        return null;
    }

    public JobInfo getJobInfo(Context context, ComponentName componentName) {
        return new JobInfo.Builder(this.mJobId, componentName).setRequiredNetworkType(2).setPeriodic(604800000).setRequiresDeviceIdle(true).setRequiresCharging(true).build();
    }

    public void recordMediaTypes() {
        Cursor cursor = null;
        try {
            cursor = GalleryDBHelper.getInstance().getReadableDatabase().rawQuery("SELECT mimeType, COUNT(*)  FROM cloud WHERE serverType IN (1,2) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND attributes&16=0 GROUP BY mimeType", (String[]) null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    GallerySamplingStatHelper.recordNumericPropertyEvent("media_type_count", "media_count_of_" + cursor.getString(0), (long) cursor.getInt(1));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
        MiscUtil.closeSilently(cursor);
    }
}
