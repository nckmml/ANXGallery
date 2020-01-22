package com.xiaomi.push.service;

import android.text.TextUtils;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.log.LogUploader;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.slim.Blob;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.stats.StatsHelper;
import java.util.Date;

public class PacketSync {
    private XMPushService mService;

    PacketSync(XMPushService xMPushService) {
        this.mService = xMPushService;
    }

    private void dispatchNetFlow(Blob blob) {
        PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId;
        String fullUserName = blob.getFullUserName();
        String num = Integer.toString(blob.getChannelId());
        if (!TextUtils.isEmpty(fullUserName) && !TextUtils.isEmpty(num) && (clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(num, fullUserName)) != null) {
            TrafficUtils.distributionTraffic(this.mService, clientLoginInfoByChidAndUserId.pkgName, (long) blob.getSerializedSize(), true, true, System.currentTimeMillis());
        }
    }

    private void dispatchNetFlow(Packet packet) {
        PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId;
        String to = packet.getTo();
        String channelId = packet.getChannelId();
        if (!TextUtils.isEmpty(to) && !TextUtils.isEmpty(channelId) && (clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(channelId, to)) != null) {
            TrafficUtils.distributionTraffic(this.mService, clientLoginInfoByChidAndUserId.pkgName, (long) TrafficUtils.getTrafficFlow(packet.toXML()), true, true, System.currentTimeMillis());
        }
    }

