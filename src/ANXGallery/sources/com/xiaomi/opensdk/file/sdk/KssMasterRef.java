package com.xiaomi.opensdk.file.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.exception.KscRuntimeException;
import cn.kuaipan.android.exception.ServerMsgException;
import cn.kuaipan.android.http.IKscTransferListener;
import cn.kuaipan.android.http.KscHttpTransmitter;
import cn.kuaipan.android.http.KssTransferStopper;
import cn.kuaipan.android.kss.IKssDownloadRequestResult;
import cn.kuaipan.android.kss.KssDef;
import cn.kuaipan.android.kss.download.KssDownloader;
import cn.kuaipan.android.kss.upload.KssUploadInfo;
import cn.kuaipan.android.kss.upload.KssUploader;
import cn.kuaipan.android.kss.upload.UploadFileInfo;
import cn.kuaipan.android.kss.upload.UploadTaskStore;
import cn.kuaipan.android.utils.ContextUtils;
import com.xiaomi.opensdk.file.model.DownloadParameter;
import com.xiaomi.opensdk.file.model.MiCloudFileListener;
import com.xiaomi.opensdk.file.model.MiCloudTransferStopper;
import com.xiaomi.opensdk.file.model.UploadContext;
import com.xiaomi.opensdk.file.model.UploadParameter;
import com.xiaomi.opensdk.file.utils.FileSDKUtils;
import java.io.File;
import org.json.JSONException;

public class KssMasterRef implements KssDef {
    private final String TAG = "KssMasterRef";
    private final KssDownloader mDownloader;
    private final UploadTaskStore mTaskStore;
    private final KssUploader mUploader;

    private class KscTransferListener extends IKscTransferListener.KscTransferListener {
        public MiCloudFileListener mMiCloudFileListener;

        public KscTransferListener(MiCloudFileListener miCloudFileListener) {
            this.mMiCloudFileListener = miCloudFileListener;
        }

        public void onDataReceived(long j, long j2) {
            MiCloudFileListener miCloudFileListener = this.mMiCloudFileListener;
            if (miCloudFileListener != null) {
                miCloudFileListener.onDataReceived(j, j2);
            }
        }

        public void onDataSended(long j, long j2) {
            MiCloudFileListener miCloudFileListener = this.mMiCloudFileListener;
            if (miCloudFileListener != null) {
                miCloudFileListener.onDataSended(j, j2);
            }
        }
    }

    public KssMasterRef(Context context) {
        this.mTaskStore = new UploadTaskStore(context, new FileDataFactory());
        KscHttpTransmitter kscHttpTransmitter = new KscHttpTransmitter(context);
        kscHttpTransmitter.setUserAgent(4, getUserAgent(context));
        this.mUploader = new KssUploader(kscHttpTransmitter, this.mTaskStore);
        this.mDownloader = new KssDownloader(kscHttpTransmitter);
    }

    private void deleteUploadInfo(int i) throws InterruptedException {
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        if (uploadTaskStore != null) {
            uploadTaskStore.removeUploadInfo(i);
        }
    }

    private static int getUploadHash(File file, String str, UploadFileInfo uploadFileInfo) {
        String sha1 = uploadFileInfo == null ? "" : uploadFileInfo.getSha1();
        return (file + ":" + str + ":" + sha1).hashCode();
    }

    private KssUploadInfo getUploadInfo(UploadFileInfo uploadFileInfo, UploadContext uploadContext, int i) throws KscException, InterruptedException {
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        KssUploadInfo uploadInfo = uploadTaskStore == null ? null : uploadTaskStore.getUploadInfo(i);
        if (uploadInfo == null) {
            UploadParameter uploadParam = uploadContext.getUploadParam();
            if (uploadParam != null) {
                try {
                    KssUploadInfo kssUploadInfo = new KssUploadInfo(uploadFileInfo, new FileUploadRequestResult(FileSDKUtils.contentKssJsonToMap(uploadParam.toJsonObject())));
                    kssUploadInfo.setUploadId(uploadParam.getUploadId());
                    UploadTaskStore uploadTaskStore2 = this.mTaskStore;
                    if (uploadTaskStore2 != null) {
                        uploadTaskStore2.putUploadInfo(i, kssUploadInfo);
                    }
                    uploadInfo = kssUploadInfo;
                } catch (JSONException e) {
                    throw KscException.newException(e, "getUploadInfo failed");
                }
            } else {
                throw new KscRuntimeException(500003, "uploadParam null");
            }
        }
        uploadInfo.setMaxChunkSize(uploadContext.getMaxChunkSize());
        Log.w("KssMasterRef", "KssUploadInfo Return:" + uploadInfo.getUploadId());
        return uploadInfo;
    }

