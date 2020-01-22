package com.miui.gallery.collage.app.poster;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.collage.core.poster.PosterModel;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class PosterMenuAdapter extends SimpleRecyclerView.Adapter<PosterHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator;
    private int mImageCount;
    private List<PosterModel> mPosterList;

    PosterMenuAdapter(Context context, List<PosterModel> list, Selectable.Selector selector) {
        this.mContext = context;
        this.mDelegator = new Selectable.Delegator(0, selector);
        this.mPosterList = list;
    }

    public int getItemCount() {
        return this.mPosterList.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(PosterHolder posterHolder, int i) {
        super.onBindViewHolder(posterHolder, i);
        posterHolder.setPosterModel(this.mContext.getResources(), this.mPosterList.get(i), this.mImageCount, i);
        this.mDelegator.onBindViewHolder(posterHolder, i);
    }

    public PosterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PosterHolder(viewGroup, this.mContext);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setImageCount(int i) {
        this.mImageCount = i;
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
