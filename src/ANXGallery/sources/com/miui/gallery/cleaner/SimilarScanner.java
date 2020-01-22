package com.miui.gallery.cleaner;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.assistant.manager.AlgorithmRequest;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.assistant.manager.result.AlgorithmResult;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.assistant.model.TinyImageFeature;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.cleaner.BaseScanner;
import com.miui.gallery.cleaner.CleanerContract;
import com.miui.gallery.cleaner.ScanResult;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.UriUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class SimilarScanner extends BaseScanner {
    private long mCameraLocalId = -1;
    /* access modifiers changed from: private */
    public long mFeatureSqlStartTime = Long.MAX_VALUE;
    private Future mFurtherLoadingTask;
    /* access modifiers changed from: private */
    public HashMap<Long, BaseScanner.MediaItem> mId2Item = new HashMap<>();
    /* access modifiers changed from: private */
    public volatile boolean mIsLoadingValid = false;
    /* access modifiers changed from: private */
    public final Object mScanLock = new Object();
    /* access modifiers changed from: private */
    public boolean mScanLockCompleted = false;
    private ArrayList<SimilarGroup> mSimilarGroupList = new ArrayList<>();

    interface OnClusterCompletedListener {
        void onAllCompleted();

        void onCompleteInBatch();
    }

    public class SimilarGroup {
        public long mGroupId;
        public Long[] mImageIdsInGroup;
        public int mStartPosIndex;

        SimilarGroup(long j, Long[] lArr) {
            this.mImageIdsInGroup = lArr;
            this.mGroupId = j;
        }
    }

    protected SimilarScanner() {
        super(3);
    }

    private void calculateImageFeatureAndCluster(List<MediaFeatureItem> list, int i, OnClusterCompletedListener onClusterCompletedListener) {
        if (this.mIsLoadingValid) {
            calculateImageFeatureAndClusterInBatch(list, 0, i, onClusterCompletedListener);
        }
    }

    /* access modifiers changed from: private */
    public void calculateImageFeatureAndClusterInBatch(List<MediaFeatureItem> list, int i, int i2, OnClusterCompletedListener onClusterCompletedListener) {
        final int i3;
        ArrayList arrayList = new ArrayList();
        if (!MiscUtil.isValid(list) || !this.mIsLoadingValid) {
            onClusterCompletedListener.onAllCompleted();
            return;
        }
        int i4 = i;
        while (true) {
            if (i >= list.size()) {
                i3 = i4;
                break;
            }
            MediaFeatureItem mediaFeatureItem = list.get(i);
            if (i < list.size() - 1 && mediaFeatureItem.isSelectionFeatureDone() && !list.get(i + 1).isSelectionFeatureDone()) {
                arrayList.add(mediaFeatureItem);
            } else if (!mediaFeatureItem.isSelectionFeatureDone() || (!arrayList.isEmpty() && DateUtils.withinTime(list.get(list.size() - 1).getDateTime(), mediaFeatureItem.getDateTime(), 3600000))) {
                arrayList.add(mediaFeatureItem);
            }
            if (arrayList.size() >= i2) {
                i3 = i;
                break;
            }
            i4 = i;
            i++;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(((MediaFeatureItem) it.next()).getImagePath())) {
                it.remove();
            }
        }
        if (!this.mIsLoadingValid || !MiscUtil.isValid(arrayList) || arrayList.size() <= 1) {
            onClusterCompletedListener.onAllCompleted();
            return;
        }
        final List<MediaFeatureItem> list2 = list;
        final int i5 = i2;
        final OnClusterCompletedListener onClusterCompletedListener2 = onClusterCompletedListener;
        ImageFeatureManager.getInstance().calculateImageFeatureAndGroupAsync(AlgorithmRequest.Priority.PRIORITY_HIGH, new ArrayList(arrayList), 5, new AlgorithmRequest.Listener() {
            public void onCancel() {
            }

            public void onComputeComplete(AlgorithmResult algorithmResult) {
            }

            public void onSaveComplete(AlgorithmResult algorithmResult) {
                if (SimilarScanner.this.mIsLoadingValid) {
                    SimilarScanner.this.calculateImageFeatureAndClusterInBatch(list2, i3, i5, onClusterCompletedListener2);
                }
                onClusterCompletedListener2.onCompleteInBatch();
            }

            public void onStart() {
            }
        });
    }

    private List<Long> getImagesIdsByGroup(long j) {
        List<TinyImageFeature> imageFeaturesByGroup = ImageFeatureCacheManager.getInstance().getImageFeaturesByGroup(j);
        Collections.sort(imageFeaturesByGroup);
        ArrayList arrayList = new ArrayList();
        for (TinyImageFeature imageId : imageFeaturesByGroup) {
            arrayList.add(Long.valueOf(imageId.getImageId()));
        }
        return arrayList;
    }

    private List<ImageFeatureCacheManager.GroupBestImage> getSimilarGroups() {
        List<ImageFeatureCacheManager.GroupBestImage> allGroups = ImageFeatureCacheManager.getInstance().getAllGroups(false);
        if (MiscUtil.isValid(allGroups)) {
            Collections.sort(allGroups, new Comparator<ImageFeatureCacheManager.GroupBestImage>() {
                public int compare(ImageFeatureCacheManager.GroupBestImage groupBestImage, ImageFeatureCacheManager.GroupBestImage groupBestImage2) {
                    TinyImageFeature bestImage = groupBestImage.getBestImage();
                    TinyImageFeature bestImage2 = groupBestImage2.getBestImage();
                    long j = 0;
                    long imageDateTime = bestImage == null ? 0 : bestImage.getImageDateTime();
                    if (bestImage2 != null) {
                        j = bestImage2.getImageDateTime();
                    }
                    int i = (imageDateTime > j ? 1 : (imageDateTime == j ? 0 : -1));
                    if (i > 0) {
                        return -1;
                    }
                    return i < 0 ? 1 : 0;
                }
            });
        }
        return allGroups;
    }

    /* access modifiers changed from: private */
    public boolean handleImagesByLimit(final int i, int i2, OnClusterCompletedListener onClusterCompletedListener) {
        List list = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, i), MediaFeatureItem.PROJECTION, String.format(Locale.US, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif')) AND localGroupId = %d AND mixedDateTime <= %s", new Object[]{Long.valueOf(this.mCameraLocalId), String.valueOf(this.mFeatureSqlStartTime)}), (String[]) null, "mixedDateTime DESC", new SafeDBUtil.QueryHandler<List<MediaFeatureItem>>() {
            public List<MediaFeatureItem> handle(Cursor cursor) {
                if (cursor != null && cursor.moveToFirst() && SimilarScanner.this.mIsLoadingValid) {
                    ArrayList arrayList = new ArrayList(cursor.getCount());
                    do {
                        arrayList.add(new MediaFeatureItem(cursor));
                    } while (cursor.moveToNext());
                    int size = arrayList.size();
                    int i = i;
                    if (size >= i) {
                        long unused = SimilarScanner.this.mFeatureSqlStartTime = ((MediaFeatureItem) arrayList.get(i - 1)).getDateTime();
                    } else {
                        long unused2 = SimilarScanner.this.mFeatureSqlStartTime = 0;
                    }
                    return arrayList;
                } else if (!SimilarScanner.this.isLoadingValid()) {
                    return null;
                } else {
                    long unused3 = SimilarScanner.this.mFeatureSqlStartTime = 0;
                    return null;
                }
            }
        });
        if (MiscUtil.isValid(list) && this.mIsLoadingValid) {
            CardUtil.bindImageFeatures(list);
        }
        if (this.mIsLoadingValid) {
            calculateImageFeatureAndCluster(list, i2, onClusterCompletedListener);
        }
        return list != null && list.size() >= i;
    }

    private void handleRemainImages() {
        this.mFurtherLoadingTask = ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
            private OnClusterCompletedListener mOnCompleteListener = new OnClusterCompletedListener() {
                public void onAllCompleted() {
                    if (SimilarScanner.this.mFeatureSqlStartTime <= 0 || !SimilarScanner.this.mIsLoadingValid) {
                        boolean unused = SimilarScanner.this.mIsLoadingValid = false;
                        SimilarScanner.this.resetSimilarGroupList();
                        SimilarScanner.this.updateScanResult(SimilarScanner.this.buildScanResult());
                        Log.d("SimilarScanner", "finish handle all images.");
                        return;
                    }
                    boolean unused2 = SimilarScanner.this.handleImagesByLimit(1000, 30, this);
                }

                public void onCompleteInBatch() {
                    if (SimilarScanner.this.mIsLoadingValid) {
                        SimilarScanner.this.resetSimilarGroupList();
                        SimilarScanner.this.updateScanResult(SimilarScanner.this.buildScanResult());
                        Log.d("SimilarScanner", "update scan result.");
                    }
                }
            };

            public Object run(ThreadPool.JobContext jobContext) {
                if (!SimilarScanner.this.mIsLoadingValid) {
                    return null;
                }
                Log.d("SimilarScanner", "handle remain images");
                boolean unused = SimilarScanner.this.handleImagesByLimit(1000, 30, this.mOnCompleteListener);
                return null;
            }
        });
    }

    private long queryCameraAlbumId(Context context) {
        return ((Long) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, new String[]{"_id"}, "serverId = 1 AND (serverType=0)", (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Long>() {
            public Long handle(Cursor cursor) {
                long j = -1;
                long j2 = (cursor == null || !cursor.moveToFirst()) ? -1 : cursor.getLong(0);
                if (j2 > 0) {
                    j = j2;
                }
                return Long.valueOf(j);
            }
        })).longValue();
    }

    private void resetIdToItemList() {
        Set<Long> keySet = this.mId2Item.keySet();
        List<Long> scanResultIds = getScanResultIds();
        ArrayList arrayList = new ArrayList();
        for (Long next : scanResultIds) {
            if (!keySet.contains(next)) {
                arrayList.add(next);
            }
        }
        if (MiscUtil.isValid(arrayList)) {
            SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Media.URI_OWNER_ALBUM_MEDIA, CleanerContract.Projection.SIMILAR_SCAN_PROJECTION, String.format(Locale.US, "%s IN (%s) AND %s = %d", new Object[]{"_id", TextUtils.join(",", arrayList), "localGroupId", Long.valueOf(this.mCameraLocalId)}), (String[]) null, (String) null, new SafeDBUtil.QueryHandler<Object>() {
                public Object handle(Cursor cursor) {
                    if (cursor == null || !cursor.moveToFirst()) {
                        return null;
                    }
                    do {
                        BaseScanner.MediaItem mediaItem = new BaseScanner.MediaItem();
                        mediaItem.mId = cursor.getLong(0);
                        mediaItem.mSha1 = cursor.getString(5);
                        mediaItem.mPath = cursor.getString(3);
                        if (TextUtils.isEmpty(mediaItem.mPath)) {
                            mediaItem.mPath = cursor.getString(2);
                        }
                        if (TextUtils.isEmpty(mediaItem.mPath)) {
                            mediaItem.mPath = cursor.getString(4);
                        }
                        mediaItem.mSize = cursor.getLong(1);
                        mediaItem.mCreateTime = cursor.getLong(6);
                        SimilarScanner.this.mId2Item.put(Long.valueOf(mediaItem.mId), mediaItem);
                    } while (cursor.moveToNext());
                    return null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void resetSimilarGroupList() {
        List<ImageFeatureCacheManager.GroupBestImage> similarGroups = getSimilarGroups();
        synchronized (this.mSimilarGroupList) {
            this.mSimilarGroupList.clear();
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (ImageFeatureCacheManager.GroupBestImage groupId : similarGroups) {
                Long valueOf = Long.valueOf(groupId.getGroupId());
                if (hashSet.add(valueOf)) {
                    List<Long> imagesIdsByGroup = getImagesIdsByGroup(valueOf.longValue());
                    Iterator<Long> it = imagesIdsByGroup.iterator();
                    while (it.hasNext()) {
                        if (!hashSet2.add(Long.valueOf(it.next().longValue()))) {
                            it.remove();
                        }
                    }
                    if (imagesIdsByGroup.size() > 1) {
                        this.mSimilarGroupList.add(new SimilarGroup(valueOf.longValue(), (Long[]) imagesIdsByGroup.toArray(new Long[imagesIdsByGroup.size()])));
                    }
                }
            }
            resetIdToItemList();
            Iterator<SimilarGroup> it2 = this.mSimilarGroupList.iterator();
            Set<Long> keySet = this.mId2Item.keySet();
            while (it2.hasNext()) {
                SimilarGroup next = it2.next();
                Long[] lArr = next.mImageIdsInGroup;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= lArr.length) {
                        break;
                    }
                    if (keySet.contains(lArr[i])) {
                        i2++;
                    } else if (next.mImageIdsInGroup.length <= 2) {
                        it2.remove();
                        break;
                    } else {
                        Long[] lArr2 = new Long[(next.mImageIdsInGroup.length - 1)];
                        System.arraycopy(next.mImageIdsInGroup, 0, lArr2, 0, i2);
                        if (i2 < next.mImageIdsInGroup.length - 1) {
                            System.arraycopy(next.mImageIdsInGroup, i2 + 1, lArr2, i2, (next.mImageIdsInGroup.length - i2) - 1);
                        }
                        next.mImageIdsInGroup = lArr2;
                    }
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ScanResult buildScanResult() {
        long j;
        int i;
        ScanResult.ResultImage[] resultImageArr = new ScanResult.ResultImage[4];
        synchronized (this.mSimilarGroupList) {
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            j = 0;
            i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                SimilarGroup next = it.next();
                Long[] lArr = next.mImageIdsInGroup;
                next.mStartPosIndex = i2;
                i2 += lArr.length;
                int i3 = i;
                long j2 = j;
                for (Long l : lArr) {
                    BaseScanner.MediaItem mediaItem = this.mId2Item.get(l);
                    if (i3 < resultImageArr.length) {
                        resultImageArr[i3] = new ScanResult.ResultImage(mediaItem.mId, mediaItem.mPath);
                    }
                    i3++;
                    j2 += mediaItem.mSize;
                }
                j = j2;
                i = i3;
            }
        }
        return new ScanResult.Builder().setType(this.mType).setImages(resultImageArr).setSize(j).setCount(i).setOnScanResultClickListener(this.mOnScanResultClickListener).build();
    }

    public ArrayList<Long> getClusterGroupId() {
        ArrayList<Long> arrayList;
        synchronized (this.mSimilarGroupList) {
            arrayList = new ArrayList<>(this.mSimilarGroupList.size());
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            while (it.hasNext()) {
                arrayList.add(Long.valueOf(it.next().mGroupId));
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> getGroupItemCount() {
        ArrayList<Integer> arrayList;
        synchronized (this.mSimilarGroupList) {
            arrayList = new ArrayList<>(this.mSimilarGroupList.size());
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().mImageIdsInGroup.length));
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> getGroupStartPos() {
        ArrayList<Integer> arrayList;
        synchronized (this.mSimilarGroupList) {
            arrayList = new ArrayList<>(this.mSimilarGroupList.size());
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().mStartPosIndex));
            }
        }
        return arrayList;
    }

    public List<Long> getScanResultIds() {
        ArrayList arrayList;
        synchronized (this.mSimilarGroupList) {
            arrayList = new ArrayList();
            int size = this.mSimilarGroupList.size();
            for (int i = 0; i < size; i++) {
                Long[] lArr = this.mSimilarGroupList.get(i).mImageIdsInGroup;
                for (Long add : lArr) {
                    arrayList.add(add);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return null;
    }

    public boolean isLoadingValid() {
        return this.mIsLoadingValid;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        synchronized (this.mSimilarGroupList) {
            this.mSimilarGroupList.clear();
            this.mId2Item.clear();
            this.mFeatureSqlStartTime = Long.MAX_VALUE;
            this.mIsLoadingValid = false;
            this.mScanLockCompleted = false;
            if (this.mFurtherLoadingTask != null) {
                this.mFurtherLoadingTask.cancel();
                this.mFurtherLoadingTask = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void recordClickScanResultEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", String.valueOf(this.mMediaItems.size()));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_result_similar_click", hashMap);
    }

    public boolean removeItem(long j) {
        boolean z;
        synchronized (this.mSimilarGroupList) {
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            z = false;
            while (it.hasNext()) {
                SimilarGroup next = it.next();
                Long[] lArr = next.mImageIdsInGroup;
                int i = 0;
                while (true) {
                    if (i >= lArr.length) {
                        break;
                    } else if (lArr[i].longValue() == j) {
                        if (lArr.length == 1) {
                            it.remove();
                        } else {
                            Long[] lArr2 = new Long[(lArr.length - 1)];
                            System.arraycopy(lArr, 0, lArr2, 0, i);
                            if (i < lArr.length - 1) {
                                System.arraycopy(lArr, i + 1, lArr2, i, (lArr.length - i) - 1);
                            }
                            next.mImageIdsInGroup = lArr2;
                        }
                        z = true;
                        continue;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    public void removeSingleItemGroups() {
        boolean z;
        synchronized (this.mSimilarGroupList) {
            Iterator<SimilarGroup> it = this.mSimilarGroupList.iterator();
            z = false;
            while (it.hasNext()) {
                if (it.next().mImageIdsInGroup.length <= 1) {
                    it.remove();
                    z = true;
                }
            }
        }
        if (z) {
            updateScanResult(buildScanResult());
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        	at java.util.ArrayList.get(ArrayList.java:433)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:210)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public com.miui.gallery.cleaner.ScanResult scan() {
        /*
            r5 = this;
            long r0 = r5.mCameraLocalId
            r2 = -1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x001a
            android.content.Context r0 = com.miui.gallery.GalleryApp.sGetAndroidContext()
            long r0 = r5.queryCameraAlbumId(r0)
            r5.mCameraLocalId = r0
            long r0 = r5.mCameraLocalId
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x001a
            r0 = 0
            return r0
        L_0x001a:
            r0 = 1
            r5.mIsLoadingValid = r0
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r5.mFeatureSqlStartTime = r0
        L_0x0024:
            r5.resetSimilarGroupList()
            java.util.ArrayList<com.miui.gallery.cleaner.SimilarScanner$SimilarGroup> r0 = r5.mSimilarGroupList
            int r0 = r0.size()
            if (r0 > 0) goto L_0x0068
            com.miui.gallery.assistant.manager.ImageFeatureManager r0 = com.miui.gallery.assistant.manager.ImageFeatureManager.getInstance()
            boolean r0 = r0.isNewImageCalculationEnable()
            if (r0 == 0) goto L_0x0068
            r0 = 0
            r5.mScanLockCompleted = r0
            com.miui.gallery.cleaner.SimilarScanner$1 r0 = new com.miui.gallery.cleaner.SimilarScanner$1
            r0.<init>()
            r1 = 30
            r5.handleImagesByLimit(r1, r1, r0)
            java.lang.Object r0 = r5.mScanLock
            monitor-enter(r0)
        L_0x0049:
            boolean r1 = r5.mScanLockCompleted     // Catch:{ Exception -> 0x0060 }
            if (r1 != 0) goto L_0x0064
            boolean r1 = r5.mIsLoadingValid     // Catch:{ Exception -> 0x0060 }
            if (r1 == 0) goto L_0x0064
            java.lang.String r1 = "SimilarScanner"
            java.lang.String r2 = "lock for similar cluster result"
            com.miui.gallery.util.Log.d((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0060 }
            java.lang.Object r1 = r5.mScanLock     // Catch:{ Exception -> 0x0060 }
            r1.wait()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0049
        L_0x005e:
            r1 = move-exception
            goto L_0x0066
        L_0x0060:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x005e }
        L_0x0064:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            goto L_0x0068
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r1
        L_0x0068:
            java.util.ArrayList<com.miui.gallery.cleaner.SimilarScanner$SimilarGroup> r0 = r5.mSimilarGroupList
            int r0 = r0.size()
            r1 = 0
            if (r0 > 0) goto L_0x0086
            long r3 = r5.mFeatureSqlStartTime
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0086
            boolean r0 = r5.mIsLoadingValid
            if (r0 == 0) goto L_0x0086
            com.miui.gallery.assistant.manager.ImageFeatureManager r0 = com.miui.gallery.assistant.manager.ImageFeatureManager.getInstance()
            boolean r0 = r0.isNewImageCalculationEnable()
            if (r0 != 0) goto L_0x0024
        L_0x0086:
            boolean r0 = r5.mIsLoadingValid
            if (r0 == 0) goto L_0x009d
            long r3 = r5.mFeatureSqlStartTime
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x009d
            com.miui.gallery.assistant.manager.ImageFeatureManager r0 = com.miui.gallery.assistant.manager.ImageFeatureManager.getInstance()
            boolean r0 = r0.isNewImageCalculationEnable()
            if (r0 == 0) goto L_0x009d
            r5.handleRemainImages()
        L_0x009d:
            java.lang.String r0 = "SimilarScanner"
            java.lang.String r1 = "scan finish."
            com.miui.gallery.util.Log.d((java.lang.String) r0, (java.lang.String) r1)
            com.miui.gallery.cleaner.ScanResult r0 = r5.buildScanResult()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.cleaner.SimilarScanner.scan():com.miui.gallery.cleaner.ScanResult");
    }
}
