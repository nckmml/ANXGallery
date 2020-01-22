package com.xiaomi.mipush.sdk.stat.db.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.FileLockerWorker;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.push.service.OnlineConfig;
import com.xiaomi.xmpush.thrift.ConfigKey;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DbManager {
    private static volatile DbManager sDbManager;
    private BaseDbHelperFactory mBaseDbHelperFactory;
    private Context mContext;
    private final HashMap<String, BaseDbHelper> mDbHelperMap = new HashMap<>();
    /* access modifiers changed from: private */
    public final ArrayList<BaseJob> mPendingList = new ArrayList<>();
    private ThreadPoolExecutor mPool = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static abstract class BaseJob implements Runnable {
        private String mDataPath;
        protected BaseDbHelper mDbHelper = null;
        private BaseJob mNextJob;
        private Random mRandom = new Random();
        private int mRetryCount = 0;
        protected String mTableName;
        private WeakReference<Context> mWRContext;

        public BaseJob(String str) {
            this.mDataPath = str;
        }

        public void append(BaseJob baseJob) {
            this.mNextJob = baseJob;
        }

        /* access modifiers changed from: package-private */
        public void attachInfo(BaseDbHelper baseDbHelper, Context context) {
            this.mDbHelper = baseDbHelper;
            this.mTableName = this.mDbHelper.getTableName();
            this.mWRContext = new WeakReference<>(context);
        }

        public abstract void doRun(Context context, SQLiteDatabase sQLiteDatabase) throws Exception;

        /* access modifiers changed from: package-private */
        public void finish(Context context) {
            BaseJob baseJob = this.mNextJob;
            if (baseJob != null) {
                baseJob.input(context, output());
            }
            onFinish(context);
        }

        public String getDataPath() {
            return this.mDataPath;
        }

        public SQLiteDatabase getDatabase() {
            return this.mDbHelper.getWritableDatabase();
        }

        public void input(Context context, Object obj) {
            DbManager.getInstance(context).exec(this);
        }

        public boolean needAttachInfo() {
            return this.mDbHelper == null || TextUtils.isEmpty(this.mTableName) || this.mWRContext == null;
        }

        public void onFinish(Context context) {
        }

        public Object output() {
            return null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
            r0 = (android.content.Context) r0.get();
         */
        public final void run() {
            final Context context;
            WeakReference<Context> weakReference = this.mWRContext;
            if (weakReference != null && context != null && context.getFilesDir() != null && this.mDbHelper != null && !TextUtils.isEmpty(this.mDataPath)) {
                File file = new File(this.mDataPath);
                FileLockerWorker.runMutiProcessJob(context, new File(file.getParentFile(), MD5.MD5_16(file.getAbsolutePath())), new Runnable() {
                    public void run() {
                        SQLiteDatabase sQLiteDatabase = null;
                        try {
                            SQLiteDatabase database = BaseJob.this.getDatabase();
                            if (database != null && database.isOpen()) {
                                database.beginTransaction();
                                BaseJob.this.doRun(context, database);
                                database.setTransactionSuccessful();
                            }
                            if (database != null) {
                                try {
                                    database.endTransaction();
                                } catch (Exception e) {
                                    e = e;
                                    MyLog.e((Throwable) e);
                                    BaseJob.this.finish(context);
                                }
                            }
                            if (BaseJob.this.mDbHelper != null) {
                                BaseJob.this.mDbHelper.close();
                            }
                        } catch (Exception e2) {
                            MyLog.e((Throwable) e2);
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Exception e3) {
                                    e = e3;
                                    MyLog.e((Throwable) e);
                                    BaseJob.this.finish(context);
                                }
                            }
                            if (BaseJob.this.mDbHelper != null) {
                                BaseJob.this.mDbHelper.close();
                            }
                        } catch (Throwable th) {
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Exception e4) {
                                    MyLog.e((Throwable) e4);
                                    BaseJob.this.finish(context);
                                    throw th;
                                }
                            }
                            if (BaseJob.this.mDbHelper != null) {
                                BaseJob.this.mDbHelper.close();
                            }
                            BaseJob.this.finish(context);
                            throw th;
                        }
                        BaseJob.this.finish(context);
                    }
                });
            }
        }
    }

    public static abstract class BaseQueryJob<T> extends BaseJob {
        private List<String> mBackRows;
        private String mGroupBy;
        private String mHaving;
        private int mLimit;
        private String mOrderBy;
        private List<T> mResults = new ArrayList();
        private String mWhereClause;
        private String[] mWhereValues;

        public BaseQueryJob(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i) {
            super(str);
            this.mBackRows = list;
            this.mWhereClause = str2;
            this.mWhereValues = strArr;
            this.mGroupBy = str3;
            this.mHaving = str4;
            this.mOrderBy = str5;
            this.mLimit = i;
        }

        public void doRun(Context context, SQLiteDatabase sQLiteDatabase) throws Exception {
            String[] strArr;
            this.mResults.clear();
            List<String> list = this.mBackRows;
            String str = null;
            if (list == null || list.size() <= 0) {
                strArr = null;
            } else {
                String[] strArr2 = new String[this.mBackRows.size()];
                this.mBackRows.toArray(strArr2);
                strArr = strArr2;
            }
            int i = this.mLimit;
            if (i > 0) {
                str = String.valueOf(i);
            }
            Cursor query = sQLiteDatabase.query(this.mTableName, strArr, this.mWhereClause, this.mWhereValues, this.mGroupBy, this.mHaving, this.mOrderBy, str);
            if (query != null && query.moveToFirst()) {
                do {
                    Object processOneData = processOneData(context, query);
                    if (processOneData != null) {
                        this.mResults.add(processOneData);
                    }
                } while (query.moveToNext());
                query.close();
            }
            notifyResult(context, this.mResults);
        }

        public SQLiteDatabase getDatabase() {
            return this.mDbHelper.getReadableDatabase();
        }

        public abstract void notifyResult(Context context, List<T> list);

        public abstract T processOneData(Context context, Cursor cursor);
    }

    public static class BatchJob extends BaseJob {
        private ArrayList<BaseJob> mJobs = new ArrayList<>();

        public BatchJob(String str, ArrayList<BaseJob> arrayList) {
            super(str);
            this.mJobs.addAll(arrayList);
        }

        public void doRun(Context context, SQLiteDatabase sQLiteDatabase) throws Exception {
            Iterator<BaseJob> it = this.mJobs.iterator();
            while (it.hasNext()) {
                BaseJob next = it.next();
                if (next != null) {
                    next.doRun(context, sQLiteDatabase);
                }
            }
        }

        public final void finish(Context context) {
            super.finish(context);
            Iterator<BaseJob> it = this.mJobs.iterator();
            while (it.hasNext()) {
                BaseJob next = it.next();
                if (next != null) {
                    next.finish(context);
                }
            }
        }
    }

    public static class DeleteJob extends BaseJob {
        private String mWhereClause;
        protected String[] mWhereValues;

        public DeleteJob(String str, String str2, String[] strArr) {
            super(str);
            this.mWhereClause = str2;
            this.mWhereValues = strArr;
        }

        public void doRun(Context context, SQLiteDatabase sQLiteDatabase) throws Exception {
            sQLiteDatabase.delete(this.mTableName, this.mWhereClause, this.mWhereValues);
        }
    }

    public static class InsertJob extends BaseJob {
        private ContentValues mContentValues;

        public InsertJob(String str, ContentValues contentValues) {
            super(str);
            this.mContentValues = contentValues;
        }

        public void doRun(Context context, SQLiteDatabase sQLiteDatabase) throws Exception {
            sQLiteDatabase.insert(this.mTableName, (String) null, this.mContentValues);
        }
    }

    private DbManager(Context context) {
        this.mContext = context;
    }

    private BaseDbHelper getDbHelper(String str) {
        BaseDbHelper baseDbHelper = this.mDbHelperMap.get(str);
        if (baseDbHelper == null) {
            synchronized (this.mDbHelperMap) {
                if (baseDbHelper == null) {
                    baseDbHelper = this.mBaseDbHelperFactory.getDbHelper(this.mContext, str);
                    this.mDbHelperMap.put(str, baseDbHelper);
                }
            }
        }
        return baseDbHelper;
    }

    public static DbManager getInstance(Context context) {
        if (sDbManager == null) {
            synchronized (DbManager.class) {
                if (sDbManager == null) {
                    sDbManager = new DbManager(context);
                }
            }
        }
        return sDbManager;
    }

    private void sendExecCmd() {
        ScheduledJobManager.getInstance(this.mContext).addOneShootJob((ScheduledJobManager.Job) new ScheduledJobManager.Job() {
            public int getJobId() {
                return 100957;
            }

            public void run() {
                synchronized (DbManager.this.mPendingList) {
                    if (DbManager.this.mPendingList.size() > 0) {
                        if (DbManager.this.mPendingList.size() > 1) {
                            DbManager.this.exec((ArrayList<BaseJob>) DbManager.this.mPendingList);
                        } else {
                            DbManager.this.execNow((BaseJob) DbManager.this.mPendingList.get(0));
                        }
                        DbManager.this.mPendingList.clear();
                        System.gc();
                    }
                }
            }
        }, OnlineConfig.getInstance(this.mContext).getIntValue(ConfigKey.StatDataProcessFrequency.getValue(), 5));
    }

    public void exec(BaseJob baseJob) {
        BaseDbHelper baseDbHelper;
        if (baseJob != null) {
            if (this.mBaseDbHelperFactory != null) {
                String dataPath = baseJob.getDataPath();
                synchronized (this.mDbHelperMap) {
                    baseDbHelper = this.mDbHelperMap.get(dataPath);
                    if (baseDbHelper == null) {
                        baseDbHelper = this.mBaseDbHelperFactory.getDbHelper(this.mContext, dataPath);
                        this.mDbHelperMap.put(dataPath, baseDbHelper);
                    }
                }
                if (!this.mPool.isShutdown()) {
                    baseJob.attachInfo(baseDbHelper, this.mContext);
                    synchronized (this.mPendingList) {
                        this.mPendingList.add(baseJob);
                        sendExecCmd();
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("should exec init method first!");
        }
    }

    public void exec(ArrayList<BaseJob> arrayList) {
        if (this.mBaseDbHelperFactory != null) {
            HashMap hashMap = new HashMap();
            if (!this.mPool.isShutdown()) {
                Iterator<BaseJob> it = arrayList.iterator();
                while (it.hasNext()) {
                    BaseJob next = it.next();
                    if (next.needAttachInfo()) {
                        next.attachInfo(getDbHelper(next.getDataPath()), this.mContext);
                    }
                    ArrayList arrayList2 = (ArrayList) hashMap.get(next.getDataPath());
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        hashMap.put(next.getDataPath(), arrayList2);
                    }
                    arrayList2.add(next);
                }
                for (String str : hashMap.keySet()) {
                    ArrayList arrayList3 = (ArrayList) hashMap.get(str);
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        BatchJob batchJob = new BatchJob(str, arrayList3);
                        batchJob.attachInfo(((BaseJob) arrayList3.get(0)).mDbHelper, this.mContext);
                        this.mPool.execute(batchJob);
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("should exec setDbHelperFactory method first!");
    }

    public void execNow(BaseJob baseJob) {
        BaseDbHelper baseDbHelper;
        if (baseJob != null) {
            if (this.mBaseDbHelperFactory != null) {
                String dataPath = baseJob.getDataPath();
                synchronized (this.mDbHelperMap) {
                    baseDbHelper = this.mDbHelperMap.get(dataPath);
                    if (baseDbHelper == null) {
                        baseDbHelper = this.mBaseDbHelperFactory.getDbHelper(this.mContext, dataPath);
                        this.mDbHelperMap.put(dataPath, baseDbHelper);
                    }
                }
                if (!this.mPool.isShutdown()) {
                    baseJob.attachInfo(baseDbHelper, this.mContext);
                    execR(baseJob);
                    return;
                }
                return;
            }
            throw new IllegalStateException("should exec init method first!");
        }
    }

    public void execR(Runnable runnable) {
        if (!this.mPool.isShutdown()) {
            this.mPool.execute(runnable);
        }
    }

    public String getTableName(String str) {
        return getDbHelper(str).getTableName();
    }
}
