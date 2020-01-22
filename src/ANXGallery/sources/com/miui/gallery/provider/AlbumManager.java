package com.miui.gallery.provider;

import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.model.FaceAlbumCover;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.face.FaceRegionRectF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AlbumManager {
    private static Map<Long, Long> ALBUM_ATTRIBUTES = null;
    private static List<Long> ALBUM_SYNC_ATTRIBUTES = null;
    private static final String SQL_ALBUM_BASIC = ("SELECT _id AS _id, attributes AS attributes, " + SQL_ALBUM_NAME + ", " + SQL_ALBUM_LOCAL_PATH + ", " + "localFlag" + " AS " + "flag" + ", " + "dateTaken" + " AS " + "top_time" + ", " + "peopleId" + " AS " + "face_people_id" + ", " + "babyInfoJson" + " AS " + "baby_info" + ", " + "NULL AS " + "baby_sharer_info" + ", " + "serverId" + " AS " + "serverId" + ", " + "thumbnailInfo" + " AS " + "thumbnail_info" + ", " + SQL_ALBUM_SORT_BY + ", " + SQL_ALBUM_IMMUTABLE + " " + "FROM " + "cloud" + " " + "WHERE " + "(serverType=0)" + " " + "AND " + "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))" + " " + "%s " + "%s");
    private static final String SQL_ALBUM_COUNT = ", count(_id) AS media_count";
    private static final String SQL_ALBUM_COVER_DEFAULT;
    private static final String SQL_ALBUM_COVER_FORMAT = (", _id AS cover_id, (" + InternalContract.Cloud.ALIAS_CLEAR_FIRST + ") AS " + "cover_path" + ", " + "sha1" + " AS " + "cover_sha1" + ", " + " CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END " + " AS " + "cover_sync_state" + ", " + "size" + " AS " + "cover_size" + ", " + "max(CASE" + " WHEN " + "localGroupId" + " IN " + "(SELECT " + "_id" + " FROM " + "cloud" + " WHERE " + "localFile" + " COLLATE NOCASE IN (%s)" + " AND " + "(serverType=0)" + ")" + " THEN " + "dateModified" + " ELSE (" + " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END " + ") END) " + "AS latest_photo ");
    private static final String SQL_ALBUM_COVER_PLACEHOLDER = ", -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo ";
    private static final String SQL_ALBUM_DEDUPLICATE_COUNT = ", count(DISTINCT sha1) AS media_count";
    private static String SQL_ALBUM_IMMUTABLE = "CASE WHEN localFile LIKE '/%%' THEN 1 ELSE 0 END AS immutable";
    private static String SQL_ALBUM_LOCAL_PATH = "localFile AS local_path";
    private static String SQL_ALBUM_NAME = "fileName AS name";
    private static String SQL_ALBUM_NOT_HIDDEN = "AND (attributes & 16 = 0)";
    private static String SQL_ALBUM_SORT_BY = "CASE WHEN sortBy IS NULL THEN CASE WHEN dateTaken >0 THEN 9223372036854775807 - dateTaken ELSE dateTaken END ELSE sortBy END AS sortBy";
    public static final String SQL_BABY_ALBUM_COVER;
    public static final String SQL_BABY_OTHER_SHARED_ALBUM_COVER;
    private static String SQL_EXCLUDE_BABY_ALBUM = "AND (babyInfoJson IS NULL)";
    private static String SQL_EXCLUDE_MEDIA_IN_BABY_ALBUM = "AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND babyInfoJson IS NULL )) ";
    private static final String SQL_RECENT_ALBUM_COVER = (", _id AS cover_id, (" + InternalContract.Cloud.ALIAS_CLEAR_FIRST + ") AS " + "cover_path" + ", " + "sha1" + " AS " + "cover_sha1" + ", " + " CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END " + " AS " + "cover_sync_state" + ", " + "size" + " AS " + "cover_size" + ", " + "max(" + "dateModified" + ") AS latest_photo ");
    private static final String SQL_SHARE_ALBUM = ("SELECT * FROM (" + SQL_SHARE_ALBUM_BASIC + ")" + " LEFT JOIN " + "(SELECT " + 2147383647 + "+" + "localGroupId" + " AS " + "_id" + "%s" + SQL_SHARE_ALBUM_COVER + ", 0 AS size " + "FROM " + "shareImage" + " " + "WHERE " + "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))" + " " + "%s" + "GROUP BY " + "localGroupId" + ") USING (" + "_id" + ")");
    private static final String SQL_SHARE_ALBUM_BASIC;
    private static final String SQL_SHARE_ALBUM_COUNT = ", count(_id) AS media_count";
    private static final String SQL_SHARE_ALBUM_COVER = (", 1073741823+_id AS cover_id, (" + InternalContract.ShareImage.ALIAS_CLEAR_FIRST + ") AS " + "cover_path" + ", " + "sha1" + " AS " + "cover_sha1" + ", " + " CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END " + " AS " + "cover_sync_state" + ", " + "size" + " AS " + "cover_size" + ", " + "max(" + " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END " + ") AS latest_photo ");
    private static String SQL_SHARE_ALBUM_NOT_HIDDEN = "AND (attributes & 16 = 0)";
    private static String SQL_SHARE_ALBUM_SORT_BY = "CASE WHEN sortBy IS NULL THEN CASE WHEN dateTaken >0 THEN 9223372036854775807 - dateTaken ELSE dateTaken END ELSE sortBy END AS sortBy";
    private static final String SQL_SHARE_DEDUPLICATE_ALBUM_COUNT = ", count(DISTINCT sha1) AS media_count";
    private static final String SQL_UNION_SHARE_ALBUM;
    private static long sScreenshotsAlbumId = -1;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT 2147383647+_id AS _id, attributes AS attributes, sharealbum.fileName AS name, NULL AS local_path, localFlag AS flag, dateTaken AS top_time, peopleId AS face_people_id, babyInfoJson AS baby_info, sharerInfo AS baby_sharer_info, serverId AS serverId, NULL AS thumbnail_info, ");
        sb.append(SQL_SHARE_ALBUM_SORT_BY);
        sb.append(", ");
        sb.append(0);
        sb.append(" AS ");
        sb.append("immutable");
        sb.append(" ");
        sb.append("FROM ");
        sb.append("shareAlbum");
        sb.append(" ");
        sb.append("WHERE ");
        sb.append("serverId");
        sb.append(" IS NOT NULL ");
        sb.append("%s");
        SQL_SHARE_ALBUM_BASIC = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(", _id AS cover_id, (");
        sb2.append(InternalContract.Cloud.ALIAS_CLEAR_FIRST);
        sb2.append(") AS ");
        sb2.append("cover_path");
        sb2.append(", ");
        sb2.append("sha1");
        sb2.append(" AS ");
        sb2.append("cover_sha1");
        sb2.append(", ");
        sb2.append(" CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END ");
        sb2.append(" AS ");
        sb2.append("cover_sync_state");
        sb2.append(", ");
        sb2.append("size");
        sb2.append(" AS ");
        sb2.append("cover_size");
        sb2.append(", ");
        sb2.append("max(");
        sb2.append(" CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END ");
        sb2.append(") AS latest_photo ");
        SQL_ALBUM_COVER_DEFAULT = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("(SELECT * FROM (SELECT cloud.serverId as imageId");
        sb3.append(SQL_ALBUM_COVER_DEFAULT);
        sb3.append("FROM ");
        sb3.append("cloud");
        sb3.append(" ");
        sb3.append(" WHERE ");
        sb3.append("localGroupId");
        sb3.append(" = %s ))");
        SQL_BABY_ALBUM_COVER = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("(SELECT * FROM (SELECT serverId as imageId");
        sb4.append(SQL_ALBUM_COVER_DEFAULT);
        sb4.append("FROM ");
        sb4.append("shareImage");
        sb4.append(" ");
        sb4.append(" WHERE ");
        sb4.append("localGroupId");
        sb4.append(" = %s ))");
        SQL_BABY_OTHER_SHARED_ALBUM_COVER = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(" UNION ");
        sb5.append(SQL_SHARE_ALBUM);
        SQL_UNION_SHARE_ALBUM = sb5.toString();
        HashMap hashMap = new HashMap();
        hashMap.put(1L, 2L);
        hashMap.put(4L, 8L);
        hashMap.put(16L, 32L);
        hashMap.put(64L, 128L);
        ALBUM_ATTRIBUTES = Collections.unmodifiableMap(hashMap);
        ArrayList arrayList = new ArrayList();
        arrayList.add(1L);
        arrayList.add(4L);
        arrayList.add(16L);
        arrayList.add(64L);
        ALBUM_SYNC_ATTRIBUTES = Collections.unmodifiableList(arrayList);
    }

    private static long doQueryScreenshotsAlbumId(Context context) {
        Context context2 = context;
        Long l = (Long) SafeDBUtil.safeQuery(context2, GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, "serverId = ? AND (serverType=0)", new String[]{String.valueOf(2)}, (String) null, $$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY.INSTANCE);
        if (l != null) {
            return l.longValue();
        }
        return -1;
    }

    private static String genPlaceholderAlbumSelection(long j, String str, long j2, long j3, long j4) {
        return String.format(Locale.US, " UNION SELECT %d AS _id, 0 AS attributes, '%s' AS name, NULL AS local_path, 0 AS flag, %d AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '%d' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable , 2147483647 AS media_count, -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo , 0 AS size ", new Object[]{Long.valueOf(j), str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)});
    }

    private static Bundle generateFaceAlbumCover(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(FaceManager.buildTopFaceCoverQuery(), (String[]) null);
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
            int columnIndex6 = rawQuery.getColumnIndex("faceRect");
            while (rawQuery.moveToNext()) {
                FaceAlbumCover faceAlbumCover = new FaceAlbumCover();
                faceAlbumCover.coverId = rawQuery.getLong(columnIndex);
                faceAlbumCover.coverPath = rawQuery.getString(columnIndex2);
                faceAlbumCover.coverSha1 = rawQuery.getString(columnIndex3);
                faceAlbumCover.coverSyncState = rawQuery.getInt(columnIndex4);
                faceAlbumCover.coverSize = rawQuery.getLong(columnIndex5);
                faceAlbumCover.faceRectF = FaceRegionRectF.resolveFrom(rawQuery.getString(columnIndex6));
                if (faceAlbumCover.faceRectF != null) {
                    arrayList.add(faceAlbumCover);
                }
                if (arrayList.size() == 4) {
                    break;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("face_album_cover", arrayList);
            bundle.putInt("face_album_count", rawQuery.getCount());
            return bundle;
        } finally {
            rawQuery.close();
        }
    }

    public static Map<Long, Long> getAlbumAttributes() {
        return ALBUM_ATTRIBUTES;
    }

    private static String getAlbumBasicColumns(boolean z, boolean z2) {
        String str = SQL_ALBUM_BASIC;
        Object[] objArr = new Object[2];
        String str2 = "";
        objArr[0] = z ? SQL_ALBUM_NOT_HIDDEN : str2;
        if (z2) {
            str2 = SQL_EXCLUDE_BABY_ALBUM;
        }
        objArr[1] = str2;
        return String.format(str, objArr);
    }

    public static List<Long> getAlbumSyncAttributes() {
        return ALBUM_SYNC_ATTRIBUTES;
    }

    private static String getExcludeEmptyAlbumSelection(boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("(media_count >0 ");
        if (z) {
            str = "";
        } else {
            str = " OR (" + InternalContract.Cloud.ALIAS_USER_CREATE_ALBUM + ")";
        }
        sb.append(str);
        sb.append(" OR ");
        sb.append("_id");
        sb.append("=");
        sb.append(2147483646);
        sb.append(")");
        return sb.toString();
    }

    private static String getExcludeEmptyRecentAlbumSelection() {
        return "(media_count > 0 OR _id!=2147483644)";
    }

    private static String getExcludeHiddenAlbumSelection(boolean z) {
        return z ? " AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND attributes&16=0)) " : "";
    }

    private static String getExcludeHiddenShareAlbumSelection(boolean z) {
        return z ? SQL_SHARE_ALBUM_NOT_HIDDEN : "";
    }

    private static String getExcludeMediaInBabyAlbumSelection(boolean z) {
        return z ? SQL_EXCLUDE_MEDIA_IN_BABY_ALBUM : "";
    }

    private static String getExcludeNonLocalSelection(int i, boolean z) {
        String str;
        if (i == 1) {
            str = InternalContract.Cloud.ALIAS_LOCAL_IMAGE;
        } else if (i != 2) {
            return getExcludeNonLocalSelection(z);
        } else {
            str = InternalContract.Cloud.ALIAS_LOCAL_VIDEO;
        }
        if (!z) {
            return "";
        }
        return String.format(" AND %s ", new Object[]{str});
    }

    private static String getExcludeNonLocalSelection(boolean z) {
        if (!z) {
            return "";
        }
        return String.format(" AND %s ", new Object[]{InternalContract.Cloud.ALIAS_LOCAL_MEDIA});
    }

    private static String getFaceAlbumColumns() {
        long virtualAlbumSortBy = GalleryPreferences.Album.getVirtualAlbumSortBy(2147483646, -997);
        return String.format(Locale.US, " UNION SELECT 2147483646 AS _id, 0 AS attributes, 'FACE' AS name, NULL AS local_path, 0 AS flag, -997 AS top_time,NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '-2147483646' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable, COUNT(*) AS media_count, 0 AS size , -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo FROM peopleFace WHERE ((localFlag=5 OR (localFlag=0 AND serverStatus='normal')) AND type = 'FACE')", new Object[]{Long.valueOf(virtualAlbumSortBy)});
    }

    private static String getFavoritesAlbumColumns(String str, boolean z, boolean z2, Integer num, boolean z3) {
        long virtualAlbumSortBy = GalleryPreferences.Album.getVirtualAlbumSortBy(2147483642, -1000);
        if (z3) {
            return genPlaceholderAlbumSelection(2147483642, "FAVORITES", -1000, -2147483642, virtualAlbumSortBy);
        }
        return String.format(Locale.US, " UNION SELECT 2147483642 AS _id, 0 AS attributes, 'FAVORITES' AS name, NULL AS local_path, 0 AS flag, -1000 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '-2147483642' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM extended_cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000) AND (serverType IN (1,2) AND isFavorite NOT NULL AND isFavorite > 0) %s%s%s%s", new Object[]{Long.valueOf(virtualAlbumSortBy), ", count(DISTINCT sha1) AS media_count", str, ", 0 AS size ", getMediaTypeSelection(num), getExcludeNonLocalSelection(z2), getExcludeHiddenAlbumSelection(z), getExcludeMediaInBabyAlbumSelection(z2)});
    }

    private static String getMediaTypeSelection(Integer num) {
        if (num == null) {
            return "";
        }
        if (num.intValue() != 2 && num.intValue() != 1) {
            return "";
        }
        return String.format(Locale.US, " AND %s = %s ", new Object[]{"serverType", num.toString()});
    }

    private static String getPanoAlbumColumns(String str, boolean z, boolean z2, boolean z3) {
        long virtualAlbumSortBy = GalleryPreferences.Album.getVirtualAlbumSortBy(2147483645, -994);
        if (z3) {
            return genPlaceholderAlbumSelection(2147483645, "PANO", -994, -2147483645, virtualAlbumSortBy);
        }
        boolean z4 = z2;
        return String.format(Locale.US, " UNION SELECT 2147483645 AS _id, 0 AS attributes, 'PANO' AS name, NULL AS local_path, 0 AS flag, -994 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '-2147483645' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (exifImageWidth > 1080 AND exifImageLength > 0 AND exifImageWidth- 3 * exifImageLength > 0 AND title LIKE 'PANO%%') AND (localGroupId!=-1000)%s%s", new Object[]{Long.valueOf(virtualAlbumSortBy), ", count(DISTINCT sha1) AS media_count", str, ", 0 AS size ", getExcludeNonLocalSelection(1, z2), getExcludeHiddenAlbumSelection(z)});
    }

    private static String getRecentAlbumColumns(boolean z, boolean z2, boolean z3, Integer num, boolean z4, boolean z5) {
        long virtualAlbumSortBy = GalleryPreferences.Album.getVirtualAlbumSortBy(2147483644, -1001);
        if (z5) {
            return genPlaceholderAlbumSelection(2147483644, "RECENT", -1001, -2147483644, virtualAlbumSortBy);
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[8];
        objArr[0] = Long.valueOf(virtualAlbumSortBy);
        objArr[1] = z3 ? SQL_ALBUM_DEDUPLICATE_COUNT : SQL_ALBUM_COUNT;
        objArr[2] = z4 ? SQL_RECENT_ALBUM_COVER : SQL_ALBUM_COVER_PLACEHOLDER;
        objArr[3] = ", 0 AS size ";
        objArr[4] = getMediaTypeSelection(num);
        objArr[5] = getExcludeNonLocalSelection(z2);
        objArr[6] = getExcludeHiddenAlbumSelection(z);
        objArr[7] = getExcludeMediaInBabyAlbumSelection(z2);
        return String.format(locale, " UNION SELECT 2147483644 AS _id, 0 AS attributes, 'RECENT' AS name, NULL AS local_path, 0 AS flag, -1001 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '-2147483644' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT mediaId AS _id, sha1, localFlag, dateModified, serverStatus, localGroupId, thumbnailFile, microthumbfile, localFile, serverType, size, babyInfoJson FROM (recentDiscoveredMedia JOIN (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud ON cloud._id = mediaId) WHERE ( CASE WHEN DATE(dateModified/1000, 'unixepoch', 'localtime') == DATE('now', 'localtime') THEN 0 ELSE CAST(JULIANDAY('now', 'localtime', 'start of day') - JULIANDAY(dateModified/1000, 'unixepoch', 'localtime') AS int) + 1 END < 30)) WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000)%s%s%s%s", objArr);
    }

    private static String getShareAlbumBasicColumns(boolean z, boolean z2, Integer num) {
        String str = SQL_UNION_SHARE_ALBUM;
        Object[] objArr = new Object[3];
        objArr[0] = getExcludeHiddenShareAlbumSelection(z);
        objArr[1] = z2 ? SQL_SHARE_DEDUPLICATE_ALBUM_COUNT : SQL_SHARE_ALBUM_COUNT;
        objArr[2] = getMediaTypeSelection(num);
        return String.format(str, objArr);
    }

    private static String getVideoAlbumColumns(String str, boolean z, boolean z2, boolean z3) {
        long virtualAlbumSortBy = GalleryPreferences.Album.getVirtualAlbumSortBy(2147483647L, -998);
        if (z3) {
            return genPlaceholderAlbumSelection(2147483647L, "VIDEO", -998, -2147483647L, virtualAlbumSortBy);
        }
        boolean z4 = z2;
        return String.format(Locale.US, " UNION SELECT 2147483647 AS _id, 0 AS attributes, 'VIDEO' AS name, NULL AS local_path, 0 AS flag, -998 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, '-2147483647' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE serverType=2 AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (localGroupId!=-1000)%s%s", new Object[]{Long.valueOf(virtualAlbumSortBy), ", count(DISTINCT sha1) AS media_count", str, ", 0 AS size ", getExcludeNonLocalSelection(2, z2), getExcludeHiddenAlbumSelection(z)});
    }

    private static boolean isInLocalMode() {
        return GalleryPreferences.LocalMode.isOnlyShowLocalPhoto();
    }

    private static boolean isInShowHiddenMode() {
        return GalleryPreferences.HiddenAlbum.isShowHiddenAlbum();
    }

    private static boolean isIncludeFaceAlbum(boolean z) {
        return AIAlbumStatusHelper.isFaceAlbumEnabled() && !z;
    }

    private static boolean isQueryColumn(String[] strArr, String str) {
        if (strArr != null && strArr.length > 0) {
            if ("*".equals(strArr[0].trim())) {
                return true;
            }
            for (String trim : strArr) {
                if (str.equals(trim.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isVirtualAlbum(long j) {
        for (int i : GalleryContract.Album.ALL_VIRTUAL_ALBUM_IDS) {
            if (((long) i) == j) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ Long lambda$doQueryScreenshotsAlbumId$33(Cursor cursor) {
        long j = -1;
        long j2 = (cursor == null || !cursor.moveToFirst()) ? -1 : cursor.getLong(0);
        if (j2 > 0) {
            j = j2;
        }
        return Long.valueOf(j);
    }

    public static long packageAttributeBit(long j, boolean z, boolean z2) {
        long longValue = ALBUM_ATTRIBUTES.get(Long.valueOf(j)).longValue();
        if (!z) {
            j = 0;
        }
        if (!z2) {
            longValue = 0;
        }
        return j | longValue;
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, Integer num, boolean z10, boolean z11, boolean z12, String str3) {
        String str4;
        char c;
        String str5;
        String str6 = str;
        boolean z13 = z9;
        Integer num2 = num;
        boolean z14 = z11;
        boolean z15 = !z8 && !isInShowHiddenMode() && z12;
        boolean z16 = !z8 && isInLocalMode();
        if (z10) {
            List<String> albumPathsBySortDate = MediaSortDateHelper.getSortDateProvider().getAlbumPathsBySortDate(MediaSortDateHelper.SortDate.MODIFY_TIME);
            if (MiscUtil.isValid(albumPathsBySortDate)) {
                str4 = String.format(Locale.US, SQL_ALBUM_COVER_FORMAT, new Object[]{"'" + TextUtils.join("', '", albumPathsBySortDate) + "'"});
            } else {
                str4 = SQL_ALBUM_COVER_DEFAULT;
            }
        } else {
            str4 = SQL_ALBUM_COVER_PLACEHOLDER;
        }
        String str7 = str4;
        String str8 = "";
        String faceAlbumColumns = (!z2 || !(num2 == null || num.intValue() == 1) || !isIncludeFaceAlbum(z16)) ? str8 : getFaceAlbumColumns();
        String videoAlbumColumns = (!z || !(num2 == null || num.intValue() == 2)) ? str8 : getVideoAlbumColumns(str7, z15, z16, z14);
        String shareAlbumBasicColumns = (!z3 || z16) ? str8 : getShareAlbumBasicColumns(z15, z13, num2);
        String panoAlbumColumns = (!z4 || !(num2 == null || num.intValue() == 1)) ? str8 : getPanoAlbumColumns(str7, z15, z16, z14);
        if (z5) {
            c = 2;
            str5 = getRecentAlbumColumns(z15, z16, z9, num, z10, z11);
        } else {
            c = 2;
            str5 = str8;
        }
        if (z6) {
            str8 = getFavoritesAlbumColumns(str7, z15, z16, num2, z14);
        }
        String albumBasicColumns = getAlbumBasicColumns(z15, z16);
        String str9 = isQueryColumn(strArr, "size") ? ", sum(size) AS size " : ", 0 AS size ";
        Object[] objArr = new Object[12];
        objArr[0] = albumBasicColumns;
        objArr[1] = z13 ? SQL_ALBUM_DEDUPLICATE_COUNT : SQL_ALBUM_COUNT;
        objArr[c] = str7;
        objArr[3] = str9;
        objArr[4] = getMediaTypeSelection(num);
        objArr[5] = getExcludeNonLocalSelection(z16);
        objArr[6] = shareAlbumBasicColumns;
        objArr[7] = videoAlbumColumns;
        objArr[8] = panoAlbumColumns;
        objArr[9] = str5;
        objArr[10] = str8;
        objArr[11] = faceAlbumColumns;
        String format = String.format("(SELECT * FROM (%s) LEFT JOIN (SELECT localGroupId AS _id%s%s%sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) %s%sGROUP BY localGroupId) USING (_id)%s %s %s %s %s %s)", objArr);
        if (z7) {
            if (TextUtils.isEmpty(str)) {
                str6 = getExcludeEmptyAlbumSelection(z16);
            } else {
                str6 = getExcludeEmptyAlbumSelection(z16) + " AND (" + str6 + ")";
            }
        } else if (!z8 && z5) {
            if (TextUtils.isEmpty(str)) {
                str6 = getExcludeEmptyRecentAlbumSelection();
            } else {
                str6 = getExcludeEmptyRecentAlbumSelection() + " AND (" + str6 + ")";
            }
        }
        return sQLiteDatabase.query(format, strArr, str6, strArr2, (String) null, (String) null, str2, str3);
    }

    public static Cursor queryFaceAlbumCover(SQLiteDatabase sQLiteDatabase) {
        AnonymousClass1 r0 = new AbstractWindowedCursor() {
            public String[] getColumnNames() {
                return new String[0];
            }

            public int getCount() {
                return 0;
            }
        };
        if (isIncludeFaceAlbum(false)) {
            r0.setExtras(generateFaceAlbumCover(sQLiteDatabase));
        }
        return r0;
    }

    public static synchronized long queryScreenshotsAlbumId(Context context) {
        long j;
        synchronized (AlbumManager.class) {
            if (sScreenshotsAlbumId == -1) {
                sScreenshotsAlbumId = doQueryScreenshotsAlbumId(context);
            }
            j = sScreenshotsAlbumId;
        }
        return j;
    }
}
