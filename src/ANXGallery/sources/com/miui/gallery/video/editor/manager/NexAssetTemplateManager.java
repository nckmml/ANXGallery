package com.miui.gallery.video.editor.manager;

import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.Log;
import com.miui.gallery.video.editor.config.VideoEditorConfig;
import com.miui.gallery.video.editor.util.FileHelper;
import com.nexstreaming.nexeditorsdk.nexApplicationConfig;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import com.nexstreaming.nexeditorsdk.nexColorEffect;
import com.nexstreaming.nexeditorsdk.nexOverlayPreset;
import com.nexstreaming.nexeditorsdk.nexTemplateManager;
import java.util.List;

public class NexAssetTemplateManager {
    /* access modifiers changed from: private */
    public static String TAG = "NexAssetTemplateManager";
    private static String assetInstallRootPath;
    private static String assetStoreSubDir;

    public interface ICheckExpiredAssetListener {
        void onFinished(List<nexTemplateManager.Template> list);
    }

    public interface ILoadAssetTemplate {
        void onFail();

        void onSuccess();
    }

    public interface ILoadWaterMarkListener {
        void onFinished(String[] strArr);
    }

    private static class NexTemplateManagerHolder {
        /* access modifiers changed from: private */
        public static final NexAssetTemplateManager INSTANCE = new NexAssetTemplateManager();
    }

    private NexAssetTemplateManager() {
    }

    public static NexAssetTemplateManager getInstance() {
        return NexTemplateManagerHolder.INSTANCE;
    }

    public static nexTemplateManager getKmTemplateManager() {
        return nexTemplateManager.getTemplateManager(GalleryApp.sGetAndroidContext(), GalleryApp.sGetAndroidContext());
    }

    private void initPath() {
        assetStoreSubDir = VideoEditorConfig.ASSET_STORE_PATH;
        assetInstallRootPath = VideoEditorConfig.ASSET_INSTALL_ROOT_PATH;
        if (FileHelper.createDir(assetStoreSubDir)) {
            nexApplicationConfig.setAssetStoreRootPath(assetStoreSubDir);
            nexApplicationConfig.setAssetInstallRootPath(assetInstallRootPath);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = getKmTemplateManager();
     */
    public void checkExpiredAsset(final ICheckExpiredAssetListener iCheckExpiredAssetListener) {
        final nexTemplateManager kmTemplateManager;
        if (iCheckExpiredAssetListener != null && kmTemplateManager != null) {
            kmTemplateManager.loadTemplate((Runnable) new Runnable() {
                public void run() {
                    List<nexTemplateManager.Template> templates = kmTemplateManager.getTemplates();
                    boolean z = false;
                    for (nexTemplateManager.Template next : templates) {
                        if (next != null) {
                            nexAssetPackageManager.getAssetPackageManager(GalleryApp.sGetAndroidContext());
                            if (nexAssetPackageManager.checkExpireAsset(next.packageInfo())) {
                                NexAssetTemplateManager.this.uninstallPackageById(next.id());
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        NexAssetTemplateManager.getKmTemplateManager().loadTemplate();
                        iCheckExpiredAssetListener.onFinished(kmTemplateManager.getTemplates());
                        return;
                    }
                    iCheckExpiredAssetListener.onFinished(templates);
                }
            });
        }
    }

    public void installProcess(int i, final ILoadAssetTemplate iLoadAssetTemplate) {
        if (iLoadAssetTemplate != null) {
            initPath();
            nexAssetPackageManager.getAssetPackageManager(GalleryApp.sGetAndroidContext()).installPackagesAsync(i, new nexAssetPackageManager.OnInstallPackageListener() {
                public void onCompleted(int i, int i2) {
                    if (i == -1) {
                        iLoadAssetTemplate.onFail();
                        Log.d(NexAssetTemplateManager.TAG, "Install a new  asset package to NexEditorSDK Fail! ");
                        return;
                    }
                    iLoadAssetTemplate.onSuccess();
                    nexColorEffect.updatePluginLut();
                    Log.d(NexAssetTemplateManager.TAG, "Install a new asset package to NexEditorSDK Success! ");
                }

                public void onProgress(int i, int i2, int i3) {
                }
            });
        }
    }

    public List<nexTemplateManager.Template> loadSmartEffectTemplateList() {
        initPath();
        nexTemplateManager kmTemplateManager = getKmTemplateManager();
        if (kmTemplateManager == null) {
            return null;
        }
        kmTemplateManager.loadTemplate();
        return kmTemplateManager.getTemplates();
    }

    public void loadWaterMarkTemplateList(ILoadWaterMarkListener iLoadWaterMarkListener) {
        if (iLoadWaterMarkListener != null) {
            initPath();
            iLoadWaterMarkListener.onFinished(nexOverlayPreset.getOverlayPreset(GalleryApp.sGetAndroidContext()).getIDs());
        }
    }

    public void uninstallPackageById(String str) {
        if (!TextUtils.isEmpty(str)) {
            nexAssetPackageManager.getAssetPackageManager(GalleryApp.sGetAndroidContext()).uninstallPackageById(str);
        }
    }
}
