package com.miui.gallery.util.photoview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ProcessingMediaHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PhotoPageDataCache {
    private static PhotoPageDataCache sInstance;
    private List<ItemViewInfo> mAppointedInfos;
    private Bundle mArguments;
    private SoftReference<View> mCacheItem;
    private WeakReference<ViewGroup> mItemViewParent;
    private SoftReference<View> mPageLayout;
    private long mPairKey;

    private PhotoPageDataCache() {
    }

    private ArrayList<ItemViewInfo> getAllItemInfos() {
        ViewGroup viewParent = getViewParent();
        ArrayList<ItemViewInfo> arrayList = new ArrayList<>();
        if (viewParent != null) {
            int childCount = viewParent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewParent.getChildAt(i);
                Object tag = childAt.getTag(R.id.tag_item_position);
                if (tag != null) {
                    arrayList.add(ItemViewInfo.getImageInfo(childAt, ((Integer) tag).intValue()));
                }
            }
        } else {
            List<ItemViewInfo> list = this.mAppointedInfos;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public static PhotoPageDataCache getInstance() {
        if (sInstance == null) {
            sInstance = new PhotoPageDataCache();
        }
        return sInstance;
    }

    private ViewGroup getViewParent() {
        WeakReference<ViewGroup> weakReference = this.mItemViewParent;
        if (weakReference != null) {
            return (ViewGroup) weakReference.get();
        }
        return null;
    }

    public static void preLoad(Context context, ImageLoadParams imageLoadParams) {
        if (imageLoadParams != null) {
            DisplayImageOptions displayImageOptions = imageLoadParams.getDisplayImageOptions();
            if (imageLoadParams.isSecret()) {
                displayImageOptions = new DisplayImageOptions.Builder().cloneFrom(displayImageOptions).secretKey(imageLoadParams.getSecretKey()).build();
            }
            String path = imageLoadParams.getPath();
            if (ImageDownloader.Scheme.ofUri(path) == ImageDownloader.Scheme.UNKNOWN) {
                path = ImageDownloader.Scheme.FILE.wrap(imageLoadParams.getPath());
            }
            String str = path;
            if (ProcessingMediaHelper.getInstance().isMediaInProcessing(str)) {
                displayImageOptions = Config.ThumbConfig.appendBlurOptions(displayImageOptions);
            }
            ImageLoader.getInstance().resume();
            ImageLoader.getInstance().loadImage(str, imageLoadParams.getTargetSize(), displayImageOptions, (ImageLoadingListener) null, (ImageLoadingProgressListener) null, imageLoadParams.getRegionRectF());
        }
    }

    public void clear() {
        long j = this.mPairKey - 1;
        this.mPairKey = j;
        if (j == 0) {
            Log.d("PhotoPageDataCache", "clear");
            this.mArguments = null;
            this.mAppointedInfos = null;
            WeakReference<ViewGroup> weakReference = this.mItemViewParent;
            if (weakReference != null) {
                weakReference.clear();
                this.mItemViewParent = null;
            }
            SoftReference<View> softReference = this.mCacheItem;
            if (softReference != null) {
                softReference.clear();
                this.mCacheItem = null;
            }
            SoftReference<View> softReference2 = this.mPageLayout;
            if (softReference2 != null) {
                softReference2.clear();
                this.mPageLayout = null;
            }
        }
    }

    public Bundle getArguments() {
        return this.mArguments;
    }

    public View getCacheItem() {
        View view;
        SoftReference<View> softReference = this.mCacheItem;
        if (softReference != null) {
            view = softReference.get();
            this.mCacheItem.clear();
            this.mCacheItem = null;
        } else {
            view = null;
        }
        Log.d("PhotoPageDataCache", "cache item %s", (Object) view);
        return view;
    }

    public ItemViewInfo getItemViewInfo(int i) {
        ViewGroup viewParent = getViewParent();
        if (viewParent != null) {
            int childCount = viewParent.getChildCount();
            View view = null;
            int i2 = Integer.MIN_VALUE;
            int i3 = Integer.MAX_VALUE;
            View view2 = null;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = viewParent.getChildAt(i4);
                Object tag = childAt.getTag(R.id.tag_item_position);
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    if (intValue == i) {
                        return ItemViewInfo.getImageInfo(childAt, Integer.valueOf(i).intValue());
                    }
                    if (intValue < i3) {
                        view2 = childAt;
                        i3 = intValue;
                    }
                    if (intValue > i2) {
                        view = childAt;
                        i2 = intValue;
                    }
                }
            }
            if (view2 != null && i < i3) {
                ItemViewInfo imageInfo = ItemViewInfo.getImageInfo(view2, i3);
                return new ItemViewInfo(i, imageInfo.getX(), imageInfo.getY() - imageInfo.getHeight(), imageInfo.getWidth(), imageInfo.getHeight());
            } else if (view != null && i > i2) {
                ItemViewInfo imageInfo2 = ItemViewInfo.getImageInfo(view, i3);
                return new ItemViewInfo(i, imageInfo2.getX(), imageInfo2.getY() + imageInfo2.getHeight(), imageInfo2.getWidth(), imageInfo2.getHeight());
            }
        }
        List<ItemViewInfo> list = this.mAppointedInfos;
        if (list != null) {
            for (ItemViewInfo next : list) {
                if (next.getPosition() == i) {
                    return next;
                }
            }
        }
        return null;
    }

    public View getPageLayout() {
        View view;
        SoftReference<View> softReference = this.mPageLayout;
        if (softReference != null) {
            view = softReference.get();
            this.mPageLayout.clear();
            this.mPageLayout = null;
        } else {
            view = null;
        }
        Log.d("PhotoPageDataCache", "page layout %s", (Object) view);
        return view;
    }

    public String getString(String str, String str2) {
        Bundle bundle = this.mArguments;
        if (bundle != null) {
            return bundle.getString(str, str2);
        }
        return null;
    }

    public boolean isItemVisible(int i) {
        ViewGroup viewParent = getViewParent();
        if (viewParent != null) {
            int childCount = viewParent.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                Object tag = viewParent.getChildAt(i2).getTag(R.id.tag_item_position);
                if (tag != null && i == ((Integer) tag).intValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void restoreInstance(Bundle bundle) {
        setArguments(bundle.getBundle("key_save_arguments"));
        setAppointedItemViewInfos(bundle.getParcelableArrayList("key_save_iteminfos"));
    }

    public void saveInstance(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putBoolean("photo_enter_transit", false);
            bundle.putBundle("key_save_arguments", getArguments());
        }
        bundle.putParcelableArrayList("key_save_iteminfos", getAllItemInfos());
    }

    public void setAppointedItemViewInfos(List<ItemViewInfo> list) {
        this.mAppointedInfos = list;
    }

    public void setArguments(Bundle bundle) {
        Log.d("PhotoPageDataCache", "setArguments %s", (Object) bundle);
        this.mPairKey++;
        this.mArguments = bundle;
    }

    public void setItemViewParent(ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.mItemViewParent = new WeakReference<>(viewGroup);
        }
    }

    public boolean viewToPosition(int i) {
        ViewGroup viewParent = getViewParent();
        if (viewParent == null || !(viewParent instanceof ScrollableView)) {
            return false;
        }
        ((ScrollableView) viewParent).viewToPosition(i);
        return true;
    }
}
