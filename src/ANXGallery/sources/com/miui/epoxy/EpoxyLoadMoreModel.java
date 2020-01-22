package com.miui.epoxy;

import com.miui.epoxy.EpoxyViewHolder;

public abstract class EpoxyLoadMoreModel<VH extends EpoxyViewHolder> extends EpoxyModel<VH> {
    private int state = 1;

    public void bindData(VH vh) {
        int i = this.state;
        if (i == 0) {
            onLoadMoreStart(vh);
        } else if (i == 1) {
            onLoadMoreComplete(vh);
        } else if (i == 2) {
            onLoadMoreFailed(vh);
        }
    }

    public abstract void onLoadMoreComplete(VH vh);

    public abstract void onLoadMoreFailed(VH vh);

    public abstract void onLoadMoreStart(VH vh);

    public final void setState(int i) {
        this.state = i;
    }
}
