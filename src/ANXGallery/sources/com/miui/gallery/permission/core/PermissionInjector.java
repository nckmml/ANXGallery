package com.miui.gallery.permission.core;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.miui.gallery.util.Log;

public class PermissionInjector extends Fragment implements PermissionCheckCallback {
    private PermissionCheckCallback mCallback;
    private PermissionCheckHelper mHelper;

    public static void injectIfNeededIn(Activity activity, PermissionCheckCallback permissionCheckCallback, Bundle bundle) {
        if (permissionCheckCallback == null) {
            Log.d("PermissionInjector", "The callback is null");
            return;
        }
        RuntimePermission[] runtimePermissions = permissionCheckCallback.getRuntimePermissions();
        if (runtimePermissions == null || runtimePermissions.length == 0) {
            Log.d("PermissionInjector", "Has no permissions to grant");
            return;
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("com.miui.gallery.permission.injector") == null) {
            PermissionInjector permissionInjector = new PermissionInjector();
            permissionInjector.setCallback(permissionCheckCallback);
            permissionInjector.setArguments(bundle);
            fragmentManager.beginTransaction().add(permissionInjector, "com.miui.gallery.permission.injector").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private boolean isShowWhenLocked() {
        Bundle arguments = getArguments();
        return arguments != null && arguments.getBoolean("SHOW_WHEN_LOCKED", false);
    }

    public RuntimePermission[] getRuntimePermissions() {
        PermissionCheckCallback permissionCheckCallback = this.mCallback;
        return permissionCheckCallback != null ? permissionCheckCallback.getRuntimePermissions() : new RuntimePermission[0];
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHelper = new PermissionCheckHelper(this, isShowWhenLocked(), this);
        this.mHelper.checkPermission();
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        PermissionCheckCallback permissionCheckCallback = this.mCallback;
        if (permissionCheckCallback != null) {
            permissionCheckCallback.onPermissionsChecked(runtimePermissionArr, iArr);
        }
        if (getActivity() != null) {
            getActivity().getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mHelper.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* access modifiers changed from: package-private */
    public void setCallback(PermissionCheckCallback permissionCheckCallback) {
        this.mCallback = permissionCheckCallback;
    }
}
