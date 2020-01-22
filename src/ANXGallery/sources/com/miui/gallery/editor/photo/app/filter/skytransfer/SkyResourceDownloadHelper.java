package com.miui.gallery.editor.photo.app.filter.skytransfer;

import android.annotation.SuppressLint;
import com.miui.gallery.net.download.Request;
import com.miui.gallery.net.resource.Resource;
import com.miui.gallery.net.resource.ResourceDownloadManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.UnzipUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;

public class SkyResourceDownloadHelper {
    private static String SKY_RES_DIR = "sky_res";
    private static String SKY_V1_DIR = "filter_sky_v1";
    private static SkyResourceDownloadHelper sInstance = new SkyResourceDownloadHelper();
    private SkyDownloadStateListener mDownloadStateListener;
    private boolean mIsDownloading;
    private ResourceDownloadManager mResourceDownloadManager;

    @SuppressLint({"CheckResult"})
    private void downloadResource(boolean z) {
        refreshDownloadStart();
        if (this.mResourceDownloadManager == null) {
            this.mResourceDownloadManager = new ResourceDownloadManager();
        }
        Resource resource = new Resource();
        resource.id = 11285679226814528L;
        String str = getDownloadPath() + File.separator + "file.zip";
        Observable.create(new ObservableOnSubscribe(str, resource, z) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Resource f$2;
            private final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                SkyResourceDownloadHelper.this.lambda$downloadResource$119$SkyResourceDownloadHelper(this.f$1, this.f$2, this.f$3, observableEmitter);
            }
        }).observeOn(Schedulers.io()).map(new Function(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return SkyResourceDownloadHelper.this.lambda$downloadResource$120$SkyResourceDownloadHelper(this.f$1, (Integer) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                SkyResourceDownloadHelper.this.lambda$downloadResource$121$SkyResourceDownloadHelper((Integer) obj);
            }
        });
    }

    private String getDownloadPath() {
        return StaticContext.sGetAndroidContext().getFilesDir().getAbsolutePath() + File.separator + SKY_RES_DIR;
    }

    public static SkyResourceDownloadHelper getInstance() {
        return sInstance;
    }

    private void refreshDownloadResult(boolean z, int i) {
        this.mIsDownloading = false;
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onFinish(2, z, i);
        }
    }

    private void refreshDownloadStart() {
        this.mIsDownloading = true;
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onDownloadStart(2);
        }
    }

    /* access modifiers changed from: private */
    public void refreshDownloading(int i) {
        SkyDownloadStateListener skyDownloadStateListener = this.mDownloadStateListener;
        if (skyDownloadStateListener != null) {
            skyDownloadStateListener.onDownloading(2, i);
        }
    }

    public String getSkyResPathV1() {
        return getDownloadPath() + File.separator + SKY_V1_DIR;
    }

    public boolean isResourceDownloaded() {
        File file = new File(getDownloadPath(), SKY_V1_DIR);
        return file.exists() && file.isDirectory();
    }

    public /* synthetic */ void lambda$downloadResource$119$SkyResourceDownloadHelper(final String str, Resource resource, boolean z, final ObservableEmitter observableEmitter) throws Exception {
        this.mResourceDownloadManager.download(resource, str, new Request.Listener() {
            public void onComplete(int i) {
                observableEmitter.onNext(Integer.valueOf(i));
            }

            public void onProgressUpdate(int i) {
                Log.d("SkyResourceDownloadHelper", "download progress %s :%d", str, Integer.valueOf(i));
                SkyResourceDownloadHelper.this.refreshDownloading(i);
            }

            public void onStart() {
            }
        }, z);
    }

    public /* synthetic */ Integer lambda$downloadResource$120$SkyResourceDownloadHelper(String str, Integer num) throws Exception {
        if (num.intValue() == 0 && !UnzipUtils.unZipFile(str)) {
            num = -2;
        }
        Log.d("SkyResourceDownloadHelper", "download %s :%d", getDownloadPath(), num);
        return num;
    }

    public /* synthetic */ void lambda$downloadResource$121$SkyResourceDownloadHelper(Integer num) throws Exception {
        refreshDownloadResult(num.intValue() == 0, num.intValue());
    }

    public void setDownloadStateListener(SkyDownloadStateListener skyDownloadStateListener) {
        this.mDownloadStateListener = skyDownloadStateListener;
    }

    public void startDownload(boolean z) {
        if (!this.mIsDownloading) {
            downloadResource(z);
        }
    }
}
