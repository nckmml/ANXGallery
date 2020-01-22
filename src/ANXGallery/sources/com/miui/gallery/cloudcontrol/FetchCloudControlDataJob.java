package com.miui.gallery.cloudcontrol;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.discovery.AbstractJob;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import java.util.Map;

public class FetchCloudControlDataJob extends AbstractJob {
    public Object execJob() {
        if (!NetworkUtils.isNetworkConnected()) {
            Log.e("FetchCloudControlDataJob", "Network is disconnected, skip exec.");
            return false;
        }
        long lastRequestTime = GalleryPreferences.CloudControl.getLastRequestTime();
        if (lastRequestTime > 0) {
            long currentTimeMillis = System.currentTimeMillis() - lastRequestTime;
            if (currentTimeMillis < 57600000) {
                Log.w("FetchCloudControlDataJob", "exec job too frequently[time interval: %d ms], skip exec.", Long.valueOf(currentTimeMillis));
                Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                generatorCommonParams.put("request_interval(minutes)", String.valueOf((currentTimeMillis / 1000) / 60));
                GallerySamplingStatHelper.recordCountEvent("cloud_control", "exec_job_too_frequently", generatorCommonParams);
                return true;
            }
        }
        return Boolean.valueOf(new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync());
    }

    public JobInfo getJobInfo(Context context, ComponentName componentName) {
        return new JobInfo.Builder(this.mJobId, componentName).setRequiredNetworkType(2).setRequiresCharging(false).setRequiresDeviceIdle(false).setPeriodic(86400000).build();
    }

    public boolean needsReschedule() {
        return false;
    }
}
