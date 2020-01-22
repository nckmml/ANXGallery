package com.miui.gallery.search.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.miui.gallery.R;
import com.miui.gallery.search.hint.SearchBarHintAdapter;
import com.miui.gallery.search.widget.bannerView.BannerView;
import com.miui.gallery.search.widget.bannerView.ILoopController;

public class BannerSearchBar extends LinearLayout implements View.OnClickListener {
    private BannerView mBannerView;
    private OnHintClickListener mOnHintClickListener;

    public interface OnHintClickListener {
        void onHintClicked(BannerSearchBar bannerSearchBar, int i);
    }

    public BannerSearchBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public BannerSearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerSearchBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public ILoopController getLooperController() {
        BannerView bannerView = this.mBannerView;
        if (bannerView != null) {
            return bannerView;
        }
        throw new RuntimeException("No looper controller found");
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.banner_search_bar_layout, this);
        this.mBannerView = (BannerView) findViewById(R.id.banner_view);
        setOnClickListener(this);
    }

    public void onClick(View view) {
        OnHintClickListener onHintClickListener = this.mOnHintClickListener;
        if (onHintClickListener != null) {
            onHintClickListener.onHintClicked(this, getLooperController().getCurrentPosition());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void setHintAdapter(SearchBarHintAdapter searchBarHintAdapter) {
        BannerView bannerView = this.mBannerView;
        if (bannerView != null) {
            bannerView.setAdapter(searchBarHintAdapter);
            return;
        }
        throw new RuntimeException("No banner view found");
    }

    public void setOnHintClickListener(OnHintClickListener onHintClickListener) {
        this.mOnHintClickListener = onHintClickListener;
    }
}
