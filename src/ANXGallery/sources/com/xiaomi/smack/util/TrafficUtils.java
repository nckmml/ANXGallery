package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.push.providers.TrafficDatabaseHelper;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.keyczar.Keyczar;

public class TrafficUtils {
    private static TrafficDatabaseHelper dbHelper = null;
    private static String imsi = "";
    private static long lastRxTs = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    private static SerializedAsyncTaskProcessor mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
    private static volatile int networkType = -1;
    /* access modifiers changed from: private */
    public static List<TrafficInfo> trafficList = Collections.synchronizedList(new ArrayList());

    static class TrafficInfo {
        public long bytes = 0;
        public String imsi = "";
        public long messageTs = 0;
        public int networkType = -1;
        public String packageName = "";
        public int rcv = -1;

        public TrafficInfo(String str, long j, int i, int i2, String str2, long j2) {
            this.packageName = str;
            this.messageTs = j;
            this.networkType = i;
            this.rcv = i2;
            this.imsi = str2;
            this.bytes = j2;
        }

        public boolean canAccumulate(TrafficInfo trafficInfo) {
            return TextUtils.equals(trafficInfo.packageName, this.packageName) && TextUtils.equals(trafficInfo.imsi, this.imsi) && trafficInfo.networkType == this.networkType && trafficInfo.rcv == this.rcv && Math.abs(trafficInfo.messageTs - this.messageTs) <= 5000;
        }
    }

    public static void distributionTraffic(Context context, String str, long j, boolean z, boolean z2, long j2) {
        saveTraffic(context, str, getTraffic(getNetworkType(context), j, z, j2, z2), z, j2);
    }

    private static int getActiveNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception unused) {
            return -1;
        }
    }

    private static synchronized String getIMSI(Context context) {
        synchronized (TrafficUtils.class) {
            if (TextUtils.isEmpty(imsi)) {
                return "";
            }
            String str = imsi;
            return str;
        }
    }

    public static int getNetworkType(Context context) {
        if (networkType == -1) {
            networkType = getActiveNetworkType(context);
        }
        return networkType;
    }

    private static long getTraffic(int i, long j, boolean z, long j2, boolean z2) {
        if (z && z2) {
            long j3 = lastRxTs;
            lastRxTs = j2;
            if (j2 - j3 > 30000 && j > 1024) {
                return j * 2;
            }
        }
        return (j * ((long) (i == 0 ? 13 : 11))) / 10;
    }

    private static TrafficDatabaseHelper getTrafficDatabaseHelper(Context context) {
        TrafficDatabaseHelper trafficDatabaseHelper = dbHelper;
        if (trafficDatabaseHelper != null) {
            return trafficDatabaseHelper;
        }
        dbHelper = new TrafficDatabaseHelper(context);
        return dbHelper;
    }

    public static int getTrafficFlow(String str) {
        try {
            return str.getBytes(Keyczar.DEFAULT_ENCODING).length;
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes().length;
        }
    }

    /* access modifiers changed from: private */
    public static void insertTraffic(Context context, List<TrafficInfo> list) {
        try {
            synchronized (TrafficDatabaseHelper.DataBaseLock) {
                SQLiteDatabase writableDatabase = getTrafficDatabaseHelper(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (TrafficInfo next : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("package_name", next.packageName);
                        contentValues.put("message_ts", Long.valueOf(next.messageTs));
                        contentValues.put("network_type", Integer.valueOf(next.networkType));
                        contentValues.put("bytes", Long.valueOf(next.bytes));
                        contentValues.put("rcv", Integer.valueOf(next.rcv));
                        contentValues.put("imsi", next.imsi);
                        writableDatabase.insert("traffic", (String) null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e) {
            MyLog.e((Throwable) e);
        }
    }

    private static void insertTrafficInfo2List(TrafficInfo trafficInfo) {
        for (TrafficInfo next : trafficList) {
            if (next.canAccumulate(trafficInfo)) {
                next.bytes += trafficInfo.bytes;
                return;
            }
        }
        trafficList.add(trafficInfo);
    }

    public static void notifyNetworkChanage(Context context) {
        networkType = getActiveNetworkType(context);
    }

    private static void saveTraffic(final Context context, String str, long j, boolean z, long j2) {
        int networkType2;
        boolean isEmpty;
        if (context != null && !TextUtils.isEmpty(str) && "com.xiaomi.xmsf".equals(context.getPackageName())) {
            String str2 = str;
            if (!"com.xiaomi.xmsf".equals(str) && -1 != (networkType2 = getNetworkType(context))) {
                synchronized (lock) {
                    isEmpty = trafficList.isEmpty();
                    insertTrafficInfo2List(new TrafficInfo(str, j2, networkType2, z ? 1 : 0, networkType2 == 0 ? getIMSI(context) : "", j));
                }
                if (isEmpty) {
                    mAsyncProcessor.addNewTaskWithDelayed(new SerializedAsyncTaskProcessor.SerializedAsyncTask() {
                        public void process() {
                            ArrayList arrayList;
                            synchronized (TrafficUtils.lock) {
                                arrayList = new ArrayList(TrafficUtils.trafficList);
                                TrafficUtils.trafficList.clear();
                            }
                            TrafficUtils.insertTraffic(context, arrayList);
                        }
                    }, 5000);
                }
            }
        }
    }
}
