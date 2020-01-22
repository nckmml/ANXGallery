package com.miui.gallery.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMediaFragment extends BaseFragment {
    private boolean mInPhotoPage;
    private PhotoPageReceiver mPhotoPageReceiver;

    private static class PhotoPageReceiver extends BroadcastReceiver {
        private WeakReference<BaseMediaFragment> mFragmentRef;

        public PhotoPageReceiver(BaseMediaFragment baseMediaFragment) {
            this.mFragmentRef = new WeakReference<>(baseMediaFragment);
        }

        public void onReceive(Context context, Intent intent) {
            BaseMediaFragment baseMediaFragment = (BaseMediaFragment) this.mFragmentRef.get();
            if (baseMediaFragment != null) {
                String action = intent.getAction();
                if ("com.miu.gallery.action.ENTER_PHOTO_PAGE".equals(action)) {
                    Log.i("BaseMediaFragment", "ACTION_ENTER_PHOTO_PAGE");
                    baseMediaFragment.onPhotoPageCreate(intent);
                } else if ("com.miui.gallery.action.EXIT_PHOTO_PAGE".equals(action)) {
                    Log.i("BaseMediaFragment", "ACTION_EXIT_PHOTO_PAGE");
                    baseMediaFragment.onPhotoPageDestroy(intent);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract Loader getLoader();

    /* access modifiers changed from: protected */
    public List<Loader> getLoaders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getLoader());
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean isInPhotoPage() {
        return this.mInPhotoPage;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mPhotoPageReceiver = new PhotoPageReceiver(this);
    }

    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().clearHardMemoryCache();
    }

    /* access modifiers changed from: protected */
    public void onPhotoPageCreate(Intent intent) {
        List<Loader> loaders;
        if (isAdded() && (loaders = getLoaders()) != null && loaders.size() > 0) {
            for (Loader next : loaders) {
                if (next != null) {
                    next.stopLoading();
                }
            }
        }
        this.mInPhotoPage = true;
    }

    /* access modifiers changed from: protected */
    public void onPhotoPageDestroy(Intent intent) {
        if (isAdded()) {
            if (intent.getIntExtra("photo_result_code", -1) == -1) {
                List<Loader> loaders = getLoaders();
                if (loaders != null && loaders.size() > 0) {
                    for (Loader next : loaders) {
                        if (next != null) {
                            next.startLoading();
                        }
                    }
                }
            } else {
                finish();
            }
        }
        this.mInPhotoPage = false;
    }

    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.miu.gallery.action.ENTER_PHOTO_PAGE");
        intentFilter.addAction("com.miui.gallery.action.EXIT_PHOTO_PAGE");
        LocalBroadcastManager.getInstance(this.mActivity).registerReceiver(this.mPhotoPageReceiver, intentFilter);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this.mActivity).unregisterReceiver(this.mPhotoPageReceiver);
    }
}
