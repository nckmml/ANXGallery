package com.miui.gallery.projection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.miui.gallery.util.Log;

public class RemoteControlReceiver extends BroadcastReceiver {
    private RemoteControlListener mListener;

    public interface RemoteControlListener {
        void onRemoteControl(boolean z);
    }

    public void onReceive(Context context, Intent intent) {
        KeyEvent keyEvent;
        RemoteControlListener remoteControlListener;
        Log.d("RemoteControlReceiver", "onReceive broadcast");
        if ("miui.intent.action.REMOTE_CONTROL".equals(intent.getAction()) && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
            if (25 == keyEvent.getKeyCode()) {
                RemoteControlListener remoteControlListener2 = this.mListener;
                if (remoteControlListener2 != null) {
                    remoteControlListener2.onRemoteControl(false);
                }
            } else if (24 == keyEvent.getKeyCode() && (remoteControlListener = this.mListener) != null) {
                remoteControlListener.onRemoteControl(true);
            }
        }
    }

    public void setListener(RemoteControlListener remoteControlListener) {
        this.mListener = remoteControlListener;
    }
}
