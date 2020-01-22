package com.miui.gallery.cleaner.slim;

import android.database.Cursor;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cleaner.BaseScanner;
import com.miui.gallery.cleaner.CleanerContract;
import com.miui.gallery.cleaner.ScanResult;
import com.miui.gallery.cleaner.slim.SlimController;
import com.miui.gallery.cloud.CloudTableUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.ScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SlimScanner extends BaseScanner implements SlimController.SpaceSlimObserver {
    public static final String SLIM_SCAN_ORDER = String.format("%s DESC", new Object[]{"alias_sort_time"});
    private static final String SLIM_SCAN_SELECTION = ("serverType = 1 AND mimeType != 'image/gif' AND localFile NOT NULL AND localFile != '' AND localGroupId != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1000) + " AND " + "localGroupId" + " != " + CloudTableUtils.getCloudIdForGroupWithoutRecord(1001));
    public static final String SYNCED_SLIM_SCAN_SELECTION;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("alias_sync_state = 0 AND ");
        sb.append(SLIM_SCAN_SELECTION);
        SYNCED_SLIM_SCAN_SELECTION = sb.toString();
    }

    public SlimScanner() {
        super(0);
    }

    private void removeMediaItem(long j) {
        boolean z;
        synchronized (this.mMediaItems) {
            Iterator it = this.mMediaItems.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((BaseScanner.MediaItem) it.next()).mId == j) {
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
        if (z) {
            updateScanResult(buildScanResult());
        }
    }

    /* access modifiers changed from: protected */
    public ScanResult buildScanResult() {
        int i;
        BaseScanner.MediaItem mediaItem;
        int screenWidth = ScreenUtils.getScreenWidth();
        int screenHeight = ScreenUtils.getScreenHeight();
        ScanResult.ResultImage[] resultImageArr = new ScanResult.ResultImage[4];
        synchronized (this.mMediaItems) {
            try {
                Iterator it = this.mMediaItems.iterator();
                int i2 = 0;
                long j = 0;
                while (it.hasNext()) {
                    try {
                        BaseScanner.MediaItem mediaItem2 = (BaseScanner.MediaItem) it.next();
                        if (Math.max(mediaItem2.mWidth, mediaItem2.mHeight) <= screenWidth || !FileUtils.isFileExist(mediaItem2.mPath)) {
                            i = screenWidth;
                            mediaItem = mediaItem2;
                        } else {
                            i = screenWidth;
                            mediaItem = mediaItem2;
                            long pow = mediaItem2.mSize - ((long) ((((double) mediaItem2.mSize) * Math.pow((double) BitmapUtils.computeThumbNailScaleSize(mediaItem2.mWidth, mediaItem2.mHeight, screenWidth, screenHeight), 2.0d)) * 0.4000000059604645d));
                            if (pow > 0) {
                                j += pow;
                            } else {
                                screenWidth = i;
                            }
                        }
                        if (i2 < resultImageArr.length) {
                            BaseScanner.MediaItem mediaItem3 = mediaItem;
                            resultImageArr[i2] = new ScanResult.ResultImage(mediaItem3.mId, mediaItem3.mPath);
                        }
                        i2++;
                        screenWidth = i;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                return new ScanResult.Builder().setType(this.mType).setSize(j).setImages(resultImageArr).setCount(i2).setOnScanResultClickListener(this.mOnScanResultClickListener).build();
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return SYNCED_SLIM_SCAN_SELECTION;
    }

    public void onMediaItemDeleted(long j) {
        removeMediaItem(j);
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        SlimController.getInstance().unregisterObserver(this);
    }

    public void onSlimPaused() {
    }

    public void onSlimProgress(long j, long j2, int i) {
        removeMediaItem(j);
    }

    public void onSlimResumed() {
    }

    /* access modifiers changed from: protected */
    public void recordClickScanResultEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", String.valueOf(this.mMediaItems.size()));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_result_slim_click", hashMap);
    }

    public ScanResult scan() {
        ArrayList arrayList = (ArrayList) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Media.URI_OWNER_ALBUM_MEDIA, CleanerContract.Projection.SLIM_SCAN_PROJECTION, getSelection(), (String[]) null, SLIM_SCAN_ORDER, new SafeDBUtil.QueryHandler<ArrayList<BaseScanner.MediaItem>>() {
            public ArrayList<BaseScanner.MediaItem> handle(Cursor cursor) {
                ArrayList<BaseScanner.MediaItem> arrayList = null;
                if (cursor == null || !cursor.moveToFirst()) {
                    return arrayList;
                }
                do {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    BaseScanner.MediaItem mediaItem = new BaseScanner.MediaItem();
                    mediaItem.mId = cursor.getLong(0);
                    mediaItem.mSize = cursor.getLong(1);
                    mediaItem.mPath = cursor.getString(2);
                    mediaItem.mWidth = cursor.getInt(3);
                    mediaItem.mHeight = cursor.getInt(4);
                    arrayList.add(mediaItem);
                } while (cursor.moveToNext());
                return arrayList;
            }
        });
        if (!MiscUtil.isValid(arrayList)) {
            return null;
        }
        this.mMediaItems = arrayList;
        SlimController.getInstance().registerObserver(this);
        return buildScanResult();
    }
}
