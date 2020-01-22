package com.miui.gallery.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.WindowInsetsCompat;
import com.miui.gallery.R;
import com.miui.gallery.compat.view.ViewCompat;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.view.menu.ActionMenuPresenter;
import com.miui.gallery.view.menu.ActionMenuView;
import com.miui.gallery.view.menu.MenuBuilder;
import com.miui.gallery.view.menu.MenuPresenter;
import com.miui.gallery.view.menu.PhoneActionMenuView;
import miui.view.animation.CubicEaseInInterpolator;
import miui.view.animation.CubicEaseOutInterpolator;

public class BottomMenu extends FrameLayout {
    private ActionMenuPresenter mActionMenuPresenter;
    private Rect mBaseInnerInsets = new Rect();
    /* access modifiers changed from: private */
    public View mContentMask;
    private View.OnClickListener mContentMaskOnClickListener;
    private View mCustomView;
    private boolean mHiddenByApp;
    private Animator mMenuBarAnim;
    /* access modifiers changed from: private */
    public ActionMenuView mMenuView;
    private MenuBuilder mOptionsMenu;
    /* access modifiers changed from: private */
    public PhoneActionMenuView mSplitMenuView;

    public class ContentMaskAnimator implements Animator.AnimatorListener {
        private ObjectAnimator mHideAnimator;
        private View.OnClickListener mOnClickListener;
        private ObjectAnimator mShowAnimator;

        private ContentMaskAnimator(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            this.mShowAnimator = ObjectAnimator.ofFloat(BottomMenu.this.mContentMask, "alpha", new float[]{0.0f, 1.0f});
            this.mShowAnimator.addListener(this);
            this.mHideAnimator = ObjectAnimator.ofFloat(BottomMenu.this.mContentMask, "alpha", new float[]{1.0f, 0.0f});
            this.mHideAnimator.addListener(this);
        }

        public Animator hide() {
            return this.mHideAnimator;
        }

