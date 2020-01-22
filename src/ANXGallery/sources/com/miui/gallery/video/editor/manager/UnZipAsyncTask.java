package com.miui.gallery.video.editor.manager;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.gallery.video.editor.DownloadCommand;
import com.miui.gallery.video.editor.interfaces.IVideoEditorListener;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import com.miui.gallery.video.editor.util.FileHelper;

public class UnZipAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private VideoEditorBaseModel mData;
    private String mUnZipPath;
    private IVideoEditorListener.IUnzipFileListener mUnzipFileListener;
    private String mZipPath;

    public UnZipAsyncTask(DownloadCommand downloadCommand, IVideoEditorListener.IUnzipFileListener iUnzipFileListener) {
        this.mData = downloadCommand.getData();
        this.mZipPath = downloadCommand.getZipPath();
        this.mUnZipPath = downloadCommand.getUnzipPath();
        this.mUnzipFileListener = iUnzipFileListener;
    }

    public void clearListener() {
        IVideoEditorListener.IUnzipFileListener iUnzipFileListener = this.mUnzipFileListener;
        if (iUnzipFileListener != null) {
            iUnzipFileListener.onUnzipFileFailed(true);
            this.mUnzipFileListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(Void... voidArr) {
        boolean z;
        if (!isCancelled()) {
            this.mUnZipPath = FileHelper.unZipFile(this.mZipPath, this.mUnZipPath);
            if (!TextUtils.isEmpty(this.mUnZipPath)) {
                this.mData.setUnZipPath(this.mUnZipPath);
                z = true;
                return Boolean.valueOf(z);
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        if (this.mUnzipFileListener != null) {
            if (bool.booleanValue()) {
                this.mUnzipFileListener.onUnzipFileSuccess();
            } else {
                this.mUnzipFileListener.onUnzipFileFailed(false);
            }
        }
    }
}
