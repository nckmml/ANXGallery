package com.miui.gallery.loader;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import com.miui.gallery.R;
import com.miui.gallery.loader.MediaSetLoader;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.CursorDataSet;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.model.MediaItem;
import com.miui.gallery.provider.ProcessingMediaManager;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.BurstFilterCursor;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.logger.TimingTracing;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MediaSetLoader extends CursorSetLoader {
    private static final String[] BUCKET_PROJECTION = {"_data", "bucket_id"};
    private static final String[] PROJECTION = {"_id", "_data", "_size", "mime_type", "latitude", "longitude", "datetaken", "duration", nexExportFormat.TAG_FORMAT_WIDTH, nexExportFormat.TAG_FORMAT_HEIGHT, "orientation"};
    /* access modifiers changed from: private */
    public static final List<Long> sMarkDeletedIds = new LinkedList();
    private String[] mBucketIds;
    /* access modifiers changed from: private */
    public boolean mFromCamera;
    private long mInitId;
    private int mInitPos = -1;
    /* access modifiers changed from: private */
    public boolean mIsFromScreenshot;
    private boolean mIsInternal;
    private ArrayList<Uri> mLimitUris;
    private List<Long> mProcessingIds;
    private Uri mUri;

    private class MediaDataSet extends CursorDataSet {
        public MediaDataSet(Cursor cursor) {
            super(cursor);
        }

        private boolean isFromCamera() {
            return MediaSetLoader.this.mFromCamera;
        }

        private boolean isFromScreenshot() {
            return MediaSetLoader.this.mIsFromScreenshot;
        }

        private void markDelete(List<BaseDataItem> list, boolean z) {
            Long[] lArr = new Long[list.size()];
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                BaseDataItem baseDataItem = list.get(i);
                lArr[i] = Long.valueOf(baseDataItem.getKey());
                strArr[i] = baseDataItem.getOriginalPath();
            }
            synchronized (MediaSetLoader.sMarkDeletedIds) {
                MediaSetLoader.sMarkDeletedIds.addAll(Arrays.asList(lArr));
            }
            ThreadManager.getWorkHandler().post(new Runnable(strArr, lArr, z) {
                private final /* synthetic */ String[] f$1;
                private final /* synthetic */ Long[] f$2;
                private final /* synthetic */ boolean f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    MediaSetLoader.MediaDataSet.this.lambda$markDelete$53$MediaSetLoader$MediaDataSet(this.f$1, this.f$2, this.f$3);
                }
            });
        }

        private void wrapItemByCursor(BaseDataItem baseDataItem, Cursor cursor) {
            String string = cursor.getString(1);
            baseDataItem.setKey(cursor.getLong(0)).setFilePath(string).setThumbPath(string).setTitle(FileUtils.getFileTitle(FileUtils.getFileName(string))).setSize(cursor.getLong(2)).setMimeType(cursor.getString(3)).setLatitude((double) cursor.getFloat(4)).setLongitude((double) cursor.getFloat(5)).setCreateTime(cursor.getLong(6)).setDuration(cursor.getInt(7) / 1000).setWidth(cursor.getInt(8)).setHeight(cursor.getInt(9)).setOrientation(cursor.getInt(10));
            if (FileMimeUtil.isRawFromMimeType(baseDataItem.getMimeType())) {
                baseDataItem.setSpecialTypeFlags(8192);
            }
        }

        public boolean addToAlbum(Activity activity, int i, boolean z, boolean z2, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
            BaseDataItem item = getItem((BaseDataItem) null, i);
            if (item != null && !TextUtils.isEmpty(item.getOriginalPath())) {
                ArrayList arrayList = new ArrayList(1);
                if (!item.isBurstItem() || item.getBurstGroup() == null) {
                    arrayList.add(Uri.fromFile(new File(item.getOriginalPath())));
                } else {
                    List<BaseDataItem> burstGroup = item.getBurstGroup();
                    for (int i2 = 0; i2 < burstGroup.size(); i2++) {
                        arrayList.add(Uri.fromFile(new File(burstGroup.get(i2).getOriginalPath())));
                    }
                }
                MediaAndAlbumOperations.addToAlbum(activity, onAddAlbumListener, arrayList, isFromCamera(), z2, item.isVideo());
            }
            return true;
        }

        public boolean addToFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
            BaseDataItem item = getItem((BaseDataItem) null, i);
            if (item != null && !TextUtils.isEmpty(item.getOriginalPath())) {
                if (!item.isBurstItem() || item.getBurstGroup() == null) {
                    MediaAndAlbumOperations.addToFavoritesByPath(activity, onCompleteListener, item.getOriginalPath());
                } else {
                    List<BaseDataItem> burstGroup = item.getBurstGroup();
                    String[] strArr = new String[burstGroup.size()];
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        strArr[i2] = burstGroup.get(i2).getOriginalPath();
                    }
                    MediaAndAlbumOperations.addToFavoritesByPath(activity, onCompleteListener, strArr);
                }
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void bindItem(BaseDataItem baseDataItem, int i) {
            if (isValidate(i)) {
                this.mCursor.moveToPosition(i);
                wrapItemByCursor(baseDataItem, this.mCursor);
                if (foldBurst()) {
                    BurstFilterCursor burstFilterCursor = (BurstFilterCursor) this.mCursor;
                    if (burstFilterCursor.isBurstPosition(i)) {
                        ArrayList arrayList = new ArrayList();
                        int contentCursorPosition = burstFilterCursor.getContentCursorPosition(i);
                        int burstCount = (burstFilterCursor.getBurstCount(i) + contentCursorPosition) - 1;
                        while (contentCursorPosition <= burstCount) {
                            Cursor contentCursorAtPosition = burstFilterCursor.getContentCursorAtPosition(contentCursorPosition);
                            BaseDataItem baseDataItem2 = new BaseDataItem();
                            wrapItemByCursor(baseDataItem2, contentCursorAtPosition);
                            arrayList.add(baseDataItem2);
                            contentCursorPosition++;
                        }
                        baseDataItem.setBurstItem(true);
                        baseDataItem.setBurstGroup(arrayList);
                        baseDataItem.setSpecialTypeFlags(64);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public int burstKeyIndex() {
            return 1;
        }

        /* access modifiers changed from: protected */
        public BaseDataItem createItem(int i) {
            MediaItem mediaItem = new MediaItem();
            bindItem(mediaItem, i);
            return mediaItem;
        }

        /* access modifiers changed from: protected */
        public int doDelete(BaseDataItem baseDataItem) {
            boolean z = false;
            if (baseDataItem == null) {
                return 0;
            }
            if (DocumentProviderUtils.needRequestExternalSDCardPermission(MediaSetLoader.this.getContext())) {
                return -112;
            }
            LinkedList linkedList = new LinkedList();
            if (!baseDataItem.isBurstItem() || baseDataItem.getBurstGroup().size() <= 0) {
                linkedList.add(baseDataItem);
            } else {
                linkedList.addAll(baseDataItem.getBurstGroup());
            }
            int size = linkedList.size();
            if (isFromCamera() || isFromScreenshot()) {
                z = true;
            }
            markDelete(linkedList, z);
            return size;
        }

        /* access modifiers changed from: protected */
        public boolean foldBurst() {
            return true;
        }

        /* access modifiers changed from: protected */
        public String getDeleteDialogMessage(BaseDataItem baseDataItem, Context context) {
            if ((!isFromCamera() && !isFromScreenshot()) || !SyncUtil.existXiaomiAccount(MediaSetLoader.this.getContext())) {
                return super.getDeleteDialogMessage(baseDataItem, context);
            }
            if (baseDataItem.isBurstItem()) {
                int size = baseDataItem.getBurstGroup() == null ? 0 : baseDataItem.getBurstGroup().size();
                return context.getResources().getQuantityString(R.plurals.delete_burst_photo_from_all_devices_and_cloud_msg, size, new Object[]{Integer.valueOf(size)});
            }
            return context.getResources().getQuantityString(FileMimeUtil.isVideoFromMimeType(baseDataItem.getMimeType()) ? R.plurals.delete_video_from_all_devices_and_cloud_msg : R.plurals.delete_photo_from_all_devices_and_cloud_msg, 1, new Object[]{1});
        }

        public long getItemKey(int i) {
            if (!isValidate(i)) {
                return -1;
            }
            this.mCursor.moveToPosition(i);
            return this.mCursor.getLong(0);
        }

        /* access modifiers changed from: protected */
        public String getItemPath(int i) {
            if (!isValidate(i)) {
                return null;
            }
            this.mCursor.moveToPosition(i);
            return this.mCursor.getString(1);
        }

        public /* synthetic */ void lambda$markDelete$53$MediaSetLoader$MediaDataSet(String[] strArr, Long[] lArr, boolean z) {
            TimingTracing.beginTracing("MediaSetLoader_delete", "delete");
            int deleteFileType = MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NONE, strArr);
            TimingTracing.addSplit("MediaSetLoader_delete", "file delete " + deleteFileType);
            int safeDelete = SafeDBUtil.safeDelete(MediaSetLoader.this.getContext(), MediaSetLoader.this.getUri(), String.format("_id in (%s)", new Object[]{TextUtils.join(",", lArr)}), (String[]) null);
            TimingTracing.addSplit("MediaSetLoader_delete", "provider delete " + safeDelete);
            MediaAndAlbumOperations.deleteInService(MediaSetLoader.this.getContext(), z ^ true ? 1 : 0, 30, strArr);
            synchronized (MediaSetLoader.sMarkDeletedIds) {
                MediaSetLoader.sMarkDeletedIds.removeAll(Arrays.asList(lArr));
            }
            StringBuilder sb = new StringBuilder();
            long stopTracing = TimingTracing.stopTracing("MediaSetLoader_delete", new StringBuilderPrinter(sb));
            Log.w("MediaSetLoader", "delete : %s", sb.toString());
            if (stopTracing > 500) {
                HashMap hashMap = new HashMap();
                hashMap.put("cost", String.valueOf(stopTracing));
                hashMap.put("detail", sb.toString());
                GallerySamplingStatHelper.recordErrorEvent("delete_performance", "MediaSetLoader", hashMap);
            }
        }

        public boolean removeFromFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
            BaseDataItem item = getItem((BaseDataItem) null, i);
            if (item != null && !TextUtils.isEmpty(item.getOriginalPath())) {
                if (!item.isBurstItem() || item.getBurstGroup() == null) {
                    MediaAndAlbumOperations.removeFromFavoritesByPath(activity, onCompleteListener, item.getOriginalPath());
                } else {
                    List<BaseDataItem> burstGroup = item.getBurstGroup();
                    String[] strArr = new String[burstGroup.size()];
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        strArr[i2] = burstGroup.get(i2).getOriginalPath();
                    }
                    MediaAndAlbumOperations.removeFromFavoritesByPath(activity, onCompleteListener, strArr);
                }
            }
            return true;
        }
    }

    public MediaSetLoader(Context context, Uri uri, Bundle bundle, boolean z) {
        super(context);
        this.mIsInternal = z;
        this.mUri = uri;
        this.mFromCamera = bundle.getBoolean("from_MiuiCamera", false);
        this.mIsFromScreenshot = bundle.getBoolean("from_MiuiScreenShot", false);
        this.mLimitUris = bundle.getParcelableArrayList("SecureUri");
        if (bundle.getBoolean("com.miui.gallery.extra.preview_single_item", false)) {
            this.mLimitUris = new ArrayList<>(1);
            this.mLimitUris.add(uri);
        }
        ImageLoadParams imageLoadParams = (ImageLoadParams) bundle.getParcelable("photo_transition_data");
        if (imageLoadParams != null) {
            this.mInitId = imageLoadParams.getKey();
        }
    }

    /* access modifiers changed from: private */
    public String[] genAllBucketIds(String str, String str2) {
        List<String> bucketId;
        if (!TextUtils.isEmpty(str)) {
            String parentFolderPath = FileUtils.getParentFolderPath(str);
            if (needLoadAllBucket(parentFolderPath)) {
                LinkedList linkedList = new LinkedList();
                String relativePath = StorageUtils.getRelativePath(getContext(), parentFolderPath);
                List<String> bucketId2 = getBucketId(relativePath);
                if (bucketId2 == null) {
                    Log.e("MediaSetLoader", "target bucket is null");
                    return null;
                }
                linkedList.addAll(bucketId2);
                if (this.mFromCamera) {
                    for (String str3 : getCameraRelatedPaths()) {
                        if (!relativePath.equalsIgnoreCase(str3) && (bucketId = getBucketId(str3)) != null) {
                            linkedList.addAll(bucketId);
                        }
                    }
                }
                return (String[]) linkedList.toArray(new String[linkedList.size()]);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return new String[]{str2};
    }

    private List<String> getBucketId(String str) {
        String[] pathsInExternalStorage = StorageUtils.getPathsInExternalStorage(getContext(), str);
        if (pathsInExternalStorage == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(pathsInExternalStorage.length);
        for (String bucketID : pathsInExternalStorage) {
            arrayList.add(String.valueOf(FileUtils.getBucketID(bucketID)));
        }
        return arrayList;
    }

    private String[] getCameraRelatedPaths() {
        return new String[]{"DCIM/Camera", "DCIM/Camera/Raw"};
    }

    private String[] getLoadAllBucketPaths() {
        return new String[]{"DCIM/Camera", "DCIM/Camera/Raw"};
    }

    private boolean isLimitedUris() {
        return MiscUtil.isValid(this.mLimitUris);
    }

    private boolean needLoadAllBucket(String str) {
        String relativePath = StorageUtils.getRelativePath(getContext(), str);
        for (String equalsIgnoreCase : getLoadAllBucketPaths()) {
            if (equalsIgnoreCase.equalsIgnoreCase(relativePath)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public String getOrder() {
        return "datetaken DESC, _id DESC";
    }

    /* access modifiers changed from: protected */
    public String[] getProjection() {
        return PROJECTION;
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        String str;
        if (isLimitedUris()) {
            ArrayList arrayList = new ArrayList(this.mLimitUris.size());
            Iterator<Uri> it = this.mLimitUris.iterator();
            while (it.hasNext()) {
                Uri next = it.next();
                if (next != null) {
                    long parseId = ContentUris.parseId(next);
                    synchronized (sMarkDeletedIds) {
                        if (!sMarkDeletedIds.contains(Long.valueOf(parseId))) {
                            arrayList.add(Long.valueOf(parseId));
                        } else {
                            Log.d("MediaSetLoader", "filter mark deleted id %d", (Object) Long.valueOf(parseId));
                        }
                    }
                }
            }
            return "_id in (" + TextUtils.join(",", arrayList) + ")";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("bucket_id in (");
        sb.append(TextUtils.join(",", this.mBucketIds));
        sb.append(")");
        sb.append(" AND (");
        sb.append("media_type");
        sb.append(" in (");
        sb.append(1);
        sb.append(",");
        sb.append(3);
        sb.append(")");
        if (MiscUtil.isValid(this.mProcessingIds)) {
            str = " OR _id in (" + TextUtils.join(",", this.mProcessingIds) + ")";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(")");
        String sb2 = sb.toString();
        String str2 = null;
        synchronized (sMarkDeletedIds) {
            if (sMarkDeletedIds.size() > 0) {
                str2 = TextUtils.join(",", sMarkDeletedIds);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return sb2;
        }
        Log.d("MediaSetLoader", "filter mark deleted ids %s", (Object) str2);
        return String.format(Locale.US, "(%s) AND (%s)", new Object[]{sb2, "_id not in (" + str2 + ")"});
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        return null;
    }

    public String getTAG() {
        return "MediaSetLoader";
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return MediaStore.Files.getContentUri(this.mIsInternal ? "internal" : "external");
    }

    public CursorDataSet loadInBackground() {
        String str = isLimitedUris() ? "MediaSetLoader_limited_load" : "MediaSetLoader_load";
        TimingTracing.beginTracing(str, "loadInBackground");
        try {
            long parseId = this.mInitId > 0 ? this.mInitId : ContentUris.parseId(this.mUri);
            if (isLimitedUris()) {
                CursorDataSet loadInBackground = super.loadInBackground();
                if (loadInBackground != null) {
                    if (this.mInitPos == -1) {
                        BaseDataItem baseDataItem = new BaseDataItem();
                        baseDataItem.setKey(parseId);
                        this.mInitPos = loadInBackground.getIndexOfItem(baseDataItem, -1);
                    }
                    loadInBackground.setInitPosition(this.mInitPos);
                    long stopTracing = TimingTracing.stopTracing(str, (Printer) null);
                    if (stopTracing > 500) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("cost", isLimitedUris() + "_" + stopTracing);
                        GallerySamplingStatHelper.recordErrorEvent("load_performance", "MediaSetLoader", hashMap);
                    }
                    return loadInBackground;
                }
            } else {
                if (this.mBucketIds == null) {
                    this.mBucketIds = (String[]) SafeDBUtil.safeQuery(getContext(), MediaStore.Files.getContentUri(this.mIsInternal ? "internal" : "external"), BUCKET_PROJECTION, "_id=?", new String[]{String.valueOf(parseId)}, (String) null, new SafeDBUtil.QueryHandler<String[]>() {
                        public String[] handle(Cursor cursor) {
                            if (cursor == null || !cursor.moveToFirst()) {
                                return null;
                            }
                            return MediaSetLoader.this.genAllBucketIds(cursor.getString(0), cursor.getString(1));
                        }
                    });
                }
                if (this.mBucketIds != null) {
                    this.mProcessingIds = ProcessingMediaManager.queryProcessingMediaIds();
                    CursorDataSet loadInBackground2 = super.loadInBackground();
                    if (loadInBackground2 != null) {
                        if (this.mInitPos == -1) {
                            BaseDataItem baseDataItem2 = new BaseDataItem();
                            baseDataItem2.setKey(parseId);
                            this.mInitPos = loadInBackground2.getIndexOfItem(baseDataItem2, -1);
                        }
                        loadInBackground2.setInitPosition(this.mInitPos);
                        long stopTracing2 = TimingTracing.stopTracing(str, (Printer) null);
                        if (stopTracing2 > 500) {
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("cost", isLimitedUris() + "_" + stopTracing2);
                            GallerySamplingStatHelper.recordErrorEvent("load_performance", "MediaSetLoader", hashMap2);
                        }
                        return loadInBackground2;
                    }
                }
            }
            return null;
        } finally {
            long stopTracing3 = TimingTracing.stopTracing(str, (Printer) null);
            if (stopTracing3 > 500) {
                HashMap hashMap3 = new HashMap();
                hashMap3.put("cost", isLimitedUris() + "_" + stopTracing3);
                GallerySamplingStatHelper.recordErrorEvent("load_performance", "MediaSetLoader", hashMap3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public CursorDataSet wrapDataSet(Cursor cursor) {
        return new MediaDataSet(cursor);
    }
}
