package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.DeviceInfo;
import com.xiaomi.channel.commonutils.android.MIUIUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.util.HashMap;

class MiPushUtils {
    public static HashMap<String, String> collectDeviceInfo(Context context, String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            hashMap.put("appToken", AppInfoHolder.getInstance(context).getAppToken());
            hashMap.put("regId", MiPushClient.getRegId(context));
            hashMap.put("appId", AppInfoHolder.getInstance(context).getAppID());
            hashMap.put("regResource", AppInfoHolder.getInstance(context).getRegResource());
            if (!MIUIUtils.isGlobalRegion()) {
                String quicklyGetIMEI = DeviceInfo.quicklyGetIMEI(context);
                if (!TextUtils.isEmpty(quicklyGetIMEI)) {
                    hashMap.put("imeiMd5", XMStringUtils.getMd5Digest(quicklyGetIMEI));
                }
            }
            hashMap.put("isMIUI", String.valueOf(MIUIUtils.isMIUI()));
            hashMap.put("miuiVersion", MIUIUtils.getMIUIType());
            hashMap.put("devId", DeviceInfo.getDeviceId(context, true));
            hashMap.put("model", Build.MODEL);
            hashMap.put("pkgName", context.getPackageName());
            hashMap.put("sdkVersion", "3_7_0");
            hashMap.put("androidVersion", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("os", Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL);
            hashMap.put("andId", DeviceInfo.getAndroidId(context));
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("clientInterfaceId", str);
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }
}
