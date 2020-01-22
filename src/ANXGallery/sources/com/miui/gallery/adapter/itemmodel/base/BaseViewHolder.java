package com.miui.gallery.adapter.itemmodel.base;

import android.view.View;
import com.miui.epoxy.EpoxyViewHolder;

public abstract class BaseViewHolder extends EpoxyViewHolder {
    public BaseViewHolder(View view) {
        super(view);
    }

    public <V extends View> V findViewById(int i) {
        return this.itemView.findViewById(i);
    }
}
