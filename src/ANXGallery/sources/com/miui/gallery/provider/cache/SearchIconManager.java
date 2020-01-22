package com.miui.gallery.provider.cache;

import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Printer;
import com.google.common.cache.Cache;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.provider.cache.CacheItem;
import com.miui.gallery.provider.cache.SearchIconItem;
import com.miui.gallery.provider.cache.SearchIconManager;
import com.miui.gallery.search.core.display.icon.IconImageLoader;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.logger.TimingTracing;
import com.miui.gallery.util.uil.CloudUriAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchIconManager {
    private static final UriMatcher sIconURIMatcher = new UriMatcher(-1);
    private final ContentObserver mContentObserver = new ContentObserver(ThreadManager.getMainHandler()) {
        public /* synthetic */ Object lambda$onChange$30$SearchIconManager$1(ThreadPool.JobContext jobContext) {
            SearchLog.d(".searchProvider.SearchIconManager", "On notify change, clear cache");
            SearchIconManager.this.releaseCache();
            return null;
        }

        public void onChange(boolean z) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job() {
                public final Object run(ThreadPool.JobContext jobContext) {
                    return SearchIconManager.AnonymousClass1.this.lambda$onChange$30$SearchIconManager$1(jobContext);
                }
            });
        }
    };
    private final Cache<String, SearchIconItem> mIconCache = IconImageLoader.getInstance().getMemoryCache();
    private final List<Uri> mObserveUris = new ArrayList();
    private final CacheItem.QueryFactory<SearchIconItem> mQueryFactory = new SearchIconItem.QueryFactory();
    private final Printer mTracingPrinter = $$Lambda$SearchIconManager$QXGvdKwyqASgRQL5PCnvLGQ7Jc.INSTANCE;

    private static class AlbumCoverIconLoader {
        private static final String[] PROJECTION = {"cover_id", "cover_path", "cover_sha1"};
        private static final String SELECTION = ("=? AND (media_count>0  OR (" + InternalContract.Cloud.ALIAS_USER_CREATE_ALBUM + ") OR " + "_id" + "=" + 2147483646 + ")");

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0083, code lost:
            if (r15 != null) goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0085, code lost:
            r15.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0093, code lost:
            if (r15 != null) goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0096, code lost:
            return r5;
         */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x009a  */
        static SearchIconItem queryIcon(Uri uri, ContentResolver contentResolver) {
            String[] strArr;
            String str;
            Cursor cursor;
            String queryParameter = uri.getQueryParameter("serverId");
            String queryParameter2 = uri.getQueryParameter("id");
            SearchIconItem searchIconItem = null;
            if (!TextUtils.isEmpty(queryParameter)) {
                str = "serverId" + SELECTION;
                strArr = new String[]{queryParameter};
            } else if (!TextUtils.isEmpty(queryParameter2)) {
                str = "_id" + SELECTION;
                strArr = new String[]{queryParameter2};
            } else {
                SearchLog.e(".searchProvider.SearchIconManager", "No valid id found for icon uri: %s", uri);
                return null;
            }
            try {
                cursor = contentResolver.query(GalleryContract.Album.URI_ALL_EXCEPT_DELETED, PROJECTION, str, strArr, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(1);
                            if (TextUtils.isEmpty(string)) {
                                string = StorageUtils.getPriorMicroThumbnailPath(cursor.getString(2));
                            }
                            searchIconItem = SearchIconManager.createIconItem(uri, string, cursor.getLong(0), cursor.getNotificationUri());
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
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
                SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                }
                throw th;
            }
        }
    }

    private static class LocalImageFaceLoader {
        private static final String[] PROJECTION = {"photo_id", "microthumbfile", "thumbnailFile", "localFile", "sha1", "faceXScale", "faceYScale", "faceWScale", "faceHScale"};

        static /* synthetic */ SearchIconItem lambda$queryIcon$31(Uri uri, Cursor cursor) {
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            String string = cursor.getString(2);
            if (TextUtils.isEmpty(string)) {
                string = cursor.getString(3);
            }
            if (TextUtils.isEmpty(string)) {
                string = cursor.getString(1);
            }
            if (TextUtils.isEmpty(string)) {
                string = StorageUtils.getPriorMicroThumbnailPath(cursor.getString(4));
            }
            SearchIconItem access$100 = SearchIconManager.createIconItem(uri, string, cursor.getLong(0), cursor.getNotificationUri());
            access$100.decodeRegionX = Float.valueOf(cursor.getFloat(5));
            access$100.decodeRegionY = Float.valueOf(cursor.getFloat(6));
            access$100.decodeRegionW = Float.valueOf(cursor.getFloat(7));
            access$100.decodeRegionH = Float.valueOf(cursor.getFloat(8));
            return access$100;
        }

        static SearchIconItem queryIcon(Uri uri, Context context) {
            String queryParameter = uri.getQueryParameter("serverId");
            return (SearchIconItem) SafeDBUtil.safeQuery(context, GalleryContract.PeopleFace.IMAGE_FACE_URI.buildUpon().appendQueryParameter("image_server_id", queryParameter).appendQueryParameter("serverId", uri.getQueryParameter("faceId")).build(), PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler(uri) {
                private final /* synthetic */ Uri f$0;

                {
                    this.f$0 = r1;
                }

                public final Object handle(Cursor cursor) {
                    return SearchIconManager.LocalImageFaceLoader.lambda$queryIcon$31(this.f$0, cursor);
                }
            });
        }
    }

    private static class LocalImageIconLoader {
        private static final String[] PROJECTION = {"_id", "alias_clear_thumbnail", "sha1"};

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0089, code lost:
            if (r13 != null) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
            r13.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
            if (r13 != null) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x009c, code lost:
            return r8;
         */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00a0  */
        static SearchIconItem queryIcon(Uri uri, boolean z, ContentResolver contentResolver) {
            String format;
            Cursor cursor;
            Uri uri2 = GalleryContract.Media.URI_ALL;
            if (z) {
                uri2 = uri2.buildUpon().appendQueryParameter("require_full_load", "true").build();
            }
            Uri uri3 = uri2;
            String queryParameter = uri.getQueryParameter("serverId");
            String queryParameter2 = uri.getQueryParameter("id");
            SearchIconItem searchIconItem = null;
            if (!TextUtils.isEmpty(queryParameter)) {
                format = String.format(Locale.US, "%s=%s", new Object[]{"serverId", queryParameter});
            } else if (!TextUtils.isEmpty(queryParameter2)) {
                format = String.format(Locale.US, "%s=%s", new Object[]{"_id", queryParameter2});
            } else {
                SearchLog.e(".searchProvider.SearchIconManager", "No valid id found for icon uri: %s", uri);
                return null;
            }
            try {
                cursor = contentResolver.query(uri3, PROJECTION, format, (String[]) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(1);
                            if (TextUtils.isEmpty(string)) {
                                string = StorageUtils.getPriorMicroThumbnailPath(cursor.getString(2));
                            }
                            Uri notificationUri = cursor.getNotificationUri();
                            if (notificationUri == null) {
                                notificationUri = GalleryContract.Media.URI;
                            }
                            searchIconItem = SearchIconManager.createIconItem(uri, string, cursor.getLong(0), notificationUri);
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    }
                }
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    private static class PeopleCoverIconLoader {
        private static final String[] PROJECTION = {"photo_id", "microthumbfile", "thumbnailFile", "localFile", "sha1", "exifOrientation", "faceXScale", "faceYScale", "faceWScale", "faceHScale"};

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: android.database.Cursor} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: android.database.Cursor} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.database.Cursor} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: com.miui.gallery.provider.cache.SearchIconItem} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: android.database.Cursor} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: com.miui.gallery.provider.cache.SearchIconItem} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: android.database.Cursor} */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00cb  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00d1  */
        /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        public static SearchIconItem queryIcon(Uri uri, ContentResolver contentResolver) {
            SearchIconItem searchIconItem;
            String queryParameter = uri.getQueryParameter("serverId");
            String queryParameter2 = uri.getQueryParameter("id");
            Uri.Builder buildUpon = GalleryContract.PeopleFace.PEOPLE_COVER_URI.buildUpon();
            Cursor cursor = null;
            if (!TextUtils.isEmpty(queryParameter)) {
                buildUpon.appendQueryParameter("serverId", queryParameter);
            } else if (!TextUtils.isEmpty(queryParameter2)) {
                buildUpon.appendQueryParameter("_id", queryParameter2).build();
            } else {
                SearchLog.w(".searchProvider.SearchIconManager", "No valid id found for uri %s", uri);
                return null;
            }
            try {
                Cursor cursor2 = contentResolver.query(buildUpon.build(), PROJECTION, (String) null, (String[]) null, (String) null);
                if (cursor2 != null) {
                    try {
                        if (cursor2.moveToFirst()) {
                            String string = cursor2.getString(2);
                            if (TextUtils.isEmpty(string)) {
                                string = cursor2.getString(3);
                            }
                            if (TextUtils.isEmpty(string)) {
                                string = cursor2.getString(1);
                            }
                            if (TextUtils.isEmpty(string)) {
                                string = StorageUtils.getPriorMicroThumbnailPath(cursor2.getString(4));
                            }
                            SearchIconItem access$100 = SearchIconManager.createIconItem(uri, string, cursor2.getLong(0), cursor2.getNotificationUri());
                            access$100.decodeRegionOrientation = cursor2.getInt(5);
                            access$100.decodeRegionX = Float.valueOf(cursor2.getFloat(6));
                            access$100.decodeRegionY = Float.valueOf(cursor2.getFloat(7));
                            access$100.decodeRegionW = Float.valueOf(cursor2.getFloat(8));
                            access$100.decodeRegionH = Float.valueOf(cursor2.getFloat(9));
                            cursor = access$100;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursor2;
                        searchIconItem = null;
                        try {
                            SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
                            if (cursor != null) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                return cursor;
            } catch (Exception e2) {
                e = e2;
                searchIconItem = null;
                SearchLog.e(".searchProvider.SearchIconManager", "Error occurred while query icon uri %s, %s", uri, e);
                if (cursor != null) {
                    return searchIconItem;
                }
                cursor.close();
                return searchIconItem;
            }
        }
    }

    static {
        sIconURIMatcher.addURI("people", (String) null, 1);
        sIconURIMatcher.addURI("album", (String) null, 2);
        sIconURIMatcher.addURI("image", (String) null, 3);
    }

    /* access modifiers changed from: private */
    public static SearchIconItem createIconItem(Uri uri, String str, long j, Uri uri2) {
        SearchIconItem searchIconItem = new SearchIconItem();
        searchIconItem.iconUri = uri.toString();
        searchIconItem.filePath = str;
        searchIconItem.downloadUri = getDownloadUri(j);
        searchIconItem.notifyUri = uri2;
        return searchIconItem;
    }

    private ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    private Context getContext() {
        return GalleryApp.sGetAndroidContext();
    }

    private static String getDownloadUri(long j) {
        Uri downloadUri = CloudUriAdapter.getDownloadUri(j);
        if (downloadUri == null) {
            return null;
        }
        return downloadUri.toString();
    }

    static /* synthetic */ void lambda$new$29(String str) {
    }

    private void observerUri(Uri uri) {
        if (!this.mObserveUris.contains(uri)) {
            getContentResolver().registerContentObserver(uri, true, this.mContentObserver);
            this.mObserveUris.add(uri);
        }
    }

    private void onIconQueried(String str, SearchIconItem searchIconItem, boolean z) {
        if (searchIconItem != null) {
            this.mIconCache.put(str, searchIconItem);
            if (z) {
                SearchIconDiskCache.getInstance().putIcon(str, searchIconItem);
            }
            if (searchIconItem.notifyUri != null) {
                observerUri(searchIconItem.notifyUri);
            }
        }
    }

    private SearchIconItem queryIcon(Uri uri, boolean z) {
        int match = sIconURIMatcher.match(uri);
        if (match == 1) {
            return PeopleCoverIconLoader.queryIcon(uri, getContentResolver());
        }
        if (match == 2) {
            return AlbumCoverIconLoader.queryIcon(uri, getContentResolver());
        }
        if (match != 3) {
            return null;
        }
        return !TextUtils.isEmpty(uri.getQueryParameter("faceId")) ? LocalImageFaceLoader.queryIcon(uri, getContext()) : LocalImageIconLoader.queryIcon(uri, z, getContentResolver());
    }

    public Cursor query(Uri uri, String[] strArr, Bundle bundle) {
        boolean z;
        boolean z2;
        boolean z3;
        String str = "SearchIconManager-" + SystemClock.elapsedRealtimeNanos();
        TimingTracing.beginTracing(str, "query");
        try {
            ArrayList arrayList = new ArrayList(1);
            boolean z4 = false;
            if (bundle != null) {
                z3 = bundle.getBoolean("use_disk_cache", false);
                if (!z3) {
                    if (!bundle.getBoolean("cache_to_disk", false)) {
                        z2 = false;
                        z = bundle.getBoolean("high_accuracy", false);
                    }
                }
                z2 = true;
                z = bundle.getBoolean("high_accuracy", false);
            } else {
                z = false;
                z3 = false;
                z2 = false;
            }
            SearchIconItem ifPresent = this.mIconCache.getIfPresent(uri.toString());
            TimingTracing.addSplit(str, "query from memory cache");
            if (ifPresent == null && z3) {
                ifPresent = SearchIconDiskCache.getInstance().getIcon(uri.toString());
                if (ifPresent != null) {
                    z4 = true;
                }
                TimingTracing.addSplit(str, "query from disk blob cache");
            }
            if (ifPresent == null) {
                ifPresent = queryIcon(uri, z);
                TimingTracing.addSplit(str, "query from database");
                onIconQueried(uri.toString(), ifPresent, z2);
                TimingTracing.addSplit(str, "dispatch query result");
            }
            if (ifPresent != null) {
                arrayList.add(ifPresent);
            }
            RawCursor rawCursor = new RawCursor(arrayList, strArr, this.mQueryFactory.getMapper());
            if (z4) {
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("from_unreliable_cache", true);
                rawCursor.setExtras(bundle2);
            }
            return rawCursor;
        } finally {
            TimingTracing.stopTracing(str, this.mTracingPrinter);
        }
    }

    public void releaseCache() {
        this.mIconCache.invalidateAll();
        this.mIconCache.cleanUp();
        getContentResolver().unregisterContentObserver(this.mContentObserver);
        this.mObserveUris.clear();
    }
}
