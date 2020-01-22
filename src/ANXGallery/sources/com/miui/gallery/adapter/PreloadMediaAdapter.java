package com.miui.gallery.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.Config;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
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

public abstract class PreloadMediaAdapter extends BaseMediaAdapter<BaseViewHolder> {
    private PreloadOnScrollListener mPreloadListener;
    private int mPreloadNum = Config.ThumbConfig.get().sPreloadNum;
    private List<PreloadViewAware> mPreloadViewList = new LinkedList();
    private List<PreloadViewAware> mRecycleViewList = new LinkedList();

    public static class PreloadOnScrollListener extends RecyclerView.OnScrollListener {
        private int mFirstVisiblePos = 0;
        private int mLastVisiblePos = 0;
        private RecyclerView.OnScrollListener mScrollListener;
        private int mScrollState = 0;

        public PreloadOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
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

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            RecyclerView.OnScrollListener onScrollListener = this.mScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(recyclerView, i);
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            GalleryRecyclerView galleryRecyclerView = (GalleryRecyclerView) recyclerView;
            int findFirstVisibleItemPosition = galleryRecyclerView.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = galleryRecyclerView.findLastVisibleItemPosition();
            int i3 = this.mFirstVisiblePos;
            if (findFirstVisibleItemPosition != i3) {
                this.mScrollState = findFirstVisibleItemPosition > i3 ? 0 : 1;
            }
            this.mFirstVisiblePos = findFirstVisibleItemPosition;
            this.mLastVisiblePos = findLastVisibleItemPosition;
            RecyclerView.OnScrollListener onScrollListener = this.mScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrolled(recyclerView, i, i2);
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

    public PreloadMediaAdapter(Context context, SyncStateDisplay.DisplayScene displayScene) {
        super(context, displayScene);
    }

    private void doPreload(int i) {
        boolean isBackwards = isBackwards();
        if (this.mPreloadViewList.size() > 0) {
            if (!isBackwards ? this.mPreloadViewList.get(0).position < i : this.mPreloadViewList.get(0).position > i) {
                return;
            }
        }
        Iterator<PreloadViewAware> it = this.mPreloadViewList.iterator();
        while (it.hasNext()) {
            PreloadViewAware next = it.next();
            if (!isBackwards ? next.position < i : next.position > i) {
                break;
            }
            it.remove();
            this.mRecycleViewList.add(next);
        }
        int i2 = -1;
        int i3 = (isBackwards ? 1 : -1) + i;
        if (this.mPreloadViewList.size() > 0) {
            List<PreloadViewAware> list = this.mPreloadViewList;
            int i4 = list.get(list.size() - 1).position;
            if (isBackwards) {
                i2 = 1;
            }
            i3 = i4 + i2;
        }
        int min = isBackwards ? Math.min(i + this.mPreloadNum, getItemCount() - 1) : Math.max(i - this.mPreloadNum, 0);
        if (isBackwards) {
            while (i3 <= min) {
                loadImage(i3);
                i3++;
            }
            return;
        }
        while (i3 >= min) {
            loadImage(i3);
            i3--;
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

    private void preload(int i) {
        if (needPreload(i)) {
            doPreload(i);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void doBindData(BaseViewHolder baseViewHolder, int i);

    public final void doBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        doBindData(baseViewHolder, i);
        preload(i);
    }

    public RecyclerView.OnScrollListener generateWrapScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mPreloadListener = new PreloadOnScrollListener(super.generateWrapScrollListener(onScrollListener));
        return this.mPreloadListener;
    }

    /* access modifiers changed from: protected */
    public void initDisplayImageOptions() {
        super.initDisplayImageOptions();
        this.mDisplayImageOptionBuilder.preferSyncLoadFromMicroCache(false);
        this.mDefaultDisplayImageOptions = this.mDisplayImageOptionBuilder.build();
    }
}
