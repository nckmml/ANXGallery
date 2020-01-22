package com.miui.gallery.projection;

import android.app.Activity;
import android.text.TextUtils;
import com.milink.api.v1.type.DeviceType;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.projection.DeviceListController;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;

public class ConnectControllerImplV1 extends ConnectController implements DeviceListController.OnItemClickListener {
    private HashMap<String, String> mFoundDevices = new HashMap<>();
    private DeviceListController mWidget = new DeviceListController();

    protected ConnectControllerImplV1() {
        this.mWidget.setOnItemClickListener(this);
    }

    public void attachActivity(Activity activity) {
        DeviceListController deviceListController = this.mWidget;
        if (deviceListController != null) {
            deviceListController.attachActivity(new WeakReference(activity));
        }
    }

    /* access modifiers changed from: protected */
    public boolean chooseDevice() {
        this.mWidget.show();
        return true;
    }

    public void detachActivity(Activity activity) {
        DeviceListController deviceListController = this.mWidget;
        if (deviceListController != null) {
            deviceListController.detachActivity(new WeakReference(activity));
        }
    }

    public void doOnConnectedFail() {
        super.doOnConnectedFail();
        this.mWidget.removeActive();
    }

    /* access modifiers changed from: protected */
    public void doOnDeviceFound(String str, String str2, DeviceType deviceType) {
        if (deviceType == DeviceType.TV) {
            this.mFoundDevices.put(str2, str);
            Log.i("project_ConnectControllerV1", "onDevicesAdded %s", (Object) str2);
            this.mWidget.addNewDevice(str2, TextUtils.equals(str2, getConnectedDevice()));
        }
    }

    /* access modifiers changed from: protected */
    public void doOnDeviceLost(String str) {
        String str2;
        Iterator<String> it = this.mFoundDevices.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = null;
                break;
            }
            str2 = it.next();
            if (this.mFoundDevices.get(str2).equals(str)) {
                break;
            }
        }
        if (str2 != null) {
            this.mFoundDevices.remove(str2);
            Log.i("project_ConnectControllerV1", "onDeviceRemoved %s", (Object) str2);
            if (this.mWidget.removeDevice(str2)) {
                ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.projection_device_connection_failed);
                closeService();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doOnOpen() {
        HashMap<String, String> hashMap = this.mFoundDevices;
        if (hashMap != null) {
            for (String addNewDevice : hashMap.keySet()) {
                this.mWidget.addNewDevice(addNewDevice, false);
            }
        }
    }

    public void onItemClicked(String str) {
        Log.i("project_ConnectControllerV1", "device %s selected", (Object) str);
        setToConnectDevice(str, this.mFoundDevices.get(str));
        this.mWidget.dismiss();
    }
}
