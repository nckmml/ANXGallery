package com.miui.gallery.util;

import android.app.Activity;
import android.view.OrientationEventListener;
import com.nexstreaming.nexeditorsdk.nexClip;

public class OrientationSensor extends OrientationEventListener {
    private boolean mEnabled = false;
    private OnOrientationChangedListener mListener;
    private int mOrientation = -1;

    public interface OnOrientationChangedListener {
        void onOrientationChanged(int i, int i2);
    }

    public OrientationSensor(Activity activity) {
        super(activity.getApplicationContext());
        this.mOrientation = restoreFromSurfaceRotation(activity.getWindowManager().getDefaultDisplay().getRotation());
    }

    public static int restoreFromSurfaceRotation(int i) {
        if (i != 0) {
            return i != 1 ? i != 2 ? i != 3 ? -1 : 90 : nexClip.kClip_Rotate_180 : nexClip.kClip_Rotate_270;
        }
        return 0;
    }

    private int roundOrientation(int i, int i2) {
        boolean z = true;
        if (i2 != -1) {
            int abs = Math.abs(i - i2);
            if (Math.min(abs, 360 - abs) < 50) {
                z = false;
            }
        }
        return z ? (((i + 45) / 90) * 90) % 360 : i2;
    }

    public static int transfer2SurfaceRotation(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 90) {
            return 3;
        }
        if (i != 180) {
            return i != 270 ? -1 : 1;
        }
        return 2;
    }

    public void disable() {
        super.disable();
        this.mEnabled = false;
    }

    public void enable() {
        super.enable();
        this.mEnabled = true;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void onOrientationChanged(int i) {
        int roundOrientation = roundOrientation(i, this.mOrientation);
        int i2 = this.mOrientation;
        if (roundOrientation == i2) {
            return;
        }
        if (roundOrientation % nexClip.kClip_Rotate_180 != 0 || i2 % nexClip.kClip_Rotate_180 != 0) {
            OnOrientationChangedListener onOrientationChangedListener = this.mListener;
            if (onOrientationChangedListener != null) {
                onOrientationChangedListener.onOrientationChanged(this.mOrientation, roundOrientation);
            }
            this.mOrientation = roundOrientation;
        }
    }

    public void setOrientationChangedListener(OnOrientationChangedListener onOrientationChangedListener) {
        this.mListener = onOrientationChangedListener;
    }
}
