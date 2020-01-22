package com.miui.gallery.card.scenario;

import com.miui.gallery.cloud.CloudTableUtils;

public class ScenarioConstants {
    public static final String IMAGE_FEATURE_SELECTION = ("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif')) AND (localGroupId in (SELECT _id FROM cloud WHERE (serverType=0) AND attributes&4!=0)) AND localGroupId != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1000) + " AND " + "localGroupId" + " != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1001));
    public static final String SCENARIO_NON_CAMERA_SELECTION = ("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif', 'image/x-adobe-dng')) AND localGroupId != (SELECT _id FROM cloud WHERE serverId=1) AND localGroupId != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1000) + " AND " + "localGroupId" + " != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1001));
    public static final Integer[] TAGS_ACTIVITY = {52, 56, 57};
    public static final Integer[] TAGS_CAT = {40};
    public static final Integer[] TAGS_DOG = {41};
    public static final Integer[] TAGS_FOODS = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
    public static final Integer[] TAGS_PARTY = {2, 51};
    public static final Integer[] TAGS_PETS = {40, 41};
    public static final Integer[] TAGS_SPORTS = {80, 81, 82, 84, 85, 86, 87, 88, 103, 90, 92, 99};
}
