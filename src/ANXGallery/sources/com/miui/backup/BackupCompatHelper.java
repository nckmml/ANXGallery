package com.miui.backup;

import android.app.backup.BackupAgent;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.miui.core.SdkHelper;
import miui.app.backup.BackupManager;
import miui.app.backup.FullBackupAgent;
import miui.reflect.IllegalArgumentException;
import miui.reflect.Method;
import miui.reflect.NoSuchMethodException;

public class BackupCompatHelper {
    private static final String TAG = "BackupCompatHelper";

    private BackupCompatHelper() {
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.backup.MiuiBackupAgentImpl, android.app.backup.BackupAgent] */
    public static BackupAgent createBackupAgentImpl(IBackupAgentDelegate iBackupAgentDelegate) {
        if (SdkHelper.IS_MIUI) {
            return new MiuiBackupAgentImpl(iBackupAgentDelegate);
        }
        return null;
    }

    public static void invokeOnRestoreFile(BackupAgent backupAgent, ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3) {
        try {
            Class cls = SdkHelper.IS_MIUI ? FullBackupAgent.class : BackupAgent.class;
            Method.of(cls, "onRestoreFile", "(Landroid/os/ParcelFileDescriptor;JILjava/lang/String;Ljava/lang/String;JJ)V").invoke(cls, backupAgent, new Object[]{parcelFileDescriptor, Long.valueOf(j), Integer.valueOf(i), str, str2, Long.valueOf(j2), Long.valueOf(j3)});
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "NoSuchMethodException", e);
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "IllegalArgumentException", e2);
        } catch (Exception e3) {
            Log.e(TAG, "", e3);
        }
    }

    public static void setIsNeedBeKilled(Context context) {
        if (SdkHelper.IS_MIUI) {
            try {
                BackupManager backupManager = BackupManager.getBackupManager(context);
                if (backupManager != null) {
                    backupManager.setIsNeedBeKilled(true);
                }
            } catch (Exception e) {
                Log.e(TAG, "EncounterErrorWhenKillApp", e);
            }
        }
    }
}
