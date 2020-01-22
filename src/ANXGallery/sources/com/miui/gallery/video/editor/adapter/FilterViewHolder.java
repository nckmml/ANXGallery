package com.miui.gallery.video.editor.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;

public class FilterViewHolder extends SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.SingleChoiceViewHolder {
    private ImageView mIcon;
    private TextView mNameTextView;
    private View mSelector;

    public FilterViewHolder(View view) {
        super(view);
        this.mIcon = (ImageView) view.findViewById(R.id.icon);
        this.mNameTextView = (TextView) view.findViewById(R.id.name);
        this.mSelector = view.findViewById(R.id.selector);
    }

    public void setIcon(int i) {
        if (i != 0) {
            this.mIcon.setImageResource(i);
        }
    }

    public void setName(int i) {
        if (i != 0) {
            this.mNameTextView.setText(i);
        }
    }

    public void setSelect(boolean z) {
        this.itemView.setSelected(z);
        View view = this.mSelector;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            if (z) {
                this.mNameTextView.setBackground((Drawable) null);
            } else {
                this.mNameTextView.setBackgroundResource(R.drawable.video_editor_filter_text_background_shape);
            }
        }
    }
}
