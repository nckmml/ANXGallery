package com.miui.gallery.cloud;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Locale;

public class CloudTableUtils {
    public static final String IS_VALID_GROUP = String.format(Locale.US, "(%s = %d AND (%s = %d OR %s = %d OR (%s = %d AND %s = \"%s\")))", new Object[]{"serverType", 0, "localFlag", 8, "localFlag", 10, "localFlag", 0, "serverStatus", "custom"});
    private static final ArrayList<Long> sIdsForGroupWithoutRecord = new ArrayList<>();
    public static final String sItemIsNotGroup = String.format(Locale.US, "(%s = %d OR %s = %d)", new Object[]{"serverType", 1, "serverType", 2});
    public static String sPhotoLocalFlag_Create_ForceCreate_Move_Copy = String.format(Locale.US, "(%s = %d OR %s = %d OR %s = %d OR %s = %d)", new Object[]{"localFlag", 8, "localFlag", 5, "localFlag", 6, "localFlag", 9});
    public static final String sPhotoLocalFlag_Synced = String.format(Locale.US, "(%s = %d AND %s = \"%s\")", new Object[]{"localFlag", 0, "serverStatus", "custom"});
    private static final ArrayList<Long> sSystemAlbumGroupIds = new ArrayList<>();

    static {
        sSystemAlbumGroupIds.add(1L);
        sSystemAlbumGroupIds.add(2L);
        sSystemAlbumGroupIds.add(3L);
        sSystemAlbumGroupIds.add(4L);
        sSystemAlbumGroupIds.add(1000L);
        sSystemAlbumGroupIds.add(1001L);
        sIdsForGroupWithoutRecord.add(3L);
        sIdsForGroupWithoutRecord.add(4L);
        sIdsForGroupWithoutRecord.add(1000L);
        sIdsForGroupWithoutRecord.add(1001L);
    }

    public static long getCloudIdForGroupWithoutRecord(long j) {
        if (isGroupWithoutRecord(j)) {
            return j * -1;
        }
        return 0;
    }

    public static long getServerIdForGroupWithoutRecord(long j) {
        if (isGroupWithoutRecordByCloudId(j)) {
            return j * -1;
        }
        return 0;
    }

    public static boolean isCameraGroup(String str) {
        return String.valueOf(1).equals(str);
    }

    public static final boolean isGroupWithoutRecord(long j) {
        return sIdsForGroupWithoutRecord.contains(Long.valueOf(j));
    }

    public static final boolean isGroupWithoutRecordByCloudId(long j) {
        return sIdsForGroupWithoutRecord.contains(Long.valueOf(j * -1));
    }

    public static boolean isScreenshotGroup(String str) {
        return String.valueOf(2).equals(str);
    }

    public static boolean isSecretAlbum(String str, String str2) {
        return (!TextUtils.isEmpty(str) ? Long.parseLong(str) : !TextUtils.isEmpty(str2) ? getServerIdForGroupWithoutRecord(Long.parseLong(str2)) : 0) == 1000;
    }

    public static boolean isSystemAlbum(long j) {
        return sSystemAlbumGroupIds.contains(Long.valueOf(j));
    }

    public static String sGetWhereClauseAll(String str, String str2, int i) {
        String format = String.format(Locale.US, "(%d = %d OR %d = %d)", new Object[]{0, Integer.valueOf(i), 10, Integer.valueOf(i)});
        String format2 = String.format(Locale.US, "(%d = %d OR %d = %d OR %d = %d)", new Object[]{0, Integer.valueOf(i), 8, Integer.valueOf(i), 10, Integer.valueOf(i)});
        return String.format(Locale.US, "( (%s) AND ((%s AND (+%s = %s AND %s)) OR (%s AND (%s = %s AND %s))) )", new Object[]{sItemIsNotGroup, sPhotoLocalFlag_Synced, "groupId", str2, format, sPhotoLocalFlag_Create_ForceCreate_Move_Copy, "localGroupId", str, format2});
    }
}
