package com.miui.gallery.picker;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.PermissionInjector;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.Log;
import com.miui.security.CrossUserCompatActivity;
import miui.app.ActionBar;

public class PickerCompatActivity extends CrossUserCompatActivity implements PermissionCheckCallback {
    protected ActionBar mActionBar;
    private boolean mIsResumed;

    /* access modifiers changed from: protected */
    public boolean allowUseOnOffline() {
        return true;
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

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.picker.PickerCompatActivity, com.miui.gallery.permission.core.PermissionCheckCallback, com.miui.security.CrossUserCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!hasCustomContentView()) {
            setContentView(R.layout.base_activity);
        }
        initActionBar();
        PermissionInjector.injectIfNeededIn(this, this, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsResumed = false;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.picker.PickerCompatActivity, android.app.Activity] */
    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        if ((!GalleryPreferences.CTA.allowUseOnOfflineGlobal() || !allowUseOnOffline()) && !GalleryPreferences.CTA.canConnectNetwork() && !GalleryPreferences.CTA.canConnectNetwork()) {
            AgreementsUtils.showUserAgreements(this, (OnAgreementInvokedListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsResumed = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
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
            Log.w("PickerCompatActivity", "already has tag of fragment %s", str);
        }
    }
}
