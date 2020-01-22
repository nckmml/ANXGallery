package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.clientreport.data.BaseClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerfKVFileHelper {
    private static PerfClientReport buildPerfClientReport(PerfClientReport perfClientReport, String str) {
        long[] parseValueStr;
        if (perfClientReport == null || (parseValueStr = parseValueStr(str)) == null) {
            return null;
        }
        perfClientReport.perfCounts = parseValueStr[0];
        perfClientReport.perfLatencies = parseValueStr[1];
        return perfClientReport;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d3, code lost:
        if (r1 == null) goto L_0x00d8;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00f4  */
    public static List<String> extractToDatas(Context context, String str) {
        FileLock fileLock;
        BufferedReader bufferedReader;
        File file;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        BufferedReader bufferedReader4;
        BufferedReader bufferedReader5;
        FileLock fileLock2;
        RandomAccessFile randomAccessFile;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return arrayList;
        }
        ? r0 = 0;
        try {
            file = new File(str + ".lock");
            try {
                IOUtils.createFileQuietly(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Exception e) {
                e = e;
                bufferedReader5 = null;
                bufferedReader2 = bufferedReader5;
                bufferedReader4 = bufferedReader5;
                try {
                    MyLog.e((Throwable) e);
                    try {
                        r0.release();
                    } catch (IOException e2) {
                        MyLog.e((Throwable) e2);
                    }
                    IOUtils.closeQuietly(bufferedReader4);
                    IOUtils.closeQuietly(bufferedReader2);
                } catch (Throwable th) {
                    th = th;
                    fileLock = r0;
                    bufferedReader3 = bufferedReader4;
                    r0 = bufferedReader2;
                    bufferedReader = bufferedReader3;
                    try {
                        fileLock.release();
                    } catch (IOException e3) {
                        MyLog.e((Throwable) e3);
                    }
                    IOUtils.closeQuietly(bufferedReader);
                    IOUtils.closeQuietly(r0);
                    if (file != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileLock2 = null;
                fileLock = fileLock2;
                bufferedReader = fileLock2;
                fileLock.release();
                IOUtils.closeQuietly(bufferedReader);
                IOUtils.closeQuietly(r0);
                if (file != null) {
                }
                throw th;
            }
            try {
                fileLock = randomAccessFile.getChannel().lock();
            } catch (Exception e4) {
                e = e4;
                bufferedReader2 = null;
                bufferedReader4 = randomAccessFile;
                MyLog.e((Throwable) e);
                r0.release();
                IOUtils.closeQuietly(bufferedReader4);
                IOUtils.closeQuietly(bufferedReader2);
            } catch (Throwable th3) {
                th = th3;
                fileLock = null;
                bufferedReader = randomAccessFile;
                fileLock.release();
                IOUtils.closeQuietly(bufferedReader);
                IOUtils.closeQuietly(r0);
                if (file != null) {
                }
                throw th;
            }
            try {
                bufferedReader2 = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split("%%%");
                        if (split.length >= 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                            PerfClientReport buildPerfClientReport = buildPerfClientReport(spiltKeyForModel(split[0]), split[1]);
                            if (buildPerfClientReport != null) {
                                arrayList.add(buildPerfClientReport.toJsonString());
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        r0 = fileLock;
                        bufferedReader4 = randomAccessFile;
                        MyLog.e((Throwable) e);
                        r0.release();
                        IOUtils.closeQuietly(bufferedReader4);
                        IOUtils.closeQuietly(bufferedReader2);
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader3 = randomAccessFile;
                        r0 = bufferedReader2;
                        bufferedReader = bufferedReader3;
                        fileLock.release();
                        IOUtils.closeQuietly(bufferedReader);
                        IOUtils.closeQuietly(r0);
                        if (file != null) {
                        }
                        throw th;
                    }
                }
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e6) {
                        MyLog.e((Throwable) e6);
                    }
                }
                IOUtils.closeQuietly(randomAccessFile);
                IOUtils.closeQuietly(bufferedReader2);
            } catch (Exception e7) {
                e = e7;
                bufferedReader2 = null;
                r0 = fileLock;
                bufferedReader4 = randomAccessFile;
                MyLog.e((Throwable) e);
                r0.release();
                IOUtils.closeQuietly(bufferedReader4);
                IOUtils.closeQuietly(bufferedReader2);
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = randomAccessFile;
                fileLock.release();
                IOUtils.closeQuietly(bufferedReader);
                IOUtils.closeQuietly(r0);
                if (file != null) {
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            file = null;
            bufferedReader5 = null;
            bufferedReader2 = bufferedReader5;
            bufferedReader4 = bufferedReader5;
            MyLog.e((Throwable) e);
            if (r0 != 0 && r0.isValid()) {
                r0.release();
            }
            IOUtils.closeQuietly(bufferedReader4);
            IOUtils.closeQuietly(bufferedReader2);
        } catch (Throwable th6) {
            th = th6;
            file = null;
            fileLock2 = null;
            fileLock = fileLock2;
            bufferedReader = fileLock2;
            if (fileLock != null && fileLock.isValid()) {
                fileLock.release();
            }
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(r0);
            if (file != null) {
                file.delete();
            }
            throw th;
        }
        file.delete();
        return arrayList;
    }

    public static String generateKey(PerfClientReport perfClientReport) {
        return perfClientReport.production + "#" + perfClientReport.clientInterfaceId + "#" + perfClientReport.reportType + "#" + perfClientReport.code;
    }

    protected static long[] parseValueStr(String str) {
        long[] jArr = new long[2];
        try {
            String[] split = str.split("#");
            if (split.length >= 2) {
                jArr[0] = Long.parseLong(split[0].trim());
                jArr[1] = Long.parseLong(split[1].trim());
            }
            return jArr;
        } catch (Exception e) {
            MyLog.e((Throwable) e);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: java.nio.channels.FileLock} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0087  */
    /* JADX WARNING: Unknown variable types count: 2 */
    public static void put(String str, BaseClientReport[] baseClientReportArr) {
        ? r2;
        ? r22;
        FileLock fileLock;
        FileLock fileLock2;
        if (baseClientReportArr != null && baseClientReportArr.length > 0 && !TextUtils.isEmpty(str)) {
            FileLock fileLock3 = null;
            try {
                File file = new File(str + ".lock");
                IOUtils.createFileQuietly(file);
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    FileLock lock = randomAccessFile.getChannel().lock();
                    HashMap<String, String> readFromFile = readFromFile(str);
                    for (PerfClientReport perfClientReport : baseClientReportArr) {
                        if (perfClientReport != null) {
                            String generateKey = generateKey(perfClientReport);
                            long j = perfClientReport.perfCounts;
                            long j2 = perfClientReport.perfLatencies;
                            if (!TextUtils.isEmpty(generateKey) && j > 0) {
                                if (j2 >= 0) {
                                    putInMemeory(readFromFile, generateKey, j, j2);
                                }
                            }
                        }
                    }
                    writeToFile(str, readFromFile);
                    r22 = randomAccessFile;
                    if (lock != null) {
                        r22 = randomAccessFile;
                        if (lock.isValid()) {
                            try {
                                lock.release();
                                r22 = randomAccessFile;
                            } catch (IOException e) {
                                e = e;
                                fileLock = randomAccessFile;
                            }
                        }
                    }
                } catch (Throwable unused) {
                    fileLock2 = randomAccessFile;
                    try {
                        MyLog.v("failed to write perf to file ");
                        r22 = fileLock2;
                        if (fileLock3 != null) {
                        }
                        IOUtils.closeQuietly(r22);
                    } catch (Throwable th) {
                        th = th;
                        r2 = fileLock2;
                        try {
                            fileLock3.release();
                        } catch (IOException e2) {
                            MyLog.e((Throwable) e2);
                        }
                        IOUtils.closeQuietly(r2);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                r2 = fileLock3;
                if (fileLock3 != null && fileLock3.isValid()) {
                    fileLock3.release();
                }
                IOUtils.closeQuietly(r2);
                throw th;
            }
            IOUtils.closeQuietly(r22);
        }
        return;
        MyLog.e((Throwable) e);
        r22 = fileLock;
        IOUtils.closeQuietly(r22);
    }

    private static void putInMemeory(HashMap<String, String> hashMap, String str, long j, long j2) {
        String str2;
        String str3 = hashMap.get(str);
        if (TextUtils.isEmpty(str3)) {
            hashMap.put(str, j + "#" + j2);
            return;
        }
        long[] parseValueStr = parseValueStr(str3);
        if (parseValueStr == null || parseValueStr[0] <= 0 || parseValueStr[1] < 0) {
            str2 = j + "#" + j2;
        } else {
            str2 = (j + parseValueStr[0]) + "#" + (j2 + parseValueStr[1]);
        }
        hashMap.put(str, str2);
    }

    private static HashMap<String, String> readFromFile(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return hashMap;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split("%%%");
                    if (split.length >= 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                        hashMap.put(split[0], split[1]);
                    }
                } catch (Exception e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    try {
                        MyLog.e((Throwable) e);
                        IOUtils.closeQuietly(bufferedReader);
                        return hashMap;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        IOUtils.closeQuietly(bufferedReader2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(bufferedReader2);
                    throw th;
                }
            }
            IOUtils.closeQuietly(bufferedReader2);
        } catch (Exception e2) {
            e = e2;
            MyLog.e((Throwable) e);
            IOUtils.closeQuietly(bufferedReader);
            return hashMap;
        }
        return hashMap;
    }

    private static String[] spiltKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("#");
    }

    private static PerfClientReport spiltKeyForModel(String str) {
        try {
            String[] spiltKey = spiltKey(str);
            if (spiltKey == null || spiltKey.length < 4 || TextUtils.isEmpty(spiltKey[0]) || TextUtils.isEmpty(spiltKey[1]) || TextUtils.isEmpty(spiltKey[2]) || TextUtils.isEmpty(spiltKey[3])) {
                return null;
            }
            PerfClientReport blankInstance = PerfClientReport.getBlankInstance();
            blankInstance.production = Integer.parseInt(spiltKey[0]);
            blankInstance.clientInterfaceId = spiltKey[1];
            blankInstance.reportType = Integer.parseInt(spiltKey[2]);
            blankInstance.code = Integer.parseInt(spiltKey[3]);
            return blankInstance;
        } catch (Exception unused) {
            MyLog.v("parse per key error");
            return null;
        }
    }

    private static void writeToFile(String str, HashMap<String, String> hashMap) {
        BufferedWriter bufferedWriter;
        Exception e;
        if (!TextUtils.isEmpty(str) && hashMap != null && hashMap.size() != 0) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                try {
                    for (String next : hashMap.keySet()) {
                        bufferedWriter.write(next + "%%%" + hashMap.get(next));
                        bufferedWriter.newLine();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        MyLog.e((Throwable) e);
                        IOUtils.closeQuietly(bufferedWriter);
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(bufferedWriter);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                bufferedWriter = null;
                e = e3;
                MyLog.e((Throwable) e);
                IOUtils.closeQuietly(bufferedWriter);
            } catch (Throwable th2) {
                bufferedWriter = null;
                th = th2;
                IOUtils.closeQuietly(bufferedWriter);
                throw th;
            }
            IOUtils.closeQuietly(bufferedWriter);
        }
    }
}
