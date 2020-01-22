package com.xiaomi.micloudsdk.file;

import android.content.Context;
import com.xiaomi.opensdk.exception.AuthenticationException;
import com.xiaomi.opensdk.exception.RetriableException;
import com.xiaomi.opensdk.exception.UnretriableException;
import com.xiaomi.opensdk.file.model.MiCloudFileClient;
import com.xiaomi.opensdk.file.model.MiCloudFileListener;
import com.xiaomi.opensdk.file.model.MiCloudTransferStopper;
import com.xiaomi.opensdk.file.model.UploadContext;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class MiCloudFileMaster<T> {
    private Context mContext;
    private MiCloudFileRequestor<T> mRequestor;

    public MiCloudFileMaster(Context context, MiCloudFileRequestor<T> miCloudFileRequestor) {
        this.mContext = context;
        this.mRequestor = miCloudFileRequestor;
    }

    public void download(T t, File file, MiCloudFileListener miCloudFileListener) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        download(t, file, miCloudFileListener, (MiCloudTransferStopper) null);
    }

    public void download(T t, File file, MiCloudFileListener miCloudFileListener, MiCloudTransferStopper miCloudTransferStopper) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        if (!MiCloudFileMasterInjector.checkDownloadConditions(this.mContext)) {
            throw new UnretriableException("Upload is forbidden by injector");
        } else if (file != null) {
            try {
                JSONObject requestDownload = this.mRequestor.requestDownload(t);
                if (this.mRequestor.handleRequestDownloadResultJson(t, requestDownload)) {
                    MiCloudFileClient.getInstance(this.mContext).download(file, MiCloudFileClient.getInstance(this.mContext).getDownloadParameterForSFS(requestDownload.getJSONObject("data")), miCloudFileListener, miCloudTransferStopper);
                }
            } catch (JSONException e) {
                throw new UnretriableException((Throwable) e);
            }
        } else {
            throw new UnretriableException("参数错误，请参考API文档");
        }
    }

    public T upload(T t, File file, MiCloudFileListener miCloudFileListener) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        return upload(t, file, miCloudFileListener, (MiCloudTransferStopper) null);
    }

    public T upload(T t, File file, MiCloudFileListener miCloudFileListener, MiCloudTransferStopper miCloudTransferStopper) throws RetriableException, UnretriableException, AuthenticationException, InterruptedException {
        if (!MiCloudFileMasterInjector.checkUploadConditions(this.mContext)) {
            throw new UnretriableException("Upload is forbidden by injector");
        } else if (t == null || file == null) {
            throw new UnretriableException("参数错误，请参考API文档");
        } else {
            UploadContext uploadContext = new UploadContext(file, miCloudFileListener, miCloudTransferStopper);
            MiCloudFileClient.getInstance(this.mContext).upload(uploadContext);
            int i = 0;
            while (uploadContext.isNeedRequestUpload() && i < 5) {
                try {
                    JSONObject requestUpload = this.mRequestor.requestUpload(t, MiCloudFileClient.getInstance(this.mContext).getRequestUploadParameter(uploadContext));
                    T handleRequestUploadResultJson = this.mRequestor.handleRequestUploadResultJson(t, requestUpload);
                    if (handleRequestUploadResultJson != null) {
                        return handleRequestUploadResultJson;
                    }
                    uploadContext.setUploadParam(MiCloudFileClient.getInstance(this.mContext).getUploadParameterForSFS(requestUpload.getJSONObject("data")));
                    MiCloudFileClient.getInstance(this.mContext).upload(uploadContext);
                    i++;
                } catch (JSONException e) {
                    throw new UnretriableException((Throwable) e);
                }
            }
            return this.mRequestor.handleCommitUploadResult(t, this.mRequestor.commitUpload(t, MiCloudFileClient.getInstance(this.mContext).getCommitParameter(uploadContext)));
        }
    }
}
