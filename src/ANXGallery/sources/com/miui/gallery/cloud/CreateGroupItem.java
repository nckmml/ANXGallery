package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.RequestOperationBase;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.data.DBCloud;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.SyncLog;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import org.json.JSONObject;

public class CreateGroupItem extends RequestOperationBase {
    private static String[] invalidCharacters = {"/", "\\", ":", "@", "*", "?", "<", ">", "\r", "\n", "\t", "-"};
    private static String[] invalidStartWiths = {".", "_"};

    public CreateGroupItem(Context context) {
        super(context);
    }

    public static String checkFileNameValid(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return context.getText(R.string.cloudfolder_name_cannot_empty).toString();
        }
        for (int string : new int[]{R.string.cloud_camera_display_name, R.string.cloud_screenshots_display_name, R.string.secret_album_display_name, R.string.all_video_album_display_name, R.string.pet_album_display_name}) {
            if (str.equalsIgnoreCase(context.getString(string))) {
                return context.getText(R.string.cloudfolder_name_reserved).toString();
            }
        }
        if (CloudControlStrategyHelper.getServerReservedAlbumNamesStrategy().containsName(str)) {
            return context.getText(R.string.cloudfolder_name_reserved).toString();
        }
        for (String str2 : invalidCharacters) {
            if (str.contains(str2)) {
                return context.getResources().getString(R.string.cloudfolder_contain_invalidate_char, new Object[]{str2});
            }
        }
        for (String str3 : invalidStartWiths) {
            if (str.startsWith(str3)) {
                return context.getResources().getString(R.string.cloudfolder_cannot_start_with, new Object[]{str3});
            }
        }
        return null;
    }

    public static String localCreateBabyGroup(Context context, String str) {
        SyncLog.d("LOCAL_CREATE_GROUP", "local create group start:" + str);
        synchronized (GalleryCloudUtils.CLOUD_URI) {
            DBCloud groupByFileName = CloudUtils.getGroupByFileName(context, str);
            if (groupByFileName != null) {
                String id = groupByFileName.getId();
                return id;
            }
            String localCreateGroup = localCreateGroup(context, (String) null, (String) null, str, true);
            return localCreateGroup;
        }
    }

    private static String localCreateGroup(Context context, String str, String str2, String str3, boolean z) {
        String lastPathSegment;
        SyncLog.d("LOCAL_CREATE_GROUP", "localCreateGroupBySpecialAppKey:" + str3);
        synchronized (GalleryCloudUtils.CLOUD_URI) {
            ContentValues contentValues = new ContentValues();
            if (!TextUtils.isEmpty(str)) {
                contentValues.put("appKey", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                contentValues.put("serverId", str2);
            }
            if (z) {
                contentValues.put("attributes", 1L);
            }
            contentValues.put("fileName", str3);
            contentValues.put("dateTaken", Long.valueOf(z ? -996 : System.currentTimeMillis()));
            contentValues.put("dateModified", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("serverType", 0);
            contentValues.put("localFlag", 8);
            contentValues.put("localFile", DownloadPathHelper.getFolderRelativePathInCloud(str3));
            Uri safeInsert = GalleryUtils.safeInsert(GalleryCloudUtils.CLOUD_URI, contentValues);
            SyncLog.d("LOCAL_CREATE_GROUP", "insert a group in local DB:" + str3);
            SyncLog.d("LOCAL_CREATE_GROUP", "local create group end:" + str3);
            lastPathSegment = safeInsert.getLastPathSegment();
        }
        return lastPathSegment;
    }

    public static void recreateGroup(String str) {
        recreateGroupBySpecialAppKey((String) null, str);
    }

    public static void recreateGroupBySpecialAppKey(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("serverId");
        contentValues.putNull("serverTag");
        contentValues.putNull("serverStatus");
        if (!TextUtils.isEmpty(str)) {
            contentValues.put("appKey", str);
        }
        contentValues.put("localFlag", 8);
        Uri uri = GalleryCloudUtils.CLOUD_URI;
        GalleryUtils.safeUpdate(uri, contentValues, "_id = '" + str2 + "'", (String[]) null);
    }

    /* access modifiers changed from: protected */
    public RequestOperationBase.Request buildRequest(Account account, RequestItemBase requestItemBase) throws Exception {
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        String createAlbumUrl = HostManager.OwnerAlbum.getCreateAlbumUrl();
        return new RequestOperationBase.Request.Builder().setMethod(2).setUrl(createAlbumUrl).setPostData(new JSONObject().put("content", requestCloudItem.dbCloud.toJSONObject())).setRetryTimes(requestCloudItem.createRetryTimes).setNeedReRequest(requestCloudItem.needReRequest).build();
    }

    /* access modifiers changed from: protected */
    public GallerySyncCode onPreRequest(RequestItemBase requestItemBase) {
        if (!(requestItemBase instanceof RequestCloudItem)) {
            SyncLog.e(getTag(), "item is not instanceof RequestCloudItem.");
            return GallerySyncCode.NOT_RETRY_ERROR;
        }
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        SyncLog.d(getTag(), "create group start: %s", (Object) requestCloudItem.dbCloud.getFileName());
        if (requestCloudItem.dbCloud.isShareItem()) {
            SyncLog.e(getTag(), "can't create share group.");
            return GallerySyncCode.NOT_RETRY_ERROR;
        } else if (SpaceFullHandler.getOwnerSpaceFullListener().isSpaceFull(requestCloudItem)) {
            return GallerySyncCode.NOT_RETRY_ERROR;
        } else {
            synchronized (GalleryCloudUtils.CLOUD_URI) {
                DBImage item = CloudUtils.getItem(GalleryCloudUtils.CLOUD_URI, this.mContext, "_id", requestCloudItem.dbCloud.getId());
                if (item.getLocalFlag() == 0 && !TextUtils.isEmpty(item.getServerId())) {
                    SyncLog.d(getTag(), "this group already exist in server, severId: %s", (Object) item.getServerId());
                }
            }
            return super.onPreRequest(requestItemBase);
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestError(GallerySyncCode gallerySyncCode, RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        if (gallerySyncCode != GallerySyncCode.OK) {
            requestItemBase.createRetryTimes++;
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("content");
        if (optJSONObject == null) {
            SyncLog.e(getTag(), "response content null");
            return;
        }
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        String string = optJSONObject.getString("id");
        CloudUtils.getLongAttributeFromJson(optJSONObject, nexExportFormat.TAG_FORMAT_TAG);
        ContentValues contentValuesForAllAndThumbNull = CloudUtils.getContentValuesForAllAndThumbNull(optJSONObject);
        synchronized (GalleryCloudUtils.CLOUD_URI) {
            DBImage itemByServerID = CloudUtils.getItemByServerID(this.mContext, string);
            if (itemByServerID != null && !itemByServerID.getId().equals(requestCloudItem.dbCloud.getId())) {
                String tag = getTag();
                SyncLog.d(tag, "cloud:" + requestCloudItem.dbCloud.getId() + " has the same one :" + itemByServerID.getId() + " delete oldCloud, fileName:" + itemByServerID.getFileName());
                CloudUtils.deleteDirty(itemByServerID);
            }
            CloudUtils.reviseAttributes(contentValuesForAllAndThumbNull, requestCloudItem.dbCloud);
            CloudUtils.updateToLocalDBForSync(GalleryCloudUtils.CLOUD_URI, contentValuesForAllAndThumbNull, requestCloudItem.dbCloud);
        }
        SyncLog.d(getTag(), AlbumShareOperations.requestUrlForBarcode(string).toString());
        String tag2 = getTag();
        SyncLog.d(tag2, "create group succeed and end:" + requestCloudItem.dbCloud.getFileName());
    }
}
