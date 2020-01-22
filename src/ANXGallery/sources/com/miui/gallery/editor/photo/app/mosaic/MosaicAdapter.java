package com.miui.gallery.editor.photo.app.mosaic;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.editor.photo.core.common.model.MosaicData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

class MosaicAdapter extends SimpleRecyclerView.Adapter<MosaicHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator = new Selectable.Delegator(0);
    private List<MosaicData> mMosaicDataList;

    MosaicAdapter(Context context, List<MosaicData> list) {
        this.mContext = context;
        this.mMosaicDataList = new ArrayList(list);
    }

    public int getItemCount() {
        List<MosaicData> list = this.mMosaicDataList;
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

    public void onBindViewHolder(MosaicHolder mosaicHolder, int i) {
        super.onBindViewHolder(mosaicHolder, i);
        mosaicHolder.setIconPath(this.mMosaicDataList.get(i).iconPath, i);
        this.mDelegator.onBindViewHolder(mosaicHolder, i);
    }

    public MosaicHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MosaicHolder(this.mContext, viewGroup);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
