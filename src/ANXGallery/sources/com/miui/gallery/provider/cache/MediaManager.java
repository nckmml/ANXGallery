package com.miui.gallery.provider.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.assistant.model.TinyImageFeature;
import com.miui.gallery.data.LocationManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.AlbumManager;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.provider.TimelineHeadersGroup;
import com.miui.gallery.provider.cache.MediaItem;
import com.miui.gallery.util.DebugUtil;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MediaManager extends CacheManager<MediaItem> {
    private static Comparator<MediaItem> sFeatureComparator;
    private static Comparator<MediaItem> sTimeComparator;
    /* access modifiers changed from: private */
    public AlbumDelegate mAlbumDelegate;
    /* access modifiers changed from: private */
    public FavoritesDelegate mFavoritesDelegate;
    /* access modifiers changed from: private */
    public String mFirstPartIds;
    /* access modifiers changed from: private */
    public CountDownLatch mFullLoadDoneSignal;
    /* access modifiers changed from: private */
    public final ArrayList<InitializeListener> mInitializeListeners;
    /* access modifiers changed from: private */
    public volatile boolean mInitialized;
    /* access modifiers changed from: private */
    public volatile boolean mIsFullLoadDone;
    private volatile boolean mIsLoadStarted;
    /* access modifiers changed from: private */
    public volatile boolean mIsMinimumLoadDone;
    /* access modifiers changed from: private */
    public final Object mMinimumLoadLock;

    public interface InitializeListener {
        void onProgressUpdate();
    }

    private class InitializeTask implements Runnable {
        private SQLiteOpenHelper mDBHelper;

        public InitializeTask(SQLiteOpenHelper sQLiteOpenHelper) {
            this.mDBHelper = sQLiteOpenHelper;
        }

        private String getLoadSortBy() {
            List<Long> albumIdsBySortDate = MediaSortDateHelper.getSortDateProvider().getAlbumIdsBySortDate(MediaSortDateHelper.SortDate.MODIFY_TIME);
            if (!MiscUtil.isValid(albumIdsBySortDate)) {
                return "mixedDateTime";
            }
            return String.format(Locale.US, "(case when %s in (%s) then %s else %s end)", new Object[]{"localGroupId", TextUtils.join(",", albumIdsBySortDate), "dateModified", "mixedDateTime"});
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0078 A[Catch:{ all -> 0x00ad }] */
        private void loadFullPart(SQLiteDatabase sQLiteDatabase) {
            String str;
            String str2;
            Log.d(".provider.cache.MediaManager", "start load second part");
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            if (TextUtils.isEmpty(MediaManager.this.mFirstPartIds)) {
                str2 = getLoadSortBy() + " DESC LIMIT -1 OFFSET " + 128;
                str = "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType IN (1,2)";
            } else {
                str = String.format("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType IN (1,2) AND _id NOT IN (%s)", new Object[]{MediaManager.this.mFirstPartIds});
                str2 = getLoadSortBy();
            }
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Cursor query = sQLiteDatabase2.query("cloud", MediaManager.this.mGenerator.getProjection(), str, (String[]) null, (String) null, (String) null, str2, (String) null);
            if (query != null) {
                try {
                    arrayList.ensureCapacity(4096);
                    while (true) {
                        int i = 0;
                        while (query.moveToNext()) {
                            arrayList.add(MediaManager.this.mGenerator.from(query));
                            i++;
                            if (i == 4096 || query.isLast()) {
                                publishResult(arrayList);
                                arrayList.clear();
                            }
                            while (query.moveToNext()) {
                            }
                        }
                        query.close();
                        Log.d(".provider.cache.MediaManager", "load second part cost: %d. current position = %d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(query.getPosition()));
                        return;
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
        }

        private void loadMinimumPart(SQLiteDatabase sQLiteDatabase) {
            String format;
            Log.d(".provider.cache.MediaManager", "read first part");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                if (TextUtils.isEmpty(MediaManager.this.mFirstPartIds)) {
                    format = "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType IN (1,2)";
                } else {
                    format = String.format("(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType IN (1,2) AND _id IN (%s)", new Object[]{MediaManager.this.mFirstPartIds});
                }
                String str = format;
                String[] projection = MediaManager.this.mGenerator.getProjection();
                Cursor query = sQLiteDatabase.query("cloud", projection, str, (String[]) null, (String) null, (String) null, getLoadSortBy() + " DESC", String.valueOf(128));
                if (query == null) {
                    Log.e(".provider.cache.MediaManager", "fill provider failed with a null cursor");
                }
                try {
                    ArrayList arrayList = new ArrayList(64);
                    while (query.moveToNext()) {
                        arrayList.add(MediaManager.this.mGenerator.from(query));
                    }
                    publishResult(arrayList);
                    Log.d(".provider.cache.MediaManager", "load %d item for the first time, costs %dms", Integer.valueOf(query.getCount()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                } finally {
                    query.close();
                }
            } catch (SQLiteException e) {
                Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                generatorCommonParams.put("dbversion", String.valueOf(sQLiteDatabase.getVersion()));
                generatorCommonParams.put("exception", e.getMessage() + " : " + e.getCause());
                GallerySamplingStatHelper.recordErrorEvent("db_helper", "db_mediamanager_load", generatorCommonParams);
                DebugUtil.exportDB(false);
                GalleryDBHelper.getInstance().deleteDatabase();
                throw e;
            }
        }

        private void lockedLoadMinimumPart(SQLiteDatabase sQLiteDatabase) {
            synchronized (MediaManager.this.mMinimumLoadLock) {
                try {
                    Log.d(".provider.cache.MediaManager", "read albums");
                    MediaManager.this.mAlbumDelegate.load(sQLiteDatabase);
                    MediaManager.this.mFavoritesDelegate.load(sQLiteDatabase);
                    String unused = MediaManager.this.mFirstPartIds = GalleryPreferences.HomePage.getHomePageImageIds();
                    loadMinimumPart(sQLiteDatabase);
                    boolean unused2 = MediaManager.this.mIsMinimumLoadDone = true;
                    MediaManager.this.mMinimumLoadLock.notifyAll();
                } catch (Throwable th) {
                    boolean unused3 = MediaManager.this.mIsMinimumLoadDone = true;
                    MediaManager.this.mMinimumLoadLock.notifyAll();
                    throw th;
                }
            }
        }

        private void notifyProgressUpdate() {
            synchronized (MediaManager.this.mInitializeListeners) {
                if (MediaManager.this.mInitializeListeners.size() > 0) {
                    Iterator it = MediaManager.this.mInitializeListeners.iterator();
                    while (it.hasNext()) {
                        InitializeListener initializeListener = (InitializeListener) it.next();
                        if (initializeListener != null) {
                            initializeListener.onProgressUpdate();
                        }
                    }
                }
            }
        }

        private void publishResult(List<MediaItem> list) {
            synchronized (MediaManager.this.mModifyLock) {
                for (MediaItem add : list) {
                    MediaManager.this.mCache.add(add);
                }
                notifyProgressUpdate();
            }
        }

        public void run() {
            Log.d(".provider.cache.MediaManager", "acquire initialize lock");
            try {
                Log.d(".provider.cache.MediaManager", "start initialize");
                SQLiteDatabase readableDatabase = this.mDBHelper.getReadableDatabase();
                lockedLoadMinimumPart(readableDatabase);
                Process.setThreadPriority(10);
                loadFullPart(readableDatabase);
                boolean unused = MediaManager.this.mInitialized = true;
                boolean unused2 = MediaManager.this.mIsFullLoadDone = true;
                MediaManager.this.mFullLoadDoneSignal.countDown();
                synchronized (MediaManager.this.mInitializeListeners) {
                    MediaManager.this.mInitializeListeners.clear();
                }
            } catch (Throwable th) {
                boolean unused3 = MediaManager.this.mIsFullLoadDone = true;
                MediaManager.this.mFullLoadDoneSignal.countDown();
                synchronized (MediaManager.this.mInitializeListeners) {
                    MediaManager.this.mInitializeListeners.clear();
                    throw th;
                }
            }
        }
    }

    private static class SingletonHolder {
        static final MediaManager INSTANCE = new MediaManager();
    }

    private MediaManager() {
        this.mIsLoadStarted = false;
        this.mIsFullLoadDone = false;
        this.mMinimumLoadLock = new Object();
        this.mIsMinimumLoadDone = false;
        this.mInitialized = false;
        this.mFullLoadDoneSignal = new CountDownLatch(1);
        this.mInitializeListeners = new ArrayList<>();
        this.mAlbumDelegate = new AlbumDelegate();
        this.mFavoritesDelegate = new FavoritesDelegate();
        this.mCache = new LinkedList();
        this.mGenerator = new MediaItem.Generator(this.mAlbumDelegate, this.mFavoritesDelegate);
        this.mQueryFactory = new MediaItem.QueryFactory();
    }

    private static List<MediaItem> filterBestItems(List<MediaItem> list, int i) {
        Collections.sort(list, getFeatureComparator());
        List<MediaItem> subList = list.subList(0, i);
        Collections.sort(subList, getTimeComparator());
        return subList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0012 A[SYNTHETIC] */
    private List<TimelineHeadersGroup> generateGroup(List<MediaItem> list, Comparator<MediaItem> comparator) {
        TimelineHeadersGroup timelineHeadersGroup;
        LinkedList linkedList = new LinkedList();
        MediaItem mediaItem = null;
        int i = 0;
        Integer num = 1;
        TimelineHeadersGroup timelineHeadersGroup2 = null;
        for (MediaItem next : list) {
            if (mediaItem == null) {
                timelineHeadersGroup = new TimelineHeadersGroup();
                timelineHeadersGroup.start = i;
                timelineHeadersGroup.itemLocations.add(next.getLocation());
                if (!TextUtils.isEmpty(next.getLocation())) {
                    timelineHeadersGroup.validLocation = next.getLocation();
                }
            } else if (comparator.compare(next, mediaItem) == 0) {
                num = Integer.valueOf(num.intValue() + 1);
                timelineHeadersGroup2.itemLocations.add(next.getLocation());
                if (TextUtils.isEmpty(timelineHeadersGroup2.validLocation) && !TextUtils.isEmpty(next.getLocation())) {
                    timelineHeadersGroup2.validLocation = next.getLocation();
                }
                i++;
                if (i != list.size()) {
                    timelineHeadersGroup2.count = num.intValue();
                    linkedList.add(timelineHeadersGroup2);
                }
            } else {
                timelineHeadersGroup2.count = num.intValue();
                linkedList.add(timelineHeadersGroup2);
                timelineHeadersGroup = new TimelineHeadersGroup();
                timelineHeadersGroup.start = i;
                timelineHeadersGroup.itemLocations.add(next.getLocation());
                if (!TextUtils.isEmpty(next.getLocation())) {
                    timelineHeadersGroup.validLocation = next.getLocation();
                }
                num = 1;
            }
            timelineHeadersGroup2 = timelineHeadersGroup;
            mediaItem = next;
            i++;
            if (i != list.size()) {
            }
        }
        return linkedList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x016e  */
    private List<TimelineHeadersGroup> generateMonthItems(List<MediaItem> list) {
        AnonymousClass4 r18;
        LinkedList linkedList;
        char c;
        TimelineHeadersGroup timelineHeadersGroup;
        MediaManager mediaManager = this;
        List<MediaItem> list2 = list;
        AnonymousClass2 r2 = new Comparator<MediaItem>() {
            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                if (GalleryDateUtils.isSameMonth(mediaItem.getAliasSortDate(), mediaItem2.getAliasSortDate())) {
                    return 0;
                }
                return Long.compare(mediaItem2.getAliasSortTime(), mediaItem.getAliasSortTime());
            }
        };
        LinkedList<TimelineHeadersGroup> linkedList2 = new LinkedList<>();
        long currentTimeMillis = System.currentTimeMillis();
        List<TimelineHeadersGroup> generateGroup = mediaManager.generateGroup(list2, r2);
        Log.d(".provider.cache.MediaManager", "generate date groups cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        AnonymousClass3 r4 = new Comparator<MediaItem>() {
            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                return (!TextUtils.isEmpty(mediaItem.getLocation()) || !TextUtils.isEmpty(mediaItem2.getLocation())) ? MediaManager.this.locationCompare(mediaItem.getLocation(), mediaItem2.getLocation()) : mediaItem2.getAliasSortDate() - mediaItem.getAliasSortDate();
            }
        };
        long currentTimeMillis2 = System.currentTimeMillis();
        for (TimelineHeadersGroup next : generateGroup) {
            int size = linkedList2.size();
            TimelineHeadersGroup timelineHeadersGroup2 = null;
            TimelineHeadersGroup timelineHeadersGroup3 = null;
            for (TimelineHeadersGroup next2 : mediaManager.generateGroup(list2.subList(next.start, next.start + next.count), r4)) {
                TimelineHeadersGroup timelineHeadersGroup4 = linkedList2.size() > size ? (TimelineHeadersGroup) linkedList2.get(linkedList2.size() - 1) : null;
                next2.start += next.start;
                if (timelineHeadersGroup2 != null) {
                    if (!TextUtils.isEmpty(timelineHeadersGroup2.validLocation)) {
                        if (timelineHeadersGroup4 != null && mediaManager.locationCompare(timelineHeadersGroup2.validLocation, timelineHeadersGroup4.validLocation) == 0) {
                            timelineHeadersGroup4.count += timelineHeadersGroup2.count;
                            timelineHeadersGroup4.itemLocations.addAll(timelineHeadersGroup2.itemLocations);
                        } else if (timelineHeadersGroup4 == null || mediaManager.locationCompare(timelineHeadersGroup2.validLocation, next2.validLocation) == 0) {
                            next2.start = timelineHeadersGroup2.start;
                            next2.count += timelineHeadersGroup2.count;
                            next2.itemLocations.addAll(0, timelineHeadersGroup2.itemLocations);
                        }
                        timelineHeadersGroup = null;
                        if (timelineHeadersGroup != null) {
                            if ((timelineHeadersGroup4 != null ? Math.abs(list2.get((timelineHeadersGroup4.start + timelineHeadersGroup4.count) - 1).getAliasSortTime() - list2.get(timelineHeadersGroup.start).getAliasSortTime()) : Long.MAX_VALUE) <= Math.abs(list2.get((timelineHeadersGroup.start + timelineHeadersGroup.count) - 1).getAliasSortTime() - list2.get(next2.start).getAliasSortTime())) {
                                timelineHeadersGroup4.count += timelineHeadersGroup.count;
                                timelineHeadersGroup4.itemLocations.addAll(timelineHeadersGroup.itemLocations);
                                timelineHeadersGroup = null;
                            } else {
                                next2.start = timelineHeadersGroup.start;
                                next2.count += timelineHeadersGroup.count;
                                next2.itemLocations.addAll(0, timelineHeadersGroup.itemLocations);
                                timelineHeadersGroup = null;
                                if (mediaManager.isValidLocationGroup(next2)) {
                                    if (timelineHeadersGroup4 == null || mediaManager.locationCompare(next2.validLocation, timelineHeadersGroup4.validLocation) != 0) {
                                        linkedList2.add(next2);
                                    } else {
                                        timelineHeadersGroup4.count += next2.count;
                                        timelineHeadersGroup4.itemLocations.addAll(next2.itemLocations);
                                    }
                                    timelineHeadersGroup2 = timelineHeadersGroup;
                                    timelineHeadersGroup3 = timelineHeadersGroup4;
                                }
                            }
                        }
                        if (mediaManager.isValidLocationGroup(next2)) {
                        }
                    }
                    timelineHeadersGroup = timelineHeadersGroup2;
                    if (timelineHeadersGroup != null) {
                    }
                    if (mediaManager.isValidLocationGroup(next2)) {
                    }
                } else if (mediaManager.isValidLocationGroup(next2)) {
                    if (timelineHeadersGroup4 == null || mediaManager.locationCompare(next2.validLocation, timelineHeadersGroup4.validLocation) != 0) {
                        linkedList2.add(next2);
                        timelineHeadersGroup3 = timelineHeadersGroup4;
                    } else {
                        timelineHeadersGroup4.count += next2.count;
                        timelineHeadersGroup4.itemLocations.addAll(next2.itemLocations);
                        timelineHeadersGroup3 = timelineHeadersGroup4;
                    }
                }
                timelineHeadersGroup2 = next2;
                timelineHeadersGroup3 = timelineHeadersGroup4;
            }
            if (timelineHeadersGroup2 != null) {
                if (timelineHeadersGroup3 == null) {
                    linkedList2.add(timelineHeadersGroup2);
                } else if (!mediaManager.isValidLocationGroup(timelineHeadersGroup2) || mediaManager.locationCompare(timelineHeadersGroup2.validLocation, timelineHeadersGroup3.validLocation) == 0) {
                    timelineHeadersGroup3.count += timelineHeadersGroup2.count;
                    timelineHeadersGroup3.itemLocations.addAll(timelineHeadersGroup2.itemLocations);
                } else {
                    linkedList2.add(timelineHeadersGroup2);
                }
            }
        }
        Log.d(".provider.cache.MediaManager", "generate location groups cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
        long currentTimeMillis3 = System.currentTimeMillis();
        LinkedList linkedList3 = new LinkedList();
        AnonymousClass4 r42 = new Comparator<MediaItem>() {
            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                if (GalleryDateUtils.isSameDay(mediaItem.getAliasSortDate(), mediaItem2.getAliasSortDate())) {
                    return 0;
                }
                return mediaItem2.getAliasSortDate() - mediaItem.getAliasSortDate();
            }
        };
        Log.d(".provider.cache.MediaManager", "image feature initialized %s", (Object) Boolean.valueOf(ImageFeatureCacheManager.getInstance().isInitialized()));
        for (TimelineHeadersGroup timelineHeadersGroup5 : linkedList2) {
            LinkedList linkedList4 = new LinkedList();
            if (timelineHeadersGroup5.count <= 10) {
                linkedList4.addAll(list2.subList(timelineHeadersGroup5.start, timelineHeadersGroup5.start + timelineHeadersGroup5.count));
            } else if (ImageFeatureCacheManager.getInstance().isInitialized()) {
                int i = timelineHeadersGroup5.start;
                int i2 = -1;
                long j = -1;
                double d = -1.0d;
                while (i < timelineHeadersGroup5.start + timelineHeadersGroup5.count) {
                    MediaItem mediaItem = list2.get(i);
                    TimelineHeadersGroup timelineHeadersGroup6 = timelineHeadersGroup5;
                    TinyImageFeature imageFeature = ImageFeatureCacheManager.getInstance().getImageFeature(mediaItem.getId());
                    if (imageFeature == null) {
                        if (i2 != -1) {
                            linkedList4.add(list2.get(i2));
                        }
                        linkedList4.add(mediaItem);
                        i2 = -1;
                        j = -1;
                        d = -1.0d;
                    } else if (imageFeature.getClusterGroupId() != j) {
                        if (i2 != -1) {
                            linkedList4.add(list2.get(i2));
                        }
                        long clusterGroupId = imageFeature.getClusterGroupId();
                        d = imageFeature.getScore();
                        j = clusterGroupId;
                        i2 = i;
                    } else if (imageFeature.getScore() > d) {
                        d = imageFeature.getScore();
                        i2 = i;
                    }
                    i++;
                    timelineHeadersGroup5 = timelineHeadersGroup6;
                }
                TimelineHeadersGroup timelineHeadersGroup7 = timelineHeadersGroup5;
                if (i2 != -1) {
                    linkedList4.add(list2.get(i2));
                }
                timelineHeadersGroup5 = timelineHeadersGroup7;
            } else {
                linkedList4.addAll(list2.subList(timelineHeadersGroup5.start, timelineHeadersGroup5.start + timelineHeadersGroup5.count));
            }
            int size2 = linkedList3.size();
            if (linkedList4.size() > 40) {
                float size3 = 40.0f / ((float) linkedList4.size());
                Iterator<TimelineHeadersGroup> it = mediaManager.generateGroup(linkedList4, r42).iterator();
                int i3 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        linkedList = linkedList2;
                        r18 = r42;
                        break;
                    }
                    TimelineHeadersGroup next3 = it.next();
                    linkedList = linkedList2;
                    r18 = r42;
                    int ceil = (int) Math.ceil((double) (((float) next3.count) * size3));
                    if (ceil > 0) {
                        List<MediaItem> filterBestItems = filterBestItems(linkedList4.subList(next3.start, next3.start + next3.count), ceil);
                        i3 += filterBestItems.size();
                        linkedList3.addAll(filterBestItems);
                        c = '(';
                        if (i3 >= 40) {
                            for (int i4 = 0; i4 < i3 - 40; i4++) {
                                linkedList3.remove(linkedList3.size() - 1);
                            }
                        }
                    } else {
                        c = '(';
                    }
                    char c2 = c;
                    linkedList2 = linkedList;
                    r42 = r18;
                }
            } else {
                linkedList = linkedList2;
                r18 = r42;
                linkedList3.addAll(linkedList4);
            }
            int size4 = linkedList3.size();
            timelineHeadersGroup5.start = size2;
            timelineHeadersGroup5.count = size4 - size2;
            mediaManager = this;
            linkedList2 = linkedList;
            r42 = r18;
        }
        LinkedList linkedList5 = linkedList2;
        Log.d(".provider.cache.MediaManager", "filter items cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis3));
        list.clear();
        list2.addAll(linkedList3);
        return linkedList5;
    }

    private static Comparator<MediaItem> getFeatureComparator() {
        if (sFeatureComparator == null) {
            sFeatureComparator = new Comparator<MediaItem>() {
                public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                    TinyImageFeature imageFeature = ImageFeatureCacheManager.getInstance().getImageFeature(mediaItem.getId());
                    TinyImageFeature imageFeature2 = ImageFeatureCacheManager.getInstance().getImageFeature(mediaItem2.getId());
                    if (imageFeature == null && imageFeature2 != null) {
                        return 1;
                    }
                    if (imageFeature != null && imageFeature2 == null) {
                        return -1;
                    }
                    if (imageFeature == null || imageFeature2 == null) {
                        return 0;
                    }
                    return Double.compare(imageFeature2.getScore(), imageFeature.getScore());
                }
            };
        }
        return sFeatureComparator;
    }

    public static MediaManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static Comparator<MediaItem> getTimeComparator() {
        if (sTimeComparator == null) {
            sTimeComparator = new Comparator<MediaItem>() {
                public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                    return Long.compare(mediaItem2.getAliasSortTime(), mediaItem.getAliasSortTime());
                }
            };
        }
        return sTimeComparator;
    }

    private boolean isValidLocationGroup(TimelineHeadersGroup timelineHeadersGroup) {
        return timelineHeadersGroup.count >= 10 && !TextUtils.isEmpty(timelineHeadersGroup.validLocation);
    }

    /* access modifiers changed from: private */
    public int locationCompare(String str, String str2) {
        String subToCity = LocationManager.getInstance().subToCity(str);
        String subToCity2 = LocationManager.getInstance().subToCity(str2);
        if (TextUtils.isEmpty(subToCity) && TextUtils.isEmpty(subToCity2)) {
            return 0;
        }
        if (TextUtils.isEmpty(subToCity) || TextUtils.isEmpty(subToCity2)) {
            return 1;
        }
        return (subToCity.contains(subToCity2) || subToCity2.contains(subToCity)) ? 0 : 1;
    }

    private int transformOrderByColumnIndex(int i) {
        if (i == 18) {
            return 19;
        }
        if (i == 30) {
            return 31;
        }
        if (i != 36) {
            return i;
        }
        return 37;
    }

    public void addInitializeListener(InitializeListener initializeListener) {
        if (this.mIsFullLoadDone) {
            Log.d(".provider.cache.MediaManager", "no need to add listener after full load done!");
            return;
        }
        synchronized (this.mInitializeListeners) {
            this.mInitializeListeners.add(initializeListener);
        }
    }

    public void deleteAttributes(long j) {
        this.mAlbumDelegate.delete(j);
    }

    public void ensureMinimumPartDone() {
        if (!this.mIsMinimumLoadDone) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.mMinimumLoadLock) {
                if (!this.mIsMinimumLoadDone) {
                    try {
                        this.mMinimumLoadLock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d(".provider.cache.MediaManager", "wait for minimum part loading cost : %dms ", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }

    /* access modifiers changed from: protected */
    public ContentValues filterLogInfo(ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.remove("location");
        contentValues2.remove("extraGPS");
        contentValues2.remove("address");
        contentValues2.remove("exifGPSLatitude");
        contentValues2.remove("exifGPSLongitude");
        contentValues2.remove("exifGPSAltitude");
        contentValues2.remove("creatorId");
        return contentValues2;
    }

    public boolean initialized() {
        if (this.mIsFullLoadDone) {
            return this.mInitialized;
        }
        Log.d(".provider.cache.MediaManager", "not initialized, waiting lock from: %s", (Object) TextUtils.join("\n\t", Thread.currentThread().getStackTrace()));
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.mFullLoadDoneSignal.await();
            Log.d(".provider.cache.MediaManager", "wait full load done costs %d ms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (InterruptedException e) {
            Log.e(".provider.cache.MediaManager", (Throwable) e);
        }
        Log.d(".provider.cache.MediaManager", "initialization finished: %b", (Object) Boolean.valueOf(this.mInitialized));
        return this.mInitialized;
    }

    public int insert(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        int i = 0;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("cloud", this.mGenerator.getProjection(), String.format("%s AND (%s)", new Object[]{"(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType IN (1,2)", str}), strArr, (String) null, (String) null, (String) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    MediaItem mediaItem = (MediaItem) this.mGenerator.from(query);
                    synchronized (this.mModifyLock) {
                        this.mCache.add(mediaItem);
                    }
                    i++;
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
        }
        return i;
    }

    public long insert(long j, ContentValues contentValues) {
        if (!isItemDeleted(contentValues)) {
            return super.insert(j, contentValues);
        }
        return 0;
    }

    public void insertAttributes(long j, long j2) {
        Log.d(".provider.cache.MediaManager", "insert attributes[%d] for album[%d]", Long.valueOf(j2), Long.valueOf(j));
        this.mAlbumDelegate.insert(j, j2);
    }

    public void insertToFavorites(String str) {
        this.mFavoritesDelegate.insertToFavorites(str);
    }

    public boolean isItemDeleted(ContentValues contentValues) {
        Integer asInteger = contentValues.getAsInteger("localFlag");
        if (asInteger != null && (asInteger.intValue() == 11 || asInteger.intValue() == 2)) {
            Log.d(".provider.cache.MediaManager", "find a deleted local flag");
            return true;
        } else if (!contentValues.containsKey("serverStatus")) {
            return false;
        } else {
            String asString = contentValues.getAsString("serverStatus");
            if (!"deleted".equals(asString) && !"purged".equals(asString)) {
                return false;
            }
            Log.d(".provider.cache.MediaManager", "find a deleted server type");
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        return;
     */
    public synchronized void load(SQLiteOpenHelper sQLiteOpenHelper) {
        if (!this.mIsLoadStarted) {
            if (!this.mIsFullLoadDone) {
                this.mIsLoadStarted = true;
                new Thread(new InitializeTask(sQLiteOpenHelper)).start();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Cursor onCreateCursor(String[] strArr, List<MediaItem> list, String str, String str2, Bundle bundle) {
        List<TimelineHeadersGroup> list2;
        RawCursor rawCursor = new RawCursor(list, strArr, this.mQueryFactory.getMapper());
        if (bundle != null && bundle.getBoolean("extra_generate_header")) {
            Log.d(".provider.cache.MediaManager", "caller need a header data, start generate for %d", (Object) Integer.valueOf(list.size()));
            long currentTimeMillis = System.currentTimeMillis();
            int i = bundle.getInt("extra_media_group_by");
            if (i == 0) {
                int indexOf = str2.indexOf(32);
                if (indexOf <= 0) {
                    indexOf = str2.length();
                }
                String substring = str2.substring(0, indexOf);
                int index = this.mQueryFactory.getMapper().getIndex(substring);
                if (index >= 0) {
                    final int transformOrderByColumnIndex = transformOrderByColumnIndex(index);
                    list2 = generateGroup(list, new Comparator<MediaItem>() {
                        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                            if (mediaItem.columnEquals(mediaItem2, transformOrderByColumnIndex)) {
                                return 0;
                            }
                            return mediaItem2.getAliasSortDate() - mediaItem.getAliasSortDate();
                        }
                    });
                } else {
                    throw new IllegalArgumentException(substring + " not found");
                }
            } else if (i == 1) {
                list2 = generateMonthItems(list);
            } else {
                throw new IllegalArgumentException(String.format("unsupported group type %s", new Object[]{Integer.valueOf(i)}));
            }
            TimelineHeadersGroup.bindGroup(list2, rawCursor);
            Log.d(".provider.cache.MediaManager", "header[%d] generated cost %d", Integer.valueOf(i), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
        return rawCursor;
    }

    public void removeFromFavorites(String str) {
        this.mFavoritesDelegate.removeFromFavorites(str);
    }

    public int update(String str, String[] strArr, ContentValues contentValues) {
        if (isItemDeleted(contentValues)) {
            delete(str, strArr);
        }
        return super.update(str, strArr, contentValues);
    }

    public void updateAttributes(long j, long j2) {
        Log.d(".provider.cache.MediaManager", "update album[%d]'s attributes to %d", Long.valueOf(j), Long.valueOf(j2));
        this.mAlbumDelegate.update(j, j2);
    }

    public void updateAttributes(long j, long j2, boolean z, boolean z2) {
        Log.d(".provider.cache.MediaManager", "updating attributesBit[%d] for album[%d] to %s", Long.valueOf(j2), Long.valueOf(j), Boolean.valueOf(z));
        long longValue = this.mAlbumDelegate.queryAttributes(j).longValue();
        this.mAlbumDelegate.update(j, ((~AlbumManager.packageAttributeBit(j2, true, true)) & longValue) | AlbumManager.packageAttributeBit(j2, z, z2));
    }
}
