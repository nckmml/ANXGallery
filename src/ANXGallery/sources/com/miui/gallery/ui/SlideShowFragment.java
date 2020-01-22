package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.adapter.SlideShowAdapter;
import com.miui.gallery.loader.PhotoLoaderManager;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SystemUiUtil;
import com.miui.gallery.widget.SlideShowView;
import com.nexstreaming.nexeditorsdk.nexProject;

public class SlideShowFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public SlideShowAdapter mAdapter;
    private int mLoadDuration = nexProject.kAutoThemeTransitionDuration;
    private SlideLoaderCallBack mLoaderCallBack;
    /* access modifiers changed from: private */
    public Handler mSlideHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                SlideShowFragment.this.loadNextBitmap();
            } else if (i == 2) {
                SlideShowFragment.this.showPendingItem(message.obj == null ? null : (SlideShowAdapter.ShowItem) message.obj);
            }
        }
    };
    private SlideShowView mSlideView;

    private class SlideLoaderCallBack implements LoaderManager.LoaderCallbacks {
        private SlideLoaderCallBack() {
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            return PhotoLoaderManager.getInstance().getPhotoDataSet(SlideShowFragment.this.getActivity(), Uri.parse(bundle.getString("photo_uri")), bundle);
        }

        public void onLoadFinished(Loader loader, Object obj) {
            SlideShowFragment.this.mAdapter.changeDataSet((BaseDataSet) obj, SlideShowFragment.this.mAdapter.getShowIndex() == 0 ? SlideShowFragment.this.getArguments().getInt("photo_init_position", 0) : SlideShowFragment.this.mAdapter.getShowIndex());
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private int getSlideDuration() {
        int max = Math.max(3500, GalleryPreferences.SlideShow.getSlideShowInterval() * 1000);
        this.mLoadDuration = max - 500;
        return max;
    }

    /* access modifiers changed from: private */
    public void loadNextBitmap() {
        this.mAdapter.nextBitmap(new FutureListener<SlideShowAdapter.ShowItem>() {
            public void onFutureDone(Future<SlideShowAdapter.ShowItem> future) {
                if (!future.isCancelled()) {
                    SlideShowFragment.this.mSlideHandler.obtainMessage(2, future.get()).sendToTarget();
                }
            }
        });
    }

    private void setResult(SlideShowAdapter.ShowItem showItem) {
        Log.i("SlideShowFragment", "setResult %d", (Object) Integer.valueOf(showItem.getIndex()));
        BaseDataItem baseDataItem = this.mAdapter.getBaseDataItem(showItem.getIndex());
        if (baseDataItem != null) {
            getArguments().putParcelable("photo_transition_data", new ImageLoadParams(baseDataItem.getKey(), baseDataItem.getPathDisplayBetter(), Config.ThumbConfig.get().sMicroTargetSize, (RectF) null, showItem.getIndex(), baseDataItem.getMimeType(), baseDataItem.getSecretKey(), baseDataItem.getSize()));
        }
        getArguments().putInt("photo_init_position", showItem.getIndex());
        getArguments().putInt("photo_count", this.mAdapter.getCount());
    }

    /* access modifiers changed from: private */
    public void showPendingItem(SlideShowAdapter.ShowItem showItem) {
        if (showItem != null && showItem.isValid()) {
            this.mSlideView.next(showItem.getBitmap(), 0);
            setResult(showItem);
            this.mSlideHandler.sendEmptyMessageDelayed(1, (long) this.mLoadDuration);
        } else if (isAdded() && isResumed()) {
            finish();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.ui.SlideShowFragment, android.app.Fragment] */
    public static void showSlideShowFragment(BaseActivity baseActivity, Bundle bundle) {
        ? slideShowFragment = new SlideShowFragment();
        slideShowFragment.setArguments(bundle);
        baseActivity.startFragment(slideShowFragment, (String) null, true, false);
    }

    public String getPageName() {
        return "slide_show";
    }

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 0;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mLoaderCallBack = new SlideLoaderCallBack();
        getLoaderManager().initLoader("SlideShowFragment".hashCode(), getArguments(), this.mLoaderCallBack);
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.slide_show_page, viewGroup, false);
        this.mSlideView = (SlideShowView) inflate.findViewById(R.id.slide);
        this.mAdapter = new SlideShowAdapter((ImageLoadParams) getArguments().getParcelable("photo_transition_data"), getArguments().getInt("photo_init_position", 0));
        this.mSlideView.setSlideDuration(getSlideDuration());
        this.mSlideView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1 && SlideShowFragment.this.isResumed()) {
                    SlideShowFragment.this.finish();
                }
                return true;
            }
        });
        return inflate;
    }

    public void onPause() {
        super.onPause();
        SystemUiUtil.showSystemBars(getActivity().getWindow().getDecorView());
    }

    public void onResume() {
        super.onResume();
        SystemUiUtil.hideSystemBars(getActivity().getWindow().getDecorView());
    }

    public void onStart() {
        super.onStart();
        if (this.mActivity.getWindow() != null) {
            this.mActivity.getWindow().addFlags(128);
        }
        this.mAdapter.resume();
        loadNextBitmap();
    }

    public void onStop() {
        super.onStop();
        if (this.mActivity.getWindow() != null) {
            this.mActivity.getWindow().clearFlags(128);
        }
        this.mAdapter.pause();
        this.mSlideView.release();
        this.mSlideHandler.removeCallbacksAndMessages((Object) null);
    }
}
