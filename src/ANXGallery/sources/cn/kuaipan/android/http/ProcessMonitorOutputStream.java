package cn.kuaipan.android.http;

import cn.kuaipan.android.exception.KscTransferStopByCallerException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ProcessMonitorOutputStream extends FilterOutputStream {
    private final IKscTransferListener mListener;
    private final KscSpeedMonitor mMonitor;
    private final boolean mSendMode;
    private final KssTransferStopper mStopper;

    public ProcessMonitorOutputStream(OutputStream outputStream, KscSpeedMonitor kscSpeedMonitor, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, boolean z) {
        super(outputStream);
        this.mMonitor = kscSpeedMonitor;
        this.mListener = iKscTransferListener;
        this.mStopper = kssTransferStopper;
        this.mSendMode = z;
    }

    private void process(long j) throws IOException {
        KssTransferStopper kssTransferStopper = this.mStopper;
        if (kssTransferStopper != null && kssTransferStopper.checkStop()) {
            throw new IOException("@ ProcessMonitorOutputStream::process()", new KscTransferStopByCallerException());
        } else if (j >= 0) {
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

    public void write(int i) throws IOException {
        super.write(i);
        process(1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        process((long) i2);
    }
}
