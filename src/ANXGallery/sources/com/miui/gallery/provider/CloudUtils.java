package com.miui.gallery.provider;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.model.SecretInfo;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.SafeDBUtil;
import java.util.ArrayList;

public class CloudUtils {
    private static long[] addToAlbum(Context context, int i, int i2, long j, boolean z, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("extra_type", i);
        bundle.putInt("extra_src_type", 0);
        bundle.putLongArray("extra_src_media_ids", jArr);
        bundle.putInt("extra_dup_type", i2);
        bundle.putBoolean("should_operate_sync", z);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_to_album", String.valueOf(j), bundle).getLongArray("ids");
    }

    private static long[] addToAlbum(Context context, int i, long j, ArrayList<Uri> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putInt("extra_type", i);
        bundle.putInt("extra_src_type", 1);
        bundle.putParcelableArrayList("extra_src_uris", arrayList);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_to_album", String.valueOf(j), bundle).getLongArray("ids");
    }

    public static long[] addToFavoritesById(Context context, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 1);
        bundle.putInt("add_remove_by", 1);
        bundle.putLongArray("extra_src_media_ids", jArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] addToFavoritesByPath(Context context, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 1);
        bundle.putInt("add_remove_by", 3);
        bundle.putStringArray("extra_src_paths", strArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] addToFavoritesBySha1(Context context, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 1);
        bundle.putInt("add_remove_by", 2);
        bundle.putStringArray("extra_src_sha1", strArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] addToSecret(Context context, ArrayList<Uri> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 1);
        bundle.putParcelableArrayList("extra_src_uris", arrayList);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_secret", (String) null, bundle).getLongArray("ids");
    }

    public static long[] addToSecret(Context context, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 1);
        bundle.putLongArray("extra_src_media_ids", jArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_secret", (String) null, bundle).getLongArray("ids");
    }

    private static void callMethodAsync(final Context context, final Uri uri, final String str, final Bundle bundle) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                Context context = context;
                if (context != null) {
                    context.getContentResolver().call(uri, str, (String) null, bundle);
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void callMethodSync(Context context, Uri uri, String str, Bundle bundle) {
        if (context != null) {
            context.getContentResolver().call(uri, str, (String) null, bundle);
        }
    }

    public static long[] copy(Context context, int i, long j, long... jArr) {
        return addToAlbum(context, 0, getDupType(i), j, false, jArr);
    }

    public static long[] copy(Context context, long j, ArrayList<Uri> arrayList) {
        return addToAlbum(context, 0, j, arrayList);
    }

    public static Bundle create(Context context, String str) {
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "create_album", str, (Bundle) null);
    }

    public static long[] deleteAlbum(Context context, int i, int i2, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putLongArray("extra_album_ids", jArr);
        bundle.putInt("extra_delete_options", getDeleteOptions(i));
        bundle.putInt("extra_delete_reason", i2);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "delete_album", (String) null, bundle).getLongArray("ids");
    }

    public static long[] deleteById(Context context, int i, int i2, int i3, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("delete_by", 0);
        bundle.putLongArray("extra_ids", jArr);
        bundle.putInt("extra_dup_type", getDupType(i));
        bundle.putInt("extra_delete_options", getDeleteOptions(i2));
        bundle.putInt("extra_delete_reason", i3);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "delete", (String) null, bundle).getLongArray("ids");
    }

    public static long[] deleteById(Context context, int i, long... jArr) {
        return deleteById(context, 0, 0, i, jArr);
    }

    public static long[] deleteByPath(Context context, int i, int i2, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("delete_by", 1);
        bundle.putStringArray("extra_paths", strArr);
        bundle.putInt("extra_delete_options", getDeleteOptions(i));
        bundle.putInt("extra_delete_reason", i2);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "delete", (String) null, bundle).getLongArray("ids");
    }

    public static long[] deleteByPath(Context context, int i, String... strArr) {
        return deleteByPath(context, 0, i, strArr);
    }

    public static int deleteBySha1(Context context, boolean z, int i, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("delete_by", 2);
        bundle.putStringArray("extra_sha1s", strArr);
        bundle.putBoolean("extra_keep_dup", z);
        bundle.putInt("extra_delete_reason", i);
        return (int) context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "delete", (String) null, bundle).getLong("count");
    }

    public static long[] deleteCloudByPath(Context context, int i, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("delete_by", 3);
        bundle.putStringArray("extra_paths", strArr);
        bundle.putInt("extra_delete_reason", i);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "delete", (String) null, bundle).getLongArray("ids");
    }

    public static long[] editPhotoTimeInfoBy(Context context, long j, long j2, String str, boolean z) {
        if (TextUtils.isEmpty(str) && j == 0) {
            return new long[0];
        }
        Bundle bundle = new Bundle();
        bundle.putLong("newtime", j2);
        bundle.putString("photo_path", str);
        bundle.putBoolean("is_favorites", z);
        bundle.putLong("photo_id", j);
        int i = 1;
        if (j == 0) {
            i = 2;
        }
        bundle.putInt("update_photo_by", i);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "update_photo_datetime", (String) null, bundle).getLongArray("ids");
    }

    public static void forceTop(Context context, Uri uri, long[] jArr) {
        Bundle bundle = new Bundle();
        bundle.putLongArray("album_id", jArr);
        callMethodAsync(context, uri, "force_top", bundle);
    }

    private static int getDeleteOptions(int i) {
        return (i == 0 || i != 1) ? 0 : 1;
    }

    private static int getDupType(int i) {
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? -1 : 2;
        }
        return 1;
    }

    public static SecretInfo getSecretInfo(Context context, long j, final SecretInfo secretInfo) {
        return (SecretInfo) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, new String[]{"_id", "localFile", "secretKey"}, "_id=?", new String[]{String.valueOf(j)}, (String) null, new SafeDBUtil.QueryHandler<SecretInfo>() {
            public SecretInfo handle(Cursor cursor) {
                if (cursor != null && cursor.moveToFirst()) {
                    secretInfo.mSecretPath = cursor.getString(cursor.getColumnIndex("localFile"));
                    secretInfo.mSecretKey = cursor.getBlob(cursor.getColumnIndex("secretKey"));
                }
                return secretInfo;
            }
        });
    }

    public static long[] move(Context context, int i, long j, boolean z, long... jArr) {
        return addToAlbum(context, 1, getDupType(i), j, z, jArr);
    }

    public static long[] move(Context context, long j, ArrayList<Uri> arrayList) {
        return addToAlbum(context, 1, j, arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    public static String queryAddressByCloudId(Context context, long j) {
        Cursor cursor = null;
        if (j == 0) {
            return null;
        }
        try {
            Cursor query = context.getContentResolver().query(ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_URI, j), new String[]{"address"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(0);
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043  */
    public static String queryAlbumNameByAlbumId(Context context, long j) {
        Cursor cursor = null;
        if (j <= 0) {
            return null;
        }
        try {
            Cursor query = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"fileName"}, "_id=?", new String[]{String.valueOf(j)}, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(0);
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static long[] removeFromFavoritesById(Context context, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 2);
        bundle.putInt("add_remove_by", 1);
        bundle.putLongArray("extra_src_media_ids", jArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] removeFromFavoritesByPath(Context context, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 2);
        bundle.putInt("add_remove_by", 3);
        bundle.putStringArray("extra_src_paths", strArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] removeFromFavoritesBySha1(Context context, String... strArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 2);
        bundle.putInt("add_remove_by", 2);
        bundle.putStringArray("extra_src_sha1", strArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_favorite", (String) null, bundle).getLongArray("ids");
    }

    public static long[] removeFromSecret(Context context, long j, long... jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("operation_type", 2);
        bundle.putLongArray("extra_src_media_ids", jArr);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "add_remove_secret", String.valueOf(j), bundle).getLongArray("ids");
    }

    public static Bundle renameAlbum(Context context, long j, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("album_id", j);
        return context.getContentResolver().call(GalleryContract.AUTHORITY_URI, "rename_album", str, bundle);
    }

    public static void unforceTop(Context context, Uri uri, long[] jArr) {
        Bundle bundle = new Bundle();
        bundle.putLongArray("album_id", jArr);
        callMethodAsync(context, uri, "unforce_top", bundle);
    }

    public static void updateAlbumAttributes(Context context, Uri uri, long j, long j2, boolean z, boolean z2) {
        updateAlbumAttributes(context, uri, new long[]{j}, j2, z, z2);
    }

    public static void updateAlbumAttributes(Context context, Uri uri, long[] jArr, long j, boolean z, boolean z2) {
        updateAlbumAttributes(context, uri, jArr, j, z, z2, true);
    }

    public static boolean updateAlbumAttributes(Context context, Uri uri, long[] jArr, long j, boolean z, boolean z2, boolean z3) {
        Bundle bundle = new Bundle();
        bundle.putLongArray("album_id", jArr);
        bundle.putLong("attributes_bit", j);
        bundle.putBoolean("set", z);
        bundle.putBoolean("manual", z2);
        if (z3) {
            callMethodAsync(context, uri, "set_album_attributes", bundle);
            return true;
        }
        callMethodSync(context, uri, "set_album_attributes", bundle);
        return true;
    }
}
