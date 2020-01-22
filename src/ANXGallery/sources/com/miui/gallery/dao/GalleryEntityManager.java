package com.miui.gallery.dao;

import android.database.sqlite.SQLiteDatabase;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.library.Library;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.SyncTag;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.dao.base.EntityManager;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.PersistentResponse;
import com.miui.gallery.pendingtask.base.PendingTaskInfo;
import com.miui.gallery.provider.cloudmanager.MediaRemarkInfo;
import com.miui.gallery.provider.peoplecover.PeopleCover;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.face.PeopleItem;

public class GalleryEntityManager extends EntityManager {
    private static GalleryEntityManager instance;

    private GalleryEntityManager() {
        super(GalleryApp.sGetAndroidContext(), "gallery_sub.db", 12);
    }

    public static synchronized GalleryEntityManager getInstance() {
        GalleryEntityManager galleryEntityManager;
        synchronized (GalleryEntityManager.class) {
            if (instance == null) {
                instance = new GalleryEntityManager();
            }
            galleryEntityManager = instance;
        }
        return galleryEntityManager;
    }

    /* access modifiers changed from: protected */
    public void onDatabaseDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w("GalleryEntityManager", "onDatabaseDowngrade from %s to %s", Integer.valueOf(i), Integer.valueOf(i2));
    }

    /* access modifiers changed from: protected */
    public void onDatabaseUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.i("GalleryEntityManager", "onDatabaseUpgrade: from %d to %d", Integer.valueOf(i), Integer.valueOf(i2));
        if (i == 7) {
            sQLiteDatabase.execSQL(" UPDATE " + Card.class.getSimpleName() + " SET " + "localFlag" + " = " + 0 + ", " + "updateTime" + " = " + "createTime" + ", " + "createdBy" + " = " + 0);
        }
        if (i2 != 12) {
            return;
        }
        if (i == 10) {
            Log.i("GalleryEntityManager", "drop table %s", (Object) Album.class.getSimpleName());
            sQLiteDatabase.execSQL(getDropTableSql(Album.class.getSimpleName()));
        } else if (i == 11) {
            Log.i("GalleryEntityManager", "drop table %s & %s", PeopleItem.class.getSimpleName(), Album.class.getSimpleName());
            sQLiteDatabase.execSQL(getDropTableSql(PeopleItem.class.getSimpleName()));
            sQLiteDatabase.execSQL(getDropTableSql(Album.class.getSimpleName()));
        }
    }

    /* access modifiers changed from: protected */
    public void onInitTableList() {
        addTable(Card.class);
        addTable(PendingTaskInfo.class);
        addTable(PersistentResponse.class);
        addTable(MediaRemarkInfo.class);
        addTable(PeopleCover.class);
        addTable(DeleteRecord.class);
        addTable(Record.class);
        addTable(ImageFeature.class);
        addTable(Library.class);
        addTable(SyncTag.class);
    }
}
