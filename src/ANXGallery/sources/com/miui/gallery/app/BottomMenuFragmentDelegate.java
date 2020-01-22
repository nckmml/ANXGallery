package com.miui.gallery.app;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.view.menu.MenuBuilder;
import com.miui.gallery.widget.BottomMenu;
import miui.app.Fragment;

public class BottomMenuFragmentDelegate extends BottomMenuDelegateImpl {
    private int mExtraThemeRes;
    private Fragment mFragment;
    /* access modifiers changed from: private */
    public byte mInvalidateMenuFlags;
    private final Runnable mInvalidateMenuRunnable = new Runnable() {
        public void run() {
            boolean z = true;
            if ((BottomMenuFragmentDelegate.this.mInvalidateMenuFlags & 1) == 1) {
                MenuBuilder unused = BottomMenuFragmentDelegate.this.mMenu = null;
            }
            if (BottomMenuFragmentDelegate.this.mMenu == null) {
                BottomMenuFragmentDelegate bottomMenuFragmentDelegate = BottomMenuFragmentDelegate.this;
                MenuBuilder unused2 = bottomMenuFragmentDelegate.mMenu = bottomMenuFragmentDelegate.createMenu();
                BottomMenuFragmentDelegate bottomMenuFragmentDelegate2 = BottomMenuFragmentDelegate.this;
                z = bottomMenuFragmentDelegate2.onCreatePanelMenu(0, bottomMenuFragmentDelegate2.mMenu);
            }
            if (z) {
                BottomMenuFragmentDelegate bottomMenuFragmentDelegate3 = BottomMenuFragmentDelegate.this;
                z = bottomMenuFragmentDelegate3.onPreparePanel(0, (View) null, bottomMenuFragmentDelegate3.mMenu);
            }
            if (z) {
                BottomMenuFragmentDelegate bottomMenuFragmentDelegate4 = BottomMenuFragmentDelegate.this;
                bottomMenuFragmentDelegate4.setMenu(bottomMenuFragmentDelegate4.mMenu);
            } else {
                BottomMenuFragmentDelegate.this.setMenu((MenuBuilder) null);
                MenuBuilder unused3 = BottomMenuFragmentDelegate.this.mMenu = null;
            }
            BottomMenuFragmentDelegate bottomMenuFragmentDelegate5 = BottomMenuFragmentDelegate.this;
            byte unused4 = bottomMenuFragmentDelegate5.mInvalidateMenuFlags = (byte) (bottomMenuFragmentDelegate5.mInvalidateMenuFlags & -18);
        }
    };
    /* access modifiers changed from: private */
    public MenuBuilder mMenu;
    private Context mThemedContext;

    public BottomMenuFragmentDelegate(Fragment fragment) {
        super(fragment.getActivity());
        this.mFragment = fragment;
    }

    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            this.mThemedContext = this.mActivity;
            int i = this.mExtraThemeRes;
            if (i != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mThemedContext, i);
            }
        }
        return this.mThemedContext;
    }

    /* access modifiers changed from: package-private */
    public final void installBottomMenu() {
        if (!this.mIsBottomMenuInstalled) {
            View view = this.mFragment.getView();
            if (view != null && (view instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) view;
                this.mBottomMenu = (BottomMenu) LayoutInflater.from(getThemedContext()).inflate(R.layout.screen_bottom_menu, viewGroup, false);
                viewGroup.addView(this.mBottomMenu);
                this.mBottomMenu.bringToFront();
                updateOptionsMenu(1);
                invalidateBottomMenu();
                this.mIsBottomMenuInstalled = true;
            }
        } else if (this.mBottomMenu.getParent() != null && (this.mBottomMenu.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup2 = (ViewGroup) this.mBottomMenu.getParent();
            if (viewGroup2.getChildCount() == 0) {
                viewGroup2.endViewTransition(this.mBottomMenu);
            }
        }
    }

    public void invalidateBottomMenu() {
        byte b = this.mInvalidateMenuFlags;
        if ((b & 16) == 0) {
            this.mInvalidateMenuFlags = (byte) (b | 16);
            this.mFragment.getActivity().getWindow().getDecorView().post(this.mInvalidateMenuRunnable);
        }
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return i == 0 && this.mFragment.isMenuVisible() && this.mFragment.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (i == 0) {
            return this.mFragment.onOptionsItemSelected(menuItem);
        }
        return false;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return onMenuItemSelected(0, menuItem);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return false;
        }
        if (!this.mFragment.isMenuVisible()) {
            return true;
        }
        this.mFragment.onPrepareOptionsMenu(menu);
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        installBottomMenu();
    }

    public void setExtraThemeRes(int i) {
        this.mExtraThemeRes = i;
    }

    public void updateOptionsMenu(int i) {
        this.mInvalidateMenuFlags = (byte) ((i & 1) | this.mInvalidateMenuFlags);
    }
}
