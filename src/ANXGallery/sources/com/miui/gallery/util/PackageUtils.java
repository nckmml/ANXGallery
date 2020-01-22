package com.miui.gallery.util;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.core.util.Pair;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PackageUtils {
    private static final Object CACHE_LOCK = new Object();
    private static volatile LoadingCache<String, Pair<String, Locale>> sAppNamesCache;

    private static void ensureAppNamesCache() {
        if (sAppNamesCache == null) {
            synchronized (CACHE_LOCK) {
                if (sAppNamesCache == null) {
                    sAppNamesCache = CacheBuilder.newBuilder().refreshAfterWrite(2, TimeUnit.MINUTES).expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(200).build(new CacheLoader<String, Pair<String, Locale>>() {
                        public Pair<String, Locale> load(String str) {
                            return Pair.create(PackageUtils.getAppNameByPackage(str), Locale.getDefault());
                        }

                        public ListenableFuture<Pair<String, Locale>> reload(String str, Pair<String, Locale> pair) throws Exception {
                            Preconditions.checkNotNull(str);
                            Preconditions.checkNotNull(pair);
                            Locale locale = Locale.getDefault();
                            if ("MiuiGallery:Absent" == pair.first || !Objects.equals(locale, pair.second)) {
                                Pair<String, Locale> load = load(str);
                                Log.d("PackageUtils", "reload for pkg [%s], oldValue: %s, newValue: %s", str, pair, load);
                                pair = load;
                            }
                            return Futures.immediateFuture(pair);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getAppNameByPackage(String str) {
        try {
            PackageManager packageManager = StaticContext.sGetAndroidContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("PackageUtils", "Package not found: %s", str);
            return "MiuiGallery:Absent";
        } catch (Exception e) {
            Log.e("PackageUtils", (Throwable) e);
            return "MiuiGallery:Absent";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = r4.lastIndexOf(95);
     */
    public static String getAppNameForScreenshot(String str) {
        int lastIndexOf;
        int lastIndexOf2;
        if (TextUtils.isEmpty(str) || !str.startsWith("Screenshot") || (lastIndexOf2 = str.lastIndexOf(46)) <= lastIndexOf) {
            return null;
        }
        String substring = str.substring(lastIndexOf + 1, lastIndexOf2);
        ensureAppNamesCache();
        Pair unchecked = sAppNamesCache.getUnchecked(substring);
        if ("MiuiGallery:Absent" == unchecked.first) {
            Log.d("PackageUtils", "App name of package [%s] is absent", (Object) substring);
            return null;
        }
        Log.d("PackageUtils", "App name of package [%s] is [%s]", substring, unchecked.first);
        return (String) unchecked.first;
    }
}
