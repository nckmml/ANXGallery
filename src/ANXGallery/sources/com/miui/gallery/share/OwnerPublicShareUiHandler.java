package com.miui.gallery.share;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.threadpool.Future;
import java.util.LinkedList;

public class OwnerPublicShareUiHandler extends PublicShareUiHandler {
    private static long CLICK_PUBLIC_INTERNAL = 60000;
    private static long CLICK_PUBLIC_LIMIT = 10;
    private static LinkedList<Long> sClickTimes;
    /* access modifiers changed from: private */
    public CheckBoxPreference mOpenPublicPreference;
    /* access modifiers changed from: private */
    public PublishProgressPreference mProgressPreference;

    public OwnerPublicShareUiHandler(NormalShareAlbumOwnerActivity normalShareAlbumOwnerActivity, String str, CloudSharerMediaSet cloudSharerMediaSet) {
        super(normalShareAlbumOwnerActivity, str, cloudSharerMediaSet);
    }

    private boolean canClickPublic() {
        long currentTimeMillis = System.currentTimeMillis();
        LinkedList<Long> linkedList = sClickTimes;
        if (linkedList == null) {
            sClickTimes = new LinkedList<>();
            sClickTimes.addLast(Long.valueOf(currentTimeMillis));
            return true;
        } else if (((long) linkedList.size()) < CLICK_PUBLIC_LIMIT) {
            sClickTimes.addLast(Long.valueOf(currentTimeMillis));
            return true;
        } else if (currentTimeMillis - sClickTimes.getFirst().longValue() > CLICK_PUBLIC_INTERNAL) {
            sClickTimes.removeFirst();
            sClickTimes.addLast(Long.valueOf(currentTimeMillis));
            return true;
        } else if (currentTimeMillis - sClickTimes.getFirst().longValue() <= CLICK_PUBLIC_INTERNAL && currentTimeMillis - sClickTimes.getFirst().longValue() >= 0 && ((long) sClickTimes.size()) >= CLICK_PUBLIC_LIMIT) {
            return false;
        } else {
            sClickTimes.clear();
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r2v5, types: [android.content.Context, com.miui.gallery.share.ShareAlbumBaseActivity] */
    private void tryToChangePublicStatus() {
        if (this.mFuturePublic != null && !this.mFuturePublic.isDone() && !this.mFuturePublic.isCancelled()) {
            this.mPublicPreference.removePreference(this.mProgressPreference);
            if (this.mFuturePublic != null) {
                this.mFuturePublic.cancel();
            }
        } else if (!canClickPublic()) {
            UIHelper.toast((int) R.string.operation_too_often);
            CheckBoxPreference checkBoxPreference = this.mOpenPublicPreference;
            checkBoxPreference.setChecked(true ^ checkBoxPreference.isChecked());
        } else {
            if (this.mProgressPreference == null) {
                this.mProgressPreference = new PublishProgressPreference(this.mActivity);
            }
            this.mPublicPreference.addPreference(this.mProgressPreference);
            this.mProgressPreference.setProgress(true, this.mOpenPublicPreference.isChecked());
            this.mFuturePublic = AlbumShareUIManager.changePublicStatusAsync(this.mAlbumId, this.mOpenPublicPreference.isChecked(), new AlbumShareUIManager.OnCompletionListener<Void, String>() {
                public void onCompletion(Void voidR, String str, int i, boolean z) {
                    OwnerPublicShareUiHandler.this.mPublicPreference.removePreference(OwnerPublicShareUiHandler.this.mProgressPreference);
                    if (!z) {
                        if (i == 0) {
                            OwnerPublicShareUiHandler ownerPublicShareUiHandler = OwnerPublicShareUiHandler.this;
                            ownerPublicShareUiHandler.updatePublicPreference(ownerPublicShareUiHandler.mOpenPublicPreference.isChecked(), str);
                            UIHelper.toast(OwnerPublicShareUiHandler.this.mOpenPublicPreference.isChecked() ? R.string.publish_succeeded : R.string.unpublish_succeeded);
                            OwnerPublicShareUiHandler.this.mOpenPublicPreference.setChecked(OwnerPublicShareUiHandler.this.mOpenPublicPreference.isChecked());
                            return;
                        }
                        OwnerPublicShareUiHandler ownerPublicShareUiHandler2 = OwnerPublicShareUiHandler.this;
                        ownerPublicShareUiHandler2.updatePublicPreference(!ownerPublicShareUiHandler2.mOpenPublicPreference.isChecked(), str);
                        UIHelper.toast(OwnerPublicShareUiHandler.this.mOpenPublicPreference.isChecked() ? R.string.publish_failed : R.string.unpublish_failed);
                        OwnerPublicShareUiHandler.this.mOpenPublicPreference.setChecked(!OwnerPublicShareUiHandler.this.mOpenPublicPreference.isChecked());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initPreferences() {
        super.initPreferences();
        this.mOpenPublicPreference = (CheckBoxPreference) this.mActivity.findPreference("open_public_share");
    }

    /* access modifiers changed from: package-private */
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (!"open_public_share".equals(preference.getKey())) {
            return false;
        }
        tryToChangePublicStatus();
        return true;
    }

    /* access modifiers changed from: protected */
    public final Future<?> requestPublicUrl(String str, AlbumShareUIManager.OnCompletionListener<Void, String> onCompletionListener) {
        return AlbumShareUIManager.requestPublicUrlForOwnerAsync(str, onCompletionListener);
    }

    /* access modifiers changed from: package-private */
    public void updateStatus() {
        this.mAlbumId = this.mCloudSingleMediaSet.getAlbumId();
        if (CloudUtils.isValidAlbumId(this.mAlbumId)) {
            updatePublicPreference(this.mCloudSingleMediaSet.isPublic(), this.mCloudSingleMediaSet.getPublicUrl());
            this.mOpenPublicPreference.setChecked(this.mCloudSingleMediaSet.isPublic());
            return;
        }
        updatePublicPreference(false, (String) null);
        this.mOpenPublicPreference.setChecked(false);
    }
}
