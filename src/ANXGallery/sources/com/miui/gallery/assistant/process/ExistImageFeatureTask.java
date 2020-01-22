package com.miui.gallery.assistant.process;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.ScenarioConstants;
import com.miui.gallery.cloud.control.BatteryMonitor;
import com.miui.gallery.pendingtask.PendingTaskManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ExistImageFeatureTask extends BaseImageTask {
    public ExistImageFeatureTask(int i) {
        super(i);
    }

    private void initPowerState(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            GalleryPreferences.Sync.setPowerCanSync(BatteryMonitor.isPowerCanSync(context, registerReceiver));
        }
    }

    private void recordTriggerEvent(String str) {
        Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
        generatorCommonParams.put("Trigger_Date", DateUtils.getDateFormat(System.currentTimeMillis()));
        GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_schedule_exist_image_task_" + str, generatorCommonParams);
    }

    public int getNetworkType() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public List<MediaFeatureItem> getToProcessImages(boolean z) {
        List<MediaFeatureItem> queryMediaItem = queryMediaItem(ScenarioConstants.IMAGE_FEATURE_SELECTION);
        List<Long> allProcessedSuccessImages = getAllProcessedSuccessImages();
        ArrayList arrayList = new ArrayList(100);
        if (!MiscUtil.isValid(queryMediaItem)) {
            return arrayList;
        }
        for (MediaFeatureItem next : queryMediaItem) {
            if (!allProcessedSuccessImages.contains(Long.valueOf(next.getId())) && (!z || !TextUtils.isEmpty(next.getImagePath()))) {
                arrayList.add(next);
                if (arrayList.size() == 100) {
                    break;
                }
            }
        }
        Log.d(this.TAG, "Processing %d images!", (Object) Integer.valueOf(arrayList.size()));
        if (arrayList.isEmpty()) {
            return arrayList;
        }
        List<MediaFeatureItem> queryNearByMediaItems = ImageFeatureManager.queryNearByMediaItems(((MediaFeatureItem) arrayList.get(0)).getDateTime());
        CardUtil.bindImageFeatures(queryNearByMediaItems);
        arrayList.addAll(ImageFeatureManager.filterNearByImages(queryNearByMediaItems));
        Log.d(this.TAG, "Processing %d images after add previous images!", (Object) Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public DownloadType onGetImageDownloadType() {
        return DownloadType.MICRO;
    }

    public boolean process(JSONObject jSONObject) throws Exception {
        recordImageFeatureTaskInterval();
        initPowerState(GalleryApp.sGetAndroidContext());
        if (GalleryPreferences.Sync.getPowerCanSync() || GalleryPreferences.Sync.getIsPlugged()) {
            Log.d(this.TAG, "Start process exist images");
            processInternal(jSONObject, 9, 2);
            return false;
        }
        Log.e(this.TAG, "The power is weak and not charging,abort processing!");
        recordTriggerEvent("fail");
        PendingTaskManager.getInstance().postTask(9, null, ExistImageFeatureChargingTask.class.getSimpleName());
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0010  */
    public void processInternal(JSONObject jSONObject, int i, int i2) {
        if (i2 <= 0) {
            i2 = 1;
        }
        recordImageFeatureTaskInterval();
        recordTriggerEvent("success");
        int i3 = 0;
        while (i3 < i2) {
            List<MediaFeatureItem> toProcessImages = getToProcessImages(true);
            if (MiscUtil.isValid(toProcessImages)) {
                i3++;
                Log.d(this.TAG, "process %d hundreds images，image batch count:%d", Integer.valueOf(i3), Integer.valueOf(toProcessImages.size()));
                if (!processImages(jSONObject, toProcessImages, true, false) || toProcessImages.size() < 100) {
                    return;
                }
                while (i3 < i2) {
                }
            } else {
                return;
            }
        }
        if (i > 0) {
            Log.d(this.TAG, "Have more un processed images,schedule next FeatureTask");
            PendingTaskManager.getInstance().postTask(i, null, getClass().getSimpleName());
        }
    }

    /* access modifiers changed from: package-private */
    public void recordImageFeatureTaskInterval() {
        long lastImageFeatureTaskTime = GalleryPreferences.Assistant.getLastImageFeatureTaskTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (lastImageFeatureTaskTime > 0) {
            GallerySamplingStatHelper.generatorCommonParams();
            long j = currentTimeMillis - lastImageFeatureTaskTime;
            long abs = Math.abs(j) / 86400000;
            if (abs == 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("assistant", "assistant_imagefeature_task_hours_interval", Math.abs(j) / 3600000);
            }
            GallerySamplingStatHelper.recordNumericPropertyEvent("assistant", "assistant_imagefeature_task_days_interval", abs);
        }
        GalleryPreferences.Assistant.setLastImageFeatureTaskTime(currentTimeMillis);
    }

    public boolean requireCharging() {
        return false;
    }

    public boolean requireDeviceIdle() {
        return true;
    }
}
