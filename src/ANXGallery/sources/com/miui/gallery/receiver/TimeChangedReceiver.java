package com.miui.gallery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GalleryUtils;

public class TimeChangedReceiver extends BroadcastReceiver {
    private void invalidateCloudCache() {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
            public Object run(ThreadPool.JobContext jobContext) {
                GalleryUtils.safeDelete(GalleryCloudUtils.CLOUD_CACHE_URI, (String) null, (String[]) null);
                return null;
            }
        });
    }

    private void invalidateDateCache() {
        GalleryDateUtils.invalidateCache();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[ADDED_TO_REGION] */
    public void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != 502473491) {
            if (hashCode != 505380757) {
                if (hashCode == 1041332296 && action.equals("android.intent.action.DATE_CHANGED")) {
                    c = 1;
                    if (c != 0 || c == 1 || c == 2) {
                        invalidateDateCache();
                        invalidateCloudCache();
                    }
                    return;
                }
            } else if (action.equals("android.intent.action.TIME_SET")) {
                c = 0;
                if (c != 0) {
                }
                invalidateDateCache();
                invalidateCloudCache();
            }
        } else if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
            c = 2;
            if (c != 0) {
            }
            invalidateDateCache();
            invalidateCloudCache();
        }
        c = 65535;
        if (c != 0) {
        }
        invalidateDateCache();
        invalidateCloudCache();
    }
}
