package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;

public class SimpleRecyclerView extends RecyclerView {

    public static abstract class Adapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {
        private LayoutInflater mInflater;
        private OnItemClickListener mOnItemClickListener;

        /* access modifiers changed from: protected */
        public final LayoutInflater getInflater() {
            return this.mInflater;
        }

        public int getItemCount() {
            return 0;
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            this.mInflater = LayoutInflater.from(recyclerView.getContext());
        }

        public void onBindViewHolder(VH vh, int i) {
            vh.itemView.setOnClickListener(this);
            vh.itemView.setTag(Integer.valueOf(i));
        }

        public void onClick(View view) {
            OnItemClickListener onItemClickListener = this.mOnItemClickListener;
            if (onItemClickListener != null) {
                onItemClickListener.OnItemClick((RecyclerView) view.getParent(), view, ((Integer) view.getTag()).intValue());
            }
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            this.mInflater = null;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }
    }

    public static class BlankDivider extends RecyclerView.ItemDecoration {
        private int mBottom;
        private int mEnd;
        private int mHorizonalInterval;
        private int mStart;
        private int mTop;
        private int mVerticalInterval;

        public BlankDivider(int i, int i2) {
            this(i, i, i, i2, i2, i2);
        }

        public BlankDivider(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mStart = i;
            this.mEnd = i2;
            this.mHorizonalInterval = i3;
            this.mTop = i4;
            this.mBottom = i5;
            this.mVerticalInterval = i6;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public BlankDivider(Resources resources, int i, int i2) {
            this(i == 0 ? 0 : resources.getDimensionPixelSize(i), i2 != 0 ? resources.getDimensionPixelSize(i2) : 0);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int i;
            int i2;
            int i3;
            super.getItemOffsets(rect, view, recyclerView, state);
            if (recyclerView.getChildAdapterPosition(view) == -1) {
                Log.w("SimpleRecyclerView", "no adapter bound");
                return;
            }
            int i4 = 0;
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                i4 = this.mStart;
                i2 = this.mHorizonalInterval;
                i = this.mTop;
                i3 = this.mVerticalInterval;
            } else {
                if (recyclerView.getChildAdapterPosition(view) == recyclerView.getAdapter().getItemCount() - 1) {
                    i2 = this.mEnd;
                    i3 = this.mBottom;
                } else {
                    i2 = this.mHorizonalInterval;
                    i3 = this.mVerticalInterval;
                }
                i = 0;
            }
            if (recyclerView.getLayoutDirection() == 1) {
                rect.set(i2, i, i4, i3);
            } else {
                rect.set(i4, i, i2, i3);
            }
        }
    }

    public interface OnItemClickListener {
        boolean OnItemClick(RecyclerView recyclerView, View view, int i);
    }

    public SimpleRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SimpleRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SimpleRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutManager(new LinearLayoutManager(context, 0, false));
        setItemAnimator((RecyclerView.ItemAnimator) null);
    }

    public boolean fling(int i, int i2) {
        return super.fling((int) (((float) i) * 0.6f), i2);
    }
}
