package cn.kuaipan.android.exception;

import android.text.TextUtils;

public class ServerMsgException extends KscException {
    private static final long serialVersionUID = -681123175263669159L;
    private final String origMessage;
    private final int statusCode;

    public ServerMsgException(int i, String str) {
        this(i, str, (String) null);
    }

    public ServerMsgException(int i, String str, String str2) {
        super(ServerMsgMap.getErrorCode(i, str), str2);
        this.statusCode = i;
        this.origMessage = str;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(this.origMessage)) {
            return super.getMessage();
        }
        return this.origMessage + "\n" + super.getMessage();
    }

    public String getSimpleMessage() {
        String str = getClass().getName() + "(ErrCode: " + getErrorCode() + "): StatusCode: " + this.statusCode;
        if (this.origMessage != null) {
            str = str + ", msg: " + this.origMessage;
        }
        if (this.detailMessage == null || this.detailMessage.length() >= 100) {
            return str;
        }
        return str + ", " + this.detailMessage;
    }
}
