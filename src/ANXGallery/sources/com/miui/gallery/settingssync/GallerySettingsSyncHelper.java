package com.miui.gallery.settingssync;

import android.content.Context;
import android.os.Bundle;
import com.miui.gallery.settingssync.GallerySettingsSyncContract;

public class GallerySettingsSyncHelper {
    public static void doSync(Context context) {
        getSyncAdapter(context, getModel(getRepository())).performSync((Bundle) null);
    }

    public static GallerySettingsSyncContract.Model getModel(GallerySettingsSyncContract.Repository repository) {
        return new GallerySettingSyncModel(repository);
    }

    public static GallerySettingsSyncContract.Repository getRepository() {
        return new GallerySyncableSettingsRepository();
    }

    public static GallerySettingsSyncContract.SyncAdapter getSyncAdapter(Context context, GallerySettingsSyncContract.Model model) {
        return new GallerySettingsSyncAdapter(context, model);
    }
}
