package com.miui.gallery.util.deprecated;

import android.content.Context;
import android.content.SharedPreferences;
import com.miui.gallery.preference.GalleryPreferences;

public class Preference extends BaseDeprecatedPreference {
    public static String createAutoUploadAlbumPreferenceKey(String str) {
        return "auto_upload_album_preference_key_prefix_" + str;
    }

    private static SharedPreferences getMultiProcessSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName() + "_preferences_multi", 4);
    }

    public static boolean getSyncFetchAllPrivateData() {
        return sGetDefaultPreferences().getBoolean("sync_fetch_all_private_data", false);
    }

    public static boolean getSyncFetchPrivateVideo() {
        return sGetDefaultPreferences().getBoolean("sync_fetch_private_video", false);
    }

    public static boolean getSyncFetchSyncExtraInfoFromV2ToV3() {
        return sGetDefaultPreferences().getBoolean("sync_fetch_syncextreinfo_from_v2_to_v3", false);
    }

    public static boolean getSyncShouldClearDataBase() {
        return sGetDefaultPreferences().getBoolean("sync_should_clean_data", false);
    }

    private static boolean isAutoUploadAlbumPreferenceKey(String str) {
        return str.startsWith("auto_upload_album_preference_key_prefix_");
    }

    public static boolean sGetCloudGalleryRecyclebinFull() {
        return sGetDefaultPreferences().getBoolean("cloud_gallery_recyclebin_full", false);
    }

    public static boolean sGetCloudGallerySpaceFull() {
        long j = sGetDefaultPreferences().getLong("cloud_gallery_space_full_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - j >= 3600000 || currentTimeMillis < j) {
            return false;
        }
        return sGetDefaultPreferences().getBoolean("cloud_gallery_space_full", false);
    }

    public static long sGetFilterMinSize() {
        try {
            return Long.parseLong(sGetDefaultPreferences().getString("filter_min_size", String.valueOf(0)));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static boolean sGetIsAlbumAutoUploadOpen(String str) {
        return sGetDefaultPreferences().getBoolean(createAutoUploadAlbumPreferenceKey(str), false);
    }

    public static boolean sGetIsScreenShotAutoUploadOpen() {
        return sGetDefaultPreferences().getBoolean(createAutoUploadAlbumPreferenceKey("auto-upload-screenshot"), true);
    }

    public static boolean sHaveCheckBabyForNewService(Context context) {
        return getMultiProcessSharedPreferences(context).getBoolean("have_check_baby_for_new_face_service", false);
    }

    public static boolean sIsFirstSynced() {
        return sGetDefaultPreferences().getBoolean("first_synced", false);
    }

    public static int sIsInternationalAccount() {
        return sGetDefaultPreferences().getInt("is_international_account", 2);
    }

    public static void sRemoveCloudSettings() {
        sGetDefaultEditor().remove("last_slowscan_time").remove("cloud_gallery_space_full").remove("cloud_gallery_recyclebin_full").remove("sync_only_in_wifi").remove("sync_fetch_syncextreinfo_from_v2_to_v3").remove("sync_fetch_all_private_data").remove("sync_fetch_private_video").remove("first_synced").remove("is_international_account").remove(GalleryPreferences.PrefKeys.FACE_CLOUD_STATUS).remove(GalleryPreferences.PrefKeys.FACE_CLOUD_STATUS_NEXT_CHECK_TIME).remove(GalleryPreferences.PrefKeys.FACE_FEATURE_SWITCH_PENDING).remove(GalleryPreferences.PrefKeys.FACE_URL_FOR_WAITING).remove(GalleryPreferences.PrefKeys.FACE_URL_FOR_QUEUING).remove("micloud_vip_level").remove("has_ever_synced_system_album").commit();
        SharedPreferences.Editor sGetDefaultEditor = sGetDefaultEditor();
        for (String next : sGetDefaultPreferences().getAll().keySet()) {
            if (isAutoUploadAlbumPreferenceKey(next)) {
                sGetDefaultEditor.remove(next);
            }
        }
        sGetDefaultEditor.commit();
    }

    public static void sSetCloudGalleryRecyclebinFull(boolean z) {
        sGetDefaultEditor().putBoolean("cloud_gallery_recyclebin_full", z).commit();
    }

    public static void sSetCloudGallerySpaceFull(boolean z) {
        sGetDefaultEditor().putBoolean("cloud_gallery_space_full", z).putLong("cloud_gallery_space_full_time", System.currentTimeMillis()).commit();
    }

    public static void sSetFirstSynced() {
        sGetDefaultEditor().putBoolean("first_synced", true).commit();
    }

    public static void sSetHaveCheckBabyForNewService(Context context) {
        getMultiProcessSharedPreferences(context).edit().putBoolean("have_check_baby_for_new_face_service", true).commit();
    }

    public static void sSetIsInternationalAccount(int i) {
        sGetDefaultEditor().putInt("is_international_account", i).commit();
    }

    public static void sSetLastSlowScanTime(long j) {
        sGetDefaultEditor().putLong("last_slowscan_time", j).apply();
    }

    public static void setDBUpgradeTo42() {
        sGetDefaultEditor().putBoolean("database_upgrade_to_42_clean_data", true).apply();
    }

    public static void setSyncFetchAllPrivateData() {
        sGetDefaultEditor().putBoolean("sync_fetch_all_private_data", true).commit();
    }

    public static void setSyncFetchPrivateVideo() {
        sGetDefaultEditor().putBoolean("sync_fetch_private_video", true).commit();
    }

    public static void setSyncFetchSyncExtraInfoFromV2ToV3(boolean z) {
        sGetDefaultEditor().putBoolean("sync_fetch_syncextreinfo_from_v2_to_v3", z).apply();
    }

    public static void setSyncShouldClearDataBase(boolean z) {
        sGetDefaultEditor().putBoolean("sync_should_clean_data", z).commit();
    }
}
