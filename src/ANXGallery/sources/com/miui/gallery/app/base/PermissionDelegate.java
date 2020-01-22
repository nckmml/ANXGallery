package com.miui.gallery.app.base;

import android.app.Activity;
import android.os.Bundle;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.PermissionInjector;
import com.miui.gallery.permission.core.RuntimePermission;
import java.lang.ref.WeakReference;

public class PermissionDelegate {
    private WeakReference<Activity> mActivityWeakReference;
    private RuntimePermission[] mUserRequestPermission;

    public PermissionDelegate(Activity activity) {
        this.mActivityWeakReference = new WeakReference<>(activity);
    }

    private void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivityWeakReference;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    public void onCreate(RuntimePermission[] runtimePermissionArr, boolean z) {
        if (!(getActivity() instanceof PermissionCheckCallback)) {
            finish();
            return;
        }
        this.mUserRequestPermission = runtimePermissionArr;
        Bundle bundle = new Bundle();
        bundle.putBoolean("SHOW_WHEN_LOCKED", z);
        PermissionInjector.injectIfNeededIn(getActivity(), (PermissionCheckCallback) getActivity(), bundle);
    }

    public void onDestory() {
        WeakReference<Activity> weakReference = this.mActivityWeakReference;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.mActivityWeakReference = null;
    }
}
