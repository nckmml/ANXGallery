package com.xiaomi.slim;

import android.text.TextUtils;
import com.google.protobuf.micro.ByteStringMicro;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.SocketConnection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;

public class SlimConnection extends SocketConnection {
    private byte[] mKey;
    /* access modifiers changed from: private */
    public BlobReader mReader;
    private Thread mReaderThread;
    private BlobWriter mWriter;

    public SlimConnection(XMPushService xMPushService, ConnectionConfiguration connectionConfiguration) {
        super(xMPushService, connectionConfiguration);
    }

    private Blob getPing(boolean z) {
        Ping ping = new Ping();
        if (z) {
            ping.setPacketID("1");
        }
        byte[] retriveStatsAsByte = StatsHelper.retriveStatsAsByte();
        if (retriveStatsAsByte != null) {
            ChannelMessage.XMMsgPing xMMsgPing = new ChannelMessage.XMMsgPing();
            xMMsgPing.setStats(ByteStringMicro.copyFrom(retriveStatsAsByte));
            ping.setPayload(xMMsgPing.toByteArray(), (String) null);
        }
        return ping;
    }

    private void initReaderAndWriter() throws XMPPException {
        try {
            this.mReader = new BlobReader(this.socket.getInputStream(), this);
            this.mWriter = new BlobWriter(this.socket.getOutputStream(), this);
            this.mReaderThread = new Thread("Blob Reader (" + this.connectionCounterValue + ")") {
                public void run() {
                    try {
                        SlimConnection.this.mReader.start();
                    } catch (Exception e) {
                        SlimConnection.this.notifyConnectionError(9, e);
                    }
                }
            };
            this.mReaderThread.start();
        } catch (Exception e) {
            throw new XMPPException("Error to init reader and writer", e);
        }
    }

    public void batchSend(Blob[] blobArr) throws XMPPException {
        for (Blob send : blobArr) {
            send(send);
        }
    }

    public synchronized void bind(PushClientsManager.ClientLoginInfo clientLoginInfo) throws XMPPException {
        Binder.bind(clientLoginInfo, getChallenge(), this);
    }

    /* access modifiers changed from: package-private */
    public synchronized byte[] getKey() {
        if (this.mKey == null && !TextUtils.isEmpty(this.challenge)) {
            String deviceUUID = ServiceConfig.getDeviceUUID();
            this.mKey = RC4Cryption.encrypt(this.challenge.getBytes(), (this.challenge.substring(this.challenge.length() / 2) + deviceUUID.substring(deviceUUID.length() / 2)).getBytes());
        }
        return this.mKey;
    }

    /* access modifiers changed from: protected */
    public synchronized void initConnection() throws XMPPException, IOException {
        initReaderAndWriter();
        this.mWriter.openStream();
    }

    public boolean isBinaryConnection() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void notifyDataArrived(Blob blob) {
        if (blob != null) {
            if (blob.hasErr()) {
                MyLog.w("[Slim] RCV blob chid=" + blob.getChannelId() + "; id=" + blob.getPacketID() + "; errCode=" + blob.getErrCode() + "; err=" + blob.getErrStr());
            }
            if (blob.getChannelId() == 0) {
                if ("PING".equals(blob.getCmd())) {
                    MyLog.w("[Slim] RCV ping id=" + blob.getPacketID());
                    updateLastReceived();
                } else if ("CLOSE".equals(blob.getCmd())) {
                    notifyConnectionError(13, (Exception) null);
                }
            }
            for (Connection.ListenerWrapper notifyListener : this.recvListeners.values()) {
                notifyListener.notifyListener(blob);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyDataArrived(Packet packet) {
        if (packet != null) {
            for (Connection.ListenerWrapper notifyListener : this.recvListeners.values()) {
                notifyListener.notifyListener(packet);
            }
        }
    }

    public void send(Blob blob) throws XMPPException {
        BlobWriter blobWriter = this.mWriter;
        if (blobWriter != null) {
            try {
                int write = blobWriter.write(blob);
                this.writeAlive = System.currentTimeMillis();
                String packageName = blob.getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    TrafficUtils.distributionTraffic(this.mPushService, packageName, (long) write, false, true, System.currentTimeMillis());
                }
                for (Connection.ListenerWrapper notifyListener : this.sendListeners.values()) {
                    notifyListener.notifyListener(blob);
                }
            } catch (Exception e) {
                throw new XMPPException((Throwable) e);
            }
        } else {
            throw new XMPPException("the writer is null.");
        }
    }

    @Deprecated
    public void sendPacket(Packet packet) throws XMPPException {
        send(Blob.from(packet, (String) null));
    }

    /* access modifiers changed from: protected */
    public void sendPing(boolean z) throws XMPPException {
        if (this.mWriter != null) {
            Blob ping = getPing(z);
            MyLog.w("[Slim] SND ping id=" + ping.getPacketID());
            send(ping);
            updateLastSent();
            return;
        }
        throw new XMPPException("The BlobWriter is null.");
    }

    /* access modifiers changed from: protected */
    public synchronized void shutdown(int i, Exception exc) {
        if (this.mReader != null) {
            this.mReader.shutdown();
            this.mReader = null;
        }
        if (this.mWriter != null) {
            try {
                this.mWriter.shutdown();
            } catch (Exception e) {
                MyLog.e((Throwable) e);
            }
            this.mWriter = null;
        }
        this.mKey = null;
        super.shutdown(i, exc);
    }

    public synchronized void unbind(String str, String str2) throws XMPPException {
        Binder.unbind(str, str2, this);
    }
}
