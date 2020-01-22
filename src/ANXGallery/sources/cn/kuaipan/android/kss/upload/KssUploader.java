package cn.kuaipan.android.kss.upload;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import cn.kuaipan.android.exception.ErrorHelper;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.exception.KscRuntimeException;
import cn.kuaipan.android.exception.ServerException;
import cn.kuaipan.android.exception.ServerMsgException;
import cn.kuaipan.android.exception.SessionExpiredException;
import cn.kuaipan.android.http.DecoderInputStream;
import cn.kuaipan.android.http.IKscTransferListener;
import cn.kuaipan.android.http.KscHttpRequest;
import cn.kuaipan.android.http.KscHttpResponse;
import cn.kuaipan.android.http.KscHttpTransmitter;
import cn.kuaipan.android.http.KssTransferStopper;
import cn.kuaipan.android.kss.FileTranceListener;
import cn.kuaipan.android.kss.IKssUploadRequestResult;
import cn.kuaipan.android.kss.KssDef;
import cn.kuaipan.android.kss.RC4Encoder;
import cn.kuaipan.android.kss.upload.UploadFileInfo;
import cn.kuaipan.android.utils.ApiDataHelper;
import cn.kuaipan.android.utils.Encode;
import cn.kuaipan.android.utils.IObtainable;
import cn.kuaipan.android.utils.RandomFileInputStream;
import com.nexstreaming.nexeditorsdk.nexEngine;
import com.xiaomi.micloudsdk.stat.MiCloudStatManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CRC32;
import org.apache.http.HttpEntity;

public class KssUploader implements KssDef {
    public static volatile boolean sBreakForUT;
    private final CRC32 CRC32 = new CRC32();
    private final byte[] CRC_BUF = new byte[8192];
    private long mChunkSize = 65536;
    private final UploadTaskStore mTaskStore;
    private final KscHttpTransmitter mTransmitter;

