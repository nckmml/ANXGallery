package com.miui.gallery.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.ViewGroup;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.HomePageAdapter;
import com.miui.gallery.dao.GalleryLiteEntityManager;
import com.miui.gallery.model.HomeMedia;
import com.miui.gallery.model.HomeMediaHeader;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.AsyncViewPrefetch;
import com.miui.gallery.ui.HomePageFragment;
import com.miui.gallery.ui.ViewProvider;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HomePageStartupHelper {
    /* access modifiers changed from: private */
    public static String TAG = "HomePageStartupHelper";
    private final int MICRO_THUMB_COLUMN_NUMBER = Config.ThumbConfig.get().sMicroThumbColumnsPortrait;
    private final int MICRO_THUMB_PRELOAD_MAX_COUNT = (this.MICRO_THUMB_COLUMN_NUMBER * 6);
    private Attacher mAttacher;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public Object mCursorLock = new Object();
    private Future mDataLoadFuture;
    /* access modifiers changed from: private */
    public DataLoadListener mDataLoadListener;
    /* access modifiers changed from: private */
    public volatile Cursor mHomePageCursor;
    /* access modifiers changed from: private */
    public volatile boolean mIsDataInitialized = false;
    /* access modifiers changed from: private */
    public volatile boolean mIsDestroyed = false;
    /* access modifiers changed from: private */
    public AsyncViewPrefetch mPrefetcher;

    public interface Attacher {
        HomePageStartupHelper getStartupHelper();

        void onStartup();
    }

    public interface DataLoadListener {
        void onDataLoadFinish(Cursor cursor);
    }

    class ImageDisplayItem {
        /* access modifiers changed from: private */
        public long mFileLength;
        /* access modifiers changed from: private */
        public String mFilePath;

        public ImageDisplayItem(String str, long j) {
            this.mFilePath = str;
            this.mFileLength = j;
        }
    }

    public HomePageStartupHelper(final Context context, Attacher attacher) {
        this.mContext = context;
        this.mAttacher = attacher;
        ThreadManager.getWorkHandler().post(new Runnable() {
            public void run() {
                HomePageStartupHelper.this.mPrefetcher = new AsyncViewPrefetch(context, (ViewGroup) null, Config.ThumbConfig.get().sPredictItemsOneScreen) {
                    public int getViewResId(int i) {
                        return R.layout.home_page_grid_item;
                    }
                }.prefetch();
            }
        });
    }

    /* access modifiers changed from: private */
    public void closeCursor() {
        synchronized (this.mCursorLock) {
            if (this.mHomePageCursor != null) {
                this.mHomePageCursor.close();
            }
        }
    }

    /* access modifiers changed from: private */
    public int getPreloadImageCount(ArrayList<Integer> arrayList) {
        int i = 0;
        if (!MiscUtil.isValid(arrayList)) {
            return 0;
        }
        int i2 = this.MICRO_THUMB_COLUMN_NUMBER;
        int i3 = this.MICRO_THUMB_PRELOAD_MAX_COUNT;
        Iterator<Integer> it = arrayList.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            i += intValue;
            if (i + i4 > i3) {
                break;
            }
            int i5 = intValue % i2;
            if (i5 != 0) {
                i4 += i2 - i5;
            }
        }
        if (i4 + i >= i3) {
            i = i3 - i4;
        }
        Log.d(TAG, "preload image count %d", (Object) Integer.valueOf(i));
        return i;
    }

    private Future loadHomePageData() {
        return ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                ArrayList<Integer> integerArrayList;
                int i;
                long currentTimeMillis = System.currentTimeMillis();
                int i2 = 0;
                try {
                    List<HomeMediaHeader> query = GalleryLiteEntityManager.getInstance().query(HomeMediaHeader.class, (String) null, (String[]) null);
                    if (MiscUtil.isValid(query)) {
                        List<HomeMedia> query2 = GalleryLiteEntityManager.getInstance().query(HomeMedia.class, (String) null, (String[]) null);
                        if (MiscUtil.isValid(query2)) {
                            MatrixCursor matrixCursor = new MatrixCursor(HomeMedia.Constants.PROJECTION, query2.size());
                            for (HomeMedia values : query2) {
                                matrixCursor.addRow(values.values());
                            }
                            HomeMediaHeader homeMediaHeader = query.get(0);
                            Bundle bundle = new Bundle();
                            bundle.putIntegerArrayList("extra_timeline_item_count_in_group", HomeMediaHeader.getGroupItemCount(homeMediaHeader));
                            bundle.putIntegerArrayList("extra_timeline_group_start_pos", HomeMediaHeader.getGroupItemStartPos(homeMediaHeader));
                            bundle.putStringArrayList("extra_timeline_group_start_locations", HomeMediaHeader.getGroupLocations(homeMediaHeader));
                            matrixCursor.setExtras(bundle);
                            Cursor unused = HomePageStartupHelper.this.mHomePageCursor = matrixCursor;
                        }
                    }
                    if (HomePageStartupHelper.this.mHomePageCursor == null) {
                        Cursor unused2 = HomePageStartupHelper.this.mHomePageCursor = HomePageStartupHelper.this.mContext.getContentResolver().query(HomePageFragment.PHOTOS_LOADER_URI, HomePageFragment.PHOTOS_LOADER_PROJECTION, "alias_show_in_homepage=1 AND sha1 NOT NULL", (String[]) null, "alias_sort_time DESC ");
                    }
                    currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                    Log.d(HomePageStartupHelper.TAG, "load preview cost %d", (Object) Long.valueOf(currentTimeMillis));
                } catch (Exception e) {
                    Log.e(HomePageStartupHelper.TAG, "error when load data in home page ", (Object) e);
                }
                if (HomePageStartupHelper.this.mIsDestroyed) {
                    HomePageStartupHelper.this.closeCursor();
                    return null;
                }
                synchronized (HomePageStartupHelper.this.mCursorLock) {
                    integerArrayList = (HomePageStartupHelper.this.mHomePageCursor == null || HomePageStartupHelper.this.mHomePageCursor.isClosed()) ? null : HomePageStartupHelper.this.mHomePageCursor.getExtras().getIntegerArrayList("extra_timeline_item_count_in_group");
                }
                int access$700 = HomePageStartupHelper.this.getPreloadImageCount(integerArrayList);
                ArrayList arrayList = new ArrayList();
                while (true) {
                    i = i2 + 1;
                    if (i2 >= access$700) {
                        break;
                    }
                    synchronized (HomePageStartupHelper.this.mCursorLock) {
                        if (HomePageStartupHelper.this.mHomePageCursor == null || HomePageStartupHelper.this.mHomePageCursor.isClosed()) {
                            break;
                        } else if (!HomePageStartupHelper.this.mHomePageCursor.moveToNext()) {
                            break;
                        } else {
                            String microPath = HomePageAdapter.getMicroPath(HomePageStartupHelper.this.mHomePageCursor, 12, 5);
                            if (microPath != null) {
                                arrayList.add(new ImageDisplayItem(microPath, HomePageStartupHelper.this.mHomePageCursor.getLong(16)));
                            }
                        }
                    }
                    i2 = i;
                }
                ThreadManager.getMainHandler().post(new Runnable() {
                    public void run() {
                        if (HomePageStartupHelper.this.mDataLoadListener != null && !HomePageStartupHelper.this.mIsDestroyed) {
                            HomePageStartupHelper.this.mDataLoadListener.onDataLoadFinish(HomePageStartupHelper.this.mHomePageCursor);
                        }
                    }
                });
                boolean unused3 = HomePageStartupHelper.this.mIsDataInitialized = true;
                HomePageStartupHelper.this.preloadHomePageImages(arrayList);
                if (HomePageStartupHelper.this.mIsDestroyed) {
                    HomePageStartupHelper.this.closeCursor();
                }
                if (i > 0) {
                    HomePageStartupHelper.this.statSnapshotLoadDuration(currentTimeMillis);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void preloadHomePageImages(ArrayList<ImageDisplayItem> arrayList) {
        if (this.mContext != null && !this.mIsDestroyed && MiscUtil.isValid(arrayList)) {
            ImageLoader.getInstance().resume();
            DisplayImageOptions.Builder highPriority = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.get().MICRO_THUMB_DISPLAY_IMAGE_OPTIONS_DEFAULT).highPriority(true);
            Iterator<ImageDisplayItem> it = arrayList.iterator();
            while (it.hasNext()) {
                ImageDisplayItem next = it.next();
                if (this.mIsDestroyed) {
                    Log.w(TAG, "preload home page images break");
                    return;
                }
                if (next.mFileLength > 0) {
                    highPriority.considerFileLength(true).fileLength(next.mFileLength);
                } else {
                    highPriority.considerFileLength(false);
                }
                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(next.mFilePath), ImageLoader.getInstance().createImageAware((String) null, Config.ThumbConfig.get().sMicroTargetSize, ViewScaleType.CROP), highPriority.build(), Config.ThumbConfig.get().sMicroTargetSize, (ImageLoadingListener) null, (ImageLoadingProgressListener) null, (RectF) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void statSnapshotLoadDuration(long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("cost", String.valueOf(j));
        GallerySamplingStatHelper.recordCountEvent("home", "snapshot_load_duration", hashMap);
    }

    public ViewProvider getViewProvider() {
        return this.mPrefetcher;
    }

    public void onActivityCreate() {
        this.mDataLoadFuture = loadHomePageData();
    }

    public void onActivityDestroy() {
        this.mIsDestroyed = true;
        closeCursor();
        if (this.mAttacher != null) {
            this.mAttacher = null;
        }
        Future future = this.mDataLoadFuture;
        if (future != null) {
            future.cancel();
            this.mDataLoadFuture = null;
        }
    }

    public void onStartup() {
        Attacher attacher = this.mAttacher;
        if (attacher != null) {
            attacher.onStartup();
        }
    }

    public void setDataLoaderListener(DataLoadListener dataLoadListener) {
        if (this.mIsDataInitialized) {
            dataLoadListener.onDataLoadFinish(this.mHomePageCursor);
            return;
        }
        Log.w(TAG, "preload home page cursor failed");
        this.mDataLoadListener = dataLoadListener;
    }
}
