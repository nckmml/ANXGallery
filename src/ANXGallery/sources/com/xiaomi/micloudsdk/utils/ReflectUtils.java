package com.xiaomi.micloudsdk.utils;

import java.lang.reflect.Field;

public class ReflectUtils {
    public static Field getField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getStaticFieldIntValue(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt((Object) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class loadClass(String str) {
        try {
            return ReflectUtils.class.getClassLoader().loadClass(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
