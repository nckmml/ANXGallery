package com.miui.mishare;

import android.content.Context;

public class DeviceModel {
    public static String getDeviceName(Context context, byte b, int i) {
        return context.getString(getDeviceRes(b, i));
    }

    private static int getDeviceRes(byte b, int i) {
        if (b >= 20 && b <= 29) {
            return R.string.vivo_phone;
        }
        if (b >= 10 && b <= 19) {
            return R.string.oppo_phone;
        }
        if (b < 30 || b > 39) {
            return R.string.unrecognized_model;
        }
        switch (i) {
            case 1:
                return R.string.xiaomi_9;
            case 2:
                return R.string.xiaomi_9_se;
            case 3:
                return R.string.xiaomi_8;
            case 4:
                return R.string.xiaomi_8_se;
            case 5:
                return R.string.xiaomi_8_ud;
            case 6:
                return R.string.xiaomi_8_young;
            case 7:
                return R.string.xiaomi_8_exp;
            case 8:
                return R.string.xiaomi_6;
            default:
                switch (i) {
                    case 257:
                        return R.string.xiaomi_mix_2;
                    case 258:
                        return R.string.xiaomi_mix_2s;
                    case 259:
                        return R.string.xiaomi_mix_3;
                    case 260:
                        return R.string.xiaomi_mix_3_5g;
                    default:
                        switch (i) {
                            case 513:
                                return R.string.xiaomi_cc_9;
                            case 514:
                                return R.string.xiaomi_cc_9e;
                            case 515:
                                return R.string.xiaomi_cc_9_custom;
                            default:
                                switch (i) {
                                    case 3841:
                                        return R.string.xiaomi_poco_f1;
                                    case 3842:
                                        return R.string.xiaomi_max_3;
                                    case 3843:
                                        return R.string.xiaomi_note_3;
                                    default:
                                        switch (i) {
                                            case 4097:
                                                return R.string.redmi_note_7;
                                            case 4098:
                                                return R.string.redmi_note_7_pro;
                                            case 4099:
                                                return R.string.redmi_6;
                                            case 4100:
                                                return R.string.redmi_6a;
                                            case 4101:
                                                return R.string.redmi_6_pro;
                                            case 4102:
                                                return R.string.redmi_7a;
                                            case 4103:
                                                return R.string.redmi_note_5;
                                            case 4104:
                                                return R.string.redmi_k20;
                                            case 4105:
                                                return R.string.redmi_k20_pro;
                                            default:
                                                switch (i) {
                                                    case 8193:
                                                        return R.string.mi_9t;
                                                    case 8194:
                                                        return R.string.mi_9t_pro;
                                                    case 8195:
                                                        return R.string.mi_a3;
                                                    default:
                                                        return R.string.xiaomi_phone;
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
