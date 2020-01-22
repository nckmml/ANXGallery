package com.xiaomi.slim;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.StringUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class Blob {
    private static final byte[] EMPTY = new byte[0];
    private static long id = 0;
    private static String prefix = (StringUtils.randomString(5) + "-");
    private ChannelMessage.ClientHeader mHeader;
    String mPackageName;
    private byte[] mPayload;
    private short mPayloadType;

    public Blob() {
        this.mPayloadType = 2;
        this.mPayload = EMPTY;
        this.mPackageName = null;
        this.mHeader = new ChannelMessage.ClientHeader();
    }

    Blob(ChannelMessage.ClientHeader clientHeader, short s, byte[] bArr) {
        this.mPayloadType = 2;
        this.mPayload = EMPTY;
        this.mPackageName = null;
        this.mHeader = clientHeader;
        this.mPayloadType = s;
        this.mPayload = bArr;
    }

    @Deprecated
    public static Blob from(Packet packet, String str) {
        int i;
        Blob blob = new Blob();
        try {
            i = Integer.parseInt(packet.getChannelId());
        } catch (Exception e) {
            MyLog.w("Blob parse chid err " + e.getMessage());
            i = 1;
        }
        blob.setChannelId(i);
        blob.setPacketID(packet.getPacketID());
        blob.setFrom(packet.getFrom());
        blob.setPackageName(packet.getPackageName());
        blob.setCmd("XMLMSG", (String) null);
        try {
            blob.setPayload(packet.toXML().getBytes("utf8"), str);
            if (TextUtils.isEmpty(str)) {
                blob.setPayloadType(3);
            } else {
                blob.setPayloadType(2);
                blob.setCmd("SECMSG", (String) null);
            }
        } catch (UnsupportedEncodingException e2) {
            MyLog.w("Blob setPayload err： " + e2.getMessage());
        }
        return blob;
    }

    static Blob from(ByteBuffer byteBuffer) throws IOException {
        try {
            ByteBuffer slice = byteBuffer.slice();
            short s = slice.getShort(0);
            short s2 = slice.getShort(2);
            int i = slice.getInt(4);
            ChannelMessage.ClientHeader clientHeader = new ChannelMessage.ClientHeader();
            clientHeader.mergeFrom(slice.array(), slice.arrayOffset() + 8, s2);
            byte[] bArr = new byte[i];
            slice.position(s2 + 8);
            slice.get(bArr, 0, i);
            return new Blob(clientHeader, s, bArr);
        } catch (Exception e) {
            MyLog.w("read Blob err :" + e.getMessage());
            throw new IOException("Malformed Input");
        }
    }

    public static synchronized String nextID() {
        String sb;
        synchronized (Blob.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(prefix);
            long j = id;
            id = 1 + j;
            sb2.append(Long.toString(j));
            sb = sb2.toString();
        }
        return sb;
    }

    public int getChannelId() {
        return this.mHeader.getChid();
    }

    public String getCmd() {
        return this.mHeader.getCmd();
    }

    public byte[] getDecryptedPayload(String str) {
        if (this.mHeader.getCipher() == 1) {
            return RC4Cryption.encrypt(RC4Cryption.generateKeyForRC4(str, getPacketID()), this.mPayload);
        }
        if (this.mHeader.getCipher() == 0) {
            return this.mPayload;
        }
        MyLog.w("unknow cipher = " + this.mHeader.getCipher());
        return this.mPayload;
    }

    public int getErrCode() {
        return this.mHeader.getErrCode();
    }

    public String getErrStr() {
        return this.mHeader.getErrStr();
    }

    public String getFullUserName() {
        if (!this.mHeader.hasUuid()) {
            return null;
        }
        return Long.toString(this.mHeader.getUuid()) + "@" + this.mHeader.getServer() + "/" + this.mHeader.getResource();
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getPacketID() {
        String id2 = this.mHeader.getId();
        if ("ID_NOT_AVAILABLE".equals(id2)) {
            return null;
        }
        if (this.mHeader.hasId()) {
            return id2;
        }
        String nextID = nextID();
        this.mHeader.setId(nextID);
        return nextID;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public short getPayloadType() {
        return this.mPayloadType;
    }

    public int getSerializedSize() {
        return this.mHeader.getSerializedSize() + 8 + this.mPayload.length;
    }

    public String getSubcmd() {
        return this.mHeader.getSubcmd();
    }

    public boolean hasErr() {
        return this.mHeader.hasErrCode();
    }

    public void setChannelId(int i) {
        this.mHeader.setChid(i);
    }

    public void setCmd(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.mHeader.setCmd(str);
            this.mHeader.clearSubcmd();
            if (!TextUtils.isEmpty(str2)) {
                this.mHeader.setSubcmd(str2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("command should not be empty");
    }

    public void setFrom(long j, String str, String str2) {
        if (j != 0) {
            this.mHeader.setUuid(j);
        }
        if (!TextUtils.isEmpty(str)) {
            this.mHeader.setServer(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.mHeader.setResource(str2);
        }
    }

    public void setFrom(String str) {
        if (!TextUtils.isEmpty(str)) {
            int indexOf = str.indexOf("@");
            try {
                long parseLong = Long.parseLong(str.substring(0, indexOf));
                int indexOf2 = str.indexOf("/", indexOf);
                String substring = str.substring(indexOf + 1, indexOf2);
                String substring2 = str.substring(indexOf2 + 1);
                this.mHeader.setUuid(parseLong);
                this.mHeader.setServer(substring);
                this.mHeader.setResource(substring2);
            } catch (Exception e) {
                MyLog.w("Blob parse user err " + e.getMessage());
            }
        }
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public void setPacketID(String str) {
        this.mHeader.setId(str);
    }

    public void setPayload(byte[] bArr, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mHeader.setCipher(1);
            this.mPayload = RC4Cryption.encrypt(RC4Cryption.generateKeyForRC4(str, getPacketID()), bArr);
            return;
        }
        this.mHeader.setCipher(0);
        this.mPayload = bArr;
    }

    public void setPayloadType(short s) {
        this.mPayloadType = s;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer toByteArray(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(getSerializedSize());
        }
        byteBuffer.putShort(this.mPayloadType);
        byteBuffer.putShort((short) this.mHeader.getCachedSize());
        byteBuffer.putInt(this.mPayload.length);
        int position = byteBuffer.position();
        this.mHeader.toByteArray(byteBuffer.array(), byteBuffer.arrayOffset() + position, this.mHeader.getCachedSize());
        byteBuffer.position(position + this.mHeader.getCachedSize());
        byteBuffer.put(this.mPayload);
        return byteBuffer;
    }

    public String toString() {
        return "Blob [chid=" + getChannelId() + "; Id=" + getPacketID() + "; cmd=" + getCmd() + "; type=" + getPayloadType() + "; from=" + getFullUserName() + " ]";
    }
}
