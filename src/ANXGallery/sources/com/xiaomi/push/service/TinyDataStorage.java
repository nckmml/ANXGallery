package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.DataCryptUtils;
import com.xiaomi.channel.commonutils.android.SharedPreferenceManager;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ByteUtils;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;

public class TinyDataStorage {
    public static final Object mTinyDataLock4Thread = new Object();

    public static void cacheTinyData(final Context context, final ClientUploadDataItem clientUploadDataItem) {
        if (TinyDataHelper.shouldUpload(clientUploadDataItem.getPkgName())) {
            MyLog.w("TinyData TinyDataStorage.cacheTinyData cache data to file begin item:" + clientUploadDataItem.getId() + "  ts:" + System.currentTimeMillis());
            ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A[SYNTHETIC, Splitter:B:25:0x004a] */
                public void run() {
                    RandomAccessFile randomAccessFile;
                    synchronized (TinyDataStorage.mTinyDataLock4Thread) {
                        FileLock fileLock = null;
                        try {
                            File file = new File(context.getFilesDir(), "tiny_data.lock");
                            IOUtils.createFileQuietly(file);
                            randomAccessFile = new RandomAccessFile(file, "rw");
                            try {
                                fileLock = randomAccessFile.getChannel().lock();
                                TinyDataStorage.writeTinyData2File(context, clientUploadDataItem);
                                if (fileLock != null) {
                                    if (fileLock.isValid()) {
                                        try {
                                            fileLock.release();
                                        } catch (IOException e) {
                                            MyLog.e((Throwable) e);
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                e = e2;
                                try {
                                    MyLog.e((Throwable) e);
                                    if (fileLock != null) {
                                        if (fileLock.isValid()) {
                                            try {
                                                fileLock.release();
                                            } catch (IOException e3) {
                                                MyLog.e((Throwable) e3);
                                            }
                                        }
                                    }
                                    IOUtils.closeQuietly(randomAccessFile);
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
                            if (fileLock != null) {
                            }
                            IOUtils.closeQuietly(randomAccessFile);
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
                    }
                }
            });
        }
    }

    public static byte[] getTinyDataKeyWithDefault(Context context) {
        String stringValue = SharedPreferenceManager.getInstance(context).getStringValue("mipush", "td_key", "");
        if (TextUtils.isEmpty(stringValue)) {
            stringValue = XMStringUtils.generateRandomString(20);
            SharedPreferenceManager.getInstance(context).setStringnValue("mipush", "td_key", stringValue);
        }
        return parseKey(stringValue);
    }

    private static byte[] parseKey(String str) {
        byte[] copyOf = Arrays.copyOf(Base64Coder.decode(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v22 */
    /* JADX WARNING: type inference failed for: r0v23 */
    /* JADX WARNING: type inference failed for: r0v25 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    public static void writeTinyData2File(Context context, ClientUploadDataItem clientUploadDataItem) {
        ? r0;
        byte[] bArr;
        ? r02;
        Object obj;
        byte[] bArr2;
        byte[] tinyDataKeyWithDefault = getTinyDataKeyWithDefault(context);
        try {
            bArr = tinyDataKeyWithDefault;
            byte[] mipushEncrypt = DataCryptUtils.mipushEncrypt(tinyDataKeyWithDefault, XmPushThriftSerializeUtils.convertThriftObjectToBytes(clientUploadDataItem));
            if (mipushEncrypt != null) {
                if (mipushEncrypt.length >= 1) {
                    if (mipushEncrypt.length > 10240) {
                        MyLog.w("TinyData write to cache file failed case too much data content item:" + clientUploadDataItem.getId() + "  ts:" + System.currentTimeMillis());
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly((Closeable) null);
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), "tiny_data.data"), true));
                    try {
                        bufferedOutputStream.write(ByteUtils.parseInt(mipushEncrypt.length));
                        bufferedOutputStream.write(mipushEncrypt);
                        bufferedOutputStream.flush();
                        MyLog.w("TinyData write to cache file success item:" + clientUploadDataItem.getId() + "  ts:" + System.currentTimeMillis());
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly(bufferedOutputStream);
                        return;
                    } catch (IOException e) {
                        IOException iOException = e;
                        obj = bufferedOutputStream;
                        e = iOException;
                        MyLog.e("TinyData write to cache file failed cause io exception item:" + clientUploadDataItem.getId(), e);
                        r02 = obj;
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly(r02);
                    } catch (Exception e2) {
                        Exception exc = e2;
                        bArr2 = bufferedOutputStream;
                        e = exc;
                        bArr = bArr2;
                        MyLog.e("TinyData write to cache file  failed item:" + clientUploadDataItem.getId(), e);
                        r02 = bArr2;
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly(r02);
                    } catch (Throwable th) {
                        r0 = bufferedOutputStream;
                        th = th;
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly(r0);
                        throw th;
                    }
                }
            }
            MyLog.w("TinyData write to cache file failed case encryption fail item:" + clientUploadDataItem.getId() + "  ts:" + System.currentTimeMillis());
            IOUtils.closeQuietly((Closeable) null);
            IOUtils.closeQuietly((Closeable) null);
        } catch (IOException e3) {
            e = e3;
            obj = null;
        } catch (Exception e4) {
            e = e4;
            bArr2 = null;
            bArr = bArr2;
            MyLog.e("TinyData write to cache file  failed item:" + clientUploadDataItem.getId(), e);
            r02 = bArr2;
            IOUtils.closeQuietly((Closeable) null);
            IOUtils.closeQuietly(r02);
        } catch (Throwable th2) {
            th = th2;
            r0 = bArr;
            IOUtils.closeQuietly((Closeable) null);
            IOUtils.closeQuietly(r0);
            throw th;
        }
    }
}
