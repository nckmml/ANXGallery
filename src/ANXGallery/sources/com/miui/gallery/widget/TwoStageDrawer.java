package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import miui.gallery.support.linearmotor.LinearMotorHelper;

public class TwoStageDrawer extends RelativeLayout {
    private int mActionPointerId;
    private boolean mAlwaysShowTargetViewDivider;
    private DrawerAnimEndListener mAnimEndListener;
    private boolean mAutoClose;
    /* access modifiers changed from: private */
    public boolean mCanScrollDown;
    private Drawable mContentViewBackground;
    private int mDetectMode;
    private int mDividerHeight;
    private float mDownTranslationY;
    private View mDownView;
    private float mDownX;
    private float mDownY;
    private boolean mDragEnabled;
    private boolean mDrawDividerWhenContentEmpty;
    private DrawerListener mDrawerListener;
    private int mInitialDrawerState;
    /* access modifiers changed from: private */
    public boolean mIsFirstLayoutChanged;
    private float mLastY;
    private int mMinimumFlingVelocity;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private float mPendingDownY;
    private boolean mPendingScroll;
    /* access modifiers changed from: private */
    public boolean mPendingTranslate;
    /* access modifiers changed from: private */
    public int mPrimaryContentHeight;
    /* access modifiers changed from: private */
    public boolean mPrimaryContentHeightChanged;
    /* access modifiers changed from: private */
    public View mPrimaryContentView;
    private int mPrimaryContentViewId;
    private Drawable mScrollDivider;
    private int mScrollableViewId;
    /* access modifiers changed from: private */
    public ScrollableView mScrollableViewWrapper;
    private Scroller mScroller;
    /* access modifiers changed from: private */
    public boolean mScrolling;
    /* access modifiers changed from: private */
    public int mSecondaryContentHeight;
    /* access modifiers changed from: private */
    public boolean mSecondaryContentHeightChanged;
    /* access modifiers changed from: private */
    public View mSecondaryContentView;
    private int mSecondaryContentViewId;
    private boolean mSupportMultiPoint;
    private View mTargetView;
    private View mTargetViewAncestor;
    private int mTargetViewId;
    private int mTouchMode;
    private int mTouchSlop;
    private TriggerListener mTriggerListener;
    private boolean mTriggerOn;
    /* access modifiers changed from: private */
    public View mTriggerView;
    /* access modifiers changed from: private */
    public int mTriggerViewHeight;
    private int mTriggerViewId;
    private VelocityTracker mVelocityTracker;

    private static class AdapterViewWrapper implements ScrollableView {
        private AdapterView<?> mAdapterView;

        public AdapterViewWrapper(AdapterView<?> adapterView) {
            this.mAdapterView = adapterView;
        }

