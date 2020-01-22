package com.miui.gallery.editor.photo.screen.base;

import android.app.Fragment;
import android.os.Bundle;
import com.miui.gallery.editor.photo.screen.base.IScreenOperation;
import com.miui.gallery.editor.photo.screen.home.ScreenEditorFragment;

public abstract class ScreenBaseMenuFragment<T extends IScreenOperation> extends Fragment {
    protected T mScreenOperation;

    /* access modifiers changed from: protected */
    public abstract Class<T> getScreenOperationClass();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mScreenOperation = ((ScreenEditorFragment) getFragmentManager().findFragmentByTag("fragment_tag_editor")).getScreenOperation(getScreenOperationClass());
    }
}
