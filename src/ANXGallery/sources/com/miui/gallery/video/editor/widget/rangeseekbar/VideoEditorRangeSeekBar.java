package com.miui.gallery.video.editor.widget.rangeseekbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.widget.rangeseekbar.drawable.DisabledRangeDrawable;
import com.miui.gallery.video.editor.widget.rangeseekbar.drawable.ThumbDrawable;
import com.miui.gallery.video.editor.widget.rangeseekbar.drawable.VideoThumbnailBackgroundDrawable;

public class VideoEditorRangeSeekBar extends View {
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private float autoMoveSpeed = 0.0f;
    private boolean autoMoving = false;
    private int mAvailableAreaLeft = Reader.READ_DONE;
    private float mAvailableAreaOffset = 0.0f;
    private int mAvailableAreaRight = Reader.READ_DONE;
    private VideoThumbnailBackgroundDrawable mBackgroundDrawable;
    private int mDragSlop;
    private int mDragState = -1;
    private int mEndRange = Reader.READ_DONE;
    private int mHeight;
    /* access modifiers changed from: private */
    public Animator mHideProgressAnimator;
    private Runnable mHideProgressBarAction;
    /* access modifiers changed from: private */
    public boolean mIsShowProgress = true;
    /* access modifiers changed from: private */
    public boolean mIsZooming;
    private float mLastTouchDownX = 0.0f;
    /* access modifiers changed from: private */
    public float mLeftRangeFloat = 0.0f;
    private ThumbDrawable mLeftThumbDrawable;
    private int mLockedAvailableAreaLeft;
    private int mLockedAvailableAreaRight;
    /* access modifiers changed from: private */
    public float mLongPressMoveDistanceFlag;
    private LongPressedRunnable mLongPressedRunnable;
    private int mMax;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;
    private int mProgress;
    private Rect mProgressBounds = new Rect();
    private float mProgressFloat = 0.0f;
    private DisabledRangeDrawable mRangeDrawable;
    /* access modifiers changed from: private */
    public float mRightRangeFloat = 1.0f;
    private ThumbDrawable mRightThumbDrawable;
    /* access modifiers changed from: private */
    public int mScaledTouchSlop;
    /* access modifiers changed from: private */
    public float mStartLongPressMoveDistanceDownXFlag;
    private int mStartRange = 0;
    private boolean mStopSlide = false;
    private int mThumbOffset = 3;
    /* access modifiers changed from: private */
    public int mTotal = Reader.READ_DONE;
    private float mTouchDownX;
    private float mTouchMoveX = 0.0f;
    /* access modifiers changed from: private */
    public int mTouchState = 0;
    private int mVisibleAreaBottom;
    private int mVisibleAreaLeft;
    private int mVisibleAreaRight;
    private int mVisibleAreaTop;
    private int mWidth;
    private ThumbDrawable progressDrawable;

    public interface ISeekbarZooming {
        void onAnimationEnd();
    }

    private class LongPressedRunnable implements Runnable {
        private boolean mIsCanceled;

        private LongPressedRunnable() {
            this.mIsCanceled = false;
        }

        public void cancel() {
            this.mIsCanceled = true;
        }

        public void run() {
            if (this.mIsCanceled) {
                return;
            }
            if ((VideoEditorRangeSeekBar.this.mTouchState == 1 || VideoEditorRangeSeekBar.this.mTouchState == 2) && VideoEditorRangeSeekBar.this.mLongPressMoveDistanceFlag < ((float) VideoEditorRangeSeekBar.this.mScaledTouchSlop)) {
                VideoEditorRangeSeekBar videoEditorRangeSeekBar = VideoEditorRangeSeekBar.this;
                int nearbyThumbId = videoEditorRangeSeekBar.getNearbyThumbId((int) videoEditorRangeSeekBar.mStartLongPressMoveDistanceDownXFlag, VideoEditorRangeSeekBar.this.mLongPressMoveDistanceFlag < 0.0f);
                if (nearbyThumbId == 1) {
                    VideoEditorRangeSeekBar videoEditorRangeSeekBar2 = VideoEditorRangeSeekBar.this;
                    videoEditorRangeSeekBar2.scaleTo(5.0f, videoEditorRangeSeekBar2.mLeftRangeFloat, false);
                } else if (nearbyThumbId == 2) {
                    VideoEditorRangeSeekBar videoEditorRangeSeekBar3 = VideoEditorRangeSeekBar.this;
                    videoEditorRangeSeekBar3.scaleTo(5.0f, videoEditorRangeSeekBar3.mRightRangeFloat, false);
                }
            }
        }
    }

    public interface OnSeekBarChangeListener {
        void onProgressChanged(VideoEditorRangeSeekBar videoEditorRangeSeekBar, int i, int i2, boolean z);

        void onProgressPreview(VideoEditorRangeSeekBar videoEditorRangeSeekBar, int i, int i2, boolean z);

