package com.miui.gallery.base_optimization.mvp.presenter;

import com.miui.gallery.base_optimization.mvp.view.IView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private CompositeDisposable mCompositeDisposable;
    private V mView;

    public void addDispose(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    public V getView() {
        return this.mView;
    }

    public void onAttachView(V v) {
        if (v != null) {
            this.mView = v;
            return;
        }
        throw new IllegalArgumentException("view can't null");
    }

    /* access modifiers changed from: protected */
    public void onDestory() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void onDetachView() {
        onDestory();
        this.mView = null;
    }
}
