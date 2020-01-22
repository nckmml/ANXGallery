package com.miui.gallery.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.SafeDBUtil;
import java.util.Locale;

public class GalleryOpenApiProvider extends ContentProvider {
    protected static final UriMatcher sUriMatcher = new UriMatcher(-1);

    static {
        sUriMatcher.addURI("com.miui.gallery.open_api", "check_thumbnail", 1);
        sUriMatcher.addURI("com.miui.gallery.open_api", "search_status", 2);
        sUriMatcher.addURI("com.miui.gallery.open_api", "secret_album", 3);
    }

    private int getSecretAlbumUnsyncedCount() {
        Integer num = (Integer) SafeDBUtil.safeQuery(getContext(), GalleryContract.Cloud.CLOUD_URI, new String[]{"count(_id)"}, String.format(Locale.US, "(%s) AND (%s) AND (%s = %d OR %s = %d)", new Object[]{CloudUtils.SELECTION_OWNER_NEED_SYNC, "(localGroupId=-1000)", "localFlag", 7, "localFlag", 8}), (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Integer>() {
            public Integer handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToFirst()) {
                    return null;
                }
                return Integer.valueOf(cursor.getInt(0));
            }
        });
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int match = sUriMatcher.match(uri);
        if (match == 1) {
            return CheckThumbnailHelper.checkThumbnail(getContext(), strArr2, uri.getBooleanQueryParameter("strict_mode", false));
        } else if (match == 2) {
            int openApiSearchStatus = AIAlbumStatusHelper.getOpenApiSearchStatus();
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"status"}, 1);
            matrixCursor.addRow(new Object[]{Integer.valueOf(openApiSearchStatus)});
            return matrixCursor;
        } else if (match != 3) {
            return null;
        } else {
            int secretAlbumUnsyncedCount = getSecretAlbumUnsyncedCount();
            MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"unsynced_count"}, 1);
            matrixCursor2.addRow(new Object[]{Integer.valueOf(secretAlbumUnsyncedCount)});
            return matrixCursor2;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
