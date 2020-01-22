package com.miui.gallery.ui;

import android.view.View;

public interface CheckableView extends Checkable {
    void setCheckableListener(View.OnClickListener onClickListener);
}
