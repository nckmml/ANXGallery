package com.miui.gallery.search.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.R;

public class IntroIconView extends View {
    private int mBackgroundColor;
    private Drawable mIconDrawable;
    private Paint mLandPaint;
    private Path mLandPath;

    public IntroIconView(Context context) {
        this(context, (AttributeSet) null);
    }

    public IntroIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    /* JADX INFO: finally extract failed */
    public IntroIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.IntroIconView);
        try {
            this.mIconDrawable = obtainStyledAttributes.getDrawable(1);
            this.mBackgroundColor = obtainStyledAttributes.getColor(0, context.getResources().getColor(R.color.intro_icon_bg));
            int color = obtainStyledAttributes.getColor(2, context.getResources().getColor(R.color.intro_icon_land_color));
            obtainStyledAttributes.recycle();
            this.mLandPaint = new Paint();
            this.mLandPaint.setAntiAlias(true);
            this.mLandPaint.setColor(color);
            this.mLandPath = new Path();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas2.drawColor(this.mBackgroundColor);
        float f = (float) height;
        float f2 = 0.75f * f;
        this.mLandPath.reset();
        float f3 = (float) width;
        this.mLandPath.moveTo(f3, f2);
        this.mLandPath.lineTo(f3, f);
        this.mLandPath.lineTo(0.0f, f);
        this.mLandPath.lineTo(0.0f, f2);
        int i = (int) (0.03f * f);
        int i2 = 0;
        while (i2 < width) {
            this.mLandPath.lineTo((float) i2, (float) (((double) f2) - (((double) i) * Math.cos(((((double) (((float) (i2 + width)) / f3)) * 0.8d) + 0.6d) * 3.141592653589793d))));
            i2++;
            f2 = f2;
            i = i;
        }
        this.mLandPath.close();
        canvas2.drawPath(this.mLandPath, this.mLandPaint);
        float f4 = 0.68f * f;
        int intrinsicWidth = ((int) (f3 - ((((float) this.mIconDrawable.getIntrinsicWidth()) * f4) / ((float) this.mIconDrawable.getIntrinsicHeight())))) / 2;
        int i3 = ((int) (f - f4)) / 2;
        this.mIconDrawable.setBounds(intrinsicWidth, i3, width - intrinsicWidth, height - i3);
        this.mIconDrawable.draw(canvas2);
    }
}
