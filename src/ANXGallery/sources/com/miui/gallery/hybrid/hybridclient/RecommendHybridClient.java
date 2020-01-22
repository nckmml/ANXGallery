package com.miui.gallery.hybrid.hybridclient;

import android.content.Context;
import android.webkit.WebSettings;

public class RecommendHybridClient extends GalleryHybridClient {
    public RecommendHybridClient(Context context, String str) {
        super(context, str);
    }

    public boolean isSupportPullToRefresh() {
        return false;
    }

    public void onConfigWebSettings(WebSettings webSettings) {
        super.onConfigWebSettings(webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }
}
