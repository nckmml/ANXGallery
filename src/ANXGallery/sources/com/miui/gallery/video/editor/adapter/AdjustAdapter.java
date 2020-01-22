package com.miui.gallery.video.editor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.video.editor.model.AdjustData;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class AdjustAdapter extends SimpleRecyclerView.Adapter<AdjustAdapterItemHolder> implements Selectable {
    private List<AdjustData> mDataList;
    private Selectable.Delegator mDelegator;
    private int mItemWidth;

    static class AdjustAdapterItemHolder extends RecyclerView.ViewHolder {
        private ImageView mIconView;
        private TextView mLabelView;

        public AdjustAdapterItemHolder(View view) {
            super(view);
            this.mLabelView = (TextView) view.findViewById(R.id.labelView);
            this.mIconView = (ImageView) view.findViewById(R.id.iconView);
        }

        /* access modifiers changed from: package-private */
        public void bind(AdjustData adjustData) {
            this.mIconView.setImageResource(adjustData.icon);
            this.mLabelView.setText(adjustData.name);
        }
    }

    public AdjustAdapter(Context context, List<AdjustData> list, Selectable.Selector selector) {
        this.mDataList = list;
        this.mDelegator = new Selectable.Delegator(-1, selector);
        this.mItemWidth = (int) ((((float) ScreenUtils.getScreenWidth()) - (context.getResources().getDimension(R.dimen.photo_editor_menu_bound_padding) * 2.0f)) / ((float) getItemCount()));
    }

    public int getItemCount() {
        return this.mDataList.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(AdjustAdapterItemHolder adjustAdapterItemHolder, int i) {
        super.onBindViewHolder(adjustAdapterItemHolder, i);
        adjustAdapterItemHolder.bind(this.mDataList.get(i));
        this.mDelegator.onBindViewHolder(adjustAdapterItemHolder, i);
    }

    public AdjustAdapterItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = getInflater().inflate(R.layout.video_editor_adjust_menu_item, viewGroup, false);
        inflate.getLayoutParams().width = this.mItemWidth;
        return new AdjustAdapterItemHolder(inflate);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
