package com.xiaomi.xmpush.thrift;

public enum ConfigType {
    INT(1),
    LONG(2),
    STRING(3),
    BOOLEAN(4);
    
    private final int value;

    private ConfigType(int i) {
        this.value = i;
    }

    public static ConfigType findByValue(int i) {
        if (i == 1) {
            return INT;
        }
        if (i == 2) {
            return LONG;
        }
        if (i == 3) {
            return STRING;
        }
        if (i != 4) {
            return null;
        }
        return BOOLEAN;
    }
}
