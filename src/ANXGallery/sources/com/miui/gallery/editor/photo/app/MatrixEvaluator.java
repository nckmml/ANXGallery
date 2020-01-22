package com.miui.gallery.editor.photo.app;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private float[] mTempEndValues = new float[9];
    private Matrix mTempMatrix = new Matrix();
    private float[] mTempStartValues = new float[9];

    public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.mTempStartValues);
        matrix2.getValues(this.mTempEndValues);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.mTempEndValues;
            float f2 = fArr[i];
            float[] fArr2 = this.mTempStartValues;
            fArr[i] = fArr2[i] + ((f2 - fArr2[i]) * f);
        }
        this.mTempMatrix.setValues(this.mTempEndValues);
        return this.mTempMatrix;
    }
}
