package com.miui.gallery.util.uil;

import com.miui.gallery.Config;
import com.miui.gallery.imageloader.GalleryImageLoaderCache;
import com.miui.gallery.imageloader.ImageLoaderSamplingStatHelper;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nexstreaming.nexeditorsdk.nexProject;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PhotoBytesCache implements GalleryImageLoaderCache.IPhotoBytesCache {
    private BlobCache mBlobCache;
    private Object mLock;

    private static class Singleton {
        /* access modifiers changed from: private */
        public static PhotoBytesCache sInstance = new PhotoBytesCache(new File(Config.BigPhotoConfig.getCacheDir()), nexProject.kAutoThemeTransitionDuration, 838860800);
    }

    private PhotoBytesCache(File file, int i, int i2) {
        this.mLock = new Object();
        if (file != null) {
            try {
                if (!file.exists()) {
                    FileUtils.createFolder(file, true);
                }
            } catch (Exception e) {
                Log.e("PhotoBytesCache", (Throwable) e);
                recordInitError(e, file);
                return;
            }
        }
        this.mBlobCache = new BlobCache(file + "/" + "photo_blob", i, i2, false, 4);
    }

    public static PhotoBytesCache getInstance() {
        return Singleton.sInstance;
    }

    private void recordInitError(Exception exc, File file) {
        Map<String, String> generatorCommonParams = ImageLoaderSamplingStatHelper.generatorCommonParams();
        generatorCommonParams.put("cacheDir", file.getAbsolutePath());
        generatorCommonParams.put("exception", exc.toString());
        ImageLoaderSamplingStatHelper.recordErrorEvent("pager_decode_bitmap", "pager_photo_cache_init_error", generatorCommonParams);
    }

    public byte[] getData(String str) {
        byte[] bArr;
        synchronized (this.mLock) {
            bArr = null;
            try {
                if (this.mBlobCache != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    bArr = this.mBlobCache.lookup(MiscUtil.crc64Long(str.getBytes()));
                    Log.d("PhotoBytesCache", "getData [%s] cost %d", str, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public boolean putData(String str, byte[] bArr) {
        synchronized (this.mLock) {
            if (this.mBlobCache != null) {
                try {
                    Log.d("PhotoBytesCache", "Save data size %d", (Object) Integer.valueOf(bArr.length));
                    this.mBlobCache.insert(MiscUtil.crc64Long(str.getBytes()), bArr);
                } catch (IOException e) {
                    Log.e("PhotoBytesCache", (Throwable) e);
                    return false;
                }
            }
        }
        return true;
    }
}
