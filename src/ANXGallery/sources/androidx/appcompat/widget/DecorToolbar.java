package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;
import android.view.Window;

public interface DecorToolbar {
    CharSequence getTitle();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setLogo(int i);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
