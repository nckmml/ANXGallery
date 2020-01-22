package androidx.core.view;

import android.os.Build;
import android.view.ViewGroup;

public final class ViewGroupCompat {
    public static int getLayoutMode(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 18) {
            return viewGroup.getLayoutMode();
        }
        return 0;
    }
}
