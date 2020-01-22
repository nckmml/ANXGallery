package com.miui.gallery.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.provider.cache.SearchHistoryManager;
import com.miui.gallery.provider.cache.SearchIconManager;
import com.miui.gallery.search.SearchContract;

public class SearchProvider extends ContentProvider {
    /* access modifiers changed from: private */
    public static final UriMatcher sUriMatcher = new UriMatcher(-1);
    private ContentResolver mContentResolver;
    private final Object mInitLock = new Object();
    private SearchHistoryManager mSearchHistoryManager = null;
    private SearchIconManager mSearchIconManager = null;

    private class ContentResolver extends GalleryContentResolver {
        private ContentResolver() {
        }

        /* access modifiers changed from: protected */
        public Object matchUri(Uri uri) {
            return Integer.valueOf(SearchProvider.sUriMatcher.match(uri));
        }

        /* access modifiers changed from: protected */
        public void notifyInternal(Uri uri, ContentObserver contentObserver, long j) {
            if (SearchProvider.sUriMatcher.match(uri) == 1) {
                SearchProvider.this.getContext().getContentResolver().notifyChange(SearchContract.History.URI, contentObserver, false);
            }
        }
    }

    static {
        sUriMatcher.addURI("com.miui.gallery.search", "history", 1);
        sUriMatcher.addURI("com.miui.gallery.search", "icon", 2);
    }

    private void init() {
        synchronized (this.mInitLock) {
            if (this.mSearchHistoryManager == null) {
                this.mSearchHistoryManager = new SearchHistoryManager();
                this.mSearchIconManager = new SearchIconManager();
                this.mContentResolver = new ContentResolver();
            }
        }
    }

    private void notifyChange(Uri uri) {
        this.mContentResolver.notifyInternal(uri, (ContentObserver) null, 0);
    }

    private void registerNotifyUri(Cursor cursor, int i) {
        if (i == 1) {
            cursor.setNotificationUri(getContext().getContentResolver(), SearchContract.History.URI);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        init();
        int delete = sUriMatcher.match(uri) != 1 ? 0 : this.mSearchHistoryManager.delete(str, strArr);
        if (delete > 0) {
            notifyChange(uri);
        }
        return delete;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        init();
        long insert = sUriMatcher.match(uri) != 1 ? -1 : this.mSearchHistoryManager.insert(-1, contentValues);
        int i = (insert > -1 ? 1 : (insert == -1 ? 0 : -1));
        if (i != 0) {
            notifyChange(uri);
        }
        if (i != 0) {
            return ContentUris.withAppendedId(uri, insert);
        }
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public void onLowMemory() {
        init();
        this.mSearchHistoryManager.releaseCache();
        this.mSearchIconManager.releaseCache();
    }

    public void onTrimMemory(int i) {
        init();
        if (i >= 5) {
            this.mSearchHistoryManager.releaseCache();
            this.mSearchIconManager.releaseCache();
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        init();
        int match = sUriMatcher.match(uri);
        boolean z = true;
        if (match != 1) {
            if (match == 2) {
                String queryParameter = uri.getQueryParameter("icon_uri");
                boolean booleanQueryParameter = uri.getBooleanQueryParameter("use_disk_cache", false);
                if (!booleanQueryParameter && !uri.getBooleanQueryParameter("cache_to_disk", false)) {
                    z = false;
                }
                boolean booleanQueryParameter2 = uri.getBooleanQueryParameter("high_accuracy", false);
                if (!TextUtils.isEmpty(queryParameter)) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("use_disk_cache", booleanQueryParameter);
                    bundle.putBoolean("cache_to_disk", z);
                    bundle.putBoolean("high_accuracy", booleanQueryParameter2);
                    return this.mSearchIconManager.query(Uri.parse(queryParameter), strArr, bundle);
                }
            }
            return null;
        }
        Bundle bundle2 = new Bundle();
        if (uri.getQueryParameter("query_limit") != null) {
            bundle2.putInt("query_limit", Integer.valueOf(uri.getQueryParameter("query_limit")).intValue());
        }
        if (!TextUtils.isEmpty(uri.getQueryParameter("query_text"))) {
            bundle2.putString("query_text", uri.getQueryParameter("query_text"));
        }
        Cursor query = this.mSearchHistoryManager.query(strArr, str, strArr2, str2, (String) null, bundle2);
        if (query == null) {
            return query;
        }
        registerNotifyUri(query, match);
        return query;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        init();
        int update = sUriMatcher.match(uri) != 1 ? 0 : this.mSearchHistoryManager.update(str, strArr, contentValues);
        if (update > 0) {
            notifyChange(uri);
        }
        return update;
    }
}
