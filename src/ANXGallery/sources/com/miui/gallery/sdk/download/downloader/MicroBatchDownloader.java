package com.miui.gallery.sdk.download.downloader;

import com.miui.gallery.sdk.download.assist.RequestItem;
import com.miui.gallery.threadpool.PriorityThreadFactory;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import miui.util.SoftReferenceSingleton;
import org.json.JSONObject;

public class MicroBatchDownloader extends MicroThumbnailDownloader {
    private static final SoftReferenceSingleton<Scheduler> mScheduler = new SoftReferenceSingleton<Scheduler>() {
        /* access modifiers changed from: protected */
        public Scheduler createInstance() {
            return Schedulers.from(new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory("micro-batch-download", 10)));
        }
    };

    /* access modifiers changed from: protected */
    public void doFileDownload(List<RequestItem> list, JSONObject jSONObject) {
        Flowable.fromIterable(list).parallel().runOn((Scheduler) mScheduler.get()).map(new Function(jSONObject) {
            private final /* synthetic */ JSONObject f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return MicroBatchDownloader.this.lambda$doFileDownload$58$MicroBatchDownloader(this.f$1, (RequestItem) obj);
            }
        }).sequential().blockingSubscribe();
    }

    /* access modifiers changed from: protected */
    public String getTag() {
        return "MicroBatchDownloader";
    }

    public /* synthetic */ Boolean lambda$doFileDownload$58$MicroBatchDownloader(JSONObject jSONObject, RequestItem requestItem) throws Exception {
        return Boolean.valueOf(downloadFileItem(requestItem, jSONObject.optJSONObject(requestItem.mDBItem.getRequestId())));
    }

    /* access modifiers changed from: protected */
    public boolean shouldWaitUriLock() {
        return false;
    }
}
