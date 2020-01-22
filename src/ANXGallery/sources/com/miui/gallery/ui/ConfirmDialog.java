package com.miui.gallery.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.widget.GalleryDialogFragment;

public class ConfirmDialog extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public ConfirmDialogInterface mConfirmDialogInterface;

    public interface ConfirmDialogInterface {
        void onCancel(DialogFragment dialogFragment);

        void onConfirm(DialogFragment dialogFragment);
    }

    public static void showConfirmDialog(FragmentManager fragmentManager, String str, String str2, String str3, String str4, ConfirmDialogInterface confirmDialogInterface) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("title", str);
        }
        bundle.putString("msg", str2);
        bundle.putString("negativeButton", str3);
        bundle.putString("positiveButton", str4);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(bundle);
        confirmDialog.setConfirmDialogInterface(confirmDialogInterface);
        confirmDialog.showAllowingStateLoss(fragmentManager, "ConfirmDialog");
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        ConfirmDialogInterface confirmDialogInterface = this.mConfirmDialogInterface;
        if (confirmDialogInterface != null) {
            confirmDialogInterface.onCancel(this);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(getActivity()).setCancelable(true).setIconAttribute(16843605).setMessage(arguments.getString("msg")).setNegativeButton(arguments.getString("negativeButton"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ConfirmDialog.this.mConfirmDialogInterface != null) {
                    ConfirmDialog.this.mConfirmDialogInterface.onCancel(ConfirmDialog.this);
                }
                ConfirmDialog.this.dismissAllowingStateLoss();
            }
        }).setPositiveButton(arguments.getString("positiveButton"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ConfirmDialog.this.mConfirmDialogInterface != null) {
                    ConfirmDialog.this.mConfirmDialogInterface.onConfirm(ConfirmDialog.this);
                }
                ConfirmDialog.this.dismissAllowingStateLoss();
            }
        });
        String string = arguments.getString("title");
        if (!TextUtils.isEmpty(string)) {
            positiveButton.setTitle(string);
        }
        AlertDialog create = positiveButton.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public void setConfirmDialogInterface(ConfirmDialogInterface confirmDialogInterface) {
        this.mConfirmDialogInterface = confirmDialogInterface;
    }
}
