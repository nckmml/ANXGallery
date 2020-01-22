package com.miui.gallery.provider.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.provider.cache.CacheItem;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class CacheManager<T extends CacheItem> {
    protected List<T> mCache;
    protected CacheItem.Generator<T> mGenerator;
    protected final Object mModifyLock = new Object();
    protected CacheItem.QueryFactory<T> mQueryFactory;

    private List<T> sort(List<T> list, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        int indexOf = str.indexOf(32);
        boolean z = false;
        String substring = str.substring(0, indexOf > 0 ? indexOf : str.length());
        int index = this.mQueryFactory.getMapper().getIndex(substring);
        if (index >= 0) {
            if (indexOf > 0 && str.substring(indexOf, str.length()).trim().equalsIgnoreCase("desc")) {
                z = true;
            }
            sort(list, index, z);
            Log.d(".provider.cache.CacheManager", "sort cost: %sms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return list;
        }
        throw new IllegalArgumentException(substring + " not found");
    }

    public int delete(String str, String[] strArr) {
        int internalDelete;
        Log.d(".provider.cache.CacheManager", "deleting %s, %s", str, Arrays.toString(strArr));
        synchronized (this.mModifyLock) {
            internalDelete = internalDelete(str, strArr, this.mCache);
        }
        Log.d(".provider.cache.CacheManager", "delete finished");
        return internalDelete;
    }

    /* access modifiers changed from: protected */
    public List<T> filter(Filter<T> filter) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(this.mCache.size());
        for (T t : this.mCache) {
            if (filter.filter(t) != null) {
                arrayList.add(t);
            }
        }
        Log.d(".provider.cache.CacheManager", "filter cost: %sms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ContentValues filterLogInfo(ContentValues contentValues) {
        return contentValues;
    }

    /* access modifiers changed from: protected */
    public List<T> group(List<T> list, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        int index = this.mQueryFactory.getMapper().getIndex(str);
        CacheItem.Merger<T> merger = this.mQueryFactory.getMerger(index);
        if (index != -1) {
            HashMap hashMap = new HashMap((list.size() * 4) / 3);
            for (T t : list) {
                Object obj = t.get(index, false);
                CacheItem cacheItem = (CacheItem) hashMap.get(obj);
                if (cacheItem == null) {
                    hashMap.put(obj, t);
                } else {
                    hashMap.put(obj, merger.merge(cacheItem, t, index));
                }
            }
            ArrayList arrayList = new ArrayList(hashMap.values());
            Log.d(".provider.cache.CacheManager", "group cost: %sms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return arrayList;
        }
        throw new IllegalArgumentException(str + " not found.");
    }

    public long insert(long j, ContentValues contentValues) {
        T from = this.mGenerator.from(j, contentValues);
        synchronized (this.mModifyLock) {
            if (this.mCache.contains(from)) {
                return j;
            }
            this.mCache.add(from);
            return j;
        }
    }

    /* access modifiers changed from: protected */
    public final int internalDelete(String str, String[] strArr, List<T> list) {
        int i = 0;
        for (T t : filter(Filter.from(str, strArr, this.mGenerator))) {
            list.remove(t);
            Log.d(".provider.cache.CacheManager", "deleted %s", (Object) t);
            i++;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int internalUpdate(String str, String[] strArr, ContentValues contentValues) {
        List<T> filter;
        Filter<T> from = Filter.from(str, strArr, this.mGenerator);
        synchronized (this.mModifyLock) {
            filter = filter(from);
        }
        int i = 0;
        for (T t : filter) {
            this.mGenerator.update(t, contentValues);
            Log.d(".provider.cache.CacheManager", "updated %s", (Object) t);
            i++;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public Cursor onCreateCursor(String[] strArr, List<T> list, String str, String str2, Bundle bundle) {
        return new MemoryCursor(new DataResult(strArr, list, this.mQueryFactory.getMapper()), str);
    }

    public Cursor query(String[] strArr, String str, String[] strArr2, String str2, String str3, Bundle bundle) {
        List<T> filter;
        long currentTimeMillis = System.currentTimeMillis();
        Filter<T> from = Filter.from(str, strArr2, this.mQueryFactory);
        synchronized (this.mModifyLock) {
            filter = filter(from);
        }
        if (!TextUtils.isEmpty(str3)) {
            filter = group(filter, str3);
        }
        if (!TextUtils.isEmpty(str2) && !filter.isEmpty()) {
            filter = sort(filter, str2);
        }
        List<T> list = filter;
        Log.d(".provider.cache.CacheManager", "query cost: %sms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return onCreateCursor(strArr, list, str, str2, bundle);
    }

    /* access modifiers changed from: protected */
    public void sort(List<T> list, int i, boolean z) {
        Comparator<T> comparator = this.mQueryFactory.getComparator(i, z);
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    public int update(String str, String[] strArr, ContentValues contentValues) {
        int internalUpdate;
        Log.d(".provider.cache.CacheManager", "updating %s, args: %s with ContentValues[%s]", str, Arrays.toString(strArr), filterLogInfo(contentValues));
        synchronized (this.mModifyLock) {
            internalUpdate = internalUpdate(str, strArr, contentValues);
        }
        Log.d(".provider.cache.CacheManager", "update finished");
        return internalUpdate;
    }
}
