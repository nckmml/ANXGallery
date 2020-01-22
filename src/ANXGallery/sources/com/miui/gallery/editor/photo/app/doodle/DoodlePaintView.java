package com.miui.gallery.editor.photo.app.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.util.MiscUtil;

public class DoodlePaintView extends View {
    private int mInnerColor = Color.parseColor("#FF7A31");
    private int mOuterColor = Color.parseColor("#F1F1F1");
    private float mOuterHeight;
    private float mOuterWidth;
    private Paint mPaint = new Paint(1);
    private PaintType mPaintType = PaintType.MEDIUM;
    private float mRadiusPercent = 0.21f;

    public enum PaintType {
        HEAVY(13.333f),
        MEDIUM(4.333f),
        LIGHT(1.333f);
        
        public final float paintSize;

        private PaintType(float f) {
            this.paintSize = f;
        }
    }

    public DoodlePaintView(Context context) {
        super(context);
        init(context);
    }

    public DoodlePaintView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void drawInnerCircle(Canvas canvas) {
        this.mPaint.setColor(this.mInnerColor);
        float f = this.mOuterWidth;
        float f2 = this.mOuterHeight;
        float f3 = this.mRadiusPercent;
        canvas.drawCircle(f / 2.0f, f2 / 2.0f, Math.min(f * f3, f3 * f2) / 2.0f, this.mPaint);
    }

    private void drawOuterCircle(Canvas canvas) {
        this.mPaint.setColor(this.mOuterColor);
        float f = this.mOuterWidth;
        float f2 = this.mOuterHeight;
        canvas.drawCircle(f / 2.0f, f2 / 2.0f, Math.min(f, f2) / 2.0f, this.mPaint);
    }

    private void init(Context context) {
        if (MiscUtil.isNightMode(context)) {
            this.mOuterColor = Color.parseColor("#242424");
        }
    }

    public PaintType getPaintType() {
        return this.mPaintType;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawOuterCircle(canvas);
        drawInnerCircle(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mOuterWidth = (float) View.MeasureSpec.getSize(i);
        this.mOuterHeight = (float) View.MeasureSpec.getSize(i2);
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void setColor(int i) {
        this.mInnerColor = i;
        invalidate();
    }

    public void updateInnerRadiusPercent() {
        float f = this.mRadiusPercent;
        if (f == 0.17f) {
            this.mRadiusPercent = 0.21f;
            this.mPaintType = PaintType.MEDIUM;
        } else if (f == 0.21f) {
            this.mRadiusPercent = 0.25f;
            this.mPaintType = PaintType.HEAVY;
        } else {
            this.mRadiusPercent = 0.17f;
            this.mPaintType = PaintType.LIGHT;
        }
        invalidate();
    }
}
