package com.miui.gallery.editor.ui.menu.bottom;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.miui.gallery.editor.R;

public class BaseEditBottomView extends ConstraintLayout {
    public BaseEditBottomView(Context context) {
        super(context);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        inflate(context, R.layout.common_editor_apply_layout, this);
    }
}
