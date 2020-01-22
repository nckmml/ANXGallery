package com.miui.epoxy;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.epoxy.eventhook.EventHook;
import com.miui.epoxy.eventhook.EventHookHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EpoxyAdapter extends RecyclerView.Adapter<EpoxyViewHolder> {
    private static final String LOG_TAG = "EpoxyAdapter";
    private final LongSparseArray<EpoxyViewHolder> boundViewHolders = new LongSparseArray<>();
    private final EventHookHelper eventHookHelper = new EventHookHelper(this);
    private boolean isAttached = false;
    protected final ModelList models = new ModelList();
    /* access modifiers changed from: private */
    public int spanCount = 1;
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        public int getSpanSize(int i) {
            EpoxyModel<?> model = EpoxyAdapter.this.getModel(i);
            if (model != null) {
                return model.getSpanSize(EpoxyAdapter.this.spanCount, i, EpoxyAdapter.this.getItemCount());
            }
            return 1;
        }
    };
    protected boolean supportPropertyStage = false;
    private ViewHolderState viewHolderState = new ViewHolderState();

    public interface IViewHolderCreator<VH extends EpoxyViewHolder> {
        VH create(View view, View view2);
    }

    protected static class ModelList extends ArrayList<EpoxyModel<?>> {
        /* access modifiers changed from: private */
        public final ViewHolderFactory viewHolderFactory = new ViewHolderFactory();

        protected ModelList() {
        }

        public void add(int i, EpoxyModel<?> epoxyModel) {
            this.viewHolderFactory.register((EpoxyModel) epoxyModel);
            super.add(i, epoxyModel);
        }

        public boolean add(EpoxyModel<?> epoxyModel) {
            this.viewHolderFactory.register((EpoxyModel) epoxyModel);
            return super.add(epoxyModel);
        }

        public boolean addAll(int i, Collection<? extends EpoxyModel<?>> collection) {
            this.viewHolderFactory.register((Collection<? extends EpoxyModel>) collection);
            return super.addAll(i, collection);
        }

        public boolean addAll(Collection<? extends EpoxyModel<?>> collection) {
            this.viewHolderFactory.register((Collection<? extends EpoxyModel>) collection);
            return super.addAll(collection);
        }
    }

    private static class ViewHolderFactory {
        private final SparseArray<Pair<Integer, IViewHolderCreator>> creatorSparseArray;

        private ViewHolderFactory() {
            this.creatorSparseArray = new SparseArray<>();
        }

        /* access modifiers changed from: package-private */
        public EpoxyViewHolder create(int i, ViewGroup viewGroup) {
            Pair pair = this.creatorSparseArray.get(i);
            if (pair != null) {
                try {
                    return ((IViewHolderCreator) pair.second).create(((Integer) pair.first).intValue() == 0 ? viewGroup : LayoutInflater.from(viewGroup.getContext()).inflate(((Integer) pair.first).intValue(), viewGroup, false), viewGroup);
                } catch (Exception e) {
                    throw new RuntimeException("cannot inflate view=" + viewGroup.getContext().getResources().getResourceName(((Integer) pair.first).intValue()) + "\nreason:" + e.getMessage(), e);
                }
            } else {
                throw new RuntimeException("cannot find viewHolderCreator for viewType=" + i);
            }
        }

        /* access modifiers changed from: package-private */
        public void register(EpoxyModel epoxyModel) {
            int viewType = epoxyModel.getViewType();
            if (viewType == -1) {
                throw new RuntimeException("illegal viewType=" + viewType);
            } else if (this.creatorSparseArray.get(viewType) == null) {
                this.creatorSparseArray.put(viewType, Pair.create(Integer.valueOf(epoxyModel.getLayoutRes()), epoxyModel.getViewHolderCreator()));
            }
        }

        /* access modifiers changed from: package-private */
        public void register(Collection<? extends EpoxyModel> collection) {
            for (EpoxyModel epoxyModel : collection) {
                if (epoxyModel != null) {
                    register(epoxyModel);
                }
            }
        }
    }

    public EpoxyAdapter() {
        setHasStableIds(true);
        this.spanSizeLookup.setSpanIndexCacheEnabled(true);
    }

    public <VH extends EpoxyViewHolder> void addEventHook(EventHook<VH> eventHook) {
        if (this.isAttached) {
            Log.w(LOG_TAG, "addEventHook is called after adapter attached");
        }
        this.eventHookHelper.add(eventHook);
    }

    public void addModel(int i, EpoxyModel<?> epoxyModel) {
        if (i <= this.models.size() && i >= 0) {
            this.models.add(i, epoxyModel);
            notifyItemInserted(i);
        }
    }

    public void addModels(Collection<? extends EpoxyModel<?>> collection) {
        int size = this.models.size();
        this.models.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }

    public void addModels(EpoxyModel<?>... epoxyModelArr) {
        addModels((Collection<? extends EpoxyModel<?>>) Arrays.asList(epoxyModelArr));
    }

    /* access modifiers changed from: protected */
    public DiffUtil.DiffResult calculateDiff(final List<? extends EpoxyModel<?>> list) {
        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
            private <T> T getOrNull(List<T> list, int i) {
                if (list == null || i < 0 || i >= list.size()) {
                    return null;
                }
                return list.get(i);
            }

            public boolean areContentsTheSame(int i, int i2) {
                EpoxyModel epoxyModel = (EpoxyModel) getOrNull(EpoxyAdapter.this.models, i);
                EpoxyModel epoxyModel2 = (EpoxyModel) getOrNull(list, i2);
                if (epoxyModel == null || epoxyModel2 == null || !epoxyModel.getClass().equals(epoxyModel2.getClass())) {
                    return false;
                }
                if (epoxyModel.isContentTheSame(epoxyModel2)) {
                    return true;
                }
                if (!EpoxyAdapter.this.supportPropertyStage) {
                    return false;
                }
                epoxyModel2.doPropertiesStage(epoxyModel);
                return false;
            }

            public boolean areItemsTheSame(int i, int i2) {
                EpoxyModel epoxyModel = (EpoxyModel) getOrNull(EpoxyAdapter.this.models, i);
                EpoxyModel epoxyModel2 = (EpoxyModel) getOrNull(list, i2);
                return epoxyModel != null && epoxyModel2 != null && epoxyModel.getClass().equals(epoxyModel2.getClass()) && epoxyModel.id() == epoxyModel2.id();
            }

            public int getNewListSize() {
                return list.size();
            }

            public int getOldListSize() {
                return EpoxyAdapter.this.models.size();
            }
        });
    }

    public boolean containsModel(EpoxyModel<?> epoxyModel) {
        return this.models.indexOf(epoxyModel) >= 0;
    }

    public int getItemCount() {
        return this.models.size();
    }

    public long getItemId(int i) {
        EpoxyModel<?> model = getModel(i);
        if (model == null) {
            return -1;
        }
        return model.id();
    }

    public int getItemViewType(int i) {
        EpoxyModel<?> model = getModel(i);
        if (model == null) {
            return -1;
        }
        return model.getViewType();
    }

    public EpoxyModel<?> getModel(int i) {
        if (i < 0 || i >= this.models.size()) {
            return null;
        }
        return (EpoxyModel) this.models.get(i);
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup(int i) {
        setSpanCount(i);
        return this.spanSizeLookup;
    }

    public void insertModelBefore(EpoxyModel<?> epoxyModel, EpoxyModel<?> epoxyModel2) {
        int indexOf = this.models.indexOf(epoxyModel2);
        if (indexOf != -1) {
            this.models.add(indexOf, epoxyModel);
            notifyItemInserted(indexOf);
        }
    }

    public void insertModelsBefore(Collection<? extends EpoxyModel<?>> collection, EpoxyModel<?> epoxyModel) {
        int indexOf = this.models.indexOf(epoxyModel);
        if (indexOf != -1) {
            this.models.addAll(indexOf, collection);
            notifyItemRangeInserted(indexOf, collection.size());
        }
    }

    public void notifyModelChanged(EpoxyModel<?> epoxyModel) {
        notifyModelChanged(epoxyModel, (Object) null);
    }

    public void notifyModelChanged(EpoxyModel<?> epoxyModel, Object obj) {
        int indexOf;
        if (epoxyModel != null && (indexOf = this.models.indexOf(epoxyModel)) != -1) {
            notifyItemChanged(indexOf, obj);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.isAttached = true;
    }

    public void onBindViewHolder(EpoxyViewHolder epoxyViewHolder, int i) {
        onBindViewHolder(epoxyViewHolder, i, (List<Object>) Collections.emptyList());
    }

    public void onBindViewHolder(EpoxyViewHolder epoxyViewHolder, int i, List<Object> list) {
        EpoxyModel<?> model = getModel(i);
        if (epoxyViewHolder != null && model != null) {
            if (this.boundViewHolders.get(epoxyViewHolder.getItemId()) != null) {
                this.viewHolderState.save(this.boundViewHolders.get(epoxyViewHolder.getItemId()));
            }
            epoxyViewHolder.bind(model, list);
            this.viewHolderState.restore(epoxyViewHolder);
            this.boundViewHolders.put(epoxyViewHolder.getItemId(), epoxyViewHolder);
        }
    }

    public EpoxyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        EpoxyViewHolder create = this.models.viewHolderFactory.create(i, viewGroup);
        create.supportPropertyStage = this.supportPropertyStage;
        this.eventHookHelper.bind(create);
        return create;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.isAttached = false;
    }

    public void onViewAttachedToWindow(EpoxyViewHolder epoxyViewHolder) {
        EpoxyModel epoxyModel = epoxyViewHolder.model;
        if (epoxyModel != null) {
            epoxyModel.attachedToWindow(epoxyViewHolder);
        }
    }

    public void onViewDetachedFromWindow(EpoxyViewHolder epoxyViewHolder) {
        EpoxyModel epoxyModel = epoxyViewHolder.model;
        if (epoxyModel != null) {
            epoxyModel.detachedFromWindow(epoxyViewHolder);
        }
    }

    public void onViewRecycled(EpoxyViewHolder epoxyViewHolder) {
        if (epoxyViewHolder != null) {
            this.viewHolderState.save(epoxyViewHolder);
            this.boundViewHolders.remove(epoxyViewHolder.getItemId());
            epoxyViewHolder.unbind();
        }
    }

    public void removeModel(EpoxyModel<?> epoxyModel) {
        int indexOf = this.models.indexOf(epoxyModel);
        if (indexOf >= 0 && indexOf < this.models.size()) {
            this.models.remove(indexOf);
            notifyItemRemoved(indexOf);
        }
    }

    public void replaceAllModels(List<? extends EpoxyModel<?>> list, boolean z) {
        if (this.models.size() == 0) {
            addModels((Collection<? extends EpoxyModel<?>>) list);
            return;
        }
        DiffUtil.DiffResult calculateDiff = calculateDiff(list);
        if (z) {
            this.models.clear();
            this.models.addAll(list);
        }
        calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) this);
    }

    public void setSpanCount(int i) {
        this.spanCount = i;
    }
}
