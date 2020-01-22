package com.miui.gallery.assistant.algorithm;

import android.graphics.Bitmap;
import com.baidu.vis.ClassIfication.Predictor;
import com.baidu.vis.ClassIfication.Response;
import com.baidu.vis.ClassIfication.SDKExceptions;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.library.LibraryManager;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;

public class BaiduSceneFilterAlgorithm extends Algorithm {
    private volatile boolean mIsInitialized = false;

    BaiduSceneFilterAlgorithm() {
        super(1005);
    }

    /* access modifiers changed from: protected */
    public void onDestroyAlgorithm() {
        try {
            Predictor.getInstance();
            int modelRelease = Predictor.modelRelease();
            if (modelRelease != 0) {
                Log.e(this.TAG, "Model release fail:%d", (Object) Integer.valueOf(modelRelease));
            }
        } catch (SDKExceptions.IlleagleLicense e) {
            e.printStackTrace();
        } catch (SDKExceptions.NotInit e2) {
            e2.printStackTrace();
        } catch (Error e3) {
            reportAlgorithmError(e3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onInitAlgorithm() {
        try {
            Predictor.getInstance();
            Predictor.init(GalleryApp.sGetAndroidContext(), "Gallery");
            try {
                String libraryItemPath = LibraryManager.getInstance().getLibraryItemPath("model.mlm");
                if (FileUtils.isFileExist(libraryItemPath)) {
                    Predictor.getInstance();
                    if (Predictor.initModelWithPath(GalleryApp.sGetAndroidContext(), libraryItemPath) == 0) {
                        this.mIsInitialized = true;
                        return true;
                    }
                }
            } catch (SDKExceptions.NV21BytesLengthNotMatch e) {
                e.printStackTrace();
            } catch (SDKExceptions.IlleagleLicense e2) {
                e2.printStackTrace();
            } catch (Error e3) {
                reportAlgorithmError(e3);
                return false;
            }
            return false;
        } catch (SDKExceptions.loadNativeLibraryError e4) {
            Log.e(this.TAG, (Throwable) e4);
            return false;
        } catch (SDKExceptions.loadLicenseLibraryError e5) {
            Log.e(this.TAG, (Throwable) e5);
            return false;
        } catch (Exception e6) {
            reportAlgorithmError(e6);
            return false;
        }
    }

    public synchronized Response predict(Bitmap bitmap) {
        if (this.mIsInitialized) {
            try {
                Predictor.getInstance();
                return Predictor.predict((Object) bitmap);
            } catch (SDKExceptions.IlleagleLicense e) {
                e.printStackTrace();
            } catch (SDKExceptions.NotInit e2) {
                e2.printStackTrace();
            } catch (Error e3) {
                reportAlgorithmError(e3);
            }
        }
        return null;
    }
}
