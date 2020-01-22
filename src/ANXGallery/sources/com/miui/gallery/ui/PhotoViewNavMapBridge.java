package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import com.miui.gallery.R;
import com.miui.gallery.widget.MiniNavMap;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewNavMapBridge implements MiniNavMap.OnGestureListener, PhotoViewAttacher.OnMatrixChangedListener, PhotoViewAttacher.OnRotateListener {
    private float mDecelerateFactor = 1.0f;
    private int mLastMoveDirection = 0;
    private int mLongSide;
    private MiniNavMap mNavMap;
    private int mNavMapMarginBottom = -1;
    private PhotoView mPhotoView;
    private float mScaleRatio;
    private RectF mTempRect = new RectF();

    public void connect(PhotoView photoView, MiniNavMap miniNavMap, int i) {
        this.mPhotoView = photoView;
        this.mNavMap = miniNavMap;
        this.mLongSide = i;
        this.mScaleRatio = (((float) this.mNavMap.getPreferredFrameSize()) * 1.0f) / ((float) i);
        this.mPhotoView.addOnMatrixChangeListener(this);
        this.mPhotoView.addOnRotateListener(this);
        this.mNavMap.setOnGestureListener(this);
    }

    public void disconnect() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            photoView.removeOnMatrixChangeListener(this);
            this.mPhotoView.removeOnRotateListener(this);
            this.mPhotoView = null;
        }
        MiniNavMap miniNavMap = this.mNavMap;
        if (miniNavMap != null) {
            miniNavMap.setOnGestureListener((MiniNavMap.OnGestureListener) null);
            this.mNavMap = null;
        }
    }

    public int getNavMapMarginBottom(Context context) {
        if (this.mNavMapMarginBottom < 0) {
            this.mNavMapMarginBottom = context.getResources().getDimensionPixelSize(R.dimen.photo_page_nav_map_margin_bottom);
        }
        return this.mNavMapMarginBottom;
    }

    public void onMatrixChanged(RectF rectF) {
        MiniNavMap miniNavMap;
        if (this.mPhotoView != null && (miniNavMap = this.mNavMap) != null && rectF != null && miniNavMap.getVisibility() == 0) {
            float width = rectF.width();
            float height = rectF.height();
            float f = ((float) this.mLongSide) * this.mScaleRatio;
            float min = (Math.min(width, height) * f) / Math.max(width, height);
            float f2 = width > height ? f : min;
            if (height <= width) {
                f = min;
            }
            if (this.mNavMap.setFrameSize(Math.round(f2), Math.round(f))) {
                this.mPhotoView.setDraggableViewportInsets(new Rect(0, 0, 0, Math.round(f) + (getNavMapMarginBottom(this.mPhotoView.getContext()) * 2)));
            }
            float f3 = f2 / width;
            Rect viewPort = this.mPhotoView.getViewPort();
            this.mTempRect.set(rectF);
            this.mTempRect.intersect((float) viewPort.left, (float) viewPort.top, (float) viewPort.right, (float) viewPort.bottom);
            this.mTempRect.set((-rectF.left) * f3, (-rectF.top) * f3, ((-rectF.left) + this.mTempRect.width()) * f3, ((-rectF.top) + this.mTempRect.height()) * f3);
            this.mNavMap.updateHighlightRect(this.mTempRect);
        }
    }

    public boolean onMove(float f, float f2) {
        if (this.mPhotoView == null || this.mNavMap == null) {
            return false;
        }
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if (abs > abs2) {
            if (this.mLastMoveDirection == -1) {
                if (Float.compare(abs, 1.0f) <= 0 && abs2 > 0.0f) {
                    f = 0.0f;
                }
            } else if (Float.compare(abs2, 1.0f) <= 0 && abs2 > 0.0f) {
                f2 = 0.0f;
            }
            this.mLastMoveDirection = 1;
        } else if (abs2 > abs) {
            if (this.mLastMoveDirection == 1) {
                if (Float.compare(abs2, 1.0f) <= 0 && abs > 0.0f) {
                    f2 = 0.0f;
                }
            } else if (Float.compare(abs, 1.0f) <= 0 && abs > 0.0f) {
                f = 0.0f;
            }
            this.mLastMoveDirection = -1;
        } else {
            this.mLastMoveDirection = 0;
        }
        float f3 = this.mScaleRatio;
        if (f3 > 0.0f) {
            f /= f3;
        }
        float f4 = this.mScaleRatio;
        if (f4 > 0.0f) {
            f2 /= f4;
        }
        PhotoView photoView = this.mPhotoView;
        float f5 = this.mDecelerateFactor;
        return photoView.translateBy(f * f5, f2 * f5);
    }

    public void onMoveDone() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            photoView.requestAlignBounds();
        }
    }

    public void onRotate(float f, float f2, float f3, float f4) {
    }

    public void onRotateBegin(float f) {
    }

    public void onRotateEnd(float f) {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null && this.mNavMap != null) {
            onMatrixChanged(photoView.getDisplayRect());
        }
    }
}
