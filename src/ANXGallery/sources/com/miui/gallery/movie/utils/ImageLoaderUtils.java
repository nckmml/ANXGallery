package com.miui.gallery.movie.utils;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageLoaderUtils {
    public static DisplayImageOptions.Builder getNormalDisplayImageOptionsBuilder() {
        return new DisplayImageOptions.Builder().considerExifParams(true).resetViewBeforeLoading(false).imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory(false).cacheOnDisk(true).cacheBigPhoto(false).bitmapConfig(Bitmap.Config.RGB_565);
    }
}
