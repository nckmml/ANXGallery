package com.miui.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.gallery.R;

public class SortByHeader extends LinearLayout {
    private View mDateTimeContainer;
    private View mNameContainer;
    private View.OnClickListener mOnSortByClickListener;
    private View mSizeContainer;
    private ImageView mSortByDateArrow;
    private TextView mSortByDateTime;
    private TextView mSortByFileName;
    private TextView mSortByFileSize;
    private ImageView mSortByNameArrow;
    private ImageView mSortBySizeArrow;

    /* renamed from: com.miui.gallery.widget.SortByHeader$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy = new int[SortBy.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[SortBy.SIZE.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[SortBy.DATE.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[SortBy.NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum SortBy {
        NONE,
        DATE,
        NAME,
        SIZE
    }

    public SortByHeader(Context context) {
        super(context);
    }

    public SortByHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SortByHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void setSortByDateSelected(boolean z, int i) {
        if (this.mDateTimeContainer != null) {
            this.mSortByDateTime.setSelected(z);
            this.mSortByDateArrow.setSelected(z);
            this.mSortByDateArrow.setBackgroundResource(i);
        }
    }

    private void setSortByNameSelected(boolean z, int i) {
        if (this.mNameContainer != null) {
            this.mSortByFileName.setSelected(z);
            this.mSortByNameArrow.setSelected(z);
            this.mSortByNameArrow.setBackgroundResource(i);
        }
    }

    private void setSortBySizeSelected(boolean z, int i) {
        if (this.mSizeContainer != null) {
            this.mSortByFileSize.setSelected(z);
            this.mSortBySizeArrow.setSelected(z);
            this.mSortBySizeArrow.setBackgroundResource(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDateTimeContainer = findViewById(R.id.datetime_container);
        this.mNameContainer = findViewById(R.id.name_container);
        this.mSizeContainer = findViewById(R.id.size_container);
        this.mSortByDateTime = (TextView) findViewById(R.id.datetime);
        this.mSortByDateArrow = (ImageView) findViewById(R.id.datetime_sort_arrow);
        this.mSortByFileName = (TextView) findViewById(R.id.name);
        this.mSortByNameArrow = (ImageView) findViewById(R.id.name_sort_arrow);
        this.mSortByFileSize = (TextView) findViewById(R.id.size);
        this.mSortBySizeArrow = (ImageView) findViewById(R.id.size_sort_arrow);
    }

    public void setOnSortByClickListener(View.OnClickListener onClickListener) {
        this.mOnSortByClickListener = onClickListener;
        View view = this.mDateTimeContainer;
        if (view != null) {
            view.setOnClickListener(this.mOnSortByClickListener);
        }
        View view2 = this.mNameContainer;
        if (view2 != null) {
            view2.setOnClickListener(this.mOnSortByClickListener);
        }
        View view3 = this.mSizeContainer;
        if (view3 != null) {
            view3.setOnClickListener(this.mOnSortByClickListener);
        }
    }

    public void updateCurrentSortView(SortBy sortBy, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[sortBy.ordinal()];
        if (i2 == 1) {
            setSortBySizeSelected(true, i);
            setSortByDateSelected(false, R.drawable.sort_by_item_arrow_up);
            setSortByNameSelected(false, R.drawable.sort_by_item_arrow_up);
        } else if (i2 == 2) {
            setSortByDateSelected(true, i);
            setSortBySizeSelected(false, R.drawable.sort_by_item_arrow_up);
            setSortByNameSelected(false, R.drawable.sort_by_item_arrow_up);
        } else if (i2 == 3) {
            setSortByNameSelected(true, i);
            setSortBySizeSelected(false, R.drawable.sort_by_item_arrow_up);
            setSortByDateSelected(false, R.drawable.sort_by_item_arrow_up);
        }
    }
}
