package com.nexstreaming.nexeditorsdk;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.security.InvalidParameterException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class WrapperForSurfaceTextureListener implements SurfaceTexture.OnFrameAvailableListener {
    private static final String LOG_TAG = "WrapperForSTL";
    private static Handler sHandler;
    private static HandlerThread sHandlerThread;
    private static int sIntanceNum;
    private SurfaceTexture mConnectedSurfaceTexture = null;
    private Semaphore mFrameAvailableSemaphore = new Semaphore(0);
    private final int mInstanceNum;

    public WrapperForSurfaceTextureListener(int i) {
        int i2 = sIntanceNum + 1;
        sIntanceNum = i2;
        this.mInstanceNum = i2;
        Log.d(LOG_TAG, "[W:" + this.mInstanceNum + "] WrapperForSurfaceTextureListener Constructor. a=" + i);
    }

    public static SurfaceTexture makeSurfaceTexture(final int i) {
        final SynchronousQueue synchronousQueue = new SynchronousQueue();
        if (sHandler == null || sHandlerThread == null) {
            sHandlerThread = new HandlerThread("surfaceTextureFactory", -2);
            sHandlerThread.start();
            sHandler = new Handler(sHandlerThread.getLooper());
        }
        sHandler.post(new Runnable() {
            public void run() {
                SurfaceTexture surfaceTexture = new SurfaceTexture(i);
                while (true) {
                    try {
                        break;
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (!synchronousQueue.offer(surfaceTexture, 1000, TimeUnit.MILLISECONDS)) {
                    surfaceTexture.release();
                    Log.w(WrapperForSurfaceTextureListener.LOG_TAG, "Surface texture abandoned");
                }
            }
        });
        SurfaceTexture surfaceTexture = null;
        while (surfaceTexture == null) {
            try {
                surfaceTexture = (SurfaceTexture) synchronousQueue.take();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        return surfaceTexture;
    }

    public void connectListener(final SurfaceTexture surfaceTexture) {
        if (this.mConnectedSurfaceTexture == null) {
            Log.d(LOG_TAG, "[W:" + this.mInstanceNum + "] WrapperForSurfaceTextureListener connectListener.");
            this.mFrameAvailableSemaphore.drainPermits();
            if (Build.VERSION.SDK_INT >= 21) {
                surfaceTexture.setOnFrameAvailableListener(this, sHandler);
            } else {
                sHandler.post(new Runnable() {
                    public void run() {
                        surfaceTexture.setOnFrameAvailableListener(WrapperForSurfaceTextureListener.this);
                    }
                });
            }
            this.mConnectedSurfaceTexture = surfaceTexture;
            return;
        }
        throw new IllegalStateException();
    }

    public void disconnectListener(SurfaceTexture surfaceTexture) {
        if (surfaceTexture == this.mConnectedSurfaceTexture) {
            Log.d(LOG_TAG, "[W:" + this.mInstanceNum + "] WrapperForSurfaceTextureListener disconnectListener.");
            surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
            this.mConnectedSurfaceTexture = null;
            return;
        }
        throw new InvalidParameterException();
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != this.mConnectedSurfaceTexture) {
            Log.w(LOG_TAG, "[W:" + this.mInstanceNum + "] WARNING - Frame available to wrong listener : " + surfaceTexture + " != " + String.valueOf(this.mConnectedSurfaceTexture));
            return;
        }
        this.mFrameAvailableSemaphore.release();
    }

    public int waitFrameAvailable(int i) {
        if (this.mConnectedSurfaceTexture != null) {
            if (i < 0) {
                i = 2500;
            }
            long nanoTime = System.nanoTime();
            int i2 = 0;
            boolean z = false;
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    z = true;
                }
            }
            boolean z2 = !this.mFrameAvailableSemaphore.tryAcquire((long) i, TimeUnit.MILLISECONDS);
            long nanoTime2 = System.nanoTime() - nanoTime;
            if (z2) {
                Log.w(LOG_TAG, "[W:" + this.mInstanceNum + "] waitFrameAvailable : (elapsed=" + nanoTime2 + ") timeout=" + z2 + " interrupted=" + z);
            }
            int i3 = z2 ? 4 : 0;
            if (z) {
                i2 = 8;
            }
            return i3 | i2;
        }
        throw new IllegalStateException();
    }
}
