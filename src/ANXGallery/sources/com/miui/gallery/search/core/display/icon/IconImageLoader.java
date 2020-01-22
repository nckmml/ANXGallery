package com.miui.gallery.search.core.display.icon;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.widget.ImageView;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.provider.cache.SearchIconItem;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.search.core.Consumer;
import com.miui.gallery.search.core.context.SearchContext;
import com.miui.gallery.search.core.display.icon.IconLoaderTask;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.face.FaceRegionRectF;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class IconImageLoader {
    private static final UriMatcher sURIMatcher = new UriMatcher(-1);
    private Cache<String, SearchIconItem> mMemoryCache;

    private static class IconLoaderHolder implements Consumer<IconLoaderTask.IconLoaderResult> {
        private DisplayImageOptions mDisplayImageOptions;
        private ImageSize mImageSize;
        private DownloadType mImageType;
        private IconLoaderTask.IconLoaderResult mLastResult;
        private IconLoaderTask.IconLoaderResult mResult;
        private IconLoaderTask mTask;
        private Uri mUri;
        private WeakReference<ImageView> mViewRef;

        public static IconLoaderHolder getImageHolder(ImageView imageView) {
            Object tag = imageView.getTag(R.id.tag_icon_loader_holder);
            if (tag == null) {
                tag = new IconLoaderHolder();
                imageView.setTag(R.id.tag_icon_loader_holder, tag);
            }
            return (IconLoaderHolder) tag;
        }

        public boolean consume(IconLoaderTask.IconLoaderResult iconLoaderResult) {
            this.mLastResult = this.mResult;
            if (iconLoaderResult == null) {
                this.mResult = null;
                this.mTask = null;
            } else if (iconLoaderResult.isValid() && iconLoaderResult.iconUri.equals(this.mUri)) {
                SearchLog.d("IconImageLoader", "Load task finished for uri %s, result is valid %s", this.mUri, Boolean.valueOf(iconLoaderResult.isValid()));
                this.mResult = iconLoaderResult;
            }
            if (!Objects.equals(this.mLastResult, this.mResult)) {
                displayResult();
            }
            if (this.mTask == null || iconLoaderResult == null || !iconLoaderResult.isFromUnreliableCache()) {
                this.mTask = null;
                return true;
            }
            this.mTask = new IconLoaderTask(GalleryApp.sGetAndroidContext(), this.mUri, this, ThreadManager.getMainHandler(), false, true, true);
            IconImageLoader.submitTask(this.mTask);
            return true;
        }

        public void displayResult() {
            if (getImageView() != null) {
                IconLoaderTask.IconLoaderResult iconLoaderResult = this.mResult;
                if (iconLoaderResult != null) {
                    BindImageHelper.bindImage(iconLoaderResult.localFilePath, this.mResult.downloadUri, this.mImageType, getImageView(), this.mDisplayImageOptions, this.mImageSize, this.mResult.facePositionRect);
                } else {
                    ImageLoader.getInstance().displayImage((String) null, new ImageViewAware(getImageView()), this.mDisplayImageOptions, this.mImageSize, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
                }
            }
        }

        public ImageView getImageView() {
            WeakReference<ImageView> weakReference = this.mViewRef;
            if (weakReference != null) {
                return (ImageView) weakReference.get();
            }
            return null;
        }

        public String getKey() {
            return IconImageLoader.generateKey(this.mUri, getImageView());
        }

        public IconLoaderTask getTask() {
            return this.mTask;
        }

        public boolean hasRunningTask() {
            IconLoaderTask iconLoaderTask = this.mTask;
            return iconLoaderTask != null && !iconLoaderTask.isCancelled();
        }

        public void set(ImageView imageView, Uri uri, DownloadType downloadType, DisplayImageOptions displayImageOptions, ImageSize imageSize) {
            this.mViewRef = new WeakReference<>(imageView);
            this.mUri = uri;
            this.mDisplayImageOptions = displayImageOptions;
            this.mImageSize = imageSize;
            this.mImageType = downloadType;
            this.mResult = null;
        }

        public void setTask(IconLoaderTask iconLoaderTask) {
            this.mTask = iconLoaderTask;
        }
    }

    private static class SingletonHolder {
        static final IconImageLoader INSTANCE = new IconImageLoader();
    }

    static {
        sURIMatcher.addURI("web", (String) null, 1);
    }

    private IconImageLoader() {
        this.mMemoryCache = CacheBuilder.newBuilder().maximumSize(100).recordStats().build();
    }

    private void cancelHolderTask(IconLoaderHolder iconLoaderHolder) {
        IconLoaderTask task = iconLoaderHolder.getTask();
        if (task != null) {
            SearchLog.d("IconImageLoader", "Cancel holder task %s", iconLoaderHolder.getKey());
            if (task.isUseDiskCache()) {
                SearchContext.getInstance().getIconLoaderCacheExecutor().cancel(task);
            } else {
                SearchContext.getInstance().getIconLoaderExecutor().cancel(task);
            }
            task.setCancelled();
            iconLoaderHolder.setTask((IconLoaderTask) null);
        }
    }

    /* access modifiers changed from: private */
    public static String generateKey(Uri uri, ImageView imageView) {
        return (uri == null || imageView == null) ? "" : uri.buildUpon().appendQueryParameter("imageView", String.valueOf(imageView.hashCode())).build().toString();
    }

    public static IconImageLoader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055  */
    private void internalDisplayImage(Context context, Uri uri, DownloadType downloadType, ImageView imageView, DisplayImageOptions displayImageOptions, ImageSize imageSize, boolean z) {
        boolean z2;
        ImageView imageView2 = imageView;
        DisplayImageOptions displayImageOptions2 = displayImageOptions;
        boolean z3 = false;
        if (uri != null) {
            if ("image".equals(uri.getScheme())) {
                z2 = true;
            } else if (ImageDownloader.Scheme.ofUri(uri.toString()) != ImageDownloader.Scheme.UNKNOWN) {
                z2 = false;
                z3 = true;
            }
            if (displayImageOptions2 != null && displayImageOptions.shouldShowImageOnLoading()) {
                imageView.setImageDrawable(displayImageOptions2.getImageOnLoading(imageView.getResources()));
            }
            if (!z3) {
                ImageLoader.getInstance().displayImage(uri.toString(), new ImageViewAware(imageView), displayImageOptions, imageSize, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
                return;
            } else if (!z2) {
                ImageLoader.getInstance().displayImage((String) null, new ImageViewAware(imageView), displayImageOptions, imageSize, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
                return;
            } else if (sURIMatcher.match(uri) == 1) {
                ImageLoader.getInstance().displayImage(uri.getQueryParameter("url"), imageView, displayImageOptions2);
                return;
            } else {
                IconLoaderHolder imageHolder = IconLoaderHolder.getImageHolder(imageView);
                if (!generateKey(uri, imageView).equals(imageHolder.getKey()) || !imageHolder.hasRunningTask()) {
                    cancelHolderTask(imageHolder);
                } else {
                    imageHolder.displayResult();
                }
                imageHolder.set(imageView, uri, downloadType, displayImageOptions, imageSize);
                SearchIconItem ifPresent = this.mMemoryCache.getIfPresent(uri.toString());
                if (ifPresent != null) {
                    Uri uri2 = null;
                    FaceRegionRectF faceRegionRectF = (ifPresent.decodeRegionW == null || ifPresent.decodeRegionH == null || ifPresent.decodeRegionX == null || ifPresent.decodeRegionY == null) ? null : new FaceRegionRectF(ifPresent.decodeRegionX.floatValue(), ifPresent.decodeRegionY.floatValue(), ifPresent.decodeRegionX.floatValue() + ifPresent.decodeRegionW.floatValue(), ifPresent.decodeRegionY.floatValue() + ifPresent.decodeRegionH.floatValue(), ifPresent.decodeRegionOrientation);
                    Uri parse = ifPresent.iconUri != null ? Uri.parse(ifPresent.iconUri) : null;
                    String str = ifPresent.filePath;
                    if (ifPresent.downloadUri != null) {
                        uri2 = Uri.parse(ifPresent.downloadUri);
                    }
                    imageHolder.consume(new IconLoaderTask.IconLoaderResult(parse, str, uri2, faceRegionRectF, false));
                    return;
                }
                IconLoaderTask iconLoaderTask = new IconLoaderTask(context, uri, imageHolder, ThreadManager.getMainHandler(), z, z, false);
                imageHolder.setTask(iconLoaderTask);
                submitTask(iconLoaderTask);
                return;
            }
        }
        z2 = false;
        imageView.setImageDrawable(displayImageOptions2.getImageOnLoading(imageView.getResources()));
        if (!z3) {
        }
    }

    /* access modifiers changed from: private */
    public static void submitTask(IconLoaderTask iconLoaderTask) {
        if (iconLoaderTask.isUseDiskCache()) {
            SearchContext.getInstance().getIconLoaderCacheExecutor().submit(iconLoaderTask);
        } else {
            SearchContext.getInstance().getIconLoaderExecutor().submit(iconLoaderTask);
        }
    }

    public void displayImage(Context context, Uri uri, DownloadType downloadType, ImageView imageView, DisplayImageOptions displayImageOptions, ImageSize imageSize) {
        internalDisplayImage(context, uri, downloadType, imageView, displayImageOptions, imageSize, false);
    }

    public void displayImageEager(Context context, Uri uri, DownloadType downloadType, ImageView imageView, DisplayImageOptions displayImageOptions, ImageSize imageSize) {
        internalDisplayImage(context, uri, downloadType, imageView, displayImageOptions, imageSize, true);
    }

    public Cache<String, SearchIconItem> getMemoryCache() {
        return this.mMemoryCache;
    }
}
