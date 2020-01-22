package com.miui.gallery.editor.photo.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.widget.GalleryDialogFragment;

public class AlertDialogFragment extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public Callbacks mCallbacks;
    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (dialogInterface != AlertDialogFragment.this.getDialog()) {
                throw new IllegalStateException("click event is not from current fragment, maybe listener not detached");
            } else if (AlertDialogFragment.this.mCallbacks != null) {
                AlertDialogFragment.this.mCallbacks.onClick(AlertDialogFragment.this, i);
            }
        }
    };

    public static class Builder {
        private boolean mCancellable;
        private int mMessageId;
        private int mNegativeId;
        private int mPositiveId;

        public AlertDialogFragment build() {
            Bundle bundle = new Bundle();
            int i = this.mMessageId;
            if (i != 0) {
                bundle.putInt("AlertDialogFragment:message_resource", i);
            }
            int i2 = this.mPositiveId;
            if (i2 != 0) {
                bundle.putInt("AlertDialogFragment:positive_resource", i2);
            }
            int i3 = this.mNegativeId;
            if (i3 != 0) {
                bundle.putInt("AlertDialogFragment:negative_resource", i3);
            }
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.setCancelable(this.mCancellable);
            alertDialogFragment.setArguments(bundle);
            return alertDialogFragment;
        }

        public Builder setCancellable(boolean z) {
            this.mCancellable = z;
            return this;
        }

        public Builder setMessage(int i) {
            this.mMessageId = i;
            return this;
        }

        public Builder setNegativeButton(int i) {
            this.mNegativeId = i;
            return this;
        }

        public Builder setPositiveButton(int i) {
            this.mPositiveId = i;
            return this;
        }
    }

    public interface Callbacks {
        void onCancel(AlertDialogFragment alertDialogFragment);

        void onClick(AlertDialogFragment alertDialogFragment, int i);

        void onDismiss(AlertDialogFragment alertDialogFragment);
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Callbacks callbacks = this.mCallbacks;
        if (callbacks != null) {
            callbacks.onCancel(this);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle arguments = getArguments();
        int i = arguments.getInt("AlertDialogFragment:message_resource");
        if (i != 0) {
            builder.setMessage(i);
        }
        int i2 = arguments.getInt("AlertDialogFragment:negative_resource");
        if (i2 != 0) {
            builder.setNegativeButton(i2, this.mOnClickListener);
        }
        int i3 = arguments.getInt("AlertDialogFragment:positive_resource");
        if (i3 != 0) {
            builder.setPositiveButton(i3, this.mOnClickListener);
        }
        return builder.create();
    }

    public void onDetach() {
        super.onDetach();
        this.mCallbacks = null;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        Callbacks callbacks = this.mCallbacks;
        if (callbacks != null) {
            callbacks.onDismiss(this);
        }
    }

    public final void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }
}
