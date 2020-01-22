package com.miui.gallery.provider;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.miui.gallery.activity.facebaby.BabyLockWallpaperSettingActivity;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.data.DBCloud;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBShareImage;
import com.miui.gallery.data.DecodeUtils;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ScreenUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import miui.os.Environment;

public class BabyLockWallpaperProvider extends ContentProvider {
    public static final Uri BASE_URI = Uri.parse("content://com.miui.gallery.cloud.baby.wallpaper_provider");

    private static final class CroppedPhotosPathHelper {
        private static final String CROPPED_PHOTOS_DIR = (Environment.getExternalStorageDirectory().getPath() + File.separator + ".cropped-photos");
        private static int sLastFileIndex = 1;

        private static String getCroppedPhotoFileName(Uri uri, String str) {
            return getCroppedPhotosPathPrefix() + getCroppedPhotosPathPostfix(uri, str);
        }

        static String getCroppedPhotoPath(Uri uri, String str) {
            File[] listFiles;
            if (uri == null || TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(CROPPED_PHOTOS_DIR, getCroppedPhotoFileName(uri, str));
            if (file.isFile()) {
                return file.getAbsolutePath();
            }
            sLastFileIndex = 1 - sLastFileIndex;
            File file2 = new File(CROPPED_PHOTOS_DIR, getCroppedPhotoFileName(uri, str));
            if (!file2.isFile() && (listFiles = new File(CROPPED_PHOTOS_DIR).listFiles()) != null) {
                String croppedPhotosPathPrefix = getCroppedPhotosPathPrefix();
                for (File file3 : listFiles) {
                    if (file3.getName().startsWith(croppedPhotosPathPrefix)) {
                        MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file3);
                    }
                }
            }
            return file2.getAbsolutePath();
        }

        private static String getCroppedPhotosPathPostfix(Uri uri, String str) {
            return uri.hashCode() + "-" + str.hashCode();
        }

        private static String getCroppedPhotosPathPrefix() {
            return sLastFileIndex + "-";
        }
    }

    private static final class PathSegments {
        final boolean isSharerAlbum;
        final String itemLocalId;

        PathSegments(boolean z, String str) {
            this.isSharerAlbum = z;
            this.itemLocalId = str;
        }

