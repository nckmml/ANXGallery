package com.miui.gallery.base_optimization.fragment.support;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.miui.gallery.base_optimization.fragment.FragmentAnimation;
import com.miui.gallery.base_optimization.fragment.support.FragmentSupport;
import com.miui.gallery.base_optimization.fragment.utils.FragmentUtils;
import java.lang.ref.WeakReference;

public class ActivityFragmentSupportDelegate implements FragmentSupport.IActivityFragmentSupport<Fragment> {
    private WeakReference<Activity> mActivityWeakReference;
    private int mContainerId;
    private String mRootFragmentName;

    public ActivityFragmentSupportDelegate(int i, Activity activity) {
        this.mActivityWeakReference = new WeakReference<>(activity);
        this.mContainerId = i;
    }

    private String getName(Fragment fragment) {
        return fragment.getClass().getCanonicalName();
    }

    public FragmentManager getFragmentManager() {
        WeakReference<Activity> weakReference = this.mActivityWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return ((Activity) this.mActivityWeakReference.get()).getFragmentManager();
    }

    public void loadRootFragment(int i, Fragment fragment) {
        this.mContainerId = i;
        this.mRootFragmentName = getName(fragment);
        Bundle arguments = fragment.getArguments();
        if (arguments != null) {
            arguments.putInt("fragment_contail_id", this.mContainerId);
        }
        FragmentUtils.startFragment(this.mContainerId, getFragmentManager(), fragment, (FragmentAnimation) null, true, false, this.mRootFragmentName);
    }
}
