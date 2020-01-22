package com.miui.gallery.util;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.preference.PreferenceHelper;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.deprecated.Storage;
import com.miui.gallery.util.uil.BlobCache;
import java.io.File;

public class OldCacheCleaner {
    public static void clean() {
        if (!PreferenceHelper.getBoolean(GalleryPreferences.PrefKeys.UPGRADE_OLD_CACHE_CLEANED, false)) {
            PreferenceHelper.putBoolean(GalleryPreferences.PrefKeys.UPGRADE_OLD_CACHE_CLEANED, true);
            cleanThumbnailCache();
            cleanBlobCache();
            cleanTempFiles();
        }
    }

    private static void cleanBlobCache() {
        String[] strArr = {"imgcache", "rev_geocoding", "bookmark", "avatar", "internal_img_cache", "cover"};
        for (File file : new File[]{GalleryApp.sGetAndroidContext().getCacheDir(), GalleryApp.sGetAndroidContext().getExternalCacheDir()}) {
            if (file != null) {
                for (String str : strArr) {
                    BlobCache.deleteFiles(file.getAbsolutePath() + File.separator + str);
                }
            }
        }
    }

    private static void cleanTempFiles() {
        for (String str : Storage.getAllSdCardAvatarFilePath()) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.TEMP, str);
        }
    }

    private static void cleanThumbnailCache() {
        for (String str : StorageUtils.getPathsInExternalStorage(GalleryApp.sGetAndroidContext(), "MIUI")) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.TEMP, str + File.separator + ".cache/Gallery");
        }
    }
}
