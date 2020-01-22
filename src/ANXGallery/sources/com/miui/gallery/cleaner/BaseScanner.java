package com.miui.gallery.cleaner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.activity.CleanerPhotoPickActivity;
import com.miui.gallery.cleaner.CleanerContract;
import com.miui.gallery.cleaner.ScanResult;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StaticContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class BaseScanner {
    CopyOnWriteArraySet<OnScanResultUpdateListener> mListeners = new CopyOnWriteArraySet<>();
    protected ArrayList<MediaItem> mMediaItems = new ArrayList<>();
    protected ScanResult.OnScanResultClickListener mOnScanResultClickListener = new ScanResult.OnScanResultClickListener() {
        public void onClick(Context context) {
            Intent intent = new Intent(context, CleanerPhotoPickActivity.class);
            intent.putExtra("extra_cleaner_photo_pick_type", BaseScanner.this.mType);
            context.startActivity(intent);
            BaseScanner.this.recordClickScanResultEvent();
        }
    };
    protected int mType;

    public static class MediaItem {
        public long mCreateTime;
        public int mHeight;
        public long mId;
        public String mPath;
        public String mSha1;
        public long mSize;
        public int mWidth;
    }

    public interface OnScanResultUpdateListener {
        void onUpdate(int i, long j, ScanResult scanResult);
    }

    protected BaseScanner(int i) {
        this.mType = i;
    }

    private void updateScanResult(long j, ScanResult scanResult) {
        Iterator<OnScanResultUpdateListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            OnScanResultUpdateListener next = it.next();
            if (next != null) {
                next.onUpdate(this.mType, j, scanResult);
            }
        }
    }

    public final void addListener(OnScanResultUpdateListener onScanResultUpdateListener) {
        if (onScanResultUpdateListener != null) {
            this.mListeners.add(onScanResultUpdateListener);
        }
    }

    /* access modifiers changed from: protected */
    public ScanResult buildScanResult() {
        int i;
        long j;
        ScanResult.ResultImage[] resultImageArr = new ScanResult.ResultImage[4];
        synchronized (this.mMediaItems) {
            Iterator<MediaItem> it = this.mMediaItems.iterator();
            i = 0;
            j = 0;
            while (it.hasNext()) {
                MediaItem next = it.next();
                j += next.mSize;
                if (i < resultImageArr.length) {
                    resultImageArr[i] = new ScanResult.ResultImage(next.mId, next.mPath);
                }
                i++;
            }
        }
        return new ScanResult.Builder().setType(this.mType).setSize(j).setImages(resultImageArr).setCount(i).setOnScanResultClickListener(this.mOnScanResultClickListener).build();
    }

    public List<Long> getScanResultIds() {
        ArrayList arrayList;
        synchronized (this.mMediaItems) {
            int size = this.mMediaItems.size();
            arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(Long.valueOf(this.mMediaItems.get(i).mId));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public abstract String getSelection();

    public boolean isLoadingValid() {
        return false;
    }

    public void onMediaItemDeleted(long j) {
        if (removeItem(j)) {
            updateScanResult(buildScanResult());
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.mMediaItems.clear();
    }

    /* access modifiers changed from: protected */
    public abstract void recordClickScanResultEvent();

    /* access modifiers changed from: protected */
    public boolean removeItem(long j) {
        boolean z;
        synchronized (this.mMediaItems) {
            Iterator<MediaItem> it = this.mMediaItems.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().mId == j) {
                        it.remove();
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        }
        return z;
    }

    public void removeItems(long[] jArr) {
        if (jArr != null) {
            synchronized (this.mMediaItems) {
                for (long j : jArr) {
                    if (removeItem(j)) {
                        updateScanResult((long) ((int) j), buildScanResult());
                    }
                }
            }
        }
    }

    public final void removeListener(OnScanResultUpdateListener onScanResultUpdateListener) {
        if (onScanResultUpdateListener != null) {
            this.mListeners.remove(onScanResultUpdateListener);
        }
    }

    public final void reset() {
        this.mListeners.clear();
        onReset();
    }

    /* access modifiers changed from: protected */
    public ScanResult scan() {
        String selection = getSelection();
        if (selection == null) {
            return null;
        }
        ArrayList<MediaItem> arrayList = (ArrayList) SafeDBUtil.safeQuery(StaticContext.sGetAndroidContext(), GalleryContract.Media.URI_OWNER_ALBUM_MEDIA, CleanerContract.Projection.NORMAL_SCAN_PROJECTION, selection, (String[]) null, "alias_create_time DESC", new SafeDBUtil.QueryHandler<ArrayList<MediaItem>>() {
            public ArrayList<MediaItem> handle(Cursor cursor) {
                ArrayList<MediaItem> arrayList = null;
                if (cursor == null || !cursor.moveToFirst()) {
                    return arrayList;
                }
                do {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    MediaItem mediaItem = new MediaItem();
                    mediaItem.mId = cursor.getLong(0);
                    mediaItem.mSize = cursor.getLong(1);
                    mediaItem.mSha1 = cursor.getString(5);
                    mediaItem.mPath = cursor.getString(2);
                    if (TextUtils.isEmpty(mediaItem.mPath)) {
                        mediaItem.mPath = cursor.getString(3);
                    }
                    if (TextUtils.isEmpty(mediaItem.mPath)) {
                        mediaItem.mPath = cursor.getString(4);
                    }
                    arrayList.add(mediaItem);
                } while (cursor.moveToNext());
                return arrayList;
            }
        });
        if (!MiscUtil.isValid(arrayList)) {
            return null;
        }
        this.mMediaItems = arrayList;
        return buildScanResult();
    }

    /* access modifiers changed from: protected */
    public final void updateScanResult(ScanResult scanResult) {
        updateScanResult(0, scanResult);
    }
}
