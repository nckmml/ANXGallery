package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.SpaceFullHandler;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.cloud.base.RetryRequestHelper;
import com.miui.gallery.cloud.base.SyncTask;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deprecated.Preference;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SyncFromServer {
    protected Account mAccount;
    protected Context mContext;
    protected GalleryExtendedAuthToken mExtendedAuthToken;

    public SyncFromServer(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        this.mContext = context;
        this.mAccount = account;
        this.mExtendedAuthToken = galleryExtendedAuthToken;
    }

    public static ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getCurrentSyncTag(final ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList, String str) {
        GalleryUtils.safeQuery(CloudUtils.getLimitUri(GalleryCloudSyncTagUtils.getUri(arrayList.get(0).syncTagType), 1), GalleryCloudSyncTagUtils.getSyncTagSelection(arrayList), str, (String[]) null, (String) null, new GalleryUtils.QueryHandler<Void>() {
            public Void handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToNext()) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        GalleryCloudSyncTagUtils.SyncTagItem syncTagItem = (GalleryCloudSyncTagUtils.SyncTagItem) it.next();
                        syncTagItem.currentValue = (long) GalleryCloudSyncTagUtils.getInitSyncTagValue(syncTagItem.syncTagType);
                    }
                    return null;
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    ((GalleryCloudSyncTagUtils.SyncTagItem) arrayList.get(i)).currentValue = Math.max(cursor.getLong(i), (long) GalleryCloudSyncTagUtils.getInitSyncTagValue(((GalleryCloudSyncTagUtils.SyncTagItem) arrayList.get(i)).syncTagType));
                }
                return null;
            }
        });
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final JSONObject getItemsList(ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList, int i) throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            Iterator<GalleryCloudSyncTagUtils.SyncTagItem> it = arrayList.iterator();
            while (it.hasNext()) {
                GalleryCloudSyncTagUtils.SyncTagItem next = it.next();
                if (next.shouldSync) {
                    if (TextUtils.isEmpty(GalleryCloudSyncTagUtils.getJsonTagInput(next.syncTagType))) {
                        SyncLog.e("SyncFromServer", "get input tag is null, syncType:" + next.syncTagType);
                        return null;
                    }
                    arrayList2.add(new BasicNameValuePair(GalleryCloudSyncTagUtils.getJsonTagInput(next.syncTagType), Long.toString(next.currentValue)));
                }
            }
            appendSyncInfo(arrayList2, arrayList);
        }
        appendParams(arrayList2, arrayList);
        if (i > 0) {
            arrayList2.add(new BasicNameValuePair("limit", Long.toString((long) i)));
        }
        return CloudUtils.getFromXiaomi(getSyncUrl(), arrayList2, this.mAccount, this.mExtendedAuthToken, 0, false);
    }

    /* access modifiers changed from: protected */
    public void appendParams(ArrayList<NameValuePair> arrayList, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList2) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
    }

    /* access modifiers changed from: protected */
    public void appendSyncInfo(ArrayList<NameValuePair> arrayList, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList2) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        if (supportSyncInfo(arrayList2)) {
            GalleryCloudSyncTagUtils.SyncTagItem syncTagItem = arrayList2.get(0);
            String str = (String) GalleryUtils.safeQuery(CloudUtils.getLimitUri(GalleryCloudSyncTagUtils.getUri(syncTagItem.syncTagType), 1), new String[]{GalleryCloudSyncTagUtils.getSyncInfoColumnName(syncTagItem.syncTagType)}, getSyncTagSelection(), (String[]) null, (String) null, new GalleryUtils.QueryHandler<String>() {
                public String handle(Cursor cursor) {
                    if (cursor != null && cursor.moveToNext()) {
                        return cursor.getString(0);
                    }
                    return null;
                }
            });
            if (str == null) {
                str = "";
            }
            arrayList.add(new BasicNameValuePair("syncExtraInfo", str));
        }
    }

    /* access modifiers changed from: protected */
    public abstract Uri getBaseUri();

    /* access modifiers changed from: protected */
    public ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getCurrentSyncTag() {
        return getCurrentSyncTag(getSyncTagList(), getSyncTagSelection());
    }

    /* access modifiers changed from: protected */
    public final long getLargestTagInLocalDB() {
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            Uri baseUri = getBaseUri();
            cursor = contentResolver.query(baseUri, new String[]{" Max( " + getTagColumnName() + " ) "}, (String) null, (String[]) null, (String) null);
            if (cursor != null && cursor.moveToNext()) {
                return cursor.getLong(0);
            }
            long initSyncTagValue = (long) GalleryCloudSyncTagUtils.getInitSyncTagValue(getSyncTagList().get(0).syncTagType);
            if (cursor != null) {
                cursor.close();
            }
            return initSyncTagValue;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getSyncItemLimit() {
        return 100;
    }

    /* access modifiers changed from: protected */
    public abstract ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> getSyncTagList();

    /* access modifiers changed from: protected */
    public abstract String getSyncTagSelection();

    /* access modifiers changed from: protected */
    public abstract String getSyncUrl();

    /* access modifiers changed from: protected */
    public abstract String getTagColumnName();

    /* access modifiers changed from: protected */
    public boolean handleDataJson(JSONObject jSONObject) throws JSONException {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean handleResultAndShouldContinue(JSONObject jSONObject, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) throws JSONException {
        handleDataJson(jSONObject);
        updateSyncInfo(jSONObject, arrayList);
        return updateSyncTagAndShouldContinue(jSONObject, arrayList);
    }

    /* access modifiers changed from: protected */
    public void onSyncSuccess() {
    }

    /* access modifiers changed from: protected */
    public boolean supportSyncInfo(ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) {
        return GalleryCloudSyncTagUtils.hasSyncInfo(arrayList.get(0).syncTagType);
    }

    public final GallerySyncResult<JSONObject> sync() throws JSONException {
        GallerySyncResult<JSONObject> retryTask;
        SyncLog.d("SyncFromServer", "sync from server start");
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            final ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> currentSyncTag = getCurrentSyncTag();
            if (SyncConditionManager.check(0) != 2) {
                retryTask = RetryRequestHelper.retryTask(new SyncTask<JSONObject>() {
                    public String getIdentifier() {
                        return SyncFromServer.this.getSyncUrl();
                    }

                    public GallerySyncResult<JSONObject> run() throws Exception {
                        SyncFromServer syncFromServer = SyncFromServer.this;
                        return CheckResult.checkXMResultCode(syncFromServer.getItemsList(currentSyncTag, syncFromServer.getSyncItemLimit()), (RequestItemBase) null, (SpaceFullHandler.SpaceFullListener) null);
                    }
                });
                JSONObject jSONObject = (JSONObject) retryTask.data;
                if (retryTask.code != GallerySyncCode.RESET_SYNC_TAG) {
                    if (retryTask.code == GallerySyncCode.OK) {
                        if (jSONObject == null) {
                            break;
                        }
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            if (optJSONObject.length() != 0) {
                                if (!handleResultAndShouldContinue(optJSONObject, currentSyncTag)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        SyncLog.e("SyncFromServer", "sync from server error:" + jSONObject);
                        break;
                    }
                } else {
                    SyncLog.e("SyncFromServer", "sync from server error " + GallerySyncCode.RESET_SYNC_TAG + ", need clear data");
                    Preference.setSyncShouldClearDataBase(true);
                    break;
                }
            } else {
                return new GallerySyncResult.Builder().setCode(GallerySyncCode.CONDITION_INTERRUPTED).build();
            }
        }
        SyncLog.d("SyncFromServer", "sync from server finish: " + (System.currentTimeMillis() - currentTimeMillis));
        if (retryTask == null || retryTask.code != GallerySyncCode.OK) {
            HashMap hashMap = new HashMap();
            hashMap.put("className", getClass().getSimpleName());
            hashMap.put("result", retryTask.toString());
            GallerySamplingStatHelper.recordErrorEvent("Sync", "sync_error_class", hashMap);
        } else {
            onSyncSuccess();
        }
        return retryTask;
    }

    /* access modifiers changed from: protected */
    public void updateSyncInfo(String str, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) {
        GalleryCloudSyncTagUtils.SyncTagItem syncTagItem = arrayList.get(0);
        Uri uri = GalleryCloudSyncTagUtils.getUri(syncTagItem.syncTagType);
        String syncInfoColumnName = GalleryCloudSyncTagUtils.getSyncInfoColumnName(syncTagItem.syncTagType);
        ContentValues contentValues = new ContentValues();
        contentValues.put(syncInfoColumnName, str);
        GalleryUtils.safeUpdate(uri, contentValues, getSyncTagSelection(), (String[]) null);
        GalleryCloudSyncTagUtils.postUpdateSyncTag(this.mContext, contentValues);
    }

    /* access modifiers changed from: protected */
    public void updateSyncInfo(JSONObject jSONObject, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) {
        String optString = jSONObject.optString("syncExtraInfo");
        if (!TextUtils.isEmpty(optString) && supportSyncInfo(arrayList)) {
            updateSyncInfo(optString, arrayList);
        }
    }

    public void updateSyncTag(GalleryCloudSyncTagUtils.SyncTagItem syncTagItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(syncTagItem);
        updateSyncTag((ArrayList<GalleryCloudSyncTagUtils.SyncTagItem>) arrayList);
    }

    /* access modifiers changed from: protected */
    public void updateSyncTag(ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) {
        Uri uri = GalleryCloudSyncTagUtils.getUri(arrayList.get(0).syncTagType);
        ContentValues contentValues = new ContentValues();
        Iterator<GalleryCloudSyncTagUtils.SyncTagItem> it = arrayList.iterator();
        while (it.hasNext()) {
            GalleryCloudSyncTagUtils.SyncTagItem next = it.next();
            contentValues.put(GalleryCloudSyncTagUtils.getColumnName(next.syncTagType), Long.valueOf(next.serverValue));
        }
        GalleryUtils.safeUpdate(uri, contentValues, getSyncTagSelection(), (String[]) null);
        GalleryCloudSyncTagUtils.postUpdateSyncTag(this.mContext, contentValues);
    }

    /* access modifiers changed from: protected */
    public boolean updateSyncTagAndShouldContinue(JSONObject jSONObject, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList) throws JSONException {
        boolean z = jSONObject.getBoolean("lastPage");
        long longAttributeFromJson = CloudUtils.getLongAttributeFromJson(jSONObject, "syncTag");
        if (z) {
            long largestTagInLocalDB = getLargestTagInLocalDB();
            longAttributeFromJson = (longAttributeFromJson > ((long) GalleryCloudSyncTagUtils.getInitSyncTagValue(arrayList.get(0).syncTagType)) || largestTagInLocalDB > ((long) GalleryCloudSyncTagUtils.getInitSyncTagValue(arrayList.get(0).syncTagType))) ? Math.max(longAttributeFromJson, largestTagInLocalDB) : 0;
        }
        if (longAttributeFromJson > arrayList.get(0).currentValue) {
            SyncLog.d("SyncFromServer", "update the syncTag:" + longAttributeFromJson);
            arrayList.get(0).serverValue = longAttributeFromJson;
            updateSyncTag(arrayList);
        }
        if (!z) {
            return true;
        }
        SyncLog.d("SyncFromServer", "last page, break sync from server");
        return false;
    }
}
