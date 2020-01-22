package com.miui.gallery.photosapi;

import android.content.Context;
import android.net.Uri;

public final class PhotosOemApi {
    public static String getAuthority(Context context) {
        return context.getString(R.string.provider_authority);
    }

    private static Uri.Builder getBaseBuilder(Context context) {
        return new Uri.Builder().scheme("content").authority(getAuthority(context));
    }

    public static Uri getQueryProcessingUri(Context context) {
        return getBaseBuilder(context).appendPath("processing").build();
    }

    public static Uri getQueryProcessingUri(Context context, long j) {
        return getBaseBuilder(context).appendPath("processing").appendPath(String.valueOf(j)).build();
    }
}
