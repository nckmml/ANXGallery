package com.xiaomi.mipush.sdk;

import com.xiaomi.xmpush.thrift.ConfigKey;
import java.util.HashMap;

public class AssemblePushInfoHelper {
    private static HashMap<AssemblePush, ManageClassInfo> mHashMaps = new HashMap<>();

    /* renamed from: com.xiaomi.mipush.sdk.AssemblePushInfoHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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

    static class ManageClassInfo {
        public String className;
        public String methodName;

        public ManageClassInfo(String str, String str2) {
            this.className = str;
            this.methodName = str2;
        }
    }

    static {
        add(AssemblePush.ASSEMBLE_PUSH_HUAWEI, new ManageClassInfo("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        add(AssemblePush.ASSEMBLE_PUSH_FCM, new ManageClassInfo("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        add(AssemblePush.ASSEMBLE_PUSH_COS, new ManageClassInfo("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
        add(AssemblePush.ASSEMBLE_PUSH_FTOS, new ManageClassInfo("com.xiaomi.assemble.control.FTOSPushManager", "newInstance"));
    }

    private static void add(AssemblePush assemblePush, ManageClassInfo manageClassInfo) {
        if (manageClassInfo != null) {
            mHashMaps.put(assemblePush, manageClassInfo);
        }
    }

    public static ConfigKey getConfigKeyByType(AssemblePush assemblePush) {
        return ConfigKey.AggregatePushSwitch;
    }

    public static ManageClassInfo getManageClassInfoByType(AssemblePush assemblePush) {
        return mHashMaps.get(assemblePush);
    }

    public static RetryType getRetryType(AssemblePush assemblePush) {
        int i = AnonymousClass1.$SwitchMap$com$xiaomi$mipush$sdk$AssemblePush[assemblePush.ordinal()];
        if (i == 1) {
            return RetryType.UPLOAD_HUAWEI_TOKEN;
        }
        if (i == 2) {
            return RetryType.UPLOAD_FCM_TOKEN;
        }
        if (i == 3) {
            return RetryType.UPLOAD_COS_TOKEN;
        }
        if (i != 4) {
            return null;
        }
        return RetryType.UPLOAD_FTOS_TOKEN;
    }
}
