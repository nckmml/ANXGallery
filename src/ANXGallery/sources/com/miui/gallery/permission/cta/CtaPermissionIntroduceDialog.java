package com.miui.gallery.permission.cta;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import com.miui.gallery.agreement.cta.CtaAgreement;
import com.miui.gallery.permission.R;
import com.miui.gallery.permission.core.OnPermissionIntroduced;
import com.miui.gallery.util.Log;
import miui.app.AlertDialog;

public class CtaPermissionIntroduceDialog extends DialogFragment {
    /* access modifiers changed from: private */
    public OnPermissionIntroduced mIntroduceListener;
    private String mPermissionToRequest;

    public static CtaPermissionIntroduceDialog newInstance(String str, OnPermissionIntroduced onPermissionIntroduced) {
        CtaPermissionIntroduceDialog ctaPermissionIntroduceDialog = new CtaPermissionIntroduceDialog();
        Bundle bundle = new Bundle();
        bundle.putString("permission", str);
        ctaPermissionIntroduceDialog.setArguments(bundle);
        ctaPermissionIntroduceDialog.setOnIntroducedListener(onPermissionIntroduced);
        return ctaPermissionIntroduceDialog;
    }

    /* access modifiers changed from: protected */
    public SpannableStringBuilder getMessage() {
        String string;
        Object[] objArr;
        PackageManager packageManager = getActivity().getPackageManager();
        String str = this.mPermissionToRequest;
        try {
            CharSequence loadLabel = packageManager.getPermissionInfo(str, 128).loadLabel(packageManager);
            if (loadLabel != null) {
                str = loadLabel.toString();
            }
            string = getString(R.string.grant_permission_item);
            objArr = new Object[]{str};
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("CtaPrivacyPermissionRequestDialog", "Get permission info failed, %s", str);
            string = getString(R.string.grant_permission_item);
            objArr = new Object[]{str};
        } catch (Throwable th) {
            String.format(getString(R.string.grant_permission_item), new Object[]{str});
            throw th;
        }
        String format = String.format(string, objArr);
        String string2 = getString(R.string.user_agreement2);
        String string3 = getString(R.string.user_agreement4);
        return CtaAgreement.buildUserNotice(getActivity(), getString(R.string.privacy_permission_request_message, new Object[]{string2, string3, format}), string2, string3);
    }

    /* access modifiers changed from: protected */
    public DialogInterface.OnClickListener getNegativeListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (CtaPermissionIntroduceDialog.this.mIntroduceListener != null) {
                    CtaPermissionIntroduceDialog.this.mIntroduceListener.onPermissionIntroduced(false);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getNegativeText() {
        return getString(R.string.privacy_permission_request_negative);
    }

    /* access modifiers changed from: protected */
    public DialogInterface.OnClickListener getPositiveListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (CtaPermissionIntroduceDialog.this.mIntroduceListener != null) {
                    CtaPermissionIntroduceDialog.this.mIntroduceListener.onPermissionIntroduced(true);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getPositiveText() {
        return getString(R.string.privacy_permission_request_positive);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.privacy_permission_request_title);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
        this.mPermissionToRequest = getArguments().getString("permission");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(getTitle()).setMessage(getMessage()).setPositiveButton(getPositiveText(), getPositiveListener()).setNegativeButton(getNegativeText(), getNegativeListener()).create();
    }

    public void onStart() {
        super.onStart();
        getDialog().getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: package-private */
    public void setOnIntroducedListener(OnPermissionIntroduced onPermissionIntroduced) {
        this.mIntroduceListener = onPermissionIntroduced;
    }
}
