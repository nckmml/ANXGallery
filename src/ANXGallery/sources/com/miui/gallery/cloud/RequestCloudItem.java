package com.miui.gallery.cloud;

import android.content.ContentValues;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.LocalUbifocus;
import com.miui.gallery.data.UbiIndexMapper;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.ExtraTextUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.StorageUtils;
import java.io.File;
import java.util.ArrayList;

public class RequestCloudItem extends RequestItemBase {
    public DBImage dbCloud;

    public RequestCloudItem(int i, DBImage dBImage) {
        this(i, dBImage, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RequestCloudItem(int i, DBImage dBImage, boolean z) {
        super(i, z ? 0 : getDelay(dBImage));
        this.dbCloud = dBImage;
        init();
    }

    public static String getDownloadOriginalFilePath(DBImage dBImage) {
        return new RequestCloudItem(dBImage.isVideoType() ? 9 : 10, dBImage).getVerifiedDownloadFilePathForRead();
    }

    private boolean isOriginFileValidate(String str) {
        return !TextUtils.isEmpty(str) && new File(str).length() >= this.dbCloud.getSize();
    }

    public static boolean shouldHideDownloadFolder(String str) {
        String relativePath = StorageUtils.getRelativePath(GalleryApp.sGetAndroidContext(), str);
        return ExtraTextUtils.startsWithIgnoreCase(relativePath, "MIUI/Gallery/cloud/.microthumbnailFile") || ExtraTextUtils.startsWithIgnoreCase(relativePath, StorageUtils.DIRECTORY_SECRET_ALBUM_PATH);
    }

    public String getDownloadFilePathForRead() {
        int downloadType = getDownloadType();
        if (downloadType == 1) {
            String microThumbnailFile = this.dbCloud.getMicroThumbnailFile();
            return new File(microThumbnailFile).exists() ? microThumbnailFile : DownloadPathHelper.getFilePathForRead(StorageUtils.getMicroThumbnailDirectories(GalleryApp.sGetAndroidContext()), getFileName());
        } else if (downloadType == 2) {
            String thumbnailFile = this.dbCloud.getThumbnailFile();
            return new File(thumbnailFile).exists() ? thumbnailFile : DownloadPathHelper.getFilePathForRead(DownloadPathHelper.getDownloadFolderRelativePath(this.dbCloud), getFileName());
        } else if (downloadType != 3 && downloadType != 4) {
            return "";
        } else {
            String localFile = this.dbCloud.getLocalFile();
            if (isOriginFileValidate(localFile)) {
                return localFile;
            }
            String filePathForRead = DownloadPathHelper.getFilePathForRead(DownloadPathHelper.getDownloadFolderRelativePath(this.dbCloud), getFileName());
            return isOriginFileValidate(filePathForRead) ? filePathForRead : "";
        }
    }

    public String getDownloadFilePathForWrite() {
        String fileName = getFileName();
        if (getDownloadType() == 3 && (this.dbCloud.isUbiFocus() || this.dbCloud.isSubUbiFocus())) {
            fileName = LocalUbifocus.createInnerFileName(fileName, UbiIndexMapper.cloudToLocal(this.dbCloud.getSubUbiIndex(), this.dbCloud.getSubUbiImageCount() + 1), this.dbCloud.getSubUbiImageCount() + 1);
        }
        return getFolderPathForWrite() + File.separator + fileName;
    }

    public String getDownloadTempFilePath() {
        int downloadType = getDownloadType();
        if (downloadType != 1) {
            return downloadType != 2 ? (downloadType == 3 || downloadType == 4) ? FileUtils.concat(StorageUtils.getOriginTempDirectory(), this.dbCloud.getSha1()) : "" : FileUtils.concat(StorageUtils.getThumbnailTempDirectory(), this.dbCloud.getSha1());
        }
        return getDownloadFilePathForWrite() + ".temp";
    }

    public String getFileName() {
        int downloadType = getDownloadType();
        if (downloadType == 1) {
            return isSecretItem() ? this.dbCloud.getSha1ThumbnailSA() : this.dbCloud.getSha1Thumbnail();
        }
        if (downloadType == 2) {
            return isSecretItem() ? this.dbCloud.getSha1ThumbnailSA() : DownloadPathHelper.getThumbnailDownloadFileNameNotSecret(this.dbCloud);
        }
        if (downloadType == 3 || downloadType == 4) {
            return isSecretItem() ? this.dbCloud.getEncodedFileName() : DownloadPathHelper.getOriginDownloadFileNameNotSecret(this.dbCloud);
        }
        throw new UnsupportedOperationException("bad checktype, checktype=" + getDownloadType());
    }

    public String getFolderPathForWrite() {
        int downloadType = getDownloadType();
        return downloadType != 1 ? (downloadType == 2 || downloadType == 3 || downloadType == 4) ? DownloadPathHelper.getDownloadFolderPath(this.dbCloud) : "" : StorageUtils.getPriorMicroThumbnailDirectory();
    }

    public String getIdentity() {
        return this.dbCloud.getTagId();
    }

    public ArrayList<RequestItemBase> getItems() {
        ArrayList<RequestItemBase> arrayList = new ArrayList<>();
        arrayList.add(this);
        return arrayList;
    }

    public int getRequestLimitAGroup() {
        return 10;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b7  */
    public String getVerifiedDownloadFilePathForRead() {
        String downloadFilePathForRead = getDownloadFilePathForRead();
        int downloadType = getDownloadType();
        String str = "localFile";
        String str2 = "";
        if (!TextUtils.isEmpty(downloadFilePathForRead)) {
            if (downloadType != 1) {
                if (downloadType == 2) {
                    if (!downloadFilePathForRead.equalsIgnoreCase(this.dbCloud.getThumbnailFile())) {
                        if (isSecretItem() || TextUtils.equals(this.dbCloud.getSha1(), ExifUtil.getUserCommentSha1(downloadFilePathForRead))) {
                            this.dbCloud.setThumbnailFile(downloadFilePathForRead);
                            str2 = downloadFilePathForRead;
                            str = "thumbnailFile";
                            if (!TextUtils.isEmpty(str)) {
                            }
                            return str2;
                        }
                    }
                } else if ((downloadType == 3 || downloadType == 4) && !downloadFilePathForRead.equalsIgnoreCase(this.dbCloud.getLocalFile())) {
                    if (isSecretItem()) {
                        this.dbCloud.setLocalFile(downloadFilePathForRead);
                    } else if (TextUtils.equals(ExifUtil.getUserCommentSha1(downloadFilePathForRead), this.dbCloud.getSha1())) {
                        if (!downloadFilePathForRead.equalsIgnoreCase(this.dbCloud.getThumbnailFile())) {
                            this.dbCloud.setThumbnailFile(downloadFilePathForRead);
                            str = "thumbnailFile";
                            if (!TextUtils.isEmpty(str)) {
                            }
                            return str2;
                        }
                    } else if (TextUtils.equals(this.dbCloud.getSha1(), FileUtils.getSha1(downloadFilePathForRead))) {
                        this.dbCloud.setLocalFile(downloadFilePathForRead);
                    }
                    str2 = downloadFilePathForRead;
                    if (!TextUtils.isEmpty(str)) {
                    }
                    return str2;
                }
                str = str2;
                if (!TextUtils.isEmpty(str)) {
                }
                return str2;
            } else if (!downloadFilePathForRead.equalsIgnoreCase(this.dbCloud.getMicroThumbnailFile())) {
                str = "microthumbfile";
                str2 = downloadFilePathForRead;
                if (!TextUtils.isEmpty(str)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(str, downloadFilePathForRead);
                    CloudUtils.updateToLocalDB(this.dbCloud.getBaseUri(), contentValues, this.dbCloud.getId());
                }
                return str2;
            }
        }
        str = str2;
        str2 = downloadFilePathForRead;
        if (!TextUtils.isEmpty(str)) {
        }
        return str2;
    }

    public boolean isInSameAlbum(RequestItemBase requestItemBase) {
        RequestCloudItem requestCloudItem = (RequestCloudItem) requestItemBase;
        if (requestCloudItem.getDownloadType() != 1 || getDownloadType() != 1 || !requestCloudItem.dbCloud.isShareItem() || !this.dbCloud.isShareItem()) {
            return true;
        }
        return TextUtils.equals(requestCloudItem.dbCloud.getShareAlbumId(), this.dbCloud.getShareAlbumId());
    }

    public boolean isSecretItem() {
        return this.dbCloud.isSecretItem();
    }

    public boolean supportMultiRequest() {
        return getDownloadType() == 1;
    }
}
