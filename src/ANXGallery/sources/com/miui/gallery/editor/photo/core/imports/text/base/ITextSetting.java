package com.miui.gallery.editor.photo.core.imports.text.base;

import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.editor.photo.core.imports.text.utils.AutoLineLayout;

public interface ITextSetting {
    int getColor();

    String getText();

    AutoLineLayout.TextAlignment getTextAlignment();

    TextStyle getTextStyle();

    float getTextTransparent();

    boolean isBoldText();

    boolean isShadow();

    void setBoldText(boolean z);

    void setColor(int i);

    void setShadow(boolean z);

    void setText(String str);

    void setTextAlignment(AutoLineLayout.TextAlignment textAlignment);

    void setTextStyle(TextStyle textStyle);

    void setTextTransparent(float f);
}
