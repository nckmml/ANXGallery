package com.miui.gallery.editor.photo.app.frame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;
import com.miui.gallery.R;

class FrameItemPreviewDrawable extends Drawable {
    private Paint mPaint = new Paint();
    private float mRatio = 1.0f;
    private Rect mRect = new Rect();
    private RectF mRectF = new RectF();
    private int mSelectColor;
    private int mSolidNormalColor;
    private Drawable mStrokeDrawable;

    FrameItemPreviewDrawable(Resources resources) {
        this.mSolidNormalColor = ResourcesCompat.getColor(resources, R.color.frame_item_color_solid_normal, (Resources.Theme) null);
        this.mSelectColor = ResourcesCompat.getColor(resources, R.color.frame_item_color_select, (Resources.Theme) null);
        this.mStrokeDrawable = ResourcesCompat.getDrawable(resources, R.drawable.frame_menu_item_drawable, (Resources.Theme) null);
    }

    public void draw(Canvas canvas) {
        float f;
        Rect bounds = getBounds();
        float min = ((float) Math.min(bounds.width(), bounds.height())) - 18.0f;
        float f2 = this.mRatio;
        if (f2 >= 1.0f) {
            f = min / f2;
        } else {
            float f3 = f2 * min;
            f = min;
            min = f3;
        }
        float f4 = min / 2.0f;
        float f5 = f / 2.0f;
        this.mRectF.set(((float) bounds.centerX()) - f4, ((float) bounds.centerY()) - f5, ((float) bounds.centerX()) + f4, ((float) bounds.centerX()) + f5);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mSolidNormalColor);
        this.mRectF.round(this.mRect);
        canvas.drawRect(this.mRect, this.mPaint);
        this.mRect.inset(-8, -8);
        this.mStrokeDrawable.setBounds(this.mRect);
        this.mStrokeDrawable.draw(canvas);
    }

    public int getOpacity() {
        return -2;
    }

    public boolean isStateful() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (iArr[i] == 16842913) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            setColorFilter(this.mSelectColor, PorterDuff.Mode.SRC_ATOP);
        } else {
            setColorFilter((ColorFilter) null);
        }
        return super.onStateChange(iArr);
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColorFilter(colorFilter);
            this.mStrokeDrawable.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }

    public void setRatio(float f) {
        this.mRatio = f;
        invalidateSelf();
    }
}
