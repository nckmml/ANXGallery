package com.miui.gallery.base_optimization.toolbar;

import android.app.ActionBar;

public class ActionbarImpl implements IToolbar<ActionBar> {
    private ActionBar mActionbar;

    public ActionbarImpl(ActionBar actionBar) {
        this.mActionbar = actionBar;
    }

    public void setTitle(int i) {
        this.mActionbar.setTitle(i);
    }
}
