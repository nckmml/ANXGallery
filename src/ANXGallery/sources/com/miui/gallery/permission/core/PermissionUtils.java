package com.miui.gallery.permission.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.miui.gallery.util.LazyValue;
import com.miui.os.Rom;
import java.util.ArrayList;

public class PermissionUtils {
    public static final LazyValue<Context, Boolean> CUSTOM_REQUEST_PERMISSION = new LazyValue<Context, Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean onInit(Context context) {
            boolean z = false;
            if (Rom.IS_INTERNATIONAL || !Rom.IS_MIUI) {
                return false;
            }
            try {
                if (context.getPackageManager().getPackageInfo("com.lbe.security.miui", 16384).versionCode >= 111) {
                    z = true;
                }
                return Boolean.valueOf(z);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    };

    public static boolean checkPermission(Activity activity, String str) {
        return !supportRuntimePermissionCheck() || ActivityCompat.checkSelfPermission(activity, str) == 0;
    }

    public static boolean checkPermission(Context context, String str) {
        return !supportRuntimePermissionCheck() || ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public static String[] getUngrantedPermissions(Activity activity, String[] strArr) {
        if (!supportRuntimePermissionCheck() || strArr == null || strArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && !checkPermission(activity, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return new String[0];
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }

    public static void requestPermissions(Fragment fragment, RuntimePermission[] runtimePermissionArr, int i) {
        String[] strArr;
        if (supportRuntimePermissionCheck()) {
            int i2 = 0;
            if (!CUSTOM_REQUEST_PERMISSION.get(fragment.getActivity()).booleanValue()) {
                strArr = new String[runtimePermissionArr.length];
                while (i2 < runtimePermissionArr.length) {
                    strArr[i2] = runtimePermissionArr[i2].name;
                    i2++;
                }
            } else {
                strArr = new String[(runtimePermissionArr.length * 2)];
                while (i2 < runtimePermissionArr.length) {
                    int i3 = i2 * 2;
                    strArr[i3] = runtimePermissionArr[i2].name;
                    strArr[i3 + 1] = runtimePermissionArr[i2].desc;
                    i2++;
                }
            }
            fragment.requestPermissions(strArr, i);
        }
    }

    private static boolean supportRuntimePermissionCheck() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
