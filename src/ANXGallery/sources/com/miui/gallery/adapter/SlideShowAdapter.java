package com.miui.gallery.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.miui.gallery.Config;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SlideShowAdapter {
    private ImageLoadParams mCacheItem;
    /* access modifiers changed from: private */
    public BlockingQueue<ShowItem> mCacheQueue = new LinkedBlockingQueue(3);
    /* access modifiers changed from: private */
    public BaseDataSet mDataSet;
    private Future mGetFuture;
    private Future mLoadFuture;
    /* access modifiers changed from: private */
    public int mLoadIndex;
    /* access modifiers changed from: private */
    public Object mLock = new Object();
    /* access modifiers changed from: private */
    public int mShowIndex;

    private class GetJob implements ThreadPool.Job<ShowItem> {
        private GetJob() {
        }

        public ShowItem run(ThreadPool.JobContext jobContext) {
            ShowItem showItem = null;
            while (!jobContext.isCancelled() && showItem == null) {
                try {
                    showItem = (ShowItem) SlideShowAdapter.this.mCacheQueue.poll(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Log.i("SlideShowAdapter", "poll interrupted, curSize %d", (Object) Integer.valueOf(SlideShowAdapter.this.mCacheQueue.size()));
                    e.printStackTrace();
                }
                if (showItem == null) {
                    synchronized (SlideShowAdapter.this.mLock) {
                        if (SlideShowAdapter.this.mLoadIndex >= SlideShowAdapter.this.getCount() && SlideShowAdapter.this.mDataSet != null) {
                            return null;
                        }
                    }
                }
            }
            Log.i("SlideShowAdapter", "getJob cancelled, curSize %d", (Object) Integer.valueOf(SlideShowAdapter.this.mCacheQueue.size()));
            if (showItem != null) {
                int unused = SlideShowAdapter.this.mShowIndex = showItem.getIndex();
            }
            return showItem;
        }
    }

    private class LoadItem {
        /* access modifiers changed from: private */
        public long mFileLength;
        /* access modifiers changed from: private */
        public int mIndex;
        private byte[] mSecretKey;
        /* access modifiers changed from: private */
        public String mUri;

        public LoadItem(String str, int i, byte[] bArr, long j) {
            this.mUri = str;
            this.mIndex = i;
            this.mSecretKey = bArr;
            this.mFileLength = j;
        }

        public byte[] getSecretKey() {
            return this.mSecretKey;
        }

        public boolean isSecret() {
            byte[] bArr = this.mSecretKey;
            return bArr != null && bArr.length > 0;
        }
    }

    private class LoadJob implements ThreadPool.Job<Void> {
        private DisplayImageOptions.Builder mDisplayOptionsBuilder = new DisplayImageOptions.Builder().cloneFrom(Config.BigPhotoConfig.BIG_PHOTO_DISPLAY_IMAGE_OPTIONS_DEFAULT).syncLoading(true);

        LoadJob() {
        }

        private DisplayImageOptions getDisplayOptions(LoadItem loadItem) {
            if (loadItem.isSecret()) {
                this.mDisplayOptionsBuilder.secretKey(loadItem.getSecretKey());
            } else {
                this.mDisplayOptionsBuilder.secretKey((byte[]) null);
            }
            if (loadItem.mFileLength > 0) {
                this.mDisplayOptionsBuilder.considerFileLength(true).fileLength(loadItem.mFileLength);
            } else {
                this.mDisplayOptionsBuilder.considerFileLength(false);
            }
            return this.mDisplayOptionsBuilder.build();
        }

        public Void run(ThreadPool.JobContext jobContext) {
            LoadItem access$700;
            while (!jobContext.isCancelled() && (access$700 = SlideShowAdapter.this.getLoadItem()) != null) {
                Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(access$700.mUri, getDisplayOptions(access$700));
                if (loadImageSync != null && !loadImageSync.isRecycled()) {
                    ShowItem showItem = new ShowItem(loadImageSync, access$700.mIndex);
                    boolean z = false;
                    while (!jobContext.isCancelled() && !z) {
                        try {
                            z = SlideShowAdapter.this.mCacheQueue.offer(showItem, 1000, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            Log.i("SlideShowAdapter", "offer interrupted, curSize %d", (Object) Integer.valueOf(SlideShowAdapter.this.mCacheQueue.size()));
                            e.printStackTrace();
                        }
                    }
                    if (!z) {
                        Log.i("SlideShowAdapter", "not offered, curSize %d", (Object) Integer.valueOf(SlideShowAdapter.this.mCacheQueue.size()));
                        synchronized (SlideShowAdapter.this.mLock) {
                            SlideShowAdapter.access$306(SlideShowAdapter.this);
                        }
                    } else {
                        continue;
                    }
                }
            }
            Log.i("SlideShowAdapter", "loadJob cancelled, curSize %d", (Object) Integer.valueOf(SlideShowAdapter.this.mCacheQueue.size()));
            return null;
        }
    }

    public static class ShowItem {
        private Bitmap mBit;
        private int mIndex;

        public ShowItem(Bitmap bitmap, int i) {
            this.mBit = bitmap;
            this.mIndex = i;
        }

        public Bitmap getBitmap() {
            return this.mBit;
        }

        public int getIndex() {
            return this.mIndex;
        }

        public boolean isValid() {
            Bitmap bitmap = this.mBit;
            return bitmap != null && !bitmap.isRecycled();
        }
    }

    public SlideShowAdapter(ImageLoadParams imageLoadParams, int i) {
        this.mCacheItem = imageLoadParams;
        this.mLoadIndex = i;
    }

    static /* synthetic */ int access$306(SlideShowAdapter slideShowAdapter) {
        int i = slideShowAdapter.mLoadIndex - 1;
        slideShowAdapter.mLoadIndex = i;
        return i;
    }

    private void cancelGet() {
        Future future = this.mGetFuture;
        if (future != null) {
            future.cancel();
            this.mGetFuture = null;
        }
    }

    private void cancelLoad() {
        Future future = this.mLoadFuture;
        if (future != null) {
            future.cancel();
            this.mLoadFuture = null;
        }
    }

    private String ensureFileScheme(String str) {
        return (TextUtils.isEmpty(str) || ImageDownloader.Scheme.ofUri(str) != ImageDownloader.Scheme.UNKNOWN) ? str : ImageDownloader.Scheme.FILE.wrap(str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        return null;
     */
    public LoadItem getLoadItem() {
        synchronized (this.mLock) {
            if (this.mDataSet != null) {
                while (this.mLoadIndex >= 0 && this.mLoadIndex < this.mDataSet.getCount()) {
                    BaseDataItem item = this.mDataSet.getItem((BaseDataItem) null, this.mLoadIndex);
                    if (item != null) {
                        String pathDisplayBetter = item.getPathDisplayBetter();
                        if (!TextUtils.isEmpty(pathDisplayBetter)) {
                            this.mLoadIndex++;
                            LoadItem loadItem = new LoadItem(ensureFileScheme(pathDisplayBetter), this.mLoadIndex - 1, item.getSecretKey(), item.getSize());
                            return loadItem;
                        }
                    }
                    this.mLoadIndex++;
                }
            } else if (this.mCacheItem != null && this.mCacheItem.match((BaseDataItem) null, this.mLoadIndex)) {
                this.mLoadIndex++;
                LoadItem loadItem2 = new LoadItem(ensureFileScheme(this.mCacheItem.getPath()), this.mLoadIndex - 1, this.mCacheItem.getSecretKey(), this.mCacheItem.getFileLength());
                return loadItem2;
            }
        }
    }

    private void startLoad() {
        cancelLoad();
        this.mLoadFuture = ThreadManager.getMiscPool().submit(new LoadJob());
    }

    public void changeDataSet(BaseDataSet baseDataSet, int i) {
        synchronized (this.mLock) {
            this.mDataSet = baseDataSet;
            this.mLoadIndex = i;
        }
        cancelLoad();
        this.mCacheQueue.clear();
        startLoad();
    }

    public BaseDataItem getBaseDataItem(int i) {
        BaseDataSet baseDataSet = this.mDataSet;
        if (baseDataSet != null) {
            return baseDataSet.getItem((BaseDataItem) null, i);
        }
        return null;
    }

    public int getCount() {
        BaseDataSet baseDataSet = this.mDataSet;
        if (baseDataSet != null) {
            return baseDataSet.getCount();
        }
        return 0;
    }

    public int getShowIndex() {
        return this.mShowIndex;
    }

    public void nextBitmap(FutureListener<ShowItem> futureListener) {
        cancelGet();
        this.mGetFuture = ThreadManager.getMiscPool().submit(new GetJob(), futureListener);
    }

    public void pause() {
        cancelLoad();
        cancelGet();
    }

    public void resume() {
        startLoad();
    }
}
