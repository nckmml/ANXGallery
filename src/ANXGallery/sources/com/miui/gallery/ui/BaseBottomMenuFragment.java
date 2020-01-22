package com.miui.gallery.ui;

import android.app.Activity;
import android.os.Bundle;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.app.BottomMenuFragment;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseBottomMenuFragment extends BottomMenuFragment {
    protected BaseActivity mActivity;
    protected boolean mUserFirstVisible = false;

    public void finish() {
        if (this.mActivity.getFragmentManager().getBackStackEntryCount() > 0) {
            this.mActivity.getFragmentManager().popBackStack();
        } else {
            this.mActivity.onBackPressed();
        }
    }

    public abstract String getPageName();

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 2131820867;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int themeRes = getThemeRes();
        if (themeRes != 0) {
            setThemeRes(themeRes);
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onPause() {
        super.onPause();
        if (useImageLoader()) {
            ImageLoader.getInstance().onPagePause();
        }
        if (recordPageByDefault()) {
            GallerySamplingStatHelper.recordPageEnd(this.mActivity, getPageName());
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onResume() {
        super.onResume();
        if (useImageLoader()) {
            ImageLoader.getInstance().onPageResume();
        }
        if (recordPageByDefault()) {
            GallerySamplingStatHelper.recordPageStart(this.mActivity, getPageName());
        }
    }

    /* access modifiers changed from: protected */
    public void onUserFirstVisible() {
    }

    /* access modifiers changed from: protected */
    public boolean recordPageByDefault() {
        return true;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (getUserVisibleHint() && !this.mUserFirstVisible) {
            onUserFirstVisible();
            this.mUserFirstVisible = true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean useImageLoader() {
        return true;
    }
}
