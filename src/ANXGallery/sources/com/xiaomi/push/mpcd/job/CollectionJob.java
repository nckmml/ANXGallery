package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.misc.ByteUtils;
import com.xiaomi.channel.commonutils.misc.JobMutualExclusor;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.push.mpcd.CDActionProvider;
import com.xiaomi.push.mpcd.CDActionProviderHolder;
import com.xiaomi.push.mpcd.CDataHelper;
import com.xiaomi.push.mpcd.Constants;
import com.xiaomi.xmpush.thrift.ClientCollectionType;
import com.xiaomi.xmpush.thrift.DataCollectionItem;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public abstract class CollectionJob extends ScheduledJobManager.Job {
    protected Context context;
    protected int period;

    public CollectionJob(Context context2, int i) {
        this.period = i;
        this.context = context2;
    }

    public static void writeItemToFile(Context context2, DataCollectionItem dataCollectionItem) {
        CDActionProvider cDActionProvider = CDActionProviderHolder.getInstance().getCDActionProvider();
        String regSecret = cDActionProvider == null ? "" : cDActionProvider.getRegSecret();
        if (!TextUtils.isEmpty(regSecret) && !TextUtils.isEmpty(dataCollectionItem.getContent())) {
            writeItemToFile(context2, dataCollectionItem, regSecret);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.nio.channels.FileLock} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:32|33|38|39|(0)|46|47|23|48|49) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|(3:16|17|(2:19|20))|21|22) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:50|51|52|(2:56|57)|58|59|60) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x008b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x009f */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0082 A[SYNTHETIC, Splitter:B:41:0x0082] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x008b=Splitter:B:46:0x008b, B:38:0x007d=Splitter:B:38:0x007d, B:21:0x005f=Splitter:B:21:0x005f, B:58:0x009f=Splitter:B:58:0x009f} */
    private static void writeItemToFile(Context context2, DataCollectionItem dataCollectionItem, String str) {
        RandomAccessFile randomAccessFile;
        FileLock fileLock;
        BufferedOutputStream bufferedOutputStream;
        byte[] encryptData = CDataHelper.encryptData(str, XmPushThriftSerializeUtils.convertThriftObjectToBytes(dataCollectionItem));
        if (encryptData != null && encryptData.length != 0) {
            synchronized (Constants.cDataLock4Thread) {
                ? r0 = 0;
                try {
                    File file = new File(context2.getExternalFilesDir((String) null), "push_cdata.lock");
                    IOUtils.createFileQuietly(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    try {
                        fileLock = randomAccessFile.getChannel().lock();
                    } catch (IOException e) {
                        e = e;
                        bufferedOutputStream = null;
                        try {
                            e.printStackTrace();
                            if (r0 != 0) {
                            }
                            IOUtils.closeQuietly(bufferedOutputStream);
                            IOUtils.closeQuietly(randomAccessFile);
                        } catch (Throwable th) {
                            th = th;
                            fileLock = r0;
                            r0 = bufferedOutputStream;
                            if (fileLock != null && fileLock.isValid()) {
                                fileLock.release();
                            }
                            IOUtils.closeQuietly(r0);
                            IOUtils.closeQuietly(randomAccessFile);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileLock = null;
                        fileLock.release();
                        IOUtils.closeQuietly(r0);
                        IOUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(context2.getExternalFilesDir((String) null), "push_cdata.data"), true));
                        try {
                            bufferedOutputStream.write(ByteUtils.parseInt(encryptData.length));
                            bufferedOutputStream.write(encryptData);
                            bufferedOutputStream.flush();
                            if (fileLock != null) {
                                if (fileLock.isValid()) {
                                    fileLock.release();
                                }
                            }
                            IOUtils.closeQuietly(bufferedOutputStream);
                        } catch (IOException e2) {
                            e = e2;
                            r0 = fileLock;
                            e.printStackTrace();
                            if (r0 != 0) {
                            }
                            IOUtils.closeQuietly(bufferedOutputStream);
                            IOUtils.closeQuietly(randomAccessFile);
                        } catch (Throwable th3) {
                            th = th3;
                            r0 = bufferedOutputStream;
                            fileLock.release();
                            IOUtils.closeQuietly(r0);
                            IOUtils.closeQuietly(randomAccessFile);
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedOutputStream = null;
                        r0 = fileLock;
                        e.printStackTrace();
                        if (r0 != 0) {
                            if (r0.isValid()) {
                                r0.release();
                            }
                        }
                        IOUtils.closeQuietly(bufferedOutputStream);
                        IOUtils.closeQuietly(randomAccessFile);
                    } catch (Throwable th4) {
                        th = th4;
                        fileLock.release();
                        IOUtils.closeQuietly(r0);
                        IOUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    bufferedOutputStream = null;
                    randomAccessFile = null;
                    e.printStackTrace();
                    if (r0 != 0) {
                    }
                    IOUtils.closeQuietly(bufferedOutputStream);
                    IOUtils.closeQuietly(randomAccessFile);
                } catch (Throwable th5) {
                    th = th5;
                    fileLock = null;
                    randomAccessFile = null;
                    fileLock.release();
                    IOUtils.closeQuietly(r0);
                    IOUtils.closeQuietly(randomAccessFile);
                    throw th;
                }
                IOUtils.closeQuietly(randomAccessFile);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkPeriodAndRecord() {
        return JobMutualExclusor.checkPeriodAndRecordWithFileLock(this.context, String.valueOf(getJobId()), (long) this.period);
    }

    /* access modifiers changed from: protected */
    public boolean checkPermission() {
        return true;
    }

    public abstract String collectInfo();

    public abstract ClientCollectionType getCollectionType();

    public void run() {
        if (checkPeriodAndRecord()) {
            CDActionProvider cDActionProvider = CDActionProviderHolder.getInstance().getCDActionProvider();
            String regSecret = cDActionProvider == null ? "" : cDActionProvider.getRegSecret();
            if (!TextUtils.isEmpty(regSecret) && checkPermission()) {
                String collectInfo = collectInfo();
                if (!TextUtils.isEmpty(collectInfo)) {
                    DataCollectionItem dataCollectionItem = new DataCollectionItem();
                    dataCollectionItem.setContent(collectInfo);
                    dataCollectionItem.setCollectedAt(System.currentTimeMillis());
                    dataCollectionItem.setCollectionType(getCollectionType());
                    writeItemToFile(this.context, dataCollectionItem, regSecret);
                }
            }
        }
    }
}
