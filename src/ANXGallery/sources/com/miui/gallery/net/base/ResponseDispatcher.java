package com.miui.gallery.net.base;

import android.os.Handler;
import android.os.Looper;

public class ResponseDispatcher {
    private Handler mHandler = null;
    /* access modifiers changed from: private */
    public ResponseListener mOnResponseListener;

    public ResponseDispatcher(ResponseListener responseListener) {
        this.mOnResponseListener = responseListener;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void onResponse(final Object... objArr) {
        ResponseListener responseListener = this.mOnResponseListener;
        if (responseListener == null) {
            return;
        }
        if (responseListener instanceof ResponseHandler) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    try {
                        ResponseDispatcher.this.mOnResponseListener.onResponse(objArr);
                    } catch (Exception unused) {
                        ResponseDispatcher.this.onResponseError(ErrorCode.DATA_BIND_ERROR, (String) null, (Object) null);
                    }
                }
            });
            return;
        }
        try {
            responseListener.onResponse(objArr);
        } catch (Exception unused) {
            onResponseError(ErrorCode.DATA_BIND_ERROR, (String) null, (Object) null);
        }
    }

    public void onResponseError(final ErrorCode errorCode, final String str, final Object obj) {
        ResponseListener responseListener = this.mOnResponseListener;
        if (responseListener == null) {
            return;
        }
        if (responseListener instanceof ResponseHandler) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ResponseDispatcher.this.mOnResponseListener.onResponseError(errorCode, str, obj);
                }
            });
        } else {
            responseListener.onResponseError(errorCode, str, obj);
        }
    }
}
