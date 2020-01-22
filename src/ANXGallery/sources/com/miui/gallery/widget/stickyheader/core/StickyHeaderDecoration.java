package com.miui.gallery.widget.stickyheader.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.recyclerview.transition.TransitionalItem;
import com.miui.gallery.widget.recyclerview.transition.TransitionalView;
import com.miui.gallery.widget.stickyheader.core.HeaderItemClickHelper;
import com.miui.gallery.widget.stickyheader.core.HeaderViewHolder;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

final class StickyHeaderDecoration<VH extends HeaderViewHolder> extends RecyclerView.ItemDecoration implements TransitionalView {
    private StickyHeaderAdapter<VH> mHeaderAdapter;
    private LongSparseArray<Rect> mHeaderBounds = new LongSparseArray<>();
    private StickyHeaderLayoutManager<VH> mHeaderLayoutManager;
    private StickyHeaderRecycler<VH> mHeaderRecycler;
    private StickyHeaderRender<VH> mHeaderRender;
    private WeakReference<RecyclerView> mHost;
    private HeaderItemClickHelper mItemClickHelper;
    private Rect mTempRect1 = new Rect();
    private Rect mTempRect2 = new Rect();
    private Rect mTempRect3 = new Rect();
    private int mVisibility = 0;

    private static class HeaderMotionEventDispatch extends RecyclerView.SimpleOnItemTouchListener {
        /* access modifiers changed from: private */
        public boolean mEnqueuingFakeEvent;
        private StickyHeaderDecoration mHeaderDecoration;
        private boolean mInterceptEvent;
        private WeakReference<HeaderViewHolder> mTouchHeader;
        private WeakReference<View> mTouchTarget;

        public HeaderMotionEventDispatch(StickyHeaderDecoration stickyHeaderDecoration) {
            this.mHeaderDecoration = stickyHeaderDecoration;
        }

        private void clearTouchTargets() {
            WeakReference<HeaderViewHolder> weakReference = this.mTouchHeader;
            if (weakReference != null) {
                weakReference.clear();
                this.mTouchHeader = null;
            }
            WeakReference<View> weakReference2 = this.mTouchTarget;
            if (weakReference2 != null) {
                weakReference2.clear();
                this.mTouchTarget = null;
            }
        }

        private void enqueueFakeEvent(final RecyclerView recyclerView) {
            recyclerView.getHandler().postAtFrontOfQueue(new Runnable() {
                public void run() {
                    boolean unused = HeaderMotionEventDispatch.this.mEnqueuingFakeEvent = true;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    obtain.setSource(4098);
                    recyclerView.onTouchEvent(obtain);
                    obtain.recycle();
                    boolean unused2 = HeaderMotionEventDispatch.this.mEnqueuingFakeEvent = false;
                }
            });
        }

        private View findTouchTarget(HeaderViewHolder headerViewHolder, float f, float f2) {
            if (!(headerViewHolder.itemView instanceof ViewGroup)) {
                return null;
            }
            float[] transformTouchPoint = transformTouchPoint(headerViewHolder, f, f2);
            ViewGroup viewGroup = (ViewGroup) headerViewHolder.itemView;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (isValidTouchTarget(childAt) && isPointInView(childAt, transformTouchPoint[0], transformTouchPoint[1])) {
                    return childAt;
                }
            }
            return null;
        }

        private HeaderViewHolder getTouchHeader() {
            WeakReference<HeaderViewHolder> weakReference = this.mTouchHeader;
            if (weakReference == null) {
                return null;
            }
            return (HeaderViewHolder) weakReference.get();
        }

        private View getTouchTarget() {
            WeakReference<View> weakReference = this.mTouchTarget;
            if (weakReference == null) {
                return null;
            }
            return (View) weakReference.get();
        }

        private boolean isPointInView(View view, float f, float f2) {
            return f > ((float) view.getLeft()) && f < ((float) view.getRight()) && f2 > ((float) view.getTop()) && f2 < ((float) view.getBottom());
        }

        private boolean isValidTouchTarget(View view) {
            return view.getVisibility() == 0 && view.isClickable();
        }

        private void setPressed(RecyclerView recyclerView, View view, boolean z) {
            view.setPressed(z);
            recyclerView.invalidate();
        }

