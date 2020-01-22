package com.miui.gallery.util;

import android.app.Activity;
import android.content.Context;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.xiaomi.micloudsdk.stat.IMiCloudStatCallback;
import com.xiaomi.micloudsdk.stat.MiCloudStatManager;
import com.xiaomi.mistatistic.sdk.MiStatException;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.URLStatsRecorder;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.util.Map;

public class GalleryStatHelper {
    private static volatile boolean sInitialized;

    public static void addHttpEvent(String str, long j, long j2, int i) {
        if (isUsable()) {
            URLStatsRecorder.addHttpEvent(new HttpEvent(str, j, j2, i));
        }
    }

    public static void addHttpEvent(String str, long j, long j2, int i, String str2) {
        if (isUsable()) {
            URLStatsRecorder.addHttpEvent(new HttpEvent(str, j, j2, i, str2));
        }
    }

    public static void addHttpEvent(String str, String str2) {
        if (isUsable()) {
            URLStatsRecorder.addHttpEvent(new HttpEvent(str, str2));
        }
    }

    public static void init(final Context context) {
        if (!sInitialized && BaseGalleryPreferences.CTA.canConnectNetwork()) {
            sInitialized = true;
            try {
                MiCloudStatManager.getInstance().init(new IMiCloudStatCallback() {
                    public void onAddHttpEvent(String str, long j, long j2, int i, String str2) {
                        URLStatsRecorder.addHttpEvent(new HttpEvent(str, j, j2, i, str2));
                    }

                    public void onEnableAutoRecord() {
                        URLStatsRecorder.enableAutoRecord();
                    }

                    public void onInitialize() {
                        try {
                            MiStatInterface.initialize(context, "2882303761517492012", "5601749292012", "com.miui.gallery");
                        } catch (MiStatException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onSetEventFilter() {
                    }

                    public void onSetUploadPolicy() {
                        MiStatInterface.setUploadPolicy(0, 300000);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isUsable() {
        return sInitialized;
    }

    public static void recordCountEvent(String str, String str2) {
        if (isUsable()) {
            try {
                MiStatInterface.recordCountEvent(str, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordCountEvent(String str, String str2, Map<String, String> map) {
        if (isUsable()) {
            try {
                MiStatInterface.recordCountEvent(str, str2, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordNumericPropertyEvent(String str, String str2, long j) {
        if (isUsable()) {
            try {
                MiStatInterface.recordNumericPropertyEvent(str, str2, j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordPageEnd(Activity activity, String str) {
        if (isUsable()) {
            try {
                MiStatInterface.recordPageEnd(activity, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordPageStart(Activity activity, String str) {
        if (isUsable()) {
            try {
                MiStatInterface.recordPageStart(activity, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordStringPropertyEvent(String str, String str2, String str3) {
        if (isUsable()) {
            try {
                MiStatInterface.recordStringPropertyEvent(str, str2, str3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
