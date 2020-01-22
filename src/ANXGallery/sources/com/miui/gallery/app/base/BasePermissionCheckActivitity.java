package com.miui.gallery.app.base;

import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.base_optimization.mvp.view.Activity;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.GalleryPreferences;

public abstract class BasePermissionCheckActivitity<P extends IPresenter> extends Activity<P> implements PermissionCheckCallback {
    private PermissionDelegate mDelegate;

    public boolean allowUseOnOffline() {
        return true;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.permission_storage_desc), true)};
    }

    /* access modifiers changed from: protected */
    public boolean isShowWhenLocked() {
        return false;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.app.base.BasePermissionCheckActivitity, android.app.Activity, com.miui.gallery.base_optimization.mvp.view.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDelegate = new PermissionDelegate(this);
        this.mDelegate.onCreate(getRuntimePermissions(), isShowWhenLocked());
    }

    public void onCtaChecked(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mDelegate.onDestory();
        this.mDelegate = null;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.app.base.BasePermissionCheckActivitity, android.app.Activity] */
    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        if ((!GalleryPreferences.CTA.allowUseOnOfflineGlobal() || !allowUseOnOffline()) && !GalleryPreferences.CTA.canConnectNetwork()) {
            AgreementsUtils.showUserAgreements(this, new OnAgreementInvokedListener() {
                public void onAgreementInvoked(boolean z) {
                    if (!BasePermissionCheckActivitity.this.allowUseOnOffline()) {
                        BasePermissionCheckActivitity.this.finish();
                    }
                    BasePermissionCheckActivitity.this.onCtaChecked(z);
                }
            });
        } else {
            onCtaChecked(true);
        }
    }
}
