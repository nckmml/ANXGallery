package com.miui.gallery.cloud.download;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.miui.gallery.preference.ThumbnailPreference;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.io.Closeable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class BatchDownloadUtil {
    private static final String[] PROJECTION = {"_id", "sha1"};

    /* renamed from: com.miui.gallery.cloud.download.BatchDownloadUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$sdk$download$DownloadType = new int[DownloadType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$sdk$download$DownloadType[DownloadType.THUMBNAIL_BATCH.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$sdk$download$DownloadType[DownloadType.ORIGIN_BATCH.ordinal()] = 2;
        }
    }

    public static void cleanDownloadedMark(Context context) {
        cleanDownloadedMark(context, DownloadType.THUMBNAIL_BATCH, false);
        cleanDownloadedMark(context, DownloadType.ORIGIN_BATCH, false);
        cleanDownloadedMark(context, DownloadType.THUMBNAIL_BATCH, true);
        cleanDownloadedMark(context, DownloadType.ORIGIN_BATCH, true);
    }

    public static void cleanDownloadedMark(Context context, DownloadType downloadType, boolean z) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$sdk$download$DownloadType[downloadType.ordinal()];
        String str = i != 1 ? i != 2 ? null : "localFile" : "thumbnailFile";
        if (str != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.putNull(str);
            Log.d("BatchDownloadUtil", "clean %s, result %d", str, Integer.valueOf(context.getContentResolver().update(getUri(z), contentValues, String.format(Locale.US, "%s=''", new Object[]{str}), (String[]) null)));
        }
    }

    private static String getFileSelection(DownloadType downloadType) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$sdk$download$DownloadType[downloadType.ordinal()];
        String str = "";
        if (i == 1) {
            str = str + String.format("(%s is null) AND ", new Object[]{"thumbnailFile"});
        } else if (i != 2) {
            return str;
        }
        return str + String.format("(%s is null) ", new Object[]{"localFile"});
    }

    private static String getTableName(boolean z) {
        return z ? "shareImage" : "cloud";
    }

    private static Uri getUri(boolean z) {
        return z ? GalleryContract.ShareImage.SHARE_URI : GalleryContract.Cloud.CLOUD_URI;
    }

    public static List<Uri> queryDownload(Context context, DownloadType downloadType, boolean z, int i) {
        String str;
        LinkedList linkedList = new LinkedList();
        Uri uri = getUri(z);
        if (i > 0) {
            str = String.format(Locale.US, "SELECT_id FROM %s WHERE (localFlag = 0 AND serverStatus = 'custom') AND serverType IN (1,2) AND %s LIMIT %s", new Object[]{getTableName(z), getFileSelection(downloadType), Integer.valueOf(i)});
        } else {
            str = String.format(Locale.US, "(localFlag = 0 AND serverStatus = 'custom') AND serverType IN (1,2) AND %s", new Object[]{getFileSelection(downloadType)});
        }
        String str2 = str;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, PROJECTION, str2, (String[]) null, "serverTag DESC");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    if (downloadType != DownloadType.THUMBNAIL_BATCH || !ThumbnailPreference.containsThumbnailKey(cursor.getString(1))) {
                        linkedList.add(ContentUris.withAppendedId(uri, cursor.getLong(0)));
                    }
                }
            }
        } catch (Exception e) {
            Log.e("BatchDownloadUtil", (Throwable) e);
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
        MiscUtil.closeSilently(cursor);
        return linkedList;
    }
}
