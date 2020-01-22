package com.miui.gallery.editor.photo.widgets.recyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;

public interface Selectable {

    public static final class Delegator implements Selectable {
        private RecyclerView mParent;
        private int mSelection;
        private Selector mSelector;

        public Delegator() {
            this(-1);
        }

        public Delegator(int i) {
            this(i, (Selector) null);
        }

        public Delegator(int i, Selector selector) {
            this.mSelection = i;
            this.mSelector = selector;
            if (selector != null) {
                selector.mAdapter = this;
            }
        }

        public int getSelection() {
            return this.mSelection;
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            if (this.mParent == null) {
                Selector selector = this.mSelector;
                if (selector != null) {
                    recyclerView.addItemDecoration(selector);
                    if (this.mSelector.mRequireLayer && recyclerView.getLayerType() == 0) {
                        recyclerView.setLayerType(2, (Paint) null);
                    }
                }
                this.mParent = recyclerView;
                return;
            }
            throw new IllegalStateException("already attach to a recycler view");
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder.itemView != null) {
                boolean z = true;
                viewHolder.itemView.setActivated(i == this.mSelection);
                View view = viewHolder.itemView;
                if (i != this.mSelection) {
                    z = false;
                }
                view.setSelected(z);
            }
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            if (this.mParent == recyclerView) {
                Selector selector = this.mSelector;
                if (selector != null) {
                    recyclerView.removeItemDecoration(selector);
                }
                this.mParent = null;
                return;
            }
            Log.w("Selectable.Delegator", "not the attached parent view .");
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x0044  */
        public void setSelection(int i) {
            int i2;
            RecyclerView.ViewHolder findViewHolderForAdapterPosition;
            int i3 = this.mSelection;
            if (i3 != i) {
                boolean z = false;
                if (i3 != -1) {
                    RecyclerView.ViewHolder findViewHolderForAdapterPosition2 = this.mParent.findViewHolderForAdapterPosition(i3);
                    if (findViewHolderForAdapterPosition2 == null || findViewHolderForAdapterPosition2.itemView == null) {
                        i2 = this.mSelection;
                        z = true;
                        this.mSelection = i;
                        if (!(i == -1 || (findViewHolderForAdapterPosition = this.mParent.findViewHolderForAdapterPosition(i)) == null || findViewHolderForAdapterPosition.itemView == null)) {
                            findViewHolderForAdapterPosition.itemView.setActivated(true);
                            findViewHolderForAdapterPosition.itemView.setSelected(true);
                        }
                        if (this.mSelector != null) {
                            this.mParent.invalidate();
                        }
                        if (z && i2 != -1) {
                            this.mParent.getAdapter().notifyItemChanged(i2);
                            return;
                        }
                    }
                    findViewHolderForAdapterPosition2.itemView.setActivated(false);
                    findViewHolderForAdapterPosition2.itemView.setSelected(false);
                }
                i2 = 1;
                this.mSelection = i;
                findViewHolderForAdapterPosition.itemView.setActivated(true);
                findViewHolderForAdapterPosition.itemView.setSelected(true);
                if (this.mSelector != null) {
                }
                if (z) {
                }
            }
        }
    }

    public static class Selector extends RecyclerView.ItemDecoration {
        Selectable mAdapter;
        boolean mRequireLayer;
        private Drawable mSelector;

        public Selector(int i) {
            this(createMaskDrawable(i));
            this.mRequireLayer = true;
        }

        public Selector(Drawable drawable) {
            this.mSelector = drawable;
        }

        private static Drawable createMaskDrawable(int i) {
            ShapeDrawable shapeDrawable = new ShapeDrawable();
            shapeDrawable.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            shapeDrawable.getPaint().setColor(i);
            return shapeDrawable;
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            RecyclerView.ViewHolder findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(this.mAdapter.getSelection());
            if (findViewHolderForAdapterPosition != null && findViewHolderForAdapterPosition.itemView != null) {
                View view = findViewHolderForAdapterPosition.itemView;
                int save = canvas.save();
                canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                this.mSelector.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                this.mSelector.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
    }

    int getSelection();
}
