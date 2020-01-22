package com.miui.gallery.editor.photo.screen.longcrop;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;
import com.miui.gallery.editor.photo.screen.core.ScreenDrawEntry;

public class ScreenLongCropFragment extends Fragment implements ILongCropEditorController {
    private ScreenLongCropEditView mEditorView;
    private LongScreenshotCropEditorView.Entry mLastCropEntry;
    private Bitmap mOrigin;
    private ScreenDrawEntry mScreenDrawEntry;
    private ScreenLongCropCallback mScreenLongCropCallback;

    public interface ScreenLongCropCallback {
        int[] getScreenRect();

        Bitmap onLoadPreview();

        void startAnimator();
    }

    private void startEnterAnimation(int[] iArr) {
        this.mEditorView.startEnterAnimator(iArr);
        this.mScreenLongCropCallback.startAnimator();
    }

    public boolean isModifiedBaseLast() {
        LongScreenshotCropEditorView.Entry export = this.mEditorView.export();
        LongScreenshotCropEditorView.Entry entry = this.mLastCropEntry;
        if (entry != null && export.equals(entry)) {
            return false;
        }
        this.mLastCropEntry = export;
        return export.isModified();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.screen_long_screenshot_crop_editor_fragment, viewGroup, false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mOrigin = null;
    }

    public LongScreenshotCropEditorView.Entry onExport() {
        return this.mEditorView.export();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditorView = (ScreenLongCropEditView) view.findViewById(R.id.editor_view);
        this.mEditorView.setBitmap(this.mScreenLongCropCallback.onLoadPreview());
        this.mEditorView.setPaintColor(getResources().getColor(R.color.screen_editor_view_background));
        this.mEditorView.setShowShadow(false);
        ScreenDrawEntry screenDrawEntry = this.mScreenDrawEntry;
        if (screenDrawEntry != null) {
            this.mEditorView.setScreenDrawEntry(screenDrawEntry);
        }
        Bitmap bitmap = this.mOrigin;
        if (bitmap != null) {
            this.mEditorView.setOriginalBitmap(bitmap, 0.0f, 1.0f);
        }
        startEnterAnimation(this.mScreenLongCropCallback.getScreenRect());
    }

    public void setOriginBitmap(Bitmap bitmap) {
        this.mOrigin = bitmap;
        if (getView() != null) {
            this.mEditorView.setOriginalBitmap(this.mOrigin, 0.0f, 1.0f);
        }
    }

    public void setScreenDrawEntry(ScreenDrawEntry screenDrawEntry) {
        this.mScreenDrawEntry = screenDrawEntry;
        ScreenLongCropEditView screenLongCropEditView = this.mEditorView;
        if (screenLongCropEditView != null) {
            screenLongCropEditView.setScreenDrawEntry(this.mScreenDrawEntry);
            this.mEditorView.invalidate();
        }
    }

    public void setScreenLongCropCallback(ScreenLongCropCallback screenLongCropCallback) {
        this.mScreenLongCropCallback = screenLongCropCallback;
    }
}
