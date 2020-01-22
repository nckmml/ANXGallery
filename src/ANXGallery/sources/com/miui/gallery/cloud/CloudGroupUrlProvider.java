package com.miui.gallery.cloud;

import com.miui.gallery.cloud.HostManager;

public abstract class CloudGroupUrlProvider {

    private static class OwnerCloudGroupUrlProvider extends CloudGroupUrlProvider {
        private OwnerCloudGroupUrlProvider() {
        }

        public String getEditGroupUrl(String str, String str2) {
            return HostManager.OwnerAlbum.getEditAlbumUrl(str2);
        }

        public String getThumbnailInfoUrl(String str, String str2) {
            return HostManager.OwnerAlbum.getThumbnailInfoUrl(str2);
        }
    }

    private static class OwnerInstanceHolder {
        /* access modifiers changed from: private */
        public static final CloudGroupUrlProvider sOwnerUrlProvider = new OwnerCloudGroupUrlProvider();
    }

    private static class SharerCloudGroupUrlProvider extends CloudGroupUrlProvider {
        private SharerCloudGroupUrlProvider() {
        }

        public String getEditGroupUrl(String str, String str2) {
            throw new UnsupportedOperationException("sharer album doesn't support to be edited!");
        }

        public String getThumbnailInfoUrl(String str, String str2) {
            return HostManager.ShareAlbum.getThumbnailInfoUrl();
        }
    }

    private static class SharerInstanceHolder {
        /* access modifiers changed from: private */
        public static final CloudGroupUrlProvider sSharerUrlProvider = new SharerCloudGroupUrlProvider();
    }

    public static CloudGroupUrlProvider getUrlProvider(boolean z) {
        return z ? SharerInstanceHolder.sSharerUrlProvider : OwnerInstanceHolder.sOwnerUrlProvider;
    }

    public abstract String getEditGroupUrl(String str, String str2);

    public abstract String getThumbnailInfoUrl(String str, String str2);
}
