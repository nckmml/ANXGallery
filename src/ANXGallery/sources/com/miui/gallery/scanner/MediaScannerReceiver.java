package com.miui.gallery.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.util.Pair;
import com.android.internal.EnvironmentCompat;
import com.miui.gallery.threadpool.GallerySchedulers;
import com.miui.gallery.util.ExtraTextUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ReceiverUtils;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.StorageUtils;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.processors.PublishProcessor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MediaScannerReceiver extends BroadcastReceiver {
    private static Disposable sDisposable;
    private static AtomicInteger sIdleCount = new AtomicInteger(0);
    private static final PublishProcessor<Pair<String, Uri>> sPublishProcessor = PublishProcessor.create();
    private static int sRegistrantCount;

    private static void dispose() {
        Disposable disposable = sDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            sDisposable.dispose();
            Log.d("MediaScannerReceiver", "dispose");
        }
    }

    private static void ensureSubscribed() {
        Disposable disposable = sDisposable;
        if (disposable == null || disposable.isDisposed()) {
            Log.d("MediaScannerReceiver", "subscribe");
            sIdleCount.set(0);
            sDisposable = sPublishProcessor.buffer(500, TimeUnit.MILLISECONDS).onBackpressureBuffer(100, (Action) null, BackpressureOverflowStrategy.DROP_OLDEST).observeOn(GallerySchedulers.misc()).takeUntil($$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc.INSTANCE).filter($$Lambda$MediaScannerReceiver$S9FS9V4wCWdKBvXqW3NQvEWqh0.INSTANCE).subscribe($$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk.INSTANCE);
        }
    }

    private static void handleBufferedUris(String str, List<Uri> list) {
        Log.d("MediaScannerReceiver", "handleBufferedUris, action: %s, count: %d", str, Integer.valueOf(list.size()));
        if ("android.intent.action.MEDIA_SCANNER_FINISHED".equals(str)) {
            handleMediaScannerFinished(StaticContext.sGetAndroidContext(), list);
        } else if ("android.intent.action.MEDIA_SCANNER_SCAN_FILE".equals(str)) {
            handleMediaScannerScanFile(StaticContext.sGetAndroidContext(), list);
        }
    }

    private static void handleMediaScannerFinished(Context context, List<Uri> list) {
        if (MiscUtil.isValid(list)) {
            ArrayList arrayList = new ArrayList(list.size());
            for (Uri next : list) {
                if (next != null && "file".equals(next.getScheme())) {
                    String path = next.getPath();
                    if (!TextUtils.isEmpty(path) && new File(path).isDirectory()) {
                        arrayList.add(path);
                    }
                }
            }
            if (MiscUtil.isValid(arrayList)) {
                MediaScannerUtil.scanDirectories(context.getApplicationContext(), arrayList, 6, true, false);
            }
        }
    }

    private static void handleMediaScannerScanFile(Context context, List<Uri> list) {
        if (MiscUtil.isValid(list)) {
            ArrayList arrayList = new ArrayList(list.size());
            for (Uri next : list) {
                if (next != null && "file".equals(next.getScheme())) {
                    String path = next.getPath();
                    if (!TextUtils.isEmpty(path)) {
                        String path2 = Environment.getExternalStorageDirectory().getPath();
                        String legacyExternalStorageDirectory = EnvironmentCompat.getLegacyExternalStorageDirectory();
                        try {
                            String canonicalPath = new File(path).getCanonicalPath();
                            if (ExtraTextUtils.startsWithIgnoreCase(canonicalPath, legacyExternalStorageDirectory)) {
                                canonicalPath = path2 + canonicalPath.substring(legacyExternalStorageDirectory.length());
                            }
                            if (shouldHandlePath(context.getApplicationContext(), FileUtils.getParentFolderPath(canonicalPath))) {
                                Log.i("MediaScannerReceiver", "ACTION_MEDIA_SCANNER_SCAN_FILE %s", (Object) canonicalPath);
                                arrayList.add(canonicalPath);
                            } else {
                                Log.i("MediaScannerReceiver", "ACTION_MEDIA_SCANNER_SCAN_FILE but not trigger %s", (Object) canonicalPath);
                            }
                        } catch (IOException unused) {
                            Log.w("MediaScannerReceiver", "can't canonicalize " + path);
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                MediaScannerUtil.scanFiles(context.getApplicationContext(), arrayList, 2);
            }
        }
    }

    static /* synthetic */ boolean lambda$ensureSubscribed$25(List list) throws Exception {
        return list.size() == 0 && sIdleCount.incrementAndGet() >= 20;
    }

    static /* synthetic */ boolean lambda$ensureSubscribed$26(List list) throws Exception {
        return list.size() > 0;
    }

    static /* synthetic */ void lambda$ensureSubscribed$27(List list) throws Exception {
        sIdleCount.set(0);
        ArrayMap arrayMap = new ArrayMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            List list2 = (List) arrayMap.get(pair.first);
            if (list2 == null) {
                list2 = new LinkedList();
                arrayMap.put(pair.first, list2);
            }
            list2.add(pair.second);
        }
        for (Map.Entry entry : arrayMap.entrySet()) {
            handleBufferedUris((String) entry.getKey(), (List) entry.getValue());
        }
    }

    public static void register(Context context, MediaScannerReceiver mediaScannerReceiver) {
        sRegistrantCount++;
        ReceiverUtils.registerReceiver(context, mediaScannerReceiver, 0, "file", "android.intent.action.MEDIA_MOUNTED", "android.intent.action.MEDIA_UNMOUNTED", "android.intent.action.MEDIA_SCANNER_FINISHED", "android.intent.action.MEDIA_SCANNER_SCAN_FILE");
    }

    private static boolean shouldHandlePath(Context context, String str) {
        return !ExtraTextUtils.startsWithIgnoreCase(StorageUtils.getRelativePath(context, str), "MIUI/Gallery/cloud");
    }

    public static void unregister(Context context, MediaScannerReceiver mediaScannerReceiver) {
        int i = sRegistrantCount - 1;
        sRegistrantCount = i;
        if (i <= 0) {
            dispose();
        }
        ReceiverUtils.safeUnregisterReceiver(context, mediaScannerReceiver);
    }

    public void onReceive(Context context, Intent intent) {
        if (!intent.getBooleanExtra("com.miui.gallery.extra.trigger_scan", false)) {
            String action = intent.getAction();
            Uri data = intent.getData();
            Log.d("MediaScannerReceiver", "Broadcast received, action: [%s], data uri: [%s]", action, data);
            if ("android.intent.action.MEDIA_SCANNER_FINISHED".equals(action) || "android.intent.action.MEDIA_SCANNER_SCAN_FILE".equals(action)) {
                if (data != null) {
                    ensureSubscribed();
                    sPublishProcessor.onNext(new Pair(action, data));
                }
            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                MediaScannerUtil.cleanup(context.getApplicationContext());
            } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                String path = data != null ? data.getPath() : null;
                if (!TextUtils.isEmpty(path)) {
                    MediaScannerUtil.scanDirectory(context.getApplicationContext(), path, 6, true, true);
                }
            }
        }
    }
}
