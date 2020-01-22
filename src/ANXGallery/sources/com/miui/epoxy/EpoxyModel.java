package com.miui.epoxy;

import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyViewHolder;
import java.util.List;

public abstract class EpoxyModel<T extends EpoxyViewHolder> {
    private static long idCounter = -2;
    private boolean afterModelBuild;
    private long id;
    private boolean shouldStageProperty;
    protected T viewHolder;

    /* JADX WARNING: Illegal instructions before constructor call */
    public EpoxyModel() {
        this(r0);
        long j = idCounter;
        idCounter = j - 1;
    }

    protected EpoxyModel(long j) {
        this.afterModelBuild = false;
        this.shouldStageProperty = false;
        if (j == -1) {
            long j2 = idCounter;
            idCounter = j2 - 1;
            this.id = j2;
            return;
        }
        this.id = j;
    }

    protected static int hashInt(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        return i3 ^ (i3 << 5);
    }

    public void attachedToWindow(T t) {
        this.viewHolder = t;
    }

    public void bindData(T t) {
        bindData(t, (List<Object>) null);
    }

    public void bindData(T t, List<Object> list) {
    }

    public void bindPartialData(T t) {
        bindData(t);
    }

    public void detachedFromWindow(T t) {
        T t2 = this.viewHolder;
        if (t2 != null) {
            t2.unbind();
        }
    }

    public void doPropertiesCleanUp() {
    }

    public void doPropertiesStage(EpoxyModel epoxyModel) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EpoxyModel)) {
            return false;
        }
        EpoxyModel epoxyModel = (EpoxyModel) obj;
        if (this.id != epoxyModel.id) {
            return false;
        }
        return getViewType() == epoxyModel.getViewType();
    }

    public abstract int getLayoutRes();

    public int getSpanSize(int i, int i2, int i3) {
        return 1;
    }

    public abstract EpoxyAdapter.IViewHolderCreator<T> getViewHolderCreator();

    /* access modifiers changed from: protected */
    public int getViewType() {
        return hashInt(getLayoutRes());
    }

    public int hashCode() {
        long j = this.id;
        return (((int) (j ^ (j >>> 32))) * 31) + getViewType();
    }

    public final long id() {
        return this.id;
    }

    public boolean isContentTheSame(EpoxyModel<?> epoxyModel) {
        return true;
    }

    public boolean isPropertiesChanged() {
        return false;
    }

    public boolean shouldSaveViewState() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void shouldStageProperty(boolean z) {
        this.shouldStageProperty = z;
    }

    public void unbind(T t) {
    }
}
