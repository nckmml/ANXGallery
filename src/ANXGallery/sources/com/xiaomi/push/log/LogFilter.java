package com.xiaomi.push.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.nexstreaming.nexeditorsdk.nexEngine;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.ServiceConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LogFilter {
    private static String MIPUSH_LOG_PATH = "/MiPushLog";
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int mCurrentLen;
    private String mEndTime;
    private ArrayList<File> mFiles = new ArrayList<>();
    private String mFromTime;
    private int mMaxLen = nexEngine.ExportHEVCHighTierLevel6;
    private boolean mStartFound;

    LogFilter() {
    }

    private void doFilter(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) throws IOException {
        char[] cArr = new char[4096];
        int read = bufferedReader.read(cArr);
        boolean z = false;
        while (read != -1 && !z) {
            String str = new String(cArr, 0, read);
            Matcher matcher = pattern.matcher(str);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= read || !matcher.find(i)) {
                    break;
                }
                int start = matcher.start();
                String substring = str.substring(start, this.mFromTime.length() + start);
                if (this.mStartFound) {
                    if (substring.compareTo(this.mEndTime) > 0) {
                        z = true;
                        read = start;
                        break;
                    }
                } else if (substring.compareTo(this.mFromTime) >= 0) {
                    this.mStartFound = true;
                    i2 = start;
                }
                int indexOf = str.indexOf(10, start);
                if (indexOf == -1) {
                    indexOf = this.mFromTime.length();
                }
                i = start + indexOf;
            }
            if (this.mStartFound) {
                int i3 = read - i2;
                this.mCurrentLen += i3;
                if (z) {
                    bufferedWriter.write(cArr, i2, i3);
                    return;
                }
                bufferedWriter.write(cArr, i2, i3);
                if (this.mCurrentLen > this.mMaxLen) {
                    return;
                }
            }
            read = bufferedReader.read(cArr);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.io.BufferedWriter} */
    /* JADX WARNING: Multi-variable type inference failed */
    private void filter2File(File file) {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2;
        BufferedWriter bufferedWriter3;
        BufferedWriter bufferedWriter4;
        BufferedWriter bufferedWriter5;
        BufferedWriter bufferedWriter6;
        BufferedWriter bufferedWriter7;
        Pattern compile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        BufferedWriter bufferedWriter8 = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            try {
                bufferedWriter.write("model :" + Build.MODEL + "; os :" + Build.VERSION.INCREMENTAL + "; uid :" + ServiceConfig.getDeviceUUID() + "; lng :" + Locale.getDefault().toString() + "; sdk :" + 38 + "; andver :" + Build.VERSION.SDK_INT + "\n");
                this.mCurrentLen = 0;
                Iterator<File> it = this.mFiles.iterator();
                while (it.hasNext()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(it.next())));
                    try {
                        doFilter(bufferedReader, bufferedWriter, compile);
                        bufferedReader.close();
                        bufferedWriter8 = bufferedReader;
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedWriter6 = bufferedReader;
                        bufferedWriter8 = bufferedWriter;
                        bufferedWriter4 = bufferedWriter6;
                        MyLog.v("LOG: filter error = " + e.getMessage());
                        bufferedWriter3 = bufferedWriter4;
                        IOUtils.closeQuietly(bufferedWriter8);
                        IOUtils.closeQuietly(bufferedWriter3);
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter7 = bufferedReader;
                        bufferedWriter8 = bufferedWriter;
                        bufferedWriter5 = bufferedWriter7;
                        MyLog.v("LOG: filter error = " + e.getMessage());
                        bufferedWriter3 = bufferedWriter5;
                        IOUtils.closeQuietly(bufferedWriter8);
                        IOUtils.closeQuietly(bufferedWriter3);
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter2 = bufferedReader;
                        bufferedWriter8 = bufferedWriter2;
                        IOUtils.closeQuietly(bufferedWriter);
                        IOUtils.closeQuietly(bufferedWriter8);
                        throw th;
                    }
                }
                bufferedWriter.write(HostManager.getInstance().dump());
                IOUtils.closeQuietly(bufferedWriter);
                IOUtils.closeQuietly(bufferedWriter8);
            } catch (FileNotFoundException e3) {
                e = e3;
                bufferedWriter6 = bufferedWriter8;
                bufferedWriter8 = bufferedWriter;
                bufferedWriter4 = bufferedWriter6;
                MyLog.v("LOG: filter error = " + e.getMessage());
                bufferedWriter3 = bufferedWriter4;
                IOUtils.closeQuietly(bufferedWriter8);
                IOUtils.closeQuietly(bufferedWriter3);
            } catch (IOException e4) {
                e = e4;
                bufferedWriter7 = bufferedWriter8;
                bufferedWriter8 = bufferedWriter;
                bufferedWriter5 = bufferedWriter7;
                MyLog.v("LOG: filter error = " + e.getMessage());
                bufferedWriter3 = bufferedWriter5;
                IOUtils.closeQuietly(bufferedWriter8);
                IOUtils.closeQuietly(bufferedWriter3);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(bufferedWriter);
                IOUtils.closeQuietly(bufferedWriter8);
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            bufferedWriter4 = null;
            MyLog.v("LOG: filter error = " + e.getMessage());
            bufferedWriter3 = bufferedWriter4;
            IOUtils.closeQuietly(bufferedWriter8);
            IOUtils.closeQuietly(bufferedWriter3);
        } catch (IOException e6) {
            e = e6;
            bufferedWriter5 = null;
            MyLog.v("LOG: filter error = " + e.getMessage());
            bufferedWriter3 = bufferedWriter5;
            IOUtils.closeQuietly(bufferedWriter8);
            IOUtils.closeQuietly(bufferedWriter3);
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = bufferedWriter8;
            bufferedWriter2 = bufferedWriter5;
            bufferedWriter8 = bufferedWriter2;
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(bufferedWriter8);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public LogFilter addFile(File file) {
        if (file.exists()) {
            this.mFiles.add(file);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public File filter(Context context, Date date, Date date2, File file) {
        File file2;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            file2 = context.getFilesDir();
            addFile(new File(file2, "xmsf.log.1"));
            addFile(new File(file2, "xmsf.log"));
        } else {
            File file3 = new File(context.getExternalFilesDir((String) null) + MIPUSH_LOG_PATH);
            addFile(new File(file3, "log0.txt"));
            addFile(new File(file3, "log1.txt"));
            file2 = file3;
        }
        if (!file2.isDirectory()) {
            return null;
        }
        File file4 = new File(file, date.getTime() + "-" + date2.getTime() + ".zip");
        if (file4.exists()) {
            return null;
        }
        setRange(date, date2);
        long currentTimeMillis = System.currentTimeMillis();
        File file5 = new File(file, "log.txt");
        filter2File(file5);
        MyLog.v("LOG: filter cost = " + (System.currentTimeMillis() - currentTimeMillis));
        if (file5.exists()) {
            long currentTimeMillis2 = System.currentTimeMillis();
            IOUtils.zip(file4, file5);
            MyLog.v("LOG: zip cost = " + (System.currentTimeMillis() - currentTimeMillis2));
            file5.delete();
            if (file4.exists()) {
                return file4;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setMaxLen(int i) {
        if (i != 0) {
            this.mMaxLen = i;
        }
    }

    /* access modifiers changed from: package-private */
    public LogFilter setRange(Date date, Date date2) {
        if (date.after(date2)) {
            this.mFromTime = this.dateFormatter.format(date2);
            this.mEndTime = this.dateFormatter.format(date);
        } else {
            this.mFromTime = this.dateFormatter.format(date);
            this.mEndTime = this.dateFormatter.format(date2);
        }
        return this;
    }
}
