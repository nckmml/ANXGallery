package com.miui.gallery.provider.cloudmanager;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StorageUtils;
import java.io.File;

class AlbumFileHandleJob {
    private String mId;
    private int mLocalFlag;
    private String mRelativeDir;

    private AlbumFileHandleJob(String str, String str2, int i) {
        this.mId = str;
        this.mRelativeDir = str2;
        this.mLocalFlag = i;
    }

    public static AlbumFileHandleJob from(Cursor cursor) {
        String string = cursor.getString(0);
        String string2 = cursor.getString(7);
        if (TextUtils.isEmpty(string2)) {
            string2 = DownloadPathHelper.getFolderRelativePathInCloud(cursor.getString(6));
        }
        return new AlbumFileHandleJob(string, string2, cursor.getInt(2));
    }

    public boolean run(Context context) {
        File file = new File(StorageUtils.getSafePathInPriorStorage(this.mRelativeDir));
        int i = this.mLocalFlag;
        if (i == -1) {
            Log.d("CloudManager.AlbumFileHandleJob", "local deletion, just delete from db");
            context.getContentResolver().delete(GalleryContract.Cloud.CLOUD_URI, "_id=?", new String[]{this.mId});
        } else if (i == 2) {
            Log.d("CloudManager.AlbumFileHandleJob", "cloud deletion, skip folder, db's deletion already done");
        } else if (i == 7 || i == 8) {
            Log.d("CloudManager.AlbumFileHandleJob", "make folder: %s", (Object) file);
            if (!file.exists()) {
                Log.d("CloudManager.AlbumFileHandleJob", "folder maked: %b", (Object) Boolean.valueOf(file.mkdirs()));
            } else {
                Log.w("CloudManager.AlbumFileHandleJob", "folder exist, skip.");
            }
        }
        return false;
    }
}
