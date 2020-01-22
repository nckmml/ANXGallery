package com.miui.gallery.base_optimization.fragment.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import com.miui.gallery.base_optimization.fragment.FragmentAnimation;

public class FragmentUtils {
    public static void startFragment(int i, FragmentManager fragmentManager, Fragment fragment, FragmentAnimation fragmentAnimation, boolean z, boolean z2, String str) {
        if (fragmentManager == null || i <= 0) {
            return;
        }
        if (TextUtils.isEmpty(str) || fragmentManager.findFragmentByTag(str) == null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (z2) {
                beginTransaction.addToBackStack(str);
            }
            if (!z || fragment.isAdded()) {
                beginTransaction.replace(i, fragment, str);
            } else {
                beginTransaction.add(i, fragment, str);
            }
            if (fragmentAnimation != null) {
                beginTransaction.setCustomAnimations(fragmentAnimation.getEnterAnim(), 0, 0, fragmentAnimation.getExitAnim());
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }
}
