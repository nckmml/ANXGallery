package com.miui.gallery.editor.photo.screen.text;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.TextData;
import com.nostra13.universalimageloader.core.ImageLoader;

class ScreenTextBubbleHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;

    ScreenTextBubbleHolder(View view) {
        super(view);
        this.mImageView = (ImageView) view.findViewById(R.id.img);
    }

    public void bind(TextData textData, int i) {
        ImageLoader.getInstance().displayImage(textData.iconPath, this.mImageView);
        ImageView imageView = this.mImageView;
        imageView.setContentDescription(imageView.getResources().getString(R.string.photo_editor_talkback_effect, new Object[]{Integer.valueOf(i + 1)}));
    }
}
