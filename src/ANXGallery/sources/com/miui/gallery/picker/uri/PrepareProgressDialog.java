package com.miui.gallery.picker.uri;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.widget.GalleryDialogFragment;

public class PrepareProgressDialog extends GalleryDialogFragment {
    private DialogInterface.OnCancelListener mCancelListener;
    private int mMax;
    private int mProgress;
    private int mStage;

    public int getDialogMessageId() {
        int i = this.mStage;
        if (i == 0) {
            return R.string.picker_download_progress_message;
        }
        if (i != 1) {
            return 0;
        }
        return R.string.picker_origin_request_progress_message;
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        DialogInterface.OnCancelListener onCancelListener = this.mCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        int dialogMessageId = getDialogMessageId();
        if (dialogMessageId != 0) {
            progressDialog.setMessage(getActivity().getString(dialogMessageId, new Object[]{Integer.valueOf(this.mProgress), Integer.valueOf(this.mMax)}));
        }
        return progressDialog;
    }

    public void setCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mCancelListener = onCancelListener;
    }

    public void setDialogMessage() {
        ProgressDialog progressDialog;
        int dialogMessageId = getDialogMessageId();
        if (dialogMessageId != 0 && (progressDialog = (ProgressDialog) getDialog()) != null && progressDialog.isShowing()) {
            progressDialog.setMessage(getActivity().getString(dialogMessageId, new Object[]{Integer.valueOf(this.mProgress), Integer.valueOf(this.mMax)}));
        }
    }

    public void setMax(int i) {
        this.mMax = i;
        setDialogMessage();
    }

    public void setStage(int i) {
        this.mStage = i;
        setDialogMessage();
    }

    public void updateProgress(int i) {
        this.mProgress = i;
        setDialogMessage();
    }
}
