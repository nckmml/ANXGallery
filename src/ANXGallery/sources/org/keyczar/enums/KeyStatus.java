package org.keyczar.enums;

public enum KeyStatus {
    PRIMARY(0, "primary"),
    ACTIVE(1, "active"),
    INACTIVE(2, "inactive");
    
    private String name;
    private int value;

    private KeyStatus(int i, String str) {
        this.value = i;
        this.name = str;
    }

    public static KeyStatus getStatus(int i) {
        if (i == 0) {
            return PRIMARY;
        }
        if (i == 1) {
            return ACTIVE;
        }
        if (i != 2) {
            return null;
        }
        return INACTIVE;
    }

    public static KeyStatus getStatus(String str) {
        if (str != null) {
            if (str.equalsIgnoreCase(PRIMARY.getName())) {
                return PRIMARY;
            }
            if (str.equalsIgnoreCase(ACTIVE.getName())) {
                return ACTIVE;
            }
            if (str.equalsIgnoreCase(INACTIVE.getName())) {
                return INACTIVE;
            }
        }
        return ACTIVE;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public int getValue() {
        return this.value;
    }
}
