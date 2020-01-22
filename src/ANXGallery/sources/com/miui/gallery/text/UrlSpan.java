package com.miui.gallery.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class UrlSpan extends ClickableSpan {
    private UrlSpanOnClickListener mOnClickListener;

    public interface UrlSpanOnClickListener {
        void onClick();
    }

    public UrlSpan(UrlSpanOnClickListener urlSpanOnClickListener) {
        this.mOnClickListener = urlSpanOnClickListener;
    }

    public void onClick(View view) {
        UrlSpanOnClickListener urlSpanOnClickListener = this.mOnClickListener;
        if (urlSpanOnClickListener != null) {
            urlSpanOnClickListener.onClick();
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(true);
        textPaint.setColor(-16738305);
    }
}
