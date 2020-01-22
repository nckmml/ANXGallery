package com.miui.gallery.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.discovery.PrintSilentInstallTask;
import com.miui.gallery.picker.uri.OriginUrlRequestor;
import com.miui.gallery.preference.GalleryPreferences;
import com.xiaomi.market.IAppDownloadManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

public class PrintInstallHelper {
    private static PrintInstallHelper sInstance;
    /* access modifiers changed from: private */
    public int mCurSilentInstallTimes = 0;
    /* access modifiers changed from: private */
    public IAppDownloadManager mDownloadManager;
    private ServiceConnection mInstallConnection;
    private BroadcastReceiver mInstallReceiver;
    /* access modifiers changed from: private */
    public int mInstallState = 0;
    private CopyOnWriteArraySet<InstallStateListener> mInstallStateListeners = new CopyOnWriteArraySet<>();
    private long mLastStartTime = 0;
    /* access modifiers changed from: private */
    public String mPackageName;
    private int mStartCount = 0;

    private class InstallReceiver extends BroadcastReceiver {
        private InstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getStringExtra("packageName"), PrintInstallHelper.this.mPackageName)) {
                int intExtra = intent.getIntExtra("errorCode", 0);
                int intExtra2 = intent.getIntExtra("status", 0);
                Log.d("PrintInstallHelper", "install error code: %d, download status: %d", Integer.valueOf(intExtra), Integer.valueOf(intExtra2));
                if (PrintInstallHelper.this.isInstallExists(intExtra)) {
                    PrintInstallHelper.this.resumeInstall();
                    return;
                }
                boolean z = true;
                if (PrintInstallHelper.this.isInstallFinish(intExtra)) {
                    int intExtra3 = intent.getIntExtra("reason", 0);
                    if (PrintInstallHelper.this.isInstallSuccess(intExtra)) {
                        Log.d("PrintInstallHelper", "print install success, silent install times : %d", (Object) Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes));
                        HashMap hashMap = new HashMap();
                        hashMap.put("silentInstallTimes", Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes));
                        GallerySamplingStatHelper.recordCountEvent("photo_print", "photo_print_package_install_success", hashMap);
                    } else {
                        Log.w("PrintInstallHelper", "print install fail, error code : %d, reason : %d, silent install times : %d", Integer.valueOf(intExtra), Integer.valueOf(intExtra3), Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes));
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(String.format(Locale.US, "%d_errorCode", new Object[]{Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes)}), Integer.valueOf(intExtra));
                        hashMap2.put(String.format(Locale.US, "%d_failReason", new Object[]{Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes)}), Integer.valueOf(intExtra3));
                        GallerySamplingStatHelper.recordCountEvent("photo_print", "photo_print_package_install_fail", hashMap2);
                        z = false;
                    }
                    int unused = PrintInstallHelper.this.mInstallState = 0;
                    PrintInstallHelper.this.notifyInstallFinish(z, intExtra, intExtra3);
                    PrintInstallHelper.this.unregisterInstallReceiver();
                } else if (intExtra2 == -3) {
                    int unused2 = PrintInstallHelper.this.mInstallState = 2;
                } else {
                    int unused3 = PrintInstallHelper.this.mInstallState = 1;
                }
            }
        }
    }

    public interface InstallStateListener {
        void onFinish(boolean z, int i, int i2);

        void onInstallLimited();

        void onInstalling();
    }

    private PrintInstallHelper() {
    }

    public static int getFailReasonMsg(int i, int i2) {
        if (i == -6) {
            return 0;
        }
        return i2 != 10 ? (i2 == 11 || i2 == 16) ? R.string.error_install_storage_not_enough : i2 != 28 ? R.string.install_fail : R.string.error_install_connect_fail : R.string.error_install_sdcard_not_available;
    }

    public static synchronized PrintInstallHelper getInstance() {
        PrintInstallHelper printInstallHelper;
        synchronized (PrintInstallHelper.class) {
            if (sInstance == null) {
                sInstance = new PrintInstallHelper();
            }
            printInstallHelper = sInstance;
        }
        return printInstallHelper;
    }

    /* access modifiers changed from: private */
    public boolean isInstallExists(int i) {
        return i == -1;
    }

    /* access modifiers changed from: private */
    public boolean isInstallFinish(int i) {
        return i == 4 || i == -2 || i == -3 || i == -4 || i == -5 || i == -6;
    }

    /* access modifiers changed from: private */
    public boolean isInstallSuccess(int i) {
        return i == 4;
    }

    /* access modifiers changed from: private */
    public void registerInstallReceiver() {
        if (this.mInstallReceiver == null) {
            Log.d("PrintInstallHelper", "register install receiver");
            this.mInstallReceiver = new InstallReceiver();
            GalleryApp.sGetAndroidContext().registerReceiver(this.mInstallReceiver, new IntentFilter("com.xiaomi.market.DOWNLOAD_INSTALL_RESULT"));
        }
    }

    /* access modifiers changed from: private */
    public void resumeInstall() {
        IAppDownloadManager iAppDownloadManager = this.mDownloadManager;
        if (iAppDownloadManager != null) {
            try {
                boolean resume = iAppDownloadManager.resume(this.mPackageName, "com.miui.gallery");
                Log.d("PrintInstallHelper", "resume install:" + resume);
            } catch (RemoteException e) {
                Log.e("PrintInstallHelper", "RemoteException when resume", (Object) e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void unbindInstallService() {
        if (this.mInstallConnection != null) {
            GalleryApp.sGetAndroidContext().unbindService(this.mInstallConnection);
            this.mInstallConnection = null;
        }
    }

    /* access modifiers changed from: private */
    public void unregisterInstallReceiver() {
        if (this.mInstallReceiver != null) {
            Log.d("PrintInstallHelper", "unregister install receiver");
            GalleryApp.sGetAndroidContext().unregisterReceiver(this.mInstallReceiver);
            this.mInstallReceiver = null;
        }
    }

    public void addInstallStateListener(InstallStateListener installStateListener) {
        if (installStateListener != null) {
            this.mInstallStateListeners.add(installStateListener);
        }
    }

    public boolean ensurePrintFucntionAvailable() {
        String printPackageName = CloudControlStrategyHelper.getPrintPackageName();
        if (TextUtils.isEmpty(printPackageName)) {
            Log.e("PrintInstallHelper", "get package from cloud control failed");
            return false;
        } else if (MiscUtil.getAppVersionCode(printPackageName) < CloudControlStrategyHelper.getCreationStrategy().getMinPrintVersionCode()) {
            installPrintPackage(false, printPackageName);
            PrintSilentInstallTask.setSilentInstallTimesDisable();
            Log.e("PrintInstallHelper", "Current print app version is lower then needed, upgrading!");
            return false;
        } else {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(CloudControlStrategyHelper.getCreationStrategy().getPrintIntentUri()));
            intent.putExtra("prodType", String.valueOf(2));
            intent.setFlags(1);
            if (MiscUtil.isIntentSupported(intent)) {
                return true;
            }
            installPrintPackage(false, printPackageName);
            PrintSilentInstallTask.setSilentInstallTimesDisable();
            return false;
        }
    }

    public boolean installPrintPackage(boolean z, String str) {
        return installPrintPackage(z, str, (InstallStateListener) null);
    }

    public boolean installPrintPackage(final boolean z, final String str, InstallStateListener installStateListener) {
        this.mPackageName = str;
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        int i = this.mInstallState;
        boolean z2 = false;
        boolean z3 = true;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    Log.e("PrintInstallHelper", "wrong install state");
                    return false;
                } else if (z) {
                    return false;
                } else {
                    resumeInstall();
                }
            } else if (z) {
                return false;
            }
            z3 = false;
        } else {
            this.mCurSilentInstallTimes = z ? GalleryPreferences.PhotoPrint.getSilentInstallTimes() : 0;
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastStartTime < 60000) {
                this.mStartCount++;
            } else {
                this.mLastStartTime = currentTimeMillis;
                this.mStartCount = 1;
            }
            if (this.mStartCount > 10) {
                Log.d("PrintInstallHelper", "limit install, start times:" + this.mStartCount);
                z2 = true;
            } else {
                this.mInstallState = 1;
                Intent intent = new Intent("com.xiaomi.market.service.AppDownloadService");
                intent.setPackage("com.xiaomi.market");
                this.mInstallConnection = new ServiceConnection() {
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        try {
                            IAppDownloadManager unused = PrintInstallHelper.this.mDownloadManager = IAppDownloadManager.Stub.asInterface(iBinder);
                            Bundle bundle = new Bundle();
                            bundle.putString("packageName", str);
                            bundle.putString("ref", "MiuiGallery");
                            bundle.putString("senderPackageName", "com.miui.gallery");
                            if (!z) {
                                bundle.putBoolean("show_cta", true);
                            }
                            PrintInstallHelper.this.registerInstallReceiver();
                            PrintInstallHelper.this.mDownloadManager.download(bundle);
                            HashMap hashMap = new HashMap();
                            hashMap.put("silentInstallTimes", Integer.valueOf(PrintInstallHelper.this.mCurSilentInstallTimes));
                            GallerySamplingStatHelper.recordCountEvent("photo_print", "photo_print_package_download", hashMap);
                        } catch (RemoteException e) {
                            Log.e("PrintInstallHelper", (Throwable) e);
                            PrintInstallHelper.this.unregisterInstallReceiver();
                        } catch (Throwable th) {
                            PrintInstallHelper.this.unbindInstallService();
                            throw th;
                        }
                        PrintInstallHelper.this.unbindInstallService();
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                };
                try {
                    sGetAndroidContext.bindService(intent, this.mInstallConnection, 1);
                } catch (Exception e) {
                    Log.e("PrintInstallHelper", "bind install service failed", (Object) e);
                }
            }
        }
        if (installStateListener != null) {
            this.mInstallStateListeners.add(installStateListener);
        }
        if (z2) {
            notifyInstallLimited();
        } else {
            notifyInstalling();
        }
        return z3;
    }

    public boolean isPhotoPrintEnable() {
        return CloudControlManager.getInstance().queryFeatureStatus("photo-print").equals(FeatureProfile.Status.ENABLE) && !BuildUtil.isInternational() && !BuildUtil.isPad();
    }

    public boolean isPrintInstalling() {
        return this.mInstallState == 1;
    }

    public void notifyInstallFinish(boolean z, int i, int i2) {
        if (!this.mInstallStateListeners.isEmpty()) {
            Iterator<InstallStateListener> it = this.mInstallStateListeners.iterator();
            while (it.hasNext()) {
                it.next().onFinish(z, i, i2);
            }
        }
    }

    public void notifyInstallLimited() {
        if (!this.mInstallStateListeners.isEmpty()) {
            Iterator<InstallStateListener> it = this.mInstallStateListeners.iterator();
            while (it.hasNext()) {
                it.next().onInstallLimited();
            }
        }
    }

    public void notifyInstalling() {
        if (!this.mInstallStateListeners.isEmpty()) {
            Iterator<InstallStateListener> it = this.mInstallStateListeners.iterator();
            while (it.hasNext()) {
                it.next().onInstalling();
            }
        }
    }

    public void printPhotos(Activity activity, Uri[] uriArr, List<OriginUrlRequestor.OriginInfo> list) {
        if (ensurePrintFucntionAvailable()) {
            String printPackageName = CloudControlStrategyHelper.getPrintPackageName();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(CloudControlStrategyHelper.getCreationStrategy().getPrintIntentUri()));
            intent.putExtra("prodType", String.valueOf(2));
            if (MiscUtil.isValid(list)) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(list.get(i).toJson());
                }
                intent.putExtra("pick-result-origin-download-info", arrayList);
                intent.putExtra("pick-result-data", new ArrayList(Arrays.asList(uriArr)));
                intent.setFlags(1);
                if (MiscUtil.isIntentSupported(intent)) {
                    try {
                        activity.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Log.e("PrintInstallHelper", "Select images to print failed");
                    }
                } else {
                    installPrintPackage(false, printPackageName);
                    PrintSilentInstallTask.setSilentInstallTimesDisable();
                }
            } else {
                Log.e("PrintInstallHelper", "No OriginInfo for print");
            }
        }
    }

    public void removeInstallStateListener(InstallStateListener installStateListener) {
        if (installStateListener != null) {
            this.mInstallStateListeners.remove(installStateListener);
        }
    }

    public void start(Context context) {
        boolean z;
        if (context != null) {
            String printPackageName = CloudControlStrategyHelper.getPrintPackageName();
            if (TextUtils.isEmpty(printPackageName)) {
                Log.e("PrintInstallHelper", "get package from cloud control failed");
                return;
            }
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setPackage(printPackageName);
            if (MiscUtil.isIntentSupported(intent)) {
                try {
                    context.startActivity(intent);
                    GallerySamplingStatHelper.recordCountEvent("photo_print", "photo_print_app_start_success");
                } catch (ActivityNotFoundException unused) {
                    Log.e("PrintInstallHelper", "find print activity failed");
                }
                z = true;
            } else {
                installPrintPackage(false, printPackageName);
                PrintSilentInstallTask.setSilentInstallTimesDisable();
                z = false;
            }
            if (GalleryPreferences.PhotoPrint.isPrintFirstClicked()) {
                GalleryPreferences.PhotoPrint.setIsPrintFirstClicked(false);
                HashMap hashMap = new HashMap();
                hashMap.put(String.format(Locale.US, "isInstalled_%s", new Object[]{Boolean.valueOf(z)}), Integer.valueOf(GalleryPreferences.PhotoPrint.getSilentInstallTimes()));
                GallerySamplingStatHelper.recordCountEvent("photo_print", "photo_print_first_clicked", hashMap);
            }
        }
    }
}
