package com.miui.gallery.app.base;

import com.miui.gallery.base_optimization.clean.UseCase;
import com.miui.gallery.base_optimization.clean.thread.ObserveThreadExecutor;
import com.miui.gallery.base_optimization.clean.thread.SubScribeThreadExecutor;
import com.miui.gallery.util.thread.RxGalleryExecutors;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class BaseUseCase<T, Param> extends UseCase<T, Param> {
    private long mStartTime;

    public BaseUseCase() {
        this(RxGalleryExecutors.getInstance().getUserThreadExecutor(), RxGalleryExecutors.getInstance().getUiThreadExecutor());
    }

    public BaseUseCase(SubScribeThreadExecutor subScribeThreadExecutor, ObserveThreadExecutor observeThreadExecutor) {
        super(subScribeThreadExecutor, observeThreadExecutor);
    }

    public void execute(DisposableSubscriber<T> disposableSubscriber, Param param) {
        this.mStartTime = System.currentTimeMillis();
        super.execute(disposableSubscriber, param);
    }
}
