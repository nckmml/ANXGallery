package com.miui.gallery.base_optimization.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import com.miui.gallery.base_optimization.fragment.support.ActivityFragmentSupportDelegate;
import com.miui.gallery.base_optimization.fragment.support.FragmentSupport;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;

public abstract class Activity<P extends IPresenter> extends com.miui.gallery.app.Activity implements FragmentSupport.IActivityFragmentSupport, IView<P> {
    private ActivityDelegate<P> mActivityDelegate;
    private FragmentSupport.IActivityFragmentSupport mFragmentSupport;

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.Activity, com.miui.gallery.base_optimization.mvp.view.Activity] */
    private void initFragmentSupport(int i) {
        if (this.mFragmentSupport == null) {
            this.mFragmentSupport = new ActivityFragmentSupportDelegate(i, this);
        }
    }

    public abstract int getLayoutId();

    public void loadRootFragment(int i, Fragment fragment) {
        if (this.mFragmentSupport == null) {
            initFragmentSupport(i);
        }
        this.mFragmentSupport.loadRootFragment(i, fragment);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivityDelegate = new ActivityDelegate<>(this);
        this.mActivityDelegate.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mActivityDelegate = null;
        this.mFragmentSupport = null;
    }
}
