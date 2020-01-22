package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

public class LinearLayoutCompat extends ViewGroup {
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }
    }

    public LinearLayoutCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i4 = 0; i4 < virtualChildCount; i4++) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i4))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                drawVerticalDivider(canvas, isLayoutRtl ? virtualChildAt.getRight() + layoutParams.rightMargin : (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    i3 = virtualChildAt2.getLeft() - layoutParams2.leftMargin;
                    i2 = this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                    drawVerticalDivider(canvas, i);
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
                drawVerticalDivider(canvas, i);
            } else {
                i3 = getWidth() - getPaddingRight();
                i2 = this.mDividerWidth;
            }
            i = i3 - i2;
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            drawHorizontalDivider(canvas, virtualChildAt2 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    /* access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0105  */
    public void layoutHorizontal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        boolean z;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i16 = i4 - i2;
        int paddingBottom = i16 - getPaddingBottom();
        int paddingBottom2 = (i16 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i17 = this.mGravity;
        int i18 = i17 & 112;
        boolean z3 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(8388615 & i17, ViewCompat.getLayoutDirection(this));
        boolean z4 = true;
        int paddingLeft = absoluteGravity != 1 ? absoluteGravity != 5 ? getPaddingLeft() : ((getPaddingLeft() + i3) - i) - this.mTotalLength : getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        if (isLayoutRtl) {
            i6 = virtualChildCount - 1;
            i5 = -1;
        } else {
            i6 = 0;
            i5 = 1;
        }
        int i19 = 0;
        while (i19 < virtualChildCount) {
            int i20 = i6 + (i5 * i19);
            View virtualChildAt = getVirtualChildAt(i20);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i20);
                z2 = z4;
                i7 = paddingTop;
                i10 = virtualChildCount;
                i8 = i18;
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z3) {
                    i11 = i19;
                    i9 = virtualChildCount;
                    if (layoutParams.height != -1) {
                        i12 = virtualChildAt.getBaseline();
                        i13 = layoutParams.gravity;
                        if (i13 < 0) {
                            i13 = i18;
                        }
                        i14 = i13 & 112;
                        i8 = i18;
                        if (i14 != 16) {
                            z = true;
                            i15 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        } else if (i14 != 48) {
                            if (i14 != 80) {
                                i15 = paddingTop;
                            } else {
                                int i21 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                                if (i12 != -1) {
                                    i21 -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i12);
                                }
                                i15 = i21;
                            }
                            z = true;
                        } else {
                            int i22 = layoutParams.topMargin + paddingTop;
                            if (i12 != -1) {
                                z = true;
                                i22 += iArr[1] - i12;
                            } else {
                                z = true;
                            }
                            i15 = i22;
                        }
                        if (hasDividerBeforeChildAt(i20)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i23 = layoutParams.leftMargin + paddingLeft;
                        View view = virtualChildAt;
                        i7 = paddingTop;
                        setChildFrame(view, i23 + getLocationOffset(virtualChildAt), i15, measuredWidth, measuredHeight);
                        int i24 = measuredWidth + layoutParams.rightMargin;
                        View view2 = view;
                        i19 = i11 + getChildrenSkipCount(view2, i20);
                        paddingLeft = i23 + i24 + getNextLocationOffset(view2);
                        i19++;
                        virtualChildCount = i9;
                        i18 = i8;
                        z4 = z;
                        paddingTop = i7;
                    }
                } else {
                    i11 = i19;
                    i9 = virtualChildCount;
                }
                i12 = -1;
                i13 = layoutParams.gravity;
                if (i13 < 0) {
                }
                i14 = i13 & 112;
                i8 = i18;
                if (i14 != 16) {
                }
                if (hasDividerBeforeChildAt(i20)) {
                }
                int i232 = layoutParams.leftMargin + paddingLeft;
                View view3 = virtualChildAt;
                i7 = paddingTop;
                setChildFrame(view3, i232 + getLocationOffset(virtualChildAt), i15, measuredWidth, measuredHeight);
                int i242 = measuredWidth + layoutParams.rightMargin;
                View view22 = view3;
                i19 = i11 + getChildrenSkipCount(view22, i20);
                paddingLeft = i232 + i242 + getNextLocationOffset(view22);
                i19++;
                virtualChildCount = i9;
                i18 = i8;
                z4 = z;
                paddingTop = i7;
            } else {
                int i25 = i19;
                i7 = paddingTop;
                i10 = virtualChildCount;
                i8 = i18;
                z2 = true;
            }
            i19++;
            virtualChildCount = i9;
            i18 = i8;
            z4 = z;
            paddingTop = i7;
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int paddingLeft = getPaddingLeft();
        int i9 = i3 - i;
        int paddingRight = i9 - getPaddingRight();
        int paddingRight2 = (i9 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i10 = this.mGravity;
        int i11 = i10 & 112;
        int i12 = i10 & 8388615;
        int paddingTop = i11 != 16 ? i11 != 80 ? getPaddingTop() : ((getPaddingTop() + i4) - i2) - this.mTotalLength : getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        int i13 = 0;
        while (i13 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i13);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i13);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i14 = layoutParams.gravity;
                if (i14 < 0) {
                    i14 = i12;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i14, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i7 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + layoutParams.leftMargin;
                    i6 = layoutParams.rightMargin;
                    i8 = i7 - i6;
                } else if (absoluteGravity != 5) {
                    i8 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i7 = paddingRight - measuredWidth;
                    i6 = layoutParams.rightMargin;
                    i8 = i7 - i6;
                }
                int i15 = i8;
                if (hasDividerBeforeChildAt(i13)) {
                    paddingTop += this.mDividerHeight;
                }
                int i16 = paddingTop + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i15, i16 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i13 += getChildrenSkipCount(virtualChildAt, i13);
                paddingTop = i16 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                i5 = 1;
                i13 += i5;
            }
            i5 = 1;
            i13 += i5;
        }
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0455  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x047a  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01db  */
    public void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        float f;
        int i10;
        boolean z;
        int baseline;
        int i11;
        int i12;
        boolean z2;
        boolean z3;
        int i13;
        View view;
        int i14;
        boolean z4;
        int measuredHeight;
        int baseline2;
        char c;
        int i15;
        int i16 = i;
        int i17 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z5 = this.mBaselineAligned;
        boolean z6 = this.mUseLargestChild;
        int i18 = 1073741824;
        boolean z7 = mode == 1073741824;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        boolean z8 = false;
        int i24 = 0;
        boolean z9 = false;
        boolean z10 = true;
        float f2 = 0.0f;
        while (true) {
            iArr = iArr3;
            if (i19 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(i19);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(i19);
            } else if (virtualChildAt.getVisibility() == 8) {
                i19 += getChildrenSkipCount(virtualChildAt, i19);
            } else {
                if (hasDividerBeforeChildAt(i19)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                float f3 = f2 + layoutParams.weight;
                if (mode == i18 && layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                    if (z7) {
                        this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        int i25 = this.mTotalLength;
                        this.mTotalLength = Math.max(i25, layoutParams.leftMargin + i25 + layoutParams.rightMargin);
                    }
                    if (z5) {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(makeMeasureSpec, makeMeasureSpec);
                        i13 = i19;
                        z3 = z6;
                        z2 = z5;
                        view = virtualChildAt;
                    } else {
                        i13 = i19;
                        z3 = z6;
                        z2 = z5;
                        view = virtualChildAt;
                        z8 = true;
                        i14 = 1073741824;
                        if (mode2 == i14 || layoutParams.height != -1) {
                            z4 = false;
                        } else {
                            z4 = true;
                            z9 = true;
                        }
                        int i26 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i26;
                        int combineMeasuredStates = View.combineMeasuredStates(i24, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            int i27 = ((((layoutParams.gravity >= 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                            iArr2[i27] = Math.max(iArr2[i27], baseline2);
                            iArr[i27] = Math.max(iArr[i27], measuredHeight - baseline2);
                        }
                        int max = Math.max(i21, measuredHeight);
                        boolean z11 = z10 && layoutParams.height == -1;
                        if (layoutParams.weight <= 0.0f) {
                            if (!z4) {
                                i26 = measuredHeight;
                            }
                            i23 = Math.max(i23, i26);
                        } else {
                            int i28 = i23;
                            if (z4) {
                                measuredHeight = i26;
                            }
                            i22 = Math.max(i22, measuredHeight);
                            i23 = i28;
                        }
                        int i29 = i13;
                        i21 = max;
                        i24 = combineMeasuredStates;
                        z10 = z11;
                        i19 = getChildrenSkipCount(view, i29) + i29;
                        f2 = f3;
                        i19++;
                        int i30 = i2;
                        iArr3 = iArr;
                        z6 = z3;
                        z5 = z2;
                        i18 = 1073741824;
                    }
                } else {
                    if (layoutParams.width != 0 || layoutParams.weight <= 0.0f) {
                        c = 65534;
                        i15 = Integer.MIN_VALUE;
                    } else {
                        c = 65534;
                        layoutParams.width = -2;
                        i15 = 0;
                    }
                    i13 = i19;
                    z3 = z6;
                    z2 = z5;
                    char c2 = c;
                    View view2 = virtualChildAt;
                    measureChildBeforeLayout(virtualChildAt, i13, i, f3 == 0.0f ? this.mTotalLength : 0, i2, 0);
                    int i31 = i15;
                    if (i31 != Integer.MIN_VALUE) {
                        layoutParams.width = i31;
                    }
                    int measuredWidth = view2.getMeasuredWidth();
                    if (z7) {
                        view = view2;
                        this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(view);
                    } else {
                        view = view2;
                        int i32 = this.mTotalLength;
                        this.mTotalLength = Math.max(i32, i32 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(view));
                    }
                    if (z3) {
                        i20 = Math.max(measuredWidth, i20);
                    }
                }
                i14 = 1073741824;
                if (mode2 == i14 || layoutParams.height != -1) {
                }
                int i262 = layoutParams.topMargin + layoutParams.bottomMargin;
                measuredHeight = view.getMeasuredHeight() + i262;
                int combineMeasuredStates2 = View.combineMeasuredStates(i24, view.getMeasuredState());
                int i272 = ((((layoutParams.gravity >= 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                iArr2[i272] = Math.max(iArr2[i272], baseline2);
                iArr[i272] = Math.max(iArr[i272], measuredHeight - baseline2);
                int max2 = Math.max(i21, measuredHeight);
                if (z10 || layoutParams.height == -1) {
                }
                if (layoutParams.weight <= 0.0f) {
                }
                int i292 = i13;
                i21 = max2;
                i24 = combineMeasuredStates2;
                z10 = z11;
                i19 = getChildrenSkipCount(view, i292) + i292;
                f2 = f3;
                i19++;
                int i302 = i2;
                iArr3 = iArr;
                z6 = z3;
                z5 = z2;
                i18 = 1073741824;
            }
            z3 = z6;
            z2 = z5;
            i19++;
            int i3022 = i2;
            iArr3 = iArr;
            z6 = z3;
            z5 = z2;
            i18 = 1073741824;
        }
        boolean z12 = z6;
        boolean z13 = z5;
        int i33 = i21;
        int i34 = i22;
        int i35 = i23;
        int i36 = i24;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) {
            i3 = i36;
        } else {
            i3 = i36;
            i33 = Math.max(i33, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z12 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int i37 = 0;
            while (i37 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(i37);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(i37);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    i37 += getChildrenSkipCount(virtualChildAt2, i37);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z7) {
                        this.mTotalLength += layoutParams2.leftMargin + i20 + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i38 = this.mTotalLength;
                        i12 = i33;
                        this.mTotalLength = Math.max(i38, i38 + i20 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                        i37++;
                        i33 = i12;
                    }
                }
                i12 = i33;
                i37++;
                i33 = i12;
            }
        }
        int i39 = i33;
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i16, 0);
        int i40 = (16777215 & resolveSizeAndState) - this.mTotalLength;
        if (z8 || (i40 != 0 && f2 > 0.0f)) {
            float f4 = this.mWeightSum;
            if (f4 > 0.0f) {
                f2 = f4;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i41 = i34;
            int i42 = -1;
            int i43 = i3;
            float f5 = f2;
            int i44 = 0;
            while (i44 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i44);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    i9 = i40;
                    i8 = virtualChildCount;
                    int i45 = i2;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f6 = layoutParams3.weight;
                    if (f6 > 0.0f) {
                        int i46 = (int) ((((float) i40) * f6) / f5);
                        float f7 = f5 - f6;
                        int i47 = i40 - i46;
                        i8 = virtualChildCount;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0) {
                            i11 = 1073741824;
                            if (mode == 1073741824) {
                                if (i46 <= 0) {
                                    i46 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i46, 1073741824), childMeasureSpec);
                                i43 = View.combineMeasuredStates(i43, virtualChildAt3.getMeasuredState() & -16777216);
                                f5 = f7;
                                i9 = i47;
                            }
                        } else {
                            i11 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i46;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i11), childMeasureSpec);
                        i43 = View.combineMeasuredStates(i43, virtualChildAt3.getMeasuredState() & -16777216);
                        f5 = f7;
                        i9 = i47;
                    } else {
                        i9 = i40;
                        i8 = virtualChildCount;
                        int i48 = i2;
                    }
                    if (z7) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3);
                        f = f5;
                    } else {
                        int i49 = this.mTotalLength;
                        f = f5;
                        this.mTotalLength = Math.max(i49, virtualChildAt3.getMeasuredWidth() + i49 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z14 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i50 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i50;
                    i42 = Math.max(i42, measuredHeight2);
                    if (!z14) {
                        i50 = measuredHeight2;
                    }
                    int max3 = Math.max(i41, i50);
                    if (z10) {
                        i10 = -1;
                        if (layoutParams3.height == -1) {
                            z = true;
                            if (z13 && (baseline = virtualChildAt3.getBaseline()) != i10) {
                                int i51 = ((((layoutParams3.gravity < 0 ? this.mGravity : layoutParams3.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[i51] = Math.max(iArr2[i51], baseline);
                                iArr[i51] = Math.max(iArr[i51], measuredHeight2 - baseline);
                            }
                            i41 = max3;
                            z10 = z;
                            f5 = f;
                        }
                    } else {
                        i10 = -1;
                    }
                    z = false;
                    if (z13 || (baseline = virtualChildAt3.getBaseline()) != i10) {
                    }
                    i41 = max3;
                    z10 = z;
                    f5 = f;
                }
                i44++;
                int i52 = i;
                i40 = i9;
                virtualChildCount = i8;
            }
            i5 = i2;
            i4 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            i6 = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? i42 : Math.max(i42, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            i3 = i43;
            i7 = i41;
        } else {
            i7 = Math.max(i34, i35);
            if (z12 && mode != 1073741824) {
                for (int i53 = 0; i53 < virtualChildCount; i53++) {
                    View virtualChildAt4 = getVirtualChildAt(i53);
                    if (!(virtualChildAt4 == null || virtualChildAt4.getVisibility() == 8 || ((LayoutParams) virtualChildAt4.getLayoutParams()).weight <= 0.0f)) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(i20, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i5 = i2;
            i4 = virtualChildCount;
            i6 = i39;
        }
        if (z10 || mode2 == 1073741824) {
            i7 = i6;
        }
        setMeasuredDimension(resolveSizeAndState | (i3 & -16777216), View.resolveSizeAndState(Math.max(i7 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i5, i3 << 16));
        if (z9) {
            forceUniformHeight(i4, i);
        }
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0335  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0340  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0343  */
    public void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        int i8;
        int i9;
        boolean z;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        View view;
        int i19;
        boolean z2;
        int i20;
        int i21;
        int i22;
        int i23 = i;
        int i24 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i25 = this.mBaselineAlignedChildIndex;
        boolean z3 = this.mUseLargestChild;
        int i26 = 0;
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        boolean z4 = false;
        boolean z5 = false;
        float f2 = 0.0f;
        boolean z6 = true;
        while (true) {
            int i32 = 8;
            int i33 = i29;
            if (i31 < virtualChildCount) {
                View virtualChildAt = getVirtualChildAt(i31);
                if (virtualChildAt == null) {
                    this.mTotalLength += measureNullChild(i31);
                    i12 = virtualChildCount;
                    i29 = i33;
                } else {
                    int i34 = i26;
                    if (virtualChildAt.getVisibility() == 8) {
                        i31 += getChildrenSkipCount(virtualChildAt, i31);
                        i12 = virtualChildCount;
                        i29 = i33;
                        i26 = i34;
                    } else {
                        if (hasDividerBeforeChildAt(i31)) {
                            this.mTotalLength += this.mDividerHeight;
                        }
                        LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                        float f3 = f2 + layoutParams.weight;
                        if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                            int i35 = this.mTotalLength;
                            this.mTotalLength = Math.max(i35, layoutParams.topMargin + i35 + layoutParams.bottomMargin);
                            i19 = i28;
                            view = virtualChildAt;
                            i14 = i30;
                            i22 = virtualChildCount;
                            z4 = true;
                            i18 = i34;
                            i15 = i27;
                            i17 = i31;
                            int i36 = i33;
                            i13 = mode2;
                            i16 = i36;
                        } else {
                            int i37 = i27;
                            if (layoutParams.height != 0 || layoutParams.weight <= 0.0f) {
                                i21 = Integer.MIN_VALUE;
                            } else {
                                layoutParams.height = -2;
                                i21 = 0;
                            }
                            i18 = i34;
                            i15 = i37;
                            int i38 = i28;
                            View view2 = virtualChildAt;
                            i22 = virtualChildCount;
                            int i39 = i33;
                            i13 = mode2;
                            i16 = i39;
                            i14 = i30;
                            i17 = i31;
                            measureChildBeforeLayout(virtualChildAt, i31, i, 0, i2, f3 == 0.0f ? this.mTotalLength : 0);
                            int i40 = i21;
                            if (i40 != Integer.MIN_VALUE) {
                                layoutParams.height = i40;
                            }
                            int measuredHeight = view2.getMeasuredHeight();
                            int i41 = this.mTotalLength;
                            view = view2;
                            this.mTotalLength = Math.max(i41, i41 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
                            i19 = z3 ? Math.max(measuredHeight, i38) : i38;
                        }
                        if (i25 >= 0 && i25 == i17 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                        if (i17 >= i25 || layoutParams.weight <= 0.0f) {
                            if (mode == 1073741824 || layoutParams.width != -1) {
                                z2 = false;
                            } else {
                                z2 = true;
                                z5 = true;
                            }
                            int i42 = layoutParams.leftMargin + layoutParams.rightMargin;
                            int measuredWidth = view.getMeasuredWidth() + i42;
                            int max = Math.max(i15, measuredWidth);
                            int combineMeasuredStates = View.combineMeasuredStates(i18, view.getMeasuredState());
                            boolean z7 = z6 && layoutParams.width == -1;
                            if (layoutParams.weight > 0.0f) {
                                if (!z2) {
                                    i42 = measuredWidth;
                                }
                                i16 = Math.max(i16, i42);
                                i20 = i14;
                            } else {
                                if (!z2) {
                                    i42 = measuredWidth;
                                }
                                i20 = Math.max(i14, i42);
                            }
                            i28 = i19;
                            z6 = z7;
                            i29 = i16;
                            f2 = f3;
                            int i43 = max;
                            i30 = i20;
                            i26 = combineMeasuredStates;
                            i31 = getChildrenSkipCount(view, i17) + i17;
                            i27 = i43;
                            i31++;
                            int i44 = i;
                            int i45 = i2;
                            mode2 = i13;
                            virtualChildCount = i12;
                        } else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                }
                i13 = mode2;
                i31++;
                int i442 = i;
                int i452 = i2;
                mode2 = i13;
                virtualChildCount = i12;
            } else {
                int i46 = i26;
                int i47 = i28;
                int i48 = i30;
                int i49 = virtualChildCount;
                int i50 = i27;
                int i51 = i33;
                int i52 = mode2;
                int i53 = i51;
                if (this.mTotalLength > 0) {
                    i3 = i49;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = i49;
                }
                if (z3) {
                    i4 = i52;
                    if (i4 == Integer.MIN_VALUE || i4 == 0) {
                        this.mTotalLength = 0;
                        int i54 = 0;
                        while (i54 < i3) {
                            View virtualChildAt2 = getVirtualChildAt(i54);
                            if (virtualChildAt2 == null) {
                                this.mTotalLength += measureNullChild(i54);
                            } else if (virtualChildAt2.getVisibility() == i32) {
                                i54 += getChildrenSkipCount(virtualChildAt2, i54);
                            } else {
                                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                                int i55 = this.mTotalLength;
                                this.mTotalLength = Math.max(i55, i55 + i47 + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                            }
                            i54++;
                            i32 = 8;
                        }
                    }
                } else {
                    i4 = i52;
                }
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                int i56 = i2;
                int i57 = i47;
                int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i56, 0);
                int i58 = (16777215 & resolveSizeAndState) - this.mTotalLength;
                if (z4 || (i58 != 0 && f2 > 0.0f)) {
                    float f4 = this.mWeightSum;
                    if (f4 > 0.0f) {
                        f2 = f4;
                    }
                    this.mTotalLength = 0;
                    float f5 = f2;
                    int i59 = 0;
                    int i60 = i46;
                    int i61 = i48;
                    i6 = i60;
                    while (i59 < i3) {
                        View virtualChildAt3 = getVirtualChildAt(i59);
                        if (virtualChildAt3.getVisibility() == 8) {
                            f = f5;
                            int i62 = i;
                        } else {
                            LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                            float f6 = layoutParams3.weight;
                            if (f6 > 0.0f) {
                                int i63 = (int) ((((float) i58) * f6) / f5);
                                i8 = i58 - i63;
                                f = f5 - f6;
                                int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                                if (layoutParams3.height == 0) {
                                    i11 = 1073741824;
                                    if (i4 == 1073741824) {
                                        if (i63 <= 0) {
                                            i63 = 0;
                                        }
                                        virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i63, 1073741824));
                                        i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & -256);
                                    }
                                } else {
                                    i11 = 1073741824;
                                }
                                int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i63;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                                virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i11));
                                i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & -256);
                            } else {
                                float f7 = f5;
                                int i64 = i;
                                i8 = i58;
                                f = f7;
                            }
                            int i65 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i65;
                            i50 = Math.max(i50, measuredWidth2);
                            if (mode != 1073741824) {
                                i9 = i6;
                                i10 = -1;
                                if (layoutParams3.width == -1) {
                                    z = true;
                                    if (!z) {
                                        i65 = measuredWidth2;
                                    }
                                    i61 = Math.max(i61, i65);
                                    boolean z8 = z6 && layoutParams3.width == i10;
                                    int i66 = this.mTotalLength;
                                    this.mTotalLength = Math.max(i66, virtualChildAt3.getMeasuredHeight() + i66 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                                    z6 = z8;
                                    i58 = i8;
                                    i6 = i9;
                                }
                            } else {
                                i9 = i6;
                                i10 = -1;
                            }
                            z = false;
                            if (!z) {
                            }
                            i61 = Math.max(i61, i65);
                            if (z6 || layoutParams3.width == i10) {
                            }
                            int i662 = this.mTotalLength;
                            this.mTotalLength = Math.max(i662, virtualChildAt3.getMeasuredHeight() + i662 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                            z6 = z8;
                            i58 = i8;
                            i6 = i9;
                        }
                        i59++;
                        f5 = f;
                    }
                    i5 = i;
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    i7 = i61;
                } else {
                    i7 = Math.max(i48, i53);
                    if (z3 && i4 != 1073741824) {
                        for (int i67 = 0; i67 < i3; i67++) {
                            View virtualChildAt4 = getVirtualChildAt(i67);
                            if (!(virtualChildAt4 == null || virtualChildAt4.getVisibility() == 8 || ((LayoutParams) virtualChildAt4.getLayoutParams()).weight <= 0.0f)) {
                                virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i57, 1073741824));
                            }
                        }
                    }
                    i5 = i;
                    i6 = i46;
                }
                if (z6 || mode == 1073741824) {
                    i7 = i50;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i5, i6), resolveSizeAndState);
                if (z5) {
                    forceUniformWidth(i3, i56);
                    return;
                }
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
