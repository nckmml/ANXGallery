package com.miui.gallery.util;

import android.graphics.Matrix;

public class MatrixUtil {
    private static final float[] mMatrixValues = new float[9];

    public static float getRotate(Matrix matrix) {
        if (matrix == null) {
            return 0.0f;
        }
        return (float) (Math.atan2((double) getValue(matrix, 1), (double) getValue(matrix, 0)) * 57.29577951308232d);
    }

    public static float getScale(Matrix matrix) {
        if (matrix == null) {
            return 0.0f;
        }
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(matrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(matrix, 3), 2.0d))));
    }

    public static float[] getTranslate(Matrix matrix) {
        float[] fArr = new float[2];
        if (matrix != null) {
            fArr[0] = getValue(matrix, 2);
            fArr[1] = getValue(matrix, 5);
        }
        return fArr;
    }

    private static float getValue(Matrix matrix, int i) {
        matrix.getValues(mMatrixValues);
        return mMatrixValues[i];
    }
}
