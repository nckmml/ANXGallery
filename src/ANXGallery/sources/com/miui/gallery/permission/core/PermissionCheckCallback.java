package com.miui.gallery.permission.core;

public interface PermissionCheckCallback {
    RuntimePermission[] getRuntimePermissions();

    void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr);
}
