package com.miui.gallery.ui.photodetail;

import com.miui.gallery.util.BaseFileMimeUtil;

public class PhotoDetailUtils {
    public static boolean isMimeTypeSupportEditDateTime(String str) {
        return BaseFileMimeUtil.isGifFromMimeType(str) || BaseFileMimeUtil.isJpegImageFromMimeType(str) || BaseFileMimeUtil.isPngImageFromMimeType(str);
    }
}
