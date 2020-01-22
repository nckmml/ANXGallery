package com.miui.gallery.video.editor.net;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.net.download.Request;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.DownloadCommand;
import com.miui.gallery.video.editor.DownloadCommandQueue;
import com.miui.gallery.video.editor.factory.VideoEditorModuleFactory;
import com.miui.gallery.video.editor.interfaces.IVideoEditorListener;
import com.miui.gallery.video.editor.manager.DownloadManager;
import com.miui.gallery.video.editor.manager.NexAssetTemplateManager;
import com.miui.gallery.video.editor.manager.UnZipAsyncTask;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import com.miui.gallery.video.editor.model.VideoEditorTemplateBaseModel;

public class ResourceDownloadManager {
    /* access modifiers changed from: private */
    public static String TAG = "ResourceDownloadManager";
    private NexAssetTemplateManager.ILoadAssetTemplate mAssetTemplateListener = new NexAssetTemplateManager.ILoadAssetTemplate() {
        public void onFail() {
            Log.e(ResourceDownloadManager.TAG, "template install fail!");
            ResourceDownloadManager resourceDownloadManager = ResourceDownloadManager.this;
            resourceDownloadManager.onCommandFail(resourceDownloadManager.mCommand);
            ResourceDownloadManager resourceDownloadManager2 = ResourceDownloadManager.this;
            resourceDownloadManager2.onNextCommandRunning(resourceDownloadManager2.mCommand);
        }

        public void onSuccess() {
            Log.d(ResourceDownloadManager.TAG, "template install success!");
            ResourceDownloadManager resourceDownloadManager = ResourceDownloadManager.this;
            resourceDownloadManager.onCommandSuccess(resourceDownloadManager.mCommand);
            ResourceDownloadManager resourceDownloadManager2 = ResourceDownloadManager.this;
            resourceDownloadManager2.onNextCommandRunning(resourceDownloadManager2.mCommand);
        }
    };
    /* access modifiers changed from: private */
    public DownloadCommand mCommand;
    private DownloadCommandQueue mCommandQueue;
    private int mCommandState = 19;
    /* access modifiers changed from: private */
    public Context mContext;
    private DownloadManager mDownloadManager;
    private IDownloadTaskListener mDownloadTaskListener;
    private FragmentManager mFragmentManager;
    private VideoEditorModuleFactory mModuleFactory;
    private Request.Listener mRequestListener = new Request.Listener() {
        public void onComplete(int i) {
            if (i == 0) {
                Log.d(ResourceDownloadManager.TAG, "the request download success!");
                ResourceDownloadManager.this.unzipResource();
                return;
            }
            Log.e(ResourceDownloadManager.TAG, "the request download fail!");
            ThreadManager.runOnMainThread(new Runnable() {
                public void run() {
                    ResourceDownloadManager.this.onCommandFail(ResourceDownloadManager.this.mCommand);
                    ResourceDownloadManager.this.onNextCommandRunning(ResourceDownloadManager.this.mCommand);
                }
            });
        }

        public void onProgressUpdate(int i) {
        }

        public void onStart() {
            Log.d(ResourceDownloadManager.TAG, "the request download start! ");
        }
    };
    private UnZipAsyncTask mUnZipAsyncTask;
    private IVideoEditorListener.IUnzipFileListener mUnzipFileListener = new IVideoEditorListener.IUnzipFileListener() {
        public void onUnzipFileFailed(boolean z) {
            if (!z) {
                ToastUtils.makeText(ResourceDownloadManager.this.mContext, (int) R.string.video_editor_unzip_file_fail);
            }
            ResourceDownloadManager resourceDownloadManager = ResourceDownloadManager.this;
            resourceDownloadManager.onCommandFail(resourceDownloadManager.mCommand);
            ResourceDownloadManager resourceDownloadManager2 = ResourceDownloadManager.this;
            resourceDownloadManager2.onNextCommandRunning(resourceDownloadManager2.mCommand);
        }

        public void onUnzipFileSuccess() {
            if (ResourceDownloadManager.this.allowResourceInstall()) {
                Log.d(ResourceDownloadManager.TAG, "resource unzip success, to install it!");
                ResourceDownloadManager.this.installResource();
                return;
            }
            Log.d(ResourceDownloadManager.TAG, "resource unzip success，the task end!");
            ResourceDownloadManager resourceDownloadManager = ResourceDownloadManager.this;
            resourceDownloadManager.onCommandSuccess(resourceDownloadManager.mCommand);
            ResourceDownloadManager resourceDownloadManager2 = ResourceDownloadManager.this;
            resourceDownloadManager2.onNextCommandRunning(resourceDownloadManager2.mCommand);
        }
    };

