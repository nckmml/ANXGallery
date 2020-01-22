package com.xiaomi.push.service;

import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.DeviceInfo;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.clientreport.PerfMessageHelper;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.Sync;
import com.xiaomi.slim.Blob;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.NotificationType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

final class MIPushHelper {
    static Blob constructBlob(MIPushAccount mIPushAccount, Context context, XmPushActionContainer xmPushActionContainer) {
        try {
            Blob blob = new Blob();
            blob.setChannelId(5);
            blob.setFrom(mIPushAccount.account);
            blob.setPackageName(getSourcePkgName(xmPushActionContainer));
            blob.setCmd("SECMSG", "message");
            String str = mIPushAccount.account;
            xmPushActionContainer.target.userId = str.substring(0, str.indexOf("@"));
            xmPushActionContainer.target.resource = str.substring(str.indexOf("/") + 1);
            blob.setPayload(XmPushThriftSerializeUtils.convertThriftObjectToBytes(xmPushActionContainer), mIPushAccount.security);
            blob.setPayloadType(1);
            MyLog.w("try send mi push message. packagename:" + xmPushActionContainer.packageName + " action:" + xmPushActionContainer.action);
            return blob;
        } catch (NullPointerException e) {
            MyLog.e((Throwable) e);
            return null;
        }
    }

    static Blob constructBlob(XMPushService xMPushService, byte[] bArr) {
        XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
        try {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(xmPushActionContainer, bArr);
            return constructBlob(MIPushAccountUtils.getMIPushAccount(xMPushService), xMPushService, xmPushActionContainer);
        } catch (TException e) {
            MyLog.e((Throwable) e);
            return null;
        }
    }

    static XmPushActionContainer contructAppAbsentMessage(String str, String str2) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        xmPushActionNotification.setAppId(str2);
        xmPushActionNotification.setType("package uninstalled");
        xmPushActionNotification.setId(Packet.nextID());
        xmPushActionNotification.setRequireAck(false);
        return generateRequestContainer(str, str2, xmPushActionNotification, ActionType.Notification);
    }

    static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(String str, String str2, T t, ActionType actionType) {
        byte[] convertThriftObjectToBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(t);
        XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
        Target target = new Target();
        target.channelId = 5;
        target.userId = "fakeid";
        xmPushActionContainer.setTarget(target);
        xmPushActionContainer.setPushAction(ByteBuffer.wrap(convertThriftObjectToBytes));
        xmPushActionContainer.setAction(actionType);
        xmPushActionContainer.setIsRequest(true);
        xmPushActionContainer.setPackageName(str);
        xmPushActionContainer.setEncryptAction(false);
        xmPushActionContainer.setAppid(str2);
        return xmPushActionContainer;
    }

    static String getReceiverPermission(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    private static String getSourcePkgName(XmPushActionContainer xmPushActionContainer) {
        if (!(xmPushActionContainer.metaInfo == null || xmPushActionContainer.metaInfo.internal == null)) {
            String str = xmPushActionContainer.metaInfo.internal.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return xmPushActionContainer.packageName;
    }

    static void prepareClientLoginInfo(final XMPushService xMPushService, PushClientsManager.ClientLoginInfo clientLoginInfo) {
        clientLoginInfo.watch((Messenger) null);
        clientLoginInfo.addClientStatusListener(new PushClientsManager.ClientLoginInfo.ClientStatusListener() {
            public void onChange(PushClientsManager.ClientStatus clientStatus, PushClientsManager.ClientStatus clientStatus2, int i) {
                if (clientStatus2 == PushClientsManager.ClientStatus.binded) {
                    MIPushClientManager.processPendingRegistrationRequest(xMPushService);
                    MIPushClientManager.processPendingMessages(xMPushService);
                } else if (clientStatus2 == PushClientsManager.ClientStatus.unbind) {
                    MIPushClientManager.notifyRegisterError(xMPushService, 70000001, " the push is not connected.");
                }
            }
        });
    }

    static void prepareMIPushAccount(XMPushService xMPushService) {
        final MIPushAccount mIPushAccount = MIPushAccountUtils.getMIPushAccount(xMPushService.getApplicationContext());
        if (mIPushAccount != null) {
            PushClientsManager.ClientLoginInfo clientLoginInfo = MIPushAccountUtils.getMIPushAccount(xMPushService.getApplicationContext()).toClientLoginInfo(xMPushService);
            prepareClientLoginInfo(xMPushService, clientLoginInfo);
            PushClientsManager.getInstance().addActiveClient(clientLoginInfo);
            final XMPushService xMPushService2 = xMPushService;
            Sync.getInstance(xMPushService).schedSync(new Sync.SyncJob("GAID", 172800) {
                /* access modifiers changed from: package-private */
                public void sync(Sync sync) {
                    String string = sync.getString("GAID", "gaid");
                    String gaid = DeviceInfo.getGaid(xMPushService2);
                    MyLog.v("gaid :" + gaid);
                    if (!TextUtils.isEmpty(gaid) && !TextUtils.equals(string, gaid)) {
                        sync.put("GAID", "gaid", gaid);
                        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
                        xmPushActionNotification.setAppId(mIPushAccount.appId);
                        xmPushActionNotification.setType(NotificationType.ClientInfoUpdate.value);
                        xmPushActionNotification.setId(PacketHelper.generatePacketID());
                        xmPushActionNotification.setExtra(new HashMap());
                        xmPushActionNotification.getExtra().put("gaid", gaid);
                        byte[] convertThriftObjectToBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(MIPushHelper.generateRequestContainer(xMPushService2.getPackageName(), mIPushAccount.appId, xmPushActionNotification, ActionType.Notification));
                        XMPushService xMPushService = xMPushService2;
                        xMPushService.sendMessage(xMPushService.getPackageName(), convertThriftObjectToBytes, true);
                    }
                }
            });
        }
    }

    static void sendPacket(XMPushService xMPushService, XmPushActionContainer xmPushActionContainer) throws XMPPException {
        PerfMessageHelper.collectUpStream(xmPushActionContainer.getPackageName(), xMPushService.getApplicationContext(), xmPushActionContainer, -1);
        Connection currentConnection = xMPushService.getCurrentConnection();
        if (currentConnection == null) {
            throw new XMPPException("try send msg while connection is null.");
        } else if (currentConnection.isBinaryConnection()) {
            Blob constructBlob = constructBlob(MIPushAccountUtils.getMIPushAccount(xMPushService), xMPushService, xmPushActionContainer);
            if (constructBlob != null) {
                currentConnection.send(constructBlob);
            }
        } else {
            throw new XMPPException("Don't support XMPP connection.");
        }
    }

    static void sendPacket(XMPushService xMPushService, String str, byte[] bArr) throws XMPPException {
        PerfMessageHelper.collectUpStream(str, xMPushService.getApplicationContext(), bArr);
        Connection currentConnection = xMPushService.getCurrentConnection();
        if (currentConnection == null) {
            throw new XMPPException("try send msg while connection is null.");
        } else if (currentConnection.isBinaryConnection()) {
            Blob constructBlob = constructBlob(xMPushService, bArr);
            if (constructBlob != null) {
                currentConnection.send(constructBlob);
            } else {
                MIPushClientManager.notifyError(xMPushService, str, bArr, 70000003, "not a valid message");
            }
        } else {
            throw new XMPPException("Don't support XMPP connection.");
        }
    }
}
