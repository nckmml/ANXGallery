package com.miui.gallery.cloud.base;

import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.xiaomi.opensdk.exception.RetriableException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.net.ssl.SSLException;
import org.apache.http.conn.ConnectTimeoutException;

public class RetryRequestHelper {

    /* renamed from: com.miui.gallery.cloud.base.RetryRequestHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode = new int[GallerySyncCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode[GallerySyncCode.RETRY_ERROR.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode[GallerySyncCode.NEED_RE_REQUEST.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode[GallerySyncCode.TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static boolean isRetriableErrorCode(GallerySyncCode gallerySyncCode) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode[gallerySyncCode.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }

    public static boolean isRetriableException(Exception exc) {
        return (exc instanceof ConnectException) || (exc instanceof ConnectTimeoutException) || (exc instanceof SocketException) || (exc instanceof SocketTimeoutException) || (exc instanceof UnknownHostException) || (exc instanceof SSLException) || (exc instanceof RetriableException);
    }

    public static <T> GallerySyncResult<T> retryTask(SyncTask<T> syncTask) {
        GallerySyncResult.Builder builder;
        GallerySyncResult<T> gallerySyncResult;
        int i = 0;
        GallerySyncResult<T> gallerySyncResult2 = null;
        while (i < 3) {
            try {
                gallerySyncResult2 = syncTask.run();
                if (gallerySyncResult2.code == GallerySyncCode.OK) {
                    if (gallerySyncResult2 == null) {
                        builder = new GallerySyncResult.Builder();
                    }
                    gallerySyncResult = gallerySyncResult2;
                    statResult(syncTask.getIdentifier(), gallerySyncResult, i);
                    return gallerySyncResult2;
                } else if (!isRetriableErrorCode(gallerySyncResult2.code) || i >= 2) {
                    if (gallerySyncResult2 == null) {
                        builder = new GallerySyncResult.Builder();
                    }
                    gallerySyncResult = gallerySyncResult2;
                    statResult(syncTask.getIdentifier(), gallerySyncResult, i);
                    return gallerySyncResult2;
                } else {
                    if (gallerySyncResult2.retryAfter > 0) {
                        Log.w("RetryRequestHelper", "%s retry after %s", syncTask.getIdentifier(), Long.valueOf(gallerySyncResult2.retryAfter));
                        ThreadManager.sleepThread(Math.min(gallerySyncResult2.retryAfter, 30) * 1000);
                    } else {
                        Log.d("RetryRequestHelper", "%s return %s, retry", syncTask.getIdentifier(), gallerySyncResult2.code);
                    }
                    i++;
                }
                gallerySyncResult = builder.setCode(GallerySyncCode.UNKNOWN).build();
                statResult(syncTask.getIdentifier(), gallerySyncResult, i);
                return gallerySyncResult2;
            } catch (Exception e) {
                Log.w("RetryRequestHelper", (Throwable) e);
                gallerySyncResult2 = new GallerySyncResult.Builder().setCode(GallerySyncCode.UNKNOWN).setException(e).build();
                if (!isRetriableException(e)) {
                    break;
                }
            } catch (Throwable th) {
                if (gallerySyncResult2 == null) {
                    gallerySyncResult2 = new GallerySyncResult.Builder().setCode(GallerySyncCode.UNKNOWN).build();
                }
                statResult(syncTask.getIdentifier(), gallerySyncResult2, i);
                throw th;
            }
        }
        if (gallerySyncResult2 == null) {
            gallerySyncResult2 = new GallerySyncResult.Builder().setCode(GallerySyncCode.UNKNOWN).build();
        }
        statResult(syncTask.getIdentifier(), gallerySyncResult2, i);
        return gallerySyncResult2;
    }

    private static void statResult(String str, GallerySyncResult gallerySyncResult, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("request", str);
        hashMap.put("code", gallerySyncResult.code.name());
        if (gallerySyncResult.exception != null) {
            hashMap.put("exception", gallerySyncResult.exception.getMessage());
        }
        hashMap.put("retryTimes", String.valueOf(i));
        GallerySamplingStatHelper.recordCountEvent("Sync", "sync_request_result", hashMap);
    }
}
