package com.nexstreaming.app.common.norm;

import android.graphics.Bitmap;
import com.nexstreaming.app.common.norm.b;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class NormColumnInfo {
    final String a;
    final Field b;
    final ColumnType c;
    final boolean d;
    final boolean e;
    final boolean f;
    final boolean g;
    final boolean h;
    final int i;
    final Class<? extends b> j;

    public enum ColumnType {
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        TEXT,
        BOOL,
        JSON,
        ENUM,
        BLOB,
        JPEG,
        PNG
    }

    NormColumnInfo(Field field) {
        ColumnType columnType;
        Class<?> type = field.getType();
        boolean z = false;
        this.f = field.isAnnotationPresent(b.c.class) || b.class.isAssignableFrom(type);
        if (!this.f || !b.class.isAssignableFrom(type)) {
            this.j = null;
        } else {
            this.j = type;
        }
        this.g = (field.getName().equals("_id") || field.isAnnotationPresent(b.f.class)) ? true : z;
        if (this.f) {
            columnType = ColumnType.LONG;
        } else if (type == Integer.class || type == Integer.TYPE) {
            columnType = ColumnType.INT;
        } else if (type == Long.class || type == Long.TYPE) {
            columnType = ColumnType.LONG;
        } else if (type == Float.class || type == Float.TYPE) {
            columnType = ColumnType.FLOAT;
        } else if (type == Double.class || type == Double.TYPE) {
            columnType = ColumnType.DOUBLE;
        } else if (type == String.class) {
            columnType = ColumnType.TEXT;
        } else if (type == Boolean.class || type == Boolean.TYPE) {
            columnType = ColumnType.BOOL;
        } else if (Collection.class.isAssignableFrom(type)) {
            columnType = ColumnType.JSON;
        } else if (Map.class.isAssignableFrom(type)) {
            columnType = ColumnType.JSON;
        } else if (type == byte[].class) {
            columnType = ColumnType.BLOB;
        } else if (type == Bitmap.class) {
            columnType = field.isAnnotationPresent(b.C0001b.class) ? ColumnType.JPEG : ColumnType.PNG;
        } else if (Enum.class.isAssignableFrom(type)) {
            columnType = ColumnType.ENUM;
        } else {
            throw new UnsupportedOperationException("Unrecognized column type: " + type.getSimpleName() + " (for column '" + field.getName() + "')");
        }
        if (!this.g || columnType == ColumnType.LONG) {
            this.d = field.isAnnotationPresent(b.g.class);
            this.h = field.isAnnotationPresent(b.d.class);
            this.e = field.isAnnotationPresent(b.e.class);
            this.a = a.camelCaseToLCUnderscore(field.getName());
            this.b = field;
            this.c = columnType;
            if (field.isAnnotationPresent(b.a.class)) {
                this.i = ((b.a) field.getAnnotation(b.a.class)).a();
            } else {
                this.i = 11;
            }
        } else {
            throw new IllegalArgumentException("Primary key must be 'long' type.");
        }
    }

    public String toString() {
        return this.a;
    }
}
