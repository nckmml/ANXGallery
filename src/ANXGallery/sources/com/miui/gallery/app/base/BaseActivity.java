package com.miui.gallery.app.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.internal.WindowCompat;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.util.ReceiverUtils;

public abstract class BaseActivity<P extends IPresenter> extends BasePermissionCheckActivitity<P> {
    private BroadcastReceiver mScreenReceiver;

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private ScreenBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction()) && !BaseActivity.this.isFinishing()) {
                BaseActivity.this.finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isShowWhenLocked() {
        return getIntent().getBooleanExtra("StartActivityWhenLocked", false);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.miui.gallery.app.base.BasePermissionCheckActivitity, com.miui.gallery.app.base.BaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (supportShowOnScreenLocked() && isShowWhenLocked()) {
            WindowCompat.setShowWhenLocked(this, true);
            this.mScreenReceiver = new ScreenBroadcastReceiver();
            ReceiverUtils.registerReceiver(this, this.mScreenReceiver, "android.intent.action.SCREEN_OFF");
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.mScreenReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    /* access modifiers changed from: protected */
    public boolean supportShowOnScreenLocked() {
        return false;
    }
}
