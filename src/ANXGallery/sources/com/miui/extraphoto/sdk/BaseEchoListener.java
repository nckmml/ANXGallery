package com.miui.extraphoto.sdk;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.miui.extraphoto.sdk.IEchoListener;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.SafeDBUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseEchoListener extends IEchoListener.Stub {
    private static final String[] UPDATE_PROJECTION = {"*"};

    /* access modifiers changed from: private */
    public boolean isDeleted(int i) {
        return i == -1 || i == 2 || i == 5;
    }

    /* access modifiers changed from: private */
    public void putValue(Cursor cursor, int i, ContentValues contentValues) {
        int type = cursor.getType(i);
        if (type == 1) {
            contentValues.put(cursor.getColumnName(i), cursor.getString(i));
        } else if (type == 2) {
            contentValues.put(cursor.getColumnName(i), Double.valueOf(cursor.getDouble(i)));
        } else if (type == 3) {
            contentValues.put(cursor.getColumnName(i), cursor.getString(i));
        } else if (type == 4) {
            contentValues.put(cursor.getColumnName(i), cursor.getBlob(i));
        }
    }

    private String updateDataBase(final String str) {
        AnonymousClass3 r6 = new SafeDBUtil.QueryHandler<String>() {
            public String handle(Cursor cursor) {
                String str;
                boolean z;
                Cursor cursor2 = cursor;
                if (cursor2 != null && cursor.moveToFirst()) {
                    long j = cursor2.getLong(cursor2.getColumnIndex("_id"));
                    String string = cursor2.getString(cursor2.getColumnIndex("serverId"));
                    if (!BaseEchoListener.this.isDeleted(cursor2.getInt(cursor2.getColumnIndex("localFlag")))) {
                        if (TextUtils.isEmpty(string)) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("_size", Long.valueOf(new File(str).length()));
                            SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), MediaStore.Files.getContentUri("external"), contentValues, "_data=?", new String[]{str});
                            contentValues.clear();
                            contentValues.put("sha1", FileUtils.getSha1(str));
                            contentValues.put("size", Long.valueOf(FileUtils.getFileSize(str)));
                            SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, contentValues, "_id=" + j, (String[]) null);
                            return str;
                        }
                        String format = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss'_STEREO.jpg'").format(new Date(cursor2.getLong(cursor2.getColumnIndex("mixedDateTime")) + 10000));
                        File file = new File(str);
                        File file2 = new File(file.getParentFile(), format);
                        String absolutePath = file2.getAbsolutePath();
                        File file3 = file;
                        if (FileUtils.move(file, file2)) {
                            String str2 = "BaseEchoListener";
                            ContentValues contentValues2 = new ContentValues();
                            long j2 = j;
                            contentValues2.put("_data", absolutePath);
                            contentValues2.put("_size", Long.valueOf(file2.length()));
                            SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), MediaStore.Files.getContentUri("external"), contentValues2, "_data like ?", new String[]{str});
                            contentValues2.clear();
                            int columnCount = cursor.getColumnCount();
                            for (int i = 0; i < columnCount; i++) {
                                BaseEchoListener.this.putValue(cursor2, i, contentValues2);
                            }
                            contentValues2.put("size", Long.valueOf(FileUtils.getFileSize(absolutePath)));
                            contentValues2.put("sha1", FileUtils.getSha1(absolutePath));
                            contentValues2.put("localFlag", 8);
                            contentValues2.putNull("_id");
                            contentValues2.putNull("serverId");
                            contentValues2.putNull("groupId");
                            contentValues2.putNull("serverStatus");
                            contentValues2.putNull("serverTag");
                            contentValues2.put("fileName", format);
                            contentValues2.put("title", FileUtils.getFileTitle(format));
                            contentValues2.put("localFile", absolutePath);
                            Uri safeInsert = SafeDBUtil.safeInsert(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, contentValues2);
                            if (safeInsert == null || ContentUris.parseId(safeInsert) <= 0) {
                                str = null;
                                z = false;
                            } else {
                                contentValues2.clear();
                                contentValues2.put("localFlag", 2);
                                str = null;
                                SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, contentValues2, "_id=" + j2, (String[]) null);
                                z = true;
                            }
                            if (z) {
                                return absolutePath;
                            }
                            Log.d(str2, "insert file fail %s", (Object) absolutePath);
                            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.ORIGINAL, absolutePath);
                        } else {
                            str = null;
                            Log.d("BaseEchoListener", "server rename file fail %s", (Object) str);
                            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.ORIGINAL, file3);
                        }
                        return str;
                    }
                }
                str = null;
                return str;
            }
        };
        return (String) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, UPDATE_PROJECTION, "localFile like ?", new String[]{str}, (String) null, r6);
    }

    public final void onEchoEnd(String str, String str2, final boolean z) throws RemoteException {
        final String str3 = null;
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (FileUtils.move(new File(str2), new File(str))) {
                Log.d("BaseEchoListener", "move file cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                long currentTimeMillis2 = System.currentTimeMillis();
                String updateDataBase = updateDataBase(str);
                Log.d("BaseEchoListener", "update dataBase cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                if (!TextUtils.isEmpty(updateDataBase)) {
                    Log.d("BaseEchoListener", "update db success %s, %s", updateDataBase, str2);
                } else {
                    z = false;
                }
                str3 = updateDataBase;
            } else {
                Log.d("BaseEchoListener", "remove file fail %s, %s", str2, str);
                z = false;
            }
        } else {
            Log.d("BaseEchoListener", "echo file fail %s, %s", str, str2);
        }
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                BaseEchoListener.this.onEnd(str3, z);
            }
        });
    }

    public final void onEchoStart() throws RemoteException {
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                BaseEchoListener.this.onStart();
            }
        });
    }

    public abstract void onEnd(String str, boolean z);

    public abstract void onStart();
}
