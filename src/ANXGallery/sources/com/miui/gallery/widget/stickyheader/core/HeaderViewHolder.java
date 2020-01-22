package com.miui.gallery.widget.stickyheader.core;

import android.view.View;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;

public class HeaderViewHolder extends BaseViewHolder {
    long mHeaderId = -1;
    int mHeaderIndex = -1;
    int mHeaderViewType = -1;
    boolean mIsStickyHeader = false;

    public HeaderViewHolder(View view) {
        super(view);
    }

    /* access modifiers changed from: package-private */
    public long getHeaderId() {
        return this.mHeaderId;
    }

    /* access modifiers changed from: package-private */
    public int getHeaderIndex() {
        return this.mHeaderIndex;
    }

    /* access modifiers changed from: package-private */
    public int getHeaderViewType() {
        return this.mHeaderViewType;
    }

    /* access modifiers changed from: package-private */
    public boolean isStickyHeader() {
        return this.mIsStickyHeader;
    }
}
