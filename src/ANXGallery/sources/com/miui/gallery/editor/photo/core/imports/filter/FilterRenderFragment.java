package com.miui.gallery.editor.photo.core.imports.filter;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.android.internal.WindowCompat;
import com.miui.arcsoftbeauty.ArcsoftBeautyJni;
import com.miui.filtersdk.filter.base.BaseOriginalFilter;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyTransferItem;
import com.miui.gallery.editor.photo.core.GLFragment;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.RenderMetaData;
import com.miui.gallery.editor.photo.core.imports.filter.render.EmptyGPUImageFilter;
import com.miui.gallery.editor.photo.core.imports.filter.render.GPUImage;
import com.miui.gallery.editor.photo.core.imports.filter.render.IFilterEmptyValidate;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.xiaomi.skytransfer.SkyTranFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterRenderFragment extends AbstractEffectFragment implements SurfaceHolder.Callback, GLFragment {
    /* access modifiers changed from: private */
    public Bitmap mBeautyBitmap;
    private BeautyTask mBeautyTask;
    private Button mCompareBtn;
    private int mCompareButtonDelay;
    private boolean mCompareEnable = false;
    private Runnable mCompareRunnable = new Runnable() {
        public void run() {
            FilterRenderFragment.this.mHandler.removeCallbacks(this);
            FilterRenderFragment.this.renderCompareOrigin(false);
        }
    };
    /* access modifiers changed from: private */
    public Bitmap mCurrentBitmap;
    private List<RenderMetaData> mEffects = new ArrayList();
    private FilterContext mFilterContext = new FilterContext();
    private View mFloatView;
    /* access modifiers changed from: private */
    public GLSurfaceView mGLSurfaceView;
    private GPUImage mGPUImage;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mIsRenderOrigin;
    /* access modifiers changed from: private */
    public boolean mIsSkyProcessing;
    /* access modifiers changed from: private */
    public SkyTransferItem mLastSkyTransferItem;
    private boolean mNeedDoRender;
    private SkyTransferItem mNextSkyTransferItem;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (FilterRenderFragment.this.isAdded()) {
                ((FrameLayout.LayoutParams) FilterRenderFragment.this.mProgressBar.getLayoutParams()).setMargins(0, (int) (((float) (FilterRenderFragment.this.mGLSurfaceView.getTop() + (FilterRenderFragment.this.mGLSurfaceView.getHeight() / 2))) - (FilterRenderFragment.this.getResources().getDimension(R.dimen.remover_render_progress_bar_size) / 2.0f)), 0, 0);
                FilterRenderFragment.this.mGLSurfaceView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private ProgressRunnable mProgressRunnable = new ProgressRunnable();
    private boolean mShowFloatView;

    private class BeautyTask extends AsyncTask<Bitmap, Void, Bitmap> {
        private BeautyTask() {
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Bitmap... bitmapArr) {
            Bitmap copy = bitmapArr[0].copy(Bitmap.Config.ARGB_8888, true);
            ArcsoftBeautyJni.smartBeauty(copy);
            return copy;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            Bitmap unused = FilterRenderFragment.this.mBeautyBitmap = bitmap;
            FilterRenderFragment.this.doRenderAfterBeauty();
        }
    }

    private class FilterContext extends GLFragment.GLContext {
        private FilterContext() {
        }

        /* access modifiers changed from: protected */
        public void onPause() {
        }

        /* access modifiers changed from: protected */
        public void onResume() {
        }

        /* access modifiers changed from: package-private */
        public void surfaceCreated() {
            super.performCreated();
        }

        /* access modifiers changed from: package-private */
        public void surfaceDestroyed() {
            super.performDestroyed();
        }
    }

    private class ProgressRunnable implements Runnable {
        private ProgressRunnable() {
        }

        public void run() {
            FilterRenderFragment.this.mProgressBar.setVisibility(0);
        }
    }

    private class SkyTransferTask extends AsyncTask<SkyTransferItem, Void, Bitmap> {
        private SkyTransferTask() {
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(SkyTransferItem... skyTransferItemArr) {
            SkyTransferItem skyTransferItem = skyTransferItemArr[0];
            Bitmap bitmap = FilterRenderFragment.this.getBitmap();
            if (bitmap == null) {
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            boolean transferSkyForShow = (FilterRenderFragment.this.mLastSkyTransferItem == null || FilterRenderFragment.this.mLastSkyTransferItem.getCate() != skyTransferItem.getCate() || !FilterRenderFragment.this.mLastSkyTransferItem.mCanAdjustMoment) ? SkyTranFilter.getInstance().transferSkyForShow(bitmap, createBitmap, skyTransferItem) : SkyTranFilter.getInstance().transferSkyAdjustMoment(bitmap, createBitmap, skyTransferItem);
            SkyTransferItem unused = FilterRenderFragment.this.mLastSkyTransferItem = skyTransferItem;
            Log.d("FilterRenderFragment", "SkyTransferTask:%s", (Object) skyTransferItem.toString());
            if (transferSkyForShow) {
                return createBitmap;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Bitmap unused = FilterRenderFragment.this.mCurrentBitmap = bitmap;
                FilterRenderFragment.this.enableComparison(true);
                if (!FilterRenderFragment.this.mIsRenderOrigin) {
                    FilterRenderFragment.this.renderBitmap(bitmap);
                }
            }
            FilterRenderFragment.this.hideProgressView();
            boolean unused2 = FilterRenderFragment.this.mIsSkyProcessing = false;
            FilterRenderFragment.this.doSkyNextProcess();
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            FilterRenderFragment.this.showProgressView();
        }
    }

    private void createFloatView() {
        int i;
        int i2;
        if (!this.mShowFloatView) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = 1002;
            layoutParams.format = 1;
            layoutParams.flags = 40;
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.gravity = 48;
            ((WindowManager) getActivity().getSystemService("window")).addView(this.mFloatView, layoutParams);
            this.mShowFloatView = true;
            int i3 = 0;
            if (WindowCompat.isNotch(getActivity())) {
                i3 = WindowCompat.getTopNotchHeight(getActivity());
            }
            if (!MiscUtil.checkNavigationBarShow(getActivity(), getActivity().getWindow())) {
                if (isNear3V4()) {
                    i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_top_3V4);
                    i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_3V4);
                } else {
                    i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_top_9V16);
                    i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_9V16);
                }
            } else if (isNear3V4()) {
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_top_has_nav_bar_3V4);
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_has_nav_bar_3V4);
            } else {
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_top_has_nav_bar_9V16);
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_has_nav_bar_9V16);
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mCompareBtn.getLayoutParams();
            layoutParams2.topMargin = i2 + i3;
            layoutParams2.setMarginEnd(i);
            this.mCompareBtn.setLayoutParams(layoutParams2);
        }
    }

    private void doRender() {
        FilterRenderData filterRenderData = new FilterRenderData(this.mEffects);
        boolean z = true;
        if (!filterRenderData.isPortraitBeauty() || !ArcsoftBeautyJni.idBeautyAvailable()) {
            if (filterRenderData.isSkyTransfer()) {
                SkyTransferItem instantiateSky = filterRenderData.instantiateSky();
                if (instantiateSky.isEmpty()) {
                    Bitmap bitmap = getBitmap();
                    enableComparison(!instantiateSky.isEmpty());
                    renderBitmap(bitmap);
                } else if (this.mIsSkyProcessing) {
                    this.mNextSkyTransferItem = instantiateSky;
                } else {
                    this.mIsSkyProcessing = true;
                    new SkyTransferTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new SkyTransferItem[]{instantiateSky});
                }
            } else {
                BaseOriginalFilter instantiate = filterRenderData.instantiate();
                renderBitmap(getBitmap(), instantiate);
                if (instantiate instanceof IFilterEmptyValidate) {
                    z = true ^ ((IFilterEmptyValidate) instantiate).isEmpty();
                }
                enableComparison(z);
            }
        } else if (this.mBeautyBitmap == null) {
            this.mBeautyTask = new BeautyTask();
            this.mBeautyTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Bitmap[]{getBitmap()});
        } else {
            doRenderAfterBeauty();
        }
    }

    /* access modifiers changed from: private */
    public void doRenderAfterBeauty() {
        FilterRenderData filterRenderData = new FilterRenderData(this.mEffects);
        if (isAdded() && filterRenderData.isPortraitBeauty()) {
            BaseOriginalFilter instantiate = filterRenderData.instantiate();
            renderBitmap(this.mBeautyBitmap, instantiate);
            enableComparison(!(instantiate instanceof EmptyGPUImageFilter));
        }
    }

    /* access modifiers changed from: private */
    public void doSkyNextProcess() {
        if (this.mNextSkyTransferItem != null) {
            this.mIsSkyProcessing = true;
            new SkyTransferTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new SkyTransferItem[]{this.mNextSkyTransferItem});
            this.mNextSkyTransferItem = null;
        }
    }

    private void removeFloatView() {
        if (this.mShowFloatView) {
            ((WindowManager) getActivity().getSystemService("window")).removeView(this.mFloatView);
            this.mShowFloatView = false;
        }
    }

    /* access modifiers changed from: private */
    public void renderBitmap(Bitmap bitmap) {
        renderBitmap(bitmap, new EmptyGPUImageFilter());
    }

    private void renderBitmap(Bitmap bitmap, BaseOriginalFilter baseOriginalFilter) {
        this.mGPUImage.setImage(bitmap);
        this.mGPUImage.setFilter(baseOriginalFilter);
        this.mGPUImage.requestRender();
    }

    private void renderCompareCurrent(boolean z) {
        Bitmap bitmap;
        if (!new FilterRenderData(this.mEffects).isSkyTransfer() || (bitmap = this.mCurrentBitmap) == null) {
            render();
        } else {
            renderBitmap(bitmap);
        }
        if (z) {
            this.mCompareBtn.setSelected(false);
        }
    }

    /* access modifiers changed from: private */
    public void renderCompareOrigin(boolean z) {
        renderBitmap(getBitmap());
        if (z) {
            this.mCompareBtn.setSelected(true);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("page", this.mEffect.name());
        GallerySamplingStatHelper.recordCountEvent("photo_editor", "compare_button_touch", hashMap);
    }

    public void add(Metadata metadata, Object obj) {
        if (metadata instanceof RenderMetaData) {
            this.mEffects.add((RenderMetaData) metadata);
        }
    }

    public void clear() {
        this.mEffects.clear();
    }

    public void enableComparison(boolean z) {
        this.mCompareEnable = z;
        if (this.mCompareEnable) {
            this.mCompareBtn.setVisibility(0);
            this.mCompareBtn.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return FilterRenderFragment.this.lambda$enableComparison$99$FilterRenderFragment(view, motionEvent);
                }
            });
            return;
        }
        this.mCompareBtn.setVisibility(4);
    }

    public GLFragment.GLContext getGLContext() {
        return this.mFilterContext;
    }

    public void hideProgressView() {
        this.mHandler.removeCallbacks(this.mProgressRunnable);
        this.mProgressBar.setVisibility(8);
    }

    public boolean isEmpty() {
        return new FilterRenderData(this.mEffects).instantiate() instanceof EmptyGPUImageFilter;
    }

    public /* synthetic */ boolean lambda$enableComparison$99$FilterRenderFragment(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            renderCompareOrigin(true);
        } else if (1 == motionEvent.getAction() || 3 == motionEvent.getAction()) {
            renderCompareCurrent(true);
        }
        return true;
    }

    public /* synthetic */ void lambda$onCreateView$97$FilterRenderFragment() {
        this.mFilterContext.surfaceCreated();
    }

    public /* synthetic */ boolean lambda$onViewCreated$98$FilterRenderFragment(View view, MotionEvent motionEvent) {
        if (!this.mCompareEnable) {
            this.mIsRenderOrigin = false;
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mIsRenderOrigin = true;
            this.mHandler.removeCallbacks(this.mCompareRunnable);
            this.mHandler.postDelayed(this.mCompareRunnable, (long) this.mCompareButtonDelay);
        } else if (action == 1 || action == 3) {
            this.mHandler.removeCallbacks(this.mCompareRunnable);
            renderCompareCurrent(false);
            this.mIsRenderOrigin = false;
        }
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.filter_effect_fragment, viewGroup, false);
        this.mGLSurfaceView = (GLSurfaceView) inflate.findViewById(R.id.gl_surface_view);
        float parseFloat = Float.parseFloat(getString(R.string.filter_bg_color_component));
        this.mGPUImage = new GPUImage(getActivity());
        this.mGPUImage.setBackgroundColor(parseFloat, parseFloat, parseFloat);
        this.mGPUImage.setImage(getBitmap());
        this.mGPUImage.setGLSurfaceView(this.mGLSurfaceView);
        this.mGLSurfaceView.postDelayed(new Runnable() {
            public final void run() {
                FilterRenderFragment.this.lambda$onCreateView$97$FilterRenderFragment();
            }
        }, 1000);
        this.mFloatView = layoutInflater.inflate(R.layout.editor_compare_btn, (ViewGroup) null, false);
        this.mCompareBtn = (Button) this.mFloatView.findViewById(R.id.compare_btn);
        this.mProgressBar = (ProgressBar) this.mFloatView.findViewById(R.id.progress);
        this.mGLSurfaceView.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        if (this.mNeedDoRender) {
            doRender();
        }
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        BeautyTask beautyTask = this.mBeautyTask;
        if (beautyTask != null) {
            beautyTask.cancel(true);
        }
        this.mGPUImage.deleteImage();
        this.mCurrentBitmap = null;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        return new FilterRenderData(this.mEffects);
    }

    public void onPause() {
        super.onPause();
        removeFloatView();
    }

    public void onResume() {
        super.onResume();
        createFloatView();
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        ArrayList arrayList = new ArrayList();
        List<RenderMetaData> list = this.mEffects;
        if (list != null && !list.isEmpty()) {
            for (RenderMetaData renderMetaData : this.mEffects) {
                arrayList.add("V9-" + renderMetaData.name);
            }
        }
        return arrayList;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCompareButtonDelay = getResources().getInteger(R.integer.compare_button_delay);
        this.mGLSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return FilterRenderFragment.this.lambda$onViewCreated$98$FilterRenderFragment(view, motionEvent);
            }
        });
    }

    public void remove(Metadata metadata) {
        int indexOf;
        if ((metadata instanceof RenderMetaData) && (indexOf = this.mEffects.indexOf(metadata)) >= 0) {
            this.mEffects.remove(indexOf);
        }
    }

    public void render() {
        if (this.mGPUImage == null) {
            this.mNeedDoRender = true;
        } else {
            doRender();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
        GPUImage gPUImage = this.mGPUImage;
        if (gPUImage != null) {
            gPUImage.setImage(bitmap);
        }
    }

    public void showProgressView() {
        this.mHandler.postDelayed(this.mProgressRunnable, 500);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("FilterRenderFragment", "surfaceCreated");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mFilterContext.surfaceDestroyed();
    }
}
