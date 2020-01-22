package com.miui.gallery.projection;

import com.miui.gallery.util.ReflectUtils;

public class ConnectControllerSingleton {
    private static ConnectController sInstance;

    public static synchronized ConnectController getInstance() {
        ConnectController connectController;
        synchronized (ConnectControllerSingleton.class) {
            if (sInstance == null) {
                if (isMilinkV1()) {
                    sInstance = new ConnectControllerImplV1();
                } else {
                    sInstance = new ConnectControllerImplV2();
                }
            }
            connectController = sInstance;
        }
        return connectController;
    }

    private static boolean isMilinkV1() {
        return ReflectUtils.getMethod("com.milink.api.v1.MilinkClientManager", "showScanList") == null;
    }
}
