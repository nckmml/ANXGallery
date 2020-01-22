package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.util.MiscUtil;

public class ColorRingProgress extends View {
    private int mBackColor;
    private int mColorInterval;
    private float mColorIntervalPercent;
    private int mForeColor;
    private Paint mPaint;
    private float mProgress;
    private RectF mRectF;
    private int mThickness;

    public ColorRingProgress(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0, 0);
    }

    public ColorRingProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public ColorRingProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorRingProgress, i, i2);
        this.mForeColor = obtainStyledAttributes.getColor(2, -1);
        this.mBackColor = obtainStyledAttributes.getColor(0, -6899465);
        this.mThickness = obtainStyledAttributes.getDimensionPixelSize(3, 14);
        this.mColorInterval = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        this.mRectF = new RectF();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) this.mThickness);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() * getHeight() > 0) {
            float f = this.mColorIntervalPercent;
            float f2 = (f * 360.0f) / 2.0f;
            float f3 = this.mProgress;
            if (1.0f - f3 < f * 2.0f || MiscUtil.floatEquals(f3, 0.0f)) {
                f2 = 0.0f;
            }
            float f4 = -90.0f - f2;
            this.mPaint.setColor(0);
            float f5 = f2 * 2.0f;
            int i = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
            if (i > 0) {
                canvas.drawArc(this.mRectF, f4, f5, false, this.mPaint);
            }
            this.mPaint.setColor(this.mForeColor);
            float f6 = f4 + f5;
            float f7 = (this.mProgress * 360.0f) - f2;
            float f8 = f6 + f7 > 270.0f ? 270.0f - f6 : f7;
            if (f8 > 0.0f) {
                canvas.drawArc(this.mRectF, f6, f8, false, this.mPaint);
            }
            this.mPaint.setColor(0);
            float f9 = f6 + f8;
            if (i > 0) {
                canvas.drawArc(this.mRectF, f9, f5, false, this.mPaint);
            }
            this.mPaint.setColor(this.mBackColor);
            float f10 = f9 + f5;
            float f11 = (270.0f - f10) - f2;
            if (f11 > 0.0f) {
                canvas.drawArc(this.mRectF, f10, f11, false, this.mPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        RectF rectF = this.mRectF;
        int i5 = this.mThickness;
        rectF.set((float) i5, (float) i5, (float) (getWidth() - this.mThickness), (float) (getHeight() - this.mThickness));
        this.mColorIntervalPercent = (float) (((double) this.mColorInterval) / (((double) getWidth()) * 3.141592653589793d));
    }

    public void setBackgroundColor(int i) {
        this.mBackColor = i;
        invalidate();
    }

    public void setForegroundColor(int i) {
        this.mForeColor = i;
        invalidate();
    }

    public void setProgress(float f) {
        this.mProgress = Math.min(Math.max(f, 0.0f), 1.0f);
        invalidate();
    }
}
