package com.miui.gallery.editor.photo.core.imports.miuibeauty;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.miui.filtersdk.beauty.BeautyParameterType;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.RenderRecord;
import com.miui.gallery.editor.photo.core.GLFragment;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautyRenderFragment;
import com.miui.gallery.editor.photo.widgets.BeautyImageView;
import com.miui.gallery.editor.photo.widgets.StrokeBoardView;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MiuiBeautyRenderFragment extends AbstractEffectFragment implements RenderRecord, GLFragment, BeautyImageView.InitRenderCallback {
    private List<Map<BeautyParameterType, Float>> mBeautyParameterList;
    /* access modifiers changed from: private */
    public Map<BeautyParameterType, Float> mBeautyParameters;
    /* access modifiers changed from: private */
    public ProgressBar mBeautyProgressBar;
    private Button mCompareBtn;
    private boolean mCompareOrigin;
    private ListIterator<Map<BeautyParameterType, Float>> mCurrIterator;
    /* access modifiers changed from: private */
    public BeautyContext mGLContext = new BeautyContext();
    /* access modifiers changed from: private */
    public OnBeautyProcessListener mOnBeautyProcessListener;
    private ImageView mOriginalImageView;
    private Bitmap mPreProcessBitmap;
    private FrameLayout mPreviewContainer;
    private int mRecordHead;
    private int mRecordTail;
    BeautyImageView mRenderView;
    /* access modifiers changed from: private */
    public boolean mShowProgressDelay = true;
    private StrokeBoardView mStrokeBoardView;
    private Bitmap mToBeCompared;

    private class BeautyContext extends GLFragment.GLContext {
        private BeautyContext() {
        }

        /* access modifiers changed from: protected */
        public void onPause() {
        }

        /* access modifiers changed from: protected */
        public void onResume() {
        }

        /* access modifiers changed from: package-private */
        public void triggerCreated() {
            super.performCreated();
        }
    }

    public interface OnBeautyProcessListener {
        void onBeautyProcessEnd();

        void onBeautyProcessStart();
    }

    private static class RenderTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<MiuiBeautyRenderFragment> mFragmentWeakReference;
        boolean mTaskDone;

        RenderTask(MiuiBeautyRenderFragment miuiBeautyRenderFragment) {
            this.mFragmentWeakReference = new WeakReference<>(miuiBeautyRenderFragment);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            MiuiBeautyRenderFragment miuiBeautyRenderFragment = (MiuiBeautyRenderFragment) this.mFragmentWeakReference.get();
            if (miuiBeautyRenderFragment == null) {
                return null;
            }
            miuiBeautyRenderFragment.mRenderView.reloadTexture(false);
            miuiBeautyRenderFragment.mRenderView.requestRender();
            return null;
        }

        public /* synthetic */ void lambda$onPreExecute$96$MiuiBeautyRenderFragment$RenderTask() {
            MiuiBeautyRenderFragment miuiBeautyRenderFragment;
            if (!this.mTaskDone && (miuiBeautyRenderFragment = (MiuiBeautyRenderFragment) this.mFragmentWeakReference.get()) != null) {
                miuiBeautyRenderFragment.mBeautyProgressBar.setVisibility(0);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            this.mTaskDone = true;
            MiuiBeautyRenderFragment miuiBeautyRenderFragment = (MiuiBeautyRenderFragment) this.mFragmentWeakReference.get();
            if (miuiBeautyRenderFragment != null) {
                miuiBeautyRenderFragment.mBeautyProgressBar.setVisibility(8);
                if (miuiBeautyRenderFragment.mOnBeautyProcessListener != null) {
                    miuiBeautyRenderFragment.mOnBeautyProcessListener.onBeautyProcessEnd();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            MiuiBeautyRenderFragment miuiBeautyRenderFragment = (MiuiBeautyRenderFragment) this.mFragmentWeakReference.get();
            if (miuiBeautyRenderFragment != null && miuiBeautyRenderFragment.mBeautyParameters != null) {
                if (miuiBeautyRenderFragment.mOnBeautyProcessListener != null) {
                    miuiBeautyRenderFragment.mOnBeautyProcessListener.onBeautyProcessStart();
                }
                this.mTaskDone = false;
                if (miuiBeautyRenderFragment.mShowProgressDelay) {
                    miuiBeautyRenderFragment.mRenderView.postDelayed(new Runnable() {
                        public final void run() {
                            MiuiBeautyRenderFragment.RenderTask.this.lambda$onPreExecute$96$MiuiBeautyRenderFragment$RenderTask();
                        }
                    }, 1000);
                } else {
                    miuiBeautyRenderFragment.mBeautyProgressBar.setVisibility(0);
                }
            }
        }
    }

    public void add(Metadata metadata, Object obj) {
        this.mBeautyParameters = obj instanceof Map ? (Map) obj : null;
        this.mRenderView.updateBeautyProcessor(this.mBeautyParameters);
    }

    public void clear() {
        clearAllRecords();
    }

    public void clearAllRecords() {
        this.mRenderView.clearAllRecords();
    }

    public void enableComparison(boolean z) {
        if (z) {
            this.mCompareBtn.setVisibility(0);
            this.mCompareBtn.bringToFront();
            this.mCompareBtn.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return MiuiBeautyRenderFragment.this.lambda$enableComparison$95$MiuiBeautyRenderFragment(view, motionEvent);
                }
            });
            return;
        }
        this.mCompareBtn.setVisibility(8);
    }

    public GLFragment.GLContext getGLContext() {
        return this.mGLContext;
    }

    public boolean isBeautyParamWorked() {
        return this.mBeautyParameters != null;
    }

    public boolean isComparisonEnable() {
        return this.mCompareBtn.getVisibility() != 8;
    }

    public boolean isEmpty() {
        return this.mBeautyParameterList.isEmpty();
    }

    public /* synthetic */ boolean lambda$enableComparison$95$MiuiBeautyRenderFragment(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (this.mOriginalImageView == null) {
                this.mOriginalImageView = new ImageView(getActivity());
                this.mOriginalImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                this.mOriginalImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            }
            this.mOriginalImageView.setImageBitmap(this.mCompareOrigin ? this.mPreProcessBitmap : this.mToBeCompared);
            this.mPreviewContainer.addView(this.mOriginalImageView, 1);
            this.mCompareBtn.setSelected(true);
            HashMap hashMap = new HashMap();
            hashMap.put("page", this.mEffect.name());
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "compare_button_touch", hashMap);
        } else if (1 == motionEvent.getAction() || 3 == motionEvent.getAction()) {
            this.mPreviewContainer.removeView(this.mOriginalImageView);
            this.mCompareBtn.setSelected(false);
        }
        return true;
    }

    public void nextRecord() {
        if (this.mCurrIterator.hasNext()) {
            this.mCurrIterator.next();
            this.mRecordTail = this.mCurrIterator.previousIndex();
        }
        this.mRenderView.renderNextBuffer();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBeautyParameterList = new LinkedList();
        this.mCurrIterator = this.mBeautyParameterList.listIterator();
        this.mRecordHead = -1;
        this.mRecordTail = -1;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.miui_beauty_fragment, viewGroup, false);
        this.mRenderView = (BeautyImageView) viewGroup2.findViewById(R.id.glsurfaceview_image);
        this.mRenderView.setInitRenderCallback(this);
        this.mPreProcessBitmap = MiuiBeautyEngine.preProcessBitmapForPreview(getBitmap());
        Bitmap bitmap = this.mPreProcessBitmap;
        this.mToBeCompared = bitmap.copy(bitmap.getConfig(), true);
        this.mRenderView.setPicData(this.mPreProcessBitmap);
        this.mCompareOrigin = true;
        this.mPreviewContainer = (FrameLayout) viewGroup2.findViewById(R.id.preview_container);
        this.mCompareBtn = (Button) viewGroup2.findViewById(R.id.compare_btn);
        this.mBeautyProgressBar = (ProgressBar) viewGroup2.findViewById(R.id.beauty_progress_bar);
        this.mStrokeBoardView = (StrokeBoardView) viewGroup2.findViewById(R.id.stroke_view);
        this.mStrokeBoardView.setBitmap(this.mPreProcessBitmap);
        return viewGroup2;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        if (this.mBeautyParameterList.isEmpty()) {
            return new MiuiBeautyRenderData((Map<BeautyParameterType, Float>) null);
        }
        MiuiBeautyRenderData miuiBeautyRenderData = new MiuiBeautyRenderData(this.mBeautyParameterList.get(0));
        for (int i = 1; i <= this.mRecordTail; i++) {
            miuiBeautyRenderData.addParams(this.mBeautyParameterList.get(i));
        }
        return miuiBeautyRenderData;
    }

    public void onRenderFinish() {
        this.mRenderView.postDelayed(new Runnable() {
            public void run() {
                MiuiBeautyRenderFragment.this.mGLContext.triggerCreated();
            }
        }, 900);
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        return null;
    }

    public void previousRecord() {
        if (this.mCurrIterator.hasPrevious()) {
            this.mCurrIterator.previous();
            this.mRecordTail = this.mCurrIterator.previousIndex();
        }
        this.mRenderView.renderPreviousBuffer();
    }

    public void recordCurrent() {
        if (this.mCurrIterator.hasNext()) {
            this.mCurrIterator.next();
            this.mCurrIterator.set(this.mBeautyParameters);
            this.mRecordTail = this.mCurrIterator.previousIndex();
        } else {
            this.mCurrIterator.add(this.mBeautyParameters);
            this.mRecordTail = this.mCurrIterator.previousIndex();
        }
        this.mRenderView.writeRecordFile();
        this.mRenderView.getBmpFromCurrBuffer(this.mToBeCompared);
    }

    public void remove(Metadata metadata) {
    }

    public void render() {
        new RenderTask(this).execute(new Void[0]);
    }

    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
    }

    public void setCompareOrigin(boolean z) {
        this.mCompareOrigin = z;
    }

    public void setOnBeautyProcessListener(OnBeautyProcessListener onBeautyProcessListener) {
        this.mOnBeautyProcessListener = onBeautyProcessListener;
    }

    public void setShowProgressDelay(boolean z) {
        this.mShowProgressDelay = z;
    }
}
