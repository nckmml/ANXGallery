package com.miui.gallery.editor.photo.screen.home;

import android.graphics.Bitmap;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;
import com.miui.gallery.editor.photo.screen.core.ScreenRenderData;

public interface IScreenEditorController {
    void checkTextEditor(boolean z);

    void doRevert();

    void doRevoke();

    void export();

    boolean isModified();

    boolean isModifiedBaseLast();

    ScreenRenderData onExport();

    boolean setCurrentScreenEditor(int i);

    void setLongCrop(boolean z);

    void setLongCropEntry(LongScreenshotCropEditorView.Entry entry);

    void setOperationUpdateListener(OperationUpdateListener operationUpdateListener);

    void setPreviewBitmap(Bitmap bitmap);

    void startScreenThumbnailAnimate(ThumbnailAnimatorCallback thumbnailAnimatorCallback);
}
