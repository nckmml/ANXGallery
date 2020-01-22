package com.miui.gallery.util;

import android.graphics.Bitmap;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.MediaFileUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileCompressTask implements ThreadPool.Job {
    private String mCompressFolder;
    private List<String> mCompressItems;
    /* access modifiers changed from: private */
    public OnCompressListener mCompressListener;
    private DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).build();

    public interface OnCompressListener {
        void onCompressComplete(List<String> list, List<String> list2);

        void onCompressProgress(float f);
    }

    public FileCompressTask(List<String> list, OnCompressListener onCompressListener, String str) {
        this.mCompressItems = list;
        this.mCompressListener = onCompressListener;
        this.mCompressFolder = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0112  */
    public Object run(ThreadPool.JobContext jobContext) {
        Iterator<String> it;
        boolean z;
        FileOutputStream fileOutputStream;
        if (MiscUtil.isValid(this.mCompressItems)) {
            int size = this.mCompressItems.size();
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            File file = new File(StorageUtils.getSafePathInPriorStorageForUnadapted(this.mCompressFolder));
            int i = 0;
            boolean z2 = true;
            if (file.exists()) {
                MediaFileUtils.deleteFileType(MediaFileUtils.FileType.FOLDER, file);
            }
            FileUtils.createFolder(file, true);
            Iterator<String> it2 = this.mCompressItems.iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                if (jobContext.isCancelled()) {
                    return null;
                }
                Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(ImageDownloader.Scheme.FILE.wrap(next), this.mDisplayImageOptions);
                if (BitmapUtils.isValid(loadImageSync)) {
                    String fileTitle = FileUtils.getFileTitle(FileUtils.getFileName(next));
                    File file2 = new File(file, fileTitle + ".jpg");
                    if (file2.exists()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fileTitle);
                        sb.append("_");
                        it = it2;
                        sb.append(System.currentTimeMillis());
                        sb.append(".jpg");
                        file2 = new File(file, sb.toString());
                    } else {
                        it = it2;
                    }
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = null;
                        try {
                            e.printStackTrace();
                            MiscUtil.closeSilently(fileOutputStream);
                            i++;
                            if (this.mCompressListener != null) {
                                final float f = (((float) i) * 1.0f) / ((float) size);
                                ThreadManager.getMainHandler().post(new Runnable() {
                                    public void run() {
                                        FileCompressTask.this.mCompressListener.onCompressProgress(r2);
                                    }
                                });
                            }
                            z = true;
                            arrayList2.add(next);
                            z2 = z;
                            it2 = it;
                        } catch (Throwable th) {
                            th = th;
                            MiscUtil.closeSilently(fileOutputStream);
                            int i2 = i + 1;
                            if (this.mCompressListener != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                        MiscUtil.closeSilently(fileOutputStream);
                        int i22 = i + 1;
                        if (this.mCompressListener != null) {
                            final float f2 = (((float) i22) * 1.0f) / ((float) size);
                            ThreadManager.getMainHandler().post(new Runnable() {
                                public void run() {
                                    FileCompressTask.this.mCompressListener.onCompressProgress(f2);
                                }
                            });
                        }
                        throw th;
                    }
                    try {
                        loadImageSync.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                        arrayList.add(file2.getAbsolutePath());
                        MiscUtil.closeSilently(fileOutputStream);
                        i++;
                        if (this.mCompressListener != null) {
                            final float f3 = (((float) i) * 1.0f) / ((float) size);
                            ThreadManager.getMainHandler().post(new Runnable() {
                                public void run() {
                                    FileCompressTask.this.mCompressListener.onCompressProgress(f2);
                                }
                            });
                        }
                        it2 = it;
                        z2 = true;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        MiscUtil.closeSilently(fileOutputStream);
                        i++;
                        if (this.mCompressListener != null) {
                        }
                        z = true;
                        arrayList2.add(next);
                        z2 = z;
                        it2 = it;
                    }
                } else {
                    it = it2;
                    z = z2;
                    arrayList2.add(next);
                    z2 = z;
                    it2 = it;
                }
            }
            if (this.mCompressListener != null) {
                ThreadManager.getMainHandler().post(new Runnable() {
                    public void run() {
                        FileCompressTask.this.mCompressListener.onCompressProgress(1.0f);
                        FileCompressTask.this.mCompressListener.onCompressComplete(arrayList, arrayList2);
                    }
                });
            }
        }
        return null;
    }
}
