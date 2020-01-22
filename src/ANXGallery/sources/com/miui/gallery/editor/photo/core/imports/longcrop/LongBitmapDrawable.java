package com.miui.gallery.editor.photo.core.imports.longcrop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

public class LongBitmapDrawable extends Drawable {
    private Bitmap[] mBitmaps = new Bitmap[0];
    private Paint mPaint = new Paint(3);

    public LongBitmapDrawable(Bitmap bitmap) {
        if (bitmap != null) {
            ArrayList arrayList = new ArrayList();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Paint paint = new Paint(4);
            while (height > 0) {
                int min = Math.min(height, 1024);
                Bitmap createBitmap = Bitmap.createBitmap(width, min, bitmap.getConfig());
                new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, (float) (-(bitmap.getHeight() - height)), paint);
                arrayList.add(createBitmap);
                height -= min;
            }
            this.mBitmaps = (Bitmap[]) arrayList.toArray(new Bitmap[arrayList.size()]);
        }
    }

    public void draw(Canvas canvas) {
        canvas.save();
        Rect bounds = getBounds();
        if (bounds != null) {
            canvas.translate((float) (-bounds.left), (float) (-bounds.top));
        }
        int i = 0;
        while (true) {
            Bitmap[] bitmapArr = this.mBitmaps;
            if (i < bitmapArr.length) {
                Bitmap bitmap = bitmapArr[i];
                if (!canvas.quickReject(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight(), Canvas.EdgeType.BW)) {
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
                }
                canvas.translate(0.0f, (float) bitmap.getHeight());
                i++;
            } else {
                canvas.restore();
                return;
            }
        }
    }

    public int getIntrinsicHeight() {
        Bitmap[] bitmapArr = this.mBitmaps;
        int i = 0;
        if (bitmapArr == null || bitmapArr.length == 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            Bitmap[] bitmapArr2 = this.mBitmaps;
            if (i >= bitmapArr2.length) {
                return i2;
            }
            i2 += bitmapArr2[i].getHeight();
            i++;
        }
    }

    public int getIntrinsicWidth() {
        Bitmap[] bitmapArr = this.mBitmaps;
        if (bitmapArr == null || bitmapArr.length == 0) {
            return 0;
        }
        return bitmapArr[0].getWidth();
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
