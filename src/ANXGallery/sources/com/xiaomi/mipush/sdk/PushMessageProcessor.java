package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.SharedPrefsCompat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.mipush.sdk.stat.PushStatClientManager;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.clientReport.PushClientReportHelper;
import com.xiaomi.push.service.clientReport.PushClientReportManager;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.NotificationType;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.RegistrationReason;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionAckNotification;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class PushMessageProcessor {
    private static Object lock = new Object();
    private static Queue<String> mCachedMsgIds;
    private static PushMessageProcessor sInstance;
    private Context sAppContext;

    private PushMessageProcessor(Context context) {
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
    }

    private void ackMessage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        XmPushActionAckMessage xmPushActionAckMessage = new XmPushActionAckMessage();
        xmPushActionAckMessage.setAppId(xmPushActionContainer.getAppid());
        xmPushActionAckMessage.setId(metaInfo.getId());
        xmPushActionAckMessage.setMessageTs(metaInfo.getMessageTs());
        if (!TextUtils.isEmpty(metaInfo.getTopic())) {
            xmPushActionAckMessage.setTopic(metaInfo.getTopic());
        }
        xmPushActionAckMessage.setDeviceStatus(XmPushThriftSerializeUtils.getDeviceStatus(this.sAppContext, xmPushActionContainer));
        PushServiceClient.getInstance(this.sAppContext).sendMessage(xmPushActionAckMessage, ActionType.AckMessage, false, xmPushActionContainer.getMetaInfo());
    }

    private void ackMessage(XmPushActionSendMessage xmPushActionSendMessage, XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
        XmPushActionAckMessage xmPushActionAckMessage = new XmPushActionAckMessage();
        xmPushActionAckMessage.setAppId(xmPushActionSendMessage.getAppId());
        xmPushActionAckMessage.setId(xmPushActionSendMessage.getId());
        xmPushActionAckMessage.setMessageTs(xmPushActionSendMessage.getMessage().getCreateAt());
        if (!TextUtils.isEmpty(xmPushActionSendMessage.getTopic())) {
            xmPushActionAckMessage.setTopic(xmPushActionSendMessage.getTopic());
        }
        if (!TextUtils.isEmpty(xmPushActionSendMessage.getAliasName())) {
            xmPushActionAckMessage.setAliasName(xmPushActionSendMessage.getAliasName());
        }
        xmPushActionAckMessage.setDeviceStatus(XmPushThriftSerializeUtils.getDeviceStatus(this.sAppContext, xmPushActionContainer));
        PushServiceClient.getInstance(this.sAppContext).sendMessage(xmPushActionAckMessage, ActionType.AckMessage, metaInfo);
    }

    public static PushMessageProcessor getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushMessageProcessor(context);
        }
        return sInstance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0145  */
    public static Intent getNotificationMessageIntent(Context context, String str, Map<String, String> map) {
        Intent intent;
        String str2;
        if (map != null && map.containsKey("notify_effect")) {
            String str3 = map.get("notify_effect");
            int i = -1;
            String str4 = map.get("intent_flag");
            try {
                if (!TextUtils.isEmpty(str4)) {
                    i = Integer.parseInt(str4);
                }
            } catch (NumberFormatException e) {
                MyLog.e("Cause by intent_flag: " + e.getMessage());
            }
            if (PushConstants.NOTIFICATION_CLICK_DEFAULT.equals(str3)) {
                try {
                    intent = context.getPackageManager().getLaunchIntentForPackage(str);
                } catch (Exception e2) {
                    MyLog.e("Cause: " + e2.getMessage());
                }
            } else if (!PushConstants.NOTIFICATION_CLICK_INTENT.equals(str3)) {
                if (PushConstants.NOTIFICATION_CLICK_WEB_PAGE.equals(str3) && (str2 = map.get("web_uri")) != null) {
                    String trim = str2.trim();
                    if (!trim.startsWith("http://") && !trim.startsWith("https://")) {
                        trim = "http://" + trim;
                    }
                    try {
                        String protocol = new URL(trim).getProtocol();
                        if ("http".equals(protocol) || "https".equals(protocol)) {
                            intent = new Intent("android.intent.action.VIEW");
                            try {
                                intent.setData(Uri.parse(trim));
                            } catch (MalformedURLException e3) {
                                e = e3;
                            }
                        }
                    } catch (MalformedURLException e4) {
                        e = e4;
                        intent = null;
                        MyLog.e("Cause: " + e.getMessage());
                        if (intent != null) {
                        }
                        return null;
                    }
                }
                intent = null;
            } else if (map.containsKey("intent_uri")) {
                String str5 = map.get("intent_uri");
                if (str5 != null) {
                    try {
                        intent = Intent.parseUri(str5, 1);
                        try {
                            intent.setPackage(str);
                        } catch (URISyntaxException e5) {
                            e = e5;
                        }
                    } catch (URISyntaxException e6) {
                        e = e6;
                        intent = null;
                        MyLog.e("Cause: " + e.getMessage());
                        if (intent != null) {
                        }
                        return null;
                    }
                }
                intent = null;
            } else {
                if (map.containsKey("class_name")) {
                    Intent intent2 = new Intent();
                    intent2.setComponent(new ComponentName(str, map.get("class_name")));
                    intent = intent2;
                }
                intent = null;
            }
            if (intent != null) {
                if (i >= 0) {
                    intent.setFlags(i);
                }
                intent.addFlags(268435456);
                try {
                    if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
                        return intent;
                    }
                    MyLog.w("not resolve activity:" + intent);
                } catch (Exception e7) {
                    MyLog.e("Cause: " + e7.getMessage());
                }
            }
        }
        return null;
    }

    private static boolean isDuplicateMessage(Context context, String str) {
        synchronized (lock) {
            AppInfoHolder.getInstance(context);
            SharedPreferences sharedPreferences = AppInfoHolder.getSharedPreferences(context);
            if (mCachedMsgIds == null) {
                String[] split = sharedPreferences.getString("pref_msg_ids", "").split(",");
                mCachedMsgIds = new LinkedList();
                for (String add : split) {
                    mCachedMsgIds.add(add);
                }
            }
            if (mCachedMsgIds.contains(str)) {
                return true;
            }
            mCachedMsgIds.add(str);
            if (mCachedMsgIds.size() > 25) {
                mCachedMsgIds.poll();
            }
            String join = XMStringUtils.join((Collection<?>) mCachedMsgIds, ",");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("pref_msg_ids", join);
            SharedPrefsCompat.apply(edit);
            return false;
        }
    }

    private boolean isHybridMsg(XmPushActionContainer xmPushActionContainer) {
        if (!TextUtils.equals("com.miui.hybrid", xmPushActionContainer.getPackageName()) && !TextUtils.equals("com.miui.hybrid.loader", xmPushActionContainer.getPackageName())) {
            return false;
        }
        Map<String, String> extra = xmPushActionContainer.getMetaInfo() == null ? null : xmPushActionContainer.getMetaInfo().getExtra();
        if (extra == null) {
            return false;
        }
        String str = extra.get("push_server_action");
        return TextUtils.equals(str, "hybrid_message") || TextUtils.equals(str, "platform_message");
    }

    /* JADX WARNING: type inference failed for: r4v13, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r12v0, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v7, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r12v1, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v9, types: [java.util.List, java.util.ArrayList] */
    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:298)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:64)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1257)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    private com.xiaomi.mipush.sdk.PushMessageHandler.PushMessageInterface processMessage(com.xiaomi.xmpush.thrift.XmPushActionContainer r20, boolean r21, byte[] r22, java.lang.String r23, int r24) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r0 = r21
            r3 = r22
            r8 = r23
            r9 = r24
            r10 = 0
            android.content.Context r4 = r1.sAppContext     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            org.apache.thrift.TBase r4 = com.xiaomi.mipush.sdk.PushContainerHelper.getResponseMessageBodyFromContainer(r4, r2)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            if (r4 != 0) goto L_0x0041
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            r0.<init>()     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            java.lang.String r3 = "receiving an un-recognized message. "
            r0.append(r3)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            com.xiaomi.xmpush.thrift.ActionType r3 = r2.action     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            r0.append(r3)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            java.lang.String r0 = r0.toString()     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.String) r0)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            android.content.Context r0 = r1.sAppContext     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            android.content.Context r3 = r1.sAppContext     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            java.lang.String r5 = "receiving an un-recognized message."
            r0.reportEvent4ERROR((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r8, (java.lang.String) r5)     // Catch:{ DecryptException -> 0x09d5, TException -> 0x09b8 }
            return r10
        L_0x0041:
            com.xiaomi.xmpush.thrift.ActionType r5 = r20.getAction()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "processing a message, action="
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.xiaomi.channel.commonutils.logger.MyLog.w(r6)
            int[] r6 = com.xiaomi.mipush.sdk.PushMessageProcessor.AnonymousClass2.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType
            int r5 = r5.ordinal()
            r5 = r6[r5]
            r6 = 1
            r11 = 0
            r7 = 0
            switch(r5) {
                case 1: goto L_0x072f;
                case 2: goto L_0x067d;
                case 3: goto L_0x0662;
                case 4: goto L_0x0629;
                case 5: goto L_0x05f0;
                case 6: goto L_0x04cc;
                case 7: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x09b7
        L_0x006a:
            android.content.Context r0 = r1.sAppContext
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r5 = r1.sAppContext
            com.xiaomi.xmpush.thrift.ActionType r8 = com.xiaomi.xmpush.thrift.ActionType.Notification
            int r3 = r3.length
            com.xiaomi.push.clientreport.PerfMessageHelper.collectPerfData(r0, r5, r4, r8, r3)
            boolean r0 = r4 instanceof com.xiaomi.xmpush.thrift.XmPushActionAckNotification
            if (r0 == 0) goto L_0x0216
            com.xiaomi.xmpush.thrift.XmPushActionAckNotification r4 = (com.xiaomi.xmpush.thrift.XmPushActionAckNotification) r4
            java.lang.String r0 = r4.getId()
            com.xiaomi.xmpush.thrift.NotificationType r2 = com.xiaomi.xmpush.thrift.NotificationType.DisablePushMessage
            java.lang.String r2 = r2.value
            java.lang.String r3 = r4.type
            boolean r2 = r2.equalsIgnoreCase(r3)
            r3 = 10
            if (r2 == 0) goto L_0x0147
            long r4 = r4.errorCode
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x00eb
            java.lang.Class<com.xiaomi.mipush.sdk.OperatePushHelper> r2 = com.xiaomi.mipush.sdk.OperatePushHelper.class
            monitor-enter(r2)
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x00e8 }
            boolean r3 = r3.isMessageOperating(r0)     // Catch:{ all -> 0x00e8 }
            if (r3 == 0) goto L_0x00e5
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x00e8 }
            r3.removeOperateMessage(r0)     // Catch:{ all -> 0x00e8 }
            java.lang.String r0 = "syncing"
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.RetryType r4 = com.xiaomi.mipush.sdk.RetryType.DISABLE_PUSH     // Catch:{ all -> 0x00e8 }
            java.lang.String r3 = r3.getSyncStatus(r4)     // Catch:{ all -> 0x00e8 }
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x00e8 }
            if (r0 == 0) goto L_0x00e5
            android.content.Context r0 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.OperatePushHelper r0 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r0)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.RetryType r3 = com.xiaomi.mipush.sdk.RetryType.DISABLE_PUSH     // Catch:{ all -> 0x00e8 }
            java.lang.String r4 = "synced"
            r0.putSyncStatus(r3, r4)     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.MiPushClient.clearNotification(r0)     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.MiPushClient.clearLocalNotificationType(r0)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.PushMessageHandler.removeAllPushCallbackClass()     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.sAppContext     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.PushServiceClient r0 = com.xiaomi.mipush.sdk.PushServiceClient.getInstance(r0)     // Catch:{ all -> 0x00e8 }
            r0.closePush()     // Catch:{ all -> 0x00e8 }
        L_0x00e5:
            monitor-exit(r2)     // Catch:{ all -> 0x00e8 }
            goto L_0x09b7
        L_0x00e8:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00e8 }
            throw r0
        L_0x00eb:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.OperatePushHelper r2 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r2)
            com.xiaomi.mipush.sdk.RetryType r4 = com.xiaomi.mipush.sdk.RetryType.DISABLE_PUSH
            java.lang.String r2 = r2.getSyncStatus(r4)
            java.lang.String r4 = "syncing"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x013c
            java.lang.Class<com.xiaomi.mipush.sdk.OperatePushHelper> r2 = com.xiaomi.mipush.sdk.OperatePushHelper.class
            monitor-enter(r2)
            android.content.Context r4 = r1.sAppContext     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.OperatePushHelper r4 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r4)     // Catch:{ all -> 0x0139 }
            boolean r4 = r4.isMessageOperating(r0)     // Catch:{ all -> 0x0139 }
            if (r4 == 0) goto L_0x0136
            android.content.Context r4 = r1.sAppContext     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.OperatePushHelper r4 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r4)     // Catch:{ all -> 0x0139 }
            int r4 = r4.getRetryCount(r0)     // Catch:{ all -> 0x0139 }
            if (r4 >= r3) goto L_0x012d
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x0139 }
            r3.increaseRetryCount(r0)     // Catch:{ all -> 0x0139 }
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.PushServiceClient r3 = com.xiaomi.mipush.sdk.PushServiceClient.getInstance(r3)     // Catch:{ all -> 0x0139 }
            r3.sendPushEnableDisableMessage(r6, r0)     // Catch:{ all -> 0x0139 }
            goto L_0x0136
        L_0x012d:
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x0139 }
            r3.removeOperateMessage(r0)     // Catch:{ all -> 0x0139 }
        L_0x0136:
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            goto L_0x09b7
        L_0x0139:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            throw r0
        L_0x013c:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.OperatePushHelper r2 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r2)
            r2.removeOperateMessage(r0)
            goto L_0x09b7
        L_0x0147:
            com.xiaomi.xmpush.thrift.NotificationType r2 = com.xiaomi.xmpush.thrift.NotificationType.EnablePushMessage
            java.lang.String r2 = r2.value
            java.lang.String r5 = r4.type
            boolean r2 = r2.equalsIgnoreCase(r5)
            if (r2 == 0) goto L_0x01f4
            long r4 = r4.errorCode
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x0198
            java.lang.Class<com.xiaomi.mipush.sdk.OperatePushHelper> r2 = com.xiaomi.mipush.sdk.OperatePushHelper.class
            monitor-enter(r2)
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x0195 }
            boolean r3 = r3.isMessageOperating(r0)     // Catch:{ all -> 0x0195 }
            if (r3 == 0) goto L_0x0192
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x0195 }
            r3.removeOperateMessage(r0)     // Catch:{ all -> 0x0195 }
            java.lang.String r0 = "syncing"
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.RetryType r4 = com.xiaomi.mipush.sdk.RetryType.ENABLE_PUSH     // Catch:{ all -> 0x0195 }
            java.lang.String r3 = r3.getSyncStatus(r4)     // Catch:{ all -> 0x0195 }
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0195 }
            if (r0 == 0) goto L_0x0192
            android.content.Context r0 = r1.sAppContext     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.OperatePushHelper r0 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r0)     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.RetryType r3 = com.xiaomi.mipush.sdk.RetryType.ENABLE_PUSH     // Catch:{ all -> 0x0195 }
            java.lang.String r4 = "synced"
            r0.putSyncStatus(r3, r4)     // Catch:{ all -> 0x0195 }
        L_0x0192:
            monitor-exit(r2)     // Catch:{ all -> 0x0195 }
            goto L_0x09b7
        L_0x0195:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0195 }
            throw r0
        L_0x0198:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.OperatePushHelper r2 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r2)
            com.xiaomi.mipush.sdk.RetryType r4 = com.xiaomi.mipush.sdk.RetryType.ENABLE_PUSH
            java.lang.String r2 = r2.getSyncStatus(r4)
            java.lang.String r4 = "syncing"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x01e9
            java.lang.Class<com.xiaomi.mipush.sdk.OperatePushHelper> r2 = com.xiaomi.mipush.sdk.OperatePushHelper.class
            monitor-enter(r2)
            android.content.Context r4 = r1.sAppContext     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.OperatePushHelper r4 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r4)     // Catch:{ all -> 0x01e6 }
            boolean r4 = r4.isMessageOperating(r0)     // Catch:{ all -> 0x01e6 }
            if (r4 == 0) goto L_0x01e3
            android.content.Context r4 = r1.sAppContext     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.OperatePushHelper r4 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r4)     // Catch:{ all -> 0x01e6 }
            int r4 = r4.getRetryCount(r0)     // Catch:{ all -> 0x01e6 }
            if (r4 >= r3) goto L_0x01da
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x01e6 }
            r3.increaseRetryCount(r0)     // Catch:{ all -> 0x01e6 }
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.PushServiceClient r3 = com.xiaomi.mipush.sdk.PushServiceClient.getInstance(r3)     // Catch:{ all -> 0x01e6 }
            r3.sendPushEnableDisableMessage(r7, r0)     // Catch:{ all -> 0x01e6 }
            goto L_0x01e3
        L_0x01da:
            android.content.Context r3 = r1.sAppContext     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.OperatePushHelper r3 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r3)     // Catch:{ all -> 0x01e6 }
            r3.removeOperateMessage(r0)     // Catch:{ all -> 0x01e6 }
        L_0x01e3:
            monitor-exit(r2)     // Catch:{ all -> 0x01e6 }
            goto L_0x09b7
        L_0x01e6:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01e6 }
            throw r0
        L_0x01e9:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.OperatePushHelper r2 = com.xiaomi.mipush.sdk.OperatePushHelper.getInstance(r2)
            r2.removeOperateMessage(r0)
            goto L_0x09b7
        L_0x01f4:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.ThirdPartyRegUpdate
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0205
            r1.processSendTokenAckNotification(r4)
            goto L_0x09b7
        L_0x0205:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.UploadTinyData
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x09b7
            r1.processStatDataACK(r4)
            goto L_0x09b7
        L_0x0216:
            boolean r0 = r4 instanceof com.xiaomi.xmpush.thrift.XmPushActionNotification
            if (r0 == 0) goto L_0x09b7
            com.xiaomi.xmpush.thrift.XmPushActionNotification r4 = (com.xiaomi.xmpush.thrift.XmPushActionNotification) r4
            java.lang.String r0 = r4.type
            java.lang.String r3 = "registration id expired"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x02b0
            android.content.Context r0 = r1.sAppContext
            java.util.List r0 = com.xiaomi.mipush.sdk.MiPushClient.getAllAlias(r0)
            android.content.Context r2 = r1.sAppContext
            java.util.List r2 = com.xiaomi.mipush.sdk.MiPushClient.getAllTopic(r2)
            android.content.Context r3 = r1.sAppContext
            java.util.List r3 = com.xiaomi.mipush.sdk.MiPushClient.getAllUserAccount(r3)
            android.content.Context r4 = r1.sAppContext
            java.lang.String r4 = com.xiaomi.mipush.sdk.MiPushClient.getAcceptTime(r4)
            android.content.Context r5 = r1.sAppContext
            com.xiaomi.xmpush.thrift.RegistrationReason r8 = com.xiaomi.xmpush.thrift.RegistrationReason.RegIdExpired
            com.xiaomi.mipush.sdk.MiPushClient.reInitialize(r5, r8)
            java.util.Iterator r0 = r0.iterator()
        L_0x0249:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0260
            java.lang.Object r5 = r0.next()
            java.lang.String r5 = (java.lang.String) r5
            android.content.Context r8 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.removeAlias(r8, r5)
            android.content.Context r8 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.setAlias(r8, r5, r10)
            goto L_0x0249
        L_0x0260:
            java.util.Iterator r0 = r2.iterator()
        L_0x0264:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x027b
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            android.content.Context r5 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.removeTopic(r5, r2)
            android.content.Context r5 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.subscribe(r5, r2, r10)
            goto L_0x0264
        L_0x027b:
            java.util.Iterator r0 = r3.iterator()
        L_0x027f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0296
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            android.content.Context r3 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.removeAccount(r3, r2)
            android.content.Context r3 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.setUserAccount(r3, r2, r10)
            goto L_0x027f
        L_0x0296:
            java.lang.String r0 = ","
            java.lang.String[] r0 = r4.split(r0)
            int r2 = r0.length
            r3 = 2
            if (r2 != r3) goto L_0x09b7
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.removeAcceptTime(r2)
            android.content.Context r2 = r1.sAppContext
            r3 = r0[r7]
            r0 = r0[r6]
            com.xiaomi.mipush.sdk.MiPushClient.addAcceptTime(r2, r3, r0)
            goto L_0x09b7
        L_0x02b0:
            java.lang.String r0 = r4.type
            java.lang.String r3 = "client_info_update_ok"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x02e3
            java.util.Map r0 = r4.getExtra()
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "app_version"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "app_version"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            r2.updateVersionName(r0)
            goto L_0x09b7
        L_0x02e3:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.AwakeApp
            java.lang.String r0 = r0.value
            java.lang.String r3 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x0332
            boolean r0 = r20.isEncryptAction()
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "awake_info"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "awake_info"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r3 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            java.lang.String r3 = r3.getAppID()
            android.content.Context r4 = r1.sAppContext
            com.xiaomi.push.service.OnlineConfig r4 = com.xiaomi.push.service.OnlineConfig.getInstance(r4)
            com.xiaomi.xmpush.thrift.ConfigKey r5 = com.xiaomi.xmpush.thrift.ConfigKey.AwakeInfoUploadWaySwitch
            int r5 = r5.getValue()
            int r4 = r4.getIntValue(r5, r7)
            com.xiaomi.mipush.sdk.AwakeHelper.doAwAppLogic(r2, r3, r4, r0)
            goto L_0x09b7
        L_0x0332:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.NormalClientConfigUpdate
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x035b
            com.xiaomi.xmpush.thrift.XmPushActionNormalConfig r0 = new com.xiaomi.xmpush.thrift.XmPushActionNormalConfig
            r0.<init>()
            byte[] r2 = r4.getBinaryExtra()     // Catch:{ TException -> 0x0355 }
            com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils.convertByteArrayToThriftObject(r0, r2)     // Catch:{ TException -> 0x0355 }
            android.content.Context r2 = r1.sAppContext     // Catch:{ TException -> 0x0355 }
            com.xiaomi.push.service.OnlineConfig r2 = com.xiaomi.push.service.OnlineConfig.getInstance(r2)     // Catch:{ TException -> 0x0355 }
            com.xiaomi.push.service.OnlineConfigHelper.updateNormalConfigs(r2, r0)     // Catch:{ TException -> 0x0355 }
            goto L_0x09b7
        L_0x0355:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            goto L_0x09b7
        L_0x035b:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.CustomClientConfigUpdate
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0384
            com.xiaomi.xmpush.thrift.XmPushActionCustomConfig r0 = new com.xiaomi.xmpush.thrift.XmPushActionCustomConfig
            r0.<init>()
            byte[] r2 = r4.getBinaryExtra()     // Catch:{ TException -> 0x037e }
            com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils.convertByteArrayToThriftObject(r0, r2)     // Catch:{ TException -> 0x037e }
            android.content.Context r2 = r1.sAppContext     // Catch:{ TException -> 0x037e }
            com.xiaomi.push.service.OnlineConfig r2 = com.xiaomi.push.service.OnlineConfig.getInstance(r2)     // Catch:{ TException -> 0x037e }
            com.xiaomi.push.service.OnlineConfigHelper.updateCustomConfigs(r2, r0)     // Catch:{ TException -> 0x037e }
            goto L_0x09b7
        L_0x037e:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            goto L_0x09b7
        L_0x0384:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.SyncInfoResult
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0397
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.SyncInfoHelper.saveInfo(r0, r4)
            goto L_0x09b7
        L_0x0397:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.ForceSync
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x03af
            java.lang.String r0 = "receive force sync notification"
            com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.SyncInfoHelper.doSyncInfoAsync(r0, r7)
            goto L_0x09b7
        L_0x03af:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.CancelPushMessage
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0431
            java.util.Map r0 = r4.getExtra()
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = com.xiaomi.push.service.PushConstants.PUSH_NOTIFY_ID
            boolean r0 = r0.containsKey(r2)
            r2 = -2
            if (r0 == 0) goto L_0x03ea
            java.util.Map r0 = r4.getExtra()
            java.lang.String r3 = com.xiaomi.push.service.PushConstants.PUSH_NOTIFY_ID
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x03ea
            int r2 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x03e5 }
            goto L_0x03ea
        L_0x03e5:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x03ea:
            r0 = -1
            if (r2 < r0) goto L_0x03f4
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.clearNotification(r0, r2)
            goto L_0x09b7
        L_0x03f4:
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = com.xiaomi.push.service.PushConstants.PUSH_TITLE
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x040d
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = com.xiaomi.push.service.PushConstants.PUSH_TITLE
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x040f
        L_0x040d:
            java.lang.String r0 = ""
        L_0x040f:
            java.util.Map r2 = r4.getExtra()
            java.lang.String r3 = com.xiaomi.push.service.PushConstants.PUSH_DESCRIPTION
            boolean r2 = r2.containsKey(r3)
            if (r2 == 0) goto L_0x0428
            java.util.Map r2 = r4.getExtra()
            java.lang.String r3 = com.xiaomi.push.service.PushConstants.PUSH_DESCRIPTION
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x042a
        L_0x0428:
            java.lang.String r2 = ""
        L_0x042a:
            android.content.Context r3 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.clearNotification(r3, r0, r2)
            goto L_0x09b7
        L_0x0431:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.HybridRegisterResult
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0456
            com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult r0 = new com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult     // Catch:{ TException -> 0x0450 }
            r0.<init>()     // Catch:{ TException -> 0x0450 }
            byte[] r2 = r4.getBinaryExtra()     // Catch:{ TException -> 0x0450 }
            com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils.convertByteArrayToThriftObject(r0, r2)     // Catch:{ TException -> 0x0450 }
            android.content.Context r2 = r1.sAppContext     // Catch:{ TException -> 0x0450 }
            com.xiaomi.mipush.sdk.MiPushClient4Hybrid.onReceiveRegisterResult(r2, r0)     // Catch:{ TException -> 0x0450 }
            goto L_0x09b7
        L_0x0450:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            goto L_0x09b7
        L_0x0456:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.HybridUnregisterResult
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x047b
            com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult r0 = new com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult     // Catch:{ TException -> 0x0475 }
            r0.<init>()     // Catch:{ TException -> 0x0475 }
            byte[] r2 = r4.getBinaryExtra()     // Catch:{ TException -> 0x0475 }
            com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils.convertByteArrayToThriftObject(r0, r2)     // Catch:{ TException -> 0x0475 }
            android.content.Context r2 = r1.sAppContext     // Catch:{ TException -> 0x0475 }
            com.xiaomi.mipush.sdk.MiPushClient4Hybrid.onReceiveUnregisterResult(r2, r0)     // Catch:{ TException -> 0x0475 }
            goto L_0x09b7
        L_0x0475:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            goto L_0x09b7
        L_0x047b:
            com.xiaomi.xmpush.thrift.NotificationType r0 = com.xiaomi.xmpush.thrift.NotificationType.PushLogUpload
            java.lang.String r0 = r0.value
            java.lang.String r2 = r4.type
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "packages"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09b7
            java.util.Map r0 = r4.getExtra()
            java.lang.String r2 = "packages"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = ","
            java.lang.String[] r0 = r0.split(r2)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = "com.xiaomi.xmsf"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x04c5
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.Logger.uploadLogFile(r2, r6)
            android.content.Context r2 = r1.sAppContext
            r1.uploadAppLog(r2, r0)
            goto L_0x09b7
        L_0x04c5:
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.Logger.uploadLogFile(r0, r7)
            goto L_0x09b7
        L_0x04cc:
            android.content.Context r0 = r1.sAppContext
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.xmpush.thrift.ActionType r5 = com.xiaomi.xmpush.thrift.ActionType.Command
            int r3 = r3.length
            com.xiaomi.push.clientreport.PerfMessageHelper.collectPerfData(r0, r2, r4, r5, r3)
            com.xiaomi.xmpush.thrift.XmPushActionCommandResult r4 = (com.xiaomi.xmpush.thrift.XmPushActionCommandResult) r4
            java.lang.String r13 = r4.getCmdName()
            java.util.List r0 = r4.getCmdArgs()
            long r2 = r4.errorCode
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x05df
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_SET_ACCEPT_TIME
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x0548
            if (r0 == 0) goto L_0x0548
            int r2 = r0.size()
            if (r2 <= r6) goto L_0x0548
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r5 = r0.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            com.xiaomi.mipush.sdk.MiPushClient.addAcceptTime(r2, r3, r5)
            java.lang.Object r2 = r0.get(r7)
            java.lang.String r3 = "00:00"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x052f
            java.lang.Object r2 = r0.get(r6)
            java.lang.String r3 = "00:00"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x052f
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            r2.setPaused(r6)
            goto L_0x0538
        L_0x052f:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            r2.setPaused(r7)
        L_0x0538:
            java.lang.String r2 = "GMT+08"
            java.util.TimeZone r2 = java.util.TimeZone.getTimeZone(r2)
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()
            java.util.List r0 = r1.getTimeForTimeZone(r2, r3, r0)
            goto L_0x05df
        L_0x0548:
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_SET_ALIAS
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x0567
            if (r0 == 0) goto L_0x0567
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0567
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.addAlias(r2, r3)
            goto L_0x05df
        L_0x0567:
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_UNSET_ALIAS
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x0585
            if (r0 == 0) goto L_0x0585
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0585
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.removeAlias(r2, r3)
            goto L_0x05df
        L_0x0585:
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_SET_ACCOUNT
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x05a3
            if (r0 == 0) goto L_0x05a3
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x05a3
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.addAccount(r2, r3)
            goto L_0x05df
        L_0x05a3:
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_UNSET_ACCOUNT
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x05c1
            if (r0 == 0) goto L_0x05c1
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x05c1
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.removeAccount(r2, r3)
            goto L_0x05df
        L_0x05c1:
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_CHK_VDEVID
            java.lang.String r2 = r2.value
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x05df
            if (r0 == 0) goto L_0x05de
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x05de
            android.content.Context r2 = r1.sAppContext
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            com.xiaomi.channel.commonutils.android.DeviceInfo.updateVirtDevId(r2, r0)
        L_0x05de:
            return r10
        L_0x05df:
            r14 = r0
            long r2 = r4.errorCode
            java.lang.String r0 = r4.reason
            java.lang.String r18 = r4.getCategory()
            r15 = r2
            r17 = r0
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.generateCommandMessage(r13, r14, r15, r17, r18)
            return r0
        L_0x05f0:
            com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult r4 = (com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult) r4
            long r2 = r4.errorCode
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x0601
            android.content.Context r0 = r1.sAppContext
            java.lang.String r2 = r4.getTopic()
            com.xiaomi.mipush.sdk.MiPushClient.removeTopic(r0, r2)
        L_0x0601:
            java.lang.String r0 = r4.getTopic()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0617
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r0 = r4.getTopic()
            r10.add(r0)
        L_0x0617:
            r12 = r10
            com.xiaomi.push.service.xmpush.Command r0 = com.xiaomi.push.service.xmpush.Command.COMMAND_UNSUBSCRIBE_TOPIC
            java.lang.String r11 = r0.value
            long r13 = r4.errorCode
            java.lang.String r15 = r4.reason
            java.lang.String r16 = r4.getCategory()
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.generateCommandMessage(r11, r12, r13, r15, r16)
            return r0
        L_0x0629:
            com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult r4 = (com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult) r4
            long r2 = r4.errorCode
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x063a
            android.content.Context r0 = r1.sAppContext
            java.lang.String r2 = r4.getTopic()
            com.xiaomi.mipush.sdk.MiPushClient.addTopic(r0, r2)
        L_0x063a:
            java.lang.String r0 = r4.getTopic()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0650
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r0 = r4.getTopic()
            r10.add(r0)
        L_0x0650:
            r12 = r10
            com.xiaomi.push.service.xmpush.Command r0 = com.xiaomi.push.service.xmpush.Command.COMMAND_SUBSCRIBE_TOPIC
            java.lang.String r11 = r0.value
            long r13 = r4.errorCode
            java.lang.String r15 = r4.reason
            java.lang.String r16 = r4.getCategory()
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.generateCommandMessage(r11, r12, r13, r15, r16)
            return r0
        L_0x0662:
            com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult r4 = (com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult) r4
            long r2 = r4.errorCode
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x0678
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r0 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r0)
            r0.clear()
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.mipush.sdk.MiPushClient.clearExtras(r0)
        L_0x0678:
            com.xiaomi.mipush.sdk.PushMessageHandler.removeAllPushCallbackClass()
            goto L_0x09b7
        L_0x067d:
            r0 = r4
            com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult r0 = (com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult) r0
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            java.lang.String r2 = r2.appRegRequestId
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0714
            java.lang.String r3 = r0.getId()
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L_0x069a
            goto L_0x0714
        L_0x069a:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            r2.appRegRequestId = r10
            long r2 = r0.errorCode
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x06d1
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r2 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r2)
            java.lang.String r3 = r0.regId
            java.lang.String r4 = r0.regSecret
            java.lang.String r5 = r0.region
            r2.putRegIDAndSecret(r3, r4, r5)
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r2)
            android.content.Context r3 = r1.sAppContext
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r6 = 6006(0x1776, float:8.416E-42)
            java.lang.String r7 = "receive register result success"
            r5 = r23
            r2.reportEvent(r3, r4, r5, r6, r7)
            goto L_0x06ea
        L_0x06d1:
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r2)
            android.content.Context r3 = r1.sAppContext
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r6 = 6006(0x1776, float:8.416E-42)
            java.lang.String r7 = "receive register result fail"
            r5 = r23
            r2.reportEvent(r3, r4, r5, r6, r7)
        L_0x06ea:
            java.lang.String r2 = r0.regId
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x06fc
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r2 = r0.regId
            r10.add(r2)
        L_0x06fc:
            r4 = r10
            com.xiaomi.push.service.xmpush.Command r2 = com.xiaomi.push.service.xmpush.Command.COMMAND_REGISTER
            java.lang.String r3 = r2.value
            long r5 = r0.errorCode
            java.lang.String r7 = r0.reason
            r8 = 0
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.generateCommandMessage(r3, r4, r5, r7, r8)
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.mipush.sdk.PushServiceClient r2 = com.xiaomi.mipush.sdk.PushServiceClient.getInstance(r2)
            r2.processPendRequest()
            return r0
        L_0x0714:
            java.lang.String r0 = "bad Registration result:"
            com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.String r4 = "bad Registration result:"
            r0.reportEvent4ERROR((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r8, (java.lang.String) r4)
            return r10
        L_0x072f:
            android.content.Context r5 = r1.sAppContext
            com.xiaomi.mipush.sdk.AppInfoHolder r5 = com.xiaomi.mipush.sdk.AppInfoHolder.getInstance(r5)
            boolean r5 = r5.isPaused()
            if (r5 == 0) goto L_0x0758
            if (r0 != 0) goto L_0x0758
            java.lang.String r0 = "receive a message in pause state. drop it"
            com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.String r4 = "receive a message in pause state. drop it"
            r0.reportEvent4NeedDrop(r2, r3, r8, r4)
            return r10
        L_0x0758:
            com.xiaomi.xmpush.thrift.XmPushActionSendMessage r4 = (com.xiaomi.xmpush.thrift.XmPushActionSendMessage) r4
            com.xiaomi.xmpush.thrift.PushMessage r5 = r4.getMessage()
            if (r5 != 0) goto L_0x077b
            java.lang.String r0 = "receive an empty message without push content, drop it"
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.String) r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.String r4 = "receive an empty message without push content, drop it"
            r0.reportEvent4ERROR((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r8, (java.lang.String) r4)
            return r10
        L_0x077b:
            if (r0 == 0) goto L_0x07a8
            boolean r6 = com.xiaomi.push.service.MIPushNotificationHelper.isBusinessMessage(r20)
            if (r6 == 0) goto L_0x0797
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r5.getId()
            com.xiaomi.xmpush.thrift.PushMetaInfo r13 = r20.getMetaInfo()
            java.lang.String r14 = r2.packageName
            java.lang.String r15 = r5.getAppId()
            com.xiaomi.mipush.sdk.MiPushClient.reportIgnoreRegMessageClicked(r6, r7, r13, r14, r15)
            goto L_0x07a8
        L_0x0797:
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r5.getId()
            com.xiaomi.xmpush.thrift.PushMetaInfo r13 = r20.getMetaInfo()
            java.lang.String r14 = r5.getAppId()
            com.xiaomi.mipush.sdk.MiPushClient.reportMessageClicked(r6, r7, r13, r14)
        L_0x07a8:
            if (r0 != 0) goto L_0x07ed
            java.lang.String r6 = r4.getAliasName()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x07cc
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r4.getAliasName()
            long r6 = com.xiaomi.mipush.sdk.MiPushClient.aliasSetTime(r6, r7)
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x07cc
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r4.getAliasName()
            com.xiaomi.mipush.sdk.MiPushClient.addAlias(r6, r7)
            goto L_0x07ed
        L_0x07cc:
            java.lang.String r6 = r4.getTopic()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x07ed
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r4.getTopic()
            long r6 = com.xiaomi.mipush.sdk.MiPushClient.topicSubscribedTime(r6, r7)
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x07ed
            android.content.Context r6 = r1.sAppContext
            java.lang.String r7 = r4.getTopic()
            com.xiaomi.mipush.sdk.MiPushClient.addTopic(r6, r7)
        L_0x07ed:
            com.xiaomi.xmpush.thrift.PushMetaInfo r6 = r2.metaInfo
            if (r6 == 0) goto L_0x0806
            com.xiaomi.xmpush.thrift.PushMetaInfo r6 = r2.metaInfo
            java.util.Map r6 = r6.getExtra()
            if (r6 == 0) goto L_0x0806
            com.xiaomi.xmpush.thrift.PushMetaInfo r6 = r2.metaInfo
            java.util.Map<java.lang.String, java.lang.String> r6 = r6.extra
            java.lang.String r7 = "jobkey"
            java.lang.Object r6 = r6.get(r7)
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0807
        L_0x0806:
            r6 = r10
        L_0x0807:
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x0811
            java.lang.String r6 = r5.getId()
        L_0x0811:
            if (r0 != 0) goto L_0x0855
            android.content.Context r7 = r1.sAppContext
            boolean r7 = isDuplicateMessage(r7, r6)
            if (r7 == 0) goto L_0x0855
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "drop a duplicate message, key="
            r3.append(r5)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.xiaomi.channel.commonutils.logger.MyLog.w(r3)
            android.content.Context r3 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r3 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r3)
            android.content.Context r5 = r1.sAppContext
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r7 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "drop a duplicate message, key="
            r9.append(r11)
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            r3.reportEvent4DUPMD(r5, r7, r8, r6)
            goto L_0x09ac
        L_0x0855:
            com.xiaomi.xmpush.thrift.PushMetaInfo r7 = r20.getMetaInfo()
            com.xiaomi.mipush.sdk.MiPushMessage r7 = com.xiaomi.mipush.sdk.PushMessageHelper.generateMessage(r4, r7, r0)
            int r11 = r7.getPassThrough()
            if (r11 != 0) goto L_0x0875
            if (r0 != 0) goto L_0x0875
            java.util.Map r11 = r7.getExtra()
            boolean r11 = com.xiaomi.push.service.MIPushNotificationHelper.isNotifyForeground(r11)
            if (r11 == 0) goto L_0x0875
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.MIPushNotificationHelper.notifyPushMessage(r0, r2, r3)
            return r10
        L_0x0875:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r11 = "receive a message, msgid="
            r3.append(r11)
            java.lang.String r11 = r5.getId()
            r3.append(r11)
            java.lang.String r11 = ", jobkey="
            r3.append(r11)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.xiaomi.channel.commonutils.logger.MyLog.w(r3)
            if (r0 == 0) goto L_0x09ab
            java.util.Map r3 = r7.getExtra()
            if (r3 == 0) goto L_0x09ab
            java.util.Map r3 = r7.getExtra()
            java.lang.String r6 = "notify_effect"
            boolean r3 = r3.containsKey(r6)
            if (r3 == 0) goto L_0x09ab
            java.util.Map r0 = r7.getExtra()
            java.lang.String r3 = "notify_effect"
            java.lang.Object r3 = r0.get(r3)
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            boolean r3 = com.xiaomi.push.service.MIPushNotificationHelper.isBusinessMessage(r20)
            if (r3 == 0) goto L_0x0929
            android.content.Context r3 = r1.sAppContext
            java.lang.String r2 = r2.packageName
            android.content.Intent r0 = getNotificationMessageIntent(r3, r2, r0)
            java.lang.String r2 = "eventMessageType"
            r0.putExtra(r2, r9)
            java.lang.String r2 = "messageId"
            r0.putExtra(r2, r8)
            if (r0 != 0) goto L_0x08eb
            java.lang.String r0 = "Getting Intent fail from ignore reg message. "
            com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.String r4 = "Getting Intent fail from ignore reg message."
            r0.reportEvent4ERROR((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r8, (java.lang.String) r4)
            return r10
        L_0x08eb:
            java.lang.String r2 = r5.getPayload()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x08fa
            java.lang.String r3 = "payload"
            r0.putExtra(r3, r2)
        L_0x08fa:
            android.content.Context r2 = r1.sAppContext
            r2.startActivity(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r0 = r1.sAppContext
            java.lang.String r3 = r0.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r6 = 3006(0xbbe, float:4.212E-42)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "business message is clicked typeId "
            r0.append(r5)
            r0.append(r11)
            java.lang.String r7 = r0.toString()
            r5 = r23
            r2.reportEvent(r3, r4, r5, r6, r7)
            goto L_0x09aa
        L_0x0929:
            android.content.Context r2 = r1.sAppContext
            java.lang.String r3 = r2.getPackageName()
            android.content.Intent r0 = getNotificationMessageIntent(r2, r3, r0)
            if (r0 == 0) goto L_0x09aa
            java.lang.String r2 = com.xiaomi.push.service.PushConstants.NOTIFICATION_CLICK_WEB_PAGE
            boolean r2 = r11.equals(r2)
            if (r2 != 0) goto L_0x094c
            java.lang.String r2 = "key_message"
            r0.putExtra(r2, r7)
            java.lang.String r2 = "eventMessageType"
            r0.putExtra(r2, r9)
            java.lang.String r2 = "messageId"
            r0.putExtra(r2, r8)
        L_0x094c:
            android.content.Context r2 = r1.sAppContext
            r2.startActivity(r0)
            java.lang.String r0 = "start activity succ"
            com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r0 = r1.sAppContext
            java.lang.String r3 = r0.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r6 = 1006(0x3ee, float:1.41E-42)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "notification message is clicked typeId "
            r0.append(r5)
            r0.append(r11)
            java.lang.String r7 = r0.toString()
            r5 = r23
            r2.reportEvent(r3, r4, r5, r6, r7)
            java.lang.String r0 = com.xiaomi.push.service.PushConstants.NOTIFICATION_CLICK_WEB_PAGE
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x09aa
            android.content.Context r0 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r0 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r0)
            android.content.Context r2 = r1.sAppContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "try open web page typeId "
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r0.reportEvent4NeedDrop(r2, r3, r8, r4)
        L_0x09aa:
            return r10
        L_0x09ab:
            r10 = r7
        L_0x09ac:
            com.xiaomi.xmpush.thrift.PushMetaInfo r3 = r20.getMetaInfo()
            if (r3 != 0) goto L_0x09b7
            if (r0 != 0) goto L_0x09b7
            r1.ackMessage(r4, r2)
        L_0x09b7:
            return r10
        L_0x09b8:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            java.lang.String r2 = "receive a message which action string is not valid. is the reg expired?"
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.String) r2)
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r2)
            android.content.Context r3 = r1.sAppContext
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r2.reportEvent4ERROR((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r8, (java.lang.Throwable) r0)
            return r10
        L_0x09d5:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
            r19.reportDecryptFail(r20)
            android.content.Context r2 = r1.sAppContext
            com.xiaomi.push.service.clientReport.PushClientReportManager r2 = com.xiaomi.push.service.clientReport.PushClientReportManager.getInstance(r2)
            android.content.Context r3 = r1.sAppContext
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.service.clientReport.PushClientReportHelper.getInterfaceIdByType(r24)
            r2.reportEvent4ERROR((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r8, (java.lang.Throwable) r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.PushMessageProcessor.processMessage(com.xiaomi.xmpush.thrift.XmPushActionContainer, boolean, byte[], java.lang.String, int):com.xiaomi.mipush.sdk.PushMessageHandler$PushMessageInterface");
    }

    private PushMessageHandler.PushMessageInterface processMessage(XmPushActionContainer xmPushActionContainer, byte[] bArr) {
        String str = null;
        try {
            TBase responseMessageBodyFromContainer = PushContainerHelper.getResponseMessageBodyFromContainer(this.sAppContext, xmPushActionContainer);
            if (responseMessageBodyFromContainer == null) {
                MyLog.e("message arrived: receiving an un-recognized message. " + xmPushActionContainer.action);
                return null;
            }
            ActionType action = xmPushActionContainer.getAction();
            MyLog.w("message arrived: processing an arrived message, action=" + action);
            if (AnonymousClass2.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType[action.ordinal()] != 1) {
                return null;
            }
            XmPushActionSendMessage xmPushActionSendMessage = (XmPushActionSendMessage) responseMessageBodyFromContainer;
            PushMessage message = xmPushActionSendMessage.getMessage();
            if (message == null) {
                MyLog.e("message arrived: receive an empty message without push content, drop it");
                return null;
            }
            if (!(xmPushActionContainer.metaInfo == null || xmPushActionContainer.metaInfo.getExtra() == null)) {
                str = xmPushActionContainer.metaInfo.extra.get("jobkey");
            }
            MiPushMessage generateMessage = PushMessageHelper.generateMessage(xmPushActionSendMessage, xmPushActionContainer.getMetaInfo(), false);
            generateMessage.setArrivedMessage(true);
            MyLog.w("message arrived: receive a message, msgid=" + message.getId() + ", jobkey=" + str);
            return generateMessage;
        } catch (DecryptException e) {
            MyLog.e((Throwable) e);
            MyLog.e("message arrived: receive a message but decrypt failed. report when click.");
            return null;
        } catch (TException e2) {
            MyLog.e((Throwable) e2);
            MyLog.e("message arrived: receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private void processSendTokenAckNotification(XmPushActionAckNotification xmPushActionAckNotification) {
        MyLog.v("ASSEMBLE_PUSH : " + xmPushActionAckNotification.toString());
        String id = xmPushActionAckNotification.getId();
        Map<String, String> extra = xmPushActionAckNotification.getExtra();
        if (extra != null) {
            String str = extra.get("RegInfo");
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("brand:" + PhoneBrand.FCM.name())) {
                    MyLog.w("ASSEMBLE_PUSH : receive fcm token sync ack");
                    AssemblePushHelper.saveAssemblePushTokenAfterAck(this.sAppContext, AssemblePush.ASSEMBLE_PUSH_FCM, str);
                    processSingleTokenACK(id, xmPushActionAckNotification.errorCode, AssemblePush.ASSEMBLE_PUSH_FCM);
                    return;
                }
                if (str.contains("brand:" + PhoneBrand.HUAWEI.name())) {
                    MyLog.w("ASSEMBLE_PUSH : receive hw token sync ack");
                    AssemblePushHelper.saveAssemblePushTokenAfterAck(this.sAppContext, AssemblePush.ASSEMBLE_PUSH_HUAWEI, str);
                    processSingleTokenACK(id, xmPushActionAckNotification.errorCode, AssemblePush.ASSEMBLE_PUSH_HUAWEI);
                    return;
                }
                if (str.contains("brand:" + PhoneBrand.OPPO.name())) {
                    MyLog.w("ASSEMBLE_PUSH : receive COS token sync ack");
                    AssemblePushHelper.saveAssemblePushTokenAfterAck(this.sAppContext, AssemblePush.ASSEMBLE_PUSH_COS, str);
                    processSingleTokenACK(id, xmPushActionAckNotification.errorCode, AssemblePush.ASSEMBLE_PUSH_COS);
                    return;
                }
                if (str.contains("brand:" + PhoneBrand.VIVO.name())) {
                    MyLog.w("ASSEMBLE_PUSH : receive FTOS token sync ack");
                    AssemblePushHelper.saveAssemblePushTokenAfterAck(this.sAppContext, AssemblePush.ASSEMBLE_PUSH_FTOS, str);
                    processSingleTokenACK(id, xmPushActionAckNotification.errorCode, AssemblePush.ASSEMBLE_PUSH_FTOS);
                }
            }
        }
    }

    private void processSingleTokenACK(String str, long j, AssemblePush assemblePush) {
        RetryType retryType = AssemblePushInfoHelper.getRetryType(assemblePush);
        if (retryType != null) {
            if (j == 0) {
                synchronized (OperatePushHelper.class) {
                    if (OperatePushHelper.getInstance(this.sAppContext).isMessageOperating(str)) {
                        OperatePushHelper.getInstance(this.sAppContext).removeOperateMessage(str);
                        if ("syncing".equals(OperatePushHelper.getInstance(this.sAppContext).getSyncStatus(retryType))) {
                            OperatePushHelper.getInstance(this.sAppContext).putSyncStatus(retryType, "synced");
                        }
                    }
                }
            } else if ("syncing".equals(OperatePushHelper.getInstance(this.sAppContext).getSyncStatus(retryType))) {
                synchronized (OperatePushHelper.class) {
                    if (OperatePushHelper.getInstance(this.sAppContext).isMessageOperating(str)) {
                        if (OperatePushHelper.getInstance(this.sAppContext).getRetryCount(str) < 10) {
                            OperatePushHelper.getInstance(this.sAppContext).increaseRetryCount(str);
                            PushServiceClient.getInstance(this.sAppContext).sendAssemblePushTokenCommon(str, retryType, assemblePush);
                        } else {
                            OperatePushHelper.getInstance(this.sAppContext).removeOperateMessage(str);
                        }
                    }
                }
            } else {
                OperatePushHelper.getInstance(this.sAppContext).removeOperateMessage(str);
            }
        }
    }

    private void processStatDataACK(XmPushActionAckNotification xmPushActionAckNotification) {
        String id = xmPushActionAckNotification.getId();
        MyLog.i("receive ack " + id);
        Map<String, String> extra = xmPushActionAckNotification.getExtra();
        if (extra != null) {
            String str = extra.get("real_source");
            if (!TextUtils.isEmpty(str)) {
                MyLog.i("receive ack : messageId = " + id + "  realSource = " + str);
                PushStatClientManager.getInstance(this.sAppContext).onResult(id, str, Boolean.valueOf(xmPushActionAckNotification.errorCode == 0));
            }
        }
    }

    private void reportDecryptFail(XmPushActionContainer xmPushActionContainer) {
        MyLog.w("receive a message but decrypt failed. report now.");
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification(xmPushActionContainer.getMetaInfo().id, false);
        xmPushActionNotification.setType(NotificationType.DecryptMessageFail.value);
        xmPushActionNotification.setAppId(xmPushActionContainer.getAppid());
        xmPushActionNotification.setPackageName(xmPushActionContainer.packageName);
        xmPushActionNotification.extra = new HashMap();
        xmPushActionNotification.extra.put("regid", MiPushClient.getRegId(this.sAppContext));
        PushServiceClient.getInstance(this.sAppContext).sendMessage(xmPushActionNotification, ActionType.Notification, false, (PushMetaInfo) null);
    }

    /* access modifiers changed from: private */
    public void sendUploadLogIntent(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            int length = serviceInfoArr.length;
            int i = 0;
            while (i < length) {
                ServiceInfo serviceInfo = serviceInfoArr[i];
                if (!serviceInfo.exported || !serviceInfo.enabled || !"com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) || context.getPackageName().equals(serviceInfo.packageName)) {
                    i++;
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.SYNC_LOG");
                        PushMessageHandler.addJob(context, intent);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
        }
    }

    private void tryToReinitialize() {
        SharedPreferences sharedPreferences = this.sAppContext.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong("last_reinitialize", 0)) > 1800000) {
            MiPushClient.reInitialize(this.sAppContext, RegistrationReason.PackageUnregistered);
            sharedPreferences.edit().putLong("last_reinitialize", currentTimeMillis).commit();
        }
    }

    private void uploadAppLog(final Context context, final String[] strArr) {
        ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
            public void run() {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        if (!TextUtils.isEmpty(strArr[i])) {
                            if (i > 0) {
                                Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                            }
                            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(strArr[i], 4);
                            if (packageInfo != null) {
                                PushMessageProcessor.this.sendUploadLogIntent(context, packageInfo);
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        MyLog.e(th);
                        return;
                    }
                }
            }
        });
    }

    public List<String> getTimeForTimeZone(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        List<String> list2 = list;
        if (timeZone.equals(timeZone2)) {
            return list2;
        }
        long rawOffset = (long) (((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60);
        long parseLong = Long.parseLong(list2.get(0).split(":")[0]);
        long parseLong2 = Long.parseLong(list2.get(0).split(":")[1]);
        long j = ((((parseLong * 60) + parseLong2) - rawOffset) + 1440) % 1440;
        long parseLong3 = ((((Long.parseLong(list2.get(1).split(":")[0]) * 60) + Long.parseLong(list2.get(1).split(":")[1])) - rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j / 60), Long.valueOf(j % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)}));
        return arrayList;
    }

    public PushMessageHandler.PushMessageInterface processIntent(Intent intent) {
        Intent intent2 = intent;
        String action = intent.getAction();
        MyLog.w("receive an intent from server, action=" + action);
        String stringExtra = intent2.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        String stringExtra2 = intent2.getStringExtra("messageId");
        int intExtra = intent2.getIntExtra("eventMessageType", -1);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent2.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent2.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                MyLog.e("receiving an empty message, drop");
                PushClientReportManager.getInstance(this.sAppContext).reportEvent4ERROR(this.sAppContext.getPackageName(), intent2, "receiving an empty message, drop");
                return null;
            }
            XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
            try {
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(xmPushActionContainer, byteArrayExtra);
                AppInfoHolder instance = AppInfoHolder.getInstance(this.sAppContext);
                PushMetaInfo metaInfo = xmPushActionContainer.getMetaInfo();
                if (xmPushActionContainer.getAction() == ActionType.SendMessage && metaInfo != null && !instance.isPaused() && !booleanExtra) {
                    metaInfo.putToExtra("mrt", stringExtra);
                    metaInfo.putToExtra("mat", Long.toString(System.currentTimeMillis()));
                    if (!isHybridMsg(xmPushActionContainer)) {
                        ackMessage(xmPushActionContainer);
                    } else {
                        MyLog.i("this is a mina's message, ack later");
                        metaInfo.putToExtra("__hybrid_message_ts", String.valueOf(metaInfo.getMessageTs()));
                        metaInfo.putToExtra("__hybrid_device_status", String.valueOf(XmPushThriftSerializeUtils.getDeviceStatus(this.sAppContext, xmPushActionContainer)));
                    }
                }
                if (xmPushActionContainer.getAction() == ActionType.SendMessage && !xmPushActionContainer.isEncryptAction()) {
                    String str = "";
                    if (!MIPushNotificationHelper.isBusinessMessage(xmPushActionContainer)) {
                        Object[] objArr = new Object[2];
                        objArr[0] = xmPushActionContainer.getPackageName();
                        objArr[1] = metaInfo != null ? metaInfo.getId() : str;
                        MyLog.w(String.format("drop an un-encrypted messages. %1$s, %2$s", objArr));
                        PushClientReportManager instance2 = PushClientReportManager.getInstance(this.sAppContext);
                        String packageName = this.sAppContext.getPackageName();
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = xmPushActionContainer.getPackageName();
                        if (metaInfo != null) {
                            str = metaInfo.getId();
                        }
                        objArr2[1] = str;
                        instance2.reportEvent4ERROR(packageName, intent2, String.format("drop an un-encrypted messages. %1$s, %2$s", objArr2));
                        return null;
                    } else if (!booleanExtra || metaInfo.getExtra() == null || !metaInfo.getExtra().containsKey("notify_effect")) {
                        MyLog.w(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[]{xmPushActionContainer.getPackageName(), metaInfo.getId()}));
                        PushClientReportManager instance3 = PushClientReportManager.getInstance(this.sAppContext);
                        String packageName2 = this.sAppContext.getPackageName();
                        Object[] objArr3 = new Object[2];
                        objArr3[0] = xmPushActionContainer.getPackageName();
                        if (metaInfo != null) {
                            str = metaInfo.getId();
                        }
                        objArr3[1] = str;
                        instance3.reportEvent4ERROR(packageName2, intent2, String.format("drop an un-encrypted messages. %1$s, %2$s", objArr3));
                        return null;
                    }
                }
                if (instance.appRegistered() || xmPushActionContainer.action == ActionType.Registration) {
                    if (!instance.appRegistered() || !instance.invalidated()) {
                        return processMessage(xmPushActionContainer, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                    }
                    if (xmPushActionContainer.action == ActionType.UnRegistration) {
                        instance.clear();
                        MiPushClient.clearExtras(this.sAppContext);
                        PushMessageHandler.removeAllPushCallbackClass();
                        return null;
                    }
                    MiPushClient.unregisterPush(this.sAppContext);
                    return null;
                } else if (MIPushNotificationHelper.isBusinessMessage(xmPushActionContainer)) {
                    return processMessage(xmPushActionContainer, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                } else {
                    MyLog.e("receive message without registration. need re-register!");
                    PushClientReportManager.getInstance(this.sAppContext).reportEvent4ERROR(this.sAppContext.getPackageName(), intent2, "receive message without registration. need re-register!");
                    tryToReinitialize();
                    return null;
                }
            } catch (TException e) {
                PushClientReportManager.getInstance(this.sAppContext).reportEvent4ERROR(this.sAppContext.getPackageName(), intent2, (Throwable) e);
                MyLog.e((Throwable) e);
                return null;
            } catch (Exception e2) {
                PushClientReportManager.getInstance(this.sAppContext).reportEvent4ERROR(this.sAppContext.getPackageName(), intent2, (Throwable) e2);
                MyLog.e((Throwable) e2);
                return null;
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
            XmPushActionContainer xmPushActionContainer2 = new XmPushActionContainer();
            try {
                byte[] byteArrayExtra2 = intent2.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    XmPushThriftSerializeUtils.convertByteArrayToThriftObject(xmPushActionContainer2, byteArrayExtra2);
                }
            } catch (TException unused) {
            }
            miPushCommandMessage.setCommand(String.valueOf(xmPushActionContainer2.getAction()));
            miPushCommandMessage.setResultCode((long) intent2.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent2.getStringExtra("mipush_error_msg"));
            MyLog.e("receive a error message. code = " + intent2.getIntExtra("mipush_error_code", 0) + ", msg= " + intent2.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if (!"com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            return null;
        } else {
            byte[] byteArrayExtra3 = intent2.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra3 == null) {
                MyLog.e("message arrived: receiving an empty message, drop");
                return null;
            }
            XmPushActionContainer xmPushActionContainer3 = new XmPushActionContainer();
            try {
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(xmPushActionContainer3, byteArrayExtra3);
                AppInfoHolder instance4 = AppInfoHolder.getInstance(this.sAppContext);
                if (MIPushNotificationHelper.isBusinessMessage(xmPushActionContainer3)) {
                    MyLog.e("message arrived: receive ignore reg message, ignore!");
                    return null;
                } else if (!instance4.appRegistered()) {
                    MyLog.e("message arrived: receive message without registration. need unregister or re-register!");
                    return null;
                } else if (!instance4.appRegistered() || !instance4.invalidated()) {
                    return processMessage(xmPushActionContainer3, byteArrayExtra3);
                } else {
                    MyLog.e("message arrived: app info is invalidated");
                    return null;
                }
            } catch (TException e3) {
                MyLog.e((Throwable) e3);
                return null;
            } catch (Exception e4) {
                MyLog.e((Throwable) e4);
                return null;
            }
        }
    }
}
