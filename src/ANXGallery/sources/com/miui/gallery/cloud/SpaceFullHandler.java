package com.miui.gallery.cloud;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.Utils;
import com.miui.gallery.util.deprecated.Preference;
import java.util.HashMap;
import miui.cloud.sync.MiCloudStatusInfo;

public class SpaceFullHandler {
    private static OwnerSpaceFullListener sOwnerSpaceFullListener;
    private static SharerSpaceFullListener sSharerSpaceFullListener;
    static HashMap<String, Boolean> sSpaceFullMap = new HashMap<>();

    static class OwnerSpaceFullListener implements SpaceFullListener {
        OwnerSpaceFullListener() {
        }

        public void handleSpaceFullError(RequestCloudItem requestCloudItem) {
            SpaceFullHandler.setOwnerSpaceFull();
        }

        public void handleSpaceNotFull(RequestCloudItem requestCloudItem) {
            SpaceFullHandler.removeOwnerSpaceFull();
        }

        public boolean isSpaceFull(RequestCloudItem requestCloudItem) {
            return SpaceFullHandler.isOwnerSpaceFull();
        }
    }

    static class SharerSpaceFullListener implements SpaceFullListener {
        SharerSpaceFullListener() {
        }

        public void handleSpaceFullError(RequestCloudItem requestCloudItem) {
            SpaceFullHandler.setSharerSpaceFull(requestCloudItem.dbCloud.getAlbumId());
        }

        public void handleSpaceNotFull(RequestCloudItem requestCloudItem) {
            SpaceFullHandler.removeSharerSpaceFull(requestCloudItem.dbCloud.getAlbumId());
        }

        public boolean isSpaceFull(RequestCloudItem requestCloudItem) {
            return SpaceFullHandler.isSharerSpaceFull(requestCloudItem.dbCloud.getAlbumId());
        }
    }

    public interface SpaceFullListener {
        void handleSpaceFullError(RequestCloudItem requestCloudItem);

        void handleSpaceNotFull(RequestCloudItem requestCloudItem);

        boolean isSpaceFull(RequestCloudItem requestCloudItem);
    }

    public static OwnerSpaceFullListener getOwnerSpaceFullListener() {
        if (sOwnerSpaceFullListener == null) {
            sOwnerSpaceFullListener = new OwnerSpaceFullListener();
        }
        return sOwnerSpaceFullListener;
    }

    public static SharerSpaceFullListener getSharerSpaceFullListener() {
        if (sSharerSpaceFullListener == null) {
            sSharerSpaceFullListener = new SharerSpaceFullListener();
        }
        return sSharerSpaceFullListener;
    }

    private static void handleSpaceFull(Context context, Account account) {
        try {
            String userData = ((AccountManager) context.getSystemService("account")).getUserData(account, "extra_micloud_status_info_quota");
            if (!TextUtils.isEmpty(userData)) {
                MiCloudStatusInfo miCloudStatusInfo = new MiCloudStatusInfo(account.name);
                miCloudStatusInfo.parseQuotaString(userData);
                MiCloudStatusInfo.QuotaInfo quotaInfo = miCloudStatusInfo.getQuotaInfo();
                if (quotaInfo != null) {
                    if (quotaInfo.getTotal() - quotaInfo.getUsed() >= 20971520) {
                        removeOwnerSpaceFull();
                        Log.d("SpaceFullHandler", "removeOwnerSpaceFull");
                    }
                    Log.i("SpaceFullHandler", "Quota info, yearPkgType: %s, total: %d, used: %d", quotaInfo.getYearlyPackageType(), Long.valueOf(quotaInfo.getTotal()), Long.valueOf(quotaInfo.getUsed()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void handleSpaceFullIfNeeded(Context context) {
        synchronized (SpaceFullHandler.class) {
            if (isOwnerSpaceFull()) {
                Account xiaomiAccount = AccountHelper.getXiaomiAccount(context);
                if (xiaomiAccount != null) {
                    handleSpaceFull(context, xiaomiAccount);
                }
            }
        }
    }

    public static synchronized boolean isOwnerSpaceFull() {
        boolean sGetCloudGallerySpaceFull;
        synchronized (SpaceFullHandler.class) {
            sGetCloudGallerySpaceFull = Preference.sGetCloudGallerySpaceFull();
        }
        return sGetCloudGallerySpaceFull;
    }

    public static synchronized boolean isSharerSpaceFull(String str) {
        synchronized (SpaceFullHandler.class) {
            Boolean bool = sSpaceFullMap.get(str);
            if (bool == null) {
                return false;
            }
            boolean booleanValue = bool.booleanValue();
            return booleanValue;
        }
    }

    public static synchronized void removeOwnerSpaceFull() {
        synchronized (SpaceFullHandler.class) {
            Preference.sSetCloudGallerySpaceFull(false);
            setMiCloudStatusInfoReceiverEnabledSetting(false);
        }
    }

    public static synchronized void removeSharerSpaceFull(String str) {
        synchronized (SpaceFullHandler.class) {
            Log.d("SpaceFullHandler", "%s, remove shareAlbum: %s space full.", new Throwable().getStackTrace()[1].getClassName(), Utils.desensitizeShareAlbumId(str));
            sSpaceFullMap.put(str, false);
        }
    }

    private static void setMiCloudStatusInfoReceiverEnabledSetting(boolean z) {
        PackageManager packageManager = GalleryApp.sGetAndroidContext().getPackageManager();
        ComponentName componentName = new ComponentName(GalleryApp.sGetAndroidContext(), MiCloudStatusInfoReceiver.class);
        int i = z ? 1 : 2;
        if (i != packageManager.getComponentEnabledSetting(componentName)) {
            packageManager.setComponentEnabledSetting(componentName, i, 1);
        }
    }

    public static synchronized void setOwnerSpaceFull() {
        synchronized (SpaceFullHandler.class) {
            String className = new Throwable().getStackTrace()[1].getClassName();
            Log.d("SpaceFullHandler", className + " set owner space full.");
            Preference.sSetCloudGallerySpaceFull(true);
            setMiCloudStatusInfoReceiverEnabledSetting(true);
        }
    }

    public static synchronized void setSharerSpaceFull(String str) {
        synchronized (SpaceFullHandler.class) {
            Log.d("SpaceFullHandler", "%s, set shareAlbum: %s space full.", new Throwable().getStackTrace()[1].getClassName(), Utils.desensitizeShareAlbumId(str));
            sSpaceFullMap.put(str, true);
        }
    }
}
