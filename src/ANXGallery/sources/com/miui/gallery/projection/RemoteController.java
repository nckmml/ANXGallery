package com.miui.gallery.projection;

import android.content.Context;
import android.provider.Settings;
import com.miui.gallery.projection.RemoteControlReceiver;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ReceiverUtils;
import java.lang.ref.WeakReference;

public class RemoteController {
    private static DisableRemoteControlRunnable sDisableRemoteControlRunnable = new DisableRemoteControlRunnable();
    private final RemoteControlReceiver.RemoteControlListener mListener;
    private RemoteControlReceiver mRemoteControlReceiver = new RemoteControlReceiver();

    private static class DisableRemoteControlRunnable implements Runnable {
        private WeakReference<Context> mContextRef;
        private WeakReference<RemoteController> mParentRef;

        private DisableRemoteControlRunnable() {
        }

        public void run() {
            WeakReference<RemoteController> weakReference = this.mParentRef;
            if (weakReference != null) {
                RemoteController remoteController = (RemoteController) weakReference.get();
                Context context = (Context) this.mContextRef.get();
                if (remoteController == null || context == null) {
                    Log.e("RemoteController", "parent is null");
                } else {
                    remoteController.disableRemoteControlWhenOnPause(context);
                }
            }
        }

        public void setParent(Context context, RemoteController remoteController) {
            this.mParentRef = new WeakReference<>(remoteController);
            this.mContextRef = new WeakReference<>(context);
        }
    }

    public RemoteController(RemoteControlReceiver.RemoteControlListener remoteControlListener) {
        this.mListener = remoteControlListener;
    }

    private void disableRemoteControl(Context context) {
        this.mRemoteControlReceiver.setListener((RemoteControlReceiver.RemoteControlListener) null);
        ReceiverUtils.safeUnregisterReceiver(context, this.mRemoteControlReceiver);
        Settings.System.putString(context.getContentResolver(), "remote_control_pkg_name", (String) null);
        Settings.System.putString(context.getContentResolver(), "remote_control_proc_name", (String) null);
    }

    /* access modifiers changed from: private */
    public void disableRemoteControlWhenOnPause(Context context) {
        if (!MiscUtil.isKeyGuardLocked(context)) {
            disableRemoteControl(context);
        }
    }

    private void enableRemoteControl(Context context) {
        ReceiverUtils.registerReceiver(context, this.mRemoteControlReceiver, "miui.intent.action.REMOTE_CONTROL");
        Settings.System.putString(context.getContentResolver(), "remote_control_pkg_name", context.getPackageName());
        Settings.System.putString(context.getContentResolver(), "remote_control_proc_name", context.getPackageName());
        this.mRemoteControlReceiver.setListener(this.mListener);
    }

    public void disable(Context context) {
        ThreadManager.getMainHandler().removeCallbacks(sDisableRemoteControlRunnable);
        disableRemoteControl(context);
    }

    public void disableDelay(Context context) {
        sDisableRemoteControlRunnable.setParent(context, this);
        ThreadManager.getMainHandler().postDelayed(sDisableRemoteControlRunnable, 2000);
    }

    public void enable(Context context) {
        ThreadManager.getMainHandler().removeCallbacks(sDisableRemoteControlRunnable);
        enableRemoteControl(context);
    }
}
