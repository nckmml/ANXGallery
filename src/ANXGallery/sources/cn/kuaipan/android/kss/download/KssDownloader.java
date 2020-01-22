package cn.kuaipan.android.kss.download;

import android.text.TextUtils;
import cn.kuaipan.android.exception.ErrorHelper;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.exception.KscRuntimeException;
import cn.kuaipan.android.http.IKscDecoder;
import cn.kuaipan.android.http.IKscTransferListener;
import cn.kuaipan.android.http.KscHttpRequest;
import cn.kuaipan.android.http.KscHttpResponse;
import cn.kuaipan.android.http.KscHttpTransmitter;
import cn.kuaipan.android.http.KssTransferStopper;
import cn.kuaipan.android.kss.IKssDownloadRequestResult;
import cn.kuaipan.android.kss.KssDef;
import cn.kuaipan.android.kss.RC4Encoder;
import cn.kuaipan.android.utils.FileUtils;
import com.xiaomi.micloudsdk.stat.MiCloudNetEventStatInjector;
import com.xiaomi.micloudsdk.stat.MiCloudStatManager;
import com.xiaomi.micloudsdk.stat.NetFailedStatParam;
import com.xiaomi.micloudsdk.stat.NetSuccessStatParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpEntity;

public class KssDownloader implements KssDef {
    private final KscHttpTransmitter mTransmitter;

