package com.miui.gallery.editor.photo.app.doodle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;

public class DoodleHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView = ((ImageView) this.itemView.findViewById(R.id.img));

    DoodleHolder(Context context, ViewGroup viewGroup) {
        super(LayoutInflater.from(context).inflate(R.layout.doodle_menu_item, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setIconPath(int i, int i2) {
        ImageView imageView = this.mImageView;
        imageView.setContentDescription(imageView.getResources().getString(R.string.photo_editor_talkback_effect, new Object[]{Integer.valueOf(i2 + 1)}));
        this.mImageView.setImageResource(i);
    }
}
