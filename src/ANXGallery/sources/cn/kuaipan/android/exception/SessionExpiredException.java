package cn.kuaipan.android.exception;

public class SessionExpiredException extends KscException {
    public final long retryAfterInMillis = 5000;

    public SessionExpiredException() {
        super(220601);
    }

    public SessionExpiredException(String str) {
        super(220601, str);
    }
}
