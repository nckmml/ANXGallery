package com.miui.gallery.card.core;

import android.content.res.Resources;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;
import java.util.List;

public class LayoutParamsHelper {
    private int mItemMaxHeight;
    private int mItemMinHeight;
    private List<Size> mLayoutSizes = new ArrayList();

    public static class Size {
        public int mHeight;
        public int mWidth;

        public Size() {
            this(0, 0);
        }

        public Size(int i, int i2) {
            this.mWidth = i;
            this.mHeight = i2;
        }

        public String toString() {
            return "Size:" + this.mWidth + "X" + this.mHeight;
        }
    }

    public LayoutParamsHelper() {
        Resources resources = GalleryApp.sGetAndroidContext().getResources();
        this.mItemMinHeight = resources.getDimensionPixelOffset(R.dimen.story_item_min_height);
        this.mItemMaxHeight = resources.getDimensionPixelOffset(R.dimen.story_item_max_height);
    }

    private Size getImageSizeGuaranteed(List<Size> list, int i, int i2, int i3) {
        Size size = (!MiscUtil.isValid(list) || i < 0 || i >= list.size()) ? null : list.get(i);
        if (size == null) {
            size = new Size(0, 0);
        }
        if (size.mWidth <= 0 || size.mHeight <= 0) {
            size.mWidth = (i2 - i3) / 2;
            size.mHeight = size.mWidth;
        }
        return size;
    }

    private float getRatio(Size size) {
        float f = 1.0f;
        try {
            float f2 = ((float) size.mWidth) / ((float) size.mHeight);
            try {
                if (Float.compare(f2, 0.0f) == 0) {
                    return 1.0f;
                }
                return f2;
            } catch (Exception e) {
                Exception exc = e;
                f = f2;
                e = exc;
                Log.e("LayoutParamsHelper", "getRatio error:" + e);
                return f;
            }
        } catch (Exception e2) {
            e = e2;
            Log.e("LayoutParamsHelper", "getRatio error:" + e);
            return f;
        }
    }

    public ImageSize getLayoutSize(int i) {
        if (i < 0 || i >= this.mLayoutSizes.size()) {
            return null;
        }
        return new ImageSize(this.mLayoutSizes.get(i).mWidth, this.mLayoutSizes.get(i).mHeight);
    }

    public void updateLayoutSizes(List<Size> list, int i, int i2) {
        List<Size> list2 = list;
        int i3 = i;
        int i4 = i2;
        this.mLayoutSizes.clear();
        if (MiscUtil.isValid(list)) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < list.size()) {
                Log.d("LayoutParamsHelper", "Row index:" + i6);
                i6++;
                Size size = new Size();
                Size size2 = new Size();
                Size size3 = new Size();
                Size imageSizeGuaranteed = getImageSizeGuaranteed(list2, i5, i3, i4);
                size.mWidth = i3;
                size.mHeight = (int) (((float) size.mWidth) / getRatio(imageSizeGuaranteed));
                int i7 = this.mItemMinHeight;
                if (((float) size.mHeight) < ((float) i7) * 1.0f) {
                    size.mHeight = (int) (((float) i7) * 1.0f);
                    this.mLayoutSizes.add(size);
                    Log.d("LayoutParamsHelper", (Object) size);
                } else {
                    int i8 = i5 + 1;
                    if (i8 == list.size()) {
                        int i9 = size.mHeight;
                        int i10 = this.mItemMaxHeight;
                        if (i9 > i10) {
                            size.mHeight = i10;
                        }
                        this.mLayoutSizes.add(size);
                        Log.d("LayoutParamsHelper", (Object) size);
                        return;
                    }
                    Size imageSizeGuaranteed2 = getImageSizeGuaranteed(list2, i8, i3, i4);
                    float ratio = getRatio(imageSizeGuaranteed);
                    float ratio2 = getRatio(imageSizeGuaranteed2);
                    float f = ratio + ratio2;
                    int i11 = (int) (((float) (i3 - i4)) / f);
                    float f2 = (float) i11;
                    if (f2 < ((float) this.mItemMinHeight) * 1.1f) {
                        int i12 = size.mHeight;
                        int i13 = this.mItemMaxHeight;
                        if (i12 <= i13) {
                            i13 = size.mHeight;
                        }
                        size.mHeight = i13;
                        this.mLayoutSizes.add(size);
                        i5 = i8 - 1;
                        Log.d("LayoutParamsHelper", (Object) size);
                    } else {
                        size.mHeight = i11;
                        size.mWidth = (int) (f2 * ratio);
                        size2.mHeight = i11;
                        size2.mWidth = (int) (f2 * ratio2);
                        i5 = i8 + 1;
                        if (i5 == list.size()) {
                            int i14 = size.mHeight;
                            int i15 = this.mItemMaxHeight;
                            if (i14 > i15) {
                                size.mHeight = i15;
                                size2.mHeight = i15;
                            }
                            this.mLayoutSizes.add(size);
                            this.mLayoutSizes.add(size2);
                            Log.d("LayoutParamsHelper", (Object) size);
                            Log.d("LayoutParamsHelper", (Object) size2);
                            return;
                        }
                        float ratio3 = getRatio(getImageSizeGuaranteed(list2, i5, i3, i4));
                        int i16 = (int) (((float) (i3 - (i4 * 2))) / (f + ratio3));
                        float f3 = (float) i16;
                        if (f3 < ((float) this.mItemMinHeight) * 1.2f) {
                            int i17 = size.mHeight;
                            int i18 = this.mItemMaxHeight;
                            if (i17 > i18) {
                                size.mHeight = i18;
                                size2.mHeight = i18;
                            }
                            this.mLayoutSizes.add(size);
                            this.mLayoutSizes.add(size2);
                            i5--;
                            Log.d("LayoutParamsHelper", (Object) size);
                            Log.d("LayoutParamsHelper", (Object) size2);
                        } else {
                            int i19 = this.mItemMaxHeight;
                            if (i16 > i19) {
                                size.mHeight = i19;
                                size2.mHeight = i19;
                                size3.mHeight = i19;
                            } else {
                                size.mHeight = i16;
                                size2.mHeight = i16;
                                size3.mHeight = i16;
                            }
                            size.mWidth = (int) (ratio * f3);
                            size2.mWidth = (int) (ratio2 * f3);
                            size3.mWidth = (int) (f3 * ratio3);
                            this.mLayoutSizes.add(size);
                            this.mLayoutSizes.add(size2);
                            this.mLayoutSizes.add(size3);
                            Log.d("LayoutParamsHelper", (Object) size);
                            Log.d("LayoutParamsHelper", (Object) size2);
                            Log.d("LayoutParamsHelper", (Object) size3);
                        }
                    }
                }
                i5++;
            }
        }
    }
}
