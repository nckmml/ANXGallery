package com.miui.gallery.editor.photo.core.imports.text.typeface;

import android.os.AsyncTask;
import com.miui.gallery.util.UnzipUtils;

public class UnzipFontTask extends AsyncTask<Void, Void, Boolean> {
    private DownloadCallback mCallBack;
    private TextStyle mResource;

    public UnzipFontTask(TextStyle textStyle, DownloadCallback downloadCallback) {
        this.mResource = textStyle;
        this.mCallBack = downloadCallback;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(Void... voidArr) {
        if (isCancelled()) {
            return false;
        }
        return Boolean.valueOf(UnzipUtils.unZipFile(this.mResource.getDownloadFilePath()));
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        DownloadCallback downloadCallback;
        if (!isCancelled() && (downloadCallback = this.mCallBack) != null) {
            downloadCallback.onCompleted(bool.booleanValue());
        }
    }

    public void release() {
        cancel(true);
        this.mCallBack = null;
    }
}
