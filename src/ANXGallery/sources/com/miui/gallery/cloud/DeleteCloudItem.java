package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.RequestOperationBase;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.UbiFocusUtils;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import com.miui.gallery.util.deprecated.Preference;
import com.miui.gallery.util.deprecated.Storage;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteCloudItem extends RequestOperationBase {
    public DeleteCloudItem(Context context) {
        super(context);
    }

    private static void deleteDownloadAndTempFiles(MediaFileUtils.FileType fileType, String str) {
        if (!CloudUtils.isFileInCloudDB(str)) {
            MediaFileUtils.deleteFileType(fileType, str);
            MediaFileUtils.FileType fileType2 = MediaFileUtils.FileType.TEMP;
            MediaFileUtils.deleteFileType(fileType2, str + ".temp");
        }
    }

    private static void deleteOriginalFileAndThumbnail(DBImage dBImage) {
        ArrayList arrayList = new ArrayList();
        String localFile = dBImage.getLocalFile();
        String thumbnailFile = dBImage.getThumbnailFile();
        if (FileUtils.isFileExist(localFile) && new File(localFile).length() == dBImage.getSize()) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.ORIGINAL, localFile);
            arrayList.add(new DeleteRecord(1, localFile, "DeleteCloudItem"));
        }
        if (!TextUtils.isEmpty(thumbnailFile) && !TextUtils.equals(localFile, thumbnailFile)) {
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.THUMBNAIL, thumbnailFile);
            arrayList.add(new DeleteRecord(1, thumbnailFile, "DeleteCloudItem"));
        }
        String fileName = dBImage.getFileName();
        if (dBImage.isShareItem()) {
            fileName = DownloadPathHelper.getDownloadFileNameNotSecret(dBImage, fileName);
        }
        for (String next : DownloadPathHelper.getAllFilePathForRead(StorageUtils.getPathsInExternalStorage(GalleryApp.sGetAndroidContext(), DownloadPathHelper.getDownloadFolderRelativePath(dBImage)), fileName)) {
            String userCommentSha1 = ExifUtil.getUserCommentSha1(next);
            if (TextUtils.isEmpty(userCommentSha1)) {
                userCommentSha1 = FileUtils.getSha1(next);
            }
            if (TextUtils.equals(userCommentSha1, dBImage.getSha1())) {
                MediaFileUtils.deleteFileType(MediaFileUtils.FileType.ORIGINAL, next);
                arrayList.add(new DeleteRecord(1, next, "DeleteCloudItem"));
            }
        }
        if (MiscUtil.isValid(arrayList)) {
            DeleteRecorder.record((Collection<DeleteRecord>) arrayList);
        }
    }

    private static boolean tryDeleteDirtyItem(DBImage dBImage) {
        int safeDelete = GalleryUtils.safeDelete(dBImage.getBaseUri(), "_id = ? AND serverId IS NULL ", new String[]{dBImage.getId()});
        UbiFocusUtils.safeDeleteSubUbi(dBImage);
        return safeDelete > 0;
    }

    public static void updateForDeleteOrPurgedGroupOnLocal(Context context, DBImage dBImage, JSONObject jSONObject) throws JSONException {
        ContentValues contentValuesForUploadDeletePurged = CloudUtils.getContentValuesForUploadDeletePurged(jSONObject);
        if (!contentValuesForUploadDeletePurged.containsKey("serverStatus")) {
            contentValuesForUploadDeletePurged.put("serverStatus", "deleted");
        }
        Uri uri = GalleryCloudUtils.CLOUD_URI;
        GalleryUtils.safeUpdate(uri, contentValuesForUploadDeletePurged, "_id = '" + dBImage.getId() + "'", (String[]) null);
    }

    public static void updateForDeleteOrPurgedOnLocal(Uri uri, Context context, DBImage dBImage, JSONObject jSONObject) throws JSONException {
        if (dBImage.getServerType() == 1 || dBImage.getServerType() == 2) {
            updateForDeleteOrPurgedOnLocalNotDeleteFile(uri, context, dBImage, jSONObject);
            ImageFeatureManager.getInstance().onImageDelete(dBImage.getId());
            for (String str : StorageUtils.getMicroThumbnailDirectories(context)) {
                deleteDownloadAndTempFiles(MediaFileUtils.FileType.MICRO_THUMBNAIL, str + File.separator + dBImage.getSha1FileName());
            }
            for (String str2 : Storage.getCloudThumbnailFilePath()) {
                deleteDownloadAndTempFiles(MediaFileUtils.FileType.THUMBNAIL, str2 + File.separator + dBImage.getSha1FileName());
            }
            deleteOriginalFileAndThumbnail(dBImage);
        } else if (dBImage.getServerType() != 0) {
        } else {
            if (CloudUtils.hasCreateCopyMoveImageByGroupId(context, dBImage.getId())) {
                CreateGroupItem.recreateGroup(dBImage.getId());
            } else {
                updateForDeleteOrPurgedGroupOnLocal(context, dBImage, jSONObject);
            }
        }
    }

    public static void updateForDeleteOrPurgedOnLocalNotDeleteFile(Uri uri, Context context, DBImage dBImage, JSONObject jSONObject) throws JSONException {
        ContentValues contentValuesForUploadDeletePurged = CloudUtils.getContentValuesForUploadDeletePurged(jSONObject);
        contentValuesForUploadDeletePurged.putNull("thumbnailFile");
        contentValuesForUploadDeletePurged.putNull("microthumbfile");
        if (dBImage.getLocalFlag() == 15) {
            contentValuesForUploadDeletePurged.remove("localFlag");
        }
        GalleryUtils.safeUpdate(uri, contentValuesForUploadDeletePurged, "_id = '" + dBImage.getId() + "'", (String[]) null);
        UbiFocusUtils.safeDeleteSubUbi(dBImage);
    }

    /* access modifiers changed from: protected */
    public RequestOperationBase.Request buildRequest(Account account, RequestItemBase requestItemBase) throws Exception {
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        RequestOperationBase.Request.Builder builder = new RequestOperationBase.Request.Builder();
        if (requestCloudItem.dbCloud.isItemType()) {
            DBImage dBImage = requestCloudItem.dbCloud;
            String requestId = dBImage.getRequestId();
            if (TextUtils.isEmpty(requestId)) {
                return null;
            }
            String deleteUrl = CloudUrlProvider.getUrlProvider(dBImage.isShareItem(), dBImage.isVideoType()).getDeleteUrl(account.name, requestId);
            ArrayList arrayList = new ArrayList();
            if (dBImage.isShareItem()) {
                arrayList.add(new BasicNameValuePair("sharedGalleryId", requestId));
            }
            builder.setUrl(deleteUrl).setMethod(2).setParams(arrayList).setPostData(new JSONObject().put("content", requestCloudItem.dbCloud.toJSONObject())).setRetryTimes(requestItemBase.otherRetryTimes).setNeedReRequest(false);
        } else {
            String serverId = requestCloudItem.dbCloud.getServerId();
            if (TextUtils.isEmpty(serverId)) {
                return null;
            }
            String deleteAlbumUrl = HostManager.OwnerAlbum.getDeleteAlbumUrl(serverId);
            String valueOf = String.valueOf(requestCloudItem.dbCloud.getServerTag());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new BasicNameValuePair(nexExportFormat.TAG_FORMAT_TAG, valueOf));
            builder.setUrl(deleteAlbumUrl).setMethod(2).setParams(arrayList2).setRetryTimes(requestItemBase.otherRetryTimes).setNeedReRequest(false);
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public GallerySyncCode onPreRequest(RequestItemBase requestItemBase) {
        if (!(requestItemBase instanceof RequestCloudItem)) {
            SyncLog.e(getTag(), "item is not instanceof RequestCloudItem.");
            return GallerySyncCode.NOT_RETRY_ERROR;
        }
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        if (!tryDeleteDirtyItem(requestCloudItem.dbCloud)) {
            return super.onPreRequest(requestItemBase);
        }
        SyncLog.d(getTag(), "serverId is null means item is deleted by user, delete this dirty record: %s", (Object) requestCloudItem.dbCloud.getFileName());
        return GallerySyncCode.NOT_RETRY_ERROR;
    }

    /* access modifiers changed from: protected */
    public void onRequestError(GallerySyncCode gallerySyncCode, RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        if (gallerySyncCode != GallerySyncCode.OK && gallerySyncCode != GallerySyncCode.ALBUM_NOT_EXIST) {
            requestItemBase.otherRetryTimes++;
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        if (Preference.sGetCloudGalleryRecyclebinFull()) {
            Preference.sSetCloudGalleryRecyclebinFull(false);
        }
        updateForDeleteOrPurgedOnLocal(requestCloudItem.dbCloud.getBaseUri(), this.mContext, requestCloudItem.dbCloud, jSONObject);
        SyncLog.d(getTag(), "Delete item success: %s, type: %s", requestCloudItem.dbCloud.getFileName(), Integer.valueOf(requestCloudItem.dbCloud.getServerType()));
    }
}
