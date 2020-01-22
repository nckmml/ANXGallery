package com.miui.gallery.util;

import com.miui.gallery.GalleryApp;

public class ResourceUtils {
    public static String getQuantityString(int i, int i2, Object... objArr) {
        return GalleryApp.sGetAndroidContext().getResources().getQuantityString(i, i2, objArr);
    }

    public static String getString(int i) {
        return GalleryApp.sGetAndroidContext().getString(i);
    }

    public static String getString(int i, Object... objArr) {
        return GalleryApp.sGetAndroidContext().getString(i, objArr);
    }
}
