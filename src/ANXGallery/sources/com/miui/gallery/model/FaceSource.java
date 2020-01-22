package com.miui.gallery.model;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import com.miui.gallery.loader.BaseLoader;
import com.miui.gallery.loader.FaceCloudSetLoader;

public class FaceSource extends PhotoLoaderSource {
    private static UriMatcher sUriMatcher;

    public FaceSource() {
        if (sUriMatcher == null) {
            sUriMatcher = new UriMatcher(-1);
            sUriMatcher.addURI("com.miui.gallery.cloud.provider", "person", 0);
            sUriMatcher.addURI("com.miui.gallery.cloud.provider", "person_recommend", 1);
        }
    }

    public BaseLoader createDataLoader(Context context, Uri uri, Bundle bundle) {
        int match = sUriMatcher.match(uri);
        if (match == 0) {
            return new FaceCloudSetLoader(context, uri, bundle);
        }
        if (match != 1) {
            return null;
        }
        return new FaceCloudSetLoader(context, uri, bundle);
    }

    public boolean match(Uri uri, Bundle bundle) {
        return uri != null && "content".equals(uri.getScheme()) && "com.miui.gallery.cloud.provider".equals(uri.getAuthority()) && sUriMatcher.match(uri) != -1;
    }
}
