package com.miui.gallery.assistant.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.algorithm.Algorithm;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.assistant.library.LibraryConstants;
import com.miui.gallery.assistant.library.LibraryManager;
import com.miui.gallery.assistant.manager.AlgorithmRequest;
import com.miui.gallery.assistant.manager.request.BatchImageAlgorithmRequest;
import com.miui.gallery.assistant.manager.request.ClusterGroupRequest;
import com.miui.gallery.assistant.manager.request.param.ClusteGroupRequestParams;
import com.miui.gallery.assistant.manager.request.param.ImageFeatureBitmapRequestParams;
import com.miui.gallery.assistant.manager.result.AlgorithmResult;
import com.miui.gallery.assistant.manager.result.BatchAlgorithmResult;
import com.miui.gallery.assistant.manager.result.ClusteGroupResult;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.assistant.process.BaseImageTask;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.ScenarioConstants;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.PhotoMovieEntranceUtils;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.assistant.FlagUtil;
import com.miui.os.Rom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ImageFeatureManager {
    private static boolean sIsSupportImageFeatureSelection;
    private static final String[] sWhiteList = {"dipper", "ursa", "jason", "wayne", "platina", "chiron", "sagit", "polaris", "perseus", "equuleus", "sirius", "comet", "lavender", "capricorn", "lithium", "natrium", "scorpio", "gemini", "cepheus", "davinci", "davinciin", "grus", "raphael", "raphaelin", "pyxis", "vela", "crux", "begonia", "begoniain", "beryllium", "tucana", "ginkgo", "violet", "laurus", "phoenix", "phoenixin", "andromeda", "cmi", "umi", "lmi", "draco", "picasso"};
    /* access modifiers changed from: private */
    public volatile boolean mHasMoreImageToProcess;
    private final Runnable mImageDeleteRunnable;
    private final ImageFeatureReusedBitCache mImageFeatureReusedBitCache;
    private volatile boolean mIsCaculating;
    private final ThreadPoolExecutor mThreadPoolExecutor;

    private static final class ImageFeatureMangerHolder {
        /* access modifiers changed from: private */
        public static final ImageFeatureManager INSTANCE = new ImageFeatureManager();
    }

    static {
        sIsSupportImageFeatureSelection = false;
        for (String equalsIgnoreCase : sWhiteList) {
            if (equalsIgnoreCase.equalsIgnoreCase(Build.DEVICE)) {
                sIsSupportImageFeatureSelection = true;
            }
        }
    }

    private ImageFeatureManager() {
        this.mHasMoreImageToProcess = false;
        this.mImageFeatureReusedBitCache = new ImageFeatureReusedBitCache();
        this.mThreadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.mThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.mIsCaculating = false;
        this.mImageDeleteRunnable = new Runnable() {
            public void run() {
                ImageFeatureManager.this.deleteImageFromCard();
            }
        };
    }

    private Runnable createImagesDeleteRunnable() {
        return new Runnable() {
            public void run() {
                if (ImageFeatureManager.isDeviceSupportStoryFunction()) {
                    ImageFeatureManager.this.deleteImagesFromCard();
                }
            }
        };
    }

    private Runnable createNewImageCalculateRunnable() {
        return new Runnable() {
            public void run() {
                ImageFeatureManager.this.scheduleNewImages();
            }
        };
    }

    /* access modifiers changed from: private */
    public void deleteImageFromCard() {
        this.mThreadPoolExecutor.execute(createImagesDeleteRunnable());
    }

    /* access modifiers changed from: private */
    public synchronized void deleteImagesFromCard() {
        List<ImageFeature> query = GalleryEntityManager.getInstance().query(ImageFeature.class, "imageId>0 AND version=2 AND imageType=2", (String[]) null, (String) null, (String) null);
        if (MiscUtil.isValid(query)) {
            Log.d("ImageFeatureManager", "Delete %d images,search them in cards", (Object) Integer.valueOf(query.size()));
            ArrayList arrayList = new ArrayList(query.size());
            for (ImageFeature sha1 : query) {
                arrayList.add(sha1.getSha1());
            }
            if (MiscUtil.isValid(arrayList)) {
                CardManager.getInstance().onDeleteImages(arrayList);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("imageType", 3);
            GalleryEntityManager.getInstance().update(ImageFeature.class, contentValues, String.format("%s IN ('%s')", new Object[]{"sha1", TextUtils.join("','", arrayList)}), (String[]) null);
        }
    }

    private boolean ensureImagePath(MediaFeatureItem mediaFeatureItem) {
        String str;
        String imagePath = mediaFeatureItem.getImagePath();
        if (TextUtils.isEmpty(imagePath)) {
            long currentTimeMillis = System.currentTimeMillis();
            str = BaseImageTask.getImageFilePath(mediaFeatureItem, DownloadType.MICRO, true).get();
            Log.d("ImageFeatureManager", "get micro thumb image using time:%d ms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } else {
            str = imagePath;
        }
        return !TextUtils.isEmpty(str);
    }

    public static List<MediaFeatureItem> filterNearByImages(List<MediaFeatureItem> list) {
        LinkedList linkedList = new LinkedList();
        if (MiscUtil.isValid(list)) {
            long j = 0;
            for (MediaFeatureItem next : list) {
                ImageFeature imageFeature = next.getImageFeature();
                if (imageFeature != null) {
                    long clusterGroupId = imageFeature.getClusterGroupId();
                    if (clusterGroupId != 0) {
                        if (j != 0) {
                            if (j != clusterGroupId) {
                                break;
                            }
                            linkedList.add(next);
                        } else {
                            linkedList.add(next);
                            j = clusterGroupId;
                        }
                    } else {
                        linkedList.add(next);
                    }
                } else {
                    linkedList.add(next);
                }
            }
        }
        return linkedList;
    }

    public static ImageFeatureManager getInstance() {
        return ImageFeatureMangerHolder.INSTANCE;
    }

    private List<MediaFeatureItem> getUnProcessedImages(List<MediaFeatureItem> list, int i) {
        ArrayList arrayList = new ArrayList();
        if (MiscUtil.isValid(list)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                MediaFeatureItem mediaFeatureItem = list.get(i2);
                if (i2 < list.size() - 1 && mediaFeatureItem.isSelectionFeatureDone() && !list.get(i2 + 1).isSelectionFeatureDone()) {
                    arrayList.add(mediaFeatureItem);
                } else if (!mediaFeatureItem.isSelectionFeatureDone() || (!arrayList.isEmpty() && DateUtils.withinTime(((MediaFeatureItem) arrayList.get(arrayList.size() - 1)).getDateTime(), mediaFeatureItem.getDateTime(), 3600000))) {
                    arrayList.add(mediaFeatureItem);
                }
                if (arrayList.size() >= i) {
                    break;
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!ensureImagePath((MediaFeatureItem) it.next())) {
                it.remove();
            }
        }
        return arrayList;
    }

    public static boolean isDeviceSupportImageFeatureSelection() {
        return sIsSupportImageFeatureSelection && !BuildUtil.isPad() && Rom.IS_MIUI;
    }

    public static boolean isDeviceSupportStoryFunction() {
        return isImageFeatureCalculationEnable() && !BuildUtil.isInternational() && !BuildUtil.isPad();
    }

    public static boolean isImageFeatureCalculationEnable() {
        return isDeviceSupportImageFeatureSelection();
    }

    public static boolean isImageFeatureSelectionVisiable() {
        return isImageFeatureCalculationEnable() && GalleryPreferences.Assistant.isImageSelectionFunctionOn();
    }

    public static boolean isStoryGenerateEnable() {
        return isDeviceSupportStoryFunction() && GalleryPreferences.Assistant.isStoryFunctionOn() && PhotoMovieEntranceUtils.isPhotoMovieAvailable();
    }

    /* access modifiers changed from: private */
    public boolean markCaculateState(boolean z) {
        Log.d("ImageFeatureManager", "New image Caculating : %b", (Object) Boolean.valueOf(z));
        this.mIsCaculating = z;
        return this.mIsCaculating;
    }

    public static List<MediaFeatureItem> queryNearByMediaItems(long j) {
        return (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, MediaFeatureItem.PROJECTION, String.format("%s < %s AND %s > %s", new Object[]{"mixedDateTime", Long.valueOf(j), "mixedDateTime", Long.valueOf(j - 3600000)}), (String[]) null, "mixedDateTime DESC", new SafeDBUtil.QueryHandler<List<MediaFeatureItem>>() {
            public List<MediaFeatureItem> handle(Cursor cursor) {
                return MediaFeatureItem.getMediaFeatureItemsFromCursor(cursor);
            }
        });
    }

    /* access modifiers changed from: private */
    public void scheduleNewImages() {
        boolean z = false;
        if (MiscUtil.isAppProcessInForeground() && GalleryPreferences.Sync.getPowerCanSync()) {
            Log.d("ImageFeatureManager", "Status meet,trigger new image feature calculation");
            AnonymousClass4 r2 = new AlgorithmRequest.Listener() {
                public void onCancel() {
                    boolean unused = ImageFeatureManager.this.markCaculateState(false);
                }

                public void onComputeComplete(AlgorithmResult algorithmResult) {
                }

                public void onSaveComplete(AlgorithmResult algorithmResult) {
                    boolean unused = ImageFeatureManager.this.markCaculateState(false);
                    if (ImageFeatureManager.this.mHasMoreImageToProcess && algorithmResult != null && algorithmResult.getResultCode() == 0) {
                        boolean unused2 = ImageFeatureManager.this.mHasMoreImageToProcess = false;
                        ImageFeatureManager.this.triggerNewImageCalculation();
                    }
                }

                public void onStart() {
                }
            };
            Log.d("ImageFeatureManager", "Start process recent %d images for image selection", (Object) 200);
            List list = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, 200), MediaFeatureItem.PROJECTION, ScenarioConstants.IMAGE_FEATURE_SELECTION, (String[]) null, "mixedDateTime DESC", new SafeDBUtil.QueryHandler<List<MediaFeatureItem>>() {
                public List<MediaFeatureItem> handle(Cursor cursor) {
                    if (cursor == null || !cursor.moveToFirst()) {
                        return null;
                    }
                    ArrayList arrayList = new ArrayList(cursor.getCount());
                    do {
                        arrayList.add(new MediaFeatureItem(cursor));
                    } while (cursor.moveToNext());
                    return arrayList;
                }
            });
            if (MiscUtil.isValid(list)) {
                CardUtil.bindImageFeatures(list);
                List<MediaFeatureItem> unProcessedImages = getUnProcessedImages(list, 30);
                if (MiscUtil.isValid(unProcessedImages)) {
                    if (unProcessedImages.size() >= 30) {
                        z = true;
                    }
                    this.mHasMoreImageToProcess = z;
                    calculateImageFeatureAndGroupAsync(AlgorithmRequest.Priority.PRIORITY_NORMAL, unProcessedImages, Algorithm.FLAG_FEATURE_ALL, r2);
                    return;
                }
            }
        }
        markCaculateState(false);
    }

    public void calculateClusterGroupAsync(AlgorithmRequest.Priority priority, List<MediaFeatureItem> list, boolean z, boolean z2, AlgorithmRequest.Listener<ClusteGroupResult> listener) {
        if (MiscUtil.isValid(list)) {
            Log.d("ImageFeatureManager", "calculateClusterGroupAsync,image count:%d", (Object) Integer.valueOf(list.size()));
            ClusterGroupRequest clusterGroupRequest = new ClusterGroupRequest(priority, new ClusteGroupRequestParams(list, z), z2);
            clusterGroupRequest.setMainThreadListener(listener);
            clusterGroupRequest.execute();
        }
    }

    public ClusteGroupResult calculateClusterGroupSync(List<MediaFeatureItem> list, boolean z, boolean z2) {
        if (!MiscUtil.isValid(list)) {
            return new ClusteGroupResult(3);
        }
        Log.d("ImageFeatureManager", "calculateClusterGroupSync,image count:%d", (Object) Integer.valueOf(list.size()));
        return (ClusteGroupResult) new ClusterGroupRequest(AlgorithmRequest.Priority.PRIORITY_NORMAL, new ClusteGroupRequestParams(list, z), z2).executeSync();
    }

    public void calculateImageFeatureAndGroupAsync(AlgorithmRequest.Priority priority, List<MediaFeatureItem> list, int i, AlgorithmRequest.Listener listener) {
        int i2;
        if (MiscUtil.isValid(list)) {
            Log.d("ImageFeatureManager", "Calculate imageGroup with algorithm async,image count:%d", (Object) Integer.valueOf(list.size()));
            for (MediaFeatureItem next : list) {
                if (next.getImageFeature() == null) {
                    i2 = i;
                    int i3 = i2;
                } else {
                    int i4 = 0;
                    for (int i5 : Algorithm.FLAG_FEATURE_ALL_ARRAY) {
                        if (FlagUtil.hasFlag(i, i5) && !next.getImageFeature().isFeatureDone(i5)) {
                            i4 = FlagUtil.setFlag(i4, i5);
                        }
                    }
                    int i6 = i;
                    i2 = i4;
                }
                if (i2 != 0) {
                    handleImageWithAlgorithmAsync(priority, next, true, i2, (AlgorithmRequest.Listener<BatchAlgorithmResult>) null);
                }
            }
            calculateClusterGroupAsync(priority, list, true, true, listener);
        } else if (listener != null) {
            listener.onCancel();
        }
    }

    public Bitmap getReusedBitMap(BitmapFactory.Options options) {
        Bitmap bitmap = this.mImageFeatureReusedBitCache.get(options);
        StringBuilder sb = new StringBuilder();
        sb.append("get reused bitmap :");
        sb.append(bitmap != null);
        Log.d("ImageFeatureManager", sb.toString());
        return bitmap;
    }

    public void handleImageWithAlgorithmAsync(AlgorithmRequest.Priority priority, MediaFeatureItem mediaFeatureItem, boolean z, int i, AlgorithmRequest.Listener<BatchAlgorithmResult> listener) {
        BatchImageAlgorithmRequest batchImageAlgorithmRequest = new BatchImageAlgorithmRequest(priority, new ImageFeatureBitmapRequestParams(mediaFeatureItem, z), i);
        batchImageAlgorithmRequest.setMainThreadListener(listener);
        batchImageAlgorithmRequest.execute();
    }

    public BatchAlgorithmResult handleImageWithAlgorithmSync(AlgorithmRequest.Priority priority, MediaFeatureItem mediaFeatureItem, boolean z, int i) {
        return (BatchAlgorithmResult) new BatchImageAlgorithmRequest(priority, new ImageFeatureBitmapRequestParams(mediaFeatureItem, z), i).executeSync();
    }

    public boolean isNewImageCalculationEnable() {
        return isImageFeatureCalculationEnable() && LibraryManager.getInstance().isLibrarysExist(LibraryConstants.sImageFeatureSelectionLibraries);
    }

    public void onImageBatchDelete(List<Long> list) {
        if (MiscUtil.isValid(list) && ImageFeatureCacheManager.getInstance().onImageDeleteBatch(list)) {
            Log.d("ImageFeatureManager", "%d Images delete or add to secret batch, delete them in cards", (Object) Integer.valueOf(list.size()));
            ThreadManager.getWorkHandler().removeCallbacks(this.mImageDeleteRunnable);
            ThreadManager.getWorkHandler().postDelayed(this.mImageDeleteRunnable, 3000);
        }
    }

    public void onImageDelete(long j) {
        if (ImageFeatureCacheManager.getInstance().onImageDelete(j)) {
            Log.d("ImageFeatureManager", "Image %s delete or add to secret, delete them in cards", (Object) Long.valueOf(j));
            ThreadManager.getWorkHandler().removeCallbacks(this.mImageDeleteRunnable);
            ThreadManager.getWorkHandler().postDelayed(this.mImageDeleteRunnable, 3000);
        }
    }

    public void onImageDelete(String str) {
        try {
            onImageDelete(Long.parseLong(str));
        } catch (NumberFormatException e) {
            Log.e("ImageFeatureManager", (Throwable) e);
        }
    }

    public void recycleBitmap(Bitmap bitmap) {
        this.mImageFeatureReusedBitCache.put(bitmap);
    }

    public void triggerNewImageCalculation() {
        if (!isNewImageCalculationEnable()) {
            Log.d("ImageFeatureManager", "ImageFeature Selection Disable or Libraries not exist");
        } else if (!this.mIsCaculating) {
            this.mIsCaculating = true;
            this.mThreadPoolExecutor.execute(createNewImageCalculateRunnable());
        }
    }
}
