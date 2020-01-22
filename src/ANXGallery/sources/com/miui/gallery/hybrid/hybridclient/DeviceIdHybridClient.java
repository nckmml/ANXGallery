package com.miui.gallery.hybrid.hybridclient;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.miui.gallery.R;
import com.miui.gallery.hybrid.hybridclient.HybridClient;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.request.HostManager;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.List;

public class DeviceIdHybridClient extends GalleryHybridClient {

    public class MiuiGalleryJSBridge {
        public MiuiGalleryJSBridge() {
        }

        @JavascriptInterface
        public String getDeviceId() {
            if (HostManager.isInternalUrl(DeviceIdHybridClient.this.mCurrentUrl)) {
                return MiscUtil.getHashedDeviceId();
            }
            return null;
        }
    }

    public DeviceIdHybridClient(Context context, String str) {
        super(context, str);
    }

    public List<HybridClient.JsInterfacePair> getJavascriptInterfaces() {
        List<HybridClient.JsInterfacePair> javascriptInterfaces = super.getJavascriptInterfaces();
        if (javascriptInterfaces == null) {
            javascriptInterfaces = new ArrayList<>(1);
        }
        javascriptInterfaces.add(new HybridClient.JsInterfacePair("MiuiGalleryJSBridge", new MiuiGalleryJSBridge()));
        return javascriptInterfaces;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.READ_PHONE_STATE", this.mContext.getString(R.string.permission_phone_desc), true)};
    }
}
