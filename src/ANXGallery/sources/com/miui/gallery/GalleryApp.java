package com.miui.gallery;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.miui.core.SdkHelper;
import com.miui.gallery.Config;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.assistant.library.LibraryManager;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.receiver.CloudPrivacyAgreementDeniedReceiver;
import com.miui.gallery.cloud.syncstate.SyncMonitor;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.dao.GalleryLiteEntityManager;
import com.miui.gallery.data.CitySearcher;
import com.miui.gallery.data.OldThumbnailTransferer;
import com.miui.gallery.discovery.GalleryJobService;
import com.miui.gallery.editor.photo.app.EditorApplicationDelegate;
import com.miui.gallery.imageloader.GalleryImageLoaderCache;
import com.miui.gallery.modules.ModulesRegister;
import com.miui.gallery.monitor.LooperBlockDetector;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.preference.PreferenceHelper;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.push.GalleryPushManager;
import com.miui.gallery.receiver.TimeChangedReceiver;
import com.miui.gallery.sdk.uploadstatus.ItemType;
import com.miui.gallery.sdk.uploadstatus.SyncProxy;
import com.miui.gallery.sdk.uploadstatus.UploadStatusItem;
import com.miui.gallery.sdk.uploadstatus.UploadStatusProxy;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.OldCacheCleaner;
import com.miui.gallery.util.PrivacyAgreementUtils;
import com.miui.gallery.util.ProcessUtils;
import com.miui.gallery.util.ReceiverUtils;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deprecated.Preference;
import com.miui.gallery.util.logger.TimingTracing;
import com.miui.gallery.util.uil.CloudUriAdapter;
import com.miui.gallery.util.uil.MicroThumbCache;
import com.miui.gallery.util.uil.PhotoBytesCache;
import com.miui.gallery.util.uil.PhotoReusedBitCache;
import com.miui.gallery.video.editor.manager.SmartVideoGuideHelper;
import com.miui.os.Rom;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xiaomi.micloudsdk.request.utils.Request;
import miui.external.Application;
import miui.net.ConnectivityHelper;

public class GalleryApp extends Application {
    /* access modifiers changed from: private */
    public static final boolean BLOCK_MONITOR = (Rom.IS_ALPHA && Log.isLoggable("BLOCK_MONITOR", 3));
    /* access modifiers changed from: private */
    public static volatile Context sContext;
    private ImageLoader.Initializer mImageLoaderInitializer = new ImageLoader.Initializer() {
        /* access modifiers changed from: protected */
        public ImageLoaderConfiguration getConfig() {
            ImageLoaderConfiguration config = Config.ImageLoaderConfig.getConfig(GalleryApp.sContext);
            GalleryImageLoaderCache.getInstance().setMicroThumbCache(MicroThumbCache.getInstance());
            GalleryImageLoaderCache.getInstance().setPhotoBytesCache(PhotoBytesCache.getInstance());
            return config;
        }
    };

    public class ApplicationDelegate extends miui.external.ApplicationDelegate {
        public ApplicationDelegate() {
        }

        public void onCreate() {
            super.onCreate();
            GalleryApp.this.initDataInUIThread();
            GalleryApp.this.initDataInSubThread();
            if (GalleryApp.BLOCK_MONITOR) {
                GalleryApp.this.openBlockMonitor();
            }
        }
    }

    static {
        if (SdkHelper.IS_MIUI) {
            com.miui.gallery.util.Log.setLogLevel(Rom.IS_STABLE ? 7 : 2);
            TimingTracing.setEnabled(!Rom.IS_STABLE);
            return;
        }
        com.miui.gallery.util.Log.setLogLevel(6);
    }

