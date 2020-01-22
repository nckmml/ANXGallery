package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.miui.gallery.R;
import java.util.Random;

public class SlideShowView extends View {
    private AlphaAnimation mAlphaAnim;
    private SlideAnimation mCurrentAnim;
    private Bitmap mCurrentBitmap;
    private boolean mIsAnimEnable;
    /* access modifiers changed from: private */
    public boolean mIsScaleOnlyMode;
    private Paint mPaint;
    private SlideAnimation mPreAnim;
    private Bitmap mPreBitmap;
    private OnRefreshedListener mRefreshedListener;
    /* access modifiers changed from: private */
    public int mScaleMode;
    private int mSlideDuration;
    private Transformation mTransFormation;

    private class FloatAnimation extends AlphaAnimation {
        FloatAnimation(float f, float f2, long j) {
            super(f, f2);
            setDuration(j);
            setInterpolator(new LinearInterpolator());
        }
    }

    public interface OnRefreshedListener {
        void onRefreshed();
    }

    private class SlideAnimation extends Animation {
        private Matrix mBaseMatrix;
        private int mHeight;
        private PointF mMovingVector;
        private int mRotation;
        private PointF mScalePoint;
        private int mWidth;

        SlideAnimation(int i, int i2, int i3, long j) {
            RectF rectF;
            this.mWidth = i;
            this.mHeight = i2;
            this.mRotation = i3;
            Random random = new Random();
            this.mMovingVector = new PointF(((float) this.mWidth) * 0.2f * (random.nextFloat() - 0.5f), ((float) this.mHeight) * 0.2f * (random.nextFloat() - 0.5f));
            RectF rectF2 = new RectF(0.0f, 0.0f, (float) SlideShowView.this.getWidth(), (float) SlideShowView.this.getHeight());
            if (SlideShowView.this.mScaleMode != 1) {
                rectF = new RectF(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight);
            } else if (this.mHeight == 0 || this.mWidth == 0 || SlideShowView.this.getWidth() == 0 || SlideShowView.this.getHeight() == 0) {
                rectF = new RectF(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight);
            } else {
                float f = ((float) this.mHeight) / ((float) this.mWidth);
                float height = ((float) SlideShowView.this.getHeight()) / ((float) SlideShowView.this.getWidth());
                if (f > height) {
                    int i4 = this.mHeight;
                    int i5 = this.mWidth;
                    rectF = new RectF(0.0f, (((float) i4) - (((float) i5) * height)) * 0.5f, (float) i5, (((float) i4) + (((float) i5) * height)) * 0.5f);
                } else {
                    int i6 = this.mWidth;
                    int i7 = this.mHeight;
                    rectF = new RectF((((float) i6) - (((float) i7) / height)) * 0.5f, 0.0f, (((float) i6) + (((float) i7) / height)) * 0.5f, (float) i7);
                }
            }
            this.mBaseMatrix = new Matrix();
            this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            setDuration(j);
            setInterpolator(new LinearInterpolator());
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            if (SlideShowView.this.mIsScaleOnlyMode) {
                if (this.mScalePoint == null) {
                    Random random = new Random();
                    this.mScalePoint = new PointF((float) (random.nextInt(2) * SlideShowView.this.getWidth()), (float) (random.nextInt(2) * SlideShowView.this.getHeight()));
                }
                f6 = this.mScalePoint.x;
                f3 = this.mScalePoint.y;
                f4 = 0.0f;
                f5 = (f * 0.2f) + 1.0f;
                f2 = 0.0f;
            } else {
                f6 = (float) (SlideShowView.this.getWidth() / 2);
                f3 = (float) (SlideShowView.this.getHeight() / 2);
                f5 = (0.2f * f) + 1.0f;
                f4 = this.mMovingVector.x * f;
                f2 = f * this.mMovingVector.y;
            }
            if (this.mRotation > 0) {
                transformation.getMatrix().setRotate((float) this.mRotation, f6, f3);
            }
            transformation.getMatrix().postConcat(this.mBaseMatrix);
            transformation.getMatrix().postTranslate(f4, f2);
            transformation.getMatrix().postScale(f5, f5, f6, f3);
        }
    }

