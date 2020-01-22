package com.nostra13.universalimageloader.cache.disc.impl.ext;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LruDiskCache implements DiskCache {
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    protected int bufferSize = 32768;
    protected DiskLruCache cache;
    protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
    protected int compressQuality = 100;
    protected final FileNameGenerator fileNameGenerator;
    private File mCacheDir;
    private int mCacheMaxFileCount;
    private long mCacheMaxSize;
    private File reserveCacheDir;

    public LruDiskCache(File file, File file2, FileNameGenerator fileNameGenerator2, long j, int i) throws IOException {
        if (file != null) {
            int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i2 < 0) {
                throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
            } else if (i < 0) {
                throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
            } else if (fileNameGenerator2 != null) {
                j = i2 == 0 ? Long.MAX_VALUE : j;
                i = i == 0 ? Reader.READ_DONE : i;
                this.reserveCacheDir = file2;
                this.fileNameGenerator = fileNameGenerator2;
                this.mCacheDir = file;
                this.mCacheMaxSize = j;
                this.mCacheMaxFileCount = i;
            } else {
                throw new IllegalArgumentException("fileNameGenerator argument must be not null");
            }
        } else {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        }
    }

    private void ensureInitialized() throws IOException {
        if (this.cache == null) {
            synchronized (this) {
                if (this.cache == null) {
                    initCache(this.mCacheDir, this.reserveCacheDir, this.mCacheMaxSize, this.mCacheMaxFileCount);
                }
            }
        }
    }

    private void initCache(File file, File file2, long j, int i) throws IOException {
        try {
            this.cache = DiskLruCache.open(file, 1, 1, j, i);
        } catch (IOException e) {
            L.e(e);
            if (file2 != null) {
                initCache(file2, (File) null, j, i);
            }
            if (this.cache == null) {
                throw e;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.File} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0031  */
    public File get(String str) {
        Throwable th;
        DiskLruCache.Snapshot snapshot;
        ? r0 = 0;
        try {
            ensureInitialized();
            snapshot = this.cache.get(getKey(str));
            if (snapshot != null) {
                try {
                    r0 = snapshot.getFile(0);
                } catch (IOException e) {
                    e = e;
                    try {
                        L.e(e);
                        if (snapshot != null) {
                            snapshot.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        r0 = snapshot;
                        th = th3;
                        if (r0 != 0) {
                        }
                        throw th;
                    }
                }
            }
            if (snapshot != null) {
                snapshot.close();
            }
            return r0;
        } catch (IOException e2) {
            e = e2;
            snapshot = null;
            L.e(e);
            if (snapshot != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
    }

    public Bitmap getBitmap(String str) {
        return null;
    }

    public String getKey(String str) {
        return this.fileNameGenerator.generate(str);
    }

    public boolean save(String str, Bitmap bitmap) throws IOException {
        ensureInitialized();
        DiskLruCache.Editor edit = this.cache.edit(getKey(str));
        if (edit == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(edit.newOutputStream(0), this.bufferSize);
        try {
            boolean compress = bitmap.compress(this.compressFormat, this.compressQuality, bufferedOutputStream);
            if (compress) {
                edit.commit();
            } else {
                edit.abort();
            }
            return compress;
        } finally {
            IoUtils.closeSilently(bufferedOutputStream);
        }
    }

    public boolean save(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException {
        ensureInitialized();
        DiskLruCache.Editor edit = this.cache.edit(getKey(str));
        if (edit == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(edit.newOutputStream(0), this.bufferSize);
        try {
            boolean copyStream = IoUtils.copyStream(inputStream, bufferedOutputStream, copyListener, this.bufferSize);
            IoUtils.closeSilently(bufferedOutputStream);
            if (copyStream) {
                edit.commit();
            } else {
                edit.abort();
            }
            return copyStream;
        } catch (Throwable th) {
            IoUtils.closeSilently(bufferedOutputStream);
            edit.abort();
            throw th;
        }
    }
}
