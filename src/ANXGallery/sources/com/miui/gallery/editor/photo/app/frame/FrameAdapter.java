package com.miui.gallery.editor.photo.app.frame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.FrameData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class FrameAdapter extends SimpleRecyclerView.Adapter<FrameItemHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator = new Selectable.Delegator(0);
    private List<FrameData> mFrameDataList;
    private int mItemWidth;

    FrameAdapter(List<FrameData> list, Context context) {
        this.mFrameDataList = list;
        this.mContext = context;
        if (getItemCount() != 0) {
            this.mItemWidth = (int) ((((float) ScreenUtils.getScreenWidth()) - (context.getResources().getDimension(R.dimen.editor_menu_frame_item_start) * 2.0f)) / ((float) getItemCount()));
        }
    }

    public int getItemCount() {
        List<FrameData> list = this.mFrameDataList;
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

    public void onBindViewHolder(FrameItemHolder frameItemHolder, int i) {
        super.onBindViewHolder(frameItemHolder, i);
        frameItemHolder.setFrameData(this.mFrameDataList.get(i));
        this.mDelegator.onBindViewHolder(frameItemHolder, i);
    }

    public FrameItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = getInflater().inflate(R.layout.frame_menu_item, viewGroup, false);
        inflate.getLayoutParams().width = this.mItemWidth;
        return new FrameItemHolder(this.mContext, inflate);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
