package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface DiskCache {
    public static final DiskCache DUMMY = new DiskCache() {
        public File get(String str) {
            return null;
        }

        public Bitmap getBitmap(String str) {
            return null;
        }

        public boolean save(String str, Bitmap bitmap) throws IOException {
            return false;
        }

        public boolean save(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException {
            return false;
        }
    };

    File get(String str);

    Bitmap getBitmap(String str);

    boolean save(String str, Bitmap bitmap) throws IOException;

    boolean save(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException;
}
