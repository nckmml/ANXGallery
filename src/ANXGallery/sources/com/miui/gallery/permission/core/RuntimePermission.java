package com.miui.gallery.permission.core;

public class RuntimePermission {
    public final String desc;
    public final String name;
    public final boolean required;

    public RuntimePermission(String str, String str2, boolean z) {
        this.name = str;
        this.desc = str2;
        this.required = z;
    }
}
