package com.xiaomi.clientreport.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {
    private static volatile SPManager sInstance;
    private Context mContext;

    private SPManager(Context context) {
        this.mContext = context;
    }

    public static SPManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SPManager.class) {
                if (sInstance == null) {
                    sInstance = new SPManager(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public synchronized long getLongValue(String str, String str2, long j) {
        return this.mContext.getSharedPreferences(str, 4).getLong(str2, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public synchronized String getStringValue(String str, String str2, String str3) {
        return this.mContext.getSharedPreferences(str, 4).getString(str2, str3);
    }

    public synchronized void setLongValue(String str, String str2, long j) {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(str, 4).edit();
        edit.putLong(str2, j);
        edit.commit();
    }

    public synchronized void setStringnValue(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(str, 4).edit();
        edit.putString(str2, str3);
        edit.commit();
    }
}
