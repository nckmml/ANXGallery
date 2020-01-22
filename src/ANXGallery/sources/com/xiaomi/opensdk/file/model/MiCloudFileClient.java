package com.xiaomi.opensdk.file.model;

import android.content.Context;
import android.text.TextUtils;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.exception.KscRuntimeException;
import cn.kuaipan.android.exception.KscTransferStopByCallerException;
import cn.kuaipan.android.exception.NetworkException;
import cn.kuaipan.android.exception.ServerException;
import cn.kuaipan.android.exception.SessionExpiredException;
import com.xiaomi.opensdk.exception.AuthenticationException;
import com.xiaomi.opensdk.exception.RetriableException;
import com.xiaomi.opensdk.exception.TransferStopByCallerException;
import com.xiaomi.opensdk.exception.UnretriableException;
import com.xiaomi.opensdk.file.sdk.KssMasterRef;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public final class MiCloudFileClient {
    private static volatile MiCloudFileClient sInstance;
    private KssMasterRef mKssMasterRef;

    private MiCloudFileClient(Context context) {
        this.mKssMasterRef = new KssMasterRef(context);
    }

    public static MiCloudFileClient getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MiCloudFileClient.class) {
                if (sInstance == null) {
                    if (context != null) {
                        sInstance = new MiCloudFileClient(context);
                    } else {
                        throw new IllegalArgumentException("context can't be null");
                    }
                }
            }
        }
        return sInstance;
    }

    private void transferException(Exception exc) throws RetriableException, UnretriableException, AuthenticationException {
        if (exc instanceof KscException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof KscTransferStopByCallerException)) {
                String simpleMessage = ((KscException) exc).getSimpleMessage();
                if (!(exc instanceof NetworkException)) {
                    if (exc instanceof ServerException) {
                        if (((ServerException) exc).getStatusCode() / 100 == 5) {
                            throw new RetriableException(simpleMessage, 300000);
                        }
                    } else if (exc instanceof SessionExpiredException) {
                        throw new UnretriableException((Throwable) exc);
                    }
                    throw new UnretriableException(simpleMessage);
                }
                throw new RetriableException(simpleMessage, 300000);
            }
            throw new UnretriableException((Throwable) new TransferStopByCallerException(cause));
        } else if (exc instanceof KscRuntimeException) {
            throw new UnretriableException(exc, ((KscRuntimeException) exc).getErrorCode());
        }
    }

    public void download(File file, DownloadParameter downloadParameter, MiCloudFileListener miCloudFileListener, MiCloudTransferStopper miCloudTransferStopper) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        if (!TextUtils.isEmpty(downloadParameter.getKssDownloadString())) {
            try {
                this.mKssMasterRef.download(file, downloadParameter, miCloudFileListener, miCloudTransferStopper, true);
            } catch (KscException e) {
                transferException(e);
            } catch (KscRuntimeException e2) {
                transferException(e2);
            }
        } else {
            throw new IllegalArgumentException("Cannot detect download sdk");
        }
    }

    public CommitParameter getCommitParameter(UploadContext uploadContext) {
        return new CommitParameter(uploadContext.getCommitString(), (String) null, uploadContext.getUploadId(), uploadContext.getSha1(), uploadContext.getFileSize());
    }

    public DownloadParameter getDownloadParameterForSFS(JSONObject jSONObject) throws JSONException {
        DownloadParameter downloadParameter = new DownloadParameter();
        downloadParameter.setKssDownloadString(jSONObject.getJSONObject("kss").toString());
        return downloadParameter;
    }

    public RequestUploadParameter getRequestUploadParameter(UploadContext uploadContext) {
        return new RequestUploadParameter(uploadContext.getKssString(), (String) null, uploadContext.getSha1(), uploadContext.getFileSize(), uploadContext.getFilePath());
    }

    public UploadParameter getUploadParameterForSFS(JSONObject jSONObject) throws JSONException {
        String str;
        UploadParameter uploadParameter = new UploadParameter();
        if (jSONObject.has("upload_id")) {
            str = jSONObject.getString("upload_id");
        } else if (jSONObject.has("uploadId")) {
            str = jSONObject.getString("uploadId");
        } else {
            throw new JSONException("Missing necessary field : upload_id / uploadId");
        }
        uploadParameter.setUploadId(str);
        uploadParameter.setKssUploadString(jSONObject.getJSONObject("kss").toString());
        return uploadParameter;
    }

    public void upload(UploadContext uploadContext) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        try {
            this.mKssMasterRef.upload(uploadContext);
        } catch (KscException e) {
            transferException(e);
        } catch (KscRuntimeException e2) {
            transferException(e2);
        }
    }
}
