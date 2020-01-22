package com.miui.gallery.util;

import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SafeDBUtil {

    public interface QueryHandler<T> {
        T handle(Cursor cursor);
    }

    private static Cursor queryToCursor(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        final ContentProviderClient contentProviderClient;
        try {
            contentProviderClient = context.getContentResolver().acquireContentProviderClient(uri.getAuthority());
            try {
                ContentProvider localContentProvider = contentProviderClient.getLocalContentProvider();
                if (localContentProvider != null) {
                    Cursor query = localContentProvider.query(uri, strArr, str, strArr2, str2);
                    if (query == null) {
                        releaseSilently(contentProviderClient);
                        releaseSilently(contentProviderClient);
                        return null;
                    }
                    AnonymousClass1 r9 = new CursorWrapper(query) {
                        public void close() {
                            super.close();
                            SafeDBUtil.releaseSilently(contentProviderClient);
                        }
                    };
                    releaseSilently(contentProviderClient);
                    return r9;
                }
                releaseSilently(contentProviderClient);
                Cursor query2 = context.getContentResolver().query(uri, strArr, str, strArr2, str2);
                releaseSilently(contentProviderClient);
                return query2;
            } catch (Exception e) {
                e = e;
                try {
                    Log.w("SafeDBUtil", (Throwable) e);
                    releaseSilently(contentProviderClient);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    releaseSilently(contentProviderClient);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            contentProviderClient = null;
            Log.w("SafeDBUtil", (Throwable) e);
            releaseSilently(contentProviderClient);
            return null;
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = null;
            releaseSilently(contentProviderClient);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static void releaseSilently(ContentProviderClient contentProviderClient) {
        if (contentProviderClient != null) {
            try {
                contentProviderClient.release();
            } catch (Exception unused) {
            }
        }
    }

    public static int safeDelete(Context context, Uri uri, String str, String[] strArr) {
        try {
            return context.getContentResolver().delete(uri, str, strArr);
        } catch (Exception e) {
            Log.w("SafeDBUtil", (Throwable) e);
            return -1;
        }
    }

    public static long safeInsert(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insert(str, (String) null, contentValues);
        } catch (Exception e) {
            Log.w("SafeDBUtil", (Throwable) e);
            return -1;
        }
    }

    public static Uri safeInsert(Context context, Uri uri, ContentValues contentValues) {
        try {
            return context.getContentResolver().insert(uri, contentValues);
        } catch (Exception e) {
            Log.w("SafeDBUtil", (Throwable) e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    public static <T> T safeQuery(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2, QueryHandler<T> queryHandler) {
        Cursor cursor;
        try {
            cursor = queryToCursor(context, uri, strArr, str, strArr2, str2);
            if (queryHandler != null) {
                try {
                    T handle = queryHandler.handle(cursor);
                    BaseMiscUtil.closeSilently(cursor);
                    return handle;
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.w("SafeDBUtil", (Throwable) e);
                        BaseMiscUtil.closeSilently(cursor);
                        if (queryHandler != null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
            Log.w("SafeDBUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(cursor);
            if (queryHandler != null) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            BaseMiscUtil.closeSilently(cursor);
            throw th;
        }
        BaseMiscUtil.closeSilently(cursor);
        if (queryHandler != null) {
            return queryHandler.handle((Cursor) null);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    public static <T> T safeQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, QueryHandler<T> queryHandler) {
        Cursor cursor;
        QueryHandler<T> queryHandler2 = queryHandler;
        try {
            cursor = sQLiteDatabase.query(str, strArr, str2, strArr2, (String) null, (String) null, str3, (String) null);
            if (queryHandler2 != null) {
                try {
                    T handle = queryHandler2.handle(cursor);
                    BaseMiscUtil.closeSilently(cursor);
                    return handle;
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.w("SafeDBUtil", (Throwable) e);
                        BaseMiscUtil.closeSilently(cursor);
                        if (queryHandler2 != null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
            Log.w("SafeDBUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(cursor);
            if (queryHandler2 != null) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            BaseMiscUtil.closeSilently(cursor);
            throw th;
        }
        BaseMiscUtil.closeSilently(cursor);
        if (queryHandler2 != null) {
            return queryHandler2.handle((Cursor) null);
        }
        return null;
    }

    public static int safeUpdate(Context context, Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            return context.getContentResolver().update(uri, contentValues, str, strArr);
        } catch (Exception e) {
            Log.w("SafeDBUtil", (Throwable) e);
            return -1;
        }
    }

    public static int safeUpdate(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            return sQLiteDatabase.update(str, contentValues, str2, strArr);
        } catch (Exception e) {
            Log.w("SafeDBUtil", (Throwable) e);
            return -1;
        }
    }
}