    private static String getUserAgent(Context context) {
        return String.format("KssRC4/1.0 %s/%s S3SDK/%s", new Object[]{context.getPackageName(), ContextUtils.getAppVersion(context), "0.9.0a"});
    }

    public void download(File file, DownloadParameter downloadParameter, MiCloudFileListener miCloudFileListener, MiCloudTransferStopper miCloudTransferStopper, boolean z) throws KscException, InterruptedException {
        if (file != null) {
            KscTransferListener kscTransferListener = new KscTransferListener(miCloudFileListener);
            try {
                FileDownloadRequestResult fileDownloadRequestResult = new FileDownloadRequestResult(FileSDKUtils.contentKssJsonToMap(downloadParameter.toJsonObject()));
                if (fileDownloadRequestResult.getStatus() != 0) {
                    String message = fileDownloadRequestResult.getMessage();
                    if (TextUtils.isEmpty(message)) {
                        throw new KscException(503000, "Unknow error when requestDownload.");
                    }
                    throw new ServerMsgException(200, message, "Failed on requestDownload");
                }
                this.mDownloader.download(file, z, (IKscTransferListener) kscTransferListener, KssTransferStopper.KssTransferStopperFromMiCloudTransferStopper.get(miCloudTransferStopper), (IKssDownloadRequestResult) fileDownloadRequestResult);
            } catch (JSONException e) {
                throw KscException.newException(e, "download failed");
            }
        } else {
            throw new KscRuntimeException(500003, "Save path can't be null.");
        }
    }

    public boolean hasStoredUploadInfo(int i) throws InterruptedException {
        Boolean bool;
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        if (uploadTaskStore == null) {
            bool = null;
        } else {
            bool = Boolean.valueOf(uploadTaskStore.getUploadInfo(i) != null);
        }
        return bool.booleanValue();
    }

    public void upload(UploadContext uploadContext) throws KscException, InterruptedException {
        File localFile = uploadContext.getLocalFile();
        if (localFile == null || !localFile.isFile() || !localFile.canRead() || localFile.length() <= 0) {
            throw new KscRuntimeException(500003, localFile + " is not a exist file.");
        }
        KscTransferListener kscTransferListener = new KscTransferListener(uploadContext.getListener());
        UploadFileInfo fileInfo = UploadFileInfo.getFileInfo(localFile);
        int uploadHash = getUploadHash(localFile, localFile.getAbsolutePath(), fileInfo);
        if (hasStoredUploadInfo(uploadHash) || uploadContext.getUploadParam() != null) {
            KssUploadInfo kssUploadInfo = null;
            while (!Thread.interrupted()) {
                if (kssUploadInfo == null) {
                    kssUploadInfo = getUploadInfo(fileInfo, uploadContext, uploadHash);
                }
                KssUploadInfo kssUploadInfo2 = kssUploadInfo;
                if (kssUploadInfo2 == null) {
                    kscTransferListener.setSendTotal(localFile.length());
                    kscTransferListener.setSendPos(localFile.length());
                    return;
                } else if (kssUploadInfo2.isBroken()) {
                    uploadContext.setNeedRequestUpload(true);
                    uploadContext.setUploadParam((UploadParameter) null);
                    return;
                } else if (kssUploadInfo2.isCompleted()) {
                    kscTransferListener.setSendTotal(localFile.length());
                    kscTransferListener.setSendPos(localFile.length());
                    deleteUploadInfo(uploadHash);
                    uploadContext.setNeedRequestUpload(false);
                    uploadContext.setCommitString(kssUploadInfo2.getCommitString());
                    uploadContext.setUploadId(kssUploadInfo2.getUploadId());
                    uploadContext.setSha1(kssUploadInfo2.getFileInfo().getSha1());
                    return;
                } else {
                    this.mUploader.upload(localFile, kscTransferListener, KssTransferStopper.KssTransferStopperFromMiCloudTransferStopper.get(uploadContext.getStopper()), uploadHash, kssUploadInfo2);
                    kssUploadInfo = kssUploadInfo2;
                }
            }
            throw new InterruptedException();
        }
        uploadContext.setNeedRequestUpload(true);
        uploadContext.setKssString(fileInfo.getKssString());
        uploadContext.setSha1(fileInfo.getSha1());
    }
}
