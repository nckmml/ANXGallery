package com.miui.gallery.movie.xmsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.miui.gallery.movie.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.xmstreaming.XmsMultiThumbnailSequenceView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class XmsMultiThumbnailSequenceViewImpl extends XmsMultiThumbnailSequenceView {
    private DisplayImageOptions mDisplayOptions = new DisplayImageOptions.Builder().considerExifParams(true).resetViewBeforeLoading(false).imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    private ImageAware mImageAware = new NonViewAware(this.mImageSize, ViewScaleType.CROP);
    private ImageSize mImageSize = new ImageSize((int) getResources().getDimension(R.dimen.photo_movie_edit_edit_item_width), (int) getResources().getDimension(R.dimen.photo_movie_edit_edit_item_width));

    public XmsMultiThumbnailSequenceViewImpl(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void nativeCancelIconTask(long j) {
    }

    /* access modifiers changed from: protected */
    public long nativeGetIcon(final String str, long j) {
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(str), this.mImageAware, this.mDisplayOptions, (ImageLoadingListener) new ImageLoadingListener() {
            public void onLoadingCancelled(String str, View view) {
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                Log.d("XmsMultiThumbnailSequenceViewImpl", "onLoadingComplete" + str);
                XmsMultiThumbnailSequenceViewImpl.this.notifyIconArrived();
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
            }

            public void onLoadingStarted(String str, View view) {
            }
        });
        return (long) str.hashCode();
    }

    /* access modifiers changed from: protected */
    public Bitmap nativeGetIconFromCache(String str, long j) {
        Bitmap loadFromMemoryCache = ImageLoader.getInstance().loadFromMemoryCache(ImageDownloader.Scheme.FILE.wrap(str), this.mImageSize);
        Log.d("XmsMultiThumbnailSequenceViewImpl", "nativeGetIconFromCache %s :%b", str, Boolean.valueOf(loadFromMemoryCache == null));
        return loadFromMemoryCache;
    }
}
