package com.miui.gallery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.PackageUtils;
import com.miui.gallery.util.SafeDBUtil;

public class LocationGenerator {
    private static LocationGenerator sInstance;
    /* access modifiers changed from: private */
    public String[] CLOUD_PROJECTION = {"_id", "exifGPSLatitude", "exifGPSLatitudeRef", "exifGPSLongitude", "exifGPSLongitudeRef", "fileName"};
    private Future mFuture;

    private LocationGenerator() {
    }

    public static synchronized LocationGenerator getInstance() {
        LocationGenerator locationGenerator;
        synchronized (LocationGenerator.class) {
            if (sInstance == null) {
                sInstance = new LocationGenerator();
            }
            locationGenerator = sInstance;
        }
        return locationGenerator;
    }

    /* access modifiers changed from: private */
    public ContentValues toValues(String str) {
        ContentValues contentValues = new ContentValues();
        if (TextUtils.isEmpty(str)) {
            contentValues.putNull("location");
        } else {
            contentValues.put("location", str);
        }
        return contentValues;
    }

    public synchronized void generate(final Context context) {
        if (this.mFuture == null || this.mFuture.isDone()) {
            this.mFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, LocationGenerator.this.CLOUD_PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Void>() {
                        public Void handle(Cursor cursor) {
                            String str;
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    int i = cursor.getInt(0);
                                    String string = cursor.getString(1);
                                    String string2 = cursor.getString(2);
                                    String string3 = cursor.getString(3);
                                    String string4 = cursor.getString(4);
                                    String appNameForScreenshot = PackageUtils.getAppNameForScreenshot(cursor.getString(5));
                                    if (!TextUtils.isEmpty(appNameForScreenshot)) {
                                        str = appNameForScreenshot;
                                    } else {
                                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string3)) {
                                            String decode = CitySearcher.getInstance().decode(new Coordinate(string, string2, string3, string4));
                                            if (LocationUtil.isLocationValidate(decode)) {
                                                str = LocationUtil.getCityNameFromRes(context, decode);
                                            }
                                        }
                                        str = null;
                                    }
                                    SafeDBUtil.safeUpdate(context, GalleryContract.Cloud.CLOUD_URI, LocationGenerator.this.toValues(str), "_id=?", new String[]{String.valueOf(i)});
                                }
                            }
                            return null;
                        }
                    });
                    SafeDBUtil.safeQuery(context, GalleryContract.ShareImage.SHARE_URI, LocationGenerator.this.CLOUD_PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Void>() {
                        public Void handle(Cursor cursor) {
                            String str;
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    int i = cursor.getInt(0);
                                    String string = cursor.getString(1);
                                    String string2 = cursor.getString(2);
                                    String string3 = cursor.getString(3);
                                    String string4 = cursor.getString(4);
                                    String appNameForScreenshot = PackageUtils.getAppNameForScreenshot(cursor.getString(5));
                                    if (!TextUtils.isEmpty(appNameForScreenshot)) {
                                        str = appNameForScreenshot;
                                    } else {
                                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string3)) {
                                            String decode = CitySearcher.getInstance().decode(new Coordinate(string, string2, string3, string4));
                                            if (LocationUtil.isLocationValidate(decode)) {
                                                str = LocationUtil.getCityNameFromRes(context, decode);
                                            }
                                        }
                                        str = null;
                                    }
                                    SafeDBUtil.safeUpdate(context, GalleryContract.ShareImage.SHARE_URI, LocationGenerator.this.toValues(str), "_id=?", new String[]{String.valueOf(i)});
                                }
                            }
                            return null;
                        }
                    });
                    LocationGenerator.this.release();
                    context.getContentResolver().notifyChange(GalleryContract.Media.URI, (ContentObserver) null, false);
                    return null;
                }
            });
        }
    }

    public synchronized void release() {
        if (this.mFuture != null) {
            this.mFuture.cancel();
        }
        sInstance = null;
    }
}
