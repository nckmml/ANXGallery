package com.xiaomi.slim;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.DebugUtils;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.RC4Cryption;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

class BlobReader {
    private ByteBuffer mBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer mCRCBuf = ByteBuffer.allocate(4);
    private Adler32 mChecksumTool = new Adler32();
    private SlimConnection mConnection;
    private volatile boolean mDone;
    private InputStream mInputStream;
    private byte[] mKey;
    private PacketParser mPacketParser;

    BlobReader(InputStream inputStream, SlimConnection slimConnection) {
        this.mInputStream = new BufferedInputStream(inputStream);
        this.mConnection = slimConnection;
        this.mPacketParser = new PacketParser();
    }

    private void loop() throws IOException {
        boolean z = false;
        this.mDone = false;
        Blob read = read();
        if ("CONN".equals(read.getCmd())) {
            ChannelMessage.XMMsgConnResp parseFrom = ChannelMessage.XMMsgConnResp.parseFrom(read.getPayload());
            if (parseFrom.hasChallenge()) {
                this.mConnection.setChallenge(parseFrom.getChallenge());
                z = true;
            }
            if (parseFrom.hasPsc()) {
                ChannelMessage.PushServiceConfigMsg psc = parseFrom.getPsc();
                Blob blob = new Blob();
                blob.setCmd("SYNC", "CONF");
                blob.setPayload(psc.toByteArray(), (String) null);
                this.mConnection.notifyDataArrived(blob);
            }
            MyLog.w("[Slim] CONN: host = " + parseFrom.getHost());
        }
        if (z) {
            this.mKey = this.mConnection.getKey();
            while (!this.mDone) {
                Blob read2 = read();
                this.mConnection.setReadAlive();
                short payloadType = read2.getPayloadType();
                if (payloadType == 1) {
                    this.mConnection.notifyDataArrived(read2);
                } else if (payloadType != 2) {
                    if (payloadType != 3) {
                        MyLog.w("[Slim] unknow blob type " + read2.getPayloadType());
                    } else {
                        try {
                            this.mConnection.notifyDataArrived(this.mPacketParser.parse(read2.getPayload(), this.mConnection));
                        } catch (Exception e) {
                            MyLog.w("[Slim] Parse packet from Blob chid=" + read2.getChannelId() + "; Id=" + read2.getPacketID() + " failure:" + e.getMessage());
                        }
                    }
                } else if (!"SECMSG".equals(read2.getCmd()) || (!(read2.getChannelId() == 2 || read2.getChannelId() == 3) || !TextUtils.isEmpty(read2.getSubcmd()))) {
                    this.mConnection.notifyDataArrived(read2);
                } else {
                    try {
                        this.mConnection.notifyDataArrived(this.mPacketParser.parse(read2.getDecryptedPayload(PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(Integer.valueOf(read2.getChannelId()).toString(), read2.getFullUserName()).security), this.mConnection));
                    } catch (Exception e2) {
                        MyLog.w("[Slim] Parse packet from Blob chid=" + read2.getChannelId() + "; Id=" + read2.getPacketID() + " failure:" + e2.getMessage());
                    }
                }
            }
            return;
        }
        MyLog.w("[Slim] Invalid CONN");
        throw new IOException("Invalid Connection");
    }

    private void read(ByteBuffer byteBuffer, int i) throws IOException {
        int position = byteBuffer.position();
        do {
            int read = this.mInputStream.read(byteBuffer.array(), position, i);
            if (read != -1) {
                i -= read;
                position += read;
            } else {
                throw new EOFException();
            }
        } while (i > 0);
        byteBuffer.position(position);
    }

    private ByteBuffer readOnePacket() throws IOException {
        this.mBuffer.clear();
        read(this.mBuffer, 8);
        short s = this.mBuffer.getShort(0);
        short s2 = this.mBuffer.getShort(2);
        if (s == -15618 && s2 == 5) {
            int i = this.mBuffer.getInt(4);
            int position = this.mBuffer.position();
            if (i <= 32768) {
                if (i + 4 > this.mBuffer.remaining()) {
                    ByteBuffer allocate = ByteBuffer.allocate(i + 2048);
                    allocate.put(this.mBuffer.array(), 0, this.mBuffer.arrayOffset() + this.mBuffer.position());
                    this.mBuffer = allocate;
                } else if (this.mBuffer.capacity() > 4096 && i < 2048) {
                    ByteBuffer allocate2 = ByteBuffer.allocate(2048);
                    allocate2.put(this.mBuffer.array(), 0, this.mBuffer.arrayOffset() + this.mBuffer.position());
                    this.mBuffer = allocate2;
                }
                read(this.mBuffer, i);
                this.mCRCBuf.clear();
                read(this.mCRCBuf, 4);
                this.mCRCBuf.position(0);
                int i2 = this.mCRCBuf.getInt();
                this.mChecksumTool.reset();
                this.mChecksumTool.update(this.mBuffer.array(), 0, this.mBuffer.position());
                if (i2 == ((int) this.mChecksumTool.getValue())) {
                    byte[] bArr = this.mKey;
                    if (bArr != null) {
                        RC4Cryption.encrypt(bArr, this.mBuffer.array(), true, position, i);
                    }
                    return this.mBuffer;
                }
                MyLog.w("CRC = " + ((int) this.mChecksumTool.getValue()) + " and " + i2);
                throw new IOException("Corrupted Blob bad CRC");
            }
            throw new IOException("Blob size too large");
        }
        throw new IOException("Malformed Input");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0073  */
    public Blob read() throws IOException {
        int i;
        try {
            ByteBuffer readOnePacket = readOnePacket();
            i = readOnePacket.position();
            try {
                readOnePacket.flip();
                readOnePacket.position(8);
                Blob ping = i == 8 ? new Ping() : Blob.from(readOnePacket.slice());
                MyLog.v("[Slim] Read {cmd=" + ping.getCmd() + ";chid=" + ping.getChannelId() + ";len=" + i + "}");
                return ping;
            } catch (IOException e) {
                e = e;
                if (i == 0) {
                }
                StringBuilder sb = new StringBuilder();
                sb.append("[Slim] read Blob [");
                byte[] array = this.mBuffer.array();
                if (i > 128) {
                }
                sb.append(DebugUtils.bytes2Hex(array, 0, i));
                sb.append("] Err:");
                sb.append(e.getMessage());
                MyLog.w(sb.toString());
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            i = 0;
            if (i == 0) {
                i = this.mBuffer.position();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[Slim] read Blob [");
            byte[] array2 = this.mBuffer.array();
            if (i > 128) {
                i = 128;
            }
            sb2.append(DebugUtils.bytes2Hex(array2, 0, i));
            sb2.append("] Err:");
            sb2.append(e.getMessage());
            MyLog.w(sb2.toString());
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.mDone = true;
    }

    /* access modifiers changed from: package-private */
    public void start() throws IOException {
        try {
            loop();
        } catch (IOException e) {
            if (!this.mDone) {
                throw e;
            }
        }
    }
}
