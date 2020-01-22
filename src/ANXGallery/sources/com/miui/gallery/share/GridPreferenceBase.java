package com.miui.gallery.share;

import android.content.Context;
import android.database.DataSetObserver;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;

public abstract class GridPreferenceBase extends Preference {
    protected ListAdapter mAdapter;
    protected final View.OnClickListener mClickListener;
    protected int mColumnCount;
    protected int mColumnWidth;
    private DataSetObserver mDataSetObserver;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;

    final class MyDataSetObserver extends DataSetObserver {
        MyDataSetObserver() {
        }

        public void onChanged() {
            GridPreferenceBase.this.update();
        }

        public void onInvalidated() {
            GridPreferenceBase.this.update();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(GridPreferenceBase gridPreferenceBase, int i);
    }

    public GridPreferenceBase(Context context) {
        this(context, (AttributeSet) null);
    }

    public GridPreferenceBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridPreferenceBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (GridPreferenceBase.this.mItemClickListener != null) {
                    GridPreferenceBase.this.mItemClickListener.onItemClick(GridPreferenceBase.this, ((Integer) view.getTag(GridPreferenceBase.this.getTagInfo())).intValue());
                }
            }
        };
        setLayoutResource(getLayoutResourceId());
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutResourceId();

    /* access modifiers changed from: protected */
    public int getTagInfo() {
        return getLayoutResourceId();
    }

    /* access modifiers changed from: protected */
    public void onBindView(View view) {
        super.onBindView(view);
        updateGrid(view);
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver;
        ListAdapter listAdapter2 = this.mAdapter;
        if (!(listAdapter2 == null || (dataSetObserver = this.mDataSetObserver) == null)) {
            listAdapter2.unregisterDataSetObserver(dataSetObserver);
            this.mDataSetObserver = null;
        }
        this.mAdapter = listAdapter;
        if (this.mAdapter != null) {
            this.mDataSetObserver = new MyDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
        update();
    }

    public void setColumnCountAndWidth(int i, int i2) {
        this.mColumnCount = i;
        this.mColumnWidth = i2;
        notifyChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void update() {
        notifyChanged();
    }

    /* access modifiers changed from: protected */
    public abstract void updateGrid(View view);
}
