package com.miui.gallery.util;

import android.database.Cursor;
import android.database.MatrixCursor;

public class CursorUtils {
    public static void addRowToMatrixCursor(Cursor cursor, MatrixCursor matrixCursor, Object[] objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            int type = cursor.getType(i);
            if (type == 0) {
                objArr[i] = null;
            } else if (type == 1) {
                objArr[i] = Long.valueOf(cursor.getLong(i));
            } else if (type == 2) {
                objArr[i] = Double.valueOf(cursor.getDouble(i));
            } else if (type == 3) {
                objArr[i] = cursor.getString(i);
            } else if (type == 4) {
                objArr[i] = cursor.getBlob(i);
            }
        }
        matrixCursor.addRow(objArr);
    }
}
