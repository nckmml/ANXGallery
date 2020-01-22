package com.miui.gallery.editor.photo.widgets.imageview;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import com.miui.gallery.editor.photo.app.MatrixEvaluator;

public class MatrixTransition {
    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
            Matrix[] unused = MatrixTransition.this.mCurrentMatrix = null;
            if (MatrixTransition.this.mMatrixUpdateListener != null) {
                MatrixTransition.this.mMatrixUpdateListener.onAnimationEnd();
            }
        }

        public void onAnimationEnd(Animator animator) {
            Matrix[] unused = MatrixTransition.this.mCurrentMatrix = null;
            if (MatrixTransition.this.mMatrixUpdateListener != null) {
                MatrixTransition.this.mMatrixUpdateListener.onAnimationEnd();
            }
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
            if (MatrixTransition.this.mMatrixUpdateListener != null) {
                MatrixTransition.this.mMatrixUpdateListener.onAnimationStart();
            }
        }
    };
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int length = valueAnimator.getValues().length;
            for (int i = 0; i < length; i++) {
                Matrix matrix = MatrixTransition.this.mCurrentMatrix[i];
                matrix.set((Matrix) valueAnimator.getAnimatedValue("property_matrix" + i));
            }
            if (MatrixTransition.this.mMatrixUpdateListener != null) {
                MatrixTransition.this.mMatrixUpdateListener.onMatrixUpdate();
            }
        }
    };
    /* access modifiers changed from: private */
    public Matrix[] mCurrentMatrix;
    /* access modifiers changed from: private */
    public MatrixUpdateListener mMatrixUpdateListener;
    private ValueAnimator mValueAnimator = new ValueAnimator();

    public interface MatrixUpdateListener {
        void onAnimationEnd();

        void onAnimationStart();

        void onMatrixUpdate();
    }

    public MatrixTransition() {
        this.mValueAnimator.setDuration(300);
        this.mValueAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        this.mValueAnimator.addListener(this.mAnimatorListener);
    }

    public void animMatrix(Matrix... matrixArr) {
        if (matrixArr.length % 2 == 0) {
            this.mValueAnimator.cancel();
            int length = matrixArr.length / 2;
            this.mCurrentMatrix = new Matrix[length];
            PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[length];
            for (int i = 0; i < matrixArr.length; i += 2) {
                Matrix matrix = matrixArr[i];
                Matrix matrix2 = matrixArr[i + 1];
                int i2 = i / 2;
                this.mCurrentMatrix[i2] = matrix;
                propertyValuesHolderArr[i2] = PropertyValuesHolder.ofObject("property_matrix" + i2, new MatrixEvaluator(), new Object[]{new Matrix(matrix), matrix2});
            }
            this.mValueAnimator.setValues(propertyValuesHolderArr);
            this.mValueAnimator.start();
            return;
        }
        throw new RuntimeException("values length should be a even number !");
    }

    public void setMatrixUpdateListener(MatrixUpdateListener matrixUpdateListener) {
        this.mMatrixUpdateListener = matrixUpdateListener;
    }
}
