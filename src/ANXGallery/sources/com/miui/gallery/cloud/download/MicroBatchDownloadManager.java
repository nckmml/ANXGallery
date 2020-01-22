package com.miui.gallery.cloud.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.cloud.download.DownloadObserver;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.sdk.download.DownloadOptions;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.sdk.download.ImageDownloader;
import com.miui.gallery.sdk.download.listener.DownloadListener;
import com.miui.gallery.sdk.download.listener.DownloadProgressListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MicroBatchDownloadManager implements DownloadObserver.OnConditionChangeListener {
    private final Object mDispatchLock;
    private Disposable mDisposable;
    private DownloadOptions mDownloadOptions;
    private final AtomicBoolean mIsTerminated;
    private final PublishProcessor<Uri> mPublishProcessor;
    /* access modifiers changed from: private */
    public Runnable mTerminalSignal;

    private static final class SingletonHolder {
        /* access modifiers changed from: private */
        public static final MicroBatchDownloadManager INSTANCE = new MicroBatchDownloadManager();
    }

    private MicroBatchDownloadManager() {
        this.mDispatchLock = new Object();
        this.mIsTerminated = new AtomicBoolean(false);
        this.mPublishProcessor = PublishProcessor.create();
        this.mDownloadOptions = new DownloadOptions.Builder().setRequireWLAN(true).setRequireDeviceStorage(true).setRequirePower(false).setQueueFirst(false).build();
        DownloadObserver.getInstance().register(GalleryApp.sGetAndroidContext(), this);
        LocalBroadcastManager.getInstance(GalleryApp.sGetAndroidContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                if (MicroBatchDownloadManager.this.mTerminalSignal == null) {
                    MicroBatchDownloadManager microBatchDownloadManager = MicroBatchDownloadManager.this;
                    Runnable unused = microBatchDownloadManager.mTerminalSignal = new Runnable() {
                        public final void run() {
                            MicroBatchDownloadManager.this.terminate();
                        }
                    };
                }
                ThreadManager.getMainHandler().postDelayed(MicroBatchDownloadManager.this.mTerminalSignal, 60000);
            }
        }, new IntentFilter("com.miui.gallery.action.FIRST_SYNC_FINISHED"));
    }

    private boolean checkCondition() {
        if (!GalleryPreferences.CTA.canConnectNetwork()) {
            Log.d("MicroBatchDownloadManager", "condition cta not allowed");
            return false;
        } else if (!NetworkUtils.isNetworkConnected()) {
            Log.d("MicroBatchDownloadManager", "condition no network");
            return false;
        } else if (NetworkUtils.isActiveNetworkMetered()) {
            Log.d("MicroBatchDownloadManager", "condition no wifi");
            return false;
        } else if (!GalleryPreferences.Sync.getPowerCanSync()) {
            Log.d("MicroBatchDownloadManager", "condition low power");
            return false;
        } else if (!GalleryPreferences.Sync.isDeviceStorageLow()) {
            return true;
        } else {
            Log.d("MicroBatchDownloadManager", "condition low storage");
            return false;
        }
    }

    private void doDownload(List<Uri> list) {
        synchronized (this.mDispatchLock) {
            for (Uri load : list) {
                ImageDownloader.getInstance().load(load, DownloadType.MICRO_BATCH, this.mDownloadOptions, (DownloadListener) null, (DownloadProgressListener) null);
            }
        }
    }

    private void ensureSubscribed() {
        Disposable disposable = this.mDisposable;
        if (disposable == null || disposable.isDisposed()) {
            this.mDisposable = this.mPublishProcessor.buffer(3, TimeUnit.SECONDS, 25).filter($$Lambda$MicroBatchDownloadManager$fCWUnspqAA8ZEfdZJuotVpMjxk.INSTANCE).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    MicroBatchDownloadManager.this.lambda$ensureSubscribed$57$MicroBatchDownloadManager((List) obj);
                }
            });
        }
    }

    public static MicroBatchDownloadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static /* synthetic */ boolean lambda$ensureSubscribed$56(List list) throws Exception {
        return list.size() > 0;
    }

    /* access modifiers changed from: private */
    public void terminate() {
        if (this.mIsTerminated.compareAndSet(false, true)) {
            Log.i("MicroBatchDownloadManager", "terminate download after first sync finish");
            if (this.mTerminalSignal != null) {
                ThreadManager.getMainHandler().removeCallbacks(this.mTerminalSignal);
                this.mTerminalSignal = null;
            }
            DownloadObserver.getInstance().unregister(GalleryApp.sGetAndroidContext(), this);
            Disposable disposable = this.mDisposable;
            if (disposable != null && !disposable.isDisposed()) {
                this.mDisposable.dispose();
                this.mDisposable = null;
            }
            ImageDownloader.getInstance().cancelAll(DownloadType.MICRO_BATCH);
            this.mDownloadOptions = null;
        }
    }

    public void download(Uri uri) {
        if (uri != null && !this.mIsTerminated.get()) {
            synchronized (this) {
                ensureSubscribed();
                this.mPublishProcessor.onNext(uri);
            }
        }
    }

    public /* synthetic */ void lambda$ensureSubscribed$57$MicroBatchDownloadManager(List list) throws Exception {
        if (!this.mIsTerminated.get()) {
            if (checkCondition()) {
                doDownload(list);
                Log.d("MicroBatchDownloadManager", "dispatch download %d items", (Object) Integer.valueOf(list.size()));
                return;
            }
            Log.d("MicroBatchDownloadManager", "condition is unsatisfied, skip %d items", (Object) Integer.valueOf(list.size()));
        }
    }

    public void onConditionChanged(Context context) {
        if (!this.mIsTerminated.get() && !checkCondition()) {
            Log.i("MicroBatchDownloadManager", "condition changed, cancel all tasks");
            ImageDownloader.getInstance().cancelAll(DownloadType.MICRO_BATCH);
        }
    }
}
