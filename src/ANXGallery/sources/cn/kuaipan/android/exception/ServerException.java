package cn.kuaipan.android.exception;

public class ServerException extends KscException {
    private static final long serialVersionUID = 6373467541984892922L;
    private final int statusCode;

    public ServerException(int i, String str) {
        super(validCode(i) + 503000, str);
        this.statusCode = i;
    }

    private static int validCode(int i) {
        if (i < 100 || i > 599) {
            return 0;
        }
        return i;
    }

    public String getSimpleMessage() {
        String str = getClass().getName() + "(ErrCode: " + getErrorCode() + "): StatusCode: " + this.statusCode;
        if (this.detailMessage == null || this.detailMessage.length() >= 100) {
            return str;
        }
        return str + ", " + this.detailMessage;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
