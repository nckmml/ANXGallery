package com.miui.gallery.net.download;

import android.os.AsyncTask;
import com.miui.gallery.net.download.ConnectionController;
import com.miui.gallery.net.download.Request;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class DownloadTask {
    private static final long RETRY_INTERVAL_MILLI = TimeUnit.SECONDS.toMillis(10);
    private CoreTask mCoreTask;
    private TaskInfo mTaskInfo;

    private class CoreTask extends AsyncTask<Void, Integer, Integer> {
        Request mRequest;

        CoreTask(Request request) {
            this.mRequest = request;
        }

        private void onComplete(int i) {
            Request request = this.mRequest;
            if (request != null) {
                Request.Listener listener = request.getListener();
                if (listener != null) {
                    listener.onComplete(i);
                }
                this.mRequest.setListener((Request.Listener) null);
                this.mRequest = null;
            }
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(DownloadTask.this.process(this.mRequest));
        }

        /* access modifiers changed from: protected */
        public void onCancelled(Integer num) {
            int intValue = num != null ? num.intValue() : -2;
            Log.d("DownloadTask", "process download cancelled %d", (Object) Integer.valueOf(intValue));
            onComplete(intValue);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            Log.d("DownloadTask", "process download finish %d", (Object) num);
            onComplete(num.intValue());
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Integer... numArr) {
            Request.Listener listener = this.mRequest.getListener();
            if (listener != null) {
                listener.onProgressUpdate(numArr[0].intValue());
            }
        }

        /* access modifiers changed from: package-private */
        public void publishProgress(int i) {
            if (this.mRequest.getListener() != null) {
                super.publishProgress(new Integer[]{Integer.valueOf(i)});
            }
        }
    }

    private static class TaskInfo {
        long mContentLength;
        MessageDigest mDigest;
        long mDownloadSize;

        private TaskInfo() {
        }
    }

    DownloadTask(Request request) {
        this.mCoreTask = new CoreTask(request);
    }

    private void configure(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
    }

    private static File getTempFile(File file) {
        String parent = file.getParent();
        return new File(parent, file.getName() + ".download");
    }

    private static boolean isRetryState(int i) {
        return i == 12 || i == 11;
    }

    private static OutputStream openOutputStream(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            if (file.exists()) {
                if (file.isDirectory()) {
                    Log.d("DownloadTask", "output file is a directory");
                    return null;
                }
                Log.w("DownloadTask", "output file will be overwritten");
            }
            File tempFile = getTempFile(file);
            if (tempFile.exists()) {
                Log.w("DownloadTask", "temp file exists, try delete");
                if (!tempFile.delete()) {
                    Log.w("DownloadTask", "temp file delete failed, will overwrite");
                }
            }
            try {
                return new FileOutputStream(tempFile);
            } catch (FileNotFoundException e) {
                Log.w("DownloadTask", (Throwable) e);
                return null;
            }
        } else {
            Log.d("DownloadTask", "create folder failed");
            return null;
        }
    }

    private void performProgressUpdate(byte[] bArr, int i) {
        int i2;
        long j = this.mTaskInfo.mDownloadSize;
        this.mTaskInfo.mDownloadSize += (long) i;
        if (this.mTaskInfo.mDigest != null) {
            this.mTaskInfo.mDigest.update(bArr, 0, i);
        }
        if (this.mTaskInfo.mContentLength > 0 && ((int) ((((double) j) / ((double) this.mTaskInfo.mContentLength)) * 100.0d)) != (i2 = (int) ((((double) this.mTaskInfo.mDownloadSize) / ((double) this.mTaskInfo.mContentLength)) * 100.0d))) {
            this.mCoreTask.publishProgress(i2);
        }
    }

    private int postProcess(int i) {
        if (i != 0) {
            File tempFile = getTempFile(this.mCoreTask.mRequest.getDestination());
            if (tempFile.exists() && !tempFile.delete()) {
                Log.d("DownloadTask", "delete tmp file failed %s", (Object) tempFile);
            }
        } else {
            File destination = this.mCoreTask.mRequest.getDestination();
            File tempFile2 = getTempFile(destination);
            if (!tempFile2.exists()) {
                Log.w("DownloadTask", "downloaded file missing");
                return 9;
            } else if (!tempFile2.renameTo(destination)) {
                Log.w("DownloadTask", "downloaded file rename failed");
                return 9;
            } else {
                Log.w("DownloadTask", "rename tmp file success");
            }
        }
        return i;
    }

    private int postTransferContent() {
        if (this.mCoreTask.mRequest.getVerifier() == null || this.mCoreTask.mRequest.getVerifier().verify(this.mTaskInfo.mDigest.digest())) {
            Log.d("DownloadTask", "verify success");
            return 0;
        }
        Log.d("DownloadTask", "verify fail");
        return 6;
    }

    private void preProcess(final Request request) {
        this.mTaskInfo = new TaskInfo();
        if (request.getListener() != null) {
            ThreadManager.getMainHandler().post(new Runnable() {
                public void run() {
                    Request.Listener listener = request.getListener();
                    if (listener != null) {
                        listener.onStart();
                    }
                }
            });
        }
    }

    private void preTransferContent(Request request) {
        Verifier verifier = request.getVerifier();
        if (verifier != null) {
            Log.d("DownloadTask", "need verify, try to get MessageDigest");
            this.mTaskInfo.mDigest = verifier.getInstance();
        }
    }

    /* access modifiers changed from: private */
    public int process(Request request) {
        int tryDownload;
        Log.d("DownloadTask", "start to download request[%s, %s]", request.getUri(), request.getDestination());
        preProcess(request);
        int maxRetryTimes = request.getMaxRetryTimes();
        int i = 0;
        do {
            tryDownload = tryDownload(request);
            if (!isRetryState(tryDownload)) {
                break;
            }
            Log.d("DownloadTask", "retry for %d", (Object) Integer.valueOf(tryDownload));
            try {
                Thread.sleep(RETRY_INTERVAL_MILLI, 0);
                i++;
            } catch (InterruptedException unused) {
                tryDownload = 5;
            }
        } while (i <= maxRetryTimes);
        return postProcess(tryDownload);
    }

    private void processHeader(HttpURLConnection httpURLConnection) {
        this.mTaskInfo.mContentLength = (long) httpURLConnection.getContentLength();
        Log.d("DownloadTask", "content length: %d", (Object) Long.valueOf(this.mTaskInfo.mContentLength));
    }

    private static int translateErrorCode(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    return 11;
                }
            }
        }
        return i2;
    }

    private static int translateResponseCode(int i) {
        if (i != 200) {
            Log.d("DownloadTask", "processing http code %d", (Object) Integer.valueOf(i));
            int i2 = i / 100;
            if (i2 == 3) {
                return 7;
            }
            if (i2 == 4) {
                return i == 408 ? 12 : 7;
            }
            if (i2 == 5) {
                return i == 504 ? 12 : 8;
            }
            if (i2 == 2) {
            }
            return 7;
        }
        Log.d("DownloadTask", "http status is ok");
        return 0;
    }

    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: type inference failed for: r0v19 */
    /* JADX WARNING: type inference failed for: r0v20 */
    /* JADX WARNING: type inference failed for: r0v21 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00e3 A[SYNTHETIC, Splitter:B:77:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00ed A[SYNTHETIC, Splitter:B:82:0x00ed] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0100 A[SYNTHETIC, Splitter:B:91:0x0100] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x010a A[SYNTHETIC, Splitter:B:96:0x010a] */
    private int tryDownload(Request request) {
        Throwable th;
        InputStream inputStream;
        OutputStream outputStream;
        ConnectionController.Holder<HttpURLConnection> open = ConnectionController.open(request.getUri(), request.getNetworkType());
        HttpURLConnection httpURLConnection = open.value;
        if (httpURLConnection == null) {
            Log.d("DownloadTask", "open connection failed");
            return translateErrorCode(open.reason);
        }
        ? r0 = 0;
        try {
            configure(httpURLConnection);
            httpURLConnection.connect();
            int translateResponseCode = translateResponseCode(httpURLConnection.getResponseCode());
            if (translateResponseCode != 0) {
                Log.d("DownloadTask", "response code not valid");
                httpURLConnection.disconnect();
                return translateResponseCode;
            }
            processHeader(httpURLConnection);
            inputStream = httpURLConnection.getInputStream();
            try {
                OutputStream openOutputStream = openOutputStream(request.getDestination());
                if (openOutputStream == null) {
                    Log.d("DownloadTask", "open output stream failed");
                    httpURLConnection.disconnect();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            Log.w("DownloadTask", (Throwable) e);
                        }
                    }
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (IOException e2) {
                            Log.w("DownloadTask", (Throwable) e2);
                        }
                    }
                    return 4;
                }
                r0 = openOutputStream;
                r0 = openOutputStream;
                preTransferContent(request);
                Log.d("DownloadTask", "start to transfer data");
                byte[] bArr = new byte[8192];
                int i = 0;
                while (!this.mCoreTask.isCancelled() && (i = inputStream.read(bArr)) != -1) {
                    openOutputStream.write(bArr, 0, i);
                    performProgressUpdate(bArr, i);
                }
                if (i == -1) {
                    Log.d("DownloadTask", "download success");
                    int postTransferContent = postTransferContent();
                    httpURLConnection.disconnect();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            Log.w("DownloadTask", (Throwable) e3);
                        }
                    }
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (IOException e4) {
                            Log.w("DownloadTask", (Throwable) e4);
                        }
                    }
                    return postTransferContent;
                }
                r0 = openOutputStream;
                r0 = openOutputStream;
                Log.d("DownloadTask", "cancelled, during download");
                r0 = openOutputStream;
                r0 = openOutputStream;
                httpURLConnection.disconnect();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        Log.w("DownloadTask", (Throwable) e5);
                    }
                }
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (IOException e6) {
                        Log.w("DownloadTask", (Throwable) e6);
                    }
                }
                return 5;
            } catch (IOException unused) {
                outputStream = r0;
                r0 = inputStream;
                try {
                    Log.w("DownloadTask", "tryDownload occur error.");
                    httpURLConnection.disconnect();
                    if (r0 != 0) {
                        try {
                            r0.close();
                        } catch (IOException e7) {
                            Log.w("DownloadTask", (Throwable) e7);
                        }
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e8) {
                            Log.w("DownloadTask", (Throwable) e8);
                        }
                    }
                    return 11;
                } catch (Throwable th2) {
                    InputStream inputStream2 = r0;
                    r0 = outputStream;
                    th = th2;
                    inputStream = inputStream2;
                    httpURLConnection.disconnect();
                    if (inputStream != null) {
                    }
                    if (r0 != 0) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                r0 = r0;
                httpURLConnection.disconnect();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e9) {
                        Log.w("DownloadTask", (Throwable) e9);
                    }
                }
                if (r0 != 0) {
                    try {
                        r0.close();
                    } catch (IOException e10) {
                        Log.w("DownloadTask", (Throwable) e10);
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            outputStream = null;
            Log.w("DownloadTask", "tryDownload occur error.");
            httpURLConnection.disconnect();
            if (r0 != 0) {
            }
            if (outputStream != null) {
            }
            return 11;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            httpURLConnection.disconnect();
            if (inputStream != null) {
            }
            if (r0 != 0) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean cancel(boolean z) {
        return this.mCoreTask.cancel(z);
    }

    /* access modifiers changed from: package-private */
    public void execute(Executor executor) {
        this.mCoreTask.executeOnExecutor(executor, new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public boolean isDone() {
        return this.mCoreTask.getStatus() == AsyncTask.Status.FINISHED;
    }
}
