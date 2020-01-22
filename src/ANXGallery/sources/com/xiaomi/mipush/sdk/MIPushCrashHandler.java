package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.SharedPrefsCompat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.OnlineConfig;
import com.xiaomi.xmpush.thrift.ConfigKey;
import java.lang.Thread;

class MIPushCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String[] crashFilterByPkgName = {"com.xiaomi.channel.commonutils", "com.xiaomi.common.logger", "com.xiaomi.measite.smack", "com.xiaomi.metoknlp", "com.xiaomi.mipush.sdk", "com.xiaomi.network", "com.xiaomi.push", "com.xiaomi.slim", "com.xiaomi.smack", "com.xiaomi.stats", "com.xiaomi.tinyData", "com.xiaomi.xmpush.thrift", "com.xiaomi.clientreport"};
    /* access modifiers changed from: private */
    public static final Object mObject = new Object();
    /* access modifiers changed from: private */
    public Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private SharedPreferences mSharedPreference;

    public MIPushCrashHandler(Context context) {
        this(context, Thread.getDefaultUncaughtExceptionHandler());
    }

    public MIPushCrashHandler(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.mContext = context;
        this.mDefaultCrashHandler = uncaughtExceptionHandler;
    }

    private boolean canUploadCrash() {
        this.mSharedPreference = this.mContext.getSharedPreferences("mipush_extra", 4);
        if (Network.isUsingMobileDataConnection(this.mContext)) {
            if (!OnlineConfig.getInstance(this.mContext).getBooleanValue(ConfigKey.Crash4GUploadSwitch.getValue(), true)) {
                return false;
            }
            return ((float) Math.abs((System.currentTimeMillis() / 1000) - this.mSharedPreference.getLong("last_crash_upload_time_stamp", 0))) >= ((float) Math.max(3600, OnlineConfig.getInstance(this.mContext).getIntValue(ConfigKey.Crash4GUploadFrequency.getValue(), 3600))) * 0.9f;
        } else if (!Network.isWIFIConnected(this.mContext)) {
            return true;
        } else {
            return Math.abs((System.currentTimeMillis() / 1000) - this.mSharedPreference.getLong("last_crash_upload_time_stamp", 0)) >= ((long) Math.max(60, OnlineConfig.getInstance(this.mContext).getIntValue(ConfigKey.CrashWIFIUploadFrequency.getValue(), 1800)));
        }
    }

    private boolean filterCrashByPkgName(boolean z, String str) {
        for (String contains : crashFilterByPkgName) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return z;
    }

    private String getCrashContent(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder(th.toString());
        sb.append("\r\n");
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTrace) {
            String stackTraceElement2 = stackTraceElement.toString();
            z = filterCrashByPkgName(z, stackTraceElement2);
            sb.append(stackTraceElement2);
            sb.append("\r\n");
        }
        return z ? sb.toString() : "";
    }

    private String getCrashSummary(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(3, stackTrace.length); i++) {
            sb.append(stackTrace[i].toString() + "\r\n");
        }
        String sb2 = sb.toString();
        return TextUtils.isEmpty(sb2) ? "" : XMStringUtils.getMd5Digest(sb2);
    }

    private void handleCrash(Throwable th) {
        String crashContent = getCrashContent(th);
        if (!TextUtils.isEmpty(crashContent)) {
            String crashSummary = getCrashSummary(th);
            if (!TextUtils.isEmpty(crashSummary)) {
                CrashStorage.getInstance(this.mContext).writeCrash2File(crashContent, crashSummary);
                if (canUploadCrash()) {
                    uploadCrash();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateUploadTimeStamp() {
        this.mSharedPreference = this.mContext.getSharedPreferences("mipush_extra", 4);
        SharedPreferences.Editor edit = this.mSharedPreference.edit();
        edit.putLong("last_crash_upload_time_stamp", System.currentTimeMillis() / 1000);
        SharedPrefsCompat.apply(edit);
    }

    private void uploadCrash() {
        ScheduledJobManager.getInstance(this.mContext).addOneShootJob(new Runnable() {
            /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
                java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
                	at java.util.ArrayList.get(ArrayList.java:433)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
                	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
                	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
                	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
                */
            public void run() {
                /*
                    r9 = this;
                    r0 = 0
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r1 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    android.content.Context r1 = r1.mContext     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    com.xiaomi.mipush.sdk.CrashStorage r1 = com.xiaomi.mipush.sdk.CrashStorage.getInstance(r1)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.util.ArrayList r1 = r1.getAllCrashFile()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    if (r1 == 0) goto L_0x00e0
                    int r2 = r1.size()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r3 = 1
                    if (r2 >= r3) goto L_0x001a
                    goto L_0x00e0
                L_0x001a:
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r2 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    android.content.Context r2 = r2.mContext     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r3 = "C100000"
                    java.util.HashMap r2 = com.xiaomi.mipush.sdk.MiPushUtils.collectDeviceInfo(r2, r3)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r3 = 0
                L_0x0027:
                    int r4 = r1.size()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    if (r3 >= r4) goto L_0x00ed
                    java.lang.Object r4 = r1.get(r3)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.io.File r4 = (java.io.File) r4     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r5 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    android.content.Context r5 = r5.mContext     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    com.xiaomi.mipush.sdk.CrashStorage r5 = com.xiaomi.mipush.sdk.CrashStorage.getInstance(r5)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r5 = r5.getCrashSummary(r4)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r7.<init>()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r8 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    android.content.Context r8 = r8.mContext     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.io.File r8 = r8.getFilesDir()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r8 = "/crash"
                    r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r8 = "/"
                    r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r8 = r4.getName()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r8 = ".zip"
                    r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    r6.<init>(r7)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    com.xiaomi.channel.commonutils.file.IOUtils.zip(r6, r4)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    boolean r0 = r6.exists()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    if (r0 == 0) goto L_0x00d4
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r0.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r7 = "https://api.xmpush.xiaomi.com/upload/crash_log?file="
                    r0.append(r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r7 = r6.getName()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r0.append(r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r7 = "file"
                    com.xiaomi.channel.commonutils.network.Network.uploadFile(r0, r2, r6, r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r7.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r8 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    android.content.Context r8 = r8.mContext     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.io.File r8 = r8.getFilesDir()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r7.append(r8)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r8 = "/crash"
                    r7.append(r8)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r8.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r5 = ":"
                    r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r5 = "0"
                    r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    java.lang.String r5 = r8.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r0.<init>(r7, r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r4.renameTo(r0)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    com.xiaomi.mipush.sdk.MIPushCrashHandler r0 = com.xiaomi.mipush.sdk.MIPushCrashHandler.this     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    r0.updateUploadTimeStamp()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                    goto L_0x00d9
                L_0x00d4:
                    java.lang.String r0 = "zip crash file failed"
                    com.xiaomi.channel.commonutils.logger.MyLog.w(r0)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
                L_0x00d9:
                    int r3 = r3 + 1
                    r0 = r6
                    goto L_0x0027
                L_0x00de:
                    r0 = move-exception
                    goto L_0x00e9
                L_0x00e0:
                    java.lang.String r1 = "no crash file to upload"
                    com.xiaomi.channel.commonutils.logger.MyLog.w(r1)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
                    return
                L_0x00e6:
                    r1 = move-exception
                    r6 = r0
                    r0 = r1
                L_0x00e9:
                    com.xiaomi.channel.commonutils.logger.MyLog.e((java.lang.Throwable) r0)
                L_0x00ec:
                    r0 = r6
                L_0x00ed:
                    if (r0 == 0) goto L_0x0100
                    boolean r1 = r0.exists()
                    if (r1 == 0) goto L_0x0100
                    boolean r0 = r0.delete()
                    if (r0 != 0) goto L_0x0100
                    java.lang.String r0 = "delete zip crash file failed"
                    com.xiaomi.channel.commonutils.logger.MyLog.w(r0)
                L_0x0100:
                    java.lang.Object r0 = com.xiaomi.mipush.sdk.MIPushCrashHandler.mObject
                    monitor-enter(r0)
                    java.lang.Object r1 = com.xiaomi.mipush.sdk.MIPushCrashHandler.mObject     // Catch:{ all -> 0x010e }
                    r1.notifyAll()     // Catch:{ all -> 0x010e }
                    monitor-exit(r0)     // Catch:{ all -> 0x010e }
                    return
                L_0x010e:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x010e }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.MIPushCrashHandler.AnonymousClass1.run():void");
            }
        });
    }

    public void uncaughtException(Thread thread, Throwable th) {
        handleCrash(th);
        synchronized (mObject) {
            try {
                mObject.wait(3000);
            } catch (InterruptedException e) {
                MyLog.e((Throwable) e);
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultCrashHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
