package com.miui.gallery.base_optimization.mvp.view;

import android.os.Bundle;
import com.miui.gallery.base_optimization.toolbar.IToolbar;
import com.miui.gallery.base_optimization.toolbar.ToolbarDelegate;

public abstract class BaseToolBarActivity<T> extends Activity implements IToolbar<T> {
    private ToolbarDelegate<T> mToolbarDelegate;

    public T getTopBar() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mToolbarDelegate = new ToolbarDelegate<>();
        this.mToolbarDelegate.onCreate(this, getTopBar() == null ? getActionBar() : getTopBar());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    public void setTitle(int i) {
        this.mToolbarDelegate.setTitle(i);
    }
}
