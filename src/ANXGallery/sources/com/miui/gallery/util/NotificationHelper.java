package com.miui.gallery.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.miui.gallery.R;
import com.nexstreaming.nexeditorsdk.nexCrop;

public class NotificationHelper {
    private static void addNotificationChannel(Context context, int i) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= 26) {
            if (i == 1) {
                notificationChannel = new NotificationChannel("com.miui.gallery.MIN", context.getString(R.string.channel_min_name), 1);
            } else if (i == 2) {
                notificationChannel = new NotificationChannel("com.miui.gallery.low", context.getString(R.string.channel_low_name), 2);
            } else if (i == 3) {
                notificationChannel = new NotificationChannel("com.miui.gallery.default", context.getString(R.string.channel_default_name), 3);
            } else {
                return;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null && !notificationManager.getNotificationChannels().contains(notificationChannel)) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public static int getBabyAlbumNotificationId(boolean z, int i) {
        return z ? (i % nexCrop.ABSTRACT_DIMENSION) + 200000 : (i % nexCrop.ABSTRACT_DIMENSION) + nexCrop.ABSTRACT_DIMENSION;
    }

    public static Notification getEmptyNotification(Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        if (Build.VERSION.SDK_INT >= 26) {
            addNotificationChannel(context, 1);
            builder.setChannelId("com.miui.gallery.MIN");
        }
        return builder.build();
    }

    public static int getPushNotificationId(int i) {
        return (i % nexCrop.ABSTRACT_DIMENSION) + 300000;
    }

    public static void setDefaultChannel(Context context, Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 26) {
            addNotificationChannel(context, 3);
            builder.setChannelId("com.miui.gallery.default");
        }
    }

    public static void setLowChannel(Context context, Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 26) {
            addNotificationChannel(context, 2);
            builder.setChannelId("com.miui.gallery.low");
        }
    }
}
