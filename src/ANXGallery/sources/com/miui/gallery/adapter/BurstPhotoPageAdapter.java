package com.miui.gallery.adapter;

import com.miui.gallery.R;
import com.miui.gallery.adapter.PhotoPageAdapter;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.ui.PhotoPageFragmentBase;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.widget.ViewPager;

public class BurstPhotoPageAdapter extends PhotoPageAdapter {
    private int mMaxPagerItemWidth;
    private ViewPager mPagerView;

    public BurstPhotoPageAdapter(int i, ImageLoadParams imageLoadParams, ItemViewInfo itemViewInfo, PhotoPageAdapter.OnPreviewedListener onPreviewedListener, PhotoPageFragmentBase.PhotoPageInteractionListener photoPageInteractionListener) {
        super(i, imageLoadParams, itemViewInfo, onPreviewedListener, photoPageInteractionListener);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId(int i) {
        return R.layout.photo_page_burst_item;
    }

    public float getPageWidth(int i) {
        float f;
        float f2;
        BaseDataItem dataItem = getDataItem(i);
        if (!(this.mPagerView == null || dataItem == null)) {
            int height = dataItem.getHeight();
            int width = dataItem.getWidth();
            int height2 = this.mPagerView.getHeight();
            int width2 = this.mPagerView.getWidth();
            if (height2 > 0 && width2 > 0 && height > 0 && width > 0) {
                if (ExifUtil.isWidthHeightRotated(dataItem.getOrientation())) {
                    f = ((float) height2) * 1.0f * ((float) height);
                    f2 = (float) width;
                } else {
                    f = ((float) height2) * 1.0f * ((float) width);
                    f2 = (float) height;
                }
                float f3 = f / f2;
                int i2 = this.mMaxPagerItemWidth;
                if (f3 > ((float) i2)) {
                    f3 = (float) i2;
                }
                return f3 / ((float) width2);
            }
        }
        return super.getPageWidth(i);
    }

    /* access modifiers changed from: protected */
    public int getViewType(int i) {
        return 4;
    }

    /* access modifiers changed from: protected */
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    /* access modifiers changed from: protected */
    public boolean isTypeMatch(Object obj, int i) {
        return isItemValidate(obj) && ((Integer) ((PhotoPageItem) obj).getTag(R.id.tag_view_type)).intValue() == 4;
    }

    public void setMaxPagerItemWidth(int i) {
        this.mMaxPagerItemWidth = i;
    }

    public void setPagerView(ViewPager viewPager) {
        this.mPagerView = viewPager;
    }
}
