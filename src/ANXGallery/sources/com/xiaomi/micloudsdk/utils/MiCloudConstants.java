package com.xiaomi.micloudsdk.utils;

import android.net.Uri;
import java.io.File;

public class MiCloudConstants {
    public static final boolean USE_MEMBER_DAILY = new File("/data/system/micloud_member_daily").exists();
    public static final boolean USE_PREVIEW = new File("/data/system/xiaomi_account_preview").exists();

    public static class SYNC {
        public static final String SYNC_EXTRAS_FORCE = get_SYNC_EXTRAS_MICLOUD_FORCE();

        private static String get_SYNC_EXTRAS_MICLOUD_FORCE() {
            try {
                return (String) Class.forName("android.content.MiSyncPolicyResolver").getField("SYNC_EXTRAS_MICLOUD_FORCE").get((Object) null);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
                return "micloud_force";
            }
        }
    }

    public static class SYNC_LOG {
        public static final Uri URI_PROVIDER = Uri.parse("content://com.miui.cloudservice.sync.SyncLogProvider");
    }

    public static class URL {
        private static final String CURRENT_VERSION = (URL_MICLOUD_STATUS_BASE + "/mic/status/v2");
        public static final String URL_GALLERY_BASE = (MiCloudConstants.USE_PREVIEW ? "http://micloud.preview.n.xiaomi.net" : "http://galleryapi.micloud.xiaomi.net");
        public static final String URL_MICLOUD_MEMBER_STATUS_QUERY = (CURRENT_VERSION + "/user/level");
        private static final String URL_MICLOUD_STATUS_BASE = (MiCloudConstants.USE_PREVIEW ? "http://statusapi.micloud.preview.n.xiaomi.net" : "http://statusapi.micloud.xiaomi.net");
        public static final String URL_MICLOUD_STATUS_QUERY = (CURRENT_VERSION + "/user/overview");
        public static final String URL_RELOCATION_BASE = (MiCloudConstants.USE_PREVIEW ? "http://relocationapi.micloud.preview.n.xiaomi.net" : "http://relocationapi.micloud.xiaomi.net");
        public static final String URL_RICH_MEDIA_BASE = (MiCloudConstants.USE_PREVIEW ? "http://api.micloud.preview.n.xiaomi.net" : "http://fileapi.micloud.xiaomi.net");
    }
}
