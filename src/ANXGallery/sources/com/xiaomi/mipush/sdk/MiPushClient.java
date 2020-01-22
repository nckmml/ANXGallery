package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.AppInfoUtils;
import com.xiaomi.channel.commonutils.android.DeviceInfo;
import com.xiaomi.channel.commonutils.android.MIUIUtils;
import com.xiaomi.channel.commonutils.android.PermissionUtils;
import com.xiaomi.channel.commonutils.android.PreferenceUtils;
import com.xiaomi.channel.commonutils.android.SharedPrefsCompat;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.mpcd.CDActionProviderHolder;
import com.xiaomi.push.mpcd.CDEntrance;
import com.xiaomi.push.service.OnlineConfig;
import com.xiaomi.push.service.PacketHelper;
import com.xiaomi.push.service.clientReport.MIPushEventDataProcessor;
import com.xiaomi.push.service.clientReport.MIPushPerfDataProcessor;
import com.xiaomi.push.service.clientReport.PushClientReportHelper;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.xmpush.Command;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import com.xiaomi.xmpush.thrift.ConfigKey;
import com.xiaomi.xmpush.thrift.NotificationType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.RegistrationReason;
import com.xiaomi.xmpush.thrift.XmPushActionCommand;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionSubscription;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscription;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MiPushClient {
    private static boolean isCrashHandlerSuggested;
    /* access modifiers changed from: private */
    public static Context sContext;
    private static long sCurMsgId = System.currentTimeMillis();

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        /* access modifiers changed from: protected */
        public String getCategory() {
            return this.category;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }
    }

    public static long accountSetTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("account_" + str, -1);
    }

    static synchronized void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString("accept_time", str + "," + str2);
            SharedPrefsCompat.apply(edit);
        }
    }

    static synchronized void addAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    static synchronized void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    private static void addPullNotificationTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        SharedPrefsCompat.apply(edit);
    }

    private static void addRegRequestTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        SharedPrefsCompat.apply(edit);
    }

    static synchronized void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static long aliasSetTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("alias_" + str, -1);
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (com.xiaomi.channel.commonutils.android.PermissionUtils.checkSelfPermission(r5, "android.permission.WRITE_EXTERNAL_STORAGE") == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (android.text.TextUtils.isEmpty(r4) != false) goto L_0x0055;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
    private static boolean checkPermission(Context context) {
        boolean z = true;
        if (context != null) {
            if (!MIUIUtils.isMIUI() && !"com.xiaomi.xmsf".equals(context.getPackageName()) && TextUtils.isEmpty(DeviceInfo.getGaid(context))) {
                if (context.getApplicationInfo().targetSdkVersion < 23 || Build.VERSION.SDK_INT < 23) {
                    String blockingGetIMEI = DeviceInfo.blockingGetIMEI(context);
                    String serialNum = DeviceInfo.getSerialNum();
                    if (TextUtils.isEmpty(blockingGetIMEI)) {
                    }
                } else if (!PermissionUtils.checkSelfPermission(context, "android.permission.READ_PHONE_STATE")) {
                }
            }
            if (!z) {
                MyLog.e("Because of lack of necessary information, mi push can't be initialized");
                ArrayList arrayList = new ArrayList();
                if (!PermissionUtils.checkSelfPermission(context, "android.permission.READ_PHONE_STATE")) {
                    arrayList.add("android.permission.READ_PHONE_STATE");
                }
                if (!PermissionUtils.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                }
                if (!arrayList.isEmpty()) {
                    String[] strArr = new String[arrayList.size()];
                    arrayList.toArray(strArr);
                    Intent intent = new Intent();
                    intent.setAction("com.xiaomi.mipush.ERROR");
                    intent.setPackage(context.getPackageName());
                    intent.putExtra("message_type", 5);
                    intent.putExtra("error_type", "error_lack_of_permission");
                    intent.putExtra("error_message", strArr);
                    context.sendBroadcast(intent);
                }
            }
            return z;
        }
        z = false;
        if (!z) {
        }
        return z;
    }

    protected static void clearExtras(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        PushServiceClient.getInstance(context).clearLocalNotificationType();
    }

    public static void clearNotification(Context context) {
        PushServiceClient.getInstance(context).clearNotification(-1);
    }

    public static void clearNotification(Context context, int i) {
        PushServiceClient.getInstance(context).clearNotification(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        PushServiceClient.getInstance(context).clearNotification(str, str2);
    }

    public static void disablePush(Context context) {
        PushServiceClient.getInstance(context).sendPushEnableDisableMessage(true);
    }

    public static void enablePush(Context context) {
        PushServiceClient.getInstance(context).sendPushEnableDisableMessage(false);
    }

    private static void forceHandleCrash() {
        boolean booleanValue = OnlineConfig.getInstance(sContext).getBooleanValue(ConfigKey.ForceHandleCrashSwitch.getValue(), false);
        if (!isCrashHandlerSuggested && booleanValue) {
            Thread.setDefaultUncaughtExceptionHandler(new MIPushCrashHandler(sContext));
        }
    }

    protected static String getAcceptTime(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString("accept_time", "00:00-23:59");
    }

    public static List<String> getAllAlias(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("alias_")) {
                arrayList.add(next.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("topic_") && !next.contains("**ALL**")) {
                arrayList.add(next.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("account_")) {
                arrayList.add(next.substring(8));
            }
        }
        return arrayList;
    }

    private static boolean getDefaultSwitch() {
        return MIUIUtils.isNotMIUI();
    }

    protected static boolean getOpenFCMPush(Context context) {
        checkNotNull(context, "context");
        return AssemblePushCollectionsManager.getInstance(context).getUserSwitch(AssemblePush.ASSEMBLE_PUSH_FCM);
    }

    protected static boolean getOpenHmsPush(Context context) {
        checkNotNull(context, "context");
        return AssemblePushCollectionsManager.getInstance(context).getUserSwitch(AssemblePush.ASSEMBLE_PUSH_HUAWEI);
    }

    protected static boolean getOpenOPPOPush(Context context) {
        checkNotNull(context, "context");
        return AssemblePushCollectionsManager.getInstance(context).getUserSwitch(AssemblePush.ASSEMBLE_PUSH_COS);
    }

    protected static boolean getOpenVIVOPush(Context context) {
        return AssemblePushCollectionsManager.getInstance(context).getUserSwitch(AssemblePush.ASSEMBLE_PUSH_FTOS);
    }

    public static String getRegId(Context context) {
        if (AppInfoHolder.getInstance(context).appRegistered()) {
            return AppInfoHolder.getInstance(context).getRegID();
        }
        return null;
    }

    private static void initEventPerfLogic(final Context context) {
        PushClientReportHelper.setUploader(new PushClientReportHelper.Uploader() {
            public void uploader(Context context, ClientUploadDataItem clientUploadDataItem) {
                MiTinyDataClient.upload(context, clientUploadDataItem);
            }
        });
        Config config = PushClientReportHelper.getConfig(context);
        ClientReportClient.init(context, config, new MIPushEventDataProcessor(context), new MIPushPerfDataProcessor(context));
        ActivityLifecycleCallbacksForCR.forceAttachApplication(context);
        ClientReportHelper.sendConfigInfo(context, config);
        OnlineConfig.getInstance(context).addOCUpdateCallbacks(new OnlineConfig.OCUpdateCallback(100, "perf event job update") {
            /* access modifiers changed from: protected */
            public void onCallback() {
                PushClientReportHelper.checkConfigChange(context);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x032d A[Catch:{ Throwable -> 0x0344 }] */
    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        try {
            MyLog.w("sdk_version = 3_7_0");
            if (miPushClientCallback != null) {
                PushMessageHandler.addPushCallbackClass(miPushClientCallback);
            }
            if (SystemUtils.isDebuggable(sContext)) {
                ManifestChecker.asynCheckManifest(sContext);
            }
            if (AppInfoHolder.getInstance(sContext).appRegistered(str, str2) || checkPermission(sContext)) {
                boolean z = AppInfoHolder.getInstance(sContext).getEnvType() != Constants.getEnvType();
                if (z || shouldSendRegRequest(sContext)) {
                    if (!z) {
                        if (AppInfoHolder.getInstance(sContext).appRegistered(str, str2) && !AppInfoHolder.getInstance(sContext).invalidated()) {
                            if (1 == PushMessageHelper.getPushMode(sContext)) {
                                checkNotNull(miPushClientCallback, "callback");
                                miPushClientCallback.onInitializeResult(0, (String) null, AppInfoHolder.getInstance(sContext).getRegID());
                            } else {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(AppInfoHolder.getInstance(sContext).getRegID());
                                PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(Command.COMMAND_REGISTER.value, arrayList, 0, (String) null, (String) null));
                            }
                            PushServiceClient.getInstance(sContext).awakePushService();
                            if (AppInfoHolder.getInstance(sContext).checkVersionNameChanged()) {
                                XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
                                xmPushActionNotification.setAppId(AppInfoHolder.getInstance(sContext).getAppID());
                                xmPushActionNotification.setType("client_info_update");
                                xmPushActionNotification.setId(PacketHelper.generatePacketID());
                                xmPushActionNotification.extra = new HashMap();
                                xmPushActionNotification.extra.put("app_version", AppInfoUtils.getVersionName(sContext, sContext.getPackageName()));
                                xmPushActionNotification.extra.put("app_version_code", Integer.toString(AppInfoUtils.getVersionCode(sContext, sContext.getPackageName())));
                                xmPushActionNotification.extra.put("push_sdk_vn", "3_7_0");
                                xmPushActionNotification.extra.put("push_sdk_vc", Integer.toString(30700));
                                String regResource = AppInfoHolder.getInstance(sContext).getRegResource();
                                if (!TextUtils.isEmpty(regResource)) {
                                    xmPushActionNotification.extra.put("deviceid", regResource);
                                }
                                PushServiceClient.getInstance(sContext).sendMessage(xmPushActionNotification, ActionType.Notification, false, (PushMetaInfo) null);
                            }
                            if (!PreferenceUtils.getSettingBoolean(sContext, "update_devId", false)) {
                                updateIMEI();
                                PreferenceUtils.setSettingBoolean(sContext, "update_devId", true);
                            }
                            String checkVirtDevId = DeviceInfo.checkVirtDevId(sContext);
                            if (!TextUtils.isEmpty(checkVirtDevId)) {
                                XmPushActionCommand xmPushActionCommand = new XmPushActionCommand();
                                xmPushActionCommand.setId(PacketHelper.generatePacketID());
                                xmPushActionCommand.setAppId(str);
                                xmPushActionCommand.setCmdName(Command.COMMAND_CHK_VDEVID.value);
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(DeviceInfo.getVirtDevId(sContext));
                                arrayList2.add(checkVirtDevId);
                                String str3 = "";
                                arrayList2.add(Build.MODEL != null ? Build.MODEL : str3);
                                if (Build.BOARD != null) {
                                    str3 = Build.BOARD;
                                }
                                arrayList2.add(str3);
                                xmPushActionCommand.setCmdArgs(arrayList2);
                                PushServiceClient.getInstance(sContext).sendMessage(xmPushActionCommand, ActionType.Command, false, (PushMetaInfo) null);
                            }
                            if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                                XmPushActionNotification xmPushActionNotification2 = new XmPushActionNotification();
                                xmPushActionNotification2.setAppId(AppInfoHolder.getInstance(sContext).getAppID());
                                xmPushActionNotification2.setType(NotificationType.PullOfflineMessage.value);
                                xmPushActionNotification2.setId(PacketHelper.generatePacketID());
                                xmPushActionNotification2.setRequireAck(false);
                                PushServiceClient.getInstance(sContext).sendMessage(xmPushActionNotification2, ActionType.Notification, false, (PushMetaInfo) null, false);
                                addPullNotificationTime(sContext);
                            }
                            addRegRequestTime(sContext);
                            scheduleOcVersionCheckJob();
                            scheduleDataCollectionJobs(sContext);
                            initEventPerfLogic(sContext);
                            SyncInfoHelper.tryToSyncInfo(sContext);
                            forceHandleCrash();
                            if (!sContext.getPackageName().equals("com.xiaomi.xmsf")) {
                                if (Logger.getUserLogger() != null) {
                                    Logger.setLogger(sContext, Logger.getUserLogger());
                                }
                                MyLog.setLogLevel(2);
                            }
                            operateSyncAction(context);
                            return;
                        }
                    }
                    String generateRandomString = XMStringUtils.generateRandomString(6);
                    AppInfoHolder.getInstance(sContext).clear();
                    AppInfoHolder.getInstance(sContext).setEnvType(Constants.getEnvType());
                    AppInfoHolder.getInstance(sContext).putAppIDAndToken(str, str2, generateRandomString);
                    MiTinyDataClient.MiTinyDataClientImp.getInstance().processPendingList("com.xiaomi.xmpushsdk.tinydataPending.appId");
                    clearExtras(sContext);
                    XmPushActionRegistration xmPushActionRegistration = new XmPushActionRegistration();
                    xmPushActionRegistration.setId(PacketHelper.generatePacketID());
                    xmPushActionRegistration.setAppId(str);
                    xmPushActionRegistration.setToken(str2);
                    xmPushActionRegistration.setPackageName(sContext.getPackageName());
                    xmPushActionRegistration.setDeviceId(generateRandomString);
                    xmPushActionRegistration.setAppVersion(AppInfoUtils.getVersionName(sContext, sContext.getPackageName()));
                    xmPushActionRegistration.setAppVersionCode(AppInfoUtils.getVersionCode(sContext, sContext.getPackageName()));
                    xmPushActionRegistration.setPushSdkVersionName("3_7_0");
                    xmPushActionRegistration.setPushSdkVersionCode(30700);
                    xmPushActionRegistration.setAndroidId(DeviceInfo.getAndroidId(sContext));
                    xmPushActionRegistration.setReason(RegistrationReason.Init);
                    if (!MIUIUtils.isGlobalRegion()) {
                        String quicklyGetIMEI = DeviceInfo.quicklyGetIMEI(sContext);
                        String quicklyGetSubIMEIS = DeviceInfo.quicklyGetSubIMEIS(sContext);
                        if (!TextUtils.isEmpty(quicklyGetIMEI)) {
                            if (MIUIUtils.isNotMIUI()) {
                                if (!TextUtils.isEmpty(quicklyGetSubIMEIS)) {
                                    quicklyGetIMEI = quicklyGetIMEI + "," + quicklyGetSubIMEIS;
                                }
                                xmPushActionRegistration.setImei(quicklyGetIMEI);
                            }
                            xmPushActionRegistration.setImeiMd5(XMStringUtils.getMd5Digest(quicklyGetIMEI) + "," + DeviceInfo.quicklyGetSubIMEISMd5(sContext));
                        }
                    }
                    xmPushActionRegistration.setSerial(DeviceInfo.getSerialNum());
                    int spaceId = DeviceInfo.getSpaceId();
                    if (spaceId >= 0) {
                        xmPushActionRegistration.setSpaceId(spaceId);
                    }
                    PushServiceClient.getInstance(sContext).register(xmPushActionRegistration, z);
                    sContext.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
                    addRegRequestTime(sContext);
                    scheduleOcVersionCheckJob();
                    scheduleDataCollectionJobs(sContext);
                    initEventPerfLogic(sContext);
                    SyncInfoHelper.tryToSyncInfo(sContext);
                    forceHandleCrash();
                    if (!sContext.getPackageName().equals("com.xiaomi.xmsf")) {
                    }
                    operateSyncAction(context);
                    return;
                }
                PushServiceClient.getInstance(sContext).awakePushService();
                MyLog.w("Could not send  register message within 5s repeatly .");
            }
        } catch (Throwable th) {
            MyLog.e(th);
        }
    }

    private static void operateSyncAction(Context context) {
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.DISABLE_PUSH))) {
            disablePush(sContext);
        }
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.ENABLE_PUSH))) {
            enablePush(sContext);
        }
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.UPLOAD_HUAWEI_TOKEN))) {
            syncAssemblePushToken(sContext);
        }
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.UPLOAD_FCM_TOKEN))) {
            syncAssembleFCMPushToken(sContext);
        }
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.UPLOAD_COS_TOKEN))) {
            syncAssembleCOSPushToken(context);
        }
        if ("syncing".equals(OperatePushHelper.getInstance(sContext).getSyncStatus(RetryType.UPLOAD_FTOS_TOKEN))) {
            syncAssembleFTOSPushToken(context);
        }
    }

    static void reInitialize(Context context, RegistrationReason registrationReason) {
        if (AppInfoHolder.getInstance(context).appRegistered()) {
            String generateRandomString = XMStringUtils.generateRandomString(6);
            String appID = AppInfoHolder.getInstance(context).getAppID();
            String appToken = AppInfoHolder.getInstance(context).getAppToken();
            AppInfoHolder.getInstance(context).clear();
            AppInfoHolder.getInstance(context).setEnvType(Constants.getEnvType());
            AppInfoHolder.getInstance(context).putAppIDAndToken(appID, appToken, generateRandomString);
            XmPushActionRegistration xmPushActionRegistration = new XmPushActionRegistration();
            xmPushActionRegistration.setId(PacketHelper.generatePacketID());
            xmPushActionRegistration.setAppId(appID);
            xmPushActionRegistration.setToken(appToken);
            xmPushActionRegistration.setDeviceId(generateRandomString);
            xmPushActionRegistration.setPackageName(context.getPackageName());
            xmPushActionRegistration.setAppVersion(AppInfoUtils.getVersionName(context, context.getPackageName()));
            xmPushActionRegistration.setReason(registrationReason);
            PushServiceClient.getInstance(context).register(xmPushActionRegistration, false);
        }
    }

    private static void registerNetworkReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            context.getApplicationContext().registerReceiver(new NetworkStatusReceiver((Object) null), intentFilter);
        } catch (Throwable th) {
            MyLog.e(th);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        registerPush(context, str, str2, new PushConfiguration());
    }

    public static void registerPush(Context context, final String str, final String str2, PushConfiguration pushConfiguration) {
        checkNotNull(context, "context");
        checkNotNull(str, "appID");
        checkNotNull(str2, "appToken");
        sContext = context.getApplicationContext();
        if (sContext == null) {
            sContext = context;
        }
        Context context2 = sContext;
        SystemUtils.initialize(context2);
        if (!NetworkStatusReceiver.isRegister()) {
            registerNetworkReceiver(sContext);
        }
        AssemblePushCollectionsManager.getInstance(sContext).setConfiguration(pushConfiguration);
        ScheduledJobManager.getInstance(context2).addOneShootJob(new Runnable() {
            public void run() {
                MiPushClient.initialize(MiPushClient.sContext, str, str2, (MiPushClientCallback) null);
            }
        });
    }

    static synchronized void removeAcceptTime(Context context) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("accept_time");
            SharedPrefsCompat.apply(edit);
        }
    }

    static synchronized void removeAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("account_" + str).commit();
        }
    }

    static synchronized void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("alias_" + str).commit();
        }
    }

    static synchronized void removeAllAccounts(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeAccount : getAllUserAccount(context)) {
                removeAccount(context, removeAccount);
            }
        }
    }

    static synchronized void removeAllAliases(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeAlias : getAllAlias(context)) {
                removeAlias(context, removeAlias);
            }
        }
    }

    static synchronized void removeAllTopics(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeTopic : getAllTopic(context)) {
                removeTopic(context, removeTopic);
            }
        }
    }

    static synchronized void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("topic_" + str).commit();
        }
    }

    static void reportIgnoreRegMessageClicked(Context context, String str, PushMetaInfo pushMetaInfo, String str2, String str3) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        if (TextUtils.isEmpty(str3)) {
            MyLog.e("do not report clicked message");
            return;
        }
        xmPushActionNotification.setAppId(str3);
        xmPushActionNotification.setType("bar:click");
        xmPushActionNotification.setId(str);
        xmPushActionNotification.setRequireAck(false);
        PushServiceClient.getInstance(context).sendMessage(xmPushActionNotification, ActionType.Notification, false, true, pushMetaInfo, true, str2, str3);
    }

    static void reportMessageClicked(Context context, String str, PushMetaInfo pushMetaInfo, String str2) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        if (!TextUtils.isEmpty(str2)) {
            xmPushActionNotification.setAppId(str2);
        } else if (AppInfoHolder.getInstance(context).checkAppInfo()) {
            xmPushActionNotification.setAppId(AppInfoHolder.getInstance(context).getAppID());
        } else {
            MyLog.e("do not report clicked message");
            return;
        }
        xmPushActionNotification.setType("bar:click");
        xmPushActionNotification.setId(str);
        xmPushActionNotification.setRequireAck(false);
        PushServiceClient.getInstance(context).sendMessage(xmPushActionNotification, ActionType.Notification, false, pushMetaInfo);
    }

    private static void scheduleDataCollectionJobs(Context context) {
        if (OnlineConfig.getInstance(sContext).getBooleanValue(ConfigKey.DataCollectionSwitch.getValue(), getDefaultSwitch())) {
            CDActionProviderHolder.getInstance().setCDActionProvider(new CDActionProviderImpl(context));
            ScheduledJobManager.getInstance(sContext).addOneShootJob((Runnable) new Runnable() {
                public void run() {
                    CDEntrance.start(MiPushClient.sContext);
                }
            }, 10);
        }
    }

    private static void scheduleOcVersionCheckJob() {
        ScheduledJobManager.getInstance(sContext).addRepeatJob(new OcVersionCheckJob(sContext), OnlineConfig.getInstance(sContext).getIntValue(ConfigKey.OcVersionCheckFrequency.getValue(), 86400), 5);
    }

    public static void setAlias(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, Command.COMMAND_SET_ALIAS.value, str, str2);
        }
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!Command.COMMAND_SET_ALIAS.value.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) >= 86400000) {
            if (Command.COMMAND_UNSET_ALIAS.value.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
                MyLog.w("Don't cancel alias for " + XMStringUtils.obfuscateString(arrayList.toString(), 3) + " is unseted");
            } else if (!Command.COMMAND_SET_ACCOUNT.value.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) >= 3600000) {
                if (!Command.COMMAND_UNSET_ACCOUNT.value.equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                    setCommand(context, str, (ArrayList<String>) arrayList, str3);
                    return;
                }
                MyLog.w("Don't cancel account for " + XMStringUtils.obfuscateString(arrayList.toString(), 3) + " is unseted");
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.onCommandResult(context, str3, str, 0, (String) null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(Command.COMMAND_SET_ACCOUNT.value, arrayList, 0, (String) null, str3));
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.onCommandResult(context, str3, str, 0, (String) null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(Command.COMMAND_SET_ALIAS.value, arrayList, 0, (String) null, str3));
        }
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(AppInfoHolder.getInstance(context).getAppID())) {
            XmPushActionCommand xmPushActionCommand = new XmPushActionCommand();
            xmPushActionCommand.setId(PacketHelper.generatePacketID());
            xmPushActionCommand.setAppId(AppInfoHolder.getInstance(context).getAppID());
            xmPushActionCommand.setCmdName(str);
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                xmPushActionCommand.addToCmdArgs(it.next());
            }
            xmPushActionCommand.setCategory(str2);
            xmPushActionCommand.setPackageName(context.getPackageName());
            PushServiceClient.getInstance(context).sendMessage(xmPushActionCommand, ActionType.Command, (PushMetaInfo) null);
        }
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, Command.COMMAND_SET_ACCOUNT.value, str, str2);
        }
    }

    private static boolean shouldPullNotification(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1)) > 300000;
    }

    private static boolean shouldSendRegRequest(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1)) > 5000;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return PushServiceClient.getInstance(context).shouldUseMIUIPush();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(AppInfoHolder.getInstance(context).getAppID()) && !TextUtils.isEmpty(str)) {
            if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) > 86400000) {
                XmPushActionSubscription xmPushActionSubscription = new XmPushActionSubscription();
                xmPushActionSubscription.setId(PacketHelper.generatePacketID());
                xmPushActionSubscription.setAppId(AppInfoHolder.getInstance(context).getAppID());
                xmPushActionSubscription.setTopic(str);
                xmPushActionSubscription.setPackageName(context.getPackageName());
                xmPushActionSubscription.setCategory(str2);
                PushServiceClient.getInstance(context).sendMessage(xmPushActionSubscription, ActionType.Subscription, (PushMetaInfo) null);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.onSubscribeResult(context, str2, 0, (String) null, str);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(Command.COMMAND_SUBSCRIBE_TOPIC.value, arrayList, 0, (String) null, (String) null));
            }
        }
    }

    public static void syncAssembleCOSPushToken(Context context) {
        PushServiceClient.getInstance(context).sendAssemblePushTokenCommon((String) null, RetryType.UPLOAD_COS_TOKEN, AssemblePush.ASSEMBLE_PUSH_COS);
    }

    public static void syncAssembleFCMPushToken(Context context) {
        PushServiceClient.getInstance(context).sendAssemblePushTokenCommon((String) null, RetryType.UPLOAD_FCM_TOKEN, AssemblePush.ASSEMBLE_PUSH_FCM);
    }

    public static void syncAssembleFTOSPushToken(Context context) {
        PushServiceClient.getInstance(context).sendAssemblePushTokenCommon((String) null, RetryType.UPLOAD_FTOS_TOKEN, AssemblePush.ASSEMBLE_PUSH_FTOS);
    }

    public static void syncAssemblePushToken(Context context) {
        PushServiceClient.getInstance(context).sendAssemblePushTokenCommon((String) null, RetryType.UPLOAD_HUAWEI_TOKEN, AssemblePush.ASSEMBLE_PUSH_HUAWEI);
    }

    public static long topicSubscribedTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("topic_" + str, -1);
    }

    public static void unregisterPush(Context context) {
        AssemblePushHelper.unregisterAssemblePush(context);
        OnlineConfig.getInstance(context).clearCallbacks();
        if (AppInfoHolder.getInstance(context).checkAppInfo()) {
            XmPushActionUnRegistration xmPushActionUnRegistration = new XmPushActionUnRegistration();
            xmPushActionUnRegistration.setId(PacketHelper.generatePacketID());
            xmPushActionUnRegistration.setAppId(AppInfoHolder.getInstance(context).getAppID());
            xmPushActionUnRegistration.setRegId(AppInfoHolder.getInstance(context).getRegID());
            xmPushActionUnRegistration.setToken(AppInfoHolder.getInstance(context).getAppToken());
            xmPushActionUnRegistration.setPackageName(context.getPackageName());
            PushServiceClient.getInstance(context).unregister(xmPushActionUnRegistration);
            PushMessageHandler.removeAllPushCallbackClass();
            AppInfoHolder.getInstance(context).invalidate();
            clearLocalNotificationType(context);
            clearNotification(context);
            clearExtras(context);
        }
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, Command.COMMAND_UNSET_ACCOUNT.value, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (AppInfoHolder.getInstance(context).checkAppInfo()) {
            if (topicSubscribedTime(context, str) < 0) {
                MyLog.w("Don't cancel subscribe for " + str + " is unsubscribed");
                return;
            }
            XmPushActionUnSubscription xmPushActionUnSubscription = new XmPushActionUnSubscription();
            xmPushActionUnSubscription.setId(PacketHelper.generatePacketID());
            xmPushActionUnSubscription.setAppId(AppInfoHolder.getInstance(context).getAppID());
            xmPushActionUnSubscription.setTopic(str);
            xmPushActionUnSubscription.setPackageName(context.getPackageName());
            xmPushActionUnSubscription.setCategory(str2);
            PushServiceClient.getInstance(context).sendMessage(xmPushActionUnSubscription, ActionType.UnSubscription, (PushMetaInfo) null);
        }
    }

    private static void updateIMEI() {
        new Thread(new Runnable() {
            public void run() {
                if (!MIUIUtils.isGlobalRegion() && DeviceInfo.blockingGetIMEI(MiPushClient.sContext) != null) {
                    XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
                    xmPushActionNotification.setAppId(AppInfoHolder.getInstance(MiPushClient.sContext).getAppID());
                    xmPushActionNotification.setType("client_info_update");
                    xmPushActionNotification.setId(PacketHelper.generatePacketID());
                    xmPushActionNotification.setExtra(new HashMap());
                    String str = "";
                    String blockingGetIMEI = DeviceInfo.blockingGetIMEI(MiPushClient.sContext);
                    if (!TextUtils.isEmpty(blockingGetIMEI)) {
                        str = str + XMStringUtils.getMd5Digest(blockingGetIMEI);
                    }
                    String blockingGetSubIMEISMd5 = DeviceInfo.blockingGetSubIMEISMd5(MiPushClient.sContext);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(blockingGetSubIMEISMd5)) {
                        str = str + "," + blockingGetSubIMEISMd5;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        xmPushActionNotification.getExtra().put("imei_md5", str);
                    }
                    int spaceId = DeviceInfo.getSpaceId();
                    if (spaceId >= 0) {
                        xmPushActionNotification.getExtra().put("space_id", Integer.toString(spaceId));
                    }
                    PushServiceClient.getInstance(MiPushClient.sContext).sendMessage(xmPushActionNotification, ActionType.Notification, false, (PushMetaInfo) null);
                }
            }
        }).start();
    }
}
