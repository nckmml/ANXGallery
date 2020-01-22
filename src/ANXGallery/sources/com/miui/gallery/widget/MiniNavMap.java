package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.view.GestureDetector;

public class MiniNavMap extends View {
    private RectF mFrame;
    private int mFrameBorderColor;
    private int mFrameBorderWidth;
    private int mFrameFillColor;
    private GestureDetector mGestureDetector;
    private RectF mHighlight;
    private int mHighlightFillColor;
    private float mHighlightScaleFactor;
    private int mHighlightStrokeColor;
    private int mHighlightStrokeWidth;
    /* access modifiers changed from: private */
    public OnGestureListener mOnGestureListener;
    private Paint mPaint;
    private int mPreferredFrameSize;
    private int mRequestedHeight;
    private int mRequestedWidth;
    private int mRoundedCornerRadius;

    public interface OnGestureListener {
        boolean onMove(float f, float f2);

        void onMoveDone();
    }

    public MiniNavMap(Context context) {
        this(context, (AttributeSet) null);
    }

    public MiniNavMap(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiniNavMap(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFrameFillColor = 777372927;
        this.mFrameBorderColor = -654311425;
        this.mFrameBorderWidth = 2;
        this.mHighlightFillColor = 777372927;
        this.mHighlightStrokeColor = 777372927;
        this.mHighlightStrokeWidth = 5;
        this.mRoundedCornerRadius = 3;
        this.mPreferredFrameSize = 365;
        this.mHighlightScaleFactor = 1.0f;
        this.mFrame = new RectF();
        this.mHighlight = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MiniNavMap, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.mFrameBorderColor = obtainStyledAttributes.getColor(index, -654311425);
                    break;
                case 1:
                    this.mFrameBorderWidth = obtainStyledAttributes.getDimensionPixelSize(index, 2);
                    break;
                case 2:
                    this.mFrameFillColor = obtainStyledAttributes.getColor(index, 788529151);
                    break;
                case 3:
                    this.mHighlightStrokeColor = obtainStyledAttributes.getColor(index, -10826753);
                    break;
                case 4:
                    this.mHighlightStrokeWidth = obtainStyledAttributes.getDimensionPixelSize(index, 5);
                    break;
                case 5:
                    this.mHighlightFillColor = obtainStyledAttributes.getColor(index, 777372927);
                    break;
                case 6:
                    this.mHighlightScaleFactor = obtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 7:
                    this.mPreferredFrameSize = obtainStyledAttributes.getDimensionPixelSize(index, 365);
                    break;
                case 8:
                    this.mRoundedCornerRadius = obtainStyledAttributes.getDimensionPixelSize(index, 3);
                    break;
            }
        }
        obtainStyledAttributes.recycle();
        init();
    }

    private void ensurePaint() {
        if (this.mPaint == null) {
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
        }
    }

    private void init() {
        this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                ViewParent parent = MiniNavMap.this.getParent();
                if (parent == null) {
                    return false;
                }
                parent.requestDisallowInterceptTouchEvent(true);
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (MiniNavMap.this.mOnGestureListener != null) {
                    return MiniNavMap.this.mOnGestureListener.onMove(f, f2);
                }
                return false;
            }
        });
        this.mGestureDetector.setTouchSlop(2);
    }

    public int getPreferredFrameSize() {
        return this.mPreferredFrameSize;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ensurePaint();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mFrameFillColor);
        RectF rectF = this.mFrame;
        int i = this.mRoundedCornerRadius;
        canvas.drawRoundRect(rectF, (float) i, (float) i, this.mPaint);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mFrameBorderColor);
        this.mPaint.setStrokeWidth((float) this.mFrameBorderWidth);
        RectF rectF2 = this.mFrame;
        int i2 = this.mRoundedCornerRadius;
        canvas.drawRoundRect(rectF2, (float) i2, (float) i2, this.mPaint);
        if (this.mFrame.intersects(0.0f, 0.0f, (float) getWidth(), (float) getHeight())) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mHighlightFillColor);
            RectF rectF3 = this.mHighlight;
            int i3 = this.mRoundedCornerRadius;
            canvas.drawRoundRect(rectF3, (float) i3, (float) i3, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(this.mHighlightStrokeColor);
            this.mPaint.setStrokeWidth((float) this.mHighlightStrokeWidth);
            RectF rectF4 = this.mHighlight;
            int i4 = this.mRoundedCornerRadius;
            canvas.drawRoundRect(rectF4, (float) i4, (float) i4, this.mPaint);
            return;
        }
        Log.w("MiniNavMap", "invalid highlight size: %s", this.mHighlight.toShortString());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mRequestedWidth, this.mRequestedHeight);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        OnGestureListener onGestureListener;
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (onTouchEvent) {
            return onTouchEvent;
        }
        if ((actionMasked != 3 && actionMasked != 1) || (onGestureListener = this.mOnGestureListener) == null) {
            return onTouchEvent;
        }
        onGestureListener.onMoveDone();
        return true;
    }

    public boolean setFrameSize(int i, int i2) {
        if (i == this.mRequestedWidth && i2 == this.mRequestedHeight) {
            return false;
        }
        this.mRequestedWidth = i;
        this.mRequestedHeight = i2;
        RectF rectF = this.mFrame;
        int i3 = this.mFrameBorderWidth;
        rectF.set(((float) i3) * 0.5f, ((float) i3) * 0.5f, ((float) i) - (((float) i3) * 0.5f), ((float) i2) - (((float) i3) * 0.5f));
        requestLayout();
        return true;
    }

    public void setOnGestureListener(OnGestureListener onGestureListener) {
        this.mOnGestureListener = onGestureListener;
    }

    public void updateHighlightRect(RectF rectF) {
        this.mHighlight.set(rectF);
        this.mHighlight.inset(-Math.min((rectF.width() * (this.mHighlightScaleFactor - 1.0f)) / 2.0f, Math.max(this.mFrame.width() - rectF.width(), 0.0f) / 2.0f), -Math.min((rectF.height() * (this.mHighlightScaleFactor - 1.0f)) / 2.0f, Math.max(this.mFrame.height() - rectF.height(), 0.0f) / 2.0f));
        if (this.mHighlight.left < 0.0f) {
            RectF rectF2 = this.mHighlight;
            rectF2.offset(-rectF2.left, 0.0f);
        }
        if (this.mHighlight.right > ((float) getWidth())) {
            this.mHighlight.offset(((float) getWidth()) - this.mHighlight.right, 0.0f);
        }
        if (this.mHighlight.top < 0.0f) {
            RectF rectF3 = this.mHighlight;
            rectF3.offset(0.0f, -rectF3.top);
        }
        if (this.mHighlight.bottom > ((float) getHeight())) {
            this.mHighlight.offset(0.0f, ((float) getHeight()) - this.mHighlight.bottom);
        }
        float f = ((float) this.mFrameBorderWidth) + (((float) this.mHighlightStrokeWidth) * 0.5f);
        this.mHighlight.inset(f, f);
        if (getVisibility() == 0) {
            invalidate();
        }
    }
}
