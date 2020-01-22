package com.miui.gallery.editor.photo.app.sticker;

import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.StickerData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

class StickerHolder extends RecyclerView.ViewHolder {
    private DisplayImageOptions mOptions;
    private ImageSize mSize;
    private ImageView mView;

    StickerHolder(View view, DisplayImageOptions displayImageOptions, ImageSize imageSize) {
        super(view);
        this.mView = (ImageView) view.findViewById(R.id.img);
        this.mOptions = displayImageOptions;
        this.mSize = imageSize;
    }

    /* access modifiers changed from: package-private */
    public void bind(StickerData stickerData, int i) {
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage("file://" + stickerData.icon, this.mView, this.mOptions, this.mSize, (RectF) null);
        ImageView imageView = this.mView;
        imageView.setContentDescription(imageView.getResources().getString(R.string.photo_editor_talkback_effect, new Object[]{Integer.valueOf(i + 1)}));
    }
}
