package com.miui.gallery.editor.photo.app.crop;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.CropData;

class ItemHolder extends RecyclerView.ViewHolder {
    private ImageView mView;

    ItemHolder(View view) {
        super(view);
        this.mView = (ImageView) view.findViewById(R.id.icon);
    }

    /* access modifiers changed from: package-private */
    public void bind(CropData cropData) {
        this.mView.setImageResource(cropData.icon);
        ImageView imageView = this.mView;
        imageView.setContentDescription(imageView.getResources().getString(cropData.talkbackName));
    }
}
