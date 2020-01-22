package com.miui.gallery.provider.cache;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.collection.LongSparseArray;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.util.DebugUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import java.util.Map;

public class AlbumDelegate {
    private static final Object INITIALIZATION_LOCK = new Object();
    private static final String[] PROJECTION = {"_id", "attributes", "localFile"};
    private LongSparseArray<Long> mAttributes = new LongSparseArray<>();
    private boolean mInitialized = false;
    private LongSparseArray<MediaSortDateHelper.SortDate> mSortDate = new LongSparseArray<>();

    public AlbumDelegate() {
        this.mAttributes.put(-1000, 1L);
    }

    public void delete(long j) {
        Log.d(".provider.cache.AlbumDelegate", "delete album[%d] attributes", (Object) Long.valueOf(j));
        this.mAttributes.delete(j);
    }

    public MediaSortDateHelper.SortDate getSortDate(long j) {
        return this.mSortDate.get(j, MediaSortDateHelper.getSortDateProvider().getDefaultSortDate());
    }

    public void insert(long j, long j2) {
        Log.d(".provider.cache.AlbumDelegate", "insert album[%d] attributes", (Object) Long.valueOf(j));
        this.mAttributes.put(j, Long.valueOf(j2));
    }

    public boolean isAutoUpload(long j) {
        return (this.mAttributes.get(j, 0L).longValue() & 1) != 0;
    }

    public boolean isHidden(long j) {
        return (this.mAttributes.get(j, 0L).longValue() & 16) != 0 && !GalleryPreferences.HiddenAlbum.isShowHiddenAlbum();
    }

    public boolean isShowInHomePage(long j) {
        long longValue = this.mAttributes.get(j, 0L).longValue();
        return GalleryPreferences.HiddenAlbum.isShowHiddenAlbum() ? (longValue & 4) != 0 : (4 & longValue) != 0 && (longValue & 16) == 0;
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        synchronized (INITIALIZATION_LOCK) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                query = sQLiteDatabase.query("cloud", PROJECTION, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND (serverType=0)", (String[]) null, (String) null, (String) null, (String) null);
                if (query != null) {
                    MediaSortDateHelper.SortDateProvider sortDateProvider = MediaSortDateHelper.getSortDateProvider();
                    query.moveToFirst();
                    while (!query.isAfterLast()) {
                        long j = query.getLong(0);
                        this.mAttributes.put(j, Long.valueOf(query.getLong(1)));
                        this.mSortDate.put(j, sortDateProvider.getSortDateByAlbumPath(query.getString(2)));
                        query.moveToNext();
                    }
                    Log.d(".provider.cache.AlbumDelegate", "loaded %d albums from cursor[%d]", Integer.valueOf(this.mAttributes.size()), Integer.valueOf(query.getCount()));
                } else {
                    Log.e(".provider.cache.AlbumDelegate", "fill provider failed with a null cursor");
                }
                if (query != null) {
                    query.close();
                }
                Log.d(".provider.cache.AlbumDelegate", "load attributes cost: %s", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                this.mInitialized = true;
            } catch (SQLiteException e) {
                Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                generatorCommonParams.put("dbversion", String.valueOf(sQLiteDatabase.getVersion()));
                generatorCommonParams.put("exception", e.getMessage() + " : " + e.getCause());
                GallerySamplingStatHelper.recordErrorEvent("db_helper", "db_mediamanager_load", generatorCommonParams);
                DebugUtil.exportDB(false);
                GalleryDBHelper.getInstance().deleteDatabase();
                throw e;
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    public Long queryAttributes(long j) {
        Long l;
        if (this.mInitialized) {
            return this.mAttributes.get(j, 0L);
        }
        Log.d(".provider.cache.AlbumDelegate", "not initialized, wait");
        synchronized (INITIALIZATION_LOCK) {
            l = this.mAttributes.get(j, 0L);
        }
        return l;
    }

    public void update(long j, long j2) {
        Log.d(".provider.cache.AlbumDelegate", "update album[%d] attributes", (Object) Long.valueOf(j));
        this.mAttributes.put(j, Long.valueOf(j2));
    }
}
