package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import com.miui.gallery.R;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.util.ExifUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class PhotoPageBurstItem extends PhotoPageImageBaseItem {
    private ShapeDrawable mDefaultDrawable;

    protected class BurstCheckManager extends PhotoPageItem.CheckManager {
        protected BurstCheckManager() {
            super();
        }

        /* access modifiers changed from: protected */
        public boolean originChecked() {
            return true;
        }
    }

    public PhotoPageBurstItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void configDefaultDrawable(BaseDataItem baseDataItem) {
        int i;
        float f;
        float f2;
        if (this.mDefaultDrawable == null && baseDataItem != null && baseDataItem.getWidth() > 0 && baseDataItem.getHeight() > 0) {
            if (ExifUtil.isWidthHeightRotated(baseDataItem.getOrientation())) {
                f2 = Math.min((float) getResources().getDimensionPixelSize(R.dimen.burst_pager_item_max_width), ((((float) getHeight()) * 1.0f) * ((float) baseDataItem.getHeight())) / ((float) baseDataItem.getWidth()));
                f = ((float) baseDataItem.getWidth()) * f2;
                i = baseDataItem.getHeight();
            } else {
                f2 = Math.min((float) getResources().getDimensionPixelSize(R.dimen.burst_pager_item_max_width), ((((float) getHeight()) * 1.0f) * ((float) baseDataItem.getWidth())) / ((float) baseDataItem.getHeight()));
                f = ((float) baseDataItem.getHeight()) * f2;
                i = baseDataItem.getWidth();
            }
            this.mDefaultDrawable = new ShapeDrawable(new RectShape());
            this.mDefaultDrawable.getPaint().setColor(getResources().getColor(R.color.burst_photo_default_color));
            this.mDefaultDrawable.getPaint().setAntiAlias(true);
            this.mDefaultDrawable.getPaint().setStyle(Paint.Style.FILL);
            this.mDefaultDrawable.setIntrinsicHeight((int) (f / ((float) i)));
            this.mDefaultDrawable.setIntrinsicWidth((int) f2);
        }
    }

    /* access modifiers changed from: protected */
    public PhotoPageItem.CheckManager createCheckManager() {
        return new BurstCheckManager();
    }

    /* access modifiers changed from: protected */
    public DisplayImageOptions getDisplayImageOptions(BaseDataItem baseDataItem, boolean z) {
        DisplayImageOptions displayImageOptions = super.getDisplayImageOptions(baseDataItem, z);
        if (baseDataItem != null) {
            configDefaultDrawable(baseDataItem);
        }
        return this.mDefaultDrawable == null ? displayImageOptions : new DisplayImageOptions.Builder().cloneFrom(displayImageOptions).showImageOnLoading((Drawable) this.mDefaultDrawable).build();
    }

    /* access modifiers changed from: protected */
    public void onImageLoadFinish(ErrorCode errorCode) {
        super.onImageLoadFinish(errorCode);
        this.mPhotoView.setInterceptTouch(true);
    }
}
