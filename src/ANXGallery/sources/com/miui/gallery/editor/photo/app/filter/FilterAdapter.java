package com.miui.gallery.editor.photo.app.filter;

import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.FilterData;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class FilterAdapter extends SimpleRecyclerView.Adapter<FilterMenuItem> {
    private boolean mEditMode = false;
    private List<FilterData> mEffects;
    private int mHighlightColor;
    private int mSelectedIndex = 0;
    private int mSubHighlightColor;
    private int mSubItemSize;

    FilterAdapter(List<FilterData> list, int i, int i2, int i3) {
        this.mEffects = list;
        this.mHighlightColor = i;
        this.mSubHighlightColor = i2;
        this.mSubItemSize = i3;
    }

    /* access modifiers changed from: package-private */
    public void clearSelected() {
        int i = this.mSelectedIndex;
        if (i != -1) {
            this.mSelectedIndex = -1;
            notifyItemChanged(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void enterEditMode() {
        this.mEditMode = true;
        notifyItemChanged(this.mSelectedIndex);
    }

    /* access modifiers changed from: package-private */
    public void exitEditMode() {
        this.mEditMode = false;
        notifyItemChanged(this.mSelectedIndex);
    }

    public int getItemCount() {
        return this.mEffects.size();
    }

    public FilterData getItemData(int i) {
        List<FilterData> list = this.mEffects;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.mEffects.get(i);
    }

    /* access modifiers changed from: package-private */
    public int getValue() {
        return this.mEffects.get(this.mSelectedIndex).progress;
    }

    /* access modifiers changed from: package-private */
    public boolean isInEditMode() {
        return this.mEditMode;
    }

    public void onBindViewHolder(FilterMenuItem filterMenuItem, int i) {
        super.onBindViewHolder(filterMenuItem, i);
        boolean z = i == this.mSelectedIndex;
        filterMenuItem.bindData(this.mEffects.get(i));
        filterMenuItem.setState(z, this.mEditMode);
        if (this.mSubItemSize <= 0 || i < getItemCount() - this.mSubItemSize) {
            filterMenuItem.setForegroundColor(this.mHighlightColor);
        } else {
            filterMenuItem.setForegroundColor(this.mSubHighlightColor);
        }
        if (z && this.mEditMode && !this.mEffects.get(i).isNone()) {
            filterMenuItem.updateIndicator(getValue());
        }
    }

    public FilterMenuItem onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FilterMenuItem(getInflater().inflate(R.layout.filter_menu_item, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setSelectedIndex(int i) {
        int i2 = this.mSelectedIndex;
        if (i != i2) {
            this.mSelectedIndex = i;
            if (i2 != -1) {
                notifyItemChanged(i2);
            }
            int i3 = this.mSelectedIndex;
            if (i3 != -1) {
                notifyItemChanged(i3);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void update(int i) {
        int i2;
        if (this.mEditMode && (i2 = this.mSelectedIndex) != -1) {
            this.mEffects.get(i2).progress = i;
            notifyItemChanged(this.mSelectedIndex);
        }
    }
}
