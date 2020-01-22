package com.miui.gallery.provider;

import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.Merger;
import com.miui.gallery.cloudcontrol.observers.FeatureStrategyObserver;
import com.miui.gallery.cloudcontrol.strategies.AlbumSortDateStrategy;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MediaSortDateHelper {

    private static class CacheBackedSortDateProvider implements SortDateProvider {
        private static final SortDate DEFAULT_SORT_DATE = SortDate.CREATE_TIME;
        private FeatureStrategyObserver<AlbumSortDateStrategy> mFeatureStatusObserver;
        private Set<Long> mSortByModifyAlbumIds;
        private Set<String> mSortByModifyAlbumPaths = GalleryPreferences.Album.getCachedSortByModifyAlbumPaths();

        CacheBackedSortDateProvider() {
            Set<String> cachedSortByModifyAlbumIds = GalleryPreferences.Album.getCachedSortByModifyAlbumIds();
            if (MiscUtil.isValid(cachedSortByModifyAlbumIds)) {
                this.mSortByModifyAlbumIds = new HashSet(cachedSortByModifyAlbumIds.size());
                for (String valueOf : cachedSortByModifyAlbumIds) {
                    this.mSortByModifyAlbumIds.add(Long.valueOf(valueOf));
                }
            }
            this.mFeatureStatusObserver = new FeatureStrategyObserver<AlbumSortDateStrategy>() {
                public void onStrategyChanged(String str, AlbumSortDateStrategy albumSortDateStrategy) {
                    CacheBackedSortDateProvider.this.dispatchStrategy(albumSortDateStrategy);
                }
            };
            AlbumSortDateStrategy albumSortDateStrategy = (AlbumSortDateStrategy) CloudControlManager.getInstance().registerStrategyObserver("album-sort-date", AlbumSortDateStrategy.class, (Merger) null, this.mFeatureStatusObserver);
            if (albumSortDateStrategy != null) {
                dispatchStrategy(albumSortDateStrategy);
            }
        }

        /* access modifiers changed from: private */
        public void dispatchStrategy(AlbumSortDateStrategy albumSortDateStrategy) {
            if (this.mFeatureStatusObserver != null) {
                CloudControlManager.getInstance().unregisterStrategyObserver(this.mFeatureStatusObserver);
                this.mFeatureStatusObserver = null;
            }
            ThreadManager.getMiscPool().submit(new ThreadPool.Job() {
                public final Object run(ThreadPool.JobContext jobContext) {
                    return MediaSortDateHelper.CacheBackedSortDateProvider.updateCacheInfo(AlbumSortDateStrategy.this);
                }
            });
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.HashSet} */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        public static void updateCacheInfo(AlbumSortDateStrategy albumSortDateStrategy) {
            HashSet hashSet = null;
            if (albumSortDateStrategy == null) {
                GalleryPreferences.Album.setCachedSortByModifyAlbumPaths((Set<String>) null);
                return;
            }
            HashSet hashSet2 = new HashSet(albumSortDateStrategy.getAlbumPathsBySortDate(AlbumSortDateStrategy.SortDate.DATE_MODIFIED));
            GalleryPreferences.Album.setCachedSortByModifyAlbumPaths(hashSet2);
            if (MiscUtil.isValid(hashSet2)) {
                hashSet = SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, String.format(Locale.US, "%s AND (%s COLLATE NOCASE IN ('%s'))", new Object[]{"(serverType=0)", "localFile", TextUtils.join("', '", hashSet2)}), (String[]) null, (String) null, new SafeDBUtil.QueryHandler<HashSet<String>>() {
                    public HashSet<String> handle(Cursor cursor) {
                        if (cursor == null || !cursor.moveToFirst()) {
                            return null;
                        }
                        HashSet<String> hashSet = new HashSet<>(cursor.getCount());
                        do {
                            hashSet.add(cursor.getString(0));
                        } while (cursor.moveToNext());
                        return hashSet;
                    }
                });
            }
            GalleryPreferences.Album.setCachedSortByModifyAlbumIds(hashSet);
        }

        public List<Long> getAlbumIdsBySortDate(SortDate sortDate) {
            return (sortDate != SortDate.MODIFY_TIME || !MiscUtil.isValid(this.mSortByModifyAlbumIds)) ? new ArrayList() : new ArrayList(this.mSortByModifyAlbumIds);
        }

        public List<String> getAlbumPathsBySortDate(SortDate sortDate) {
            return (sortDate != SortDate.MODIFY_TIME || !MiscUtil.isValid(this.mSortByModifyAlbumPaths)) ? new ArrayList() : new ArrayList(this.mSortByModifyAlbumPaths);
        }

        public SortDate getDefaultSortDate() {
            return DEFAULT_SORT_DATE;
        }

        public SortDate getSortDateByAlbumPath(String str) {
            return (!MiscUtil.isValid(this.mSortByModifyAlbumPaths) || TextUtils.isEmpty(str)) ? DEFAULT_SORT_DATE : this.mSortByModifyAlbumPaths.contains(str.toLowerCase()) ? SortDate.MODIFY_TIME : DEFAULT_SORT_DATE;
        }
    }

    private static class SingletonHolder {
        static final SortDateProvider INSTANCE = MediaSortDateHelper.createSortDateProvider();
    }

    public enum SortDate {
        CREATE_TIME,
        MODIFY_TIME
    }

    public interface SortDateProvider {
        List<Long> getAlbumIdsBySortDate(SortDate sortDate);

        List<String> getAlbumPathsBySortDate(SortDate sortDate);

        SortDate getDefaultSortDate();

        SortDate getSortDateByAlbumPath(String str);
    }

    /* access modifiers changed from: private */
    public static SortDateProvider createSortDateProvider() {
        return new CacheBackedSortDateProvider();
    }

    public static SortDateProvider getSortDateProvider() {
        return SingletonHolder.INSTANCE;
    }
}
