package com.miui.gallery.widget.stickyheader.core;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public interface OnHeaderClickListener {
    boolean onHeaderClick(RecyclerView recyclerView, View view, long j);
}
