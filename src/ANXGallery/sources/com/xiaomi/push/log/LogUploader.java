package com.xiaomi.push.log;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class LogUploader {
    private static volatile LogUploader sInstance;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final ConcurrentLinkedQueue<Task> mTasks = new ConcurrentLinkedQueue<>();

    class CleanUpTask extends Task {
        CleanUpTask() {
            super();
        }

        public void process() {
            LogUploader.this.cleanUp();
        }
    }

    class Task extends SerializedAsyncTaskProcessor.SerializedAsyncTask {
        long timestamp = System.currentTimeMillis();

        Task() {
        }

        public boolean canExcuteNow() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean isExpired() {
            return System.currentTimeMillis() - this.timestamp > 172800000;
        }

        public void process() {
        }
    }

    class UploadTask extends Task {
        File file;
        boolean force;
        int retryNum;
        String token;
        boolean uploaded;
        String url;

        UploadTask(String str, String str2, File file2, boolean z) {
            super();
            this.url = str;
            this.token = str2;
            this.file = file2;
            this.force = z;
        }

        private boolean checkLimit() {
            int i;
            int i2 = 0;
            SharedPreferences sharedPreferences = LogUploader.this.mContext.getSharedPreferences("log.timestamp", 0);
            String string = sharedPreferences.getString("log.requst", "");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject = new JSONObject(string);
                currentTimeMillis = jSONObject.getLong("time");
                i = jSONObject.getInt("times");
            } catch (JSONException unused) {
                i = 0;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
                currentTimeMillis = System.currentTimeMillis();
            } else if (i > 10) {
                return false;
            } else {
                i2 = i;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("time", currentTimeMillis);
                jSONObject2.put("times", i2 + 1);
                sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
            } catch (JSONException e) {
                MyLog.v("JSONException on put " + e.getMessage());
            }
            return true;
        }

        public boolean canExcuteNow() {
            return Network.isWIFIConnected(LogUploader.this.mContext) || (this.force && Network.hasNetwork(LogUploader.this.mContext));
        }

        public void postProcess() {
            if (!this.uploaded) {
                this.retryNum++;
                if (this.retryNum < 3) {
                    LogUploader.this.mTasks.add(this);
                }
            }
            if (this.uploaded || this.retryNum >= 3) {
                this.file.delete();
            }
            LogUploader.this.uploadIfNeed((long) ((1 << this.retryNum) * 1000));
        }

        public void process() {
            try {
                if (checkLimit()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", ServiceConfig.getDeviceUUID());
                    hashMap.put("token", this.token);
                    hashMap.put("net", Network.getActiveConnPoint(LogUploader.this.mContext));
                    Network.uploadFile(this.url, hashMap, this.file, "file");
                }
                this.uploaded = true;
            } catch (IOException unused) {
            }
        }
    }

    private LogUploader(Context context) {
        this.mContext = context;
        this.mTasks.add(new CleanUpTask());
        executeTask(0);
    }

    private void cleanExpiredTask() {
        while (!this.mTasks.isEmpty()) {
            Task peek = this.mTasks.peek();
            if (peek != null) {
                if (peek.isExpired() || this.mTasks.size() > 6) {
                    MyLog.v("remove Expired task");
                    this.mTasks.remove(peek);
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void cleanUp() {
        if (!SDCardUtils.isSDCardBusy() && !SDCardUtils.isSDCardUnavailable()) {
            try {
                File file = new File(this.mContext.getExternalFilesDir((String) null) + "/.logcache");
                if (file.exists() && file.isDirectory()) {
                    for (File delete : file.listFiles()) {
                        delete.delete();
                    }
                }
            } catch (NullPointerException unused) {
            }
        }
    }

    private void executeTask(long j) {
        if (!this.mTasks.isEmpty()) {
            TaskExecutor.execute(new SerializedAsyncTaskProcessor.SerializedAsyncTask() {
                SerializedAsyncTaskProcessor.SerializedAsyncTask current;

                public void postProcess() {
                    SerializedAsyncTaskProcessor.SerializedAsyncTask serializedAsyncTask = this.current;
                    if (serializedAsyncTask != null) {
                        serializedAsyncTask.postProcess();
                    }
                }

                public void process() {
                    Task task = (Task) LogUploader.this.mTasks.peek();
                    if (task != null && task.canExcuteNow()) {
                        if (LogUploader.this.mTasks.remove(task)) {
                            this.current = task;
                        }
                        SerializedAsyncTaskProcessor.SerializedAsyncTask serializedAsyncTask = this.current;
                        if (serializedAsyncTask != null) {
                            serializedAsyncTask.process();
                        }
                    }
                }
            }, j);
        }
    }

    public static LogUploader getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LogUploader.class) {
                if (sInstance == null) {
                    sInstance = new LogUploader(context);
                }
            }
        }
        sInstance.mContext = context;
        return sInstance;
    }

    /* access modifiers changed from: private */
    public void uploadIfNeed(long j) {
        Task peek = this.mTasks.peek();
        if (peek != null && peek.canExcuteNow()) {
            executeTask(j);
        }
    }

    public void checkUpload() {
        cleanExpiredTask();
        uploadIfNeed(0);
    }

    public void upload(String str, String str2, Date date, Date date2, int i, boolean z) {
        final int i2 = i;
        final Date date3 = date;
        final Date date4 = date2;
        final String str3 = str;
        final String str4 = str2;
        final boolean z2 = z;
        this.mTasks.add(new Task() {
            File file;

            public void postProcess() {
                File file2 = this.file;
                if (file2 != null && file2.exists()) {
                    LogUploader.this.mTasks.add(new UploadTask(str3, str4, this.file, z2));
                }
                LogUploader.this.uploadIfNeed(0);
            }

            public void process() {
                if (SDCardUtils.isSDCardUseful()) {
                    try {
                        File file2 = new File(LogUploader.this.mContext.getExternalFilesDir((String) null) + "/.logcache");
                        file2.mkdirs();
                        if (file2.isDirectory()) {
                            LogFilter logFilter = new LogFilter();
                            logFilter.setMaxLen(i2);
                            this.file = logFilter.filter(LogUploader.this.mContext, date3, date4, file2);
                        }
                    } catch (NullPointerException unused) {
                    }
                }
            }
        });
        executeTask(0);
    }
}
