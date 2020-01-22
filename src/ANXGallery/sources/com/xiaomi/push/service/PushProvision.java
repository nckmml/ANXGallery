package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import com.xiaomi.channel.commonutils.misc.BuildSettings;

public class PushProvision {
    private static PushProvision sInstance;
    private Context mContext;
    private int mProvisioned = 0;

    private PushProvision(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static PushProvision getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushProvision(context);
        }
        return sInstance;
    }

    public boolean checkProvisioned() {
        return BuildSettings.ReleaseChannel.contains("xmsf") || BuildSettings.ReleaseChannel.contains("xiaomi") || BuildSettings.ReleaseChannel.contains("miui");
    }

    @SuppressLint({"NewApi"})
    public int getProvisioned() {
        int i = this.mProvisioned;
        if (i != 0) {
            return i;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.mProvisioned = Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0);
            return this.mProvisioned;
        }
        this.mProvisioned = Settings.Secure.getInt(this.mContext.getContentResolver(), "device_provisioned", 0);
        return this.mProvisioned;
    }

    @SuppressLint({"NewApi"})
    public Uri getProvisionedUri() {
        return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getUriFor("device_provisioned") : Settings.Secure.getUriFor("device_provisioned");
    }
}
