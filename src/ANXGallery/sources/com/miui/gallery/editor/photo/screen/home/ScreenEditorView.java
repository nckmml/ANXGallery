package com.miui.gallery.editor.photo.screen.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.miui.gallery.editor.photo.core.common.model.BaseDrawNode;
import com.miui.gallery.editor.photo.core.imports.doodle.DoodleManager;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;
import com.miui.gallery.editor.photo.screen.base.IScreenOperation;
import com.miui.gallery.editor.photo.screen.base.IScreenOperationEditor;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseGestureView;
import com.miui.gallery.editor.photo.screen.core.ScreenDrawEntry;
import com.miui.gallery.editor.photo.screen.core.ScreenProviderManager;
import com.miui.gallery.editor.photo.screen.core.ScreenRenderData;
import com.miui.gallery.editor.photo.screen.crop.ScreenCropView;
import com.miui.gallery.editor.photo.screen.doodle.ScreenDoodleView;
import com.miui.gallery.editor.photo.screen.home.ScreenEditViewAnimatorHelper;
import com.miui.gallery.editor.photo.screen.mosaic.ScreenMosaicProvider;
import com.miui.gallery.editor.photo.screen.mosaic.ScreenMosaicView;
import com.miui.gallery.editor.photo.screen.text.ScreenTextDrawNode;
import com.miui.gallery.editor.photo.screen.text.ScreenTextView;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScreenEditorView extends ScreenBaseGestureView implements IScreenEditorController {
    private ScreenEditViewAnimatorHelper mAnimatorHelper;
    private IScreenOperationEditor mCurrentScreenEditor;
    private List<BaseDrawNode> mDrawNodeList = new ArrayList();
    private boolean mIsLongCrop;
    private List<BaseDrawNode> mLastNodeList = new ArrayList();
    private IScreenOperationEditor mLastScreenEditor;
    private LongScreenshotCropEditorView.Entry mLongCropEntry = new LongScreenshotCropEditorView.Entry();
    private RectF mLongCropFullBitmapRect;
    private OperationUpdateListener mOperationUpdateListener;
    private LinkedList<BaseDrawNode> mRevokedNodeList = new LinkedList<>();
    private List<BaseDrawNode> mSavingNodeList = new ArrayList();
    private ScreenCropView mScreenCrop;
    private ScreenDoodleView mScreenDoodle;
    private ScreenEditViewAnimatorHelper.Callback mScreenEditViewAnimatorCallback = new ScreenEditViewAnimatorHelper.Callback() {
        public Bitmap getOriginBitmap() {
            return ScreenEditorView.this.mOriginBitmap;
        }

        public RectF getShowRect() {
            return ScreenEditorView.this.getBitmapGestureParamsHolder().mBitmapDisplayInitRect;
        }

        public void onAnimationStart() {
            if (ScreenEditorView.this.mThumbnailAnimatorCallback != null) {
                ScreenEditorView.this.mThumbnailAnimatorCallback.onAnimationStart();
            }
        }

        public void onAnimationUpdate(float f) {
            if (ScreenEditorView.this.mThumbnailAnimatorCallback != null) {
                ScreenEditorView.this.mThumbnailAnimatorCallback.onAnimationUpdate(f);
            }
        }

        public void onInvalidate() {
            ScreenEditorView.this.invalidate();
        }
    };
    private ScreenMosaicView mScreenMosaic;
    private ScreenTextView mScreenText;
    /* access modifiers changed from: private */
    public ThumbnailAnimatorCallback mThumbnailAnimatorCallback;
    private int mTopPixel;

    public ScreenEditorView(Context context) {
        super(context);
    }

    public ScreenEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScreenEditorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void clearRevokeNode() {
        this.mRevokedNodeList.clear();
    }

    private void drawToDisplayCanvas() {
        if (this.mDrawNodeList.size() > 10) {
            this.mDisplayCanvas.save();
            this.mDisplayCanvas.concat(this.mBitmapGestureParamsHolder.mDisplayToBitmapMatrix);
            for (int i = 0; i < this.mDrawNodeList.size() - 1; i++) {
                this.mDrawNodeList.get(i).draw(this.mDisplayCanvas, getLongCropFullDisplayRect());
            }
            this.mDisplayCanvas.restore();
            List<BaseDrawNode> list = this.mDrawNodeList;
            this.mSavingNodeList.addAll(this.mDrawNodeList);
            this.mDrawNodeList.clear();
            this.mDrawNodeList.add(list.remove(list.size() - 1));
        }
    }

    private RectF getLongCropFullDisplayRect() {
        return this.mLongCropFullBitmapRect;
    }

    private void setLongCropFullBitmapRect() {
        if (this.mIsLongCrop) {
            RectF rectF = new RectF(this.mBitmapGestureParamsHolder.mBitmapDisplayInitRect);
            float height = rectF.height() / (this.mLongCropEntry.mBottomRatio - this.mLongCropEntry.mTopRatio);
            rectF.top -= this.mLongCropEntry.mTopRatio * height;
            rectF.bottom += height * (1.0f - this.mLongCropEntry.mBottomRatio);
            this.mLongCropFullBitmapRect = rectF;
        }
    }

    public void addDrawNode(BaseDrawNode baseDrawNode) {
        this.mDrawNodeList.add(baseDrawNode);
        baseDrawNode.setBitmapRect(getLongCropFullDisplayRect());
        clearRevokeNode();
        notifyOperationUpdate();
    }

    public void addRevokedDrawNode(BaseDrawNode baseDrawNode) {
        this.mRevokedNodeList.add(baseDrawNode);
    }

    public boolean canRevert() {
        return !this.mRevokedNodeList.isEmpty();
    }

    public boolean canRevoke() {
        IScreenOperationEditor iScreenOperationEditor = this.mCurrentScreenEditor;
        ScreenTextView screenTextView = this.mScreenText;
        return (iScreenOperationEditor == screenTextView && screenTextView.canRevoke()) || !this.mDrawNodeList.isEmpty() || !this.mSavingNodeList.isEmpty();
    }

    public void checkTextEditor(boolean z) {
        IScreenOperationEditor iScreenOperationEditor = this.mCurrentScreenEditor;
        ScreenTextView screenTextView = this.mScreenText;
        if (iScreenOperationEditor == screenTextView) {
            screenTextView.onChangeOperation(!z);
        }
    }

    public void doRevert() {
        this.mCurrentScreenEditor.clearActivation();
        BaseDrawNode removeLast = this.mRevokedNodeList.removeLast();
        if (this.mCurrentScreenEditor != this.mScreenText || !(removeLast instanceof ScreenTextDrawNode) || ((ScreenTextDrawNode) removeLast).isSaved()) {
            this.mDrawNodeList.add(removeLast);
            invalidate();
            notifyOperationUpdate();
            return;
        }
        this.mScreenText.doRevert();
        notifyOperationUpdate();
    }

    public void doRevoke() {
        this.mCurrentScreenEditor.clearActivation();
        IScreenOperationEditor iScreenOperationEditor = this.mCurrentScreenEditor;
        ScreenTextView screenTextView = this.mScreenText;
        if (iScreenOperationEditor != screenTextView || !screenTextView.canRevoke()) {
            if (this.mDrawNodeList.size() <= 0) {
                if (this.mSavingNodeList.size() >= 10) {
                    List<BaseDrawNode> list = this.mDrawNodeList;
                    List<BaseDrawNode> list2 = this.mSavingNodeList;
                    list.addAll(list2.subList(list2.size() - 10, this.mSavingNodeList.size()));
                    List<BaseDrawNode> list3 = this.mSavingNodeList;
                    this.mSavingNodeList = list3.subList(0, list3.size() - 10);
                } else {
                    this.mDrawNodeList.addAll(this.mSavingNodeList);
                    this.mSavingNodeList.clear();
                }
                refreshDisplayCanvas();
            }
            List<BaseDrawNode> list4 = this.mDrawNodeList;
            BaseDrawNode baseDrawNode = list4.get(list4.size() - 1);
            this.mDrawNodeList.remove(baseDrawNode);
            this.mRevokedNodeList.add(baseDrawNode);
            invalidate();
            notifyOperationUpdate();
            return;
        }
        this.mScreenText.doRevoke();
        notifyOperationUpdate();
    }

    public void export() {
        IScreenOperationEditor iScreenOperationEditor = this.mCurrentScreenEditor;
        ScreenTextView screenTextView = this.mScreenText;
        if (iScreenOperationEditor == screenTextView) {
            screenTextView.onChangeOperation(false);
        }
    }

    public IScreenOperationEditor getCurrentScreenEditor() {
        return this.mCurrentScreenEditor;
    }

    public <T extends IScreenOperation> T getScreenOperation(Class<T> cls) {
        if (cls.isInstance(this.mScreenDoodle)) {
            return this.mScreenDoodle;
        }
        if (cls.isInstance(this.mScreenMosaic)) {
            return this.mScreenMosaic;
        }
        if (cls.isInstance(this.mScreenText)) {
            return this.mScreenText;
        }
        Log.e("ScreenEditorView", "getScreenOperation impossible error");
        return null;
    }

    public void init() {
        if (!this.mIsLongCrop) {
            this.mScreenCrop = new ScreenCropView(this);
        }
        setCurrentScreenEditor(2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = r1.mScreenCrop;
     */
    public boolean isModified() {
        ScreenCropView screenCropView;
        return this.mDrawNodeList.size() > 0 || this.mSavingNodeList.size() > 0 || (screenCropView != null && screenCropView.isModified());
    }

    public boolean isModifiedBaseLast() {
        boolean z = this.mLastNodeList.size() != this.mDrawNodeList.size() + this.mSavingNodeList.size() || !this.mLastNodeList.containsAll(this.mDrawNodeList) || !this.mLastNodeList.containsAll(this.mSavingNodeList);
        this.mLastNodeList.clear();
        this.mLastNodeList.addAll(this.mDrawNodeList);
        this.mLastNodeList.addAll(this.mSavingNodeList);
        if (z) {
            return true;
        }
        ScreenCropView screenCropView = this.mScreenCrop;
        return screenCropView != null && screenCropView.isModifiedBaseLast();
    }

    public void notifyOperationUpdate() {
        OperationUpdateListener operationUpdateListener = this.mOperationUpdateListener;
        if (operationUpdateListener != null) {
            operationUpdateListener.onOperationUpdate(isModified(), canRevoke(), canRevert());
        }
    }

    public void onBitmapMatrixChanged() {
        ScreenCropView screenCropView = this.mScreenCrop;
        if (screenCropView != null) {
            screenCropView.onStart();
        }
        invalidate();
    }

    public void onCanvasMatrixChange() {
        this.mCurrentScreenEditor.canvasMatrixChange();
        ScreenCropView screenCropView = this.mScreenCrop;
        if (screenCropView != null) {
            screenCropView.canvasMatrixChange();
        }
        invalidate();
        notifyOperationUpdate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScreenCropView screenCropView = this.mScreenCrop;
        if (screenCropView != null) {
            screenCropView.onDetachedFromWindow();
        }
        ScreenTextView screenTextView = this.mScreenText;
        if (screenTextView != null) {
            screenTextView.onDetachedFromWindow();
        }
        ScreenDoodleView screenDoodleView = this.mScreenDoodle;
        if (screenDoodleView != null) {
            screenDoodleView.onDetachedFromWindow();
        }
        ScreenMosaicView screenMosaicView = this.mScreenMosaic;
        if (screenMosaicView != null) {
            screenMosaicView.onDetachedFromWindow();
        }
        this.mThumbnailAnimatorCallback = null;
    }

    public void onDraw(Canvas canvas) {
        if (this.mDisplayBitmap != null && !this.mDisplayBitmap.isRecycled()) {
            ScreenEditViewAnimatorHelper screenEditViewAnimatorHelper = this.mAnimatorHelper;
            if (screenEditViewAnimatorHelper == null || screenEditViewAnimatorHelper.isAnimatorEnd()) {
                drawToDisplayCanvas();
                canvas.save();
                canvas.concat(this.mBitmapGestureParamsHolder.mCanvasMatrix);
                canvas.drawBitmap(this.mDisplayBitmap, this.mBitmapGestureParamsHolder.mBitmapToDisplayMatrix, this.mBitmapPaint);
                canvas.clipRect(getBitmapGestureParamsHolder().mBitmapDisplayInitRect);
                for (BaseDrawNode draw : this.mDrawNodeList) {
                    draw.draw(canvas, getLongCropFullDisplayRect());
                }
                IScreenOperationEditor iScreenOperationEditor = this.mCurrentScreenEditor;
                ScreenTextView screenTextView = this.mScreenText;
                if (iScreenOperationEditor == screenTextView) {
                    screenTextView.drawCurrentNode(canvas, getLongCropFullDisplayRect());
                }
                canvas.restore();
                this.mCurrentScreenEditor.drawOverlay(canvas);
                ScreenCropView screenCropView = this.mScreenCrop;
                if (screenCropView != null) {
                    screenCropView.drawOverlay(canvas);
                    return;
                }
                return;
            }
            this.mAnimatorHelper.draw(canvas);
        }
    }

    public ScreenRenderData onExport() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSavingNodeList);
        arrayList.addAll(this.mDrawNodeList);
        ScreenDrawEntry screenDrawEntry = new ScreenDrawEntry(this.mIsLongCrop, this.mBitmapGestureParamsHolder.mBitmapDisplayInitRect, arrayList);
        ScreenCropView screenCropView = this.mScreenCrop;
        return new ScreenRenderData(screenDrawEntry, screenCropView == null ? null : screenCropView.export());
    }

    /* access modifiers changed from: protected */
    public void refreshDisplayCanvas() {
        this.mDisplayCanvas.drawBitmap(this.mOriginBitmap, 0.0f, (float) (-this.mTopPixel), this.mBitmapPaint);
        this.mDisplayCanvas.save();
        this.mDisplayCanvas.concat(this.mBitmapGestureParamsHolder.mDisplayToBitmapMatrix);
        for (BaseDrawNode draw : this.mSavingNodeList) {
            draw.draw(this.mDisplayCanvas, getLongCropFullDisplayRect());
        }
        this.mDisplayCanvas.restore();
    }

    public void removeDrawNode(BaseDrawNode baseDrawNode) {
        this.mDrawNodeList.remove(baseDrawNode);
        notifyOperationUpdate();
    }

    public void removeRevokedDrawNode(BaseDrawNode baseDrawNode) {
        this.mRevokedNodeList.remove(baseDrawNode);
    }

    public boolean setCurrentScreenEditor(int i) {
        this.mLastScreenEditor = this.mCurrentScreenEditor;
        IScreenOperationEditor iScreenOperationEditor = this.mLastScreenEditor;
        if (iScreenOperationEditor != null) {
            iScreenOperationEditor.onChangeOperation(false);
        }
        if (i == 2) {
            if (this.mScreenDoodle == null) {
                this.mScreenDoodle = new ScreenDoodleView(this);
                this.mScreenDoodle.setDoodleData(DoodleManager.getDefaultScreenDoodleData(), 0);
            }
            this.mCurrentScreenEditor = this.mScreenDoodle;
        } else if (i == 3) {
            if (this.mScreenText == null) {
                this.mScreenText = new ScreenTextView(this);
            }
            this.mCurrentScreenEditor = this.mScreenText;
        } else if (i == 4) {
            if (this.mScreenMosaic == null) {
                ScreenMosaicProvider screenMosaicProvider = (ScreenMosaicProvider) ScreenProviderManager.INSTANCE.getProvider(ScreenMosaicProvider.class);
                if (!screenMosaicProvider.isInitialized()) {
                    Log.w("ScreenEditorView", "ScreenMosaicProvider has not initialized");
                    return false;
                }
                this.mScreenMosaic = new ScreenMosaicView(this);
                this.mScreenMosaic.setMosaicData(screenMosaicProvider.getDefaultData(), 0);
            }
            this.mCurrentScreenEditor = this.mScreenMosaic;
        }
        IScreenOperationEditor iScreenOperationEditor2 = this.mCurrentScreenEditor;
        if (iScreenOperationEditor2 != null) {
            iScreenOperationEditor2.onChangeOperation(true);
        }
        notifyOperationUpdate();
        return true;
    }

    public void setLongCrop(boolean z) {
        this.mIsLongCrop = z;
    }

    public void setLongCropEntry(LongScreenshotCropEditorView.Entry entry) {
        if (!this.mLongCropEntry.equals(entry)) {
            this.mLongCropEntry = entry;
            setPreviewBitmap(this.mOriginBitmap);
        }
    }

    public void setOperationUpdateListener(OperationUpdateListener operationUpdateListener) {
        this.mOperationUpdateListener = operationUpdateListener;
    }

    public void setPreviewBitmap(Bitmap bitmap) {
        this.mOriginBitmap = bitmap;
        this.mTopPixel = (int) ((((float) bitmap.getHeight()) * this.mLongCropEntry.mTopRatio) + 0.5f);
        this.mDisplayBitmap = Bitmap.createBitmap(bitmap.getWidth(), ((int) ((((float) bitmap.getHeight()) * this.mLongCropEntry.mBottomRatio) + 0.5f)) - this.mTopPixel, Bitmap.Config.ARGB_8888);
        this.mBitmapGestureParamsHolder.setBitmap(this.mDisplayBitmap);
        setLongCropFullBitmapRect();
        this.mDisplayCanvas = new Canvas(this.mDisplayBitmap);
        refreshDisplayCanvas();
    }

    public void startScreenThumbnailAnimate(ThumbnailAnimatorCallback thumbnailAnimatorCallback) {
        if (this.mAnimatorHelper == null) {
            this.mAnimatorHelper = new ScreenEditViewAnimatorHelper();
        }
        this.mThumbnailAnimatorCallback = thumbnailAnimatorCallback;
        this.mAnimatorHelper.startEnterAnimation(getContext(), this.mScreenEditViewAnimatorCallback, thumbnailAnimatorCallback.getThumbnailRect());
    }
}
