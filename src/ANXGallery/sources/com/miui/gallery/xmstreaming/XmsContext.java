package com.miui.gallery.xmstreaming;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.miui.gallery.util.Log;
import java.lang.ref.WeakReference;

public class XmsContext {
    static int MEDIA_INFO_STARTED_AS_NEXT = 2;
    private static final int PLAYER_EVENT_COMPILE_PROGRESS = 203;
    private static final int PLAYER_EVENT_COMPILE_RESULT = 204;
    private static final int PLAYER_EVENT_PLAY_EOF = 202;
    private static final int PLAYER_EVENT_PROGRESS_CHANGED = 201;
    private static final int PLAYER_EVENT_STATE_CHANGED = 200;
    public static final int STATE_PAUSING = 2;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_SEEKING = 3;
    private static final String TAG = "XMSContext";
    private static volatile boolean mIsNativeInitialized = false;
    private static XmsContext mXmsContext = new XmsContext();
    private CompileCallback mCompileCallback;
    private EventHandler mEventHandler;
    private PlaybackCallback mPlaybackCallback;
    private StreamingEngineCallback mStreamingEngineCallback;

    public interface CompileCallback {
        void onCompileFailed(boolean z);

        void onCompileFinished();

        void onCompileProgress(int i);
    }

    private static class EventHandler extends Handler {
        private final WeakReference<XmsContext> mWeakPlayer;

        public EventHandler(XmsContext xmsContext, Looper looper) {
            super(looper);
            this.mWeakPlayer = new WeakReference<>(xmsContext);
        }

        public void handleMessage(Message message) {
            XmsContext xmsContext = (XmsContext) this.mWeakPlayer.get();
            if (xmsContext == null) {
                Log.w(XmsContext.TAG, "XmsContext went away with unhandled events");
                return;
            }
            Log.d(XmsContext.TAG, "get msg %d,%d", Integer.valueOf(message.what), Integer.valueOf(message.arg1));
            switch (message.what) {
                case XmsContext.PLAYER_EVENT_STATE_CHANGED /*200*/:
                    xmsContext.notifyStateChanged(message.arg1);
                    return;
                case XmsContext.PLAYER_EVENT_PROGRESS_CHANGED /*201*/:
                    xmsContext.notifyProgressChanged(message.arg1);
                    return;
                case XmsContext.PLAYER_EVENT_PLAY_EOF /*202*/:
                    xmsContext.notifyPlayEOF();
                    return;
                case XmsContext.PLAYER_EVENT_COMPILE_PROGRESS /*203*/:
                    xmsContext.notifyCompileProgress(message.arg1);
                    return;
                case XmsContext.PLAYER_EVENT_COMPILE_RESULT /*204*/:
                    xmsContext.notifyCompileResult(message.arg1);
                    return;
                default:
                    Log.e(XmsContext.TAG, "Unknown message type " + message.what);
                    return;
            }
        }
    }

    public interface PlaybackCallback {
        void onPlaybackEOF();

        void onPlaybackPreloadingCompletion();

        void onPlaybackTimelinePosition(int i);
    }

    public interface StreamingEngineCallback {
        void onFirstVideoFramePresented();

        void onStreamingEngineStateChanged(int i);
    }

    static {
        System.loadLibrary("XmStreamingSDK");
    }

    public static XmsContext getInstance() {
        return mXmsContext;
    }

    private static void initNativeOnce() {
        synchronized (XmsContext.class) {
            if (!mIsNativeInitialized) {
                mIsNativeInitialized = true;
            }
        }
    }

    private native boolean nativeApplyTimeline(long j);

    private native boolean nativeCancelCompile(long j);

    private native boolean nativeCompile(long j, int i, int i2, String str);

    private native boolean nativeConnectTimelineWithSurface(long j, Surface surface);

    private native XmsTimeline nativeCreateTimeline(int i, int i2);

    private static native void nativeInit(boolean z);

    private static native boolean nativeIsPlaying();

    private static native boolean nativeIsReadyForExport();

    private static native boolean nativeIsReadyForSwitch();

    private static native void nativePause();

    private static native void nativePauseToBackground();

    private static native void nativeRelease();

    private static native void nativeResume();

    private static native void nativeResumeToForeground(long j);

    private static native void nativeSeekTo(int i);

    private static native void nativeSet(Object obj);

    private static native void nativeSkipPause();

    private static native void nativeStartPreview();

    private static native void nativeStop();

    /* access modifiers changed from: private */
    public void notifyCompileProgress(int i) {
        CompileCallback compileCallback = this.mCompileCallback;
        if (compileCallback != null) {
            compileCallback.onCompileProgress(i);
        }
    }

    /* access modifiers changed from: private */
    public void notifyCompileResult(int i) {
        CompileCallback compileCallback = this.mCompileCallback;
        if (compileCallback == null) {
            return;
        }
        if (i >= 0) {
            compileCallback.onCompileFinished();
        } else if (i == -1) {
            compileCallback.onCompileFailed(true);
        } else {
            compileCallback.onCompileFailed(false);
        }
    }

    /* access modifiers changed from: private */
    public void notifyPlayEOF() {
        PlaybackCallback playbackCallback = this.mPlaybackCallback;
        if (playbackCallback != null) {
            playbackCallback.onPlaybackEOF();
        }
    }

