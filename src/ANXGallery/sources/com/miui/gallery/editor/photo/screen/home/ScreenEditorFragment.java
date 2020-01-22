package com.miui.gallery.editor.photo.screen.home;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;
import com.miui.gallery.editor.photo.screen.base.IScreenOperation;
import com.miui.gallery.editor.photo.screen.core.ScreenRenderData;

public class ScreenEditorFragment extends Fragment implements IScreenEditorController {
    private ScreenEditorView mEditorView;
    private boolean mIsLongCrop;
    private OperationUpdateListener mOperationUpdateListener;

    public void checkTextEditor(boolean z) {
        this.mEditorView.checkTextEditor(z);
    }

    public void doRevert() {
        if (this.mEditorView.canRevert()) {
            this.mEditorView.doRevert();
        }
    }

    public void doRevoke() {
        if (this.mEditorView.canRevoke()) {
            this.mEditorView.doRevoke();
        }
    }

    public void export() {
        this.mEditorView.export();
    }

    public <T extends IScreenOperation> T getScreenOperation(Class<T> cls) {
        return this.mEditorView.getScreenOperation(cls);
    }

    public boolean isModified() {
        ScreenEditorView screenEditorView = this.mEditorView;
        if (screenEditorView == null) {
            return false;
        }
        return screenEditorView.isModified();
    }

    public boolean isModifiedBaseLast() {
        ScreenEditorView screenEditorView = this.mEditorView;
        if (screenEditorView == null) {
            return false;
        }
        return screenEditorView.isModifiedBaseLast();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.screen_editor_fragment, viewGroup, false);
    }

    public ScreenRenderData onExport() {
        return this.mEditorView.onExport();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditorView = (ScreenEditorView) view.findViewById(R.id.screen_editor_view);
        this.mEditorView.setOperationUpdateListener(this.mOperationUpdateListener);
        this.mEditorView.setLongCrop(this.mIsLongCrop);
        this.mEditorView.init();
    }

    public boolean setCurrentScreenEditor(int i) {
        return this.mEditorView.setCurrentScreenEditor(i);
    }

    public void setLongCrop(boolean z) {
        this.mIsLongCrop = z;
    }

    public void setLongCropEntry(LongScreenshotCropEditorView.Entry entry) {
        this.mEditorView.setLongCropEntry(entry);
    }

    public void setOperationUpdateListener(OperationUpdateListener operationUpdateListener) {
        this.mOperationUpdateListener = operationUpdateListener;
    }

    public void setPreviewBitmap(Bitmap bitmap) {
        this.mEditorView.setPreviewBitmap(bitmap);
    }

    public void startScreenThumbnailAnimate(ThumbnailAnimatorCallback thumbnailAnimatorCallback) {
        ScreenEditorView screenEditorView = this.mEditorView;
        if (screenEditorView != null) {
            screenEditorView.startScreenThumbnailAnimate(thumbnailAnimatorCallback);
        }
    }
}
