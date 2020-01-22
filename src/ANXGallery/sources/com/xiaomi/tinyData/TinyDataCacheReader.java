package com.xiaomi.tinyData;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.android.DataCryptUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ByteUtils;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.push.service.TinyDataStorage;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;

public class TinyDataCacheReader {
    private static boolean mTinyDataJobIsRunning;

    static class TinyDataReadJob implements Runnable {
        private Context mContext;
        private TinyDataUploader mUploader;

        public TinyDataReadJob(Context context, TinyDataUploader tinyDataUploader) {
            this.mUploader = tinyDataUploader;
            this.mContext = context;
        }

        public void run() {
            TinyDataCacheReader.extractTinyData(this.mContext, this.mUploader);
        }
    }

    public static void addTinyDataCacheReadJob(Context context, TinyDataUploader tinyDataUploader) {
        ScheduledJobManager.getInstance(context).addOneShootJob(new TinyDataReadJob(context, tinyDataUploader));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bb  */
    public static void extractTinyData(Context context, TinyDataUploader tinyDataUploader) {
        RandomAccessFile randomAccessFile;
        File file;
        if (!mTinyDataJobIsRunning) {
            mTinyDataJobIsRunning = true;
            File file2 = new File(context.getFilesDir(), "tiny_data.data");
            if (!file2.exists()) {
                MyLog.w("TinyData no ready file to get data.");
                return;
            }
            verifyFileDir(context);
            byte[] tinyDataKeyWithDefault = TinyDataStorage.getTinyDataKeyWithDefault(context);
            FileLock fileLock = null;
            try {
                File file3 = new File(context.getFilesDir(), "tiny_data.lock");
                IOUtils.createFileQuietly(file3);
                randomAccessFile = new RandomAccessFile(file3, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    file2.renameTo(new File(context.getFilesDir() + "/tdReadTemp" + "/" + "tiny_data.data"));
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e) {
                            e = e;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        MyLog.e((Throwable) e);
                        try {
                            fileLock.release();
                        } catch (IOException e3) {
                            e = e3;
                        }
                        IOUtils.closeQuietly(randomAccessFile);
                        file = new File(context.getFilesDir() + "/tdReadTemp" + "/" + "tiny_data.data");
                        if (file.exists()) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                            MyLog.e((Throwable) e4);
                        }
                        IOUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
                MyLog.e((Throwable) e);
                if (fileLock != null && fileLock.isValid()) {
                    fileLock.release();
                }
                IOUtils.closeQuietly(randomAccessFile);
                file = new File(context.getFilesDir() + "/tdReadTemp" + "/" + "tiny_data.data");
                if (file.exists()) {
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
                if (fileLock != null && fileLock.isValid()) {
                    fileLock.release();
                }
                IOUtils.closeQuietly(randomAccessFile);
                throw th;
            }
            IOUtils.closeQuietly(randomAccessFile);
            file = new File(context.getFilesDir() + "/tdReadTemp" + "/" + "tiny_data.data");
            if (file.exists()) {
                MyLog.w("TinyData no ready file to get data.");
                return;
            }
            readTinyDataFromFile(context, tinyDataUploader, file, tinyDataKeyWithDefault);
            TinyDataCacheProcessor.setIsTinyDataExtracting(false);
            updateTinyDataUploadTimeStamp(context);
            mTinyDataJobIsRunning = false;
            return;
        }
        MyLog.w("TinyData extractTinyData is running");
        return;
        MyLog.e((Throwable) e);
        IOUtils.closeQuietly(randomAccessFile);
        file = new File(context.getFilesDir() + "/tdReadTemp" + "/" + "tiny_data.data");
        if (file.exists()) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.e("TinyData read from cache file failed cause lengthBuffer error. size:" + r7);
     */
    private static void readTinyDataFromFile(Context context, TinyDataUploader tinyDataUploader, File file, byte[] bArr) {
        int i;
        ArrayList arrayList = new ArrayList();
        byte[] bArr2 = new byte[4];
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            loop0:
            while (true) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    try {
                        int read = bufferedInputStream2.read(bArr2);
                        if (read == -1) {
                            break loop0;
                        } else if (read != 4) {
                            break loop0;
                        } else {
                            i = ByteUtils.toInt(bArr2);
                            if (i < 1) {
                                break loop0;
                            } else if (i > 10240) {
                                break loop0;
                            } else {
                                byte[] bArr3 = new byte[i];
                                int read2 = bufferedInputStream2.read(bArr3);
                                if (read2 != i) {
                                    MyLog.e("TinyData read from cache file failed cause buffer size not equal length. size:" + read2 + "__length:" + i);
                                    break loop0;
                                }
                                byte[] mipushDecrypt = DataCryptUtils.mipushDecrypt(bArr, bArr3);
                                if (mipushDecrypt != null) {
                                    if (mipushDecrypt.length != 0) {
                                        ClientUploadDataItem clientUploadDataItem = new ClientUploadDataItem();
                                        XmPushThriftSerializeUtils.convertByteArrayToThriftObject(clientUploadDataItem, mipushDecrypt);
                                        arrayList.add(clientUploadDataItem);
                                        i2++;
                                        i3 += mipushDecrypt.length;
                                        if (i2 >= 8 || i3 >= 10240) {
                                            MyLog.w("TinyData readTinyDataFromFile upload tiny data by part. items:" + arrayList.size() + " ts:" + System.currentTimeMillis());
                                            TinyDataCacheUploader.uploadTinyData(context, tinyDataUploader, arrayList);
                                            arrayList = new ArrayList();
                                        }
                                    }
                                }
                                MyLog.e("TinyData read from cache file failed cause decrypt fail");
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            MyLog.e((Throwable) e);
                            IOUtils.closeQuietly(bufferedInputStream);
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream2 = bufferedInputStream;
                            IOUtils.closeQuietly(bufferedInputStream2);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtils.closeQuietly(bufferedInputStream2);
                        throw th;
                    }
                }
            }
            MyLog.e("TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:" + i);
            TinyDataCacheUploader.uploadTinyData(context, tinyDataUploader, arrayList);
            MyLog.w("TinyData readTinyDataFromFile upload tiny data at last. items:" + arrayList.size() + " ts:" + System.currentTimeMillis());
            if (file != null && file.exists() && !file.delete()) {
                MyLog.w("TinyData delete reading temp file failed");
            }
            IOUtils.closeQuietly(bufferedInputStream2);
        } catch (Exception e2) {
            e = e2;
            MyLog.e((Throwable) e);
            IOUtils.closeQuietly(bufferedInputStream);
        }
    }

    private static void updateTinyDataUploadTimeStamp(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 4).edit();
        edit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    private static void verifyFileDir(Context context) {
        File file = new File(context.getFilesDir() + "/tdReadTemp");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