        void onStartTrackingTouch(VideoEditorRangeSeekBar videoEditorRangeSeekBar, int i, int i2);
    }

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public int availableAreaLeft;
        /* access modifiers changed from: private */
        public int availableAreaRight;
        public int endRange = Reader.READ_DONE;
        /* access modifiers changed from: private */
        public float leftRangeFloat;
        /* access modifiers changed from: private */
        public int lockedAvailableAreaLeft;
        /* access modifiers changed from: private */
        public int lockedAvailableAreaRight;
        public int max;
        public int progress;
        /* access modifiers changed from: private */
        public float progressFloat;
        /* access modifiers changed from: private */
        public float rightRangeFloat;
        public int startRange = 0;
        private Parcelable superState;
        public int total = Reader.READ_DONE;

        SavedState(Parcel parcel) {
            this.superState = parcel.readParcelable(getClass().getClassLoader());
            this.total = parcel.readInt();
            this.startRange = parcel.readInt();
            this.endRange = parcel.readInt();
            this.max = parcel.readInt();
            this.progress = parcel.readInt();
            this.availableAreaLeft = parcel.readInt();
            this.availableAreaRight = parcel.readInt();
            this.lockedAvailableAreaLeft = parcel.readInt();
            this.lockedAvailableAreaRight = parcel.readInt();
            this.leftRangeFloat = parcel.readFloat();
            this.rightRangeFloat = parcel.readFloat();
            this.progressFloat = parcel.readFloat();
        }

        SavedState(Parcelable parcelable) {
            this.superState = parcelable;
        }

        public int describeContents() {
            return 0;
        }

