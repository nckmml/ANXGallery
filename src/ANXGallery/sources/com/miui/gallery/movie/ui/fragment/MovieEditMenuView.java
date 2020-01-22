package com.miui.gallery.movie.ui.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.editor.ui.menu.BaseMenuView;
import com.miui.gallery.editor.ui.menu.content.NoScrollViewPagerContentView;
import com.miui.gallery.movie.R;

public class MovieEditMenuView extends BaseMenuView {
    public MovieEditMenuView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public View initBottomView() {
        return inflate(this.mContext, R.layout.movie_edit_menu_bottom_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View initContentView() {
        return new NoScrollViewPagerContentView(this.mContext);
    }

    /* access modifiers changed from: protected */
    public View initTopView() {
        return inflate(this.mContext, R.layout.movie_edit_menu_top_layout, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void modifyBottomGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(0);
    }

    /* access modifiers changed from: protected */
    public void modifyContentGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.photo_movie_edit_content_guide_line_end));
    }

    /* access modifiers changed from: protected */
    public void modifyTopGuideline(Guideline guideline) {
        guideline.setGuidelineEnd(getResources().getDimensionPixelSize(R.dimen.photo_movie_edit_top_guide_line_end));
    }
}
