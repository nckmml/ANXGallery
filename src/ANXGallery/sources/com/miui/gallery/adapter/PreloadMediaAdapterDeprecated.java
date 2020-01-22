package com.miui.gallery.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AbsListView;
import com.miui.gallery.Config;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class PreloadMediaAdapterDeprecated extends BaseMediaSyncStateAdapter {
    private PreloadOnScrollListener mPreloadListener;
    private int mPreloadNum = Config.ThumbConfig.get().sPreloadNum;
    private List<PreloadViewAware> mPreloadViewList = new LinkedList();
    private List<PreloadViewAware> mRecycleViewList = new LinkedList();

    public static class PreloadOnScrollListener implements AbsListView.OnScrollListener {
        private int mFirstVisiblePos = 0;
        private int mLastVisiblePos = 0;
        private AbsListView.OnScrollListener mScrollListener;
        private int mScrollState = 0;

        public PreloadOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
            this.mScrollListener = onScrollListener;
        }

        public int getFirstPosition() {
            return this.mFirstVisiblePos;
        }

        public int getLastPosition() {
            return this.mLastVisiblePos;
        }

        public int getScrollState() {
            return this.mScrollState;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            int i4 = this.mFirstVisiblePos;
            if (i != i4) {
                this.mScrollState = i > i4 ? 0 : 1;
            }
            this.mFirstVisiblePos = i;
            this.mLastVisiblePos = (this.mFirstVisiblePos + i2) - 1;
            AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScroll(absListView, i, i2, i3);
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(absListView, i);
            }
        }
    }

    private static class PreloadViewAware extends NonViewAware {
        protected int position;

        public PreloadViewAware(ImageSize imageSize, ViewScaleType viewScaleType) {
            super(imageSize, viewScaleType);
        }

        public PreloadViewAware setImageSize(ImageSize imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        public PreloadViewAware setImageUri(String str) {
            this.imageUri = str;
            return this;
        }

        public PreloadViewAware setPosition(int i) {
            this.position = i;
            return this;
        }

        public PreloadViewAware setScaleType(ViewScaleType viewScaleType) {
            this.scaleType = viewScaleType;
            return this;
        }
    }

    public PreloadMediaAdapterDeprecated(Context context, SyncStateDisplay.DisplayScene displayScene) {
        super(context, displayScene);
    }

    public PreloadMediaAdapterDeprecated(Context context, SyncStateDisplay.DisplayScene displayScene, int i) {
        super(context, displayScene, i);
    }

    private void doPreload(Context context, Cursor cursor) {
        boolean isBackwards = isBackwards();
        int position = cursor.getPosition();
        if (this.mPreloadViewList.size() > 0) {
            if (!isBackwards ? this.mPreloadViewList.get(0).position < position : this.mPreloadViewList.get(0).position > position) {
                return;
            }
        }
        Iterator<PreloadViewAware> it = this.mPreloadViewList.iterator();
        while (it.hasNext()) {
            PreloadViewAware next = it.next();
            if (!isBackwards ? next.position < position : next.position > position) {
                break;
            }
            it.remove();
            this.mRecycleViewList.add(next);
        }
        int i = -1;
        int i2 = (isBackwards ? 1 : -1) + position;
        if (this.mPreloadViewList.size() > 0) {
            List<PreloadViewAware> list = this.mPreloadViewList;
            int i3 = list.get(list.size() - 1).position;
            if (isBackwards) {
                i = 1;
            }
            i2 = i3 + i;
        }
        int min = isBackwards ? Math.min(position + this.mPreloadNum, cursor.getCount() - 1) : Math.max(position - this.mPreloadNum, 0);
        if (isBackwards) {
            while (i2 <= min) {
                loadImage(i2);
                i2++;
            }
            return;
        }
        while (i2 >= min) {
            loadImage(i2);
            i2--;
        }
    }

    private PreloadViewAware getViewAware() {
        return this.mRecycleViewList.size() > 0 ? this.mRecycleViewList.remove(0) : new PreloadViewAware(Config.ThumbConfig.get().sMicroTargetSize, ViewScaleType.CROP);
    }

    private boolean isBackwards() {
        return this.mPreloadListener.getScrollState() == 0;
    }

    private void loadImage(int i) {
        PreloadViewAware viewAware = getViewAware();
        String wrap = ImageDownloader.Scheme.FILE.wrap(getClearThumbFilePath(i));
        viewAware.setPosition(i).setImageUri(wrap).setImageSize(getDisplayImageSize(i)).setScaleType(getDisplayScaleType(i));
        ImageLoader.getInstance().displayImage(wrap, (ImageAware) viewAware, new DisplayImageOptions.Builder().cloneFrom(getDisplayImageOptions(i)).cacheInMemory(false).cacheInSubMemory(true).preferSyncLoadFromMicroCache(false).build());
        this.mPreloadViewList.add(viewAware);
    }

    private boolean needPreload(int i) {
        PreloadOnScrollListener preloadOnScrollListener;
        if (this.mPreloadNum <= 0 || (preloadOnScrollListener = this.mPreloadListener) == null) {
            return false;
        }
        int scrollState = preloadOnScrollListener.getScrollState();
        return scrollState != 0 ? scrollState == 1 && i <= this.mPreloadListener.getFirstPosition() : i >= this.mPreloadListener.getLastPosition();
    }

    private void preload(Context context, Cursor cursor) {
        if (needPreload(cursor.getPosition())) {
            doPreload(context, cursor);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void doBindData(View view, Context context, Cursor cursor);

    /* access modifiers changed from: protected */
    public final void doBindView(View view, Context context, Cursor cursor) {
        doBindData(view, context, cursor);
        preload(context, cursor);
    }

    public AbsListView.OnScrollListener generateWrapScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mPreloadListener = new PreloadOnScrollListener(super.generateWrapScrollListener(onScrollListener));
        return this.mPreloadListener;
    }

    public void setPreloadNum(int i) {
        this.mPreloadNum = i;
    }
}
