package com.miui.gallery.editor.photo.app.crop;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.CropData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class CropAdapter extends SimpleRecyclerView.Adapter<ItemHolder> implements Selectable {
    private List<CropData> mDataList;
    private Selectable.Delegator mDelegator;

    public CropAdapter(Context context, List<CropData> list, Selectable.Selector selector) {
        this.mDataList = list;
        this.mDelegator = new Selectable.Delegator(2, selector);
    }

    public int getItemCount() {
        return this.mDataList.size();
    }

    public CropData getSelectedItem() {
        return this.mDataList.get(this.mDelegator.getSelection());
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        super.onBindViewHolder(itemHolder, i);
        itemHolder.bind(this.mDataList.get(i));
        this.mDelegator.onBindViewHolder(itemHolder, i);
    }

    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemHolder(getInflater().inflate(R.layout.crop_menu_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
