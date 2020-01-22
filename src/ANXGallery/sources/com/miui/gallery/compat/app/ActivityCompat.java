package com.miui.gallery.compat.app;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import com.miui.gallery.util.MiscUtil;
import java.util.List;

public abstract class ActivityCompat {

    public interface SharedElementCallback {
        void onSharedElementStart();
    }

    public static void finishAfterTransition(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    public static boolean isInMultiWindowMode(Activity activity) {
        Object invokeSafely;
        if (Build.VERSION.SDK_INT >= 24) {
            return activity.isInMultiWindowMode();
        }
        if (Build.VERSION.SDK_INT < 21 || (invokeSafely = MiscUtil.invokeSafely(activity, "isInMultiWindowMode", (Class<?>[]) null, new Object[0])) == null || !(invokeSafely instanceof Boolean)) {
            return false;
        }
        return ((Boolean) invokeSafely).booleanValue();
    }

    public static void postponeEnterTransition(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.postponeEnterTransition();
        }
    }

    public static void setEnterSharedElementCallback(Activity activity, final SharedElementCallback sharedElementCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setEnterSharedElementCallback(new android.app.SharedElementCallback() {
                public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
                    SharedElementCallback sharedElementCallback = sharedElementCallback;
                    if (sharedElementCallback != null) {
                        sharedElementCallback.onSharedElementStart();
                    }
                }
            });
        }
    }

    public static void startPostponedEnterTransition(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }
}
