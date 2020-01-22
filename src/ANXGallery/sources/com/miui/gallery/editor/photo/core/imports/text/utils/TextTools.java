package com.miui.gallery.editor.photo.core.imports.text.utils;

import android.graphics.Typeface;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.MiscUtil;
import java.util.List;
import java.util.Locale;

public class TextTools {
    public static void checkResourceExist(List<TextStyle> list) {
        if (MiscUtil.isValid(list)) {
            for (TextStyle next : list) {
                if (next.isLocal()) {
                    next.setTypeFace(Typeface.DEFAULT);
                    next.setState(17);
                } else if (next.isExtra() && FileUtils.isFileExist(next.getFilePath())) {
                    next.setTypeFace(Typeface.createFromFile(next.getFilePath()));
                    next.setState(17);
                }
            }
        }
    }

    public static boolean isZhCNLanguage() {
        return Locale.getDefault().toString().equals("zh_CN");
    }
}
