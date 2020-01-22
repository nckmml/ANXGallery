package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.android.AppInfoUtils;
import com.xiaomi.channel.commonutils.android.MIUIUtils;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.reflect.JavaCalls;
import com.xiaomi.push.service.NotificationIconHelper;
import com.xiaomi.push.service.clientReport.PushClientReportManager;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jcodec.containers.mp4.boxes.Box;
import org.json.JSONException;
import org.json.JSONObject;

public class MIPushNotificationHelper {
    public static long lastNotify;
    private static final LinkedList<Pair<Integer, XmPushActionContainer>> notifyContainerCache = new LinkedList<>();
    private static ExecutorService sThreadPool = Executors.newCachedThreadPool();

    private static class DownloadOnlinePicTask implements Callable<Bitmap> {
        private Context context;
        private boolean isSizeLimited;
        private String picUrl;

        public DownloadOnlinePicTask(String str, Context context2, boolean z) {
            this.context = context2;
            this.picUrl = str;
            this.isSizeLimited = z;
        }

        public Bitmap call() throws Exception {
            if (TextUtils.isEmpty(this.picUrl)) {
                MyLog.w("Failed get online picture/icon resource cause picUrl is empty");
                return null;
            } else if (this.picUrl.startsWith("http")) {
                NotificationIconHelper.GetIconResult iconFromUrl = NotificationIconHelper.getIconFromUrl(this.context, this.picUrl, this.isSizeLimited);
                if (iconFromUrl != null) {
                    return iconFromUrl.bitmap;
                }
                MyLog.w("Failed get online picture/icon resource");
                return null;
            } else {
                Bitmap iconFromUri = NotificationIconHelper.getIconFromUri(this.context, this.picUrl);
                if (iconFromUri != null) {
                    return iconFromUri;
                }
                MyLog.w("Failed get online picture/icon resource");
                return iconFromUri;
            }
        }
    }

    public static class GetNotificationResult {
        Notification notification;
        long trafficSize = 0;
    }

    public static class NotifyPushMessageInfo {
        public String targetPkgName;
        public long traffic = 0;
    }

    public static class ResultHolder {
        public boolean ifSummaryShow = false;
        public HashMap<String, Integer> notificationMap = new HashMap<>();
        public HashMap<String, HashSet<Integer>> notifyIdMap = new HashMap<>();
    }

    private static void cancelGroupSummary(NotificationManager notificationManager, String str, String str2) {
        int groupSummaryNotifyId = getGroupSummaryNotifyId(str, str2);
        MyLog.i("GROUPSUMMARY  : cancelGroupSummarytargetPackageName = " + str + " id = " + groupSummaryNotifyId);
        notificationManager.cancel(groupSummaryNotifyId);
    }

