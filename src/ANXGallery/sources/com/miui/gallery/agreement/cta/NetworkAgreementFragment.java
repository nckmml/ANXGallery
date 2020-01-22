package com.miui.gallery.agreement.cta;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.permission.R;
import miui.app.AlertDialog;

public class NetworkAgreementFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public OnAgreementInvokedListener mListener;

    /* access modifiers changed from: protected */
    public SpannableStringBuilder getMessage() {
        return CtaAgreement.buildUserNotice(getActivity(), R.string.user_notice_identify_summary_format);
    }

    /* access modifiers changed from: protected */
    public DialogInterface.OnClickListener getNegativeListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (NetworkAgreementFragment.this.mListener != null) {
                    NetworkAgreementFragment.this.mListener.onAgreementInvoked(false);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getNegativeText() {
        return getString(17039360);
    }

    /* access modifiers changed from: protected */
    public DialogInterface.OnClickListener getPositiveListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (NetworkAgreementFragment.this.mListener != null) {
                    NetworkAgreementFragment.this.mListener.onAgreementInvoked(true);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getPositiveText() {
        return getString(R.string.user_agree);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.user_notice_title);
    }

    public void invoke(Activity activity, OnAgreementInvokedListener onAgreementInvokedListener) {
        Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag("NetworkAgreementFragment");
        if (!(findFragmentByTag == null || findFragmentByTag == this || !(findFragmentByTag instanceof NetworkAgreementFragment))) {
            ((DialogFragment) findFragmentByTag).dismiss();
        }
        this.mListener = onAgreementInvokedListener;
        show(activity.getFragmentManager(), "NetworkAgreementFragment");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(getTitle()).setMessage(getMessage()).setPositiveButton(getPositiveText(), getPositiveListener()).setNegativeButton(getNegativeText(), getNegativeListener()).create();
    }

    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
