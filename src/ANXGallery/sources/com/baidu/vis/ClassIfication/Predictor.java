package com.baidu.vis.ClassIfication;

import android.content.Context;
import android.util.Log;
import com.baidu.vis.ClassIfication.SDKExceptions;
import java.io.File;

public class Predictor {
    private static final String TAG = "ClassIfication";
    private static boolean isInited = false;
    public static SDKExceptions.loadLicenseLibraryError loadLicenseLibraryError = null;
    public static SDKExceptions.loadNativeLibraryError loadNativeLibraryError = null;
    private static int mAuthorityStatus = 0;
    private static Predictor mInstance = null;
    private static final boolean sCheckAuthority = true;

    public static synchronized Predictor getInstance() {
        Predictor predictor;
        synchronized (Predictor.class) {
            if (mInstance == null) {
                mInstance = new Predictor();
            }
            predictor = mInstance;
        }
        return predictor;
    }

    public static synchronized int init(Context context, String str) throws SDKExceptions.loadNativeLibraryError, SDKExceptions.loadLicenseLibraryError {
        int i;
        synchronized (Predictor.class) {
            if (loadNativeLibraryError != null) {
                throw loadNativeLibraryError;
            } else if (loadLicenseLibraryError == null) {
                i = mAuthorityStatus;
            } else {
                throw loadLicenseLibraryError;
            }
        }
        return i;
    }

    public static synchronized int initModel(Context context, String str) throws SDKExceptions.NoSDCardPermission, SDKExceptions.MissingModleFileInAssetFolder, SDKExceptions.IlleagleCpuArch, SDKExceptions.IlleagleLicense {
        int nativeModelInit;
        synchronized (Predictor.class) {
            if (mAuthorityStatus == 0) {
                Util.copyAssets(context, str);
                nativeModelInit = nativeModelInit(new File(context.getExternalFilesDir((String) null), str).getAbsolutePath(), context);
                if (nativeModelInit == 0) {
                    isInited = true;
                }
            } else {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            }
        }
        return nativeModelInit;
    }

    public static synchronized int initModelWithPath(Context context, String str) throws SDKExceptions.IlleagleLicense, SDKExceptions.NV21BytesLengthNotMatch {
        int nativeModelInit;
        synchronized (Predictor.class) {
            if (mAuthorityStatus == 0) {
                Util.checkFile(str);
                nativeModelInit = nativeModelInit(str, context);
                if (nativeModelInit == 0) {
                    isInited = true;
                }
            } else {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            }
        }
        return nativeModelInit;
    }

    public static synchronized int modelRelease() throws SDKExceptions.IlleagleLicense, SDKExceptions.NotInit {
        int nativeRelease;
        synchronized (Predictor.class) {
            if (mAuthorityStatus != 0) {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            } else if (isInited) {
                nativeRelease = nativeRelease();
            } else {
                Log.d(TAG, "model not init");
                throw new SDKExceptions.NotInit();
            }
        }
        return nativeRelease;
    }

    public static native int nativeModelInit(String str, Context context);

    public static native Response nativePredict(Object obj, String str, byte[] bArr, int i, int i2);

    public static native int nativeRelease();

    public static synchronized Response predict(Object obj) throws SDKExceptions.IlleagleLicense, SDKExceptions.NotInit {
        Response nativePredict;
        synchronized (Predictor.class) {
            if (mAuthorityStatus != 0) {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            } else if (isInited) {
                nativePredict = nativePredict(obj, "", (byte[]) null, 0, 0);
            } else {
                Log.d(TAG, "model not init");
                throw new SDKExceptions.NotInit();
            }
        }
        return nativePredict;
    }

    public static synchronized Response predict(String str) throws SDKExceptions.IlleagleLicense, SDKExceptions.NotInit, SDKExceptions.NV21BytesLengthNotMatch {
        Response nativePredict;
        synchronized (Predictor.class) {
            if (mAuthorityStatus != 0) {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            } else if (isInited) {
                Util.checkFile(str);
                nativePredict = nativePredict((Object) null, str, (byte[]) null, 0, 0);
            } else {
                Log.d(TAG, "model not init");
                throw new SDKExceptions.NotInit();
            }
        }
        return nativePredict;
    }

    public static synchronized Response predict(byte[] bArr, int i, int i2) throws SDKExceptions.IlleagleLicense, SDKExceptions.NotInit, SDKExceptions.NV21BytesLengthNotMatch {
        Response nativePredict;
        synchronized (Predictor.class) {
            if (mAuthorityStatus != 0) {
                Log.d(TAG, "license error : " + mAuthorityStatus);
                throw new SDKExceptions.IlleagleLicense();
            } else if (!isInited) {
                Log.d(TAG, "model not init");
                throw new SDKExceptions.NotInit();
            } else if (bArr.length != 0) {
                nativePredict = nativePredict((Object) null, "", bArr, i, i2);
            } else {
                Log.d(TAG, "NV21Bytes Length NotMatch");
                throw new SDKExceptions.NV21BytesLengthNotMatch();
            }
        }
        return nativePredict;
    }
}
