package com.miui.gallery.agreement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.agreement.core.Agreement;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.agreement.cta.CtaAgreement;
import com.miui.gallery.agreement.cta.NetworkAgreementFragment;
import com.miui.gallery.agreement.cta.SystemCtaAgreement;
import com.miui.gallery.agreement.cta.SystemNetworkAgreementInjector;
import com.miui.gallery.permission.R;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.util.BaseBuildUtil;
import com.miui.gallery.util.Log;
import java.util.ArrayList;

public class AgreementsUtils {
    private static ArrayList<Agreement> getAgreements(Context context) {
        ArrayList<Agreement> arrayList = new ArrayList<>();
        Agreement agreement = new Agreement(context.getResources().getString(R.string.user_agreement2), CtaAgreement.Licence.getUserAgreementUrl(), true);
        Agreement agreement2 = new Agreement(context.getResources().getString(R.string.user_agreement4), CtaAgreement.Licence.getPrivacyUrl(), true);
        arrayList.add(agreement);
        arrayList.add(agreement2);
        return arrayList;
    }

    public static boolean isKoreaRegion() {
        return "KR".equalsIgnoreCase(BaseBuildUtil.getRegion());
    }

    public static boolean isNetworkingAgreementAccepted() {
        if (!BaseBuildUtil.isInternational()) {
            return BaseGalleryPreferences.CTA.canConnectNetwork();
        }
        if (!isKoreaRegion()) {
            if (!BaseGalleryPreferences.CTA.canConnectNetwork()) {
                BaseGalleryPreferences.CTA.setCanConnectNetwork(true);
            }
            return true;
        } else if (!BaseGalleryPreferences.Agreement.isRequiredAgreementsAllowed()) {
            return BaseGalleryPreferences.CTA.canConnectNetwork();
        } else {
            if (!BaseGalleryPreferences.CTA.canConnectNetwork()) {
                BaseGalleryPreferences.CTA.setCanConnectNetwork(true);
            }
            return true;
        }
    }

    public static void showNetworkingAgreement(Activity activity, OnAgreementInvokedListener onAgreementInvokedListener) {
        if (SystemCtaAgreement.SUPPORT_SYSTEM_CTA.get(activity).booleanValue()) {
            new SystemNetworkAgreementInjector().invoke(activity, new OnNetworkInvokedWrapper(onAgreementInvokedListener));
        } else {
            new NetworkAgreementFragment().invoke(activity, new OnNetworkInvokedWrapper(onAgreementInvokedListener));
        }
    }

    public static void showUserAgreements(final Activity activity, final OnAgreementInvokedListener onAgreementInvokedListener) {
        if (isKoreaRegion()) {
            if (!BaseGalleryPreferences.Agreement.isRequiredAgreementsAllowed()) {
                AgreementDialogFragment.newInstance(getAgreements(activity)).invoke(activity, new OnAgreementInvokedListener() {
                    public void onAgreementInvoked(boolean z) {
                        BaseGalleryPreferences.Agreement.setRequiredAgreementsAllowed(z);
                        if (z) {
                            BaseGalleryPreferences.CTA.setCanConnectNetwork(true);
                        } else {
                            activity.finish();
                        }
                        OnAgreementInvokedListener onAgreementInvokedListener = onAgreementInvokedListener;
                        if (onAgreementInvokedListener != null) {
                            onAgreementInvokedListener.onAgreementInvoked(z);
                        }
                    }
                });
            } else if (onAgreementInvokedListener != null) {
                onAgreementInvokedListener.onAgreementInvoked(true);
            }
        } else if (!BaseBuildUtil.isInternational()) {
            showNetworkingAgreement(activity, onAgreementInvokedListener);
        } else {
            BaseGalleryPreferences.CTA.setCanConnectNetwork(true);
            if (onAgreementInvokedListener != null) {
                onAgreementInvokedListener.onAgreementInvoked(true);
            }
        }
    }

    public static void viewAgreement(Context context, Agreement agreement) {
        if (agreement == null || TextUtils.isEmpty(agreement.mLink)) {
            Log.w("AgreementsUtils", "agreement can't view");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(agreement.mLink));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("AgreementsUtils", (Throwable) e);
        }
    }
}
