package com.miui.gallery.editor.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.miui.gallery.editor.R;

public class RoundImageView extends ImageView {
    private float bottomCorner;
    private float corner;
    private float height;
    public float mDefaultCorners;
    private float topCorner;
    private float width;

    public RoundImageView(Context context) {
        super(context);
        init(context);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        this.mDefaultCorners = (float) context.getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_item_corner);
        float f = this.mDefaultCorners;
        this.corner = f;
        this.topCorner = f;
        this.bottomCorner = f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = this.width;
        float f2 = this.corner;
        if (f > f2 && this.height > f2) {
            Path path = new Path();
            path.moveTo(this.topCorner, 0.0f);
            path.lineTo(this.width - this.corner, 0.0f);
            float f3 = this.width;
            path.quadTo(f3, 0.0f, f3, this.topCorner);
            path.lineTo(this.width, this.height - this.bottomCorner);
            float f4 = this.width;
            float f5 = this.height;
            path.quadTo(f4, f5, f4 - this.bottomCorner, f5);
            path.lineTo(this.bottomCorner, this.height);
            float f6 = this.height;
            path.quadTo(0.0f, f6, 0.0f, f6 - this.bottomCorner);
            path.lineTo(0.0f, this.topCorner);
            path.quadTo(0.0f, 0.0f, this.topCorner, 0.0f);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.width = (float) getWidth();
        this.height = (float) getHeight();
    }

    public void setBottomCorner(boolean z) {
        this.bottomCorner = z ? this.corner : 0.0f;
        invalidate();
    }

    public void setCorner(float f) {
        this.corner = f;
        this.topCorner = f;
        this.bottomCorner = f;
        invalidate();
    }

    public void setTopCorner(boolean z) {
        this.topCorner = z ? this.corner : 0.0f;
        invalidate();
    }
}
