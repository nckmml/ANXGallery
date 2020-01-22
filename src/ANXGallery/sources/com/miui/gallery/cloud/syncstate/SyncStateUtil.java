package com.miui.gallery.cloud.syncstate;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.deviceprovider.UploadStatusController;
import java.util.Locale;
import miui.cloud.MiCloudCompat;
import miui.cloud.sync.MiCloudStatusInfo;

public class SyncStateUtil {

    public static class CloudSpaceInfo {
        private MiCloudStatusInfo.QuotaInfo mInfo;

        public CloudSpaceInfo(Context context) {
            MiCloudStatusInfo miCloudStatusInfo;
            MiCloudStatusInfo.QuotaInfo quotaInfo = null;
            try {
                miCloudStatusInfo = MiCloudStatusInfo.fromUserData(context);
            } catch (Exception e) {
                e.printStackTrace();
                miCloudStatusInfo = null;
            }
            this.mInfo = miCloudStatusInfo != null ? miCloudStatusInfo.getQuotaInfo() : quotaInfo;
        }

        public long getTotal() {
            MiCloudStatusInfo.QuotaInfo quotaInfo = this.mInfo;
            if (quotaInfo != null) {
                return quotaInfo.getTotal();
            }
            return 0;
        }

        public long getUsed() {
            MiCloudStatusInfo.QuotaInfo quotaInfo = this.mInfo;
            if (quotaInfo != null) {
                return quotaInfo.getUsed();
            }
            return 0;
        }

        public boolean isSpaceLow() {
            MiCloudStatusInfo.QuotaInfo quotaInfo = this.mInfo;
            return quotaInfo == null || quotaInfo.isSpaceFull() || this.mInfo.isSpaceLowPercent();
        }
    }

    public static CloudSpaceInfo getCloudSpaceInfo(Context context) {
        return new CloudSpaceInfo(context);
    }

    public static DirtyCount getDirtyCount(Context context) {
        String format = String.format(Locale.US, "(%s) AND (%s = %s OR %s = %s) AND (%s = %s OR %s = %s) GROUP BY %s, %s", new Object[]{CloudUtils.SELECTION_OWNER_NEED_SYNC, "serverType", 1, "serverType", 2, "localFlag", 7, "localFlag", 8, "serverType", "oversized"});
        String format2 = String.format(Locale.US, "(%s = %d OR %s = %d)  GROUP BY %s, %s", new Object[]{"localFlag", 7, "localFlag", 8, "serverType", "oversized"});
        String[] strArr = {"count(*)", "serverType", String.format(Locale.US, " CASE WHEN ((serverType = 1 AND size < %s) OR (serverType = 2 AND size < %s)) THEN 0 ELSE 1 END AS oversized", new Object[]{Long.valueOf(CloudUtils.getMaxImageSizeLimit()), Long.valueOf(CloudUtils.getMaxVideoSizeLimit())})};
        return queryDirtyCount(context, GalleryCloudUtils.CLOUD_URI, strArr, format).plus(queryDirtyCount(context, GalleryCloudUtils.SHARE_IMAGE_URI, strArr, format2));
    }

    public static long[] getDirtySize(Context context) {
        String format = String.format(Locale.US, "(%s) AND (%s = %s OR %s = %s) AND (%s = %s OR %s = %s) GROUP BY %s", new Object[]{CloudUtils.SELECTION_OWNER_NEED_SYNC, "serverType", 1, "serverType", 2, "localFlag", 7, "localFlag", 8, "serverType"});
        String format2 = String.format(Locale.US, "(%s = %d OR %s = %d) GROUP BY %s", new Object[]{"localFlag", 7, "localFlag", 8, "serverType"});
        long[] querySize = querySize(context, GalleryContract.Cloud.CLOUD_URI, format);
        long[] querySize2 = querySize(context, GalleryContract.ShareImage.SHARE_URI, format2);
        return new long[]{querySize[0] + querySize2[0], querySize[1] + querySize2[1]};
    }

    public static String getQuantityStringWithUnit(Context context, long j) {
        return MiCloudCompat.getQuantityStringWithUnit(j);
    }

