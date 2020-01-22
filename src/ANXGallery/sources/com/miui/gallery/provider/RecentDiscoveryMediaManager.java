package com.miui.gallery.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.discovery.DiscoveryMessageManager;
import com.miui.gallery.discovery.RecentDiscoveryMessageOperator;
import com.miui.gallery.model.BaseAlbumCover;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import miui.date.DateUtils;

public class RecentDiscoveryMediaManager {
    private static final String ALIAS_MICRO_VALID = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"microthumbfile", "microthumbfile"});
    private static final String ALIAS_THUMBNAIL_VALID = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"thumbnailFile", "thumbnailFile"});
    private static final String COVER_ALIAS_DISPLAYABLE = (" CASE WHEN " + ALIAS_MICRO_VALID + " THEN " + "microthumbfile" + " " + "WHEN " + ALIAS_THUMBNAIL_VALID + " THEN " + "thumbnailFile" + " " + "ELSE " + "localFile" + " " + "END ");
    private static final String SQL_ALBUM_COVER = ("_id AS cover_id, " + COVER_ALIAS_DISPLAYABLE + " AS " + "cover_path" + ", " + "sha1" + " AS " + "cover_sha1" + ", " + "alias_sync_state" + " AS " + "cover_sync_state" + ", " + "size" + " AS " + "cover_size");
    private static final String SQL_INCLUDE_LOCAL = (" AND " + InternalContract.Cloud.ALIAS_LOCAL_MEDIA + " ");
    private static Map<String, String> sRecentDiscoveryMediaMap = new HashMap();

    private static class Group {
        public long albumId;
        public List<Integer> cursorPosList = new ArrayList();
        public long endDate;
        public int julianday;
        public long startDate;

        public Group(long j, int i, long j2, int i2) {
            this.albumId = j2;
            this.startDate = j;
            this.endDate = j;
            this.julianday = i;
            this.cursorPosList.add(Integer.valueOf(i2));
        }
    }

    public static class RecentMediaEntry {
        /* access modifiers changed from: private */
        public final long mAlbumId;
        private final long mDateModified;
        private final long mMediaId;
        private final String mThumbPath;

        public RecentMediaEntry(long j, long j2, String str, long j3) {
            this.mAlbumId = j;
            this.mMediaId = j2;
            this.mThumbPath = str;
            this.mDateModified = j3;
        }

        public long getDateModified() {
            return this.mDateModified;
        }

        public long getMediaId() {
            return this.mMediaId;
        }

        public String getThumbPath() {
            return this.mThumbPath;
        }
    }

    static {
        sRecentDiscoveryMediaMap.put("_id", "mediaId");
        sRecentDiscoveryMediaMap.put("sha1", "sha1");
        sRecentDiscoveryMediaMap.put("localGroupId", "localGroupId");
        sRecentDiscoveryMediaMap.put("microthumbfile", "microthumbfile");
        sRecentDiscoveryMediaMap.put("thumbnailFile", "thumbnailFile");
        sRecentDiscoveryMediaMap.put("localFile", "localFile");
        sRecentDiscoveryMediaMap.put("serverType", "serverType");
        sRecentDiscoveryMediaMap.put("title", "title");
        sRecentDiscoveryMediaMap.put("duration", "duration");
        sRecentDiscoveryMediaMap.put("description", "description");
        sRecentDiscoveryMediaMap.put("size", "size");
        sRecentDiscoveryMediaMap.put("mimeType", "mimeType");
        sRecentDiscoveryMediaMap.put("location", "location");
        sRecentDiscoveryMediaMap.put("exifGPSLatitude", "exifGPSLatitude");
        sRecentDiscoveryMediaMap.put("exifGPSLatitudeRef", "exifGPSLatitudeRef");
        sRecentDiscoveryMediaMap.put("exifGPSLongitude", "exifGPSLongitude");
        sRecentDiscoveryMediaMap.put("exifGPSLongitudeRef", "exifGPSLongitudeRef");
        sRecentDiscoveryMediaMap.put("alias_micro_thumbnail", InternalContract.RecentDiscoveredMedia.ALIAS_MICRO_THUMBNAIL);
        sRecentDiscoveryMediaMap.put("alias_create_time", " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END ");
        sRecentDiscoveryMediaMap.put("alias_create_date", "STRFTIME('%Y%m%d', CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END /1000, 'unixepoch', 'localtime')");
        sRecentDiscoveryMediaMap.put("alias_sync_state", " CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END ");
        sRecentDiscoveryMediaMap.put("secretKey", "secretKey");
        sRecentDiscoveryMediaMap.put("exifImageWidth", "exifImageWidth");
        sRecentDiscoveryMediaMap.put("exifImageLength", "exifImageLength");
        sRecentDiscoveryMediaMap.put("serverId", "serverId");
        sRecentDiscoveryMediaMap.put("serverTag", "serverTag");
        sRecentDiscoveryMediaMap.put("creatorId", "creatorId");
        sRecentDiscoveryMediaMap.put("dateAdded", "dateAdded");
        sRecentDiscoveryMediaMap.put("alias_julianday", "CAST(JULIANDAY(dateModified/1000, 'unixepoch', 'localtime', 'start of day') AS int)");
        sRecentDiscoveryMediaMap.put("dateModified", "dateModified");
        sRecentDiscoveryMediaMap.put("babyInfoJson", "babyInfoJson");
        sRecentDiscoveryMediaMap.put("alias_clear_thumbnail", InternalContract.RecentDiscoveredMedia.ALIAS_CLEAR_THUMBNAIL);
        sRecentDiscoveryMediaMap.put("alias_is_favorite", "isFavorite");
        sRecentDiscoveryMediaMap.put("specialTypeFlags", "specialTypeFlags");
        sRecentDiscoveryMediaMap.put("alias_sort_time", "dateModified");
        sRecentDiscoveryMediaMap.put("exifOrientation", "exifOrientation");
        sRecentDiscoveryMediaMap.put("burst_group_id", MovieStatUtils.DOWNLOAD_SUCCESS);
    }

    private static void addOneRowToMatrixCursor(Cursor cursor, MatrixCursor matrixCursor, String[] strArr) {
        int length = strArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            int type = cursor.getType(i);
            if (type == 0) {
                objArr[i] = null;
            } else if (type == 1) {
                objArr[i] = Long.valueOf(cursor.getLong(i));
            } else if (type == 2) {
                objArr[i] = Double.valueOf(cursor.getDouble(i));
            } else if (type == 3) {
                objArr[i] = cursor.getString(i);
            } else if (type == 4) {
                objArr[i] = cursor.getBlob(i);
            }
        }
        matrixCursor.addRow(objArr);
    }

    public static void cleanupInvalidRecords() {
        try {
            GalleryDBHelper.getInstance().getWritableDatabase().execSQL("DELETE FROM recentDiscoveredMedia WHERE mediaId IN  (SELECT mediaId FROM (SELECT mediaId, cloud._id AS _id, localFlag, serverStatus FROM (recentDiscoveredMedia LEFT OUTER JOIN cloud ON cloud._id = mediaId)) WHERE (localFlag IN (11, -1, 2) OR (localFlag=0 AND serverStatus<>'custom')) OR (_id IS NULL))");
        } catch (SQLException e) {
            Log.e("RecentDiscoveryMediaManager", "Something wrong happened when cleanup recent table: %s", (Object) e.getMessage());
            e.printStackTrace();
        }
    }

    private static void doInsertToRecent(Context context, boolean z, RecentMediaEntry... recentMediaEntryArr) {
        if (context != null && recentMediaEntryArr != null && recentMediaEntryArr.length > 0) {
            HashMap hashMap = new HashMap();
            for (RecentMediaEntry recentMediaEntry : recentMediaEntryArr) {
                if (recentMediaEntry.mAlbumId != -1000) {
                    String relativePath = StorageUtils.getRelativePath(context, FileUtils.getParentFolderPath(recentMediaEntry.getThumbPath()));
                    if (TextUtils.isEmpty(relativePath)) {
                        Log.w("RecentDiscoveryMediaManager", "Could't get album path for %s", recentMediaEntry.getThumbPath());
                        return;
                    }
                    List list = (List) hashMap.get(relativePath);
                    if (list == null) {
                        list = new LinkedList();
                        hashMap.put(relativePath, list);
                    }
                    list.add(recentMediaEntry);
                }
            }
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            for (Map.Entry entry : hashMap.entrySet()) {
                boolean equalsIgnoreCase = "DCIM/Camera".equalsIgnoreCase((String) entry.getKey());
                if (!z || (!equalsIgnoreCase && isAlbumInWhiteList((String) entry.getKey()))) {
                    List<RecentMediaEntry> list2 = (List) entry.getValue();
                    if (MiscUtil.isValid(list2)) {
                        linkedList.addAll(list2);
                        if (linkedList2.size() < 3 && isMediaCanShowInMessage(((RecentMediaEntry) list2.get(0)).mAlbumId)) {
                            for (RecentMediaEntry recentMediaEntry2 : list2) {
                                if (GalleryDateUtils.daysBeforeToday(recentMediaEntry2.getDateModified()) < 30) {
                                    linkedList2.add(recentMediaEntry2.getThumbPath());
                                    if (linkedList2.size() >= 3) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else if (!equalsIgnoreCase) {
                    recordNotInWhiteListAlbum((String) entry.getKey());
                }
            }
            int size = linkedList.size();
            if (size != 0) {
                ContentValues[] contentValuesArr = new ContentValues[size];
                for (int i = 0; i < size; i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mediaId", Long.valueOf(((RecentMediaEntry) linkedList.get(i)).getMediaId()));
                    contentValues.put("dateAdded", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("source", 0);
                    contentValuesArr[i] = contentValues;
                }
                context.getContentResolver().bulkInsert(GalleryContract.RecentDiscoveredMedia.URI, contentValuesArr);
                if (linkedList2.size() > 0) {
                    DiscoveryMessageManager.getInstance().saveMessage(context, 1, new RecentDiscoveryMessageOperator.RecentSaveParams(linkedList2.size(), linkedList2));
                }
            }
        }
    }

    private static Bundle generateAlbumCover(SQLiteDatabase sQLiteDatabase, int i, boolean z) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT " + SQL_ALBUM_COVER + " FROM " + getRecentDiscoveryTable(z) + " ORDER BY " + "dateModified" + " DESC ", (String[]) null);
        if (rawQuery == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            int columnIndex = rawQuery.getColumnIndex("cover_id");
            int columnIndex2 = rawQuery.getColumnIndex("cover_path");
            int columnIndex3 = rawQuery.getColumnIndex("cover_sha1");
            int columnIndex4 = rawQuery.getColumnIndex("cover_sync_state");
            int columnIndex5 = rawQuery.getColumnIndex("cover_size");
            while (rawQuery.moveToNext()) {
                BaseAlbumCover baseAlbumCover = new BaseAlbumCover();
                baseAlbumCover.coverId = rawQuery.getLong(columnIndex);
                baseAlbumCover.coverPath = rawQuery.getString(columnIndex2);
                baseAlbumCover.coverSha1 = rawQuery.getString(columnIndex3);
                baseAlbumCover.coverSyncState = rawQuery.getInt(columnIndex4);
                baseAlbumCover.coverSize = rawQuery.getLong(columnIndex5);
                arrayList.add(baseAlbumCover);
                if (arrayList.size() >= i) {
                    break;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("album_covers", arrayList);
            bundle.putInt("album_photo_count", rawQuery.getCount());
            return bundle;
        } finally {
            rawQuery.close();
        }
    }

    private static long getAlbumId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("localGroupId"));
    }

    private static long getDateModified(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("dateModified"));
    }

    private static long getDateModified(Cursor cursor, int i) {
        if (moveCursorToPosition(cursor, i)) {
            return cursor.getLong(cursor.getColumnIndex("dateModified"));
        }
        return 0;
    }

    private static String getDaysWithinSelection(int i) {
        return String.format(Locale.US, " AND ( CASE WHEN DATE(dateModified/1000, 'unixepoch', 'localtime') == DATE('now', 'localtime') THEN 0 ELSE CAST(JULIANDAY('now', 'localtime', 'start of day') - JULIANDAY(dateModified/1000, 'unixepoch', 'localtime') AS int) + 1 END < %d)", new Object[]{Integer.valueOf(i)});
    }

    private static String getExcludeBabyAlbumSelection() {
        return isInLocalMode() ? "AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND babyInfoJson IS NULL )) " : "";
    }

    private static String getExcludeHiddenAlbumSelection() {
        return isInShowHiddenMode() ? "" : " AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND attributes&16=0)) ";
    }

    private static String getIncludeLocalSelection() {
        return isInLocalMode() ? SQL_INCLUDE_LOCAL : "";
    }

    private static int getJulianday(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex("alias_julianday"));
    }

    private static String getNotInHiddenAlbumSelection(long j) {
        if (isInShowHiddenMode()) {
            return "";
        }
        return String.format(Locale.US, " AND (%s in (SELECT _id FROM cloud WHERE serverType=0 AND attributes&16=0))", new Object[]{Long.valueOf(j)});
    }

    private static String getNotSecretSelection(long j) {
        return String.format(Locale.US, "(%s !=-1000)", new Object[]{Long.valueOf(j)});
    }

    public static String getRecentDiscoveryTable(boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : sRecentDiscoveryMediaMap.entrySet()) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append((String) next.getValue());
            sb.append(" AS ");
            sb.append((String) next.getKey());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("(SELECT ");
        sb2.append(sb);
        sb2.append(" FROM (");
        sb2.append("recentDiscoveredMedia");
        sb2.append(" JOIN ");
        sb2.append("extended_cloud");
        sb2.append(" ON ");
        sb2.append("extended_cloud");
        sb2.append(".");
        sb2.append("_id");
        sb2.append(" = ");
        sb2.append("mediaId");
        sb2.append(")");
        sb2.append(" WHERE ");
        sb2.append("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))");
        sb2.append(" AND ");
        sb2.append("(localGroupId!=-1000)");
        sb2.append(getExcludeHiddenAlbumSelection());
        sb2.append(getIncludeLocalSelection());
        sb2.append(getExcludeBabyAlbumSelection());
        sb2.append(getDaysWithinSelection(30));
        if (z) {
            str = " GROUP BY " + "sha1";
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append(")");
        return sb2.toString();
    }

    private static void insertItemToGroupList(long j, int i, long j2, int i2, ArrayList<Group> arrayList) {
        long j3 = j;
        int i3 = i2;
        ArrayList<Group> arrayList2 = arrayList;
        if (MiscUtil.isValid(arrayList)) {
            boolean z = true;
            int size = arrayList.size() - 1;
            while (size >= 0) {
                Group group = arrayList2.get(size);
                if (group.endDate - j3 > 180000) {
                    if (!z || j2 != group.albumId) {
                        int i4 = i;
                    } else if (i == group.julianday) {
                        mergeItemToGroup(j, i3, group);
                        return;
                    }
                    arrayList2.add(new Group(j, i, j2, i2));
                    return;
                }
                int i5 = i;
                if (j2 == group.albumId) {
                    mergeItemToGroup(j, i3, group);
                    return;
                } else {
                    size--;
                    z = false;
                }
            }
            arrayList2.add(new Group(j, i, j2, i2));
            return;
        }
        int i6 = i;
        if (arrayList2 == null) {
            arrayList2 = new ArrayList<>();
        }
        arrayList2.add(new Group(j, i, j2, i2));
    }

    public static void insertToRecent(Context context, RecentMediaEntry... recentMediaEntryArr) {
        doInsertToRecent(context, true, recentMediaEntryArr);
    }

    public static void insertToRecentUnchecked(Context context, RecentMediaEntry... recentMediaEntryArr) {
        doInsertToRecent(context, false, recentMediaEntryArr);
    }

    private static boolean isAlbumInWhiteList(String str) {
        ArrayList<String> albumsInWhiteList = CloudControlStrategyHelper.getAlbumsInWhiteList();
        if (MiscUtil.isValid(albumsInWhiteList)) {
            Iterator<String> it = albumsInWhiteList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next != null && next.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        ArrayList<Pattern> albumWhiteListPatterns = CloudControlStrategyHelper.getAlbumWhiteListPatterns();
        if (!MiscUtil.isValid(albumWhiteListPatterns)) {
            return false;
        }
        for (Pattern matcher : albumWhiteListPatterns) {
            if (matcher.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInLocalMode() {
        return GalleryPreferences.LocalMode.isOnlyShowLocalPhoto();
    }

    private static boolean isInShowHiddenMode() {
        return GalleryPreferences.HiddenAlbum.isShowHiddenAlbum();
    }

    private static boolean isMediaCanShowInMessage(long j) {
        Cursor rawQuery = GalleryDBHelper.getInstance().getReadableDatabase().rawQuery("SELECT " + getNotSecretSelection(j) + getNotInHiddenAlbumSelection(j), (String[]) null);
        boolean z = false;
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    if (rawQuery.getInt(0) != 0) {
                        z = true;
                    }
                    return z;
                }
                rawQuery.close();
            } finally {
                rawQuery.close();
            }
        }
        return false;
    }

    private static void mergeItemToGroup(long j, int i, Group group) {
        group.endDate = j;
        group.cursorPosList.add(Integer.valueOf(i));
    }

    private static boolean moveCursorToPosition(Cursor cursor, int i) {
        return cursor != null && !cursor.isClosed() && cursor.moveToPosition(i);
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, boolean z, boolean z2) {
        String str4;
        String[] strArr3;
        boolean z3;
        if (z2) {
            if (strArr != null) {
                ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
                if (!arrayList.contains("localGroupId")) {
                    arrayList.add("localGroupId");
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!arrayList.contains("dateModified")) {
                    arrayList.add("dateModified");
                    z3 = true;
                }
                if (!arrayList.contains("alias_julianday")) {
                    arrayList.add("alias_julianday");
                    z3 = true;
                }
                if (z3) {
                    strArr3 = new String[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        strArr3[i] = (String) arrayList.get(i);
                    }
                    str4 = "dateModified DESC ";
                }
            }
            strArr3 = strArr;
            str4 = "dateModified DESC ";
        } else {
            strArr3 = strArr;
            str4 = str3;
        }
        Cursor query = sQLiteDatabase.query(getRecentDiscoveryTable(z), strArr3, str, strArr2, str2, (String) null, str4);
        if (query == null || query.getCount() <= 0 || !z2) {
            return query;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Cursor resortAndGenerateHeaders = resortAndGenerateHeaders(query, strArr3);
            Log.d("RecentDiscoveryMediaManager", "resortAndGenerateHeaders cost %d ms.", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return resortAndGenerateHeaders;
        } catch (Exception e) {
            Log.e("RecentDiscoveryMediaManager", "resortAndGenerateHeaders failed.");
            e.printStackTrace();
            return null;
        } finally {
            MiscUtil.closeSilently(query);
        }
    }

    public static Cursor queryAlbumCover(SQLiteDatabase sQLiteDatabase, int i, boolean z) {
        AnonymousClass1 r0 = new AbstractWindowedCursor() {
            public String[] getColumnNames() {
                return new String[0];
            }

            public int getCount() {
                return 0;
            }
        };
        r0.setExtras(generateAlbumCover(sQLiteDatabase, i, z));
        return r0;
    }

    private static void recordNotInWhiteListAlbum(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("album_relative_path", str);
        GallerySamplingStatHelper.recordCountEvent("media_scanner", "scanner_directory_not_in_white_list", hashMap);
    }

    private static Cursor resortAndGenerateHeaders(Cursor cursor, String[] strArr) {
        if (cursor == null || cursor.getCount() <= 0 || cursor.getColumnIndex("dateModified") == -1 || cursor.getColumnIndex("alias_julianday") == -1 || cursor.getColumnIndex("localGroupId") == -1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            insertItemToGroupList(getDateModified(cursor), getJulianday(cursor), getAlbumId(cursor), cursor.getPosition(), arrayList);
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Group group = (Group) it.next();
            arrayList4.add(DateUtils.formatRelativeTime(getDateModified(cursor, group.cursorPosList.get(0).intValue()), false));
            arrayList2.add(Integer.valueOf(group.cursorPosList.size()));
            arrayList3.add(Integer.valueOf(i));
            i += group.cursorPosList.size();
            for (Integer intValue : group.cursorPosList) {
                if (moveCursorToPosition(cursor, intValue.intValue())) {
                    addOneRowToMatrixCursor(cursor, matrixCursor, strArr);
                }
            }
        }
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("extra_timeline_item_count_in_group", arrayList2);
        bundle.putIntegerArrayList("extra_timeline_group_start_pos", arrayList3);
        bundle.putStringArrayList("extra_timeline_group_time_labels", arrayList4);
        matrixCursor.setExtras(bundle);
        return matrixCursor;
    }
}
