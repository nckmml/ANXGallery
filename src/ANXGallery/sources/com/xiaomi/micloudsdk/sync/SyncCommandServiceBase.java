package com.xiaomi.micloudsdk.sync;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

@Deprecated
public abstract class SyncCommandServiceBase extends IntentService {
    public SyncCommandServiceBase() {
        super("SyncCommandServiceBase");
    }

    public abstract void handleCommand(String str);

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent == null || !"com.xiaomi.micloud.action.SYNC_COMMAND".equals(intent.getAction())) {
            Log.e("SyncCommandServiceBase", "illegal arguments");
        } else {
            handleCommand(intent.getStringExtra("key_command"));
        }
    }
}
