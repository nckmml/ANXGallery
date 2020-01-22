package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.VideoAttrsReader;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import java.io.IOException;
import java.util.HashMap;
import miui.os.ExtraFileUtils;

public class GalleryCloudUtils {
    public static final Uri BASE_URI = Uri.parse("content://com.miui.gallery.cloud.provider");
    public static final Uri CLOUD_CACHE_URI = BASE_URI.buildUpon().appendPath("cloudCache").build();
    public static final Uri CLOUD_SETTING_URI = BASE_URI.buildUpon().appendPath("cloudSetting").build();
    public static final Uri CLOUD_URI = BASE_URI.buildUpon().appendPath("cloud").build();
    public static final Uri CLOUD_USER_URI = BASE_URI.buildUpon().appendPath("cloudUser").build();
    public static final Uri NEW_FLAG_CACHE = BASE_URI.buildUpon().appendPath("newFlagCache").build();
    public static final Uri OWNER_SUB_UBIFOCUS_URI = BASE_URI.buildUpon().appendPath("ownerSubUbifocus").build();
    public static final Uri SHARE_ALBUM_URI = BASE_URI.buildUpon().appendPath("shareAlbum").build();
    public static final Uri SHARE_IMAGE_URI = BASE_URI.buildUpon().appendPath("shareImage").build();
    public static final Uri SHARE_SUB_UBIFOCUS_URI = BASE_URI.buildUpon().appendPath("shareSubUbifocus").build();
    public static final Uri SHARE_USER_URI = BASE_URI.buildUpon().appendPath("shareUser").build();
    public static final Uri USER_INFO_URI = BASE_URI.buildUpon().appendPath("userInfo").build();
    private static Account sAccount;

    public static String convertDoubleToLaLon(double d) {
        int floor = (int) Math.floor(Math.abs(d));
        double d2 = (double) floor;
        double floor2 = Math.floor((Math.abs(d) - d2) * 60.0d);
        double floor3 = Math.floor(((Math.abs(d) - d2) - (floor2 / 60.0d)) * 3600000.0d);
        if (d < 0.0d) {
            return "-" + floor + "/1," + floor2 + "/1," + floor3 + "/1000";
        }
        return floor + "/1," + floor2 + "/1," + floor3 + "/1000";
    }

    private static void ensureDateTakenAndLocation(String str, boolean z, final ContentValues contentValues) {
        GalleryUtils.safeQuery(UriUtil.appendLimit(z ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 1), new String[]{"datetaken", "latitude", "longitude"}, "_data = ? ", new String[]{str}, (String) null, new GalleryUtils.QueryHandler<Void>() {
            public Void handle(Cursor cursor) {
                if (cursor != null && cursor.moveToNext()) {
                    long j = cursor.getLong(0);
                    double d = cursor.getDouble(1);
                    double d2 = cursor.getDouble(2);
                    if (j > 0) {
                        contentValues.put("dateTaken", Long.valueOf(j));
                    }
                    if (d != 0.0d && contentValues.get("exifGPSLatitude") == null) {
                        contentValues.put("exifGPSLatitude", GalleryCloudUtils.convertDoubleToLaLon(d));
                    }
                    if (d2 != 0.0d && contentValues.get("exifGPSLongitude") == null) {
                        contentValues.put("exifGPSLongitude", GalleryCloudUtils.convertDoubleToLaLon(d2));
                    }
                }
                return null;
            }
        });
    }

    private static synchronized Account getAccount() {
        Account account;
        synchronized (GalleryCloudUtils.class) {
            if (sAccount == null) {
                sAccount = AccountHelper.getXiaomiAccount(GalleryApp.sGetAndroidContext());
            }
            account = sAccount;
        }
        return account;
    }

    public static String getAccountName() {
        Account account = getAccount();
        return (account == null || account.name == null) ? "" : account.name;
    }

    public static String getFocusIndexColumnElement(boolean z) {
        return transformToEditedColumnsElement(z ? 53 : 59);
    }

    public static boolean isGalleryCloudSyncable(Context context) {
        Account account = getAccount();
        return account != null && ContentResolver.getMasterSyncAutomatically() && ContentResolver.getSyncAutomatically(account, "com.miui.gallery.cloud.provider");
    }

    public static String mergeEditedColumns(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        for (String str3 : str.split(",+")) {
            if (!TextUtils.isEmpty(str3)) {
                String str4 = "," + str3 + ",";
                str2 = str2.replace(str4, "") + str4;
            }
        }
        return str2;
    }

