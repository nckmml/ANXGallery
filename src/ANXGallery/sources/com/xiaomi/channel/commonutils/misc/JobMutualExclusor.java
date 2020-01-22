package com.xiaomi.channel.commonutils.misc;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.AppInfoUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;

public class JobMutualExclusor {
    private static final String COMMON_PATH = (Environment.getExternalStorageDirectory().getPath() + "/mipush/");
    private static final String LAST_COLLECT_FILE_PATH = (COMMON_PATH + "lcfp");
    private static final String LAST_COLLECT_FILE_PATH_LOCK = (COMMON_PATH + "lcfp.lock");

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static boolean checkPeriodAndRecordWithFileLock(Context context, String str, long j) {
        ? r2;
        RandomAccessFile randomAccessFile;
        FileLock fileLock = null;
        try {
            File file = new File(LAST_COLLECT_FILE_PATH_LOCK);
            IOUtils.createFileQuietly(file);
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            try {
                FileLock lock = randomAccessFile2.getChannel().lock();
                boolean checkPeriodAndRecordWorking = checkPeriodAndRecordWorking(context, str, j);
                if (lock != null && lock.isValid()) {
                    try {
                        lock.release();
                    } catch (IOException unused) {
                    }
                }
                IOUtils.closeQuietly(randomAccessFile2);
                return checkPeriodAndRecordWorking;
            } catch (IOException e) {
                e = e;
                randomAccessFile = randomAccessFile2;
                try {
                    e.printStackTrace();
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused2) {
                        }
                    }
                    IOUtils.closeQuietly(randomAccessFile);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    r2 = randomAccessFile;
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused3) {
                        }
                    }
                    IOUtils.closeQuietly(r2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            randomAccessFile = fileLock;
            e.printStackTrace();
            fileLock.release();
            IOUtils.closeQuietly(randomAccessFile);
            return true;
        } catch (Throwable th2) {
            th = th2;
            r2 = fileLock;
            fileLock.release();
            IOUtils.closeQuietly(r2);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e3 A[Catch:{ IOException -> 0x00f3 }, LOOP:1: B:53:0x00dd->B:55:0x00e3, LOOP_END] */
    private static boolean checkPeriodAndRecordWorking(Context context, String str, long j) {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        if (Build.VERSION.SDK_INT < 23) {
            Context context2 = context;
        } else if (!AppInfoUtils.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return true;
        }
        File file = new File(LAST_COLLECT_FILE_PATH);
        ArrayList<String> arrayList = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = str + ":" + context.getPackageName() + "," + currentTimeMillis;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            IOUtils.closeQuietly(bufferedReader);
                            break;
                        }
                        String[] split = readLine.split(":");
                        if (split.length == 2) {
                            if (TextUtils.equals(split[0], String.valueOf(str))) {
                                String[] split2 = split[1].split(",");
                                if (split2.length == 2) {
                                    long parseLong = Long.parseLong(split2[1]);
                                    if (!TextUtils.equals(split2[0], context.getPackageName()) && ((float) Math.abs(currentTimeMillis - parseLong)) < ((float) (1000 * j)) * 0.9f) {
                                        IOUtils.closeQuietly(bufferedReader);
                                        return false;
                                    }
                                }
                            } else {
                                arrayList.add(readLine);
                            }
                        }
                    } catch (Exception unused) {
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(bufferedReader);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                bufferedReader2 = null;
                try {
                    arrayList.clear();
                    IOUtils.closeQuietly(bufferedReader2);
                    arrayList.add(str2);
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    while (r0.hasNext()) {
                    }
                    IOUtils.closeQuietly(bufferedWriter);
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    IOUtils.closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                IOUtils.closeQuietly(bufferedReader);
                throw th;
            }
        } else if (!IOUtils.createFileQuietly(file)) {
            return true;
        }
        arrayList.add(str2);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            try {
                for (String write : arrayList) {
                    bufferedWriter.write(write);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e = e;
                try {
                    MyLog.e(e.toString());
                    IOUtils.closeQuietly(bufferedWriter);
                    return true;
                } catch (Throwable th4) {
                    th = th4;
                    IOUtils.closeQuietly(bufferedWriter);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            bufferedWriter = null;
            MyLog.e(e.toString());
            IOUtils.closeQuietly(bufferedWriter);
            return true;
        } catch (Throwable th5) {
            th = th5;
            bufferedWriter = null;
            IOUtils.closeQuietly(bufferedWriter);
            throw th;
        }
        IOUtils.closeQuietly(bufferedWriter);
        return true;
    }
}
