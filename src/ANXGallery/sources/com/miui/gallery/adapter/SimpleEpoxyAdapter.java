package com.miui.gallery.adapter;

import com.miui.epoxy.BaseEpoxyAdapter;
import com.miui.epoxy.EpoxyModel;
import com.miui.gallery.adapter.itemmodel.trans.ItemModelTransManager;
import java.util.Collection;
import java.util.Collections;

public class SimpleEpoxyAdapter<T> extends BaseEpoxyAdapter<T> {
    public void notifyDataChanged(T t) {
        if (t != null && -1 != this.dataList.indexOf(t)) {
            notifyModelChanged(getModel(this.dataList.indexOf(t)));
        }
    }

    public void removeData(T t) {
        super.removeData(t);
        checkEmptyView();
    }

    /* access modifiers changed from: protected */
    public Collection<? extends EpoxyModel<?>> transData(T t) {
        return Collections.singletonList(ItemModelTransManager.transDataToModel(t));
    }
}
