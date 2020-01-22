package com.miui.gallery.editor.photo.screen.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.widget.GalleryDialogFragment;

public class ScreenDeleteDialogFragment extends GalleryDialogFragment {
    private Context mContext;
    private AlertDialog mDialog;
    private DialogClickListener mListener;

    public interface DialogClickListener {
        void onDelete();
    }

    public /* synthetic */ void lambda$onCreateDialog$91$ScreenDeleteDialogFragment(DialogInterface dialogInterface, int i) {
        DialogClickListener dialogClickListener = this.mListener;
        if (dialogClickListener != null) {
            dialogClickListener.onDelete();
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mContext = getActivity();
        this.mDialog = new AlertDialog.Builder(this.mContext).setTitle(R.string.screen_editor_btn_delete).setMessage(R.string.screen_editor_delete_dialog_msg).setPositiveButton(R.string.screen_editor_btn_delete, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ScreenDeleteDialogFragment.this.lambda$onCreateDialog$91$ScreenDeleteDialogFragment(dialogInterface, i);
            }
        }).setNegativeButton(R.string.screen_save_dialog_btn_cancel, (DialogInterface.OnClickListener) null).create();
        return this.mDialog;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mListener = null;
    }

    public void setDialogClickListener(DialogClickListener dialogClickListener) {
        this.mListener = dialogClickListener;
    }
}
