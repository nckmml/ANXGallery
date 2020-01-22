package com.miui.gallery.base_optimization.toolbar;

import android.app.ActionBar;
import android.os.Build;
import android.widget.Toolbar;
import java.lang.ref.WeakReference;
import miui.app.Activity;

public class ToolbarDelegate<T> implements IToolbar<T> {
    private WeakReference<Activity> mActivity;
    private IToolbar mToolbar;

    public void onCreate(Activity activity, T t) {
        this.mActivity = new WeakReference<>(activity);
        if (Build.VERSION.SDK_INT < 21 || !(t instanceof Toolbar)) {
            this.mToolbar = new ActionbarImpl((ActionBar) t);
            return;
        }
        Toolbar toolbar = (Toolbar) t;
        this.mToolbar = new ToolBarImpl(toolbar);
        activity.setActionBar(toolbar);
    }

    public void setTitle(int i) {
        this.mToolbar.setTitle(i);
    }
}
