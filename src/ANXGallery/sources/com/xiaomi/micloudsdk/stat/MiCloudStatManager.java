package com.xiaomi.micloudsdk.stat;

@Deprecated
public class MiCloudStatManager {
    private IMiCloudStatCallback mCldStatCallback;
    private boolean mEnable;
    private boolean mIsInitialized;

    private static class Holder {
        /* access modifiers changed from: private */
        public static final MiCloudStatManager _instance = new MiCloudStatManager();
    }

    private MiCloudStatManager() {
        this.mEnable = false;
        this.mIsInitialized = false;
    }

    public static MiCloudStatManager getInstance() {
        return Holder._instance;
    }

    public void addHttpEvent(String str, long j, long j2, int i, String str2) {
        IMiCloudStatCallback iMiCloudStatCallback;
        if (this.mEnable && this.mIsInitialized && (iMiCloudStatCallback = this.mCldStatCallback) != null) {
            iMiCloudStatCallback.onAddHttpEvent(str, j, j2, i, str2);
        }
    }

    public void init(IMiCloudStatCallback iMiCloudStatCallback) {
        this.mCldStatCallback = iMiCloudStatCallback;
        this.mEnable = true;
        this.mCldStatCallback.onInitialize();
        this.mIsInitialized = true;
        this.mCldStatCallback.onSetUploadPolicy();
        this.mCldStatCallback.onSetEventFilter();
        this.mCldStatCallback.onEnableAutoRecord();
    }
}
