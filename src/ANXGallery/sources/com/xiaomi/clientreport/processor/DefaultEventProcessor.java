package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.android.DataCryptUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ByteUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.clientreport.data.BaseClientReport;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.manager.ClientReportLogicManager;
import com.xiaomi.clientreport.util.ClientReportUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DefaultEventProcessor implements IEventProcessor {
    protected Context mContext;
    private HashMap<String, ArrayList<BaseClientReport>> mEventMap;

    public DefaultEventProcessor(Context context) {
        setContext(context);
    }

    public static String getFirstEventFileName(BaseClientReport baseClientReport) {
        return String.valueOf(baseClientReport.production);
    }

    private String getWriteFileName(BaseClientReport baseClientReport) {
        File externalFilesDir = this.mContext.getExternalFilesDir("event");
        String firstEventFileName = getFirstEventFileName(baseClientReport);
        if (externalFilesDir == null) {
            return null;
        }
        String str = externalFilesDir.getAbsolutePath() + File.separator + firstEventFileName;
        for (int i = 0; i < 100; i++) {
            String str2 = str + i;
            if (ClientReportUtil.isFileCanBeUse(this.mContext, str2)) {
                return str2;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.e("eventData read from cache file failed because magicNumber error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.e("eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K");
     */
    private List<String> readFile(String str) {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(new File(str));
            while (true) {
                try {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (read != 4) {
                        break;
                    } else if (ByteUtils.toInt(bArr) != -573785174) {
                        MyLog.e("eventData read from cache file failed because magicNumber error");
                        break;
                    } else {
                        int read2 = fileInputStream2.read(bArr2);
                        if (read2 == -1) {
                            break;
                        } else if (read2 != 4) {
                            MyLog.e("eventData read from cache file failed cause lengthBuffer error");
                            break;
                        } else {
                            int i = ByteUtils.toInt(bArr2);
                            if (i < 1) {
                                break;
                            } else if (i > 4096) {
                                break;
                            } else {
                                byte[] bArr3 = new byte[i];
                                if (fileInputStream2.read(bArr3) != i) {
                                    MyLog.e("eventData read from cache file failed cause buffer size not equal length");
                                    break;
                                }
                                arrayList.add(bytesToString(bArr3));
                            }
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    try {
                        MyLog.e((Throwable) e);
                        IOUtils.closeQuietly(fileInputStream);
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        IOUtils.closeQuietly(fileInputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(fileInputStream2);
                    throw th;
                }
            }
            IOUtils.closeQuietly(fileInputStream2);
        } catch (Exception e2) {
            e = e2;
            MyLog.e((Throwable) e);
            IOUtils.closeQuietly(fileInputStream);
            return arrayList;
        }
        return arrayList;
    }

    private void write2File(BaseClientReport[] baseClientReportArr, String str) {
        if (baseClientReportArr != null && baseClientReportArr.length > 0 && !TextUtils.isEmpty(str)) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str), true));
                try {
                    for (BaseClientReport baseClientReport : baseClientReportArr) {
                        if (baseClientReport != null) {
                            byte[] stringToBytes = stringToBytes(baseClientReport.toJsonString());
                            if (stringToBytes != null && stringToBytes.length >= 1) {
                                if (stringToBytes.length <= 4096) {
                                    bufferedOutputStream2.write(ByteUtils.parseInt(-573785174));
                                    bufferedOutputStream2.write(ByteUtils.parseInt(stringToBytes.length));
                                    bufferedOutputStream2.write(stringToBytes);
                                    bufferedOutputStream2.flush();
                                }
                            }
                            MyLog.e("event data throw a invalid item ");
                        }
                    }
                    IOUtils.closeQuietly(bufferedOutputStream2);
                } catch (Exception e) {
                    e = e;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        MyLog.e("event data write to cache file failed cause exception", e);
                        IOUtils.closeQuietly(bufferedOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream2 = bufferedOutputStream;
                        IOUtils.closeQuietly(bufferedOutputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(bufferedOutputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                MyLog.e("event data write to cache file failed cause exception", e);
                IOUtils.closeQuietly(bufferedOutputStream);
            }
        }
    }

    public String bytesToString(byte[] bArr) {
        byte[] parseKey;
        if (bArr != null && bArr.length >= 1) {
            if (!ClientReportLogicManager.getInstance(this.mContext).getConfig().isEventEncrypted()) {
                return XMStringUtils.bytesToString(bArr);
            }
            String eventKeyWithDefault = ClientReportUtil.getEventKeyWithDefault(this.mContext);
            if (!TextUtils.isEmpty(eventKeyWithDefault) && (parseKey = ClientReportUtil.parseKey(eventKeyWithDefault)) != null && parseKey.length > 0) {
                try {
                    return XMStringUtils.bytesToString(Base64.decode(DataCryptUtils.mipushDecrypt(parseKey, bArr), 2));
                } catch (InvalidAlgorithmParameterException e) {
                    MyLog.e((Throwable) e);
                } catch (NoSuchAlgorithmException e2) {
                    MyLog.e((Throwable) e2);
                } catch (InvalidKeyException e3) {
                    MyLog.e((Throwable) e3);
                } catch (NoSuchPaddingException e4) {
                    MyLog.e((Throwable) e4);
                } catch (BadPaddingException e5) {
                    MyLog.e((Throwable) e5);
                } catch (IllegalBlockSizeException e6) {
                    MyLog.e((Throwable) e6);
                }
            }
        }
        return null;
    }

    public void preProcess(BaseClientReport baseClientReport) {
        if ((baseClientReport instanceof EventClientReport) && this.mEventMap != null) {
            EventClientReport eventClientReport = (EventClientReport) baseClientReport;
            String firstEventFileName = getFirstEventFileName(eventClientReport);
            ArrayList arrayList = this.mEventMap.get(firstEventFileName);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(eventClientReport);
            this.mEventMap.put(firstEventFileName, arrayList);
        }
    }

    public void process() {
        HashMap<String, ArrayList<BaseClientReport>> hashMap = this.mEventMap;
        if (hashMap != null) {
            if (hashMap.size() > 0) {
                for (String str : this.mEventMap.keySet()) {
                    ArrayList arrayList = this.mEventMap.get(str);
                    if (arrayList != null && arrayList.size() > 0) {
                        BaseClientReport[] baseClientReportArr = new BaseClientReport[arrayList.size()];
                        arrayList.toArray(baseClientReportArr);
                        write(baseClientReportArr);
                    }
                }
            }
            this.mEventMap.clear();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00b4 A[SYNTHETIC] */
    public void readAndSend() {
        String absolutePath;
        File file;
        ClientReportUtil.moveFiles(this.mContext, "event", "eventUploading");
        File[] readFileName = ClientReportUtil.getReadFileName(this.mContext, "eventUploading");
        if (readFileName != null && readFileName.length > 0) {
            FileLock fileLock = null;
            RandomAccessFile randomAccessFile = null;
            File file2 = null;
            for (File file3 : readFileName) {
                if (file3 == null) {
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e) {
                            MyLog.e((Throwable) e);
                        }
                    }
                    IOUtils.closeQuietly(randomAccessFile);
                    if (file2 == null) {
                    }
                } else {
                    try {
                        absolutePath = file3.getAbsolutePath();
                        file = new File(absolutePath + ".lock");
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            MyLog.e((Throwable) e);
                            if (fileLock != null && fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (IOException e3) {
                                    MyLog.e((Throwable) e3);
                                }
                            }
                            IOUtils.closeQuietly(randomAccessFile);
                            if (file2 == null) {
                            }
                            file2.delete();
                        } catch (Throwable th) {
                            th = th;
                            if (fileLock != null && fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (IOException e4) {
                                    MyLog.e((Throwable) e4);
                                }
                            }
                            IOUtils.closeQuietly(randomAccessFile);
                            if (file2 != null) {
                                file2.delete();
                            }
                            throw th;
                        }
                    }
                    try {
                        IOUtils.createFileQuietly(file);
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                        try {
                            fileLock = randomAccessFile2.getChannel().lock();
                            send(readFile(absolutePath));
                            file3.delete();
                            if (fileLock != null && fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (IOException e5) {
                                    MyLog.e((Throwable) e5);
                                }
                            }
                            IOUtils.closeQuietly(randomAccessFile2);
                            file.delete();
                            randomAccessFile = randomAccessFile2;
                            file2 = file;
                        } catch (Exception e6) {
                            e = e6;
                            randomAccessFile = randomAccessFile2;
                            file2 = file;
                            MyLog.e((Throwable) e);
                            fileLock.release();
                            IOUtils.closeQuietly(randomAccessFile);
                            if (file2 == null) {
                            }
                            file2.delete();
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile = randomAccessFile2;
                            file2 = file;
                            fileLock.release();
                            IOUtils.closeQuietly(randomAccessFile);
                            if (file2 != null) {
                            }
                            throw th;
                        }
                    } catch (Exception e7) {
                        e = e7;
                        file2 = file;
                        MyLog.e((Throwable) e);
                        fileLock.release();
                        IOUtils.closeQuietly(randomAccessFile);
                        if (file2 == null) {
                        }
                        file2.delete();
                    } catch (Throwable th3) {
                        th = th3;
                        file2 = file;
                        fileLock.release();
                        IOUtils.closeQuietly(randomAccessFile);
                        if (file2 != null) {
                        }
                        throw th;
                    }
                }
                file2.delete();
            }
        }
    }

    public void send(List<String> list) {
        ClientReportUtil.sendFile(this.mContext, list);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setEventMap(HashMap<String, ArrayList<BaseClientReport>> hashMap) {
        this.mEventMap = hashMap;
    }

    public byte[] stringToBytes(String str) {
        byte[] parseKey;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!ClientReportLogicManager.getInstance(this.mContext).getConfig().isEventEncrypted()) {
            return XMStringUtils.getBytes(str);
        }
        String eventKeyWithDefault = ClientReportUtil.getEventKeyWithDefault(this.mContext);
        byte[] bytes = XMStringUtils.getBytes(str);
        if (!TextUtils.isEmpty(eventKeyWithDefault) && bytes != null && bytes.length > 1 && (parseKey = ClientReportUtil.parseKey(eventKeyWithDefault)) != null) {
            try {
                if (parseKey.length > 1) {
                    return DataCryptUtils.mipushEncrypt(parseKey, Base64.encode(bytes, 2));
                }
            } catch (Exception e) {
                MyLog.e((Throwable) e);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.nio.channels.FileLock} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: java.nio.channels.FileLock} */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v5 */
    /* JADX WARNING: type inference failed for: r4v9 */
    /* JADX WARNING: type inference failed for: r4v10 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: type inference failed for: r4v12 */
    /* JADX WARNING: type inference failed for: r4v14 */
    /* JADX WARNING: type inference failed for: r4v16 */
    /* JADX WARNING: type inference failed for: r4v17 */
    /* JADX WARNING: type inference failed for: r4v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
    /* JADX WARNING: Unknown variable types count: 2 */
    public void write(BaseClientReport[] baseClientReportArr) {
        ? r4;
        ? r42;
        FileLock fileLock;
        FileLock fileLock2;
        if (baseClientReportArr != null && baseClientReportArr.length > 0) {
            if (baseClientReportArr[0] != null) {
                String writeFileName = getWriteFileName(baseClientReportArr[0]);
                if (!TextUtils.isEmpty(writeFileName)) {
                    FileLock fileLock3 = null;
                    try {
                        File file = new File(writeFileName + ".lock");
                        IOUtils.createFileQuietly(file);
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        try {
                            FileLock lock = randomAccessFile.getChannel().lock();
                            for (BaseClientReport baseClientReport : baseClientReportArr) {
                                if (baseClientReport != null) {
                                    write2File(baseClientReportArr, writeFileName);
                                }
                            }
                            r42 = randomAccessFile;
                            if (lock != null) {
                                r42 = randomAccessFile;
                                if (lock.isValid()) {
                                    try {
                                        lock.release();
                                        r42 = randomAccessFile;
                                    } catch (IOException e) {
                                        e = e;
                                        fileLock = randomAccessFile;
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            fileLock2 = randomAccessFile;
                            try {
                                MyLog.e((Throwable) e);
                                r42 = fileLock2;
                                if (fileLock3 != null) {
                                    r42 = fileLock2;
                                    if (fileLock3.isValid()) {
                                        try {
                                            fileLock3.release();
                                            r42 = fileLock2;
                                        } catch (IOException e3) {
                                            e = e3;
                                            fileLock = fileLock2;
                                        }
                                    }
                                }
                                IOUtils.closeQuietly(r42);
                            } catch (Throwable th) {
                                th = th;
                                r4 = fileLock2;
                                try {
                                    fileLock3.release();
                                } catch (IOException e4) {
                                    MyLog.e((Throwable) e4);
                                }
                                IOUtils.closeQuietly(r4);
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        fileLock2 = fileLock3;
                        MyLog.e((Throwable) e);
                        r42 = fileLock2;
                        if (fileLock3 != null) {
                        }
                        IOUtils.closeQuietly(r42);
                    } catch (Throwable th2) {
                        th = th2;
                        r4 = fileLock3;
                        if (fileLock3 != null && fileLock3.isValid()) {
                            fileLock3.release();
                        }
                        IOUtils.closeQuietly(r4);
                        throw th;
                    }
                    IOUtils.closeQuietly(r42);
                }
                return;
            }
            return;
        }
        return;
        MyLog.e((Throwable) e);
        r42 = fileLock;
        IOUtils.closeQuietly(r42);
    }
}
