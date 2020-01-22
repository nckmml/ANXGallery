package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.CollectionUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class OnlineConfig {
    private static volatile OnlineConfig instance;
    private HashSet<OCUpdateCallback> mCallbacks = new HashSet<>();
    protected SharedPreferences preferences;

    public static abstract class OCUpdateCallback implements Runnable {
        private String mDescription;
        private int mId;

        public OCUpdateCallback(int i, String str) {
            this.mId = i;
            this.mDescription = str;
        }

        public boolean equals(Object obj) {
            return (obj instanceof OCUpdateCallback) && this.mId == ((OCUpdateCallback) obj).mId;
        }

        public int hashCode() {
            return this.mId;
        }

        /* access modifiers changed from: protected */
        public abstract void onCallback();

        public final void run() {
            onCallback();
        }
    }

    private OnlineConfig(Context context) {
        this.preferences = context.getSharedPreferences("mipush_oc", 0);
    }

    private String getCustomOcKey(int i) {
        return "custom_oc_" + i;
    }

    public static OnlineConfig getInstance(Context context) {
        if (instance == null) {
            synchronized (OnlineConfig.class) {
                if (instance == null) {
                    instance = new OnlineConfig(context);
                }
            }
        }
        return instance;
    }

    private String getNormalOcKey(int i) {
        return "normal_oc_" + i;
    }

    private void putConfig(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
        } else if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
        } else if (pair.second instanceof String) {
            editor.putString(str, (String) pair.second);
        } else if (pair.second instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
        }
    }

    public synchronized void addOCUpdateCallbacks(OCUpdateCallback oCUpdateCallback) {
        if (!this.mCallbacks.contains(oCUpdateCallback)) {
            this.mCallbacks.add(oCUpdateCallback);
        }
    }

    public synchronized void clearCallbacks() {
        this.mCallbacks.clear();
    }

    public boolean getBooleanValue(int i, boolean z) {
        String customOcKey = getCustomOcKey(i);
        if (this.preferences.contains(customOcKey)) {
            return this.preferences.getBoolean(customOcKey, false);
        }
        String normalOcKey = getNormalOcKey(i);
        return this.preferences.contains(normalOcKey) ? this.preferences.getBoolean(normalOcKey, false) : z;
    }

    public int getIntValue(int i, int i2) {
        String customOcKey = getCustomOcKey(i);
        if (this.preferences.contains(customOcKey)) {
            return this.preferences.getInt(customOcKey, 0);
        }
        String normalOcKey = getNormalOcKey(i);
        return this.preferences.contains(normalOcKey) ? this.preferences.getInt(normalOcKey, 0) : i2;
    }

    public String getStringValue(int i, String str) {
        String customOcKey = getCustomOcKey(i);
        if (this.preferences.contains(customOcKey)) {
            return this.preferences.getString(customOcKey, (String) null);
        }
        String normalOcKey = getNormalOcKey(i);
        return this.preferences.contains(normalOcKey) ? this.preferences.getString(normalOcKey, (String) null) : str;
    }

    /* access modifiers changed from: package-private */
    public void runCallback() {
        MyLog.v("OC_Callback : receive new oc data");
        HashSet hashSet = new HashSet();
        synchronized (this) {
            hashSet.addAll(this.mCallbacks);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            OCUpdateCallback oCUpdateCallback = (OCUpdateCallback) it.next();
            if (oCUpdateCallback != null) {
                oCUpdateCallback.run();
            }
        }
        hashSet.clear();
    }

    public void updateCustomConfigs(List<Pair<Integer, Object>> list) {
        if (!CollectionUtils.isEmpty(list)) {
            SharedPreferences.Editor edit = this.preferences.edit();
            for (Pair next : list) {
                if (next.first != null) {
                    String customOcKey = getCustomOcKey(((Integer) next.first).intValue());
                    if (next.second == null) {
                        edit.remove(customOcKey);
                    } else {
                        putConfig(edit, next, customOcKey);
                    }
                }
            }
            edit.commit();
        }
    }

    public void updateNormalConfigs(List<Pair<Integer, Object>> list) {
        if (!CollectionUtils.isEmpty(list)) {
            SharedPreferences.Editor edit = this.preferences.edit();
            for (Pair next : list) {
                if (!(next.first == null || next.second == null)) {
                    putConfig(edit, next, getNormalOcKey(((Integer) next.first).intValue()));
                }
            }
            edit.commit();
        }
    }
}
