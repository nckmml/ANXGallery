package com.xiaomi.slim;

import android.os.Build;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.push.service.ServiceConfig;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

public class BlobWriter {
    ByteBuffer mBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer mCRCBuf = ByteBuffer.allocate(4);
    private Adler32 mChecksumTool = new Adler32();
    private SlimConnection mConnection;
    private int mDSTSavings;
    private byte[] mKey;
    private OutputStream mOut;
    private int mTimeZone;

    BlobWriter(OutputStream outputStream, SlimConnection slimConnection) {
        this.mOut = new BufferedOutputStream(outputStream);
        this.mConnection = slimConnection;
        TimeZone timeZone = TimeZone.getDefault();
        this.mTimeZone = timeZone.getRawOffset() / 3600000;
        this.mDSTSavings = timeZone.useDaylightTime() ? 1 : 0;
    }

    public void openStream() throws IOException {
        ChannelMessage.XMMsgConn xMMsgConn = new ChannelMessage.XMMsgConn();
        xMMsgConn.setVersion(106);
        xMMsgConn.setModel(Build.MODEL);
        xMMsgConn.setOs(SystemUtils.getManufacturerOSVersion());
        xMMsgConn.setUdid(ServiceConfig.getDeviceUUID());
        xMMsgConn.setSdk(38);
        xMMsgConn.setConnpt(this.mConnection.getConnectionPoint());
        xMMsgConn.setHost(this.mConnection.getHost());
        xMMsgConn.setLocale(Locale.getDefault().toString());
        xMMsgConn.setAndver(Build.VERSION.SDK_INT);
        byte[] connectionBlob = this.mConnection.getConfiguration().getConnectionBlob();
        if (connectionBlob != null) {
            xMMsgConn.setPsc(ChannelMessage.PushServiceConfigMsg.parseFrom(connectionBlob));
        }
        Blob blob = new Blob();
        blob.setChannelId(0);
        blob.setCmd("CONN", (String) null);
        blob.setFrom(0, "xiaomi.com", (String) null);
        blob.setPayload(xMMsgConn.toByteArray(), (String) null);
        write(blob);
        MyLog.w("[slim] open conn: andver=" + Build.VERSION.SDK_INT + " sdk=" + 38 + " hash=" + ServiceConfig.getDeviceUUID() + " tz=" + this.mTimeZone + ":" + this.mDSTSavings + " Model=" + Build.MODEL + " os=" + Build.VERSION.INCREMENTAL);
    }

    public void shutdown() throws IOException {
        Blob blob = new Blob();
        blob.setCmd("CLOSE", (String) null);
        write(blob);
        this.mOut.close();
    }

    public int write(Blob blob) throws IOException {
        int serializedSize = blob.getSerializedSize();
        if (serializedSize > 32768) {
            MyLog.w("Blob size=" + serializedSize + " should be less than " + 32768 + " Drop blob chid=" + blob.getChannelId() + " id=" + blob.getPacketID());
            return 0;
        }
        this.mBuffer.clear();
        int i = serializedSize + 8 + 4;
        if (i > this.mBuffer.capacity() || this.mBuffer.capacity() > 4096) {
            this.mBuffer = ByteBuffer.allocate(i);
        }
        this.mBuffer.putShort(-15618);
        this.mBuffer.putShort(5);
        this.mBuffer.putInt(serializedSize);
        int position = this.mBuffer.position();
        this.mBuffer = blob.toByteArray(this.mBuffer);
        if (!"CONN".equals(blob.getCmd())) {
            if (this.mKey == null) {
                this.mKey = this.mConnection.getKey();
            }
            RC4Cryption.encrypt(this.mKey, this.mBuffer.array(), true, position, serializedSize);
        }
        this.mChecksumTool.reset();
        this.mChecksumTool.update(this.mBuffer.array(), 0, this.mBuffer.position());
        this.mCRCBuf.putInt(0, (int) this.mChecksumTool.getValue());
        this.mOut.write(this.mBuffer.array(), 0, this.mBuffer.position());
        this.mOut.write(this.mCRCBuf.array(), 0, 4);
        this.mOut.flush();
        int position2 = this.mBuffer.position() + 4;
        MyLog.v("[Slim] Wrote {cmd=" + blob.getCmd() + ";chid=" + blob.getChannelId() + ";len=" + position2 + "}");
        return position2;
    }
}
