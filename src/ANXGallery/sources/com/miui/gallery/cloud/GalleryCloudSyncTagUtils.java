package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.card.SyncTagUtil;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.SyncLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GalleryCloudSyncTagUtils {
    private static HashMap<String, String> sColumnNameToPushNameMap = new HashMap<>();
    public static HashMap<Integer, SyncTagConstant> sSyncTagConstantsMap = new HashMap<>();
    private static final Object sSyncTagLock = new Object();

    public static class SyncTagConstant {
        public final String columnName;
        public final int initValue;
        public final String jsonTagInput;
        public final String jsonTagOutput;
        public final String pushName;
        public final boolean shouldCheckInit;
        public final boolean shouldInsertCloudSetting;
        public final String syncInfoColumnName;
        public final Uri uri;

        public SyncTagConstant(String str, String str2, String str3, String str4, int i, boolean z, boolean z2, Uri uri2, String str5) {
            this.columnName = str;
            this.jsonTagInput = str2;
            this.jsonTagOutput = str3;
            this.pushName = str4;
            this.initValue = i;
            this.shouldInsertCloudSetting = z;
            this.shouldCheckInit = z2;
            this.syncInfoColumnName = str5;
            this.uri = uri2;
        }

        public boolean hasSyncInfo() {
            return this.syncInfoColumnName != null;
        }
    }

    public static class SyncTagItem {
        public long currentValue;
        public long serverValue;
        public boolean shouldSync = true;
        public final int syncTagType;

        public SyncTagItem(int i) {
            this.syncTagType = i;
        }
    }

    static {
        sColumnNameToPushNameMap.put("syncTag", "micloud.gallery.sync");
        sColumnNameToPushNameMap.put("shareSyncTagAlbumList", "micloud.gallery.albumlist.sync");
        sColumnNameToPushNameMap.put("shareSyncTagAlbumInfo", "micloud.gallery.albuminfo.sync");
        sColumnNameToPushNameMap.put("ownerSyncTagUserList", "micloud.gallery.sharerlist.sync");
        sColumnNameToPushNameMap.put("shareSyncTagImageList", "micloud.gallery.imagelist.sync");
        sColumnNameToPushNameMap.put("faceWatermark", "gallery-facerecognition");
        sSyncTagConstantsMap.put(1, new SyncTagConstant("syncTag", "syncTag", "syncTag", "micloud.gallery.sync", 0, true, false, GalleryCloudUtils.CLOUD_SETTING_URI, "syncInfo"));
        sSyncTagConstantsMap.put(2, new SyncTagConstant("ownerSyncTagUserList", "mySharerListsTag", "sharerlist", "micloud.gallery.sharerlist.sync", 0, true, false, GalleryCloudUtils.CLOUD_SETTING_URI, "shareSyncInfo"));
        sSyncTagConstantsMap.put(3, new SyncTagConstant("shareSyncTagAlbumList", "albumListTag", "albumlist", "micloud.gallery.albumlist.sync", 0, true, true, GalleryCloudUtils.CLOUD_SETTING_URI, "shareSyncInfo"));
        sSyncTagConstantsMap.put(4, new SyncTagConstant("shareSyncTagAlbumInfo", "albumInfoTag", "albuminfo", "micloud.gallery.albuminfo.sync", 0, true, true, GalleryCloudUtils.CLOUD_SETTING_URI, "shareSyncInfo"));
        sSyncTagConstantsMap.put(5, new SyncTagConstant("shareSyncTagImageList", "imageListTag", "imagelist", "micloud.gallery.imagelist.sync", 0, true, true, GalleryCloudUtils.CLOUD_SETTING_URI, "shareSyncInfo"));
        sSyncTagConstantsMap.put(8, new SyncTagConstant("albumImageTag", "syncTag", "syncTag", (String) null, 0, false, false, GalleryCloudUtils.SHARE_ALBUM_URI, "syncInfo"));
        sSyncTagConstantsMap.put(9, new SyncTagConstant("albumUserTag", "updateTag", "syncTag", (String) null, 0, false, false, GalleryCloudUtils.SHARE_ALBUM_URI, (String) null));
        sSyncTagConstantsMap.put(10, new SyncTagConstant("albumUserTag", "updateTag", "syncTag", (String) null, 0, false, false, GalleryCloudUtils.CLOUD_URI, (String) null));
        sSyncTagConstantsMap.put(11, new SyncTagConstant("faceWatermark", (String) null, (String) null, "gallery-facerecognition", 0, false, false, GalleryCloudUtils.CLOUD_SETTING_URI, (String) null));
    }

    public static String getAccountSelections(Account account) {
        return "accountName = '" + account.name + "' and " + "accountType" + " = '" + account.type + "'";
    }

    public static String getCardSyncInfo(Account account) {
        return SyncTagUtil.getCardSyncInfo(account);
    }

    public static long getCardSyncTag(Account account) {
        return SyncTagUtil.getCardSyncTag(account);
    }

    public static String getColumnName(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.columnName;
        }
        return null;
    }

    public static String getFaceSyncToken(Account account) {
        return (String) GalleryUtils.safeQuery(CloudUtils.getLimitUri(GalleryCloudUtils.CLOUD_SETTING_URI, 1), new String[]{"faceSyncToken"}, getAccountSelections(account), (String[]) null, (String) null, new GalleryUtils.QueryHandler<String>() {
            public String handle(Cursor cursor) {
                return (cursor == null || !cursor.moveToNext()) ? "" : cursor.getString(0);
            }
        });
    }

    public static int getInitSyncTagValue(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.initValue;
        }
        return 0;
    }

    public static String getJsonTagInput(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.jsonTagInput;
        }
        return null;
    }

    public static String getJsonTagOutput(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.jsonTagOutput;
        }
        return null;
    }

    public static String getSyncInfoColumnName(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.syncInfoColumnName;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0065, code lost:
        if (r1 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        if (r1 == null) goto L_0x0074;
     */
    public static ArrayList<SyncTagItem> getSyncTag(Context context, Account account, ArrayList<SyncTagItem> arrayList) {
        synchronized (sSyncTagLock) {
            Cursor cursor = null;
            try {
                cursor = getSyncTagCursorByAccount(context, account, arrayList);
                if (cursor == null || !cursor.moveToNext()) {
                    Iterator<SyncTagItem> it = arrayList.iterator();
                    while (it.hasNext()) {
                        SyncTagItem next = it.next();
                        next.currentValue = (long) sSyncTagConstantsMap.get(Integer.valueOf(next.syncTagType)).initValue;
                    }
                } else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        arrayList.get(i).currentValue = Math.max(cursor.getLong(i), (long) sSyncTagConstantsMap.get(Integer.valueOf(arrayList.get(i).syncTagType)).initValue);
                    }
                }
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        return arrayList;
    }

    private static Cursor getSyncTagCursorByAccount(Context context, Account account, ArrayList<SyncTagItem> arrayList) {
        if (account != null) {
            return context.getContentResolver().query(GalleryCloudUtils.CLOUD_SETTING_URI, getSyncTagSelection(arrayList), getAccountSelections(account), (String[]) null, (String) null);
        }
        SyncLog.e("GalleryCloudSyncTagUtils", "account is null");
        return null;
    }

    public static String[] getSyncTagSelection(ArrayList<SyncTagItem> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return new String[]{" * "};
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = sSyncTagConstantsMap.get(Integer.valueOf(arrayList.get(i).syncTagType)).columnName;
        }
        return strArr;
    }

    public static Uri getUri(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.uri;
        }
        return null;
    }

    public static boolean hasSyncInfo(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.hasSyncInfo();
        }
        return false;
    }

    public static void insertAccountToDB(Context context, Account account) {
        if (!TextUtils.isEmpty(account.name) && !TextUtils.isEmpty(account.type)) {
            synchronized (sSyncTagLock) {
                ContentValues contentValues = new ContentValues();
                for (SyncTagConstant next : sSyncTagConstantsMap.values()) {
                    if (next.shouldInsertCloudSetting) {
                        contentValues.put(next.columnName, Integer.valueOf(next.initValue));
                    }
                }
                internalUpdateAccount(context, account, contentValues, (ArrayList<SyncTagItem>) null);
            }
            SyncTagUtil.ensureAccount(account);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    private static void internalUpdateAccount(Context context, Account account, ContentValues contentValues, ArrayList<SyncTagItem> arrayList) {
        Cursor cursor;
        try {
            cursor = getSyncTagCursorByAccount(context, account, arrayList);
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        if (arrayList != null) {
                            if (!arrayList.isEmpty()) {
                                GalleryUtils.safeUpdate(GalleryCloudUtils.CLOUD_SETTING_URI, contentValues, (String) null, (String[]) null);
                                postUpdateSyncTag(context, contentValues);
                                if (cursor == null) {
                                    cursor.close();
                                    return;
                                }
                                return;
                            }
                        }
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            contentValues.put("accountName", account.name);
            contentValues.put("accountType", account.type);
            GalleryUtils.safeInsert(GalleryCloudUtils.CLOUD_SETTING_URI, contentValues);
            postUpdateSyncTag(context, contentValues);
            if (cursor == null) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void postUpdateSyncTag(Context context, ContentValues contentValues) {
        for (Map.Entry next : contentValues.valueSet()) {
            String str = sColumnNameToPushNameMap.get(next.getKey());
            if (!TextUtils.isEmpty(str)) {
                SyncLog.d("GalleryCloudSyncTagUtils", "pushName:" + str + ", pushData:" + next.getValue());
            }
        }
    }

    public static void setCardSyncInfo(Account account, String str) {
        SyncTagUtil.setCardSyncInfo(account, str);
    }

    public static void setCardSyncTag(Account account, long j) {
        SyncTagUtil.setCardSyncTag(account, j);
    }

    public static void setFaceSyncToken(Account account, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("faceSyncToken", str);
        GalleryUtils.safeUpdate(GalleryCloudUtils.CLOUD_SETTING_URI, contentValues, getAccountSelections(account), (String[]) null);
    }

    public static void setFaceSyncWatermark(Account account, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("faceWatermark", Long.valueOf(j));
        GalleryUtils.safeUpdate(GalleryCloudUtils.CLOUD_SETTING_URI, contentValues, getAccountSelections(account), (String[]) null);
    }

    public static boolean shouldSyncTagValue(int i) {
        SyncTagConstant syncTagConstant = sSyncTagConstantsMap.get(Integer.valueOf(i));
        if (syncTagConstant != null) {
            return syncTagConstant.shouldCheckInit;
        }
        return false;
    }
}
