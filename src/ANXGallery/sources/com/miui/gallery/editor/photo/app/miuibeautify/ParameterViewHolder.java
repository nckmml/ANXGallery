package com.miui.gallery.editor.photo.app.miuibeautify;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautifyData;

class ParameterViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public ParameterViewHolder(View view) {
        super(view);
        this.mTextView = (TextView) view.findViewById(R.id.label);
    }

    /* access modifiers changed from: package-private */
    public void bind(int i, MiuiBeautifyData miuiBeautifyData) {
        this.mTextView.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
        this.mTextView.setText(miuiBeautifyData.name);
    }
}
