package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.widget.DividerTypeProvider;

public class SectionedDividerItemDecoration extends RecyclerView.ItemDecoration {
    private DividerTypeProvider mDividerTypeProvider;
    private int mItemDistance;
    private Drawable mItemDivider;
    private int mItemDividerHeight;
    private Rect mItemDividerPadding = new Rect(0, 0, 0, 0);
    private int mSectionDistance;
    private Drawable mSectionDivider;
    private int mSectionDividerHeight;
    private int mSectionDividerMargin;

    public SectionedDividerItemDecoration(Context context, int i, int i2, DividerTypeProvider dividerTypeProvider) {
        this.mSectionDivider = context.getResources().getDrawable(i);
        this.mItemDivider = context.getResources().getDrawable(i2);
        this.mSectionDividerHeight = context.getResources().getDimensionPixelSize(R.dimen.section_divider_height);
        this.mItemDividerHeight = context.getResources().getDimensionPixelSize(R.dimen.item_divider_height);
        this.mDividerTypeProvider = dividerTypeProvider;
        this.mItemDistance = context.getResources().getDimensionPixelSize(R.dimen.album_page_item_distance);
        this.mSectionDistance = context.getResources().getDimensionPixelSize(R.dimen.album_page_section_distance);
        this.mSectionDividerMargin = context.getResources().getDimensionPixelSize(R.dimen.album_page_section_divider_margin);
    }

    private void drawBottomDivider(Canvas canvas, View view, int i, int i2, int i3) {
        int i4;
        int i5;
        Drawable drawable;
        int i6;
        int bottom = view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin + Math.round(ViewCompat.getTranslationY(view));
        if (i == 1) {
            Drawable drawable2 = this.mSectionDivider;
            if (drawable2 != null && (i4 = this.mSectionDividerHeight) > 0) {
                int i7 = bottom + (this.mSectionDistance / 2);
                int i8 = this.mSectionDividerMargin;
                drawable2.setBounds(i2 + i8, i7, i3 - i8, i4 + i7);
                this.mSectionDivider.draw(canvas);
            }
        } else if (i != 2) {
            if (i == 3 && (drawable = this.mItemDivider) != null && (i6 = this.mItemDividerHeight) > 0) {
                drawable.setBounds(i2, bottom + this.mItemDividerPadding.top, i3, (i6 + bottom) - this.mItemDividerPadding.bottom);
                this.mItemDivider.draw(canvas);
            }
        } else if (this.mItemDivider != null && (i5 = this.mItemDividerHeight) > 0) {
            int i9 = i5 + bottom;
            if (1 == view.getLayoutDirection()) {
                this.mItemDivider.setBounds(i2 + this.mItemDividerPadding.right, bottom + this.mItemDividerPadding.top, i3 - this.mItemDividerPadding.left, i9 - this.mItemDividerPadding.bottom);
            } else {
                this.mItemDivider.setBounds(i2 + this.mItemDividerPadding.left, bottom + this.mItemDividerPadding.top, i3 - this.mItemDividerPadding.right, i9 - this.mItemDividerPadding.bottom);
            }
            this.mItemDivider.draw(canvas);
        }
    }

    private void drawTopDivider(Canvas canvas, View view, int i, int i2, int i3) {
        Drawable drawable;
        int i4;
        int top = view.getTop() + ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin + Math.round(ViewCompat.getTranslationY(view));
        if (i == 3 && (drawable = this.mItemDivider) != null && (i4 = this.mItemDividerHeight) > 0) {
            drawable.setBounds(i2, top + this.mItemDividerPadding.top, i3, (i4 + top) - this.mItemDividerPadding.bottom);
            this.mItemDivider.draw(canvas);
        }
    }

    private int getBottomDividerType(int i) {
        DividerTypeProvider dividerTypeProvider = this.mDividerTypeProvider;
        if (dividerTypeProvider != null) {
            return dividerTypeProvider.getBottomDividerType(i);
        }
        return 0;
    }

    private int getTopDividerType(int i) {
        DividerTypeProvider dividerTypeProvider = this.mDividerTypeProvider;
        if (dividerTypeProvider != null) {
            return dividerTypeProvider.getTopDividerType(i);
        }
        return 0;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = getTopDividerType(childAdapterPosition) != 3 ? 0 : this.mItemDividerHeight;
        int bottomDividerType = getBottomDividerType(childAdapterPosition);
        rect.set(0, i, 0, bottomDividerType != 1 ? bottomDividerType != 2 ? bottomDividerType != 3 ? 0 : this.mItemDividerHeight : this.mItemDistance : this.mSectionDistance);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int round = Math.round(ViewCompat.getTranslationX(childAt));
            int left = childAt.getLeft() + round;
            int right = childAt.getRight() + round;
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            Canvas canvas2 = canvas;
            View view = childAt;
            int i2 = left;
            int i3 = right;
            drawTopDivider(canvas2, view, getTopDividerType(childAdapterPosition), i2, i3);
            drawBottomDivider(canvas2, view, getBottomDividerType(childAdapterPosition), i2, i3);
        }
        super.onDrawOver(canvas, recyclerView, state);
    }

    public void setItemDividerPadding(Rect rect) {
        if (rect != null) {
            this.mItemDividerPadding.set(rect);
        } else {
            this.mItemDividerPadding.setEmpty();
        }
    }
}
