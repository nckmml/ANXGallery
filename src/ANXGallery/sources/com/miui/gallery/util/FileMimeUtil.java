package com.miui.gallery.util;

import android.text.TextUtils;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class FileMimeUtil extends BaseFileMimeUtil {
    private static HashMap<String, String> sSupportUploadMimeTypeMap;

    public static String getSupportUploadMimeType(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "*/*";
        }
        String mimeTypeForFile = MediaFile.getMimeTypeForFile(str);
        if (TextUtils.isEmpty(mimeTypeForFile)) {
            return "*/*";
        }
        if ((!isImageFromMimeType(mimeTypeForFile) && !isVideoFromMimeType(mimeTypeForFile)) || (lastIndexOf = str.lastIndexOf(46)) < 0) {
            return "*/*";
        }
        String supportedMimeTypeByExt = getSupportedMimeTypeByExt(str.substring(lastIndexOf + 1).toUpperCase(Locale.ROOT));
        if (supportedMimeTypeByExt != null) {
            return supportedMimeTypeByExt;
        }
        if (!isVideoFromMimeType(mimeTypeForFile)) {
            return "*/*";
        }
        try {
            supportedMimeTypeByExt = rawGetMimeType(str, VIDEO_MIMES);
        } catch (IOException e) {
            Log.w("BaseFileMimeUtil", (Throwable) e);
        }
        return supportedMimeTypeByExt != null ? supportedMimeTypeByExt : "*/*";
    }

    private static String getSupportedMimeTypeByExt(String str) {
        if (sSupportUploadMimeTypeMap == null) {
            sSupportUploadMimeTypeMap = CloudControlStrategyHelper.getUploadSupportedFileTypes();
        }
        HashMap<String, String> hashMap = sSupportUploadMimeTypeMap;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(str);
    }
}
