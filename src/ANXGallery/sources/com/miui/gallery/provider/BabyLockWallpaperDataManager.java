package com.miui.gallery.provider;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.RequestCloudItem;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBShareAlbum;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.ExifUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class BabyLockWallpaperDataManager {
    public static String[] BABY_ALBUM_INFO_PROJECTION = {"_id", "fileName"};
    public static String[] BABY_PHOTO_INFO_PROJECTION = {"_id", "fileName"};
    private static BabyLockWallpaperDataManager sInstance;
    private ArrayList<BabyAlbumInfo> mAlbums;
    private ArrayList<BabyPhotoInfo> mAllPhotos;

    public static class BabyAlbumInfo {
        public boolean isOtherShared;
        public long localId;
        public String name;
    }

    static class BabyPhotoInfo {
        public long id;
        public boolean isOtherShared;

        BabyPhotoInfo() {
        }
    }

    private class PhotoObserver extends ContentObserver {
        public PhotoObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            BabyLockWallpaperDataManager.this.refresh();
        }
    }

    private BabyLockWallpaperDataManager() {
        intialAlbumAndPhotos();
        GalleryApp.sGetAndroidContext().getContentResolver().registerContentObserver(GalleryContract.Cloud.CLOUD_URI, true, new PhotoObserver(ThreadManager.getMainHandler()));
    }

    private String createUri(DBImage dBImage) {
        if (dBImage == null) {
            return null;
        }
        return BabyLockWallpaperProvider.BASE_URI.buildUpon().appendPath(dBImage.isShareItem() ? "sharer_album" : "owner_album").appendPath(dBImage.getId()).build().toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f8  */
    public static RectF getFacePos(DBImage dBImage) {
        String str;
        Cursor cursor = null;
        if (TextUtils.isEmpty(dBImage.getSha1())) {
            return null;
        }
        Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
        if (dBImage.isShareItem()) {
            DBShareAlbum dBShareAlbumBySharedId = CloudUtils.getDBShareAlbumBySharedId(dBImage.getAlbumId());
            if (dBShareAlbumBySharedId != null) {
                str = dBShareAlbumBySharedId.getPeopleId();
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                try {
                    Cursor queryJoinTable = FaceDataManager.queryJoinTable(new String[]{"peopleFace.faceXScale", "peopleFace.faceYScale", "peopleFace.faceWScale", "peopleFace.faceHScale"}, String.format(Locale.US, "%s.%s=%s.%s AND %s.%s=%s.%s  AND %s.%s=? AND %s.%s=? AND %s.%s=?", new Object[]{"peopleFace", "serverId", "faceToImages", "faceId", "faceToImages", "imageServerId", "cloud", "serverId", "peopleFace", nexExportFormat.TAG_FORMAT_TYPE, "peopleFace", "groupId", "cloud", "sha1"}), new String[]{"FACE", str, dBImage.getSha1()}, (String) null, (String) null);
                    if (queryJoinTable != null) {
                        try {
                            if (queryJoinTable.moveToNext()) {
                                float f = (float) queryJoinTable.getDouble(0);
                                float f2 = (float) queryJoinTable.getDouble(1);
                                RectF rectF = new RectF(f, f2, ((float) queryJoinTable.getDouble(2)) + f, ((float) queryJoinTable.getDouble(3)) + f2);
                                if (queryJoinTable != null) {
                                    queryJoinTable.close();
                                }
                                return rectF;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = queryJoinTable;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    }
                    if (queryJoinTable != null) {
                        queryJoinTable.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } else {
            DBImage item = !TextUtils.isEmpty(dBImage.getLocalGroupId()) ? CloudUtils.getItem(GalleryCloudUtils.CLOUD_URI, sGetAndroidContext, "_id", dBImage.getLocalGroupId()) : CloudUtils.getItemByServerID(sGetAndroidContext, String.valueOf(dBImage.getGroupId()));
            if (item != null) {
                str = item.getPeopleFaceId();
                if (TextUtils.isEmpty(str)) {
                }
            }
        }
        str = null;
        if (TextUtils.isEmpty(str)) {
        }
    }

    public static synchronized BabyLockWallpaperDataManager getInstance() {
        BabyLockWallpaperDataManager babyLockWallpaperDataManager;
        synchronized (BabyLockWallpaperDataManager.class) {
            if (sInstance == null) {
                sInstance = new BabyLockWallpaperDataManager();
            }
            babyLockWallpaperDataManager = sInstance;
        }
        return babyLockWallpaperDataManager;
    }

    public static String getWallpaperFilePath(DBImage dBImage) {
        String str = null;
        if (dBImage == null) {
            return null;
        }
        if (!dBImage.isVideoType()) {
            str = RequestCloudItem.getDownloadOriginalFilePath(dBImage);
        }
        return TextUtils.isEmpty(str) ? dBImage.getThumbnailFile() : str;
    }

    /* access modifiers changed from: private */
    public synchronized void intialAlbumAndPhotos() {
        this.mAlbums = FaceManager.queryAllBabyAlbums();
        if (this.mAlbums != null && this.mAlbums.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<BabyAlbumInfo> it = this.mAlbums.iterator();
            while (it.hasNext()) {
                BabyAlbumInfo next = it.next();
                if (GalleryPreferences.BabyLock.isBabyAlbumForLockWallpaper(next.localId, next.isOtherShared)) {
                    arrayList.add(next);
                }
            }
            this.mAllPhotos = FaceManager.queryAllBabyAlbumPhotos(arrayList);
        }
    }

    private boolean isLegal(DBImage dBImage, int[] iArr) {
        String wallpaperFilePath = getWallpaperFilePath(dBImage);
        if (TextUtils.isEmpty(wallpaperFilePath) || !new File(wallpaperFilePath).isFile()) {
            return false;
        }
        int i = iArr[0];
        iArr[0] = i - 1;
        if (i <= 0) {
            return true;
        }
        ExifUtil.ExifInfo parseRotationInfo = ExifUtil.parseRotationInfo(dBImage.getJsonExifInteger("orientation", 0).intValue());
        boolean z = parseRotationInfo.rotation == 90 || parseRotationInfo.rotation == 270;
        int intValue = dBImage.getJsonExifInteger("imageWidth", 0).intValue();
        int intValue2 = dBImage.getJsonExifInteger("imageLength", 0).intValue();
        if (z) {
            int i2 = intValue2;
            intValue2 = intValue;
            intValue = i2;
        }
        return intValue < intValue2 || getFacePos(dBImage) != null;
    }

    public synchronized boolean existBabyAlbum() {
        return this.mAlbums != null && this.mAlbums.size() > 0;
    }

    public synchronized ArrayList<BabyAlbumInfo> getAllBabyAlbums() {
        return (ArrayList) this.mAlbums.clone();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        return null;
     */
    public synchronized String getRandomUri() {
        if (this.mAllPhotos != null) {
            if (this.mAllPhotos.size() != 0) {
                int[] iArr = {3};
                for (int i = 0; i < 5; i++) {
                    int random = (int) (Math.random() * ((double) this.mAllPhotos.size()));
                    Uri uri = GalleryCloudUtils.CLOUD_URI;
                    if (this.mAllPhotos.get(random).isOtherShared) {
                        uri = GalleryCloudUtils.SHARE_IMAGE_URI;
                    }
                    DBImage item = CloudUtils.getItem(uri, GalleryApp.sGetAndroidContext(), "_id", String.valueOf(this.mAllPhotos.get(random).id));
                    if (isLegal(item, iArr)) {
                        return createUri(item);
                    }
                }
                return null;
            }
        }
    }

    public void refresh() {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                BabyLockWallpaperDataManager.this.intialAlbumAndPhotos();
                return null;
            }
        });
    }
}
