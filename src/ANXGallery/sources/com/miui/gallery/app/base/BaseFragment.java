package com.miui.gallery.app.base;

import android.content.res.Configuration;
import android.os.Bundle;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.base_optimization.mvp.view.Fragment;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseFragment<P extends IPresenter> extends Fragment<P> {
    private int mScreenOrientation;

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 2131820867;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != this.mScreenOrientation) {
            this.mScreenOrientation = configuration.orientation;
            int i = this.mScreenOrientation;
            if (i == 1) {
                onScreenOrientationChangeToVertical();
            } else if (i == 2) {
                onScreenOrientationChangeToHorizontal();
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mScreenOrientation = getResources().getConfiguration().orientation;
    }

    public void onPause() {
        super.onPause();
        if (useImageLoader()) {
            ImageLoader.getInstance().onPagePause();
        }
        if (recordPageByDefault()) {
            GallerySamplingStatHelper.recordPageEnd(getActivity(), getPageName());
        }
    }

    public void onResume() {
        super.onResume();
        if (useImageLoader()) {
            ImageLoader.getInstance().onPageResume();
        }
        if (recordPageByDefault()) {
            GallerySamplingStatHelper.recordPageStart(getActivity(), getPageName());
        }
    }

    /* access modifiers changed from: protected */
    public void onScreenOrientationChangeToHorizontal() {
    }

    /* access modifiers changed from: protected */
    public void onScreenOrientationChangeToVertical() {
    }

    /* access modifiers changed from: protected */
    public boolean recordPageByDefault() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean useImageLoader() {
        return true;
    }
}
