package com.miui.gallery.adapter.itemmodel.base;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.epoxy.EpoxyModel;
import com.miui.epoxy.EpoxyViewHolder;
import com.miui.gallery.base_optimization.support.IViewSupport;
import com.miui.gallery.base_optimization.support.ViewSupportDelegate;
import com.miui.gallery.util.imageloader.IImageLoader;
import com.miui.gallery.util.imageloader.ImageLoaderSupportDelegate;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public abstract class BaseItemModel<DATA, VH extends EpoxyViewHolder> extends EpoxyModel<VH> implements IViewSupport {
    protected IImageLoader mImageLoaderSupport;
    protected DATA mItemData;
    protected IViewSupport mViewSupport;

    public BaseItemModel() {
    }

    public BaseItemModel(long j, DATA data) {
        super(j);
        this.mItemData = data;
        this.mViewSupport = ViewSupportDelegate.getInstance();
        this.mImageLoaderSupport = ImageLoaderSupportDelegate.getInstance();
    }

    /* access modifiers changed from: protected */
    public void bindImage(ImageView imageView, String str, Uri uri, DisplayImageOptions displayImageOptions) {
        this.mImageLoaderSupport.bindImage(imageView, str, uri, displayImageOptions);
    }

    public DATA getItemData() {
        return this.mItemData;
    }

    public void setText(TextView textView, String str) {
        this.mViewSupport.setText(textView, str);
    }
}
