package com.miui.gallery.util.photoview;

import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TileDecodeManager {
    /* access modifiers changed from: private */
    public volatile SparseArray<Tile> mCurrentDecodingTiles = new SparseArray<>();
    private FutureContainer mDecodeFutureContainer;
    private WeakReference<Handler> mDecodeHandlerRef;
    private WeakReference<TileBitProvider> mDecodeProviderRef;
    /* access modifiers changed from: private */
    public BlockingQueue<Tile> mDecodeQueue = new LinkedBlockingQueue();
    /* access modifiers changed from: private */
    public final Object mLock = new Object();

    private static class FutureContainer {
        List<Future> mFutures;

        private FutureContainer() {
            this.mFutures = new ArrayList();
        }

        public void add(Future future) {
            this.mFutures.add(future);
        }

        public void cancel() {
            for (Future cancel : this.mFutures) {
                cancel.cancel();
            }
            this.mFutures.clear();
        }
    }

    private class TileDecodeJob implements ThreadPool.Job<Void> {
        private TileDecodeJob() {
        }

        public Void run(ThreadPool.JobContext jobContext) {
            while (!jobContext.isCancelled()) {
                try {
                    Tile tile = (Tile) TileDecodeManager.this.mDecodeQueue.poll(2000, TimeUnit.MILLISECONDS);
                    if (tile == null) {
                        continue;
                    } else if (tile.isActive()) {
                        if (jobContext.isCancelled()) {
                            TileDecodeManager.this.mDecodeQueue.clear();
                            return null;
                        }
                        SystemClock.uptimeMillis();
                        int makeTileKey = TileView.makeTileKey(tile.getTileRect());
                        synchronized (TileDecodeManager.this.mLock) {
                            TileDecodeManager.this.mCurrentDecodingTiles.put(makeTileKey, tile);
                        }
                        boolean decode = tile.decode(TileDecodeManager.this.getProvider());
                        Handler access$600 = TileDecodeManager.this.getHandler();
                        if (access$600 != null) {
                            access$600.obtainMessage(decode ? 1 : 2, tile).sendToTarget();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i("TileDecodeManager", "tile decode job is cancelled %s", (Object) Boolean.valueOf(jobContext.isCancelled()));
            return null;
        }
    }

    public TileDecodeManager(Handler handler, TileBitProvider tileBitProvider) {
        this.mDecodeHandlerRef = new WeakReference<>(handler);
        this.mDecodeProviderRef = new WeakReference<>(tileBitProvider);
    }

    /* access modifiers changed from: private */
    public Handler getHandler() {
        WeakReference<Handler> weakReference = this.mDecodeHandlerRef;
        if (weakReference != null) {
            return (Handler) weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public TileBitProvider getProvider() {
        WeakReference<TileBitProvider> weakReference = this.mDecodeProviderRef;
        if (weakReference != null) {
            return (TileBitProvider) weakReference.get();
        }
        return null;
    }

    private void startDecodeEngine() {
        if (this.mDecodeFutureContainer == null) {
            this.mDecodeFutureContainer = new FutureContainer();
            TileBitProvider provider = getProvider();
            if (provider == null || provider.getParallelism() <= 1) {
                this.mDecodeFutureContainer.add(ThreadManager.getDecodePool().submit(new TileDecodeJob()));
            } else {
                for (int i = 0; i < provider.getParallelism(); i++) {
                    this.mDecodeFutureContainer.add(ThreadManager.getDecodePool().submit(new TileDecodeJob()));
                }
            }
            Log.i("TileDecodeManager", "start decode engine");
        }
    }

    public void cancel() {
        FutureContainer futureContainer = this.mDecodeFutureContainer;
        if (futureContainer != null) {
            futureContainer.cancel();
            Log.i("TileDecodeManager", "cancel decode engine");
        }
        this.mDecodeFutureContainer = null;
        this.mDecodeQueue.clear();
        synchronized (this.mLock) {
            this.mCurrentDecodingTiles.clear();
        }
    }

    public void clear() {
        this.mDecodeQueue.clear();
        Log.i("TileDecodeManager", "clear queue");
    }

    public Tile getDecodingTile(int i) {
        Tile tile;
        synchronized (this.mLock) {
            tile = this.mCurrentDecodingTiles.get(i);
        }
        return tile;
    }

    public boolean put(Tile tile) {
        if (tile == null) {
            return false;
        }
        startDecodeEngine();
        return this.mDecodeQueue.offer(tile);
    }

    public void removeDecodingTile(int i) {
        synchronized (this.mLock) {
            this.mCurrentDecodingTiles.remove(i);
        }
    }
}