        private float[] transformTouchPoint(HeaderViewHolder headerViewHolder, float f, float f2) {
            float[] fArr = {f, f2};
            Rect headerBoundsInLayout = this.mHeaderDecoration.getHeaderBoundsInLayout(headerViewHolder.getHeaderId());
            if (headerBoundsInLayout != null) {
                fArr[0] = f - ((float) headerBoundsInLayout.left);
                fArr[1] = f2 - ((float) headerBoundsInLayout.top);
            }
            return fArr;
        }

        /* access modifiers changed from: package-private */
        public boolean handleTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            boolean z;
            HeaderViewHolder findHeaderUnder;
            View findTouchTarget;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                clearTouchTargets();
                HeaderViewHolder findHeaderUnder2 = this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY());
                if (findHeaderUnder2 != null) {
                    this.mTouchHeader = new WeakReference<>(findHeaderUnder2);
                    View findTouchTarget2 = findTouchTarget(findHeaderUnder2, motionEvent.getX(), motionEvent.getY());
                    if (findTouchTarget2 != null) {
                        this.mTouchTarget = new WeakReference<>(findTouchTarget2);
                    }
                }
            }
            if (actionMasked == 3 || getTouchHeader() == null || getTouchTarget() == null || (findHeaderUnder = this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY())) == null || getTouchHeader() != findHeaderUnder || getTouchTarget() != (findTouchTarget = findTouchTarget(findHeaderUnder, motionEvent.getX(), motionEvent.getY()))) {
                z = false;
            } else {
                if (actionMasked == 0) {
                    setPressed(recyclerView, findTouchTarget, true);
                }
                if (actionMasked == 1) {
                    setPressed(recyclerView, findTouchTarget, false);
                    findTouchTarget.performClick();
                    recyclerView.playSoundEffect(0);
                }
                z = true;
            }
            if (!z || actionMasked == 1) {
                View touchTarget = getTouchTarget();
                if (touchTarget != null) {
                    setPressed(recyclerView, touchTarget, false);
                }
                clearTouchTargets();
            }
            return z;
        }

        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (this.mEnqueuingFakeEvent) {
                return this.mInterceptEvent;
            }
            handleTouchEvent(recyclerView, motionEvent);
            if (motionEvent.getActionMasked() == 0) {
                HeaderViewHolder touchHeader = getTouchHeader();
                this.mInterceptEvent = touchHeader != null && touchHeader.isStickyHeader();
                if (this.mInterceptEvent) {
                    enqueueFakeEvent(recyclerView);
                }
            }
            return this.mInterceptEvent;
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            handleTouchEvent(recyclerView, motionEvent);
        }
    }

    private static class HeaderTransitionalItem implements TransitionalItem {
        private final Drawable mDrawable;
        private final RectF mFrame;

        public HeaderTransitionalItem(RectF rectF, Drawable drawable) {
            this.mFrame = rectF;
            this.mDrawable = drawable;
        }

        public Drawable getTransitDrawable() {
            return this.mDrawable;
        }

        public RectF getTransitFrame() {
            return this.mFrame;
        }

        public long getTransitId() {
            return 0;
        }

        public String getTransitPath() {
            return null;
        }

        public ImageView.ScaleType getTransitScaleType() {
            return ImageView.ScaleType.FIT_XY;
        }
    }

    StickyHeaderDecoration(RecyclerView recyclerView) {
        this.mItemClickHelper = new HeaderItemClickHelper(recyclerView.getContext(), this);
        recyclerView.addOnItemTouchListener(this.mItemClickHelper);
        recyclerView.addOnItemTouchListener(new HeaderMotionEventDispatch(this));
        this.mHost = new WeakReference<>(recyclerView);
    }

    private static Drawable createHeaderDrawable(View view, Rect rect) {
        Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return new BitmapDrawable(createBitmap);
    }

    private boolean dispatchOnPreDraw(VH vh) {
        if (!(vh.itemView instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) vh.itemView;
        int childCount = viewGroup.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            ViewTreeObserver viewTreeObserver = viewGroup.getChildAt(i).getViewTreeObserver();
            if (viewTreeObserver != null) {
                z &= viewTreeObserver.dispatchOnPreDraw();
            }
        }
        return z;
    }

    private void drawHeader(RecyclerView recyclerView, Canvas canvas) {
        boolean z = false;
        for (int i = 0; i < this.mHeaderBounds.size(); i++) {
            long keyAt = this.mHeaderBounds.keyAt(i);
            VH layoutHeader = this.mHeaderRecycler.getLayoutHeader(keyAt);
            if (layoutHeader == null) {
                Log.e("StickyHeaderDecoration", "header %s hasn't laidout", (Object) Long.valueOf(keyAt));
            } else {
                boolean z2 = true;
                boolean z3 = layoutHeader.itemView.getVisibility() == 0;
                if (!dispatchOnPreDraw(layoutHeader) && z3) {
                    z2 = false;
                }
                if (!z2) {
                    this.mHeaderRender.draw(recyclerView, canvas, layoutHeader, this.mHeaderBounds.get(keyAt));
                } else {
                    z |= z3;
                }
            }
        }
        if (z) {
            Log.d("StickyHeaderDecoration", "reschedule drawing");
            recyclerView.invalidate();
        }
    }

    private View findFirstItemNoBorderingWithHeader(RecyclerView recyclerView, VH vh, Rect rect) {
        int childCount = recyclerView.getChildCount();
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (childCount < 1 || itemCount < 1) {
            Log.w("StickyHeaderDecoration", "find next header: child num %d, or data num %d is illegal", Integer.valueOf(childCount), Integer.valueOf(itemCount));
            return null;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (recyclerView.getChildAdapterPosition(childAt) != -1 && !isItemBorderingWithHeader(recyclerView, childAt, vh, rect)) {
                return childAt;
            }
        }
        return null;
    }

    private void initHeaderBounds(Rect rect, RecyclerView recyclerView, VH vh, View view, boolean z) {
        View findFirstItemNoBorderingWithHeader;
        VH headerView;
        this.mHeaderLayoutManager.getHeaderBounds(rect, vh.itemView, view);
        if (z && (findFirstItemNoBorderingWithHeader = findFirstItemNoBorderingWithHeader(recyclerView, vh, rect)) != null) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(findFirstItemNoBorderingWithHeader);
            if (this.mHeaderLayoutManager.needOffsetForHeader(recyclerView.getChildViewHolder(findFirstItemNoBorderingWithHeader)) && (headerView = this.mHeaderRecycler.getHeaderView(recyclerView, this.mHeaderAdapter.getHeaderIndex(childAdapterPosition))) != null) {
                this.mHeaderLayoutManager.getHeaderBounds(this.mTempRect1, headerView.itemView, findFirstItemNoBorderingWithHeader);
                StickyHeaderUtil.extractMargins(headerView.itemView, this.mTempRect2);
                StickyHeaderUtil.extractMargins(vh.itemView, this.mTempRect3);
                int i = rect.bottom + this.mTempRect3.bottom;
                int i2 = this.mTempRect1.top - this.mTempRect2.top;
                if (i2 < i) {
                    rect.offset(0, i2 - i);
                }
            }
        }
    }

    private void invalidate() {
        if (this.mHost.get() != null) {
            ((RecyclerView) this.mHost.get()).invalidate();
        }
    }

    private boolean isItemBorderingWithHeader(RecyclerView recyclerView, View view, VH vh, Rect rect) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1 || this.mHeaderAdapter.getHeaderIndex(childAdapterPosition) != vh.getHeaderIndex()) {
            return false;
        }
        StickyHeaderUtil.extractMargins(vh.itemView, this.mTempRect1);
        return view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin <= rect.bottom + this.mTempRect1.bottom;
    }

    private boolean isReady() {
        if (this.mHeaderAdapter == null) {
            Log.w("StickyHeaderDecoration", "sticky header adapter mustn't be null");
            return false;
        } else if (this.mHeaderLayoutManager == null) {
            Log.w("StickyHeaderDecoration", "sticky header layout mustn't be null");
            return false;
        } else if (this.mHeaderRecycler == null) {
            Log.w("StickyHeaderDecoration", "sticky header recycler mustn't be null");
            return false;
        } else if (this.mHeaderRender != null) {
            return true;
        } else {
            this.mHeaderRender = new DefaultStickyHeaderRender();
            return true;
        }
    }

    private void layoutHeader(RecyclerView recyclerView) {
        boolean hasStickyHeader;
        VH headerView;
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            int adapterPosition = childViewHolder.getAdapterPosition();
            if (!(childViewHolder == null || adapterPosition == -1 || (!hasStickyHeader && !this.mHeaderLayoutManager.hasDifferentHeader(adapterPosition, adapterPosition - 1)))) {
                int headerIndex = this.mHeaderAdapter.getHeaderIndex(childAdapterPosition);
                if (StickyHeaderUtil.isValidHeaderIndex(headerIndex) && (headerView = this.mHeaderRecycler.getHeaderView(recyclerView, headerIndex)) != null) {
                    Rect rect = this.mHeaderBounds.get(headerView.getHeaderId());
                    if (rect == null) {
                        rect = new Rect();
                        this.mHeaderBounds.put(headerView.getHeaderId(), rect);
                    }
                    initHeaderBounds(rect, recyclerView, headerView, childAt, hasStickyHeader);
                    headerView.mIsStickyHeader = (hasStickyHeader = this.mHeaderLayoutManager.hasStickyHeader(this.mHeaderRecycler, childViewHolder));
                }
            }
        }
    }

    private boolean notifyDataChanged() {
        if (this.mHost.get() == null) {
            return false;
        }
        ((RecyclerView) this.mHost.get()).invalidateItemDecorations();
        return true;
    }

    private void recycleHeader(RecyclerView recyclerView) {
        LongSparseArray<Rect> clone = this.mHeaderBounds.clone();
        this.mHeaderBounds.clear();
        LongSparseArray longSparseArray = new LongSparseArray();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(i));
            if (childAdapterPosition != -1) {
                int headerIndex = this.mHeaderAdapter.getHeaderIndex(childAdapterPosition);
                if (StickyHeaderUtil.isValidHeaderIndex(headerIndex)) {
                    long headerId = this.mHeaderAdapter.getHeaderId(headerIndex);
                    if (StickyHeaderUtil.isValidHeaderId(headerId)) {
                        longSparseArray.put(headerId, Integer.valueOf(headerIndex));
                        Rect rect = clone.get(headerId);
                        if (rect != null) {
                            this.mHeaderBounds.put(headerId, rect);
                        }
                    }
                }
            }
        }
        LongSparseArray<VH> layoutHeaders = this.mHeaderRecycler.getLayoutHeaders();
        for (int i2 = 0; i2 < layoutHeaders.size(); i2++) {
            long keyAt = layoutHeaders.keyAt(i2);
            if (longSparseArray.get(keyAt) == null) {
                this.mHeaderRecycler.recycleHeader((HeaderViewHolder) layoutHeaders.get(keyAt));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public VH findHeaderUnder(float f, float f2) {
        for (int i = 0; i < this.mHeaderBounds.size(); i++) {
            long keyAt = this.mHeaderBounds.keyAt(i);
            if (this.mHeaderBounds.get(keyAt).contains(Math.round(f), Math.round(f2))) {
                return this.mHeaderRecycler.getLayoutHeader(keyAt);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public StickyHeaderAdapter<VH> getHeaderAdapter() {
        return this.mHeaderAdapter;
    }

    /* access modifiers changed from: package-private */
    public Rect getHeaderBoundsInLayout(long j) {
        return this.mHeaderBounds.get(j);
    }

    /* access modifiers changed from: package-private */
    public StickyHeaderLayoutManager<VH> getHeaderLayoutManager() {
        return this.mHeaderLayoutManager;
    }

    /* access modifiers changed from: package-private */
    public StickyHeaderRender<VH> getHeaderRender() {
        return this.mHeaderRender;
    }

    /* access modifiers changed from: package-private */
    public int getHeaderVisibility() {
        return this.mVisibility;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mVisibility == 8 || !isReady()) {
            return;
        }
        if (this.mHeaderAdapter.getHeaderCount() <= 0) {
            Log.w("StickyHeaderDecoration", "getItemOffsets: header count is invalid");
            return;
        }
        RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        if (childViewHolder == null || childViewHolder.getAdapterPosition() == -1) {
            Log.e("StickyHeaderDecoration", "view holder is null or adapter position is invalid");
        } else if (this.mHeaderLayoutManager.needOffsetForHeader(childViewHolder)) {
            int adapterPosition = childViewHolder.getAdapterPosition();
            int headerIndex = this.mHeaderAdapter.getHeaderIndex(adapterPosition);
            if (!StickyHeaderUtil.isValidHeaderIndex(headerIndex)) {
                Log.w("StickyHeaderDecoration", "header index of position %d is invalid", Integer.valueOf(adapterPosition));
                return;
            }
            VH headerView = this.mHeaderRecycler.getHeaderView(recyclerView, headerIndex);
            if (headerView == null) {
                Log.w("StickyHeaderDecoration", "generate header view for %d failed", Integer.valueOf(headerIndex));
                return;
            }
            StickyHeaderUtil.extractMargins(headerView.itemView, this.mTempRect1);
            rect.top = headerView.itemView.getHeight() + this.mTempRect1.top + this.mTempRect1.bottom;
        }
    }

    public List<TransitionalItem> getTransitionalItems() {
        LinkedList linkedList = new LinkedList();
        if (this.mVisibility != 8) {
            recycleHeader(this.mHeaderLayoutManager.mRecyclerView);
            layoutHeader(this.mHeaderLayoutManager.mRecyclerView);
            for (int i = 0; i < this.mHeaderBounds.size(); i++) {
                long keyAt = this.mHeaderBounds.keyAt(i);
                VH layoutHeader = this.mHeaderRecycler.getLayoutHeader(keyAt);
                if (layoutHeader == null) {
                    Log.e("StickyHeaderDecoration", "header %s hasn't laidout", (Object) Long.valueOf(keyAt));
                } else {
                    Rect rect = this.mHeaderBounds.get(keyAt);
                    linkedList.add(new HeaderTransitionalItem(new RectF(rect), createHeaderDrawable(layoutHeader.itemView, rect)));
                }
            }
        }
        return linkedList;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mVisibility == 0) {
            super.onDraw(canvas, recyclerView, state);
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mVisibility != 8 && isReady()) {
            int childCount = recyclerView.getChildCount();
            int itemCount = recyclerView.getAdapter().getItemCount();
            if (childCount < 1 || itemCount < 1) {
                Log.w("StickyHeaderDecoration", "draw over: child num %d, or data num %d is illegal", Integer.valueOf(childCount), Integer.valueOf(itemCount));
                return;
            }
            recycleHeader(recyclerView);
            layoutHeader(recyclerView);
            if (this.mVisibility == 0) {
                super.onDrawOver(canvas, recyclerView, state);
                drawHeader(recyclerView, canvas);
            }
        }
    }

    public void setHeaderClickListener(HeaderItemClickHelper.HeaderClickListener headerClickListener) {
        this.mItemClickHelper.setHeaderClickListener(headerClickListener);
    }

    /* access modifiers changed from: package-private */
    public void setHeaderVisibility(RecyclerView recyclerView, int i) {
        int i2 = this.mVisibility;
        if (i2 != i) {
            if (i2 == 8 || i == 8) {
                recyclerView.requestLayout();
            } else {
                recyclerView.invalidate();
            }
            this.mVisibility = i;
        }
    }

    /* access modifiers changed from: package-private */
    public void setStickyHeaderAdapter(StickyHeaderAdapter<VH> stickyHeaderAdapter) {
        this.mHeaderAdapter = stickyHeaderAdapter;
        this.mHeaderRecycler = new StickyHeaderRecycler<>(this.mHeaderAdapter);
        StickyHeaderLayoutManager<VH> stickyHeaderLayoutManager = this.mHeaderLayoutManager;
        if (stickyHeaderLayoutManager != null) {
            stickyHeaderLayoutManager.setStickyHeaderAdapter(stickyHeaderAdapter);
            notifyDataChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStickyHeaderLayoutManager(StickyHeaderLayoutManager<VH> stickyHeaderLayoutManager) {
        this.mHeaderLayoutManager = stickyHeaderLayoutManager;
        StickyHeaderLayoutManager<VH> stickyHeaderLayoutManager2 = this.mHeaderLayoutManager;
        if (stickyHeaderLayoutManager2 != null) {
            stickyHeaderLayoutManager2.setStickyHeaderAdapter(this.mHeaderAdapter);
        }
        if (this.mHeaderAdapter != null) {
            notifyDataChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStickyHeaderRender(StickyHeaderRender<VH> stickyHeaderRender) {
        this.mHeaderRender = stickyHeaderRender;
        invalidate();
    }
}
