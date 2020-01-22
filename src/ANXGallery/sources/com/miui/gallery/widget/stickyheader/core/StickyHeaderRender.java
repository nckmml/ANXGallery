package com.miui.gallery.widget.stickyheader.core;

import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public interface StickyHeaderRender<VH extends RecyclerView.ViewHolder> {
    void draw(RecyclerView recyclerView, Canvas canvas, VH vh, Rect rect);
}
