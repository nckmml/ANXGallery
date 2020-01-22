package com.miui.gallery.editor.photo.app.miuibeautify;

import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautifyData;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class BeautyParameterAdapter extends SimpleRecyclerView.Adapter<ParameterViewHolder> {
    private int[] mIcons;
    private List<MiuiBeautifyData> mParameterData;

    BeautyParameterAdapter(List<MiuiBeautifyData> list, int[] iArr) {
        this.mParameterData = list;
        this.mIcons = iArr;
    }

    public int getItemCount() {
        return this.mParameterData.size();
    }

    public void onBindViewHolder(ParameterViewHolder parameterViewHolder, int i) {
        super.onBindViewHolder(parameterViewHolder, i);
        parameterViewHolder.bind(this.mIcons[i], this.mParameterData.get(i));
    }

    public ParameterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ParameterViewHolder(getInflater().inflate(R.layout.miuibeauty_menu_item, viewGroup, false));
    }
}
