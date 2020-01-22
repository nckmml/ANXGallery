package com.miui.gallery.video.editor.ui.menu;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.editor.ui.menu.type.BaseMenuBottomView;
import com.miui.gallery.video.editor.ui.menu.bottom.WaterMarkBottomView;
import com.miui.gallery.video.editor.ui.menu.content.BaseContentView;

public class WaterMarkView extends BaseMenuBottomView {
    public WaterMarkView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return new WaterMarkBottomView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return new BaseContentView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void modifyBottomGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(0);
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_watermark_content_line_guide));
    }
}
