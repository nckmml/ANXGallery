package com.miui.gallery.cloud;

import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import java.util.Locale;

public class HostManager {
    /* access modifiers changed from: private */
    public static final String BASE_ANONYMOUS_HOST = (ApplicationHelper.getMiCloudProvider().getCloudManager().getGalleryAnonymousHost() + "/mic/gallery/v3");
    /* access modifiers changed from: private */
    public static final String BASE_FACE_HOST = (ApplicationHelper.getMiCloudProvider().getCloudManager().getFaceHost() + "/mic/gallery/face/v1");
    /* access modifiers changed from: private */
    public static final String BASE_HOST = (ApplicationHelper.getMiCloudProvider().getCloudManager().getGalleryHost() + "/mic/gallery/v3");
    /* access modifiers changed from: private */
    public static final String BASE_SEARCH_ANONYMOUS_HOST = (ApplicationHelper.getMiCloudProvider().getCloudManager().getSearchAnonymousHost() + "/mic/gallery/search/v1");
    /* access modifiers changed from: private */
    public static final String BASE_SEARCH_FEEDBACK_HOST = BASE_FACE_HOST;
    /* access modifiers changed from: private */
    public static final String BASE_SEARCH_HOST = (ApplicationHelper.getMiCloudProvider().getCloudManager().getSearchHost() + "/mic/gallery/search/v1");

    public static class AlbumShareOperation {
        public static String getAcceptInvitationUrl() {
            return HostManager.BASE_HOST + "/user/share/album/shareurl/accept";
        }

        public static String getBarcodeShareUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/shareurl/barcode", new Object[]{str});
        }

