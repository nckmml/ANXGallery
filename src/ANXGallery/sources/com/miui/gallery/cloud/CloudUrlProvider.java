package com.miui.gallery.cloud;

import com.miui.gallery.cloud.HostManager;

public abstract class CloudUrlProvider {
    private static final CloudUrlProvider sOwnerImage = new OwnerImageUrlProvider();
    private static final CloudUrlProvider sOwnerVideo = new OwnerVideoUrlProvider();
    private static final CloudUrlProvider sSharerImage = new SharerImageUrlProvider();
    private static final CloudUrlProvider sSharerVideo = new SharerVideoUrlProvider();

    private static class OwnerImageUrlProvider extends CloudUrlProvider {
        private OwnerImageUrlProvider() {
        }

        public String getCommitSubUbiUrl(String str, String str2, int i) {
            return HostManager.OwnerImage.getCommitSubUbiUrl(str2, i);
        }

        public String getCommitUrl(String str, String str2) {
            return HostManager.OwnerImage.getCommitUrl(str2);
        }

        public String getCopyUrl(String str, String str2) {
            return HostManager.OwnerImage.getCopyUrl(str2);
        }

        public String getCreateSubUbiUrl(String str, String str2, int i) {
            return HostManager.OwnerImage.getCreateSubUbiUrl(str2, i);
        }

        public String getCreateUrl(String str, String str2) {
            return HostManager.OwnerImage.getCreateUrl();
        }

        public String getDeleteUrl(String str, String str2) {
            return HostManager.OwnerImage.getDeleteUrl(str2);
        }

        public String getEditUrl(String str, String str2) {
            return HostManager.OwnerImage.getEditUrl(str2);
        }

        public String getHideCopyUrl(String str, String str2) {
            return HostManager.OwnerImage.getHideCopyUrl(str2);
        }

        public String getHideMoveUrl(String str, String str2) {
            return HostManager.OwnerImage.getHideMoveUrl(str2);
        }

        public String getMoveUrl(String str, String str2) {
            return HostManager.OwnerImage.getMoveUrl(str2);
        }

        public String getRequestDownloadUrl(String str, String str2) {
            return HostManager.OwnerImage.getDownloadUrl(str2);
        }

        public String getUnHideMoveUrl(String str, String str2) {
            return HostManager.OwnerImage.getUnHideMoveUrl(str2);
        }

        public String getUnhideCopyUrl(String str, String str2) {
            return HostManager.OwnerImage.getUnHideCopyUrl(str2);
        }

        public String getUpdateUrl(String str, String str2) {
            return HostManager.OwnerImage.getUpdateUrl(str2);
        }
    }

    private static class OwnerVideoUrlProvider extends CloudUrlProvider {
        private OwnerVideoUrlProvider() {
        }

        public String getCommitSubUbiUrl(String str, String str2, int i) {
            return null;
        }

        public String getCommitUrl(String str, String str2) {
            return HostManager.OwnerVideo.getCommitUrl(str2);
        }

        public String getCopyUrl(String str, String str2) {
            return HostManager.OwnerVideo.getCopyUrl(str2);
        }

        public String getCreateSubUbiUrl(String str, String str2, int i) {
            return null;
        }

        public String getCreateUrl(String str, String str2) {
            return HostManager.OwnerVideo.getCreateUrl();
        }

        public String getDeleteUrl(String str, String str2) {
            return HostManager.OwnerVideo.getDeleteUrl(str2);
        }

        public String getEditUrl(String str, String str2) {
            return null;
        }

        public String getHideCopyUrl(String str, String str2) {
            return HostManager.OwnerVideo.getHideCopyUrl(str2);
        }

        public String getHideMoveUrl(String str, String str2) {
            return HostManager.OwnerVideo.getHideMoveUrl(str2);
        }

        public String getMoveUrl(String str, String str2) {
            return HostManager.OwnerVideo.getMoveUrl(str2);
        }

        public String getRequestDownloadUrl(String str, String str2) {
            return HostManager.OwnerVideo.getDownloadUrl(str2);
        }

        public String getUnHideMoveUrl(String str, String str2) {
            return HostManager.OwnerVideo.getUnHideMoveUrl(str2);
        }

        public String getUnhideCopyUrl(String str, String str2) {
            return HostManager.OwnerVideo.getUnHideCopyUrl(str2);
        }

