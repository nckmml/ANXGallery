package com.miui.gallery.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.agreement.cta.CtaAgreement;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.request.HostManager;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import java.util.LinkedList;
import java.util.List;

public class SettingsSearchProvider extends ContentProvider {

    private class Builder {
        String intentAction;
        String intentTargetPackage;
        String keywords;
        String title;
        String uriString;

        private Builder() {
            this.title = "";
            this.keywords = "";
            this.intentAction = "miui.intent.action.APP_SETTINGS";
            this.intentTargetPackage = "com.miui.gallery";
            this.uriString = "";
        }

        public RawData build() {
            return new RawData(this.title, this.keywords, this.intentAction, this.intentTargetPackage, this.uriString);
        }

        public Builder setIntentAction(String str) {
            this.intentAction = str;
            return this;
        }

        public Builder setIntentTargetPackage(String str) {
            this.intentTargetPackage = str;
            return this;
        }

        public Builder setKeywords(String str) {
            this.keywords = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setUriString(String str) {
            this.uriString = str;
            return this;
        }
    }

    class RawData {
        String intentAction;
        String intentTargetPackage;
        String keywords;
        String title;
        String uriString;

        public RawData(String str, String str2, String str3, String str4, String str5) {
            this.title = str;
            this.keywords = str2;
            this.intentAction = str3;
            this.intentTargetPackage = str4;
            this.uriString = str5;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public List<RawData> prepareData() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.backup_automatically_title)).setKeywords(TextUtils.join(";", getContext().getResources().getStringArray(R.array.backup_automatically_key_words))).build());
        if (AIAlbumStatusHelper.useNewAIAlbumSwitch()) {
            linkedList.add(new Builder().setTitle(getContext().getString(R.string.ai_album_setting_title)).setKeywords(getContext().getString(R.string.ai_album_setting_key_word)).build());
        }
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.auto_download_media_title)).build());
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.download_type_title)).setKeywords(TextUtils.join(";", new String[]{getContext().getResources().getString(R.string.download_type_origin), getContext().getResources().getString(R.string.download_type_thumbnail)})).build());
        if (ImageFeatureManager.isImageFeatureCalculationEnable()) {
            linkedList.add(new Builder().setTitle(getContext().getString(R.string.image_selection_title)).build());
            if (ImageFeatureManager.isDeviceSupportStoryFunction()) {
                linkedList.add(new Builder().setTitle(getContext().getString(R.string.generate_story_title)).build());
            }
        }
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.album_show_local_only)).build());
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.show_hidden_album_title)).build());
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.slideshow_interval_title)).build());
        if (ApplicationHelper.isCloudTrashBinFeatureOpen()) {
            linkedList.add(new Builder().setTitle(getContext().getString(R.string.trash_bin)).setKeywords(getContext().getString(R.string.trash_bin_key_word)).setIntentAction("android.intent.action.VIEW").setUriString(HostManager.getTrashBinUrl()).build());
        }
        linkedList.add(new Builder().setTitle(getContext().getString(R.string.user_agreement4)).setIntentAction("android.intent.action.VIEW").setIntentTargetPackage("").setUriString(CtaAgreement.Licence.getPrivacyUrl()).build());
        return linkedList;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(SettingsSearchContract.SEARCH_RESULT_COLUMNS);
        for (RawData next : prepareData()) {
            matrixCursor.newRow().add("title", next.title).add("keywords", next.keywords).add("intentAction", next.intentAction).add("intentTargetPackage", next.intentTargetPackage).add("uriString", next.uriString);
        }
        return matrixCursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
