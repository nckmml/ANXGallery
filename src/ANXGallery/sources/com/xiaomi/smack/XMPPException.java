package com.xiaomi.smack;

import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import java.io.PrintStream;
import java.io.PrintWriter;

public class XMPPException extends Exception {
    private XMPPError error = null;
    private StreamError streamError = null;
    private Throwable wrappedThrowable = null;

    public XMPPException() {
    }

    public XMPPException(StreamError streamError2) {
        this.streamError = streamError2;
    }

    public XMPPException(String str) {
        super(str);
    }

    public XMPPException(String str, Throwable th) {
        super(str);
        this.wrappedThrowable = th;
    }

    public XMPPException(Throwable th) {
        this.wrappedThrowable = th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        r1 = r2.streamError;
     */
    public String getMessage() {
        StreamError streamError2;
        XMPPError xMPPError;
        String message = super.getMessage();
        return (message != null || (xMPPError = this.error) == null) ? (message != null || streamError2 == null) ? message : streamError2.toString() : xMPPError.toString();
    }

    public Throwable getWrappedThrowable() {
        return this.wrappedThrowable;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.wrappedThrowable != null) {
            printStream.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.wrappedThrowable != null) {
            printWriter.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printWriter);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        XMPPError xMPPError = this.error;
        if (xMPPError != null) {
            sb.append(xMPPError);
        }
        StreamError streamError2 = this.streamError;
        if (streamError2 != null) {
            sb.append(streamError2);
        }
        if (this.wrappedThrowable != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.wrappedThrowable);
        }
        return sb.toString();
    }
}
