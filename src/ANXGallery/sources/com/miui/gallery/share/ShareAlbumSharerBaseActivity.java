package com.miui.gallery.share;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.google.common.collect.Lists;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.CloudShareAlbumMediator;
import com.miui.gallery.share.ShareAlbumBaseActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ShareAlbumSharerBaseActivity extends ShareAlbumBaseActivity implements View.OnClickListener, CloudShareAlbumMediator.OnShareAlbumExitedListener {
    private Handler mHandler;
    private Button mViewExitShare;

    public static void exitShare(final Activity activity, final String str, final String str2, final Runnable runnable) {
        final AnonymousClass2 r0 = new AlbumShareUIManager.OnCompletionListener<Path, Void>() {
            public void onCompletion(Path path, Void voidR, int i, boolean z) {
                if (z) {
                    UIHelper.toast((int) R.string.cancel_hint);
                } else if (i == 0) {
                    Activity activity = activity;
                    UIHelper.toast(activity, activity.getString(R.string.exit_share_successfully, new Object[]{activity.getString(R.string.quotation, new Object[]{str2})}));
                    runnable.run();
                } else {
                    Activity activity2 = activity;
                    UIHelper.toast(activity2, activity2.getString(R.string.exit_share_failed, new Object[]{activity2.getString(R.string.quotation, new Object[]{str2})}));
                }
            }
        };
        final AlbumShareUIManager.BlockMessage create = AlbumShareUIManager.BlockMessage.create(activity, (CharSequence) null, activity.getString(R.string.exit_share_in_process, new Object[]{str2}), false);
        new AlertDialog.Builder(activity).setTitle(R.string.album_share_exit_confirm).setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlbumShareUIManager.exitAlbumShareAsync(Path.fromString(str), r0, create);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
    }

    /* access modifiers changed from: protected */
    public void doSyncFromServer(AlbumShareUIManager.OnCompletionListener<Void, Void> onCompletionListener) {
        getUserCache().syncFromServer(this.mAlbumId, onCompletionListener);
    }

    /* access modifiers changed from: protected */
    public int getContentView() {
        return R.layout.share_album_sharer;
    }

    /* access modifiers changed from: protected */
    public List<CloudUserCacheEntry> getShareUsers() {
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.addAll(getUserCache().getCloudUserListByAlbumId(this.mAlbumId));
        Collections.sort(newArrayList, new ShareAlbumBaseActivity.UserCacheDescComparator());
        String str = this.mCreatorId;
        String accountName = GalleryCloudUtils.getAccountName();
        Iterator it = newArrayList.iterator();
        CloudUserCacheEntry cloudUserCacheEntry = null;
        while (it.hasNext()) {
            CloudUserCacheEntry cloudUserCacheEntry2 = (CloudUserCacheEntry) it.next();
            String str2 = cloudUserCacheEntry2.mUserId;
            if (TextUtils.equals(str2, str)) {
                it.remove();
            } else if (TextUtils.equals(str2, accountName)) {
                it.remove();
                cloudUserCacheEntry = cloudUserCacheEntry2;
            }
        }
        if (cloudUserCacheEntry != null) {
            newArrayList.add(0, cloudUserCacheEntry);
        } else {
            newArrayList.add(0, new CloudUserCacheEntry(this.mAlbumId, accountName, 0, (String) null, (String) null, (String) null, (String) null));
        }
        newArrayList.add(0, getOwnerEntry(str));
        return newArrayList;
    }

    /* access modifiers changed from: protected */
    public CloudUserCache getUserCache() {
        return CloudUserCache.getSharerUserCache();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.share.ShareAlbumSharerBaseActivity, android.app.Activity] */
    public void onClick(View view) {
        if (view.getId() == R.id.exit_share) {
            exitShare(this, this.mPath, this.mAlbumName, new Runnable() {
                public void run() {
                    ShareAlbumSharerBaseActivity.this.setResult(-1);
                    ShareAlbumSharerBaseActivity.this.finish();
                }
            });
            return;
        }
        throw new UnsupportedOperationException("Invalid view id, id=" + view.getId());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isFinishing()) {
            if (!CloudUtils.isValidAlbumId(this.mAlbumId)) {
                finish();
                return;
            }
            this.mViewExitShare = (Button) findViewById(R.id.exit_share);
            this.mViewExitShare.setOnClickListener(this);
            syncFromServer();
            this.mHandler = new Handler();
            CloudShareAlbumMediator.getInstance().addListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        CloudShareAlbumMediator.getInstance().removeListener(this);
    }
}
