package com.miui.gallery.editor.photo.widgets.imageview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import com.miui.gallery.editor.photo.widgets.imageview.MatrixTransition;

public class BitmapGestureParamsHolder {
    private Matrix mAnimTargetMatrix = new Matrix();
    public RectF mBitmapDisplayInitRect = new RectF();
    public RectF mBitmapDisplayInsideRect = new RectF();
    public RectF mBitmapDisplayRect = new RectF();
    public RectF mBitmapRect = new RectF();
    public Matrix mBitmapToDisplayMatrix = new Matrix();
    public Matrix mCanvasMatrix = new Matrix();
    public Matrix mCanvasMatrixInvert = new Matrix();
    public RectF mDisplayInitRect = new RectF();
    public RectF mDisplayInsideRect = new RectF();
    public RectF mDisplayRect = new RectF();
    public Matrix mDisplayToBitmapMatrix = new Matrix();
    private MatrixTransition mMatrixTransition;
    private MatrixTransition.MatrixUpdateListener mMatrixUpdateListener = new MatrixTransition.MatrixUpdateListener() {
        public void onAnimationEnd() {
        }

        public void onAnimationStart() {
        }

        public void onMatrixUpdate() {
            BitmapGestureParamsHolder.this.performCanvasMatrixChange();
        }
    };
    private float[] mMatrixValues = new float[9];
    private float mMaxWidth;
    public float mMaxWidthScale;
    private ParamsChangeListener mParamsChangeListener;
    private RectF mRectFTemp = new RectF();
    private float mScaleFocusX = 0.0f;
    private float mScaleFocusY = 0.0f;
    private View mTarget;

    public interface ParamsChangeListener {
        void onBitmapMatrixChanged();

        void onCanvasMatrixChange();
    }

    public BitmapGestureParamsHolder(View view, ParamsChangeListener paramsChangeListener) {
        this.mTarget = view;
        this.mMatrixTransition = new MatrixTransition();
        this.mMatrixTransition.setMatrixUpdateListener(this.mMatrixUpdateListener);
        this.mParamsChangeListener = paramsChangeListener;
    }

    private void onBitmapMatrixChanged() {
        ParamsChangeListener paramsChangeListener = this.mParamsChangeListener;
        if (paramsChangeListener != null) {
            paramsChangeListener.onBitmapMatrixChanged();
        }
    }

    private void onCanvasMatrixChange() {
        ParamsChangeListener paramsChangeListener = this.mParamsChangeListener;
        if (paramsChangeListener != null) {
            paramsChangeListener.onCanvasMatrixChange();
        }
    }

    private void refreshBitmapDisplayRect() {
        this.mCanvasMatrix.mapRect(this.mBitmapDisplayRect, this.mBitmapDisplayInitRect);
        this.mCanvasMatrix.invert(this.mCanvasMatrixInvert);
        this.mCanvasMatrixInvert.mapRect(this.mDisplayInsideRect, this.mDisplayRect);
        this.mBitmapDisplayInsideRect.set(this.mBitmapDisplayRect);
        this.mBitmapDisplayInsideRect.intersect(this.mDisplayRect);
    }

    private void refreshBitmapInitRect() {
        this.mBitmapToDisplayMatrix.reset();
        this.mBitmapToDisplayMatrix.setRectToRect(this.mBitmapRect, this.mDisplayInitRect, Matrix.ScaleToFit.CENTER);
        this.mBitmapDisplayInitRect.set(this.mBitmapRect);
        this.mBitmapToDisplayMatrix.mapRect(this.mBitmapDisplayInitRect);
        this.mDisplayToBitmapMatrix.reset();
        this.mDisplayToBitmapMatrix.setRectToRect(this.mBitmapDisplayInitRect, this.mBitmapRect, Matrix.ScaleToFit.FILL);
    }

    private void resetBitmapMatrix() {
        if (!this.mBitmapRect.isEmpty() && !this.mDisplayInitRect.isEmpty()) {
            refreshBitmapInitRect();
            this.mBitmapDisplayRect.set(this.mBitmapDisplayInitRect);
            this.mDisplayInsideRect.set(this.mDisplayRect);
            this.mBitmapDisplayInsideRect.set(this.mBitmapDisplayRect);
            this.mMaxWidth = this.mBitmapDisplayInitRect.width() * 4.0f;
            this.mMaxWidthScale = this.mBitmapDisplayInitRect.width() * 6.0f;
            onBitmapMatrixChanged();
        }
    }

    private void resolvePadding(RectF rectF) {
        rectF.left += (float) this.mTarget.getPaddingLeft();
        rectF.right -= (float) this.mTarget.getPaddingRight();
        rectF.top += (float) this.mTarget.getPaddingTop();
        rectF.bottom -= (float) this.mTarget.getPaddingBottom();
    }

    public float convertDistanceX(float f) {
        return f * (this.mDisplayInsideRect.width() / this.mDisplayRect.width());
    }

    public float convertDistanceY(float f) {
        return f * (this.mDisplayInsideRect.height() / this.mDisplayRect.height());
    }

    public void convertPointToBitmapCoordinate(MotionEvent motionEvent, float[] fArr) {
        fArr[0] = motionEvent.getX();
        fArr[1] = motionEvent.getY();
        convertPointToBitmapCoordinate(fArr);
    }

    public void convertPointToBitmapCoordinate(float[] fArr) {
        convertPointToViewPortCoordinate(fArr);
        this.mDisplayToBitmapMatrix.mapPoints(fArr);
    }

