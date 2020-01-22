package com.miui.gallery.editor.photo.core.imports.filter.render;

import com.miui.filtersdk.filter.base.ColorLookupFilter;
import com.miui.filtersdk.utils.OpenGlUtils;

public class PathLUTFilter extends ColorLookupFilter {
    public PathLUTFilter(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void onInitialized() {
        runOnDraw(new Runnable() {
            public void run() {
                PathLUTFilter pathLUTFilter = PathLUTFilter.this;
                pathLUTFilter.mLookupSourceTexture = OpenGlUtils.loadTexture(OpenGlUtils.getImageFromPath(pathLUTFilter.mTable), -1, true);
            }
        });
    }
}
