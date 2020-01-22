package com.miui.gallery.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.source.server.OpenSearchRequest;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.miui.os.Rom;

public class AIAlbumStatusHelper {
    public static void doPostCloudControlJob() {
        if (!CloudControlStrategyHelper.getSearchStrategy().isAIAlbumEnabled() && CloudControlManager.getInstance().queryFeatureStatus("search-auto-open-search") == FeatureProfile.Status.ENABLE) {
            SearchStatUtils.reportEvent("from_cloud_control", "auto_request_open_search", "result", SearchConstants.isErrorStatus(OpenSearchRequest.request()) ? "failed" : "succeeded", "device", Build.DEVICE);
        }
    }

    public static int getOpenApiSearchStatus() {
        if (!isLocalSearchOpen(true)) {
            return 0;
        }
        if (isCloudControlSearchAIAlbumOpen()) {
            return 1;
        }
        return isLocalSearchOpen(false) ? 2 : 0;
    }

    private static boolean internalSetFaceAlbumStatus(boolean z) {
        if (isFaceSwitchSet() && isFaceAlbumEnabled() == z) {
            return false;
        }
        GalleryPreferences.Face.setFaceSwitchStatus(z);
        GalleryPreferences.SettingsSync.markDirty(true);
        return true;
    }

    private static boolean internalSetLocalSearchStatus(boolean z) {
        if (isLocalSearchSet() && z == isLocalSearchOpen(true)) {
            return false;
        }
        GalleryPreferences.Search.setIsUserSearchSwitchOpen(z);
        GalleryPreferences.SettingsSync.markDirty(true);
        return true;
    }

    public static boolean isAIAlbumEnabled() {
        return isFaceAlbumEnabled() || isLocalSearchOpen(true);
    }

    public static boolean isCloudControlSearchAIAlbumOpen() {
        return CloudControlStrategyHelper.getSearchStrategy().isAIAlbumEnabled();
    }

    public static boolean isCloudControlSearchBarOpen() {
        return CloudControlStrategyHelper.getSearchStrategy().isSearchBarEnabled();
    }

    public static boolean isFaceAlbumEnabled() {
        return GalleryPreferences.Face.getFaceSwitchEnabled();
    }

    public static boolean isFaceSwitchSet() {
        return GalleryPreferences.Face.isFaceSwitchSet();
    }

    public static boolean isLocalSearchOpen(boolean z) {
        return GalleryPreferences.Search.isUserSearchSwitchOpen(z);
    }

    public static boolean isLocalSearchSet() {
        return GalleryPreferences.Search.isUserSearchSwitchSet();
    }

    private static void notifyAIAlbumStatusChanged(Context context, boolean z) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.miui.gallery.action.AI_ALBUM_SWITCH_CHANGE"));
    }

    private static void notifyFaceAlbumStatusChange(Context context, boolean z) {
        context.getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, false);
        context.getContentResolver().notifyChange(GalleryContract.PeopleFace.PERSONS_URI, (ContentObserver) null, false);
    }

    private static void notifySearchStatusChanged(boolean z) {
    }

    public static void registerAIAlbumStatusReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter("com.miui.gallery.action.AI_ALBUM_SWITCH_CHANGE"));
    }

    public static void requestOpenCloudControlSearch(String str) {
        if (!isLocalSearchOpen(true)) {
            Log.w("AIAlbumStatusHelper", "Local search switch is off, no need to request open cloud search.");
            return;
        }
        long userLastRequestOpenTime = GalleryPreferences.Search.getUserLastRequestOpenTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - userLastRequestOpenTime > 600000) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    GalleryPreferences.Search.setIsUserSearchSwitchOpen(true);
                    OpenSearchRequest.request();
                    return null;
                }
            });
            GalleryPreferences.Search.setUserLastRequestOpenTime(currentTimeMillis);
        } else {
            Log.w("AIAlbumStatusHelper", "Ignore open search request, too frequent.");
        }
        SearchStatUtils.reportEvent(str, "auto_request_open_search");
    }

    public static void setAIAlbumLocalStatus(Context context, boolean z) {
        boolean z2;
        if (internalSetLocalSearchStatus(z)) {
            notifySearchStatusChanged(z);
            z2 = true;
        } else {
            z2 = false;
        }
        if (internalSetFaceAlbumStatus(z)) {
            notifyFaceAlbumStatusChange(context, z);
            z2 = true;
        }
        if (z2) {
            notifyAIAlbumStatusChanged(context, z);
        }
    }

    public static void setFaceAlbumStatus(Context context, boolean z) {
        if (internalSetFaceAlbumStatus(z)) {
            notifyFaceAlbumStatusChange(context, z);
            notifyAIAlbumStatusChanged(context, z);
        }
    }

    public static void setLocalSearchStatus(Context context, boolean z) {
        if (internalSetLocalSearchStatus(z)) {
            notifySearchStatusChanged(z);
            notifyAIAlbumStatusChanged(context, z);
        }
    }

    public static void unregisterAIAlbumStatusReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    public static boolean useNewAIAlbumSwitch() {
        return !Rom.IS_INTERNATIONAL || isCloudControlSearchAIAlbumOpen();
    }
}
