package com.xiaomi.micloudsdk.stat;

public interface IMiCloudNetEventStatCallback {
    void onAddNetFailedEvent(NetFailedStatParam netFailedStatParam);

    void onAddNetSuccessEvent(NetSuccessStatParam netSuccessStatParam);
}
