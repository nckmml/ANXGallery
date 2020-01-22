package com.xiaomi.xmpush.thrift;

public enum RegistrationReason {
    RegIdExpired(0),
    PackageUnregistered(1),
    Init(2);
    
    private final int value;

    private RegistrationReason(int i) {
        this.value = i;
    }

    public static RegistrationReason findByValue(int i) {
        if (i == 0) {
            return RegIdExpired;
        }
        if (i == 1) {
            return PackageUnregistered;
        }
        if (i != 2) {
            return null;
        }
        return Init;
    }

    public int getValue() {
        return this.value;
    }
}
