package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.CustomSettings;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.URLStatsRecorder;
import com.xiaomi.mistatistic.sdk.b;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import com.xiaomi.mistatistic.sdk.data.h;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProcessMonitorManager */
public class n {
    private static final String[] a = {"mistat_dau", "pv", "pt", "session", "new", "pa"};
    /* access modifiers changed from: private */
    public static Context b = d.a();
    private static HashMap<Integer, Integer> c = new HashMap<>();
    /* access modifiers changed from: private */
    public static boolean d = m.a(b, "config_monitor_enable");

    static {
        c.put(1, 1);
        c.put(2, 2);
        c.put(3, 4);
        c.put(4, 8);
        c.put(5, 16);
        c.put(6, 32);
        c.put(7, 64);
        c.put(8, 128);
        c.put(9, 256);
        c.put(10, 512);
        c.put(11, 1024);
    }

    public static int a(String str) {
        int i = 0;
        try {
            for (Map.Entry next : c.entrySet()) {
                int intValue = ((Integer) next.getKey()).intValue();
                int intValue2 = ((Integer) next.getValue()).intValue();
                if (a(intValue, str)) {
                    i |= intValue2;
                }
            }
        } catch (Exception e) {
            j.a("PMM", "getClientConfiguration exception", (Throwable) e);
        }
        return i;
    }

    private static JSONObject a(StatEventPojo statEventPojo) {
        JSONObject jSONObject = new JSONObject();
        if (statEventPojo != null) {
            try {
                jSONObject.put("category", statEventPojo.mCategory);
                jSONObject.put("key", statEventPojo.mKey);
            } catch (Exception e) {
                j.a("PMM", "pojoToJson exception", (Throwable) e);
            }
        }
        return jSONObject;
    }

    public static void a() {
        try {
            if (d && b != null) {
                HashMap hashMap = new HashMap();
                for (String a2 : a) {
                    a(a2, (Map<String, Long>) hashMap);
                }
                if (!hashMap.isEmpty()) {
                    LocalEventRecorder.insertEvent(new h(m.d(b, "monitor_start", 0), hashMap));
                    c();
                }
            }
        } catch (Exception e) {
            j.a("PMM", "createMonitorEvent exception:", (Throwable) e);
        }
    }

    public static void a(final double d2) {
        r.b.execute(new Runnable() {
            public void run() {
                try {
                    if (t.a(g.a(n.b), d2)) {
                        boolean unused = n.d = true;
                        m.b(n.b, "config_monitor_enable", true);
                        return;
                    }
                    boolean unused2 = n.d = false;
                    m.b(n.b, "config_monitor_enable", false);
                } catch (Exception e) {
                    j.a("PMM", "setMonitorEnable exception", (Throwable) e);
                }
            }
        });
    }

    public static void a(String str, AbstractEvent abstractEvent) {
        try {
            if (b != null) {
                a(str, abstractEvent.valueToJSon());
            }
        } catch (Exception e) {
            j.a("PMM", "monitor AbstractEvent exception", (Throwable) e);
        }
    }

    public static void a(String str, StatEventPojo statEventPojo) {
        try {
            if (b != null) {
                a(str, a(statEventPojo));
            }
        } catch (Exception e) {
            j.a("PMM", "monitor StatEventPojo exception", (Throwable) e);
        }
    }

