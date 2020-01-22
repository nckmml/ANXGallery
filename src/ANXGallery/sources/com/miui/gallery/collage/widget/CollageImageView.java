package com.miui.gallery.collage.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.collage.render.CollageRender;
import com.miui.gallery.editor.photo.widgets.imageview.MatrixTransition;

public class CollageImageView extends View {
    private final float MAX_SCALE = 4.0f;
    private final float MAX_SCALE_TOUCH = 6.0f;
    /* access modifiers changed from: private */
    public boolean mActivating = false;
    private Matrix mAnimTargetMatrix = new Matrix();
    private int mBackgroundColor;
    private Paint mBackgroundPaint = new Paint();
    private RectF mBitmapDisplayInitRect = new RectF();
    private RectF mBitmapDisplayRect = new RectF();
    private Matrix mBitmapMatrix = new Matrix();
    private Paint mBitmapPaint = new Paint(3);
    private RectF mBitmapRect = new RectF();
    private Matrix mCanvasMatrix = new Matrix();
    private RectF mDisplayInitRect = new RectF();
    private RectF mDisplayRect = new RectF();
    private boolean mDrawBitmap = true;
    private Matrix mDrawingMatrix = new Matrix();
    private GradientDrawable mGradientDrawable = new GradientDrawable();
    private Drawable mMask;
    private MatrixListener mMatrixListener = new MatrixListener();
    private MatrixTransition mMatrixTransition;
    private float[] mMatrixValues = new float[9];
    private float mMaxWidth;
    private float mMaxWidthScale;
    private boolean mMirror = false;
    private Bitmap mOriginBitmap;
    private float mRadius = 0.0f;
    private RectF mRectFTemp = new RectF();
    private int mRotateDegree = 0;
    private PorterDuffXfermode mXfermodeSrcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private PorterDuffXfermode mXfermodeSrcOut = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);

    private class MatrixListener implements MatrixTransition.MatrixUpdateListener {
        private MatrixListener() {
        }

        public void onAnimationEnd() {
            boolean unused = CollageImageView.this.mActivating = false;
        }

        public void onAnimationStart() {
        }

        public void onMatrixUpdate() {
            CollageImageView.this.invalidate();
            CollageImageView.this.refreshBitmapDisplayRect();
        }
    }

    public CollageImageView(Context context) {
        super(context);
        init();
    }

    private void drawBitmap(Canvas canvas) {
        canvas.drawRect(this.mDisplayRect, this.mBackgroundPaint);
        if (this.mDrawBitmap) {
            canvas.drawBitmap(this.mOriginBitmap, this.mDrawingMatrix, this.mBitmapPaint);
        }
    }

    private void drawBitmapWithMask(Canvas canvas) {
        this.mMask.setBounds(0, 0, getWidth(), getHeight());
        drawBitmapWithMaskDrawable(canvas, this.mMask, true);
    }

    private void drawBitmapWithMaskDrawable(Canvas canvas, Drawable drawable, boolean z) {
        canvas.saveLayer(this.mDisplayRect, (Paint) null, 31);
        drawable.draw(canvas);
        this.mBackgroundPaint.setXfermode(z ? this.mXfermodeSrcOut : this.mXfermodeSrcIn);
        this.mBitmapPaint.setXfermode(this.mXfermodeSrcIn);
        drawBitmap(canvas);
        this.mBitmapPaint.setXfermode((Xfermode) null);
        this.mBackgroundPaint.setXfermode((Xfermode) null);
        canvas.restore();
    }

    private void drawBitmapWithRadius(Canvas canvas) {
        this.mGradientDrawable.setBounds(0, 0, getWidth(), getHeight());
        this.mGradientDrawable.setCornerRadius(this.mRadius);
        drawBitmapWithMaskDrawable(canvas, this.mGradientDrawable, false);
    }

    private void init() {
        this.mMatrixTransition = new MatrixTransition();
        this.mMatrixTransition.setMatrixUpdateListener(this.mMatrixListener);
        this.mBackgroundColor = getResources().getColor(R.color.collage_item_background);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mBackgroundPaint.setColor(this.mBackgroundColor);
        this.mGradientDrawable.setColor(-1);
    }

    /* access modifiers changed from: private */
    public void refreshBitmapDisplayRect() {
        this.mCanvasMatrix.mapRect(this.mBitmapDisplayRect, this.mBitmapDisplayInitRect);
    }

    private void resetBitmapMatrix() {
        if (!this.mBitmapRect.isEmpty() && !this.mDisplayInitRect.isEmpty()) {
            CollageRender.initBitmapMatrix(this.mBitmapRect, this.mBitmapMatrix, this.mDisplayInitRect, this.mMirror, this.mRotateDegree, this.mBitmapDisplayInitRect);
            resetInitParams();
            this.mCanvasMatrix.reset();
            invalidate();
        }
    }

    private void resetBitmapMatrixWithAnim() {
        if (!this.mBitmapRect.isEmpty() && !this.mDisplayInitRect.isEmpty()) {
            CollageRender.initBitmapMatrix(this.mBitmapRect, this.mAnimTargetMatrix, this.mDisplayInitRect, this.mMirror, this.mRotateDegree, this.mBitmapDisplayInitRect);
            resetInitParams();
            this.mMatrixTransition.animMatrix(this.mBitmapMatrix, this.mAnimTargetMatrix, this.mCanvasMatrix, new Matrix());
        }
    }

    private void resetInitParams() {
        this.mBitmapDisplayRect.set(this.mBitmapDisplayInitRect);
        this.mMaxWidth = this.mBitmapDisplayInitRect.width() * 4.0f;
        this.mMaxWidthScale = this.mBitmapDisplayInitRect.width() * 6.0f;
    }

    private void resetMatrixWithAnim() {
        this.mActivating = true;
        this.mCanvasMatrix.getValues(this.mMatrixValues);
        if (this.mMatrixValues[0] <= 1.0f) {
            countAnimMatrixWhenZoomOut(this.mAnimTargetMatrix);
        } else {
            countAnimMatrixWhenZoomIn(this.mAnimTargetMatrix);
        }
        this.mMatrixTransition.animMatrix(this.mCanvasMatrix, this.mAnimTargetMatrix);
    }

    private void resolvePadding(RectF rectF) {
        rectF.left += (float) getPaddingLeft();
        rectF.right -= (float) getPaddingRight();
        rectF.top += (float) getPaddingTop();
        rectF.bottom -= (float) getPaddingBottom();
    }

    public void appendScale(float f, float f2, float f3) {
        if (f <= 1.0f || this.mBitmapDisplayRect.width() <= this.mMaxWidthScale) {
            this.mCanvasMatrix.postScale(f, f, f2 - ((float) getLeft()), f3 - ((float) getTop()));
            refreshBitmapDisplayRect();
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void countAnimMatrixWhenZoomIn(Matrix matrix) {
        Matrix matrix2 = matrix;
        matrix2.set(this.mCanvasMatrix);
        this.mRectFTemp.set(this.mBitmapDisplayRect);
        RectF rectF = this.mRectFTemp;
        float width = rectF.width();
        float f = this.mMaxWidth;
        if (width > f) {
            float width2 = f / rectF.width();
            matrix2.postScale(width2, width2, this.mDisplayInitRect.centerX(), this.mDisplayInitRect.centerY());
            matrix2.mapRect(rectF, this.mBitmapDisplayInitRect);
        }
        float width3 = rectF.width();
        float height = rectF.height();
        float f2 = rectF.left;
        float f3 = rectF.right;
        float f4 = rectF.top;
        float f5 = rectF.bottom;
        float width4 = this.mDisplayRect.width();
        float height2 = this.mDisplayRect.height();
        float f6 = this.mDisplayRect.left;
        float f7 = this.mDisplayRect.right;
        float f8 = this.mDisplayRect.top;
        float f9 = this.mDisplayRect.bottom;
        float f10 = 0.0f;
        float f11 = f2 > f6 ? width3 > width4 ? -(f2 - f6) : (-(f2 - f6)) + ((width4 - width3) * 0.5f) : f3 < f7 ? width3 > width4 ? f7 - f3 : (f7 - f3) - ((width4 - width3) * 0.5f) : 0.0f;
        if (f4 > f8) {
            f10 = height > height2 ? -(f4 - f8) : (-(f4 - f8)) + ((height2 - height) * 0.5f);
        } else if (f5 < f9) {
            f10 = height > height2 ? f9 - f5 : (f9 - f5) - ((height2 - height) * 0.5f);
        }
        matrix2.postTranslate(f11, f10);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0064  */
    public void countAnimMatrixWhenZoomOut(Matrix matrix) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        matrix.set(this.mCanvasMatrix);
        matrix.getValues(this.mMatrixValues);
        RectF rectF = this.mRectFTemp;
        rectF.set(this.mBitmapDisplayRect);
        float f6 = 1.0f / this.mMatrixValues[0];
        matrix.postScale(f6, f6, this.mDisplayInitRect.centerX(), this.mDisplayInitRect.centerY());
        matrix.mapRect(rectF, this.mBitmapDisplayInitRect);
        float f7 = 0.0f;
        if (rectF.left > this.mDisplayInitRect.left) {
            f4 = this.mDisplayInitRect.left;
            f5 = rectF.left;
        } else if (rectF.right < this.mDisplayInitRect.right) {
            f4 = this.mDisplayInitRect.right;
            f5 = rectF.right;
        } else {
            f = 0.0f;
            if (rectF.top <= this.mDisplayInitRect.top) {
                f2 = this.mDisplayInitRect.top;
                f3 = rectF.top;
            } else {
                if (rectF.bottom < this.mDisplayInitRect.bottom) {
                    f2 = this.mDisplayInitRect.bottom;
                    f3 = rectF.bottom;
                }
                matrix.postTranslate(f, f7);
            }
            f7 = f2 - f3;
            matrix.postTranslate(f, f7);
        }
        f = f4 - f5;
        if (rectF.top <= this.mDisplayInitRect.top) {
        }
        f7 = f2 - f3;
        matrix.postTranslate(f, f7);
    }

    public CollageRender.BitmapRenderData generateBitmapRenderData() {
        Matrix matrix = new Matrix();
        RectF rectF = new RectF();
        this.mCanvasMatrix.invert(matrix);
        matrix.mapRect(rectF, this.mBitmapDisplayInitRect);
        rectF.left = (rectF.left - this.mBitmapDisplayInitRect.left) / this.mBitmapDisplayInitRect.width();
        rectF.right = (rectF.right - this.mBitmapDisplayInitRect.left) / this.mBitmapDisplayInitRect.width();
        rectF.top = (rectF.top - this.mBitmapDisplayInitRect.top) / this.mBitmapDisplayInitRect.height();
        rectF.bottom = (rectF.bottom - this.mBitmapDisplayInitRect.top) / this.mBitmapDisplayInitRect.height();
        CollageRender.BitmapRenderData bitmapRenderData = new CollageRender.BitmapRenderData();
        bitmapRenderData.bitmap = this.mOriginBitmap;
        bitmapRenderData.rotate = this.mRotateDegree;
        bitmapRenderData.mirror = this.mMirror;
        bitmapRenderData.bitmapInsideRect.set(rectF);
        bitmapRenderData.transition = true;
        bitmapRenderData.maskDrawable = this.mMask;
        bitmapRenderData.radius = this.mRadius;
        return bitmapRenderData;
    }

    public Bitmap getBitmap() {
        return this.mOriginBitmap;
    }

    public void getCanvasMatrix(Matrix matrix) {
        matrix.set(this.mCanvasMatrix);
    }

    public void getDisplayRect(RectF rectF) {
        rectF.set(this.mDisplayRect);
    }

    public int getRotateDegree() {
        return this.mRotateDegree;
    }

    public boolean isActivating() {
        return this.mActivating;
    }

    public boolean isMirror() {
        return this.mMirror;
    }

    public void mirror() {
        this.mMirror = !this.mMirror;
        resetBitmapMatrix();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mOriginBitmap != null) {
            this.mDrawingMatrix.reset();
            this.mDrawingMatrix.postConcat(this.mBitmapMatrix);
            this.mDrawingMatrix.postConcat(this.mCanvasMatrix);
            canvas.save();
            canvas.clipRect(this.mDisplayRect);
            if (this.mMask != null) {
                drawBitmapWithMask(canvas);
            } else if (this.mRadius > 0.0f) {
                drawBitmapWithRadius(canvas);
            } else {
                drawBitmap(canvas);
            }
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mDisplayRect.set(0.0f, 0.0f, (float) i, (float) i2);
        this.mDisplayInitRect.set(this.mDisplayRect);
        resolvePadding(this.mDisplayInitRect);
        resetBitmapMatrix();
    }

    public void resetBitmapLocation() {
        resetMatrixWithAnim();
    }

    public void resetDrawData(Bitmap bitmap, int i, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.mOriginBitmap = bitmap;
        this.mBitmapRect.set(0.0f, 0.0f, (float) width, (float) height);
        this.mRotateDegree = i;
        this.mMirror = z;
        this.mDrawBitmap = true;
        resetBitmapMatrix();
    }

    public void rotate() {
        this.mRotateDegree -= 90;
        this.mRotateDegree %= 360;
        resetBitmapMatrixWithAnim();
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public void setBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.mOriginBitmap = bitmap;
        this.mBitmapRect.set(0.0f, 0.0f, (float) width, (float) height);
        resetBitmapMatrix();
    }

    public void setDrawBitmap(boolean z) {
        this.mDrawBitmap = z;
        invalidate();
    }

    public void setMask(Drawable drawable) {
        this.mMask = drawable;
        invalidate();
    }

    public void setRadius(float f) {
        this.mRadius = f;
        invalidate();
    }

    public void transition(float f, float f2) {
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
        refreshBitmapDisplayRect();
        invalidate();
    }
}
