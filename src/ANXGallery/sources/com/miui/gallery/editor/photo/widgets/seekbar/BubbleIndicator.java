package com.miui.gallery.editor.photo.widgets.seekbar;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.SeekBar;

public class BubbleIndicator<V extends View> implements SeekBar.OnSeekBarChangeListener {
    private Callback<V> mCallback;
    private V mContentView;
    private SeekBar.OnSeekBarChangeListener mDelegate;
    private int[] mLocation = new int[2];
    private int mOffsetY;
    /* access modifiers changed from: private */
    public PopupWindow mPopup;

    public interface Callback<V extends View> {
        void updateProgress(V v, int i);
    }

    public BubbleIndicator(V v, int i, Callback<V> callback, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mContentView = v;
        this.mOffsetY = i;
        this.mCallback = callback;
        this.mDelegate = onSeekBarChangeListener;
    }

    /* access modifiers changed from: private */
    public int computeX(SeekBar seekBar) {
        return (((this.mLocation[0] + seekBar.getPaddingLeft()) - seekBar.getThumbOffset()) + seekBar.getThumb().getBounds().centerX()) - (this.mContentView.getWidth() / 2);
    }

    /* access modifiers changed from: private */
    public int computeY(SeekBar seekBar) {
        return ((this.mLocation[1] + (seekBar.getHeight() / 2)) - this.mOffsetY) - (this.mContentView.getHeight() / 2);
    }

    private void updateProgress(V v, int i) {
        onProgressUpdate(v, i);
        Callback<V> callback = this.mCallback;
        if (callback != null) {
            callback.updateProgress(this.mContentView, i);
        }
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }

    public boolean isShowing() {
        PopupWindow popupWindow = this.mPopup;
        return popupWindow != null && popupWindow.isShowing();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            updateProgress(this.mContentView, i);
            PopupWindow popupWindow = this.mPopup;
            if (popupWindow != null) {
                popupWindow.update(computeX(seekBar), computeY(seekBar), -1, -1);
            }
        }
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mDelegate;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(seekBar, i, z);
        }
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(V v, int i) {
    }

    public void onStartTrackingTouch(final SeekBar seekBar) {
        if (this.mPopup == null) {
            this.mContentView.setVisibility(4);
            this.mPopup = new PopupWindow(this.mContentView, -2, -2, false);
            this.mPopup.setTouchable(false);
            this.mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    BubbleIndicator.this.mPopup.update(BubbleIndicator.this.computeX(seekBar), BubbleIndicator.this.computeY(seekBar), -1, -1);
                    view.setVisibility(0);
                    view.removeOnLayoutChangeListener(this);
                }
            });
        }
        seekBar.getLocationInWindow(this.mLocation);
        this.mPopup.setAnimationStyle(2131820889);
        this.mPopup.showAtLocation(seekBar, 0, computeX(seekBar), computeY(seekBar));
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mDelegate;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(seekBar);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.mPopup.dismiss();
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mDelegate;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(seekBar);
        }
    }
}
