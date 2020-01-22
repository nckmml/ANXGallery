package com.xiaomi.xmpush.thrift;

public enum ConfigListType {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);
    
    private final int value;

    private ConfigListType(int i) {
        this.value = i;
    }

    public static ConfigListType findByValue(int i) {
        if (i == 1) {
            return MISC_CONFIG;
        }
        if (i != 2) {
            return null;
        }
        return PLUGIN_CONFIG;
    }

    public int getValue() {
        return this.value;
    }
}
