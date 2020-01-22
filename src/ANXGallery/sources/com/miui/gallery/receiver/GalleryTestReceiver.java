package com.miui.gallery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.settingssync.GallerySettingsSyncHelper;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.nexstreaming.nexeditorsdk.nexExportFormat;

public class GalleryTestReceiver extends BroadcastReceiver {
    public void onReceive(Context context, final Intent intent) {
        if ("com.miui.gallery.action.REQUEST_CLOUD_CONTROL_DATA".equals(intent.getAction())) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
                public Object run(ThreadPool.JobContext jobContext) {
                    if (intent.hasExtra(nexExportFormat.TAG_FORMAT_TYPE)) {
                        String stringExtra = intent.getStringExtra(nexExportFormat.TAG_FORMAT_TYPE);
                        if ("real_name".equals(stringExtra)) {
                            new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync(true);
                            return null;
                        } else if ("anonymous".equals(stringExtra)) {
                            new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync(false);
                            return null;
                        }
                    }
                    new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync();
                    return null;
                }
            });
        } else if ("com.miui.gallery.action.REQUEST_SYNC_SETTINGS".equals(intent.getAction())) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    GallerySettingsSyncHelper.doSync(GalleryApp.sGetAndroidContext());
                    return null;
                }
            });
        }
    }
}
