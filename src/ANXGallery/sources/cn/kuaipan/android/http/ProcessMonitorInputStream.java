package cn.kuaipan.android.http;

import cn.kuaipan.android.exception.KscTransferStopByCallerException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProcessMonitorInputStream extends FilterInputStream {
    private long mCurrent = 0;
    private final IKscTransferListener mListener;
    private long mMarkPos = 0;
    private final KscSpeedMonitor mMonitor;
    private final boolean mSendMode;
    private final KssTransferStopper mStopper;

    protected ProcessMonitorInputStream(InputStream inputStream, KscSpeedMonitor kscSpeedMonitor, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, boolean z) {
        super(inputStream);
        this.mMonitor = kscSpeedMonitor;
        this.mListener = iKscTransferListener;
        this.mStopper = kssTransferStopper;
        this.mSendMode = z;
    }

    private void process(long j) throws IOException {
        KssTransferStopper kssTransferStopper = this.mStopper;
        if (kssTransferStopper != null && kssTransferStopper.checkStop()) {
            throw new IOException("@ ProcessMonitorInput::process()", new KscTransferStopByCallerException());
        } else if (j >= 0) {
            this.mCurrent += j;
            KscSpeedMonitor kscSpeedMonitor = this.mMonitor;
            if (kscSpeedMonitor != null) {
                kscSpeedMonitor.recode(j);
            }
            IKscTransferListener iKscTransferListener = this.mListener;
            if (iKscTransferListener == null) {
                return;
            }
            if (this.mSendMode) {
                iKscTransferListener.sended(j);
            } else {
                iKscTransferListener.received(j);
            }
        }
    }

    public synchronized void mark(int i) {
        super.mark(i);
        this.mMarkPos = this.mCurrent;
    }

    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            process(1);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read > 0) {
            process((long) read);
        }
        return read;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.mCurrent = this.mMarkPos;
        if (this.mListener != null) {
            if (this.mSendMode) {
                this.mListener.setSendPos(this.mCurrent);
            } else {
                this.mListener.setReceivePos(this.mCurrent);
            }
        }
    }

    public long skip(long j) throws IOException {
        long skip = super.skip(j);
        if (skip > 0) {
            process(skip);
        }
        return skip;
    }
}
