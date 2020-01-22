package com.miui.gallery.assistant.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.assistant.model.TinyImageFeature;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageFeatureCacheManager {
    private Map<Long, GroupBestImage> mGroupBestMap;
    private Map<Long, TinyImageFeature> mImageTinyFeatureMap;
    private volatile boolean mInitialized;
    private volatile boolean mIsFirstShowImageSelection;

    public static class GroupBestImage {
        TinyImageFeature mBestImage;
        volatile long mGroupId;
        AtomicInteger mImageCount;
        TreeSet<TinyImageFeature> mImageFeatureInGroup = new TreeSet<>();

        public GroupBestImage(long j, int i, TinyImageFeature tinyImageFeature) {
            this.mGroupId = j;
            this.mImageCount = new AtomicInteger(i);
            if (tinyImageFeature != null) {
                this.mImageFeatureInGroup.add(tinyImageFeature);
                if (!tinyImageFeature.isPoorImage()) {
                    this.mBestImage = tinyImageFeature;
                }
            }
        }

        public void clear() {
            AtomicInteger atomicInteger = this.mImageCount;
            atomicInteger.compareAndSet(atomicInteger.get(), 0);
            this.mBestImage = null;
            this.mImageFeatureInGroup.clear();
        }

        public TinyImageFeature getBestImage() {
            return this.mBestImage;
        }

        public long getGroupId() {
            return this.mGroupId;
        }

        public int getImageCount() {
            return this.mImageCount.get();
        }

        public TreeSet<TinyImageFeature> getImageFeatureInGroup() {
            return this.mImageFeatureInGroup;
        }

        public int increase() {
            return this.mImageCount.incrementAndGet();
        }

        public void tryReplace(TinyImageFeature tinyImageFeature) {
            if (tinyImageFeature != null) {
                this.mImageFeatureInGroup.add(tinyImageFeature);
                if (!tinyImageFeature.isPoorImage()) {
                    TinyImageFeature tinyImageFeature2 = this.mBestImage;
                    if (tinyImageFeature2 == null || tinyImageFeature2.getScore() < tinyImageFeature.getScore() || this.mBestImage.getImageId() == tinyImageFeature.getImageId()) {
                        this.mBestImage = tinyImageFeature;
                    }
                }
            }
        }
    }

    private static final class ImageFeatureManagerHolder {
        /* access modifiers changed from: private */
        public static final ImageFeatureCacheManager S_IMAGE_FEATURE_CACHE_MANAGER = new ImageFeatureCacheManager();
    }

    private ImageFeatureCacheManager() {
        this.mInitialized = false;
        this.mIsFirstShowImageSelection = false;
    }

    private void addImageFeature(TinyImageFeature tinyImageFeature) {
        if (this.mInitialized && tinyImageFeature != null) {
            refreshGroupBestMap(tinyImageFeature, !this.mImageTinyFeatureMap.containsKey(Long.valueOf(tinyImageFeature.getImageId())));
            this.mImageTinyFeatureMap.put(Long.valueOf(tinyImageFeature.getImageId()), tinyImageFeature);
        }
    }

    private void fillTinyFeaturesFromCursor(List<TinyImageFeature> list, Cursor cursor) {
        if (list != null && cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    list.add(new TinyImageFeature(cursor));
                } finally {
                    cursor.close();
                }
            }
        }
    }

    private synchronized List<TinyImageFeature> getAllImageFeatures() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        fillTinyFeaturesFromCursor(arrayList, GalleryEntityManager.getInstance().rawQuery(ImageFeature.class, TinyImageFeature.PROJECTION, ImageFeature.ALL_IQA_CLUSTER_SELECTION, (String[]) null, (String) null, (String) null, "imageId ASC", (String) null));
        return arrayList;
    }

    private synchronized List<TinyImageFeature> getImageFeaturesById(List<Long> list) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (MiscUtil.isValid(list)) {
            fillTinyFeaturesFromCursor(arrayList, GalleryEntityManager.getInstance().rawQuery(ImageFeature.class, TinyImageFeature.PROJECTION, ImageFeature.ALL_IQA_CLUSTER_SELECTION + " AND " + String.format("%s IN (%s)", new Object[]{"imageId", TextUtils.join(",", list)}), (String[]) null, (String) null, (String) null, "imageId ASC", (String) null));
        }
        return arrayList;
    }

    public static ImageFeatureCacheManager getInstance() {
        return ImageFeatureManagerHolder.S_IMAGE_FEATURE_CACHE_MANAGER;
    }

    private synchronized void initGroupBestMap() {
        Collection<TinyImageFeature> values = this.mImageTinyFeatureMap.values();
        if (MiscUtil.isValid(values)) {
            for (TinyImageFeature refreshGroupBestMap : values) {
                refreshGroupBestMap(refreshGroupBestMap, true);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        return;
     */
    private synchronized void refreshGroupBestMap(TinyImageFeature tinyImageFeature, boolean z) {
        if (tinyImageFeature != null) {
            if (this.mGroupBestMap.containsKey(Long.valueOf(tinyImageFeature.getClusterGroupId()))) {
                GroupBestImage groupBestImage = this.mGroupBestMap.get(Long.valueOf(tinyImageFeature.getClusterGroupId()));
                if (z) {
                    groupBestImage.increase();
                }
                groupBestImage.tryReplace(tinyImageFeature);
            } else {
                this.mGroupBestMap.put(Long.valueOf(tinyImageFeature.getClusterGroupId()), new GroupBestImage(tinyImageFeature.getClusterGroupId(), 1, tinyImageFeature));
            }
        }
    }

    private synchronized void refreshGroupBestMapbyGroupId(long j) {
        GroupBestImage groupBestImage = this.mGroupBestMap.get(Long.valueOf(j));
        if (groupBestImage != null) {
            if (groupBestImage.mImageCount.get() == 1) {
                this.mGroupBestMap.remove(Long.valueOf(j));
            } else {
                groupBestImage.clear();
                List<TinyImageFeature> imageFeaturesByGroup = getImageFeaturesByGroup(j);
                if (MiscUtil.isValid(imageFeaturesByGroup)) {
                    for (TinyImageFeature refreshGroupBestMap : imageFeaturesByGroup) {
                        refreshGroupBestMap(refreshGroupBestMap, true);
                    }
                }
            }
        }
    }

    public List<GroupBestImage> getAllGroups(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (this.mInitialized) {
            for (Map.Entry<Long, GroupBestImage> value : this.mGroupBestMap.entrySet()) {
                GroupBestImage groupBestImage = (GroupBestImage) value.getValue();
                if (groupBestImage != null && ((z || groupBestImage.getImageCount() > 1) && groupBestImage.mBestImage != null)) {
                    arrayList.add(groupBestImage);
                }
            }
        }
        return arrayList;
    }

    public int getBestImageCount(boolean z) {
        int i = 0;
        if (this.mInitialized) {
            for (Map.Entry<Long, GroupBestImage> value : this.mGroupBestMap.entrySet()) {
                GroupBestImage groupBestImage = (GroupBestImage) value.getValue();
                if (groupBestImage != null && ((z || groupBestImage.mImageCount.get() > 1) && groupBestImage.mBestImage != null)) {
                    i++;
                }
            }
        }
        return i;
    }

    public TinyImageFeature getImageFeature(long j) {
        if (this.mInitialized) {
            return this.mImageTinyFeatureMap.get(Long.valueOf(j));
        }
        return null;
    }

    public List<TinyImageFeature> getImageFeaturesByGroup(long j) {
        if (!this.mInitialized) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (TinyImageFeature next : this.mImageTinyFeatureMap.values()) {
            if (next.getClusterGroupId() == j) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public synchronized void init() {
        if (!this.mInitialized) {
            long currentTimeMillis = System.currentTimeMillis();
            List<TinyImageFeature> allImageFeatures = getAllImageFeatures();
            int i = 16;
            this.mImageTinyFeatureMap = new ConcurrentHashMap(allImageFeatures != null ? allImageFeatures.size() : 16);
            if (allImageFeatures != null) {
                i = allImageFeatures.size() / 2;
            }
            this.mGroupBestMap = new ConcurrentHashMap(i);
            if (MiscUtil.isValid(allImageFeatures)) {
                for (TinyImageFeature next : allImageFeatures) {
                    this.mImageTinyFeatureMap.put(Long.valueOf(next.getImageId()), next);
                }
                allImageFeatures.clear();
            }
            initGroupBestMap();
            this.mIsFirstShowImageSelection = GalleryPreferences.Assistant.isFirstShowImageSelection();
            this.mInitialized = true;
            Log.d("ImageFeatureCacheManager", "Initialize use time: " + (System.currentTimeMillis() - currentTimeMillis) + "ms.");
            Log.d("ImageFeatureCacheManager", "ImageFeature count: %d ; Cluster group count: %d ", Integer.valueOf(this.mImageTinyFeatureMap.size()), Integer.valueOf(this.mGroupBestMap.size()));
        }
    }

    public boolean isBestImage(long j, boolean z, boolean z2, List<Long> list) {
        GroupBestImage groupBestImage;
        boolean z3;
        TinyImageFeature imageFeature = getImageFeature(j);
        if (!this.mInitialized || imageFeature == null || (groupBestImage = this.mGroupBestMap.get(Long.valueOf(imageFeature.getClusterGroupId()))) == null) {
            return false;
        }
        TinyImageFeature tinyImageFeature = groupBestImage.mBestImage;
        TreeSet<TinyImageFeature> imageFeatureInGroup = groupBestImage.getImageFeatureInGroup();
        if (list != null) {
            Iterator<TinyImageFeature> it = imageFeatureInGroup.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!list.contains(Long.valueOf(it.next().getImageId()))) {
                        break;
                    }
                } else {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (tinyImageFeature == null && z2) {
            tinyImageFeature = imageFeatureInGroup.first();
        }
        return (z || groupBestImage.getImageCount() > 1) && tinyImageFeature != null && tinyImageFeature.getImageId() == j && !z3;
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006d, code lost:
        return false;
     */
    public synchronized boolean onImageDelete(long j) {
        TinyImageFeature imageFeature = getImageFeature(j);
        if (this.mInitialized && imageFeature != null && imageFeature.getClusterGroupId() > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mImageTinyFeatureMap.remove(Long.valueOf(j));
            ContentValues contentValues = new ContentValues();
            contentValues.put("imageType", 2);
            GalleryEntityManager.getInstance().update(ImageFeature.class, contentValues, String.format("%s = %s", new Object[]{"imageId", Long.valueOf(j)}), (String[]) null);
            refreshGroupBestMapbyGroupId(imageFeature.getClusterGroupId());
            Log.d("ImageFeatureCacheManager", "Delete image %d using %d ms!", Long.valueOf(j), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0094, code lost:
        return false;
     */
    public synchronized boolean onImageDeleteBatch(List<Long> list) {
        if (this.mInitialized) {
            if (MiscUtil.isValid(list)) {
                long currentTimeMillis = System.currentTimeMillis();
                ArrayList arrayList = new ArrayList();
                for (Long next : list) {
                    TinyImageFeature imageFeature = getImageFeature(next.longValue());
                    if (imageFeature != null && imageFeature.getClusterGroupId() > 0) {
                        arrayList.add(next);
                        this.mImageTinyFeatureMap.remove(next);
                        refreshGroupBestMapbyGroupId(imageFeature.getClusterGroupId());
                    }
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("imageType", 2);
                GalleryEntityManager.getInstance().update(ImageFeature.class, contentValues, String.format("%s IN (%s)", new Object[]{"imageId", TextUtils.join(",", arrayList)}), (String[]) null);
                Log.d("ImageFeatureCacheManager", "Delete %d images batch using %d ms!", Integer.valueOf(list.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                return true;
            }
        }
    }

    public void onImageFeaturesChanged(List<Long> list) {
        List<TinyImageFeature> imageFeaturesById = getImageFeaturesById(list);
        if (MiscUtil.isValid(imageFeaturesById)) {
            for (TinyImageFeature addImageFeature : imageFeaturesById) {
                addImageFeature(addImageFeature);
            }
        }
    }

    public void setFirstShowImageSelection(boolean z) {
        this.mIsFirstShowImageSelection = z;
    }

    public boolean shouldShowImageSelectionTip() {
        return this.mInitialized && this.mIsFirstShowImageSelection && this.mImageTinyFeatureMap.size() > 0;
    }

    public boolean shouldShowSelectionStar(long j, boolean z, boolean z2) {
        return ImageFeatureManager.isImageFeatureSelectionVisiable() && isBestImage(j, z, z2, (List<Long>) null);
    }

    public boolean shouldShowSelectionStar(long j, boolean z, boolean z2, List<Long> list) {
        return ImageFeatureManager.isImageFeatureSelectionVisiable() && isBestImage(j, z, z2, list);
    }
}
