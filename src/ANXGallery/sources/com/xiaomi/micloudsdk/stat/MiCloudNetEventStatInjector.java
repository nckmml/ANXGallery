package com.xiaomi.micloudsdk.stat;

public class MiCloudNetEventStatInjector {
    private IMiCloudNetEventStatCallback mIMiCloudStatCallback;
    private boolean mIsInitialized;

    private static class MiCloudNetEventStatInjectorHolder {
        /* access modifiers changed from: private */
        public static final MiCloudNetEventStatInjector instance = new MiCloudNetEventStatInjector();
    }

    private MiCloudNetEventStatInjector() {
        this.mIsInitialized = false;
    }

    public static MiCloudNetEventStatInjector getInstance() {
        return MiCloudNetEventStatInjectorHolder.instance;
    }

    public void addNetFailedEvent(NetFailedStatParam netFailedStatParam) {
        IMiCloudNetEventStatCallback iMiCloudNetEventStatCallback = this.mIMiCloudStatCallback;
        if (iMiCloudNetEventStatCallback != null) {
            iMiCloudNetEventStatCallback.onAddNetFailedEvent(netFailedStatParam);
        }
    }

    public void addNetSuccessEvent(NetSuccessStatParam netSuccessStatParam) {
        IMiCloudNetEventStatCallback iMiCloudNetEventStatCallback = this.mIMiCloudStatCallback;
        if (iMiCloudNetEventStatCallback != null) {
            iMiCloudNetEventStatCallback.onAddNetSuccessEvent(netSuccessStatParam);
        }
    }
}
