package com.miui.gallery.permission.core;

import android.app.Activity;
import android.app.Fragment;
import com.miui.gallery.permission.PermissionIntroductionUtils;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

class PermissionCheckHelper {
    private PermissionCheckCallback mCallback;
    private Fragment mHost;
    private boolean mIsShowWhenLocked;
    private HashMap<String, CheckResult> mTmpResults;

    private static class CheckResult {
        int grantResult;
        RuntimePermission permission;

        private CheckResult() {
        }
    }

    public PermissionCheckHelper(Fragment fragment, boolean z, PermissionCheckCallback permissionCheckCallback) {
        if (fragment == null || permissionCheckCallback == null) {
            throw new RuntimeException("PermissionCheckHelper: activity or callback can't be null");
        }
        this.mHost = fragment;
        this.mIsShowWhenLocked = z;
        this.mCallback = permissionCheckCallback;
        this.mTmpResults = new HashMap<>();
    }

    private void callbackResult() {
        RuntimePermission[] runtimePermissionArr = new RuntimePermission[this.mTmpResults.size()];
        int[] iArr = new int[this.mTmpResults.size()];
        int i = 0;
        for (CheckResult next : this.mTmpResults.values()) {
            runtimePermissionArr[i] = next.permission;
            iArr[i] = next.grantResult;
            i++;
        }
        this.mCallback.onPermissionsChecked(runtimePermissionArr, iArr);
    }

    private String[] filterResults(CheckResult[] checkResultArr, int i) {
        LinkedList linkedList = new LinkedList();
        for (CheckResult checkResult : checkResultArr) {
            if (checkResult.grantResult == i) {
                linkedList.add(checkResult.permission.name);
            }
        }
        return (String[]) linkedList.toArray(new String[linkedList.size()]);
    }

    private CheckResult[] initResults(RuntimePermission[] runtimePermissionArr) {
        CheckResult[] checkResultArr = new CheckResult[runtimePermissionArr.length];
        this.mTmpResults.clear();
        for (int i = 0; i < runtimePermissionArr.length; i++) {
            checkResultArr[i] = new CheckResult();
            checkResultArr[i].permission = runtimePermissionArr[i];
            checkResultArr[i].grantResult = -1;
            this.mTmpResults.put(runtimePermissionArr[i].name, checkResultArr[i]);
        }
        return checkResultArr;
    }

    /* access modifiers changed from: private */
    public void requestPermissions(CheckResult[] checkResultArr) {
        Activity activity = this.mHost.getActivity();
        if (activity == null) {
            Log.e("PermissionCheckHelper", "host has detached?");
            return;
        }
        for (CheckResult checkResult : checkResultArr) {
            this.mTmpResults.put(checkResult.permission.name, checkResult);
        }
        String[] ungrantedPermissions = PermissionUtils.getUngrantedPermissions(activity, filterResults(checkResultArr, 0));
        if (ungrantedPermissions == null || ungrantedPermissions.length <= 0) {
            callbackResult();
        } else if (this.mIsShowWhenLocked) {
            PermissionDeniedActivity.startActivity(activity, Arrays.asList(ungrantedPermissions), true);
            activity.finish();
        } else {
            RuntimePermission[] runtimePermissionArr = new RuntimePermission[ungrantedPermissions.length];
            for (int i = 0; i < ungrantedPermissions.length; i++) {
                runtimePermissionArr[i] = this.mTmpResults.get(ungrantedPermissions[i]).permission;
            }
            PermissionUtils.requestPermissions(this.mHost, runtimePermissionArr, 46);
        }
    }

    /* access modifiers changed from: private */
    public void showPermissionIntroduction(final CheckResult[] checkResultArr, final int i) {
        final Activity activity = this.mHost.getActivity();
        if (activity == null) {
            Log.e("PermissionCheckHelper", "host has detached?");
        } else {
            PermissionIntroductionUtils.showPermissionIntroduction(activity, checkResultArr[i].permission.name, new OnPermissionIntroduced() {
                public void onPermissionIntroduced(boolean z) {
                    if (z) {
                        checkResultArr[i].grantResult = 0;
                    } else if (!checkResultArr[i].permission.required) {
                        checkResultArr[i].grantResult = -1;
                    } else {
                        activity.finish();
                        return;
                    }
                    int i = i;
                    CheckResult[] checkResultArr = checkResultArr;
                    if (i < checkResultArr.length - 1) {
                        PermissionCheckHelper.this.showPermissionIntroduction(checkResultArr, i + 1);
                    } else {
                        PermissionCheckHelper.this.requestPermissions(checkResultArr);
                    }
                }
            });
        }
    }

    public void checkPermission() {
        RuntimePermission[] runtimePermissions = this.mCallback.getRuntimePermissions();
        if (runtimePermissions == null || runtimePermissions.length <= 0) {
            this.mCallback.onPermissionsChecked(runtimePermissions, new int[0]);
        } else {
            showPermissionIntroduction(initResults(runtimePermissions), 0);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 46 && strArr != null) {
            ArrayList arrayList = new ArrayList();
            boolean z = true;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                CheckResult checkResult = this.mTmpResults.get(strArr[i2]);
                if (checkResult != null) {
                    checkResult.grantResult = iArr[i2];
                    if (iArr[i2] != 0 && checkResult.permission.required) {
                        arrayList.add(strArr[i2]);
                        z = false;
                    }
                }
            }
            if (z) {
                callbackResult();
                return;
            }
            Activity activity = this.mHost.getActivity();
            if (activity != null) {
                PermissionDeniedActivity.startActivity(activity, arrayList, this.mIsShowWhenLocked);
                activity.finish();
            }
        }
    }
}
