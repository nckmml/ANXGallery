package com.miui.epoxy.eventhook;

import android.view.View;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyViewHolder;
import java.util.List;

public abstract class EventHook<VH extends EpoxyViewHolder> {
    final Class<VH> clazz;

    public EventHook(Class<VH> cls) {
        this.clazz = cls;
    }

    public View onBind(VH vh) {
        return null;
    }

    public List<? extends View> onBindMany(VH vh) {
        return null;
    }

    public abstract void onEvent(View view, VH vh, EpoxyAdapter epoxyAdapter);
}
