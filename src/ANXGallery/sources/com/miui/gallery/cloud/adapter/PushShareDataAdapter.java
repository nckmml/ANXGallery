package com.miui.gallery.cloud.adapter;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import com.miui.gallery.cloud.SyncShareAlbumFromLocal;
import com.miui.gallery.cloud.SyncSharerFromLocal;
import com.miui.gallery.cloud.SyncSharerSubUbiFromLocal;
import com.miui.gallery.cloud.base.AbstractSyncAdapter;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;

public class PushShareDataAdapter extends AbstractSyncAdapter {
    public PushShareDataAdapter(Context context) {
        super(context);
    }

    public long getBindingReason() {
        return 512;
    }

    public boolean isAsynchronous() {
        return true;
    }

    /* access modifiers changed from: protected */
    public GallerySyncResult onPerformSync(Account account, Bundle bundle, GalleryExtendedAuthToken galleryExtendedAuthToken) throws Exception {
        if (!ApplicationHelper.supportShare()) {
            SyncLog.w(this.TAG, "the feature of share album isn't enabled");
            return new GallerySyncResult.Builder().setCode(GallerySyncCode.OK).build();
        }
        new SyncShareAlbumFromLocal(getContext(), account, galleryExtendedAuthToken).sync();
        new SyncSharerFromLocal(getContext(), account, galleryExtendedAuthToken).sync();
        new SyncSharerSubUbiFromLocal(getContext(), account, galleryExtendedAuthToken).sync();
        return new GallerySyncResult.Builder().setCode(GallerySyncCode.OK).build();
    }
}
