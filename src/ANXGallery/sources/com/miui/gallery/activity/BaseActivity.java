package com.miui.gallery.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.android.internal.WindowCompat;
import com.miui.gallery.R;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.app.Activity;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.PermissionInjector;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ReceiverUtils;
import com.miui.gallery.util.TransitionPatching;
import miui.app.ActionBar;

public class BaseActivity extends Activity implements PermissionCheckCallback {
    protected ActionBar mActionBar;
    private boolean mIsResumed;
    /* access modifiers changed from: private */
    public boolean mIsStartingEnterTransition = false;
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
    public boolean allowUseOnOffline() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.activity.BaseActivity, com.miui.gallery.permission.core.PermissionCheckCallback, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void checkPermission() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("SHOW_WHEN_LOCKED", isShowWhenLocked());
        PermissionInjector.injectIfNeededIn(this, this, bundle);
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 0;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.permission_storage_desc), true)};
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void initActionBar() {
        this.mActionBar = getActionBar();
    }

    /* access modifiers changed from: protected */
    public boolean isCheckPermissionCustomized() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isShowWhenLocked() {
        return getIntent().getBooleanExtra("StartActivityWhenLocked", false);
    }

    /* access modifiers changed from: protected */
    public boolean needShowUserAgreements() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, com.miui.gallery.app.Activity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        DocumentProviderUtils.handleRequestPermissionResult(this, i, i2, intent);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context, com.miui.gallery.app.Activity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!hasCustomContentView()) {
            setContentView(R.layout.base_activity);
        }
        initActionBar();
        if (!isCheckPermissionCustomized()) {
            checkPermission();
        }
        if (supportShowOnScreenLocked() && isShowWhenLocked()) {
            WindowCompat.setShowWhenLocked(this, true);
            this.mScreenReceiver = new ScreenBroadcastReceiver();
            ReceiverUtils.registerReceiver(this, this.mScreenReceiver, "android.intent.action.SCREEN_OFF");
        }
    }

    /* access modifiers changed from: protected */
    public void onCtaChecked(boolean z) {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context, com.miui.gallery.app.Activity] */
    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.mScreenReceiver;
        if (broadcastReceiver != null) {
            ReceiverUtils.safeUnregisterReceiver(this, broadcastReceiver);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context, com.miui.gallery.app.Activity] */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if ((i != 82 && i != 187) || !supportEnterSetting()) {
            return super.onKeyDown(i, keyEvent);
        }
        IntentUtil.enterGallerySetting(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsResumed = false;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        if ((!GalleryPreferences.CTA.allowUseOnOfflineGlobal() || !allowUseOnOffline()) && !GalleryPreferences.CTA.canConnectNetwork() && needShowUserAgreements()) {
            AgreementsUtils.showUserAgreements(this, new OnAgreementInvokedListener() {
                public void onAgreementInvoked(boolean z) {
                    BaseActivity.this.onCtaChecked(z);
                    if (!z && !BaseActivity.this.allowUseOnOffline()) {
                        BaseActivity.this.finish();
                    }
                }
            });
        } else {
            onCtaChecked(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsResumed = true;
        this.mIsStartingEnterTransition = false;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, com.miui.gallery.app.Activity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.mIsStartingEnterTransition) {
            TransitionPatching.onActivityStopWhenEnterStarting(this);
            this.mIsStartingEnterTransition = false;
        }
        super.onStop();
    }

    public void setTitle(CharSequence charSequence) {
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null) {
            actionBar.setTitle(charSequence);
        }
    }

    public void startFragment(Fragment fragment, String str, boolean z, boolean z2) {
        if (getFragmentContainerId() <= 0) {
            throw new IllegalArgumentException("invalidate container id");
        } else if (TextUtils.isEmpty(str) || getFragmentManager().findFragmentByTag(str) == null) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            if (z) {
                beginTransaction.addToBackStack(str);
            }
            if (z2) {
                beginTransaction.add(getFragmentContainerId(), fragment, str);
            } else {
                beginTransaction.replace(getFragmentContainerId(), fragment, str);
            }
            beginTransaction.commitAllowingStateLoss();
        } else {
            Log.w("BaseActivity", "already has tag of fragment %s", str);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, com.miui.gallery.app.Activity, android.app.Activity] */
    public void startPostponedEnterTransition() {
        super.startPostponedEnterTransition();
        this.mIsStartingEnterTransition = true;
        TransitionPatching.setOnEnterStartedListener(this, new Runnable() {
            public void run() {
                boolean unused = BaseActivity.this.mIsStartingEnterTransition = false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean supportEnterSetting() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean supportShowOnScreenLocked() {
        return false;
    }
}