        static PathSegments parse(List<String> list) {
            if (list == null || list.size() < 2) {
                return null;
            }
            String str = list.get(0);
            String str2 = list.get(1);
            if (("owner_album".equals(str) || "sharer_album".equals(str)) && !TextUtils.isEmpty(str2)) {
                return new PathSegments("sharer_album".equals(str), str2);
            }
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.isSharerAlbum ? "sharer_album" : "owner_album");
            sb.append("/");
            sb.append(this.itemLocalId);
            return sb.toString();
        }
    }

    private String getNextLockWallpaperUri() {
        return BabyLockWallpaperDataManager.getInstance().getRandomUri();
    }

    private String getSettingsComponent() {
        return new ComponentName(getContext(), BabyLockWallpaperSettingActivity.class).flattenToString();
    }

    private boolean provideLockWallpaperEnabled() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean existBabyAlbum = BabyLockWallpaperDataManager.getInstance().existBabyAlbum();
        Log.d("BabyLock", "provideLockWallpaperEnabled: " + existBabyAlbum + ", cost: " + (System.currentTimeMillis() - currentTimeMillis));
        return existBabyAlbum;
    }

    private static boolean saveBitmapFile(Bitmap bitmap, String str) {
        if (bitmap == null) {
            return false;
        }
        File file = new File(str);
        File saveBitmapToFile = CloudUtils.saveBitmapToFile(bitmap, file.getParent(), file.getName());
        bitmap.recycle();
        return saveBitmapToFile != null;
    }

    private String tryToCrop(DBImage dBImage, Uri uri, String str) {
        boolean z;
        int i;
        RectF facePos;
        float f;
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Log.d("BabyLock", "start tryToCrop %s", (Object) dBImage.getId());
        ExifUtil.ExifInfo parseRotationInfo = ExifUtil.parseRotationInfo(str2, (byte[]) null);
        if (parseRotationInfo != null) {
            Log.d("BabyLock", "rotation: %s, flip: %s", Integer.valueOf(parseRotationInfo.rotation), Boolean.valueOf(parseRotationInfo.flip));
            i = parseRotationInfo.rotation;
            z = i == 90 || i == 270;
        } else {
            i = 0;
            z = false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str2, options);
        int i2 = z ? options.outHeight : options.outWidth;
        int i3 = z ? options.outWidth : options.outHeight;
        if (i2 < i3 && i == 0) {
            return str2;
        }
        String croppedPhotoPath = CroppedPhotosPathHelper.getCroppedPhotoPath(uri, str);
        if (TextUtils.isEmpty(croppedPhotoPath)) {
            return null;
        }
        if (new File(croppedPhotoPath).isFile()) {
            Log.d("BabyLock", "cache hit");
            return croppedPhotoPath;
        }
        int screenWidth = ScreenUtils.getScreenWidth();
        int screenHeight = ScreenUtils.getScreenHeight();
        if (i2 > i3 && (facePos = BabyLockWallpaperDataManager.getFacePos(dBImage)) != null) {
            Log.d("BabyLock", "start to crop face region");
            int i4 = (i3 * screenWidth) / screenHeight;
            float f2 = (float) i2;
            float centerX = ExifUtil.adjustRectOrientation(1, 1, facePos, dBImage.getJsonExifInteger("orientation", 0).intValue(), true).centerX() * f2;
            float f3 = (float) (i4 / 2);
            float f4 = 0.0f;
            if (centerX < f3) {
                f = Math.min(((float) i4) + 0.0f, f2);
            } else {
                f = Math.min(centerX + f3, f2);
                f4 = Math.max(0.0f, f - ((float) i4));
            }
            try {
                Rect rect = new Rect((int) f4, 0, (int) f, i3);
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = BitmapUtils.computeSampleSizeSmaller(rect.width(), rect.height(), screenWidth, screenHeight);
                if (parseRotationInfo != null) {
                    rect = ExifUtil.adjustRectOrientation(i2, i3, rect, parseRotationInfo.exifOrientation, false);
                }
                if (saveBitmapFile(DecodeUtils.considerOrientation(BitmapRegionDecoder.newInstance(str2, true).decodeRegion(rect, options2), parseRotationInfo), croppedPhotoPath)) {
                    return croppedPhotoPath;
                }
                Log.w("BabyLock", "failed to save cropped bitmap!");
            } catch (Throwable th) {
                Log.e("BabyLock", "tryToCrop occur error.\n", (Object) th);
                th.printStackTrace();
            }
        }
        try {
            Log.d("BabyLock", "start to adjust image orientation");
            BitmapFactory.Options options3 = new BitmapFactory.Options();
            options3.inSampleSize = BitmapUtils.computeSampleSizeSmaller(i2, i3, screenWidth, screenHeight);
            if (saveBitmapFile(DecodeUtils.considerOrientation(BitmapFactory.decodeFile(str2, options3), parseRotationInfo), croppedPhotoPath)) {
                return croppedPhotoPath;
            }
            Log.w("BabyLock", "failed to save bitmap!");
            return str2;
        } catch (Throwable th2) {
            Log.e("BabyLock", "adjust image orientation occur error.\n", (Object) th2);
        }
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (MiscUtil.getAppVersionCode("com.android.systemui") >= 201906250 && !"com.android.systemui".equalsIgnoreCase(getCallingPackage())) {
            return bundle2;
        }
        if ("enableProvideLockWallpaper".equals(str)) {
            bundle2.putBoolean("result_boolean", provideLockWallpaperEnabled());
        } else if ("getSettingsComponent".equals(str)) {
            bundle2.putString("result_string", getSettingsComponent());
        } else if ("getNextLockWallpaperUri".equals(str)) {
            bundle2.putString("result_string", getNextLockWallpaperUri());
        } else {
            Log.e("BabyLock", "unsupported method: " + str);
        }
        return bundle2;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        if (!"r".equals(str)) {
            Log.e("BabyLock", "illegal mode: " + str);
            return null;
        } else if (uri == null || !"com.miui.gallery.cloud.baby.wallpaper_provider".equals(uri.getAuthority())) {
            Log.e("BabyLock", "illegal uri: " + uri);
            return null;
        } else {
            final PathSegments parse = PathSegments.parse(uri.getPathSegments());
            if (parse == null) {
                Log.e("BabyLock", "illegal uri: " + uri);
                return null;
            }
            DBImage dBImage = (DBImage) GalleryUtils.safeQuery(parse.isSharerAlbum ? "shareImage" : "cloud", CloudUtils.getProjectionAll(), String.format(Locale.US, "%s=?", new Object[]{"_id"}), new String[]{parse.itemLocalId}, (String) null, new GalleryUtils.QueryHandler<DBImage>() {
                public DBImage handle(Cursor cursor) {
                    if (cursor == null || !cursor.moveToNext()) {
                        return null;
                    }
                    return parse.isSharerAlbum ? new DBShareImage(cursor) : new DBCloud(cursor);
                }
            });
            String tryToCrop = tryToCrop(dBImage, uri, BabyLockWallpaperDataManager.getWallpaperFilePath(dBImage));
            if (!TextUtils.isEmpty(tryToCrop)) {
                try {
                    return ParcelFileDescriptor.open(new File(tryToCrop), 268435456);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
