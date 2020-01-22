package com.xiaomi.push.service.awake;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class AwakeDataHelper {
    public static String decode(String str) {
        return XMStringUtils.bytesToString(Base64.decode(str, 2));
    }

    public static String encode(String str) {
        return Base64.encodeToString(XMStringUtils.getBytes(str), 2);
    }

    public static Uri getContentUri(String str, String str2) {
        return Uri.parse("content://" + str).buildUpon().appendPath(str2).build();
    }

    public static String getString(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String next : hashMap.keySet()) {
                jSONObject.put(next, hashMap.get(next));
            }
        } catch (JSONException e) {
            MyLog.e((Throwable) e);
        }
        return jSONObject.toString();
    }

    public static String obfuscateLogContent(HashMap<String, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.put("event_type", hashMap.get("event_type") + "");
            hashMap2.put("description", hashMap.get("description") + "");
            String str = hashMap.get("awake_info");
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    hashMap2.put("__planId__", String.valueOf(jSONObject.opt("__planId__")));
                    hashMap2.put("flow_id", String.valueOf(jSONObject.opt("flow_id")));
                    hashMap2.put("jobkey", String.valueOf(jSONObject.opt("jobkey")));
                    hashMap2.put("msg_id", String.valueOf(jSONObject.opt("msg_id")));
                    hashMap2.put("A", String.valueOf(jSONObject.opt("awake_app")));
                    hashMap2.put("B", String.valueOf(jSONObject.opt("awakened_app")));
                    hashMap2.put("module", String.valueOf(jSONObject.opt("awake_type")));
                } catch (JSONException e) {
                    MyLog.e((Throwable) e);
                }
            }
        }
        return getString(hashMap2);
    }
}
