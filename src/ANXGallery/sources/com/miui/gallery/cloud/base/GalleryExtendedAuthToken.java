package com.miui.gallery.cloud.base;

import android.text.TextUtils;
import com.xiaomi.micloudsdk.data.ExtendedAuthToken;

public class GalleryExtendedAuthToken {
    private ExtendedAuthToken mToken;

    public GalleryExtendedAuthToken(ExtendedAuthToken extendedAuthToken) {
        this.mToken = extendedAuthToken;
    }

    public static GalleryExtendedAuthToken parse(String str) {
        ExtendedAuthToken parse;
        if (!TextUtils.isEmpty(str) && (parse = ExtendedAuthToken.parse(str)) != null) {
            return new GalleryExtendedAuthToken(parse);
        }
        return null;
    }

    public String getSecurity() {
        return this.mToken.security;
    }
}
