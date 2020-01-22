package com.miui.gallery.share;

import android.content.Context;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.lib.MiuiGalleryUtils;
import com.miui.gallery.share.AlbumShareInvitationActivityBase;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;

public class AlbumShareInvitationAcceptActivity extends AlbumShareInvitationActivityBase {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!ApplicationHelper.supportShare()) {
            ToastUtils.makeText((Context) this, (int) R.string.error_share_not_support);
            finish();
            return;
        }
        if (bundle == null && getIntent() != null) {
            bundle = getIntent().getExtras();
        }
        if (bundle != null && bundle.containsKey("invitation-url")) {
            String string = bundle.getString("invitation-url");
            if (MiuiGalleryUtils.isAlbumShareInvitationUrl(string)) {
                AlbumShareInvitationHandler.acceptShareInvitation(this, string, 16, AlbumShareUIManager.BlockMessage.create(this, (CharSequence) null, getString(R.string.get_album_info_in_process), true, new AlbumShareInvitationActivityBase.OnBlockMessageCancelled(this)));
                return;
            }
        }
        ToastUtils.makeText((Context) this, (int) R.string.invalid_invition_url);
        finish();
    }
}
