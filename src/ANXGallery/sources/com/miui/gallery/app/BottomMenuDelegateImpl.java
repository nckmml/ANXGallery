package com.miui.gallery.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.miui.gallery.view.ActionBarWrapper;
import com.miui.gallery.view.menu.MenuBuilder;
import com.miui.gallery.view.menu.MenuPresenter;
import com.miui.gallery.widget.BottomMenu;

public abstract class BottomMenuDelegateImpl implements MenuBuilder.Callback, MenuPresenter.Callback {
    private ActionBarWrapper mActionBarWrapper;
    final Activity mActivity;
    BottomMenu mBottomMenu;
    protected boolean mIsBottomMenuInstalled;
    protected MenuBuilder mMenu;
    private boolean mShowHideAnimationEnabled = true;

    BottomMenuDelegateImpl(Activity activity) {
        this.mActivity = activity;
    }

    /* access modifiers changed from: private */
    public void onHideActionBar() {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu != null) {
            bottomMenu.hide(this.mShowHideAnimationEnabled);
        }
    }

    /* access modifiers changed from: private */
    public void onSetShowHideAnimationEnabled(boolean z) {
        this.mShowHideAnimationEnabled = z;
    }

    /* access modifiers changed from: private */
    public void onShowActionBar() {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu != null) {
            bottomMenu.show(this.mShowHideAnimationEnabled);
        }
    }

    /* access modifiers changed from: protected */
    public MenuBuilder createMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(getActionBarThemedContext());
        menuBuilder.setCallback(this);
        return menuBuilder;
    }

    /* access modifiers changed from: protected */
    public final Context getActionBarThemedContext() {
        Activity activity = this.mActivity;
        ActionBar actionBar = activity.getActionBar();
        return actionBar != null ? actionBar.getThemedContext() : activity;
    }

    public int getMenuCollapsedHeight() {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu != null) {
            return bottomMenu.getCollapsedHeight();
        }
        return -1;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        this.mActivity.closeOptionsMenu();
    }

    public void onCreate(Bundle bundle) {
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu == null || !bottomMenu.isOverflowReserved()) {
            menuBuilder.close();
        } else if (this.mBottomMenu.isOverflowMenuShowing() && z) {
            this.mBottomMenu.hideOverflowMenu();
        } else if (this.mBottomMenu.getVisibility() == 0) {
            this.mBottomMenu.showOverflowMenu();
        }
    }

    public void setCustomView(View view) {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu != null) {
            bottomMenu.setCustomView(view);
        }
    }

    /* access modifiers changed from: protected */
    public void setMenu(MenuBuilder menuBuilder) {
        BottomMenu bottomMenu;
        if (menuBuilder != this.mMenu) {
            this.mMenu = menuBuilder;
            if (this.mIsBottomMenuInstalled && (bottomMenu = this.mBottomMenu) != null) {
                bottomMenu.setMenu(menuBuilder, this);
            }
        }
    }

    public void setVisible(boolean z) {
        BottomMenu bottomMenu = this.mBottomMenu;
        if (bottomMenu == null) {
            return;
        }
        if (z) {
            bottomMenu.show(false);
        } else {
            bottomMenu.hide(false);
        }
    }

    /* access modifiers changed from: protected */
    public miui.app.ActionBar wrapActionBar(miui.app.ActionBar actionBar) {
        if (actionBar == null) {
            return null;
        }
        ActionBarWrapper actionBarWrapper = this.mActionBarWrapper;
        if (actionBarWrapper == null || actionBarWrapper.getWrapped() != actionBar) {
            this.mActionBarWrapper = new ActionBarWrapper(actionBar) {
                public void hide() {
                    super.hide();
                    BottomMenuDelegateImpl.this.onHideActionBar();
                }

                public void setShowHideAnimationEnabled(boolean z) {
                    super.setShowHideAnimationEnabled(z);
                    BottomMenuDelegateImpl.this.onSetShowHideAnimationEnabled(z);
                }

                public void show() {
                    super.show();
                    BottomMenuDelegateImpl.this.onShowActionBar();
                }
            };
        }
        return this.mActionBarWrapper;
    }
}
