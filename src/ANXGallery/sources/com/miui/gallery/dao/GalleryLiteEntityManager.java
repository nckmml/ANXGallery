package com.miui.gallery.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.dao.base.EntityManager;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.HomeMedia;
import com.miui.gallery.model.HomeMediaHeader;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.face.PeopleItem;
import java.io.Closeable;

public class GalleryLiteEntityManager extends EntityManager {

    private static class SingletonHolder {
        static final GalleryLiteEntityManager INSTANCE = new GalleryLiteEntityManager();
    }

    private GalleryLiteEntityManager() {
        super(GalleryApp.sGetAndroidContext(), "gallery_lite.db", 2);
    }

    public static GalleryLiteEntityManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public void onDatabaseDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w("GalleryLiteEntityManager", "onDatabaseDowngrade from %s to %s", Integer.valueOf(i), Integer.valueOf(i2));
    }

    /* access modifiers changed from: protected */
    public void onDatabaseUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.i("GalleryLiteEntityManager", "onDatabaseUpgrade: from %d to %d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    /* access modifiers changed from: protected */
    public void onInitTableList() {
        addTable(Album.class);
        addTable(PeopleItem.class);
        addTable(HomeMedia.class);
        addTable(HomeMediaHeader.class);
    }

    public void warmUp() {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cursor = null;
        try {
            cursor = rawQuery(Album.class, new String[]{"count(*)"}, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
            if (cursor != null && cursor.moveToFirst()) {
                Log.d("GalleryLiteEntityManager", "table Album has %d items", (Object) Integer.valueOf(cursor.getInt(0)));
            }
            Log.i("GalleryLiteEntityManager", "warm up costs: %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            Log.e("GalleryLiteEntityManager", (Throwable) e);
        } catch (Throwable th) {
            MiscUtil.closeSilently((Closeable) null);
            throw th;
        }
        MiscUtil.closeSilently(cursor);
    }
}
