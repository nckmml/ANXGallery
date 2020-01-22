package androidx.core.view;

import android.view.View;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
    private WeakReference<View> mView;

    public void cancel() {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }
}
