package com.xiaomi.micloudsdk.file;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.request.utils.Request;
import com.xiaomi.opensdk.exception.AuthenticationException;
import com.xiaomi.opensdk.exception.RetriableException;
import com.xiaomi.opensdk.exception.UnretriableException;
import com.xiaomi.opensdk.file.model.CommitParameter;
import com.xiaomi.opensdk.file.model.RequestUploadParameter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MiCloudFileRequestor<T> {
    public MiCloudFileRequestor(Context context) {
        Request.init(context);
    }

    private String getHttpResopnse(String str, Map<String, String> map, boolean z) throws UnretriableException, RetriableException, AuthenticationException {
        if (!z) {
            return Request.secureGet(str, map);
        }
        try {
            return Request.securePost(str, map);
        } catch (UnsupportedEncodingException e) {
            Log.e("MiCloudFileRequestor", "requestServer error", e);
            throw new UnretriableException("requestServer error:" + e);
        } catch (IllegalBlockSizeException e2) {
            Log.e("MiCloudFileRequestor", "requestServer error", e2);
            throw new UnretriableException("requestServer error:" + e2);
        } catch (BadPaddingException e3) {
            Log.e("MiCloudFileRequestor", "requestServer error", e3);
            throw new UnretriableException("requestServer error:" + e3);
        } catch (ClientProtocolException e4) {
            Log.e("MiCloudFileRequestor", "requestServer error", e4);
            throw new UnretriableException("requestServer error:" + e4);
        } catch (IOException e5) {
            Log.e("MiCloudFileRequestor", "requestServer error", e5);
            if (RetriableException.isRetriableException(e5)) {
                throw new RetriableException("IOException:" + e5, 300000);
            }
            throw new UnretriableException("requestServer error:" + e5);
        } catch (CloudServerException e6) {
            int statusCode = e6.getStatusCode();
            if (statusCode == 401 || statusCode == 403) {
                throw new AuthenticationException();
            } else if (statusCode == 500) {
                throw new RetriableException("IOException:" + e6, 300000);
            } else if (statusCode != 503) {
                throw new UnretriableException("requestServer error:" + e6);
            } else {
                throw new RetriableException("IOException:" + e6, (long) e6.retryTime);
            }
        }
    }

    public JSONObject commitUpload(T t, CommitParameter commitParameter) throws RetriableException, UnretriableException, AuthenticationException {
        String commitUploadURL = getCommitUploadURL(t, commitParameter);
        if (!TextUtils.isEmpty(commitUploadURL)) {
            try {
                Map<String, String> commitUploadParams = getCommitUploadParams(t, commitParameter);
                if (commitUploadParams != null) {
                    return new JSONObject(getHttpResopnse(commitUploadURL, commitUploadParams, true));
                }
                throw new UnretriableException("getCommitUploadParams() can't return null.");
            } catch (JSONException e) {
                throw new UnretriableException("Http 200 返回的不是JSON格式:" + e);
            } catch (JSONException e2) {
                throw new UnretriableException("error in getCommitUploadParams():" + e2);
            }
        } else {
            throw new UnretriableException("commitUploadUrl is null or empty.");
        }
    }

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getCommitUploadParams(T t, CommitParameter commitParameter) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract String getCommitUploadURL(T t, CommitParameter commitParameter);

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getRequestDownloadParams(T t) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract String getRequestDownloadURL(T t);

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getRequestUploadParams(T t, RequestUploadParameter requestUploadParameter) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract String getRequestUploadURL(T t, RequestUploadParameter requestUploadParameter);

    /* access modifiers changed from: protected */
    public abstract T handleCommitUploadResult(T t, JSONObject jSONObject) throws UnretriableException, RetriableException, AuthenticationException;

    /* access modifiers changed from: protected */
    public abstract boolean handleRequestDownloadResultJson(T t, JSONObject jSONObject) throws UnretriableException, RetriableException, AuthenticationException;

    /* access modifiers changed from: protected */
    public abstract T handleRequestUploadResultJson(T t, JSONObject jSONObject) throws UnretriableException, RetriableException, AuthenticationException;

    public JSONObject requestDownload(T t) throws RetriableException, UnretriableException, AuthenticationException {
        String requestDownloadURL = getRequestDownloadURL(t);
        if (!TextUtils.isEmpty(requestDownloadURL)) {
            try {
                Map<String, String> requestDownloadParams = getRequestDownloadParams(t);
                if (requestDownloadParams != null) {
                    return new JSONObject(getHttpResopnse(requestDownloadURL, requestDownloadParams, false));
                }
                throw new UnretriableException("getRequestDownloadParams() can't return null.");
            } catch (JSONException e) {
                throw new UnretriableException("Http 200 返回的不是JSON格式:" + e);
            } catch (JSONException e2) {
                throw new UnretriableException("error in getRequestDownloadParams():" + e2);
            }
        } else {
            throw new UnretriableException("requestDownloadUrl is null or empty.");
        }
    }

    public JSONObject requestUpload(T t, RequestUploadParameter requestUploadParameter) throws RetriableException, UnretriableException, AuthenticationException {
        String requestUploadURL = getRequestUploadURL(t, requestUploadParameter);
        if (!TextUtils.isEmpty(requestUploadURL)) {
            try {
                Map<String, String> requestUploadParams = getRequestUploadParams(t, requestUploadParameter);
                if (requestUploadParams != null) {
                    return new JSONObject(getHttpResopnse(requestUploadURL, requestUploadParams, true));
                }
                throw new UnretriableException("getRequestUploadParams() can't return null.");
            } catch (JSONException e) {
                throw new UnretriableException("Http 200 返回的不是JSON格式:" + e);
            } catch (JSONException e2) {
                throw new UnretriableException("error in getRequestUploadParams():" + e2);
            }
        } else {
            throw new UnretriableException("requestUploadUrl is null or empty.");
        }
    }
}
