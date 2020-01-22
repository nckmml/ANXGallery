package com.miui.gallery.widget.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;

class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int[] EMPTY_STATE_SET = new int[0];
    private static final int[] PRESSED_STATE_SET = {16842919};
    /* access modifiers changed from: private */
    public int mAnimTranslateX = 0;
    /* access modifiers changed from: private */
    public int mAnimTranslateY = 0;
    /* access modifiers changed from: private */
    public int mAnimationState = 0;
    private int mDefaultThumbHeight;
    private int mDefaultThumbWidth;
    private int mDragState = 0;
    private final Runnable mHideRunnable = new Runnable() {
        public void run() {
            FastScroller.this.hide(300);
        }
    };
    private int mHorizontalDragInitX;
    float mHorizontalDragX;
    private final int[] mHorizontalRange = new int[2];
    /* access modifiers changed from: private */
    public StateListDrawable mHorizontalThumbDrawable;
    /* access modifiers changed from: private */
    public int mHorizontalThumbHeight;
    private int mHorizontalThumbWidth;
    int mHorizontalThumbX;
    /* access modifiers changed from: private */
    public Drawable mHorizontalTrackDrawable;
    private int mHorizontalTrackHeight;
    private int mMargin;
    private boolean mNeedDispatchFakeEvent = false;
    /* access modifiers changed from: private */
    public boolean mNeedHorizontalScrollbar = false;
    /* access modifiers changed from: private */
    public boolean mNeedVerticalScrollbar = false;
    private final RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (!FastScroller.this.isDragging()) {
                FastScroller.this.updateThumbPositionByScrolling(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
            }
        }
    };
    private final GalleryRecyclerView mRecyclerView;
    private int mRecyclerViewHeight = 0;
    private int mRecyclerViewWidth = 0;
    private int mScrollSlop;
    private int mScrollbarMinimumRange;
    /* access modifiers changed from: private */
    public final ValueAnimator mShowHideAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int mState = 0;
    private int mTouchOffset;
    private int mVerticalDragInitY;
    float mVerticalDragY;
    private final int[] mVerticalRange = new int[2];
    /* access modifiers changed from: private */
    public StateListDrawable mVerticalThumbDrawable;
    private int mVerticalThumbHeight;
    /* access modifiers changed from: private */
    public int mVerticalThumbWidth;
    int mVerticalThumbY;
    /* access modifiers changed from: private */
    public Drawable mVerticalTrackDrawable;
    private int mVerticalTrackWidth;

    private class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled;

        private AnimatorListener() {
            this.mCanceled = false;
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
            } else if (((Float) FastScroller.this.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                int unused = FastScroller.this.mAnimationState = 0;
                FastScroller.this.setState(0);
            } else {
                int unused2 = FastScroller.this.mAnimationState = 2;
                FastScroller.this.requestRedraw();
            }
        }
    }

    private class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        private AnimatorUpdater() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) (255.0f * floatValue);
            if (FastScroller.this.mNeedVerticalScrollbar) {
                if (FastScroller.this.mVerticalThumbDrawable != null) {
                    FastScroller.this.mVerticalThumbDrawable.setAlpha(i);
                }
                if (FastScroller.this.mVerticalTrackDrawable != null) {
                    FastScroller.this.mVerticalTrackDrawable.setAlpha(i);
                }
                FastScroller fastScroller = FastScroller.this;
                int unused = fastScroller.mAnimTranslateX = (int) ((1.0f - floatValue) * ((float) fastScroller.mVerticalThumbWidth));
                if (FastScroller.this.isLayoutRTL()) {
                    FastScroller fastScroller2 = FastScroller.this;
                    int unused2 = fastScroller2.mAnimTranslateX = -fastScroller2.mAnimTranslateX;
                }
            }
            if (FastScroller.this.mNeedHorizontalScrollbar) {
                if (FastScroller.this.mHorizontalThumbDrawable != null) {
                    FastScroller.this.mHorizontalThumbDrawable.setAlpha(i);
                }
                if (FastScroller.this.mHorizontalTrackDrawable != null) {
                    FastScroller.this.mHorizontalTrackDrawable.setAlpha(i);
                }
                FastScroller fastScroller3 = FastScroller.this;
                int unused3 = fastScroller3.mAnimTranslateY = (int) ((1.0f - floatValue) * ((float) fastScroller3.mHorizontalThumbHeight));
            }
            FastScroller.this.requestRedraw();
        }
    }

    FastScroller(GalleryRecyclerView galleryRecyclerView) {
        this.mRecyclerView = galleryRecyclerView;
        this.mShowHideAnimator.addListener(new AnimatorListener());
        this.mShowHideAnimator.addUpdateListener(new AnimatorUpdater());
        this.mScrollSlop = ViewConfiguration.get(galleryRecyclerView.getContext()).getScaledTouchSlop();
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    private void dispatchFakeEventExceptSelf(MotionEvent motionEvent) {
        int itemTouchListenerCount = this.mRecyclerView.getItemTouchListenerCount();
        if (itemTouchListenerCount > 1) {
            Log.d("FastScroller", "dispatch fake event %s", (Object) motionEvent);
            for (int i = 0; i < itemTouchListenerCount; i++) {
                RecyclerView.OnItemTouchListener itemTouchListenerAt = this.mRecyclerView.getItemTouchListenerAt(i);
                if (itemTouchListenerAt != this) {
                    itemTouchListenerAt.onInterceptTouchEvent(this.mRecyclerView, motionEvent);
                }
            }
        }
    }

    private void drawHorizontalScrollbar(Canvas canvas) {
        StateListDrawable stateListDrawable = this.mHorizontalThumbDrawable;
        if (stateListDrawable != null) {
            int i = this.mRecyclerViewHeight;
            int i2 = this.mHorizontalThumbHeight;
            int i3 = (i - i2) + this.mAnimTranslateY;
            int i4 = this.mHorizontalThumbX;
            stateListDrawable.setBounds(0, 0, this.mHorizontalThumbWidth, i2);
            Drawable drawable = this.mHorizontalTrackDrawable;
            if (drawable != null) {
                drawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
            }
            canvas.translate(0.0f, (float) i3);
            Drawable drawable2 = this.mHorizontalTrackDrawable;
            if (drawable2 != null) {
                drawable2.draw(canvas);
            }
            canvas.translate((float) i4, 0.0f);
            this.mHorizontalThumbDrawable.draw(canvas);
            canvas.translate((float) (-i4), (float) (-i3));
        }
    }

    private void drawVerticalScrollbar(Canvas canvas) {
        StateListDrawable stateListDrawable = this.mVerticalThumbDrawable;
        if (stateListDrawable != null) {
            int i = this.mRecyclerViewWidth;
            int i2 = this.mVerticalThumbWidth;
            int i3 = i - i2;
            int i4 = this.mVerticalThumbY;
            stateListDrawable.setBounds(0, 0, i2, this.mVerticalThumbHeight);
            Drawable drawable = this.mVerticalTrackDrawable;
            if (drawable != null) {
                drawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
            }
            Drawable drawable2 = this.mVerticalTrackDrawable;
            if (drawable2 != null) {
                ensureLayoutDirection(drawable2);
            }
            ensureLayoutDirection(this.mVerticalThumbDrawable);
            if (isLayoutRTL()) {
                Drawable drawable3 = this.mVerticalTrackDrawable;
                if (drawable3 != null) {
                    drawable3.draw(canvas);
                }
                int i5 = this.mAnimTranslateX;
                canvas.translate((float) i5, (float) i4);
                this.mVerticalThumbDrawable.draw(canvas);
                canvas.translate((float) (-i5), (float) (-i4));
                return;
            }
            int i6 = i3 + this.mAnimTranslateX;
            canvas.translate((float) i6, 0.0f);
            Drawable drawable4 = this.mVerticalTrackDrawable;
            if (drawable4 != null) {
                drawable4.draw(canvas);
            }
            canvas.translate(0.0f, (float) i4);
            this.mVerticalThumbDrawable.draw(canvas);
            canvas.translate((float) (-i6), (float) (-i4));
        }
    }

    private void ensureLayoutDirection(Drawable drawable) {
        if (drawable.getLayoutDirection() != this.mRecyclerView.getLayoutDirection()) {
            drawable.setLayoutDirection(this.mRecyclerView.getLayoutDirection());
        }
    }

    private int[] getHorizontalRange() {
        int[] iArr = this.mHorizontalRange;
        int i = this.mMargin;
        iArr[0] = i;
        iArr[1] = (this.mRecyclerViewWidth - i) - this.mHorizontalThumbWidth;
        return iArr;
    }

    private int[] getVerticalRange() {
        int[] iArr = this.mVerticalRange;
        int i = this.mMargin;
        iArr[0] = i;
        iArr[1] = (this.mRecyclerViewHeight - i) - this.mVerticalThumbHeight;
        return iArr;
    }

    private void horizontalScrollTo(float f) {
        float f2 = f;
        int scrollTo = scrollTo(this.mHorizontalDragX, f2, getHorizontalRange(), this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
        if (scrollTo != 0) {
            this.mRecyclerView.scrollBy(scrollTo, 0);
        }
        this.mHorizontalDragX = f;
    }

    /* access modifiers changed from: private */
    public boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView) == 1;
    }

    /* access modifiers changed from: private */
    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    private void resetHideDelay(int i) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, (long) i);
    }

    private int scrollTo(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / ((float) i4)) * ((float) i5));
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    /* access modifiers changed from: private */
    public void setState(int i) {
        StateListDrawable stateListDrawable;
        StateListDrawable stateListDrawable2;
        StateListDrawable stateListDrawable3;
        StateListDrawable stateListDrawable4;
        if (i == 2 && this.mState != 2) {
            if (this.mDragState == 2 && (stateListDrawable4 = this.mVerticalThumbDrawable) != null) {
                stateListDrawable4.setState(PRESSED_STATE_SET);
            }
            if (this.mDragState == 1 && (stateListDrawable3 = this.mHorizontalThumbDrawable) != null) {
                stateListDrawable3.setState(PRESSED_STATE_SET);
            }
            cancelHide();
        }
        if (i == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && i != 2) {
            if (this.mDragState == 2 && (stateListDrawable2 = this.mVerticalThumbDrawable) != null) {
                stateListDrawable2.setState(EMPTY_STATE_SET);
            }
            if (this.mDragState == 1 && (stateListDrawable = this.mHorizontalThumbDrawable) != null) {
                stateListDrawable.setState(EMPTY_STATE_SET);
            }
            resetHideDelay(1200);
        } else if (i == 1) {
            resetHideDelay(1500);
        }
        this.mState = i;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void updateAppearance() {
        StateListDrawable stateListDrawable = this.mVerticalThumbDrawable;
        if (stateListDrawable != null) {
            this.mVerticalThumbWidth = Math.max(this.mDefaultThumbWidth, stateListDrawable.getIntrinsicWidth());
            this.mVerticalThumbHeight = Math.max(this.mDefaultThumbHeight, this.mVerticalThumbDrawable.getIntrinsicHeight());
            this.mVerticalThumbDrawable.setAlpha(255);
        }
        Drawable drawable = this.mVerticalTrackDrawable;
        if (drawable != null) {
            this.mVerticalTrackWidth = Math.max(this.mDefaultThumbWidth, drawable.getIntrinsicWidth());
            this.mVerticalTrackDrawable.setAlpha(255);
        }
        StateListDrawable stateListDrawable2 = this.mHorizontalThumbDrawable;
        if (stateListDrawable2 != null) {
            this.mHorizontalThumbWidth = Math.max(this.mDefaultThumbWidth, stateListDrawable2.getIntrinsicWidth());
            this.mHorizontalThumbHeight = Math.max(this.mDefaultThumbHeight, this.mHorizontalThumbDrawable.getIntrinsicWidth());
            this.mHorizontalThumbDrawable.setAlpha(255);
        }
        Drawable drawable2 = this.mHorizontalTrackDrawable;
        if (drawable2 != null) {
            this.mHorizontalTrackHeight = Math.max(this.mDefaultThumbHeight, drawable2.getIntrinsicWidth());
            this.mHorizontalTrackDrawable.setAlpha(255);
        }
        updateThumbPositionByScrolling(this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerView.computeVerticalScrollOffset());
        requestRedraw();
    }

    private void verticalScrollTo(float f) {
        int[] verticalRange = getVerticalRange();
        int i = verticalRange[1] - verticalRange[0];
        if (i != 0) {
            float f2 = (f - ((float) this.mTouchOffset)) / ((float) i);
            int itemCount = this.mRecyclerView.getAdapter().getItemCount();
            int max = Math.max(0, Math.min((int) (((float) itemCount) * f2), itemCount - 1));
            this.mRecyclerView.invalidateItemDecorations();
            this.mRecyclerView.scrollToPosition(max);
            this.mVerticalDragY = f;
            updateThumbPositionByDragging(-1, (int) Math.min((float) verticalRange[1], Math.max(f, (float) verticalRange[0])));
        }
    }

    public void attach() {
        setupCallbacks();
        updateThumbPositionByScrolling(this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerView.computeVerticalScrollOffset());
        requestRedraw();
    }

    public void detach() {
        destroyCallbacks();
    }

    /* access modifiers changed from: package-private */
    public void hide(int i) {
        int i2 = this.mAnimationState;
        if (i2 == 1) {
            this.mShowHideAnimator.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.mAnimationState = 3;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f});
        this.mShowHideAnimator.setDuration((long) i);
        this.mShowHideAnimator.start();
    }

    public boolean isDragging() {
        return this.mState == 2;
    }

    /* access modifiers changed from: package-private */
    public boolean isPointInsideHorizontalThumb(float f, float f2) {
        if (f2 >= ((float) (this.mRecyclerViewHeight - this.mHorizontalThumbHeight))) {
            int i = this.mHorizontalThumbX;
            return f >= ((float) i) && f <= ((float) (i + this.mHorizontalThumbWidth));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPointInsideVerticalThumb(float f, float f2) {
        if (!isLayoutRTL() ? f >= ((float) (this.mRecyclerViewWidth - this.mVerticalThumbWidth)) : f <= ((float) (this.mVerticalThumbWidth / 2))) {
            int i = this.mVerticalThumbY;
            return f2 >= ((float) i) && f2 <= ((float) (i + this.mVerticalThumbHeight));
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mRecyclerViewWidth != this.mRecyclerView.getWidth() || this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
            setState(0);
        } else if (this.mAnimationState != 0) {
            if (this.mNeedVerticalScrollbar) {
                drawVerticalScrollbar(canvas);
            }
            if (this.mNeedHorizontalScrollbar) {
                drawHorizontalScrollbar(canvas);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1 || actionMasked == 3) {
            this.mHorizontalDragInitX = -1;
            this.mVerticalDragInitY = -1;
        }
        int i = this.mState;
        boolean z = false;
        if (i != 1) {
            return i == 2;
        }
        if (actionMasked == 0) {
            boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            if (isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY())) {
                this.mHorizontalDragInitX = (int) motionEvent.getX();
                return false;
            } else if (!isPointInsideVerticalThumb) {
                return false;
            } else {
                this.mVerticalDragInitY = (int) motionEvent.getY();
                return false;
            }
        } else if (actionMasked != 2) {
            return false;
        } else {
            if (this.mHorizontalDragInitX <= 0 || Math.abs(motionEvent.getX() - ((float) this.mHorizontalDragInitX)) <= ((float) this.mScrollSlop)) {
                if (this.mVerticalDragInitY > 0 && Math.abs(motionEvent.getY() - ((float) this.mVerticalDragInitY)) > ((float) this.mScrollSlop)) {
                    this.mDragState = 2;
                    this.mVerticalDragY = (float) ((int) motionEvent.getY());
                    this.mTouchOffset = (int) (this.mVerticalDragY - ((float) this.mVerticalThumbY));
                }
                if (z) {
                    return z;
                }
                this.mNeedDispatchFakeEvent = true;
                setState(2);
                return z;
            }
            this.mDragState = 1;
            this.mHorizontalDragX = (float) ((int) motionEvent.getX());
            this.mTouchOffset = (int) (this.mHorizontalDragX - ((float) this.mHorizontalThumbX));
            z = true;
            if (z) {
            }
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mState != 0) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0 || actionMasked == 1 || actionMasked == 3) {
                this.mHorizontalDragInitX = -1;
                this.mVerticalDragInitY = -1;
            }
            if (actionMasked == 0) {
                boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
                if (isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY())) {
                    this.mHorizontalDragInitX = (int) motionEvent.getX();
                } else if (isPointInsideVerticalThumb) {
                    this.mVerticalDragInitY = (int) motionEvent.getY();
                }
            } else if (actionMasked == 2) {
                if (this.mState != 2) {
                    if (this.mHorizontalDragInitX > 0 && Math.abs(motionEvent.getX() - ((float) this.mHorizontalDragInitX)) > ((float) this.mScrollSlop)) {
                        this.mDragState = 1;
                        this.mHorizontalDragX = (float) ((int) motionEvent.getX());
                        this.mTouchOffset = (int) (this.mHorizontalDragX - ((float) this.mHorizontalThumbX));
                    } else if (this.mVerticalDragInitY > 0 && Math.abs(motionEvent.getY() - ((float) this.mVerticalDragInitY)) > ((float) this.mScrollSlop)) {
                        this.mDragState = 2;
                        this.mVerticalDragY = (float) ((int) motionEvent.getY());
                        this.mTouchOffset = (int) (this.mVerticalDragY - ((float) this.mVerticalThumbY));
                    }
                    setState(2);
                } else {
                    show();
                    if (this.mDragState == 1) {
                        horizontalScrollTo(motionEvent.getX());
                    }
                    if (this.mDragState == 2) {
                        verticalScrollTo(motionEvent.getY());
                    }
                }
            } else if (motionEvent.getAction() == 1 && this.mState == 2) {
                this.mVerticalDragY = 0.0f;
                this.mHorizontalDragX = 0.0f;
                setState(1);
                this.mDragState = 0;
            }
        }
        if (this.mNeedDispatchFakeEvent) {
            this.mNeedDispatchFakeEvent = false;
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            dispatchFakeEventExceptSelf(obtain);
        }
    }

    public void setStyle(int i) {
        TypedArray obtainStyledAttributes = this.mRecyclerView.getContext().obtainStyledAttributes((AttributeSet) null, R.styleable.CustomFastScroll, R.attr.customFastScrollStyle, i);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.mDefaultThumbHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                case 1:
                    this.mDefaultThumbWidth = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                case 2:
                    this.mHorizontalThumbDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(index);
                    break;
                case 3:
                    this.mHorizontalTrackDrawable = obtainStyledAttributes.getDrawable(index);
                    break;
                case 4:
                    this.mMargin = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                case 5:
                    this.mScrollbarMinimumRange = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                case 6:
                    this.mVerticalThumbDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(index);
                    break;
                case 7:
                    this.mVerticalTrackDrawable = obtainStyledAttributes.getDrawable(index);
                    break;
            }
        }
        obtainStyledAttributes.recycle();
        updateAppearance();
    }

    public void show() {
        int i = this.mAnimationState;
        if (i != 0) {
            if (i == 3) {
                this.mShowHideAnimator.cancel();
            } else {
                return;
            }
        }
        this.mAnimationState = 1;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f});
        this.mShowHideAnimator.setDuration(150);
        this.mShowHideAnimator.setStartDelay(0);
        this.mShowHideAnimator.start();
    }

    /* access modifiers changed from: package-private */
    public void updateThumbPositionByDragging(int i, int i2) {
        if (this.mDragState == 1) {
            this.mNeedHorizontalScrollbar = true;
            this.mHorizontalThumbX = i;
        }
        if (this.mDragState == 2) {
            this.mNeedVerticalScrollbar = true;
            this.mVerticalThumbY = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateThumbPositionByScrolling(int i, int i2) {
        int computeVerticalScrollRange = this.mRecyclerView.computeVerticalScrollRange();
        int i3 = this.mRecyclerViewHeight;
        int i4 = computeVerticalScrollRange - i3;
        this.mNeedVerticalScrollbar = i4 > 0 && i3 >= this.mScrollbarMinimumRange;
        int computeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        int i5 = this.mRecyclerViewWidth;
        int i6 = computeHorizontalScrollRange - i5;
        this.mNeedHorizontalScrollbar = i6 > 0 && i5 >= this.mScrollbarMinimumRange;
        if (this.mNeedVerticalScrollbar || this.mNeedHorizontalScrollbar) {
            if (this.mNeedVerticalScrollbar) {
                float f = (((float) i2) * 1.0f) / ((float) i4);
                int[] verticalRange = getVerticalRange();
                this.mVerticalThumbY = (int) (((float) verticalRange[0]) + (((float) (verticalRange[1] - verticalRange[0])) * f));
            }
            if (this.mNeedHorizontalScrollbar) {
                int[] horizontalRange = getHorizontalRange();
                this.mHorizontalThumbX = (int) (((float) horizontalRange[0]) + (((float) (horizontalRange[1] - horizontalRange[0])) * ((((float) i) * 1.0f) / ((float) i6))));
            }
            int i7 = this.mState;
            if (i7 == 0 || i7 == 1) {
                setState(1);
            }
        } else if (this.mState != 0) {
            setState(0);
        }
    }
}
