package org.apache.thrift.transport;

public abstract class TTransport {
    public void consumeBuffer(int i) {
    }

    public byte[] getBuffer() {
        return null;
    }

    public int getBufferPosition() {
        return 0;
    }

    public int getBytesRemainingInBuffer() {
        return -1;
    }

    public abstract int read(byte[] bArr, int i, int i2) throws TTransportException;

    public int readAll(byte[] bArr, int i, int i2) throws TTransportException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read(bArr, i + i3, i2 - i3);
            if (read > 0) {
                i3 += read;
            } else {
                throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes.");
            }
        }
        return i3;
    }

    public abstract void write(byte[] bArr, int i, int i2) throws TTransportException;
}
