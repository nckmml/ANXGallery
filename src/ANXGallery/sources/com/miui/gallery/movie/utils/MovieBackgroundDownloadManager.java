package com.miui.gallery.movie.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.entity.TemplateResource;
import com.miui.gallery.movie.net.TemplateResourceRequest;
import com.miui.gallery.movie.ui.factory.TemplateFactory;
import com.miui.gallery.movie.ui.listener.MovieDownloadListener;
import com.miui.gallery.net.base.RequestError;
import com.miui.gallery.util.BaseFileUtils;
import com.miui.gallery.util.BaseNetworkUtils;
import com.miui.gallery.util.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MovieBackgroundDownloadManager {
    private static MovieBackgroundDownloadManager sInstance = new MovieBackgroundDownloadManager();
    private List<TemplateResource> mTemplateResources;

    private MovieBackgroundDownloadManager() {
    }

    public static MovieBackgroundDownloadManager getInstance() {
        return sInstance;
    }

    private TemplateResource getTemplateResourceFromNameSync(String str) {
        List<TemplateResource> list = this.mTemplateResources;
        if (list == null) {
            try {
                Object[] executeSync = new TemplateResourceRequest().executeSync();
                if (executeSync != null && executeSync.length > 0 && (executeSync[0] instanceof List)) {
                    list = (List) executeSync[0];
                }
                Log.d("MovieBackgroundDownloadManager", "getTemplateList %d ", (Object) Integer.valueOf(list == null ? -1 : list.size()));
            } catch (RequestError unused) {
                Log.e("MovieBackgroundDownloadManager", "RequestError: getTemplateList");
            }
        }
        TemplateResource templateResource = null;
        if (list == null) {
            Log.d("MovieBackgroundDownloadManager", "template resource is null");
        } else {
            for (TemplateResource next : list) {
                if (TextUtils.equals(next.nameKey, str)) {
                    templateResource = next;
                }
            }
        }
        this.mTemplateResources = list;
        return templateResource;
    }

    public void downloadTemplate(Context context, int i) {
        if (!BaseNetworkUtils.isNetworkConnected()) {
            Log.d("MovieBackgroundDownloadManager", "download templateId %d no network", (Object) Integer.valueOf(i));
        } else if (BaseNetworkUtils.isActiveNetworkMetered()) {
            Log.d("MovieBackgroundDownloadManager", "download templateId %d in network metered", (Object) Integer.valueOf(i));
        } else {
            MovieConfig.init(context);
            Observable.just(Integer.valueOf(i)).observeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).map($$Lambda$MovieBackgroundDownloadManager$k7LycHZWs21VROhZxpUkRVToJE.INSTANCE).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    MovieBackgroundDownloadManager.this.lambda$downloadTemplate$1$MovieBackgroundDownloadManager((String) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$downloadTemplate$1$MovieBackgroundDownloadManager(String str) throws Exception {
        if (TextUtils.equals(str, MovieStatUtils.FROM_NORMAL) || BaseFileUtils.isFileExist(TemplateFactory.getTemplateDir(str))) {
            Log.d("MovieBackgroundDownloadManager", "template %s is already exist", (Object) str);
            return;
        }
        TemplateResource templateResourceFromNameSync = getTemplateResourceFromNameSync(str);
        if (templateResourceFromNameSync != null) {
            Log.d("MovieBackgroundDownloadManager", "start download %s in background", (Object) templateResourceFromNameSync.nameKey);
            MovieDownloadManager.getInstance().downloadResource(templateResourceFromNameSync, (MovieDownloadListener) null, false);
        }
    }
}
