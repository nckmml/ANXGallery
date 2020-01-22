package com.miui.gallery.editor.photo.app.mosaic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.nostra13.universalimageloader.core.ImageLoader;

class MosaicHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView = ((ImageView) this.itemView.findViewById(R.id.img));

    MosaicHolder(Context context, ViewGroup viewGroup) {
        super(LayoutInflater.from(context).inflate(R.layout.mosaic_menu_item, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setIconPath(String str, int i) {
        ImageLoader.getInstance().displayImage(str, this.mImageView);
        ImageView imageView = this.mImageView;
        imageView.setContentDescription(imageView.getResources().getString(R.string.photo_editor_talkback_effect, new Object[]{Integer.valueOf(i + 1)}));
    }
}
