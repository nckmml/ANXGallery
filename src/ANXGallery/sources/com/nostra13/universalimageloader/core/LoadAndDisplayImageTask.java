package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.miui.gallery.imageloader.GalleryImageLoaderCache;
import com.miui.gallery.imageloader.ImageLoaderSamplingStatHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import miui.graphics.BitmapFactory;

final class LoadAndDisplayImageTask implements IoUtils.CopyListener, Runnable {
    /* access modifiers changed from: private */
    public final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    /* access modifiers changed from: private */
    public final BitmapDisplayer displayer;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final boolean syncLoading;
    private final ImageSize targetSize;
    final String uri;

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo2, Handler handler2) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo2;
        this.handler = handler2;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.uri = imageLoadingInfo2.uri;
        this.memoryCacheKey = imageLoadingInfo2.memoryCacheKey;
        this.imageAware = imageLoadingInfo2.imageAware;
        this.targetSize = imageLoadingInfo2.targetSize;
        this.options = imageLoadingInfo2.options;
        this.listener = imageLoadingInfo2.listener;
        this.progressListener = imageLoadingInfo2.progressListener;
        this.syncLoading = this.options.isSyncLoading();
        this.displayer = imageLoadingInfo2.options.getDisplayer();
    }

    private void cacheImageThumbnail(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            L.d("Cache image's thumbnail [%s]", this.memoryCacheKey);
            try {
                this.configuration.thumbnailCache.save(this.memoryCacheKey, bitmap);
            } catch (Exception e) {
                L.e(e);
            }
        }
    }

    private void checkTaskInterrupted() throws TaskCancelledException {
        if (isTaskInterrupted()) {
            throw new TaskCancelledException();
        }
    }

    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {
            throw new TaskCancelledException();
        }
    }

    private Bitmap decodeImage(String str) throws IOException {
        String str2 = str;
        Bitmap decode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str2, this.uri, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options, this.imageLoadingInfo.regionDecodeRect));
        if (decode == null || this.options.getBlurRadius() <= 0) {
            return decode;
        }
        Bitmap fastBlur = BitmapFactory.fastBlur(decode, this.options.getBlurRadius());
        if (decode != fastBlur) {
            if (this.options.getReusedBitmapCache() != null) {
                this.options.getReusedBitmapCache().put(decode);
            } else {
                decode.recycle();
            }
        }
        return fastBlur;
    }

    private boolean delayIfNeed() {
        if (!this.options.shouldDelayBeforeLoading()) {
            return false;
        }
        L.d("Delay %d ms before loading...  [%s]", Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
        try {
            Thread.sleep((long) this.options.getDelayBeforeLoading());
            return isTaskNotActual();
        } catch (InterruptedException unused) {
            L.e("Task was interrupted [%s]", this.memoryCacheKey);
            return true;
        }
    }

    private boolean downloadImage() throws IOException {
        InputStream stream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
        if (stream == null) {
            L.e("No stream for image [%s]", this.memoryCacheKey);
            return false;
        }
        try {
            return this.configuration.diskCache.save(this.uri, stream, this);
        } finally {
            IoUtils.closeSilently(stream);
        }
    }

    private void fireCancelEvent() {
        L.d("Cancel loading image %s", this.uri);
        if (!this.syncLoading && !isTaskInterrupted()) {
            runTask(new Runnable() {
                public void run() {
                    LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
                }
            }, false, this.handler, this.engine);
        }
    }

    private void fireFailEvent(FailReason.FailType failType, Throwable th) {
        L.e(th, "Loading image failed, fail Type %s, uri %s", failType, this.uri);
        fireFailEventNoLog(failType, th);
    }

    private void fireFailEventNoLog(final FailReason.FailType failType, final Throwable th) {
        if (!this.syncLoading && !isTaskInterrupted() && !isTaskNotActual()) {
            runTask(new Runnable() {
                public void run() {
                    if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                        Drawable imageOnFail = LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources);
                        if (LoadAndDisplayImageTask.this.displayer == null || !(imageOnFail instanceof BitmapDrawable)) {
                            LoadAndDisplayImageTask.this.imageAware.setImageDrawable(imageOnFail);
                        } else {
                            LoadAndDisplayImageTask.this.displayer.display(((BitmapDrawable) imageOnFail).getBitmap(), LoadAndDisplayImageTask.this.imageAware, LoadedFrom.MEMORY_CACHE);
                        }
                    }
                    LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(failType, th));
                }
            }, false, this.handler, this.engine);
        }
    }

    private boolean fireProgressEvent(final int i, final int i2) {
        if (isTaskInterrupted() || isTaskNotActual()) {
            return false;
        }
        if (this.progressListener == null) {
            return true;
        }
        runTask(new Runnable() {
            public void run() {
                LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
            }
        }, false, this.handler, this.engine);
        return true;
    }

    private ImageDownloader getDownloader() {
        return this.engine.isNetworkDenied() ? this.networkDeniedDownloader : this.engine.isSlowNetwork() ? this.slowNetworkDownloader : this.downloader;
    }

    private boolean isTaskInterrupted() {
        if (!Thread.interrupted()) {
            return false;
        }
        L.d("Task was interrupted [%s]", this.memoryCacheKey);
        return true;
    }

    private boolean isTaskNotActual() {
        return isViewCollected() || isViewReused();
    }

    private boolean isViewCollected() {
        if (!this.imageAware.isCollected()) {
            return false;
        }
        L.d("ImageAware was collected by GC. Task is cancelled. [%s]", this.memoryCacheKey);
        return true;
    }

    private boolean isViewReused() {
        if (!(!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware)))) {
            return false;
        }
        L.d("ImageAware is reused for another image. Task is cancelled. [%s]", this.memoryCacheKey);
        return true;
    }

    private void recordDecodeError(Throwable th) {
        if (!this.options.isCacheBigPhoto()) {
            Map<String, String> generatorCommonParams = ImageLoaderSamplingStatHelper.generatorCommonParams();
            generatorCommonParams.put("uri", this.uri);
            generatorCommonParams.put("exception", th != null ? th.toString() : "unknow");
            ImageLoaderSamplingStatHelper.recordErrorEvent("pager_decode_bitmap", "pager_photo_decode_error", generatorCommonParams);
        }
    }

    private boolean resizeAndSaveImage(int i, int i2) throws IOException {
        File file = this.configuration.diskCache.get(this.uri);
        if (file == null || !file.exists()) {
            return false;
        }
        Bitmap decode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath()), this.uri, new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new DisplayImageOptions.Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build()));
        if (!(decode == null || this.configuration.processorForDiskCache == null)) {
            L.d("Process image before cache on disk [%s]", this.memoryCacheKey);
            decode = this.configuration.processorForDiskCache.process(decode);
            if (decode == null) {
                L.e("Bitmap processor for disk cache returned null [%s]", this.memoryCacheKey);
            }
        }
        if (decode == null) {
            return false;
        }
        boolean save = this.configuration.diskCache.save(this.uri, decode);
        decode.recycle();
        return save;
    }

    static void runTask(Runnable runnable, boolean z, Handler handler2, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler2 == null) {
            imageLoaderEngine.fireCallback(runnable);
        } else {
            handler2.post(runnable);
        }
    }

    private boolean tryCacheImageOnDisk() throws TaskCancelledException {
        L.d("Cache image on disk [%s]", this.memoryCacheKey);
        try {
            boolean downloadImage = downloadImage();
            if (!downloadImage) {
                return downloadImage;
            }
            int i = this.configuration.maxImageWidthForDiskCache;
            int i2 = this.configuration.maxImageHeightForDiskCache;
            if (i <= 0 && i2 <= 0) {
                return downloadImage;
            }
            L.d("Resize image in disk cache [%s]", this.memoryCacheKey);
            resizeAndSaveImage(i, i2);
            return downloadImage;
        } catch (IOException e) {
            L.e(e);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        if (r1.getHeight() > 0) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a8, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e6, code lost:
        recordDecodeError(r0);
        com.nostra13.universalimageloader.utils.L.e(r0);
        fireFailEvent(com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f2, code lost:
        fireFailEventNoLog(com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f9, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8 A[ExcHandler: TaskCancelledException (r0v1 'e' com.nostra13.universalimageloader.core.LoadAndDisplayImageTask$TaskCancelledException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    private Bitmap tryLoadBitmap() throws TaskCancelledException {
        Bitmap bitmap;
        File file;
        try {
            File file2 = this.configuration.diskCache.get(this.uri);
            if (file2 == null || !file2.exists() || file2.length() <= 0) {
                bitmap = null;
            } else {
                L.d("Load image from disk cache [%s]", this.memoryCacheKey);
                this.loadedFrom = LoadedFrom.DISC_CACHE;
                checkTaskNotActual();
                bitmap = decodeImage(ImageDownloader.Scheme.FILE.wrap(file2.getAbsolutePath()));
            }
            if (bitmap != null) {
                if (bitmap.getWidth() > 0) {
                }
            }
            L.d("Load image from network [%s]", this.memoryCacheKey);
            this.loadedFrom = LoadedFrom.NETWORK;
            String str = this.uri;
            if (this.options.isCacheOnDisk() && tryCacheImageOnDisk() && (file = this.configuration.diskCache.get(this.uri)) != null) {
                str = ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath());
            }
            checkTaskNotActual();
            bitmap = decodeImage(str);
            if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                fireFailEvent(FailReason.FailType.DECODING_ERROR, (Throwable) null);
            }
        } catch (IllegalStateException e) {
            IllegalStateException e2 = e;
            bitmap = null;
            recordDecodeError(e2);
            fireFailEvent(FailReason.FailType.NETWORK_DENIED, (Throwable) null);
            return bitmap;
        } catch (TaskCancelledException e3) {
        } catch (FileNotFoundException e4) {
            FileNotFoundException fileNotFoundException = e4;
            bitmap = null;
            FileNotFoundException e5 = fileNotFoundException;
            if (!this.options.isCacheBigPhoto()) {
            }
            return bitmap;
        } catch (IOException e6) {
            IOException iOException = e6;
            bitmap = null;
            IOException e7 = iOException;
            recordDecodeError(e7);
            L.e(e7);
            fireFailEvent(FailReason.FailType.IO_ERROR, e7);
            return bitmap;
        } catch (OutOfMemoryError e8) {
            OutOfMemoryError outOfMemoryError = e8;
            bitmap = null;
            OutOfMemoryError e9 = outOfMemoryError;
            recordDecodeError(e9);
            L.e(e9);
            fireFailEvent(FailReason.FailType.OUT_OF_MEMORY, e9);
            return bitmap;
        } catch (Exception e10) {
            Exception exc = e10;
            bitmap = null;
            Exception e11 = exc;
            recordDecodeError(e11);
            L.e(e11);
            fireFailEvent(FailReason.FailType.UNKNOWN, e11);
            return bitmap;
        }
        return bitmap;
    }

    private Bitmap tryLoadThumbnail() throws TaskCancelledException {
        checkTaskNotActual();
        return this.configuration.thumbnailCache.getBitmap(this.memoryCacheKey);
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    L.d("ImageLoader is paused. Waiting...  [%s]", this.memoryCacheKey);
                    try {
                        this.engine.getPauseLock().wait();
                        L.d(".. Resume loading [%s]", this.memoryCacheKey);
                    } catch (InterruptedException unused) {
                        L.e("Task was interrupted [%s]", this.memoryCacheKey);
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    /* access modifiers changed from: package-private */
    public String getLoadingUri() {
        return this.uri;
    }

    public boolean onBytesCopied(int i, int i2) {
        return this.syncLoading || fireProgressEvent(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0245, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        fireCancelEvent();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0250, code lost:
        if (r0 != false) goto L_0x0252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0252, code lost:
        r1.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0255, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009d, code lost:
        if (r4.isRecycled() != false) goto L_0x009f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:115:0x0247 */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01e4 A[Catch:{ TaskCancelledException -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0215 A[Catch:{ TaskCancelledException -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071 A[Catch:{ TaskCancelledException -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099 A[SYNTHETIC, Splitter:B:36:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a7 A[Catch:{ TaskCancelledException -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x012c A[Catch:{ TaskCancelledException -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015f A[Catch:{ TaskCancelledException -> 0x0247 }] */
    public void run() {
        boolean z;
        Bitmap bitmap;
        boolean z2;
        if (this.options.isHighPriority()) {
            Thread.currentThread().setPriority(5);
        } else {
            Thread.currentThread().setPriority(this.configuration.threadPriority);
        }
        if (!waitIfPaused() && !delayIfNeed()) {
            boolean isSerialByUri = this.options.isSerialByUri();
            ReentrantLock reentrantLock = this.imageLoadingInfo.loadFromUriLock;
            if (isSerialByUri) {
                L.d("Start display image task [%s]", this.memoryCacheKey);
                if (reentrantLock.isLocked()) {
                    L.d("Image already is loading. Waiting... [%s]", this.memoryCacheKey);
                }
                reentrantLock.lock();
            }
            checkTaskNotActual();
            if (!this.options.isCacheInMemory()) {
                if (!this.options.isCacheInSubMemory()) {
                    z = false;
                    Bitmap bitmap2 = null;
                    if (!z) {
                        bitmap = this.configuration.memoryCache.get(this.memoryCacheKey);
                        if ((bitmap == null || bitmap.isRecycled()) && this.configuration.subMemoryCache != null) {
                            bitmap = this.configuration.subMemoryCache.get(this.memoryCacheKey);
                        }
                    } else {
                        bitmap = null;
                    }
                    if (bitmap != null) {
                    }
                    if (this.options.isLoadFromMicroCache()) {
                        GalleryImageLoaderCache.IMicroThumbCache microThumbCache = GalleryImageLoaderCache.getInstance().getMicroThumbCache();
                        if (microThumbCache != null) {
                            if (this.options.isBitmapReused() && !ImageLoader.getInstance().isOnImageLoadingBitmap(this.options, this.imageAware.getBitmap())) {
                                bitmap2 = this.imageAware.getBitmap();
                            }
                            bitmap = microThumbCache.getCenterCropBitmap(this.memoryCacheKey, bitmap2);
                        }
                        checkTaskNotActual();
                        checkTaskInterrupted();
                        if (bitmap != null) {
                            if (this.options.isCacheInMemory()) {
                                L.d("Cache image in memory [%s]", this.memoryCacheKey);
                                this.configuration.memoryCache.put(this.memoryCacheKey, bitmap);
                            }
                            if (this.options.isCacheInSubMemory() && this.configuration.subMemoryCache != null) {
                                this.configuration.subMemoryCache.put(this.memoryCacheKey, bitmap);
                            }
                        }
                    }
                    if ((bitmap == null || bitmap.isRecycled()) && this.options.isLoadFromThumbnailCache()) {
                        bitmap = tryLoadThumbnail();
                        checkTaskNotActual();
                        checkTaskInterrupted();
                        if (bitmap != null) {
                            if (this.options.isCacheInMemory()) {
                                L.d("Cache image in memory [%s]", this.memoryCacheKey);
                                this.configuration.memoryCache.put(this.memoryCacheKey, bitmap);
                            }
                            if (this.options.isCacheInSubMemory() && this.configuration.subMemoryCache != null) {
                                this.configuration.subMemoryCache.put(this.memoryCacheKey, bitmap);
                            }
                        }
                    }
                    if (bitmap != null) {
                        Bitmap tryLoadBitmap = tryLoadBitmap();
                        if (tryLoadBitmap != null) {
                            checkTaskNotActual();
                            checkTaskInterrupted();
                            if (this.options.shouldPreProcess()) {
                                L.d("PreProcess image before caching in memory [%s]", this.memoryCacheKey);
                                tryLoadBitmap = this.options.getPreProcessor().process(tryLoadBitmap);
                                if (tryLoadBitmap == null) {
                                    L.e("Pre-processor returned null [%s]", this.memoryCacheKey);
                                }
                            }
                            if (bitmap != null) {
                                if (this.options.isCacheInMemory()) {
                                    L.d("Cache image in memory [%s]", this.memoryCacheKey);
                                    this.configuration.memoryCache.put(this.memoryCacheKey, bitmap);
                                }
                                if (this.options.isCacheInSubMemory() && this.configuration.subMemoryCache != null) {
                                    this.configuration.subMemoryCache.put(this.memoryCacheKey, bitmap);
                                }
                            }
                            if (this.options.isCacheThumbnail()) {
                                if (this.options.isDelayCacheThumbnail()) {
                                    z2 = true;
                                    if (bitmap != null && this.options.shouldPostProcess()) {
                                        L.d("PostProcess image before displaying [%s]", this.memoryCacheKey);
                                        bitmap = this.options.getPostProcessor().process(bitmap);
                                        if (bitmap == null) {
                                            L.e("Post-processor returned null [%s]", this.memoryCacheKey);
                                        }
                                    }
                                    checkTaskNotActual();
                                    checkTaskInterrupted();
                                    if (isSerialByUri) {
                                        reentrantLock.unlock();
                                    }
                                    runTask(new DisplayBitmapTask(bitmap, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
                                    if (z2) {
                                        cacheImageThumbnail(bitmap);
                                        return;
                                    }
                                    return;
                                }
                                cacheImageThumbnail(bitmap);
                            }
                        } else if (isSerialByUri) {
                            reentrantLock.unlock();
                            return;
                        } else {
                            return;
                        }
                    } else {
                        this.loadedFrom = LoadedFrom.MEMORY_CACHE;
                        L.d("...Get cached bitmap from memory after waiting. [%s]", this.memoryCacheKey);
                    }
                    z2 = false;
                    L.d("PostProcess image before displaying [%s]", this.memoryCacheKey);
                    bitmap = this.options.getPostProcessor().process(bitmap);
                    if (bitmap == null) {
                    }
                    checkTaskNotActual();
                    checkTaskInterrupted();
                    if (isSerialByUri) {
                    }
                    runTask(new DisplayBitmapTask(bitmap, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
                    if (z2) {
                    }
                }
            }
            z = true;
            Bitmap bitmap22 = null;
            if (!z) {
            }
            if (bitmap != null) {
            }
            if (this.options.isLoadFromMicroCache()) {
            }
            bitmap = tryLoadThumbnail();
            checkTaskNotActual();
            checkTaskInterrupted();
            if (bitmap != null) {
            }
            if (bitmap != null) {
            }
            z2 = false;
            L.d("PostProcess image before displaying [%s]", this.memoryCacheKey);
            bitmap = this.options.getPostProcessor().process(bitmap);
            if (bitmap == null) {
            }
            checkTaskNotActual();
            checkTaskInterrupted();
            if (isSerialByUri) {
            }
            runTask(new DisplayBitmapTask(bitmap, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
            if (z2) {
            }
        }
    }
}
