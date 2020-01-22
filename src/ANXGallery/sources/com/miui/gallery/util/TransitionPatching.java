package com.miui.gallery.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.reflect.Method;

public class TransitionPatching {
    public static void onActivityStopWhenEnterStarting(Activity activity) {
        Object field;
        Method method;
        Log.w("TransitionPatching", "onActivityStopWhenEnterStarting");
        if (activity != null && Build.VERSION.SDK_INT > 23) {
            try {
                Object field2 = ReflectUtils.getField("android.app.Activity", activity, "mActivityTransitionState");
                if (field2 != null && (field = ReflectUtils.getField("android.app.ActivityTransitionState", field2, "mEnterTransitionCoordinator")) != null) {
                    Method method2 = ReflectUtils.getMethod("android.app.EnterTransitionCoordinator", "forceViewsToAppear");
                    if (method2 != null) {
                        ReflectUtils.invokeMethod(field, method2, new Object[0]);
                        Log.d("TransitionPatching", "forceViewsToAppear");
                    }
                    Object field3 = ReflectUtils.getField("android.app.EnterTransitionCoordinator", field, "mViewsReadyListener");
                    if (field3 != null && (method = ReflectUtils.getMethod("com.android.internal.view.OneShotPreDrawListener", "removeListener")) != null) {
                        ReflectUtils.invokeMethod(field3, method, new Object[0]);
                        Log.d("TransitionPatching", "removeListener");
                    }
                }
            } catch (Exception e) {
                Log.w("TransitionPatching", "preActivityStop occurs error.\n", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r2 = r2.getDecorView();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r0 = r2.getViewTreeObserver();
     */
    public static void setOnEnterStartedListener(Activity activity, final Runnable runnable) {
        final View decorView;
        final ViewTreeObserver viewTreeObserver;
        Log.d("TransitionPatching", "onStartEnterTransition");
        if (activity != null && runnable != null && (r2 = activity.getWindow()) != null && decorView != null && viewTreeObserver != null) {
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    Log.d("TransitionPatching", "onEnterTransitionStarted");
                    runnable.run();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this);
                        return true;
                    }
                    ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                    if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                        return true;
                    }
                    viewTreeObserver.removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
    }
}
