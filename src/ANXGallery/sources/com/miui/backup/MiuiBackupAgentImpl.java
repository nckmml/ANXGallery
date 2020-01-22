package com.miui.backup;

import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import miui.app.backup.BackupMeta;
import miui.app.backup.FullBackupAgent;

public class MiuiBackupAgentImpl extends FullBackupAgent {
    private static final String TAG = "MiuiBackupAgentImpl";
    private IBackupAgentDelegate mDelegate;

    public MiuiBackupAgentImpl(IBackupAgentDelegate iBackupAgentDelegate) {
        this.mDelegate = iBackupAgentDelegate;
    }

    private void consumeBytesFromPipe(ParcelFileDescriptor parcelFileDescriptor, long j) throws IOException {
        Throwable th;
        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        try {
            byte[] bArr = new byte[32768];
            long j2 = j;
            while (true) {
                if (j2 <= 0) {
                    break;
                }
                int read = fileInputStream.read(bArr, 0, j2 > ((long) bArr.length) ? bArr.length : (int) j2);
                if (read <= 0) {
                    Log.w(TAG, "Incomplete read: expected " + j2 + " but got " + (j - j2));
                    break;
                }
                j2 -= (long) read;
            }
            fileInputStream.close();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        MiuiBackupAgentImpl.super.attachBaseContext(context);
        this.mDelegate.attachBaseContext(context);
    }

    /* access modifiers changed from: protected */
    public int getVersion(int i) {
        return this.mDelegate.getVersion(i);
    }

    /* access modifiers changed from: protected */
    public int onAttachRestore(BackupMeta backupMeta, ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
        return this.mDelegate.onAttachRestore(BackupMetaUtils.translate(backupMeta), parcelFileDescriptor, str);
    }

    public void onCreate() {
        this.mDelegate.onCreate();
    }

    /* access modifiers changed from: protected */
    public int onDataRestore(BackupMeta backupMeta, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        return this.mDelegate.onDataRestore(BackupMetaUtils.translate(backupMeta), parcelFileDescriptor);
    }

    public void onDestroy() {
        this.mDelegate.onDestroy();
    }

    /* access modifiers changed from: protected */
    public int onFullBackup(ParcelFileDescriptor parcelFileDescriptor, int i) throws IOException {
        return this.mDelegate.onFullBackup(parcelFileDescriptor, i);
    }

    /* access modifiers changed from: protected */
    public void onOriginalAttachesRestore(BackupMeta backupMeta, ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3) throws IOException {
        Log.w(TAG, "intercept original onRestoreFile, type=" + i + " domain=" + str + " path=" + str2);
        if (i == 1) {
            consumeBytesFromPipe(parcelFileDescriptor, j);
        }
    }

    /* access modifiers changed from: protected */
    public int onRestoreEnd(BackupMeta backupMeta) throws IOException {
        return this.mDelegate.onRestoreEnd(BackupMetaUtils.translate(backupMeta));
    }

    /* access modifiers changed from: protected */
    public int tarAttaches(String str, FullBackupDataOutput fullBackupDataOutput, int i) throws IOException {
        MiuiBackupAgentImpl.super.tarAttaches(str, fullBackupDataOutput, i);
        return this.mDelegate.tarAttaches(str, fullBackupDataOutput, i);
    }
}