        public void onAnimationCancel(Animator animator) {
            if (animator == this.mHideAnimator) {
                BottomMenu.this.mMenuView.bringToFront();
                BottomMenu.this.mContentMask.setOnClickListener((View.OnClickListener) null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (BottomMenu.this.mContentMask.getAlpha() == 0.0f) {
                BottomMenu.this.mMenuView.bringToFront();
                BottomMenu.this.mContentMask.setOnClickListener((View.OnClickListener) null);
                BottomMenu.this.mContentMask.setVisibility(8);
            }
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
            if (animator == this.mShowAnimator) {
                BottomMenu.this.mContentMask.setVisibility(0);
                BottomMenu.this.mContentMask.bringToFront();
                BottomMenu.this.mMenuView.bringToFront();
                BottomMenu.this.mContentMask.setOnClickListener(this.mOnClickListener);
            }
        }

        public Animator show() {
            return this.mShowAnimator;
        }
    }

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BottomMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private boolean applyInsets(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (!z || layoutParams.leftMargin == rect.left) {
            z5 = false;
        } else {
            layoutParams.leftMargin = rect.left;
            z5 = true;
        }
        if (z2 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z5 = true;
        }
        if (z4 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z5 = true;
        }
        if (!z3 || layoutParams.bottomMargin == rect.bottom) {
            return z5;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    private void configPresenters(MenuBuilder menuBuilder) {
        if (menuBuilder != null) {
            menuBuilder.addMenuPresenter(this.mActionMenuPresenter);
        } else {
            this.mActionMenuPresenter.initForMenu(getContext(), (MenuBuilder) null);
        }
        this.mActionMenuPresenter.updateMenuView(true);
    }

    private Animator getMenuBarAnimator(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (z) {
            ActionMenuView actionMenuView = this.mMenuView;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "TranslationY", new float[]{actionMenuView.getTranslationY(), 0.0f});
            ActionMenuView actionMenuView2 = this.mMenuView;
            animatorSet.playTogether(new Animator[]{ofFloat, ObjectAnimator.ofFloat(actionMenuView2, "Alpha", new float[]{actionMenuView2.getAlpha(), 1.0f})});
            animatorSet.setInterpolator(new CubicEaseOutInterpolator());
            animatorSet.setDuration((long) getContext().getResources().getInteger(R.integer.action_bar_appear_duration));
        } else {
            int menuBarHeight = getMenuBarHeight() + ViewCompat.getSystemWindowInsetBottom(this);
            ActionMenuView actionMenuView3 = this.mMenuView;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView3, "TranslationY", new float[]{actionMenuView3.getTranslationY(), (float) menuBarHeight});
            ActionMenuView actionMenuView4 = this.mMenuView;
            animatorSet.playTogether(new Animator[]{ofFloat2, ObjectAnimator.ofFloat(actionMenuView4, "Alpha", new float[]{actionMenuView4.getAlpha(), 0.0f})});
            animatorSet.setInterpolator(new CubicEaseInInterpolator());
            animatorSet.setDuration((long) getContext().getResources().getInteger(R.integer.action_bar_disappear_duration));
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    BottomMenu.this.mMenuView.setVisibility(8);
                }
            });
        }
        return animatorSet;
    }

    private int getMenuBarHeight() {
        int height = this.mMenuView.getHeight();
        ActionMenuView actionMenuView = this.mMenuView;
        if (!(actionMenuView instanceof PhoneActionMenuView)) {
            return height;
        }
        PhoneActionMenuView phoneActionMenuView = (PhoneActionMenuView) actionMenuView;
        return !phoneActionMenuView.isOverflowMenuShowing() ? phoneActionMenuView.getCollapsedHeight() : height;
    }

    private boolean isBackPressed(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1;
    }

    private boolean isLayoutHideNavigation() {
        return (getWindowSystemUiVisibility() & 512) != 0;
    }

    private void pullChildren() {
        View view;
        if (this.mContentMask == null) {
            this.mContentMask = findViewById(R.id.content_mask);
            if (BuildUtil.isOledDisplay() && (view = this.mContentMask) != null) {
                view.setBackground(getContext().getResources().getDrawable(R.drawable.window_content_mask_oled));
            }
        }
    }

    private void updateContentMaskVisibility(boolean z) {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView instanceof PhoneActionMenuView) {
            this.mSplitMenuView = (PhoneActionMenuView) actionMenuView;
            if (this.mSplitMenuView.isOverflowMenuShowing() && this.mContentMask != null) {
                if (z) {
                    getContentMaskAnimator(this.mContentMaskOnClickListener).show().start();
                } else {
                    getContentMaskAnimator((View.OnClickListener) null).hide().start();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ActionMenuPresenter createActionMenuPresenter(MenuPresenter.Callback callback) {
        ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(getContext(), BuildUtil.isTablet() ? R.layout.action_menu_layout_tablet : R.layout.action_menu_layout, R.layout.action_menu_item_layout, R.layout.action_bar_expanded_menu_layout, R.layout.action_bar_list_menu_item_layout);
        actionMenuPresenter.setCallback(callback);
        actionMenuPresenter.setId(R.id.action_menu_presenter);
        return actionMenuPresenter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        r3 = r2.mActionMenuPresenter;
     */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ActionMenuPresenter actionMenuPresenter;
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return isBackPressed(keyEvent) && actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu(false);
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        boolean z;
        this.mBaseInnerInsets.set(ViewCompat.getSystemWindowInsets(this));
        if (!isLayoutHideNavigation() || WindowInsetsCompat.shouldAlwaysConsumeSystemBars(this)) {
            Rect rect2 = this.mBaseInnerInsets;
            rect2.left = 0;
            rect2.right = 0;
        }
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            z = applyInsets(actionMenuView, this.mBaseInnerInsets, true, false, false, true) | false;
        } else {
            z = false;
        }
        if (z) {
            requestLayout();
        }
        return false;
    }

    public int getAnimatedVisibility() {
        if (!this.mHiddenByApp) {
            return getVisibility();
        }
        return 8;
    }

    public int getCollapsedHeight() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null) {
            return -1;
        }
        int collapsedHeight = actionMenuView.getCollapsedHeight();
        if (collapsedHeight > 0) {
            return collapsedHeight;
        }
        return -1;
    }

    public ContentMaskAnimator getContentMaskAnimator(View.OnClickListener onClickListener) {
        return new ContentMaskAnimator(onClickListener);
    }

    public void hide(boolean z) {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            if (this.mMenuView != null) {
                Animator animator = this.mMenuBarAnim;
                if (animator != null) {
                    animator.cancel();
                }
                if (z) {
                    this.mMenuBarAnim = getMenuBarAnimator(false);
                    this.mMenuBarAnim.start();
                } else {
                    this.mMenuView.setTranslationY((float) (getMenuBarHeight() + ViewCompat.getSystemWindowInsetBottom(this)));
                    this.mMenuView.setAlpha(0.0f);
                    this.mMenuView.setVisibility(8);
                }
                updateContentMaskVisibility(false);
            }
        }
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu(false);
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowReserved();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestFitSystemWindows();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        pullChildren();
        this.mContentMaskOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (BottomMenu.this.mSplitMenuView != null && BottomMenu.this.mSplitMenuView.isOverflowMenuShowing()) {
                    BottomMenu.this.mSplitMenuView.getPresenter().hideOverflowMenu(true);
                }
            }
        };
    }

    public void setCustomView(View view) {
        this.mCustomView = view;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.setCustomView(view);
        }
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        ViewGroup viewGroup;
        MenuBuilder menuBuilder = this.mOptionsMenu;
        if (menu != menuBuilder) {
            if (menuBuilder != null) {
                menuBuilder.removeMenuPresenter(this.mActionMenuPresenter);
            }
            MenuBuilder menuBuilder2 = (MenuBuilder) menu;
            this.mOptionsMenu = menuBuilder2;
            ActionMenuView actionMenuView = this.mMenuView;
            if (!(actionMenuView == null || (viewGroup = (ViewGroup) actionMenuView.getParent()) == null)) {
                viewGroup.removeView(this.mMenuView);
            }
            if (this.mActionMenuPresenter == null) {
                this.mActionMenuPresenter = createActionMenuPresenter(callback);
            }
            this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
            this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 80;
            configPresenters(menuBuilder2);
            ActionMenuView actionMenuView2 = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
            ViewGroup viewGroup2 = (ViewGroup) actionMenuView2.getParent();
            if (!(viewGroup2 == null || viewGroup2 == this)) {
                viewGroup2.removeView(actionMenuView2);
            }
            int animatedVisibility = getAnimatedVisibility();
            actionMenuView2.setVisibility(animatedVisibility);
            addView(actionMenuView2, layoutParams);
            View findViewById = actionMenuView2.findViewById(R.id.expanded_menu);
            if (findViewById != null) {
                findViewById.requestLayout();
            }
            if (animatedVisibility != 8) {
                requestFitSystemWindows();
            }
            actionMenuView2.setCustomView(this.mCustomView);
            this.mMenuView = actionMenuView2;
        }
    }

    public void show(boolean z) {
        if (this.mHiddenByApp) {
            this.mHiddenByApp = false;
            if (this.mMenuView == null) {
                Log.d("BottomMenu", "show: menuView is null");
                return;
            }
            Animator animator = this.mMenuBarAnim;
            if (animator != null) {
                animator.cancel();
            }
            this.mMenuView.setVisibility(0);
            if (z) {
                this.mMenuBarAnim = getMenuBarAnimator(true);
                this.mMenuBarAnim.start();
                ActionMenuView actionMenuView = this.mMenuView;
                if ((actionMenuView instanceof PhoneActionMenuView) && (!((PhoneActionMenuView) actionMenuView).isOverflowMenuShowing())) {
                    this.mMenuView.startLayoutAnimation();
                }
            } else {
                this.mMenuView.setTranslationY(0.0f);
                this.mMenuView.setAlpha(1.0f);
            }
            updateContentMaskVisibility(true);
        }
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu();
    }
}
