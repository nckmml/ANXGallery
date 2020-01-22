package com.android.internal;

import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;

public class FilesCompat {
    public static int setPermissions(String str, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT < 21) {
            return FilesUtilsV19.setPermissions(str, i, i2, i3);
        }
        try {
            Os.chmod(str, i);
            if (i2 < 0 && i3 < 0) {
                return 0;
            }
            try {
                Os.chown(str, i2, i3);
                return 0;
            } catch (ErrnoException e) {
                Log.w("FilesCompat", "chown failed " + str, e);
                return e.errno;
            }
        } catch (ErrnoException e2) {
            Log.w("FilesCompat", "chmod failed " + str, e2);
            return e2.errno;
        }
    }
}