    private static void putExifIntToContentValues(ExifInterface exifInterface, String str, ContentValues contentValues, String str2) {
        String attribute = exifInterface.getAttribute(str);
        if (attribute != null) {
            try {
                contentValues.put(str2, Integer.valueOf(attribute));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static void putValuesForImage(String str, ContentValues contentValues) throws IOException {
        ExifInterface exifInterface = new ExifInterface(str);
        contentValues.put("title", ExtraFileUtils.getFileTitle(ExtraFileUtils.getFileName(str)));
        int attributeInt = exifInterface.getAttributeInt("ImageWidth", 0);
        int attributeInt2 = exifInterface.getAttributeInt("ImageLength", 0);
        if (attributeInt <= 0 || attributeInt2 <= 0) {
            BitmapFactory.Options bitmapSize = ApplicationHelper.getBitmapProvider().getBitmapSize(str);
            int i = bitmapSize.outWidth;
            attributeInt2 = bitmapSize.outHeight;
            attributeInt = i;
        }
        contentValues.put("exifImageWidth", Integer.valueOf(attributeInt));
        contentValues.put("exifImageLength", Integer.valueOf(attributeInt2));
        contentValues.put("exifOrientation", Integer.valueOf(exifInterface.getAttributeInt("Orientation", 0)));
        contentValues.put("exifGPSLatitude", exifInterface.getAttribute("GPSLatitude"));
        contentValues.put("exifGPSLongitude", exifInterface.getAttribute("GPSLongitude"));
        contentValues.put("exifMake", exifInterface.getAttribute("Make"));
        contentValues.put("exifModel", exifInterface.getAttribute("Model"));
        putExifIntToContentValues(exifInterface, "Flash", contentValues, "exifFlash");
        contentValues.put("exifGPSLatitudeRef", exifInterface.getAttribute("GPSLatitudeRef"));
        contentValues.put("exifGPSLongitudeRef", exifInterface.getAttribute("GPSLongitudeRef"));
        contentValues.put("exifExposureTime", exifInterface.getAttribute("ExposureTime"));
        contentValues.put("exifFNumber", exifInterface.getAttribute("FNumber"));
        contentValues.put("exifISOSpeedRatings", exifInterface.getAttribute("ISOSpeedRatings"));
        contentValues.put("exifGPSAltitude", exifInterface.getAttribute("GPSAltitude"));
        putExifIntToContentValues(exifInterface, "GPSAltitudeRef", contentValues, "exifGPSAltitudeRef");
        contentValues.put("exifGPSTimeStamp", exifInterface.getAttribute("GPSTimeStamp"));
        contentValues.put("exifGPSDateStamp", exifInterface.getAttribute("GPSDateStamp"));
        putExifIntToContentValues(exifInterface, "WhiteBalance", contentValues, "exifWhiteBalance");
        contentValues.put("exifFocalLength", exifInterface.getAttribute("FocalLength"));
        contentValues.put("exifGPSProcessingMethod", exifInterface.getAttribute("GPSProcessingMethod"));
        contentValues.put("exifDateTime", exifInterface.getAttribute("DateTime"));
        ensureDateTakenAndLocation(str, true, contentValues);
    }

    public static boolean putValuesForVideo(String str, ContentValues contentValues) throws IOException {
        try {
            VideoAttrsReader read = VideoAttrsReader.read(str);
            contentValues.put("title", read.getTitle());
            contentValues.put("duration", Long.valueOf(read.getDuration() / 1000));
            contentValues.put("dateTaken", Long.valueOf(read.getDateTaken()));
            contentValues.put("exifImageWidth", Integer.valueOf(read.getVideoWidth()));
            contentValues.put("exifImageLength", Integer.valueOf(read.getVideoHeight()));
            ensureDateTakenAndLocation(str, false, contentValues);
            return true;
        } catch (VideoAttrsReader.VideoAttrsUnretrivableException unused) {
            HashMap hashMap = new HashMap();
            hashMap.put("fileName", FileUtils.getFileName(str));
            GallerySamplingStatHelper.recordErrorEvent("Sync", "read_video_attrs_failed", hashMap);
            return false;
        }
    }

    public static synchronized void resetAccountCache() {
        synchronized (GalleryCloudUtils.class) {
            sAccount = null;
        }
    }

    public static String transformToEditedColumnsElement(int i) {
        return "," + i + ",";
    }
}
