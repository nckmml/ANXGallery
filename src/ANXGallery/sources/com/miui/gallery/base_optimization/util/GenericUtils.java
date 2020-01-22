package com.miui.gallery.base_optimization.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {
    public static <T> T getClassT(Object obj, int i) {
        try {
            Type genericSuperclass = obj.getClass().getGenericSuperclass();
            if (!(genericSuperclass instanceof ParameterizedType)) {
                genericSuperclass = obj.getClass().getSuperclass().getGenericSuperclass();
            }
            return ((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[i]).newInstance();
        } catch (ClassCastException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
