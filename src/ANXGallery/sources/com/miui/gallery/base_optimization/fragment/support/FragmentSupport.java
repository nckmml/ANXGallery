package com.miui.gallery.base_optimization.fragment.support;

import android.app.Fragment;

public class FragmentSupport {

    public interface IActivityFragmentSupport<F extends Fragment> {
        void loadRootFragment(int i, F f);
    }
}
