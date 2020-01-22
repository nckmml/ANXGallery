package com.miui.gallery.settingssync;

import com.miui.gallery.settingssync.GallerySettingsSyncContract;
import java.util.ArrayList;
import java.util.List;

public class GallerySyncableSettingsRepository implements GallerySettingsSyncContract.Repository {
    private static List<GallerySettingsSyncContract.SyncableSetting> sSyncableSettings = new ArrayList();

    static {
        sSyncableSettings.add(new FaceSetting());
        sSyncableSettings.add(new SearchSetting());
    }

    public List<GallerySettingsSyncContract.SyncableSetting> getSyncableSettings() {
        return sSyncableSettings;
    }
}
