package com.baidu.vis.ClassIfication;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import com.baidu.vis.ClassIfication.SDKExceptions;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Util {
    private static final String TAG = "Predictor";

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    public static Bitmap bytes2Bitmap(byte[] bArr, int i, int i2, Bitmap.Config config) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        return createBitmap;
    }

    public static void checkFile(String str) throws SDKExceptions.NV21BytesLengthNotMatch {
        if (!new File(str).exists()) {
            throw new SDKExceptions.NV21BytesLengthNotMatch();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:20|21|22|23|24|25|26|(2:27|(1:29)(1:73))|(2:31|32)|33|34|76) */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00bf, code lost:
        throw new com.baidu.vis.ClassIfication.SDKExceptions.MissingModleFileInAssetFolder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0072 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x00ba */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009f A[SYNTHETIC, Splitter:B:51:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a9 A[SYNTHETIC, Splitter:B:58:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ae A[SYNTHETIC, Splitter:B:62:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    public static void copyAssets(Context context, String str) throws SDKExceptions.NoSDCardPermission, SDKExceptions.MissingModleFileInAssetFolder, SDKExceptions.IlleagleCpuArch {
        FileOutputStream fileOutputStream;
        String str2 = Build.CPU_ABI;
        if (str2.contains("v7a") || str2.contains("v8a")) {
            AssetManager assets = context.getAssets();
            InputStream open = assets.open(str);
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                InputStream inputStream = null;
                if (new File(context.getExternalFilesDir((String) null), str).exists()) {
                    Log.d(TAG, "NN model on SD card");
                    return;
                }
                try {
                    InputStream open2 = assets.open(str);
                    try {
                        fileOutputStream = new FileOutputStream(new File(context.getExternalFilesDir((String) null), str));
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = null;
                        inputStream = open2;
                        try {
                            Log.e(nexExportFormat.TAG_FORMAT_TAG, "Failed to copy asset file: " + str, e);
                            if (inputStream != null) {
                            }
                            if (fileOutputStream == null) {
                            }
                            fileOutputStream.close();
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                        inputStream = open2;
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = open2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        if (open2 != null) {
                            open2.close();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        inputStream = open2;
                        Log.e(nexExportFormat.TAG_FORMAT_TAG, "Failed to copy asset file: " + str, e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (fileOutputStream == null) {
                            return;
                        }
                        fileOutputStream.close();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = open2;
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream = null;
                    Log.e(nexExportFormat.TAG_FORMAT_TAG, "Failed to copy asset file: " + str, e);
                    if (inputStream != null) {
                    }
                    if (fileOutputStream == null) {
                    }
                    fileOutputStream.close();
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    if (inputStream != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
                fileOutputStream.close();
                return;
            }
            throw new SDKExceptions.NoSDCardPermission();
        }
        throw new SDKExceptions.IlleagleCpuArch();
    }
}
