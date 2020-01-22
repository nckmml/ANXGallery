package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;
import org.keyczar.Keyczar;

public class XmPushTBinaryProtocol extends TBinaryProtocol {
    private static int MAX_THRIFT_BINARY_SIZE = 104857600;
    private static int MAX_THRIFT_LIST_SIZE = 10000;
    private static int MAX_THRIFT_MAP_SIZE = 10000;
    private static int MAX_THRIFT_SET_SIZE = 10000;
    private static int MAX_THRIFT_STRING_SIZE = 10485760;

    public static class Factory extends TBinaryProtocol.Factory {
        public Factory() {
            super(false, true);
        }

        public Factory(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        public TProtocol getProtocol(TTransport tTransport) {
            XmPushTBinaryProtocol xmPushTBinaryProtocol = new XmPushTBinaryProtocol(tTransport, this.strictRead_, this.strictWrite_);
            if (this.readLength_ != 0) {
                xmPushTBinaryProtocol.setReadLength(this.readLength_);
            }
            return xmPushTBinaryProtocol;
        }
    }

    public XmPushTBinaryProtocol(TTransport tTransport, boolean z, boolean z2) {
        super(tTransport, z, z2);
    }

    public ByteBuffer readBinary() throws TException {
        int readI32 = readI32();
        if (readI32 <= MAX_THRIFT_BINARY_SIZE) {
            checkReadLength(readI32);
            if (this.trans_.getBytesRemainingInBuffer() >= readI32) {
                ByteBuffer wrap = ByteBuffer.wrap(this.trans_.getBuffer(), this.trans_.getBufferPosition(), readI32);
                this.trans_.consumeBuffer(readI32);
                return wrap;
            }
            byte[] bArr = new byte[readI32];
            this.trans_.readAll(bArr, 0, readI32);
            return ByteBuffer.wrap(bArr);
        }
        throw new TProtocolException(3, "Thrift binary size " + readI32 + " out of range!");
    }

    public TList readListBegin() throws TException {
        byte readByte = readByte();
        int readI32 = readI32();
        if (readI32 <= MAX_THRIFT_LIST_SIZE) {
            return new TList(readByte, readI32);
        }
        throw new TProtocolException(3, "Thrift list size " + readI32 + " out of range!");
    }

    public TMap readMapBegin() throws TException {
        byte readByte = readByte();
        byte readByte2 = readByte();
        int readI32 = readI32();
        if (readI32 <= MAX_THRIFT_MAP_SIZE) {
            return new TMap(readByte, readByte2, readI32);
        }
        throw new TProtocolException(3, "Thrift map size " + readI32 + " out of range!");
    }

    public TSet readSetBegin() throws TException {
        byte readByte = readByte();
        int readI32 = readI32();
        if (readI32 <= MAX_THRIFT_SET_SIZE) {
            return new TSet(readByte, readI32);
        }
        throw new TProtocolException(3, "Thrift set size " + readI32 + " out of range!");
    }

    public String readString() throws TException {
        int readI32 = readI32();
        if (readI32 > MAX_THRIFT_STRING_SIZE) {
            throw new TProtocolException(3, "Thrift string size " + readI32 + " out of range!");
        } else if (this.trans_.getBytesRemainingInBuffer() < readI32) {
            return readStringBody(readI32);
        } else {
            try {
                String str = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), readI32, Keyczar.DEFAULT_ENCODING);
                this.trans_.consumeBuffer(readI32);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new TException("JVM DOES NOT SUPPORT UTF-8");
            }
        }
    }
}
