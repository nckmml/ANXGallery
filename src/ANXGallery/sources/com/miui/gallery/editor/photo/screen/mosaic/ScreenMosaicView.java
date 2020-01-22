package com.miui.gallery.editor.photo.screen.mosaic;

import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseGestureView;
import com.miui.gallery.editor.photo.screen.base.ScreenVirtualEditorView;
import com.miui.gallery.editor.photo.screen.core.ScreenProviderManager;
import com.miui.gallery.editor.photo.screen.home.ScreenEditorView;
import com.miui.gallery.editor.photo.screen.mosaic.RenderThread;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicData;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicShaderHolder;
import java.util.ArrayList;

public class ScreenMosaicView extends ScreenVirtualEditorView implements IScreenMosaicOperation {
    /* access modifiers changed from: private */
    public boolean mAllowDraw = false;
    private MosaicEntity mCurrentEntity;
    private int mCurrentMenuItemIndex = 0;
    /* access modifiers changed from: private */
    public MosaicShaderHolder mCurrentShaderHolder;
    private GesListener mGesListener = new GesListener();
    private ArrayList<MosaicNode> mMosaicNodeList = new ArrayList<>();
    private Paint mPaint;
    private float mPaintSize = 98.0f;
    private RenderThread.RenderListener mRenderListener = new RenderThread.RenderListener() {
        public void onShaderComplete(MosaicShaderHolder mosaicShaderHolder) {
            MosaicShaderHolder unused = ScreenMosaicView.this.mCurrentShaderHolder = mosaicShaderHolder;
            boolean unused2 = ScreenMosaicView.this.mAllowDraw = true;
        }
    };
    private RenderThread mRenderThread;

    private class GesListener implements ScreenBaseGestureView.FeatureGesListener {
        private MosaicNode mCurrentNode;
        public float[] mPoint;

        private GesListener() {
            this.mPoint = new float[2];
        }

        private void generateMosaic(float f, float f2) {
            if (this.mCurrentNode == null) {
                this.mCurrentNode = new MosaicPathNode();
                ScreenMosaicView.this.addNewItem(this.mCurrentNode);
            }
            this.mCurrentNode.receivePosition(f, f2);
        }

        public void onActionUp(float f, float f2) {
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.mCurrentNode = null;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ScreenMosaicView.this.mAllowDraw) {
                if (this.mCurrentNode == null) {
                    ScreenMosaicView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent, this.mPoint);
                    float[] fArr = this.mPoint;
                    generateMosaic(fArr[0], fArr[1]);
                }
                ScreenMosaicView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent2, this.mPoint);
                if (motionEvent2.getPointerCount() == 1) {
                    float[] fArr2 = this.mPoint;
                    generateMosaic(fArr2[0], fArr2[1]);
                }
                ScreenMosaicView.this.invalidate();
            }
        }

        public void onSingleTapUp(MotionEvent motionEvent) {
        }
    }

    public ScreenMosaicView(ScreenEditorView screenEditorView) {
        super(screenEditorView);
        init();
    }

    /* access modifiers changed from: private */
    public void addNewItem(MosaicNode mosaicNode) {
        mosaicNode.setPaintSize(this.mPaintSize / getBitmapGestureParamsHolder().getMatrixValues()[0]);
        BitmapShader bitmapShader = this.mCurrentShaderHolder.getBitmapShader();
        if (this.mCurrentShaderHolder.type != MosaicEntity.TYPE.BITMAP) {
            bitmapShader.setLocalMatrix(getBitmapGestureParamsHolder().mBitmapToDisplayMatrix);
        }
        mosaicNode.setShader(bitmapShader);
        this.mMosaicNodeList.add(mosaicNode);
        addDrawNode(mosaicNode);
    }

    private void init() {
        this.mRenderThread = new RenderThread();
        this.mRenderThread.setRenderListener(this.mRenderListener);
        initPaint();
    }

    private void initPaint() {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setColor(-65536);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
    }

    private void setCurrentEntity(MosaicEntity mosaicEntity) {
        if (mosaicEntity != this.mCurrentEntity) {
            this.mCurrentEntity = mosaicEntity;
            if (this.mCurrentEntity.getMosaicShaderHolder() == null) {
                refreshShaderByCurrentShader();
                return;
            }
            this.mAllowDraw = true;
            this.mCurrentShaderHolder = this.mCurrentEntity.getMosaicShaderHolder();
        }
    }

    public void canvasMatrixChange() {
    }

    public void clearActivation() {
    }

    public void drawOverlay(Canvas canvas) {
    }

    public int getCurrentMenuItemIndex() {
        return this.mCurrentMenuItemIndex;
    }

    public void onChangeOperation(boolean z) {
        if (z) {
            this.mEditorView.setFeatureGestureListener(this.mGesListener);
        } else {
            this.mMosaicNodeList.clear();
        }
    }

    public void onDetachedFromWindow() {
        this.mRenderThread.setRenderListener((RenderThread.RenderListener) null);
        this.mRenderThread.quitSafely();
        ScreenMosaicProvider screenMosaicProvider = (ScreenMosaicProvider) ScreenProviderManager.INSTANCE.getProvider(ScreenMosaicProvider.class);
        if (screenMosaicProvider != null) {
            screenMosaicProvider.clearShader();
        }
    }

    public void refreshShaderByCurrentShader() {
        this.mAllowDraw = false;
        this.mRenderThread.sendGenerateShaderMsg(this.mCurrentEntity, this.mEditorView.getOriginBitmap(), this.mEditorView.getBitmapGestureParamsHolder().mBitmapToDisplayMatrix);
    }

    public void setMosaicData(MosaicData mosaicData, int i) {
        setCurrentEntity((MosaicEntity) mosaicData);
        this.mCurrentMenuItemIndex = i;
    }

    public void setMosaicPaintSize(int i) {
        this.mPaintSize = (float) i;
    }
}
