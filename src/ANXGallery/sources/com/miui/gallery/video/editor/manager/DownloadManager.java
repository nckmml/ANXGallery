package com.miui.gallery.video.editor.manager;

import android.net.Uri;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.net.HttpManager;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.ResponseListener;
import com.miui.gallery.net.download.GalleryDownloadManager;
import com.miui.gallery.net.download.Request;
import com.miui.gallery.net.download.Verifier;
import com.miui.gallery.net.resource.DownloadInfo;
import com.miui.gallery.net.resource.DownloadRequest;
import com.miui.gallery.util.Log;
import com.miui.gallery.video.editor.DownloadCommand;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadManager {
    /* access modifiers changed from: private */
    public List<Request> mRequestList = new ArrayList();

    public void cancelAll() {
        HttpManager.getInstance().cancelAll("DownloadManager");
        for (Request listener : this.mRequestList) {
            listener.setListener((Request.Listener) null);
        }
        this.mRequestList.clear();
    }

    public void download(DownloadCommand downloadCommand, final Request.Listener listener) {
        if (downloadCommand == null) {
            Log.w("DownloadManager", "resource is null, download is fail.");
            return;
        }
        final String zipPath = downloadCommand.getZipPath();
        DownloadRequest downloadRequest = new DownloadRequest(downloadCommand.getId());
        downloadRequest.setTag("DownloadManager");
        downloadRequest.execute(new ResponseListener() {
            public void onResponse(Object... objArr) {
                DownloadInfo downloadInfo = objArr[0];
                Log.d("DownloadManager", String.format("download %s, %s", new Object[]{downloadInfo.url, downloadInfo.sha1}));
                File file = new File(zipPath);
                if (file.exists()) {
                    Log.d("DownloadManager", "the file already exist and its path is : %s", (Object) file.getAbsolutePath());
                    file.delete();
                }
                Request request = new Request(Uri.parse(downloadInfo.url), file);
                DownloadManager.this.mRequestList.add(request);
                request.setVerifier(new Verifier.Sha1(downloadInfo.sha1));
                request.setAllowedOverMetered(NetworkUtils.isActiveNetworkMetered());
                request.setListener(listener);
                GalleryDownloadManager.INSTANCE.enqueue(request);
            }

            public void onResponseError(ErrorCode errorCode, String str, Object obj) {
                listener.onComplete(-1);
                Log.w("DownloadManager", "errorMessage:%s,errorCode.name:5s", str, errorCode.name());
            }
        });
    }
}
