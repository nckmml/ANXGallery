package com.xiaomi.channel.commonutils.file;

import android.content.Context;
import java.io.File;
import java.io.IOException;

public abstract class FileLockerWorker implements Runnable {
    private Context mContext;
    private File mFile;
    private Runnable mRunnable;

    private FileLockerWorker(Context context, File file) {
        this.mContext = context;
        this.mFile = file;
    }

    public static void runMutiProcessJob(Context context, File file, final Runnable runnable) {
        new FileLockerWorker(context, file) {
            /* access modifiers changed from: protected */
            public void doWork(Context context) {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }.run();
    }

    /* access modifiers changed from: protected */
    public abstract void doWork(Context context);

    public final void run() {
        FileLocker fileLocker = null;
        try {
            if (this.mFile == null) {
                this.mFile = new File(this.mContext.getFilesDir(), "default_locker");
            }
            fileLocker = FileLocker.lock(this.mContext, this.mFile);
            if (this.mRunnable != null) {
                this.mRunnable.run();
            }
            doWork(this.mContext);
            if (fileLocker == null) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (fileLocker == null) {
                return;
            }
        } catch (Throwable th) {
            if (fileLocker != null) {
                fileLocker.unlock();
            }
            throw th;
        }
        fileLocker.unlock();
    }
}
