package com.miui.gallery.share;

import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.QRPreference;
import com.miui.gallery.threadpool.Future;
import java.util.List;

public class NormalShareAlbumOwnerActivity extends ShareAlbumOwnerBaseActivity {
    private Future<?> mFutureQR;
    private OwnerPublicShareUiHandler mPublicShareUiHandler;
    /* access modifiers changed from: private */
    public QRPreference mQRCodePreference;

    /* access modifiers changed from: private */
    public void tryToRequestBarcodeUrl() {
        if (this.mQRCodePreference.getQRText() == null) {
            Future<?> future = this.mFutureQR;
            if (future == null || future.isDone() || this.mFutureQR.isCancelled()) {
                this.mQRCodePreference.setStatus(QRPreference.Status.REQUESTING);
                this.mFutureQR = AlbumShareUIManager.requestUrlForBarcodeAsync(this.mAlbumId, new AlbumShareUIManager.OnCompletionListener<Void, String>() {
                    public void onCompletion(Void voidR, String str, int i, boolean z) {
                        if (!z) {
                            if (i != 0 || str == null) {
                                NormalShareAlbumOwnerActivity.this.mQRCodePreference.setStatus(QRPreference.Status.FAILED);
                                return;
                            }
                            NormalShareAlbumOwnerActivity.this.mQRCodePreference.setQRText(str);
                            NormalShareAlbumOwnerActivity.this.mQRCodePreference.setStatus(QRPreference.Status.SUCCESS);
                        }
                    }
                });
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.share.NormalShareAlbumOwnerActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public ShareUserAdapterBase createShareUserAdapter() {
        return new ShareUserAdapter(this, true, GalleryCloudUtils.getAccountName());
    }

    /* access modifiers changed from: protected */
    public int getContentView() {
        return R.layout.share_album_owner;
    }

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "album_normal_share_owner_info";
    }

    /* access modifiers changed from: protected */
    public int getPreferencesResourceId() {
        return R.xml.share_album_owner;
    }

    /* access modifiers changed from: protected */
    public List<CloudUserCacheEntry> getShareUsers() {
        List<CloudUserCacheEntry> shareUsers = super.getShareUsers();
        shareUsers.add(0, getOwnerEntry(GalleryCloudUtils.getAccountName()));
        return shareUsers;
    }

    /* access modifiers changed from: protected */
    public void initPreferences() {
        super.initPreferences();
        this.mPublicShareUiHandler = new OwnerPublicShareUiHandler(this, this.mAlbumName, this.mCloudSingleMediaSet);
        this.mPublicShareUiHandler.initPreferences();
        this.mQRCodePreference = (QRPreference) findPreference("qr_code");
        this.mQRCodePreference.setRetryListener(new View.OnClickListener() {
            public void onClick(View view) {
                NormalShareAlbumOwnerActivity.this.tryToRequestBarcodeUrl();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        OwnerPublicShareUiHandler ownerPublicShareUiHandler = this.mPublicShareUiHandler;
        if (ownerPublicShareUiHandler != null) {
            ownerPublicShareUiHandler.onDestroy();
        }
        Future<?> future = this.mFutureQR;
        if (future != null) {
            future.cancel();
        }
        super.onDestroy();
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (this.mPublicShareUiHandler.onPreferenceTreeClick(preferenceScreen, preference)) {
            return true;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    /* access modifiers changed from: protected */
    public void onUpdateProgressChanged(int i) {
        this.mPublicShareUiHandler.updateProgressBar(i);
    }

    /* access modifiers changed from: protected */
    public void requestInvitation(CloudUserCacheEntry cloudUserCacheEntry, AlbumShareUIManager.OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation> onCompletionListener) {
        requestInvitation((String) null, (String) null, (String) null, (String) null, onCompletionListener);
    }

    /* access modifiers changed from: protected */
    public void updateSharers() {
        super.updateSharers();
        this.mPublicShareUiHandler.updateSharers(this.mSharerCount);
    }

    /* access modifiers changed from: protected */
    public void updateStatus() {
        if (!isFinishing()) {
            super.updateStatus();
            if (CloudUtils.isValidAlbumId(this.mAlbumId)) {
                tryToRequestBarcodeUrl();
            }
            this.mPublicShareUiHandler.updateStatus();
        }
    }
}
