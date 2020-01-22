package com.miui.gallery.agreement.cta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.permission.R;

public class SystemNetworkAgreementInjector extends Fragment {
    private OnAgreementInvokedListener mListener;

    public void invoke(Activity activity, OnAgreementInvokedListener onAgreementInvokedListener) {
        this.mListener = onAgreementInvokedListener;
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("SystemNetworkAgreementInjector") == null) {
            fragmentManager.beginTransaction().add(this, "SystemNetworkAgreementInjector").commit();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 111) {
            OnAgreementInvokedListener onAgreementInvokedListener = this.mListener;
            if (onAgreementInvokedListener != null) {
                boolean z = true;
                if (i2 != 1) {
                    z = false;
                }
                onAgreementInvokedListener.onAgreementInvoked(z);
            }
            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent("miui.intent.action.SYSTEM_PERMISSION_DECLARE");
        intent.setPackage("com.miui.securitycenter");
        intent.putExtra("main_purpose", getString(R.string.app_summary));
        intent.putExtra("use_network", true);
        intent.putExtra("mandatory_permission", false);
        startActivityForResult(intent, 111);
    }
}
