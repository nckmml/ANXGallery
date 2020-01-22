package com.cdv.io;

import android.graphics.SurfaceTexture;

public class NvCameraSurfaceTextureListener implements SurfaceTexture.OnFrameAvailableListener {
    private final int m_texId;

    public NvCameraSurfaceTextureListener(int i) {
        this.m_texId = i;
    }

    private static native void notifyCameraFrameAvailable(int i);

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        notifyCameraFrameAvailable(this.m_texId);
    }
}
