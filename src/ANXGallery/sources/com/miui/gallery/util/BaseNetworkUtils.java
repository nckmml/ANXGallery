package com.miui.gallery.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseNetworkUtils {
    public static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) StaticContext.sGetAndroidContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean isActiveNetworkMetered() {
        ConnectivityManager connectivityManager = (ConnectivityManager) StaticContext.sGetAndroidContext().getSystemService("connectivity");
        return connectivityManager != null && isNetworkConnected() && connectivityManager.isActiveNetworkMetered();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        r0 = r0.getActiveNetworkInfo();
     */
    public static boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) StaticContext.sGetAndroidContext().getSystemService("connectivity");
        return (connectivityManager == null || activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? false : true;
    }
}