    public void convertPointToViewPortCoordinate(float[] fArr) {
        this.mCanvasMatrixInvert.mapPoints(fArr);
    }

    /* access modifiers changed from: protected */
    public void countAnimMatrixWhenZoomIn(Matrix matrix) {
        float f;
        Matrix matrix2 = matrix;
        matrix2.set(this.mCanvasMatrix);
        this.mRectFTemp.set(this.mBitmapDisplayRect);
        RectF rectF = this.mRectFTemp;
        float width = rectF.width();
        float f2 = this.mMaxWidth;
        if (width > f2) {
            float width2 = f2 / rectF.width();
            matrix2.postScale(width2, width2, this.mScaleFocusX, this.mScaleFocusY);
            matrix2.mapRect(rectF, this.mBitmapDisplayInitRect);
        }
        float width3 = rectF.width();
        float height = rectF.height();
        float f3 = rectF.left;
        float f4 = rectF.right;
        float f5 = rectF.top;
        float f6 = rectF.bottom;
        float width4 = this.mDisplayRect.width();
        float height2 = this.mDisplayRect.height();
        float f7 = this.mDisplayRect.left;
        float f8 = this.mDisplayRect.right;
        float f9 = this.mDisplayRect.top;
        float f10 = this.mDisplayRect.bottom;
        float f11 = 0.0f;
        float width5 = f3 > f7 ? width3 > width4 ? -(f3 - f7) : (-(f3 - f7)) + ((width4 - width3) * ((this.mBitmapDisplayInitRect.left - this.mDisplayRect.left) / (this.mDisplayRect.width() - this.mBitmapDisplayInitRect.width()))) : f4 < f8 ? width3 > width4 ? f8 - f4 : (f8 - f4) - ((width4 - width3) * (1.0f - ((this.mBitmapDisplayInitRect.left - this.mDisplayRect.left) / (this.mDisplayRect.width() - this.mBitmapDisplayInitRect.width())))) : 0.0f;
        if (f5 > f9) {
            if (height > height2) {
                f = -(f5 - f9);
                matrix2.postTranslate(width5, f);
            }
            f11 = (-(f5 - f9)) + ((height2 - height) * ((this.mBitmapDisplayInitRect.top - this.mDisplayRect.top) / (this.mDisplayRect.height() - this.mBitmapDisplayInitRect.height())));
        } else if (f6 < f10) {
            f11 = height > height2 ? f10 - f6 : (f10 - f6) - ((height2 - height) * (1.0f - ((this.mBitmapDisplayInitRect.top - this.mDisplayRect.top) / (this.mDisplayRect.height() - this.mBitmapDisplayInitRect.height()))));
        }
        f = f11;
        matrix2.postTranslate(width5, f);
    }

    /* access modifiers changed from: protected */
    public void countAnimMatrixWhenZoomOut(Matrix matrix) {
        matrix.reset();
    }

    public float[] getMatrixValues() {
        this.mCanvasMatrix.getValues(this.mMatrixValues);
        return this.mMatrixValues;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mDisplayRect.set(0.0f, 0.0f, (float) i, (float) i2);
        this.mDisplayInitRect.set(this.mDisplayRect);
        resolvePadding(this.mDisplayInitRect);
        resetBitmapMatrix();
    }

    public void performCanvasMatrixChange() {
        refreshBitmapDisplayRect();
        onCanvasMatrixChange();
    }

    public void performScale(float f, float f2, float f3) {
        this.mCanvasMatrix.postScale(f, f, f2, f3);
        this.mScaleFocusX = f2;
        this.mScaleFocusY = f3;
        performCanvasMatrixChange();
    }

    public void performTransition(float f, float f2) {
        float width = this.mDisplayRect.width() / 2.0f;
        float height = this.mDisplayRect.height() / 2.0f;
        float centerX = this.mDisplayRect.centerX();
        float centerY = this.mDisplayRect.centerY();
        float f3 = 1.0f;
        float f4 = (f <= 0.0f || this.mBitmapDisplayRect.left <= this.mDisplayRect.left) ? (f >= 0.0f || this.mBitmapDisplayRect.right >= this.mDisplayRect.right) ? 1.0f : (this.mBitmapDisplayRect.right - centerX) / width : (centerX - this.mBitmapDisplayRect.left) / width;
        if (f2 > 0.0f && this.mBitmapDisplayRect.top > this.mDisplayRect.top) {
            f3 = (centerY - this.mBitmapDisplayRect.top) / height;
        } else if (f2 < 0.0f && this.mBitmapDisplayRect.bottom < this.mDisplayRect.bottom) {
            f3 = (this.mBitmapDisplayRect.bottom - centerY) / height;
        }
        this.mCanvasMatrix.postTranslate(f * f4, f2 * f3);
        performCanvasMatrixChange();
    }

    public void resetMatrixWithAnim() {
        this.mCanvasMatrix.getValues(this.mMatrixValues);
        if (this.mMatrixValues[0] <= 1.0f) {
            countAnimMatrixWhenZoomOut(this.mAnimTargetMatrix);
        } else {
            countAnimMatrixWhenZoomIn(this.mAnimTargetMatrix);
        }
        this.mMatrixTransition.animMatrix(this.mCanvasMatrix, this.mAnimTargetMatrix);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmapRect.set(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        resetBitmapMatrix();
    }
}
