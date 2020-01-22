package com.miui.gallery.base_optimization.mvp.view;

import android.os.Bundle;
import com.miui.gallery.base_optimization.R;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.base_optimization.util.GenericUtils;

public class ActivityDelegate<P extends IPresenter> implements IView<P> {
    private boolean isInflate;
    private Activity mActivity;
    private P mPresenter;

    public ActivityDelegate(Activity activity) {
        this.mActivity = activity;
    }

    public void onCreate(Bundle bundle) {
        if (!this.isInflate) {
            if (-1 == this.mActivity.getLayoutId()) {
                this.mActivity.setContentView(R.layout.base_activity_layout);
            } else {
                Activity activity = this.mActivity;
                activity.setContentView(activity.getLayoutId());
            }
            this.isInflate = true;
        }
        if (this.mPresenter == null) {
            this.mPresenter = (IPresenter) GenericUtils.getClassT(this.mActivity, 0);
            P p = this.mPresenter;
            if (p != null) {
                p.onAttachView(this.mActivity);
            }
        }
    }
}
