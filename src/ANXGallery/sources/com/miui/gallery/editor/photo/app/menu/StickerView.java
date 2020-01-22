package com.miui.gallery.editor.photo.app.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.editor.ui.menu.content.NoScrollViewPagerContentView;
import com.miui.gallery.editor.ui.menu.type.BaseMenuTopView;

public class StickerView extends BaseMenuTopView {
    public StickerView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return inflate(this.mContext, R.layout.photo_editor_sticker_apply_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return new NoScrollViewPagerContentView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public View initTopView() {
        return inflate(this.mContext, R.layout.photo_editor_sticker_top_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void modifyTopGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_menu_sticker_top_guide_end));
    }
}
