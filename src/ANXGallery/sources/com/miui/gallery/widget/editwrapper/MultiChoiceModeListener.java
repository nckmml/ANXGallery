package com.miui.gallery.widget.editwrapper;

import android.view.ActionMode;

public interface MultiChoiceModeListener extends ActionMode.Callback {
    void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z);

    void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z);
}
