package com.xiaomi.mistatistic.sdk.controller;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.text.TextUtils;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.a;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: EventDAO */
public class h {
    public static boolean a = false;
    private static String b = "";
    private static volatile k c;
    private boolean d = false;
    /* access modifiers changed from: private */
    public com.xiaomi.mistatistic.sdk.a e = null;
    /* access modifiers changed from: private */
    public boolean f = false;
    private ServiceConnection g = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = h.this.f = true;
            com.xiaomi.mistatistic.sdk.a unused2 = h.this.e = a.C0006a.a(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = h.this.f = false;
            com.xiaomi.mistatistic.sdk.a unused2 = h.this.e = null;
        }
    };

    /* compiled from: EventDAO */
    private static class a {
        /* access modifiers changed from: private */
        public static final k a = new k(d.a());
    }

    private static StatEventPojo a(Cursor cursor) {
        StatEventPojo statEventPojo = new StatEventPojo();
        long j = cursor.getLong(2);
        String string = cursor.getString(4);
        String string2 = cursor.getString(5);
        String string3 = cursor.getString(1);
        String string4 = cursor.getString(3);
        String string5 = cursor.getString(6);
        int i = cursor.getInt(7);
        statEventPojo.mCategory = string3;
        statEventPojo.mKey = string4;
        statEventPojo.mValue = string;
        statEventPojo.mTimeStamp = j;
        statEventPojo.mType = string2;
        statEventPojo.mExtra = string5;
        statEventPojo.mAnonymous = i;
        return statEventPojo;
    }

    public static void a() {
        c = g();
    }

    public static boolean b() {
        return a;
    }

    private void f() {
        if (!this.f) {
            try {
                Context a2 = d.a();
                Intent intent = new Intent(a2, Class.forName(b));
                a2.startService(intent);
                if (this.e != null) {
                    j.b("DAO", "unbind service before bind it again!");
                    a2.unbindService(this.g);
                }
                a2.bindService(intent, this.g, 1);
            } catch (Exception e2) {
                j.a("DAO", "ensureServiceBinded exception", (Throwable) e2);
            }
        }
    }

    private static k g() {
        if (c == null) {
            c = a.a;
        }
        return c;
    }

    public int a(int i) {
        if (!a) {
            return b(i);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        int a2 = this.e.a(i);
                        j.b("DAO", String.format("process getEventCount, result is: %d", new Object[]{Integer.valueOf(a2)}));
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return 0;
        } catch (Exception e2) {
            j.a("DAO", "getEventCount", (Throwable) e2);
            return 0;
        }
    }

    public StatEventPojo a(String str, String str2) {
        if (!a) {
            return b(str, str2);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        StatEventPojo a2 = this.e.a(str, str2);
                        j.b("DAO", "process query, result is: " + a2);
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return null;
        } catch (Exception e2) {
            j.a("DAO", "queryCustomEvent exception", (Throwable) e2);
            return null;
        }
    }

    public List<StatEventPojo> a(long j) {
        if (!a) {
            return b(j);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        List<StatEventPojo> a2 = this.e.a(j);
                        Object[] objArr = new Object[1];
                        objArr[0] = Integer.valueOf(a2 == null ? 0 : a2.size());
                        j.b("DAO", String.format("process getAll, result size is : %d", objArr));
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return new ArrayList();
        } catch (Exception e2) {
            j.a("DAO", "getAllEventOrderByTimestampDescend exception", (Throwable) e2);
            return new ArrayList();
        }
    }

    public void a(long j, long j2, int i) {
        if (a) {
            try {
                Context a2 = d.a();
                Intent intent = new Intent(a2, Class.forName(b));
                intent.putExtra(nexExportFormat.TAG_FORMAT_TYPE, 5);
                intent.putExtra("startTime", j);
                intent.putExtra("endTime", j2);
                intent.putExtra("eventType", i);
                a2.startService(intent);
            } catch (Exception e2) {
                j.a("DAO", "deleteEventsByStartAndEndTS", (Throwable) e2);
            }
        } else {
            b(j, j2, i);
        }
    }

    public void a(StatEventPojo statEventPojo) {
        if (a) {
            try {
                Intent intent = new Intent(d.a(), Class.forName(b));
                intent.putExtra(nexExportFormat.TAG_FORMAT_TYPE, 1);
                intent.putExtra("StatEventPojo", statEventPojo);
                d.a().startService(intent);
            } catch (Exception e2) {
                j.a("DAO", "insertNewEvent exception", (Throwable) e2);
            }
        } else {
            b(statEventPojo);
        }
    }

    public void a(Boolean bool) {
        this.d = bool.booleanValue();
    }

    public void a(String str, String str2, String str3) {
        if (a) {
            try {
                Intent intent = new Intent(d.a(), Class.forName(b));
                intent.putExtra(nexExportFormat.TAG_FORMAT_TYPE, 2);
                intent.putExtra("key", str);
                intent.putExtra("category", str2);
                intent.putExtra("newValue", str3);
                d.a().startService(intent);
            } catch (Exception e2) {
                j.a("DAO", "updateEventByKeyAndCategory exception", (Throwable) e2);
            }
        } else {
            b(str, str2, str3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0055 A[Catch:{ Exception -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0069 A[Catch:{ all -> 0x0078 }] */
    public int b(int i) {
        k kVar;
        Cursor query;
        int i2 = i;
        synchronized (g()) {
            Cursor cursor = null;
            try {
                SQLiteDatabase readableDatabase = c.getReadableDatabase();
                if (i2 == 1) {
                    query = readableDatabase.query("mistat_event", new String[]{"count(*)"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                } else {
                    if (i2 == 2) {
                        query = readableDatabase.query("mistat_event", new String[]{"count(*)"}, "category IN (?, ?, ?, ?, ?, ?, ?)", new String[]{"mistat_basic", "mistat_pt", "mistat_pv", "mistat_session", "mistat_pa", "mistat_session_extra", "mistat_monitor"}, (String) null, (String) null, (String) null, (String) null);
                    }
                    if (cursor == null || !cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (c != null) {
                            kVar = c;
                            kVar.close();
                        }
                    } else {
                        int i3 = cursor.getInt(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (c != null) {
                            c.close();
                        }
                    }
                }
                cursor = query;
                if (cursor == null || !cursor.moveToFirst()) {
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error while getting count from DB", (Throwable) e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        kVar = c;
                    }
                    return 0;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.xiaomi.mistatistic.sdk.data.StatEventPojo} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c A[SYNTHETIC, Splitter:B:25:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A[Catch:{ all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005c A[Catch:{ all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0063 A[Catch:{ all -> 0x0058 }] */
    public StatEventPojo b(String str, String str2) {
        ? r1;
        Cursor cursor;
        k kVar;
        synchronized (g()) {
            r1 = 0;
            try {
                cursor = c.getReadableDatabase().query("mistat_event", (String[]) null, "category=? AND key=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            r1 = a(cursor);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            j.a("DAO", "queryCustomEvent exception", (Throwable) e);
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (c != null) {
                                kVar = c;
                                kVar.close();
                                r1 = r1;
                            }
                            return r1;
                        } catch (Throwable th) {
                            th = th;
                            r1 = cursor;
                            if (r1 != 0) {
                                r1.close();
                            }
                            if (c != null) {
                                c.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                r1 = r1;
                if (c != null) {
                    kVar = c;
                    r1 = r1;
                    kVar.close();
                    r1 = r1;
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
                j.a("DAO", "queryCustomEvent exception", (Throwable) e);
                if (cursor != null) {
                }
                if (c != null) {
                }
                return r1;
            } catch (Throwable th2) {
                th = th2;
                if (r1 != 0) {
                }
                if (c != null) {
                }
                throw th;
            }
        }
        return r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ca, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d1, code lost:
        c.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:36:0x00a0, B:47:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a0 A[SYNTHETIC, Splitter:B:36:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a7 A[Catch:{ all -> 0x00c7, all -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bb A[SYNTHETIC, Splitter:B:50:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c2 A[Catch:{ all -> 0x00c7, all -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ca A[Catch:{ all -> 0x00c7, all -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d1 A[Catch:{ all -> 0x00c7, all -> 0x00d7 }] */
    public List<StatEventPojo> b(long j) {
        k kVar;
        ArrayList arrayList = new ArrayList();
        synchronized (g()) {
            Cursor cursor = null;
            try {
                SQLiteDatabase readableDatabase = c.getReadableDatabase();
                if (readableDatabase == null) {
                    try {
                        if (c != null) {
                            c.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    cursor = readableDatabase.query("mistat_event", (String[]) null, "ts < ? ", new String[]{String.valueOf(j)}, (String) null, (String) null, "ts DESC", String.valueOf(500));
                    if (cursor != null && cursor.moveToLast()) {
                        long j2 = cursor.getLong(cursor.getColumnIndex("ts"));
                        cursor.close();
                        String[] strArr = new String[3];
                        strArr[0] = String.valueOf(j);
                        strArr[1] = String.valueOf(j2);
                        try {
                            strArr[2] = this.d ? String.valueOf(1) : String.valueOf(0);
                            cursor = readableDatabase.query("mistat_event", (String[]) null, "ts<? AND ts>=? AND anonymous=?", strArr, (String) null, (String) null, "ts DESC");
                        } catch (Exception e2) {
                            e = e2;
                            j.a("DAO", "Error while reading data from DB", (Throwable) e);
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (c != null) {
                                kVar = c;
                                kVar.close();
                            }
                            return arrayList;
                        }
                    }
                    if (cursor == null || !cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (c != null) {
                            kVar = c;
                            kVar.close();
                        }
                    } else {
                        do {
                            arrayList.add(a(cursor));
                        } while (cursor.moveToNext());
                        if (cursor != null) {
                        }
                        if (c != null) {
                        }
                    }
                }
            } catch (Exception e3) {
                e = e3;
                j.a("DAO", "Error while reading data from DB", (Throwable) e);
                if (cursor != null) {
                }
                if (c != null) {
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                }
                if (c != null) {
                }
                throw th;
            }
        }
    }

    public void b(long j, long j2, int i) {
        k kVar;
        synchronized (g()) {
            try {
                j.a("DAO", "deleteEventsByStartAndEndTS, start:%d, end:%d", Long.valueOf(j), Long.valueOf(j2));
                SQLiteDatabase writableDatabase = c.getWritableDatabase();
                if (i == 1) {
                    String[] strArr = new String[3];
                    strArr[0] = String.valueOf(j2);
                    strArr[1] = String.valueOf(j);
                    strArr[2] = this.d ? String.valueOf(1) : String.valueOf(0);
                    writableDatabase.delete("mistat_event", "ts<=? AND ts>=? AND anonymous=?", strArr);
                } else if (i == 2) {
                    String[] strArr2 = new String[10];
                    strArr2[0] = String.valueOf(j2);
                    strArr2[1] = String.valueOf(j);
                    strArr2[2] = "mistat_basic";
                    strArr2[3] = "mistat_pt";
                    strArr2[4] = "mistat_pv";
                    strArr2[5] = "mistat_session";
                    strArr2[6] = "mistat_pa";
                    strArr2[7] = "mistat_session_extra";
                    strArr2[8] = "mistat_monitor";
                    strArr2[9] = this.d ? String.valueOf(1) : String.valueOf(0);
                    writableDatabase.delete("mistat_event", "ts<=? AND ts>=? AND category IN (?, ?, ?, ?, ?, ?, ?) AND anonymous=?", strArr2);
                }
                if (c != null) {
                    kVar = c;
                    kVar.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error while deleting event by ts from DB", (Throwable) e2);
                    if (c != null) {
                        kVar = c;
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    public void b(StatEventPojo statEventPojo) {
        k kVar;
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", statEventPojo.mCategory);
        contentValues.put("key", TextUtils.isEmpty(statEventPojo.mKey) ? "" : statEventPojo.mKey);
        contentValues.put("ts", Long.valueOf(statEventPojo.mTimeStamp));
        contentValues.put(nexExportFormat.TAG_FORMAT_TYPE, TextUtils.isEmpty(statEventPojo.mType) ? "" : statEventPojo.mType);
        contentValues.put("value", TextUtils.isEmpty(statEventPojo.mValue) ? "" : statEventPojo.mValue);
        contentValues.put("extra", TextUtils.isEmpty(statEventPojo.mExtra) ? "" : statEventPojo.mExtra);
        contentValues.put("anonymous", Integer.valueOf(statEventPojo.mAnonymous));
        synchronized (g()) {
            try {
                c.getWritableDatabase().insert("mistat_event", "", contentValues);
                if (c != null) {
                    kVar = c;
                    kVar.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", String.format("Error to insert data into DB, key= %s", new Object[]{statEventPojo.mKey}), (Throwable) e2);
                    if (c != null) {
                        kVar = c;
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    public void b(String str, String str2, String str3) {
        k kVar;
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", str3);
        synchronized (g()) {
            try {
                c.getWritableDatabase().update("mistat_event", contentValues, "category=? AND key=?", new String[]{str2, str});
                if (c != null) {
                    kVar = c;
                    kVar.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error to update data from DB, key=" + str, (Throwable) e2);
                    if (c != null) {
                        kVar = c;
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    public List<StatEventPojo> c(long j) {
        if (!a) {
            return d(j);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        List<StatEventPojo> c2 = this.e.c(j);
                        Object[] objArr = new Object[1];
                        objArr[0] = Integer.valueOf(c2 == null ? 0 : c2.size());
                        j.b("DAO", String.format("process getBasicEventsOrderByTsDescend, result size is : %d", objArr));
                        return c2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return new ArrayList();
        } catch (Exception e2) {
            j.a("DAO", "getBasicEventsOrderByTsDescend exception", (Throwable) e2);
            return new ArrayList();
        }
    }

    public boolean c() {
        if (!a) {
            return d();
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        boolean a2 = this.e.a();
                        j.b("DAO", String.format("process hasMonitorEvent , result is: %b", new Object[]{Boolean.valueOf(a2)}));
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return false;
        } catch (Exception e2) {
            j.a("DAO", "hasMonitorEvent", (Throwable) e2);
            return false;
        }
    }

    public List<StatEventPojo> d(long j) {
        k kVar;
        ArrayList arrayList = new ArrayList();
        synchronized (g()) {
            Cursor cursor = null;
            try {
                SQLiteDatabase readableDatabase = c.getReadableDatabase();
                if (readableDatabase == null) {
                    cursor.close();
                    c.close();
                    return arrayList;
                }
                String[] strArr = new String[8];
                strArr[0] = "mistat_basic";
                strArr[1] = "mistat_pt";
                strArr[2] = "mistat_pv";
                strArr[3] = "mistat_session";
                strArr[4] = "mistat_pa";
                strArr[5] = "mistat_session_extra";
                strArr[6] = "mistat_monitor";
                strArr[7] = this.d ? String.valueOf(1) : String.valueOf(0);
                Cursor query = readableDatabase.query("mistat_event", (String[]) null, "category IN (?, ?, ?, ?, ?, ?, ?) AND anonymous=?", strArr, (String) null, (String) null, "ts DESC", String.valueOf(500));
                if (query == null || !query.moveToFirst()) {
                    query.close();
                    kVar = c;
                    kVar.close();
                    return arrayList;
                }
                do {
                    arrayList.add(a(query));
                } while (query.moveToNext());
                query.close();
                kVar = c;
                kVar.close();
                return arrayList;
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error while getBasicEventsOrderByTsDescendImpl", (Throwable) e2);
                    cursor.close();
                    kVar = c;
                } catch (Throwable th) {
                    cursor.close();
                    c.close();
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        return true;
     */
    public boolean d() {
        k kVar;
        synchronized (g()) {
            Cursor cursor = null;
            try {
                Cursor query = c.getReadableDatabase().query("mistat_event", (String[]) null, "category=?", new String[]{"mistat_monitor"}, (String) null, (String) null, (String) null);
                if (query == null || !query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    if (c != null) {
                        kVar = c;
                        kVar.close();
                    }
                } else {
                    if (query != null) {
                        query.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "hasMonitorEventImpl exception", (Throwable) e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        kVar = c;
                    }
                    return false;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0065 A[SYNTHETIC, Splitter:B:21:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c A[Catch:{ all -> 0x0072 }] */
    public List<StatEventPojo> e(long j) {
        k kVar;
        ArrayList arrayList = new ArrayList();
        synchronized (g()) {
            Cursor cursor = null;
            try {
                SQLiteDatabase readableDatabase = c.getReadableDatabase();
                if (readableDatabase != null) {
                    Cursor query = readableDatabase.query("mistat_event", (String[]) null, "ts <= ? AND category = ? OR category = ? OR category = ? OR category = ? OR category = ? ", new String[]{String.valueOf(j), "mistat_basic", "mistat_pa", "mistat_session", "mistat_pv", "mistat_pt"}, (String) null, (String) null, (String) null, (String) null);
                    if (query == null || !query.moveToFirst()) {
                        if (query != null) {
                            query.close();
                        }
                        if (c != null) {
                            kVar = c;
                            kVar.close();
                        }
                    } else {
                        do {
                            arrayList.add(a(query));
                        } while (query.moveToNext());
                        if (query != null) {
                        }
                        if (c != null) {
                        }
                    }
                } else if (c != null) {
                    c.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "getExpiredEvents", (Throwable) e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        kVar = c;
                    }
                    return arrayList;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    public boolean e() {
        boolean z;
        k kVar;
        synchronized (g()) {
            Cursor cursor = null;
            z = false;
            try {
                Cursor query = c.getReadableDatabase().query("mistat_event", (String[]) null, "anonymous=?", new String[]{String.valueOf(1)}, (String) null, (String) null, "ts DESC", String.valueOf(500));
                if (query != null && query.moveToLast()) {
                    z = true;
                }
                if (query != null) {
                    query.close();
                }
                if (c != null) {
                    kVar = c;
                    kVar.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error while isExistAnonymousData from DB", (Throwable) e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        kVar = c;
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
        return z;
    }

    public void f(long j) {
        if (a) {
            try {
                Intent intent = new Intent(d.a(), Class.forName(b));
                intent.putExtra(nexExportFormat.TAG_FORMAT_TYPE, 3);
                intent.putExtra("timeStamp", j);
                d.a().startService(intent);
            } catch (Exception e2) {
                j.a("DAO", "deleteExpiredEvents", (Throwable) e2);
            }
        } else {
            g(j);
        }
    }

    public void g(long j) {
        k kVar;
        synchronized (g()) {
            try {
                int delete = c.getWritableDatabase().delete("mistat_event", "ts <= ? AND category <> ? ", new String[]{String.valueOf(j), "mistat_monitor"});
                if (delete > 0) {
                    MiStatInterface.recordCalculateEvent("quality_monitor", "delete_old_events", (long) delete);
                }
                if (c != null) {
                    kVar = c;
                    kVar.close();
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "Error while deleting out-of-date data from DB", (Throwable) e2);
                    if (c != null) {
                        kVar = c;
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }

    public boolean j(long j) {
        if (!a) {
            return k(j);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.f) {
                    if (this.e != null) {
                        boolean b2 = this.e.b(j);
                        j.b("DAO", String.format("process queryPaEventByTs , result is: %b", new Object[]{Boolean.valueOf(b2)}));
                        return b2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return false;
        } catch (Exception e2) {
            j.a("DAO", "queryPaEventByTs", (Throwable) e2);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        return true;
     */
    public boolean k(long j) {
        k kVar;
        synchronized (g()) {
            Cursor cursor = null;
            try {
                Cursor query = c.getReadableDatabase().query("mistat_event", (String[]) null, "ts=? AND category=?", new String[]{String.valueOf(j), "mistat_pa"}, (String) null, (String) null, (String) null);
                if (query == null || !query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    if (c != null) {
                        kVar = c;
                        kVar.close();
                    }
                } else {
                    if (query != null) {
                        query.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                }
            } catch (Exception e2) {
                try {
                    j.a("DAO", "queryPaEventByTsImpl exception", (Throwable) e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        kVar = c;
                    }
                    return false;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (c != null) {
                        c.close();
                    }
                    throw th;
                }
            }
        }
    }
}
