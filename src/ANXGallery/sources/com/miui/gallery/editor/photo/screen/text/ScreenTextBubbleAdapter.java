package com.miui.gallery.editor.photo.screen.text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.TextData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class ScreenTextBubbleAdapter extends SimpleRecyclerView.Adapter<ScreenTextBubbleHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator;
    private List<TextData> mTextDataList;

    public ScreenTextBubbleAdapter(Context context, List<TextData> list, int i) {
        this.mTextDataList = list;
        this.mContext = context;
        this.mDelegator = new Selectable.Delegator(i, new Selectable.Selector(context.getResources().getDrawable(R.drawable.shape_text_edit_menu_selector)));
    }

    public int getItemCount() {
        List<TextData> list = this.mTextDataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public TextData getItemData(int i) {
        List<TextData> list = this.mTextDataList;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.mTextDataList.get(i);
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(ScreenTextBubbleHolder screenTextBubbleHolder, int i) {
        super.onBindViewHolder(screenTextBubbleHolder, i);
        screenTextBubbleHolder.bind(this.mTextDataList.get(i), i);
        this.mDelegator.onBindViewHolder(screenTextBubbleHolder, i);
    }

    public ScreenTextBubbleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ScreenTextBubbleHolder(LayoutInflater.from(this.mContext).inflate(R.layout.screen_text_menu_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
