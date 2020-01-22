package com.miui.gallery.editor.photo.app.filter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.FilterData;
import com.miui.gallery.editor.ui.view.RoundImageView;

class FilterMenuItem extends RecyclerView.ViewHolder {
    private ColorDrawable mColorDrawable = ((ColorDrawable) new ColorDrawable().mutate());
    private View mConfigIndicator;
    private FilterData mData;
    private ImageView mPreviewView;
    boolean mSelected;
    private ImageView mSelector;
    private TextView mTitleView;
    private TextView mValueIndicator;

    FilterMenuItem(View view) {
        super(view);
        this.mPreviewView = (ImageView) view.findViewById(R.id.preview);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mValueIndicator = (TextView) view.findViewById(R.id.value_indicator);
        this.mConfigIndicator = view.findViewById(R.id.configable_indicator);
        this.mSelector = (ImageView) view.findViewById(R.id.selector);
    }

    /* access modifiers changed from: package-private */
    public void bindData(FilterData filterData) {
        this.mData = filterData;
        this.mPreviewView.setImageResource(filterData.icon);
        this.mPreviewView.setContentDescription(filterData.name);
        this.mTitleView.setText(filterData.name);
    }

    /* access modifiers changed from: package-private */
    public void setForegroundColor(int i) {
        this.mColorDrawable.setColor(i);
        GradientDrawable gradientDrawable = (GradientDrawable) this.mTitleView.getBackground();
        gradientDrawable.setColor(i);
        if (this.mSelected) {
            this.mColorDrawable.setAlpha((int) (this.mData.isNone() ? 153.0d : 229.5d));
            gradientDrawable.setColor(this.mTitleView.getResources().getColor(R.color.transparent));
        }
        RoundImageView.class.cast(this.mSelector).setTopCorner(this.mSelected);
        this.mSelector.setImageDrawable(this.mColorDrawable);
    }

    /* access modifiers changed from: package-private */
    public void setState(boolean z, boolean z2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mSelector.getLayoutParams();
        this.mSelected = z;
        int[] rules = layoutParams.getRules();
        if (!z) {
            this.mValueIndicator.setVisibility(8);
            this.mConfigIndicator.setVisibility(8);
            if (rules[6] != R.id.title) {
                layoutParams.removeRule(10);
                layoutParams.addRule(6, R.id.title);
                return;
            }
            return;
        }
        if (!z2) {
            this.mValueIndicator.setVisibility(8);
            if (!this.mData.isNone()) {
                this.mConfigIndicator.setVisibility(0);
            }
        } else {
            this.mValueIndicator.setVisibility(0);
            this.mConfigIndicator.setVisibility(8);
        }
        if (rules[10] != -1) {
            layoutParams.addRule(10, -1);
            layoutParams.removeRule(6);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateIndicator(int i) {
        this.mValueIndicator.setText(String.valueOf(i));
    }
}