    /* access modifiers changed from: private */
    public void doInitInSubThreadAtOnce() {
        PreferenceHelper.getPreferences();
        Preference.sGetDefaultPreferences();
        GalleryStatHelper.init(this);
        GalleryLiteEntityManager.getInstance().warmUp();
        com.miui.gallery.util.Log.d("HomePageStartupHelper", "warmUp");
        this.mImageLoaderInitializer.preInitialize();
        this.mImageLoaderInitializer = null;
        CloudControlManager.getInstance().init(this);
        registerTimeChangedReceiver(this);
        if (ImageFeatureManager.isImageFeatureCalculationEnable()) {
            ImageFeatureCacheManager.getInstance().init();
        }
        LibraryManager.getInstance().init(this);
        SyncMonitor.getInstance();
        SyncProxy.getInstance().init(sContext, new CloudUriAdapter());
        SyncProxy.getInstance().getUploadStatusProxy().addUploadStatusChangedListener(new UploadStatusProxy.UploadStatusChangedListener() {
            public void onUploadStatusChanged(UploadStatusItem uploadStatusItem) {
                if (uploadStatusItem != null && uploadStatusItem.getItemType() == ItemType.OWNER) {
                    Uri userUri = uploadStatusItem.getUserUri();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("sync_status", uploadStatusItem.mStatus.toString());
                    GalleryApp.sContext.getContentResolver().update(userUri, contentValues, "_id = ?", new String[]{userUri.getLastPathSegment()});
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void doInitInSubThreadDelay() {
        GalleryJobService.scheduleJobs(this);
        CloudUtils.checkAccount((Activity) null, true, (Runnable) null);
        CitySearcher.getInstance().preLoadData();
        if (PermissionUtils.checkPermission((Context) this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            OldThumbnailTransferer.transfer(sContext);
            OldCacheCleaner.clean();
        }
        PhotoReusedBitCache.getInstance().initFirstCacheBitmap();
        if (!GalleryPreferences.Sync.getEverRefillLocalGroupId()) {
            GalleryDBHelper.refillLocalGroupId(GalleryDBHelper.getInstance().getWritableDatabase(), true, false);
            GalleryPreferences.Sync.setEverRefillLocalGroupId();
        }
        if (!GalleryPreferences.Sync.getEverSyncedSystemAlbum()) {
            SyncUtil.requestSync(this, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(1).build());
        }
        GalleryPushManager.getInstance().registerPush(this);
        if (GalleryPreferences.CTA.canConnectNetwork()) {
            long currentTimeMillis = System.currentTimeMillis() - GalleryPreferences.CloudControl.getLastRequestSucceedTime();
            if (currentTimeMillis >= 259200000 && ConnectivityHelper.getInstance().isUnmeteredNetworkConnected()) {
                new CloudControlRequestHelper(this).execRequestSync();
            } else if (currentTimeMillis >= 604800000 && ConnectivityHelper.getInstance().isNetworkConnected()) {
                new CloudControlRequestHelper(this).execRequestSync();
            }
        }
        if (!PrivacyAgreementUtils.isCloudServiceAgreementEnable(this)) {
            CloudPrivacyAgreementDeniedReceiver.onCloudPrivacyAgreementDenied(this);
        }
        SmartVideoGuideHelper.init();
        IntentUtil.prepareVideoPlayerStatus();
    }

    /* access modifiers changed from: private */
    public void initDataInSubThread() {
        new Thread(new Runnable() {
            public void run() {
                GalleryApp.this.doInitInSubThreadAtOnce();
                Process.setThreadPriority(10);
                ThreadManager.sleepThread(500);
                GalleryApp.this.doInitInSubThreadDelay();
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void initDataInUIThread() {
        Request.init(sContext);
        ImageLoader.setInitializer(this.mImageLoaderInitializer);
        setupLeakCanary();
    }

    public static boolean isEditorProcess() {
        return "com.miui.gallery:photo_editor".equals(ProcessUtils.currentProcessName());
    }

    /* access modifiers changed from: private */
    public void openBlockMonitor() {
        LooperBlockDetector.start(Looper.getMainLooper(), 150);
    }

    private void registerTimeChangedReceiver(Context context) {
        ReceiverUtils.registerReceiver(context, new TimeChangedReceiver(), "android.intent.action.TIME_SET", "android.intent.action.DATE_CHANGED", "android.intent.action.TIMEZONE_CHANGED");
    }

    public static Context sGetAndroidContext() {
        return sContext;
    }

    private void setupLeakCanary() {
    }

    public miui.external.ApplicationDelegate onCreateApplicationDelegate() {
        sContext = this;
        StaticContext.init(sContext);
        ModulesRegister.register();
        return isEditorProcess() ? new EditorApplicationDelegate() : new ApplicationDelegate();
    }
}
