package com.miui.gallery.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class ProcessTask<Params, Result> extends AsyncTask<Params, Void, Result> {
    private OnCompleteListener<Result> mCompleteListener;
    private boolean mIsCancelable;
    private OnCancelListener<Result> mOnCancelListener;
    private ProcessCallback<Params, Result> mProcessCallback;
    private ProgressDialogFragment mProgressDialog;

    public interface OnCancelListener<R> {
        void onCancelled(R r);
    }

    public interface OnCompleteListener<R> {
        void onCompleteProcess(R r);
    }

    public interface ProcessCallback<P, R> {
        R doProcess(P[] pArr);
    }

    public ProcessTask(ProcessCallback<Params, Result> processCallback) {
        this(processCallback, (OnCompleteListener) null);
    }

    public ProcessTask(ProcessCallback<Params, Result> processCallback, OnCompleteListener<Result> onCompleteListener) {
        if (processCallback != null) {
            this.mProcessCallback = processCallback;
            this.mCompleteListener = onCompleteListener;
            return;
        }
        throw new IllegalArgumentException("Null processCallback is not accepted!");
    }

    /* access modifiers changed from: protected */
    public Result doInBackground(Params... paramsArr) {
        Result doProcess = this.mProcessCallback.doProcess(paramsArr);
        if (isCancelled()) {
            return null;
        }
        return doProcess;
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
        super.onCancelled(result);
        OnCancelListener<Result> onCancelListener = this.mOnCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancelled(result);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
        super.onPostExecute(result);
        OnCompleteListener<Result> onCompleteListener = this.mCompleteListener;
        if (onCompleteListener != null) {
            onCompleteListener.onCompleteProcess(result);
        }
        ProgressDialogFragment progressDialogFragment = this.mProgressDialog;
        if (progressDialogFragment != null && progressDialogFragment.getFragmentManager() != null) {
            this.mProgressDialog.dismissAllowingStateLoss();
        }
    }

    public void setCancelable(boolean z) {
        this.mIsCancelable = z;
    }

    public void setCompleteListener(OnCompleteListener onCompleteListener) {
        this.mCompleteListener = onCompleteListener;
    }

    public void showProgress(Activity activity, String str) {
        if (activity != null) {
            this.mProgressDialog = new ProgressDialogFragment();
            this.mProgressDialog.setIndeterminate(true);
            this.mProgressDialog.setMessage(str);
            this.mProgressDialog.setCancelable(this.mIsCancelable);
            if (this.mIsCancelable) {
                this.mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        ProcessTask.this.cancel(true);
                    }
                });
            }
            this.mProgressDialog.showAllowingStateLoss(activity.getFragmentManager(), "ProcessTask");
        }
    }
}
