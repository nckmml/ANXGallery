package com.miui.gallery.video.editor.ui.menu;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.editor.ui.menu.type.BaseMenuContentView;
import com.miui.gallery.video.editor.ui.menu.content.BaseContentView;

public class SmartEffectView extends BaseMenuContentView {
    public SmartEffectView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return new BaseContentView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.editor_audio_content_line_guide));
    }
}
