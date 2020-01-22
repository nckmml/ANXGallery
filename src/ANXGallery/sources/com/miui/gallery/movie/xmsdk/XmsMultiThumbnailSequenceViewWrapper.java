package com.miui.gallery.movie.xmsdk;

import android.content.Context;
import android.view.View;
import com.miui.gallery.movie.ui.view.IMultiThumbnailSequenceView;
import com.miui.gallery.xmstreaming.XmsMultiThumbnailSequenceView;
import java.util.ArrayList;

public class XmsMultiThumbnailSequenceViewWrapper implements IMultiThumbnailSequenceView<ArrayList<XmsMultiThumbnailSequenceView.ThumbnailSequenceDesc>> {
    private XmsMultiThumbnailSequenceView mImpl;

    public XmsMultiThumbnailSequenceViewWrapper(Context context) {
        this.mImpl = new XmsMultiThumbnailSequenceViewImpl(context);
    }

    public void fullScroll(int i) {
        this.mImpl.fullScroll(i);
    }

    public double getPixelPerMicrosecond() {
        return this.mImpl.getPixelPerMicrosecond();
    }

    public View getRealView() {
        return this.mImpl;
    }

    public int getSequenceWidth(int i, double d) {
        return (int) Math.floor((((double) i) * d * 1000.0d) + 0.5d);
    }

    public void scrollTo(int i, int i2) {
        this.mImpl.scrollTo(i, i2);
    }

    public void setEndPadding(int i) {
        this.mImpl.setEndPadding(i);
    }

    public void setOnScrollChangeListener(IMultiThumbnailSequenceView.OnScrollChangeListener onScrollChangeListener) {
        this.mImpl.setOnScrollChangeListenser(new XmsMultiThumbnailSequenceView.OnScrollChangeListener() {
            public final void onScrollChanged(XmsMultiThumbnailSequenceView xmsMultiThumbnailSequenceView, int i, int i2) {
                IMultiThumbnailSequenceView.OnScrollChangeListener.this.onScrollChanged(i, i2);
            }
        });
    }

    public void setPixelPerMicrosecond(double d) {
        this.mImpl.setPixelPerMicrosecond(d);
    }

    public void setStartPadding(int i) {
        this.mImpl.setStartPadding(i);
    }

    public void setThumbnailAspectRatio(float f) {
        this.mImpl.setThumbnailAspectRatio(f);
    }

    public void setThumbnailImageFillMode(int i) {
        this.mImpl.setThumbnailImageFillMode(i);
    }

    public void setThumbnailSequenceDescArray(ArrayList<XmsMultiThumbnailSequenceView.ThumbnailSequenceDesc> arrayList) {
        this.mImpl.setThumbnailSequenceDescArray(arrayList);
    }
}
