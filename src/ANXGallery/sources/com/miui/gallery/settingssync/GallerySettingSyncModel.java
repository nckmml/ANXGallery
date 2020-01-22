package com.miui.gallery.settingssync;

import android.text.TextUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.settingssync.GallerySettingsSyncContract;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GallerySettingSyncModel implements GallerySettingsSyncContract.Model {
    private final GallerySettingsSyncContract.Repository mRepository;

    public GallerySettingSyncModel(GallerySettingsSyncContract.Repository repository) {
        this.mRepository = repository;
    }

    private JSONObject convertSettingToJSON(GallerySettingsSyncContract.SyncableSetting syncableSetting) throws JSONException {
        Boolean isEnabled = syncableSetting.isEnabled();
        String value = syncableSetting.getValue();
        if (isEnabled == null && value == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", syncableSetting.getName());
        if (isEnabled != null) {
            jSONObject.put("enable", isEnabled);
        }
        if (value != null) {
            jSONObject.put("value", value);
        }
        return jSONObject;
    }

    private GallerySettingsSyncContract.SyncableSetting findSettingByName(List<GallerySettingsSyncContract.SyncableSetting> list, String str) {
        for (GallerySettingsSyncContract.SyncableSetting next : list) {
            if (str.endsWith(next.getName())) {
                return next;
            }
        }
        return null;
    }

    private void saveJSONToSetting(GallerySettingsSyncContract.SyncableSetting syncableSetting, JSONObject jSONObject) throws JSONException {
        String str = null;
        Boolean valueOf = jSONObject.has("enable") ? Boolean.valueOf(jSONObject.getBoolean("enable")) : null;
        if (jSONObject.has("value")) {
            str = jSONObject.getString("value");
        }
        syncableSetting.writeValue(valueOf, str);
    }

    private void saveJSONToSettings(List<GallerySettingsSyncContract.SyncableSetting> list, JSONArray jSONArray) throws JSONException {
        GallerySettingsSyncContract.SyncableSetting findSettingByName;
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("name");
                if (!TextUtils.isEmpty(optString) && (findSettingByName = findSettingByName(list, optString)) != null) {
                    saveJSONToSetting(findSettingByName, jSONObject);
                }
            }
        }
    }

    public JSONObject getUploadSettings() {
        List<GallerySettingsSyncContract.SyncableSetting> syncableSettings = this.mRepository.getSyncableSettings();
        if (!MiscUtil.isValid(syncableSettings)) {
            Log.d("GallerySettingSyncModel", "No syncable settings found!");
            return null;
        }
        try {
            JSONArray jSONArray = null;
            JSONArray jSONArray2 = null;
            for (GallerySettingsSyncContract.SyncableSetting next : syncableSettings) {
                JSONObject convertSettingToJSON = convertSettingToJSON(next);
                if (convertSettingToJSON != null) {
                    if (next.isExport()) {
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(convertSettingToJSON);
                    } else {
                        if (jSONArray2 == null) {
                            jSONArray2 = new JSONArray();
                        }
                        jSONArray2.put(convertSettingToJSON);
                    }
                }
            }
            if (jSONArray == null && jSONArray2 == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            if (jSONArray != null) {
                jSONObject.put("itemList", jSONArray);
            }
            if (jSONArray2 != null) {
                jSONObject.put("extraConfig", jSONArray2);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e("GallerySettingSyncModel", "Failed form settings to JSONObject, %s", (Object) e);
            return null;
        }
    }

    public boolean isDirty() {
        return GalleryPreferences.SettingsSync.isDirty() || !GalleryPreferences.SettingsSync.isFirstUploadComplete();
    }

    public void markDirty(boolean z) {
        GalleryPreferences.SettingsSync.markDirty(z);
        if (!z && !GalleryPreferences.SettingsSync.isFirstUploadComplete()) {
            GalleryPreferences.SettingsSync.setFirstUploadComplete();
            Log.d("GallerySettingSyncModel", "First setting upload complete");
        }
    }

    public boolean onDownloadSettings(JSONObject jSONObject) {
        if (jSONObject == null) {
            Log.w("GallerySettingSyncModel", "No download settings received!");
            return true;
        }
        List<GallerySettingsSyncContract.SyncableSetting> syncableSettings = this.mRepository.getSyncableSettings();
        if (!MiscUtil.isValid(syncableSettings)) {
            Log.d("GallerySettingSyncModel", "No syncable settings found!");
            return true;
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("itemList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("extraConfig");
            saveJSONToSettings(syncableSettings, optJSONArray);
            saveJSONToSettings(syncableSettings, optJSONArray2);
            return true;
        } catch (JSONException e) {
            Log.e("GallerySettingSyncModel", "Failed save settings from JSONObject, %s", (Object) e);
            return false;
        }
    }
}
