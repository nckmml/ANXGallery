package com.xiaomi.mistatistic.sdk.data;

import com.xiaomi.mistatistic.sdk.controller.j;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MonitorEvent */
public class h extends AbstractEvent {
    private long a;
    private Map<String, Long> b;

    public h(long j, Map<String, Long> map) {
        this.a = j;
        if (map == null) {
            this.b = null;
        } else {
            this.b = map;
        }
    }

    private String a(Map<String, Long> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.isEmpty()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                jSONObject.put(next, map.get(next));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            j.a("json error", (Throwable) e);
            return null;
        }
    }

    public String getCategory() {
        return "mistat_monitor";
    }

    public StatEventPojo toPojo() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.mCategory = getCategory();
        statEventPojo.mTimeStamp = this.mTS;
        statEventPojo.mValue = String.valueOf(this.a);
        statEventPojo.mExtra = a(this.b);
        return statEventPojo;
    }

    public JSONObject valueToJSon() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("category", getCategory());
        jSONObject.put("start", this.a);
        jSONObject.put("end", this.mTS);
        Map<String, Long> map = this.b;
        if (map != null) {
            jSONObject.put("params", new JSONObject(map));
        }
        return jSONObject;
    }
}
