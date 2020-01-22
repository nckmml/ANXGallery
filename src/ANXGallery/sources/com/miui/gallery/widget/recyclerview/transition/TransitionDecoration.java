package com.miui.gallery.widget.recyclerview.transition;

import android.graphics.Canvas;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;
import java.util.Collections;
import java.util.List;

class TransitionDecoration extends RecyclerView.ItemDecoration {
    private List<DecorItem> mDecorItems;
    private float mProgress;

    TransitionDecoration() {
    }

    private void drawItems(Canvas canvas) {
        List<DecorItem> list = this.mDecorItems;
        if (list != null) {
            for (DecorItem onDraw : list) {
                onDraw.onDraw(canvas, this.mProgress);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<DecorItem> getDecorItems() {
        List<DecorItem> list = this.mDecorItems;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        drawItems(canvas);
    }

    public void updateItems(RecyclerView recyclerView, List<DecorItem> list) {
        this.mDecorItems = list;
        recyclerView.invalidate();
    }

    public void updateProgress(RecyclerView recyclerView, float f) {
        List<DecorItem> list = this.mDecorItems;
        if (list == null || list.isEmpty()) {
            Log.e("TransitionDecoration", "mDecorItems is empty");
            return;
        }
        float min = Math.min(1.0f, Math.max(f, 0.0f));
        if (Float.compare(this.mProgress, min) != 0) {
            this.mProgress = min;
            Log.d("TransitionDecoration", "update progress %s", (Object) Float.valueOf(this.mProgress));
            recyclerView.invalidate();
        }
    }
}
