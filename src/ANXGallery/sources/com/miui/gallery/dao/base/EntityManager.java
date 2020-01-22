package com.miui.gallery.dao.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class EntityManager {
    private SQLiteOpenHelper dbHelper;
    /* access modifiers changed from: private */
    public ArrayList<Class<? extends Entity>> mTables = null;

    private class SQLiteOpenHelperImpl extends SQLiteOpenHelper {
        public SQLiteOpenHelperImpl(Context context, String str, int i) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            EntityManager.this.initTableList();
            try {
                Iterator it = EntityManager.this.mTables.iterator();
                while (it.hasNext()) {
                    EntityManager.createTable(sQLiteDatabase, (Class) it.next());
                }
            } catch (Exception e) {
                Log.e("EntityManager", "Db onCreate error.\n", (Object) e);
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            EntityManager.this.onDatabaseDowngrade(sQLiteDatabase, i, i2);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            EntityManager.this.initTableList();
            try {
                EntityManager.checkTableChange(sQLiteDatabase, EntityManager.this.mTables);
                EntityManager.this.onDatabaseUpgrade(sQLiteDatabase, i, i2);
            } catch (Exception e) {
                Log.e("EntityManager", "Db onUpgrade error.\n", (Object) e);
            }
        }
    }

    public EntityManager(Context context, String str, int i) {
        this.dbHelper = new SQLiteOpenHelperImpl(context, str, i);
    }

    public static void checkTableChange(SQLiteDatabase sQLiteDatabase, List<Class<? extends Entity>> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i = 0;
        while (i < size) {
            Entity entity = (Entity) newInstance(list.get(i));
            String tableName = entity.getTableName();
            List<TableColumn> tableColumns = entity.getTableColumns();
            Cursor cursor = null;
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE type=? AND name=?", new String[]{"table", tableName});
                if (cursor == null || !cursor.moveToFirst()) {
                    arrayList.add(getCreateTableSql(tableName, tableColumns));
                } else {
                    String string = cursor.getString(0);
                    for (String trim : string.substring(string.indexOf("(") + 1).split(",")) {
                        String str = trim.trim().split(" ", 2)[0];
                        Iterator<TableColumn> it = tableColumns.iterator();
                        while (it.hasNext()) {
                            if (str.equalsIgnoreCase(it.next().name)) {
                                it.remove();
                            }
                        }
                    }
                    for (TableColumn addColumnSql : tableColumns) {
                        arrayList.add(getAddColumnSql(tableName, addColumnSql));
                    }
                }
                i++;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        sQLiteDatabase.beginTransaction();
        try {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                sQLiteDatabase.execSQL((String) it2.next());
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, Class<? extends Entity> cls) {
        Entity entity = (Entity) newInstance(cls);
        if (entity != null) {
            sQLiteDatabase.execSQL(getCreateTableSql(entity.getTableName(), entity.getTableColumns()));
        }
    }

    private <T extends Entity> T cursorToEntity(Class<T> cls, Cursor cursor) {
        T t = (Entity) newInstance(cls);
        if (t != null) {
            t.initFromCursor(cursor);
        }
        return t;
    }

    public static String getAddColumnSql(String str, TableColumn tableColumn) {
        StringBuilder sb = new StringBuilder("ALTER TABLE ");
        sb.append(str);
        sb.append(" ADD ");
        sb.append(tableColumn.name);
        sb.append(" ");
        sb.append(tableColumn.type);
        if (tableColumn.isUnique) {
            sb.append(" UNIQUE");
        } else if (tableColumn.defaultValue != null) {
            sb.append(" DEFAULT '");
            sb.append(tableColumn.defaultValue);
            sb.append("'");
        }
        return sb.toString();
    }

    private static String getConstraintsSelection(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(strArr[i]);
            if (i == strArr.length - 1) {
                stringBuffer.append("=?");
            } else {
                stringBuffer.append("=? AND ");
            }
        }
        return stringBuffer.toString();
    }

    public static String getCreateTableSql(String str, List<TableColumn> list) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(str);
        sb.append("(_id INTEGER PRIMARY KEY AUTOINCREMENT");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(", ");
            TableColumn tableColumn = list.get(i);
            sb.append(tableColumn.name);
            sb.append(" ");
            sb.append(tableColumn.type);
            if (tableColumn.isUnique) {
                sb.append(" UNIQUE");
            } else if (tableColumn.defaultValue != null) {
                sb.append(" DEFAULT '");
                sb.append(tableColumn.defaultValue);
                sb.append("'");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String getDropTableSql(String str) {
        return "DROP TABLE IF EXISTS " + str;
    }

    private static String getTableName(Class<? extends Entity> cls) {
        Entity entity = (Entity) newInstance(cls);
        if (entity != null) {
            return entity.getTableName();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void initTableList() {
        if (this.mTables == null) {
            onInitTableList();
        }
    }

    private static <T> T newInstance(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            Log.e("EntityManager", "newInstance error.\n", (Object) e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void addTable(Class<? extends Entity> cls) {
        if (this.mTables == null) {
            this.mTables = new ArrayList<>();
        }
        this.mTables.add(cls);
    }

    public boolean delete(Entity entity) {
        int i;
        if (entity == null || entity.getId() < 0) {
            return false;
        }
        try {
            i = this.dbHelper.getWritableDatabase().delete(entity.getTableName(), "_id=?", new String[]{String.valueOf(entity.getId())});
        } catch (Exception e) {
            Log.e("EntityManager", "delete error.\n", (Object) e);
            i = 0;
        }
        return i > 0;
    }

    public boolean delete(Class<? extends Entity> cls, String str, String[] strArr) {
        int i;
        String tableName = getTableName(cls);
        if (tableName == null) {
            return false;
        }
        try {
            i = this.dbHelper.getWritableDatabase().delete(tableName, str, strArr);
        } catch (Exception e) {
            Log.e("EntityManager", "delete error.\n", (Object) e);
            i = 0;
        }
        return i > 0;
    }

    public boolean deleteAll(Class<? extends Entity> cls) {
        return delete(cls, (String) null, (String[]) null);
    }

    public <T extends Entity> T find(Class<T> cls, long j) {
        return find(cls, "_id=?", new String[]{String.valueOf(j)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r11 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r11 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0046  */
    public <T extends Entity> T find(Class<T> cls, String str, String[] strArr) {
        Cursor cursor;
        T t = (Entity) newInstance(cls);
        T t2 = null;
        if (t == null) {
            return null;
        }
        try {
            cursor = this.dbHelper.getReadableDatabase().query(t.getTableName(), (String[]) null, str, strArr, (String) null, (String) null, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        t.initFromCursor(cursor);
                        t2 = t;
                    }
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.e("EntityManager", "find error.\n", (Object) e);
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
            Log.e("EntityManager", "find error.\n", (Object) e);
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    public <T extends Entity> T find(Class<T> cls, String... strArr) {
        Entity entity = (Entity) newInstance(cls);
        if (entity == null) {
            return null;
        }
        String tableName = entity.getTableName();
        String[] uniqueConstraints = entity.getUniqueConstraints();
        String constraintsSelection = getConstraintsSelection(uniqueConstraints);
        if (!TextUtils.isEmpty(constraintsSelection) && strArr.length == uniqueConstraints.length) {
            return find(cls, constraintsSelection, strArr);
        }
        throw new RuntimeException(String.format("uniques should match %.getUniqueConstraints()", new Object[]{tableName}));
    }

    public EntityTransaction getTransaction() {
        return new EntityTransaction(this.dbHelper);
    }

    public int insert(Collection<? extends Entity> collection) {
        long j;
        if (!MiscUtil.isValid(collection)) {
            return 0;
        }
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            int i = 0;
            for (Entity entity : collection) {
                try {
                    j = writableDatabase.insert(entity.getTableName(), (String) null, entity.convertToContents());
                } catch (Exception e) {
                    Log.e("EntityManager", "insert error. %s", (Object) e);
                    j = -1;
                }
                if (j != -1) {
                    entity.setId(j);
                    i++;
                }
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            return i;
        } catch (Exception e2) {
            Log.e("EntityManager", "insert error %s", (Object) e2);
            writableDatabase.endTransaction();
            return 0;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    public boolean insert(Entity entity) {
        long j;
        if (entity == null) {
            return false;
        }
        try {
            j = this.dbHelper.getWritableDatabase().insert(entity.getTableName(), (String) null, entity.convertToContents());
        } catch (Exception e) {
            Log.e("EntityManager", "insert error. %s", (Object) e);
            j = -1;
        }
        int i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
        if (i != 0) {
            entity.setId(j);
        }
        return i != 0;
    }

    public boolean insertWithOnConflict(Entity entity, int i) {
        long j;
        if (entity == null) {
            return false;
        }
        try {
            j = this.dbHelper.getWritableDatabase().insertWithOnConflict(entity.getTableName(), (String) null, entity.convertToContents(), i);
        } catch (Exception e) {
            Log.e("EntityManager", "insert error %s", (Object) e);
            j = -1;
        }
        int i2 = (j > -1 ? 1 : (j == -1 ? 0 : -1));
        if (i2 != 0) {
            entity.setId(j);
        }
        return i2 != 0;
    }

    /* access modifiers changed from: protected */
    public void onDatabaseDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new SQLiteException("Can't downgrade database from version " + i + " to " + i2);
    }

    /* access modifiers changed from: protected */
    public abstract void onDatabaseUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void onInitTableList();

    public <T extends Entity> List<T> query(Class<T> cls, String str, String[] strArr) {
        return query(cls, (String[]) null, str, strArr, (String) null, (String) null, (String) null, (String) null);
    }

    public <T extends Entity> List<T> query(Class<T> cls, String str, String[] strArr, String str2, String str3) {
        return query(cls, (String[]) null, str, strArr, (String) null, (String) null, str2, str3);
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.util.List<T>, android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r11v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r11v2, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r11 == 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        if (r11 == 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        return r12;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    public <T extends Entity> List<T> query(Class<T> cls, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        String tableName = getTableName(cls);
        ? r11 = 0;
        if (tableName == null) {
            return r11;
        }
        ArrayList arrayList = new ArrayList();
        try {
            r11 = this.dbHelper.getReadableDatabase().query(tableName, strArr, str, strArr2, str2, str3, str4, str5);
            if (r11 != 0 && r11.moveToFirst()) {
                Class<T> cls2 = cls;
                do {
                    T cursorToEntity = cursorToEntity(cls, r11);
                    if (cursorToEntity != null) {
                        arrayList.add(cursorToEntity);
                    }
                } while (r11.moveToNext());
            }
        } catch (Exception e) {
            Log.e("EntityManager", "query error.\n", (Object) e);
        } catch (Throwable th) {
            if (r11 != 0) {
                r11.close();
            }
            throw th;
        }
    }

    public Cursor rawQuery(Class<? extends Entity> cls, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        String tableName = getTableName(cls);
        if (tableName == null) {
            return null;
        }
        try {
            return this.dbHelper.getReadableDatabase().query(tableName, strArr, str, strArr2, str2, str3, str4, str5);
        } catch (Exception e) {
            Log.e("EntityManager", "query error.\n", (Object) e);
            return null;
        }
    }

    public boolean update(Entity entity) {
        int i;
        if (entity == null || entity.getId() < 0) {
            return false;
        }
        try {
            i = this.dbHelper.getWritableDatabase().update(entity.getTableName(), entity.convertToContents(), "_id=?", new String[]{String.valueOf(entity.getId())});
        } catch (Exception e) {
            Log.e("EntityManager", "update error.\n", (Object) e);
            i = 0;
        }
        return i > 0;
    }

    public boolean update(Class<? extends Entity> cls, ContentValues contentValues, String str, String[] strArr) {
        int i;
        String tableName = getTableName(cls);
        if (tableName == null) {
            return false;
        }
        try {
            i = this.dbHelper.getWritableDatabase().update(tableName, contentValues, str, strArr);
        } catch (Exception e) {
            Log.e("EntityManager", "update error.\n", (Object) e);
            i = 0;
        }
        return i > 0;
    }
}
