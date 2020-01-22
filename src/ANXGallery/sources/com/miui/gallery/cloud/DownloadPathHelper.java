package com.miui.gallery.cloud;

import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBShareAlbum;
import com.miui.gallery.data.DBShareSubUbiImage;
import com.miui.gallery.util.ExtraTextUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StorageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadPathHelper {
    public static String addPostfixToFileName(String str, String str2) {
        String str3 = FileUtils.getFileNameWithoutExtension(str) + "_" + str2;
        String extension = FileUtils.getExtension(str);
        if (TextUtils.isEmpty(extension)) {
            return str3;
        }
        return str3 + "." + extension;
    }

    public static List<String> getAllFilePathForRead(String[] strArr, String str) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null) {
            for (String str2 : strArr) {
                String str3 = str2 + File.separator + str;
                if (new File(str3).exists()) {
                    arrayList.add(str3);
                }
            }
        }
        return arrayList;
    }

    public static String getDownloadFileNameNotSecret(DBImage dBImage, String str) {
        if (dBImage == null) {
            Log.e("DownloadPathHelper", "dbImage is null!");
            return "";
        } else if (!dBImage.isShareItem()) {
            return str;
        } else {
            if (!dBImage.isSubUbiFocus() || (dBImage = CloudUtils.getItem(GalleryCloudUtils.SHARE_IMAGE_URI, GalleryApp.sGetAndroidContext(), "_id", ((DBShareSubUbiImage) dBImage).getUbiLocalId())) != null) {
                String albumId = dBImage.getAlbumId();
                if (TextUtils.isEmpty(albumId)) {
                    DBShareAlbum dBShareAlbumByLocalId = CloudUtils.getDBShareAlbumByLocalId(dBImage.getLocalGroupId());
                    if (dBShareAlbumByLocalId == null) {
                        Log.e("DownloadPathHelper", "dbShareAlbum should not be null!");
                        return "";
                    }
                    albumId = dBShareAlbumByLocalId.getAlbumId();
                }
                return addPostfixToFileName(str, albumId);
            }
            Log.e("DownloadPathHelper", "mainDBImage is null!");
            return "";
        }
    }

    public static String getDownloadFolderPath(DBImage dBImage) {
        String downloadFolderRelativePath = getDownloadFolderRelativePath(dBImage);
        return downloadFolderRelativePath == null ? "" : StorageUtils.getSafePathInPriorStorage(downloadFolderRelativePath);
    }

    public static String getDownloadFolderRelativePath(DBImage dBImage) {
        String str;
        String str2;
        String str3;
        if (dBImage == null) {
            Log.e("DownloadPathHelper", "dbImage is null!");
            return null;
        }
        if (dBImage.isShareItem()) {
            str = "MIUI/Gallery/cloud/sharer";
        } else {
            if (dBImage.getServerType() != 0 || TextUtils.isEmpty(dBImage.getLocalFile())) {
                if (dBImage.getServerType() == 0) {
                    str3 = dBImage.getId();
                    str2 = dBImage.getServerId();
                } else {
                    str3 = dBImage.getLocalGroupId();
                    str2 = String.valueOf(dBImage.getGroupId());
                }
                if (CloudTableUtils.isCameraGroup(str2)) {
                    str = CloudUtils.getCameraLocalFile();
                } else if (CloudTableUtils.isScreenshotGroup(str2)) {
                    str = CloudUtils.getScreenshotsLocalFile();
                } else if (CloudTableUtils.isSecretAlbum(str2, str3)) {
                    str = StorageUtils.DIRECTORY_SECRET_ALBUM_PATH;
                } else if (dBImage.getServerType() != 0) {
                    return getDownloadFolderRelativePath(CloudUtils.getItem(GalleryCloudUtils.CLOUD_URI, GalleryApp.sGetAndroidContext(), "_id", str3));
                } else {
                    str = CloudUtils.getOwnerAlbumLocalFile(dBImage.getFileName(), dBImage.getAppKey());
                }
            } else {
                str = dBImage.getLocalFile();
            }
            if (TextUtils.equals(str, StorageUtils.KEY_FOR_EMPTY_RELATIVE_PATH)) {
                return "";
            }
        }
        if (!dBImage.isSubUbiFocus()) {
            return str;
        }
        return str + File.separator + ".ubifocus";
    }

    public static String getFilePathForRead(String str, String str2) {
        return str == null ? "" : getFilePathForRead(StorageUtils.getPathsInExternalStorage(GalleryApp.sGetAndroidContext(), str), str2);
    }

    public static String getFilePathForRead(String[] strArr, String str) {
        if (strArr == null) {
            return "";
        }
        for (String str2 : strArr) {
            String str3 = str2 + File.separator + str;
            if (new File(str3).exists()) {
                return str3;
            }
        }
        return "";
    }

    public static String getFolderRelativePathInCloud(String str) {
        return ("MIUI/Gallery/cloud/owner" + File.separator + str).toLowerCase();
    }

    public static String getOriginDownloadFileNameNotSecret(DBImage dBImage) {
        if (dBImage != null) {
            return getDownloadFileNameNotSecret(dBImage, dBImage.getFileName());
        }
        Log.e("DownloadPathHelper", "dbImage is null!");
        return "";
    }

    public static String getShareFolderRelativePathInCloud() {
        return "MIUI/Gallery/cloud/sharer";
    }

    public static String getThumbnailDownloadFileNameNotSecret(DBImage dBImage) {
        if (dBImage == null) {
            Log.e("DownloadPathHelper", "dbImage is null!");
            return "";
        }
        String fileNameWithoutExtension = FileUtils.getFileNameWithoutExtension(getDownloadFileNameNotSecret(dBImage, dBImage.getFileName()));
        return fileNameWithoutExtension + ".jpg";
    }

    public static boolean isShareFolderRelativePath(String str) {
        return ExtraTextUtils.startsWithIgnoreCase(str, "MIUI/Gallery/cloud/sharer");
    }
}