    public static int[] getSyncedCount(Context context) {
        return querySyncedCount(context, GalleryContract.Cloud.CLOUD_URI, String.format(Locale.US, "%s AND (%s = %s OR %s = %s) GROUP BY %s", new Object[]{"(localFlag = 0 AND serverStatus = 'custom')", "serverType", 1, "serverType", 2, "serverType"}));
    }

    public static long[] getSyncedSize(Context context) {
        return querySize(context, GalleryContract.Cloud.CLOUD_URI, String.format(Locale.US, "%s AND (%s = %s OR %s = %s) GROUP BY %s", new Object[]{"(localFlag = 0 AND serverStatus = 'custom')", "serverType", 1, "serverType", 2, "serverType"}));
    }

    public static boolean hasSyncedData(Context context) {
        return ((Integer) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, new String[]{"count(*)"}, String.format(Locale.US, "%s AND %s", new Object[]{"(localFlag = 0 AND serverStatus = 'custom')", "serverType IN (1,2)"}), (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Integer>() {
            public Integer handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToFirst()) {
                    return 0;
                }
                return Integer.valueOf(cursor.getInt(0));
            }
        })).intValue() > 0;
    }

    public static boolean isMasterSyncAutomatically() {
        return ContentResolver.getMasterSyncAutomatically();
    }

    public static boolean isSyncActive() {
        Account account = AccountCache.getAccount();
        if (account == null) {
            return false;
        }
        return ContentResolver.isSyncActive(account, "com.miui.gallery.cloud.provider");
    }

    public static boolean isSyncAutomatically() {
        Account account = AccountCache.getAccount();
        if (account == null) {
            return false;
        }
        return ContentResolver.getSyncAutomatically(account, "com.miui.gallery.cloud.provider");
    }

    public static boolean isSyncPending() {
        Account account = AccountCache.getAccount();
        if (account == null) {
            return false;
        }
        return ContentResolver.isSyncPending(account, "com.miui.gallery.cloud.provider");
    }

    public static boolean isUploading() {
        return UploadStatusController.getInstance().isUploading();
    }

    private static DirtyCount queryDirtyCount(Context context, Uri uri, String[] strArr, String str) {
        return (DirtyCount) SafeDBUtil.safeQuery(context, uri, strArr, str, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<DirtyCount>() {
            public DirtyCount handle(Cursor cursor) {
                DirtyCount dirtyCount = new DirtyCount();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int i = cursor.getInt(0);
                        int i2 = cursor.getInt(1);
                        int i3 = cursor.getInt(2);
                        if (i2 == 1) {
                            if (i3 == 1) {
                                dirtyCount.setOversizedImageCount(i);
                            } else {
                                dirtyCount.setSyncableImageCount(i);
                            }
                        } else if (i3 == 1) {
                            dirtyCount.setOversizedVideoCount(i);
                        } else if (i3 == 0) {
                            dirtyCount.setSyncableVideoCount(i);
                        }
                    }
                }
                return dirtyCount;
            }
        });
    }

    private static long[] querySize(Context context, Uri uri, String str) {
        return (long[]) SafeDBUtil.safeQuery(context, uri, new String[]{"sum(size)", "serverType"}, str, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<long[]>() {
            public long[] handle(Cursor cursor) {
                long[] jArr = {0, 0};
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        long j = cursor.getLong(0);
                        if (cursor.getInt(1) == 1) {
                            jArr[0] = j;
                        } else {
                            jArr[1] = j;
                        }
                    }
                }
                return jArr;
            }
        });
    }

    private static int[] querySyncedCount(Context context, Uri uri, String str) {
        return (int[]) SafeDBUtil.safeQuery(context, uri, new String[]{"count(*)", "serverType"}, str, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<int[]>() {
            public int[] handle(Cursor cursor) {
                int[] iArr = {0, 0};
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int i = cursor.getInt(0);
                        if (cursor.getInt(1) == 1) {
                            iArr[0] = i;
                        } else {
                            iArr[1] = i;
                        }
                    }
                }
                return iArr;
            }
        });
    }
}
