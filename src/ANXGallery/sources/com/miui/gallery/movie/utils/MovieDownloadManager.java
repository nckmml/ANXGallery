package com.miui.gallery.movie.utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import com.miui.gallery.movie.R;
import com.miui.gallery.movie.entity.MovieResource;
import com.miui.gallery.movie.ui.listener.MovieDownloadListener;
import com.miui.gallery.net.download.Request;
import com.miui.gallery.net.resource.ResourceDownloadManager;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.gallery.util.BaseNetworkUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.UnzipUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MovieDownloadManager {
    private static MovieDownloadManager sInstance = new MovieDownloadManager();
    private ResourceDownloadManager mResourceDownloadManager;

    private MovieDownloadManager() {
    }

    public static MovieDownloadManager getInstance() {
        return sInstance;
    }

    static /* synthetic */ boolean lambda$downloadResource$3(MovieDownloadListener movieDownloadListener, Integer num) throws Exception {
        if (!(num.intValue() == 0 || movieDownloadListener == null)) {
            movieDownloadListener.onCompleted(false);
        }
        return num.intValue() == 0;
    }

    static /* synthetic */ void lambda$downloadResource$4(MovieResource movieResource, MovieDownloadListener movieDownloadListener, Integer num) throws Exception {
        Log.d("MovieDownloadManager", "download %s :%d", movieResource.getDownloadFilePath(), num);
        boolean unZipFile = UnzipUtils.unZipFile(movieResource.getDownloadFilePath());
        if (movieDownloadListener != null) {
            movieDownloadListener.onCompleted(unZipFile);
        }
    }

    public void cancelAll() {
        ResourceDownloadManager resourceDownloadManager = this.mResourceDownloadManager;
        if (resourceDownloadManager != null) {
            resourceDownloadManager.cancelAll();
        }
    }

    public void downloadResource(MovieResource movieResource, MovieDownloadListener movieDownloadListener, boolean z) {
        if (this.mResourceDownloadManager == null) {
            this.mResourceDownloadManager = new ResourceDownloadManager();
        }
        if (movieDownloadListener != null) {
            movieDownloadListener.onStart();
        }
        Observable.create(new ObservableOnSubscribe(movieResource, z) {
            private final /* synthetic */ MovieResource f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                MovieDownloadManager.this.lambda$downloadResource$2$MovieDownloadManager(this.f$1, this.f$2, observableEmitter);
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return MovieDownloadManager.lambda$downloadResource$3(MovieDownloadListener.this, (Integer) obj);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer(movieDownloadListener) {
            private final /* synthetic */ MovieDownloadListener f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                MovieDownloadManager.lambda$downloadResource$4(MovieResource.this, this.f$1, (Integer) obj);
            }
        });
    }

    public void downloadResourceWithCheck(Activity activity, final MovieResource movieResource, final MovieDownloadListener movieDownloadListener) {
        if (!BaseNetworkUtils.isNetworkConnected()) {
            ToastUtils.makeText((Context) activity, R.string.movie_download_failed_for_notwork);
            Log.d("MovieDownloadManager", "download resource no network");
        } else if (BaseNetworkUtils.isActiveNetworkMetered()) {
            ConfirmDialog.showConfirmDialog(activity.getFragmentManager(), activity.getResources().getString(R.string.movie_download_with_metered_network_title), activity.getResources().getString(R.string.movie_download_with_metered_network_msg), activity.getResources().getString(R.string.movie_cancel_download), activity.getResources().getString(R.string.movie_download), new ConfirmDialog.ConfirmDialogInterface() {
                public void onCancel(DialogFragment dialogFragment) {
                }

                public void onConfirm(DialogFragment dialogFragment) {
                    MovieDownloadManager.this.downloadResource(movieResource, movieDownloadListener, true);
                }
            });
        } else {
            downloadResource(movieResource, movieDownloadListener, false);
        }
    }

    public /* synthetic */ void lambda$downloadResource$2$MovieDownloadManager(final MovieResource movieResource, boolean z, final ObservableEmitter observableEmitter) throws Exception {
        this.mResourceDownloadManager.download(movieResource, movieResource.getDownloadFilePath(), new Request.Listener() {
            public void onComplete(int i) {
                observableEmitter.onNext(Integer.valueOf(i));
            }

            public void onProgressUpdate(int i) {
                Log.d("MovieDownloadManager", "download progress %s :%d", movieResource.getDownloadFilePath(), Integer.valueOf(i));
            }

            public void onStart() {
            }
        }, z);
    }
}
