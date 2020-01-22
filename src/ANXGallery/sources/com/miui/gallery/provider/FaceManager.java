package com.miui.gallery.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.provider.BabyLockWallpaperDataManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.provider.peoplecover.PeopleCoverManager;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GalleryTimeUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.face.FaceRegionRectF;
import com.miui.os.Rom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class FaceManager {
    private static final String[] BASIC_PEOPLE_INFO_PROJECTION = {"_id", "serverId", "peopleName", "relationType"};
    private static final String[] CLOUD_IMAGE_OF_FACE_PROJECTION = {"*"};
    private static final String FACE_COVER_ALIAS_DISPLAYABLE = (" CASE WHEN " + THUMBNAIL_ALIAS + " THEN " + "thumbnailFile" + " " + "WHEN " + LOCAL_FILE_ALIAS + " THEN " + "localFile" + " " + "ELSE " + "microthumbfile" + " " + "END ");
    private static final String GROUP_LOCAL_FLAG_SYNCED_CREATED_RENAME = String.format(Locale.US, "(%s OR %s = %d OR %s = %d)", new Object[]{LOCAL_FLAG_SYNCED, "localFlag", 8, "localFlag", 10});
    private static final String[] ID_COUNT_PROJECTION = {"_id"};
    private static final int LEAST_FACE_COUNT_OF_DISPLAY_PEOPLE = (Rom.IS_ALPHA ? 1 : 2);
    private static final String LOCAL_FILE_ALIAS = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"localFile", "localFile"});
    private static final String LOCAL_FLAG_SYNCED = String.format(Locale.US, "(%s = %d AND %s = \"%s\")", new Object[]{"localFlag", 0, "serverStatus", "custom"});
    private static final String MICRO_THUMBNAIL_ALIAS = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"microthumbfile", "microthumbfile"});
    private static final String Other_SHARED_GROUP_LOCAL_FLAG_SYNCED = String.format(Locale.US, "(%s = \"%s\" AND NOT (%s IS NULL))", new Object[]{"serverStatus", "custom", "serverId"});
    private static final String[] PEOPLE_ALBUM_SERVER_ID_PROJECTION = {"serverId"};
    public static final String[] PEOPLE_RELATION_PROJECTION = {"relationType", "relationText"};
    public static final String PEOPLE_USER_DEFINE_RELATION_SELECTION;
    private static final String PERSON_VISIBLE_CONDITION = String.format(Locale.US, "(visibilityType = 1 OR visibilityType = 4 OR faceCount >= %d AND (visibilityType IS NULL OR visibilityType = 0))", new Object[]{Integer.valueOf(LEAST_FACE_COUNT_OF_DISPLAY_PEOPLE)});
    private static final String PHOTO_LOCAL_FLAG_CREATE_MOVED_SYNCED = String.format(Locale.US, "(%s OR %s = %d OR %s = %d OR %s = %d OR %s = %d)", new Object[]{LOCAL_FLAG_SYNCED, "localFlag", 8, "localFlag", 5, "localFlag", 6, "localFlag", 9});
    private static final String SQL_FACE_ALBUM_COVER = ("photo_id AS cover_id, " + FACE_COVER_ALIAS_DISPLAYABLE + " AS " + "cover_path" + ", " + "sha1" + " AS " + "cover_sha1" + ", " + "faceXScale" + "||' '||" + "faceYScale" + "||' '||" + "faceWScale" + "||' '||" + "faceHScale" + "||' '||" + "exifOrientation" + " AS " + "faceRect" + ", " + 0 + " AS " + "cover_sync_state" + ", " + "size" + " AS " + "cover_size");
    private static final String[] SQL_FACE_ALBUM_COVER_PROJECTION = {" CASE WHEN " + InternalContract.Cloud.ALIAS_THUMBNAIL_VALID + " THEN " + "thumbnailFile" + " " + "WHEN " + InternalContract.Cloud.ALIAS_ORIGIN_FILE_VALID + " THEN " + "localFile" + " " + "ELSE " + "microthumbfile" + " " + "END AS " + "cover_path" + ", " + "exifOrientation" + ", " + "faceXScale" + ", " + "faceYScale" + ", " + "faceWScale" + ", " + "faceHScale" + ", " + "faceCoverScore" + ", " + "serverId"};
    private static final String[] SQL_OLDEST_FACE_OF_ALBUM_PROJECTION = {" min( CASE WHEN dateTaken NOT NULL THEN dateTaken ELSE mixedDateTime END) AS oldest_image_time"};
    private static final String SQL_ONE_PERSON_ALBUM_ITEM = String.format(Locale.US, "SELECT * from (extended_faceImage LEFT JOIN favorites ON extended_faceImage.sha1 = favorites.sha1) WHERE (%s) GROUP BY serverId", new Object[]{"serverId = '%s'"});
    private static final String SQL_ONE_PERSON_ALBUM_NORMAL = String.format(Locale.US, "SELECT * from (extended_faceImage LEFT JOIN favorites ON extended_faceImage.sha1 = favorites.sha1) WHERE (%s) GROUP BY serverId", new Object[]{"(localFlag =0 AND serverStatus='normal' AND groupId = '%s') OR (localFlag =5 AND localGroupId = %s)"});
    private static final String SQL_ONE_PERSON_ALBUM_RECOMMEND = String.format(Locale.US, "SELECT * from (extended_faceImage LEFT JOIN favorites ON extended_faceImage.sha1 = favorites.sha1) WHERE (%s) GROUP BY serverId", new Object[]{"(localFlag =0 AND serverStatus='normal' AND serverId IN (%s))"});
    private static final String SQL_PERSONS_ALBUM = String.format(Locale.US, "SELECT * from (extended_faceImage LEFT JOIN favorites ON extended_faceImage.sha1 = favorites.sha1) WHERE (%s) GROUP BY serverId", new Object[]{"(localFlag =0 AND serverStatus='normal' AND groupId in (%s)) OR (localFlag =5 AND localGroupId in (%s))"});
    private static final String SQL_PERSONS_ALBUM_ALL_BASIC = (" SELECT _id, peopleName, se AS serverId,CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN cId ELSE covercId END AS photo_id, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN sha1 ELSE coversha1 END AS sha1, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN cm ELSE covercm END AS microthumbfile, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN ch ELSE coverch END AS thumbnailFile, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN cl ELSE covercl END AS localFile, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN co ELSE coverco END AS exifOrientation, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN fx ELSE coverfx END AS faceXScale, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN fy ELSE coverfy END AS faceYScale, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN fw ELSE coverfw END AS faceWScale, CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN fh ELSE coverfh END AS faceHScale, relationType, relationText, visibilityType, sum(faceCount) as faceCount,CASE WHEN (coverse IS NULL OR maxFaceScore > 0) THEN size ELSE coversize END AS size,  COUNT(*) FROM (   SELECT _id, peopleName, serverId as se, cId, sha1, cm, ch, cl, co, fx, fy, fw, fh, size, peopleFace.relationType, peopleFace.relationText, peopleFace.visibilityType, maxFaceScore, faceCount,(CASE WHEN NOT(peopleName IS NULL) THEN 1 ELSE 2 END) AS order_by_notnull_null FROM peopleFace  JOIN  (SELECT pg, COUNT(*) as faceCount, cId, sha1, cd, cm, ch, cl, co, fx, fy, fw, fh, size, MAX(faceCoverScore) as maxFaceScore FROM           (SELECT serverId, faceXScale AS fx, faceYScale AS fy, faceWScale AS fw, faceHScale AS fh, size, groupId AS pg, photo_id AS cId, sha1, mixedDateTime AS cd, microthumbfile AS cm, thumbnailFile AS ch, localFile AS cl, exifOrientation as co, faceCoverScore            FROM extended_faceImage           WHERE localFlag =0 AND serverStatus='normal'            GROUP BY serverId            ORDER BY mixedDateTime DESC  ) temp1  GROUP BY pg  ) temp2  ON type = 'PEOPLE' AND %s AND se = pg  UNION ALL SELECT _id, peopleName, serverId as se, cId asphoto_id, sha1, cm, ch, cl, co, fx, fy, fw, fh, size, peopleFace.relationType, peopleFace.relationText, peopleFace.visibilityType, maxFaceScore, faceCount,(CASE WHEN NOT(peopleName IS NULL) THEN 1 ELSE 2 END) AS order_by_notnull_null FROM  peopleFace  JOIN  (SELECT lpg, COUNT(*) as faceCount, cId, sha1, cd, cm, ch, cl, co, fx, fy, fw, fh, size, MAX(faceCoverScore) as maxFaceScore FROM           (SELECT serverId, faceXScale AS fx, faceYScale AS fy, faceWScale AS fw, faceHScale AS fh, localGroupId AS lpg, photo_id AS cId, sha1, mixedDateTime AS cd, microthumbfile AS cm, thumbnailFile AS ch, size, localFile AS cl, exifOrientation as co, faceCoverScore            FROM extended_faceImage           WHERE localFlag =5            GROUP BY serverId            ORDER BY mixedDateTime DESC )  temp1  GROUP BY lpg  ) temp2  ON type = 'PEOPLE' AND %s AND _id = lpg )  LEFT JOIN (SELECT coverse, coverfx, coverfy, coverfw, coverfh, coverpg, covercId, coversha1, max(covercd), covercm, coverch, covercl, coversize, coverco FROM           (SELECT serverId AS coverse, faceXScale AS coverfx, faceYScale AS coverfy, faceWScale AS coverfw, faceHScale AS coverfh, groupId AS coverpg, photo_id AS covercId, sha1 AS coversha1, mixedDateTime AS covercd, microthumbfile AS covercm, thumbnailFile AS coverch, localFile AS covercl, size AS coversize, exifOrientation as coverco           FROM extended_faceImage           WHERE serverid in ( %s ) AND (localFlag =0 AND serverStatus='normal')            GROUP BY serverId ) cover1 GROUP BY coverpg) cover2 ON se = cover2.coverpg GROUP BY _id ORDER BY " + PeopleContactInfo.getRelationOrderSql() + ", " + PeopleContactInfo.getUserDefineRelationOrderSql() + ", " + "order_by_notnull_null" + " ASC" + ", " + "faceCount" + " DESC");
    private static final String THUMBNAIL_ALIAS = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"thumbnailFile", "thumbnailFile"});

    public static class BasicPeopleInfo {
        public int id;
        public String name;
        public int relationType;
        public String serverId;

        public BasicPeopleInfo(int i, String str, String str2, int i2) {
            this.id = i;
            this.serverId = str;
            this.name = str2;
            this.relationType = i2;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("relationType = ");
        sb.append(PeopleContactInfo.getUserDefineRelationIndex());
        sb.append(" AND ");
        sb.append("type = 'PEOPLE' AND ( visibilityType = 1 OR visibilityType =4) AND localFlag NOT IN ( 13, 2)");
        sb.append(" AND ");
        sb.append("(groupid IS NULL OR groupid = '' OR groupid = serverId)");
        sb.append(" GROUP BY relationtext");
        PEOPLE_USER_DEFINE_RELATION_SELECTION = sb.toString();
    }

    private static String buildAlbumQuery(String[] strArr, String str, String[] strArr2, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        String join = (strArr == null || strArr.length == 0) ? "*" : TextUtils.join(",", strArr);
        sb.append("SELECT ");
        sb.append(join);
        sb.append(" FROM (");
        sb.append(str3);
        sb.append(")");
        if (!TextUtils.isEmpty(str)) {
            if (strArr2 != null && strArr2.length > 0) {
                str = String.format(Locale.US, str, (Object[]) strArr2);
            }
            sb.append(" WHERE ");
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(" order by ");
            sb.append(" ");
            sb.append(str2);
        }
        sb.append(";");
        return sb.toString();
    }

    public static String buildIgnorePersonsQuery() {
        return String.format(Locale.US, SQL_PERSONS_ALBUM_ALL_BASIC, new Object[]{"visibilityType = 2 AND (groupId IS NULL OR groupId = '' OR groupId = serverId)", "visibilityType = 2 AND (groupId IS NULL OR groupId = '' OR groupId = serverId)", formatSelectionIn((List) PeopleCoverManager.getInstance().getCoverIds())});
    }

    public static String buildImageFaceQuery(String[] strArr, String str, String str2) {
        return buildAlbumQuery(strArr, (String) null, (String[]) null, (String) null, String.format(Locale.US, "SELECT p.serverId, p.faceXScale, p.faceYScale, p.faceWScale, p.faceHScale,    c._id as photo_id, c.serverId as photo_server_id, c.microthumbfile, c.thumbnailFile, c.localFile, c.sha1   FROM peopleface p JOIN facetoimages f JOIN cloud c   WHERE p.serverId='%s' AND photo_server_id='%s'   AND p.type = 'FACE' AND (p.localFlag =0 AND p.serverStatus='normal') AND p.serverId = faceId  AND imageServerId = c.serverId    AND (c.localFlag <>2 AND c.serverStatus='custom') ", new Object[]{str, str2}));
    }

    public static String buildOnePersonItemQuery(String[] strArr, String str) {
        return buildAlbumQuery(strArr, (String) null, (String[]) null, (String) null, String.format(Locale.US, SQL_ONE_PERSON_ALBUM_ITEM, new Object[]{str}));
    }

    public static String buildOnePersonQuery(String[] strArr, String str, String str2, String str3, String str4) {
        return buildAlbumQuery(strArr, str, (String[]) null, str2, String.format(Locale.US, SQL_ONE_PERSON_ALBUM_NORMAL, new Object[]{str3, str4}));
    }

    public static String buildPeopleCoverQuery(String[] strArr, String str, String str2) {
        String str3;
        if (str != null) {
            str3 = String.format(Locale.US, "serverId='%s'", new Object[]{str});
        } else {
            str3 = String.format(Locale.US, "_id=%s", new Object[]{str2});
        }
        String format = String.format(Locale.US, "(visibilityType = 1 OR visibilityType = 4 OR faceCount >= %d AND (visibilityType IS NULL OR visibilityType = 0))", new Object[]{1});
        return buildAlbumQuery(strArr, str3, (String[]) null, (String) null, String.format(Locale.US, SQL_PERSONS_ALBUM_ALL_BASIC, new Object[]{format, format, formatSelectionIn((List) PeopleCoverManager.getInstance().getCoverIds())}));
    }

    public static String buildPeopleTagQuery() {
        return "SELECT serverId, eTag FROM (SELECT serverId FROM peopleface WHERE type = 'PEOPLE' AND ( visibilityType = 1 OR visibilityType =4 OR (visibilityType =2 AND groupId is null)) AND localFlag NOT IN ( 2)) serverTable JOIN (SELECT groupId AS peopleId, MAX(eTag) as eTag FROM peopleface GROUP BY groupId) tagTable  ON serverTable.serverId = tagTable.peopleId";
    }

    public static String buildPersonsItemQuery(String[] strArr, String str, String str2, String str3, String str4) {
        return buildAlbumQuery(strArr, str, (String[]) null, str2, String.format(SQL_PERSONS_ALBUM, new Object[]{str3, str4}));
    }

    public static String buildPersonsQuery() {
        Locale locale = Locale.US;
        String str = SQL_PERSONS_ALBUM_ALL_BASIC;
        String str2 = PERSON_VISIBLE_CONDITION;
        return String.format(locale, str, new Object[]{str2, str2, formatSelectionIn((List) PeopleCoverManager.getInstance().getCoverIds())});
    }

    public static String buildRecommendFacesOfOnePersonQuery(String[] strArr, String str, String str2) {
        return buildAlbumQuery(strArr, (String) null, (String[]) null, str, String.format(Locale.US, SQL_ONE_PERSON_ALBUM_RECOMMEND, new Object[]{str2}));
    }

    public static String buildTopFaceCoverQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(SQL_FACE_ALBUM_COVER);
        sb.append(" FROM ");
        sb.append("( ");
        Locale locale = Locale.US;
        String str = SQL_PERSONS_ALBUM_ALL_BASIC;
        String str2 = PERSON_VISIBLE_CONDITION;
        sb.append(String.format(locale, str, new Object[]{str2, str2, formatSelectionIn((List) PeopleCoverManager.getInstance().getCoverIds())}));
        sb.append(")");
        return sb.toString();
    }

    public static ContentValues changeCursorData2ContentValuesOfCloudTable(Cursor cursor, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fileName", cursor.getString(cursor.getColumnIndex("fileName")));
        contentValues.put("dateTaken", Long.valueOf(cursor.getLong(cursor.getColumnIndex("dateTaken"))));
        contentValues.put("dateModified", Long.valueOf(cursor.getLong(cursor.getColumnIndex("dateModified"))));
        contentValues.put("size", Long.valueOf(cursor.getLong(cursor.getColumnIndex("size"))));
        contentValues.put("mimeType", cursor.getString(cursor.getColumnIndex("mimeType")));
        contentValues.put("title", cursor.getString(cursor.getColumnIndex("title")));
        contentValues.put("sha1", cursor.getString(cursor.getColumnIndex("sha1")));
        contentValues.put("ubiSubImageCount", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ubiSubImageCount"))));
        contentValues.put("ubiSubIndex", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ubiSubIndex"))));
        contentValues.put("ubiFocusIndex", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ubiFocusIndex"))));
        contentValues.put("localFlag", 6);
        contentValues.put("serverType", 1);
        contentValues.put("localGroupId", str);
        if (cursor.getColumnIndex("photo_id") >= 0) {
            contentValues.put("localImageId", cursor.getString(cursor.getColumnIndex("photo_id")));
        } else {
            contentValues.put("localImageId", cursor.getString(cursor.getColumnIndex("_id")));
        }
        if (cursor.getString(cursor.getColumnIndex("thumbnailFile")) != null) {
            contentValues.put("thumbnailFile", cursor.getString(cursor.getColumnIndex("thumbnailFile")));
        }
        if (cursor.getString(cursor.getColumnIndex("microthumbfile")) != null) {
            contentValues.put("microthumbfile", cursor.getString(cursor.getColumnIndex("microthumbfile")));
        }
        return contentValues;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.ArrayList<com.miui.gallery.provider.BabyLockWallpaperDataManager$BabyPhotoInfo>, android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ab, code lost:
        if (r0 != 0) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b5, code lost:
        if (r0 == 0) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b7, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ba, code lost:
        return r9;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    public static ArrayList<BabyLockWallpaperDataManager.BabyPhotoInfo> doQueryAllBabyAlbumPhotos(ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> arrayList) {
        ? r0 = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return r0;
        }
        Uri uri = GalleryContract.Cloud.CLOUD_URI;
        if (arrayList.get(0).isOtherShared) {
            uri = GalleryCloudUtils.SHARE_IMAGE_URI;
        }
        Uri uri2 = uri;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(arrayList.get(i).localId);
            if (i < size - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(")");
        ArrayList<BabyLockWallpaperDataManager.BabyPhotoInfo> arrayList2 = new ArrayList<>();
        try {
            r0 = GalleryApp.sGetAndroidContext().getContentResolver().query(uri2, BabyLockWallpaperDataManager.BABY_PHOTO_INFO_PROJECTION, "localGroupId in " + stringBuffer.toString() + " AND " + PHOTO_LOCAL_FLAG_CREATE_MOVED_SYNCED + " AND " + InternalContract.Cloud.ALIAS_LOCAL_MEDIA, (String[]) null, (String) null);
            while (r0 != 0 && r0.moveToNext()) {
                BabyLockWallpaperDataManager.BabyPhotoInfo babyPhotoInfo = new BabyLockWallpaperDataManager.BabyPhotoInfo();
                babyPhotoInfo.id = r0.getLong(r0.getColumnIndex("_id"));
                babyPhotoInfo.isOtherShared = arrayList.get(0).isOtherShared;
                arrayList2.add(babyPhotoInfo);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0064, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        if (r0 == null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0070, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        return r8;
     */
    private static ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> doQueryAllBabyAlbums(boolean z) {
        Uri uri = GalleryContract.Cloud.CLOUD_URI;
        String str = GROUP_LOCAL_FLAG_SYNCED_CREATED_RENAME;
        if (z) {
            uri = GalleryCloudUtils.SHARE_ALBUM_URI;
            str = Other_SHARED_GROUP_LOCAL_FLAG_SYNCED;
        }
        Uri uri2 = uri;
        Cursor cursor = null;
        ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> arrayList = new ArrayList<>();
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(uri2, BabyLockWallpaperDataManager.BABY_ALBUM_INFO_PROJECTION, "not (babyInfoJson is null ) AND " + str, (String[]) null, (String) null);
            while (cursor != null && cursor.moveToNext()) {
                BabyLockWallpaperDataManager.BabyAlbumInfo babyAlbumInfo = new BabyLockWallpaperDataManager.BabyAlbumInfo();
                babyAlbumInfo.localId = cursor.getLong(cursor.getColumnIndex("_id"));
                babyAlbumInfo.name = cursor.getString(cursor.getColumnIndex("fileName"));
                if (z) {
                    babyAlbumInfo.isOtherShared = true;
                } else {
                    babyAlbumInfo.isOtherShared = false;
                }
                arrayList.add(babyAlbumInfo);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static String formatSelectionIn(List list) {
        return formatSelectionIn(list, MovieStatUtils.DOWNLOAD_FAILED);
    }

    public static String formatSelectionIn(List list, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (MiscUtil.isValid(list)) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append("'");
                stringBuffer.append(list.get(i));
                stringBuffer.append("'");
                if (i < size - 1) {
                    stringBuffer.append(',');
                }
            }
        } else {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private static String formatSelectionIn(long[] jArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(jArr[i]);
            if (i < length - 1) {
                stringBuffer.append(',');
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0020 A[SYNTHETIC] */
    private static Cursor generateGroupHeaders(Cursor cursor, String[] strArr) {
        if (cursor.getColumnIndex("mixedDateTime") >= 0 && cursor.getColumnIndex("location") >= 0) {
            LinkedList linkedList = new LinkedList();
            TimelineHeadersGroup timelineHeadersGroup = null;
            int i = 0;
            Integer num = 1;
            int i2 = 0;
            while (cursor.moveToNext()) {
                int format = GalleryDateUtils.format(cursor.getLong(cursor.getColumnIndex("mixedDateTime")));
                String string = cursor.getString(cursor.getColumnIndex("location"));
                if (i == 0) {
                    timelineHeadersGroup = new TimelineHeadersGroup();
                    timelineHeadersGroup.start = i2;
                    timelineHeadersGroup.itemLocations.add(string);
                    if (!TextUtils.isEmpty(string)) {
                        timelineHeadersGroup.validLocation = string;
                    }
                } else if (format == i) {
                    num = Integer.valueOf(num.intValue() + 1);
                    timelineHeadersGroup.itemLocations.add(string);
                    if (TextUtils.isEmpty(timelineHeadersGroup.validLocation) && !TextUtils.isEmpty(string)) {
                        timelineHeadersGroup.validLocation = string;
                    }
                    i2++;
                    if (i2 != cursor.getCount()) {
                        timelineHeadersGroup.count = num.intValue();
                        linkedList.add(timelineHeadersGroup);
                    }
                } else {
                    timelineHeadersGroup.count = num.intValue();
                    linkedList.add(timelineHeadersGroup);
                    timelineHeadersGroup = new TimelineHeadersGroup();
                    timelineHeadersGroup.start = i2;
                    timelineHeadersGroup.itemLocations.add(string);
                    if (!TextUtils.isEmpty(string)) {
                        timelineHeadersGroup.validLocation = string;
                    }
                    num = 1;
                }
                i = format;
                i2++;
                if (i2 != cursor.getCount()) {
                }
            }
            TimelineHeadersGroup.bindGroup(linkedList, cursor);
        }
        return cursor;
    }

    private static FaceRegionRectF getFacePositionRectOfCoverImage(Cursor cursor) {
        return cursor == null ? new FaceRegionRectF(0.0f, 0.0f, 0.0f, 0.0f, 0) : new FaceRegionRectF(cursor.getFloat(cursor.getColumnIndex("faceXScale")), cursor.getFloat(cursor.getColumnIndex("faceYScale")), cursor.getFloat(cursor.getColumnIndex("faceXScale")) + cursor.getFloat(cursor.getColumnIndex("faceWScale")), cursor.getFloat(cursor.getColumnIndex("faceYScale")) + cursor.getFloat(cursor.getColumnIndex("faceHScale")), cursor.getInt(cursor.getColumnIndex("exifOrientation")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0063, code lost:
        if (r8 == null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0065, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0068, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0059, code lost:
        if (r8 != null) goto L_0x0065;
     */
    public static ArrayList<BasicPeopleInfo> getPeopleBasicInfoByIds(long[] jArr) {
        String[] strArr = BASIC_PEOPLE_INFO_PROJECTION;
        String str = "_id in (" + formatSelectionIn(jArr) + ")";
        ArrayList<BasicPeopleInfo> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = GalleryDBHelper.getInstance().getReadableDatabase().query("peopleFace", strArr, str, (String[]) null, (String) null, (String) null, (String) null);
            while (cursor != null && cursor.moveToNext()) {
                arrayList.add(new BasicPeopleInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static int getPeopleLocalFlagByLocalID(String str) {
        return ((Integer) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.PeopleFace.PEOPLE_FACE_URI, new String[]{"localFlag"}, "_id = ?", new String[]{str}, (String) null, new SafeDBUtil.QueryHandler<Integer>() {
            public Integer handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToNext()) {
                    return -1;
                }
                return Integer.valueOf(cursor.getInt(0));
            }
        })).intValue();
    }

    public static long getPeopleLocalIdByServerId(String str) {
        String[] strArr = {"_id"};
        Cursor cursor = null;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.PEOPLE_FACE_URI, strArr, "serverId = ?", new String[]{str}, (String) null);
            if (cursor == null || !cursor.moveToNext()) {
                if (cursor == null) {
                    return -1;
                }
                cursor.close();
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (Exception unused) {
            if (cursor == null) {
                return -1;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static final boolean localCopyFaceImages2BabyAlbum(ContentValues contentValues, boolean z) {
        String asString = contentValues.getAsString("exifGPSDateStamp");
        String asString2 = contentValues.getAsString("exifGPSTimeStamp");
        String asString3 = contentValues.getAsString("exifDateTime");
        Long asLong = contentValues.getAsLong("dateTaken");
        Long asLong2 = contentValues.getAsLong("dateModified");
        contentValues.put("mixedDateTime", Long.valueOf(GalleryTimeUtils.getTakenDateTime(asString, asString2, asString3, asLong != null ? asLong.longValue() : 0, asLong2 != null ? asLong2.longValue() : 0)));
        return GalleryApp.sGetAndroidContext().getContentResolver().insert(z ? GalleryCloudUtils.SHARE_IMAGE_URI : GalleryContract.Cloud.CLOUD_URI, contentValues) != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        if (r9 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        if (r9 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    private static String queryAStringColumn(String str, String str2, String str3) {
        Cursor cursor;
        String[] strArr = {str3};
        Cursor cursor2 = null;
        try {
            cursor = GalleryDBHelper.getInstance().getReadableDatabase().query("peopleFace", strArr, str + " = ? ", new String[]{str2}, (String) null, (String) null, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        String string = cursor.getString(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                    }
                    throw th;
                }
            }
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public static ArrayList<BabyLockWallpaperDataManager.BabyPhotoInfo> queryAllBabyAlbumPhotos(ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<BabyLockWallpaperDataManager.BabyPhotoInfo> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (arrayList.get(i).isOtherShared) {
                arrayList4.add(arrayList.get(i));
            } else {
                arrayList3.add(arrayList.get(i));
            }
        }
        if (arrayList3.size() > 0) {
            arrayList2.addAll(doQueryAllBabyAlbumPhotos(arrayList3));
        }
        if (arrayList4.size() > 0) {
            arrayList2.addAll(doQueryAllBabyAlbumPhotos(arrayList4));
        }
        return arrayList2;
    }

    public static ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> queryAllBabyAlbums() {
        ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> arrayList = new ArrayList<>();
        arrayList.addAll(doQueryAllBabyAlbums(false));
        arrayList.addAll(doQueryAllBabyAlbums(true));
        return arrayList;
    }

    public static Cursor queryAllImagesOfOnePerson(String str) {
        return GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.ONE_PERSON_URI, CLOUD_IMAGE_OF_FACE_PROJECTION, (String) null, new String[]{str, MovieStatUtils.DOWNLOAD_FAILED}, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r7 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002b, code lost:
        if (r7 != null) goto L_0x0037;
     */
    public static ArrayList<String> queryAllPeopleAlbumServerIds() {
        Uri uri = GalleryContract.PeopleFace.PEOPLE_FACE_URI;
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(uri, PEOPLE_ALBUM_SERVER_ID_PROJECTION, "type = 'PEOPLE' AND ( visibilityType = 1 OR visibilityType =4) AND localFlag NOT IN ( 13, 2)", (String[]) null, (String) null);
            while (cursor != null && cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static ArrayList<String> queryAllUserDefineRelationsOfPeople(Context context) {
        return (ArrayList) SafeDBUtil.safeQuery(context, GalleryContract.PeopleFace.PEOPLE_FACE_URI, PEOPLE_RELATION_PROJECTION, PEOPLE_USER_DEFINE_RELATION_SELECTION, (String[]) null, PeopleContactInfo.getRelationOrderSql() + ", " + PeopleContactInfo.getUserDefineRelationOrderSql(), new SafeDBUtil.QueryHandler<ArrayList<String>>() {
            public ArrayList<String> handle(Cursor cursor) {
                ArrayList<String> arrayList = new ArrayList<>();
                while (cursor != null && cursor.moveToNext()) {
                    String string = cursor.getString(cursor.getColumnIndex("relationText"));
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                }
                return arrayList;
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        if (r1 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0042, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0044, code lost:
        r1.close();
     */
    public static long queryBabyAlbumByPeopleId(String str) {
        Cursor cursor = null;
        long j = -1;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, "peopleId = ?  AND" + GROUP_LOCAL_FLAG_SYNCED_CREATED_RENAME, new String[]{str}, (String) null);
            if (cursor != null && cursor.moveToNext()) {
                j = cursor.getLong(cursor.getColumnIndex("_id"));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r14v1 */
    /* JADX WARNING: type inference failed for: r14v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r14v4 */
    /* JADX WARNING: type inference failed for: r14v5 */
    /* JADX WARNING: type inference failed for: r14v7 */
    /* JADX WARNING: type inference failed for: r14v9 */
    /* JADX WARNING: type inference failed for: r14v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r15 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        r15.close();
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
        if (r15 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        return r14;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    public static String queryBabyAlbumCover(String str, boolean z) {
        Cursor cursor;
        String format = String.format(Locale.US, AlbumManager.SQL_BABY_ALBUM_COVER, new Object[]{str});
        if (z) {
            format = String.format(Locale.US, AlbumManager.SQL_BABY_OTHER_SHARED_ALBUM_COVER, new Object[]{str});
        }
        ? r14 = 0;
        try {
            cursor = GalleryDBHelper.getInstance().getReadableDatabase().query(format, new String[]{"cover_path"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        r14 = cursor.getString(0);
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    Cursor cursor2 = cursor;
                    th = th;
                    r14 = cursor2;
                    if (r14 != 0) {
                    }
                    throw th;
                }
            }
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (r14 != 0) {
                r14.close();
            }
            throw th;
        }
    }

    public static PeopleContactInfo queryContactInfoOfOnePerson(long j) {
        String queryAStringColumn = queryAStringColumn("_id", String.valueOf(j), "peopleContactJsonInfo");
        if (TextUtils.isEmpty(queryAStringColumn)) {
            return null;
        }
        return PeopleContactInfo.fromJson(queryAStringColumn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r1 == null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        if (r1 != null) goto L_0x0031;
     */
    public static int queryCountOfPhotosOfOneRecommendAlbum(String str) {
        Cursor cursor = null;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.RECOMMEND_FACES_OF_ONE_PERSON_URI, ID_COUNT_PROJECTION, (String) null, new String[]{str}, (String) null);
            if (cursor != null) {
                int count = cursor.getCount();
                if (cursor != null) {
                    cursor.close();
                }
                return count;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (r1 == null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
        if (r1 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0037, code lost:
        r1.close();
     */
    public static String queryCoverImageOfOneFace(String str, RectF[] rectFArr) {
        String str2 = "";
        Cursor cursor = null;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.ONE_PERSON_ITEM_URI, SQL_FACE_ALBUM_COVER_PROJECTION, (String) null, new String[]{str}, "dateTaken DESC ");
            if (cursor != null && cursor.moveToNext()) {
                rectFArr[0] = getFacePositionRectOfCoverImage(cursor);
                str2 = cursor.getString(cursor.getColumnIndex("cover_path"));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0078, code lost:
        if (r1 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0085, code lost:
        if (r1 == null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0088, code lost:
        return r0;
     */
    public static String queryCoverImageOfOnePerson(String str, RectF[] rectFArr) {
        String str2 = "";
        Cursor cursor = null;
        try {
            boolean z = true;
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.ONE_PERSON_URI, SQL_FACE_ALBUM_COVER_PROJECTION, (String) null, new String[]{str, MovieStatUtils.DOWNLOAD_FAILED}, "faceCoverScore DESC, dateTaken DESC ");
            int columnIndex = cursor.getColumnIndex("cover_path");
            int columnIndex2 = cursor.getColumnIndex("serverId");
            int columnIndex3 = cursor.getColumnIndex("faceCoverScore");
            ArrayList coverIds = PeopleCoverManager.getInstance().getCoverIds();
            if (cursor != null && cursor.moveToFirst()) {
                if (cursor.getDouble(columnIndex3) < 0.0d) {
                    while (true) {
                        if (!coverIds.contains(cursor.getString(columnIndex2))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            rectFArr[0] = getFacePositionRectOfCoverImage(cursor);
                            str2 = cursor.getString(columnIndex);
                            break;
                        }
                    }
                    z = false;
                } else {
                    z = false;
                }
                if (!z) {
                    cursor.moveToFirst();
                    rectFArr[0] = getFacePositionRectOfCoverImage(cursor);
                    str2 = cursor.getString(columnIndex);
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static Cursor queryOnePersonAlbum(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String str2, String str3, String str4, boolean z) {
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery(buildOnePersonQuery(strArr, str, str2, str3, str4), (String[]) null);
            return (!z || rawQuery == null || rawQuery.getCount() <= 0) ? rawQuery : generateGroupHeaders(rawQuery, strArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Long> queryPeopleIdOfRelation(Context context, final int i) {
        return (List) SafeDBUtil.safeQuery(context, GalleryContract.PeopleFace.PERSONS_URI, (String[]) null, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<ArrayList<Long>>() {
            public ArrayList<Long> handle(Cursor cursor) {
                if (cursor == null) {
                    return null;
                }
                ArrayList<Long> arrayList = new ArrayList<>();
                int columnIndex = cursor.getColumnIndex("_id");
                int columnIndex2 = cursor.getColumnIndex("relationType");
                while (cursor.moveToNext()) {
                    if (cursor.getInt(columnIndex2) == i) {
                        arrayList.add(Long.valueOf(cursor.getLong(columnIndex)));
                    }
                }
                return arrayList;
            }
        });
    }

    public static String queryPersonName(long j) {
        return queryAStringColumn("_id", String.valueOf(j), "peopleName");
    }

    public static String queryPersonName(String str) {
        return queryAStringColumn("serverId", str, "peopleName");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        if (r7 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r7 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    public static String querySharerInfoOfBabyAlbum(String str) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryCloudUtils.SHARE_ALBUM_URI, CloudUtils.getProjectionAll(), "_id = ? ", new String[]{str}, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.getCount() != 0) {
                        if (cursor.moveToNext()) {
                            String string = cursor.getString(cursor.getColumnIndex("sharerInfo"));
                            if (cursor != null) {
                                cursor.close();
                            }
                            return string;
                        }
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        if (r0 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
        r0.close();
     */
    public static long queryTimeOfOldestImagesOfOnePerson(String str) {
        Cursor cursor = null;
        long j = 0;
        try {
            cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.ONE_PERSON_URI, SQL_OLDEST_FACE_OF_ALBUM_PROJECTION, (String) null, new String[]{str, MovieStatUtils.DOWNLOAD_FAILED}, (String) null);
            if (cursor != null && cursor.moveToNext()) {
                j = cursor.getLong(cursor.getColumnIndex("oldest_image_time"));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void safeUpdatePeopleFaceByIds(ContentValues contentValues, ArrayList<String> arrayList) {
        try {
            GalleryApp.sGetAndroidContext().getContentResolver().update(GalleryContract.PeopleFace.PEOPLE_FACE_URI, contentValues, "_id in (" + TextUtils.join(",", arrayList) + ")", (String[]) null);
        } catch (Exception unused) {
        }
    }
}
