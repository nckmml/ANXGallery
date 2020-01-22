package cn.kuaipan.android.http;

import cn.kuaipan.android.exception.KscTransferStopByCallerException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

public class ProcessMonitorEntity extends HttpEntityWrapper {
    private final IKscTransferListener mListener;
    private final KscSpeedMonitor mMonitor;
    private boolean mMonitorUsed = false;
    private final boolean mSendMode;
    private final KssTransferStopper mStopper;

    public ProcessMonitorEntity(HttpEntity httpEntity, KscSpeedMonitor kscSpeedMonitor, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, boolean z) {
        super(httpEntity);
        this.mMonitor = kscSpeedMonitor;
        this.mListener = iKscTransferListener;
        this.mStopper = kssTransferStopper;
        this.mSendMode = z;
    }

    public InputStream getContent() throws IOException {
        KssTransferStopper kssTransferStopper = this.mStopper;
        if (kssTransferStopper == null || !kssTransferStopper.checkStop()) {
            InputStream content = ProcessMonitorEntity.super.getContent();
            if (this.mMonitorUsed) {
                return content;
            }
            IKscTransferListener iKscTransferListener = this.mListener;
            if (iKscTransferListener != null) {
                if (this.mSendMode) {
                    iKscTransferListener.setSendTotal(getContentLength());
                } else {
                    iKscTransferListener.setReceiveTotal(getContentLength());
                }
            }
            ProcessMonitorInputStream processMonitorInputStream = new ProcessMonitorInputStream(content, this.mMonitor, this.mListener, this.mStopper, this.mSendMode);
            this.mMonitorUsed = true;
            return processMonitorInputStream;
        }
        throw new IOException("@ getContent()", new KscTransferStopByCallerException());
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        KssTransferStopper kssTransferStopper = this.mStopper;
        if (kssTransferStopper == null || !kssTransferStopper.checkStop()) {
            if (!this.mMonitorUsed) {
                ProcessMonitorOutputStream processMonitorOutputStream = new ProcessMonitorOutputStream(outputStream, this.mMonitor, this.mListener, this.mStopper, this.mSendMode);
                this.mMonitorUsed = true;
                outputStream = processMonitorOutputStream;
            }
            ProcessMonitorEntity.super.writeTo(outputStream);
            return;
        }
        throw new IOException("@ writeTo()", new KscTransferStopByCallerException());
    }
}
