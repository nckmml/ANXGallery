package com.miui.gallery.base_optimization.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.base_optimization.util.GenericUtils;
import java.lang.ref.WeakReference;

public class FragmentDelegate<P extends IPresenter> implements IView<P> {
    private WeakReference<Activity> mActivity;
    private Fragment<P> mFragment;
    private P mPresenter;
    private View mRootView;
    protected boolean mUserFirstVisible = false;
    private SparseArray<View> mViews;

    public FragmentDelegate(Fragment<P> fragment) {
        this.mFragment = fragment;
    }

    private final P findPresenterFromClass(int i) {
        P classT = GenericUtils.getClassT(this.mFragment, i);
        if (classT == null || !(classT instanceof IPresenter)) {
            return null;
        }
        return (IPresenter) classT;
    }

    public <V extends View> V findViewById(int i) {
        if (this.mRootView == null) {
            return null;
        }
        if (this.mViews == null) {
            this.mViews = new SparseArray<>();
        }
        if (this.mViews.get(i) != null) {
            return (View) this.mViews.get(i);
        }
        V findViewById = this.mRootView.findViewById(i);
        this.mViews.put(i, findViewById);
        return findViewById;
    }

    public String getPageName() {
        Fragment<P> fragment = this.mFragment;
        return fragment != null ? fragment.getClass().getSimpleName() : "";
    }

    public P getPresenter() {
        return this.mPresenter;
    }

    public Activity getSafeActivity() {
        if (this.mFragment.getActivity() != null) {
            return this.mFragment.getActivity();
        }
        WeakReference<Activity> weakReference = this.mActivity;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void initPresenter() {
        if (this.mPresenter == null) {
            this.mPresenter = findPresenterFromClass(0);
            if (this.mPresenter == null) {
                this.mPresenter = findPresenterFromClass(1);
            }
            P p = this.mPresenter;
            if (p != null) {
                p.onAttachView(this.mFragment);
            }
        }
    }

    public void onAttach(Activity activity) {
        if (this.mActivity == null) {
            this.mActivity = new WeakReference<>(activity);
        }
    }

    public void onCreate(Bundle bundle) {
        int themeRes = this.mFragment.getThemeRes();
        if (themeRes != 0) {
            setThemeRes(themeRes);
        }
        initPresenter();
    }

    public void onDetach() {
        WeakReference<Activity> weakReference = this.mActivity;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.mActivity = null;
        P p = this.mPresenter;
        if (p != null) {
            p.onDetachView();
        }
        this.mRootView = null;
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (-1 != this.mFragment.getLayoutId()) {
            View view = this.mRootView;
            if (view != null) {
                ((ViewGroup) view).removeAllViews();
            }
            this.mRootView = layoutInflater.inflate(this.mFragment.getLayoutId(), viewGroup, false);
            this.mFragment.initView(this.mRootView, bundle, viewGroup);
            return this.mRootView;
        }
        throw new IllegalArgumentException("layoutId can't null");
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void setThemeRes(int i) {
        Fragment<P> fragment = this.mFragment;
        if (fragment != null) {
            fragment.setThemeRes(i);
        }
    }

    public void setUserVisibleHint(boolean z) {
        if (z && !this.mUserFirstVisible) {
            this.mUserFirstVisible = true;
            this.mFragment.onUserFirstVisible();
        }
    }
}
