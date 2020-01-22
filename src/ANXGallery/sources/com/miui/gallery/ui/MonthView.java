package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.miui.gallery.Config;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.recyclerview.transition.FuzzyMatchItem;
import com.miui.gallery.widget.recyclerview.transition.TransitionHelper;
import com.miui.gallery.widget.recyclerview.transition.TransitionalItem;
import com.miui.gallery.widget.recyclerview.transition.TransitionalView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MonthView extends View implements TransitionalView {
    private static Rect sTempRect = new Rect();
    private LinkedList<ItemView> mChildren;
    private LinkedList<ItemView> mDetachedChildren;
    private DisplayImageOptions.Builder mDisplayImageOptionBuilder;
    private int mItemHorizontalSpace;
    private int mItemVerticalSpace;
    private int mSpanCount;

    private static class ItemView extends NonViewAware {
        /* access modifiers changed from: private */
        public WeakReference<View> mBindView;
        /* access modifiers changed from: private */
        public MonthItem mData;
        /* access modifiers changed from: private */
        public Drawable mDrawable;
        /* access modifiers changed from: private */
        public RectF mFrame;
        private Matrix mMatrix;

        public ItemView(View view) {
            this(view, (RectF) null, (MonthItem) null);
        }

        public ItemView(View view, RectF rectF, MonthItem monthItem) {
            super(Config.ThumbConfig.get().sMicroTargetSize, ViewScaleType.CROP);
            this.mBindView = new WeakReference<>(view);
            this.mFrame = rectF;
            this.mData = monthItem;
            this.mMatrix = new Matrix();
        }

        private void configureBounds() {
            float f;
            float f2;
            Drawable drawable = this.mDrawable;
            if (drawable != null && this.mFrame != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
                int width = (int) this.mFrame.width();
                int height = (int) this.mFrame.height();
                boolean z = (intrinsicWidth < 0 || width == intrinsicWidth) && (intrinsicHeight < 0 || height == intrinsicHeight);
                this.mMatrix.reset();
                this.mDrawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                if (!z) {
                    float f3 = 0.0f;
                    if (intrinsicWidth * height > width * intrinsicHeight) {
                        float f4 = ((float) height) / ((float) intrinsicHeight);
                        f = 0.0f;
                        f3 = (((float) width) - (((float) intrinsicWidth) * f4)) * 0.5f;
                        f2 = f4;
                    } else {
                        f2 = ((float) width) / ((float) intrinsicWidth);
                        f = (((float) height) - (((float) intrinsicHeight) * f2)) * 0.5f;
                    }
                    this.mMatrix.setScale(f2, f2);
                    this.mMatrix.postTranslate((float) Math.round(f3), (float) Math.round(f));
                }
            }
        }

        public ItemView bindView(View view) {
            this.mBindView = new WeakReference<>(view);
            return this;
        }

        public void detach() {
            this.mData = null;
            this.mDrawable = null;
            WeakReference<View> weakReference = this.mBindView;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.mBindView = null;
        }

        public int getHeight() {
            RectF rectF = this.mFrame;
            if (rectF != null) {
                return (int) rectF.height();
            }
            return 0;
        }

        public int getId() {
            MonthItem monthItem = this.mData;
            return monthItem != null ? (int) monthItem.mId : super.hashCode();
        }

        public int getWidth() {
            RectF rectF = this.mFrame;
            if (rectF != null) {
                return (int) rectF.width();
            }
            return 0;
        }

        public void invalidate() {
            if (this.mDrawable != null && this.mFrame != null) {
                WeakReference<View> weakReference = this.mBindView;
                View view = weakReference != null ? (View) weakReference.get() : null;
                if (view != null) {
                    view.invalidate();
                } else {
                    Log.w("MonthView", "bind view is null: reference %s", Boolean.valueOf(this.mBindView == null));
                }
            }
        }

        public void onDraw(Canvas canvas) {
            if (this.mFrame != null && this.mDrawable != null) {
                int save = canvas.save();
                canvas.translate(this.mFrame.left, this.mFrame.top);
                canvas.clipRect(0.0f, 0.0f, this.mFrame.width(), this.mFrame.height());
                canvas.concat(this.mMatrix);
                this.mDrawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }

        public ItemView setData(MonthItem monthItem) {
            this.mData = monthItem;
            return this;
        }

        public ItemView setFrame(RectF rectF) {
            this.mFrame = rectF;
            configureBounds();
            invalidate();
            return this;
        }

        public boolean setImageBitmap(Bitmap bitmap) {
            WeakReference<View> weakReference = this.mBindView;
            return setImageDrawable((weakReference == null || weakReference.get() == null) ? new BitmapDrawable(bitmap) : new BitmapDrawable(((View) this.mBindView.get()).getResources(), bitmap));
        }

        public boolean setImageDrawable(Drawable drawable) {
            this.mDrawable = drawable;
            configureBounds();
            invalidate();
            return true;
        }
    }

    public static class MonthItem {
        /* access modifiers changed from: private */
        public long mDate;
        /* access modifiers changed from: private */
        public Uri mDownloadUri;
        /* access modifiers changed from: private */
        public long mFileLength;
        /* access modifiers changed from: private */
        public long mId;
        /* access modifiers changed from: private */
        public String mLocalPath;
        /* access modifiers changed from: private */
        public int mPosition;

        public static class Builder {
            /* access modifiers changed from: private */
            public long mDate;
            /* access modifiers changed from: private */
            public Uri mDownloadUri;
            /* access modifiers changed from: private */
            public long mFileLength;
            /* access modifiers changed from: private */
            public long mId;
            /* access modifiers changed from: private */
            public String mLocalPath;
            /* access modifiers changed from: private */
            public int mPosition;

            public MonthItem build() {
                return new MonthItem(this);
            }

            public Builder setDate(long j) {
                this.mDate = j;
                return this;
            }

            public Builder setDownloadUri(Uri uri) {
                this.mDownloadUri = uri;
                return this;
            }

            public Builder setFileLength(long j) {
                this.mFileLength = j;
                return this;
            }

            public Builder setId(long j) {
                this.mId = j;
                return this;
            }

            public Builder setLocalPath(String str) {
                this.mLocalPath = str;
                return this;
            }

            public Builder setPosition(int i) {
                this.mPosition = i;
                return this;
            }
        }

        private MonthItem(Builder builder) {
            this.mId = builder.mId;
            this.mDownloadUri = builder.mDownloadUri;
            this.mLocalPath = builder.mLocalPath;
            this.mPosition = builder.mPosition;
            this.mDate = builder.mDate;
            this.mFileLength = builder.mFileLength;
        }

        public boolean equals(Object obj) {
            return obj != null && this.mId == ((MonthItem) obj).mId;
        }

        public int hashCode() {
            return (int) this.mId;
        }
    }

    private static class MonthTransitionItem implements TransitionalItem {
        private RectF mFrame;
        private ItemView mItemView;

        public MonthTransitionItem(MonthView monthView, ItemView itemView) {
            this.mItemView = itemView;
            this.mFrame = new RectF(itemView.mFrame);
            this.mFrame.offset((float) monthView.getLeft(), (float) monthView.getTop());
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.view.View} */
        /* JADX WARNING: Multi-variable type inference failed */
        public Drawable getTransitDrawable() {
            Drawable.ConstantState constantState;
            View view = null;
            Drawable access$800 = BindImageHelper.isViewBindDrawable((ImageAware) this.mItemView) ? this.mItemView.mDrawable : null;
            if (access$800 == null || (constantState = access$800.getConstantState()) == null) {
                return access$800;
            }
            if (this.mItemView.mBindView != null) {
                view = this.mItemView.mBindView.get();
            }
            return view != null ? constantState.newDrawable(view.getResources()) : constantState.newDrawable();
        }

        public RectF getTransitFrame() {
            return this.mFrame;
        }

        public long getTransitId() {
            return this.mItemView.mData.mId;
        }

        public String getTransitPath() {
            return this.mItemView.mData.mLocalPath;
        }

        public ImageView.ScaleType getTransitScaleType() {
            return ImageView.ScaleType.CENTER_CROP;
        }
    }

    public MonthView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MonthView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MonthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildren = new LinkedList<>();
        this.mDetachedChildren = new LinkedList<>();
        this.mItemHorizontalSpace = 0;
        this.mItemVerticalSpace = 0;
        this.mSpanCount = 10;
    }

    private float calculateItemSize(int i) {
        int i2 = this.mSpanCount;
        return (((float) ((i - getPaddingStart()) - getPaddingEnd())) - ((((float) this.mItemHorizontalSpace) * 1.0f) * ((float) (i2 - 1)))) / ((float) i2);
    }

    private int calculateLineNum(int i) {
        int i2 = this.mSpanCount;
        return ((i + i2) - 1) / i2;
    }

    private void detachItemView(ItemView itemView) {
        itemView.detach();
        this.mDetachedChildren.add(itemView);
    }

    private void drawChildren(Canvas canvas) {
        Iterator it = this.mChildren.iterator();
        while (it.hasNext()) {
            ((ItemView) it.next()).onDraw(canvas);
        }
    }

    private DisplayImageOptions getDisplayImageOptions(MonthItem monthItem) {
        if (this.mDisplayImageOptionBuilder == null) {
            this.mDisplayImageOptionBuilder = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.get().MICRO_THUMB_DISPLAY_IMAGE_OPTIONS_DEFAULT).preferSyncLoadFromMicroCache(false);
        }
        if (monthItem.mFileLength > 0) {
            this.mDisplayImageOptionBuilder.considerFileLength(true).fileLength(monthItem.mFileLength);
        }
        return this.mDisplayImageOptionBuilder.build();
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private void layoutChildren() {
        if (this.mSpanCount > 0 && getWidth() > 0) {
            float calculateItemSize = calculateItemSize(getWidth());
            int i = -1;
            Iterator it = this.mChildren.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                ItemView itemView = (ItemView) it.next();
                i++;
                if (i == this.mSpanCount) {
                    i2++;
                    i = 0;
                }
                float width = isLayoutRTL() ? ((float) getWidth()) - ((((float) getPaddingStart()) + ((((float) this.mItemHorizontalSpace) + calculateItemSize) * ((float) i))) + calculateItemSize) : ((float) getPaddingStart()) + ((((float) this.mItemHorizontalSpace) + calculateItemSize) * ((float) i));
                float paddingTop = ((float) getPaddingTop()) + ((((float) this.mItemVerticalSpace) + calculateItemSize) * ((float) i2));
                RectF rectF = itemView.mFrame == null ? new RectF() : itemView.mFrame;
                rectF.set(width, paddingTop, width + calculateItemSize, paddingTop + calculateItemSize);
                itemView.setFrame(rectF);
            }
        }
    }

    private ItemView obtainItemView() {
        if (this.mDetachedChildren.size() <= 0) {
            return new ItemView(this);
        }
        ItemView remove = this.mDetachedChildren.remove(0);
        remove.bindView(this);
        return remove;
    }

    private FuzzyMatchItem parseMatchItem(MonthItem monthItem) {
        return new FuzzyMatchItem(monthItem.mPosition, monthItem.mId, "", monthItem.mDate);
    }

    private void requestLayoutIfNecessary() {
        if (this.mChildren.size() > 0 && isLaidOut()) {
            requestLayout();
        }
    }

    public void bindData(List<MonthItem> list) {
        int i = 0;
        boolean z = calculateLineNum(list.size()) != calculateLineNum(this.mChildren.size());
        for (int size = this.mChildren.size(); size < list.size(); size++) {
            this.mChildren.add(obtainItemView());
        }
        int size2 = this.mChildren.size();
        for (int size3 = list.size(); size3 < size2; size3++) {
            detachItemView(this.mChildren.removeLast());
        }
        if (z) {
            Log.d("MonthView", "relayout items");
            requestLayoutIfNecessary();
        }
        Iterator it = this.mChildren.iterator();
        while (it.hasNext()) {
            ItemView itemView = (ItemView) it.next();
            MonthItem monthItem = list.get(i);
            if (!monthItem.equals(itemView.mData)) {
                itemView.setData(monthItem);
                Log.d("MonthView", "bind data %d", (Object) Integer.valueOf(monthItem.mPosition));
                BindImageHelper.bindImage(monthItem.mLocalPath, monthItem.mDownloadUri, DownloadType.MICRO, (ImageAware) itemView, getDisplayImageOptions(monthItem), Config.ThumbConfig.get().sMicroTargetSize);
            }
            i++;
        }
    }

    public FuzzyMatchItem findClosestMatchItemUnder(float f, float f2) {
        Iterator it = this.mChildren.iterator();
        double d = 2.147483647E9d;
        ItemView itemView = null;
        while (it.hasNext()) {
            ItemView itemView2 = (ItemView) it.next();
            double distance = TransitionHelper.distance(itemView2.mFrame, f, f2);
            if (distance > 0.0d && distance < d) {
                itemView = itemView2;
                d = distance;
            }
        }
        return parseMatchItem(itemView.mData);
    }

    public FuzzyMatchItem findMatchItemUnder(float f, float f2) {
        Iterator it = this.mChildren.iterator();
        while (it.hasNext()) {
            ItemView itemView = (ItemView) it.next();
            if (itemView.mFrame != null && itemView.mFrame.contains(f, f2)) {
                return parseMatchItem(itemView.mData);
            }
        }
        return null;
    }

    public Rect getItemFrame(long j) {
        Rect rect = new Rect();
        Iterator it = this.mChildren.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ItemView itemView = (ItemView) it.next();
            if (itemView.mData.mId == j) {
                if (itemView.mFrame != null) {
                    itemView.mFrame.round(rect);
                }
            }
        }
        return rect;
    }

    public List<TransitionalItem> getTransitionalItems() {
        ArrayList arrayList = new ArrayList(this.mChildren.size());
        Iterator it = this.mChildren.iterator();
        while (it.hasNext()) {
            arrayList.add(new MonthTransitionItem(this, (ItemView) it.next()));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawChildren(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        layoutChildren();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            if (this.mChildren.size() >= 0) {
                int size = View.MeasureSpec.getSize(i);
                float calculateItemSize = calculateItemSize(size);
                int calculateLineNum = calculateLineNum(this.mChildren.size());
                i = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                i2 = View.MeasureSpec.makeMeasureSpec((int) (((float) (getPaddingTop() + getPaddingBottom() + (this.mItemVerticalSpace * (calculateLineNum - 1)))) + (calculateItemSize * ((float) calculateLineNum))), 1073741824);
            }
            super.onMeasure(i, i2);
            return;
        }
        throw new IllegalArgumentException("the width of month view must be exactly");
    }

    public void setItemHorizontalSpace(int i) {
        if (this.mItemHorizontalSpace != i) {
            this.mItemHorizontalSpace = i;
            requestLayoutIfNecessary();
        }
    }

    public void setItemVerticalSpace(int i) {
        if (this.mItemVerticalSpace != i) {
            this.mItemVerticalSpace = i;
            requestLayoutIfNecessary();
        }
    }

    public void setSpanCount(int i) {
        if (this.mSpanCount != i) {
            this.mSpanCount = i;
            requestLayoutIfNecessary();
        }
    }
}
