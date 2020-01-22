package com.miui.gallery.util;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.Config;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.os.Rom;
import com.nexstreaming.nexeditorsdk.nexEngine;

public class PhotoOperationsUtil {
    private static String TAG = "PhotoOperationsUtil";
    private static Class sMiuiSettingSystem;

    public static int getImageSupportedOperations(String str, String str2, double d, double d2) {
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        int i = WallpaperUtils.supported(sGetAndroidContext) ? 5637164 : 5637132;
        if (!Rom.IS_INTERNATIONAL && Rom.IS_MIUI) {
            i |= 2048;
        }
        if (!TextUtils.isEmpty(str) && StorageUtils.isInExternalStorage(sGetAndroidContext, str)) {
            i |= 1;
            if (!TextUtils.equals(str2, "image/gif") && !DownloadPathHelper.isShareFolderRelativePath(StorageUtils.getRelativePath(sGetAndroidContext, str))) {
                i |= 512;
            }
        }
        if (FileMimeUtil.isGifFromMimeType(str2)) {
            i = i & -33 & -2049;
        }
        if (BitmapUtils.isSupportedByRegionDecoder(str2)) {
            i |= 64;
        }
        if (BitmapUtils.isRotationSupported(str2)) {
            i |= 2;
        }
        if (LocationUtil.isValidateCoordinate(d, d2)) {
            i |= 16;
        }
        return ApplicationHelper.isSecretAlbumFeatureOpen() ? i | nexEngine.ExportHEVCHighTierLevel61 : i;
    }

    private static Class getMiuiSettingSystem() throws ClassNotFoundException {
        Class cls = sMiuiSettingSystem;
        if (cls != null) {
            return cls;
        }
        sMiuiSettingSystem = Class.forName("android.provider.MiuiSettings$Secure");
        return sMiuiSettingSystem;
    }

    public static int getVideoSupportedOperations(String str) {
        int i = VideoWallpaperUtils.isSupported() ? 1053828 : 1049732;
        if (TextUtils.isEmpty(str) || !StorageUtils.isInExternalStorage(GalleryApp.sGetAndroidContext(), str)) {
            return i;
        }
        int i2 = i | 1 | 512;
        return (!ApplicationHelper.isSecretAlbumFeatureOpen() || !Config.SecretAlbumConfig.isVideoSupported()) ? i2 : i2 | nexEngine.ExportHEVCHighTierLevel61;
    }

    public static boolean isScreenProjectOn(ContentResolver contentResolver, boolean z) {
        try {
            Class miuiSettingSystem = getMiuiSettingSystem();
            boolean booleanValue = ((Boolean) miuiSettingSystem.getMethod("getBoolean", new Class[]{ContentResolver.class, String.class, Boolean.TYPE}).invoke(miuiSettingSystem, new Object[]{contentResolver, "screen_project_in_screening", Boolean.valueOf(z)})).booleanValue();
            String str = TAG;
            Log.i(str, "isScreenProjectOn value[screen_project_in_screening] = " + booleanValue);
            return booleanValue;
        } catch (Exception e) {
            String str2 = TAG;
            Log.i(str2, "Error: isScreenProjectOn value[screen_project_in_screening] : " + e);
            e.printStackTrace();
            return z;
        }
    }

    public static boolean isSupportedOptions(int i, int i2) {
        return (i & i2) != 0;
    }
}
