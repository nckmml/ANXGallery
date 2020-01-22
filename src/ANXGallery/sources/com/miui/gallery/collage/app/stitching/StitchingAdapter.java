package com.miui.gallery.collage.app.stitching;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.collage.core.stitching.StitchingModel;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class StitchingAdapter extends SimpleRecyclerView.Adapter<StitchingHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator;
    private List<StitchingModel> mStitchingModelList;

    StitchingAdapter(Context context, List<StitchingModel> list, Selectable.Selector selector) {
        this.mStitchingModelList = list;
        this.mContext = context;
        this.mDelegator = new Selectable.Delegator(0, selector);
    }

    public int getItemCount() {
        return this.mStitchingModelList.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(StitchingHolder stitchingHolder, int i) {
        super.onBindViewHolder(stitchingHolder, i);
        stitchingHolder.setStitchingModel(this.mContext.getResources(), this.mStitchingModelList.get(i), i);
        this.mDelegator.onBindViewHolder(stitchingHolder, i);
    }

    public StitchingHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new StitchingHolder(viewGroup, this.mContext);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
