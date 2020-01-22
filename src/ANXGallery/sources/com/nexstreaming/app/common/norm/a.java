package com.nexstreaming.app.common.norm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.google.gson_nex.Gson;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: NormDb */
public abstract class a extends SQLiteOpenHelper {
    private static final String LOG_TAG = "NormDb";
    private final Gson gson = new Gson();

    public a(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
    }

    public static String camelCaseToLCUnderscore(String str) {
        String lowerCase = str.replaceAll("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])", "_").toLowerCase(Locale.ENGLISH);
        if (lowerCase.length() < 1) {
            return "_";
        }
        char charAt = lowerCase.charAt(0);
        if (charAt == '_') {
            return lowerCase;
        }
        if (charAt >= 'a' && charAt <= 'z') {
            return lowerCase;
        }
        return "_" + lowerCase;
    }

    private <T extends b> int count_internal(Class<T> cls, String str, Object[] objArr, boolean z) {
        String[] strArr;
        if (objArr != null) {
            strArr = new String[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                strArr[i] = String.valueOf(objArr[i]);
            }
        } else {
            strArr = null;
        }
        c a = c.a((Class<? extends b>) cls);
        Cursor query = getReadableDatabase().query(a.c(), new String[]{a.f().a}, str, strArr, (String) null, (String) null, (String) null);
        int count = query.getCount();
        query.close();
        return count;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r7v2 */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r7v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    private <T extends b> ContentValues getContentValuesForRecord(T t) {
        ContentValues contentValues = new ContentValues();
        try {
            for (NormColumnInfo normColumnInfo : t.getTableInfo().e()) {
                if (normColumnInfo != null) {
                    if (!normColumnInfo.g) {
                        ? r7 = 0;
                        switch (normColumnInfo.c) {
                            case TEXT:
                                Object obj = normColumnInfo.b.get(t);
                                if (obj != null) {
                                    contentValues.put(normColumnInfo.a, String.valueOf(obj));
                                    break;
                                } else {
                                    contentValues.putNull(normColumnInfo.a);
                                    break;
                                }
                            case INT:
                                contentValues.put(normColumnInfo.a, Integer.valueOf(normColumnInfo.b.getInt(t)));
                                break;
                            case LONG:
                                if (!normColumnInfo.f) {
                                    contentValues.put(normColumnInfo.a, Long.valueOf(normColumnInfo.b.getLong(t)));
                                    break;
                                } else {
                                    Object obj2 = normColumnInfo.b.get(t);
                                    if (!(obj2 instanceof b)) {
                                        break;
                                    } else {
                                        contentValues.put(normColumnInfo.a, Long.valueOf(((b) obj2).getDbRowID()));
                                        break;
                                    }
                                }
                            case DOUBLE:
                                contentValues.put(normColumnInfo.a, Double.valueOf(normColumnInfo.b.getDouble(t)));
                                break;
                            case FLOAT:
                                contentValues.put(normColumnInfo.a, Float.valueOf(normColumnInfo.b.getFloat(t)));
                                break;
                            case ENUM:
                                Enum enumR = (Enum) normColumnInfo.b.get(t);
                                String str = normColumnInfo.a;
                                if (enumR != null) {
                                    r7 = enumR.name();
                                }
                                contentValues.put(str, r7);
                                break;
                            case BOOL:
                                contentValues.put(normColumnInfo.a, Integer.valueOf(normColumnInfo.b.getBoolean(t) ? 1 : 0));
                                break;
                            case BLOB:
                                contentValues.put(normColumnInfo.a, (byte[]) normColumnInfo.b.get(t));
                                break;
                            case PNG:
                                Bitmap bitmap = (Bitmap) normColumnInfo.b.get(t);
                                if (bitmap != null) {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    r7 = byteArrayOutputStream.toByteArray();
                                }
                                contentValues.put(normColumnInfo.a, r7);
                                break;
                            case JPEG:
                                Bitmap bitmap2 = (Bitmap) normColumnInfo.b.get(t);
                                if (bitmap2 != null) {
                                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream2);
                                    r7 = byteArrayOutputStream2.toByteArray();
                                }
                                contentValues.put(normColumnInfo.a, r7);
                                break;
                            case JSON:
                                contentValues.put(normColumnInfo.a, this.gson.toJson(normColumnInfo.b.get(t)));
                                break;
                        }
                    }
                }
            }
            return contentValues;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:29|30|31|32|72) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00d1 */
    private <T extends b> List<T> query_internal(Class<T> cls, String str, Object[] objArr, boolean z) {
        String[] strArr;
        Object[] objArr2 = objArr;
        if (objArr2 != null) {
            String[] strArr2 = new String[objArr2.length];
            for (int i = 0; i < objArr2.length; i++) {
                strArr2[i] = String.valueOf(objArr2[i]);
            }
            strArr = strArr2;
        } else {
            strArr = null;
        }
        c a = c.a((Class<? extends b>) cls);
        NormColumnInfo[] e = a.e();
        Cursor query = getReadableDatabase().query(a.c(), a.d(), str, strArr, (String) null, (String) null, (String) null);
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        try {
            query.moveToPosition(-1);
            while (query.moveToNext()) {
                b bVar = (b) cls.newInstance();
                for (int i2 = 0; i2 < e.length; i2++) {
                    switch (e[i2].c) {
                        case TEXT:
                            e[i2].b.set(bVar, query.getString(i2));
                            break;
                        case INT:
                            e[i2].b.setInt(bVar, query.getInt(i2));
                            break;
                        case LONG:
                            if (e[i2].f) {
                                if (z) {
                                    if (!hashMap.containsKey(e[i2].j)) {
                                        hashMap.put(e[i2].j, new HashMap());
                                    }
                                    long j = query.getLong(query.getColumnIndex(e[i2].a));
                                    Object obj = ((Map) hashMap.get(e[i2].j)).get(Long.valueOf(j));
                                    if (obj == null) {
                                        obj = findById(e[i2].j, j);
                                        ((Map) hashMap.get(e[i2].j)).put(Long.valueOf(j), obj);
                                    }
                                    e[i2].b.set(bVar, obj);
                                    break;
                                } else {
                                    break;
                                }
                            } else {
                                e[i2].b.setLong(bVar, query.getLong(i2));
                                break;
                            }
                        case DOUBLE:
                            e[i2].b.setDouble(bVar, query.getDouble(i2));
                            break;
                        case FLOAT:
                            e[i2].b.setFloat(bVar, query.getFloat(i2));
                            break;
                        case ENUM:
                            Class type = e[i2].b.getType();
                            String string = query.getString(i2);
                            if (string == null) {
                                break;
                            } else {
                                e[i2].b.set(bVar, Enum.valueOf(type, string));
                                e[i2].b.set(bVar, (Object) null);
                                break;
                            }
                        case BOOL:
                            e[i2].b.setBoolean(bVar, query.getInt(i2) != 0);
                            break;
                        case BLOB:
                            e[i2].b.set(bVar, query.getBlob(i2));
                            break;
                        case PNG:
                        case JPEG:
                            byte[] blob = query.getBlob(i2);
                            e[i2].b.set(bVar, BitmapFactory.decodeByteArray(blob, 0, blob.length));
                            break;
                        case JSON:
                            e[i2].b.set(bVar, this.gson.fromJson(query.getString(i2), e[i2].b.getGenericType()));
                            break;
                    }
                }
                arrayList.add(bVar);
            }
            query.close();
            return arrayList;
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    private <T extends b> void updateRecIndex(T t, long j) {
        try {
            t.getTableInfo().f().b.setLong(t, j);
            t.addedOrUpdatedToDb = true;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T extends b> void add(T t) {
        updateRecIndex(t, getWritableDatabase().insert(t.getTableInfo().c(), (String) null, getContentValuesForRecord(t)));
    }

    public <T extends b> void addOrUpdate(T t) {
        updateRecIndex(t, getWritableDatabase().insertWithOnConflict(t.getTableInfo().c(), (String) null, getContentValuesForRecord(t), 5));
    }

    public void beginTransaction() {
        getWritableDatabase().beginTransaction();
    }

    public <T extends b> int count(Class<T> cls) {
        return count_internal(cls, (String) null, (Object[]) null, false);
    }

    public <T extends b> int count(Class<T> cls, String str, Object... objArr) {
        return count_internal(cls, str, objArr, false);
    }

    public <T extends b> void delete(T t) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String c = t.getTableInfo().c();
        writableDatabase.delete(c, t.getTableInfo().f() + " = ?", new String[]{String.valueOf(t.getDbRowID())});
    }

    public <T extends b> void delete(Class<T> cls, String str, Object... objArr) {
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            strArr[i] = String.valueOf(objArr[i]);
        }
        getWritableDatabase().delete(c.a((Class<? extends b>) cls).c(), str, strArr);
    }

    public void endTransaction() throws SQLiteFullException {
        try {
            getWritableDatabase().endTransaction();
        } catch (SQLiteFullException unused) {
            throw new SQLiteFullException();
        }
    }

    public <T extends b> T findById(Class<T> cls, long j) {
        List<T> query = query(cls, c.a((Class<? extends b>) cls).f().a + " = ? LIMIT 1", Long.valueOf(j));
        if (query == null || query.isEmpty()) {
            return null;
        }
        return (b) query.get(0);
    }

    public <T extends b> T findFirst(Class<T> cls, String str, Object... objArr) {
        List<T> query = query(cls, str + " LIMIT 1", objArr);
        if (query == null || query.isEmpty()) {
            return null;
        }
        return (b) query.get(0);
    }

    public <T extends b> long findFirstRowId(Class<T> cls, String str, Object... objArr) {
        List<T> query = query(cls, str + " LIMIT 1", objArr);
        if (query == null || query.isEmpty()) {
            return 0;
        }
        return ((b) query.get(0)).getDbRowID();
    }

    /* access modifiers changed from: protected */
    public abstract Class<? extends b>[] getTableClasses();

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (Class a : getTableClasses()) {
            for (String str : c.a((Class<? extends b>) a).a()) {
                Log.d(LOG_TAG, "CreateTable:" + str);
                sQLiteDatabase.execSQL(str);
                Log.d(LOG_TAG, "Created");
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (Class a : getTableClasses()) {
            for (String str : c.a((Class<? extends b>) a).b()) {
                Log.d(LOG_TAG, "onDowngrade:" + str);
                sQLiteDatabase.execSQL(str);
                Log.d(LOG_TAG, "onDowngrade");
            }
        }
        onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(LOG_TAG, "onUpgrade oldVersion=" + i + ", newVersion=" + i2);
        if (i < 11) {
            for (Class a : getTableClasses()) {
                for (String str : c.a((Class<? extends b>) a).b()) {
                    Log.d(LOG_TAG, "DropTable:" + str);
                    sQLiteDatabase.execSQL(str);
                    Log.d(LOG_TAG, "Dropped");
                }
            }
            onCreate(sQLiteDatabase);
        } else if (i < i2) {
            for (Class a2 : getTableClasses()) {
                for (String str2 : c.a((Class<? extends b>) a2).a(i, i2)) {
                    Log.d(LOG_TAG, "onUpgrade:" + str2);
                    sQLiteDatabase.execSQL(str2);
                    Log.d(LOG_TAG, "onUpgrade end");
                }
            }
        }
    }

    public <T extends b> List<T> query(Class<T> cls) {
        return query_internal(cls, (String) null, (Object[]) null, true);
    }

    public <T extends b> List<T> query(Class<T> cls, String str, Object... objArr) {
        return query_internal(cls, str, objArr, true);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    public <T extends b, RESULT_TYPE> List<RESULT_TYPE> queryIndividualField(Class<T> cls, String str, String str2, Object... objArr) {
        String[] strArr;
        Object obj;
        String str3 = str;
        Object[] objArr2 = objArr;
        if (objArr2 != null) {
            String[] strArr2 = new String[objArr2.length];
            for (int i = 0; i < objArr2.length; i++) {
                strArr2[i] = String.valueOf(objArr2[i]);
            }
            strArr = strArr2;
        } else {
            strArr = null;
        }
        c a = c.a((Class<? extends b>) cls);
        NormColumnInfo a2 = a.a(str3);
        Cursor query = getReadableDatabase().query(a.c(), new String[]{str3}, str2, strArr, (String) null, (String) null, (String) null);
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        try {
            query.moveToPosition(-1);
            while (query.moveToNext()) {
                switch (a2.c) {
                    case TEXT:
                        obj = query.getString(0);
                        continue;
                    case INT:
                        obj = Integer.valueOf(query.getInt(0));
                        continue;
                    case LONG:
                        if (!a2.f) {
                            obj = Long.valueOf(query.getLong(0));
                            break;
                        } else {
                            if (!hashMap.containsKey(a2.j)) {
                                hashMap.put(a2.j, new HashMap());
                            }
                            long j = query.getLong(0);
                            Object obj2 = ((Map) hashMap.get(a2.j)).get(Long.valueOf(j));
                            if (obj2 == null) {
                                obj2 = findById(a2.j, j);
                                ((Map) hashMap.get(a2.j)).put(Long.valueOf(j), obj2);
                            }
                            obj = obj2;
                            continue;
                        }
                    case DOUBLE:
                        obj = Double.valueOf(query.getDouble(0));
                        continue;
                    case FLOAT:
                        obj = Float.valueOf(query.getFloat(0));
                        continue;
                    case ENUM:
                        Class type = a2.b.getType();
                        String string = query.getString(0);
                        if (string != null) {
                            try {
                                obj = Enum.valueOf(type, string);
                                continue;
                            } catch (IllegalArgumentException unused) {
                            }
                        }
                    case BOOL:
                        obj = Boolean.valueOf(query.getInt(0) != 0);
                        continue;
                    case BLOB:
                        obj = query.getBlob(0);
                        continue;
                    case PNG:
                    case JPEG:
                        byte[] blob = query.getBlob(0);
                        obj = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                        continue;
                    case JSON:
                        obj = this.gson.fromJson(query.getString(0), a2.b.getGenericType());
                        continue;
                }
                obj = null;
                arrayList.add(obj);
            }
            return arrayList;
        } finally {
            query.close();
        }
    }

    public void setTransactionSuccessful() {
        getWritableDatabase().setTransactionSuccessful();
    }

    public <T extends b> void update(T t) {
        ContentValues contentValuesForRecord = getContentValuesForRecord(t);
        try {
            getWritableDatabase().update(t.getTableInfo().c(), contentValuesForRecord, "_id = ?", new String[]{String.valueOf(t.getTableInfo().f().b.getLong(t))});
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
