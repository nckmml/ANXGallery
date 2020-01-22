package com.cdv.io;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

public class NvAndroidVirtualCameraSurfaceTexture implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "Virtual Camera";
    private SurfaceTexture m_surfaceTexture;
    private int m_texId = 0;

    public NvAndroidVirtualCameraSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.m_surfaceTexture = surfaceTexture;
    }

    private static native void notifyCameraFrameAvailable(int i);

    public void attachToGLContext(int i) {
        try {
            this.m_surfaceTexture.attachToGLContext(i);
            this.m_texId = i;
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void detachFromGLContext() {
        try {
            this.m_surfaceTexture.detachFromGLContext();
            this.m_texId = 0;
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void expendCacheTexImage() {
        int i = 0;
        while (i < 10) {
            try {
                this.m_surfaceTexture.updateTexImage();
                i++;
            } catch (Exception e) {
                Log.e(TAG, "" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.m_surfaceTexture;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        notifyCameraFrameAvailable(this.m_texId);
    }

    public void release() {
        this.m_surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
        this.m_surfaceTexture = null;
    }

    public void setupOnFrameAvailableListener(Handler handler) {
        if (handler == null || Build.VERSION.SDK_INT < 21) {
            this.m_surfaceTexture.setOnFrameAvailableListener(this);
        } else {
            this.m_surfaceTexture.setOnFrameAvailableListener(this, handler);
        }
    }
}
