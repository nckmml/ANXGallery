package com.miui.gallery.editor.photo.app.doodle;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

class DoodleAdapter extends SimpleRecyclerView.Adapter<DoodleHolder> implements Selectable {
    private Context mContext;
    private List<DoodleData> mDataList;
    private Selectable.Delegator mDelegator = new Selectable.Delegator(0);

    DoodleAdapter(Context context, List<DoodleData> list) {
        this.mContext = context;
        this.mDataList = new ArrayList(list);
    }

    public int getItemCount() {
        List<DoodleData> list = this.mDataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(DoodleHolder doodleHolder, int i) {
        super.onBindViewHolder(doodleHolder, i);
        doodleHolder.setIconPath(i != getSelection() ? this.mDataList.get(i).normal : this.mDataList.get(i).selected, i);
        this.mDelegator.onBindViewHolder(doodleHolder, i);
    }

    public DoodleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DoodleHolder(this.mContext, viewGroup);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
