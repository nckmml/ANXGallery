package com.miui.gallery.util.uil;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.sdk.download.DownloadType;

public class CloudImageLoadingListenerAdapter implements CloudImageLoadingListener {
    public void onDownloadComplete(Uri uri, DownloadType downloadType, View view, String str) {
    }

    public void onLoadingCancelled(Uri uri, DownloadType downloadType, View view) {
    }

    public void onLoadingComplete(Uri uri, DownloadType downloadType, View view, Bitmap bitmap) {
    }

    public void onLoadingFailed(Uri uri, DownloadType downloadType, View view, ErrorCode errorCode) {
    }

    public void onLoadingStarted(Uri uri, DownloadType downloadType, View view) {
    }
}
