package com.xiaomi.micloudsdk.stat;

@Deprecated
public interface IMiCloudStatCallback {
    void onAddHttpEvent(String str, long j, long j2, int i, String str2);

    void onEnableAutoRecord();

    void onInitialize();

    void onSetEventFilter();

    void onSetUploadPolicy();
}
