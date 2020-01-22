package com.miui.gallery.reddot;

public abstract class RedDot implements Rules {
    String mKey;

    RedDot(String str) {
        this.mKey = str;
    }

    public String getKey() {
        return this.mKey;
    }
}