    public static void a(String str, String str2) {
        JSONArray jSONArray;
        try {
            if (b != null) {
                JSONArray jSONArray2 = new JSONArray(str2);
                if (jSONArray2.length() > 0) {
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        JSONObject jSONObject = jSONArray2.getJSONObject(i);
                        if (!(jSONObject == null || (jSONArray = jSONObject.getJSONArray("content")) == null || jSONArray.length() <= 0)) {
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                a(str, jSONArray.getJSONObject(i2));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            j.a("PMM", "monitor data exception", (Throwable) e);
        }
    }

    public static void a(String str, List<StatEventPojo> list) {
        try {
            if (b != null) {
                for (StatEventPojo a2 : list) {
                    a(str, a(a2));
                }
            }
        } catch (Exception e) {
            j.a("PMM", "monitor List<StatEventPojo> exception", (Throwable) e);
        }
    }

    private static void a(String str, Map<String, Long> map) {
        String str2 = str;
        Map<String, Long> map2 = map;
        try {
            long b2 = b("c-" + str2);
            long b3 = b("w-" + str2);
            long b4 = b("p-" + str2);
            long b5 = b("u-" + str2);
            String str3 = "u-";
            long b6 = b("d-" + str2);
            long b7 = b("ed-" + str2);
            if (b2 != 0 || b3 != 0 || b4 != 0 || b5 != 0 || b6 != 0) {
                map2.put("c-" + str2, Long.valueOf(b2));
                map2.put("w-" + str2, Long.valueOf(b3));
                map2.put("p-" + str2, Long.valueOf(b4));
                map2.put(str3 + str2, Long.valueOf(b5));
                map2.put("d-" + str2, Long.valueOf(b6));
                if (b7 != 0) {
                    map2.put("ed-" + str2, Long.valueOf(b7));
                }
            } else if (b7 != 0) {
                map2.put("ed-" + str2, Long.valueOf(b7));
            }
        } catch (Exception e) {
            j.a("PMM", "fillMonitorCounterMap exception", (Throwable) e);
        }
    }

    private static void a(String str, JSONObject jSONObject) {
        try {
            if (d && b != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (b("monitor_start") == 0) {
                    m.c(b, "monitor_start", currentTimeMillis);
                }
                String string = jSONObject.getString("category");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                if (string.equals("mistat_basic")) {
                    b(str, jSONObject);
                } else if (string.equals("mistat_pt")) {
                    c(str, jSONObject);
                } else if (string.equals("mistat_pv")) {
                    d(str, jSONObject);
                } else if (string.equals("mistat_pa")) {
                    f(str, jSONObject);
                } else if (string.equals("mistat_session")) {
                    e(str, jSONObject);
                }
            }
        } catch (Exception e) {
            j.a("PMM", "monitor JSONObject exception", (Throwable) e);
        }
    }

    private static boolean a(int i, String str) {
        boolean z;
        switch (i) {
            case 1:
                z = URLStatsRecorder.isRecordEnabled();
                break;
            case 2:
                z = CustomSettings.isUseSystemUploadingService();
                break;
            case 3:
                z = CustomSettings.isUseSystemStatService();
                break;
            case 4:
                z = j.a();
                break;
            case 5:
                z = MiStatInterface.isABTestInitialized();
                break;
            case 6:
                z = b.a();
                break;
            case 7:
                z = BuildSetting.isTestNetworkEnabled();
                break;
            case 8:
                z = BuildSetting.isSelfStats();
                break;
            case 9:
                z = h.b();
                break;
            case 10:
                if (!TextUtils.isEmpty(str)) {
                    z = str.toLowerCase().startsWith("https");
                    break;
                } else {
                    return false;
                }
            case 11:
                try {
                    z = BuildSetting.isRespectUEP();
                    break;
                } catch (Exception e) {
                    j.a("PMM", "checkSetting exception", (Throwable) e);
                    return false;
                }
            default:
                return false;
        }
        return z;
    }

    private static long b(String str) {
        try {
            if (b != null) {
                return m.d(b, str, 0);
            }
            return 0;
        } catch (Exception e) {
            j.a("PMM", "getMonitor exception", (Throwable) e);
            return 0;
        }
    }

    private static void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.equals("mistat_dau")) {
            m.c(b, c(str, "mistat_dau"), m.d(b, c(str, "mistat_dau"), 0) + 1);
        } else if (str2.equals("new")) {
            m.c(b, c(str, "new"), m.d(b, c(str, "new"), 0) + 1);
        }
    }

    private static void b(String str, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("values");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    b(str, optJSONArray.getJSONObject(i).optString("key", ""));
                }
                return;
            }
            b(str, jSONObject.optString("key", ""));
        } catch (Exception e) {
            j.a("PMM", "basicMonitor exception", (Throwable) e);
        }
    }

    private static String c(String str, String str2) {
        return str + "-" + str2;
    }

    private static void c() {
        try {
            if (b != null) {
                m.b(b);
                m.c(b, "monitor_start", System.currentTimeMillis());
            }
        } catch (Exception e) {
            j.a("PMM", "cleanMonitor exception", (Throwable) e);
        }
    }

    private static void c(String str, JSONObject jSONObject) {
        int i;
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("values");
            if (optJSONArray != null) {
                i = 0;
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    i += optJSONArray.getJSONObject(i2).optString("value", "").split(",").length;
                }
            } else {
                i = 1;
            }
            m.c(b, c(str, "pt"), m.d(b, c(str, "pt"), 0) + ((long) i));
        } catch (Exception e) {
            j.a("PMM", "ptMonitor exception", (Throwable) e);
        }
    }

    private static void d(String str, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("values");
            m.c(b, c(str, "pv"), m.d(b, c(str, "pv"), 0) + ((long) (optJSONArray != null ? optJSONArray.length() : 1)));
        } catch (Exception e) {
            j.a("PMM", "pvMonitor exception", (Throwable) e);
        }
    }

    private static void e(String str, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("values");
            m.c(b, c(str, "session"), m.d(b, c(str, "session"), 0) + ((long) (optJSONArray != null ? optJSONArray.length() : 1)));
        } catch (Exception e) {
            j.a("PMM", "sessionMonitor exception", (Throwable) e);
        }
    }

    private static void f(String str, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("values");
            int length = optJSONArray != null ? optJSONArray.length() : 1;
            long d2 = m.d(b, c(str, "pa"), 0);
            Context context = b;
            m.c(context, str + c(str, "pa"), d2 + ((long) length));
        } catch (Exception e) {
            j.a("PMM", "paMonitor exception", (Throwable) e);
        }
    }
}
