package com.miui.gallery.adapter;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.miui.gallery.GalleryApp;

public class ExcludeOnTouchListener implements View.OnTouchListener {
    private Rect mExcludeRegion;
    private View mExcludedView;
    private View mHostView;
    private int mLastX;
    private int mLastY;
    private int mMoveX;
    private int mMoveY;
    private OnTouchValidListener mOnTouchValidListener;
    private int mSlop = ViewConfiguration.get(GalleryApp.sGetAndroidContext()).getScaledTouchSlop();

    interface OnTouchValidListener {
        void onTouchValid(View view);
    }

    public ExcludeOnTouchListener(View view, View view2, OnTouchValidListener onTouchValidListener) {
        this.mHostView = view;
        this.mExcludedView = view2;
        this.mOnTouchValidListener = onTouchValidListener;
    }

    private void initExcludeRegion() {
        Rect rect = this.mExcludeRegion;
        if (rect == null || rect.width() == 0 || this.mExcludeRegion.height() == 0) {
            Rect rect2 = new Rect();
            Rect rect3 = new Rect();
            this.mExcludedView.getGlobalVisibleRect(rect2);
            this.mHostView.getGlobalVisibleRect(rect3);
            this.mExcludeRegion = new Rect();
            this.mExcludeRegion.set(rect2.left - rect3.left, rect2.top - rect3.top, rect2.right - rect3.left, rect2.bottom - rect3.top);
            Rect rect4 = this.mExcludeRegion;
            rect4.inset(-rect4.width(), -this.mExcludeRegion.height());
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        initExcludeRegion();
        if (this.mExcludeRegion.contains(x, y)) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                int i = this.mMoveX;
                int i2 = this.mSlop;
                if (i <= i2 && this.mMoveY <= i2) {
                    OnTouchValidListener onTouchValidListener = this.mOnTouchValidListener;
                    if (onTouchValidListener != null) {
                        onTouchValidListener.onTouchValid(view);
                    }
                    return true;
                }
            } else if (action == 2) {
                this.mMoveX += Math.abs(this.mLastX - x);
                this.mMoveY += Math.abs(this.mLastY - y);
                this.mLastX = x;
                this.mLastY = y;
            }
            return false;
        }
        this.mLastX = x;
        this.mLastY = y;
        this.mMoveX = 0;
        this.mMoveY = 0;
        return true;
    }
}
