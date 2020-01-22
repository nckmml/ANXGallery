package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;

public class GalleryResumeSyncReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SyncLog.v("GalleryResumeSyncReceiver", "Received resume sync broadcast for " + ((Account) intent.getParcelableExtra(ApplicationHelper.getIntentProvider().getExtraAccount())));
        SyncUtil.requestSync(context, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(Long.MAX_VALUE).build());
    }
}
