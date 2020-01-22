package com.cdv.io;

import android.media.MediaRecorder;

public class NvMediaRecorderListener implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    private int m_id = -1;

    public NvMediaRecorderListener(int i) {
        this.m_id = i;
    }

    private static native void notifyMediaRecorderError(int i, int i2, int i3);

    private static native void notifyMediaRecorderInfo(int i, int i2, int i3);

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        notifyMediaRecorderError(this.m_id, i, i2);
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        notifyMediaRecorderInfo(this.m_id, i, i2);
    }
}
