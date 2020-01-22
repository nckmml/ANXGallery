package com.miui.gallery.util;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.miui.gallery.provider.ProcessingMedia;
import java.util.List;

public class ProcessingMediaHelper {
    private static final String EXTERNAL_FILES_PREFIX = MediaStore.Files.getContentUri("external").toString();
    private static final String EXTERNAL_IMAGES_PREFIX = MediaStore.Images.Media.getContentUri("external").toString();
    private static final String EXTERNAL_VIDEO_PREFIX = MediaStore.Video.Media.getContentUri("external").toString();
    private static final Object sLock = new Object();
    private LongSparseArray<String> mProcessingItems;

    private static class SingletonHolder {
        static final ProcessingMediaHelper INSTANCE = new ProcessingMediaHelper();
    }

    private ProcessingMediaHelper() {
        this.mProcessingItems = new LongSparseArray<>();
    }

    public static ProcessingMediaHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addProcessingItem(long j, String str) {
        synchronized (sLock) {
            this.mProcessingItems.put(j, str);
        }
    }

    public void calibrateCache(List<ProcessingMedia> list) {
        synchronized (sLock) {
            this.mProcessingItems.clear();
            if (list != null) {
                for (ProcessingMedia next : list) {
                    this.mProcessingItems.put(next.getMediaStoreId(), next.getPath());
                }
            }
        }
    }

    public boolean isMediaInProcessing(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith(EXTERNAL_FILES_PREFIX) || str.startsWith(EXTERNAL_IMAGES_PREFIX) || str.startsWith(EXTERNAL_VIDEO_PREFIX)) {
            try {
                long parseId = ContentUris.parseId(Uri.parse(str));
                synchronized (sLock) {
                    if (this.mProcessingItems.get(parseId) != null) {
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.w("ProcessingMediaHelper", "Illegal Uri format: %s", str);
                Log.w("ProcessingMediaHelper", (Throwable) e);
                return false;
            }
        } else if (str.startsWith("file://")) {
            String substring = str.substring(7);
            synchronized (sLock) {
                for (int i = 0; i < this.mProcessingItems.size(); i++) {
                    if (TextUtils.equals(substring, this.mProcessingItems.valueAt(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeProcessingItem(long j, String str) {
        synchronized (sLock) {
            if (j > 0) {
                this.mProcessingItems.remove(j);
                Log.d("ProcessingMediaHelper", "remove by media id");
            } else if (!TextUtils.isEmpty(str)) {
                int i = 0;
                while (true) {
                    if (i >= this.mProcessingItems.size()) {
                        break;
                    } else if (TextUtils.equals(str, this.mProcessingItems.valueAt(i))) {
                        this.mProcessingItems.removeAt(i);
                        Log.d("ProcessingMediaHelper", "remove by file path");
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                Log.d("ProcessingMediaHelper", "illegal arguments id[%s], path[%s]", Long.valueOf(j), str);
            }
        }
    }
}
