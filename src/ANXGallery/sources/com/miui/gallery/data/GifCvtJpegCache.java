package com.miui.gallery.data;

import com.miui.gallery.util.Utils;
import java.io.File;

public class GifCvtJpegCache {
    private int mCurrentLoopPosition = 0;
    private final String[] mDestinations;
    private final boolean mInitSuccess;
    private final int mSize;
    private final String[] mSources;

    public GifCvtJpegCache(File file, int i) {
        boolean z = true;
        Utils.assertTrue(i > 0);
        this.mInitSuccess = (file == null || (!file.isDirectory() && !file.mkdirs()) || !file.canWrite()) ? false : z;
        this.mSize = i;
        this.mSources = new String[i];
        this.mDestinations = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            String[] strArr = this.mDestinations;
            strArr[i2] = new File(file, "gif_cvt" + i2 + ".jpg").getAbsolutePath();
        }
    }
}
