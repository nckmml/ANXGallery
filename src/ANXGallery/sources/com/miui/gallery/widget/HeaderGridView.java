package com.miui.gallery.widget;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class HeaderGridView extends GridView {
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();

    private static class FixedViewInfo {
        public Object data;
        public boolean isSelectable;
        public ViewGroup viewContainer;

        private FixedViewInfo() {
        }
    }

    private static class HeaderViewGridAdapter extends BaseAdapter implements Filterable {
        static final ArrayList<FixedViewInfo> EMPTY_INFO_LIST = new ArrayList<>();
        private final ListAdapter mAdapter;
        boolean mAreAllFixedViewsSelectable;
        private final DataSetObservable mDataSetObservable = new DataSetObservable();
        ArrayList<FixedViewInfo> mFooterViewInfos;
        ArrayList<FixedViewInfo> mHeaderViewInfos;
        private final boolean mIsFilterable;
        private int mLastVisibleItemHeight;
        private int mNumColumns;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> arrayList, ArrayList<FixedViewInfo> arrayList2, ListAdapter listAdapter) {
            boolean z = true;
            this.mNumColumns = 1;
            this.mAdapter = listAdapter;
            this.mIsFilterable = listAdapter instanceof Filterable;
            if (arrayList != null) {
                if (arrayList == null) {
                    this.mHeaderViewInfos = EMPTY_INFO_LIST;
                } else {
                    this.mHeaderViewInfos = arrayList;
                }
                if (arrayList2 == null) {
                    this.mFooterViewInfos = EMPTY_INFO_LIST;
                } else {
                    this.mFooterViewInfos = arrayList2;
                }
                this.mAreAllFixedViewsSelectable = (!areAllListInfosSelectable(this.mHeaderViewInfos) || !areAllListInfosSelectable(this.mFooterViewInfos)) ? false : z;
                return;
            }
            throw new IllegalArgumentException("headerViewInfos cannot be null");
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> arrayList) {
            if (arrayList == null) {
                return true;
            }
            Iterator<FixedViewInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                if (!it.next().isSelectable) {
                    return false;
                }
            }
            return true;
        }

        private int getAlignedAdapterCount() {
            int count = this.mAdapter.getCount();
            if (count <= 0) {
                return 0;
            }
            int i = this.mNumColumns;
            return ((count + i) - 1) - ((count - 1) % i);
        }

        private View getPlaceholderView(View view, ViewGroup viewGroup, int i) {
            if (view == null) {
                view = new View(viewGroup.getContext());
            }
            view.setVisibility(4);
            view.setMinimumHeight(i);
            return view;
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return this.mAreAllFixedViewsSelectable && listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public int getCount() {
            int headersCount = (getHeadersCount() + getFootersCount()) * this.mNumColumns;
            return this.mAdapter != null ? headersCount + getAlignedAdapterCount() : headersCount;
        }

        public Filter getFilter() {
            if (this.mIsFilterable) {
                return ((Filterable) this.mAdapter).getFilter();
            }
            return null;
        }

        public int getFootersCount() {
            return this.mFooterViewInfos.size();
        }

        public int getHeadersCount() {
            return this.mHeaderViewInfos.size();
        }

        public Object getItem(int i) {
            int headersCount = getHeadersCount();
            int i2 = this.mNumColumns;
            int i3 = headersCount * i2;
            if (i >= i3) {
                int i4 = i - i3;
                int alignedAdapterCount = getAlignedAdapterCount();
                ListAdapter listAdapter = this.mAdapter;
                if (listAdapter != null) {
                    if (i4 < listAdapter.getCount()) {
                        return this.mAdapter.getItem(i4);
                    }
                    if (i4 < alignedAdapterCount) {
                        return null;
                    }
                }
                int i5 = i4 - alignedAdapterCount;
                int footersCount = getFootersCount();
                int i6 = this.mNumColumns;
                if (i5 >= footersCount * i6) {
                    throw new ArrayIndexOutOfBoundsException(i5);
                } else if (i5 % i6 == 0) {
                    return this.mFooterViewInfos.get(i5 / i6).data;
                } else {
                    return null;
                }
            } else if (i % i2 == 0) {
                return this.mHeaderViewInfos.get(i / i2).data;
            } else {
                return null;
            }
        }

        public long getItemId(int i) {
            int i2;
            int headersCount = getHeadersCount() * this.mNumColumns;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter == null || i < headersCount || (i2 = i - headersCount) >= listAdapter.getCount()) {
                return -1;
            }
            return this.mAdapter.getItemId(i2);
        }

        public int getItemViewType(int i) {
            int headersCount = getHeadersCount();
            int i2 = this.mNumColumns;
            int i3 = headersCount * i2;
            if (i >= i3 || i % i2 == 0) {
                ListAdapter listAdapter = this.mAdapter;
                if (listAdapter != null && i >= i3) {
                    int i4 = i - i3;
                    if (i4 < listAdapter.getCount()) {
                        return this.mAdapter.getItemViewType(i4);
                    }
                    if (i4 < getAlignedAdapterCount()) {
                        return this.mAdapter.getViewTypeCount();
                    }
                }
                int alignedAdapterCount = i - (i3 + getAlignedAdapterCount());
                int footersCount = getFootersCount();
                int i5 = this.mNumColumns;
                if (alignedAdapterCount >= footersCount * i5 || alignedAdapterCount % i5 == 0) {
                    return -2;
                }
                ListAdapter listAdapter2 = this.mAdapter;
                if (listAdapter2 != null) {
                    return listAdapter2.getViewTypeCount();
                }
                return 1;
            }
            ListAdapter listAdapter3 = this.mAdapter;
            if (listAdapter3 != null) {
                return listAdapter3.getViewTypeCount();
            }
            return 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int headersCount = getHeadersCount();
            int i2 = this.mNumColumns;
            int i3 = headersCount * i2;
            if (i < i3) {
                ViewGroup viewGroup2 = this.mHeaderViewInfos.get(i / i2).viewContainer;
                if (i % this.mNumColumns != 0) {
                    return getPlaceholderView(view, viewGroup, viewGroup2.getHeight());
                }
                viewGroup2.setVisibility(0);
                return viewGroup2;
            }
            int i4 = i - i3;
            int alignedAdapterCount = getAlignedAdapterCount();
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                if (i4 < listAdapter.getCount()) {
                    View view2 = this.mAdapter.getView(i4, view, viewGroup);
                    if (i4 % this.mNumColumns == 0) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(-1, 0), View.MeasureSpec.makeMeasureSpec(-2, 0));
                        this.mLastVisibleItemHeight = view2.getMeasuredHeight();
                    }
                    return view2;
                } else if (i4 < alignedAdapterCount) {
                    return getPlaceholderView(view, viewGroup, this.mLastVisibleItemHeight);
                }
            }
            int i5 = i4 - alignedAdapterCount;
            int footersCount = getFootersCount();
            int i6 = this.mNumColumns;
            if (i5 < footersCount * i6) {
                ViewGroup viewGroup3 = this.mFooterViewInfos.get(i5 / i6).viewContainer;
                if (i5 % this.mNumColumns != 0) {
                    return getPlaceholderView(view, viewGroup, viewGroup3.getHeight());
                }
                viewGroup3.setVisibility(0);
                return viewGroup3;
            }
            throw new ArrayIndexOutOfBoundsException(i5);
        }

        public int getViewTypeCount() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return listAdapter.getViewTypeCount() + 1;
            }
            return 2;
        }

        public boolean hasStableIds() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return listAdapter.hasStableIds();
            }
            return false;
        }

        public boolean isEmpty() {
            ListAdapter listAdapter = this.mAdapter;
            return (listAdapter == null || listAdapter.isEmpty()) && getHeadersCount() == 0 && getFootersCount() == 0;
        }

        public boolean isEnabled(int i) {
            int headersCount = getHeadersCount();
            int i2 = this.mNumColumns;
            int i3 = headersCount * i2;
            if (i < i3) {
                return i % i2 == 0 && this.mHeaderViewInfos.get(i / i2).isSelectable;
            }
            int i4 = i - i3;
            int alignedAdapterCount = getAlignedAdapterCount();
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                if (i4 < listAdapter.getCount()) {
                    return this.mAdapter.isEnabled(i4);
                }
                if (i4 < alignedAdapterCount) {
                    return false;
                }
            }
            int i5 = i4 - alignedAdapterCount;
            int footersCount = getFootersCount();
            int i6 = this.mNumColumns;
            if (i5 < footersCount * i6) {
                return i5 % i6 == 0 && this.mFooterViewInfos.get(i5 / i6).isSelectable;
            }
            throw new ArrayIndexOutOfBoundsException(i5);
        }

        public void notifyDataSetChanged() {
            this.mDataSetObservable.notifyChanged();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDataSetObservable.registerObserver(dataSetObserver);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void setNumColumns(int i) {
            if (i < 1) {
                throw new IllegalArgumentException("Number of columns must be 1 or more");
            } else if (this.mNumColumns != i) {
                this.mNumColumns = i;
                notifyDataSetChanged();
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDataSetObservable.unregisterObserver(dataSetObserver);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public HeaderGridView(Context context) {
        super(context);
        initHeaderGridView();
    }

    public HeaderGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initHeaderGridView();
    }

    public HeaderGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initHeaderGridView();
    }

    private void initHeaderGridView() {
        super.setClipChildren(false);
        setGravity(3);
    }

    public int getFooterViewCount() {
        return this.mFooterViewInfos.size();
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewInfos.size();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof HeaderViewGridAdapter)) {
            ((HeaderViewGridAdapter) adapter).setNumColumns(getNumColumns());
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.mHeaderViewInfos.size() > 0 || this.mFooterViewInfos.size() > 0) {
            HeaderViewGridAdapter headerViewGridAdapter = new HeaderViewGridAdapter(this.mHeaderViewInfos, this.mFooterViewInfos, listAdapter);
            int numColumns = getNumColumns();
            if (numColumns > 1) {
                headerViewGridAdapter.setNumColumns(numColumns);
            }
            super.setAdapter(headerViewGridAdapter);
            return;
        }
        super.setAdapter(listAdapter);
    }

    public void setClipChildren(boolean z) {
    }
}
