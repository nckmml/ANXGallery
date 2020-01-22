package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.reflect.Field;

public class ImageViewAware extends ViewAware {
    public ImageViewAware(ImageView imageView) {
        this(imageView, true);
    }

    public ImageViewAware(ImageView imageView, boolean z) {
        super(imageView, z);
        trySaveBitmapForReused(imageView);
    }

    private static int getImageViewFieldValue(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue <= 0 || intValue >= Integer.MAX_VALUE) {
                return 0;
            }
            return intValue;
        } catch (Exception e) {
            L.e(e);
            return 0;
        }
    }

    private void trySaveBitmapForReused(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && (drawable instanceof BitmapDrawable)) {
            saveBitmap(((BitmapDrawable) drawable).getBitmap());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = (android.widget.ImageView) r2.viewRef.get();
     */
    public int getHeight() {
        ImageView imageView;
        int height = super.getHeight();
        return (height > 0 || imageView == null) ? height : getImageViewFieldValue(imageView, "mMaxHeight");
    }

    public ViewScaleType getScaleType() {
        ImageView imageView = (ImageView) this.viewRef.get();
        return imageView != null ? ViewScaleType.fromImageView(imageView) : super.getScaleType();
    }

    public Object getTag(int i) {
        ImageView wrappedView = getWrappedView();
        if (wrappedView != null) {
            return wrappedView.getTag(i);
        }
        L.e("wrapped view is null", new Object[0]);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = (android.widget.ImageView) r2.viewRef.get();
     */
    public int getWidth() {
        ImageView imageView;
        int width = super.getWidth();
        return (width > 0 || imageView == null) ? width : getImageViewFieldValue(imageView, "mMaxWidth");
    }

    public ImageView getWrappedView() {
        return (ImageView) super.getWrappedView();
    }

    /* access modifiers changed from: protected */
    public void setImageBitmapInto(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    /* access modifiers changed from: protected */
    public void setImageDrawableInto(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    public void setTag(int i, Object obj) {
        ImageView wrappedView = getWrappedView();
        if (wrappedView != null) {
            wrappedView.setTag(i, obj);
        } else {
            L.e("wrapped view is null", new Object[0]);
        }
    }
}
