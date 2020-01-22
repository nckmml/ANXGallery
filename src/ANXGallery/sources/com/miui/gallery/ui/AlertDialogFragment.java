package com.miui.gallery.ui;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.widget.GalleryDialogFragment;
import miui.app.AlertDialog;

@TargetApi(11)
public class AlertDialogFragment extends GalleryDialogFragment {
    private DialogInterface.OnCancelListener mCancelListener;
    private CharSequence mCheckBoxMessage;
    private DialogInterface.OnDismissListener mDismissListener;
    private boolean mIsChecked;
    private CharSequence mMessage;
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private CharSequence mNegativeButtonText;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private CharSequence mPositiveButtonText;
    private CharSequence mTitle;

    public static final class Builder {
        private DialogInterface.OnCancelListener mCancelListener;
        private boolean mCancelable = true;
        private CharSequence mCheckBoxMessage;
        private DialogInterface.OnDismissListener mDismissListener;
        private boolean mIsChecked;
        private CharSequence mMessage;
        private DialogInterface.OnClickListener mNegativeButtonListener;
        private CharSequence mNegativeButtonText;
        private DialogInterface.OnClickListener mPositiveButtonListener;
        private CharSequence mPositiveButtonText;
        private CharSequence mTitle;

        public AlertDialogFragment create() {
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.setTitle(this.mTitle);
            alertDialogFragment.setMessage(this.mMessage);
            alertDialogFragment.setCheckBox(this.mIsChecked, this.mCheckBoxMessage);
            alertDialogFragment.setNegativeButton(this.mNegativeButtonText, this.mNegativeButtonListener);
            alertDialogFragment.setPositiveButton(this.mPositiveButtonText, this.mPositiveButtonListener);
            alertDialogFragment.setOnCancelListener(this.mCancelListener);
            alertDialogFragment.setOnDismissListener(this.mDismissListener);
            alertDialogFragment.setCancelable(this.mCancelable);
            return alertDialogFragment;
        }

        public Builder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.mMessage = charSequence;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.mNegativeButtonText = charSequence;
            this.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.mPositiveButtonText = charSequence;
            this.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        DialogInterface.OnCancelListener onCancelListener = this.mCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(this.mTitle).setMessage(this.mMessage).setCheckBox(this.mIsChecked, this.mCheckBoxMessage).setPositiveButton(this.mPositiveButtonText, this.mPositiveButtonListener).setNegativeButton(this.mNegativeButtonText, this.mNegativeButtonListener).create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    /* access modifiers changed from: package-private */
    public void setCheckBox(boolean z, CharSequence charSequence) {
        this.mIsChecked = z;
        this.mCheckBoxMessage = charSequence;
    }

    /* access modifiers changed from: package-private */
    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
    }

    /* access modifiers changed from: package-private */
    public void setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButtonText = charSequence;
        this.mNegativeButtonListener = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mCancelListener = onCancelListener;
    }

    /* access modifiers changed from: package-private */
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
    }

    /* access modifiers changed from: package-private */
    public void setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButtonText = charSequence;
        this.mPositiveButtonListener = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }
}
