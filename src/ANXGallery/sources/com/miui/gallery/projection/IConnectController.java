package com.miui.gallery.projection;

import com.miui.gallery.model.BaseDataItem;

public interface IConnectController {

    public interface DataSet {
        int getCount();

        BaseDataItem getItem(BaseDataItem baseDataItem, int i);
    }

    public interface OnMediaPlayListener {
        void onLoading();

        void onPaused();

        void onPlaying();

        void onStopped();
    }

    public interface OnStatusisListener {
        void onConnectStatusChanged(int i);

        void onOpenStatusChanged(boolean z);
    }

    void closeService();

    int getConnectStatus();

    String getConnectedDevice();

    String getConnectingDevice();

    boolean isConnected();

    void project();

    void registerStatusListener(OnStatusisListener onStatusisListener);

    boolean showSlide(int i);

    void syncRemoteView(float f, float f2, float f3, float f4, float f5, float f6, float f7);

    void unregisterStatusListener(OnStatusisListener onStatusisListener);

    void updateCurrentFolder(DataSet dataSet);

    void updateCurrentPhoto(String str, int i);
}
