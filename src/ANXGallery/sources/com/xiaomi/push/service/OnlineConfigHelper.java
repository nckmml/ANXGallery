package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.channel.commonutils.misc.CollectionUtils;
import com.xiaomi.xmpush.thrift.ConfigListType;
import com.xiaomi.xmpush.thrift.ConfigType;
import com.xiaomi.xmpush.thrift.NormalConfig;
import com.xiaomi.xmpush.thrift.OnlineConfigItem;
import com.xiaomi.xmpush.thrift.XmPushActionCustomConfig;
import com.xiaomi.xmpush.thrift.XmPushActionNormalConfig;
import java.util.ArrayList;
import java.util.List;

public class OnlineConfigHelper {

    /* renamed from: com.xiaomi.push.service.OnlineConfigHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$ConfigListType = new int[ConfigListType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$ConfigType = new int[ConfigType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|(2:1|2)|3|5|6|7|8|(2:9|10)|11|13|14|(3:15|16|18)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
        static {
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ConfigType[ConfigType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $SwitchMap$com$xiaomi$xmpush$thrift$ConfigType[ConfigType.LONG.ordinal()] = 2;
            $SwitchMap$com$xiaomi$xmpush$thrift$ConfigType[ConfigType.STRING.ordinal()] = 3;
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ConfigType[ConfigType.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$xiaomi$xmpush$thrift$ConfigListType[ConfigListType.MISC_CONFIG.ordinal()] = 1;
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ConfigListType[ConfigListType.PLUGIN_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static List<Pair<Integer, Object>> convertMessage(List<OnlineConfigItem> list, boolean z) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (OnlineConfigItem next : list) {
            int key = next.getKey();
            ConfigType findByValue = ConfigType.findByValue(next.getType());
            if (findByValue != null) {
                if (!z || !next.clear) {
                    int i = AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$ConfigType[findByValue.ordinal()];
                    arrayList.add(i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : new Pair(Integer.valueOf(key), Boolean.valueOf(next.isBoolValue())) : new Pair(Integer.valueOf(key), next.getStringValue()) : new Pair(Integer.valueOf(key), Long.valueOf(next.getLongValue())) : new Pair(Integer.valueOf(key), Integer.valueOf(next.getIntValue())));
                } else {
                    arrayList.add(new Pair(Integer.valueOf(key), (Object) null));
                }
            }
        }
        return arrayList;
    }

    public static int getVersion(OnlineConfig onlineConfig, ConfigListType configListType) {
        String versionKey = getVersionKey(configListType);
        int i = 0;
        if (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$ConfigListType[configListType.ordinal()] == 1) {
            i = 1;
        }
        return onlineConfig.preferences.getInt(versionKey, i);
    }

    private static String getVersionKey(ConfigListType configListType) {
        return "oc_version_" + configListType.getValue();
    }

    public static void setVersion(OnlineConfig onlineConfig, ConfigListType configListType, int i) {
        onlineConfig.preferences.edit().putInt(getVersionKey(configListType), i).commit();
    }

    public static void updateCustomConfigs(OnlineConfig onlineConfig, XmPushActionCustomConfig xmPushActionCustomConfig) {
        onlineConfig.updateCustomConfigs(convertMessage(xmPushActionCustomConfig.getCustomConfigs(), true));
        onlineConfig.runCallback();
    }

    public static void updateNormalConfigs(OnlineConfig onlineConfig, XmPushActionNormalConfig xmPushActionNormalConfig) {
        for (NormalConfig next : xmPushActionNormalConfig.getNormalConfigs()) {
            if (next.getVersion() > getVersion(onlineConfig, next.getType())) {
                setVersion(onlineConfig, next.getType(), next.getVersion());
                onlineConfig.updateNormalConfigs(convertMessage(next.configItems, false));
            }
        }
        onlineConfig.runCallback();
    }
}
