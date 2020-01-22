package cn.kuaipan.android.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

public class KscException extends Exception implements IKscError {
    private static final long serialVersionUID = 7461260166746901326L;
    protected final String detailMessage;
    private final int errCode;

    public KscException(int i) {
        this(i, (String) null, (Throwable) null);
    }

    public KscException(int i, String str) {
        this(i, str, (Throwable) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public KscException(int i, String str, Throwable th) {
        super(r0.toString(), getSerial(th));
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("ErrCode: ");
        sb.append(i);
        if (str == null) {
            str2 = "";
        } else {
            str2 = "\n" + str;
        }
        sb.append(str2);
        this.errCode = i;
        this.detailMessage = str;
    }

    static Throwable getSerial(Throwable th) {
        return (th != null && (th instanceof HttpHostConnectException)) ? new HttpHostConnectExceptionWrapper((HttpHostConnectException) th) : th;
    }

    public static KscException newException(Throwable th, String str) throws InterruptedException {
        Throwable transformIfStopByCallerException = ErrorHelper.transformIfStopByCallerException(th);
        if (transformIfStopByCallerException instanceof KscException) {
            return (KscException) transformIfStopByCallerException;
        }
        ErrorHelper.handleInterruptException(transformIfStopByCallerException);
        if (transformIfStopByCallerException instanceof ConnectException) {
            return new NetworkException(504111, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof SocketException) {
            return new NetworkException(504000, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof SocketTimeoutException) {
            return new NetworkException(504400, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof ConnectTimeoutException) {
            return new NetworkException(504110, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof ClientProtocolException) {
            return new NetworkException(504500, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof UnknownHostException) {
            return new NetworkException(504501, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof InvalidKeyException) {
            return new KscException(500009, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof FileNotFoundException) {
            return new KscException(403001, str, transformIfStopByCallerException);
        }
        if (transformIfStopByCallerException instanceof IOException) {
            return new KscException(403000, str, transformIfStopByCallerException);
        }
        if (!(transformIfStopByCallerException instanceof RuntimeException)) {
            return new KscException(403999, str, transformIfStopByCallerException);
        }
        throw ((RuntimeException) transformIfStopByCallerException);
    }

    public int getErrorCode() {
        return this.errCode;
    }

    public String getSimpleMessage() {
        String str = getClass().getName() + "(ErrCode: " + this.errCode + ")";
        String str2 = this.detailMessage;
        if (str2 == null || str2.length() >= 100) {
            return str;
        }
        return str + ": " + this.detailMessage;
    }
}
