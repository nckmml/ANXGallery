package com.miui.gallery.loader;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.model.StorageItem;
import com.miui.gallery.util.ContentUtils;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.logger.TimingTracing;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StorageSetLoader extends BaseLoader {
    private String mFolderPath;
    private String mInitFilePath = null;
    private Uri mInitUri;
    /* access modifiers changed from: private */
    public ArrayList<Uri> mLimitUris;

    private static class MFileComparator implements Comparator<File> {
        private MFileComparator() {
        }

        public int compare(File file, File file2) {
            int i = (file2.lastModified() > file.lastModified() ? 1 : (file2.lastModified() == file.lastModified() ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }
    }

    private class MFileFilter implements FileFilter {
        private MFileFilter() {
        }

        public boolean accept(File file) {
            if (file.isFile()) {
                return StorageSetLoader.this.isSupportFile(file.getAbsolutePath());
            }
            return false;
        }
    }

    private class StorageDataSet extends BaseDataSet {
        private List<File> mFiles;
        private String mInitFilePath;

        public StorageDataSet(List<File> list, String str) {
            this.mFiles = list;
            this.mInitFilePath = str;
        }

        private int getIndexOfItem(String str) {
            if (this.mFiles != null && !TextUtils.isEmpty(str)) {
                int size = this.mFiles.size();
                for (int i = 0; i < size; i++) {
                    if (this.mFiles.get(i).getAbsolutePath().equalsIgnoreCase(str)) {
                        return i;
                    }
                }
            }
            return 0;
        }

        private File getItem(int i) {
            List<File> list = this.mFiles;
            if (list == null || i <= -1 || i >= list.size()) {
                return null;
            }
            return this.mFiles.get(i);
        }

        public boolean addNewFile(String str, int i) {
            if (TextUtils.isEmpty(str) || !StorageSetLoader.this.isLimitedUris()) {
                return super.addNewFile(str, i);
            }
            StorageSetLoader.this.mLimitUris.add(Math.min(i, StorageSetLoader.this.mLimitUris.size()), Uri.fromFile(new File(str)));
            return true;
        }

        public boolean addToAlbum(Activity activity, int i, boolean z, boolean z2, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
            File item = getItem(i);
            if (item != null) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(Uri.fromFile(item));
                MediaAndAlbumOperations.addToAlbum(activity, onAddAlbumListener, arrayList, false, z2, FileMimeUtil.isVideoFromMimeType(FileMimeUtil.getMimeType(item.getPath())));
            }
            return true;
        }

        public boolean addToFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
            BaseDataItem item = getItem((BaseDataItem) null, i);
            if (item != null && !TextUtils.isEmpty(item.getOriginalPath())) {
                MediaAndAlbumOperations.addToFavoritesByPath(activity, onCompleteListener, item.getOriginalPath());
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void bindItem(BaseDataItem baseDataItem, int i) {
            File item = getItem(i);
            if (item != null) {
                String absolutePath = item.getAbsolutePath();
                baseDataItem.setKey((long) item.getName().hashCode()).setFilePath(absolutePath).setThumbPath(absolutePath).setMimeType(FileMimeUtil.getMimeType(absolutePath));
                if (FileMimeUtil.isRawFromMimeType(baseDataItem.getMimeType())) {
                    baseDataItem.setSpecialTypeFlags(8192);
                }
            }
        }

        /* access modifiers changed from: protected */
        public BaseDataItem createItem(int i) {
            StorageItem storageItem = new StorageItem();
            bindItem(storageItem, i);
            return storageItem;
        }

        /* access modifiers changed from: protected */
        public int doDelete(BaseDataItem baseDataItem) {
            StringBuilder sb;
            if (baseDataItem != null && !TextUtils.isEmpty(baseDataItem.getOriginalPath())) {
                TimingTracing.beginTracing("StorageSetLoader_delete", "delete");
                try {
                    if (DocumentProviderUtils.needRequestExternalSDCardPermission(StorageSetLoader.this.getContext())) {
                        return -112;
                    }
                    TimingTracing.addSplit("StorageSetLoader_delete", "sdcard permission");
                    if (MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NONE, baseDataItem.getOriginalPath()) > 0) {
                        TimingTracing.addSplit("StorageSetLoader_delete", "delete file");
                        MediaFileUtils.triggerMediaScan(true, new File(baseDataItem.getOriginalPath()));
                        TimingTracing.addSplit("StorageSetLoader_delete", "trigger scan");
                        MediaAndAlbumOperations.deleteInService(StorageSetLoader.this.getContext(), 1, 31, baseDataItem.getOriginalPath());
                        TimingTracing.addSplit("StorageSetLoader_delete", "service delete");
                        StringBuilder sb2 = new StringBuilder();
                        if (TimingTracing.stopTracing("StorageSetLoader_delete", new StringBuilderPrinter(sb2)) > 500) {
                            Log.w("StorageSetLoader", "delete slowly: %s", sb2.toString());
                            HashMap hashMap = new HashMap();
                            hashMap.put("detail", sb2.toString());
                            GallerySamplingStatHelper.recordErrorEvent("delete_performance", "StorageSetLoader", hashMap);
                        }
                        return 1;
                    }
                    Log.w("StorageSetLoader", "Failed to delete file %s", baseDataItem.getOriginalPath());
                    StringBuilder sb3 = new StringBuilder();
                    if (TimingTracing.stopTracing("StorageSetLoader_delete", new StringBuilderPrinter(sb3)) > 500) {
                        Log.w("StorageSetLoader", "delete slowly: %s", sb3.toString());
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("detail", sb3.toString());
                        GallerySamplingStatHelper.recordErrorEvent("delete_performance", "StorageSetLoader", hashMap2);
                    }
                } finally {
                    sb = new StringBuilder();
                    if (TimingTracing.stopTracing("StorageSetLoader_delete", new StringBuilderPrinter(sb)) > 500) {
                        Log.w("StorageSetLoader", "delete slowly: %s", sb.toString());
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("detail", sb.toString());
                        GallerySamplingStatHelper.recordErrorEvent("delete_performance", "StorageSetLoader", hashMap3);
                    }
                }
            }
            return 0;
        }

        public int getCount() {
            List<File> list = this.mFiles;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public int getInitPosition() {
            return getIndexOfItem(this.mInitFilePath);
        }

        public long getItemKey(int i) {
            File item = getItem(i);
            if (item != null) {
                return (long) item.getName().hashCode();
            }
            return -1;
        }

        /* access modifiers changed from: protected */
        public String getItemPath(int i) {
            File item = getItem(i);
            if (item != null) {
                return item.getAbsolutePath();
            }
            return null;
        }

        public void onRelease() {
        }

        public boolean removeFromFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
            BaseDataItem item = getItem((BaseDataItem) null, i);
            if (item != null && !TextUtils.isEmpty(item.getOriginalPath())) {
                MediaAndAlbumOperations.removeFromFavoritesByPath(activity, onCompleteListener, item.getOriginalPath());
            }
            return true;
        }
    }

    public StorageSetLoader(Context context, Uri uri, Bundle bundle) {
        super(context);
        this.mInitUri = uri;
        String path = uri.getPath();
        if (new File(path).isDirectory()) {
            this.mFolderPath = path;
            return;
        }
        this.mLimitUris = bundle.getParcelableArrayList("com.miui.gallery.extra.photo_items");
        if (bundle.getBoolean("com.miui.gallery.extra.preview_single_item", false)) {
            this.mLimitUris = new ArrayList<>(1);
            this.mLimitUris.add(Uri.fromFile(new File(path)));
        }
        if (!isLimitedUris()) {
            this.mFolderPath = FileUtils.getParentFolderPath(path);
        }
    }

    /* access modifiers changed from: private */
    public boolean isLimitedUris() {
        return MiscUtil.isValid(this.mLimitUris);
    }

    /* access modifiers changed from: private */
    public boolean isSupportFile(String str) {
        boolean isSupportType = isSupportType(FileMimeUtil.getMimeType(str));
        return (isSupportType || !str.equalsIgnoreCase(this.mInitFilePath)) ? isSupportType : isSupportType | isSupportType(FileMimeUtil.getMimeTypeByParseFile(str));
    }

    private boolean isSupportType(String str) {
        return FileMimeUtil.isImageFromMimeType(str) || FileMimeUtil.isVideoFromMimeType(str);
    }

    public BaseDataSet loadInBackground() {
        List list;
        String str = isLimitedUris() ? "StorageSetLoader_limited_load" : "StorageSetLoader_load";
        TimingTracing.beginTracing(str, "loadInBackground");
        try {
            if (TextUtils.isEmpty(this.mInitFilePath)) {
                this.mInitFilePath = ContentUtils.getValidFilePathForContentUri(getContext(), this.mInitUri);
                if (TextUtils.isEmpty(this.mInitFilePath)) {
                    this.mInitFilePath = this.mInitUri.getPath();
                }
            }
            if (isLimitedUris()) {
                list = new ArrayList(this.mLimitUris.size());
                Iterator<Uri> it = this.mLimitUris.iterator();
                while (it.hasNext()) {
                    Uri next = it.next();
                    if (next != null && "file".equalsIgnoreCase(next.getScheme())) {
                        String path = next.getPath();
                        if (FileUtils.isFileExist(path) && isSupportFile(path)) {
                            list.add(new File(path));
                        }
                    }
                }
            } else {
                File[] listFiles = new File(this.mFolderPath).listFiles(new MFileFilter());
                if (listFiles != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    Arrays.sort(listFiles, new MFileComparator());
                    Log.d("StorageSetLoader", "file sort cost[%d], size[%d]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(listFiles.length));
                    list = Arrays.asList(listFiles);
                } else {
                    if (!TextUtils.isEmpty(this.mInitFilePath)) {
                        File file = new File(this.mInitFilePath);
                        if (file.exists()) {
                            ArrayList arrayList = new ArrayList(1);
                            arrayList.add(file);
                            list = arrayList;
                        }
                    }
                    list = null;
                }
            }
            return new StorageDataSet(list, this.mInitFilePath);
        } finally {
            long stopTracing = TimingTracing.stopTracing(str, (Printer) null);
            if (stopTracing > 500) {
                HashMap hashMap = new HashMap();
                hashMap.put("cost", isLimitedUris() + "_" + stopTracing);
                GallerySamplingStatHelper.recordErrorEvent("load_performance", "StorageSetLoader", hashMap);
            }
        }
    }
}
