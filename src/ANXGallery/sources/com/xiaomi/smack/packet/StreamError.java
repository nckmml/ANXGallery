package com.xiaomi.smack.packet;

public class StreamError {
    private String code;

    public StreamError(String str) {
        this.code = str;
    }

    public String toString() {
        return "stream:error (" + this.code + ")";
    }
}