    private static ResultHolder checkActiveNotification(NotificationManager notificationManager, String str, String str2) {
        StatusBarNotification[] activeNotifications;
        Object callMethod;
        ResultHolder resultHolder = new ResultHolder();
        if (notificationManager != null && Build.VERSION.SDK_INT >= 23 && !TextUtils.isEmpty(str) && (activeNotifications = notificationManager.getActiveNotifications()) != null && activeNotifications.length > 0) {
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                if (statusBarNotification != null) {
                    String packageName = statusBarNotification.getPackageName();
                    String group = statusBarNotification.getNotification() != null ? statusBarNotification.getNotification().getGroup() : "";
                    if (!TextUtils.isEmpty(group)) {
                        String str3 = packageName + group;
                        HashSet hashSet = null;
                        if (!str2.equals(str3) || (callMethod = JavaCalls.callMethod(statusBarNotification.getNotification(), "isGroupSummary", (Object[]) null)) == null || !(callMethod instanceof Boolean) || !Boolean.class.cast(callMethod).booleanValue()) {
                            resultHolder.notificationMap.put(str3, Integer.valueOf((resultHolder.notificationMap.containsKey(str3) ? resultHolder.notificationMap.get(str3).intValue() : 0) + 1));
                            if (resultHolder.notifyIdMap.containsKey(str3)) {
                                hashSet = resultHolder.notifyIdMap.get(str3);
                            }
                            if (hashSet == null) {
                                hashSet = new HashSet();
                                resultHolder.notifyIdMap.put(str3, hashSet);
                            }
                            hashSet.add(Integer.valueOf(statusBarNotification.getId()));
                        } else {
                            resultHolder.ifSummaryShow = true;
                        }
                    }
                }
            }
        }
        return resultHolder;
    }

    private static boolean checkMatch(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    @TargetApi(23)
    private static void checkNotificationAutoGroup(Context context, Notification notification, int i, String str) {
        HashSet hashSet;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        String str2 = context.getPackageName() + notification.getGroup();
        ResultHolder checkActiveNotification = checkActiveNotification(notificationManager, notification.getGroup(), str2);
        boolean z = false;
        int intValue = checkActiveNotification.notificationMap.containsKey(str2) ? checkActiveNotification.notificationMap.get(str2).intValue() : 0;
        if (checkActiveNotification.notifyIdMap.containsKey(str2) && (hashSet = checkActiveNotification.notifyIdMap.get(str2)) != null && hashSet.contains(Integer.valueOf(i))) {
            z = true;
        }
        if (intValue > 1 || (intValue == 1 && !z)) {
            if (!checkActiveNotification.ifSummaryShow) {
                showGroupSummary(context, str, notification, notificationManager);
            }
        } else if (intValue < 1 && checkActiveNotification.ifSummaryShow) {
            cancelGroupSummary(notificationManager, str, notification.getGroup());
        }
    }

    static void clearLocalNotifyType(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    public static void clearNotification(Context context, String str) {
        clearNotification(context, str, -1);
    }

    public static void clearNotification(Context context, String str, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        LinkedList linkedList = new LinkedList();
        if (i >= 0) {
            notificationManager.cancel(hashCode);
        }
        synchronized (notifyContainerCache) {
            Iterator it = notifyContainerCache.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                XmPushActionContainer xmPushActionContainer = (XmPushActionContainer) pair.second;
                if (xmPushActionContainer != null) {
                    String targetPackage = getTargetPackage(xmPushActionContainer);
                    if (i >= 0) {
                        if (hashCode == ((Integer) pair.first).intValue() && TextUtils.equals(targetPackage, str)) {
                            linkedList.add(pair);
                        }
                    } else if (i == -1 && TextUtils.equals(targetPackage, str)) {
                        notificationManager.cancel(((Integer) pair.first).intValue());
                        linkedList.add(pair);
                    }
                }
            }
            if (notifyContainerCache != null) {
                notifyContainerCache.removeAll(linkedList);
                uploadClearMessageData(context, linkedList);
            }
        }
    }

    public static void clearNotification(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            LinkedList linkedList = new LinkedList();
            synchronized (notifyContainerCache) {
                Iterator it = notifyContainerCache.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    XmPushActionContainer xmPushActionContainer = (XmPushActionContainer) pair.second;
                    if (xmPushActionContainer != null) {
                        String targetPackage = getTargetPackage(xmPushActionContainer);
                        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
                        if (metaInfo != null) {
                            if (TextUtils.equals(targetPackage, str)) {
                                String title = metaInfo.getTitle();
                                String description = metaInfo.getDescription();
                                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description) && checkMatch(str2, title) && checkMatch(str3, description)) {
                                    notificationManager.cancel(((Integer) pair.first).intValue());
                                    linkedList.add(pair);
                                }
                            }
                        }
                    }
                }
                if (notifyContainerCache != null) {
                    notifyContainerCache.removeAll(linkedList);
                    uploadClearMessageData(context, linkedList);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0070, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004e, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L_0x0072;
     */
    private static String[] determineTitleAndDespByDIP(Context context, PushMetaInfo pushMetaInfo) {
        String str;
        String title = pushMetaInfo.getTitle();
        String description = pushMetaInfo.getDescription();
        Map<String, String> extra = pushMetaInfo.getExtra();
        if (extra != null) {
            int intValue = Float.valueOf((((float) context.getResources().getDisplayMetrics().widthPixels) / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            if (intValue <= 320) {
                String str2 = extra.get("title_short");
                if (!TextUtils.isEmpty(str2)) {
                    title = str2;
                }
                str = extra.get("description_short");
            } else if (intValue > 360) {
                String str3 = extra.get("title_long");
                if (!TextUtils.isEmpty(str3)) {
                    title = str3;
                }
                str = extra.get("description_long");
            }
            description = str;
        }
        return new String[]{title, description};
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int i = 1;
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private static Notification enableCustomIconForMiui(Notification notification) {
        Object field = JavaCalls.getField(notification, "extraNotification");
        if (field != null) {
            JavaCalls.callMethod(field, "setCustomizedIcon", true);
        }
        return notification;
    }

    private static Bitmap getBitmapFromId(Context context, int i) {
        return drawableToBitmap(context.getResources().getDrawable(i));
    }

    private static int getChannelImportance(Map<String, String> map) {
        if (map == null) {
            return 3;
        }
        String str = map.get("channel_importance");
        if (TextUtils.isEmpty(str)) {
            return 3;
        }
        try {
            MyLog.v("importance=" + 3);
            return Integer.parseInt(str);
        } catch (Exception e) {
            MyLog.e("parsing channel importance error: " + e);
            return 3;
        }
    }

    private static String getChannelName(Context context, String str, Map<String, String> map) {
        return (map == null || TextUtils.isEmpty(map.get("channel_name"))) ? AppInfoUtils.getAppLabel(context, str) : map.get("channel_name");
    }

    private static PendingIntent getClickedPendingIntent(Context context, XmPushActionContainer xmPushActionContainer, PushMetaInfo pushMetaInfo, byte[] bArr, int i) {
        Intent intent;
        Context context2 = context;
        XmPushActionContainer xmPushActionContainer2 = xmPushActionContainer;
        PushMetaInfo pushMetaInfo2 = pushMetaInfo;
        byte[] bArr2 = bArr;
        int i2 = isNormalNotificationMessage(xmPushActionContainer) ? 1000 : isBusinessMessage(xmPushActionContainer) ? 3000 : -1;
        String id = pushMetaInfo2 != null ? pushMetaInfo.getId() : "";
        if (pushMetaInfo2 == null || TextUtils.isEmpty(pushMetaInfo2.url)) {
            if (isBusinessMessage(xmPushActionContainer)) {
                intent = new Intent();
                intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
                intent.putExtra("mipush_payload", bArr2);
                intent.putExtra("mipush_notified", true);
                intent.addCategory(String.valueOf(i));
                intent.addCategory(String.valueOf(id));
            } else {
                intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent.setComponent(new ComponentName(xmPushActionContainer2.packageName, "com.xiaomi.mipush.sdk.PushMessageHandler"));
                intent.putExtra("mipush_payload", bArr2);
                intent.putExtra("mipush_notified", true);
                intent.addCategory(String.valueOf(i));
                intent.addCategory(String.valueOf(id));
            }
            intent.putExtra("messageId", id);
            intent.putExtra("eventMessageType", i2);
            if (!ComponentHelper.checkActivity(context2, xmPushActionContainer2.packageName, "com.xiaomi.mipush.MESSAGE_CLICKED")) {
                return PendingIntent.getService(context2, 0, intent, Box.MAX_BOX_SIZE);
            }
            Intent intent2 = new Intent();
            intent2.setAction("com.xiaomi.mipush.MESSAGE_CLICKED");
            intent2.setClassName(xmPushActionContainer2.packageName, "com.xiaomi.mipush.sdk.BridgeActivity");
            intent2.addFlags(276824064);
            intent2.putExtra("mipush_serviceIntent", intent);
            intent2.addCategory(String.valueOf(i));
            intent2.addCategory(String.valueOf(id));
            return PendingIntent.getActivity(context2, 0, intent2, Box.MAX_BOX_SIZE);
        }
        Intent intent3 = new Intent("android.intent.action.VIEW");
        intent3.setData(Uri.parse(pushMetaInfo2.url));
        intent3.addFlags(268435456);
        intent3.putExtra("messageId", id);
        intent3.putExtra("eventMessageType", i2);
        return PendingIntent.getActivity(context2, 0, intent3, Box.MAX_BOX_SIZE);
    }

    private static int getGroupSummaryNotifyId(String str, String str2) {
        return (((str + str2).hashCode() / 10) * 10) + 32768;
    }

    private static int getIconId(Context context, String str, String str2) {
        if (str.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(str2, "drawable", str);
        }
        return 0;
    }

    private static int getIdForSmallIcon(Context context, String str) {
        int iconId = getIconId(context, str, "mipush_notification");
        int iconId2 = getIconId(context, str, "mipush_small_notification");
        if (iconId <= 0) {
            iconId = iconId2 > 0 ? iconId2 : context.getApplicationInfo().icon;
        }
        return (iconId != 0 || Build.VERSION.SDK_INT < 9) ? iconId : context.getApplicationInfo().logo;
    }

    public static String getInterfaceId(XmPushActionContainer xmPushActionContainer) {
        return isBusinessMessage(xmPushActionContainer) ? "E100002" : isNormalNotificationMessage(xmPushActionContainer) ? "E100000" : isPassThoughMessage(xmPushActionContainer) ? "E100001" : isRegisterMessage(xmPushActionContainer) ? "E100003" : "";
    }

    static int getLocalNotifyType(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Reader.READ_DONE);
    }

    private static RemoteViews getNotificationForCustomLayout(Context context, XmPushActionContainer xmPushActionContainer, byte[] bArr) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        String targetPackage = getTargetPackage(xmPushActionContainer);
        Map<String, String> extra = metaInfo.getExtra();
        if (extra == null) {
            return null;
        }
        String str = extra.get("layout_name");
        String str2 = extra.get("layout_value");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(targetPackage);
                int identifier = resourcesForApplication.getIdentifier(str, "layout", targetPackage);
                if (identifier == 0) {
                    return null;
                }
                RemoteViews remoteViews = new RemoteViews(targetPackage, identifier);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    if (jSONObject.has("text")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("text");
                        Iterator<String> keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            String string = jSONObject2.getString(next);
                            int identifier2 = resourcesForApplication.getIdentifier(next, "id", targetPackage);
                            if (identifier2 > 0) {
                                remoteViews.setTextViewText(identifier2, string);
                            }
                        }
                    }
                    if (jSONObject.has("image")) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("image");
                        Iterator<String> keys2 = jSONObject3.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            String string2 = jSONObject3.getString(next2);
                            int identifier3 = resourcesForApplication.getIdentifier(next2, "id", targetPackage);
                            int identifier4 = resourcesForApplication.getIdentifier(string2, "drawable", targetPackage);
                            if (identifier3 > 0) {
                                remoteViews.setImageViewResource(identifier3, identifier4);
                            }
                        }
                    }
                    if (jSONObject.has("time")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("time");
                        Iterator<String> keys3 = jSONObject4.keys();
                        while (keys3.hasNext()) {
                            String next3 = keys3.next();
                            String string3 = jSONObject4.getString(next3);
                            if (string3.length() == 0) {
                                string3 = "yy-MM-dd hh:mm";
                            }
                            int identifier5 = resourcesForApplication.getIdentifier(next3, "id", targetPackage);
                            if (identifier5 > 0) {
                                remoteViews.setTextViewText(identifier5, new SimpleDateFormat(string3).format(new Date(System.currentTimeMillis())));
                            }
                        }
                    }
                    return remoteViews;
                } catch (JSONException e) {
                    MyLog.e((Throwable) e);
                    return null;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                MyLog.e((Throwable) e2);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x04a9  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x04d0  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0202  */
    @SuppressLint({"NewApi"})
    private static GetNotificationResult getNotificationForLargeIcons(Context context, XmPushActionContainer xmPushActionContainer, byte[] bArr, RemoteViews remoteViews, PendingIntent pendingIntent) {
        boolean z;
        Bitmap onlinePictureResource;
        long currentTimeMillis;
        Uri uri;
        int i;
        GetNotificationResult getNotificationResult;
        Notification notification;
        Object field;
        List list;
        String str;
        Bitmap bitmap;
        Context context2 = context;
        RemoteViews remoteViews2 = remoteViews;
        GetNotificationResult getNotificationResult2 = new GetNotificationResult();
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        String targetPackage = getTargetPackage(xmPushActionContainer);
        Map<String, String> extra = metaInfo.getExtra();
        Notification.Builder builder = new Notification.Builder(context2);
        String[] determineTitleAndDespByDIP = determineTitleAndDespByDIP(context2, metaInfo);
        builder.setContentTitle(determineTitleAndDespByDIP[0]);
        builder.setContentText(determineTitleAndDespByDIP[1]);
        if (remoteViews2 != null) {
            builder.setContent(remoteViews2);
        } else if (Build.VERSION.SDK_INT >= 16 && extra != null && extra.containsKey("notification_style_type")) {
            builder = setNotificationStyle(context2, extra, builder, determineTitleAndDespByDIP[1]);
        }
        Notification.Builder notificationStyleAction = setNotificationStyleAction(builder, context2, xmPushActionContainer.getPackageName(), extra);
        notificationStyleAction.setWhen(System.currentTimeMillis());
        String str2 = extra == null ? null : extra.get("notification_show_when");
        if (!TextUtils.isEmpty(str2)) {
            notificationStyleAction.setShowWhen(Boolean.parseBoolean(str2));
        } else if (Build.VERSION.SDK_INT >= 24) {
            notificationStyleAction.setShowWhen(true);
        }
        notificationStyleAction.setContentIntent(pendingIntent);
        int iconId = getIconId(context2, targetPackage, "mipush_notification");
        int iconId2 = getIconId(context2, targetPackage, "mipush_small_notification");
        if (iconId <= 0 || iconId2 <= 0) {
            notificationStyleAction.setSmallIcon(getIdForSmallIcon(context2, targetPackage));
        } else {
            notificationStyleAction.setLargeIcon(getBitmapFromId(context2, iconId));
            notificationStyleAction.setSmallIcon(iconId2);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Bitmap onlinePictureResource2 = extra == null ? null : getOnlinePictureResource(context2, extra.get("notification_small_icon_uri"), true);
            if (onlinePictureResource2 != null) {
                Object callStaticMethod = JavaCalls.callStaticMethod("android.graphics.drawable.Icon", "createWithBitmap", onlinePictureResource2);
                if (callStaticMethod != null) {
                    JavaCalls.callMethod(notificationStyleAction, "setSmallIcon", callStaticMethod);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("miui.isGrayscaleIcon", true);
                    notificationStyleAction.addExtras(bundle);
                } else {
                    MyLog.w("failed te get small icon with url:" + extra.get("notification_small_icon_uri"));
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to get small icon url:");
                sb.append(extra == null ? null : extra.get("notification_small_icon_uri"));
                MyLog.w(sb.toString());
            }
            String str3 = extra == null ? null : extra.get("notification_small_icon_color");
            if (!TextUtils.isEmpty(str3)) {
                try {
                    JavaCalls.callMethod(notificationStyleAction, "setColor", Integer.valueOf(Color.parseColor(str3)));
                } catch (Exception e) {
                    MyLog.e((Throwable) e);
                }
            }
        }
        String str4 = extra == null ? null : extra.get("__dynamic_icon_uri");
        boolean z2 = (extra != null && Boolean.parseBoolean(extra.get("__adiom"))) || !MIUIUtils.isMIUI();
        if (str4 != null && z2) {
            if (str4.startsWith("http")) {
                NotificationIconHelper.GetIconResult iconFromUrl = NotificationIconHelper.getIconFromUrl(context2, str4, true);
                if (iconFromUrl != null) {
                    bitmap = iconFromUrl.bitmap;
                    getNotificationResult2.trafficSize = iconFromUrl.downloadSize;
                } else {
                    bitmap = null;
                }
            } else {
                bitmap = NotificationIconHelper.getIconFromUri(context2, str4);
            }
            if (bitmap != null) {
                notificationStyleAction.setLargeIcon(bitmap);
                z = true;
                onlinePictureResource = extra != null ? null : getOnlinePictureResource(context2, extra.get("notification_large_icon_uri"), true);
                if (onlinePictureResource != null) {
                    notificationStyleAction.setLargeIcon(onlinePictureResource);
                }
                if (extra != null && Build.VERSION.SDK_INT >= 24) {
                    str = extra.get("notification_group");
                    boolean parseBoolean = Boolean.parseBoolean(extra.get("notification_is_summary"));
                    if (TextUtils.isEmpty(str)) {
                        str = getTargetPackage(xmPushActionContainer);
                    }
                    JavaCalls.callMethod(notificationStyleAction, "setGroup", str);
                    JavaCalls.callMethod(notificationStyleAction, "setGroupSummary", Boolean.valueOf(parseBoolean));
                }
                notificationStyleAction.setAutoCancel(true);
                currentTimeMillis = System.currentTimeMillis();
                if (extra != null && extra.containsKey("ticker")) {
                    notificationStyleAction.setTicker(extra.get("ticker"));
                }
                if (currentTimeMillis - lastNotify <= 10000) {
                    lastNotify = currentTimeMillis;
                    i = metaInfo.notifyType;
                    if (hasLocalNotifyType(context2, targetPackage)) {
                        i = getLocalNotifyType(context2, targetPackage);
                    }
                    notificationStyleAction.setDefaults(i);
                    if (!(extra == null || (i & 1) == 0)) {
                        String str5 = extra.get("sound_uri");
                        if (!TextUtils.isEmpty(str5)) {
                            if (str5.startsWith("android.resource://" + targetPackage)) {
                                notificationStyleAction.setDefaults(i ^ 1);
                                uri = Uri.parse(str5);
                                notificationStyleAction.setSound(uri);
                                if (extra == null || Build.VERSION.SDK_INT < 26) {
                                    getNotificationResult = getNotificationResult2;
                                    if (extra != null && Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 26) {
                                        JavaCalls.callMethod(notificationStyleAction, "setPriority", Integer.valueOf(getPriority(extra)));
                                    }
                                } else {
                                    int timeout = getTimeout(extra);
                                    if (timeout > 0) {
                                        JavaCalls.callMethod(notificationStyleAction, "setTimeoutAfter", Long.valueOf((long) (timeout * 1000)));
                                    }
                                    String str6 = extra.get("channel_id");
                                    if (!TextUtils.isEmpty(str6) || context.getApplicationInfo().targetSdkVersion >= 26) {
                                        String str7 = "mipush_" + targetPackage + "_default";
                                        if (TextUtils.isEmpty(str6)) {
                                            str6 = str7;
                                        } else if (MIUIUtils.isMIUI()) {
                                            str6 = "mipush_" + targetPackage + "_" + str6;
                                        }
                                        JavaCalls.callMethod(notificationStyleAction, "setChannelId", str6);
                                        String channelName = getChannelName(context2, targetPackage, extra);
                                        int channelImportance = getChannelImportance(extra);
                                        NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
                                        getNotificationResult = getNotificationResult2;
                                        try {
                                            if (JavaCalls.callMethod(notificationManager, "getNotificationChannel", str6) == null) {
                                                Object newInstance = SystemUtils.loadClass(context2, "android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{str6, channelName, Integer.valueOf(channelImportance)});
                                                if (i > -100) {
                                                    notificationStyleAction.setSound((Uri) null, (AudioAttributes) null);
                                                    notificationStyleAction.setDefaults(0);
                                                    int i2 = i & 1;
                                                    int i3 = i & 2;
                                                    int i4 = i & 4;
                                                    if (i2 != 1) {
                                                        JavaCalls.callMethod(newInstance, "setSound", null, null);
                                                    }
                                                    if (uri != null) {
                                                        JavaCalls.callMethod(newInstance, "setSound", uri, Notification.AUDIO_ATTRIBUTES_DEFAULT);
                                                    }
                                                    Object[] objArr = new Object[1];
                                                    objArr[0] = Boolean.valueOf(i3 == 2);
                                                    JavaCalls.callMethod(newInstance, "enableVibration", objArr);
                                                    Object[] objArr2 = new Object[1];
                                                    objArr2[0] = Boolean.valueOf(i4 == 4);
                                                    JavaCalls.callMethod(newInstance, "enableLights", objArr2);
                                                }
                                                setChannelDescription(newInstance, extra);
                                                JavaCalls.callMethod(notificationManager, "createNotificationChannel", newInstance);
                                            }
                                            Object callMethod = JavaCalls.callMethod(notificationManager, "getNotificationChannels", new Object[0]);
                                            if (callMethod != null && (callMethod instanceof List) && (list = (List) callMethod) != null && list.size() > 0) {
                                                for (int i5 = 0; i5 < list.size(); i5++) {
                                                    Object obj = list.get(i5);
                                                    Object callMethod2 = JavaCalls.callMethod(obj, "getName", new Object[0]);
                                                    Object callMethod3 = JavaCalls.callMethod(obj, "getId", new Object[0]);
                                                    String appLabel = AppInfoUtils.getAppLabel(context2, targetPackage);
                                                    if (callMethod2 != null && callMethod3 != null && (callMethod2 instanceof CharSequence) && (callMethod3 instanceof String) && callMethod2.equals(appLabel)) {
                                                        if (((String) callMethod3).startsWith("mipush_" + targetPackage) && !callMethod3.equals(str7)) {
                                                            JavaCalls.callMethod(notificationManager, "deleteNotificationChannel", callMethod3);
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (Exception e2) {
                                            MyLog.e((Throwable) e2);
                                        }
                                    } else {
                                        getNotificationResult = getNotificationResult2;
                                    }
                                    String str8 = extra.get("background_color");
                                    if (!TextUtils.isEmpty(str8)) {
                                        try {
                                            int parseInt = Integer.parseInt(str8);
                                            notificationStyleAction.setOngoing(true);
                                            notificationStyleAction.setColor(parseInt);
                                            JavaCalls.callMethod(notificationStyleAction, "setColorized", true);
                                        } catch (Exception e3) {
                                            MyLog.e((Throwable) e3);
                                        }
                                    }
                                }
                                if (MIUIUtils.isXMS() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
                                    JavaCalls.callStaticMethod("miui.util.NotificationHelper", "setTargetPkg", context2, notificationStyleAction, getTargetPackage(xmPushActionContainer));
                                }
                                notification = notificationStyleAction.getNotification();
                                if (z && MIUIUtils.isMIUI()) {
                                    enableCustomIconForMiui(notification);
                                }
                                if (!(extra == null || (field = JavaCalls.getField(notification, "extraNotification")) == null)) {
                                    if (!TextUtils.isEmpty(extra.get("enable_keyguard"))) {
                                        JavaCalls.callMethod(field, "setEnableKeyguard", Boolean.valueOf(Boolean.parseBoolean(extra.get("enable_keyguard"))));
                                    }
                                    if (!TextUtils.isEmpty(extra.get("enable_float"))) {
                                        JavaCalls.callMethod(field, "setEnableFloat", Boolean.valueOf(Boolean.parseBoolean(extra.get("enable_float"))));
                                    }
                                }
                                GetNotificationResult getNotificationResult3 = getNotificationResult;
                                getNotificationResult3.notification = notification;
                                return getNotificationResult3;
                            }
                        }
                    }
                } else {
                    i = -100;
                }
                uri = null;
                if (extra == null || Build.VERSION.SDK_INT < 26) {
                }
                JavaCalls.callStaticMethod("miui.util.NotificationHelper", "setTargetPkg", context2, notificationStyleAction, getTargetPackage(xmPushActionContainer));
                notification = notificationStyleAction.getNotification();
                enableCustomIconForMiui(notification);
                if (!TextUtils.isEmpty(extra.get("enable_keyguard"))) {
                }
                if (!TextUtils.isEmpty(extra.get("enable_float"))) {
                }
                GetNotificationResult getNotificationResult32 = getNotificationResult;
                getNotificationResult32.notification = notification;
                return getNotificationResult32;
            }
        }
        z = false;
        if (extra != null) {
        }
        if (onlinePictureResource != null) {
        }
        str = extra.get("notification_group");
        boolean parseBoolean2 = Boolean.parseBoolean(extra.get("notification_is_summary"));
        if (TextUtils.isEmpty(str)) {
        }
        JavaCalls.callMethod(notificationStyleAction, "setGroup", str);
        JavaCalls.callMethod(notificationStyleAction, "setGroupSummary", Boolean.valueOf(parseBoolean2));
        notificationStyleAction.setAutoCancel(true);
        currentTimeMillis = System.currentTimeMillis();
        notificationStyleAction.setTicker(extra.get("ticker"));
        if (currentTimeMillis - lastNotify <= 10000) {
        }
        uri = null;
        if (extra == null || Build.VERSION.SDK_INT < 26) {
        }
        JavaCalls.callStaticMethod("miui.util.NotificationHelper", "setTargetPkg", context2, notificationStyleAction, getTargetPackage(xmPushActionContainer));
        notification = notificationStyleAction.getNotification();
        enableCustomIconForMiui(notification);
        if (!TextUtils.isEmpty(extra.get("enable_keyguard"))) {
        }
        if (!TextUtils.isEmpty(extra.get("enable_float"))) {
        }
        GetNotificationResult getNotificationResult322 = getNotificationResult;
        getNotificationResult322.notification = notification;
        return getNotificationResult322;
    }

    private static Bitmap getOnlinePictureResource(Context context, String str, boolean z) {
        Future submit = sThreadPool.submit(new DownloadOnlinePicTask(str, context, z));
        try {
            Bitmap bitmap = (Bitmap) submit.get(180, TimeUnit.SECONDS);
            if (bitmap != null) {
                return bitmap;
            }
            submit.cancel(true);
            return bitmap;
        } catch (InterruptedException e) {
            MyLog.e((Throwable) e);
        } catch (ExecutionException e2) {
            MyLog.e((Throwable) e2);
        } catch (TimeoutException e3) {
            MyLog.e((Throwable) e3);
        } catch (Throwable th) {
            submit.cancel(true);
            throw th;
        }
        submit.cancel(true);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x0148  */
    private static Intent getPendingIntentFromExtra(Context context, String str, int i, Map<String, String> map) {
        Intent intent;
        Intent intent2;
        String str2 = map.get(i < 2 ? "notification_style_button_left_notify_effect" : i < 3 ? "notification_style_button_mid_notify_effect" : "notification_style_button_right_notify_effect");
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (PushConstants.NOTIFICATION_CLICK_DEFAULT.equals(str2)) {
            try {
                intent = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e) {
                MyLog.e("Cause: " + e.getMessage());
            }
        } else {
            if (PushConstants.NOTIFICATION_CLICK_INTENT.equals(str2)) {
                String str3 = i < 2 ? "notification_style_button_left_intent_uri" : i < 3 ? "notification_style_button_mid_intent_uri" : "notification_style_button_right_intent_uri";
                String str4 = i < 2 ? "notification_style_button_left_intent_class" : i < 3 ? "notification_style_button_mid_intent_class" : "notification_style_button_right_intent_class";
                if (map.containsKey(str3)) {
                    String str5 = map.get(str3);
                    if (str5 != null) {
                        try {
                            intent2 = Intent.parseUri(str5, 1);
                            try {
                                intent2.setPackage(str);
                            } catch (URISyntaxException e2) {
                                e = e2;
                            }
                        } catch (URISyntaxException e3) {
                            e = e3;
                            intent2 = null;
                            MyLog.e("Cause: " + e.getMessage());
                            intent = intent2;
                            if (intent != null) {
                            }
                            return null;
                        }
                    }
                } else if (map.containsKey(str4)) {
                    Intent intent3 = new Intent();
                    intent3.setComponent(new ComponentName(str, map.get(str4)));
                    intent2 = intent3;
                }
                intent2 = null;
            } else {
                if (PushConstants.NOTIFICATION_CLICK_WEB_PAGE.equals(str2)) {
                    String str6 = map.get(i < 2 ? "notification_style_button_left_web_uri" : i < 3 ? "notification_style_button_mid_web_uri" : "notification_style_button_right_web_uri");
                    if (!TextUtils.isEmpty(str6)) {
                        String trim = str6.trim();
                        if (!trim.startsWith("http://") && !trim.startsWith("https://")) {
                            trim = "http://" + trim;
                        }
                        try {
                            String protocol = new URL(trim).getProtocol();
                            if ("http".equals(protocol) || "https".equals(protocol)) {
                                intent2 = new Intent("android.intent.action.VIEW");
                                try {
                                    intent2.setData(Uri.parse(trim));
                                    intent = setXiaomiBrowserDefault(context, intent2);
                                } catch (MalformedURLException e4) {
                                    e = e4;
                                    MyLog.e("Cause: " + e.getMessage());
                                    intent = intent2;
                                    if (intent != null) {
                                    }
                                    return null;
                                }
                            }
                        } catch (MalformedURLException e5) {
                            e = e5;
                            intent2 = null;
                            MyLog.e("Cause: " + e.getMessage());
                            intent = intent2;
                            if (intent != null) {
                            }
                            return null;
                        }
                    }
                }
                intent = null;
            }
            intent = intent2;
        }
        if (intent != null) {
            intent.addFlags(268435456);
            try {
                if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
                    return intent;
                }
            } catch (Exception e6) {
                MyLog.e("Cause: " + e6.getMessage());
            }
        }
        return null;
    }

    private static int getPriority(Map<String, String> map) {
        if (map == null) {
            return 0;
        }
        String str = map.get("notification_priority");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            MyLog.v("priority=" + str);
            return Integer.parseInt(str);
        } catch (Exception e) {
            MyLog.e("parsing notification priority error: " + e);
            return 0;
        }
    }

    private static PendingIntent getStylePendingIntent(Context context, String str, int i, Map<String, String> map) {
        Intent pendingIntentFromExtra;
        if (map == null || (pendingIntentFromExtra = getPendingIntentFromExtra(context, str, i, map)) == null) {
            return null;
        }
        return PendingIntent.getActivity(context, 0, pendingIntentFromExtra, 0);
    }

    static String getTargetPackage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo;
        if (!(!"com.xiaomi.xmsf".equals(xmPushActionContainer.packageName) || (metaInfo = xmPushActionContainer.getMetaInfo()) == null || metaInfo.getExtra() == null)) {
            String str = metaInfo.getExtra().get("miui_package_name");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return xmPushActionContainer.packageName;
    }

    private static int getTimeout(Map<String, String> map) {
        String str = map == null ? null : map.get("timeout");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    static boolean hasLocalNotifyType(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    public static boolean isApplicationForeground(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && Arrays.asList(next.pkgList).contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBusinessMessage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        return isIdVaild(metaInfo) && metaInfo.isIgnoreRegInfo();
    }

    private static boolean isIdVaild(PushMetaInfo pushMetaInfo) {
        if (pushMetaInfo == null) {
            return false;
        }
        String id = pushMetaInfo.getId();
        return !TextUtils.isEmpty(id) && id.length() == 22 && "satuigm".indexOf(id.charAt(0)) >= 0;
    }

    public static boolean isNPBMessage(XmPushActionContainer xmPushActionContainer) {
        return isBusinessMessage(xmPushActionContainer) || isNormalNotificationMessage(xmPushActionContainer) || isPassThoughMessage(xmPushActionContainer);
    }

    public static boolean isNormalNotificationMessage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        return isIdVaild(metaInfo) && metaInfo.passThrough == 0 && !isBusinessMessage(xmPushActionContainer);
    }

    public static boolean isNotifyForeground(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals(map.get("notify_foreground"));
    }

    public static boolean isPassThoughMessage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        return isIdVaild(metaInfo) && metaInfo.passThrough == 1 && !isBusinessMessage(xmPushActionContainer);
    }

    public static boolean isRegisterMessage(XmPushActionContainer xmPushActionContainer) {
        return xmPushActionContainer.getAction() == ActionType.Registration;
    }

    public static NotifyPushMessageInfo notifyPushMessage(Context context, XmPushActionContainer xmPushActionContainer, byte[] bArr) {
        Notification notification;
        Context context2 = context;
        XmPushActionContainer xmPushActionContainer2 = xmPushActionContainer;
        byte[] bArr2 = bArr;
        NotifyPushMessageInfo notifyPushMessageInfo = new NotifyPushMessageInfo();
        if (AppInfoUtils.getAppNotificationOp(context2, getTargetPackage(xmPushActionContainer)) == AppInfoUtils.AppNotificationOp.NOT_ALLOWED) {
            PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
            if (metaInfo != null) {
                PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4NeedDrop(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo.getId(), "Do not notify because user block " + getTargetPackage(xmPushActionContainer) + "‘s notification");
            }
            MyLog.w("Do not notify because user block " + getTargetPackage(xmPushActionContainer) + "‘s notification");
            return notifyPushMessageInfo;
        }
        final NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
        PushMetaInfo metaInfo2 = xmPushActionContainer.getMetaInfo();
        RemoteViews notificationForCustomLayout = getNotificationForCustomLayout(context, xmPushActionContainer, bArr);
        final int hashCode = ((getTargetPackage(xmPushActionContainer).hashCode() / 10) * 10) + (metaInfo2 != null ? metaInfo2.getNotifyId() : 0);
        PendingIntent clickedPendingIntent = getClickedPendingIntent(context2, xmPushActionContainer2, metaInfo2, bArr2, hashCode);
        if (clickedPendingIntent == null) {
            if (metaInfo2 != null) {
                PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4NeedDrop(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), "The click PendingIntent is null. ");
            }
            MyLog.w("The click PendingIntent is null. ");
            return notifyPushMessageInfo;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            GetNotificationResult notificationForLargeIcons = getNotificationForLargeIcons(context2, xmPushActionContainer2, bArr2, notificationForCustomLayout, clickedPendingIntent);
            notifyPushMessageInfo.traffic = notificationForLargeIcons.trafficSize;
            notifyPushMessageInfo.targetPkgName = getTargetPackage(xmPushActionContainer);
            notification = notificationForLargeIcons.notification;
        } else {
            notification = new Notification(getIdForSmallIcon(context2, getTargetPackage(xmPushActionContainer)), (CharSequence) null, System.currentTimeMillis());
            String[] determineTitleAndDespByDIP = determineTitleAndDespByDIP(context2, metaInfo2);
            try {
                notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context2, determineTitleAndDespByDIP[0], determineTitleAndDespByDIP[1], clickedPendingIntent});
            } catch (NoSuchMethodException e) {
                if (metaInfo2 != null) {
                    PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4ERROR(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), (Throwable) e);
                }
                MyLog.e((Throwable) e);
            } catch (IllegalAccessException e2) {
                if (metaInfo2 != null) {
                    PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4ERROR(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), (Throwable) e2);
                }
                MyLog.e((Throwable) e2);
            } catch (IllegalArgumentException e3) {
                if (metaInfo2 != null) {
                    PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4ERROR(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), (Throwable) e3);
                }
                MyLog.e((Throwable) e3);
            } catch (InvocationTargetException e4) {
                if (metaInfo2 != null) {
                    PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4ERROR(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), (Throwable) e4);
                }
                MyLog.e((Throwable) e4);
            }
            Map<String, String> extra = metaInfo2.getExtra();
            if (extra != null && extra.containsKey("ticker")) {
                notification.tickerText = extra.get("ticker");
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastNotify > 10000) {
                lastNotify = currentTimeMillis;
                int i = metaInfo2.notifyType;
                if (hasLocalNotifyType(context2, getTargetPackage(xmPushActionContainer))) {
                    i = getLocalNotifyType(context2, getTargetPackage(xmPushActionContainer));
                }
                notification.defaults = i;
                if (!(extra == null || (i & 1) == 0)) {
                    String str = extra.get("sound_uri");
                    if (!TextUtils.isEmpty(str)) {
                        if (str.startsWith("android.resource://" + getTargetPackage(xmPushActionContainer))) {
                            notification.defaults = i ^ 1;
                            notification.sound = Uri.parse(str);
                        }
                    }
                }
            }
            notification.flags |= 16;
            if (notificationForCustomLayout != null) {
                notification.contentView = notificationForCustomLayout;
            }
        }
        if (MIUIUtils.isMIUI() && Build.VERSION.SDK_INT >= 19) {
            if (!TextUtils.isEmpty(metaInfo2.getId())) {
                notification.extras.putString("message_id", metaInfo2.getId());
            }
            String str2 = metaInfo2.getInternal() == null ? null : metaInfo2.getInternal().get("score_info");
            if (!TextUtils.isEmpty(str2)) {
                notification.extras.putString("score_info", str2);
            }
            int i2 = -1;
            if (isNormalNotificationMessage(xmPushActionContainer)) {
                i2 = 1000;
            } else if (isBusinessMessage(xmPushActionContainer)) {
                i2 = 3000;
            }
            notification.extras.putString("eventMessageType", String.valueOf(i2));
            notification.extras.putString("target_package", getTargetPackage(xmPushActionContainer));
        }
        String str3 = metaInfo2.getExtra() == null ? null : metaInfo2.getExtra().get("message_count");
        if (MIUIUtils.isMIUI() && str3 != null) {
            try {
                setMessageCount(notification, Integer.parseInt(str3));
            } catch (NumberFormatException e5) {
                if (metaInfo2 != null) {
                    PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent4ERROR(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), (Throwable) e5);
                }
                MyLog.e((Throwable) e5);
            }
        }
        if (!MIUIUtils.isXMS() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
            setTargetPackage(notification, getTargetPackage(xmPushActionContainer));
        }
        notificationManager.notify(hashCode, notification);
        if (MIUIUtils.isMIUI() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
            checkNotificationAutoGroup(context2, notification, hashCode, getTargetPackage(xmPushActionContainer));
        }
        if (isBusinessMessage(xmPushActionContainer)) {
            PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), 3002, "try show business message");
        }
        if (isNormalNotificationMessage(xmPushActionContainer)) {
            PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent(xmPushActionContainer.getPackageName(), getInterfaceId(xmPushActionContainer), metaInfo2.getId(), 1002, "try show notification message");
        }
        if (Build.VERSION.SDK_INT < 26) {
            ScheduledJobManager instance = ScheduledJobManager.getInstance(context);
            instance.cancelJob(hashCode);
            int timeout = getTimeout(metaInfo2.getExtra());
            if (timeout > 0) {
                instance.addOneShootJob((ScheduledJobManager.Job) new ScheduledJobManager.Job() {
                    public int getJobId() {
                        return hashCode;
                    }

                    public void run() {
                        notificationManager.cancel(hashCode);
                    }
                }, timeout);
            }
        }
        Pair pair = new Pair(Integer.valueOf(hashCode), xmPushActionContainer2);
        synchronized (notifyContainerCache) {
            notifyContainerCache.add(pair);
            if (notifyContainerCache.size() > 100) {
                notifyContainerCache.remove();
            }
        }
        return notifyPushMessageInfo;
    }

    private static void setChannelDescription(Object obj, Map<String, String> map) {
        if (map != null && !TextUtils.isEmpty(map.get("channel_description"))) {
            JavaCalls.callMethod(obj, "setDescription", map.get("channel_description"));
        }
    }

    static void setLocalNotifyType(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    private static void setMessageCount(Notification notification, int i) {
        Object field = JavaCalls.getField(notification, "extraNotification");
        if (field != null) {
            JavaCalls.callMethod(field, "setMessageCount", Integer.valueOf(i));
        }
    }

    @TargetApi(16)
    private static Notification.Builder setNotificationStyle(Context context, Map<String, String> map, Notification.Builder builder, String str) {
        if ("2".equals(map.get("notification_style_type"))) {
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(builder);
            Bitmap onlinePictureResource = map == null ? null : getOnlinePictureResource(context, map.get("notification_bigPic_uri"), false);
            if (onlinePictureResource == null) {
                return builder;
            }
            bigPictureStyle.bigPicture(onlinePictureResource);
            bigPictureStyle.setSummaryText(str);
            bigPictureStyle.bigLargeIcon((Bitmap) null);
            builder.setStyle(bigPictureStyle);
        } else if ("1".equals(map.get("notification_style_type"))) {
            builder.setStyle(new Notification.BigTextStyle().bigText(str));
        }
        return builder;
    }

    @TargetApi(16)
    private static Notification.Builder setNotificationStyleAction(Notification.Builder builder, Context context, String str, Map<String, String> map) {
        PendingIntent stylePendingIntent = getStylePendingIntent(context, str, 1, map);
        if (stylePendingIntent != null && !TextUtils.isEmpty(map.get("notification_style_button_left_name"))) {
            builder.addAction(0, map.get("notification_style_button_left_name"), stylePendingIntent);
        }
        PendingIntent stylePendingIntent2 = getStylePendingIntent(context, str, 2, map);
        if (stylePendingIntent2 != null && !TextUtils.isEmpty(map.get("notification_style_button_mid_name"))) {
            builder.addAction(0, map.get("notification_style_button_mid_name"), stylePendingIntent2);
        }
        PendingIntent stylePendingIntent3 = getStylePendingIntent(context, str, 3, map);
        if (stylePendingIntent3 != null && !TextUtils.isEmpty(map.get("notification_style_button_right_name"))) {
            builder.addAction(0, map.get("notification_style_button_right_name"), stylePendingIntent3);
        }
        return builder;
    }

    private static Notification setTargetPackage(Notification notification, String str) {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[]{str});
        } catch (Exception e) {
            MyLog.e((Throwable) e);
        }
        return notification;
    }

    private static Intent setXiaomiBrowserDefault(Context context, Intent intent) {
        try {
            if (context.getPackageManager().getPackageInfo("com.android.browser", 4) != null) {
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            }
            return intent;
        } catch (PackageManager.NameNotFoundException e) {
            MyLog.e((Throwable) e);
            return intent;
        }
    }

    @TargetApi(23)
    private static void showGroupSummary(Context context, String str, Notification notification, NotificationManager notificationManager) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                String group = notification.getGroup();
                Notification notification2 = null;
                if (Build.VERSION.SDK_INT >= 26) {
                    if (JavaCalls.callMethod(notificationManager, "getNotificationChannel", "groupSummary") == null) {
                        JavaCalls.callMethod(notificationManager, "createNotificationChannel", SystemUtils.loadClass(context, "android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{"groupSummary", "group_summary", 3}));
                    }
                    if (!TextUtils.isEmpty(group)) {
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setContentTitle("GroupSummary").setContentText("GroupSummary").setLargeIcon(notification.getLargeIcon()).setSmallIcon(notification.getSmallIcon()).setAutoCancel(true).setGroup(group).setGroupSummary(true);
                        JavaCalls.callMethod(builder, "setChannelId", "groupSummary");
                        notification2 = builder.build();
                    }
                } else if (!TextUtils.isEmpty(group)) {
                    Notification.Builder builder2 = new Notification.Builder(context);
                    builder2.setContentTitle("GroupSummary").setContentText("GroupSummary").setLargeIcon(notification.getLargeIcon()).setSmallIcon(notification.getSmallIcon()).setPriority(0).setDefaults(-1).setAutoCancel(true).setGroup(group).setGroupSummary(true);
                    notification2 = builder2.build();
                }
                if (notification2 != null && !MIUIUtils.isXMS() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
                    setTargetPackage(notification2, str);
                    if (notification2.extras != null) {
                        notification2.extras.putString("target_package", str);
                    }
                }
                int groupSummaryNotifyId = getGroupSummaryNotifyId(str, group);
                if (notification2 != null) {
                    notificationManager.notify(groupSummaryNotifyId, notification2);
                    MyLog.i("GROUPSUMMARY  : showGroupSummary targetPackageName = " + str + " id = " + groupSummaryNotifyId);
                }
            }
        } catch (Exception e) {
            MyLog.e((Throwable) e);
        }
    }

    public static void uploadClearMessageData(Context context, LinkedList<? extends Object> linkedList) {
        if (linkedList != null && linkedList.size() > 0) {
            TinyDataHelper.cacheTinyData(context, "category_clear_notification", "clear_notification", (long) linkedList.size(), "");
        }
    }
}
