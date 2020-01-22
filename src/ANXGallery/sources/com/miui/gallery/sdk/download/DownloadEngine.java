package com.miui.gallery.sdk.download;

import android.net.Uri;
import com.miui.gallery.sdk.download.DownloadOptions;
import com.miui.gallery.sdk.download.adapter.BaseUriAdapter;
import com.miui.gallery.sdk.download.assist.DownloadItem;
import com.miui.gallery.sdk.download.downloader.IDownloader;
import com.miui.gallery.sdk.download.downloader.MicroBatchDownloader;
import com.miui.gallery.sdk.download.downloader.MicroThumbnailDownloader;
import com.miui.gallery.sdk.download.downloader.OriginDownloader;
import com.miui.gallery.sdk.download.downloader.ThumbnailDownloader;
import com.miui.gallery.sdk.download.executor.AbsDownloadExecutor;
import com.miui.gallery.sdk.download.executor.SingleThreadExecutor;
import com.miui.gallery.sdk.download.executor.ThreadPoolExecutor;
import com.miui.gallery.sdk.download.util.DownloadUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

class DownloadEngine {
    private static final Map<DownloadType, Integer> sDownloaderMap = new HashMap();
    private static final IDownloader[] sDownloaders = new IDownloader[4];
    private static final AbsDownloadExecutor[] sExecutors = new AbsDownloadExecutor[5];
    private static final Map<DownloadType, Integer> sExecutorsMap = new HashMap();
    final DownloadOptions mDefaultDownloadOptions = new DownloadOptions.Builder().setUriAdapter(new BaseUriAdapter()).build();
    private final Map<String, ReentrantLock> mUriLocks = new HashMap();

    static {
        sExecutorsMap.put(DownloadType.MICRO, 0);
        sExecutorsMap.put(DownloadType.THUMBNAIL, 1);
        sExecutorsMap.put(DownloadType.ORIGIN, 2);
        sExecutorsMap.put(DownloadType.ORIGIN_FORCE, 2);
        sExecutorsMap.put(DownloadType.THUMBNAIL_BATCH, 3);
        sExecutorsMap.put(DownloadType.ORIGIN_BATCH, 3);
        sExecutorsMap.put(DownloadType.MICRO_BATCH, 4);
        sDownloaderMap.put(DownloadType.MICRO, 0);
        sDownloaderMap.put(DownloadType.THUMBNAIL, 1);
        sDownloaderMap.put(DownloadType.THUMBNAIL_BATCH, 1);
        sDownloaderMap.put(DownloadType.ORIGIN, 2);
        sDownloaderMap.put(DownloadType.ORIGIN_FORCE, 2);
        sDownloaderMap.put(DownloadType.ORIGIN_BATCH, 2);
        sDownloaderMap.put(DownloadType.MICRO_BATCH, 3);
    }

    DownloadEngine() {
    }

    private static IDownloader createDownloader(int i) {
        if (i == 0) {
            return new MicroThumbnailDownloader();
        }
        if (i == 1) {
            return new ThumbnailDownloader();
        }
        if (i == 2) {
            return new OriginDownloader();
        }
        if (i == 3) {
            return new MicroBatchDownloader();
        }
        throw new UnsupportedOperationException("Unsupported type: " + i);
    }

    private static AbsDownloadExecutor createExecutor(int i) {
        if (i == 0) {
            return new ThreadPoolExecutor(8, 100);
        }
        if (i == 1) {
            return new ThreadPoolExecutor(1, 100);
        }
        if (i == 2) {
            return new SingleThreadExecutor(1, -1);
        }
        if (i == 3) {
            return new ThreadPoolExecutor(10, -1);
        }
        if (i == 4) {
            return new SingleThreadExecutor(25, -1);
        }
        throw new IllegalArgumentException("invalidate executor type " + i);
    }

    private String generateLockKey(Uri uri, DownloadType downloadType) {
        switch (downloadType) {
            case THUMBNAIL:
            case THUMBNAIL_BATCH:
            case ORIGIN:
            case ORIGIN_BATCH:
            case ORIGIN_FORCE:
                return uri.toString();
            case MICRO:
            case MICRO_BATCH:
                return uri.buildUpon().appendQueryParameter(nexExportFormat.TAG_FORMAT_TYPE, "micro").build().toString();
            default:
                return DownloadUtil.generateKey(uri, downloadType);
        }
    }

    private static AbsDownloadExecutor getExecutor(DownloadType downloadType) {
        AbsDownloadExecutor absDownloadExecutor;
        int intValue = sExecutorsMap.get(downloadType).intValue();
        synchronized (sExecutors) {
            if (sExecutors[intValue] == null) {
                sExecutors[intValue] = createExecutor(intValue);
            }
            absDownloadExecutor = sExecutors[intValue];
        }
        return absDownloadExecutor;
    }

    public boolean cancel(DownloadItem downloadItem) {
        return getExecutor(downloadItem.getType()).cancel(downloadItem);
    }

    public void cancelAll() {
        int i = 0;
        while (true) {
            AbsDownloadExecutor[] absDownloadExecutorArr = sExecutors;
            if (i < absDownloadExecutorArr.length) {
                if (absDownloadExecutorArr[i] != null) {
                    absDownloadExecutorArr[i].cancelAll();
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void cancelAll(DownloadType downloadType) {
        getExecutor(downloadType).cancelAll();
    }

    public boolean contains(DownloadItem downloadItem) {
        return getExecutor(downloadItem.getType()).contains(downloadItem);
    }

    public void download(DownloadItem downloadItem, boolean z, boolean z2) {
        if (downloadItem == null || downloadItem.getKey() == null) {
            throw new IllegalArgumentException("Invalid download item");
        }
        getExecutor(downloadItem.getType()).download(downloadItem, z, z2);
    }

    public IDownloader getDownloader(DownloadType downloadType) {
        IDownloader iDownloader;
        int intValue = sDownloaderMap.get(downloadType).intValue();
        synchronized (sDownloaders) {
            if (sDownloaders[intValue] == null) {
                sDownloaders[intValue] = createDownloader(intValue);
            }
            iDownloader = sDownloaders[intValue];
        }
        return iDownloader;
    }

    public ReentrantLock getLockForUri(Uri uri, DownloadType downloadType) {
        ReentrantLock reentrantLock;
        synchronized (this.mUriLocks) {
            String generateLockKey = generateLockKey(uri, downloadType);
            reentrantLock = this.mUriLocks.get(generateLockKey);
            if (reentrantLock == null) {
                reentrantLock = new ReentrantLock();
                this.mUriLocks.put(generateLockKey, reentrantLock);
            }
        }
        return reentrantLock;
    }
}
