package com.miui.gallery.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.miui.gallery.data.CityDatabaseUtils;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.portJava.Polygon;
import com.miui.gallery.util.portJava.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class CityDatabaseHelper extends SQLiteOpenHelper {
    private static final String[] PROJECTION = {"*"};
    public static final ArrayList<GalleryDBHelper.TableColumn> sCityBoundaryColumns = new ArrayList<>();
    private final SQLiteDatabase mSqliteDatabase = openDB();

    public static class CityBoundRect {
        public BoundRect[] boundRects;
        public String cityId;

        public static class BoundRect implements Serializable {
            public static int FLAG_ADD = 1;
            public static int FlAG_MINUS = 2;
            private static final long serialVersionUID = 1;
            public Rectangle boundRect;
            public int flag;

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("BoundRect flag:");
                sb.append(this.flag);
                sb.append("  BoundRect:");
                Rectangle rectangle = this.boundRect;
                sb.append(rectangle != null ? rectangle.toString() : "null");
                return sb.toString();
            }
        }

        private CityBoundRect(String str, BoundRect[] boundRectArr) {
            this.cityId = str;
            this.boundRects = boundRectArr;
        }

        public boolean containsIntCoordinate(int i, int i2) {
            BoundRect[] boundRectArr = this.boundRects;
            if (boundRectArr != null) {
                for (BoundRect boundRect : boundRectArr) {
                    if (boundRect.flag == BoundRect.FLAG_ADD && boundRect.boundRect.contains(i, i2)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class CityBoundRectList extends HashMap<String, CityBoundRect> {
    }

    public static class CityBoundary {
        public Boundary[] boundaries;
        public String cityId;

        public static class Boundary implements Serializable {
            public static int FLAG_ADD = 1;
            public static int FlAG_MINUS = 2;
            private static final long serialVersionUID = 1;
            public Polygon boundary;
            public int flag;

            public String toString() {
                String str;
                StringBuilder sb = new StringBuilder();
                sb.append("Boundary flag:");
                sb.append(this.flag);
                sb.append("  boundray:");
                if (this.boundary != null) {
                    str = this.boundary.xpoints[0] + "," + this.boundary.ypoints[0];
                } else {
                    str = "null";
                }
                sb.append(str);
                return sb.toString();
            }
        }

        private CityBoundary(String str, Boundary[] boundaryArr) {
            this.cityId = str;
            this.boundaries = boundaryArr;
        }

        public boolean containsIntCoordinate(int i, int i2) {
            boolean z = false;
            for (Boundary boundary : this.boundaries) {
                if (boundary.flag == Boundary.FLAG_ADD) {
                    if (!z) {
                        z = boundary.boundary.contains(i, i2);
                    }
                } else if (boundary.flag == Boundary.FlAG_MINUS && boundary.boundary.contains(i, i2)) {
                    return false;
                }
            }
            return z;
        }
    }

    public static class CityBoundaryColumn extends GalleryDBHelper.TableColumn {
        CityBoundaryColumn(String str, String str2) {
            super(str, str2);
        }
    }

    static {
        sCityBoundaryColumns.add(new CityBoundaryColumn("cityid", "text"));
        sCityBoundaryColumns.add(new CityBoundaryColumn("boundary", "blob"));
        sCityBoundaryColumns.add(new CityBoundaryColumn("rect", "blob"));
    }

    public CityDatabaseHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    static /* synthetic */ ConcurrentMap lambda$queryCityBoundary$23(Cursor cursor) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        if (cursor == null) {
            Log.e("CityDatabaseHelper", "queryCityBoundary fails, the returned cursor is null");
            return concurrentHashMap;
        }
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            concurrentHashMap.put(string, new CityBoundary(string, (CityBoundary.Boundary[]) CityDatabaseUtils.PolygonHelper.parseFromByteArray(cursor.getBlob(1))));
        }
        return concurrentHashMap;
    }

    private SQLiteDatabase openDB() {
        try {
            return getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isDbOpened() {
        SQLiteDatabase sQLiteDatabase = this.mSqliteDatabase;
        return sQLiteDatabase != null && sQLiteDatabase.isOpen();
    }

    public CityBoundRectList loadCityBoundRects() {
        if (!isDbOpened()) {
            return null;
        }
        return (CityBoundRectList) SafeDBUtil.safeQuery(this.mSqliteDatabase, "cityBoundary", new String[]{"cityid", "rect"}, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<CityBoundRectList>() {
            public CityBoundRectList handle(Cursor cursor) {
                if (cursor == null) {
                    Log.e("CityDatabaseHelper", "loadCityBoundRects fails, the returned cursor is null");
                    return null;
                }
                CityBoundRectList cityBoundRectList = new CityBoundRectList();
                int columnIndex = cursor.getColumnIndex("cityid");
                int columnIndex2 = cursor.getColumnIndex("rect");
                while (cursor.moveToNext()) {
                    String string = cursor.getString(columnIndex);
                    cityBoundRectList.put(string, new CityBoundRect(string, (CityBoundRect.BoundRect[]) CityDatabaseUtils.PolygonHelper.parseFromByteArray(cursor.getBlob(columnIndex2))));
                }
                return cityBoundRectList;
            }
        });
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.e("CityDatabaseHelper", "should not create: city.db is a readonly database");
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.e("CityDatabaseHelper", "should not downgrade: city.db is a readonly database");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.e("CityDatabaseHelper", "should not upgrade: city.db is a readonly database");
    }

    public ConcurrentMap<String, CityBoundary> queryCityBoundary(ArrayList<String> arrayList) {
        if (!isDbOpened() || arrayList.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("cityid IN(");
        for (int i = 0; i < arrayList.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append("\"" + arrayList.get(i) + "\"");
        }
        sb.append(')');
        return (ConcurrentMap) SafeDBUtil.safeQuery(this.mSqliteDatabase, "cityBoundary", PROJECTION, sb.toString(), (String[]) null, (String) null, $$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU.INSTANCE);
    }
}
