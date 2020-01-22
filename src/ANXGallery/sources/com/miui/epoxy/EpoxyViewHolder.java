package com.miui.epoxy;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EpoxyViewHolder extends RecyclerView.ViewHolder {
    EpoxyModel model;
    boolean supportPropertyStage = false;

    public EpoxyViewHolder(View view) {
        super(view);
    }

    /* access modifiers changed from: package-private */
    public void bind(EpoxyModel epoxyModel, List<Object> list) {
        if (this.supportPropertyStage) {
            epoxyModel.shouldStageProperty(true);
        }
        if (epoxyModel.isPropertiesChanged()) {
            epoxyModel.bindPartialData(this);
            epoxyModel.doPropertiesCleanUp();
        } else if (list == null || list.isEmpty()) {
            epoxyModel.bindData(this);
        } else {
            epoxyModel.bindData(this, list);
        }
        this.model = epoxyModel;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldSaveViewState() {
        EpoxyModel epoxyModel = this.model;
        return epoxyModel != null && epoxyModel.shouldSaveViewState();
    }

    /* access modifiers changed from: package-private */
    public void unbind() {
        EpoxyModel epoxyModel = this.model;
        if (epoxyModel != null) {
            if (this.supportPropertyStage) {
                epoxyModel.shouldStageProperty(false);
            }
            if (this.model.isPropertiesChanged()) {
                this.model.doPropertiesCleanUp();
            }
            this.model.unbind(this);
            this.model = null;
        }
    }
}
