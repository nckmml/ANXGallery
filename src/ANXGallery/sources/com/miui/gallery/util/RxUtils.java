package com.miui.gallery.util;

import com.miui.gallery.util.thread.RxGalleryExecutors;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public class RxUtils {
    public static <T> FlowableTransformer<T, T> ioAndMainThread() {
        return new FlowableTransformer<T, T>() {
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.subscribeOn(Schedulers.from(RxGalleryExecutors.getInstance().getUserThreadExecutor())).observeOn(RxGalleryExecutors.getInstance().getUiThreadExecutor().getScheduler(), true);
            }
        };
    }
}
