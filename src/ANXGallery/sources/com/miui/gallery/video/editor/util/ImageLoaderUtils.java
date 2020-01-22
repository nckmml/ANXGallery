package com.miui.gallery.video.editor.util;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageLoaderUtils {
    public static DisplayImageOptions mVideoEditorDisplayImageOptions = new DisplayImageOptions.Builder().considerExifParams(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory(true).cacheOnDisk(true).cacheBigPhoto(false).bitmapConfig(Bitmap.Config.ARGB_8888).build();
}
