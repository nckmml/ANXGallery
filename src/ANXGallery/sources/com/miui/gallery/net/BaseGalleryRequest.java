package com.miui.gallery.net;

import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.RequestError;
import com.miui.gallery.net.json.BaseJsonRequest;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.util.BaseNetworkUtils;
import com.miui.gallery.util.Log;
import org.json.JSONObject;

public class BaseGalleryRequest extends BaseJsonRequest<GalleryResponse> {
    public BaseGalleryRequest(int i, String str) {
        super(i, str);
    }

    private boolean checkExecuteCondition() {
        if (!BaseGalleryPreferences.CTA.canConnectNetwork()) {
            handleError(ErrorCode.NETWORK_NOT_CONNECTED, "CTA not confirmed.", (Object) null);
            return false;
        } else if (isUseCache() || BaseNetworkUtils.isNetworkConnected()) {
            return true;
        } else {
            handleError(ErrorCode.NETWORK_NOT_CONNECTED, "Network not connected.", (Object) null);
            return false;
        }
    }

    public final void execute() {
        if (checkExecuteCondition()) {
            super.execute();
        }
    }

    public final Object[] executeSync() throws RequestError {
        if (checkExecuteCondition()) {
            return super.executeSync();
        }
        throw this.mRequestError;
    }

    /* access modifiers changed from: protected */
    public final void handleResponse(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("code")) {
                    if (jSONObject.getInt("code") != ErrorCode.SUCCESS.CODE) {
                        handleError(ErrorCode.SERVER_ERROR, jSONObject.optString("description"), jSONObject);
                        return;
                    } else if (jSONObject.isNull("data")) {
                        handleError(ErrorCode.BODY_EMPTY, "response empty data", jSONObject);
                        return;
                    } else {
                        GalleryResponse galleryResponse = new GalleryResponse();
                        galleryResponse.data = jSONObject.optJSONObject("data");
                        galleryResponse.syncTag = jSONObject.optString("syncTag", (String) null);
                        galleryResponse.syncToken = jSONObject.optString("syncToken", (String) null);
                        galleryResponse.isLastPage = jSONObject.optBoolean("lastPage", true);
                        onRequestSuccess(galleryResponse);
                        return;
                    }
                }
            } catch (Exception e) {
                handleError(ErrorCode.HANDLE_ERROR, e.getMessage(), e);
                return;
            }
        }
        handleError(ErrorCode.PARSE_ERROR, "response has no code", (Object) null);
    }

    public void onRequestError(ErrorCode errorCode, String str, Object obj) {
        deliverError(errorCode, str, obj);
        Log.w("BaseGalleryRequest", "%s onRequestError:%s | %s ", getClass().getSimpleName(), errorCode, str);
        if (obj instanceof Throwable) {
            Log.w("BaseGalleryRequest", (Throwable) obj);
        } else if (obj != null) {
            Log.d("BaseGalleryRequest", obj.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(GalleryResponse galleryResponse) throws Exception {
        onRequestSuccess(galleryResponse.data);
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(JSONObject jSONObject) throws Exception {
    }
}
