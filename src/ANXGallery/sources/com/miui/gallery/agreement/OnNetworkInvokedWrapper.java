package com.miui.gallery.agreement;

import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.preference.BaseGalleryPreferences;

class OnNetworkInvokedWrapper implements OnAgreementInvokedListener {
    private final OnAgreementInvokedListener mWrapped;

    OnNetworkInvokedWrapper(OnAgreementInvokedListener onAgreementInvokedListener) {
        this.mWrapped = onAgreementInvokedListener;
    }

    public void onAgreementInvoked(boolean z) {
        if (z) {
            BaseGalleryPreferences.CTA.setCanConnectNetwork(true);
        } else {
            BaseGalleryPreferences.CTA.setToAllowUseOnOfflineGlobal(true);
        }
        OnAgreementInvokedListener onAgreementInvokedListener = this.mWrapped;
        if (onAgreementInvokedListener != null) {
            onAgreementInvokedListener.onAgreementInvoked(z);
        }
    }
}
