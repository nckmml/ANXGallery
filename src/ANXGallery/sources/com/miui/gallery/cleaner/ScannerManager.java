package com.miui.gallery.cleaner;

import com.miui.gallery.cleaner.BaseScanner;
import com.miui.gallery.cleaner.slim.SlimScanner;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.nexstreaming.nexeditorsdk.nexClip;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class ScannerManager {
    public static final int[] TIME_COST_STAGE = {5, 10, 15, 25, 40, 60, nexClip.kClip_Rotate_180, 300};
    private static ScannerManager instance;
    /* access modifiers changed from: private */
    public ScanObserverHolder mObserverHolder = new ScanObserverHolder();
    /* access modifiers changed from: private */
    public BaseScanner.OnScanResultUpdateListener mOnScanResultUpdateListener = new BaseScanner.OnScanResultUpdateListener() {
        public void onUpdate(int i, long j, ScanResult scanResult) {
            if (scanResult == null || scanResult.getSize() <= 0) {
                ScannerManager.this.removeScanResult(i);
            } else {
                ScannerManager.this.updateScanResult(i, scanResult);
            }
            if (j > 0) {
                Iterator it = ScannerManager.this.mScanners.iterator();
                while (it.hasNext()) {
                    BaseScanner baseScanner = (BaseScanner) it.next();
                    if (baseScanner.mType != i) {
                        baseScanner.onMediaItemDeleted(j);
                    }
                }
            }
        }
    };
    private boolean mReset = true;
    /* access modifiers changed from: private */
    public Future<Void> mScanFuture;
    private FutureHandler mScanFutureHandler = new FutureHandler<Void>() {
        public void onPostExecute(Future<Void> future) {
            if (!future.isCancelled()) {
                ScannerManager.this.mObserverHolder.onScanFinish(ScannerManager.this.mSize);
                HashMap hashMap = new HashMap();
                hashMap.put("timeCost", GallerySamplingStatHelper.formatValueStage((float) ((int) ((System.currentTimeMillis() - ScannerManager.this.mScanStartTime) / 1000)), ScannerManager.TIME_COST_STAGE));
                GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_scan_finish", hashMap);
            }
        }
    };
    private ThreadPool.Job<Void> mScanJob = new ThreadPool.Job<Void>() {
        public Void run(ThreadPool.JobContext jobContext) {
            Iterator it = ScannerManager.this.mScanners.iterator();
            while (it.hasNext()) {
                BaseScanner baseScanner = (BaseScanner) it.next();
                if (jobContext.isCancelled()) {
                    return null;
                }
                ScanResult scan = baseScanner.scan();
                if (scan != null && scan.getSize() > 0) {
                    synchronized (ScannerManager.this) {
                        if (jobContext.isCancelled()) {
                            return null;
                        }
                        synchronized (ScannerManager.this.mScanResults) {
                            long unused = ScannerManager.this.mSize = ScannerManager.this.mSize + scan.getSize();
                            ScannerManager.this.mScanResults.add(scan);
                        }
                        baseScanner.addListener(ScannerManager.this.mOnScanResultUpdateListener);
                        ScannerManager.this.onScanProgress(jobContext);
                    }
                }
            }
            synchronized (ScannerManager.this) {
                if (!jobContext.isCancelled()) {
                    Future unused2 = ScannerManager.this.mScanFuture = null;
                }
            }
            return null;
        }
    };
    /* access modifiers changed from: private */
    public ArrayList<ScanResult> mScanResults = new ArrayList<>();
    /* access modifiers changed from: private */
    public long mScanStartTime;
    /* access modifiers changed from: private */
    public ArrayList<BaseScanner> mScanners = new ArrayList<>();
    /* access modifiers changed from: private */
    public volatile long mSize;

    public interface ScanObserver {
        void onScanCanceled();

        void onScanFinish(long j);

        void onScanProgress(long j);

        void onScanResultUpdate(long j);

        void onScanStart();
    }

    private static class ScanObserverHolder implements ScanObserver {
        CopyOnWriteArraySet<ScanObserver> observers;

        private ScanObserverHolder() {
            this.observers = new CopyOnWriteArraySet<>();
        }

        public void onScanCanceled() {
            Iterator<ScanObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onScanCanceled();
            }
        }

        public void onScanFinish(long j) {
            Iterator<ScanObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onScanFinish(j);
            }
        }

        public void onScanProgress(long j) {
            Iterator<ScanObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onScanProgress(j);
            }
        }

        public void onScanResultUpdate(long j) {
            Iterator<ScanObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onScanResultUpdate(j);
            }
        }

        public void onScanStart() {
            Iterator<ScanObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onScanStart();
            }
        }
    }

    private ScannerManager() {
        this.mScanners.add(new SlimScanner());
        this.mScanners.add(new ScreenshotScanner());
        this.mScanners.add(new RawPhotoScanner());
        this.mScanners.add(new VideoScanner());
        this.mScanners.add(new SimilarScanner());
    }

    public static synchronized ScannerManager getInstance() {
        ScannerManager scannerManager;
        synchronized (ScannerManager.class) {
            if (instance == null) {
                instance = new ScannerManager();
            }
            scannerManager = instance;
        }
        return scannerManager;
    }

    /* access modifiers changed from: private */
    public void onScanProgress(final ThreadPool.JobContext jobContext) {
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                if (!jobContext.isCancelled()) {
                    ScannerManager.this.mObserverHolder.onScanProgress(ScannerManager.this.mSize);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeScanResult(int i) {
        synchronized (this.mScanResults) {
            Iterator<ScanResult> it = this.mScanResults.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ScanResult next = it.next();
                if (next.getType() == i) {
                    it.remove();
                    this.mSize -= next.getSize();
                    if (this.mSize < 0) {
                        this.mSize = 0;
                    }
                }
            }
        }
        updateScanResult();
    }

    private void updateScanResult() {
        ThreadManager.runOnMainThread(new Runnable() {
            public void run() {
                ScannerManager.this.mObserverHolder.onScanResultUpdate(ScannerManager.this.mSize);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateScanResult(int i, ScanResult scanResult) {
        synchronized (this.mScanResults) {
            int i2 = 0;
            int size = this.mScanResults.size();
            while (true) {
                if (i2 >= size) {
                    break;
                }
                ScanResult scanResult2 = this.mScanResults.get(i2);
                if (scanResult2.getType() == i) {
                    this.mScanResults.set(i2, scanResult);
                    this.mSize += scanResult.getSize() - scanResult2.getSize();
                    if (this.mSize < 0) {
                        this.mSize = 0;
                    }
                } else {
                    i2++;
                }
            }
        }
        updateScanResult();
    }

    public List<ScanResult> getScanResults() {
        return new ArrayList(this.mScanResults);
    }

    public long getScanSize() {
        long j;
        synchronized (this.mScanResults) {
            j = this.mSize;
        }
        return j;
    }

    public BaseScanner getScanner(int i) {
        Iterator<BaseScanner> it = this.mScanners.iterator();
        while (it.hasNext()) {
            BaseScanner next = it.next();
            if (next.mType == i) {
                return next;
            }
        }
        return null;
    }

    public long getStartTime() {
        return this.mScanStartTime;
    }

    public synchronized boolean isReset() {
        return this.mReset;
    }

    public synchronized boolean isScanFinish() {
        return !isReset() && !isScanning();
    }

    public boolean isScanResultEmpty() {
        boolean z;
        synchronized (this.mScanResults) {
            if (!this.mScanResults.isEmpty()) {
                if (this.mSize != 0) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public synchronized boolean isScanning() {
        return this.mScanFuture != null;
    }

    public void registerObserver(ScanObserver scanObserver) {
        this.mObserverHolder.observers.add(scanObserver);
    }

    public synchronized void resetScan() {
        if (this.mScanFuture != null) {
            this.mScanFuture.cancel();
            this.mScanFuture = null;
            ThreadManager.runOnMainThread(new Runnable() {
                public void run() {
                    ScannerManager.this.mObserverHolder.onScanCanceled();
                }
            });
        }
        Iterator<BaseScanner> it = this.mScanners.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        synchronized (this.mScanResults) {
            this.mSize = 0;
            this.mScanStartTime = 0;
            this.mScanResults.clear();
        }
        this.mReset = true;
    }

    public synchronized void startScan() {
        resetScan();
        this.mScanStartTime = System.currentTimeMillis();
        this.mScanFuture = ThreadManager.getMiscPool().submit(this.mScanJob, this.mScanFutureHandler);
        this.mReset = false;
        ThreadManager.runOnMainThread(new Runnable() {
            public void run() {
                ScannerManager.this.mObserverHolder.onScanStart();
            }
        });
    }

    public void unregisterObserver(ScanObserver scanObserver) {
        this.mObserverHolder.observers.remove(scanObserver);
    }
}
