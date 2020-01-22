package com.miui.gallery.widget.overscroll;

import android.view.View;

public class IOverScrollInterface {

    public interface IOverScrollDecoratorAdapter {
        View getView();

        boolean isInAbsoluteEnd();

        boolean isInAbsoluteStart();
    }
}
