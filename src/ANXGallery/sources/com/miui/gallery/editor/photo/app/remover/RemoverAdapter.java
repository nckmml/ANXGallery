package com.miui.gallery.editor.photo.app.remover;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.RemoverData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class RemoverAdapter extends SimpleRecyclerView.Adapter<RemoverItemHolder> implements Selectable {
    private List<RemoverData> mDataList;
    private Selectable.Delegator mDelegator;
    private int mItemWidth;

    public RemoverAdapter(Context context, List<RemoverData> list, Selectable.Selector selector) {
        this.mDataList = list;
        this.mDelegator = new Selectable.Delegator(-1, selector);
        if (getItemCount() != 0) {
            this.mItemWidth = (int) ((((float) ScreenUtils.getScreenWidth()) - (context.getResources().getDimension(R.dimen.editor_menu_remove_item_start) * 2.0f)) / ((float) getItemCount()));
        }
    }

    public int getItemCount() {
        List<RemoverData> list = this.mDataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(RemoverItemHolder removerItemHolder, int i) {
        super.onBindViewHolder(removerItemHolder, i);
        removerItemHolder.bind(this.mDataList.get(i));
        this.mDelegator.onBindViewHolder(removerItemHolder, i);
    }

    public RemoverItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = getInflater().inflate(R.layout.remover_menu_item, viewGroup, false);
        inflate.getLayoutParams().width = this.mItemWidth;
        return new RemoverItemHolder(inflate);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
