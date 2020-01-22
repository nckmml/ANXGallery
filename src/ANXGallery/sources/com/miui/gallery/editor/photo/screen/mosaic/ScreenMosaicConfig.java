package com.miui.gallery.editor.photo.screen.mosaic;

import android.graphics.Shader;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;

class ScreenMosaicConfig {
    public final MosaicEntity.TYPE effectType;
    public boolean supportScreenEditor;
    public final Shader.TileMode tileMode;
}
