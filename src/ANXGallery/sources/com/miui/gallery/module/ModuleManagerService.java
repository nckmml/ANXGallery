package com.miui.gallery.module;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import miui.net.ConnectivityHelper;

public class ModuleManagerService extends JobService {
    /* access modifiers changed from: private */
    public String TAG = "ModuleManagerService";

    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r4.getExtras().getString("module");
     */
    public boolean onStartJob(final JobParameters jobParameters) {
        final String string;
        Log.d(this.TAG, "onStartJob");
        if (jobParameters == null || string == null) {
            return false;
        }
        Log.d(this.TAG, "onStartLoadingModulesFromCloud");
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                if (GalleryPreferences.CTA.canConnectNetwork() && ConnectivityHelper.getInstance().isUnmeteredNetworkConnected() && new CacheRepository(ModuleManagerService.this.getApplicationContext(), false).cache(string) == null) {
                    Log.d(ModuleManagerService.this.TAG, "cache %s failed", (Object) string);
                }
                ModuleManagerService.this.jobFinished(jobParameters, false);
                return null;
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(this.TAG, "onStopJob");
        return false;
    }
}
