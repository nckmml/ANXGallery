package com.miui.gallery.editor.photo.core.imports.text;

import android.content.Context;
import java.io.File;

public class TextFontConfig {
    public static String FONT_PATH;

    public static void init(Context context) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        if (externalFilesDir == null) {
            externalFilesDir = context.getFilesDir();
        }
        FONT_PATH = (externalFilesDir + File.separator + "text_font") + File.separator + "font";
    }
}
