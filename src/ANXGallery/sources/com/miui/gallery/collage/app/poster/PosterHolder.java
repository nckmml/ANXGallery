package com.miui.gallery.collage.app.poster;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.collage.core.poster.PosterModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.Locale;

class PosterHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView = ((ImageView) this.itemView.findViewById(R.id.collage_item_icon));

    PosterHolder(ViewGroup viewGroup, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.poster_icon_item, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setPosterModel(Resources resources, PosterModel posterModel, int i, int i2) {
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.ASSETS.wrap(String.format(Locale.US, "%s%s%s%d%s", new Object[]{posterModel.relativePath, File.separator, "preview_", Integer.valueOf(i), ".png"})), this.mImageView);
        ImageView imageView = this.mImageView;
        imageView.setContentDescription(imageView.getResources().getString(R.string.photo_editor_talkback_effect, new Object[]{Integer.valueOf(i2 + 1)}));
    }
}
