package io.reactivex;

import io.reactivex.functions.Cancellable;

public interface FlowableEmitter<T> extends Emitter<T> {
    void setCancellable(Cancellable cancellable);
}