    private void processRedirectMessage(CommonPacketExtension commonPacketExtension) {
        String text = commonPacketExtension.getText();
        if (!TextUtils.isEmpty(text)) {
            String[] split = text.split(";");
            Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost(), false);
            if (fallbacksByHost != null && split.length > 0) {
                fallbacksByHost.addPreferredHost(split);
                this.mService.disconnect(20, (Exception) null);
                this.mService.scheduleConnect(true);
            }
        }
    }

    public void handleBlob(Blob blob) throws InvalidProtocolBufferMicroException {
        String cmd = blob.getCmd();
        if (blob.getChannelId() != 0) {
            String num = Integer.toString(blob.getChannelId());
            if ("SECMSG".equals(blob.getCmd())) {
                if (!blob.hasErr()) {
                    this.mService.getClientEventDispatcher().notifyPacketArrival(this.mService, num, blob);
                    return;
                }
                MyLog.w("Recv SECMSG errCode = " + blob.getErrCode() + " errStr = " + blob.getErrStr());
            } else if ("BIND".equals(cmd)) {
                ChannelMessage.XMMsgBindResp parseFrom = ChannelMessage.XMMsgBindResp.parseFrom(blob.getPayload());
                String fullUserName = blob.getFullUserName();
                PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(num, fullUserName);
                if (clientLoginInfoByChidAndUserId != null) {
                    if (parseFrom.getResult()) {
                        MyLog.w("SMACK: channel bind succeeded, chid=" + blob.getChannelId());
                        clientLoginInfoByChidAndUserId.setStatus(PushClientsManager.ClientStatus.binded, 1, 0, (String) null, (String) null);
                        return;
                    }
                    String errorType = parseFrom.getErrorType();
                    if ("auth".equals(errorType)) {
                        if ("invalid-sig".equals(parseFrom.getErrorReason())) {
                            MyLog.w("SMACK: bind error invalid-sig token = " + clientLoginInfoByChidAndUserId.token + " sec = " + clientLoginInfoByChidAndUserId.security);
                            StatsHelper.stats(0, ChannelStatsType.BIND_INVALID_SIG.getValue(), 1, (String) null, 0);
                        }
                        clientLoginInfoByChidAndUserId.setStatus(PushClientsManager.ClientStatus.unbind, 1, 5, parseFrom.getErrorReason(), errorType);
                        PushClientsManager.getInstance().deactivateClient(num, fullUserName);
                    } else if ("cancel".equals(errorType)) {
                        clientLoginInfoByChidAndUserId.setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, parseFrom.getErrorReason(), errorType);
                        PushClientsManager.getInstance().deactivateClient(num, fullUserName);
                    } else if ("wait".equals(errorType)) {
                        this.mService.scheduleRebindChannel(clientLoginInfoByChidAndUserId);
                        clientLoginInfoByChidAndUserId.setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, parseFrom.getErrorReason(), errorType);
                    }
                    MyLog.w("SMACK: channel bind failed, chid=" + num + " reason=" + parseFrom.getErrorReason());
                }
            } else if ("KICK".equals(cmd)) {
                ChannelMessage.XMMsgKick parseFrom2 = ChannelMessage.XMMsgKick.parseFrom(blob.getPayload());
                String fullUserName2 = blob.getFullUserName();
                String type = parseFrom2.getType();
                String reason = parseFrom2.getReason();
                MyLog.w("kicked by server, chid=" + num + " res= " + PushClientsManager.ClientLoginInfo.getResource(fullUserName2) + " type=" + type + " reason=" + reason);
                if ("wait".equals(type)) {
                    PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(num, fullUserName2);
                    if (clientLoginInfoByChidAndUserId2 != null) {
                        this.mService.scheduleRebindChannel(clientLoginInfoByChidAndUserId2);
                        clientLoginInfoByChidAndUserId2.setStatus(PushClientsManager.ClientStatus.unbind, 3, 0, reason, type);
                        return;
                    }
                    return;
                }
                this.mService.closeChannel(num, fullUserName2, 3, reason, type);
                PushClientsManager.getInstance().deactivateClient(num, fullUserName2);
            }
        } else if ("PING".equals(cmd)) {
            byte[] payload = blob.getPayload();
            if (payload != null && payload.length > 0) {
                ChannelMessage.XMMsgPing parseFrom3 = ChannelMessage.XMMsgPing.parseFrom(payload);
                if (parseFrom3.hasPsc()) {
                    ServiceConfig.getInstance().handle(parseFrom3.getPsc());
                }
            }
            if (!"com.xiaomi.xmsf".equals(this.mService.getPackageName())) {
                this.mService.sendPongIfNeed();
            }
            if ("1".equals(blob.getPacketID())) {
                MyLog.w("received a server ping");
            } else {
                StatsHelper.pingEnded();
            }
            this.mService.onPong();
        } else if ("SYNC".equals(cmd)) {
            if ("CONF".equals(blob.getSubcmd())) {
                ServiceConfig.getInstance().handle(ChannelMessage.PushServiceConfigMsg.parseFrom(blob.getPayload()));
            } else if (TextUtils.equals("U", blob.getSubcmd())) {
                ChannelMessage.XMMsgU parseFrom4 = ChannelMessage.XMMsgU.parseFrom(blob.getPayload());
                LogUploader.getInstance(this.mService).upload(parseFrom4.getUrl(), parseFrom4.getToken(), new Date(parseFrom4.getStart()), new Date(parseFrom4.getEnd()), parseFrom4.getMaxlen() * 1024, parseFrom4.getForce());
                Blob blob2 = new Blob();
                blob2.setChannelId(0);
                blob2.setCmd(blob.getCmd(), "UCA");
                blob2.setPacketID(blob.getPacketID());
                XMPushService xMPushService = this.mService;
                xMPushService.executeJob(new SendMessageJob(xMPushService, blob2));
            } else if (TextUtils.equals("P", blob.getSubcmd())) {
                ChannelMessage.XMMsgP parseFrom5 = ChannelMessage.XMMsgP.parseFrom(blob.getPayload());
                Blob blob3 = new Blob();
                blob3.setChannelId(0);
                blob3.setCmd(blob.getCmd(), "PCA");
                blob3.setPacketID(blob.getPacketID());
                ChannelMessage.XMMsgP xMMsgP = new ChannelMessage.XMMsgP();
                if (parseFrom5.hasCookie()) {
                    xMMsgP.setCookie(parseFrom5.getCookie());
                }
                blob3.setPayload(xMMsgP.toByteArray(), (String) null);
                XMPushService xMPushService2 = this.mService;
                xMPushService2.executeJob(new SendMessageJob(xMPushService2, blob3));
                MyLog.w("ACK msgP: id = " + blob.getPacketID());
            }
        } else if ("NOTIFY".equals(blob.getCmd())) {
            ChannelMessage.XMMsgNotify parseFrom6 = ChannelMessage.XMMsgNotify.parseFrom(blob.getPayload());
            MyLog.w("notify by server err = " + parseFrom6.getErrCode() + " desc = " + parseFrom6.getErrStr());
        }
    }

    public void onBlobReceive(Blob blob) {
        if (5 != blob.getChannelId()) {
            dispatchNetFlow(blob);
        }
        try {
            handleBlob(blob);
        } catch (Exception e) {
            MyLog.e("handle Blob chid = " + blob.getChannelId() + " cmd = " + blob.getCmd() + " packetid = " + blob.getPacketID() + " failure ", e);
        }
    }

    public void onPacketReceive(Packet packet) {
        if (!"5".equals(packet.getChannelId())) {
            dispatchNetFlow(packet);
        }
        String channelId = packet.getChannelId();
        if (TextUtils.isEmpty(channelId)) {
            channelId = "1";
            packet.setChannelId(channelId);
        }
        if (channelId.equals(MovieStatUtils.DOWNLOAD_SUCCESS)) {
            MyLog.w("Received wrong packet with chid = 0 : " + packet.toXML());
        }
        if (packet instanceof IQ) {
            CommonPacketExtension extension = packet.getExtension("kick");
            if (extension != null) {
                String to = packet.getTo();
                String attributeValue = extension.getAttributeValue(nexExportFormat.TAG_FORMAT_TYPE);
                String attributeValue2 = extension.getAttributeValue("reason");
                MyLog.w("kicked by server, chid=" + channelId + " res=" + PushClientsManager.ClientLoginInfo.getResource(to) + " type=" + attributeValue + " reason=" + attributeValue2);
                if ("wait".equals(attributeValue)) {
                    PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(channelId, to);
                    if (clientLoginInfoByChidAndUserId != null) {
                        this.mService.scheduleRebindChannel(clientLoginInfoByChidAndUserId);
                        clientLoginInfoByChidAndUserId.setStatus(PushClientsManager.ClientStatus.unbind, 3, 0, attributeValue2, attributeValue);
                        return;
                    }
                    return;
                }
                this.mService.closeChannel(channelId, to, 3, attributeValue2, attributeValue);
                PushClientsManager.getInstance().deactivateClient(channelId, to);
                return;
            }
        } else if (packet instanceof Message) {
            Message message = (Message) packet;
            if ("redir".equals(message.getType())) {
                CommonPacketExtension extension2 = message.getExtension("hosts");
                if (extension2 != null) {
                    processRedirectMessage(extension2);
                    return;
                }
                return;
            }
        }
        this.mService.getClientEventDispatcher().notifyPacketArrival(this.mService, channelId, packet);
    }
}
