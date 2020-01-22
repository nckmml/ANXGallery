package com.miui.gallery.editor.photo.app.sticker;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.StickerCategory;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class HeaderAdapter extends SimpleRecyclerView.Adapter<HeaderHolder> implements Selectable {
    private List<StickerCategory> mCategories;
    private Selectable.Delegator mDelegator;

    public HeaderAdapter(List<StickerCategory> list, Selectable.Selector selector) {
        this.mCategories = list;
        this.mDelegator = new Selectable.Delegator(0, selector);
    }

    public int getItemCount() {
        return this.mCategories.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(HeaderHolder headerHolder, int i) {
        super.onBindViewHolder(headerHolder, i);
        headerHolder.bind(this.mCategories.get(i));
        this.mDelegator.onBindViewHolder(headerHolder, i);
    }

    public HeaderHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HeaderHolder(getInflater().inflate(R.layout.sticker_category_navigation_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
