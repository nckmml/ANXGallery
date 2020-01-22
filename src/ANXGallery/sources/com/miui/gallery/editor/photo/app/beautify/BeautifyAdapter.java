package com.miui.gallery.editor.photo.app.beautify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.BeautifyData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class BeautifyAdapter extends SimpleRecyclerView.Adapter<BeautifyAdapterItemHolder> implements Selectable {
    private List<BeautifyData> mDataList;
    private Selectable.Delegator mDelegator;
    private int mItemWidth;

    static class BeautifyAdapterItemHolder extends RecyclerView.ViewHolder {
        private ImageView mIconView;
        private TextView mLabelView;

        public BeautifyAdapterItemHolder(View view) {
            super(view);
            this.mLabelView = (TextView) view.findViewById(R.id.labelView);
            this.mIconView = (ImageView) view.findViewById(R.id.iconView);
        }

        /* access modifiers changed from: package-private */
        public void bind(BeautifyData beautifyData) {
            this.mIconView.setImageResource(beautifyData.icon);
            this.mLabelView.setText(beautifyData.name);
        }
    }

    public BeautifyAdapter(Context context, List<BeautifyData> list, Selectable.Selector selector) {
        this.mDataList = list;
        this.mDelegator = new Selectable.Delegator(-1, selector);
        if (getItemCount() != 0) {
            this.mItemWidth = (int) ((((float) ScreenUtils.getScreenWidth()) - (context.getResources().getDimension(R.dimen.editor_menu_beautify_item_start) * 2.0f)) / ((float) getItemCount()));
        }
    }

    public int getItemCount() {
        List<BeautifyData> list = this.mDataList;
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

    public void onBindViewHolder(BeautifyAdapterItemHolder beautifyAdapterItemHolder, int i) {
        super.onBindViewHolder(beautifyAdapterItemHolder, i);
        beautifyAdapterItemHolder.bind(this.mDataList.get(i));
        this.mDelegator.onBindViewHolder(beautifyAdapterItemHolder, i);
    }

    public BeautifyAdapterItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = getInflater().inflate(R.layout.beautify_menu_item, viewGroup, false);
        inflate.getLayoutParams().width = this.mItemWidth;
        return new BeautifyAdapterItemHolder(inflate);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
