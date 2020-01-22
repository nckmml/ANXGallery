package com.miui.gallery.people;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.miui.gallery.util.uil.CloudImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class PeopleDisplayHelper {
    private static DisplayImageOptions.Builder sPeopleDisplayOptionsBuilder;

    public static void bindImage(ImageView imageView, String str, Uri uri, DisplayImageOptions displayImageOptions, RectF rectF, DownloadType downloadType) {
        BindImageHelper.bindImage(str, uri, NetworkUtils.isActiveNetworkMetered() ? DownloadType.MICRO : DownloadType.THUMBNAIL, imageView, displayImageOptions, Config.ThumbConfig.get().sMicroTargetSize, rectF, true, true);
        if (DownloadType.MICRO == downloadType && !TextUtils.isEmpty(str) && !NetworkUtils.isActiveNetworkMetered()) {
            CloudImageLoader.getInstance().displayImage(uri, DownloadType.THUMBNAIL, imageView, displayImageOptions, Config.ThumbConfig.get().sMicroTargetSize, rectF, true, false);
        }
    }

    public static DisplayImageOptions.Builder getDefaultPeopleDisplayOptionsBuilder() {
        if (sPeopleDisplayOptionsBuilder == null) {
            sPeopleDisplayOptionsBuilder = new DisplayImageOptions.Builder().cacheThumbnail(true).loadFromMicroCache(true).considerExifParams(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading((int) R.drawable.default_face_cover).showImageOnFail(R.drawable.default_face_cover).showImageForEmptyUri(R.drawable.default_face_cover).displayer(new PeopleItemBitmapDisplayer(true)).usingRegionDecoderFace(true);
        }
        return sPeopleDisplayOptionsBuilder;
    }
}
