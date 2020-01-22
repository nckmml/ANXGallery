package com.miui.gallery.share;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.util.FeatureUtil;

public abstract class PublicShareUiHandler {
    protected ShareAlbumBaseActivity mActivity;
    protected String mAlbumId;
    private String mAlbumName;
    protected CloudSharerMediaSet mCloudSingleMediaSet;
    protected Future<?> mFuturePublic;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            PublicShareUiHandler.this.updateProgressBarDirectly(message.arg1);
        }
    };
    protected PreferenceCategory mPublicPreference;
    private DescriptPreference mPublicUrlDescriptPreference;
    private PreferenceScreen mSendPublicUrlPreference;
    protected PreferenceCategory mSharersPreference;

    public PublicShareUiHandler(ShareAlbumBaseActivity shareAlbumBaseActivity, String str, CloudSharerMediaSet cloudSharerMediaSet) {
        this.mActivity = shareAlbumBaseActivity;
        this.mAlbumName = str;
        this.mCloudSingleMediaSet = cloudSharerMediaSet;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.share.ShareAlbumBaseActivity, android.app.Activity] */
    private void sendPublicPreference(String str) {
        this.mSendPublicUrlPreference.setIntent(PhoneShareAlbumProvider.getInstance().getShareUrlIntent(this.mActivity, this.mAlbumName, "", str, true));
        this.mPublicPreference.addPreference(this.mSendPublicUrlPreference);
    }

    /* access modifiers changed from: private */
    public void updateProgressBarDirectly(int i) {
        if (i == 1) {
            this.mSharersPreference.setSummary(R.string.loading_members);
        } else {
            this.mSharersPreference.setSummary((CharSequence) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void initPreferences() {
        this.mSharersPreference = (PreferenceCategory) this.mActivity.findPreference("sharers");
        this.mSharersPreference.setTitle(this.mActivity.getString(R.string.album_member, new Object[]{""}));
        this.mPublicPreference = (PreferenceCategory) this.mActivity.findPreference("public_share");
        this.mSendPublicUrlPreference = (PreferenceScreen) this.mActivity.findPreference("send_public_url");
    }

    public void onDestroy() {
        Future<?> future = this.mFuturePublic;
        if (future != null) {
            future.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Future<?> requestPublicUrl(String str, AlbumShareUIManager.OnCompletionListener<Void, String> onCompletionListener);

    /* access modifiers changed from: package-private */
    public void updateProgressBar(int i) {
        this.mHandler.removeMessages(1);
        if (i != 1) {
            this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 1, i, 0), 2000);
            return;
        }
        updateProgressBarDirectly(i);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, com.miui.gallery.share.ShareAlbumBaseActivity] */
    /* access modifiers changed from: protected */
    public void updatePublicPreference(boolean z, final String str) {
        if (!FeatureUtil.isSupportPublicShareAlbum()) {
            this.mActivity.getPreferenceScreen().removePreference(this.mPublicPreference);
        } else if (z) {
            if (this.mSendPublicUrlPreference != null) {
                sendPublicPreference(str);
            }
            if (this.mPublicUrlDescriptPreference == null) {
                this.mPublicUrlDescriptPreference = new DescriptPreference(this.mActivity, (AttributeSet) null);
                this.mPublicUrlDescriptPreference.setKey("public_url_descript");
                this.mPublicUrlDescriptPreference.setDescriptTitle((int) R.string.public_url_descript);
            }
            if (!TextUtils.isEmpty(str)) {
                this.mPublicUrlDescriptPreference.setDescriptDetail(str);
                this.mPublicUrlDescriptPreference.setDescriptDetailVisibility(0);
                this.mPublicUrlDescriptPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(str));
                        try {
                            PublicShareUiHandler.this.mActivity.startActivity(intent);
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            return false;
                        }
                    }
                });
            }
            if (this.mPublicPreference.findPreference("public_url_descript") == null) {
                this.mPublicPreference.addPreference(this.mPublicUrlDescriptPreference);
            }
        } else {
            PreferenceScreen preferenceScreen = this.mSendPublicUrlPreference;
            if (preferenceScreen != null) {
                this.mPublicPreference.removePreference(preferenceScreen);
            }
            DescriptPreference descriptPreference = this.mPublicUrlDescriptPreference;
            if (descriptPreference != null) {
                this.mPublicPreference.removePreference(descriptPreference);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateSharers(int i) {
        if (!CloudUtils.isValidAlbumId(this.mAlbumId)) {
            return;
        }
        if (i > 0) {
            PreferenceCategory preferenceCategory = this.mSharersPreference;
            ShareAlbumBaseActivity shareAlbumBaseActivity = this.mActivity;
            preferenceCategory.setTitle(shareAlbumBaseActivity.getString(R.string.album_member, new Object[]{"(" + i + ")"}));
            return;
        }
        this.mSharersPreference.setTitle(this.mActivity.getString(R.string.album_member, new Object[]{""}));
    }
}
