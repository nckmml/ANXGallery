package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.xiaomi.xmpush.thrift.ClientCollectionType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StorageCollectionJob extends CollectionJob {
    public StorageCollectionJob(Context context, int i) {
        super(context, i);
    }

    private double getNum(double d) {
        int i = 1;
        while (true) {
            double d2 = (double) i;
            if (d2 >= d) {
                return d2;
            }
            i <<= 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        if (r1 != null) goto L_0x005d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e A[SYNTHETIC, Splitter:B:27:0x006e] */
    private String getRamSize() {
        BufferedReader bufferedReader;
        Throwable th;
        String[] split;
        boolean exists = new File("/proc/meminfo").exists();
        String str = MovieStatUtils.DOWNLOAD_SUCCESS;
        if (exists) {
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                try {
                    String readLine = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(readLine) && (split = readLine.split("\\s+")) != null && split.length >= 2) {
                        double doubleValue = (Double.valueOf(split[1]).doubleValue() / 1024.0d) / 1024.0d;
                        if (doubleValue > 0.5d) {
                            doubleValue = Math.ceil(doubleValue);
                        }
                        str = doubleValue + "";
                    }
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused3) {
                bufferedReader = null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                if (bufferedReader != null) {
                }
                throw th;
            }
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
        }
        return str + "GB";
    }

    private String getRomSize() {
        double num = getNum(((((double) getSize(Environment.getDataDirectory())) / 1024.0d) / 1024.0d) / 1024.0d);
        return num + "GB";
    }

    private long getSize(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
    }

    public String collectInfo() {
        return "ram:" + getRamSize() + "," + "rom:" + getRomSize();
    }

    public ClientCollectionType getCollectionType() {
        return ClientCollectionType.Storage;
    }

    public int getJobId() {
        return 23;
    }
}
