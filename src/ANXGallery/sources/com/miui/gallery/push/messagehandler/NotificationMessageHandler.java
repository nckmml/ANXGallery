package com.miui.gallery.push.messagehandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.push.GalleryPushMessage;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.NotificationHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.jcodec.containers.mp4.boxes.Box;
import org.json.JSONObject;

public class NotificationMessageHandler extends MessageHandler {
    private static long mLastTime;

    private int getPushNotificationId(int i) {
        return NotificationHelper.getPushNotificationId(i);
    }

    public void handleDirect(Context context, GalleryPushMessage galleryPushMessage) {
        String str;
        if (context == null) {
            Log.d("NotificationMessageHandler", "context is null");
            return;
        }
        String data = galleryPushMessage.getData();
        if (data == null) {
            Log.e("NotificationMessageHandler", "message data is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(galleryPushMessage.getData());
            int pushNotificationId = getPushNotificationId(jSONObject.optInt("id", 0));
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager == null) {
                Log.d("NotificationMessageHandler", "notification manager is null");
            } else if (jSONObject.optBoolean("isCancel", false)) {
                notificationManager.cancel(pushNotificationId);
                Log.d("NotificationMessageHandler", "is cancel message");
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - mLastTime;
                mLastTime = currentTimeMillis;
                if (j < 600000) {
                    Log.e("NotificationMessageHandler", "too frequently to push notification, time duration is %d", (Object) Long.valueOf(j));
                    return;
                }
                int optInt = jSONObject.optInt("version", 0);
                if (optInt > 0) {
                    Log.d("NotificationMessageHandler", "message version is not fit, server version is %d, local version is %d", Integer.valueOf(optInt), 0);
                    return;
                }
                String optString = jSONObject.optString("url");
                if (TextUtils.isEmpty(optString)) {
                    Log.e("NotificationMessageHandler", "empty url");
                    return;
                }
                Uri parse = Uri.parse(optString);
                Intent intent = new Intent("android.intent.action.VIEW", parse);
                if (parse.getBooleanQueryParameter("is_activity_action", false)) {
                    Log.d("NotificationMessageHandler", "is activity action");
                    String optString2 = jSONObject.optString("intent_package_name");
                    if (!TextUtils.isEmpty(optString2)) {
                        intent.setPackage(optString2);
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("intent_extra");
                    if (optJSONObject != null) {
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            intent.putExtra(next, optJSONObject.optString(next));
                        }
                    }
                    if (intent.resolveActivity(context.getPackageManager()) == null) {
                        Log.d("NotificationMessageHandler", "no activity to handle this uri");
                        return;
                    }
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("title");
                JSONObject optJSONObject3 = jSONObject.optJSONObject("description");
                String str2 = null;
                if (optJSONObject2 == null || optJSONObject3 == null) {
                    str = null;
                } else {
                    String lowerCase = Locale.getDefault().toString().toLowerCase();
                    str = optJSONObject2.optString(lowerCase);
                    str2 = optJSONObject3.optString(lowerCase);
                }
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str2)) {
                        Intent intent2 = new Intent("android.intent.action.VIEW", GalleryContract.Common.URI_PUSH_LANDING_PAGE);
                        intent2.setPackage("com.miui.gallery");
                        intent2.putExtra("notification_content_intent", intent);
                        intent2.putExtra("notification_content_id", pushNotificationId);
                        PendingIntent activity = PendingIntent.getActivity(context, 0, intent2, Box.MAX_BOX_SIZE);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker(str);
                        builder.setContentTitle(str);
                        builder.setContentText(str2);
                        builder.setSmallIcon(R.mipmap.ic_launcher_gallery);
                        builder.setContentIntent(activity);
                        NotificationHelper.setLowChannel(context, builder);
                        Notification build = builder.build();
                        build.flags = 16;
                        notificationManager.notify(pushNotificationId, build);
                        new HashMap().put("pushContent", String.format(Locale.US, "%d_%s", new Object[]{Integer.valueOf(pushNotificationId), optString}));
                        GalleryStatHelper.recordCountEvent("push_notification", "push_notification_arrive");
                        return;
                    }
                }
                Log.e("NotificationMessageHandler", "no title or description");
            }
        } catch (Exception e) {
            Log.e("NotificationMessageHandler", "fail to parse notification data from" + data, (Object) e);
        }
    }

    public void handlePull(Context context, GalleryPushMessage galleryPushMessage) {
    }
}
