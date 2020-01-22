package com.miui.gallery.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.baseui.R;
import miui.view.animation.CubicEaseOutInterpolator;

public class StartEndSmoothScrollerController extends SmoothScrollerController {
    protected float mMinDistance;

    public StartEndSmoothScrollerController(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mMinDistance = context.getResources().getDimension(R.dimen.scroll_control_item_min_scroll_distance);
    }

    /* access modifiers changed from: protected */
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 155.0f / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getLayoutManager() != null) {
            int calculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
            int calculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
            int leftDecorationWidth = calculateDxToMakeVisible > 0 ? calculateDxToMakeVisible - getLayoutManager().getLeftDecorationWidth(view) : calculateDxToMakeVisible + getLayoutManager().getRightDecorationWidth(view);
            int topDecorationHeight = calculateDyToMakeVisible > 0 ? calculateDyToMakeVisible - getLayoutManager().getTopDecorationHeight(view) : calculateDyToMakeVisible + getLayoutManager().getBottomDecorationHeight(view);
            if (((float) ((int) Math.sqrt((double) ((leftDecorationWidth * leftDecorationWidth) + (topDecorationHeight * topDecorationHeight))))) > this.mMinDistance) {
                action.update(-leftDecorationWidth, -topDecorationHeight, 300, new CubicEaseOutInterpolator());
            }
        }
    }
}
