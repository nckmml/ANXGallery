package com.miui.gallery.editor.photo.app.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.editor.ui.menu.type.BaseMenuBottomView;

public class CropView extends BaseMenuBottomView {
    public CropView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return inflate(this.mContext, R.layout.photo_editor_crop_apply_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return inflate(this.mContext, R.layout.photo_editor_menu_crop_content_view, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initTopView() {
        return inflate(this.mContext, R.layout.photo_editor_crop_top_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_menu_crop_content_guide_line_end));
    }

    /* access modifiers changed from: protected */
    public void modifyTopGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_menu_crop_top_guide_line_end));
    }
}
