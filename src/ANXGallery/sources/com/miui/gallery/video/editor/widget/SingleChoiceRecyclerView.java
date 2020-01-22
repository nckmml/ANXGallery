package com.miui.gallery.video.editor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;

public class SingleChoiceRecyclerView extends RecyclerView {

    public static abstract class SingleChoiceRecyclerViewAdapter<SCVH extends SingleChoiceViewHolder> extends RecyclerView.Adapter<SCVH> implements View.OnClickListener {
        private int lastSelectedItemPosition = 0;
        private ItemSelectChangeListener mItemSelectChangeListener;
        private int selectedItemPosition = 0;

        public interface ItemSelectChangeListener {
            boolean onItemSelect(SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z);
        }

        public static abstract class SingleChoiceViewHolder extends RecyclerView.ViewHolder {
            private int itemPosition;
            protected View mItemView;

            public SingleChoiceViewHolder(View view) {
                super(view);
                this.mItemView = view;
                view.setTag(R.id.video_editor_viewHolder, this);
            }

            public int getItemPosition() {
                return this.itemPosition;
            }

            public void setItemPosition(int i) {
                this.itemPosition = i;
                this.itemView.setTag(R.id.video_editor_itemIndex, Integer.valueOf(i));
            }

            public void setItemViewOnClickListener(View.OnClickListener onClickListener) {
                this.mItemView.setOnClickListener(onClickListener);
            }

            public void setMarginLeft(int i) {
                ViewGroup.LayoutParams layoutParams = this.mItemView.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(i);
                }
            }

            public abstract void setSelect(boolean z);
        }

        public void clearLastSelectedPostion() {
            notifyItemChanged(this.lastSelectedItemPosition, 1);
        }

        public int getSelectedItemPosition() {
            return this.selectedItemPosition;
        }

        public abstract void onBindView(SCVH scvh, int i);

        public void onBindViewHolder(SCVH scvh, int i) {
            scvh.setSelect(i == this.selectedItemPosition);
            scvh.setItemPosition(i);
            onBindView(scvh, i);
        }

        public void onClick(View view) {
            int itemPosition = ((SingleChoiceViewHolder) view.getTag(R.id.video_editor_viewHolder)).getItemPosition();
            boolean z = this.selectedItemPosition != itemPosition;
            if (z) {
                this.lastSelectedItemPosition = this.selectedItemPosition;
            }
            ItemSelectChangeListener itemSelectChangeListener = this.mItemSelectChangeListener;
            if (itemSelectChangeListener != null) {
                itemSelectChangeListener.onItemSelect(this, itemPosition, z);
            }
        }

        public abstract SCVH onCreateSingleChoiceViewHolder(ViewGroup viewGroup, int i);

        public SCVH onCreateViewHolder(ViewGroup viewGroup, int i) {
            SCVH onCreateSingleChoiceViewHolder = onCreateSingleChoiceViewHolder(viewGroup, i);
            onCreateSingleChoiceViewHolder.setItemViewOnClickListener(this);
            return onCreateSingleChoiceViewHolder;
        }

        public void setItemSelectChangeListener(ItemSelectChangeListener itemSelectChangeListener) {
            this.mItemSelectChangeListener = itemSelectChangeListener;
        }

        public void setSelectedItemPosition(int i) {
            this.selectedItemPosition = i;
            notifyItemChanged(i, 1);
        }
    }

    public SingleChoiceRecyclerView(Context context) {
        super(context);
    }

    public SingleChoiceRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SingleChoiceRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
