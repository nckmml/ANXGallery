package com.xiaomi.micloudsdk.request.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import micloud.compat.v18.utils.PrivacyIntentHelperCompat;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestInjector {
    public static void checkResponse(Context context, String str) {
        Log.d("RequestInjector", "checkResponse");
        if (str != null && isPrivacyError(str)) {
            PrivacyIntentHelperCompat.notifyPrivacyDenied(context);
        }
    }

    public static void handleCloudServerException(Context context, CloudServerException cloudServerException) {
        if (cloudServerException.code == 503 && cloudServerException.retryTime > 0) {
            Intent intent = new Intent("com.xiaomi.action.DATA_IN_TRANSFER");
            intent.setPackage("com.miui.cloudservice");
            intent.putExtra("retryTime", cloudServerException.retryTime);
            context.sendBroadcast(intent);
        } else if (cloudServerException.code == 52003) {
            PrivacyIntentHelperCompat.notifyPrivacyDenied(context);
        }
    }

    private static boolean isPrivacyError(String str) {
        try {
            return new JSONObject(str).optInt("code", 0) == 52003;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
