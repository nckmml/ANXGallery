package com.miui.gallery.editor.ui.menu.type;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.editor.R;
import com.miui.gallery.editor.ui.menu.BaseMenuView;

public class EditNavMenuView extends BaseMenuView {
    public EditNavMenuView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return inflate(this.mContext, R.layout.common_edit_menu_confirm_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return inflate(this.mContext, R.layout.common_edit_menu_nav_content_view, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initTopView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void modifyBottomGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_nav_bottom_guide_line_end));
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.photo_editor_nav_guide_line_end));
    }

    /* access modifiers changed from: protected */
    public void modifyTopGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_menu_height));
    }
}
