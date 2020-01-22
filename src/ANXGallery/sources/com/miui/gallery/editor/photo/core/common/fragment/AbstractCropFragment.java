package com.miui.gallery.editor.photo.core.common.fragment;

import android.view.MotionEvent;
import com.miui.gallery.editor.photo.core.RenderFragment;
import com.miui.gallery.editor.photo.core.common.model.CropData;

public abstract class AbstractCropFragment extends RenderFragment {
    private OnCropStateChangedListener mCropChangedListener;
    private boolean mHasChanged;

    public interface OnCropStateChangedListener {
        void changeRotationState(boolean z);

        void onCropped();

        void onRestored();
    }

    public void autoCrop(float f) {
        if (!this.mHasChanged) {
            this.mHasChanged = true;
        }
        onAutoCrop(f);
    }

    public void changeRotationState(boolean z) {
        OnCropStateChangedListener onCropStateChangedListener = this.mCropChangedListener;
        if (onCropStateChangedListener != null) {
            onCropStateChangedListener.changeRotationState(z);
        }
    }

    public void doMirror() {
        onChanged();
        onDoMirror();
    }

    public void doRotate() {
        onChanged();
        onDoRotate();
    }

    public boolean doTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public abstract void finishTuning();

    /* access modifiers changed from: protected */
    public final void notifyCropped() {
        OnCropStateChangedListener onCropStateChangedListener = this.mCropChangedListener;
        if (onCropStateChangedListener != null) {
            onCropStateChangedListener.onCropped();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyRestored() {
        OnCropStateChangedListener onCropStateChangedListener = this.mCropChangedListener;
        if (onCropStateChangedListener != null) {
            onCropStateChangedListener.onRestored();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onAutoCrop(float f);

    public void onChanged() {
        if (!this.mHasChanged) {
            this.mHasChanged = true;
            notifyCropped();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onDoMirror();

    /* access modifiers changed from: protected */
    public abstract void onDoRotate();

    /* access modifiers changed from: protected */
    public abstract void onSetAspectRatio(CropData.AspectRatio aspectRatio);

    /* access modifiers changed from: protected */
    public abstract void onTurning(float f);

    public abstract void prepareTuning();

    public void restore() {
        if (this.mHasChanged) {
            this.mHasChanged = false;
            notifyRestored();
        }
    }

    public void setAspectRatio(CropData.AspectRatio aspectRatio) {
        onChanged();
        onSetAspectRatio(aspectRatio);
    }

    public void setOnCropChangedListener(OnCropStateChangedListener onCropStateChangedListener) {
        this.mCropChangedListener = onCropStateChangedListener;
    }

    public void tuning(float f) {
        onChanged();
        onTurning(f);
    }
}
