package com.miui.gallery.util;

import java.lang.reflect.Array;

public class ArrayUtils {
    public static <T> T[] concat(T[] tArr, T[] tArr2) {
        if (tArr == null) {
            return tArr2;
        }
        if (tArr2 == null) {
            return tArr;
        }
        int length = tArr.length + tArr2.length;
        Class<?> cls = tArr.getClass();
        T[] tArr3 = cls == Object[].class ? (Object[]) new Object[length] : (Object[]) Array.newInstance(cls.getComponentType(), length);
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }
}
