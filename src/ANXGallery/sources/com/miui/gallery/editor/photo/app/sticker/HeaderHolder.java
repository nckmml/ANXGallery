package com.miui.gallery.editor.photo.app.sticker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.editor.photo.core.common.model.StickerCategory;

public class HeaderHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;

    public HeaderHolder(View view) {
        super(view);
        if (view instanceof TextView) {
            this.mTitle = (TextView) view;
        }
    }

    /* access modifiers changed from: package-private */
    public void bind(StickerCategory stickerCategory) {
        TextView textView = this.mTitle;
        if (textView != null) {
            Context context = textView.getContext();
            int identifier = context.getResources().getIdentifier(stickerCategory.name, "string", "com.miui.gallery");
            if (identifier != 0) {
                this.mTitle.setText(context.getString(identifier));
            }
        }
    }
}