        public Parcelable getSuperState() {
            return this.superState;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.superState, i);
            parcel.writeInt(this.total);
            parcel.writeInt(this.startRange);
            parcel.writeInt(this.endRange);
            parcel.writeInt(this.max);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.availableAreaLeft);
            parcel.writeInt(this.availableAreaRight);
            parcel.writeInt(this.lockedAvailableAreaLeft);
            parcel.writeInt(this.lockedAvailableAreaRight);
            parcel.writeFloat(this.leftRangeFloat);
            parcel.writeFloat(this.rightRangeFloat);
            parcel.writeFloat(this.progressFloat);
        }
    }

    private class VideoEditorRangeSeekBarBitmapProviderWrapper implements VideoThumbnailBackgroundDrawable.BitmapProvider {
        private VideoThumbnailBackgroundDrawable.BitmapProvider mWrapped;

        public VideoEditorRangeSeekBarBitmapProviderWrapper(VideoThumbnailBackgroundDrawable.BitmapProvider bitmapProvider) {
            this.mWrapped = bitmapProvider;
        }

        public Bitmap getBitmap(int i, int i2) {
            return this.mWrapped.getBitmap((int) (((float) VideoEditorRangeSeekBar.this.mTotal) * (((float) i) / ((float) i2))), VideoEditorRangeSeekBar.this.mTotal);
        }
    }

    public VideoEditorRangeSeekBar(Context context) {
        super(context);
        init();
    }

    public VideoEditorRangeSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public VideoEditorRangeSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void adjustThumb() {
        int i = this.mLockedAvailableAreaLeft;
        int i2 = this.mLockedAvailableAreaRight;
        float f = ((float) i) + (((float) (i2 - i)) * this.mLeftRangeFloat);
        float f2 = ((float) i) + (((float) (i2 - i)) * this.mRightRangeFloat);
        if (f >= ((float) this.mVisibleAreaLeft) && f2 <= ((float) this.mVisibleAreaRight)) {
            clearAvailableAreaOffset();
            zoomAvailableAreaTo(this.mLockedAvailableAreaLeft, this.mLockedAvailableAreaRight, true, (ISeekbarZooming) null);
        } else if (f2 - f < ((float) getVisibleAreaWidth())) {
            int i3 = this.mVisibleAreaRight;
            if (f2 > ((float) i3)) {
                float f3 = ((float) i3) - f2;
                int i4 = (int) ((f2 + f3) - 0.5f);
                if (((int) ((f + f3) - 0.5f)) >= this.mVisibleAreaLeft && i4 <= i3) {
                    clearAvailableAreaOffset();
                    zoomAvailableAreaTo((int) (((float) this.mLockedAvailableAreaLeft) + f3), (int) (((float) this.mLockedAvailableAreaRight) + f3), true, (ISeekbarZooming) null);
                    return;
                }
                return;
            }
            int i5 = this.mVisibleAreaLeft;
            if (f < ((float) i5)) {
                float f4 = ((float) i5) - f;
                int i6 = (int) (f2 + f4 + 0.5f);
                if (((int) (f + f4 + 0.5f)) >= i5 && i6 <= i3) {
                    clearAvailableAreaOffset();
                    zoomAvailableAreaTo((int) (((float) this.mLockedAvailableAreaLeft) + f4), (int) (((float) this.mLockedAvailableAreaRight) + f4), true, (ISeekbarZooming) null);
                    return;
                }
                return;
            }
            clearAvailableAreaOffset();
            lockFloatRangeTo(this.mLeftRangeFloat, this.mRightRangeFloat, (ISeekbarZooming) null);
        } else {
            clearAvailableAreaOffset();
            lockFloatRangeTo(this.mLeftRangeFloat, this.mRightRangeFloat, (ISeekbarZooming) null);
        }
    }

    private void autoMove() {
        if (this.autoMoving) {
            this.mAvailableAreaOffset += this.autoMoveSpeed;
            int i = this.mAvailableAreaLeft;
            float f = ((float) i) + this.mAvailableAreaOffset;
            int i2 = this.mVisibleAreaLeft;
            if (f > ((float) i2)) {
                this.mAvailableAreaOffset = (float) (i2 - i);
            }
            int i3 = this.mAvailableAreaRight;
            float f2 = ((float) i3) + this.mAvailableAreaOffset;
            int i4 = this.mVisibleAreaRight;
            if (f2 < ((float) i4)) {
                this.mAvailableAreaOffset = (float) (i4 - i3);
            }
            trackTouchEvent(this.mLastTouchDownX);
            invalidate();
        }
    }

    private boolean canSlip() {
        return this.mProgress > this.mStartRange + (this.progressDrawable.getIntrinsicWidth() / 2) && this.mProgress < this.mEndRange + (this.progressDrawable.getIntrinsicWidth() / 2);
    }

    private void clearAvailableAreaOffset() {
        float f = this.mAvailableAreaOffset;
        this.mAvailableAreaLeft = (int) (((float) this.mAvailableAreaLeft) + f);
        this.mAvailableAreaRight = (int) (((float) this.mAvailableAreaRight) + f);
        this.mAvailableAreaOffset = 0.0f;
    }

    private void init() {
        this.mLeftThumbDrawable = new ThumbDrawable(getResources().getDrawable(R.drawable.video_editor_handle_left));
        this.mRightThumbDrawable = new ThumbDrawable(getResources().getDrawable(R.drawable.video_editor_handle_right));
        this.progressDrawable = new ThumbDrawable(getResources().getDrawable(R.drawable.video_editor_seek_progress));
        this.mBackgroundDrawable = new VideoThumbnailBackgroundDrawable();
        this.mBackgroundDrawable.setCLayoutDirection(getLayoutDirection() == 0 ? 0 : 1);
        this.mRangeDrawable = new DisabledRangeDrawable(new ColorDrawable(getResources().getColor(R.color.video_editor_trim_mask_bg)));
        this.mRightThumbDrawable.setCallback(this);
        this.mLeftThumbDrawable.setCallback(this);
        this.mBackgroundDrawable.setCallback(this);
        this.mRangeDrawable.setCallback(this);
        this.mDragSlop = (int) (((double) this.progressDrawable.getIntrinsicWidth()) * 2.5d);
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.video_editor_video_trim_seek_bar_content_padding_top);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.video_editor_video_trim_progress_bar_content_padding_top);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.video_editor_video_trim_progress_bar_content_padding_bottom);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.video_editor_video_trim_seek_bar_content_padding_bottom);
        this.mBackgroundDrawable.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize4);
        this.mRangeDrawable.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize4);
        this.progressDrawable.setPadding(0, dimensionPixelSize2, 0, dimensionPixelSize3);
        int dimensionPixelSize5 = getResources().getDimensionPixelSize(R.dimen.video_editor_video_trim_seek_bar_thumb_bar_padding_bottom);
        this.mLeftThumbDrawable.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize5);
        this.mRightThumbDrawable.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize5);
    }

    private void lockFloatRangeTo(float f, float f2, ISeekbarZooming iSeekbarZooming) {
        int visibleAreaWidth = getVisibleAreaWidth();
        int i = this.mVisibleAreaLeft;
        float f3 = (float) visibleAreaWidth;
        float f4 = ((float) i) + (f * f3);
        float f5 = ((float) i) + (f2 * f3);
        float f6 = f3 / (f5 - f4);
        float f7 = ((float) i) - ((f4 - ((float) i)) * f6);
        int i2 = this.mVisibleAreaRight;
        zoomAvailableAreaTo((int) f7, (int) (((float) i2) + ((((float) i2) - f5) * f6)), true, iSeekbarZooming);
    }

    private void onStartTrackingTouch() {
        if (this.mOnSeekBarChangeListener != null) {
            int i = 1;
            if (getLayoutDirection() == 0) {
                OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
                if (this.mDragState == 0) {
                    i = 0;
                }
                onSeekBarChangeListener.onStartTrackingTouch(this, i, this.mDragState == 0 ? this.mStartRange : this.mEndRange);
                return;
            }
            OnSeekBarChangeListener onSeekBarChangeListener2 = this.mOnSeekBarChangeListener;
            if (this.mDragState != 0) {
                i = 0;
            }
            onSeekBarChangeListener2.onStartTrackingTouch(this, i, this.mDragState == 0 ? this.mEndRange : this.mStartRange);
        }
    }

    private void onStopTrackingTouch() {
        if (this.mOnSeekBarChangeListener != null) {
            int i = 1;
            if (getLayoutDirection() == 0) {
                OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
                if (this.mDragState == 0) {
                    i = 0;
                }
                onSeekBarChangeListener.onStartTrackingTouch(this, i, this.mDragState == 0 ? this.mStartRange : this.mEndRange);
                return;
            }
            OnSeekBarChangeListener onSeekBarChangeListener2 = this.mOnSeekBarChangeListener;
            if (this.mDragState != 0) {
                i = 0;
            }
            onSeekBarChangeListener2.onStartTrackingTouch(this, i, this.mDragState == 0 ? this.mEndRange : this.mStartRange);
        }
    }

    private void recoverToLockedArea() {
        if (this.mAvailableAreaLeft != this.mLockedAvailableAreaLeft || this.mAvailableAreaRight != this.mLockedAvailableAreaRight || Float.compare(this.mAvailableAreaOffset, 0.0f) != 0) {
            if (Float.compare(this.mAvailableAreaOffset, 0.0f) != 0) {
                adjustThumb();
            } else {
                zoomAvailableAreaTo(this.mLockedAvailableAreaLeft, this.mLockedAvailableAreaRight, true, (ISeekbarZooming) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scaleTo(float f, float f2, boolean z) {
        float availableWidth = ((float) getAvailableWidth()) * (f - 1.0f);
        zoomAvailableAreaTo((int) (((float) this.mAvailableAreaLeft) - (availableWidth * f2)), (int) (((float) this.mAvailableAreaRight) + (availableWidth * (1.0f - f2))), z, (ISeekbarZooming) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c  */
    private void trackTouchEvent(float f) {
        int i;
        this.mLastTouchDownX = f;
        int i2 = this.mVisibleAreaLeft;
        if (f < ((float) i2)) {
            this.autoMoveSpeed = (((float) i2) - f) * 0.2f;
        } else {
            i2 = this.mVisibleAreaRight;
            if (f > ((float) i2)) {
                this.autoMoveSpeed = (((float) i2) - f) * 0.2f;
            } else {
                this.autoMoveSpeed = 0.0f;
                float availableWidth = ((f - ((float) this.mAvailableAreaLeft)) - this.mAvailableAreaOffset) / ((float) getAvailableWidth());
                i = this.mDragState;
                if (i != 0) {
                    float f2 = this.mRightRangeFloat;
                    if (availableWidth > f2) {
                        this.mLeftRangeFloat = f2;
                    } else {
                        this.mLeftRangeFloat = availableWidth;
                    }
                } else if (i == 1) {
                    float f3 = this.mLeftRangeFloat;
                    if (availableWidth < f3) {
                        this.mRightRangeFloat = f3;
                    } else {
                        this.mRightRangeFloat = availableWidth;
                    }
                } else if (i == 2) {
                    this.mProgressFloat = availableWidth;
                }
                updateRangeValue();
                int i3 = 0;
                if (Float.compare(this.autoMoveSpeed, 0.0f) != 0) {
                    this.autoMoving = false;
                } else if ((this.autoMoveSpeed > 0.0f && this.mAvailableAreaLeft < this.mVisibleAreaLeft) || (this.autoMoveSpeed < 0.0f && this.mAvailableAreaRight > this.mVisibleAreaRight)) {
                    if (this.mLeftRangeFloat < this.mRightRangeFloat) {
                        this.autoMoving = true;
                    } else {
                        this.autoMoving = false;
                    }
                }
                if (this.mDragState != 2) {
                    if (canSlip()) {
                        this.mOnSeekBarChangeListener.onProgressPreview(this, -1, this.mProgress, true);
                    }
                } else if (getLayoutDirection() == 0) {
                    OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
                    if (this.mDragState != 0) {
                        i3 = 1;
                    }
                    onSeekBarChangeListener.onProgressChanged(this, i3, this.mDragState == 0 ? this.mStartRange : this.mEndRange, true);
                } else {
                    OnSeekBarChangeListener onSeekBarChangeListener2 = this.mOnSeekBarChangeListener;
                    if (this.mDragState == 0) {
                        i3 = 1;
                    }
                    onSeekBarChangeListener2.onProgressChanged(this, i3, this.mDragState == 0 ? this.mEndRange : this.mStartRange, true);
                }
                updateState();
            }
        }
        f = (float) i2;
        float availableWidth2 = ((f - ((float) this.mAvailableAreaLeft)) - this.mAvailableAreaOffset) / ((float) getAvailableWidth());
        i = this.mDragState;
        if (i != 0) {
        }
        updateRangeValue();
        int i32 = 0;
        if (Float.compare(this.autoMoveSpeed, 0.0f) != 0) {
        }
        if (this.mDragState != 2) {
        }
        updateState();
    }

    private void updateRangeValue() {
        if (this.mDragState == 2) {
            this.mProgress = (int) ((((float) this.mTotal) * this.mProgressFloat) + 0.5f);
        } else if (getLayoutDirection() == 0) {
            int i = this.mTotal;
            this.mStartRange = (int) ((((float) i) * this.mLeftRangeFloat) + 0.5f);
            this.mEndRange = (int) ((((float) i) * this.mRightRangeFloat) + 0.5f);
        } else {
            int i2 = this.mTotal;
            this.mStartRange = (int) ((((float) i2) * (1.0f - this.mRightRangeFloat)) + 0.5f);
            this.mEndRange = (int) ((((float) i2) * (1.0f - this.mLeftRangeFloat)) + 0.5f);
        }
    }

    private void updateState() {
        int availableWidth = getAvailableWidth();
        float f = this.mAvailableAreaOffset;
        int i = (int) (((float) this.mAvailableAreaRight) + f);
        this.mProgressBounds.set((int) (((float) this.mAvailableAreaLeft) + f), this.mVisibleAreaTop, i, this.mVisibleAreaBottom);
        this.mBackgroundDrawable.setBounds(this.mProgressBounds);
        this.mRangeDrawable.setBounds(this.mProgressBounds);
        this.mBackgroundDrawable.setBounds(this.mProgressBounds);
        float f2 = this.mAvailableAreaOffset;
        float f3 = (float) availableWidth;
        int i2 = this.mAvailableAreaLeft;
        int i3 = (int) ((this.mLeftRangeFloat * f3) + ((float) i2) + 0.5f + f2);
        int i4 = (int) ((this.mRightRangeFloat * f3) + ((float) i2) + 0.5f + f2);
        if (this.mDragState == 2 && this.mTouchState == 2) {
            int i5 = (int) (f2 + (f3 * this.mProgressFloat) + ((float) i2) + 0.5f);
            int intrinsicWidth = ((int) (((float) this.progressDrawable.getIntrinsicWidth()) * 0.9f)) + i3;
            int intrinsicWidth2 = ((int) (((float) this.progressDrawable.getIntrinsicWidth()) * 0.1f)) + i4;
            if (i5 <= intrinsicWidth) {
                i5 = intrinsicWidth;
            } else if (i5 >= intrinsicWidth2) {
                i5 = intrinsicWidth2;
            }
            this.progressDrawable.moveProgressThumb(i5, this.mHeight / 2);
        } else {
            this.progressDrawable.moveTo(((int) (this.mAvailableAreaOffset + ((float) i3) + (((float) (i4 - i3)) * this.mProgressFloat) + 0.5f)) + ((int) (((float) this.progressDrawable.getIntrinsicWidth()) * 0.4f)), this.mHeight / 2);
        }
        this.mLeftThumbDrawable.moveLeftThumb(i3, this.mHeight / 2);
        this.mRightThumbDrawable.moveRightThumb(i4, this.mHeight / 2);
        this.mRangeDrawable.setStartRangeScale(this.mLeftRangeFloat);
        this.mRangeDrawable.setEndRangeScale(this.mRightRangeFloat);
        invalidate();
    }

    private void zoomAvailableAreaTo(int i, int i2, boolean z, final ISeekbarZooming iSeekbarZooming) {
        if (i2 - i < this.mTotal) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofInt(this, "AvailableAreaLeft", new int[]{this.mAvailableAreaLeft, i}), ObjectAnimator.ofInt(this, "AvailableAreaRight", new int[]{this.mAvailableAreaRight, i2})});
            animatorSet.start();
            this.mIsZooming = true;
            animatorSet.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    ISeekbarZooming iSeekbarZooming = iSeekbarZooming;
                    if (iSeekbarZooming != null) {
                        iSeekbarZooming.onAnimationEnd();
                    }
                    boolean unused = VideoEditorRangeSeekBar.this.mIsZooming = false;
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            });
            if (z) {
                this.mLockedAvailableAreaLeft = i;
                this.mLockedAvailableAreaRight = i2;
            }
        }
    }

    public int getAvailableHeight() {
        return (this.mHeight - this.mBackgroundDrawable.getPaddingTop()) - this.mBackgroundDrawable.getPaddingBottom();
    }

    public int getAvailableWidth() {
        return this.mAvailableAreaRight - this.mAvailableAreaLeft;
    }

    public int getEndRange() {
        return this.mEndRange;
    }

    public int getNearbyThumbId(int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        int abs = Math.abs(this.mLeftThumbDrawable.getLocationX() - i);
        int abs2 = Math.abs(this.mRightThumbDrawable.getLocationX() - i);
        int abs3 = Math.abs(this.progressDrawable.getLocationX() - i);
        int abs4 = Math.abs(this.mLeftThumbDrawable.getLocationX() - this.progressDrawable.getLocationX());
        int abs5 = Math.abs(this.mRightThumbDrawable.getLocationX() - this.progressDrawable.getLocationX());
        boolean z2 = abs4 <= this.mDragSlop;
        boolean z3 = abs5 <= this.mDragSlop;
        if (z) {
            if (z2 && (abs < (i5 = this.mDragSlop) || abs3 < i5)) {
                return 1;
            }
            if (z3 && this.mIsShowProgress && (abs2 < (i4 = this.mDragSlop) || abs3 < i4)) {
                return 3;
            }
            if (this.mIsShowProgress && abs3 < this.mDragSlop) {
                return 3;
            }
            int i6 = this.mDragSlop;
            if (abs < i6) {
                return 1;
            }
            if (abs2 < i6) {
                return 2;
            }
        } else if (z3 && (abs2 < (i3 = this.mDragSlop) || abs3 < i3)) {
            return 2;
        } else {
            if (z2 && this.mIsShowProgress && (abs < (i2 = this.mDragSlop) || abs3 < i2)) {
                return 3;
            }
            if (this.mIsShowProgress && abs3 < this.mDragSlop) {
                return 3;
            }
            int i7 = this.mDragSlop;
            if (abs2 < i7) {
                return 2;
            }
            if (abs < i7) {
                return 1;
            }
        }
        return 0;
    }

    public int getStartRange() {
        return this.mStartRange;
    }

    public int getVisibleAreaWidth() {
        return this.mVisibleAreaRight - this.mVisibleAreaLeft;
    }

    public void hideProgressBar() {
        if (this.mIsShowProgress) {
            hideProgressBar(false);
        }
    }

    public void hideProgressBar(boolean z) {
        if (z) {
            this.mHideProgressAnimator = ObjectAnimator.ofInt(this, "ProgressBarAlpha", new int[]{255, 0}).setDuration(1000);
            this.mHideProgressAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    boolean unused = VideoEditorRangeSeekBar.this.mIsShowProgress = false;
                    Animator unused2 = VideoEditorRangeSeekBar.this.mHideProgressAnimator = null;
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            });
            this.mHideProgressAnimator.start();
            return;
        }
        this.mIsShowProgress = false;
        setProgressBarAlpha(0);
    }

    public boolean isTouching() {
        return this.mTouchState != 0;
    }

    public boolean isZooming() {
        return this.mIsZooming;
    }

    public void lockRangeTo(int i, int i2, ISeekbarZooming iSeekbarZooming) {
        if (i2 <= this.mTotal && i < i2 && i2 - i > this.mWidth) {
            if (getLayoutDirection() == 0) {
                int i3 = this.mTotal;
                lockFloatRangeTo(((float) i) / ((float) i3), ((float) i2) / ((float) i3), iSeekbarZooming);
                return;
            }
            int i4 = this.mTotal;
            lockFloatRangeTo(1.0f - (((float) i2) / ((float) i4)), 1.0f - (((float) i) / ((float) i4)), iSeekbarZooming);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mBackgroundDrawable.draw(canvas);
        this.mRangeDrawable.draw(canvas);
        this.mLeftThumbDrawable.draw(canvas);
        this.mRightThumbDrawable.draw(canvas);
        if (this.mIsShowProgress) {
            this.progressDrawable.draw(canvas);
        }
        if (this.autoMoving) {
            autoMove();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.mWidth = i3 - i;
            this.mHeight = i4 - i2;
            ThumbDrawable thumbDrawable = this.mLeftThumbDrawable;
            thumbDrawable.setSize(thumbDrawable.getIntrinsicWidth(), this.mHeight);
            ThumbDrawable thumbDrawable2 = this.mRightThumbDrawable;
            thumbDrawable2.setSize(thumbDrawable2.getIntrinsicWidth(), this.mHeight);
            ThumbDrawable thumbDrawable3 = this.progressDrawable;
            thumbDrawable3.setSize(thumbDrawable3.getIntrinsicWidth(), this.mHeight);
            this.mVisibleAreaLeft = this.mLeftThumbDrawable.getPaddingLeft() + getPaddingLeft();
            this.mVisibleAreaRight = (this.mWidth - this.mRightThumbDrawable.getPaddingRight()) - getPaddingRight();
            this.mVisibleAreaTop = getPaddingTop();
            this.mVisibleAreaBottom = this.mHeight - getPaddingBottom();
            this.mBackgroundDrawable.setDrawingArea(this.mVisibleAreaLeft, this.mVisibleAreaRight);
            DisabledRangeDrawable disabledRangeDrawable = this.mRangeDrawable;
            int i5 = this.mVisibleAreaLeft;
            int i6 = this.mThumbOffset;
            disabledRangeDrawable.setDrawingArea(i5 - i6, this.mVisibleAreaRight + i6);
            ThumbDrawable thumbDrawable4 = this.mLeftThumbDrawable;
            int i7 = this.mVisibleAreaLeft;
            int i8 = this.mThumbOffset;
            thumbDrawable4.setDrawingArea(i7 - i8, this.mVisibleAreaRight + i8);
            ThumbDrawable thumbDrawable5 = this.mRightThumbDrawable;
            int i9 = this.mVisibleAreaLeft;
            int i10 = this.mThumbOffset;
            thumbDrawable5.setDrawingArea(i9 - i10, this.mVisibleAreaRight + i10);
            ThumbDrawable thumbDrawable6 = this.progressDrawable;
            int i11 = this.mVisibleAreaLeft;
            int i12 = this.mThumbOffset;
            thumbDrawable6.setDrawingArea(i11 - i12, this.mVisibleAreaRight + i12);
            if (this.mAvailableAreaLeft == Integer.MAX_VALUE) {
                this.mAvailableAreaLeft = this.mVisibleAreaLeft;
                this.mLockedAvailableAreaLeft = this.mAvailableAreaLeft;
            }
            if (this.mAvailableAreaRight == Integer.MAX_VALUE) {
                this.mAvailableAreaRight = this.mVisibleAreaRight;
                this.mLockedAvailableAreaRight = this.mAvailableAreaRight;
            }
            updateState();
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && (parcelable instanceof SavedState)) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.mTotal = savedState.total;
            this.mStartRange = savedState.startRange;
            this.mEndRange = savedState.endRange;
            this.mMax = savedState.max;
            this.mProgress = savedState.progress;
            this.mAvailableAreaLeft = savedState.availableAreaLeft;
            this.mAvailableAreaRight = savedState.availableAreaRight;
            this.mLockedAvailableAreaLeft = savedState.lockedAvailableAreaLeft;
            this.mLockedAvailableAreaRight = savedState.lockedAvailableAreaRight;
            this.mRightRangeFloat = savedState.rightRangeFloat;
            this.mLeftRangeFloat = savedState.leftRangeFloat;
            this.mProgressFloat = savedState.progressFloat;
        }
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        this.mBackgroundDrawable.setCLayoutDirection(i == 0 ? 0 : 1);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.total = this.mTotal;
        savedState.startRange = this.mStartRange;
        savedState.endRange = this.mEndRange;
        savedState.max = this.mMax;
        savedState.progress = this.mProgress;
        int unused = savedState.availableAreaLeft = this.mAvailableAreaLeft;
        int unused2 = savedState.availableAreaRight = this.mAvailableAreaRight;
        int unused3 = savedState.lockedAvailableAreaLeft = this.mLockedAvailableAreaLeft;
        int unused4 = savedState.lockedAvailableAreaRight = this.mLockedAvailableAreaRight;
        float unused5 = savedState.rightRangeFloat = this.mRightRangeFloat;
        float unused6 = savedState.leftRangeFloat = this.mLeftRangeFloat;
        float unused7 = savedState.progressFloat = this.mProgressFloat;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mTouchDownX = motionEvent.getX();
            this.mDragState = -1;
            this.mTouchState = 1;
            this.mTouchMoveX = 0.0f;
            this.mLongPressMoveDistanceFlag = 0.0f;
            this.mStartLongPressMoveDistanceDownXFlag = motionEvent.getX();
            this.mLongPressedRunnable = new LongPressedRunnable();
            postDelayed(this.mLongPressedRunnable, (long) LONGPRESS_TIMEOUT);
        } else if (action == 1) {
            this.mTouchState = 0;
            LongPressedRunnable longPressedRunnable = this.mLongPressedRunnable;
            if (longPressedRunnable != null) {
                longPressedRunnable.cancel();
                this.mLongPressedRunnable = null;
            }
            if (this.mDragState != -1) {
                onStopTrackingTouch();
                this.mDragState = -1;
                this.autoMoving = false;
            }
            recoverToLockedArea();
        } else if (action == 2) {
            this.mTouchState = 2;
            this.mLongPressMoveDistanceFlag = motionEvent.getX() - this.mStartLongPressMoveDistanceDownXFlag;
            this.mTouchMoveX = motionEvent.getX() - this.mTouchDownX;
            if (this.mAvailableAreaLeft == this.mLockedAvailableAreaLeft || this.mAvailableAreaRight == this.mLockedAvailableAreaRight) {
                if (this.autoMoving || Math.abs(this.mLongPressMoveDistanceFlag) >= ((float) (this.mScaledTouchSlop / 2))) {
                    LongPressedRunnable longPressedRunnable2 = this.mLongPressedRunnable;
                    if (longPressedRunnable2 != null) {
                        longPressedRunnable2.cancel();
                        this.mLongPressedRunnable = null;
                    }
                    this.mLongPressMoveDistanceFlag = 0.0f;
                    this.mStartLongPressMoveDistanceDownXFlag = motionEvent.getX();
                } else if (this.mLongPressedRunnable == null) {
                    this.mLongPressedRunnable = new LongPressedRunnable();
                    postDelayed(this.mLongPressedRunnable, (long) LONGPRESS_TIMEOUT);
                }
            }
            int i = this.mDragState;
            if (i == -1) {
                this.mTouchMoveX = motionEvent.getX() - this.mTouchDownX;
                if (Math.abs(this.mTouchMoveX) > ((float) this.mScaledTouchSlop)) {
                    int nearbyThumbId = getNearbyThumbId((int) this.mTouchDownX, this.mTouchMoveX < 0.0f);
                    if (nearbyThumbId == 1) {
                        this.mDragState = 0;
                    } else if (nearbyThumbId == 2) {
                        this.mDragState = 1;
                    } else if (nearbyThumbId != 3) {
                        this.mDragState = -1;
                    } else {
                        this.mDragState = 2;
                    }
                    if (this.mDragState != -1) {
                        onStartTrackingTouch();
                    }
                }
            } else if (!this.mStopSlide) {
                trackTouchEvent(motionEvent.getX());
            } else if (i == 0 && this.mTouchMoveX < 0.0f) {
                trackTouchEvent(motionEvent.getX());
            } else if (this.mDragState == 1 && this.mTouchMoveX > 0.0f) {
                trackTouchEvent(motionEvent.getX());
            }
        }
        return true;
    }

    public void setAvailableAreaLeft(int i) {
        this.mAvailableAreaLeft = i;
        updateState();
    }

    public void setAvailableAreaRight(int i) {
        this.mAvailableAreaRight = i;
        updateState();
    }

    public void setBitmapProvider(VideoThumbnailBackgroundDrawable.BitmapProvider bitmapProvider) {
        this.mBackgroundDrawable.setmBitmapProvider(new VideoEditorRangeSeekBarBitmapProviderWrapper(bitmapProvider));
    }

    public void setEndRange(int i) {
        int i2 = this.mTotal;
        if (i > i2) {
            this.mEndRange = i2;
        } else {
            int i3 = this.mStartRange;
            if (i < i3) {
                this.mEndRange = i3;
            } else {
                this.mEndRange = i;
            }
        }
        if (getLayoutDirection() == 0) {
            this.mRightRangeFloat = ((float) this.mEndRange) / ((float) this.mTotal);
        } else {
            this.mLeftRangeFloat = 1.0f - (((float) this.mEndRange) / ((float) this.mTotal));
        }
        updateState();
    }

    public void setMax(int i) {
        int i2 = this.mMax;
        this.mProgress = (int) (((float) i2) * (((float) this.mProgress) / ((float) i2)));
        this.mMax = i;
        if (getLayoutDirection() == 0) {
            this.mProgressFloat = ((float) this.mProgress) / ((float) this.mMax);
        } else {
            this.mProgressFloat = 1.0f - (((float) this.mProgress) / ((float) this.mMax));
        }
        updateState();
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public void setProgress(int i) {
        if (i < 0) {
            this.mProgress = 0;
        } else {
            int i2 = this.mMax;
            if (i > i2) {
                this.mProgress = i2;
            } else {
                this.mProgress = i;
            }
        }
        if (getLayoutDirection() == 0) {
            this.mProgressFloat = ((float) this.mProgress) / ((float) this.mMax);
        } else {
            this.mProgressFloat = 1.0f - (((float) this.mProgress) / ((float) this.mMax));
        }
        updateState();
    }

    public void setProgressBarAlpha(int i) {
        this.progressDrawable.setAlpha(i);
        invalidate();
    }

    public void setStartRange(int i) {
        if (i < 0) {
            this.mStartRange = 0;
        } else {
            int i2 = this.mEndRange;
            if (i > i2) {
                this.mStartRange = i2;
            } else {
                this.mStartRange = i;
            }
        }
        if (getLayoutDirection() == 0) {
            this.mLeftRangeFloat = ((float) this.mStartRange) / ((float) this.mTotal);
        } else {
            this.mRightRangeFloat = 1.0f - (((float) this.mStartRange) / ((float) this.mTotal));
        }
        updateState();
    }

    public void setStopSlide(boolean z) {
        this.mStopSlide = z;
    }

    public void setThumbnailAspectRatio(float f) {
        this.mBackgroundDrawable.setAspectRatio(f);
    }

    public void setTotal(int i) {
        this.mTotal = i;
        updateRangeValue();
    }

    public void showProgressBar() {
        if (!this.mIsShowProgress) {
            Runnable runnable = this.mHideProgressBarAction;
            if (runnable != null) {
                removeCallbacks(runnable);
                this.mHideProgressBarAction = null;
            }
            Animator animator = this.mHideProgressAnimator;
            if (animator != null) {
                animator.cancel();
                this.mHideProgressAnimator = null;
            }
            this.mIsShowProgress = true;
            setProgressBarAlpha(255);
        }
    }
}
