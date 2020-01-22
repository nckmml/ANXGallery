package com.miui.gallery.cloud;

import com.miui.gallery.cloud.ServerErrorCode;
import com.miui.gallery.cloud.SpaceFullHandler;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckResult {
    private static GallerySyncResult checkErrorCode(int i, JSONObject jSONObject, RequestItemBase requestItemBase, SpaceFullHandler.SpaceFullListener spaceFullListener) throws JSONException {
        ServerErrorCode.GalleryErrorCodeItem galleryErrorCodeItem = ServerErrorCode.sGalleryServerErrors.get(Integer.valueOf(i));
        GallerySyncResult.Builder builder = new GallerySyncResult.Builder();
        if (galleryErrorCodeItem == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "checkErrorCode");
            hashMap.put("unknow error", jSONObject == null ? "null" : jSONObject.toString());
            GallerySamplingStatHelper.recordErrorEvent("Sync", "check_result", hashMap);
            builder.setCode(GallerySyncCode.NOT_RETRY_ERROR);
        } else {
            if (galleryErrorCodeItem.errorHandler != null) {
                galleryErrorCodeItem.errorHandler.onError(jSONObject, requestItemBase, spaceFullListener);
            }
            if (ServerErrorCode.isRetryCode(galleryErrorCodeItem.code)) {
                long j = 5;
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null && optJSONObject.has("retryAfter")) {
                    j = CloudUtils.getLongAttributeFromJson(optJSONObject, "retryAfter");
                }
                builder.setRetryAfter(j);
            }
            builder.setCode(galleryErrorCodeItem.result);
        }
        return builder.setData(jSONObject).build();
    }

    public static GallerySyncCode checkKSSThumbnailResult(int i) {
        return (i == 404 || i == 516 || i == 519 || i == 520) ? GallerySyncCode.NOT_RETRY_ERROR : GallerySyncCode.RETRY_ERROR;
    }

    public static GallerySyncResult<JSONObject> checkXMResultCode(JSONObject jSONObject, RequestItemBase requestItemBase, SpaceFullHandler.SpaceFullListener spaceFullListener) throws JSONException {
        GallerySyncResult.Builder builder = new GallerySyncResult.Builder();
        if (jSONObject == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "checkXMResultCode");
            hashMap.put("message", "result is null");
            GallerySamplingStatHelper.recordErrorEvent("Sync", "check_result", hashMap);
            builder.setCode(GallerySyncCode.NOT_RETRY_ERROR);
        } else if (!jSONObject.has("code")) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("function", "checkXMResultCode");
            hashMap2.put("message", "result has no code");
            GallerySamplingStatHelper.recordErrorEvent("Sync", "check_result", hashMap2);
            builder.setCode(GallerySyncCode.RETRY_ERROR).setRetryAfter(0);
        } else {
            builder.clone(checkErrorCode(jSONObject.getInt("code"), jSONObject, requestItemBase, spaceFullListener));
        }
        return builder.setData(jSONObject).build();
    }

    public static GallerySyncResult<JSONObject> checkXMResultCodeForFaceRequest(JSONObject jSONObject, RequestItemBase requestItemBase) throws JSONException {
        GallerySyncResult.Builder builder = new GallerySyncResult.Builder();
        if (jSONObject == null) {
            builder.setCode(GallerySyncCode.NOT_RETRY_ERROR);
        } else if (!jSONObject.has("code")) {
            builder.setCode(GallerySyncCode.RETRY_ERROR);
        } else {
            int i = jSONObject.getInt("code");
            if (i == 52000) {
                builder.setCode(GallerySyncCode.RESET_FACE_TAG);
            } else {
                builder.clone(checkErrorCode(i, jSONObject, requestItemBase, (SpaceFullHandler.SpaceFullListener) null));
            }
        }
        return builder.setData(jSONObject).build();
    }

    public static boolean isNotRetryCode(int i) {
        return i / 100 == 4;
    }

    public static int parseErrorCode(JSONObject jSONObject) throws JSONException {
        int i = jSONObject == null ? -6 : jSONObject.getInt("code");
        if (i != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "parseErrorCode");
            hashMap.put("code", String.valueOf(i));
            hashMap.put("message", jSONObject == null ? "null" : jSONObject.toString());
            GallerySamplingStatHelper.recordErrorEvent("Sync", "check_result", hashMap);
        }
        return i;
    }
}
