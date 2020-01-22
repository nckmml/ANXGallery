package com.xiaomi.micloudsdk.remote;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public abstract class XiaomiAccountRemoteMethodInvoker<R> extends RemoteMethodInvoker<R> {
    public XiaomiAccountRemoteMethodInvoker(Context context) {
        super(context);
    }

    private static boolean bindAccountService(Context context, String str, ServiceConnection serviceConnection) {
        Intent intent = new Intent(str);
        intent.setPackage("com.xiaomi.account");
        return context.bindService(intent, serviceConnection, 1);
    }

    /* access modifiers changed from: protected */
    public boolean bindService(Context context, ServiceConnection serviceConnection) {
        return bindAccountService(context, "com.xiaomi.account.action.BIND_XIAOMI_ACCOUNT_SERVICE", serviceConnection) || bindAccountService(context, "android.intent.action.BIND_XIAOMI_ACCOUNT_SERVICE", serviceConnection);
    }
}