    /* access modifiers changed from: private */
    public void notifyProgressChanged(int i) {
        PlaybackCallback playbackCallback;
        if (i == 0 && (playbackCallback = this.mPlaybackCallback) != null) {
            playbackCallback.onPlaybackPreloadingCompletion();
        }
        PlaybackCallback playbackCallback2 = this.mPlaybackCallback;
        if (playbackCallback2 != null) {
            playbackCallback2.onPlaybackTimelinePosition(i);
        }
    }

    /* access modifiers changed from: private */
    public void notifyStateChanged(int i) {
        StreamingEngineCallback streamingEngineCallback = this.mStreamingEngineCallback;
        if (streamingEngineCallback != null) {
            streamingEngineCallback.onStreamingEngineStateChanged(i);
        }
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        XmsContext xmsContext;
        EventHandler eventHandler;
        if (obj != null && (xmsContext = (XmsContext) ((WeakReference) obj).get()) != null && (eventHandler = xmsContext.mEventHandler) != null) {
            xmsContext.mEventHandler.sendMessage(eventHandler.obtainMessage(i, i2, i3, obj2));
        }
    }

    public boolean applyTimeline(XmsTimeline xmsTimeline) {
        boolean nativeApplyTimeline;
        synchronized (XmsContext.class) {
            nativeApplyTimeline = nativeApplyTimeline(xmsTimeline.getInternalObject());
            if (nativeApplyTimeline) {
                nativeStartPreview();
            }
        }
        return nativeApplyTimeline;
    }

    public boolean cancelCompile(long j) {
        boolean nativeCancelCompile;
        synchronized (XmsContext.class) {
            nativeCancelCompile = nativeCancelCompile(j);
        }
        return nativeCancelCompile;
    }

    public void compileTimeline(XmsTimeline xmsTimeline, String str, CompileCallback compileCallback) {
        this.mCompileCallback = compileCallback;
        synchronized (XmsContext.class) {
            nativeCompile(xmsTimeline.m_internalObject, xmsTimeline.getPreferWidth(), xmsTimeline.getPreferHeight(), str);
        }
    }

    public boolean connectTimelineWithSurface(XmsTimeline xmsTimeline, Surface surface) {
        boolean nativeConnectTimelineWithSurface;
        synchronized (XmsContext.class) {
            nativeConnectTimelineWithSurface = nativeConnectTimelineWithSurface(xmsTimeline.getInternalObject(), surface);
        }
        return nativeConnectTimelineWithSurface;
    }

    public XmsTimeline createTimeline(int i, int i2) {
        XmsTimeline nativeCreateTimeline;
        synchronized (XmsContext.class) {
            nativeCreateTimeline = nativeCreateTimeline(i, i2);
        }
        return nativeCreateTimeline;
    }

    public void initPlayer(boolean z) {
        initNativeOnce();
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        nativeSet(new WeakReference(this));
        nativeInit(z);
    }

    public boolean isPlaying() {
        boolean nativeIsPlaying;
        synchronized (XmsContext.class) {
            nativeIsPlaying = nativeIsPlaying();
        }
        return nativeIsPlaying;
    }

    public boolean isReadyForExport() {
        boolean nativeIsReadyForExport;
        synchronized (XmsContext.class) {
            nativeIsReadyForExport = nativeIsReadyForExport();
        }
        return nativeIsReadyForExport;
    }

    public boolean isReadyForSwitch() {
        boolean nativeIsReadyForSwitch;
        synchronized (XmsContext.class) {
            nativeIsReadyForSwitch = nativeIsReadyForSwitch();
        }
        return nativeIsReadyForSwitch;
    }

    public native void nativeSurfaceChanged(Surface surface, int i, int i2);

    public void pause() {
        synchronized (XmsContext.class) {
            nativePause();
        }
    }

    public void pauseToBackground() {
        synchronized (XmsContext.class) {
            nativePauseToBackground();
        }
    }

    public void release() {
        Log.d(TAG, "release");
        this.mEventHandler.removeCallbacksAndMessages((Object) null);
        synchronized (XmsContext.class) {
            nativeRelease();
        }
    }

    public void resume() {
        synchronized (XmsContext.class) {
            nativeResume();
        }
    }

    public void resumeToForeground(long j) {
        synchronized (XmsContext.class) {
            nativeResumeToForeground(j);
        }
    }

    public void seekToPos(int i) {
        synchronized (XmsContext.class) {
            nativeSeekTo(i);
        }
    }

    public void setPlaybackCallback(PlaybackCallback playbackCallback) {
        this.mPlaybackCallback = playbackCallback;
    }

    public void setStreamingEngineCallback(StreamingEngineCallback streamingEngineCallback) {
        this.mStreamingEngineCallback = streamingEngineCallback;
    }

    public void skipPause() {
        synchronized (XmsContext.class) {
            nativeSkipPause();
        }
    }

    public void startPreview() {
        synchronized (XmsContext.class) {
            nativeStartPreview();
        }
    }

    public void stop() {
        synchronized (XmsContext.class) {
            nativeStop();
        }
    }
}
