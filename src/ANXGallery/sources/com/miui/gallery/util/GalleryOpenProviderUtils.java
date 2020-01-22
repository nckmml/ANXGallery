package com.miui.gallery.util;

import android.net.Uri;

public class GalleryOpenProviderUtils {
    public static Uri translateToContent(String str) {
        return Uri.parse("content://com.miui.gallery.open/raw").buildUpon().appendPath(str).build();
    }
}
