package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.OperationUpdateListener;
import com.miui.gallery.editor.photo.core.GLFragment;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractMosaicFragment;
import com.miui.gallery.editor.photo.core.common.model.MosaicData;
import java.util.List;

public class MosaicFragment extends AbstractMosaicFragment implements SurfaceHolder.Callback, GLFragment {
    /* access modifiers changed from: private */
    public MosaicContext mMosaicContext = new MosaicContext();
    private MosaicGLView mMosaicView;
    private MosaicGLEntity mNextEntity;
    private int mNextPaintSize = -1;
    private OperationUpdateListener mOperationUpdateListener;

    private class MosaicContext extends GLFragment.GLContext {
        private MosaicContext() {
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

    public boolean canRevert() {
        return this.mMosaicView.canRevert();
    }

    public boolean canRevoke() {
        return this.mMosaicView.canRevoke();
    }

    public void clear() {
        this.mMosaicView.onClear();
    }

    public void doRevert() {
        this.mMosaicView.doRevert();
    }

    public void doRevoke() {
        this.mMosaicView.doRevoke();
    }

    public GLFragment.GLContext getGLContext() {
        return this.mMosaicContext;
    }

    public boolean isEmpty() {
        return this.mMosaicView.isEmpty();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = (FrameLayout) layoutInflater.inflate(R.layout.editor_mosaic_view_container, viewGroup, false);
        this.mMosaicView = new MosaicGLView(getActivity());
        this.mMosaicView.setBitmap(getBitmap());
        MosaicGLEntity mosaicGLEntity = this.mNextEntity;
        if (mosaicGLEntity != null) {
            this.mMosaicView.setCurrentEntity(mosaicGLEntity);
            this.mNextEntity = null;
        }
        int i = this.mNextPaintSize;
        if (i != 1) {
            this.mMosaicView.setMosaicPaintSize(i);
            this.mNextPaintSize = -1;
        }
        OperationUpdateListener operationUpdateListener = this.mOperationUpdateListener;
        if (operationUpdateListener != null) {
            this.mMosaicView.setOperationUpdateListener(operationUpdateListener);
            this.mOperationUpdateListener = null;
        }
        this.mMosaicView.postDelayed(new Runnable() {
            public void run() {
                MosaicFragment.this.mMosaicContext.surfaceCreated();
            }
        }, 1000);
        int dimensionPixelSize = getActivity().getResources().getDimensionPixelSize(R.dimen.render_view_margin_top);
        this.mMosaicView.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
        frameLayout.addView(this.mMosaicView, -1, -1);
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        return new MosaicRenderData(this.mMosaicView.export());
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        return this.mMosaicView.generateSample();
    }

    public void setMosaicData(MosaicData mosaicData) {
        MosaicGLEntity mosaicGLEntity = (MosaicGLEntity) mosaicData;
        MosaicGLView mosaicGLView = this.mMosaicView;
        if (mosaicGLView != null) {
            mosaicGLView.setCurrentEntity(mosaicGLEntity);
        } else {
            this.mNextEntity = mosaicGLEntity;
        }
    }

    public void setMosaicPaintSize(int i) {
        MosaicGLView mosaicGLView = this.mMosaicView;
        if (mosaicGLView != null) {
            mosaicGLView.setMosaicPaintSize(i);
        } else {
            this.mNextPaintSize = i;
        }
    }

    public void setOperationUpdateListener(OperationUpdateListener operationUpdateListener) {
        MosaicGLView mosaicGLView = this.mMosaicView;
        if (mosaicGLView != null) {
            mosaicGLView.setOperationUpdateListener(operationUpdateListener);
        } else {
            this.mOperationUpdateListener = operationUpdateListener;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mMosaicContext.surfaceDestroyed();
    }
}