    public SlideShowView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlideShowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideShowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSlideDuration = 3500;
        this.mIsAnimEnable = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlideShowView, i, 0);
        this.mScaleMode = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        initParams();
    }

    private void initParams() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(false);
        this.mTransFormation = new Transformation();
        this.mAlphaAnim = new FloatAnimation(0.0f, 1.0f, 1000);
    }

    private void invalidateView() {
        invalidate();
        OnRefreshedListener onRefreshedListener = this.mRefreshedListener;
        if (onRefreshedListener != null) {
            onRefreshedListener.onRefreshed();
        }
    }

    private boolean isCurValid() {
        Bitmap bitmap = this.mCurrentBitmap;
        return bitmap != null && !bitmap.isRecycled();
    }

    private boolean isPreValid() {
        Bitmap bitmap = this.mPreBitmap;
        return bitmap != null && !bitmap.isRecycled();
    }

    public void next(Bitmap bitmap, int i) {
        this.mAlphaAnim.start();
        if (isPreValid()) {
            this.mPreBitmap.recycle();
            this.mPreBitmap = null;
        }
        this.mPreBitmap = this.mCurrentBitmap;
        this.mPreAnim = this.mCurrentAnim;
        this.mCurrentBitmap = bitmap;
        if (((i / 90) & 1) == 0) {
            this.mCurrentAnim = new SlideAnimation(bitmap.getWidth(), bitmap.getHeight(), i, (long) this.mSlideDuration);
        } else {
            this.mCurrentAnim = new SlideAnimation(bitmap.getHeight(), bitmap.getWidth(), i, (long) this.mSlideDuration);
        }
        this.mCurrentAnim.start();
        invalidateView();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        this.mTransFormation.clear();
        boolean transformation = this.mAlphaAnim.getTransformation(drawingTime, this.mTransFormation);
        float alpha = this.mPreBitmap == null ? 1.0f : this.mTransFormation.getAlpha();
        if (!this.mIsAnimEnable) {
            Bitmap bitmap = null;
            if (isPreValid()) {
                bitmap = this.mPreBitmap;
            } else if (isCurValid()) {
                bitmap = this.mCurrentBitmap;
            }
            if (bitmap != null) {
                int width = getWidth();
                int height = getHeight();
                int width2 = bitmap.getWidth();
                int height2 = bitmap.getHeight();
                Matrix matrix = new Matrix();
                matrix.setTranslate((float) Math.round(((float) (width - width2)) * 0.5f), (float) Math.round(((float) (height - height2)) * 0.5f));
                canvas.save();
                canvas.concat(matrix);
                this.mPaint.setAlpha(255);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
                canvas.restore();
                invalidateView();
                return;
            }
            return;
        }
        if (isPreValid() && alpha < 1.0f) {
            this.mTransFormation.clear();
            transformation |= this.mPreAnim.getTransformation(drawingTime, this.mTransFormation);
            canvas.save();
            canvas.concat(this.mTransFormation.getMatrix());
            this.mPaint.setAlpha((int) ((1.0f - alpha) * 255.0f));
            canvas.drawBitmap(this.mPreBitmap, 0.0f, 0.0f, this.mPaint);
            canvas.restore();
        }
        if (isCurValid() && alpha > 0.0f) {
            this.mTransFormation.clear();
            transformation |= this.mCurrentAnim.getTransformation(drawingTime, this.mTransFormation);
            canvas.save();
            canvas.concat(this.mTransFormation.getMatrix());
            this.mPaint.setAlpha((int) (alpha * 255.0f));
            canvas.drawBitmap(this.mCurrentBitmap, 0.0f, 0.0f, this.mPaint);
            canvas.restore();
        }
        if (transformation) {
            invalidateView();
        }
    }

    public void release() {
        if (isPreValid()) {
            this.mPreBitmap.recycle();
            this.mPreBitmap = null;
        }
        if (isCurValid()) {
            this.mCurrentBitmap.recycle();
            this.mCurrentBitmap = null;
        }
        AlphaAnimation alphaAnimation = this.mAlphaAnim;
        if (alphaAnimation != null) {
            alphaAnimation.cancel();
        }
        SlideAnimation slideAnimation = this.mPreAnim;
        if (slideAnimation != null) {
            slideAnimation.cancel();
        }
        SlideAnimation slideAnimation2 = this.mCurrentAnim;
        if (slideAnimation2 != null) {
            slideAnimation2.cancel();
        }
    }

    public void setRefreshListener(OnRefreshedListener onRefreshedListener) {
        this.mRefreshedListener = onRefreshedListener;
    }

    public void setScaleOnlyMode(boolean z) {
        this.mIsScaleOnlyMode = z;
    }

    public void setSlideAnimEnable(boolean z) {
        this.mIsAnimEnable = z;
    }

    public void setSlideDuration(int i) {
        this.mSlideDuration = Math.max(i, 3500);
    }
}
