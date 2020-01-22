package com.xiaomi.push.mpcd.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.push.mpcd.IntentHandler;

public class BroadcastActionsReceiver extends BroadcastReceiver {
    private IntentHandler mHandler;

    public BroadcastActionsReceiver(IntentHandler intentHandler) {
        this.mHandler = intentHandler;
    }

    public void onReceive(Context context, Intent intent) {
        IntentHandler intentHandler = this.mHandler;
        if (intentHandler != null) {
            intentHandler.handle(context, intent);
        }
    }
}
