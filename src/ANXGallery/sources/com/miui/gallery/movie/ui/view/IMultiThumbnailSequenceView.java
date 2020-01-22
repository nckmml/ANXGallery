package com.miui.gallery.movie.ui.view;

import android.view.View;

public interface IMultiThumbnailSequenceView<T> {

    public interface OnScrollChangeListener {
        void onScrollChanged(int i, int i2);
    }

    void fullScroll(int i);

    double getPixelPerMicrosecond();

    View getRealView();

    int getSequenceWidth(int i, double d);

    void scrollTo(int i, int i2);

    void setEndPadding(int i);

    void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener);

    void setPixelPerMicrosecond(double d);

    void setStartPadding(int i);

    void setThumbnailAspectRatio(float f);

    void setThumbnailImageFillMode(int i);

    void setThumbnailSequenceDescArray(T t);
}
