package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.SharedPrefsCompat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.push.service.OnlineConfig;
import java.util.HashMap;

public class AssemblePushHelper {
    private static HashMap<String, String> mTokens = new HashMap<>();

    /* renamed from: com.xiaomi.mipush.sdk.AssemblePushHelper$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$mipush$sdk$AssemblePush = new int[AssemblePush.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[AssemblePush.ASSEMBLE_PUSH_HUAWEI.ordinal()] = 1;
            $SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[AssemblePush.ASSEMBLE_PUSH_FCM.ordinal()] = 2;
            $SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[AssemblePush.ASSEMBLE_PUSH_COS.ordinal()] = 3;
            try {
                $SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[AssemblePush.ASSEMBLE_PUSH_FTOS.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    static void checkAssemblePushStatus(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String tokenKey = getTokenKey(AssemblePush.ASSEMBLE_PUSH_HUAWEI);
        String tokenKey2 = getTokenKey(AssemblePush.ASSEMBLE_PUSH_FCM);
        if (!TextUtils.isEmpty(sharedPreferences.getString(tokenKey, "")) && TextUtils.isEmpty(sharedPreferences.getString(tokenKey2, ""))) {
            z = true;
        }
        if (z) {
            PushServiceClient.getInstance(context).send3rdPushHint(2, tokenKey);
        }
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v8, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    public static HashMap<String, String> getAssemblePushExtra(Context context, AssemblePush assemblePush) {
        HashMap<String, String> hashMap = new HashMap<>();
        String tokenKey = getTokenKey(assemblePush);
        if (TextUtils.isEmpty(tokenKey)) {
            return hashMap;
        }
        int i = AnonymousClass2.$SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[assemblePush.ordinal()];
        ApplicationInfo applicationInfo = null;
        if (i == 1) {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            } catch (Exception e) {
                MyLog.e(e.toString());
            }
            int i2 = -1;
            if (applicationInfo != null) {
                i2 = applicationInfo.metaData.getInt("com.huawei.hms.client.appid");
            }
            applicationInfo = "brand:" + AssemblePushUtils.getPhoneBrand(context).name() + "~" + "token" + ":" + getAssemblePushToken(context, tokenKey) + "~" + "package_name" + ":" + context.getPackageName() + "~" + "app_id" + ":" + i2;
        } else if (i == 2) {
            applicationInfo = "brand:" + PhoneBrand.FCM.name() + "~" + "token" + ":" + getAssemblePushToken(context, tokenKey) + "~" + "package_name" + ":" + context.getPackageName();
        } else if (i == 3) {
            applicationInfo = "brand:" + PhoneBrand.OPPO.name() + "~" + "token" + ":" + getAssemblePushToken(context, tokenKey) + "~" + "package_name" + ":" + context.getPackageName();
        } else if (i == 4) {
            applicationInfo = "brand:" + PhoneBrand.VIVO.name() + "~" + "token" + ":" + getAssemblePushToken(context, tokenKey) + "~" + "package_name" + ":" + context.getPackageName();
        }
        hashMap.put("RegInfo", applicationInfo);
        return hashMap;
    }

    protected static synchronized String getAssemblePushToken(Context context, String str) {
        String str2;
        synchronized (AssemblePushHelper.class) {
            str2 = mTokens.get(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
        }
        return str2;
    }

    public static String getTokenKey(AssemblePush assemblePush) {
        int i = AnonymousClass2.$SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[assemblePush.ordinal()];
        if (i == 1) {
            return "hms_push_token";
        }
        if (i == 2) {
            return "fcm_push_token";
        }
        if (i == 3) {
            return "cos_push_token";
        }
        if (i != 4) {
            return null;
        }
        return "ftos_push_token";
    }

    public static boolean isOpenAssemblePushOnlineSwitch(Context context, AssemblePush assemblePush) {
        if (AssemblePushInfoHelper.getConfigKeyByType(assemblePush) != null) {
            return OnlineConfig.getInstance(context).getBooleanValue(AssemblePushInfoHelper.getConfigKeyByType(assemblePush).getValue(), true);
        }
        return false;
    }

    public static void registerAssemblePush(Context context) {
        AssemblePushCollectionsManager.getInstance(context).register();
    }

    /* access modifiers changed from: private */
    public static synchronized void saveAssemblePushToken(Context context, AssemblePush assemblePush, String str) {
        synchronized (AssemblePushHelper.class) {
            String tokenKey = getTokenKey(assemblePush);
            if (TextUtils.isEmpty(tokenKey)) {
                MyLog.w("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                return;
            }
            SharedPrefsCompat.apply(context.getSharedPreferences("mipush_extra", 0).edit().putString(tokenKey, str));
            MyLog.w("ASSEMBLE_PUSH : update sp file success!  " + str);
        }
    }

    public static void saveAssemblePushTokenAfterAck(final Context context, final AssemblePush assemblePush, final String str) {
        ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
            public void run() {
                String str;
                if (!TextUtils.isEmpty(str)) {
                    String[] split = str.split("~");
                    int length = split.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            str = "";
                            break;
                        }
                        String str2 = split[i];
                        if (!TextUtils.isEmpty(str2) && str2.startsWith("token:")) {
                            str = str2.substring(str2.indexOf(":") + 1);
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        MyLog.w("ASSEMBLE_PUSH : receive correct token");
                        AssemblePushHelper.saveAssemblePushToken(context, assemblePush, str);
                        AssemblePushHelper.checkAssemblePushStatus(context);
                        return;
                    }
                    MyLog.w("ASSEMBLE_PUSH : receive incorrect token");
                }
            }
        });
    }

    public static void unregisterAssemblePush(Context context) {
        AssemblePushCollectionsManager.getInstance(context).unregister();
    }
}
