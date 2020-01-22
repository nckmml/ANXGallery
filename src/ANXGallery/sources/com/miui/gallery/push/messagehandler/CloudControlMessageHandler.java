package com.miui.gallery.push.messagehandler;

import android.content.Context;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miui.gallery.cloudcontrol.CloudControlDBHelper;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.pendingtask.PendingTaskManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.push.GalleryPushMessage;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import miui.net.ConnectivityHelper;

public class CloudControlMessageHandler extends MessageHandler {
    private static final long ONE_WEEK_MILLIS = TimeUnit.DAYS.toMillis(7);

    public void handleDirect(Context context, GalleryPushMessage galleryPushMessage) {
        long tag = galleryPushMessage.getTag();
        long pushTag = GalleryPreferences.CloudControl.getPushTag();
        boolean z = false;
        if (tag <= pushTag) {
            Log.e("CloudControlMessageHandler", "Current push tag is less than last push tag, skip handle");
            Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
            generatorCommonParams.put("businessModule", galleryPushMessage.getBusinessModule());
            generatorCommonParams.put("tag_lastTag", String.format(Locale.US, "%d_%d", new Object[]{Long.valueOf(tag), Long.valueOf(pushTag)}));
            GallerySamplingStatHelper.recordErrorEvent("mipush", "push_tag_is_invalid", generatorCommonParams);
            return;
        }
        GalleryPreferences.CloudControl.setPushTag(tag);
        String data = galleryPushMessage.getData();
        try {
            ArrayList arrayList = (ArrayList) new GsonBuilder().registerTypeAdapter(FeatureProfile.class, new FeatureProfile.Deserializer()).create().fromJson(data, new TypeToken<ArrayList<FeatureProfile>>() {
            }.getType());
            if (MiscUtil.isValid(arrayList)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FeatureProfile featureProfile = (FeatureProfile) it.next();
                    CloudControlManager.getInstance().insertToCache(featureProfile);
                    if (CloudControlDBHelper.tryInsertToDB(context, featureProfile) == 0) {
                        Log.e("CloudControlMessageHandler", "Persist failed: %s", (Object) String.valueOf(featureProfile));
                        z = true;
                    }
                }
                if (z) {
                    Map<String, String> generatorCommonParams2 = GallerySamplingStatHelper.generatorCommonParams();
                    generatorCommonParams2.put("response", data);
                    GallerySamplingStatHelper.recordErrorEvent("cloud_control", "cloud_control_persist_error", generatorCommonParams2);
                }
            }
        } catch (Exception e) {
            Log.e("CloudControlMessageHandler", "Parse direct content [%s] failed: \n%s", data, e);
        }
    }

    public void handlePull(Context context, GalleryPushMessage galleryPushMessage) {
        if (galleryPushMessage != null) {
            if (ConnectivityHelper.getInstance().isUnmeteredNetworkConnected()) {
                new CloudControlRequestHelper(context).execRequestSync();
                Log.d("CloudControlMessageHandler", "Pull data trigger by push");
                return;
            }
            if (ConnectivityHelper.getInstance().isNetworkConnected()) {
                if (System.currentTimeMillis() - GalleryPreferences.CloudControl.getLastRequestSucceedTime() >= ONE_WEEK_MILLIS) {
                    new CloudControlRequestHelper(context).execRequestSync();
                    Log.d("CloudControlMessageHandler", "Force pull data after one week");
                    return;
                }
            }
            Log.d("CloudControlMessageHandler", "Post as pending task");
            PendingTaskManager.getInstance().postTask(3, galleryPushMessage);
        }
    }
}
