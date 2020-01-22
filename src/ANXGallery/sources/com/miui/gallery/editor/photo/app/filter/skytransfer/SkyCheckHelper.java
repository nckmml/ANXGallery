package com.miui.gallery.editor.photo.app.filter.skytransfer;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.ToastUtils;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class SkyCheckHelper {
    private static SkyCheckHelper sInstance = new SkyCheckHelper();
    private static boolean sIsSkyLibAvailable;
    private static boolean sIsSkyResAvailable;
    private static final String[] sSkyWhiteList = {"umi", "cmi", "dipper", "ursa", "jason", "wayne", "platina", "chiron", "sagit", "polaris", "perseus", "equuleus", "sirius", "comet", "lavender", "capricorn", "lithium", "natrium", "scorpio", "gemini", "cepheus", "davinci", "davinciin", "grus", "raphael", "raphaelin", "pyxis", "vela", "crux", "beryllium", "tucana", "violet", "phoenix", "phoenixin", "andromeda", "begonia", "begoniain", "draco"};
    private SkyDownloadStateListener mChildDownloadStateListener = new SkyDownloadStateListener() {
        private boolean isLibFinish;
        private boolean isResFinish;

        public void onDownloadStart(int i) {
            if (i == 1) {
                SkyCheckHelper.this.refreshDownloadStart();
                this.isLibFinish = false;
                return;
            }
            this.isResFinish = false;
        }

        public void onDownloading(int i, int i2) {
            if (i == 1) {
                SkyCheckHelper.this.refreshDownloading(i2);
            }
        }

        public void onFinish(int i, boolean z, int i2) {
            if (i == 2) {
                this.isResFinish = true;
            }
            if (i == 1) {
                this.isLibFinish = true;
            }
            if (this.isResFinish && this.isLibFinish) {
                SkyCheckHelper.this.refreshDownloadResult(z, i2);
            }
        }
    };
    private Set<SkyDownloadStateListener> mDownloadStateListeners = new CopyOnWriteArraySet();
    private boolean mIsDownloading;
    private SkyDownloadStateListener mStubStateListener = new SkyDownloadStateListener() {
        public void onDownloadStart(int i) {
            SkyCheckHelper.this.refreshDownloadStart();
        }

        public void onDownloading(int i, int i2) {
            SkyCheckHelper.this.refreshDownloading(i2);
        }

        public void onFinish(int i, boolean z, int i2) {
            SkyCheckHelper.this.refreshDownloadResult(z, i2);
        }
    };

    public static SkyCheckHelper getInstance() {
        return sInstance;
    }

    public static boolean isSkyAvailable() {
        if (!isSkyEnable()) {
            return false;
        }
        if (!sIsSkyLibAvailable) {
            sIsSkyLibAvailable = SkyLibraryLoaderHelper.getInstance().getLoaderState() == 0;
        }
        if (!sIsSkyResAvailable) {
            sIsSkyResAvailable = SkyResourceDownloadHelper.getInstance().isResourceDownloaded();
        }
        return sIsSkyLibAvailable && sIsSkyResAvailable;
    }

    public static boolean isSkyEnable() {
        if (GalleryApp.isEditorProcess()) {
            return false;
        }
        for (String equals : sSkyWhiteList) {
            if (equals.equals(Build.DEVICE)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void refreshDownloadResult(boolean z, int i) {
        this.mIsDownloading = false;
        for (SkyDownloadStateListener onFinish : this.mDownloadStateListeners) {
            onFinish.onFinish(0, z, i);
        }
        ToastUtils.makeText(StaticContext.sGetAndroidContext(), z ? R.string.filter_sky_download_finish : R.string.filter_sky_download_failed);
        Log.d("SkyCheckHelper", "refreshDownloadResult" + z);
    }

    /* access modifiers changed from: private */
    public void refreshDownloadStart() {
        for (SkyDownloadStateListener onDownloadStart : this.mDownloadStateListeners) {
            onDownloadStart.onDownloadStart(0);
        }
    }

    /* access modifiers changed from: private */
    public void refreshDownloading(int i) {
        for (SkyDownloadStateListener onDownloading : this.mDownloadStateListeners) {
            onDownloading.onDownloading(0, i);
        }
    }

    /* access modifiers changed from: private */
    public void startDownload(boolean z) {
        if (isSkyAvailable()) {
            refreshDownloadResult(true, 0);
            return;
        }
        this.mIsDownloading = true;
        refreshDownloading(0);
        if (!sIsSkyResAvailable && !sIsSkyLibAvailable) {
            SkyLibraryLoaderHelper.getInstance().setDownloadStateListener(this.mChildDownloadStateListener);
            SkyLibraryLoaderHelper.getInstance().startDownload(z);
            SkyResourceDownloadHelper.getInstance().setDownloadStateListener(this.mChildDownloadStateListener);
            SkyResourceDownloadHelper.getInstance().startDownload(z);
        } else if (!sIsSkyLibAvailable) {
            SkyLibraryLoaderHelper.getInstance().setDownloadStateListener(this.mStubStateListener);
            SkyLibraryLoaderHelper.getInstance().startDownload(z);
        } else if (!sIsSkyResAvailable) {
            SkyResourceDownloadHelper.getInstance().setDownloadStateListener(this.mStubStateListener);
            SkyResourceDownloadHelper.getInstance().startDownload(z);
        }
    }

    public void addDownloadStateListener(SkyDownloadStateListener skyDownloadStateListener) {
        if (skyDownloadStateListener != null) {
            this.mDownloadStateListeners.add(skyDownloadStateListener);
        }
    }

    public boolean checkAbleOrDownload(Activity activity) {
        if (!isSkyEnable()) {
            return false;
        }
        if (isSkyAvailable()) {
            return true;
        }
        startDownloadWithCheck(activity);
        return false;
    }

    public void removeDownloadStateListener(SkyDownloadStateListener skyDownloadStateListener) {
        if (skyDownloadStateListener != null) {
            this.mDownloadStateListeners.remove(skyDownloadStateListener);
        }
    }

    public void startDownloadWithCheck(Activity activity) {
        if (!this.mIsDownloading) {
            if (!NetworkUtils.isNetworkConnected()) {
                ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.filter_sky_download_failed);
                Log.d("SkyCheckHelper", "download sdk no network");
                refreshDownloadResult(false, -1);
            } else if (NetworkUtils.isActiveNetworkMetered()) {
                AnonymousClass1 r7 = new ConfirmDialog.ConfirmDialogInterface() {
                    public void onCancel(DialogFragment dialogFragment) {
                        SkyCheckHelper.this.refreshDownloadResult(false, -1);
                    }

                    public void onConfirm(DialogFragment dialogFragment) {
                        SkyCheckHelper.this.startDownload(true);
                    }
                };
                ConfirmDialog.showConfirmDialog(activity.getFragmentManager(), activity.getResources().getString(R.string.filter_sky_download_sdk_without_wifi_title), String.format(activity.getResources().getString(R.string.filter_sky_download_sdk_without_wifi_msg), new Object[]{22}), activity.getResources().getString(R.string.base_btn_cancel), activity.getResources().getString(R.string.base_btn_download), r7);
            } else {
                startDownload(false);
            }
        }
    }

    public void startDownloadWithWifi() {
        if (NetworkUtils.isNetworkConnected() && !NetworkUtils.isActiveNetworkMetered()) {
            startDownload(false);
        }
    }
}
