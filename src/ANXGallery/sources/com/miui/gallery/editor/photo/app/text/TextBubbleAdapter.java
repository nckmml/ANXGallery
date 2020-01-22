package com.miui.gallery.editor.photo.app.text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.TextData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class TextBubbleAdapter extends SimpleRecyclerView.Adapter<TextBubbleHolder> implements Selectable {
    private Context mContext;
    private Selectable.Delegator mDelegator = new Selectable.Delegator(0);
    private List<TextData> mTextDataList;

    public TextBubbleAdapter(Context context, List<TextData> list, int i) {
        this.mTextDataList = list;
        this.mContext = context;
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

    public void onBindViewHolder(TextBubbleHolder textBubbleHolder, int i) {
        super.onBindViewHolder(textBubbleHolder, i);
        textBubbleHolder.bind(this.mTextDataList.get(i), i);
        this.mDelegator.onBindViewHolder(textBubbleHolder, i);
    }

    public TextBubbleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TextBubbleHolder(LayoutInflater.from(this.mContext).inflate(R.layout.text_menu_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
