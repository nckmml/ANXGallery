package com.miui.gallery.editor.photo.app.filter.skytransfer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.library.Library;
import com.miui.gallery.assistant.library.LibraryManager;
import com.miui.gallery.assistant.library.LibraryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.OptionalResult;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SkyLibraryLoaderHelper {
    private static SkyLibraryLoaderHelper sInstance = new SkyLibraryLoaderHelper();
    private SkyDownloadStateListener mDownloadStateListener;
    private boolean mIsDownloading;

    private void doDownloadLibrary(Library library, boolean z) {
        LibraryManager.getInstance().downloadLibrary(library, z, new LibraryManager.DownloadListener() {
            public void onDownloadProgress(long j, int i) {
                Log.d("SkyLibraryLoaderHelper", "onDownloadProgress: %d", (Object) Integer.valueOf(i));
                SkyLibraryLoaderHelper.this.refreshDownloading(i);
            }

            public void onDownloadResult(long j, int i) {
                Log.d("SkyLibraryLoaderHelper", "download result %d", (Object) Integer.valueOf(i));
                SkyLibraryLoaderHelper.this.refreshDownloadResult(i == 0, i);
            }
        });
    }

    public static SkyLibraryLoaderHelper getInstance() {
        return sInstance;
    }

    private int getLoaderState(Library library) {
        if (library == null || library.getLibraryStatus() != Library.LibraryStatus.STATE_AVAILABLE) {
            return this.mIsDownloading ? 1 : 2;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void refreshDownloadResult(boolean z, int i) {
        this.mIsDownloading = false;
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onFinish(1, z, i);
        }
    }

    private void refreshDownloadStart() {
        Log.d("SkyLibraryLoaderHelper", "refreshDownloadStart");
        this.mIsDownloading = true;
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onDownloadStart(1);
        }
    }

    /* access modifiers changed from: private */
    public void refreshDownloading(int i) {
        this.mIsDownloading = true;
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onDownloading(1, i);
        }
    }

    public String getLibraryDirPath() {
        return LibraryUtils.getLibraryDirPath(GalleryApp.sGetAndroidContext());
    }

    public int getLoaderState() {
        return getLoaderState(LibraryManager.getInstance().getLibrary(1012));
    }

    public /* synthetic */ void lambda$startDownload$118$SkyLibraryLoaderHelper(boolean z, OptionalResult optionalResult) throws Exception {
        Library library = (Library) optionalResult.getIncludeNull();
        if (library == null) {
            Log.w("SkyLibraryLoaderHelper", "getLibrarySync failed");
            refreshDownloadResult(false, -2);
            return;
        }
        doDownloadLibrary(library, z);
    }

    public void setDownloadStateListener(SkyDownloadStateListener skyDownloadStateListener) {
        this.mDownloadStateListener = skyDownloadStateListener;
    }

    @SuppressLint({"CheckResult"})
    public void startDownload(boolean z) {
        refreshDownloadStart();
        Library library = LibraryManager.getInstance().getLibrary(1012);
        if (library == null) {
            Observable.create($$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0.INSTANCE).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    SkyLibraryLoaderHelper.this.lambda$startDownload$118$SkyLibraryLoaderHelper(this.f$1, (OptionalResult) obj);
                }
            });
        } else {
            doDownloadLibrary(library, z);
        }
    }
}
