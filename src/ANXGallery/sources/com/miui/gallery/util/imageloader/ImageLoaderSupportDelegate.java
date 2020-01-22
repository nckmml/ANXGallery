package com.miui.gallery.util.imageloader;

import android.net.Uri;
import android.widget.ImageView;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.imageloader.imageloadiotion.AlbumImageLoadOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ImageLoaderSupportDelegate implements IImageLoader {

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final ImageLoaderSupportDelegate INSTANCE = new ImageLoaderSupportDelegate();
    }

    private ImageLoaderSupportDelegate() {
    }

    public static ImageLoaderSupportDelegate getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void bindImage(ImageView imageView, String str, Uri uri, DisplayImageOptions displayImageOptions) {
        BindImageHelper.bindImage(str, uri, DownloadType.MICRO, imageView, displayImageOptions, AlbumImageLoadOptions.getInstance().getMicroTargetSize());
    }
}
