package com.miui.gallery.ui.album;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.concurrent.TimeUnit;

public abstract class NoRepeatContentObserver extends ContentObserver {
    /* access modifiers changed from: private */
    public ContentOnSubscribe<MyContentSubscribeBean> mContentOnSubscribe;
    /* access modifiers changed from: private */
    public MyContentSubscribeBean mContentSubscribeBean;
    private Disposable mDisposable;
    private long mIntervalDuration;
    private TimeUnit mTimeUnit;

    private final class ContentOnSubscribe<T extends MyContentSubscribeBean> implements FlowableOnSubscribe<T> {
        private FlowableEmitter<T> mEmitter;

        private ContentOnSubscribe() {
        }

        public FlowableEmitter<T> getEmitter() {
            return this.mEmitter;
        }

        public void subscribe(FlowableEmitter<T> flowableEmitter) throws Exception {
            this.mEmitter = flowableEmitter;
        }
    }

    private static class MyContentSubscribeBean {
        /* access modifiers changed from: private */
        public boolean isSelfChange;
        /* access modifiers changed from: private */
        public Uri mUri;

        private MyContentSubscribeBean() {
        }

        public void setSelfChange(boolean z) {
            this.isSelfChange = z;
        }

        public void setUri(Uri uri) {
            this.mUri = uri;
        }
    }

    public NoRepeatContentObserver(Handler handler) {
        this(handler, 1, TimeUnit.SECONDS);
    }

    public NoRepeatContentObserver(Handler handler, long j, TimeUnit timeUnit) {
        super(handler);
        this.mIntervalDuration = j;
        this.mTimeUnit = timeUnit;
        this.mContentOnSubscribe = new ContentOnSubscribe<>();
        this.mContentSubscribeBean = new MyContentSubscribeBean();
        this.mDisposable = (Disposable) Flowable.create(this.mContentOnSubscribe, BackpressureStrategy.BUFFER).debounce(this.mIntervalDuration, this.mTimeUnit).doOnCancel(new Action() {
            public void run() throws Exception {
                ContentOnSubscribe unused = NoRepeatContentObserver.this.mContentOnSubscribe = null;
                MyContentSubscribeBean unused2 = NoRepeatContentObserver.this.mContentSubscribeBean = null;
            }
        }).subscribeWith(new DisposableSubscriber<MyContentSubscribeBean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(MyContentSubscribeBean myContentSubscribeBean) {
                if (myContentSubscribeBean != null) {
                    NoRepeatContentObserver.this.onDataChange(myContentSubscribeBean.isSelfChange, myContentSubscribeBean.mUri);
                }
            }
        });
    }

    public void onChange(boolean z, Uri uri) {
        MyContentSubscribeBean myContentSubscribeBean = this.mContentSubscribeBean;
        if (myContentSubscribeBean != null) {
            myContentSubscribeBean.setUri(uri);
            this.mContentSubscribeBean.setSelfChange(z);
            this.mContentOnSubscribe.getEmitter().onNext(this.mContentSubscribeBean);
        }
    }

    public abstract void onDataChange(boolean z, Uri uri);
}
