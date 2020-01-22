package com.miui.gallery.base_optimization.toolbar;

import android.annotation.TargetApi;
import android.widget.Toolbar;

@TargetApi(21)
public class ToolBarImpl implements IToolbar<Toolbar> {
    private Toolbar mToolbar;

    public ToolBarImpl(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }

    public void setTitle(int i) {
        this.mToolbar.setTitle(i);
    }
}
