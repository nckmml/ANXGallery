package com.miui.gallery.editor.photo.app.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.StickerData;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.List;

public class CategoryDetailAdapter extends SimpleRecyclerView.Adapter<StickerHolder> {
    private List<StickerData> mDataList;
    private DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder().resetViewBeforeLoading(true).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).bitmapConfig(Bitmap.Config.ARGB_8888).cacheThumbnail(false).loadFromThumbnailCache(true).build();
    private ImageSize mSize;

    public CategoryDetailAdapter(Context context, List<StickerData> list) {
        this.mDataList = list;
        this.mSize = new ImageSize(context.getResources().getDimensionPixelSize(R.dimen.editor_menu_sticker_view_pager_item_width), context.getResources().getDimensionPixelSize(R.dimen.editor_menu_sticker_view_pager_item_height));
    }

    public List<StickerData> getDataList() {
        return this.mDataList;
    }

    public int getItemCount() {
        return this.mDataList.size();
    }

    public void onBindViewHolder(StickerHolder stickerHolder, int i) {
        super.onBindViewHolder(stickerHolder, i);
        stickerHolder.bind(this.mDataList.get(i), i);
    }

    public StickerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new StickerHolder(getInflater().inflate(R.layout.sticker_menu_item, viewGroup, false), this.mDisplayImageOptions, this.mSize);
    }

    public void setDataList(List<StickerData> list) {
        this.mDataList = list;
    }
}
