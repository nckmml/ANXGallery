package com.miui.gallery.base_optimization.clean;

import com.miui.gallery.base_optimization.clean.thread.ObserveThreadExecutor;
import com.miui.gallery.base_optimization.clean.thread.SubScribeThreadExecutor;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class UseCase<T, Param> {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ObserveThreadExecutor mObserveThreadExecutor;
    private SubScribeThreadExecutor mSubScribeThreadExecutor;

    public UseCase(SubScribeThreadExecutor subScribeThreadExecutor, ObserveThreadExecutor observeThreadExecutor) {
        this.mSubScribeThreadExecutor = subScribeThreadExecutor;
        this.mObserveThreadExecutor = observeThreadExecutor;
    }

    /* access modifiers changed from: protected */
    public final void addDisposable(Disposable disposable) {
        this.mCompositeDisposable.add(disposable);
    }

    /* access modifiers changed from: protected */
    public abstract Flowable<T> buildUseCaseFlowable(Param param);

    public void dispose() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void execute(DisposableSubscriber<T> disposableSubscriber, Param param) {
        addDisposable((Disposable) buildUseCaseFlowable(param).subscribeOn(Schedulers.from(this.mSubScribeThreadExecutor)).observeOn(this.mObserveThreadExecutor.getScheduler(), true).subscribeWith(disposableSubscriber));
    }
}
