package com.miui.epoxy.eventhook;

import android.view.View;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyModel;
import com.miui.epoxy.EpoxyViewHolder;

public abstract class OnClickEventHook<VH extends EpoxyViewHolder> extends EventHook<VH> {
    public OnClickEventHook(Class<VH> cls) {
        super(cls);
    }

    public abstract void onClick(View view, VH vh, int i, EpoxyModel epoxyModel);

    public void onEvent(View view, final VH vh, final EpoxyAdapter epoxyAdapter) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int adapterPosition = vh.getAdapterPosition();
                EpoxyModel<?> model = epoxyAdapter.getModel(adapterPosition);
                if (adapterPosition != -1 && model != null) {
                    OnClickEventHook.this.onClick(view, vh, adapterPosition, model);
                }
            }
        });
    }
}
