package com.miui.gallery.app;

import android.os.Bundle;
import android.view.View;
import miui.app.ActionBar;

public abstract class BottomMenuFragment extends Fragment implements IBottomMenu {
    private BottomMenuFragmentDelegate mDelegate;
    private boolean mHasMenu = true;
    private boolean mMenuVisible = true;

    public ActionBar getActionBar() {
        return this.mDelegate.wrapActionBar(super.getActionBar());
    }

    public int getMenuCollapsedHeight() {
        return this.mDelegate.getMenuCollapsedHeight();
    }

    public void hideBottomMenu() {
        BottomMenuFragmentDelegate bottomMenuFragmentDelegate = this.mDelegate;
        if (bottomMenuFragmentDelegate != null) {
            bottomMenuFragmentDelegate.setVisible(false);
        }
    }

    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        BottomMenuFragmentDelegate bottomMenuFragmentDelegate = this.mDelegate;
        if (bottomMenuFragmentDelegate != null) {
            bottomMenuFragmentDelegate.updateOptionsMenu(1);
            if (!isHidden() && this.mHasMenu && this.mMenuVisible && isAdded()) {
                this.mDelegate.invalidateBottomMenu();
            }
        }
    }

    public boolean isMenuVisible() {
        return this.mHasMenu && this.mMenuVisible && !isHidden() && isAdded();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setHasOptionsMenu(false);
        super.setMenuVisibility(false);
        this.mDelegate = new BottomMenuFragmentDelegate(this);
        this.mDelegate.onCreate(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mDelegate.onViewCreated(view, bundle);
    }

    public void setHasBottomMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (this.mHasMenu && this.mDelegate != null && !isHidden() && isAdded()) {
                this.mDelegate.invalidateBottomMenu();
            }
        }
    }

    public void setHasOptionsMenu(boolean z) {
        setHasBottomMenu(z);
    }

    public void setMenuCustomView(View view) {
        BottomMenuFragmentDelegate bottomMenuFragmentDelegate = this.mDelegate;
        if (bottomMenuFragmentDelegate != null) {
            bottomMenuFragmentDelegate.setCustomView(view);
        }
    }

    public void setMenuVisibility(boolean z) {
        BottomMenuFragmentDelegate bottomMenuFragmentDelegate;
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (!isHidden() && isAdded() && (bottomMenuFragmentDelegate = this.mDelegate) != null) {
                bottomMenuFragmentDelegate.invalidateBottomMenu();
            }
        }
    }

    public void setThemeRes(int i) {
        super.setThemeRes(i);
        this.mDelegate.setExtraThemeRes(i);
    }

    public void showBottomMenu() {
        BottomMenuFragmentDelegate bottomMenuFragmentDelegate = this.mDelegate;
        if (bottomMenuFragmentDelegate != null) {
            bottomMenuFragmentDelegate.setVisible(true);
        }
    }
}
