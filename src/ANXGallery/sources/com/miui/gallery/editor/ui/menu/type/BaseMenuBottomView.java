package com.miui.gallery.editor.ui.menu.type;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.editor.ui.menu.BaseMenuView;
import com.miui.gallery.editor.ui.menu.bottom.BaseEditBottomView;

public abstract class BaseMenuBottomView extends BaseMenuView {
    public BaseMenuBottomView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return new BaseEditBottomView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public View initTopView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void modifyBottomGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(0);
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
    }

    /* access modifiers changed from: protected */
    public void modifyTopGuideline(Guideline guideline) {
    }
}
