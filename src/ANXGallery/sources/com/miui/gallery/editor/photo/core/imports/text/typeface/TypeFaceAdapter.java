package com.miui.gallery.editor.photo.core.imports.text.typeface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.widget.recyclerview.SingleChoiceRecyclerView;
import java.util.List;

public class TypeFaceAdapter extends SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter<TypeFaceHolder> {
    private LayoutInflater mLayoutInflater;
    private List<TextStyle> mTextStyleList;

    public TypeFaceAdapter(Context context, List<TextStyle> list) {
        this.mTextStyleList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public int getItemCount() {
        List<TextStyle> list = this.mTextStyleList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void onBindView(TypeFaceHolder typeFaceHolder, int i) {
        TextStyle textStyle = this.mTextStyleList.get(i);
        typeFaceHolder.bind(textStyle, i == getSelectedItemPosition());
        if (textStyle.isDownloadSuccess()) {
            textStyle.setState(17);
        }
    }

    public void onBindViewHolder(TypeFaceHolder typeFaceHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            onBindViewHolder(typeFaceHolder, i);
            return;
        }
        TextStyle textStyle = this.mTextStyleList.get(i);
        typeFaceHolder.bind(textStyle, i == getSelectedItemPosition());
        if (textStyle.isDownloadSuccess()) {
            textStyle.setState(17);
        }
    }

    public TypeFaceHolder onCreateSingleChoiceViewHolder(ViewGroup viewGroup, int i) {
        return new TypeFaceHolder(this.mLayoutInflater.inflate(R.layout.text_edit_dialog_options_font_panel_item, viewGroup, false));
    }
}
