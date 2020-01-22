package miui.cloud;

import java.io.File;

public class MiCloudCompat {
    public static final String FACE_HOST;
    public static final String GALLERY_ANONYMOUS_HOST;
    public static final String GALLERY_HOST;
    public static final String SEARCH_ANONYMOUS_HOST;
    public static final String SEARCH_HOST;
    public static final String URL_ACCOUNT_API_BASE_SECURE;
    public static final boolean USE_PREVIEW = new File("/data/system/xiaomi_account_preview").exists();

    static {
        if (USE_PREVIEW) {
            GALLERY_HOST = "http://galleryapi.micloud.preview.n.xiaomi.net";
            GALLERY_ANONYMOUS_HOST = "http://galleryapi.micloud.preview.n.xiaomi.net";
            URL_ACCOUNT_API_BASE_SECURE = "http://api.account.preview.n.xiaomi.net/pass";
            FACE_HOST = "http://galleryfaceapi.micloud.preview.n.xiaomi.net";
            SEARCH_HOST = "http://gallerysearchapi.micloud.preview.n.xiaomi.net";
            SEARCH_ANONYMOUS_HOST = "http://gallerysearchapi.micloud.preview.n.xiaomi.nett";
            return;
        }
        GALLERY_HOST = "http://galleryapi.micloud.xiaomi.net";
        GALLERY_ANONYMOUS_HOST = "http://g.galleryapi.micloud.xiaomi.net";
        URL_ACCOUNT_API_BASE_SECURE = "https://api.account.xiaomi.com/pass";
        FACE_HOST = "http://galleryfaceapi.micloud.xiaomi.net";
        SEARCH_HOST = "http://gallerysearchapi.micloud.xiaomi.net";
        SEARCH_ANONYMOUS_HOST = "http://g.gallerysearchapi.micloud.xiaomi.net";
    }

    public static String getQuantityStringWithUnit(long j) {
        float f = (float) j;
        if (f > 1.07374184E8f) {
            return String.format("%1$.2fGB", new Object[]{Float.valueOf(((f / 1024.0f) / 1024.0f) / 1024.0f)});
        } else if (f <= 104857.6f) {
            return f > 0.0f ? "0.1MB" : "0MB";
        } else {
            return String.format("%1$.2fMB", new Object[]{Float.valueOf((f / 1024.0f) / 1024.0f)});
        }
    }
}