        public String getUpdateUrl(String str, String str2) {
            return HostManager.OwnerVideo.getUpdateUrl(str2);
        }
    }

    private static class SharerImageUrlProvider extends CloudUrlProvider {
        private SharerImageUrlProvider() {
        }

        public String getCommitSubUbiUrl(String str, String str2, int i) {
            return HostManager.ShareImage.getCommitSubUbiUrl(i);
        }

        public String getCommitUrl(String str, String str2) {
            return HostManager.ShareImage.getCommitUrl();
        }

        public String getCopyUrl(String str, String str2) {
            return HostManager.ShareImage.getCopyUrl();
        }

        public String getCreateSubUbiUrl(String str, String str2, int i) {
            return HostManager.ShareImage.getCreateSubUbiUrl(i);
        }

        public String getCreateUrl(String str, String str2) {
            return HostManager.ShareImage.getCreateUrl();
        }

        public String getDeleteUrl(String str, String str2) {
            return HostManager.ShareImage.getDeleteUrl();
        }

        public String getEditUrl(String str, String str2) {
            return null;
        }

        public String getHideCopyUrl(String str, String str2) {
            return null;
        }

        public String getHideMoveUrl(String str, String str2) {
            return null;
        }

        public String getMoveUrl(String str, String str2) {
            return HostManager.ShareImage.getMoveUrl(str2);
        }

        public String getRequestDownloadUrl(String str, String str2) {
            return HostManager.ShareImage.getDownloadUrl();
        }

        public String getUnHideMoveUrl(String str, String str2) {
            return null;
        }

        public String getUnhideCopyUrl(String str, String str2) {
            return null;
        }

        public String getUpdateUrl(String str, String str2) {
            return null;
        }
    }

    private static class SharerVideoUrlProvider extends CloudUrlProvider {
        private SharerVideoUrlProvider() {
        }

        public String getCommitSubUbiUrl(String str, String str2, int i) {
            return null;
        }

        public String getCommitUrl(String str, String str2) {
            return HostManager.ShareVideo.getCommitUrl();
        }

        public String getCopyUrl(String str, String str2) {
            return HostManager.ShareVideo.getCopyUrl();
        }

        public String getCreateSubUbiUrl(String str, String str2, int i) {
            return null;
        }

        public String getCreateUrl(String str, String str2) {
            return HostManager.ShareVideo.getCreateUrl();
        }

        public String getDeleteUrl(String str, String str2) {
            return HostManager.ShareVideo.getDeleteUrl();
        }

        public String getEditUrl(String str, String str2) {
            return null;
        }

        public String getHideCopyUrl(String str, String str2) {
            return null;
        }

        public String getHideMoveUrl(String str, String str2) {
            return null;
        }

        public String getMoveUrl(String str, String str2) {
            return HostManager.ShareVideo.getMoveUrl(str2);
        }

        public String getRequestDownloadUrl(String str, String str2) {
            return HostManager.ShareVideo.getDownloadUrl();
        }

        public String getUnHideMoveUrl(String str, String str2) {
            return null;
        }

        public String getUnhideCopyUrl(String str, String str2) {
            return null;
        }

        public String getUpdateUrl(String str, String str2) {
            return null;
        }
    }

    public static CloudUrlProvider getUrlProvider(boolean z, boolean z2) {
        return z ? z2 ? sSharerVideo : sSharerImage : z2 ? sOwnerVideo : sOwnerImage;
    }

    public abstract String getCommitSubUbiUrl(String str, String str2, int i);

    public abstract String getCommitUrl(String str, String str2);

    public abstract String getCopyUrl(String str, String str2);

    public abstract String getCreateSubUbiUrl(String str, String str2, int i);

    public abstract String getCreateUrl(String str, String str2);

    public abstract String getDeleteUrl(String str, String str2);

    public abstract String getEditUrl(String str, String str2);

    public abstract String getHideCopyUrl(String str, String str2);

    public abstract String getHideMoveUrl(String str, String str2);

    public abstract String getMoveUrl(String str, String str2);

    public abstract String getRequestDownloadUrl(String str, String str2);

    public abstract String getUnHideMoveUrl(String str, String str2);

    public abstract String getUnhideCopyUrl(String str, String str2);

    public abstract String getUpdateUrl(String str, String str2);
}
