package com.miui.gallery.video.editor.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.widget.GalleryDialogFragment;

public class ProgressDialog extends GalleryDialogFragment {
    private ImageButton mCancelImageButton;
    private TextView mMsgTextView;
    private ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public ProgressDialogInterface mProgressDialogInterface;
    private int mTempMax = -1;
    private String mTempMsg;
    private int mTempMsgId = -1;
    private int mTempProgress = -1;
    private View mView;

    public interface ProgressDialogInterface {
        boolean onCancelClicked();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mView = LayoutInflater.from(getActivity()).inflate(R.layout.video_editor_dialog_auto_trim_progress, (ViewGroup) null);
        this.mMsgTextView = (TextView) this.mView.findViewById(R.id.video_editor_progress_dialog_msg_textview);
        this.mProgressBar = (ProgressBar) this.mView.findViewById(R.id.video_editor_progress_dialog_progressbar);
        this.mCancelImageButton = (ImageButton) this.mView.findViewById(R.id.video_editor_progress_dialog_cancel_button);
        this.mProgressBar.setMax(100);
        this.mCancelImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ProgressDialog.this.mProgressDialogInterface == null || ProgressDialog.this.mProgressDialogInterface.onCancelClicked()) {
                    ProgressDialog.this.dismissAllowingStateLoss();
                }
            }
        });
        int i = this.mTempMax;
        if (i != -1) {
            this.mProgressBar.setMax(i);
        }
        int i2 = this.mTempProgress;
        if (i2 != -1) {
            this.mProgressBar.setProgress(i2);
        }
        int i3 = this.mTempMsgId;
        if (i3 != -1) {
            this.mMsgTextView.setText(i3);
        }
        String str = this.mTempMsg;
        if (str != null) {
            this.mMsgTextView.setText(str);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), 2131821025);
        dialog.setContentView(this.mView, new ViewGroup.LayoutParams(getActivity().getWindowManager().getDefaultDisplay().getWidth(), -2));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(80);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4;
            }
        });
        return dialog;
    }

    public void setCancleButtonEnable(boolean z) {
        ImageButton imageButton = this.mCancelImageButton;
        if (imageButton != null) {
            imageButton.setVisibility(z ? 0 : 4);
        }
    }

    public void setMax(int i) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setMax(i);
        } else {
            this.mTempMax = i;
        }
    }

    public void setMsg(int i) {
        TextView textView = this.mMsgTextView;
        if (textView != null) {
            textView.setText(i);
        } else {
            this.mTempMsgId = i;
        }
    }

    public void setProgress(int i) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setProgress(i);
        } else {
            this.mTempProgress = i;
        }
    }

    public void setProgressDialogInterface(ProgressDialogInterface progressDialogInterface) {
        this.mProgressDialogInterface = progressDialogInterface;
    }
}
