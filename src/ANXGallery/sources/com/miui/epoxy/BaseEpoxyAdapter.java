package com.miui.epoxy;

import android.view.View;
import android.widget.TextView;
import com.miui.epoxy.EpoxyAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseEpoxyAdapter<T> extends EpoxyAdapter {
    protected final List<T> dataList = new ArrayList();
    private EpoxyModel<?> emptyViewModel = null;
    private final BaseEpoxyAdapter<T>.OrderedMap<Long, EpoxyModel<?>> footers = new OrderedMap<>();
    private boolean hasMore = false;
    private final BaseEpoxyAdapter<T>.OrderedMap<Long, EpoxyModel<?>> headers = new OrderedMap<>();
    private EpoxyLoadMoreModel<?> loadMoreModel = new LoadMoreModel();

    private static class LoadMoreModel extends EpoxyLoadMoreModel<ViewHolder> {

        public class ViewHolder extends EpoxyViewHolder {
            /* access modifiers changed from: private */
            public TextView title;

            public ViewHolder(View view) {
                super(view);
                this.title = (TextView) view.findViewById(R.id.section_title);
            }
        }

        private LoadMoreModel() {
        }

        public int getLayoutRes() {
            return R.layout.layout_empty_view_model;
        }

        public EpoxyAdapter.IViewHolderCreator<ViewHolder> getViewHolderCreator() {
            return new EpoxyAdapter.IViewHolderCreator<ViewHolder>() {
                public ViewHolder create(View view, View view2) {
                    return new ViewHolder(view);
                }
            };
        }

        public void onLoadMoreComplete(ViewHolder viewHolder) {
            viewHolder.title.setText("click to load");
        }

        public void onLoadMoreFailed(ViewHolder viewHolder) {
            viewHolder.title.setText("click to retry");
        }

        public void onLoadMoreStart(ViewHolder viewHolder) {
            viewHolder.title.setText("loading...");
        }
    }

    private class OrderedMap<K, V> implements Iterable<V> {
        /* access modifiers changed from: private */
        public HashMap<K, V> map;
        /* access modifiers changed from: private */
        public List<K> orderList;

        private OrderedMap() {
            this.map = new HashMap<>();
            this.orderList = new ArrayList();
        }

        public V getFirstOrNull() {
            if (this.orderList.size() == 0) {
                return null;
            }
            return this.map.get(this.orderList.get(0));
        }

        public Iterator<V> iterator() {
            return new Iterator<V>() {
                private int index = 0;

                public boolean hasNext() {
                    return this.index < OrderedMap.this.orderList.size();
                }

                public V next() {
                    if (hasNext()) {
                        HashMap access$400 = OrderedMap.this.map;
                        List access$300 = OrderedMap.this.orderList;
                        int i = this.index;
                        this.index = i + 1;
                        return access$400.get(access$300.get(i));
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        public int size() {
            return this.orderList.size();
        }

        public Collection<V> values() {
            ArrayList arrayList = new ArrayList();
            for (K k : this.orderList) {
                arrayList.add(this.map.get(k));
            }
            return arrayList;
        }
    }

    private void addDataModels(Collection<? extends EpoxyModel<?>> collection) {
        EpoxyModel<?> loadMoreOrFirstFooter = getLoadMoreOrFirstFooter();
        if (loadMoreOrFirstFooter == null) {
            addModels(collection);
        } else {
            insertModelsBefore(collection, loadMoreOrFirstFooter);
        }
    }

    private void batchDoPropertiesStage(Collection<T> collection, Collection<T> collection2) {
        if (collection.isEmpty() || collection2.isEmpty()) {
        }
    }

    private EpoxyModel<?> getLoadMoreOrFirstFooter() {
        return this.hasMore ? this.loadMoreModel : this.footers.getFirstOrNull();
    }

    public final void addDataList(Collection<T> collection) {
        addDataList(collection, this.hasMore);
    }

    public final void addDataList(Collection<T> collection, boolean z) {
        setHasMore(z);
        addDataModels(transDataList(collection));
        this.dataList.addAll(collection);
        checkEmptyView();
    }

    public void checkEmptyView() {
        if (isDataListEmpty()) {
            EpoxyModel<?> epoxyModel = this.emptyViewModel;
            if (epoxyModel != null && !containsModel(epoxyModel)) {
                addModel(this.headers.size(), this.emptyViewModel);
                return;
            }
            return;
        }
        removeModel(this.emptyViewModel);
    }

    public void clearData() {
        clearData(this.hasMore);
    }

    public void clearData(boolean z) {
        if (!this.dataList.isEmpty()) {
            updateDataList(Collections.emptyList(), z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isDataListEmpty() {
        return this.dataList.isEmpty();
    }

    public abstract void notifyDataChanged(T t);

    public void removeData(T t) {
        if (this.dataList.remove(t)) {
            replaceAllDataModels();
        }
    }

    /* access modifiers changed from: protected */
    public void replaceAllDataModels() {
        replaceAllDataModels(true);
    }

    /* access modifiers changed from: protected */
    public void replaceAllDataModels(boolean z) {
        EpoxyModel<?> epoxyModel;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.headers.values());
        if (!isDataListEmpty() || (epoxyModel = this.emptyViewModel) == null) {
            arrayList.addAll(transDataList(this.dataList));
            if (this.hasMore) {
                arrayList.add(this.loadMoreModel);
            }
        } else {
            arrayList.add(epoxyModel);
        }
        arrayList.addAll(this.footers.values());
        replaceAllModels(arrayList, z);
    }

    public final void setEmptyViewModel(EpoxyModel<?> epoxyModel) {
        EpoxyModel<?> epoxyModel2 = this.emptyViewModel;
        if (epoxyModel2 != epoxyModel) {
            if (epoxyModel2 != null) {
                removeModel(epoxyModel2);
            }
            this.emptyViewModel = epoxyModel;
        }
    }

    public final void setHasMore(boolean z) {
        if (this.hasMore != z) {
            this.hasMore = z;
            if (!z) {
                this.loadMoreModel.setState(1);
                removeModel(this.loadMoreModel);
            } else if (this.footers.size() == 0) {
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.loadMoreModel});
            } else {
                insertModelBefore(this.loadMoreModel, this.footers.getFirstOrNull());
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract Collection<? extends EpoxyModel<?>> transData(T t);

    /* access modifiers changed from: protected */
    public Collection<? extends EpoxyModel<?>> transDataList(Collection<T> collection) {
        ArrayList arrayList = new ArrayList();
        for (T transData : collection) {
            arrayList.addAll(transData(transData));
        }
        return arrayList;
    }

    public final void updateDataList(Collection<T> collection) {
        updateDataList(collection, this.hasMore);
    }

    public final void updateDataList(Collection<T> collection, boolean z) {
        this.hasMore = z;
        if (!z) {
            this.loadMoreModel.setState(1);
        }
        if (this.supportPropertyStage) {
            batchDoPropertiesStage(this.dataList, collection);
        }
        this.dataList.clear();
        this.dataList.addAll(collection);
        replaceAllDataModels();
    }
}
