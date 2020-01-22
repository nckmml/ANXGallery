package com.miui.gallery.editor.photo.core.imports.longcrop;

import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;

public class CropRenderData extends RenderData {
    public final LongScreenshotCropEditorView.Entry mEntry;

    public CropRenderData(LongScreenshotCropEditorView.Entry entry) {
        this.mEntry = entry;
    }
}
