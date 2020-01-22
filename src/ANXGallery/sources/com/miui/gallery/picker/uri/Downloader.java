package com.miui.gallery.picker.uri;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.uil.CloudImageLoader;
import com.miui.gallery.util.uil.CloudImageLoadingListenerAdapter;
import com.miui.gallery.util.uil.CloudImageLoadingProgressListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Downloader {
    private Handler.Callback mCallback = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            DownloadResult downloadResult = (DownloadResult) message.obj;
            Log.d("Downloader", "%s finished", (Object) downloadResult);
            if (downloadResult.mState == DownloadResult.State.SUCCESS) {
                Downloader.this.mSuccess.add(downloadResult);
            } else {
                Downloader.this.mFails.add(downloadResult);
            }
            if (Downloader.this.mDownloadListener != null) {
                Downloader.this.mDownloadListener.onUpdate(Downloader.this.mSuccess, Downloader.this.mFails);
            }
            Downloader.this.checkFinished();
            return false;
        }
    };
    private boolean mCancelled;
    /* access modifiers changed from: private */
    public DownloadListener mDownloadListener;
    private List<DownloadTask> mDownloadTasks;
    /* access modifiers changed from: private */
    public List<DownloadResult> mFails;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper(), this.mCallback);
    private boolean mPaused;
    /* access modifiers changed from: private */
    public List<DownloadResult> mSuccess;

    public interface DownloadListener {
        void onCancelled(List<DownloadResult> list, List<DownloadResult> list2);

        void onEnd(List<DownloadResult> list, List<DownloadResult> list2);

        void onStart(List<DownloadTask> list);

        void onUpdate(List<DownloadResult> list, List<DownloadResult> list2);
    }

    public static class DownloadResult {
        public String mPath;
        public State mState;
        public DownloadTask mTask;

        public enum State {
            PENDING,
            SUCCESS,
            FAILED,
            CANCELLED
        }

        DownloadResult(DownloadTask downloadTask, State state, String str) {
            this.mTask = downloadTask;
            this.mState = state;
            this.mPath = str;
        }

        public String toString() {
            return "DownloadResult{mTask=" + this.mTask + ", mState=" + this.mState + ", mPath='" + this.mPath + "'" + '}';
        }
    }

    public static class DownloadTask {
        public int mPosition;
        public int mSize;
        public DownloadType mType;
        public Uri mUri;

        public DownloadTask(Uri uri, DownloadType downloadType, int i, int i2) {
            this.mUri = uri;
            this.mType = downloadType;
            this.mSize = i;
            this.mPosition = i2;
        }

        public String toString() {
            return "DownloadTask{mUri=" + this.mUri + ", mType=" + this.mType + ", mSize=" + this.mSize + ", mPosition =" + this.mPosition + '}';
        }
    }

    public class TaskStateListener extends CloudImageLoadingListenerAdapter {
        private DownloadTask mTask;

        TaskStateListener(DownloadTask downloadTask) {
            this.mTask = downloadTask;
        }

        public void onDownloadComplete(Uri uri, DownloadType downloadType, View view, String str) {
            Downloader.this.mHandler.sendMessage(Downloader.this.mHandler.obtainMessage(1, new DownloadResult(this.mTask, DownloadResult.State.SUCCESS, str)));
        }

        public void onLoadingCancelled(Uri uri, DownloadType downloadType, View view) {
            Downloader.this.mHandler.sendMessage(Downloader.this.mHandler.obtainMessage(1, new DownloadResult(this.mTask, DownloadResult.State.CANCELLED, (String) null)));
        }

        public void onLoadingFailed(Uri uri, DownloadType downloadType, View view, ErrorCode errorCode) {
            Downloader.this.mHandler.sendMessage(Downloader.this.mHandler.obtainMessage(1, new DownloadResult(this.mTask, DownloadResult.State.FAILED, (String) null)));
        }
    }

    public Downloader(ArrayList<DownloadTask> arrayList, DownloadListener downloadListener) {
        this.mDownloadTasks = (List) arrayList.clone();
        this.mDownloadListener = downloadListener;
        this.mSuccess = new ArrayList();
        this.mFails = new ArrayList();
    }

    /* access modifiers changed from: private */
    public void checkFinished() {
        DownloadListener downloadListener;
        Log.d("Downloader", "success: %d, failed: %d, total: %d", Integer.valueOf(this.mSuccess.size()), Integer.valueOf(this.mFails.size()), Integer.valueOf(this.mDownloadTasks.size()));
        if (this.mSuccess.size() + this.mFails.size() < this.mDownloadTasks.size() || this.mPaused || (downloadListener = this.mDownloadListener) == null) {
            return;
        }
        if (this.mCancelled) {
            downloadListener.onCancelled(this.mSuccess, this.mFails);
        } else {
            downloadListener.onEnd(this.mSuccess, this.mFails);
        }
    }

    public void cancel() {
        this.mCancelled = true;
        for (DownloadTask next : this.mDownloadTasks) {
            boolean z = false;
            Iterator<DownloadResult> it = this.mSuccess.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().mTask == next) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            Iterator<DownloadResult> it2 = this.mFails.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().mTask == next) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                Log.d("Downloader", "cancel %s", (Object) next);
                CloudImageLoader.getInstance().cancel(next.mUri, next.mType);
            }
        }
    }

    public void destroy() {
        Log.d("Downloader", "downloader destroy");
        this.mDownloadListener = null;
        this.mDownloadTasks.clear();
        this.mSuccess.clear();
        this.mFails.clear();
    }

    public int getRemainSize() {
        return this.mDownloadTasks.size() - this.mSuccess.size();
    }

    public void pause() {
        Log.d("Downloader", "downloader pause");
        this.mPaused = true;
    }

    public void resume() {
        Log.d("Downloader", "downloader resume");
        this.mPaused = false;
        checkFinished();
    }

    public void start() {
        ArrayList arrayList = new ArrayList(this.mDownloadTasks.size());
        ArrayList arrayList2 = new ArrayList(this.mDownloadTasks.size());
        ArrayList arrayList3 = new ArrayList(this.mDownloadTasks.size());
        for (DownloadTask next : this.mDownloadTasks) {
            arrayList.add(next.mUri);
            arrayList2.add(next.mType);
            arrayList3.add(new TaskStateListener(next));
        }
        DownloadListener downloadListener = this.mDownloadListener;
        if (downloadListener != null) {
            downloadListener.onStart(this.mDownloadTasks);
        }
        CloudImageLoader.getInstance().loadImages(arrayList, arrayList2, arrayList3, (List<CloudImageLoadingProgressListener>) null);
    }
}
