package com.miui.gallery.model;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.loader.BaseLoader;
import com.miui.gallery.loader.MediaSetLoader;
import com.miui.gallery.util.Log;
import java.util.List;

public class MediaSource extends PhotoLoaderSource {
    private static UriMatcher sUriMatcher;
    private String TAG = "MediaSource";

    public MediaSource() {
        if (sUriMatcher == null) {
            sUriMatcher = new UriMatcher(-1);
            sUriMatcher.addURI("media", "*/images/media/#", 1);
            sUriMatcher.addURI("media", "*/video/media/#", 2);
            sUriMatcher.addURI("media", "*/file/#", 3);
        }
    }

    private String checkArgumentVolumeName(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "empty volume name");
            return null;
        } else if ("internal".equals(str) || "external".equals(str) || "external_primary".equals(str)) {
            return str;
        } else {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (('a' > charAt || charAt > 'f') && (('0' > charAt || charAt > '9') && charAt != '-')) {
                    Log.e(this.TAG, "illegal volume name");
                    return null;
                }
            }
            return str;
        }
    }

    private String getVolumeNameFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments != null && pathSegments.size() > 0) {
            return pathSegments.get(0);
        }
        Log.e(this.TAG, "illegal uri : wrong path");
        return null;
    }

    public BaseLoader createDataLoader(Context context, Uri uri, Bundle bundle) {
        if (sUriMatcher.match(uri) == -1) {
            return null;
        }
        String checkArgumentVolumeName = checkArgumentVolumeName(getVolumeNameFromUri(uri));
        if (TextUtils.isEmpty(checkArgumentVolumeName)) {
            return null;
        }
        return "internal".equals(checkArgumentVolumeName) ? new MediaSetLoader(context, uri, bundle, true) : new MediaSetLoader(context, uri, bundle, false);
    }

    public boolean match(Uri uri, Bundle bundle) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }
}
