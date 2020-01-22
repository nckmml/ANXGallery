package com.miui.gallery.util.imageloader;

import android.net.Uri;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public interface IImageLoader {
    void bindImage(ImageView imageView, String str, Uri uri, DisplayImageOptions displayImageOptions);
}
