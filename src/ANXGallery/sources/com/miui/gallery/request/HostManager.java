package com.miui.gallery.request;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StorageUtils;
import java.io.File;

public class HostManager {
    private static final String MICLOUD_GALLERY_WEB_URL_BASE = (new File(URL_SWITCH_FILE).exists() ? "https://daily.i.mi.com/mobile/gallery" : "https://i.mi.com/mobile/gallery");
    private static final String URL_SWITCH_FILE = StorageUtils.getPathInPrimaryStorage("MIUI/Gallery/cloud/url_daily");

    public static void clearCookies() {
        try {
            if (CookieManager.getInstance().hasCookies()) {
                CookieManager.getInstance().removeAllCookie();
            }
        } catch (Exception e) {
            Log.e("HostManager", (Throwable) e);
        }
    }

    public static String getTrashBinUrl() {
        return MICLOUD_GALLERY_WEB_URL_BASE + "/trash";
    }

    public static boolean isGalleryUrl(String str) {
        if (!isInternalUrl(str)) {
            return false;
        }
        String path = Uri.parse(str).getPath();
        if (!TextUtils.isEmpty(path)) {
            return path.contains("/mobile/gallery");
        }
        return false;
    }

    public static boolean isInternalUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (parse.isOpaque() || parse.isRelative()) {
            return true;
        }
        String host = parse.getHost();
        if (host == null) {
            return false;
        }
        return host.endsWith("miui.com") || host.endsWith("mi.com") || host.endsWith("xiaomi.com") || host.endsWith("xiaomi.net");
    }
}
