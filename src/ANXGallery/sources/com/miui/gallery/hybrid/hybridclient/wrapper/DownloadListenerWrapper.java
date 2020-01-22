package com.miui.gallery.hybrid.hybridclient.wrapper;

import android.webkit.DownloadListener;

public class DownloadListenerWrapper implements DownloadListener {
    protected DownloadListener downloadListener;

    public DownloadListenerWrapper(DownloadListener downloadListener2) {
        this.downloadListener = downloadListener2;
    }

    public DownloadListener getWrapped() {
        return this.downloadListener;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        DownloadListener downloadListener2 = this.downloadListener;
        if (downloadListener2 != null) {
            downloadListener2.onDownloadStart(str, str2, str3, str4, j);
        }
    }
}
