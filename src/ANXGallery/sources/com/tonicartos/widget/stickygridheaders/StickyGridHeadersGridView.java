package com.tonicartos.widget.stickygridheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.collection.ArrayMap;
import com.google.common.collect.Lists;
import com.miui.gallery.R;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.photoview.ScrollableView;
import com.miui.gallery.view.GestureDetector;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapterWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StickyGridHeadersGridView extends GridView implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener, ScrollableView {
    /* access modifiers changed from: private */
    public static final String ERROR_PLATFORM = ("Error supporting platform " + Build.VERSION.SDK_INT + ".");
    static final String TAG = StickyGridHeadersGridView.class.getSimpleName();
    protected StickyGridHeadersBaseAdapterWrapper mAdapter;
    private boolean mAreHeadersSticky;
    private boolean mClipToPaddingHasBeenSet;
    private final Rect mClippingRect;
    private boolean mClippingToPadding;
    private long mCurrentHeaderId;
    protected boolean mDataChanged;
    private DataSetObserver mDataSetObserver;
    private ArrayList<FixedViewInfo> mFooterViewInfos;
    private GestureDetector mGestureDetector;
    GestureDetector.OnGestureListener mGestureListener;
    private int mHeaderBottomPosition;
    boolean mHeaderChildBeingPressed;
    private ArrayList<FixedViewInfo> mHeaderViewInfos;
    private boolean mHeadersIgnorePadding;
    private int mHorizontalSpacing;
    private boolean mMaskStickyHeaderRegion;
    protected int mMotionHeaderPosition;
    private float mMotionY;
    private OnHeaderClickListener mOnHeaderClickListener;
    private OnHeaderLongClickListener mOnHeaderLongClickListener;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener;
    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    public CheckForHeaderLongPress mPendingCheckForLongPress;
    public CheckForHeaderTap mPendingCheckForTap;
    private PerformHeaderClick mPerformHeaderClick;
    /* access modifiers changed from: private */
    public int mPreScrollState;
    private ListAdapter mRealAdapter;
    private int mRequestedNumColumns;
    private AbsListView.OnScrollListener mScrollListener;
    /* access modifiers changed from: private */
    public int mScrollState;
    private View mStickiedHeader;
    private int mStickyHeaderBackgroundAlpha;
    private Drawable mStickyHeaderBackgroundDrawable;
    protected int mTouchMode;
    /* access modifiers changed from: private */
    public Runnable mTouchModeReset;
    private int mTouchSlop;
    private int mVerticalSpacing;

    private class CheckForHeaderLongPress extends WindowRunnable implements Runnable {
        private CheckForHeaderLongPress() {
            super();
        }

        public void run() {
            StickyGridHeadersGridView stickyGridHeadersGridView = StickyGridHeadersGridView.this;
            if (stickyGridHeadersGridView.getHeaderAt(stickyGridHeadersGridView.mMotionHeaderPosition) != null) {
                if (sameWindow()) {
                    boolean z = StickyGridHeadersGridView.this.mDataChanged;
                }
                StickyGridHeadersGridView.this.mTouchMode = 2;
            }
        }
    }

    final class CheckForHeaderTap implements Runnable {
        CheckForHeaderTap() {
        }

        public void run() {
            if (StickyGridHeadersGridView.this.mTouchMode == 0) {
                StickyGridHeadersGridView stickyGridHeadersGridView = StickyGridHeadersGridView.this;
                stickyGridHeadersGridView.mTouchMode = 1;
                View headerAt = stickyGridHeadersGridView.getHeaderAt(stickyGridHeadersGridView.mMotionHeaderPosition);
                if (headerAt != null && !StickyGridHeadersGridView.this.mHeaderChildBeingPressed) {
                    if (!StickyGridHeadersGridView.this.mDataChanged) {
                        headerAt.setPressed(true);
                        StickyGridHeadersGridView.this.setPressed(true);
                        StickyGridHeadersGridView.this.refreshDrawableState();
                        int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                        if (StickyGridHeadersGridView.this.isLongClickable()) {
                            if (StickyGridHeadersGridView.this.mPendingCheckForLongPress == null) {
                                StickyGridHeadersGridView stickyGridHeadersGridView2 = StickyGridHeadersGridView.this;
                                stickyGridHeadersGridView2.mPendingCheckForLongPress = new CheckForHeaderLongPress();
                            }
                            StickyGridHeadersGridView.this.mPendingCheckForLongPress.rememberWindowAttachCount();
                            StickyGridHeadersGridView stickyGridHeadersGridView3 = StickyGridHeadersGridView.this;
                            stickyGridHeadersGridView3.postDelayed(stickyGridHeadersGridView3.mPendingCheckForLongPress, (long) longPressTimeout);
                            return;
                        }
                        StickyGridHeadersGridView.this.mTouchMode = 2;
                        return;
                    }
                    StickyGridHeadersGridView.this.mTouchMode = 2;
                }
            }
        }
    }

    public class FixedViewInfo {
        public Object data;
        public View view;

        public FixedViewInfo() {
        }
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(AdapterView<?> adapterView, View view, long j);
    }

    public interface OnHeaderLongClickListener {
    }

    private class PerformHeaderClick extends WindowRunnable implements Runnable {
        int mClickMotionPosition;

        private PerformHeaderClick() {
            super();
        }

        public void run() {
            int i;
            View headerAt;
            if (!StickyGridHeadersGridView.this.mDataChanged && StickyGridHeadersGridView.this.mAdapter != null && StickyGridHeadersGridView.this.mAdapter.getCount() > 0 && (i = this.mClickMotionPosition) != -1 && i < StickyGridHeadersGridView.this.mAdapter.getCount() && sameWindow() && (headerAt = StickyGridHeadersGridView.this.getHeaderAt(this.mClickMotionPosition)) != null) {
                StickyGridHeadersGridView stickyGridHeadersGridView = StickyGridHeadersGridView.this;
                stickyGridHeadersGridView.performHeaderClick(headerAt, stickyGridHeadersGridView.headerViewPositionToId(this.mClickMotionPosition));
            }
        }
    }

    class RuntimePlatformSupportException extends RuntimeException {
        private static final long serialVersionUID = -6512098808936536538L;

        public RuntimePlatformSupportException(Exception exc) {
            super(StickyGridHeadersGridView.ERROR_PLATFORM, exc);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean areHeadersSticky;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.areHeadersSticky = parcel.readByte() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.areHeadersSticky + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.areHeadersSticky ? (byte) 1 : 0);
        }
    }

    private class WindowRunnable {
        private int mOriginalAttachCount;

        private WindowRunnable() {
        }

        public void rememberWindowAttachCount() {
            this.mOriginalAttachCount = StickyGridHeadersGridView.this.getWindowAttachCount();
        }

        public boolean sameWindow() {
            return StickyGridHeadersGridView.this.hasWindowFocus() && StickyGridHeadersGridView.this.getWindowAttachCount() == this.mOriginalAttachCount;
        }
    }

    public StickyGridHeadersGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAreHeadersSticky = true;
        this.mClippingRect = new Rect();
        this.mCurrentHeaderId = -1;
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                StickyGridHeadersGridView.this.reset();
            }

            public void onInvalidated() {
                StickyGridHeadersGridView.this.reset();
            }
        };
        this.mMaskStickyHeaderRegion = false;
        this.mStickyHeaderBackgroundAlpha = 250;
        this.mPreScrollState = 0;
        this.mScrollState = 0;
        this.mHeaderChildBeingPressed = false;
        this.mHeaderViewInfos = Lists.newArrayList();
        this.mFooterViewInfos = Lists.newArrayList();
        this.mGestureListener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                int pointToPosition;
                Log.d(StickyGridHeadersGridView.TAG, "onSingleTapUp mPreScrollState %s, mScrollState %s", Integer.valueOf(StickyGridHeadersGridView.this.mPreScrollState), Integer.valueOf(StickyGridHeadersGridView.this.mScrollState));
                if ((StickyGridHeadersGridView.this.mPreScrollState == 0 || StickyGridHeadersGridView.this.mScrollState != 0) && (pointToPosition = StickyGridHeadersGridView.this.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && pointToPosition < StickyGridHeadersGridView.this.mAdapter.getCount() && StickyGridHeadersGridView.this.mAdapter.isEnabled(pointToPosition)) {
                    StickyGridHeadersGridView stickyGridHeadersGridView = StickyGridHeadersGridView.this;
                    View childAt = stickyGridHeadersGridView.getChildAt(pointToPosition - stickyGridHeadersGridView.getFirstVisiblePosition());
                    if (childAt != null) {
                        StickyGridHeadersGridView stickyGridHeadersGridView2 = StickyGridHeadersGridView.this;
                        stickyGridHeadersGridView2.onItemClick(stickyGridHeadersGridView2, childAt, pointToPosition, stickyGridHeadersGridView2.mAdapter.getItemId(pointToPosition));
                    }
                }
                return super.onSingleTapUp(motionEvent);
            }
        };
        super.setOnScrollListener(this);
        setVerticalFadingEdgeEnabled(false);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private int findMotionHeader(float f) {
        if (this.mStickiedHeader != null && f <= ((float) this.mHeaderBottomPosition)) {
            return -2;
        }
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                View childAt = getChildAt(i);
                int bottom = childAt.getBottom();
                int top = childAt.getTop();
                if (f <= ((float) bottom) && f >= ((float) top)) {
                    return i;
                }
            }
            firstVisiblePosition += getNumColumns();
            i += getNumColumns();
        }
        return -1;
    }

    private int getHeaderHeight() {
        View view = this.mStickiedHeader;
        if (view != null) {
            return view.getMeasuredHeight();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public long headerViewPositionToId(int i) {
        return i == -2 ? this.mCurrentHeaderId : this.mAdapter.getHeaderId(getFirstVisiblePosition() + i);
    }

    private void measureHeader() {
        if (this.mStickiedHeader != null) {
            int makeMeasureSpec = this.mHeadersIgnorePadding ? View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824) : View.MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            ViewGroup.LayoutParams layoutParams = this.mStickiedHeader.getLayoutParams();
            int makeMeasureSpec2 = (layoutParams == null || layoutParams.height <= 0) ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            this.mStickiedHeader.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mStickiedHeader.measure(makeMeasureSpec, makeMeasureSpec2);
            if (this.mHeadersIgnorePadding) {
                this.mStickiedHeader.layout(getLeft(), 0, getRight(), this.mStickiedHeader.getMeasuredHeight());
            } else {
                this.mStickiedHeader.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.mStickiedHeader.getMeasuredHeight());
            }
        }
    }

    private boolean removeFixedViewInfo(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (arrayList.get(i).view == view) {
                arrayList.remove(i);
                detachHeader(view);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void reset() {
        this.mHeaderBottomPosition = 0;
        swapStickiedHeader((View) null);
        this.mCurrentHeaderId = Long.MIN_VALUE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    private void scrollChanged(int i) {
        int i2;
        long j;
        int childCount;
        long j2;
        StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper = this.mAdapter;
        if (stickyGridHeadersBaseAdapterWrapper != null && stickyGridHeadersBaseAdapterWrapper.getCount() != 0 && this.mAreHeadersSticky && getChildAt(0) != null) {
            if (this.mAdapter.getHeaderId(i) < ((long) this.mHeaderViewInfos.size())) {
                this.mCurrentHeaderId = -1;
                this.mStickiedHeader = null;
                return;
            }
            int numColumns = getNumColumns();
            int i3 = i - numColumns;
            if (i3 < 0) {
                i3 = i;
            }
            int i4 = i + numColumns;
            if (i4 >= this.mAdapter.getCount()) {
                i4 = i;
            }
            int i5 = this.mVerticalSpacing;
            if (i5 == 0) {
                j = this.mAdapter.getHeaderId(i);
            } else {
                if (i5 < 0) {
                    this.mAdapter.getHeaderId(i);
                    if (getChildAt(numColumns).getTop() <= 0) {
                        j2 = this.mAdapter.getHeaderId(i4);
                        i2 = i4;
                    } else {
                        j = this.mAdapter.getHeaderId(i);
                    }
                } else {
                    int top = getChildAt(0).getTop();
                    if (top <= 0 || top >= this.mVerticalSpacing) {
                        j = this.mAdapter.getHeaderId(i);
                    } else {
                        j2 = this.mAdapter.getHeaderId(i3);
                        i2 = i3;
                    }
                }
                j = j2;
                if (this.mCurrentHeaderId != j) {
                    swapStickiedHeader(this.mAdapter.getHeaderView(i2, this.mStickiedHeader, this));
                    measureHeader();
                    this.mCurrentHeaderId = j;
                }
                childCount = getChildCount();
                if (childCount == 0) {
                    int i6 = 99999;
                    View view = null;
                    for (int i7 = 0; i7 < childCount; i7 += numColumns) {
                        View childAt = super.getChildAt(i7);
                        int top2 = this.mClippingToPadding ? childAt.getTop() - getPaddingTop() : childAt.getTop();
                        if (top2 >= 0 && this.mAdapter.getItemId(getPositionForView(childAt)) == -1 && top2 < i6) {
                            view = childAt;
                            i6 = top2;
                        }
                    }
                    int headerHeight = getHeaderHeight();
                    if (view == null) {
                        this.mHeaderBottomPosition = headerHeight;
                        if (this.mClippingToPadding) {
                            this.mHeaderBottomPosition += getPaddingTop();
                            return;
                        }
                        return;
                    } else if (i == 0 && super.getChildAt(0).getTop() > 0 && !this.mClippingToPadding) {
                        this.mHeaderBottomPosition = 0;
                        return;
                    } else if (this.mClippingToPadding) {
                        this.mHeaderBottomPosition = Math.min(view.getTop(), getPaddingTop() + headerHeight);
                        this.mHeaderBottomPosition = this.mHeaderBottomPosition < getPaddingTop() ? headerHeight + getPaddingTop() : this.mHeaderBottomPosition;
                        return;
                    } else {
                        this.mHeaderBottomPosition = Math.min(view.getTop(), headerHeight);
                        int i8 = this.mHeaderBottomPosition;
                        if (i8 < 0) {
                            i8 = headerHeight;
                        }
                        this.mHeaderBottomPosition = i8;
                        return;
                    }
                } else {
                    return;
                }
            }
            i2 = i;
            if (this.mCurrentHeaderId != j) {
            }
            childCount = getChildCount();
            if (childCount == 0) {
            }
        }
    }

    private void swapStickiedHeader(View view) {
        detachHeader(this.mStickiedHeader);
        attachHeader(view);
        this.mStickiedHeader = view;
        View view2 = this.mStickiedHeader;
        if (view2 != null) {
            Drawable drawable = this.mStickyHeaderBackgroundDrawable;
            if (drawable != null) {
                view2.setBackground(drawable);
            } else {
                view2.setBackgroundResource(R.drawable.sticky_header_bg);
            }
            if (getStickyHeaderIsTranscluent() && this.mStickiedHeader.getBackground() != null) {
                this.mStickiedHeader.getBackground().setAlpha(this.mStickyHeaderBackgroundAlpha);
            }
        }
    }

    private MotionEvent transformEvent(MotionEvent motionEvent, int i) {
        if (i == -2) {
            return motionEvent;
        }
        View childAt = getChildAt(i);
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(0.0f, (float) (-childAt.getTop()));
        return obtain;
    }

    public void addFooterView(View view) {
        addFooterView(view, (Object) null);
    }

    public void addFooterView(View view, Object obj) {
        int size = this.mFooterViewInfos.size();
        int i = 0;
        while (i < size) {
            if (this.mFooterViewInfos.get(i).view != view) {
                i++;
            } else {
                return;
            }
        }
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = view;
        fixedViewInfo.data = obj;
        this.mFooterViewInfos.add(fixedViewInfo);
        attachHeader(view);
        StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper = this.mAdapter;
        if (stickyGridHeadersBaseAdapterWrapper != null) {
            stickyGridHeadersBaseAdapterWrapper.setFooter(this.mFooterViewInfos);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void addHeaderView(View view) {
        addHeaderView(view, (Object) null);
    }

    public void addHeaderView(View view, Object obj) {
        int size = this.mHeaderViewInfos.size();
        int i = 0;
        while (i < size) {
            if (this.mHeaderViewInfos.get(i).view != view) {
                i++;
            } else {
                return;
            }
        }
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = view;
        fixedViewInfo.data = obj;
        this.mHeaderViewInfos.add(fixedViewInfo);
        attachHeader(view);
        StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper = this.mAdapter;
        if (stickyGridHeadersBaseAdapterWrapper != null) {
            stickyGridHeadersBaseAdapterWrapper.setHeader(this.mHeaderViewInfos);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void attachHeader(View view) {
        if (view != null && isAttachedToWindow()) {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                declaredField.setAccessible(true);
                Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", new Class[]{Class.forName("android.view.View$AttachInfo"), Integer.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[]{declaredField.get(this), 8});
            } catch (NoSuchMethodException e) {
                throw new RuntimePlatformSupportException(e);
            } catch (ClassNotFoundException e2) {
                throw new RuntimePlatformSupportException(e2);
            } catch (IllegalArgumentException e3) {
                throw new RuntimePlatformSupportException(e3);
            } catch (IllegalAccessException e4) {
                throw new RuntimePlatformSupportException(e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimePlatformSupportException(e5);
            } catch (NoSuchFieldException e6) {
                throw new RuntimePlatformSupportException(e6);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void detachHeader(View view) {
        if (view != null) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[0]);
            } catch (NoSuchMethodException e) {
                throw new RuntimePlatformSupportException(e);
            } catch (IllegalArgumentException e2) {
                throw new RuntimePlatformSupportException(e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimePlatformSupportException(e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimePlatformSupportException(e4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        Canvas canvas2 = canvas;
        if (Build.VERSION.SDK_INT < 8) {
            scrollChanged(getFirstVisiblePosition());
        }
        int numColumns = getNumColumns();
        View view = this.mStickiedHeader;
        char c = 1;
        char c2 = 0;
        boolean z = view != null && this.mAreHeadersSticky && view.getVisibility() == 0;
        int headerHeight = getHeaderHeight();
        int i5 = this.mHeaderBottomPosition - headerHeight;
        if (z && this.mMaskStickyHeaderRegion) {
            if (this.mHeadersIgnorePadding) {
                Rect rect = this.mClippingRect;
                rect.left = 0;
                rect.right = getWidth();
            } else {
                this.mClippingRect.left = getPaddingLeft();
                this.mClippingRect.right = getWidth() - getPaddingRight();
            }
            Rect rect2 = this.mClippingRect;
            rect2.top = this.mHeaderBottomPosition;
            rect2.bottom = getHeight();
            canvas.save();
            canvas2.clipRect(this.mClippingRect);
        }
        super.dispatchDraw(canvas);
        ArrayMap arrayMap = new ArrayMap();
        int count = getAdapter() == null ? -1 : getAdapter().getCount();
        int count2 = getRealAdapter() == null ? -1 : getRealAdapter().getCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int i6 = 0;
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            long itemIdAtPosition = getItemIdAtPosition(firstVisiblePosition);
            if (itemIdAtPosition == -1) {
                arrayMap.put(Integer.valueOf(i6), 0);
            } else if (itemIdAtPosition == -4) {
                arrayMap.put(Integer.valueOf(i6), 1);
            }
            firstVisiblePosition += numColumns;
            i6 += numColumns;
        }
        int i7 = 0;
        while (i7 < arrayMap.size()) {
            View childAt = getChildAt(((Integer) arrayMap.keyAt(i7)).intValue());
            try {
                View view2 = (View) childAt.getTag();
                if (view2 == null) {
                    int intValue = ((Integer) arrayMap.keyAt(i7)).intValue();
                    int firstVisiblePosition2 = getFirstVisiblePosition() + intValue;
                    String str = TAG;
                    Object[] objArr = new Object[5];
                    objArr[c2] = Integer.valueOf(getChildCount());
                    objArr[c] = Integer.valueOf(count);
                    objArr[2] = Integer.valueOf(getAdapter() == null ? -1 : getAdapter().getCount());
                    objArr[3] = Integer.valueOf(count2);
                    objArr[4] = Integer.valueOf(getRealAdapter() == null ? -1 : getRealAdapter().getCount());
                    Log.i(str, "view count [%d], adapter count[%d/%d], real adapter count[%d/%d]", objArr);
                    String str2 = "null";
                    Log.w(TAG, "current[layout: %d|adapter: %d] item is %s", Integer.valueOf(intValue), Integer.valueOf(firstVisiblePosition2), getItemAtPosition(firstVisiblePosition2) == null ? str2 : "not null");
                    String simpleName = childAt.getClass().getSimpleName();
                    if (childAt.getTag() != null) {
                        str2 = childAt.getTag().getClass().getSimpleName();
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("header_class/tag_class", simpleName + "/" + str2);
                    GallerySamplingStatHelper.recordErrorEvent("error_track", "sticky_grid_headers_get_tag_npe", hashMap);
                } else if (view2.getVisibility() == 0) {
                    if (((Integer) arrayMap.valueAt(i7)).intValue() == 0) {
                        if (((long) ((StickyGridHeadersBaseAdapterWrapper.HeaderFillerView) childAt).getHeaderId()) == this.mCurrentHeaderId && childAt.getTop() < 0 && this.mAreHeadersSticky) {
                        }
                    }
                    int makeMeasureSpec = this.mHeadersIgnorePadding ? View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824) : View.MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
                    int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    view2.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    view2.measure(makeMeasureSpec, makeMeasureSpec2);
                    if (this.mHeadersIgnorePadding) {
                        view2.layout(getLeft(), 0, getRight(), childAt.getHeight());
                    } else {
                        view2.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), childAt.getHeight());
                    }
                    if (this.mHeadersIgnorePadding) {
                        Rect rect3 = this.mClippingRect;
                        rect3.left = 0;
                        rect3.right = getWidth();
                    } else {
                        this.mClippingRect.left = getPaddingLeft();
                        this.mClippingRect.right = getWidth() - getPaddingRight();
                    }
                    this.mClippingRect.bottom = childAt.getBottom();
                    this.mClippingRect.top = childAt.getTop();
                    canvas.save();
                    canvas2.clipRect(this.mClippingRect);
                    if (this.mHeadersIgnorePadding) {
                        canvas2.translate(0.0f, (float) childAt.getTop());
                    } else {
                        canvas2.translate((float) getPaddingLeft(), (float) childAt.getTop());
                    }
                    view2.draw(canvas2);
                    canvas.restore();
                }
                i7++;
                c = 1;
                c2 = 0;
            } catch (Exception unused) {
                return;
            }
        }
        if (z && this.mMaskStickyHeaderRegion) {
            canvas.restore();
        } else if (!z) {
            return;
        }
        if (this.mStickiedHeader.getWidth() != (this.mHeadersIgnorePadding ? getWidth() : (getWidth() - getPaddingLeft()) - getPaddingRight())) {
            if (this.mHeadersIgnorePadding) {
                i2 = 1073741824;
                i3 = View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
            } else {
                i2 = 1073741824;
                i3 = View.MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            }
            ViewGroup.LayoutParams layoutParams = this.mStickiedHeader.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                i = 0;
                i4 = View.MeasureSpec.makeMeasureSpec(0, 0);
            } else {
                i4 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, i2);
                i = 0;
            }
            this.mStickiedHeader.measure(View.MeasureSpec.makeMeasureSpec(i, i), View.MeasureSpec.makeMeasureSpec(i, i));
            this.mStickiedHeader.measure(i3, i4);
            if (this.mHeadersIgnorePadding) {
                this.mStickiedHeader.layout(getLeft(), i, getRight(), this.mStickiedHeader.getHeight());
            } else {
                this.mStickiedHeader.layout(getLeft() + getPaddingLeft(), i, getRight() - getPaddingRight(), this.mStickiedHeader.getHeight());
            }
        } else {
            i = 0;
        }
        if (this.mHeadersIgnorePadding) {
            Rect rect4 = this.mClippingRect;
            rect4.left = i;
            rect4.right = getWidth();
        } else {
            this.mClippingRect.left = getPaddingLeft();
            this.mClippingRect.right = getWidth() - getPaddingRight();
        }
        Rect rect5 = this.mClippingRect;
        rect5.bottom = i5 + headerHeight;
        if (this.mClippingToPadding) {
            rect5.top = getPaddingTop();
        } else {
            rect5.top = 0;
        }
        canvas.save();
        canvas2.clipRect(this.mClippingRect);
        if (this.mHeadersIgnorePadding) {
            canvas2.translate(0.0f, (float) i5);
        } else {
            canvas2.translate((float) getPaddingLeft(), (float) i5);
        }
        if (!getStickyHeaderIsTranscluent() && this.mHeaderBottomPosition != headerHeight) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (this.mHeaderBottomPosition * 255) / headerHeight, 31);
        }
        this.mStickiedHeader.draw(canvas2);
        if (!getStickyHeaderIsTranscluent() && this.mHeaderBottomPosition != headerHeight) {
            canvas.restore();
        }
        canvas.restore();
    }

    public View getChildViewByItemIndex(int i) {
        int itemPositionByItemIndex = this.mAdapter.getItemPositionByItemIndex(i);
        if (itemPositionByItemIndex - super.getFirstVisiblePosition() < 0 || itemPositionByItemIndex > super.getLastVisiblePosition()) {
            return null;
        }
        return getChildAt(itemPositionByItemIndex - super.getFirstVisiblePosition());
    }

    public int getGroupIndexByItemIndex(int i) {
        return this.mAdapter.getGroupIndexByItemIndex(i);
    }

    public int getGroupItemStartIndex(int i) {
        return this.mAdapter.getGroupItemStartIndex(i);
    }

    public int getGroupItemsCount(int i) {
        return this.mAdapter.getCountForHeader(i);
    }

    public View getHeaderAt(int i) {
        if (i == -2) {
            return this.mStickiedHeader;
        }
        try {
            return (View) getChildAt(i).getTag();
        } catch (Exception unused) {
            return null;
        }
    }

    public int getHeaderNum() {
        return this.mAdapter.getHeaderNum();
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewInfos.size();
    }

    public int getItemIndexByItemPosition(int i) {
        return this.mAdapter.translatePosition(i).mPosition;
    }

    public ListAdapter getRealAdapter() {
        return this.mRealAdapter;
    }

    public int getRequestedNumColumns() {
        return this.mRequestedNumColumns;
    }

    public View getStickiedHeader() {
        return this.mStickiedHeader;
    }

    public boolean getStickyHeaderIsTranscluent() {
        return !this.mMaskStickyHeaderRegion;
    }

    public void notifyChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<FixedViewInfo> it = this.mHeaderViewInfos.iterator();
        while (it.hasNext()) {
            attachHeader(it.next().view);
        }
        Iterator<FixedViewInfo> it2 = this.mFooterViewInfos.iterator();
        while (it2.hasNext()) {
            attachHeader(it2.next().view);
        }
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(getContext(), this.mGestureListener);
            this.mGestureDetector.setIsDoubleTapEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Iterator<FixedViewInfo> it = this.mHeaderViewInfos.iterator();
        while (it.hasNext()) {
            detachHeader(it.next().view);
        }
        Iterator<FixedViewInfo> it2 = this.mFooterViewInfos.iterator();
        while (it2.hasNext()) {
            detachHeader(it2.next().view);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int findMotionHeader;
        View headerAt;
        if (motionEvent.getActionMasked() != 0 || (findMotionHeader = findMotionHeader((float) ((int) motionEvent.getY()))) == -1 || this.mScrollState == 2 || (headerAt = getHeaderAt(findMotionHeader)) == null || !headerAt.dispatchTouchEvent(transformEvent(motionEvent, findMotionHeader))) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        Log.d(TAG, "header child handled event");
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = this.mAdapter.translatePosition(i).mPosition;
        if (i2 < 0) {
            Log.d(TAG, "position is invalid, skip");
            return;
        }
        AdapterView.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(adapterView, view, i2, j);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = this.mAdapter.translatePosition(i).mPosition;
        if (i2 >= 0) {
            return this.mOnItemLongClickListener.onItemLongClick(adapterView, view, i2, j);
        }
        Log.d(TAG, "position is invalid, skip");
        return true;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = this.mAdapter.translatePosition(i).mPosition;
        if (i2 < 0) {
            Log.d(TAG, "position is invalid, skip");
        }
        this.mOnItemSelectedListener.onItemSelected(adapterView, view, i2, j);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper;
        measureHeader();
        int numColumns = getNumColumns();
        super.onMeasure(i, i2);
        int numColumns2 = getNumColumns();
        if (((numColumns == -1 && numColumns2 != this.mRequestedNumColumns) || (numColumns != -1 && numColumns2 != numColumns)) && (stickyGridHeadersBaseAdapterWrapper = this.mAdapter) != null) {
            stickyGridHeadersBaseAdapterWrapper.onColumnChanged();
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        this.mOnItemSelectedListener.onNothingSelected(adapterView);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mAreHeadersSticky = savedState.areHeadersSticky;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.areHeadersSticky = this.mAreHeadersSticky;
        return savedState;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper;
        if (!(this.mScrollListener == null || (stickyGridHeadersBaseAdapterWrapper = this.mAdapter) == null)) {
            StickyGridHeadersBaseAdapterWrapper.VisibleInfo translateVisibleInfo = stickyGridHeadersBaseAdapterWrapper.translateVisibleInfo(i, i2);
            this.mScrollListener.onScroll(absListView, translateVisibleInfo.mFirstPosition, translateVisibleInfo.mVisibleCount, this.mRealAdapter.getCount());
        }
        if (Build.VERSION.SDK_INT >= 8) {
            scrollChanged(i);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i);
        }
        Log.d(TAG, "scrollState changed %s", (Object) Integer.valueOf(i));
        this.mScrollState = i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int action = motionEvent.getAction();
        boolean z = this.mHeaderChildBeingPressed;
        if (z) {
            View headerAt = getHeaderAt(this.mMotionHeaderPosition);
            int i2 = this.mMotionHeaderPosition;
            final View childAt = i2 == -2 ? headerAt : getChildAt(i2);
            if (action == 1 || action == 3) {
                this.mHeaderChildBeingPressed = false;
            }
            if (headerAt != null) {
                headerAt.dispatchTouchEvent(transformEvent(motionEvent, this.mMotionHeaderPosition));
                headerAt.invalidate();
                headerAt.postDelayed(new Runnable() {
                    public void run() {
                        StickyGridHeadersGridView.this.invalidate(0, childAt.getTop(), StickyGridHeadersGridView.this.getWidth(), childAt.getTop() + childAt.getHeight());
                    }
                }, (long) ViewConfiguration.getPressedStateDuration());
                invalidate(0, childAt.getTop(), getWidth(), childAt.getTop() + childAt.getHeight());
            }
        }
        int i3 = action & 255;
        if (i3 == 0) {
            if (this.mPendingCheckForTap == null) {
                this.mPendingCheckForTap = new CheckForHeaderTap();
            }
            postDelayed(this.mPendingCheckForTap, (long) ViewConfiguration.getTapTimeout());
            float y = (float) ((int) motionEvent.getY());
            this.mMotionY = y;
            this.mMotionHeaderPosition = findMotionHeader(y);
            int i4 = this.mMotionHeaderPosition;
            if (!(i4 == -1 || this.mScrollState == 2)) {
                View headerAt2 = getHeaderAt(i4);
                if (headerAt2 != null) {
                    if (headerAt2.dispatchTouchEvent(transformEvent(motionEvent, this.mMotionHeaderPosition))) {
                        this.mHeaderChildBeingPressed = true;
                        headerAt2.setPressed(true);
                    }
                    headerAt2.invalidate();
                    int i5 = this.mMotionHeaderPosition;
                    if (i5 != -2) {
                        headerAt2 = getChildAt(i5);
                    }
                    invalidate(0, headerAt2.getTop(), getWidth(), headerAt2.getTop() + headerAt2.getHeight());
                }
                this.mTouchMode = 0;
                return true;
            }
        } else if (i3 == 1) {
            int i6 = this.mTouchMode;
            if (i6 == -2) {
                this.mTouchMode = -1;
                return true;
            } else if (!(i6 == -1 || (i = this.mMotionHeaderPosition) == -1)) {
                final View headerAt3 = getHeaderAt(i);
                Log.d(TAG, "wasHeaderChildBeingPressed %s", (Object) Boolean.valueOf(z));
                if (!z) {
                    if (headerAt3 != null) {
                        if (this.mTouchMode != 0) {
                            headerAt3.setPressed(false);
                        }
                        if (this.mPerformHeaderClick == null) {
                            this.mPerformHeaderClick = new PerformHeaderClick();
                        }
                        final PerformHeaderClick performHeaderClick = this.mPerformHeaderClick;
                        performHeaderClick.mClickMotionPosition = this.mMotionHeaderPosition;
                        performHeaderClick.rememberWindowAttachCount();
                        int i7 = this.mTouchMode;
                        if (i7 == 0 || i7 == 1) {
                            Handler handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.mTouchMode == 0 ? this.mPendingCheckForTap : this.mPendingCheckForLongPress);
                            }
                            if (!this.mDataChanged) {
                                this.mTouchMode = 1;
                                headerAt3.setPressed(true);
                                setPressed(true);
                                Runnable runnable = this.mTouchModeReset;
                                if (runnable != null) {
                                    removeCallbacks(runnable);
                                }
                                this.mTouchModeReset = new Runnable() {
                                    public void run() {
                                        StickyGridHeadersGridView stickyGridHeadersGridView = StickyGridHeadersGridView.this;
                                        stickyGridHeadersGridView.mMotionHeaderPosition = -1;
                                        Runnable unused = stickyGridHeadersGridView.mTouchModeReset = null;
                                        StickyGridHeadersGridView.this.mTouchMode = -1;
                                        headerAt3.setPressed(false);
                                        StickyGridHeadersGridView.this.setPressed(false);
                                        headerAt3.invalidate();
                                        StickyGridHeadersGridView.this.invalidate(0, headerAt3.getTop(), StickyGridHeadersGridView.this.getWidth(), headerAt3.getHeight());
                                        if (!StickyGridHeadersGridView.this.mDataChanged) {
                                            performHeaderClick.run();
                                        }
                                    }
                                };
                                postDelayed(this.mTouchModeReset, (long) ViewConfiguration.getPressedStateDuration());
                            } else {
                                this.mTouchMode = -1;
                            }
                        } else if (!this.mDataChanged) {
                            performHeaderClick.run();
                        }
                    }
                    this.mTouchMode = -1;
                } else {
                    this.mTouchMode = -1;
                    return true;
                }
            }
        } else if (i3 == 2 && this.mMotionHeaderPosition != -1 && Math.abs(motionEvent.getY() - this.mMotionY) > ((float) this.mTouchSlop)) {
            this.mTouchMode = -1;
            View headerAt4 = getHeaderAt(this.mMotionHeaderPosition);
            if (headerAt4 != null) {
                headerAt4.setPressed(false);
                headerAt4.invalidate();
            }
            Handler handler2 = getHandler();
            if (handler2 != null) {
                handler2.removeCallbacks(this.mPendingCheckForLongPress);
            }
            this.mMotionHeaderPosition = -1;
        }
        this.mPreScrollState = this.mScrollState;
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        this.mGestureDetector.onTouchEvent(motionEvent);
        return onTouchEvent;
    }

    public boolean performHeaderClick(View view, long j) {
        if (this.mOnHeaderClickListener == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.mOnHeaderClickListener.onHeaderClick(this, view, j);
        return true;
    }

    public boolean removeFooterView(View view) {
        if (this.mFooterViewInfos.isEmpty() || this.mAdapter == null || !removeFixedViewInfo(view, this.mFooterViewInfos)) {
            return false;
        }
        this.mAdapter.setFooter(this.mFooterViewInfos);
        this.mAdapter.notifyDataSetChanged();
        return true;
    }

    public boolean removeHeaderView(View view) {
        if (this.mHeaderViewInfos.isEmpty() || this.mAdapter == null || !removeFixedViewInfo(view, this.mHeaderViewInfos)) {
            return false;
        }
        this.mAdapter.setHeader(this.mHeaderViewInfos);
        this.mAdapter.notifyDataSetChanged();
        return true;
    }

    public void setAdapter(ListAdapter listAdapter) {
        StickyGridHeadersBaseAdapter stickyGridHeadersBaseAdapter;
        DataSetObserver dataSetObserver;
        if (this.mRealAdapter != listAdapter) {
            StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper = this.mAdapter;
            if (!(stickyGridHeadersBaseAdapterWrapper == null || (dataSetObserver = this.mDataSetObserver) == null)) {
                stickyGridHeadersBaseAdapterWrapper.unregisterDataSetObserver(dataSetObserver);
            }
            if (!this.mClipToPaddingHasBeenSet) {
                this.mClippingToPadding = true;
            }
            this.mRealAdapter = listAdapter;
            if (listAdapter instanceof StickyGridHeadersBaseAdapter) {
                stickyGridHeadersBaseAdapter = (StickyGridHeadersBaseAdapter) listAdapter;
            } else {
                stickyGridHeadersBaseAdapter = listAdapter instanceof StickyGridHeadersSimpleAdapter ? new StickyGridHeadersSimpleAdapterWrapper((StickyGridHeadersSimpleAdapter) listAdapter) : new StickyGridHeadersListAdapterWrapper(listAdapter);
            }
            this.mAdapter = new StickyGridHeadersBaseAdapterWrapper(getContext(), this, stickyGridHeadersBaseAdapter, this.mHeaderViewInfos, this.mFooterViewInfos);
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            reset();
            super.setAdapter(this.mAdapter);
        }
    }

    public void setAreHeadersSticky(boolean z) {
        if (z != this.mAreHeadersSticky) {
            this.mAreHeadersSticky = z;
            requestLayout();
        }
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.mClippingToPadding = z;
        this.mClipToPaddingHasBeenSet = true;
    }

    public void setHeadersIgnorePadding(boolean z) {
        this.mHeadersIgnorePadding = z;
    }

    public void setHorizontalSpacing(int i) {
        super.setHorizontalSpacing(i);
        this.mHorizontalSpacing = i;
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.mRequestedNumColumns = i;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mOnHeaderClickListener = onHeaderClickListener;
    }

    public void setOnHeaderLongClickListener(OnHeaderLongClickListener onHeaderLongClickListener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.mOnHeaderLongClickListener = onHeaderLongClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        super.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (Build.VERSION.SDK_INT >= 26 && view.isAccessibilityFocused()) {
                    StickyGridHeadersGridView.this.onItemClick(adapterView, view, i, j);
                }
            }
        });
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
        super.setOnItemLongClickListener(this);
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
        super.setOnItemSelectedListener(this);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setStickyHeaderBackgroundDrawable(Drawable drawable) {
        this.mStickyHeaderBackgroundDrawable = drawable;
    }

    public void setStickyHeaderIsTranscluent(int i) {
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException("The value of alpha out of range.");
        }
        this.mMaskStickyHeaderRegion = false;
        this.mStickyHeaderBackgroundAlpha = i;
    }

    public void setStickyHeaderIsTranscluent(boolean z) {
        this.mMaskStickyHeaderRegion = !z;
    }

    public void setVerticalSpacing(int i) {
        super.setVerticalSpacing(i);
        this.mVerticalSpacing = i;
    }

    public void viewToPosition(int i) {
        setSelection(Math.max(0, this.mAdapter.getItemPositionByItemIndex(i) - ((getLastVisiblePosition() - getFirstVisiblePosition()) / 3)));
    }
}
