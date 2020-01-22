package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.miui.gallery.R;
import miui.util.AttributeResolver;

public class ScrollableViewDrawer extends FrameLayout {
    private static final boolean DEBUGGABLE = Log.isLoggable("ScrollableViewDrawer", 3);
    private int mActionPointerId;
    private DrawerAnimEndListener mAnimEndListener;
    private View mContentView;
    private int mContentViewId;
    private boolean mDetermineHorizontal;
    private boolean mDetermineVertical;
    private boolean mDetermineVerticalDrag;
    private int mDividerHeight;
    private float mDownX;
    private float mDownY;
    private boolean mDragEnabled;
    private DrawerListener mDrawerListener;
    private float mLastMotionY;
    private View.OnLayoutChangeListener mOnContentViewLayoutChangeListener;
    /* access modifiers changed from: private */
    public DrawerAnimEndListener mPendingOpenDrawerAnimEndListener;
    private boolean mPendingOpenDrawerBeforeLaidOut;
    private View.OnLayoutChangeListener mPendingOpenDrawerLayoutChangeListener;
    /* access modifiers changed from: private */
    public boolean mPendingOpenDrawerWithAnimation;
    /* access modifiers changed from: private */
    public boolean mPendingTranslate;
    private Drawable mScrollDivider;
    private int mScrollableViewId;
    private IScrollableView mScrollableViewWrapper;
    /* access modifiers changed from: private */
    public Scroller mScroller;
    /* access modifiers changed from: private */
    public int mState;
    private View mTargetView;
    private int mTargetViewId;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    /* access modifiers changed from: private */
    public int mVerticalRange;
    /* access modifiers changed from: private */
    public int mYOffset;

    private static class AdapterViewWrapper implements IScrollableView {
        private AdapterView<?> iAdapterView;

        public AdapterViewWrapper(AdapterView<?> adapterView) {
            this.iAdapterView = adapterView;
        }

        public boolean canScroll() {
            int firstVisiblePosition = this.iAdapterView.getFirstVisiblePosition();
            if (firstVisiblePosition > 0) {
                com.miui.gallery.util.Log.d("ScrollableViewDrawer", "canScrollDown true first visible %s", (Object) Integer.valueOf(firstVisiblePosition));
                return true;
            }
            int paddingTop = this.iAdapterView.getPaddingTop();
            int childCount = this.iAdapterView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (this.iAdapterView.getChildAt(i).getTop() < paddingTop) {
                    com.miui.gallery.util.Log.d("ScrollableViewDrawer", "canScrollDown true %s", (Object) Integer.valueOf(i));
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
        void onDrawerClosed(ScrollableViewDrawer scrollableViewDrawer);

        void onDrawerOpened(ScrollableViewDrawer scrollableViewDrawer);

        void onDrawerSlide(ScrollableViewDrawer scrollableViewDrawer, float f);
    }

    public interface IScrollableView {
        boolean canScroll();
    }

    private static class ScrollViewWrapper implements IScrollableView {
        private ScrollView iScrollView;

        public ScrollViewWrapper(ScrollView scrollView) {
            this.iScrollView = scrollView;
        }

        public boolean canScroll() {
            return this.iScrollView.getScrollY() > 0;
        }
    }

    public ScrollableViewDrawer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrollableViewDrawer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mActionPointerId = -1;
        this.mPendingTranslate = false;
        this.mPendingOpenDrawerBeforeLaidOut = false;
        this.mPendingOpenDrawerWithAnimation = false;
        this.mPendingOpenDrawerAnimEndListener = null;
        this.mPendingOpenDrawerLayoutChangeListener = null;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mOnContentViewLayoutChangeListener = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                com.miui.gallery.util.Log.d("ScrollableViewDrawer", "ContentView layout change, newBottom: %d, oldBottom: %d", Integer.valueOf(i4), Integer.valueOf(i8));
                if (i4 == ScrollableViewDrawer.this.mVerticalRange) {
                    return;
                }
                if (!ScrollableViewDrawer.this.mScroller.isFinished() || ScrollableViewDrawer.this.mState == 1) {
                    boolean unused = ScrollableViewDrawer.this.mPendingTranslate = true;
                    return;
                }
                int unused2 = ScrollableViewDrawer.this.mVerticalRange = i4;
                if (ScrollableViewDrawer.this.mState == 2) {
                    int unused3 = ScrollableViewDrawer.this.mYOffset = i4;
                    ScrollableViewDrawer.this.onTranslationYChange();
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollableViewDrawer, i, 0);
        this.mContentViewId = obtainStyledAttributes.getResourceId(0, 0);
        if (this.mContentViewId != 0) {
            this.mTargetViewId = obtainStyledAttributes.getResourceId(4, 0);
            if (this.mTargetViewId != 0) {
                this.mScrollableViewId = obtainStyledAttributes.getResourceId(3, 0);
                if (this.mScrollableViewId != 0) {
                    this.mScrollDivider = obtainStyledAttributes.getDrawable(2);
                    if (this.mScrollDivider == null) {
                        this.mScrollDivider = AttributeResolver.resolveDrawable(context, R.attr.scrollDivider);
                    }
                    Drawable drawable = this.mScrollDivider;
                    if (drawable != null) {
                        this.mDividerHeight = drawable.getIntrinsicHeight();
                    }
                    this.mDragEnabled = obtainStyledAttributes.getBoolean(1, true);
                    obtainStyledAttributes.recycle();
                    return;
                }
                throw new IllegalArgumentException("The scrollableView attribute is required and must refer to a valid child.");
            }
            throw new IllegalArgumentException("The targetView attribute is required and must refer to a valid child.");
        }
        throw new IllegalArgumentException("The contentView attribute is required and must refer to a valid child.");
    }