    public KssDownloader(KscHttpTransmitter kscHttpTransmitter) {
        this.mTransmitter = kscHttpTransmitter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0191, code lost:
        r29.getRequest().abort();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0199, code lost:
        releaseResponse(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x019e, code lost:
        r4.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01aa, code lost:
        r4 = r33.obtainRecorder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        r23 = r5;
        r29 = r15;
        r12 = null;
        r5 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0124, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0127, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0128, code lost:
        r5 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x012c, code lost:
        r29 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0130, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0131, code lost:
        r5 = r35;
        r29 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0135, code lost:
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0137, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0138, code lost:
        r5 = r35;
        r29 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013e, code lost:
        r29 = r15;
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0167, code lost:
        if (r29 != null) goto L_0x0169;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0169, code lost:
        r29.getRequest().abort();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0171, code lost:
        releaseResponse(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0174, code lost:
        if (r4 != null) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0176, code lost:
        r4.recycle();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01aa A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00be A[SYNTHETIC, Splitter:B:41:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00da A[Catch:{ Exception -> 0x0127, all -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f6 A[Catch:{ Exception -> 0x0127, all -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0124 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:47:0x00d5] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:27:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:18:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x017f A[EDGE_INSN: B:119:0x017f->B:91:0x017f ?: BREAK  
    EDGE_INSN: B:120:0x017f->B:91:0x017f ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0184 A[SYNTHETIC, Splitter:B:93:0x0184] */
    private void download(IKssDownloadRequestResult iKssDownloadRequestResult, KssAccessor kssAccessor, LoadMap loadMap, KssTransferStopper kssTransferStopper, AtomicInteger atomicInteger) throws InterruptedException, InvalidKeyException, KscException {
        KscHttpRequest kscHttpRequest;
        KscHttpResponse kscHttpResponse;
        long j;
        KscHttpResponse kscHttpResponse2;
        long j2;
        Throwable error;
        KssAccessor kssAccessor2 = kssAccessor;
        LoadMap loadMap2 = loadMap;
        LoadRecorder obtainRecorder = loadMap.obtainRecorder();
        loop0:
        while (obtainRecorder != null) {
            if (!Thread.interrupted()) {
                long start = obtainRecorder.getSpace().getStart();
                String[] blockUrls = iKssDownloadRequestResult.getBlockUrls(start);
                long blockStart = start - loadMap2.getBlockStart(start);
                if (blockUrls == null || blockUrls.length <= 0) {
                    throw new IllegalArgumentException("No available urls to download.");
                }
                RC4Encoder rC4Encoder = new RC4Encoder(iKssDownloadRequestResult.getSecureKey());
                int i = 0;
                while (true) {
                    if (i >= blockUrls.length) {
                        AtomicInteger atomicInteger2 = atomicInteger;
                        break;
                    } else if (!Thread.interrupted()) {
                        try {
                            rC4Encoder.init();
                            String str = blockUrls[i];
                            KscHttpRequest kscHttpRequest2 = new KscHttpRequest(KscHttpRequest.HttpMethod.GET, str, (IKscDecoder) rC4Encoder, (IKscTransferListener) null);
                            if (blockStart > 0) {
                                try {
                                    kscHttpRequest2.getRequest().addHeader("Range", "bytes=" + blockStart + "-");
                                } catch (Exception e) {
                                    e = e;
                                    j = blockStart;
                                    kscHttpRequest = kscHttpRequest2;
                                    AtomicInteger atomicInteger3 = atomicInteger;
                                    kscHttpResponse2 = null;
                                    try {
                                        ErrorHelper.handleInterruptException(e);
                                        if (ErrorHelper.isStopByCallerException(e) || i >= blockUrls.length - 1) {
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        kscHttpResponse = kscHttpResponse2;
                                        if (kscHttpRequest != null) {
                                        }
                                        if (obtainRecorder != null) {
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                }
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            j = blockStart;
                            kscHttpResponse = this.mTransmitter.execute(kscHttpRequest2, 4, kssTransferStopper);
                            try {
                                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                                if (kscHttpResponse.getResponse() != null) {
                                    HttpEntity entity = kscHttpResponse.getResponse().getEntity();
                                    if (entity != null) {
                                        j2 = entity.getContentLength();
                                        int statusCode = kscHttpResponse.getStatusCode();
                                        String str2 = "";
                                        error = kscHttpResponse.getError();
                                        if (error != null) {
                                            str2 = error.getClass().getSimpleName();
                                        }
                                        kscHttpRequest = kscHttpRequest2;
                                        MiCloudStatManager.getInstance().addHttpEvent(str, currentTimeMillis2, j2, statusCode, str2);
                                        if (error != null) {
                                            NetSuccessStatParam netSuccessStatParam = r13;
                                            String str3 = str;
                                            MiCloudNetEventStatInjector instance = MiCloudNetEventStatInjector.getInstance();
                                            NetSuccessStatParam netSuccessStatParam2 = new NetSuccessStatParam(str3, currentTimeMillis, currentTimeMillis2, j2, statusCode, 0);
                                            instance.addNetSuccessEvent(netSuccessStatParam);
                                        } else {
                                            MiCloudNetEventStatInjector instance2 = MiCloudNetEventStatInjector.getInstance();
                                            String str4 = str;
                                            NetFailedStatParam netFailedStatParam = r13;
                                            NetFailedStatParam netFailedStatParam2 = new NetFailedStatParam(str4, currentTimeMillis, currentTimeMillis2, error, 0);
                                            instance2.addNetFailedEvent(netFailedStatParam);
                                        }
                                        ErrorHelper.throwError(kscHttpResponse);
                                        save(kscHttpResponse, kssAccessor2, obtainRecorder, atomicInteger);
                                        loadMap2.verify(kssAccessor2, true);
                                        releaseResponse(kscHttpResponse);
                                        if (obtainRecorder == null) {
                                            obtainRecorder.recycle();
                                        }
                                    }
                                }
                                j2 = 0;
                                int statusCode2 = kscHttpResponse.getStatusCode();
                                String str22 = "";
                                error = kscHttpResponse.getError();
                                if (error != null) {
                                }
                                kscHttpRequest = kscHttpRequest2;
                                try {
                                    MiCloudStatManager.getInstance().addHttpEvent(str, currentTimeMillis2, j2, statusCode2, str22);
                                    if (error != null) {
                                    }
                                    ErrorHelper.throwError(kscHttpResponse);
                                    save(kscHttpResponse, kssAccessor2, obtainRecorder, atomicInteger);
                                    loadMap2.verify(kssAccessor2, true);
                                    releaseResponse(kscHttpResponse);
                                    if (obtainRecorder == null) {
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                } catch (Throwable th3) {
                                }
                            } catch (Exception e3) {
                                e = e3;
                                AtomicInteger atomicInteger4 = atomicInteger;
                                kscHttpResponse2 = kscHttpResponse;
                                kscHttpRequest = kscHttpRequest2;
                                ErrorHelper.handleInterruptException(e);
                                if (ErrorHelper.isStopByCallerException(e) || i >= blockUrls.length - 1) {
                                }
                            } catch (Throwable th4) {
                            }
                        } catch (Exception e4) {
                            e = e4;
                            j = blockStart;
                            AtomicInteger atomicInteger5 = atomicInteger;
                            kscHttpResponse2 = null;
                            kscHttpRequest = null;
                            ErrorHelper.handleInterruptException(e);
                            if (ErrorHelper.isStopByCallerException(e) || i >= blockUrls.length - 1) {
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            kscHttpResponse = null;
                            kscHttpRequest = null;
                            if (kscHttpRequest != null) {
                            }
                            if (obtainRecorder != null) {
                            }
                            throw th;
                        }
                    } else {
                        throw new InterruptedException();
                    }
                    i++;
                    blockStart = j;
                }
                throw KscException.newException(e, kscHttpResponse2 != null ? "No response." : kscHttpResponse2.dump());
            }
            throw new InterruptedException();
        }
    }

    private void releaseResponse(KscHttpResponse kscHttpResponse) throws InterruptedException {
        if (kscHttpResponse != null) {
            try {
                kscHttpResponse.release();
            } catch (Throwable th) {
                InterruptedException isInterrupted = ErrorHelper.isInterrupted(th);
                if (isInterrupted != null) {
                    throw isInterrupted;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    private void save(KscHttpResponse kscHttpResponse, KssAccessor kssAccessor, LoadRecorder loadRecorder, AtomicInteger atomicInteger) throws IllegalStateException, IOException {
        boolean z = false;
        try {
            InputStream content = kscHttpResponse.getContent();
            if (content != null) {
                byte[] bArr = new byte[8192];
                boolean z2 = false;
                while (true) {
                    try {
                        int read = content.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        z2 = true;
                        if (read > 0 && kssAccessor.write(bArr, 0, read, loadRecorder) < read) {
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = z2;
                        if (z) {
                            atomicInteger.set(3);
                        }
                        throw th;
                    }
                }
                if (z2) {
                    atomicInteger.set(3);
                    return;
                }
                return;
            }
            throw new KscRuntimeException(500008, "Not meet exception, but no response.\n" + kscHttpResponse.dump());
        } catch (Throwable th2) {
            th = th2;
            if (z) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f A[Catch:{ IOException -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009e A[Catch:{ InvalidKeyException -> 0x00c5, KscException -> 0x00b1, all -> 0x00f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010f A[SYNTHETIC, Splitter:B:81:0x010f] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00d3 A[SYNTHETIC] */
    public void download(File file, boolean z, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, IKssDownloadRequestResult iKssDownloadRequestResult) throws KscException, InterruptedException {
        LoadMap loadMap;
        KInfo kInfo;
        boolean z2;
        KssAccessor kssAccessor;
        long totalSize;
        AtomicInteger atomicInteger;
        boolean z3 = false;
        KssAccessor kssAccessor2 = null;
        try {
            long totalSize2 = iKssDownloadRequestResult.getTotalSize();
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            } else if (!z || file.isDirectory() || file.length() > totalSize2) {
                if (!FileUtils.deletes(file)) {
                    throw new SecurityException("Failed delete target file. Can't download to dest path: " + file);
                }
            }
            loadMap = new LoadMap(iKssDownloadRequestResult, iKscTransferListener);
            try {
                File infoFile = KInfo.getInfoFile(file);
                kInfo = new KInfo(infoFile);
                try {
                    if (infoFile.exists()) {
                        kInfo.load();
                        if (TextUtils.equals(kInfo.getHash(), iKssDownloadRequestResult.getHash())) {
                            z2 = kInfo.loadToMap(loadMap);
                            if (!z2 && file.exists()) {
                                loadMap.initSize(file.length());
                            }
                            kssAccessor = new KssAccessor(file);
                            loadMap.verify(kssAccessor, false);
                            totalSize = iKssDownloadRequestResult.getTotalSize();
                            if (file.length() != totalSize) {
                                kssAccessor.inflate(totalSize);
                            }
                            try {
                                atomicInteger = new AtomicInteger(3);
                                while (true) {
                                    z3 = loadMap.isCompleted();
                                    if (!z3) {
                                        long modifyTime = iKssDownloadRequestResult.getModifyTime();
                                        if (modifyTime > 0) {
                                            file.setLastModified(modifyTime);
                                        }
                                        try {
                                            kssAccessor.close();
                                        } catch (Throwable unused) {
                                        }
                                        if (z3) {
                                            kInfo.delete();
                                            return;
                                        }
                                        kInfo.setHash(iKssDownloadRequestResult.getHash());
                                        kInfo.setLoadMap(loadMap);
                                        kInfo.save();
                                        return;
                                    } else if (!Thread.interrupted()) {
                                        download(iKssDownloadRequestResult, kssAccessor, loadMap, kssTransferStopper, atomicInteger);
                                        atomicInteger.set(3);
                                    } else {
                                        throw new InterruptedException();
                                    }
                                }
                            } catch (InvalidKeyException e) {
                                throw KscException.newException(e, "Failed when download kss block.");
                            } catch (KscException e2) {
                                if (!ErrorHelper.isNetworkException(e2) || atomicInteger.decrementAndGet() < 0) {
                                    throw e2;
                                }
                                Thread.sleep(5000);
                            } catch (Throwable th) {
                                th = th;
                                kssAccessor2 = kssAccessor;
                                if (kssAccessor2 != null) {
                                    try {
                                        kssAccessor2.close();
                                    } catch (Throwable unused2) {
                                    }
                                }
                                if (kInfo != null) {
                                    if (z3) {
                                        kInfo.delete();
                                    } else if (loadMap != null) {
                                        kInfo.setHash(iKssDownloadRequestResult.getHash());
                                        kInfo.setLoadMap(loadMap);
                                        kInfo.save();
                                    }
                                }
                                throw th;
                            }
                            throw e2;
                        }
                    }
                    z2 = false;
                    loadMap.initSize(file.length());
                    try {
                        kssAccessor = new KssAccessor(file);
                        try {
                            loadMap.verify(kssAccessor, false);
                            totalSize = iKssDownloadRequestResult.getTotalSize();
                            if (file.length() != totalSize) {
                            }
                            atomicInteger = new AtomicInteger(3);
                            while (true) {
                                z3 = loadMap.isCompleted();
                                if (!z3) {
                                }
                            }
                            throw e2;
                        } catch (IOException e3) {
                            e = e3;
                            kssAccessor2 = kssAccessor;
                            throw KscException.newException(e, "Local IO error, when prepare kss download.");
                        }
                    } catch (IOException e4) {
                        e = e4;
                        throw KscException.newException(e, "Local IO error, when prepare kss download.");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (kssAccessor2 != null) {
                    }
                    if (kInfo != null) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                kInfo = null;
                if (kssAccessor2 != null) {
                }
                if (kInfo != null) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            loadMap = null;
            kInfo = null;
            if (kssAccessor2 != null) {
            }
            if (kInfo != null) {
            }
            throw th;
        }
    }
}