    public interface IDownloadTaskListener {
        void onCommandFail(VideoEditorBaseModel videoEditorBaseModel, int i);

        void onCommandStart(VideoEditorBaseModel videoEditorBaseModel, int i);

        void onCommandSuccess(VideoEditorBaseModel videoEditorBaseModel, int i);

        void onTaskCancel(VideoEditorBaseModel videoEditorBaseModel, int i);
    }

    public ResourceDownloadManager(Context context, FragmentManager fragmentManager, VideoEditorModuleFactory videoEditorModuleFactory) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mModuleFactory = videoEditorModuleFactory;
    }

    /* access modifiers changed from: private */
    public boolean allowResourceInstall() {
        DownloadCommand downloadCommand = this.mCommand;
        if (downloadCommand == null) {
            return false;
        }
        return downloadCommand.isTemplate();
    }

    /* access modifiers changed from: private */
    public void buildCommand(VideoEditorBaseModel videoEditorBaseModel, int i) {
        if (this.mCommandQueue == null) {
            this.mCommandQueue = new DownloadCommandQueue();
        }
        DownloadCommand downloadCommand = new DownloadCommand(videoEditorBaseModel, i, this.mModuleFactory);
        boolean put = this.mCommandQueue.put(downloadCommand);
        Log.d(TAG, "command num: %s ", (Object) Integer.valueOf(this.mCommandQueue.getCapacity()));
        if (put) {
            onCommandStart(downloadCommand);
            downloadResource();
        }
    }

    private void confirmDownloadResource() {
        if (!this.mCommandQueue.isEmpty()) {
            if (this.mDownloadManager == null) {
                this.mDownloadManager = new DownloadManager();
            }
            this.mCommand = this.mCommandQueue.get();
            this.mCommandState = 17;
            this.mDownloadManager.download(this.mCommand, this.mRequestListener);
        }
    }

    private void downloadResource() {
        if (hasCommandRunning()) {
            Log.e(TAG, "the other command is running.");
        } else {
            confirmDownloadResource();
        }
    }

    private boolean hasCommandRunning() {
        return this.mCommandState == 17;
    }

    private boolean hasCurrentCommand(long j) {
        int i = 0;
        while (true) {
            DownloadCommandQueue downloadCommandQueue = this.mCommandQueue;
            if (downloadCommandQueue == null || i >= downloadCommandQueue.getCapacity()) {
                return false;
            }
            DownloadCommand downloadCommand = this.mCommandQueue.get(i);
            if (downloadCommand != null && downloadCommand.getId() == j) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void installResource() {
        DownloadCommand downloadCommand = this.mCommand;
        if (downloadCommand != null) {
            VideoEditorTemplateBaseModel videoEditorTemplateBaseModel = (VideoEditorTemplateBaseModel) downloadCommand.getData();
            if (videoEditorTemplateBaseModel.getAssetId() > 0) {
                NexAssetTemplateManager.getInstance().installProcess(videoEditorTemplateBaseModel.getAssetId(), this.mAssetTemplateListener);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onCommandFail(DownloadCommand downloadCommand) {
        if (this.mDownloadTaskListener != null && downloadCommand != null) {
            Log.d(TAG, "the command %s is completed on fail. ", (Object) downloadCommand.getData().getLabel());
            this.mDownloadTaskListener.onCommandFail(downloadCommand.getData(), downloadCommand.getPosition());
        }
    }

    private void onCommandStart(DownloadCommand downloadCommand) {
        if (this.mDownloadTaskListener != null) {
            Log.d(TAG, "the command %s start. ", (Object) downloadCommand.getData().getLabel());
            this.mCommandState = hasCommandRunning() ? this.mCommandState : 16;
            this.mDownloadTaskListener.onCommandStart(downloadCommand.getData(), downloadCommand.getPosition());
        }
    }

    /* access modifiers changed from: private */
    public void onCommandSuccess(DownloadCommand downloadCommand) {
        if (this.mDownloadTaskListener != null && downloadCommand != null) {
            Log.d(TAG, "the command %s is completed on success. ", (Object) downloadCommand.getData().getLabel());
            this.mDownloadTaskListener.onCommandSuccess(downloadCommand.getData(), downloadCommand.getPosition());
        }
    }

    /* access modifiers changed from: private */
    public void onNextCommandRunning(DownloadCommand downloadCommand) {
        DownloadCommandQueue downloadCommandQueue = this.mCommandQueue;
        if (downloadCommandQueue != null && !downloadCommandQueue.isEmpty()) {
            this.mCommandState = 18;
            this.mCommandQueue.remove(downloadCommand);
            confirmDownloadResource();
        }
    }

    private void onTaskCancel(DownloadCommand downloadCommand) {
        IDownloadTaskListener iDownloadTaskListener = this.mDownloadTaskListener;
        if (!(iDownloadTaskListener == null || downloadCommand == null)) {
            this.mCommandState = 19;
            iDownloadTaskListener.onTaskCancel(downloadCommand.getData(), downloadCommand.getPosition());
        }
        while (true) {
            DownloadCommandQueue downloadCommandQueue = this.mCommandQueue;
            if (downloadCommandQueue == null || downloadCommandQueue.isEmpty()) {
                DownloadCommandQueue downloadCommandQueue2 = this.mCommandQueue;
            } else {
                DownloadCommand downloadCommand2 = this.mCommandQueue.get();
                onCommandFail(downloadCommand2);
                this.mCommandQueue.remove(downloadCommand2);
            }
        }
        DownloadCommandQueue downloadCommandQueue22 = this.mCommandQueue;
        if (downloadCommandQueue22 != null) {
            downloadCommandQueue22.clear();
            this.mCommandQueue = null;
        }
        this.mCommand = null;
        Log.d(TAG, "the task is completed. ");
    }

    private void showNetworkConfirmDialog(ConfirmDialog.ConfirmDialogInterface confirmDialogInterface) {
        ConfirmDialog.showConfirmDialog(this.mFragmentManager, this.mContext.getResources().getString(R.string.video_editor_download_with_metered_network_title), this.mContext.getResources().getString(R.string.video_editor_download_with_metered_network_msg), this.mContext.getResources().getString(R.string.video_editor_cancel_download), this.mContext.getResources().getString(R.string.video_editor_download), confirmDialogInterface);
    }

    /* access modifiers changed from: private */
    public void unzipResource() {
        DownloadCommand downloadCommand = this.mCommand;
        if (downloadCommand != null) {
            this.mUnZipAsyncTask = new UnZipAsyncTask(downloadCommand, this.mUnzipFileListener);
            this.mUnZipAsyncTask.execute(new Void[0]);
        }
    }

    public void cancel() {
        onTaskCancel(this.mCommand);
        DownloadManager downloadManager = this.mDownloadManager;
        if (downloadManager != null) {
            downloadManager.cancelAll();
            this.mDownloadManager = null;
        }
        UnZipAsyncTask unZipAsyncTask = this.mUnZipAsyncTask;
        if (unZipAsyncTask != null) {
            unZipAsyncTask.clearListener();
            this.mUnZipAsyncTask.cancel(true);
        }
        if (this.mDownloadTaskListener != null) {
            this.mDownloadTaskListener = null;
        }
    }

    public void createDownloadCommand(final VideoEditorBaseModel videoEditorBaseModel, final int i) {
        if (!NetworkUtils.isNetworkConnected()) {
            ToastUtils.makeText(this.mContext, (int) R.string.video_editor_download_failed_for_notwork);
        } else if (NetworkUtils.isActiveNetworkMetered()) {
            if (hasCurrentCommand(videoEditorBaseModel.getId())) {
                Log.d(TAG, "the command has exist.");
            } else {
                showNetworkConfirmDialog(new ConfirmDialog.ConfirmDialogInterface() {
                    public void onCancel(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                    }

                    public void onConfirm(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                        ResourceDownloadManager.this.buildCommand(videoEditorBaseModel, i);
                    }
                });
            }
        } else if (hasCurrentCommand(videoEditorBaseModel.getId())) {
            Log.d(TAG, "the command has exist.");
        } else {
            buildCommand(videoEditorBaseModel, i);
        }
    }

    public void setDownloadTaskListener(IDownloadTaskListener iDownloadTaskListener) {
        this.mDownloadTaskListener = iDownloadTaskListener;
    }
}
