package com.miui.gallery.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.Arrays;
import miui.app.AlertDialog;

public class CopyOrMoveDialog extends GalleryDialogFragment {
    private AlertDialog mDialog;
    private DialogInterface.OnClickListener mItemClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (CopyOrMoveDialog.this.mOnOperationSelectedListener == null) {
                return;
            }
            if (i == -2) {
                CopyOrMoveDialog.this.mOnOperationSelectedListener.onOperationSelected(2);
                dialogInterface.cancel();
            } else if (i >= 0 && i < 2) {
                Log.d("CopyOrMoveDialog", "Creation select : %d", (Object) Integer.valueOf(i));
                CopyOrMoveDialog.this.mOnOperationSelectedListener.onOperationSelected(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public OnOperationSelectedListener mOnOperationSelectedListener;

    public interface OnOperationSelectedListener {
        void onOperationSelected(int i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence[] textArray = getResources().getTextArray(R.array.menu_copy_or_move);
        if (textArray.length > 2) {
            textArray = (CharSequence[]) Arrays.copyOf(textArray, 2);
        }
        builder.setTitle(R.string.select_image_operation).setItems(textArray, this.mItemClickListener).setNegativeButton(R.string.cancel, this.mItemClickListener);
        this.mDialog = builder.create();
        this.mDialog.setCanceledOnTouchOutside(false);
        return this.mDialog;
    }

    public void setOnOperationSelectedListener(OnOperationSelectedListener onOperationSelectedListener) {
        this.mOnOperationSelectedListener = onOperationSelectedListener;
    }
}
