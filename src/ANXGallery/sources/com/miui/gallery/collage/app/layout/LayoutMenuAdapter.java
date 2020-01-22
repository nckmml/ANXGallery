package com.miui.gallery.collage.app.layout;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.collage.core.layout.LayoutModel;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class LayoutMenuAdapter extends SimpleRecyclerView.Adapter<LayoutHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator;
    private List<LayoutModel> mLayoutList;

    LayoutMenuAdapter(Context context, List<LayoutModel> list, Selectable.Selector selector) {
        this.mContext = context;
        this.mDelegator = new Selectable.Delegator(0, selector);
        this.mLayoutList = list;
    }

    public int getItemCount() {
        return this.mLayoutList.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(LayoutHolder layoutHolder, int i) {
        super.onBindViewHolder(layoutHolder, i);
        layoutHolder.setLayoutModel(this.mLayoutList.get(i), i);
        this.mDelegator.onBindViewHolder(layoutHolder, i);
    }

    public LayoutHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LayoutHolder(viewGroup, this.mContext);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
