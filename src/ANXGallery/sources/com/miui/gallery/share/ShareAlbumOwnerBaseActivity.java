package com.miui.gallery.share;

import android.content.DialogInterface;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.google.common.collect.Lists;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.ShareAlbumBaseActivity;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ShareAlbumOwnerBaseActivity extends ShareAlbumBaseActivity {
    protected Future<?> mFutureCreateItem;
    private Future<?> mFutureSms;
    /* access modifiers changed from: private */
    public CloudObserver mObserver = new CloudObserver(ThreadManager.getMainHandler());

    private class CloudObserver extends ContentObserver {
        public CloudObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            ShareAlbumOwnerBaseActivity.this.mCloudSingleMediaSet.rereloadItem();
            ShareAlbumOwnerBaseActivity.this.updateStatus();
        }
    }

    /* access modifiers changed from: private */
    public void addSharer(final int i, CloudUserCacheEntry cloudUserCacheEntry) {
        requestInvitation(cloudUserCacheEntry, new AlbumShareUIManager.OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation>() {
            /* JADX WARNING: type inference failed for: r2v1, types: [android.content.Context, com.miui.gallery.share.ShareAlbumOwnerBaseActivity] */
            /* JADX WARNING: type inference failed for: r4v1, types: [com.miui.gallery.share.ShareAlbumOwnerBaseActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.miui.gallery.share.ShareAlbumOwnerBaseActivity] */
            public void onCompletion(Void voidR, AlbumShareOperations.OutgoingInvitation outgoingInvitation, int i, boolean z) {
                if (z) {
                    ? r2 = ShareAlbumOwnerBaseActivity.this;
                    UIHelper.toast(r2, r2.getString(R.string.cancel_hint));
                } else if (i != 0 || outgoingInvitation == null) {
                    ? r22 = ShareAlbumOwnerBaseActivity.this;
                    UIHelper.toast(r22, r22.getString(R.string.fail_hint));
                } else {
                    PhoneShareAlbumProvider instance = PhoneShareAlbumProvider.getInstance();
                    ? r4 = ShareAlbumOwnerBaseActivity.this;
                    instance.shareOperation(r4, i, outgoingInvitation, r4.mAlbumName);
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.miui.gallery.share.ShareAlbumOwnerBaseActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void kickUser(String str) {
        AnonymousClass5 r0 = new AlbumShareUIManager.OnCompletionListener<Void, Pair<List<String>, List<String>>>() {
            public void onCompletion(Void voidR, Pair<List<String>, List<String>> pair, int i, boolean z) {
                if (z) {
                    UIHelper.toast((int) R.string.cancel_hint);
                } else if (i != 0 || pair == null || ((List) pair.first).isEmpty() || !((List) pair.second).isEmpty()) {
                    UIHelper.toast((int) R.string.kick_sharer_failed);
                } else {
                    UIHelper.toast((int) R.string.kick_sharer_successfully);
                }
            }
        };
        AlbumShareUIManager.BlockMessage create = AlbumShareUIManager.BlockMessage.create(this, (CharSequence) null, getString(R.string.kick_sharer), false);
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.add(str);
        AlbumShareUIManager.kickSharersAsync(this.mAlbumId, newArrayList, r0, create);
    }

    /* access modifiers changed from: private */
    public void tryToCreateCloudItem() {
        if (!CloudUtils.isValidAlbumId(this.mCloudSingleMediaSet.getAlbumId())) {
            this.mFutureCreateItem = AlbumShareUIManager.tryToCreateCloudAlbumAsync(String.valueOf(this.mCloudSingleMediaSet.getId()), new AlbumShareUIManager.OnCompletionListener<Void, String>() {
                public void onCompletion(Void voidR, String str, int i, boolean z) {
                    ShareAlbumOwnerBaseActivity.this.mObserver.onChange(true);
                    AlbumShareUIManager.doAfterCloudMediaSetSetReload(new Runnable() {
                        public void run() {
                            ShareAlbumOwnerBaseActivity.this.updateStatus();
                        }
                    }, ShareAlbumOwnerBaseActivity.this.mCloudSingleMediaSet.getPath(), true);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void doSyncFromServer(AlbumShareUIManager.OnCompletionListener<Void, Void> onCompletionListener) {
        if (CloudUtils.isValidAlbumId(this.mAlbumId)) {
            getUserCache().syncFromServer(this.mAlbumId, onCompletionListener);
        }
    }

    /* access modifiers changed from: protected */
    public List<CloudUserCacheEntry> getShareUsers() {
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.addAll(getUserCache().getCloudUserListByAlbumId(this.mAlbumId));
        Collections.sort(newArrayList, new ShareAlbumBaseActivity.UserCacheDescComparator());
        return newArrayList;
    }

    /* access modifiers changed from: protected */
    public CloudUserCache getUserCache() {
        return CloudUserCache.getOwnerUserCache();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isFinishing()) {
            findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShareAlbumOwnerBaseActivity.this.tryToCreateCloudItem();
                    ShareAlbumOwnerBaseActivity.this.updateStatus();
                }
            });
            tryToCreateCloudItem();
            updateStatus();
            GalleryApp.sGetAndroidContext().getContentResolver().registerContentObserver(GalleryCloudUtils.CLOUD_URI, true, this.mObserver);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Future<?> future = this.mFutureCreateItem;
        if (future != null) {
            future.cancel();
        }
        Future<?> future2 = this.mFutureSms;
        if (future2 != null) {
            future2.cancel();
        }
        super.onDestroy();
    }

    public void onItemClick(GridPreferenceBase gridPreferenceBase, int i) {
        if (this.mShareUserGridAdapter != null && i < this.mShareUserGridAdapter.getCount()) {
            CloudUserCacheEntry item = this.mShareUserGridAdapter.getItem(i);
            if (item == null || (TextUtils.isEmpty(item.mUserId) && TextUtils.isEmpty(item.mServerStatus))) {
                showAddSharerDialog(item);
            } else if (!TextUtils.equals(item.mServerStatus, "normal")) {
            } else {
                if (TextUtils.equals(this.mCreatorId, item.mUserId)) {
                    UIHelper.toast((int) R.string.cannot_remove_creator);
                } else {
                    showKickUserDialog(item);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void requestInvitation(CloudUserCacheEntry cloudUserCacheEntry, AlbumShareUIManager.OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation> onCompletionListener) {
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [com.miui.gallery.share.ShareAlbumOwnerBaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void requestInvitation(String str, String str2, String str3, String str4, AlbumShareUIManager.OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation> onCompletionListener) {
        this.mFutureSms = AlbumShareUIManager.requestInvitationForSmsAsync(this.mAlbumId, str, str2, str3, str4, onCompletionListener, AlbumShareUIManager.BlockMessage.create(this, getString(R.string.request_sms_url_title), getString(R.string.request_sms_url_msg)));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.share.ShareAlbumOwnerBaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void showAddSharerDialog(final CloudUserCacheEntry cloudUserCacheEntry) {
        int addSharerDialogItemsLength = PhoneShareAlbumProvider.getInstance().getAddSharerDialogItemsLength();
        if (addSharerDialogItemsLength >= 1) {
            if (addSharerDialogItemsLength == 1) {
                addSharer(0, cloudUserCacheEntry);
            } else {
                PhoneShareAlbumProvider.getInstance().showAddSharerDialog(this, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ShareAlbumOwnerBaseActivity.this.addSharer(i, cloudUserCacheEntry);
                        dialogInterface.dismiss();
                    }
                });
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.share.ShareAlbumOwnerBaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void showKickUserDialog(final CloudUserCacheEntry cloudUserCacheEntry) {
        if (cloudUserCacheEntry != null) {
            PhoneShareAlbumProvider.getInstance().showKickUserDialog(this, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ShareAlbumOwnerBaseActivity.this.kickUser(cloudUserCacheEntry.mUserId);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void updateSharers() {
        CloudUtils.isValidAlbumId(this.mAlbumId);
        super.updateSharers();
    }

    /* access modifiers changed from: protected */
    public void updateStatus() {
        if (!isFinishing()) {
            this.mAlbumId = this.mCloudSingleMediaSet.getAlbumId();
            if (CloudUtils.isValidAlbumId(this.mAlbumId)) {
                findViewById(16908298).setVisibility(0);
                findViewById(R.id.status_wrapper).setVisibility(8);
                updateSharers();
                syncFromServer();
                return;
            }
            findViewById(16908298).setVisibility(8);
            findViewById(R.id.status_wrapper).setVisibility(0);
            Future<?> future = this.mFutureCreateItem;
            if (future == null || future.isDone()) {
                findViewById(R.id.loading_bar).setVisibility(8);
                ((TextView) findViewById(R.id.loading_text)).setText(R.string.fail_to_sync_album);
                findViewById(R.id.retry_button).setVisibility(0);
                return;
            }
            findViewById(R.id.loading_bar).setVisibility(0);
            ((TextView) findViewById(R.id.loading_text)).setText(R.string.sync_album_in_proecess);
            findViewById(R.id.retry_button).setVisibility(8);
        }
    }
}
