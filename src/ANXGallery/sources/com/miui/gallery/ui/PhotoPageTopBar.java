package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.data.LocationUtil;

public class PhotoPageTopBar {
    private TextView mLocation = ((TextView) this.mTopLayout.findViewById(R.id.top_bar_location));
    private View mOperationView = this.mTopLayout.findViewById(R.id.operation_btn);
    private View mSpecialTypeEnter = this.mTopLayout.findViewById(R.id.special_type_enter);
    private TextView mSubTitle = ((TextView) this.mTopLayout.findViewById(R.id.top_bar_subtitle));
    private TextView mTitle = ((TextView) this.mTopLayout.findViewById(R.id.top_bar_title));
    private View mTopLayout;

    public PhotoPageTopBar(Context context, View.OnClickListener onClickListener) {
        this.mTopLayout = LayoutInflater.from(context).inflate(R.layout.photo_page_top_bar, (ViewGroup) null);
        this.mTopLayout.findViewById(R.id.action_back).setOnClickListener(onClickListener);
    }

    public View getOperationView() {
        return this.mOperationView;
    }

    public View getView() {
        return this.mTopLayout;
    }

    public void hide() {
        this.mTopLayout.setVisibility(8);
    }

    public boolean isShowing() {
        return this.mTopLayout.getVisibility() == 0;
    }

    public void setLocation(Context context, String str) {
        if (LocationUtil.isLocationValidate(str)) {
            this.mLocation.setText(str);
            this.mLocation.setVisibility(0);
            return;
        }
        this.mLocation.setVisibility(4);
    }

    public void setSpecialTypeEnterClickListener(View.OnClickListener onClickListener) {
        this.mSpecialTypeEnter.setOnClickListener(onClickListener);
    }

    public void setSpecialTypeEnterVisible(boolean z) {
        this.mSpecialTypeEnter.setVisibility(z ? 0 : 8);
    }

    public void setSubTitle(String str) {
        this.mSubTitle.setText(str);
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
    }

    public void show() {
        this.mTopLayout.setVisibility(0);
    }

    public void updateSpecialTypeEnter(int i, int i2) {
        if (i > 0 && i2 > 0) {
            View view = this.mSpecialTypeEnter;
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(i);
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setText(i2);
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(view.getResources().getDrawable(i), (Drawable) null, (Drawable) null, (Drawable) null);
            }
        }
    }
}
