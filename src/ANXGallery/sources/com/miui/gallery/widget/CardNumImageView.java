package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import com.miui.gallery.R;

public class CardNumImageView extends ForegroundImageView {
    private Paint.FontMetricsInt mFontMetrics;
    private int mNum;
    private TextPaint mTextPaint;
    private int mTextSize;

    public CardNumImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardNumImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardNumImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardNumImageView, i, 0);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.card_num_imageview_textsize);
        if (obtainStyledAttributes != null) {
            this.mNum = obtainStyledAttributes.getInt(0, 0);
            this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(1, dimensionPixelSize);
            obtainStyledAttributes.recycle();
        } else {
            this.mTextSize = dimensionPixelSize;
        }
        initPaint();
    }

    private void drawNumText(Canvas canvas) {
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawText(String.valueOf(this.mNum), (float) rect.centerX(), (float) ((((rect.bottom + rect.top) - this.mFontMetrics.bottom) - this.mFontMetrics.top) / 2), this.mTextPaint);
    }

    private void initPaint() {
        this.mTextPaint = new TextPaint(257);
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mTextPaint.setColor(-1);
        this.mFontMetrics = this.mTextPaint.getFontMetricsInt();
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawNumText(canvas);
    }

    public void setNum(int i) {
        if (this.mNum != i) {
            this.mNum = i;
            invalidate();
        }
    }
}
