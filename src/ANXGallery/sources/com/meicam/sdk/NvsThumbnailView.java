package com.meicam.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class NvsThumbnailView extends View {
    private long m_iconGetter = 0;
    private String m_mediaFilePath;
    private boolean m_needUpdate = false;
    private boolean m_painting = false;
    /* access modifiers changed from: private */
    public Bitmap m_thumbnail = null;
    private long m_thumbnailView = 0;

    public NvsThumbnailView(Context context) {
        super(context);
    }

    public NvsThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NvsThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NvsThumbnailView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void cancelIconTask() {
        if (!isInEditMode()) {
            nativeCancelIconTask(this.m_iconGetter);
        }
    }

    private native void nativeCancelIconTask(long j);

    private native void nativeClose(long j);

    private native void nativeGetThumbnail(long j, String str);

    private native long nativeInit();

    public String getMediaFilePath() {
        NvsUtils.checkFunctionInMainThread();
        return this.m_mediaFilePath;
    }

    /* access modifiers changed from: protected */
    public void notifyThumbnailArrived(final Bitmap bitmap) {
        if (!this.m_painting) {
            this.m_thumbnail = bitmap;
            invalidate();
            return;
        }
        new Handler().post(new Runnable() {
            public void run() {
                Bitmap unused = NvsThumbnailView.this.m_thumbnail = bitmap;
                NvsThumbnailView.this.invalidate();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        if (!isInEditMode()) {
            this.m_thumbnailView = nativeInit();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        cancelIconTask();
        long j = this.m_thumbnailView;
        if (j != 0) {
            nativeClose(j);
            this.m_thumbnailView = 0;
            this.m_iconGetter = 0;
        }
        this.m_thumbnail = null;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String str = this.m_mediaFilePath;
        if (str != null && !str.isEmpty()) {
            if (this.m_thumbnail != null && !this.m_needUpdate) {
                Rect rect = new Rect();
                getDrawingRect(rect);
                int width = this.m_thumbnail.getWidth();
                int height = this.m_thumbnail.getHeight();
                double d = (double) width;
                double width2 = d / ((double) rect.width());
                double d2 = (double) height;
                double height2 = d2 / ((double) rect.height());
                if (width2 > height2) {
                    double d3 = d / height2;
                    rect.left += (int) ((((double) rect.width()) - d3) / 2.0d);
                    rect.right = (int) (((double) rect.left) + d3);
                } else {
                    double d4 = d2 / width2;
                    rect.top += (int) ((((double) rect.height()) - d4) / 2.0d);
                    rect.bottom = (int) (((double) rect.top) + d4);
                }
                canvas.drawBitmap(this.m_thumbnail, (Rect) null, rect, new Paint(2));
            } else if (this.m_thumbnailView != 0 && this.m_iconGetter != 0) {
                this.m_needUpdate = false;
                this.m_painting = true;
                if (!isInEditMode()) {
                    nativeGetThumbnail(this.m_iconGetter, this.m_mediaFilePath);
                }
                this.m_painting = false;
            }
        }
    }

    public void setMediaFilePath(String str) {
        NvsUtils.checkFunctionInMainThread();
        String str2 = this.m_mediaFilePath;
        if (str2 == null || str == null || !str2.equals(str)) {
            this.m_mediaFilePath = str;
            this.m_needUpdate = true;
            cancelIconTask();
            invalidate();
        }
    }
}
