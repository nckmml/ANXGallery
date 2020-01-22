package com.miui.gallery.xmstreaming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public abstract class XmsMultiThumbnailSequenceView extends HorizontalScrollView {
    private static final String TAG = "XmsMultiThumbnailSequenceView";
    public static final int THUMBNAIL_IMAGE_FILLMODE_ASPECTCROP = 1;
    public static final int THUMBNAIL_IMAGE_FILLMODE_STRETCH = 0;
    private ContentView m_contentView;
    /* access modifiers changed from: private */
    public int m_contentWidth = 0;
    private ArrayList<ThumbnailSequenceDesc> m_descArray;
    private int m_endPadding = 0;
    private long m_maxTimelinePosToScroll = 0;
    private double m_pixelPerMicrosecond = 5.4E-5d;
    Bitmap m_placeholderBitmap;
    private OnScrollChangeListener m_scrollChangeListener;
    private boolean m_scrollEnabled = true;
    private int m_startPadding = 0;
    private float m_thumbnailAspectRatio = 0.5625f;
    private int m_thumbnailImageFillMode = 0;
    private TreeMap<ThumbnailId, Thumbnail> m_thumbnailMap = new TreeMap<>();
    private ArrayList<ThumbnailSequence> m_thumbnailSequenceArray = new ArrayList<>();
    private TreeMap<Integer, ThumbnailSequence> m_thumbnailSequenceMap = new TreeMap<>();
    private int m_thumbnailWidth = 0;
    private boolean m_updatingThumbnail = false;

    private class ContentView extends ViewGroup {
        public ContentView(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            XmsMultiThumbnailSequenceView.this.updateThumbnails();
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            int access$200 = XmsMultiThumbnailSequenceView.this.m_contentWidth;
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (!(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
                size = XmsMultiThumbnailSequenceView.this.getHeight();
            }
            setMeasuredDimension(resolveSizeAndState(Math.max(access$200, getSuggestedMinimumWidth()), i, 0), resolveSizeAndState(Math.max(size, getSuggestedMinimumHeight()), i2, 0));
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            if (i2 != i4) {
                XmsMultiThumbnailSequenceView.this.requestUpdateThumbnailSequenceGeometry();
            }
            super.onSizeChanged(i, i2, i3, i4);
        }

        public boolean shouldDelayChildPressedState() {
            return false;
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(XmsMultiThumbnailSequenceView xmsMultiThumbnailSequenceView, int i, int i2);
    }

    private static class Thumbnail {
        long m_iconTaskId = 0;
        ImageView m_imageView;
        boolean m_imageViewUpToDate = false;
        ThumbnailSequence m_owner;
        long m_timestamp = 0;
        boolean m_touched = false;
    }

    private static class ThumbnailId implements Comparable<ThumbnailId> {
        public int m_seqIndex;
        public long m_timestamp;

        public ThumbnailId(int i, long j) {
            this.m_seqIndex = i;
            this.m_timestamp = j;
        }

        public int compareTo(ThumbnailId thumbnailId) {
            int i = this.m_seqIndex;
            int i2 = thumbnailId.m_seqIndex;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            long j = this.m_timestamp;
            long j2 = thumbnailId.m_timestamp;
            if (j < j2) {
                return -1;
            }
            return j > j2 ? 1 : 0;
        }
    }

    private static class ThumbnailSequence {
        long m_inPoint = 0;
        int m_index = 0;
        String m_mediaFilePath;
        long m_outPoint = 0;
        boolean m_stillImageHint = false;
        long m_trimDuration = 0;
        long m_trimIn = 0;
        int m_width = 0;
        int m_x = 0;

        public long calcTimestampFromX(int i) {
            return this.m_trimIn + ((long) Math.floor(((((double) (i - this.m_x)) / ((double) this.m_width)) * ((double) this.m_trimDuration)) + 0.5d));
        }
    }

    public static class ThumbnailSequenceDesc {
        public long inPoint = 0;
        public String mediaFilePath;
        public long outPoint = 4000000;
        public boolean stillImageHint = false;
        public long trimIn = 0;
        public long trimOut = 4000000;
    }

    public XmsMultiThumbnailSequenceView(Context context) {
        super(context);
        init(context);
    }

    public XmsMultiThumbnailSequenceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public XmsMultiThumbnailSequenceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public XmsMultiThumbnailSequenceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void cancelIconTask() {
        if (!isInEditMode()) {
            nativeCancelIconTask(0);
        }
    }

    private void clearThumbnailSequences() {
        cancelIconTask();
        clearThumbnails();
        this.m_thumbnailSequenceArray.clear();
        this.m_thumbnailSequenceMap.clear();
        this.m_contentWidth = 0;
    }

    private void clearThumbnails() {
        for (Map.Entry<ThumbnailId, Thumbnail> value : this.m_thumbnailMap.entrySet()) {
            this.m_contentView.removeView(((Thumbnail) value.getValue()).m_imageView);
        }
        this.m_thumbnailMap.clear();
    }

    private void init(Context context) {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        this.m_contentView = new ContentView(context);
        addView(this.m_contentView, new FrameLayout.LayoutParams(-2, -1));
    }

    /* access modifiers changed from: private */
    public void requestUpdateThumbnailSequenceGeometry() {
        new Handler().post(new Runnable() {
            public void run() {
                XmsMultiThumbnailSequenceView.this.updateThumbnailSequenceGeometry();
            }
        });
    }

    private boolean setBitmapToImageView(Bitmap bitmap, ImageView imageView) {
        if (bitmap == null || imageView == null) {
            return false;
        }
        Log.d(TAG, "setBitmapToImageView");
        imageView.setImageBitmap(bitmap);
        return true;
    }

    /* access modifiers changed from: private */
    public void updateThumbnailSequenceGeometry() {
        int max;
        cancelIconTask();
        clearThumbnails();
        int height = getHeight();
        if (height != 0) {
            this.m_thumbnailWidth = (int) Math.floor(((double) (((float) height) * this.m_thumbnailAspectRatio)) + 0.5d);
            this.m_thumbnailWidth = Math.max(this.m_thumbnailWidth, 1);
            this.m_thumbnailSequenceMap.clear();
            int i = this.m_startPadding;
            Iterator<ThumbnailSequence> it = this.m_thumbnailSequenceArray.iterator();
            while (it.hasNext()) {
                ThumbnailSequence next = it.next();
                int floor = ((int) Math.floor((((double) next.m_inPoint) * this.m_pixelPerMicrosecond) + 0.5d)) + this.m_startPadding;
                int floor2 = ((int) Math.floor((((double) next.m_outPoint) * this.m_pixelPerMicrosecond) + 0.5d)) + this.m_startPadding;
                if (floor2 > floor) {
                    next.m_x = floor;
                    next.m_width = floor2 - floor;
                    this.m_thumbnailSequenceMap.put(Integer.valueOf(floor), next);
                    i = floor2;
                }
            }
            long j = this.m_maxTimelinePosToScroll;
            if (j <= 0) {
                i += this.m_endPadding;
            } else {
                int floor3 = (int) Math.floor(((double) this.m_startPadding) + (((double) j) * this.m_pixelPerMicrosecond) + 0.5d);
                if (floor3 < i) {
                    i = floor3;
                }
            }
            this.m_contentWidth = i;
            this.m_contentView.layout(0, 0, this.m_contentWidth, getHeight());
            this.m_contentView.requestLayout();
            if (getWidth() + getScrollX() > this.m_contentWidth && (max = Math.max(getScrollX() - ((getWidth() + getScrollX()) - this.m_contentWidth), 0)) != getScrollX()) {
                scrollTo(max, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateThumbnails() {
        Drawable drawable;
        Bitmap bitmap;
        int i;
        boolean z;
        if (this.m_thumbnailSequenceMap.isEmpty()) {
            clearThumbnails();
            return;
        }
        int i2 = this.m_thumbnailWidth;
        int scrollX = getScrollX();
        int width = getWidth();
        int max = Math.max(scrollX - i2, this.m_startPadding);
        int i3 = width + max + i2;
        if (i3 <= max) {
            clearThumbnails();
            return;
        }
        Integer floorKey = this.m_thumbnailSequenceMap.floorKey(Integer.valueOf(max));
        if (floorKey == null) {
            floorKey = this.m_thumbnailSequenceMap.firstKey();
        }
        for (Map.Entry<Integer, ThumbnailSequence> value : this.m_thumbnailSequenceMap.tailMap(floorKey).entrySet()) {
            ThumbnailSequence thumbnailSequence = (ThumbnailSequence) value.getValue();
            if (thumbnailSequence.m_x + thumbnailSequence.m_width >= max) {
                if (thumbnailSequence.m_x >= i3) {
                    break;
                }
                if (thumbnailSequence.m_x < max) {
                    int i4 = thumbnailSequence.m_x;
                    int i5 = this.m_thumbnailWidth;
                    i = i4 + (((max - thumbnailSequence.m_x) / i5) * i5);
                } else {
                    i = thumbnailSequence.m_x;
                }
                int i6 = thumbnailSequence.m_x + thumbnailSequence.m_width;
                while (true) {
                    if (i >= i6) {
                        z = false;
                        break;
                    } else if (i >= i3) {
                        z = true;
                        break;
                    } else {
                        int i7 = this.m_thumbnailWidth;
                        if (i + i7 > i6) {
                            i7 = i6 - i;
                        }
                        long calcTimestampFromX = thumbnailSequence.calcTimestampFromX(i);
                        ThumbnailId thumbnailId = new ThumbnailId(thumbnailSequence.m_index, calcTimestampFromX);
                        Thumbnail thumbnail = this.m_thumbnailMap.get(thumbnailId);
                        if (thumbnail == null) {
                            Thumbnail thumbnail2 = new Thumbnail();
                            thumbnail2.m_owner = thumbnailSequence;
                            thumbnail2.m_timestamp = calcTimestampFromX;
                            thumbnail2.m_imageViewUpToDate = false;
                            thumbnail2.m_touched = true;
                            this.m_thumbnailMap.put(thumbnailId, thumbnail2);
                            thumbnail2.m_imageView = new ImageView(getContext());
                            int i8 = this.m_thumbnailImageFillMode;
                            if (i8 == 0) {
                                thumbnail2.m_imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            } else if (i8 == 1) {
                                thumbnail2.m_imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                            this.m_contentView.addView(thumbnail2.m_imageView);
                            thumbnail2.m_imageView.layout(i, 0, i + i7, this.m_contentView.getHeight());
                        } else {
                            thumbnail.m_touched = true;
                        }
                        i += i7;
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        this.m_updatingThumbnail = true;
        TreeMap treeMap = new TreeMap();
        Iterator<Map.Entry<ThumbnailId, Thumbnail>> it = this.m_thumbnailMap.entrySet().iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Thumbnail thumbnail3 = (Thumbnail) next.getValue();
            if (!(thumbnail3.m_imageView == null || (drawable = thumbnail3.m_imageView.getDrawable()) == null || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null)) {
                this.m_placeholderBitmap = bitmap;
            }
            if (!thumbnail3.m_touched) {
                if (thumbnail3.m_iconTaskId != 0) {
                    nativeCancelIconTask(thumbnail3.m_iconTaskId);
                }
                this.m_contentView.removeView(thumbnail3.m_imageView);
                it.remove();
            } else {
                thumbnail3.m_touched = false;
                if (thumbnail3.m_imageViewUpToDate) {
                    treeMap.put(next.getKey(), ((BitmapDrawable) thumbnail3.m_imageView.getDrawable()).getBitmap());
                } else {
                    long j = thumbnail3.m_owner.m_stillImageHint ? 0 : thumbnail3.m_timestamp;
                    Bitmap nativeGetIconFromCache = nativeGetIconFromCache(thumbnail3.m_owner.m_mediaFilePath, j);
                    if (nativeGetIconFromCache != null) {
                        treeMap.put(next.getKey(), nativeGetIconFromCache);
                        if (setBitmapToImageView(nativeGetIconFromCache, thumbnail3.m_imageView)) {
                            thumbnail3.m_imageViewUpToDate = true;
                            thumbnail3.m_iconTaskId = 0;
                        }
                    } else {
                        thumbnail3.m_iconTaskId = nativeGetIcon(thumbnail3.m_owner.m_mediaFilePath, j);
                        z2 = true;
                    }
                }
            }
        }
        this.m_updatingThumbnail = false;
        if (!z2) {
            return;
        }
        if (!treeMap.isEmpty()) {
            for (Map.Entry next2 : this.m_thumbnailMap.entrySet()) {
                Thumbnail thumbnail4 = (Thumbnail) next2.getValue();
                if (!thumbnail4.m_imageViewUpToDate) {
                    Map.Entry ceilingEntry = treeMap.ceilingEntry(next2.getKey());
                    if (ceilingEntry != null) {
                        setBitmapToImageView((Bitmap) ceilingEntry.getValue(), thumbnail4.m_imageView);
                    } else {
                        setBitmapToImageView((Bitmap) treeMap.lastEntry().getValue(), thumbnail4.m_imageView);
                    }
                }
            }
        } else if (this.m_placeholderBitmap != null) {
            for (Map.Entry<ThumbnailId, Thumbnail> value2 : this.m_thumbnailMap.entrySet()) {
                Thumbnail thumbnail5 = (Thumbnail) value2.getValue();
                if (!thumbnail5.m_imageViewUpToDate) {
                    setBitmapToImageView(this.m_placeholderBitmap, thumbnail5.m_imageView);
                }
            }
        }
    }

    public int getEndPadding() {
        return this.m_endPadding;
    }

    public long getMaxTimelinePosToScroll() {
        return this.m_maxTimelinePosToScroll;
    }

    public OnScrollChangeListener getOnScrollChangeListenser() {
        return this.m_scrollChangeListener;
    }

    public double getPixelPerMicrosecond() {
        return this.m_pixelPerMicrosecond;
    }

    public boolean getScrollEnabled() {
        return this.m_scrollEnabled;
    }

    public int getStartPadding() {
        return this.m_startPadding;
    }

    public float getThumbnailAspectRatio() {
        return this.m_thumbnailAspectRatio;
    }

    public int getThumbnailImageFillMode() {
        return this.m_thumbnailImageFillMode;
    }

    public ArrayList<ThumbnailSequenceDesc> getThumbnailSequenceDescArray() {
        return this.m_descArray;
    }

    public long mapTimelinePosFromX(int i) {
        return (long) Math.floor((((double) ((i + getScrollX()) - this.m_startPadding)) / this.m_pixelPerMicrosecond) + 0.5d);
    }

    public int mapXFromTimelinePos(long j) {
        return (((int) Math.floor((((double) j) * this.m_pixelPerMicrosecond) + 0.5d)) + this.m_startPadding) - getScrollX();
    }

    /* access modifiers changed from: protected */
    public abstract void nativeCancelIconTask(long j);

    /* access modifiers changed from: protected */
    public abstract long nativeGetIcon(String str, long j);

    /* access modifiers changed from: protected */
    public abstract Bitmap nativeGetIconFromCache(String str, long j);

    /* access modifiers changed from: protected */
    public void notifyIconArrived() {
        if (!this.m_updatingThumbnail) {
            updateThumbnails();
        } else {
            new Handler().post(new Runnable() {
                public void run() {
                    XmsMultiThumbnailSequenceView.this.updateThumbnails();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        cancelIconTask();
        this.m_scrollChangeListener = null;
        super.onDetachedFromWindow();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.m_scrollEnabled) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnScrollChangeListener onScrollChangeListener = this.m_scrollChangeListener;
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, i, i3);
        }
        updateThumbnails();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.m_scrollEnabled) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void scaleWithAnchor(double d, int i) {
        if (d > 0.0d) {
            long mapTimelinePosFromX = mapTimelinePosFromX(i);
            this.m_pixelPerMicrosecond *= d;
            updateThumbnailSequenceGeometry();
            scrollTo((getScrollX() + mapXFromTimelinePos(mapTimelinePosFromX)) - i, 0);
        }
    }

    public void setEndPadding(int i) {
        if (i >= 0 && i != this.m_endPadding) {
            this.m_endPadding = i;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setMaxTimelinePosToScroll(int i) {
        long max = (long) Math.max(i, 0);
        if (max != this.m_maxTimelinePosToScroll) {
            this.m_maxTimelinePosToScroll = max;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setOnScrollChangeListenser(OnScrollChangeListener onScrollChangeListener) {
        this.m_scrollChangeListener = onScrollChangeListener;
    }

    public void setPixelPerMicrosecond(double d) {
        if (d > 0.0d && d != this.m_pixelPerMicrosecond) {
            this.m_pixelPerMicrosecond = d;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setScrollEnabled(boolean z) {
        this.m_scrollEnabled = z;
    }

    public void setStartPadding(int i) {
        if (i >= 0 && i != this.m_startPadding) {
            this.m_startPadding = i;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setThumbnailAspectRatio(float f) {
        if (f < 0.1f) {
            f = 0.1f;
        } else if (f > 10.0f) {
            f = 10.0f;
        }
        if (Math.abs(this.m_thumbnailAspectRatio - f) >= 0.001f) {
            this.m_thumbnailAspectRatio = f;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setThumbnailImageFillMode(int i) {
        int i2 = this.m_thumbnailImageFillMode;
        if (!(i2 == 1 || i2 == 0)) {
            this.m_thumbnailImageFillMode = 0;
        }
        if (this.m_thumbnailImageFillMode != i) {
            this.m_thumbnailImageFillMode = i;
            updateThumbnailSequenceGeometry();
        }
    }

    public void setThumbnailSequenceDescArray(ArrayList<ThumbnailSequenceDesc> arrayList) {
        if (arrayList != this.m_descArray) {
            clearThumbnailSequences();
            this.m_descArray = arrayList;
            if (arrayList != null) {
                int i = 0;
                Iterator<ThumbnailSequenceDesc> it = arrayList.iterator();
                long j = 0;
                while (it.hasNext()) {
                    ThumbnailSequenceDesc next = it.next();
                    if (next.mediaFilePath == null || next.inPoint < j || next.outPoint <= next.inPoint || next.trimIn < 0 || next.trimOut <= next.trimIn) {
                        Log.e(TAG, "Invalid ThumbnailSequenceDesc!");
                    } else {
                        ThumbnailSequence thumbnailSequence = new ThumbnailSequence();
                        thumbnailSequence.m_index = i;
                        thumbnailSequence.m_mediaFilePath = next.mediaFilePath;
                        thumbnailSequence.m_inPoint = next.inPoint;
                        thumbnailSequence.m_outPoint = next.outPoint;
                        thumbnailSequence.m_trimIn = next.trimIn;
                        thumbnailSequence.m_trimDuration = next.trimOut - next.trimIn;
                        thumbnailSequence.m_stillImageHint = next.stillImageHint;
                        this.m_thumbnailSequenceArray.add(thumbnailSequence);
                        i++;
                        j = next.outPoint;
                    }
                }
            }
            updateThumbnailSequenceGeometry();
        }
    }
}
