package cn.kuaipan.android.exception;

public class KscRuntimeException extends RuntimeException implements IKscError {
    private static final long serialVersionUID = 4693852528580738850L;
    private final String detailMessage;
    private final int errCode;

    public KscRuntimeException(int i) {
        this(i, (String) null, (Throwable) null);
    }

    public KscRuntimeException(int i, String str) {
        this(i, str, (Throwable) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public KscRuntimeException(int i, String str, Throwable th) {
        super(r0.toString(), KscException.getSerial(th));
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("ErrCode:");
        sb.append(i);
        if (str == null) {
            str2 = "";
        } else {
            str2 = " details:" + str;
        }
        sb.append(str2);
        this.errCode = i;
        this.detailMessage = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public KscRuntimeException(int i, Throwable th) {
        this(i, th == null ? null : th.toString(), th);
    }

    public int getErrorCode() {
        return this.errCode;
    }
}
