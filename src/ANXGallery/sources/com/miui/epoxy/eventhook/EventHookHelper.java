package com.miui.epoxy.eventhook;

import android.util.Log;
import android.view.View;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyViewHolder;
import java.util.ArrayList;
import java.util.List;

public class EventHookHelper<VH extends EpoxyViewHolder> {
    private final EpoxyAdapter cementAdapter;
    private final List<EventHook<VH>> eventHooks = new ArrayList();
    private boolean isAfterBind = false;

    public EventHookHelper(EpoxyAdapter epoxyAdapter) {
        this.cementAdapter = epoxyAdapter;
    }

    private void attachToView(EventHook<VH> eventHook, VH vh, View view) {
        if (view != null) {
            eventHook.onEvent(view, vh, this.cementAdapter);
            this.isAfterBind = true;
        }
    }

    public void add(EventHook<VH> eventHook) {
        if (this.isAfterBind) {
            Log.e("EventHookHelper", "can not add event hook after bind");
        } else {
            this.eventHooks.add(eventHook);
        }
    }

    public void bind(EpoxyViewHolder epoxyViewHolder) {
        for (EventHook next : this.eventHooks) {
            if (next.clazz.isInstance(epoxyViewHolder)) {
                EpoxyViewHolder epoxyViewHolder2 = (EpoxyViewHolder) next.clazz.cast(epoxyViewHolder);
                View onBind = next.onBind(epoxyViewHolder2);
                if (onBind != null) {
                    attachToView(next, epoxyViewHolder2, onBind);
                }
                List<? extends View> onBindMany = next.onBindMany(epoxyViewHolder2);
                if (onBindMany != null) {
                    for (View attachToView : onBindMany) {
                        attachToView(next, epoxyViewHolder2, attachToView);
                    }
                }
            }
        }
    }
}
