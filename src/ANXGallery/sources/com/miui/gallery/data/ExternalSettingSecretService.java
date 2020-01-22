package com.miui.gallery.data;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.common.collect.Maps;
import com.miui.gallery.data.IExternalSettingSecretInterface;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StorageUtils;
import java.io.File;
import java.util.Map;

public class ExternalSettingSecretService extends Service {

    private class Deletor extends IExternalSettingSecretInterface.Stub {
        private Map<String, FileData> mPendingDeleteMap;

        private Deletor() {
            this.mPendingDeleteMap = Maps.newHashMap();
        }

        private void deleteInGroup(String str, String str2, int i) {
            int i2 = 0;
            Cursor query = ExternalSettingSecretService.this.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, String.format("%s AND %s = '%s' AND %s = '%s' AND %s = %s", new Object[]{"(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", "sha1", str, "fileName", str2, "localGroupId", String.valueOf(i)}), (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        long[] jArr = new long[query.getCount()];
                        for (int i3 = 0; i3 < query.getCount(); i3++) {
                            query.moveToPosition(i3);
                            jArr[i3] = query.getLong(0);
                        }
                        long[] deleteById = CloudUtils.deleteById(ExternalSettingSecretService.this, 53, jArr);
                        if (deleteById != null) {
                            i2 = deleteById.length;
                        }
                        Log.i("ExternalSettingSecretService", "delete %d rows from cloud", (Object) Integer.valueOf(i2));
                    }
                } finally {
                    query.close();
                }
            }
        }

        private int getLocalGroupId(String str) {
            Cursor query = ExternalSettingSecretService.this.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, "localFile like ? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{str}, (String) null);
            if (query == null) {
                return -1;
            }
            try {
                if (query.moveToNext()) {
                    return query.getInt(0);
                }
                query.close();
                return -1;
            } finally {
                query.close();
            }
        }

        private void preSettingSecret(String str) {
            String str2;
            Log.i("ExternalSettingSecretService", "pre-process path: " + str);
            if (!TextUtils.isEmpty(str)) {
                if (!new File(str).exists()) {
                    Log.i("ExternalSettingSecretService", "skip non-exist file: " + str);
                    return;
                }
                String mimeType = FileMimeUtil.getMimeType(str);
                if (FileMimeUtil.isImageFromMimeType(mimeType) || FileMimeUtil.isVideoFromMimeType(mimeType)) {
                    String relativePath = StorageUtils.getRelativePath(ExternalSettingSecretService.this, str);
                    if (TextUtils.isEmpty(relativePath) || !relativePath.startsWith("MIUI/Gallery/cloud/sharer")) {
                        String relativePath2 = StorageUtils.getRelativePath(ExternalSettingSecretService.this, FileUtils.getParentFolderPath(str));
                        if (TextUtils.isEmpty(relativePath2)) {
                            Log.i("ExternalSettingSecretService", "cannot get folder path through %s, skip ", (Object) str);
                            return;
                        }
                        int localGroupId = getLocalGroupId(relativePath2);
                        if (localGroupId == -1) {
                            Log.i("ExternalSettingSecretService", "album '%s' not exists, skip ", relativePath2, str);
                            return;
                        }
                        try {
                            String fileTitle = FileUtils.getFileTitle(FileUtils.getFileName(str));
                            ExifUtil.UserCommentData userCommentData = ExifUtil.getUserCommentData(str);
                            String str3 = null;
                            if (userCommentData != null) {
                                str3 = userCommentData.getFileName(fileTitle);
                                str2 = userCommentData.getSha1();
                            } else {
                                str2 = null;
                            }
                            this.mPendingDeleteMap.put(str, new FileData(FileUtils.getSha1(str), str2, str3, localGroupId));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        throw new IllegalArgumentException("unsupported folder: MIUI/Gallery/cloud/sharer");
                    }
                } else {
                    Log.i("ExternalSettingSecretService", "skip non-media file: " + str);
                }
            }
        }

        public void onFinishSettingSecret(String[] strArr, String[] strArr2) {
            if (strArr != null) {
                for (String str : strArr) {
                    FileData remove = this.mPendingDeleteMap.remove(str);
                    if (remove != null) {
                        Log.i("ExternalSettingSecretService", "about to delete: " + str);
                        String str2 = remove.mSha1;
                        String str3 = remove.mSha1InExif;
                        String str4 = remove.mFileNameInExif;
                        int i = remove.mLocalGroupId;
                        if (!TextUtils.isEmpty(str3)) {
                            if (TextUtils.isEmpty(str4)) {
                                Log.w("ExternalSettingSecretService", "failed to get fileName from exif of " + str);
                                deleteInGroup(str3, FileUtils.getFileName(str), i);
                            } else {
                                deleteInGroup(str3, str4, i);
                            }
                        }
                        deleteInGroup(str2, FileUtils.getFileName(str), i);
                    }
                }
            }
            if (strArr2 != null && !this.mPendingDeleteMap.isEmpty()) {
                for (String remove2 : strArr2) {
                    this.mPendingDeleteMap.remove(remove2);
                }
            }
            if (!this.mPendingDeleteMap.isEmpty()) {
                throw new IllegalStateException("setting secret results of paths in " + this.mPendingDeleteMap + " must be provided!");
            }
        }

        public void preSettingSecret(String[] strArr) {
            if (!this.mPendingDeleteMap.isEmpty()) {
                throw new IllegalStateException("we are not done with " + this.mPendingDeleteMap + " yet!!");
            } else if (strArr != null) {
                for (String preSettingSecret : strArr) {
                    preSettingSecret(preSettingSecret);
                }
            }
        }
    }

    private static final class FileData {
        final String mFileNameInExif;
        final int mLocalGroupId;
        final String mSha1;
        final String mSha1InExif;

        FileData(String str, String str2, String str3, int i) {
            this.mSha1 = str;
            this.mSha1InExif = str2;
            this.mFileNameInExif = str3;
            this.mLocalGroupId = i;
        }
    }

    public IBinder onBind(Intent intent) {
        return new Deletor();
    }
}