        public static String getChangePublicUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/webshare", new Object[]{str});
        }

        public static String getDeleteSharerUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/sharer/delete", new Object[]{str});
        }

        public static String getExitShareUrl() {
            return HostManager.BASE_HOST + "/user/share/album/sharer/delete";
        }

        public static String getOwnerRequestPublicUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/websharelink", new Object[]{str});
        }

        public static String getRefuseInvitationUrl() {
            return HostManager.BASE_HOST + "/anonymous/share/album/shareurl/refuse";
        }

        public static String getRequestUserInfoUrl() {
            return HostManager.BASE_HOST + "/user/profile/basic";
        }

        public static String getSharerRequestPublicUrl() {
            return HostManager.BASE_HOST + "/user/share/album/websharelink";
        }

        public static String getSmsShareUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/shareurl/sms", new Object[]{str});
        }
    }

    public static class Baby {
        public static String getUpdateBabyInfoUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s", new Object[]{str});
        }
    }

    public static class CloudControl {
        public static String getAnonymousUrl() {
            return HostManager.BASE_ANONYMOUS_HOST + "/anonymous/policies";
        }

        public static String getUrl() {
            return HostManager.BASE_HOST + "/user/modules";
        }
    }

    public static class CloudPrivacy {
        public static String getCloudPrivacyUrl() {
            return String.format(Locale.US, "%s?_locale=%s_%s", new Object[]{"https://i.mi.com/gallery/intro/h5", Locale.getDefault().getLanguage(), Locale.getDefault().getCountry()});
        }
    }

    public static class OwnerAlbum {
        public static String getCreateAlbumUrl() {
            return HostManager.BASE_HOST + "/user/full/album";
        }

        public static String getDeleteAlbumUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/delete", new Object[]{str});
        }

        public static String getEditAlbumUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s", new Object[]{str});
        }

        public static String getRenameAlbumUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s", new Object[]{str});
        }

        public static String getThumbnailInfoUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/album/%s/thumbnail", new Object[]{str});
        }
    }

    public static class OwnerImage extends OwnerMedia {
        public static String getCommitSubUbiUrl(String str, int i) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s/subimage/%s/storage", new Object[]{str, Integer.valueOf(i)});
        }

        public static String getCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/copy", new Object[]{str});
        }

        public static String getCreateSubUbiUrl(String str, int i) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s/subimage/%s", new Object[]{str, Integer.valueOf(i)});
        }

        public static String getCreateUrl() {
            return HostManager.BASE_HOST + "/user/full";
        }

        public static String getDeleteUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s/delete", new Object[]{str});
        }

        public static String getDownloadUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s/storage", new Object[]{str});
        }

        public static String getEditUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s", new Object[]{str});
        }

        public static String getHideCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/hide/copy", new Object[]{str});
        }

        public static String getHideMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/hide/move", new Object[]{str});
        }

        public static String getMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/move", new Object[]{str});
        }

        public static String getUnHideCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/unhide/copy", new Object[]{str});
        }

        public static String getUnHideMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/unhide/move", new Object[]{str});
        }
    }

    public static class OwnerMedia {
        public static String getCommitUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s/storage", new Object[]{str});
        }

        public static String getRequestThumbnailUrl() {
            return HostManager.BASE_HOST + "/user/thumbnails";
        }

        public static String getUpdateUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/%s", new Object[]{str});
        }
    }

    public static class OwnerVideo extends OwnerMedia {
        public static String getCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/copy", new Object[]{str});
        }

        public static String getCreateUrl() {
            return HostManager.BASE_HOST + "/user/full/video";
        }

        public static String getDeleteUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/delete", new Object[]{str});
        }

        public static String getDownloadUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/storage", new Object[]{str});
        }

        public static String getHideCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/hide/copy", new Object[]{str});
        }

        public static String getHideMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/hide/move", new Object[]{str});
        }

        public static String getMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/move", new Object[]{str});
        }

        public static String getUnHideCopyUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/unhide/copy", new Object[]{str});
        }

        public static String getUnHideMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/unhide/move", new Object[]{str});
        }
    }

    public static class PeopleFace {
        public static String getFaceDeleteUrl(String str) {
            return HostManager.BASE_FACE_HOST + String.format(Locale.US, "/user/face/%s/delete", new Object[]{str});
        }

        public static String getFaceInfoSyncUrl() {
            return HostManager.BASE_FACE_HOST + "/user/faceinfo";
        }

        public static String getFaceMoveUrl() {
            return HostManager.BASE_FACE_HOST + "/user/face/batchmove";
        }

        public static String getPeopleCreateUrl() {
            return HostManager.BASE_FACE_HOST + "/user/people/create";
        }

        public static String getPeopleFaceSyncUrl() {
            return HostManager.BASE_FACE_HOST + "/user";
        }

        public static String getPeopleFeedBackUrl() {
            return HostManager.BASE_FACE_HOST + "/user/feedback";
        }

        public static String getPeopleIgnoreUrl(String str) {
            return HostManager.BASE_FACE_HOST + String.format(Locale.US, "/user/people/%s/ignore", new Object[]{str});
        }

        public static String getPeopleMergeUrl() {
            return HostManager.BASE_FACE_HOST + "/user/people/merge";
        }

        public static String getPeopleRecommendUrl(String str) {
            return HostManager.BASE_FACE_HOST + String.format(Locale.US, "/user/people/%s/recommend", new Object[]{str});
        }

        public static String getPeopleRecoveryUrl(String str) {
            return HostManager.BASE_FACE_HOST + String.format(Locale.US, "/user/people/%s/recovery", new Object[]{str});
        }

        public static String getPeopleRenameUrl(String str) {
            return HostManager.BASE_FACE_HOST + String.format(Locale.US, "/user/people/%s/updatename", new Object[]{str});
        }
    }

    public static class RecommendList {
        public static String getAnonymousUrl() {
            return HostManager.BASE_ANONYMOUS_HOST + "/anonymous/recommends";
        }

        public static String getUrl() {
            return HostManager.BASE_HOST + "/user/recommends";
        }
    }

    public static class Search {
        public static String getSearchAnonymousUrlHost() {
            return HostManager.BASE_SEARCH_ANONYMOUS_HOST;
        }

        public static String getSearchFeedbackUrlHost() {
            return HostManager.BASE_SEARCH_FEEDBACK_HOST;
        }

        public static String getSearchUrlHost() {
            return HostManager.BASE_SEARCH_HOST;
        }
    }

    public static class Setting {
        public static String getSyncUrl() {
            return HostManager.BASE_HOST + "/user/setting";
        }
    }

    public static class ShareAlbum {
        public static String getEditAlbumUrl() {
            return HostManager.BASE_HOST + "/user/share/album/relation";
        }

        public static String getThumbnailInfoUrl() {
            return HostManager.BASE_HOST + "/user/share/album/thumbnail";
        }
    }

    public static class ShareImage extends ShareMedia {
        public static String getCommitSubUbiUrl(int i) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/share/subimage/%s/storage", new Object[]{Integer.valueOf(i)});
        }

        public static String getCopyUrl() {
            return HostManager.BASE_HOST + "/user/share/copy";
        }

        public static String getCreateSubUbiUrl(int i) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/share/subimage/%s", new Object[]{Integer.valueOf(i)});
        }

        public static String getCreateUrl() {
            return HostManager.BASE_HOST + "/user/share/album";
        }

        public static String getDeleteUrl() {
            return HostManager.BASE_HOST + "/user/share/delete";
        }

        public static String getDownloadUrl() {
            return HostManager.BASE_HOST + "/user/share/storage";
        }

        public static String getMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/image/%s/move", new Object[]{str});
        }
    }

    public static class ShareMedia {
        public static String getCommitUrl() {
            return HostManager.BASE_HOST + "/user/share/storage";
        }

        public static String getRequestThumbnailUrl() {
            return HostManager.BASE_HOST + "/user/share/thumbnails";
        }
    }

    public static class ShareVideo extends ShareMedia {
        public static String getCopyUrl() {
            return HostManager.BASE_HOST + "/user/share/video/copy";
        }

        public static String getCreateUrl() {
            return HostManager.BASE_HOST + "/user/share/album/video";
        }

        public static String getDeleteUrl() {
            return HostManager.BASE_HOST + "/user/share/video/delete";
        }

        public static String getDownloadUrl() {
            return HostManager.BASE_HOST + "/user/share/video/storage";
        }

        public static String getMoveUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/video/%s/move", new Object[]{str});
        }
    }

    public static class Story {
        public static String getCardInfosUrl() {
            return HostManager.BASE_HOST + "/user/cardinfo";
        }

        public static String getCreateCardUrl() {
            return HostManager.BASE_HOST + "/user/cardinfo";
        }

        public static String getDeleteCardUrl() {
            return HostManager.BASE_HOST + "/user/cardinfo/delete";
        }

        public static String getOperationCardAnonymousUrl() {
            return HostManager.BASE_ANONYMOUS_HOST + "/anonymous/operationcard";
        }

        public static String getOperationCardUrl() {
            return HostManager.BASE_HOST + "/user/operationcard";
        }

        public static String getUpdateCardUrl() {
            return HostManager.BASE_HOST + "/user/cardinfo/update";
        }
    }

    public static class SyncPull {
        public static String getPullOwnerAlbumUrl() {
            return HostManager.BASE_HOST + "/user/full/album_v2";
        }

        public static String getPullOwnerAllUrl() {
            return HostManager.BASE_HOST + "/user/full";
        }

        public static String getPullOwnerPrivateUrl() {
            return HostManager.BASE_HOST + "/user/full/hide";
        }

        public static String getPullOwnerShareUserUrl(String str) {
            return HostManager.BASE_HOST + String.format(Locale.US, "/user/full/album/%s/sharer", new Object[]{str});
        }

        public static String getPullShareAlbumImage() {
            return HostManager.BASE_HOST + "/user/share/album";
        }

        public static String getPullShareAll() {
            return HostManager.BASE_HOST + "/user/share/album/changes";
        }

        public static String getPullShareUserUrl() {
            return HostManager.BASE_HOST + "/user/share/album/sharer";
        }
    }

    public static class Upgrade {
        public static String getUpgradeUrl() {
            return HostManager.BASE_HOST + "/user/upgrade";
        }
    }
}
