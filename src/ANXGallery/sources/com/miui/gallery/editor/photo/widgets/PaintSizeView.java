package com.miui.gallery.editor.photo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.R;

public class PaintSizeView extends View {
    private int mDiameter;
    private Paint mPaint;

    public PaintSizeView(Context context) {
        super(context);
        init();
    }

    public PaintSizeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PaintSizeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(getResources().getDimension(R.dimen.paint_stroke_size));
        this.mPaint.setColor(-1);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(((float) canvas.getWidth()) / 2.0f, ((float) canvas.getHeight()) / 2.0f, ((float) this.mDiameter) / 2.0f, this.mPaint);
    }

    public void setDiameter(int i) {
        this.mDiameter = i;
        invalidate();
    }
}
