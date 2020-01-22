package com.miui.gallery.search.core.display;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.icon.IconImageLoader;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public abstract class AbstractSuggestionViewFactory implements SuggestionViewFactory {
    private final Context mContext;
    protected DisplayImageOptions mDefaultDisplayImageOptions;
    protected DisplayImageOptions.Builder mDisplayImageOptionBuilder;
    private final LayoutInflater mLayoutInflater;

    public AbstractSuggestionViewFactory(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        initDisplayImageOptions(context);
    }

    /* access modifiers changed from: protected */
    public void bindIcon(ImageView imageView, String str, DisplayImageOptions displayImageOptions) {
        IconImageLoader.getInstance().displayImage(this.mContext, str == null ? null : Uri.parse(str), DownloadType.MICRO, imageView, displayImageOptions, Config.ThumbConfig.get().sMicroTargetSize);
    }

    public void bindViewHolder(QueryInfo queryInfo, Suggestion suggestion, int i, BaseSuggestionViewHolder baseSuggestionViewHolder, OnActionClickListener onActionClickListener) {
        if (baseSuggestionViewHolder.getIconView() != null) {
            bindIcon(baseSuggestionViewHolder.getIconView(), suggestion.getSuggestionIcon(), getDisplayImageOptionsForViewType(baseSuggestionViewHolder.getViewType()));
        }
        if (baseSuggestionViewHolder.getTitle() != null) {
            baseSuggestionViewHolder.getTitle().setText(suggestion.getSuggestionTitle());
        }
        if (baseSuggestionViewHolder.getSubTitle() != null) {
            baseSuggestionViewHolder.getSubTitle().setText(suggestion.getSuggestionSubTitle());
        }
    }

    /* access modifiers changed from: protected */
    public BaseSuggestionViewHolder createViewHolder(View view) {
        return new BaseSuggestionViewHolder(view);
    }

    public BaseSuggestionViewHolder createViewHolder(ViewGroup viewGroup, String str) {
        return createViewHolder(viewGroup != null ? LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutIdForViewType(str), viewGroup, false) : getBaseLayoutInflater().inflate(getLayoutIdForViewType(str), viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public LayoutInflater getBaseLayoutInflater() {
        return this.mLayoutInflater;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public DisplayImageOptions getDisplayImageOptionsForViewType(String str) {
        return this.mDefaultDisplayImageOptions;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutIdForViewType(String str);

    /* access modifiers changed from: protected */
    public void initDisplayImageOptions(Context context) {
        this.mDisplayImageOptionBuilder = new DisplayImageOptions.Builder().cacheThumbnail(true).loadFromMicroCache(true).considerExifParams(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading((int) R.drawable.image_default_cover).showImageOnFail(R.drawable.image_default_cover).showImageForEmptyUri(R.drawable.image_default_cover);
        this.mDefaultDisplayImageOptions = this.mDisplayImageOptionBuilder.build();
    }
}