    public KssUploader(KscHttpTransmitter kscHttpTransmitter, UploadTaskStore uploadTaskStore) {
        this.mTaskStore = uploadTaskStore;
        this.mTransmitter = kscHttpTransmitter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010d, code lost:
        updatePos(r0, r21, r17, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011a, code lost:
        if (r17 < r8.mChunkSize) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011c, code lost:
        r8.mChunkSize = java.lang.Math.min(MAX_CHUNKSIZE, r8.mChunkSize << 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0155, code lost:
        return r0;
     */
    private UploadChunkInfo _uploadChunk(Uri uri, long j, RandomFileInputStream randomFileInputStream, RC4Encoder rC4Encoder, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, KssUploadInfo kssUploadInfo) throws KscException, InterruptedException, IOException {
        long j2;
        boolean z;
        RandomFileInputStream randomFileInputStream2 = randomFileInputStream;
        RC4Encoder rC4Encoder2 = rC4Encoder;
        IKscTransferListener iKscTransferListener2 = iKscTransferListener;
        AtomicInteger atomicInteger = new AtomicInteger(3);
        loop0:
        while (true) {
            UploadChunkInfo uploadChunkInfo = null;
            while (true) {
                if (atomicInteger.get() < 0) {
                    break loop0;
                }
                randomFileInputStream.reset();
                long min = Math.min(4194304, ((long) randomFileInputStream.available()) + j);
                String str = "KssUploader";
                if (min < 0) {
                    Log.d(str, "blockSize<0, adjust blockSize to 4M");
                    j2 = 4194304;
                } else {
                    j2 = min;
                }
                long min2 = Math.min(Math.min(this.mChunkSize, j2 - j), kssUploadInfo.getMaxChunkSize());
                ServerExpect serverExpect = kssUploadInfo.mExpectInfo;
                if (serverExpect != null) {
                    serverExpect.validate();
                    String str2 = str;
                    if (serverExpect.nextChunkSize > 0) {
                        long min3 = Math.min(min2, serverExpect.nextChunkSize);
                        str = str2;
                        Log.v(str, String.format("Adjust chunk size from %d to %d", new Object[]{Long.valueOf(min2), Long.valueOf(min3)}));
                        min2 = min3;
                    } else {
                        str = str2;
                    }
                    if (serverExpect.uploadDelay > 0) {
                        Log.v(str, String.format("Sleeping for delay %d(s)", new Object[]{Integer.valueOf(serverExpect.uploadDelay)}));
                        Thread.sleep((long) (serverExpect.uploadDelay * 1000));
                    }
                }
                long j3 = min2;
                Uri build = uri.buildUpon().appendQueryParameter("body_sum", String.valueOf((long) getCRC(new DecoderInputStream(randomFileInputStream2, rC4Encoder2, 8192), j3))).build();
                randomFileInputStream.reset();
                DecoderInputStream decoderInputStream = new DecoderInputStream(randomFileInputStream2, rC4Encoder2, 8192);
                if (iKscTransferListener2 != null) {
                    try {
                        iKscTransferListener2.setSendPos(0);
                    } catch (KscException e) {
                        e = e;
                        z = true;
                    }
                }
                long j4 = j3;
                z = true;
                try {
                    uploadChunkInfo = doUpload(build, decoderInputStream, j3, iKscTransferListener, kssTransferStopper);
                    if (uploadChunkInfo.isContinue()) {
                        break loop0;
                    } else if (uploadChunkInfo.isComplete()) {
                        break loop0;
                    } else if (!uploadChunkInfo.canRetry() || atomicInteger.decrementAndGet() < 0) {
                        return uploadChunkInfo;
                    } else {
                        Log.d(str, "upload needChunkRetry: " + uploadChunkInfo.stat);
                    }
                } catch (KscException e2) {
                    e = e2;
                    if (!ErrorHelper.isNetworkException(e) || atomicInteger.decrementAndGet() < 0) {
                        throw e;
                    }
                    this.mChunkSize = Math.max(65536, this.mChunkSize >> (z ? 1 : 0));
                    if (!Thread.interrupted()) {
                        Thread.sleep(5000);
                    } else {
                        throw new InterruptedException();
                    }
                }
            }
            Thread.sleep(5000);
        }
        throw e;
    }

    private void deleteUploadInfo(int i) throws InterruptedException {
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        if (uploadTaskStore != null) {
            uploadTaskStore.removeUploadInfo(i);
        }
    }

    private UploadChunkInfo doUpload(Uri uri, InputStream inputStream, long j, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper) throws KscException, InterruptedException {
        HttpEntity entity;
        Map<String, Object> map = null;
        try {
            KscHttpRequest kscHttpRequest = new KscHttpRequest(KscHttpRequest.HttpMethod.POST, uri, map, iKscTransferListener);
            kscHttpRequest.setPostEntity(new KssInputStreamEntity(inputStream, j));
            long currentTimeMillis = System.currentTimeMillis();
            KscHttpResponse execute = this.mTransmitter.execute(kscHttpRequest, 4, kssTransferStopper);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            long j2 = 0;
            if (!(execute.getResponse() == null || (entity = execute.getResponse().getEntity()) == null)) {
                j2 = entity.getContentLength();
            }
            long j3 = j2;
            int statusCode = execute.getStatusCode();
            String str = "";
            if (execute.getError() != null) {
                str = execute.getError().getClass().getSimpleName();
            }
            MiCloudStatManager.getInstance().addHttpEvent(uri.toString(), currentTimeMillis2, j3, statusCode, str);
            ErrorHelper.throwError(execute);
            if (statusCode == 200) {
                map = ApiDataHelper.contentToMap(execute);
                UploadChunkInfo uploadChunkInfo = new UploadChunkInfo(map);
                uploadChunkInfo.expect_info = ServerExpect.getServerExpect(execute);
                return uploadChunkInfo;
            }
            ServerException serverException = new ServerException(statusCode, execute.dump());
            Log.w("KssUploader", "Exception in doUpload", serverException);
            throw serverException;
        } finally {
            if (map != null && (map instanceof IObtainable)) {
                ((IObtainable) map).recycle();
            }
        }
    }

    private synchronized int getCRC(InputStream inputStream, long j) throws IOException {
        int read;
        this.CRC32.reset();
        while (j > 0 && (read = inputStream.read(this.CRC_BUF, 0, (int) Math.min((long) this.CRC_BUF.length, j))) >= 0) {
            j -= (long) read;
            this.CRC32.update(this.CRC_BUF, 0, read);
        }
        return (int) this.CRC32.getValue();
    }

    private UploadChunkInfoPersist getUploadPos(int i) throws InterruptedException {
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        if (uploadTaskStore == null) {
            return null;
        }
        return uploadTaskStore.getUploadPos(i);
    }

    private static void updatePos(UploadChunkInfo uploadChunkInfo, long j, long j2, long j3) {
        if (uploadChunkInfo != null) {
            if (uploadChunkInfo.isComplete()) {
                uploadChunkInfo.next_pos = j3;
                uploadChunkInfo.left_bytes = 0;
            } else if (uploadChunkInfo.isContinue()) {
                long j4 = j + j2;
                long j5 = j3 - j4;
                if (uploadChunkInfo.next_pos != j4 || uploadChunkInfo.left_bytes != j5) {
                    Log.w("KssUploader", "Chunk pos is (" + uploadChunkInfo.next_pos + ", " + uploadChunkInfo.left_bytes + "), but in process is (" + j4 + ", " + j5 + ")");
                    uploadChunkInfo.next_pos = j4;
                    uploadChunkInfo.left_bytes = j5;
                }
            } else {
                uploadChunkInfo.next_pos = j;
                uploadChunkInfo.left_bytes = j3 - j;
            }
        }
    }

    private void updateUploadInfo(int i, KssUploadInfo kssUploadInfo, UploadChunkInfoPersist uploadChunkInfoPersist) {
        UploadTaskStore uploadTaskStore = this.mTaskStore;
        if (uploadTaskStore != null) {
            uploadTaskStore.updateUploadInfo(i, kssUploadInfo, uploadChunkInfoPersist);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01f8, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01fd, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01fe, code lost:
        r21 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0201, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0202, code lost:
        r21 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0226 A[SYNTHETIC, Splitter:B:105:0x0226] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0207 A[EDGE_INSN: B:115:0x0207->B:86:0x0207 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0075 A[Catch:{ Throwable -> 0x0211, all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01fd A[ExcHandler: all (th java.lang.Throwable), PHI: r14 
      PHI: (r14v3 cn.kuaipan.android.utils.RandomFileInputStream) = (r14v1 cn.kuaipan.android.utils.RandomFileInputStream), (r14v1 cn.kuaipan.android.utils.RandomFileInputStream), (r14v1 cn.kuaipan.android.utils.RandomFileInputStream), (r14v4 cn.kuaipan.android.utils.RandomFileInputStream), (r14v1 cn.kuaipan.android.utils.RandomFileInputStream), (r14v1 cn.kuaipan.android.utils.RandomFileInputStream) binds: [B:22:0x008d, B:23:?, B:29:0x009a, B:35:0x00b3, B:25:0x0092, B:26:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:22:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0209 A[SYNTHETIC, Splitter:B:87:0x0209] */
    private void uploadBlock(int i, File file, FileTranceListener fileTranceListener, KssTransferStopper kssTransferStopper, KssUploadInfo kssUploadInfo, int i2) throws KscException, InterruptedException {
        long j;
        String str;
        long j2;
        long j3;
        IKssUploadRequestResult requestResult;
        String str2;
        RandomFileInputStream randomFileInputStream;
        RandomFileInputStream randomFileInputStream2;
        String str3;
        AtomicInteger atomicInteger;
        String str4;
        String str5;
        RandomFileInputStream randomFileInputStream3;
        UploadChunkInfo uploadChunkInfo;
        AtomicInteger atomicInteger2;
        UploadChunkInfo uploadChunkInfo2;
        String str6;
        long j4;
        IKssUploadRequestResult iKssUploadRequestResult;
        String str7;
        long j5;
        AtomicInteger atomicInteger3;
        String str8;
        IKscTransferListener iKscTransferListener;
        int i3 = i;
        FileTranceListener fileTranceListener2 = fileTranceListener;
        KssUploadInfo kssUploadInfo2 = kssUploadInfo;
        int i4 = i2;
        UploadChunkInfoPersist uploadPos = getUploadPos(i);
        if (uploadPos != null) {
            str = uploadPos.upload_id;
            if (!TextUtils.isEmpty(str)) {
                j = uploadPos.pos;
                j2 = j - (j % 65536);
                j3 = ((long) (i4 + 1)) * 4194304;
                if (j2 >= j3 || j2 < ((long) i4) * 4194304) {
                    j2 = ((long) i4) * 4194304;
                }
                long min = Math.min(file.length(), j3);
                requestResult = kssUploadInfo.getRequestResult();
                str2 = "KssUploader";
                Log.d(str2, "RC4 key:" + Arrays.toString(requestResult.getSecureKey()));
                atomicInteger = new AtomicInteger(3);
                str4 = "";
                str5 = str;
                randomFileInputStream3 = null;
                while (true) {
                    try {
                        if (atomicInteger.get() < 0) {
                            break;
                        }
                        if (randomFileInputStream3 != null) {
                            randomFileInputStream3.close();
                        }
                        RC4Encoder rC4Encoder = new RC4Encoder(requestResult.getSecureKey());
                        String str9 = str4;
                        RandomFileInputStream randomFileInputStream4 = new RandomFileInputStream(file);
                        try {
                            randomFileInputStream4.moveToPos(j2);
                            if (fileTranceListener2 != null) {
                                fileTranceListener2.setSendPos(j2);
                            }
                            uploadChunkInfo = new UploadChunkInfo(j2 % 4194304, min - j2, str5);
                            String str10 = str2;
                            IKssUploadRequestResult iKssUploadRequestResult2 = requestResult;
                            while (true) {
                                if (uploadChunkInfo.next_pos >= min) {
                                    atomicInteger2 = atomicInteger;
                                    uploadChunkInfo2 = uploadChunkInfo;
                                    str6 = "Exception in uploadBlock";
                                    j4 = j3;
                                    iKssUploadRequestResult = iKssUploadRequestResult2;
                                    str7 = str9;
                                    j5 = 0;
                                    break;
                                }
                                String str11 = "Exception in uploadBlock";
                                j5 = 0;
                                if (uploadChunkInfo.left_bytes <= 0) {
                                    atomicInteger2 = atomicInteger;
                                    uploadChunkInfo2 = uploadChunkInfo;
                                    j4 = j3;
                                    iKssUploadRequestResult = iKssUploadRequestResult2;
                                    str6 = str11;
                                    str7 = str9;
                                    break;
                                } else if (!Thread.interrupted()) {
                                    if (fileTranceListener2 == null) {
                                        atomicInteger3 = atomicInteger;
                                        iKscTransferListener = null;
                                    } else {
                                        iKscTransferListener = fileTranceListener2.getChunkListaner(uploadChunkInfo.next_pos + j2);
                                        atomicInteger3 = atomicInteger;
                                    }
                                    UploadChunkInfo uploadChunkInfo3 = uploadChunkInfo;
                                    RandomFileInputStream randomFileInputStream5 = randomFileInputStream4;
                                    long j6 = j2;
                                    RC4Encoder rC4Encoder2 = rC4Encoder;
                                    String str12 = str10;
                                    randomFileInputStream = randomFileInputStream4;
                                    iKssUploadRequestResult = iKssUploadRequestResult2;
                                    str8 = str12;
                                    RC4Encoder rC4Encoder3 = rC4Encoder;
                                    str6 = str11;
                                    long j7 = j3;
                                    try {
                                        uploadChunkInfo = uploadChunk(randomFileInputStream5, rC4Encoder2, iKscTransferListener, kssTransferStopper, kssUploadInfo, i2, uploadChunkInfo3);
                                        if (uploadChunkInfo == null) {
                                            throw new KscRuntimeException(500008, "Return chunkInfo is null");
                                        } else if (uploadChunkInfo.isContinue()) {
                                            UploadChunkInfoPersist uploadChunkInfoPersist = new UploadChunkInfoPersist();
                                            uploadChunkInfoPersist.pos = (((long) i4) * 4194304) + uploadChunkInfo.next_pos;
                                            uploadChunkInfoPersist.upload_id = uploadChunkInfo.upload_id;
                                            updateUploadInfo(i3, kssUploadInfo2, uploadChunkInfoPersist);
                                            if (sBreakForUT) {
                                                Log.d(str8, "break for UT");
                                                Thread.currentThread().interrupt();
                                            }
                                            rC4Encoder = rC4Encoder3;
                                            atomicInteger = atomicInteger3;
                                            j2 = j6;
                                            j3 = j7;
                                            iKssUploadRequestResult2 = iKssUploadRequestResult;
                                            fileTranceListener2 = fileTranceListener;
                                            RandomFileInputStream randomFileInputStream6 = randomFileInputStream;
                                            str10 = str8;
                                            randomFileInputStream4 = randomFileInputStream6;
                                        } else if (uploadChunkInfo.isComplete()) {
                                            UploadChunkInfoPersist uploadChunkInfoPersist2 = new UploadChunkInfoPersist();
                                            j4 = j7;
                                            uploadChunkInfoPersist2.pos = Math.min(j4, file.length());
                                            str7 = str9;
                                            uploadChunkInfoPersist2.upload_id = str7;
                                            IKssUploadRequestResult.Block block = iKssUploadRequestResult.getBlock(i4);
                                            block.meta = uploadChunkInfo.commit_meta;
                                            block.exist = true;
                                            updateUploadInfo(i3, kssUploadInfo2, uploadChunkInfoPersist2);
                                        } else {
                                            str7 = str9;
                                            j4 = j7;
                                            if (uploadChunkInfo.isSessionExpired()) {
                                                kssUploadInfo.markBroken();
                                                deleteUploadInfo(i);
                                                SessionExpiredException sessionExpiredException = new SessionExpiredException(uploadChunkInfo.stat);
                                                Log.w(str8, str6, sessionExpiredException);
                                                throw sessionExpiredException;
                                            }
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        if (randomFileInputStream != null) {
                                            try {
                                                randomFileInputStream.close();
                                            } catch (Throwable unused) {
                                            }
                                        }
                                        throw th;
                                    }
                                } else {
                                    RandomFileInputStream randomFileInputStream7 = randomFileInputStream4;
                                    throw new InterruptedException();
                                }
                            }
                            String str13 = str10;
                            randomFileInputStream = randomFileInputStream4;
                            str8 = str13;
                            uploadChunkInfo = uploadChunkInfo2;
                            if (uploadChunkInfo.isComplete()) {
                                randomFileInputStream3 = randomFileInputStream;
                                break;
                            } else if (!uploadChunkInfo.needBlockRetry() || atomicInteger3.decrementAndGet() <= 0) {
                                ServerMsgException serverMsgException = new ServerMsgException(200, uploadChunkInfo.stat);
                                Log.w(str8, str6, serverMsgException);
                                kssUploadInfo.markBroken();
                                deleteUploadInfo(i);
                            } else {
                                Log.d(str8, "upload needBlockRetry: " + uploadChunkInfo.stat);
                                str4 = str7;
                                j3 = j4;
                                requestResult = iKssUploadRequestResult;
                                str2 = str8;
                                randomFileInputStream3 = randomFileInputStream;
                                atomicInteger = atomicInteger3;
                                fileTranceListener2 = fileTranceListener;
                                str5 = str4;
                                j2 = j5;
                            }
                        } catch (Throwable th2) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        randomFileInputStream = randomFileInputStream3;
                        if (randomFileInputStream != null) {
                        }
                        throw th;
                    }
                }
                if (randomFileInputStream3 == null) {
                    try {
                        randomFileInputStream3.close();
                        return;
                    } catch (Throwable unused2) {
                        return;
                    }
                } else {
                    return;
                }
            }
        } else {
            str = null;
        }
        j = 0;
        j2 = j - (j % 65536);
        j3 = ((long) (i4 + 1)) * 4194304;
        j2 = ((long) i4) * 4194304;
        long min2 = Math.min(file.length(), j3);
        requestResult = kssUploadInfo.getRequestResult();
        str2 = "KssUploader";
        Log.d(str2, "RC4 key:" + Arrays.toString(requestResult.getSecureKey()));
        try {
            atomicInteger = new AtomicInteger(3);
            str4 = "";
            str5 = str;
            randomFileInputStream3 = null;
            while (true) {
                if (atomicInteger.get() < 0) {
                }
                Log.d(str8, "upload needBlockRetry: " + uploadChunkInfo.stat);
                str4 = str7;
                j3 = j4;
                requestResult = iKssUploadRequestResult;
                str2 = str8;
                randomFileInputStream3 = randomFileInputStream;
                atomicInteger = atomicInteger3;
                fileTranceListener2 = fileTranceListener;
                str5 = str4;
                j2 = j5;
            }
            if (randomFileInputStream3 == null) {
            }
        } catch (Throwable th4) {
            th = th4;
            randomFileInputStream = null;
            if (randomFileInputStream != null) {
            }
            throw th;
        }
    }

    private void uploadBlock(int i, File file, FileTranceListener fileTranceListener, KssTransferStopper kssTransferStopper, KssUploadInfo kssUploadInfo, boolean z, int i2) throws KscException, InterruptedException {
        if (kssUploadInfo != null) {
            verifyBlock(file, kssUploadInfo.getFileInfo(), i2);
            IKssUploadRequestResult.Block block = kssUploadInfo.getRequestResult().getBlock(i2);
            if (block == null) {
                throw new KscRuntimeException(500008, "Block should not be null");
            } else if (!block.isComplete()) {
                uploadBlock(i, file, fileTranceListener, kssTransferStopper, kssUploadInfo, i2);
            } else if (fileTranceListener != null) {
                fileTranceListener.setSendPos(Math.min(((long) (i2 + 1)) * 4194304, file.length()));
            }
        } else {
            throw new IllegalArgumentException("The KssUploadInfo can not be empty.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3 A[LOOP:0: B:5:0x0018->B:32:0x00b3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b7  */
    private UploadChunkInfo uploadChunk(RandomFileInputStream randomFileInputStream, RC4Encoder rC4Encoder, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, KssUploadInfo kssUploadInfo, int i, UploadChunkInfo uploadChunkInfo) throws KscException, InterruptedException {
        RandomFileInputStream randomFileInputStream2 = randomFileInputStream;
        int i2 = i;
        UploadChunkInfo uploadChunkInfo2 = uploadChunkInfo;
        IKssUploadRequestResult requestResult = kssUploadInfo.getRequestResult();
        String[] nodeUrls = requestResult.getNodeUrls();
        if (nodeUrls == null || nodeUrls.length <= 0) {
            throw new IllegalArgumentException("No available urls.");
        }
        int i3 = 0;
        UploadChunkInfo uploadChunkInfo3 = null;
        while (i3 < nodeUrls.length) {
            if (!Thread.interrupted()) {
                try {
                    randomFileInputStream2.moveToPos((((long) i2) * 4194304) + uploadChunkInfo2.next_pos);
                    randomFileInputStream2.mark(nexEngine.ExportHEVCMainTierLevel61);
                    Uri.Builder buildUpon = Uri.parse(nodeUrls[i3] + "/upload_block_chunk").buildUpon();
                    buildUpon.appendQueryParameter("chunk_pos", String.valueOf(uploadChunkInfo2.next_pos));
                    if (!TextUtils.isEmpty(uploadChunkInfo2.upload_id)) {
                        buildUpon.appendQueryParameter("upload_id", uploadChunkInfo2.upload_id);
                    } else {
                        buildUpon.appendQueryParameter("file_meta", requestResult.getFileMeta());
                        buildUpon.appendQueryParameter("block_meta", requestResult.getBlock(i2).meta);
                    }
                    UploadChunkInfo _uploadChunk = _uploadChunk(buildUpon.build(), uploadChunkInfo2.next_pos, randomFileInputStream, rC4Encoder, iKscTransferListener, kssTransferStopper, kssUploadInfo);
                    try {
                        try {
                            kssUploadInfo.mExpectInfo = _uploadChunk.expect_info;
                            return _uploadChunk;
                        } catch (Exception e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        KssUploadInfo kssUploadInfo2 = kssUploadInfo;
                        uploadChunkInfo3 = _uploadChunk;
                        ErrorHelper.handleInterruptException(e);
                        if (ErrorHelper.isStopByCallerException(e) || i3 >= nodeUrls.length - 1) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    KssUploadInfo kssUploadInfo3 = kssUploadInfo;
                    ErrorHelper.handleInterruptException(e);
                    if (ErrorHelper.isStopByCallerException(e) || i3 >= nodeUrls.length - 1) {
                        throw KscException.newException(e, "Failed when upload a kss chunk.");
                    }
                    i3++;
                }
            } else {
                throw new InterruptedException();
            }
        }
        return uploadChunkInfo3;
    }

    private static void verifyBlock(File file, UploadFileInfo uploadFileInfo, int i) throws KscException, InterruptedException {
        FileInputStream fileInputStream;
        UploadFileInfo.BlockInfo blockInfo = uploadFileInfo.getBlockInfo(i);
        long j = ((long) i) * 4194304;
        int min = (int) Math.min(file.length() - j, 4194304);
        if (min == blockInfo.size) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    if (fileInputStream.skip(j) != j) {
                        throw new KscException(403002, "File size has changed.");
                    } else if (TextUtils.equals(Encode.SHA1Encode(fileInputStream, min), blockInfo.sha1)) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable unused) {
                        }
                    } else {
                        throw new KscException(403002, "Block has changed.");
                    }
                } catch (IOException e) {
                    e = e;
                    try {
                        throw KscException.newException(e, (String) null);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            fileInputStream.close();
                        } catch (Throwable unused2) {
                        }
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
                throw KscException.newException(e, (String) null);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                fileInputStream.close();
                throw th;
            }
        } else {
            throw new KscException(403002, "Block size has changed.");
        }
    }

    public void upload(File file, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper, int i, KssUploadInfo kssUploadInfo) throws KscException, InterruptedException {
        FileTranceListener fileTranceListener;
        if (iKscTransferListener != null) {
            fileTranceListener = new FileTranceListener(iKscTransferListener, true);
            iKscTransferListener.setSendTotal(file.length());
        } else {
            fileTranceListener = null;
        }
        int i2 = 0;
        while (!Thread.interrupted()) {
            Log.d("KssUploader", "upload blockIndex: " + i2);
            uploadBlock(i, file, fileTranceListener, kssTransferStopper, kssUploadInfo, true, i2);
            i2++;
            if (kssUploadInfo.isCompleted()) {
                return;
            }
        }
        throw new InterruptedException();
    }
}
