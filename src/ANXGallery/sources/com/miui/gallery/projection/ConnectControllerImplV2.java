package com.miui.gallery.projection;

import com.milink.api.v1.MiLinkClientScanListCallback;
import com.milink.api.v1.type.DeviceType;
import com.milink.api.v1.type.ReturnCode;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectControllerImplV2 extends ConnectController implements MiLinkClientScanListCallback {
    private AtomicBoolean mChooseTillOpen = new AtomicBoolean(false);

    /* access modifiers changed from: protected */
    public boolean chooseDevice() {
        ReturnCode showScanList = this.mPhotoManager.showScanList(this, 2);
        if (showScanList == ReturnCode.OK) {
            return true;
        }
        if (showScanList != ReturnCode.NotConnected) {
            return false;
        }
        this.mChooseTillOpen.set(true);
        return false;
    }

    /* access modifiers changed from: protected */
    public void doOnDeviceFound(String str, String str2, DeviceType deviceType) {
    }

    /* access modifiers changed from: protected */
    public void doOnDeviceLost(String str) {
        if (this.mConnectedDevice != null && this.mConnectedDevice.getValue() == str) {
            Log.i("project_ConnectControllerV2", "onDeviceRemoved %s", this.mConnectedDevice.getValue());
            ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.projection_device_connection_failed);
            closeService();
        }
    }

    /* access modifiers changed from: protected */
    public void doOnOpen() {
        if (this.mChooseTillOpen.get()) {
            this.mChooseTillOpen.set(false);
            chooseDevice();
        }
    }

    public void onConnectFail(String str, String str2) {
    }

    public void onConnectSuccess(String str, String str2) {
    }

    public void onSelectDevice(String str, String str2, String str3) {
        setToConnectDevice(str2, str);
    }
}
