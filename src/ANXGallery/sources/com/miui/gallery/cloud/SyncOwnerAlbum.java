package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.SyncLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SyncOwnerAlbum extends SyncFromServer {
    private long mSyncId;

    public SyncOwnerAlbum(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        super(context, account, galleryExtendedAuthToken);
        initSyncId();
        Log.d("SyncSystemAlbum", "init syncId %d", (Object) Long.valueOf(this.mSyncId));
    }

    private void initSyncId() {
        if (GalleryPreferences.Sync.getEverSyncedSystemAlbum()) {
            String format = String.format(Locale.US, "%s AND (%s=%d AND %s='%s')", new Object[]{"(serverType=0)", "localFlag", 0, "serverStatus", "custom"});
            this.mSyncId = ((Long) SafeDBUtil.safeQuery(this.mContext, getBaseUri(), new String[]{"max(serverId)"}, format, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Long>() {
                public Long handle(Cursor cursor) {
                    if (cursor == null || !cursor.moveToFirst()) {
                        return 0L;
                    }
                    return Long.valueOf(cursor.getLong(0));
                }
            })).longValue();
            return;
        }
        this.mSyncId = 0;
    }

    /* access modifiers changed from: protected */
    public void appendParams(ArrayList<NameValuePair> arrayList, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList2) {
        arrayList.add(new BasicNameValuePair("syncId", String.valueOf(this.mSyncId)));
    }

    /* access modifiers changed from: protected */
    public Uri getBaseUri() {
        return GalleryCloudUtils.CLOUD_URI;
    }

    /* access modifiers changed from: protected */
    public ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getCurrentSyncTag() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getSyncTagList() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSyncTagSelection() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSyncUrl() {
        return HostManager.SyncPull.getPullOwnerAlbumUrl();
    }

    /* access modifiers changed from: protected */
    public String getTagColumnName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean handleResultAndShouldContinue(JSONObject jSONObject, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray("albums");
        if (optJSONArray == null) {
            SyncLog.e("SyncSystemAlbum", "response content null");
            return false;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
            long longAttributeFromJson = CloudUtils.getLongAttributeFromJson(jSONObject3, "albumId");
            ContentValues contentValuesForOwnerAlbum = CloudUtils.getContentValuesForOwnerAlbum(jSONObject3);
            DBImage itemByServerID = CloudUtils.getItemByServerID(this.mContext, String.valueOf(longAttributeFromJson));
            boolean isUmodifyAlbum = CloudUtils.isUmodifyAlbum(String.valueOf(longAttributeFromJson));
            if (itemByServerID == null && isUmodifyAlbum) {
                CloudUtils.addRecordsForCameraAndScreenshots(new CloudUtils.Insertable() {
                    public long insert(Uri uri, String str, String str2, ContentValues contentValues) {
                        return GalleryUtils.safeInsert(uri, contentValues) == null ? 0 : 1;
                    }
                });
                itemByServerID = CloudUtils.getItemByServerID(this.mContext, String.valueOf(longAttributeFromJson));
                HashMap hashMap = new HashMap();
                hashMap.put("groupId", String.valueOf(longAttributeFromJson));
                hashMap.put("model", Build.MODEL);
                hashMap.put("version", Build.VERSION.INCREMENTAL);
                GallerySamplingStatHelper.recordCountEvent("Sync", "system_album_record_not_found", hashMap);
            }
            if (isUmodifyAlbum) {
                if (itemByServerID != null && itemByServerID.getLocalFlag() == 0 && !GalleryPreferences.Sync.getEverSyncedSystemAlbum()) {
                    CloudUtils.updateToLocalDBByServerId(getBaseUri(), contentValuesForOwnerAlbum, String.valueOf(longAttributeFromJson));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("localGroupId", itemByServerID.getId());
                    SafeDBUtil.safeUpdate(this.mContext, getBaseUri(), contentValues, String.format(Locale.US, "%s=%s AND (%s!=%s) AND (%s=%d OR %s=%d)", new Object[]{"groupId", Long.valueOf(longAttributeFromJson), "localGroupId", itemByServerID.getId(), "serverType", 1, "serverType", 2}), (String[]) null);
                }
            } else if (!CloudTableUtils.isGroupWithoutRecord(longAttributeFromJson) && itemByServerID == null) {
                SyncCloudBase.handleNewGroup(this.mContext, getBaseUri(), contentValuesForOwnerAlbum, String.valueOf(longAttributeFromJson));
            }
        }
        if (!GalleryPreferences.Sync.getEverSyncedSystemAlbum()) {
            SafeDBUtil.safeDelete(this.mContext, getBaseUri(), String.format(Locale.US, "%s=-1 AND (%s=%d OR %s=%d) AND (%s=%d OR %s=%d)", new Object[]{"localGroupId", "localFlag", 7, "localFlag", 8, "serverType", 1, "serverType", 2}), (String[]) null);
        }
        GalleryPreferences.Sync.setEverSyncedSystemAlbum();
        this.mSyncId = CloudUtils.getLongAttributeFromJson(jSONObject2, "syncAlbumId");
        return !jSONObject2.getBoolean("lastPage");
    }
}
