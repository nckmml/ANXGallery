package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.data.DBShareAlbum;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deprecated.Preference;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SyncSharerAll extends SyncFromServer {
    private Bundle mExtras;

    public SyncSharerAll(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, Bundle bundle) {
        super(context, account, galleryExtendedAuthToken);
        this.mExtras = bundle;
    }

    private void handleAlbumList(JSONObject jSONObject) throws JSONException {
        boolean z;
        JSONArray optJSONArray = jSONObject.optJSONArray("entries");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            String string = jSONObject2.getString("sharedId");
            String optString = jSONObject2.optString("status");
            if (TextUtils.isEmpty(optString)) {
                optString = "normal";
            }
            long longAttributeFromJson = CloudUtils.getLongAttributeFromJson(jSONObject2, nexExportFormat.TAG_FORMAT_TAG);
            JSONObject jSONObject3 = jSONObject2.getJSONObject("content");
            String string2 = jSONObject3.getString("status");
            String string3 = jSONObject3.getString("fileName");
            synchronized (GalleryCloudUtils.SHARE_ALBUM_URI) {
                DBShareAlbum dBShareAlbumBySharedId = CloudUtils.getDBShareAlbumBySharedId(string);
                if (dBShareAlbumBySharedId == null) {
                    if (DBShareAlbum.isNormalStatus(string2, optString)) {
                        Uri safeInsert = GalleryUtils.safeInsert(GalleryCloudUtils.SHARE_ALBUM_URI, DBShareAlbum.getContentValue(jSONObject2, dBShareAlbumBySharedId));
                        if (safeInsert != null) {
                            CloudUtils.updateLocalGroupIdInGroup(GalleryCloudUtils.SHARE_IMAGE_URI, string, safeInsert.getLastPathSegment(), "albumId");
                        }
                        insertCreatorIntoShareUser(jSONObject2);
                    }
                    z = false;
                } else if (!DBShareAlbum.isNormalStatus(string2, optString)) {
                    notifyKicked(string3, string);
                    CloudUtils.deleteShareAlbumInLocal(string, dBShareAlbumBySharedId.getId());
                    SyncLog.d("SyncSharerAll", "delete share album:" + string);
                    z = false;
                } else if (longAttributeFromJson > dBShareAlbumBySharedId.getServerTag()) {
                    GalleryUtils.safeUpdate(GalleryCloudUtils.SHARE_ALBUM_URI, DBShareAlbum.getContentValue(jSONObject2, dBShareAlbumBySharedId), "albumId = ? ", new String[]{string});
                }
                z = true;
            }
            if (z) {
                SyncSharerImageForAlbum syncSharerImageForAlbum = new SyncSharerImageForAlbum(this.mContext, this.mAccount, this.mExtendedAuthToken, string);
                SyncLog.d("SyncSharerAll", "sync share image when insertCreatorAndSyncImages start");
                syncSharerImageForAlbum.sync();
                SyncLog.d("SyncSharerAll", "sync share image when insertCreatorAndSyncImages end");
                if (Preference.getSyncShouldClearDataBase()) {
                    SyncLog.i("SyncSharerAll", "need clear data");
                    return;
                }
            }
            if (DBShareAlbum.isNormalStatus(string2, optString) && jSONObject3.has("isPublic") && jSONObject3.getBoolean("isPublic")) {
                AlbumShareOperations.requestPublicUrl(jSONObject2.getString("sharedId"), true);
            }
        }
    }

    private void handleImageList(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("entries");
        for (int i = 0; i < optJSONArray.length(); i++) {
            SyncSharerImageForAlbum syncSharerImageForAlbum = new SyncSharerImageForAlbum(this.mContext, this.mAccount, this.mExtendedAuthToken, optJSONArray.getJSONObject(i).getString("sharedId"));
            SyncLog.d("SyncSharerAll", "sync share image when handleImageList start");
            syncSharerImageForAlbum.sync();
            SyncLog.d("SyncSharerAll", "sync share image when handleImageList end");
            if (Preference.getSyncShouldClearDataBase()) {
                SyncLog.i("SyncSharerAll", "need clear data");
                return;
            }
        }
    }

    private void handleUserList(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("entries");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            new SyncOwnerUserForAlbum(this.mContext, this.mAccount, this.mExtendedAuthToken, jSONObject2.getString("albumId")).handleDataJson(jSONObject2);
        }
    }

    private void insertCreatorIntoShareUser(JSONObject jSONObject) throws JSONException {
        SyncSharerUserForAlbum.insertCreatorIntoShareUser(jSONObject.getString("creatorId"), jSONObject.getString("sharedId"));
    }

    private void notifyKicked(String str, String str2) {
        CloudUtils.sendShareAlbumNotification(this.mContext, this.mContext.getString(R.string.album_share_kicked, new Object[]{this.mContext.getString(R.string.quotation, new Object[]{str})}), 2, (Uri) null);
        CloudShareAlbumMediator.getInstance().onShareAlbumExited(str2);
    }

    /* access modifiers changed from: protected */
    public Uri getBaseUri() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getCurrentSyncTag() {
        boolean z;
        ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> currentSyncTag = super.getCurrentSyncTag();
        Iterator<GalleryCloudSyncTagUtils.SyncTagItem> it = currentSyncTag.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            GalleryCloudSyncTagUtils.SyncTagItem next = it.next();
            if (GalleryCloudSyncTagUtils.shouldSyncTagValue(next.syncTagType) && next.currentValue == ((long) GalleryCloudSyncTagUtils.getInitSyncTagValue(next.syncTagType))) {
                z = true;
                break;
            }
        }
        if (z) {
            Iterator<GalleryCloudSyncTagUtils.SyncTagItem> it2 = currentSyncTag.iterator();
            while (it2.hasNext()) {
                GalleryCloudSyncTagUtils.SyncTagItem next2 = it2.next();
                if (GalleryCloudSyncTagUtils.shouldSyncTagValue(next2.syncTagType)) {
                    next2.currentValue = (long) GalleryCloudSyncTagUtils.getInitSyncTagValue(next2.syncTagType);
                }
            }
        }
        return currentSyncTag;
    }

    /* access modifiers changed from: protected */
    public ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getSyncTagList() {
        ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList = new ArrayList<>();
        arrayList.add(new GalleryCloudSyncTagUtils.SyncTagItem(4));
        arrayList.add(new GalleryCloudSyncTagUtils.SyncTagItem(3));
        arrayList.add(new GalleryCloudSyncTagUtils.SyncTagItem(5));
        arrayList.add(new GalleryCloudSyncTagUtils.SyncTagItem(2));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getSyncTagSelection() {
        return GalleryCloudSyncTagUtils.getAccountSelections(this.mAccount);
    }

    /* access modifiers changed from: protected */
    public String getSyncUrl() {
        return HostManager.SyncPull.getPullShareAll();
    }

    /* access modifiers changed from: protected */
    public String getTagColumnName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean handleResultAndShouldContinue(JSONObject jSONObject, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) throws JSONException {
        Iterator<GalleryCloudSyncTagUtils.SyncTagItem> it = arrayList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            GalleryCloudSyncTagUtils.SyncTagItem next = it.next();
            String jsonTagOutput = GalleryCloudSyncTagUtils.getJsonTagOutput(next.syncTagType);
            if (TextUtils.isEmpty(jsonTagOutput)) {
                SyncLog.e("SyncSharerAll", "get output tag is null, syncType:" + next.syncTagType);
                return false;
            } else if (jSONObject.has(jsonTagOutput)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(jsonTagOutput);
                boolean z2 = jSONObject2.getBoolean("lastPage");
                long longAttributeFromJson = CloudUtils.getLongAttributeFromJson(jSONObject2, "syncTag");
                if (jSONObject2.has("entries")) {
                    int i = next.syncTagType;
                    if (i == 2) {
                        handleUserList(jSONObject2);
                    } else if (i == 3) {
                        handleAlbumList(jSONObject2);
                    } else if (i == 4) {
                        handleAlbumList(jSONObject2);
                    } else if (i != 5) {
                        SyncLog.i("SyncSharerAll", "can't handle this syncTag:" + next.syncTagType);
                    } else {
                        handleImageList(jSONObject2);
                    }
                } else {
                    SyncLog.d("SyncSharerAll", jsonTagOutput + " entries is null, may be is first sync, just save syncTag:" + jSONObject2);
                }
                if (Preference.getSyncShouldClearDataBase()) {
                    SyncLog.i("SyncSharerAll", "need clear data");
                    return false;
                }
                if (longAttributeFromJson > next.currentValue) {
                    SyncLog.d("SyncSharerAll", "update the syncTag:" + longAttributeFromJson);
                    next.serverValue = longAttributeFromJson;
                    updateSyncTag(next);
                }
                if (z2) {
                    SyncLog.d("SyncSharerAll", "last page, break sync from server");
                    next.shouldSync = false;
                } else {
                    z = true;
                }
            }
        }
        updateSyncInfo(jSONObject, arrayList);
        return z;
    }
}