    /* access modifiers changed from: private */
    public void clearPendingOpenDrawerInfo() {
        if (this.mPendingOpenDrawerBeforeLaidOut) {
            this.mPendingOpenDrawerBeforeLaidOut = false;
            this.mPendingOpenDrawerWithAnimation = false;
            this.mPendingOpenDrawerAnimEndListener = null;
            View.OnLayoutChangeListener onLayoutChangeListener = this.mPendingOpenDrawerLayoutChangeListener;
            if (onLayoutChangeListener != null) {
                removeOnLayoutChangeListener(onLayoutChangeListener);
                this.mPendingOpenDrawerLayoutChangeListener = null;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0022  */
    private void computeStateChanged() {
        int i;
        int i2;
        int i3 = this.mYOffset;
        if (i3 == 0) {
            if (!this.mDetermineVerticalDrag) {
                i = 0;
                i2 = this.mState;
                if (i2 != i) {
                    this.mState = i;
                    notifyStateChanged();
                    return;
                } else if (i2 == 1) {
                    notifyStateChanged();
                    return;
                } else {
                    return;
                }
            }
        } else if (i3 == getVerticalRange() && !this.mDetermineVerticalDrag) {
            i = 2;
            i2 = this.mState;
            if (i2 != i) {
            }
        }
        i = 1;
        i2 = this.mState;
        if (i2 != i) {
        }
    }

    private int getVerticalRange() {
        if (this.mVerticalRange == 0) {
            this.mVerticalRange = this.mContentView.getMeasuredHeight();
        }
        return this.mVerticalRange;
    }

    private void internalOpenDrawer(boolean z, DrawerAnimEndListener drawerAnimEndListener) {
        this.mAnimEndListener = drawerAnimEndListener;
        if (z) {
            this.mScroller.startScroll(0, this.mYOffset, 0, getVerticalRange() - this.mYOffset, 800);
        } else {
            this.mScroller.forceFinished(true);
            this.mYOffset = getVerticalRange();
            onTranslationYChange();
        }
        invalidate();
    }

    private void notifyStateChanged() {
        DrawerListener drawerListener = this.mDrawerListener;
        if (drawerListener != null) {
            int i = this.mState;
            if (i == 0) {
                drawerListener.onDrawerClosed(this);
                if (this.mPendingTranslate) {
                    this.mVerticalRange = this.mContentView.getMeasuredHeight();
                    this.mPendingTranslate = false;
                }
            } else if (i == 1) {
                drawerListener.onDrawerSlide(this, ((float) this.mYOffset) / ((float) getVerticalRange()));
            } else if (i == 2) {
                drawerListener.onDrawerOpened(this);
                if (this.mPendingTranslate) {
                    this.mVerticalRange = this.mContentView.getMeasuredHeight();
                    this.mYOffset = this.mVerticalRange;
                    onTranslationYChange();
                    this.mPendingTranslate = false;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onTranslationYChange() {
        View view;
        if (this.mDragEnabled && (view = this.mTargetView) != null) {
            view.setTranslationY((float) this.mYOffset);
            computeStateChanged();
        }
    }

    private boolean shouldInterceptDown() {
        return this.mTargetView != null && this.mYOffset == 0 && !this.mScrollableViewWrapper.canScroll();
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mTargetView == null) {
            return;
        }
        if (this.mScroller.computeScrollOffset()) {
            this.mYOffset = this.mScroller.getCurrY();
            onTranslationYChange();
            postInvalidateOnAnimation();
            return;
        }
        DrawerAnimEndListener drawerAnimEndListener = this.mAnimEndListener;
        if (drawerAnimEndListener != null) {
            drawerAnimEndListener.onDrawerAnimEnd(isDrawerOpen());
            this.mAnimEndListener = null;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mDragEnabled) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActionPointerId);
                    if (findPointerIndex == -1) {
                        this.mActionPointerId = motionEvent.getPointerId(0);
                        findPointerIndex = 0;
                    }
                    float x = motionEvent.getX(findPointerIndex) - this.mDownX;
                    float y = motionEvent.getY(findPointerIndex) - this.mDownY;
                    if (!this.mDetermineVerticalDrag && !this.mDetermineHorizontal) {
                        if (this.mYOffset == getVerticalRange() && y < ((float) (-this.mTouchSlop))) {
                            this.mDetermineVerticalDrag = true;
                            com.miui.gallery.util.Log.d("ScrollableViewDrawer", "determine vertical up dragging");
                        } else if (y > ((float) this.mTouchSlop)) {
                            this.mDetermineVerticalDrag = shouldInterceptDown();
                            com.miui.gallery.util.Log.d("ScrollableViewDrawer", "determine vertical down dragging %s", (Object) Boolean.valueOf(this.mDetermineVerticalDrag));
                        }
                        if (this.mDetermineVerticalDrag) {
                            com.miui.gallery.util.Log.d("ScrollableViewDrawer", "determine vertical drag");
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                        if (Math.abs(y) > ((float) this.mTouchSlop) && !this.mDetermineHorizontal) {
                            this.mDetermineVertical = true;
                        }
                        if (Math.abs(x) > ((float) this.mTouchSlop) && !this.mDetermineVertical) {
                            this.mDetermineHorizontal = true;
                        }
                    }
                    if (!this.mDetermineVerticalDrag) {
                        this.mVelocityTracker.addMovement(motionEvent);
                        this.mLastMotionY = motionEvent.getY(findPointerIndex);
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        int actionIndex = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionIndex) == this.mActionPointerId) {
                            int i = actionIndex == 0 ? 1 : 0;
                            this.mActionPointerId = motionEvent.getPointerId(i);
                            this.mDownX = motionEvent.getX(i);
                            float y2 = motionEvent.getY(i);
                            this.mDownY = y2;
                            this.mLastMotionY = y2;
                        }
                    }
                }
            }
            if (!this.mDetermineVerticalDrag) {
                this.mVelocityTracker.clear();
            }
            this.mDetermineVerticalDrag = false;
            this.mDetermineHorizontal = false;
            this.mDetermineVertical = false;
        } else {
            this.mActionPointerId = motionEvent.getPointerId(0);
            this.mDetermineVerticalDrag = false;
            this.mVelocityTracker.clear();
            this.mVelocityTracker.addMovement(motionEvent);
            this.mDownX = motionEvent.getX(0);
            float y3 = motionEvent.getY(0);
            this.mDownY = y3;
            this.mLastMotionY = y3;
            this.mYOffset = (int) this.mTargetView.getTranslationY();
        }
        if (DEBUGGABLE) {
            com.miui.gallery.util.Log.d("ScrollableViewDrawer", "dispatchTouchEvent:action=%s, mYOffset=%s", Integer.valueOf(motionEvent.getActionMasked()), Integer.valueOf(this.mYOffset));
        }
        return super.dispatchTouchEvent(motionEvent) || this.mDetermineVerticalDrag;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        if (view == this.mContentView) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int top = view.getTop();
            int i = this.mYOffset + top;
            int i2 = this.mDividerHeight;
            if (i2 > 0) {
                this.mScrollDivider.setBounds(paddingLeft, i - i2, width, i);
                this.mScrollDivider.draw(canvas);
                i -= this.mDividerHeight;
            }
            canvas.save();
            canvas.clipRect(paddingLeft, top, width, i);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (view == this.mContentView) {
            canvas.restore();
        }
        return drawChild;
    }

    public boolean isDrawerOpen() {
        return this.mYOffset > 0;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mContentView = findViewById(this.mContentViewId);
        View view = this.mContentView;
        if (view == null) {
            throw new IllegalArgumentException("The contentView attribute is must refer to an existing child.");
        } else if (view.getParent() == this) {
            this.mContentView.addOnLayoutChangeListener(this.mOnContentViewLayoutChangeListener);
            this.mTargetView = findViewById(this.mTargetViewId);
            View view2 = this.mTargetView;
            if (view2 != null) {
                View findViewById = view2.findViewById(this.mScrollableViewId);
                if (findViewById == null) {
                    throw new IllegalArgumentException("The scrollableView attribute is must refer to an existing child of targetView.");
                } else if (findViewById instanceof IScrollableView) {
                    this.mScrollableViewWrapper = (IScrollableView) findViewById;
                } else if (findViewById instanceof ScrollView) {
                    this.mScrollableViewWrapper = new ScrollViewWrapper((ScrollView) findViewById);
                } else if (findViewById instanceof AdapterView) {
                    this.mScrollableViewWrapper = new AdapterViewWrapper((AdapterView) findViewById);
                } else {
                    throw new IllegalArgumentException("The scrollableView attribute is must refer to an ScrollView or AdapterView or IScrollableViewWrapper.");
                }
            } else {
                throw new IllegalArgumentException("The targetView attribute is must refer to an existing child.");
            }
        } else {
            throw new IllegalArgumentException("The contentView attribute is must be a direct child of ScrollableViewDrawer.");
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDragEnabled && this.mDetermineVerticalDrag;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                this.mVelocityTracker.addMovement(motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                int i = this.mYOffset;
                if (i == 0 || i == getVerticalRange()) {
                    computeStateChanged();
                } else {
                    if (this.mVelocityTracker.getYVelocity() > 5.0f) {
                        this.mScroller.startScroll(0, this.mYOffset, 0, getVerticalRange() - this.mYOffset);
                    } else {
                        Scroller scroller = this.mScroller;
                        int i2 = this.mYOffset;
                        scroller.startScroll(0, i2, 0, -i2);
                    }
                    invalidate();
                }
                this.mVelocityTracker.clear();
            } else if (actionMasked == 2) {
                this.mVelocityTracker.addMovement(motionEvent);
                int findPointerIndex = motionEvent.findPointerIndex(this.mActionPointerId);
                if (findPointerIndex == -1) {
                    this.mActionPointerId = motionEvent.getPointerId(0);
                    findPointerIndex = 0;
                }
                float y = motionEvent.getY(findPointerIndex);
                this.mYOffset = Math.max(0, Math.min(((int) (y - this.mLastMotionY)) + this.mYOffset, getVerticalRange()));
                this.mLastMotionY = y;
                onTranslationYChange();
                invalidate();
            } else if (actionMasked == 3) {
                int i3 = this.mYOffset;
                if (i3 == 0 || i3 == getVerticalRange()) {
                    computeStateChanged();
                } else {
                    Scroller scroller2 = this.mScroller;
                    int i4 = this.mYOffset;
                    scroller2.startScroll(0, i4, 0, -i4);
                    invalidate();
                }
                this.mVelocityTracker.clear();
            }
        }
        if (DEBUGGABLE) {
            com.miui.gallery.util.Log.w("ScrollableViewDrawer", "onTouchEvent:action=%s, mYOffset=%s", Integer.valueOf(motionEvent.getActionMasked()), Integer.valueOf(this.mYOffset));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void openDrawer() {
        openDrawer(false, (DrawerAnimEndListener) null);
    }

    public void openDrawer(boolean z, DrawerAnimEndListener drawerAnimEndListener) {
        if (isLaidOut()) {
            clearPendingOpenDrawerInfo();
            internalOpenDrawer(z, drawerAnimEndListener);
            return;
        }
        this.mPendingOpenDrawerBeforeLaidOut = true;
        this.mPendingOpenDrawerWithAnimation = z;
        this.mPendingOpenDrawerAnimEndListener = drawerAnimEndListener;
        this.mPendingOpenDrawerLayoutChangeListener = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                final boolean access$600 = ScrollableViewDrawer.this.mPendingOpenDrawerWithAnimation;
                final DrawerAnimEndListener access$700 = ScrollableViewDrawer.this.mPendingOpenDrawerAnimEndListener;
                ScrollableViewDrawer.this.clearPendingOpenDrawerInfo();
                ScrollableViewDrawer.this.post(new Runnable() {
                    public void run() {
                        ScrollableViewDrawer.this.openDrawer(access$600, access$700);
                    }
                });
            }
        };
        addOnLayoutChangeListener(this.mPendingOpenDrawerLayoutChangeListener);
    }

    public void setDragEnabled(boolean z) {
        this.mDragEnabled = z;
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.mDrawerListener = drawerListener;
    }
}