        public boolean canScrollDown() {
            if (this.mAdapterView.getFirstVisiblePosition() > 0) {
                return true;
            }
            int paddingTop = this.mAdapterView.getPaddingTop();
            int childCount = this.mAdapterView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (this.mAdapterView.getChildAt(i).getTop() < paddingTop) {
                    return true;
                }
            }
            return false;
        }
    }

    public interface DrawerAnimEndListener {
        void onDrawerAnimEnd(boolean z);
    }

    public interface DrawerListener {
        void onDrawerClose(TwoStageDrawer twoStageDrawer);

        void onDrawerHalfOpen(TwoStageDrawer twoStageDrawer);

        void onDrawerOpen(TwoStageDrawer twoStageDrawer);

        void onDrawerSlide(TwoStageDrawer twoStageDrawer, float f);
    }

    private static class FixedViewWrapper implements ScrollableView {
        public boolean canScrollDown() {
            return false;
        }
    }

    private class MyOnScrollChangeListener implements ViewTreeObserver.OnScrollChangedListener {
        private MyOnScrollChangeListener() {
        }

        public void onScrollChanged() {
            boolean z = TwoStageDrawer.this.mScrollableViewWrapper != null && TwoStageDrawer.this.mScrollableViewWrapper.canScrollDown();
            if (TwoStageDrawer.this.mCanScrollDown != z) {
                boolean unused = TwoStageDrawer.this.mCanScrollDown = z;
                TwoStageDrawer.this.invalidate();
            }
        }
    }

    private static class RecyclerViewWrapper implements ScrollableView {
        private RecyclerView mRecycler;

        public RecyclerViewWrapper(RecyclerView recyclerView) {
            this.mRecycler = recyclerView;
        }

        public boolean canScrollDown() {
            return this.mRecycler.canScrollVertically(-1);
        }
    }

    private static class ScrollViewWrapper implements ScrollableView {
        private ScrollView mScrollView;

        public ScrollViewWrapper(ScrollView scrollView) {
            this.mScrollView = scrollView;
        }

        public boolean canScrollDown() {
            return this.mScrollView.getScrollY() > 0;
        }
    }

    public interface ScrollableView {
        boolean canScrollDown();
    }

    public interface TriggerListener {
        void onTriggerOpen(TwoStageDrawer twoStageDrawer);

        void onTriggerSlide(TwoStageDrawer twoStageDrawer, float f);
    }

    public TwoStageDrawer(Context context) {
        this(context, (AttributeSet) null);
    }

    public TwoStageDrawer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoStageDrawer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mActionPointerId = -1;
        this.mIsFirstLayoutChanged = true;
        this.mInitialDrawerState = 0;
        this.mSupportMultiPoint = true;
        this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                boolean z;
                TwoStageDrawer twoStageDrawer = TwoStageDrawer.this;
                int access$100 = twoStageDrawer.getViewHeight(twoStageDrawer.mPrimaryContentView);
                if (access$100 != TwoStageDrawer.this.mPrimaryContentHeight) {
                    TwoStageDrawer twoStageDrawer2 = TwoStageDrawer.this;
                    boolean unused = twoStageDrawer2.mPrimaryContentHeightChanged = (twoStageDrawer2.mPrimaryContentHeight == 0 || access$100 == 0) ? false : true;
                    int unused2 = TwoStageDrawer.this.mPrimaryContentHeight = access$100;
                    z = true;
                } else {
                    z = false;
                }
                TwoStageDrawer twoStageDrawer3 = TwoStageDrawer.this;
                int access$1002 = twoStageDrawer3.getViewHeight(twoStageDrawer3.mSecondaryContentView);
                if (access$1002 != TwoStageDrawer.this.mSecondaryContentHeight) {
                    TwoStageDrawer twoStageDrawer4 = TwoStageDrawer.this;
                    boolean unused3 = twoStageDrawer4.mSecondaryContentHeightChanged = (twoStageDrawer4.mSecondaryContentHeight == 0 || access$1002 == 0) ? false : true;
                    int unused4 = TwoStageDrawer.this.mSecondaryContentHeight = access$1002;
                    z = true;
                }
                TwoStageDrawer twoStageDrawer5 = TwoStageDrawer.this;
                int access$1003 = twoStageDrawer5.getViewHeight(twoStageDrawer5.mTriggerView);
                if (access$1003 != TwoStageDrawer.this.mTriggerViewHeight) {
                    int unused5 = TwoStageDrawer.this.mTriggerViewHeight = access$1003;
                    z = true;
                }
                if (!z) {
                    return;
                }
                if (TwoStageDrawer.this.mIsFirstLayoutChanged) {
                    boolean unused6 = TwoStageDrawer.this.mIsFirstLayoutChanged = false;
                    TwoStageDrawer.this.onFirstLayoutChanged();
                } else if (TwoStageDrawer.this.mScrolling) {
                    boolean unused7 = TwoStageDrawer.this.mPendingTranslate = true;
                } else {
                    TwoStageDrawer.this.adaptiveTranslate();
                }
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinimumFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TwoStageDrawer, i, 0);
        this.mPrimaryContentViewId = obtainStyledAttributes.getResourceId(6, 0);
        this.mSecondaryContentViewId = obtainStyledAttributes.getResourceId(9, 0);
        this.mTargetViewId = obtainStyledAttributes.getResourceId(10, 0);
        this.mScrollableViewId = obtainStyledAttributes.getResourceId(8, 0);
        this.mScrollDivider = obtainStyledAttributes.getDrawable(7);
        Drawable drawable = this.mScrollDivider;
        if (drawable != null) {
            this.mDividerHeight = drawable.getIntrinsicHeight();
        }
        this.mTriggerViewId = obtainStyledAttributes.getResourceId(3, 0);
        this.mContentViewBackground = obtainStyledAttributes.getDrawable(4);
        this.mDragEnabled = obtainStyledAttributes.getBoolean(5, false);
        this.mAlwaysShowTargetViewDivider = obtainStyledAttributes.getBoolean(0, true);
        this.mDrawDividerWhenContentEmpty = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
    }

    /* access modifiers changed from: private */
    public void adaptiveTranslate() {
        int translationY;
        if ((this.mPrimaryContentView != null || this.mSecondaryContentView != null) && (translationY = (int) this.mTargetView.getTranslationY()) != 0) {
            View view = this.mPrimaryContentView;
            if (view == null || view.getTranslationY() != 0.0f) {
                View view2 = this.mSecondaryContentView;
                if (view2 != null && view2.getTranslationY() == 0.0f) {
                    startScroll(translationY, this.mSecondaryContentHeight - translationY);
                    return;
                }
                return;
            }
            View view3 = this.mSecondaryContentView;
            if (view3 == null || view3.getTranslationY() != 0.0f) {
                startScroll(translationY, this.mPrimaryContentHeight - translationY);
            } else {
                startScroll(translationY, (this.mPrimaryContentHeight + this.mSecondaryContentHeight) - translationY);
            }
        }
    }

    private int computeScrollDuration(int i, float f) {
        float abs = Math.abs(f);
        int round = abs == 0.0f ? 0 : Math.round(Math.abs(((float) i) / abs) * 1000.0f) * 4;
        if (round > 800) {
            return 800;
        }
        return round;
    }

    private void dispatchEventToTargetView(MotionEvent motionEvent, boolean z) {
        View view = this.mDownView;
        View view2 = this.mTargetViewAncestor;
        if (view != view2) {
            dispatchTouchEventToChild(view2, motionEvent);
        } else if (motionEvent.getActionMasked() == 3) {
            float[] transformEventOffset = getTransformEventOffset(this.mTargetViewAncestor);
            motionEvent.offsetLocation(transformEventOffset[0], transformEventOffset[1]);
            super.dispatchTouchEvent(motionEvent);
            motionEvent.offsetLocation(-transformEventOffset[0], -transformEventOffset[1]);
        } else {
            super.dispatchTouchEvent(motionEvent);
        }
        if (z) {
            motionEvent.recycle();
        }
    }

    private void dispatchTouchEventToChild(View view, MotionEvent motionEvent) {
        float[] transformEventOffset = getTransformEventOffset(view);
        motionEvent.offsetLocation(transformEventOffset[0], transformEventOffset[1]);
        view.dispatchTouchEvent(motionEvent);
        motionEvent.offsetLocation(-transformEventOffset[0], -transformEventOffset[1]);
    }

    private void dispatchTouchEventToChild(View view, MotionEvent motionEvent, int i) {
        dispatchTouchEventToChild(view, motionEvent, i, false);
    }

    private void dispatchTouchEventToChild(View view, MotionEvent motionEvent, int i, boolean z) {
        MotionEvent obtainEvent = obtainEvent(motionEvent, i, z);
        dispatchTouchEventToChild(view, obtainEvent);
        obtainEvent.recycle();
    }

    private boolean drawPrimaryContentView(Canvas canvas, View view, long j) {
        int i;
        int translationY = (int) this.mTargetView.getTranslationY();
        if (translationY <= 0) {
            return true;
        }
        int width = getWidth();
        int i2 = this.mDividerHeight;
        if (i2 <= 0 || translationY >= this.mPrimaryContentHeight) {
            i = 0;
        } else {
            this.mScrollDivider.setBounds(0, 0, width, i2 + 0);
            this.mScrollDivider.draw(canvas);
            i = this.mDividerHeight + 0;
        }
        Drawable drawable = this.mContentViewBackground;
        if (drawable != null) {
            drawable.setBounds(0, i, width, translationY);
            this.mContentViewBackground.draw(canvas);
        }
        canvas.save();
        canvas.clipRect(0, i, width, translationY);
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    private boolean drawSecondaryContentView(Canvas canvas, View view, long j) {
        int i;
        int translationY = (int) this.mTargetView.getTranslationY();
        if (translationY <= this.mPrimaryContentHeight) {
            return true;
        }
        int width = getWidth();
        int i2 = this.mPrimaryContentHeight;
        if (translationY > i2 && translationY < this.mSecondaryContentHeight + i2 && (i = this.mDividerHeight) > 0) {
            this.mScrollDivider.setBounds(0, i2, width, i + i2);
            this.mScrollDivider.draw(canvas);
            i2 += this.mDividerHeight;
        }
        Drawable drawable = this.mContentViewBackground;
        if (drawable != null) {
            drawable.setBounds(0, i2, width, translationY);
            this.mContentViewBackground.draw(canvas);
        }
        canvas.save();
        canvas.clipRect(0, i2, width, translationY);
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    private boolean drawTargetViewAncestor(Canvas canvas, View view, long j) {
        boolean drawChild = super.drawChild(canvas, view, j);
        int translationY = (int) this.mTargetView.getTranslationY();
        if (this.mAlwaysShowTargetViewDivider || ((translationY <= 0 && this.mCanScrollDown) || (this.mDrawDividerWhenContentEmpty && this.mPrimaryContentHeight == 0 && this.mSecondaryContentHeight == 0))) {
            int width = getWidth();
            int i = this.mDividerHeight;
            if (i > 0) {
                this.mScrollDivider.setBounds(0, translationY, width, i + translationY);
                this.mScrollDivider.draw(canvas);
            }
        }
        return drawChild;
    }

    private boolean drawTriggerView(Canvas canvas, View view, long j) {
        int translationY = (int) this.mTargetView.getTranslationY();
        if (translationY <= this.mPrimaryContentHeight + this.mSecondaryContentHeight) {
            return true;
        }
        int width = getWidth();
        int i = this.mPrimaryContentHeight + this.mSecondaryContentHeight;
        Drawable drawable = this.mContentViewBackground;
        if (drawable != null) {
            drawable.setBounds(0, i, width, translationY);
            this.mContentViewBackground.draw(canvas);
        }
        canvas.save();
        canvas.clipRect(0, i, width, translationY);
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    private View getTouchDownView(int i, int i2) {
        Rect rect = new Rect();
        for (View view : new View[]{this.mTargetViewAncestor, this.mTriggerView, this.mSecondaryContentView, this.mPrimaryContentView}) {
            if (view != null && view.getVisibility() == 0) {
                int x = (int) view.getX();
                int y = (int) view.getY();
                rect.set(x, y, view.getWidth() + x, view.getHeight() + y);
                if (rect.contains(i, i2)) {
                    return view;
                }
            }
        }
        return null;
    }

    private float[] getTransformEventOffset(View view) {
        return new float[]{-(((float) view.getLeft()) + view.getTranslationX()), -(((float) view.getTop()) + view.getTranslationY())};
    }

    /* access modifiers changed from: private */
    public int getViewHeight(View view) {
        if (view == null || view.getVisibility() == 8) {
            return 0;
        }
        int measuredHeight = view.getMeasuredHeight();
        if (!(view instanceof ViewGroup)) {
            return measuredHeight;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private void notifyDrawerStateChanged() {
        if (this.mDrawerListener == null) {
            return;
        }
        if (this.mPrimaryContentView != null || this.mSecondaryContentView != null) {
            int translationY = (int) this.mTargetView.getTranslationY();
            if (this.mDetectMode == 2) {
                this.mDrawerListener.onDrawerSlide(this, ((float) Math.min(this.mPrimaryContentHeight + this.mSecondaryContentHeight, Math.max(translationY, 0))) / ((float) (this.mPrimaryContentHeight + this.mSecondaryContentHeight)));
            } else if (translationY == 0) {
                this.mDrawerListener.onDrawerClose(this);
            } else {
                int i = this.mPrimaryContentHeight;
                if (translationY != i) {
                    int i2 = this.mSecondaryContentHeight;
                    if (translationY == i + i2) {
                        this.mDrawerListener.onDrawerOpen(this);
                    } else {
                        this.mDrawerListener.onDrawerSlide(this, ((float) translationY) / ((float) (i + i2)));
                    }
                } else if (this.mSecondaryContentHeight == 0) {
                    this.mDrawerListener.onDrawerOpen(this);
                } else {
                    this.mDrawerListener.onDrawerHalfOpen(this);
                }
            }
        }
    }

    private void notifyTriggerStateChanged() {
        int i;
        if (this.mTriggerListener != null && (i = this.mTriggerViewHeight) > 0 && this.mDetectMode == 2) {
            int translationY = (((int) this.mTargetView.getTranslationY()) - this.mPrimaryContentHeight) - this.mSecondaryContentHeight;
            this.mTriggerListener.onTriggerSlide(this, ((float) translationY) / ((float) i));
            if (translationY >= i) {
                if (!this.mTriggerOn) {
                    this.mTriggerOn = true;
                    this.mTriggerListener.onTriggerOpen(this);
                    LinearMotorHelper.performHapticFeedback(getContext(), LinearMotorHelper.EFFECT_KEY_SWITCH, LinearMotorHelper.HAPTIC_STRENGTH_ID_0);
                }
            } else if (this.mTriggerOn) {
                this.mTriggerOn = false;
            }
        }
    }

    private MotionEvent obtainEvent(MotionEvent motionEvent, int i) {
        return obtainEvent(motionEvent, i, false);
    }

    private MotionEvent obtainEvent(MotionEvent motionEvent, int i, boolean z) {
        if (z) {
            return MotionEvent.obtain(motionEvent.getEventTime(), motionEvent.getEventTime(), i, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(i);
        return obtain;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076  */
    private void offsetTranslationAnimation(float f, float f2) {
        float f3;
        int translationY = (int) this.mTargetView.getTranslationY();
        int i = this.mPrimaryContentHeight;
        int i2 = this.mSecondaryContentHeight;
        float f4 = (translationY > i + i2 ? ((float) (i + i2)) + (((float) ((translationY - i) - i2)) * 2.0f) : (float) translationY) + f;
        if (this.mDownTranslationY != ((float) this.mPrimaryContentHeight)) {
            float translationY2 = this.mTargetView.getTranslationY();
            int i3 = this.mPrimaryContentHeight;
            if (translationY2 <= ((float) i3) && f2 > ((float) this.mMinimumFlingVelocity)) {
                f3 = Math.max(0.0f, Math.min(f4, this.mPrimaryContentView != null ? (float) i3 : (float) this.mSecondaryContentHeight));
                int i4 = this.mPrimaryContentHeight;
                int i5 = this.mSecondaryContentHeight;
                updateTranslationAnimation(f3 <= ((float) (i4 + i5)) ? i4 + i5 + ((int) (((f3 - ((float) i4)) - ((float) i5)) / 2.0f)) : (int) f3);
                invalidate();
            }
        }
        f3 = Math.max(0.0f, Math.min(f4, this.mTriggerViewHeight <= 0 ? (float) (this.mPrimaryContentHeight + this.mSecondaryContentHeight) : 2.14748365E9f));
        int i42 = this.mPrimaryContentHeight;
        int i52 = this.mSecondaryContentHeight;
        updateTranslationAnimation(f3 <= ((float) (i42 + i52)) ? i42 + i52 + ((int) (((f3 - ((float) i42)) - ((float) i52)) / 2.0f)) : (int) f3);
        invalidate();
    }

    /* access modifiers changed from: private */
    public void onFirstLayoutChanged() {
        int i = this.mInitialDrawerState;
        int i2 = 0;
        if (i == 0) {
            i2 = this.mSecondaryContentHeight + this.mPrimaryContentHeight;
        } else if (i == 1) {
            i2 = this.mPrimaryContentHeight;
        }
        updateTranslationAnimation(i2);
    }

    private void startScroll(int i, int i2) {
        this.mScroller.startScroll(0, i, 0, i2);
        this.mScrolling = true;
        invalidate();
    }

    private void startScroll(int i, int i2, float f) {
        int computeScrollDuration = computeScrollDuration(i2, f);
        if (computeScrollDuration > 0) {
            startScroll(i, i2, computeScrollDuration);
        } else {
            startScroll(i, i2);
        }
    }

    private void startScroll(int i, int i2, int i3) {
        this.mScroller.startScroll(0, i, 0, i2, i3);
        this.mScrolling = true;
        invalidate();
    }

    private void updateTranslationAnimation(int i) {
        this.mTargetView.setTranslationY((float) i);
        int finalY = this.mScroller.getFinalY();
        View view = this.mPrimaryContentView;
        if (view != null) {
            if (!this.mPrimaryContentHeightChanged) {
                int i2 = this.mPrimaryContentHeight;
                if (i < i2) {
                    view.setTranslationY((float) (i - i2));
                } else if (view.getTranslationY() != 0.0f) {
                    this.mPrimaryContentView.setTranslationY(0.0f);
                }
            } else if (finalY == 0) {
                view.setTranslationY((float) (-this.mPrimaryContentHeight));
            } else {
                view.setTranslationY(0.0f);
            }
        }
        View view2 = this.mSecondaryContentView;
        if (view2 != null) {
            if (!this.mSecondaryContentHeightChanged) {
                int i3 = this.mPrimaryContentHeight;
                if (i <= i3) {
                    view2.setTranslationY((float) ((0 - i3) - this.mSecondaryContentHeight));
                } else {
                    view2.setTranslationY((float) ((i - i3) - this.mSecondaryContentHeight));
                }
            } else if (finalY == 0 || finalY == this.mPrimaryContentHeight) {
                this.mSecondaryContentView.setTranslationY((float) (-(this.mPrimaryContentHeight + this.mSecondaryContentHeight)));
            } else {
                view2.setTranslationY(0.0f);
            }
        }
        View view3 = this.mTriggerView;
        if (view3 != null) {
            int i4 = this.mPrimaryContentHeight;
            int i5 = this.mSecondaryContentHeight;
            if (i <= i4 + i5) {
                view3.setTranslationY((float) (((0 - i4) - i5) - this.mTriggerViewHeight));
            } else {
                view3.setTranslationY(0.0f);
            }
        }
        notifyTriggerStateChanged();
    }

    public void closeDrawer(boolean z, DrawerAnimEndListener drawerAnimEndListener) {
        this.mAnimEndListener = drawerAnimEndListener;
        if (z) {
            int translationY = (int) this.mTargetView.getTranslationY();
            startScroll(translationY, -translationY, 500);
            return;
        }
        this.mScroller.forceFinished(true);
        updateTranslationAnimation(0);
        notifyDrawerStateChanged();
        invalidate();
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mTargetView == null) {
            return;
        }
        if (this.mScroller.computeScrollOffset()) {
            updateTranslationAnimation(this.mScroller.getCurrY());
            postInvalidateOnAnimation();
            return;
        }
        DrawerAnimEndListener drawerAnimEndListener = this.mAnimEndListener;
        if (drawerAnimEndListener != null) {
            drawerAnimEndListener.onDrawerAnimEnd(isDrawerOpen());
            this.mAnimEndListener = null;
        }
        if (this.mScrolling) {
            this.mScrolling = false;
            notifyDrawerStateChanged();
            if (this.mPendingTranslate) {
                adaptiveTranslate();
                this.mPendingTranslate = false;
                return;
            }
            this.mPrimaryContentHeightChanged = false;
            this.mSecondaryContentHeightChanged = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0174, code lost:
        if (r11.mScrollableViewWrapper != null) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0160, code lost:
        if (r7.canScrollDown() == false) goto L_0x0176;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02ca  */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.mSupportMultiPoint && motionEvent.getPointerCount() > 1) {
            this.mDetectMode = 0;
            return super.dispatchTouchEvent(motionEvent);
        } else if ((!this.mDragEnabled && this.mTriggerView == null) || ((this.mPrimaryContentHeight <= 0 && this.mSecondaryContentHeight <= 0 && this.mTriggerViewHeight <= 0) || (actionMasked != 0 && this.mDetectMode == 0))) {
            return super.dispatchTouchEvent(motionEvent);
        } else {
            if (actionMasked != 0) {
                if (actionMasked == 1) {
                    int translationY = (int) this.mTargetView.getTranslationY();
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    if (this.mAutoClose && translationY != 0) {
                        startScroll(translationY, -translationY, this.mVelocityTracker.getYVelocity());
                    } else if (translationY != 0) {
                        int i3 = this.mPrimaryContentHeight;
                        if (translationY < i3) {
                            if (this.mVelocityTracker.getYVelocity() > ((float) this.mMinimumFlingVelocity)) {
                                startScroll(translationY, this.mPrimaryContentHeight - translationY, this.mVelocityTracker.getYVelocity());
                            } else {
                                startScroll(translationY, -translationY, this.mVelocityTracker.getYVelocity());
                            }
                        } else if (translationY > i3 && translationY != i3 + this.mSecondaryContentHeight) {
                            if (this.mVelocityTracker.getYVelocity() > ((float) this.mMinimumFlingVelocity)) {
                                startScroll(translationY, (this.mPrimaryContentHeight + this.mSecondaryContentHeight) - translationY, this.mVelocityTracker.getYVelocity());
                            } else {
                                startScroll(translationY, this.mPrimaryContentHeight - translationY, this.mVelocityTracker.getYVelocity());
                            }
                        }
                    }
                } else if (actionMasked == 2) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActionPointerId);
                    if (findPointerIndex == -1) {
                        this.mActionPointerId = motionEvent.getPointerId(0);
                        findPointerIndex = 0;
                    }
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    float abs = Math.abs(x - this.mDownX);
                    float abs2 = Math.abs(y - this.mDownY);
                    if (this.mDetectMode == 1) {
                        if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                            this.mDetectMode = 0;
                        } else if (abs2 > ((float) this.mTouchSlop)) {
                            if (this.mDragEnabled || this.mTriggerView == null || y - this.mDownY >= 0.0f) {
                                this.mDetectMode = 2;
                                getParent().requestDisallowInterceptTouchEvent(true);
                                View view = this.mPrimaryContentView;
                                if (view != null && this.mDownView == view) {
                                    dispatchTouchEventToChild(view, motionEvent, 3);
                                }
                                View view2 = this.mSecondaryContentView;
                                if (view2 != null && this.mDownView == view2) {
                                    dispatchTouchEventToChild(view2, motionEvent, 3);
                                }
                                View view3 = this.mTriggerView;
                                if (view3 != null && this.mDownView == view3) {
                                    dispatchTouchEventToChild(view3, motionEvent, 3);
                                }
                            } else {
                                this.mDetectMode = 0;
                            }
                        }
                    }
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    float f = y - this.mLastY;
                    if (this.mDetectMode == 2 && (f >= 1.0f || f <= -1.0f)) {
                        int i4 = this.mTouchMode;
                        if (f <= 0.0f) {
                            if (f < 0.0f) {
                                if (this.mTargetView.getTranslationY() <= 0.0f) {
                                }
                                i4 = 1;
                            }
                            if (i4 == 1) {
                            }
                            this.mTouchMode = i4;
                            this.mLastY = y;
                        } else if (this.mTargetView.getTranslationY() < ((float) (this.mPrimaryContentHeight + this.mSecondaryContentHeight + this.mTriggerViewHeight))) {
                            ScrollableView scrollableView = this.mScrollableViewWrapper;
                            if (scrollableView != null) {
                            }
                            i4 = 1;
                            if (i4 == 1) {
                                int i5 = this.mTouchMode;
                                if (i5 == 2) {
                                    dispatchEventToTargetView(obtainEvent(motionEvent, 1), true);
                                } else if (this.mDownView == this.mTargetViewAncestor && i5 == 0) {
                                    dispatchEventToTargetView(obtainEvent(motionEvent, 3), true);
                                }
                                offsetTranslationAnimation(f, this.mVelocityTracker.getYVelocity());
                                notifyDrawerStateChanged();
                            } else if (i4 == 2) {
                                int i6 = this.mTouchMode;
                                if (i6 == 1 || (this.mDownView != this.mTargetViewAncestor && i6 == 0)) {
                                    dispatchEventToTargetView(obtainEvent(motionEvent, 0), true);
                                    if (this.mTouchMode == 1) {
                                        this.mPendingScroll = true;
                                        this.mPendingDownY = motionEvent.getY();
                                    }
                                } else {
                                    dispatchEventToTargetView(motionEvent, false);
                                    if (this.mPendingScroll && Math.abs(y - this.mPendingDownY) > ((float) this.mTouchSlop)) {
                                        this.mPendingScroll = false;
                                    }
                                }
                            }
                            this.mTouchMode = i4;
                            this.mLastY = y;
                        }
                        i4 = 2;
                        if (i4 == 1) {
                        }
                        this.mTouchMode = i4;
                        this.mLastY = y;
                    }
                } else if (actionMasked == 3) {
                    int translationY2 = (int) this.mTargetView.getTranslationY();
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    if (translationY2 != 0 && (this.mAutoClose || !(translationY2 == (i2 = this.mPrimaryContentHeight) || translationY2 == i2 + this.mSecondaryContentHeight))) {
                        startScroll(translationY2, -translationY2, this.mVelocityTracker.getYVelocity());
                    }
                } else if (actionMasked != 5) {
                    if (actionMasked == 6) {
                        int actionIndex = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionIndex) == this.mActionPointerId) {
                            int i7 = actionIndex == 0 ? 1 : 0;
                            this.mActionPointerId = motionEvent.getPointerId(i7);
                            this.mDownX = motionEvent.getX(i7);
                            float y2 = motionEvent.getY(i7);
                            this.mDownY = y2;
                            this.mLastY = y2;
                        }
                        this.mVelocityTracker.addMovement(motionEvent);
                    }
                }
                i = this.mDetectMode;
                if (i != 2) {
                    int i8 = this.mTouchMode;
                    if (i8 == 2) {
                        if (actionMasked != 2) {
                            if (actionMasked != 1 || !this.mPendingScroll) {
                                dispatchEventToTargetView(motionEvent, false);
                            } else {
                                Log.w("TwoStageDrawer", "monitor not real scroll, cancel!");
                                dispatchEventToTargetView(obtainEvent(motionEvent, 3), true);
                            }
                        }
                    } else if (i8 != 1 && i8 == 0) {
                        super.dispatchTouchEvent(motionEvent);
                    }
                } else if (i == 1) {
                    super.dispatchTouchEvent(motionEvent);
                }
                if (actionMasked == 1 || actionMasked == 3) {
                    this.mVelocityTracker.clear();
                    this.mDetectMode = 1;
                    this.mTouchMode = 0;
                    this.mPendingScroll = false;
                    this.mTriggerOn = false;
                }
                return true;
            }
            int actionIndex2 = motionEvent.getActionIndex();
            this.mActionPointerId = motionEvent.getPointerId(actionIndex2);
            this.mVelocityTracker.clear();
            this.mVelocityTracker.addMovement(motionEvent);
            this.mDownX = motionEvent.getX(actionIndex2);
            float y3 = motionEvent.getY(actionIndex2);
            this.mLastY = y3;
            this.mDownY = y3;
            this.mDownTranslationY = this.mTargetView.getTranslationY();
            this.mDownView = getTouchDownView((int) this.mDownX, (int) this.mDownY);
            this.mDetectMode = 1;
            this.mTouchMode = 0;
            this.mPendingScroll = false;
            i = this.mDetectMode;
            if (i != 2) {
            }
            this.mVelocityTracker.clear();
            this.mDetectMode = 1;
            this.mTouchMode = 0;
            this.mPendingScroll = false;
            this.mTriggerOn = false;
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        return view == this.mPrimaryContentView ? drawPrimaryContentView(canvas, view, j) : view == this.mSecondaryContentView ? drawSecondaryContentView(canvas, view, j) : view == this.mTargetViewAncestor ? drawTargetViewAncestor(canvas, view, j) : view == this.mTriggerView ? drawTriggerView(canvas, view, j) : super.drawChild(canvas, view, j);
    }

    public void halfCloseDrawer(boolean z, DrawerAnimEndListener drawerAnimEndListener) {
        if (this.mPrimaryContentView != null && this.mSecondaryContentView != null) {
            this.mAnimEndListener = drawerAnimEndListener;
            if (z) {
                int translationY = (int) this.mTargetView.getTranslationY();
                startScroll(translationY, this.mPrimaryContentHeight - translationY, 500);
                return;
            }
            this.mScroller.forceFinished(true);
            updateTranslationAnimation(this.mPrimaryContentHeight);
            notifyDrawerStateChanged();
            invalidate();
        }
    }

    public void halfOpenDrawer() {
        halfOpenDrawer(false);
    }

    public void halfOpenDrawer(boolean z) {
        if (this.mPrimaryContentView != null && this.mSecondaryContentView != null) {
            getViewTreeObserver().dispatchOnGlobalLayout();
            if (z) {
                int translationY = (int) this.mTargetView.getTranslationY();
                startScroll(translationY, this.mPrimaryContentHeight - translationY, 800);
                return;
            }
            updateTranslationAnimation(this.mPrimaryContentHeight);
            notifyDrawerStateChanged();
            invalidate();
        }
    }

    public boolean isAnimating() {
        return !this.mScroller.isFinished();
    }

    public boolean isDrawerHalfOpen() {
        float translationY = this.mTargetView.getTranslationY();
        return (this.mPrimaryContentView == null || this.mSecondaryContentView == null || translationY == 0.0f || translationY != ((float) this.mPrimaryContentHeight)) ? false : true;
    }

    public boolean isDrawerOpen() {
        float translationY = this.mTargetView.getTranslationY();
        return ((this.mPrimaryContentView == null && this.mSecondaryContentView == null) || translationY == 0.0f || translationY != ((float) (this.mPrimaryContentHeight + this.mSecondaryContentHeight))) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.mPrimaryContentViewId;
        if (i > 0) {
            this.mPrimaryContentView = findViewById(i);
            View view = this.mPrimaryContentView;
            if (view == null) {
                throw new IllegalArgumentException("The primaryContentView attribute is must refer to an existing child.");
            } else if (view.getParent() != this) {
                throw new IllegalArgumentException("The primaryContentView attribute is must be a direct child of TwoStageDrawer.");
            }
        }
        int i2 = this.mSecondaryContentViewId;
        if (i2 > 0) {
            this.mSecondaryContentView = findViewById(i2);
            View view2 = this.mSecondaryContentView;
            if (view2 == null) {
                throw new IllegalArgumentException("The secondaryContentView attribute is must refer to an existing child.");
            } else if (view2.getParent() != this) {
                throw new IllegalArgumentException("The secondaryContentView attribute is must be a direct child of TwoStageDrawer.");
            }
        }
        int i3 = this.mTargetViewId;
        if (i3 > 0) {
            setTargetView(findViewById(i3));
        }
        int i4 = this.mScrollableViewId;
        if (i4 > 0) {
            setScrollableView(this.mTargetView.findViewById(i4));
        }
        int i5 = this.mTriggerViewId;
        if (i5 > 0) {
            this.mTriggerView = findViewById(i5);
            View view3 = this.mTriggerView;
            if (view3 == null) {
                throw new IllegalArgumentException("The triggerView attribute is must refer to an existing child.");
            } else if (view3.getParent() != this) {
                throw new IllegalArgumentException("The triggerView attribute is must be a direct child of TwoStageDrawer.");
            }
        }
    }

    public void openDrawer() {
        openDrawer(false, (DrawerAnimEndListener) null);
    }

    public void openDrawer(boolean z, DrawerAnimEndListener drawerAnimEndListener) {
        getViewTreeObserver().dispatchOnGlobalLayout();
        this.mAnimEndListener = drawerAnimEndListener;
        if (z) {
            int translationY = (int) this.mTargetView.getTranslationY();
            startScroll(translationY, (this.mPrimaryContentHeight + this.mSecondaryContentHeight) - translationY, 800);
            return;
        }
        this.mScroller.forceFinished(true);
        updateTranslationAnimation(this.mPrimaryContentHeight + this.mSecondaryContentHeight);
        notifyDrawerStateChanged();
        invalidate();
    }

    public void setAlwaysShowTargetViewDivider(boolean z) {
        this.mAlwaysShowTargetViewDivider = z;
    }

    public void setAutoClose(boolean z) {
        this.mAutoClose = z;
    }

    public void setContentViewBackground(Drawable drawable) {
        this.mContentViewBackground = drawable;
    }

    public void setDragEnabled(boolean z) {
        this.mDragEnabled = z;
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.mDrawerListener = drawerListener;
    }

    public void setInitialDrawerState(int i) {
        this.mInitialDrawerState = i;
    }

    public void setPrimaryContentView(View view) {
        this.mPrimaryContentView = view;
    }

    public void setScrollDivider(Drawable drawable) {
        this.mScrollDivider = drawable;
        Drawable drawable2 = this.mScrollDivider;
        if (drawable2 != null) {
            this.mDividerHeight = drawable2.getIntrinsicHeight();
        } else {
            this.mDividerHeight = 0;
        }
    }

    public void setScrollableView(View view) {
        if (view != null) {
            if (view instanceof ScrollableView) {
                this.mScrollableViewWrapper = (ScrollableView) view;
            } else if (view instanceof ScrollView) {
                this.mScrollableViewWrapper = new ScrollViewWrapper((ScrollView) view);
            } else if (view instanceof AdapterView) {
                this.mScrollableViewWrapper = new AdapterViewWrapper((AdapterView) view);
            } else if (view instanceof RecyclerView) {
                this.mScrollableViewWrapper = new RecyclerViewWrapper((RecyclerView) view);
            } else {
                Log.w("TwoStageDrawer", "The scrollableView is a fixed view that can't scroll all the time");
                this.mScrollableViewWrapper = new FixedViewWrapper();
            }
            view.getViewTreeObserver().addOnScrollChangedListener(new MyOnScrollChangeListener());
            return;
        }
        throw new IllegalArgumentException("The scrollableView attribute is must refer to an existing child of targetView.");
    }

    public void setSecondaryContentView(View view) {
        this.mSecondaryContentView = view;
    }

    public void setSupportMultiPoint(boolean z) {
        this.mSupportMultiPoint = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    public void setTargetView(View view) {
        if (view != null) {
            View view2 = this.mTargetView;
            if (view2 != view) {
                this.mTargetView = view;
                this.mTargetViewAncestor = this.mTargetView;
                while (true) {
                    View view3 = this.mTargetViewAncestor;
                    if (view3 != null && view3.getParent() != this) {
                        this.mTargetViewAncestor = (View) this.mTargetViewAncestor.getParent();
                    } else if (view2 == null) {
                        updateTranslationAnimation((int) view2.getTranslationY());
                        return;
                    } else {
                        return;
                    }
                }
                if (view2 == null) {
                }
            }
        } else {
            throw new IllegalArgumentException("The targetView attribute is must refer to an existing child.");
        }
    }

    public void setTriggerListener(TriggerListener triggerListener) {
        this.mTriggerListener = triggerListener;
    }
}
