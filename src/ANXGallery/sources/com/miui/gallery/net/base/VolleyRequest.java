package com.miui.gallery.net.base;

import android.os.Looper;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.miui.gallery.net.HttpManager;
import com.miui.gallery.threadpool.ThreadManager;
import java.util.concurrent.CountDownLatch;

public abstract class VolleyRequest<T, E> extends BaseRequest<E> implements Response.ErrorListener, Response.Listener<T> {
    private Request<T> mRequest;
    private CountDownLatch mSyncExecuteLock = null;

    private void releaseSyncExecuteLock() {
        CountDownLatch countDownLatch = this.mSyncExecuteLock;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            this.mSyncExecuteLock = null;
        }
    }

    public final void cancel() {
        releaseSyncExecuteLock();
        setOnResponseListener((ResponseListener) null);
        Request<T> request = this.mRequest;
        if (request != null) {
            request.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Request<T> createVolleyRequest(Response.Listener<T> listener, Response.ErrorListener errorListener);

    public void execute() {
        this.mRequest = createVolleyRequest(this, this);
        Request<T> request = this.mRequest;
        if (request != null) {
            request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
            this.mRequest.setShouldCache(isUseCache());
            Object tag = getTag();
            if (this.mRequest.getTag() == null && tag != null) {
                this.mRequest.setTag(tag);
            }
            HttpManager.getInstance().addToRequestQueue(this.mRequest);
        }
    }

    public Object[] executeSync() throws RequestError {
        if (Looper.myLooper() == Looper.getMainLooper() || Looper.myLooper() == ThreadManager.getRequestThreadLooper()) {
            throw new RuntimeException("executeSync could not call on main thread or request thread.");
        }
        this.mSyncExecuteLock = new CountDownLatch(1);
        execute();
        try {
            if (this.mSyncExecuteLock != null) {
                this.mSyncExecuteLock.await();
            }
        } catch (InterruptedException unused) {
        }
        if (this.mRequestError == null) {
            return this.mResponse;
        }
        throw this.mRequestError;
    }

    /* access modifiers changed from: protected */
    public final void handleError(ErrorCode errorCode, String str, Object obj) {
        this.mCacheExpires = -1;
        onRequestError(errorCode, str, obj);
    }

    /* access modifiers changed from: protected */
    public abstract void handleResponse(T t);

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    public final void onErrorResponse(VolleyError volleyError) {
        ErrorCode errorCode = ErrorCode.NET_ERROR;
        if (volleyError instanceof RequestError) {
            errorCode = ((RequestError) volleyError).getErrorCode();
        }
        ? cause = volleyError.getCause();
        if (cause != 0) {
            volleyError = cause;
        }
        handleError(errorCode, volleyError.getMessage(), volleyError);
        releaseSyncExecuteLock();
    }

    public final void onResponse(T t) {
        handleResponse(t);
        releaseSyncExecuteLock();
        Request<T> request = this.mRequest;
        if (request instanceof Cacheable) {
            Cacheable cacheable = (Cacheable) request;
            if (this.mCacheExpires > 0 && !cacheable.isFromCache()) {
                HttpManager.getInstance().putToCache(this.mRequest.getCacheKey(), cacheable.getData(), this.mCacheExpires, this.mCacheSoftTtl);
            }
        }
    }
}
