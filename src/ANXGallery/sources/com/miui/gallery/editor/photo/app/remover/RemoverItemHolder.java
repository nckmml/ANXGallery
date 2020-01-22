package com.miui.gallery.editor.photo.app.remover;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.RemoverData;

class RemoverItemHolder extends RecyclerView.ViewHolder {
    private ImageView mIconView;
    private TextView mLabelView;

    public RemoverItemHolder(View view) {
        super(view);
        this.mLabelView = (TextView) view.findViewById(R.id.labelView);
        this.mIconView = (ImageView) view.findViewById(R.id.iconView);
    }

    /* access modifiers changed from: package-private */
    public void bind(RemoverData removerData) {
        this.mIconView.setImageResource(removerData.mIcon);
        this.mLabelView.setText(removerData.name);
    }
}
