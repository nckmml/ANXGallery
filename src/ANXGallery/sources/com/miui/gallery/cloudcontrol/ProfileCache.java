package com.miui.gallery.cloudcontrol;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.core.util.Pair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miui.gallery.R;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.cloudcontrol.observers.FeatureStatusObserver;
import com.miui.gallery.cloudcontrol.observers.FeatureStrategyObserver;
import com.miui.gallery.cloudcontrol.strategies.BaseStrategy;
import com.miui.gallery.cloudcontrol.strategies.RecommendStrategy;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.reddot.DisplayStatusManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SafeDBUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class ProfileCache {
    private HashMap<String, FeatureProfile> mCloudCache = new HashMap<>();
    private HashMap<String, Object> mCloudStrategyCache = new HashMap<>();
    private volatile boolean mIsLoadFinished;
    private HashMap<String, FeatureProfile> mLocalCache = new HashMap<>();
    private HashMap<String, Object> mLocalStrategyCache = new HashMap<>();
    private volatile boolean mPendingNotify;
    private final PublishSubject<Pair<String, FeatureProfile.Status>> mStatusSubject = PublishSubject.create();
    private final PublishSubject<String> mStrategySubject = PublishSubject.create();
    private final Object mSyncLock = new Object();

    ProfileCache() {
    }

    static /* synthetic */ boolean lambda$registerStatusObserver$9(String str, Pair pair) throws Exception {
        return !TextUtils.isEmpty((CharSequence) pair.first) && ((String) pair.first).equals(str);
    }

    private void loadFromDB(Context context) {
        Context context2 = context;
        SafeDBUtil.safeQuery(context2, GalleryContract.CloudControl.URI, FeatureDBItem.PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler(System.currentTimeMillis()) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final Object handle(Cursor cursor) {
                return ProfileCache.this.lambda$loadFromDB$12$ProfileCache(this.f$1, cursor);
            }
        });
    }

    private void loadFromLocalFile(Reader reader) {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(FeatureProfile.class, new FeatureProfile.Deserializer());
            ArrayList arrayList = (ArrayList) gsonBuilder.create().fromJson(reader, new TypeToken<ArrayList<FeatureProfile>>() {
            }.getType());
            synchronized (this.mSyncLock) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FeatureProfile featureProfile = (FeatureProfile) it.next();
                    this.mLocalCache.put(featureProfile.getName(), featureProfile);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
            }
        } catch (Exception e2) {
            try {
                Log.e("CloudControl.ProfileCache", "Failed to load from local file, errorClause: %s, errorMessage: %s.", e2.getCause(), e2.getMessage());
                e2.printStackTrace();
                Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                generatorCommonParams.put("errorMsg", e2.getMessage());
                GallerySamplingStatHelper.recordErrorEvent("cloud_control", "parse_local_file_failed", generatorCommonParams);
            } finally {
                try {
                    reader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private void notifyStatusChanged(String str, String str2) {
        this.mStatusSubject.onNext(new Pair(str, FeatureProfile.Status.fromValue(str2)));
    }

    private void notifyStrategyChanged(String str) {
        this.mStrategySubject.onNext(str);
    }

    /* access modifiers changed from: package-private */
    public void clearCloudCache() {
        synchronized (this.mSyncLock) {
            for (Map.Entry<String, FeatureProfile> key : this.mCloudCache.entrySet()) {
                String str = (String) key.getKey();
                notifyStatusChanged(str, this.mLocalCache.get(str) != null ? this.mLocalCache.get(str).getStatus() : FeatureProfile.Status.UNAVAILABLE.getValue());
            }
            this.mCloudCache.clear();
            this.mCloudStrategyCache.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void insertToCloudCache(FeatureProfile featureProfile) {
        String name = featureProfile.getName();
        String status = featureProfile.getStatus();
        String strategy = featureProfile.getStrategy();
        FeatureProfile featureProfile2 = this.mCloudCache.get(name);
        synchronized (this.mSyncLock) {
            if (FeatureProfile.Status.REMOVE.getValue().equals(status)) {
                if (featureProfile2 != null) {
                    this.mCloudCache.remove(name);
                    this.mCloudStrategyCache.remove(name);
                }
                notifyStatusChanged(name, status);
            } else if (featureProfile2 != null) {
                if (featureProfile2.getStatus() != null && featureProfile2.getStatus().equals(FeatureProfile.Status.ENABLE.getValue())) {
                    status = FeatureProfile.Status.ENABLE.getValue();
                }
                if (!TextUtils.equals(featureProfile2.getStrategy(), strategy)) {
                    if (name.equals("recommendation")) {
                        if (featureProfile2.getStrategy() != null) {
                            if (!featureProfile2.getStrategy().isEmpty()) {
                                if (((RecommendStrategy) new Gson().fromJson(strategy, RecommendStrategy.class)).getMaxSeqId() > ((RecommendStrategy) new Gson().fromJson(featureProfile2.getStrategy(), RecommendStrategy.class)).getMaxSeqId()) {
                                    DisplayStatusManager.updateFeature("recommendation");
                                }
                            }
                        }
                        DisplayStatusManager.updateFeature("recommendation");
                    }
                    featureProfile2.setStrategy(strategy);
                    this.mCloudStrategyCache.remove(name);
                    notifyStrategyChanged(name);
                }
                if (!TextUtils.equals(featureProfile2.getStatus(), status)) {
                    featureProfile2.setStatus(status);
                    notifyStatusChanged(name, status);
                }
            } else {
                FeatureProfile featureProfile3 = new FeatureProfile();
                featureProfile3.setStrategy(strategy);
                featureProfile3.setStatus(status);
                featureProfile3.setName(name);
                this.mCloudCache.put(name, featureProfile3);
                notifyStatusChanged(name, status);
                notifyStrategyChanged(name);
            }
        }
    }

    public /* synthetic */ Object lambda$loadFromDB$12$ProfileCache(long j, Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            Log.e("CloudControl.ProfileCache", "Fill cache failed with a null cursor.");
            return null;
        }
        synchronized (this.mSyncLock) {
            while (cursor.moveToNext()) {
                FeatureDBItem fromCursor = FeatureDBItem.fromCursor(cursor);
                this.mCloudCache.put(fromCursor.getName(), fromCursor);
            }
        }
        Log.d("CloudControl.ProfileCache", "Load %d items from database, cost %d ms.", Integer.valueOf(cursor.getCount()), Long.valueOf(System.currentTimeMillis() - j));
        return null;
    }

    public /* synthetic */ Pair lambda$registerStrategyObserver$11$ProfileCache(Class cls, Merger merger, String str) throws Exception {
        return new Pair(str, queryStrategy(str, cls, merger));
    }

    /* access modifiers changed from: package-private */
    public void load(Context context) {
        loadFromDB(context);
        loadFromLocalFile(new InputStreamReader(context.getResources().openRawResource(R.raw.cloud_control)));
        this.mIsLoadFinished = true;
    }

    /* access modifiers changed from: package-private */
    public void notifyAfterLoadFinished() {
        synchronized (this.mSyncLock) {
            if (this.mPendingNotify) {
                Log.d("CloudControl.ProfileCache", "Notify all feature status after cache load finished:");
                for (Map.Entry next : this.mCloudCache.entrySet()) {
                    if (!TextUtils.isEmpty((CharSequence) next.getKey()) && next.getValue() != null) {
                        Log.d("CloudControl.ProfileCache", "Feature name: %s, feature status: %s", next.getKey(), String.valueOf(((FeatureProfile) next.getValue()).getStatus()));
                        notifyStatusChanged((String) next.getKey(), ((FeatureProfile) next.getValue()).getStatus());
                        notifyStrategyChanged((String) next.getKey());
                    }
                }
                this.mPendingNotify = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public FeatureProfile.Status queryStatus(String str) {
        if (TextUtils.isEmpty(str)) {
            return FeatureProfile.Status.UNAVAILABLE;
        }
        synchronized (this.mSyncLock) {
            if (this.mIsLoadFinished) {
                String str2 = null;
                if (this.mCloudCache.get(str) != null) {
                    str2 = this.mCloudCache.get(str).getStatus();
                } else if (this.mLocalCache.get(str) != null) {
                    str2 = this.mLocalCache.get(str).getStatus();
                }
                if (TextUtils.isEmpty(str2)) {
                    FeatureProfile.Status status = FeatureProfile.Status.UNAVAILABLE;
                    return status;
                }
                try {
                    FeatureProfile.Status fromValue = FeatureProfile.Status.fromValue(str2);
                    return fromValue;
                } catch (Exception unused) {
                    return FeatureProfile.Status.UNAVAILABLE;
                }
            } else {
                this.mPendingNotify = true;
                FeatureProfile.Status status2 = FeatureProfile.Status.UNAVAILABLE;
                return status2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bf  */
    public <T extends BaseStrategy> T queryStrategy(String str, Class<T> cls, Merger<T> merger) {
        T t;
        T t2;
        T t3 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.mSyncLock) {
            if (this.mIsLoadFinished) {
                T t4 = this.mCloudStrategyCache.get(str);
                if (t4 != null && cls.isAssignableFrom(t4.getClass())) {
                    t = (BaseStrategy) t4;
                } else if (this.mCloudCache.get(str) != null) {
                    String strategy = this.mCloudCache.get(str).getStrategy();
                    try {
                        t = (BaseStrategy) new Gson().fromJson(strategy, cls);
                        if (t != null) {
                            try {
                                t.doAdditionalProcessing();
                                this.mCloudStrategyCache.put(str, t);
                            } catch (Exception e) {
                                e = e;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        t = null;
                        Log.e("CloudControl.ProfileCache", "Failed to deserialize strategy: %s", (Object) strategy);
                        e.printStackTrace();
                        t2 = this.mLocalStrategyCache.get(str);
                        if (t2 != null || cls.isAssignableFrom(t2.getClass())) {
                        }
                        if (merger == null) {
                        }
                        if (t != null) {
                        }
                        return t3;
                    }
                } else {
                    t = null;
                }
                t2 = this.mLocalStrategyCache.get(str);
                if (t2 != null && cls.isAssignableFrom(t2.getClass())) {
                    t3 = (BaseStrategy) t2;
                } else if (this.mLocalCache.get(str) != null) {
                    String strategy2 = this.mLocalCache.get(str).getStrategy();
                    try {
                        T t5 = (BaseStrategy) new Gson().fromJson(strategy2, cls);
                        if (t5 != null) {
                            try {
                                t5.doAdditionalProcessing();
                                this.mLocalStrategyCache.put(str, t5);
                            } catch (Exception e3) {
                                e = e3;
                                t3 = t5;
                            }
                        }
                        t3 = t5;
                    } catch (Exception e4) {
                        e = e4;
                        Log.e("CloudControl.ProfileCache", "Failed to deserialize strategy: %s", (Object) strategy2);
                        e.printStackTrace();
                        if (merger == null) {
                        }
                        if (t != null) {
                        }
                        return t3;
                    }
                }
                if (merger == null && t != null && t3 != null) {
                    T t6 = (BaseStrategy) merger.merge(t3, t);
                    return t6;
                } else if (t != null) {
                    t3 = t;
                }
            } else {
                this.mPendingNotify = true;
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public FeatureProfile.Status registerStatusObserver(String str, FeatureStatusObserver featureStatusObserver) {
        if (!TextUtils.isEmpty(str)) {
            if (featureStatusObserver != null) {
                this.mStatusSubject.filter(new Predicate(str) {
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final boolean test(Object obj) {
                        return ProfileCache.lambda$registerStatusObserver$9(this.f$0, (Pair) obj);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(featureStatusObserver);
            }
            return queryStatus(str);
        }
        throw new IllegalArgumentException("feature should not be empty or null.");
    }

    /* access modifiers changed from: package-private */
    public <T extends BaseStrategy> T registerStrategyObserver(String str, Class<T> cls, Merger<T> merger, FeatureStrategyObserver<T> featureStrategyObserver) {
        if (!TextUtils.isEmpty(str)) {
            if (featureStrategyObserver != null) {
                this.mStrategySubject.filter(new Predicate(str) {
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final boolean test(Object obj) {
                        return ((String) obj).equals(this.f$0);
                    }
                }).map(new Function(cls, merger) {
                    private final /* synthetic */ Class f$1;
                    private final /* synthetic */ Merger f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final Object apply(Object obj) {
                        return ProfileCache.this.lambda$registerStrategyObserver$11$ProfileCache(this.f$1, this.f$2, (String) obj);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(featureStrategyObserver);
            }
            return queryStrategy(str, cls, merger);
        }
        throw new IllegalArgumentException("feature should not be empty or null.");
    }

    /* access modifiers changed from: package-private */
    public void unregisterStatusObserver(FeatureStatusObserver featureStatusObserver) {
        if (!featureStatusObserver.isDisposed()) {
            featureStatusObserver.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterStrategyObserver(FeatureStrategyObserver featureStrategyObserver) {
        if (!featureStrategyObserver.isDisposed()) {
            featureStrategyObserver.dispose();
        }
    }
}
