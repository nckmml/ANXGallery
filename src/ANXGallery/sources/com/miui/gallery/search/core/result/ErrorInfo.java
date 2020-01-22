package com.miui.gallery.search.core.result;

public class ErrorInfo {
    private Object mErrorData;
    private int mErrorStatus;

    public ErrorInfo(int i) {
        this(i, (Object) null);
    }

    public ErrorInfo(int i, Object obj) {
        this.mErrorStatus = -1;
        this.mErrorStatus = i;
        this.mErrorData = obj;
    }

    public int getErrorStatus() {
        return this.mErrorStatus;
    }

    public String toString() {
        return super.toString() + " code : " + this.mErrorStatus + ", message : " + this.mErrorData;
    }
}
