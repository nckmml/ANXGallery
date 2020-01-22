package com.miui.gallery.base_optimization.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;

public abstract class Fragment<P extends IPresenter> extends com.miui.gallery.app.Fragment implements IView<P> {
    private FragmentDelegate<P> mFragmentDelegate;

    public <V extends View> V findViewById(int i) {
        return this.mFragmentDelegate.findViewById(i);
    }

    public void finish() {
        getSafeActivity().onBackPressed();
    }

    public abstract int getLayoutId();

    public String getPageName() {
        return this.mFragmentDelegate.getPageName();
    }

    public P getPresenter() {
        return this.mFragmentDelegate.getPresenter();
    }

    public Activity getSafeActivity() {
        return this.mFragmentDelegate.getSafeActivity();
    }

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 16974383;
    }

    public abstract void initView(View view, Bundle bundle, View view2);

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mFragmentDelegate = new FragmentDelegate<>(this);
        this.mFragmentDelegate.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentDelegate.onCreate(bundle);
    }

    public void onDestroy() {
        this.mFragmentDelegate.onDetach();
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.mFragmentDelegate.onInflateView(layoutInflater, viewGroup, bundle);
    }

    public void onPause() {
        super.onPause();
        this.mFragmentDelegate.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mFragmentDelegate.onResume();
    }

    /* access modifiers changed from: protected */
    public void onUserFirstVisible() {
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mFragmentDelegate == null) {
            this.mFragmentDelegate = new FragmentDelegate<>(this);
        }
        this.mFragmentDelegate.setUserVisibleHint(z);
    }
}
