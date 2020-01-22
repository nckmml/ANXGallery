package com.miui.gallery.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.widget.GalleryDialogFragment;
import miui.app.ProgressDialog;

public class ProgressDialogFragment extends GalleryDialogFragment {
    private boolean mIndeterminate;
    private CharSequence mMessage;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private CharSequence mTitle;

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        DialogInterface.OnCancelListener onCancelListener = this.mOnCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(this.mIndeterminate);
        progressDialog.setTitle(this.mTitle);
        progressDialog.setMessage(this.mMessage);
        return progressDialog;
    }

    public void setIndeterminate(boolean z) {
        this.mIndeterminate = z;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }
}
