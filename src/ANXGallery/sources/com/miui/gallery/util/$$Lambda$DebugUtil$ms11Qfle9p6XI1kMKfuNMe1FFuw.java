package com.miui.gallery.util;

import com.miui.gallery.threadpool.ThreadPool;

/* renamed from: com.miui.gallery.util.-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw implements ThreadPool.Job {
    public static final /* synthetic */ $$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw INSTANCE = new $$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw();

    private /* synthetic */ $$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw() {
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return DebugUtil.doExportDB();
    }
}
