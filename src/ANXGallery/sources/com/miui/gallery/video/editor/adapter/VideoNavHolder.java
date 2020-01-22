package com.miui.gallery.video.editor.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.model.MenuFragmentData;

public class VideoNavHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mView;

    public VideoNavHolder(View view) {
        super(view);
        this.mView = (TextView) view.findViewById(R.id.label);
        this.mImageView = (ImageView) view.findViewById(R.id.img_nav);
    }

    /* access modifiers changed from: package-private */
    public void bind(MenuFragmentData menuFragmentData) {
        this.mView.setText(menuFragmentData.nameId);
        this.mImageView.setImageResource(menuFragmentData.iconId);
    }
}
