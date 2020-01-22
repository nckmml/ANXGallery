package com.xiaomi.push.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MIPushLog2File implements LoggerInterface {
    public static String MIPUSH_LOG_PATH = "/MiPushLog";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");
    /* access modifiers changed from: private */
    public static List<Pair<String, Throwable>> logs = Collections.synchronizedList(new ArrayList());
    private static SerializedAsyncTaskProcessor mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
    private String mSDCardRootPath = "";
    /* access modifiers changed from: private */
    public String mTag;
    private Context sAppContext;

    public MIPushLog2File(Context context) {
        this.sAppContext = context;
        if (context.getApplicationContext() != null) {
            this.sAppContext = context.getApplicationContext();
        }
        this.mTag = this.sAppContext.getPackageName();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedWriter, java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01a1 A[SYNTHETIC, Splitter:B:104:0x01a1] */
    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0157 A[SYNTHETIC, Splitter:B:74:0x0157] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0175 A[SYNTHETIC, Splitter:B:86:0x0175] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0183 A[SYNTHETIC, Splitter:B:92:0x0183] */
    public void writeLog2File() {
        RandomAccessFile randomAccessFile;
        FileLock fileLock;
        FileLock fileLock2;
        FileLock lock;
        File externalFilesDir;
        ? r2 = 0;
        try {
            if (TextUtils.isEmpty(this.mSDCardRootPath) && (externalFilesDir = this.sAppContext.getExternalFilesDir(r2)) != null) {
                this.mSDCardRootPath = externalFilesDir.getAbsolutePath() + "";
            }
            File file = new File(this.mSDCardRootPath + MIPUSH_LOG_PATH);
            if ((!file.exists() || !file.isDirectory()) && !file.mkdirs()) {
                Log.w(this.mTag, "Create mipushlog directory fail.");
                return;
            }
            File file2 = new File(file, "log.lock");
            if (!file2.exists() || file2.isDirectory()) {
                file2.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file2, "rw");
            try {
                lock = randomAccessFile.getChannel().lock();
            } catch (Exception e) {
                e = e;
                fileLock2 = r2;
                try {
                    Log.e(this.mTag, "", e);
                    if (r2 != 0) {
                    }
                    try {
                        fileLock2.release();
                    } catch (IOException e2) {
                        Log.e(this.mTag, "", e2);
                    }
                    if (randomAccessFile == null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    fileLock = fileLock2;
                    r2 = r2;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (IOException e3) {
                            Log.e(this.mTag, "", e3);
                        }
                    }
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                            Log.e(this.mTag, "", e4);
                        }
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e5) {
                            Log.e(this.mTag, "", e5);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileLock = r2;
                if (r2 != 0) {
                }
                fileLock.release();
                if (randomAccessFile != null) {
                }
                throw th;
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                while (!logs.isEmpty()) {
                    try {
                        Pair remove = logs.remove(0);
                        String str = (String) remove.first;
                        if (remove.second != null) {
                            str = (str + "\n") + Log.getStackTraceString((Throwable) remove.second);
                        }
                        bufferedWriter.write(str + "\n");
                    } catch (Exception e6) {
                        e = e6;
                        r2 = bufferedWriter;
                        fileLock2 = lock;
                        Log.e(this.mTag, "", e);
                        if (r2 != 0) {
                        }
                        fileLock2.release();
                        if (randomAccessFile == null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        r2 = bufferedWriter;
                        fileLock = lock;
                        if (r2 != 0) {
                        }
                        fileLock.release();
                        if (randomAccessFile != null) {
                        }
                        throw th;
                    }
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                File file3 = new File(file, "log1.txt");
                if (file3.length() >= 1048576) {
                    File file4 = new File(file, "log0.txt");
                    if (file4.exists() && file4.isFile()) {
                        file4.delete();
                    }
                    file3.renameTo(file4);
                }
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e7) {
                        Log.e(this.mTag, "", e7);
                    }
                }
                if (lock != null && lock.isValid()) {
                    try {
                        lock.release();
                    } catch (IOException e8) {
                        Log.e(this.mTag, "", e8);
                    }
                }
                try {
                    randomAccessFile.close();
                } catch (IOException e9) {
                    Log.e(this.mTag, "", e9);
                }
            } catch (Exception e10) {
                e = e10;
                fileLock2 = lock;
                Log.e(this.mTag, "", e);
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e11) {
                        Log.e(this.mTag, "", e11);
                    }
                }
                if (fileLock2 != null && fileLock2.isValid()) {
                    fileLock2.release();
                }
                if (randomAccessFile == null) {
                    randomAccessFile.close();
                }
            }
        } catch (Exception e12) {
            e = e12;
            FileLock fileLock3 = r2;
            randomAccessFile = fileLock3;
            fileLock2 = fileLock3;
            Log.e(this.mTag, "", e);
            if (r2 != 0) {
            }
            fileLock2.release();
            if (randomAccessFile == null) {
            }
        } catch (Throwable th4) {
            th = th4;
            FileLock fileLock4 = r2;
            randomAccessFile = fileLock4;
            fileLock = fileLock4;
            if (r2 != 0) {
            }
            fileLock.release();
            if (randomAccessFile != null) {
            }
            throw th;
        }
    }

    public final void log(String str) {
        log(str, (Throwable) null);
    }

    public final void log(String str, Throwable th) {
        logs.add(new Pair(String.format("%1$s %2$s %3$s ", new Object[]{dateFormatter.format(new Date()), this.mTag, str}), th));
        mAsyncProcessor.addNewTask(new SerializedAsyncTaskProcessor.SerializedAsyncTask() {
            public void process() {
                if (!MIPushLog2File.logs.isEmpty()) {
                    try {
                        if (!SDCardUtils.isSDCardUseful()) {
                            Log.w(MIPushLog2File.this.mTag, "SDCard is unavailable.");
                        } else {
                            MIPushLog2File.this.writeLog2File();
                        }
                    } catch (Exception e) {
                        Log.e(MIPushLog2File.this.mTag, "", e);
                    }
                }
            }
        });
    }
}
