package com.xiaomi.mistatistic.sdk;

import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.xiaomi.mistatistic.sdk.controller.i;
import com.xiaomi.mistatistic.sdk.controller.j;
import com.xiaomi.mistatistic.sdk.controller.t;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import com.xiaomi.mistatistic.sdk.network.c;
import com.xiaomi.mistatistic.sdk.network.d;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class URLStatsRecorder {
    /* access modifiers changed from: private */
    public static final Map<String, URLStreamHandler> a = new HashMap();
    private static final List<String> b = new ArrayList();
    private static final List<String> c = new ArrayList();
    private static final List<String> d = new ArrayList();
    private static final List<String> e = new ArrayList();
    private static Boolean f = null;

    static class a extends URLStreamHandler {
        private URLStreamHandler a;

        public a(URLStreamHandler uRLStreamHandler) {
            this.a = uRLStreamHandler;
        }

        /* access modifiers changed from: protected */
        public URLConnection openConnection(URL url) throws IOException {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Method declaredMethod = URLStreamHandler.class.getDeclaredMethod("openConnection", new Class[]{URL.class});
                declaredMethod.setAccessible(true);
                URLConnection uRLConnection = (URLConnection) declaredMethod.invoke(this.a, new Object[]{url});
                if (uRLConnection instanceof HttpsURLConnection) {
                    d dVar = new d((HttpsURLConnection) uRLConnection);
                    dVar.a(elapsedRealtime);
                    return dVar;
                } else if (!(uRLConnection instanceof HttpURLConnection)) {
                    return uRLConnection;
                } else {
                    c cVar = new c((HttpURLConnection) uRLConnection);
                    cVar.a(elapsedRealtime);
                    return cVar;
                }
            } catch (Exception e) {
                i.a().a(new HttpEvent(url.toString(), e.getClass().getSimpleName()));
                throw new IOException();
            }
        }

        /* access modifiers changed from: protected */
        public URLConnection openConnection(URL url, Proxy proxy) throws IOException {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Method declaredMethod = URLStreamHandler.class.getDeclaredMethod("openConnection", new Class[]{URL.class, Proxy.class});
                declaredMethod.setAccessible(true);
                URLConnection uRLConnection = (URLConnection) declaredMethod.invoke(this.a, new Object[]{url, proxy});
                if (uRLConnection instanceof HttpsURLConnection) {
                    d dVar = new d((HttpsURLConnection) uRLConnection);
                    dVar.a(elapsedRealtime);
                    return dVar;
                } else if (!(uRLConnection instanceof HttpURLConnection)) {
                    return uRLConnection;
                } else {
                    c cVar = new c((HttpURLConnection) uRLConnection);
                    cVar.a(elapsedRealtime);
                    return cVar;
                }
            } catch (Exception e) {
                i.a().a(new HttpEvent(url.toString(), e.getClass().getSimpleName()));
                throw new IOException();
            }
        }
    }

    static {
        b.add("file");
        b.add("ftp");
        b.add("http");
        b.add("https");
        b.add("jar");
        c.add("http");
    }

    public static void addHttpEvent(HttpEvent httpEvent) {
        if (!t.b()) {
            Log.w("MI_STAT", "The statistics is disabled.");
        } else if (MiStatInterface.checkInitialized()) {
            if (httpEvent == null) {
                Log.e(j.b(), "addHttpEvent: httpEvent is null.");
            } else {
                i.a().a(httpEvent);
            }
        }
    }

    public static boolean enableAutoRecord() {
        if (!t.b()) {
            Log.w("MI_STAT", "The statistics is disabled.");
            return false;
        } else if (!MiStatInterface.checkInitialized()) {
            return false;
        } else {
            Boolean bool = f;
            if (bool != null) {
                return bool.booleanValue();
            }
            try {
                if (!b.isEmpty()) {
                    for (String url : b) {
                        new URL(url, "www.xiaomi.com", "");
                    }
                    Field declaredField = Build.VERSION.SDK_INT >= 24 ? URL.class.getDeclaredField("handlers") : URL.class.getDeclaredField("streamHandlers");
                    declaredField.setAccessible(true);
                    Hashtable hashtable = (Hashtable) declaredField.get((Object) null);
                    for (String next : b) {
                        a.put(next, (URLStreamHandler) hashtable.get(next));
                    }
                    URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
                        public URLStreamHandler createURLStreamHandler(String str) {
                            if (URLStatsRecorder.a.containsKey(str)) {
                                return new a((URLStreamHandler) URLStatsRecorder.a.get(str));
                            }
                            return null;
                        }
                    });
                    f = true;
                }
            } catch (Throwable th) {
                f = false;
                j.a("USR", "failed to enable url interceptor", th);
            }
            return f.booleanValue();
        }
    }

    public static boolean isRecordEnabled() {
        Boolean bool = f;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
