package com.miui.gallery.cloudcontrol;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloudcontrol.CloudControlRequest;
import com.miui.gallery.cloudcontrol.RecommendListRequest;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.RequestError;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.PrivacyAgreementUtils;
import com.miui.gallery.util.SyncUtil;
import java.util.Iterator;
import java.util.Map;

public class CloudControlRequestHelper {
    private Context mContext;

    public CloudControlRequestHelper(Context context) {
        this.mContext = context;
    }

    private void doPostJobs() {
        AIAlbumStatusHelper.doPostCloudControlJob();
    }

    private boolean execRequestSyncInternal(boolean z) {
        if (!PrivacyAgreementUtils.isCloudServiceAgreementEnable(this.mContext)) {
            Log.w("CloudControlRequestHelper", "Request failed: privacy agreement disabled");
            return false;
        } else if (!AgreementsUtils.isNetworkingAgreementAccepted()) {
            Log.e("CloudControlRequestHelper", "Request failed: CTA not confirmed.");
            return false;
        } else {
            execRecommendRequest(z);
            try {
                CloudControlResponse cloudControlResponse = (CloudControlResponse) new CloudControlRequest.Builder().setMethod(z ? 1002 : 1).setUrl(z ? HostManager.CloudControl.getUrl() : HostManager.CloudControl.getAnonymousUrl()).setSyncToken(GalleryPreferences.CloudControl.getSyncToken()).build().simpleExecuteSync();
                if (cloudControlResponse == null) {
                    handleError(ErrorCode.BODY_EMPTY, "Response is empty", "Response is empty", z, false);
                    return false;
                }
                handleResponse(cloudControlResponse);
                GalleryPreferences.CloudControl.setLastRequestSucceedTime(System.currentTimeMillis());
                GalleryPreferences.CloudControl.setLastRequestTime(System.currentTimeMillis());
                return true;
            } catch (RequestError e) {
                handleError(e.getErrorCode(), e.getMessage(), e.getResponseData(), z, false);
                return false;
            } catch (Exception e2) {
                handleError(ErrorCode.HANDLE_ERROR, e2.getMessage(), (Object) null, z, false);
                return false;
            } finally {
                GalleryPreferences.CloudControl.setLastRequestTime(System.currentTimeMillis());
            }
        }
    }

    private int getRequestIntervalMinutes() {
        long lastRequestTime = GalleryPreferences.CloudControl.getLastRequestTime();
        return lastRequestTime == 0 ? Reader.READ_DONE : (int) (((System.currentTimeMillis() - lastRequestTime) / 1000) / 60);
    }

    private void handleError(ErrorCode errorCode, String str, Object obj, boolean z, boolean z2) {
        String name = errorCode != null ? errorCode.name() : "UNKNOWN";
        Log.e("CloudControlRequestHelper", "Request failed, errorCode: %s, errorMessage: %s, responseData: %s, isLoggedIn: %b.", name, str, String.valueOf(obj), Boolean.valueOf(z));
        Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
        if (!z2) {
            generatorCommonParams.put("request_interval(minutes)", String.valueOf(getRequestIntervalMinutes()));
        }
        generatorCommonParams.put("error_code", name);
        generatorCommonParams.put("error_message", str);
        generatorCommonParams.put("response", String.valueOf(obj));
        GallerySamplingStatHelper.recordErrorEvent(z2 ? "recommend_cloud_control" : "cloud_control", z ? "cloud_control_real_name_request_error" : "cloud_control_anonymous_request_error", generatorCommonParams);
    }

    private void handleResponse(CloudControlResponse cloudControlResponse) {
        handleResponse(cloudControlResponse, false);
    }

    private void handleResponse(CloudControlResponse cloudControlResponse, boolean z) {
        if (MiscUtil.isValid(cloudControlResponse.getFeatureProfiles())) {
            boolean z2 = false;
            Iterator<FeatureProfile> it = cloudControlResponse.getFeatureProfiles().iterator();
            while (it.hasNext()) {
                FeatureProfile next = it.next();
                CloudControlManager.getInstance().insertToCache(next);
                if (CloudControlDBHelper.tryInsertToDB(this.mContext, next) == 0) {
                    z2 = true;
                    Log.e("CloudControlRequestHelper", "Persist error: %s", (Object) String.valueOf(next));
                }
            }
            if (z2 && !z) {
                Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                generatorCommonParams.put("response", String.valueOf(cloudControlResponse));
                GallerySamplingStatHelper.recordErrorEvent("cloud_control", "cloud_control_persist_error", generatorCommonParams);
            }
        }
        if (!TextUtils.isEmpty(cloudControlResponse.getSyncToken()) && !z) {
            GalleryPreferences.CloudControl.setSyncToken(cloudControlResponse.getSyncToken());
        }
    }

    public boolean execRecommendRequest() {
        return execRecommendRequest(SyncUtil.existXiaomiAccount(this.mContext));
    }

    public boolean execRecommendRequest(boolean z) {
        if (!ImageFeatureManager.isDeviceSupportStoryFunction()) {
            return false;
        }
        try {
            RecommendListResponse recommendListResponse = (RecommendListResponse) (z ? new RecommendListRequest.Builder().setMethod(true).setUrl(HostManager.RecommendList.getUrl()).build() : new RecommendListRequest.Builder().setMethod(false).setUrl(HostManager.RecommendList.getAnonymousUrl()).build()).simpleExecuteSync();
            if (recommendListResponse == null) {
                handleError(ErrorCode.BODY_EMPTY, "Response is empty", "Response is empty", z, true);
                return false;
            }
            handleResponse(recommendListResponse, true);
            return true;
        } catch (RequestError e) {
            handleError(e.getErrorCode(), e.getMessage(), e.getResponseData(), z, true);
            return false;
        } catch (Exception e2) {
            handleError(ErrorCode.HANDLE_ERROR, e2.getMessage(), (Object) null, z, true);
            return false;
        }
    }

    public boolean execRequestSync() {
        return execRequestSync(SyncUtil.existXiaomiAccount(this.mContext));
    }

    public boolean execRequestSync(boolean z) {
        boolean execRequestSyncInternal = execRequestSyncInternal(z);
        if (execRequestSyncInternal) {
            try {
                doPostJobs();
            } catch (Exception e) {
                Log.e("CloudControlRequestHelper", "Error occurred while executing post cloud control request job, %s", (Object) e);
            }
        }
        return execRequestSyncInternal;
    }
}
