package com.nexstreaming.app.common.localprotocol;

public enum nexProtoErrorCode {
    OK(0),
    InvalidHDR(101),
    InvalidRQ(102),
    InvalidRS(103),
    InvalidSSID(104),
    Unrecognized(800);
    
    private final int mValue;

    private nexProtoErrorCode(int i) {
        this.mValue = i;
    }

    static nexProtoErrorCode fromValue(int i) {
        for (nexProtoErrorCode nexprotoerrorcode : values()) {
            if (nexprotoerrorcode.getValue() == i) {
                return nexprotoerrorcode;
            }
        }
        return Unrecognized;
    }

    public int getValue() {
        return this.mValue;
    }
}
