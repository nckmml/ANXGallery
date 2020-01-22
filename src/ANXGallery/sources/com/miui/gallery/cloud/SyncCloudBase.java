package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.baby.BabyAlbumUtils;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.data.DBCloud;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import com.xiaomi.micloudsdk.request.utils.Request;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SyncCloudBase extends SyncFromServer {
    public SyncCloudBase(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        super(context, account, galleryExtendedAuthToken);
    }

    private final Set<String> getLargerTags(long j) {
        HashSet newHashSet = Sets.newHashSet();
        Cursor cursor = null;
        try {
            Uri appendLimit = UriUtil.appendLimit(getBaseUri(), 100);
            cursor = this.mContext.getContentResolver().query(appendLimit, new String[]{"serverTag"}, "serverTag > " + j, (String[]) null, "serverTag ASC ");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    newHashSet.add(cursor.getString(0));
                }
            }
            return newHashSet;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final void handleCustom(DBImage dBImage, JSONObject jSONObject) throws JSONException {
        ContentValues contentValuesForAll = CloudUtils.getContentValuesForAll(jSONObject);
        contentValuesForAll.putNull("editedColumns");
        CloudUtils.reviseAttributes(contentValuesForAll, dBImage);
        int intValue = contentValuesForAll.getAsInteger("serverType").intValue();
        if (intValue == 1 || intValue == 2) {
            String localGroupId = getLocalGroupId(contentValuesForAll);
            if (TextUtils.isEmpty(localGroupId)) {
                logForNullLocalGroupId(contentValuesForAll, jSONObject);
            } else {
                contentValuesForAll.put("localGroupId", localGroupId);
            }
            CloudUtils.reviseSpecialTypeFlags(contentValuesForAll, dBImage);
            if (TextUtils.equals(dBImage.getSha1(), contentValuesForAll.getAsString("sha1")) && dBImage.getGroupId() != contentValuesForAll.getAsLong("groupId").longValue()) {
                DBImage item = CloudUtils.getItem(getBaseUri(), this.mContext, "serverId", contentValuesForAll.getAsString("groupId"));
                String pathInPrimaryStorage = TextUtils.isEmpty(item.getLocalFile()) ? null : StorageUtils.getPathInPrimaryStorage(item.getLocalFile());
                if (pathInPrimaryStorage != null) {
                    String localFile = dBImage.getLocalFile();
                    if (FileUtils.isFileExist(localFile)) {
                        SyncLog.d("SyncCloudBase", "move local file %s while updating group id, result %s", localFile, Boolean.valueOf(FileUtils.move(new File(localFile), new File(pathInPrimaryStorage, FileUtils.getFileName(localFile)))));
                    }
                    String thumbnailFile = dBImage.getThumbnailFile();
                    if (FileUtils.isFileExist(thumbnailFile)) {
                        SyncLog.d("SyncCloudBase", "move thumb file %s while updating group id, result %s", thumbnailFile, Boolean.valueOf(FileUtils.move(new File(thumbnailFile), new File(pathInPrimaryStorage, FileUtils.getFileName(thumbnailFile)))));
                    }
                } else {
                    SyncLog.e("SyncCloudBase", "get dst folder error while updating group id, old %s, new %s", Long.valueOf(dBImage.getGroupId()), contentValuesForAll.getAsLong("groupId"));
                }
            }
        } else if (intValue == 0) {
            String localFile2 = dBImage.getLocalFile();
            String asString = contentValuesForAll.getAsString("localFile");
            if (!TextUtils.isEmpty(localFile2) && !TextUtils.isEmpty(asString) && !localFile2.equalsIgnoreCase(asString)) {
                if (asString.startsWith(".cloud/owner")) {
                    contentValuesForAll.remove("localFile");
                }
                statSuspiciousAlbumPathModify(localFile2, asString);
            }
            updateLocalGroupIdInGroup(dBImage.getId(), contentValuesForAll);
        }
        CloudUtils.updateToLocalDBForSync(getBaseUri(), contentValuesForAll, dBImage);
        CloudUtils.updateFilePathForSync(getBaseUri(), contentValuesForAll, dBImage);
        if ((intValue == 1 || intValue == 2) && contentValuesForAll.containsKey("sha1") && jSONObject.has("isFavorite")) {
            handleFavoriteInfo(jSONObject, contentValuesForAll.getAsString("sha1"));
        }
    }

    private final void handleDelete(DBImage dBImage, JSONObject jSONObject) throws JSONException {
        DeleteCloudItem.updateForDeleteOrPurgedOnLocal(getBaseUri(), this.mContext, dBImage, jSONObject);
    }

    static String handleNewGroup(Context context, Uri uri, ContentValues contentValues, String str) throws JSONException {
        String str2;
        if (CloudUtils.isUmodifyAlbum(str)) {
            DBImage itemByServerID = CloudUtils.getItemByServerID(context, String.valueOf(str));
            if (itemByServerID != null) {
                CloudUtils.reviseAttributes(contentValues, itemByServerID);
                CloudUtils.updateToLocalDBForSync(uri, contentValues, itemByServerID);
                return itemByServerID.getId();
            }
            String serverAlbumName = CloudControlStrategyHelper.getServerUnModifyAlbumsStrategy().getServerAlbumName(Long.parseLong(str));
            DBCloud groupByFileName = serverAlbumName != null ? CloudUtils.getGroupByFileName(context, serverAlbumName) : null;
            if (groupByFileName != null) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("fileName", groupByFileName.getFileName() + "_" + System.currentTimeMillis());
                CloudUtils.updateToLocalDB(uri, contentValues2, (DBImage) groupByFileName);
            }
            contentValues.put("localFlag", 0);
            CloudUtils.reviseAttributes(contentValues, (DBImage) null);
            Uri safeInsertImage = GalleryUtils.safeInsertImage(uri, contentValues);
            if (safeInsertImage != null) {
                return safeInsertImage.getLastPathSegment();
            }
            return null;
        }
        if (!ApplicationHelper.isAutoUploadFeatureOpen() || !contentValues.containsKey("appKey")) {
            str2 = null;
        } else {
            str2 = contentValues.getAsString("appKey");
            DBCloud groupByAppKey = CloudUtils.getGroupByAppKey(GalleryApp.sGetAndroidContext(), str2);
            if (groupByAppKey != null) {
                CloudUtils.reviseAttributes(contentValues, groupByAppKey);
                CloudUtils.updateToLocalDBForSync(uri, contentValues, (DBImage) groupByAppKey);
                return groupByAppKey.getId();
            }
        }
        String asString = contentValues.getAsString("localFile");
        String asString2 = contentValues.getAsString("fileName");
        DBCloud groupByFileName2 = CloudUtils.getGroupByFileName(context, asString2);
        if (groupByFileName2 != null) {
            if (CloudUtils.isUmodifyAlbum(groupByFileName2.getServerId())) {
                statSuspiciousAlbum(contentValues);
            } else {
                String localFile = groupByFileName2.getLocalFile();
                if (TextUtils.isEmpty(localFile) || TextUtils.isEmpty(asString) || localFile.equalsIgnoreCase(asString)) {
                    CloudUtils.reviseAttributes(contentValues, groupByFileName2);
                    CloudUtils.updateToLocalDBForSync(uri, contentValues, (DBImage) groupByFileName2);
                    return groupByFileName2.getId();
                } else if (groupByFileName2.getLocalFlag() == 8 || groupByFileName2.getLocalFlag() == 7) {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("fileName", groupByFileName2.getFileName() + "_" + System.currentTimeMillis());
                    CloudUtils.updateToLocalDB(uri, contentValues3, (DBImage) groupByFileName2);
                } else {
                    SyncLog.e("SyncCloudBase", "old album's localflag: %s, old album's name: %s", Integer.valueOf(groupByFileName2.getLocalFlag()), groupByFileName2.getFileName());
                }
            }
        }
        if (TextUtils.isEmpty(asString)) {
            asString = CloudUtils.getOwnerAlbumLocalFile(asString2, str2);
            String mergeEditedColumns = GalleryCloudUtils.mergeEditedColumns(GalleryCloudUtils.transformToEditedColumnsElement(38), contentValues.getAsString("editedColumns"));
            contentValues.put("localFile", asString);
            contentValues.put("editedColumns", mergeEditedColumns);
        }
        DBCloud groupItemByColumnnameAndValue = CloudUtils.getGroupItemByColumnnameAndValue(context, "localFile", asString, true);
        if (groupItemByColumnnameAndValue != null) {
            CloudUtils.reviseAttributes(contentValues, groupItemByColumnnameAndValue);
            CloudUtils.updateToLocalDBForSync(uri, contentValues, (DBImage) groupItemByColumnnameAndValue);
            return groupItemByColumnnameAndValue.getId();
        }
        contentValues.put("localFlag", 0);
        CloudUtils.reviseAttributes(contentValues, (DBImage) null);
        Uri safeInsertImage2 = GalleryUtils.safeInsertImage(uri, contentValues);
        if (safeInsertImage2 != null) {
            return safeInsertImage2.getLastPathSegment();
        }
        return null;
    }

    private final void handlePurged(DBImage dBImage, JSONObject jSONObject) throws JSONException {
        DeleteCloudItem.updateForDeleteOrPurgedOnLocal(getBaseUri(), this.mContext, dBImage, jSONObject);
    }

    private void logForNullLocalGroupId(ContentValues contentValues, JSONObject jSONObject) {
        SyncLog.i("SyncCloudBase", "localGroupId is null, schemaJson= ", jSONObject == null ? "" : jSONObject.toString());
        HashMap hashMap = new HashMap();
        hashMap.put("groupId", contentValues.getAsString("groupId"));
        hashMap.put("serverstatus", contentValues.getAsString("serverStatus"));
        hashMap.put("model", Build.MODEL);
        hashMap.put("version", Build.VERSION.INCREMENTAL);
        GallerySamplingStatHelper.recordCountEvent("Sync", "group_record_not_found", hashMap);
    }

    private static void statSuspiciousAlbum(ContentValues contentValues) {
        HashMap hashMap = new HashMap();
        hashMap.put("album_info", contentValues.toString());
        GallerySamplingStatHelper.recordErrorEvent("Sync", "sync_suspicious_album", hashMap);
    }

    private static void statSuspiciousAlbumPathModify(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("oldLocalFile", str);
        hashMap.put("newLocalFile", str2);
        GallerySamplingStatHelper.recordCountEvent("Sync", "sync_suspicious_album_path_modify", hashMap);
    }

    /* access modifiers changed from: protected */
    public void appendParams(ArrayList<NameValuePair> arrayList, ArrayList<GalleryCloudSyncTagUtils.SyncTagItem> arrayList2) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        if (arrayList2 != null) {
            super.appendParams(arrayList, arrayList2);
            Set<String> largerTags = getLargerTags(arrayList2.get(0).currentValue);
            HashMap newHashMap = Maps.newHashMap();
            Iterator<NameValuePair> it = arrayList.iterator();
            while (it.hasNext()) {
                NameValuePair next = it.next();
                newHashMap.put(next.getName(), next.getValue());
            }
            Request.addFilterTagsToParams(getSyncUrl(), newHashMap, this.mExtendedAuthToken.getSecurity(), largerTags, arrayList2.get(0).currentValue);
            String str = (String) newHashMap.get("filterTag");
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new BasicNameValuePair("filterTag", str));
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean belong2BabyAlbum(ContentValues contentValues, boolean z) {
        return BabyAlbumUtils.isBabyAlbumForThisServerId(this.mContext, contentValues.getAsString("groupId"), z);
    }

    /* access modifiers changed from: protected */
    public abstract List<DBImage> getCandidateItemsInAGroup(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public abstract DBImage getItem(String str);

    /* access modifiers changed from: protected */
    public abstract String getLocalGroupId(ContentValues contentValues);

    /* access modifiers changed from: protected */
    public final boolean handleDataJson(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("content");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            SyncLog.d("SyncCloudBase", "contentArray is empty, return.");
            return false;
        }
        boolean z = false;
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (handleItem(jSONObject2)) {
                z = true;
            }
            String string = jSONObject2.getString(nexExportFormat.TAG_FORMAT_TYPE);
            String string2 = jSONObject2.getString("status");
            if (string.equals("group") && string2.equals("custom") && jSONObject2.has("isPublic") && jSONObject2.getBoolean("isPublic")) {
                AlbumShareOperations.requestPublicUrl(jSONObject2.getString("id"), false);
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void handleFavoriteInfo(JSONObject jSONObject, String str) throws JSONException {
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ed, code lost:
        return false;
     */
    public final boolean handleItem(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString(nexExportFormat.TAG_FORMAT_TYPE);
        String string2 = jSONObject.getString("status");
        String string3 = jSONObject.getString("id");
        synchronized (getBaseUri()) {
            DBImage item = getItem(string3);
            if (item == null) {
                ContentValues contentValuesForAll = CloudUtils.getContentValuesForAll(jSONObject);
                if (string.equals("group")) {
                    updateLocalGroupIdInGroup(handleNewGroup(this.mContext, getBaseUri(), contentValuesForAll, string3), contentValuesForAll);
                    return false;
                }
                contentValuesForAll.put("localGroupId", getLocalGroupId(contentValuesForAll));
                handleUbiSubImage(jSONObject, handleNewImage(contentValuesForAll, string3, string2, jSONObject));
                if (contentValuesForAll.containsKey("sha1") && jSONObject.has("isFavorite")) {
                    handleFavoriteInfo(jSONObject, contentValuesForAll.getAsString("sha1"));
                }
                return true;
            } else if (item.getServerTag() >= CloudUtils.getLongAttributeFromJson(jSONObject, nexExportFormat.TAG_FORMAT_TAG)) {
                SyncLog.d("SyncCloudBase", "cloud:" + item.getFileName() + " local tag:" + item.getServerTag() + " >= server tag:" + CloudUtils.getLongAttributeFromJson(jSONObject, nexExportFormat.TAG_FORMAT_TAG) + ", don't update local.");
                return false;
            } else if (string2.equals("custom")) {
                handleCustom(item, jSONObject);
                handleUbiSubImage(jSONObject, (String) null);
            } else if (string2.equals("deleted")) {
                handleDelete(item, jSONObject);
            } else if (string2.equals("purged")) {
                handlePurged(item, jSONObject);
            } else {
                SyncLog.e("SyncCloudBase", "status unrecognized, schema:" + jSONObject);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String handleNewImage(ContentValues contentValues, String str, String str2, JSONObject jSONObject) {
        List list;
        DBImage dBImage;
        ContentValues contentValues2 = contentValues;
        contentValues2.put("localFlag", 0);
        String asString = contentValues2.getAsString("fileName");
        String asString2 = contentValues2.getAsString("sha1");
        String asString3 = contentValues2.getAsString("localGroupId");
        if (TextUtils.isEmpty(asString3)) {
            list = new ArrayList();
            logForNullLocalGroupId(contentValues2, jSONObject);
        } else {
            list = getCandidateItemsInAGroup(asString, asString2, asString3);
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                dBImage = null;
                break;
            }
            dBImage = (DBImage) it.next();
            if (!TextUtils.equals(dBImage.getSha1(), asString2)) {
                if (dBImage.getFileName().endsWith(".jpg") && dBImage.getSize() < 1048576) {
                    if (!TextUtils.equals(asString2, ExifUtil.getUserCommentSha1(dBImage.getLocalFile()))) {
                        long datetaken = dBImage.getDatetaken();
                        long longValue = contentValues2.getAsLong("dateTaken").longValue();
                        if (datetaken > 0 && longValue > 0 && datetaken / 1000 == longValue / 1000) {
                            contentValues2.put("thumbnailFile", dBImage.getLocalFile());
                            contentValues2.putNull("localFile");
                            SyncLog.d("SyncCloudBase", "match taken time, thumbnail %s, origin %s", dBImage.getFileName(), contentValues2.getAsString("fileName"));
                            Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                            generatorCommonParams.put("groupId", contentValues2.getAsString("groupId"));
                            generatorCommonParams.put("name", dBImage.getFileName() + "_" + contentValues2.getAsString("fileName"));
                            GallerySamplingStatHelper.recordCountEvent("Sync", "sync_item_match_taken_time", generatorCommonParams);
                            break;
                        }
                    } else {
                        contentValues2.put("thumbnailFile", dBImage.getLocalFile());
                        contentValues2.putNull("localFile");
                        SyncLog.d("SyncCloudBase", "match exif sha1, thumbnail %s", (Object) dBImage.getFileName());
                        Map<String, String> generatorCommonParams2 = GallerySamplingStatHelper.generatorCommonParams();
                        generatorCommonParams2.put("groupId", contentValues2.getAsString("groupId"));
                        generatorCommonParams2.put("name", dBImage.getFileName());
                        GallerySamplingStatHelper.recordCountEvent("Sync", "sync_item_match_exif_sha1", generatorCommonParams2);
                        break;
                    }
                }
            } else if (dBImage.getSize() < ((long) contentValues2.getAsInteger("size").intValue())) {
                contentValues2.put("thumbnailFile", dBImage.getLocalFile());
                contentValues2.putNull("localFile");
                SyncLog.d("SyncCloudBase", "match db sha1, thumbnail %s", (Object) dBImage.getFileName());
            } else {
                contentValues2.putNull("thumbnailFile");
                contentValues2.put("localFile", dBImage.getLocalFile());
                SyncLog.d("SyncCloudBase", "match db sha1, original file %s", (Object) dBImage.getFileName());
            }
        }
        if (dBImage != null) {
            CloudUtils.reviseSpecialTypeFlags(contentValues2, dBImage);
            GalleryUtils.safeUpdateImage(contentValues2, dBImage);
            return dBImage.getId();
        }
        CloudUtils.reviseSpecialTypeFlags(contentValues2, (DBImage) null);
        Uri safeInsertImage = GalleryUtils.safeInsertImage(getBaseUri(), contentValues2);
        if (safeInsertImage == null) {
            return null;
        }
        String lastPathSegment = safeInsertImage.getLastPathSegment();
        onNewImageSynced(contentValues2, lastPathSegment);
        return lastPathSegment;
    }

    /* access modifiers changed from: protected */
    public abstract void handleUbiSubImage(JSONObject jSONObject, String str) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract void onNewImageSynced(ContentValues contentValues, String str);

    /* access modifiers changed from: protected */
    public abstract void updateLocalGroupIdInGroup(String str, ContentValues contentValues);
}
