package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.miui.gallery.editor.blocksdk.Block;
import com.miui.gallery.editor.blocksdk.BlockSdkUtils;
import com.miui.gallery.editor.blocksdk.SplitUtils;
import com.miui.gallery.editor.photo.app.OperationUpdateListener;
import com.miui.gallery.editor.photo.core.imports.filter.render.OpenGlUtils;
import com.miui.gallery.editor.photo.core.imports.filter.render.PixelBuffer;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicOperationItem;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicRender;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicUndoManager;
import com.miui.gallery.editor.photo.widgets.glview.BitmapGestureGLView;
import com.miui.gallery.editor.photo.widgets.glview.shader.GLTextureShader;
import com.miui.gallery.util.CounterUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MosaicGLView extends BitmapGestureGLView {
    private MosaicUndoManager.CaptureListener mCaptureListener = new MosaicUndoManager.CaptureListener() {
        public void onCapture() {
            if (MosaicGLView.this.mOperationUpdateListener != null) {
                MosaicGLView.this.post(new Runnable() {
                    public void run() {
                        if (MosaicGLView.this.mOperationUpdateListener != null) {
                            MosaicGLView.this.mOperationUpdateListener.onOperationUpdate();
                        }
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public MosaicGLEntity mCurrentEntity;
    /* access modifiers changed from: private */
    public MosaicRender mCustomRender;
    private BitmapGestureGLView.FeatureGesListener mFeatureGesListener = new BitmapGestureGLView.FeatureGesListener() {
        private boolean mIsFirst;
        public float[] mMatrixValues = new float[9];
        private MosaicOperationItem.PaintingItem mPaintingItem;
        private float[] mPoint = new float[2];

        private void addPoint(float[] fArr, boolean z) {
            MosaicGLView.this.mMarkRect.left = fArr[0] - MosaicGLView.this.mPaintSizeScale;
            MosaicGLView.this.mMarkRect.right = fArr[0] + MosaicGLView.this.mPaintSizeScale;
            MosaicGLView.this.mMarkRect.top = fArr[1] - MosaicGLView.this.mPaintSizeScale;
            MosaicGLView.this.mMarkRect.bottom = fArr[1] + MosaicGLView.this.mPaintSizeScale;
            BitmapGestureGLView.generateVertexPositionToBitmap(MosaicGLView.this.mMarkRect, MosaicGLView.this.mMarkPosition, (float) MosaicGLView.this.mOriginBitmap.getWidth(), (float) MosaicGLView.this.mOriginBitmap.getHeight());
            GLRectF gLRectF = new GLRectF(MosaicGLView.this.mMarkPosition);
            if (z) {
                MosaicGLView.this.mCustomRender.drawRect(gLRectF, true);
            } else {
                MosaicGLView.this.mCustomRender.drawRect(gLRectF, false);
            }
            this.mPaintingItem.add(gLRectF);
            MosaicGLView.this.requestRender();
        }

        public void onActionUp(float f, float f2) {
            if (!this.mPaintingItem.isEmpty()) {
                MosaicGLView.this.mMosaicUndoManager.record(this.mPaintingItem, MosaicGLView.this.mCurrentEntity, true);
                MosaicGLView.this.mCustomRender.capture(MosaicGLView.this.mMosaicUndoManager);
                MosaicGLView.this.requestRender();
            }
        }

        public boolean onDown(MotionEvent motionEvent) {
            MosaicGLView.this.mBitmapGestureParamsHolder.mCanvasMatrix.getValues(this.mMatrixValues);
            float f = this.mMatrixValues[0];
            MosaicGLView mosaicGLView = MosaicGLView.this;
            float unused = mosaicGLView.mPaintSizeScale = ((mosaicGLView.mPaintSize * ((float) MosaicGLView.this.mOriginBitmap.getWidth())) / ((float) ScreenUtils.getScreenWidth())) / f;
            this.mPaintingItem = new MosaicOperationItem.PaintingItem();
            this.mIsFirst = true;
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
            if (this.mIsFirst) {
                MosaicGLView.this.mBitmapGestureParamsHolder.convertPointToBitmapCoordinate(motionEvent, this.mPoint);
                addPoint(this.mPoint, true);
                this.mIsFirst = false;
            }
            MosaicGLView.this.mBitmapGestureParamsHolder.convertPointToBitmapCoordinate(motionEvent2, this.mPoint);
            addPoint(this.mPoint, false);
        }

        public void onSingleTapUp(MotionEvent motionEvent) {
            MosaicGLView.this.mBitmapGestureParamsHolder.convertPointToBitmapCoordinate(motionEvent, this.mPoint);
            addPoint(this.mPoint, true);
        }
    };
    private float[] mGLPosition = new float[8];
    private float[] mGLPositionReverse = new float[8];
    private boolean mInit = false;
    /* access modifiers changed from: private */
    public float[] mMarkPosition = new float[8];
    /* access modifiers changed from: private */
    public RectF mMarkRect = new RectF();
    /* access modifiers changed from: private */
    public MosaicUndoManager mMosaicUndoManager;
    /* access modifiers changed from: private */
    public OperationUpdateListener mOperationUpdateListener;
    /* access modifiers changed from: private */
    public float mPaintSize = 200.0f;
    /* access modifiers changed from: private */
    public float mPaintSizeScale = this.mPaintSize;

    static class MosaicEntry implements Parcelable {
        public static final Parcelable.Creator<MosaicEntry> CREATOR = new Parcelable.Creator<MosaicEntry>() {
            public MosaicEntry createFromParcel(Parcel parcel) {
                return new MosaicEntry(parcel);
            }

            public MosaicEntry[] newArray(int i) {
                return new MosaicEntry[i];
            }
        };
        /* access modifiers changed from: private */
        public final LinkedList<MosaicOperationItem> mMosaicOperationItems;
        private final int mPreviewHeight;
        private final int mPreviewWidth;

        protected MosaicEntry(Parcel parcel) {
            this.mMosaicOperationItems = new LinkedList<>();
            parcel.readTypedList(this.mMosaicOperationItems, MosaicOperationItem.CREATOR);
            this.mPreviewWidth = parcel.readInt();
            this.mPreviewHeight = parcel.readInt();
        }

        MosaicEntry(LinkedList<MosaicOperationItem> linkedList, int i, int i2) {
            this.mMosaicOperationItems = new LinkedList<>(linkedList);
            this.mPreviewWidth = i;
            this.mPreviewHeight = i2;
        }

        private Bitmap getBitmapWithBlock(Bitmap bitmap, List<Block> list, float f) {
            List<Block> list2 = list;
            CounterUtil counterUtil = new CounterUtil("MosaicEntry");
            Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(ImageDownloader.Scheme.ASSETS.wrap(MosaicProvider.PEN_MASK_PATH));
            Block.TotalBlockInfo totalBlockInfo = list2.get(0).mTotalBlockInfo;
            int i = totalBlockInfo.mBlockWidth;
            final int i2 = totalBlockInfo.mBlockHeight;
            PixelBuffer pixelBuffer = new PixelBuffer(i, i2);
            int genTexture = OpenGlUtils.genTexture();
            final float f2 = f;
            AnonymousClass2 r12 = r0;
            final Bitmap bitmap2 = bitmap;
            CounterUtil counterUtil2 = counterUtil;
            int i3 = genTexture;
            final List<Block> list3 = list;
            AnonymousClass2 r0 = new MosaicRender(genTexture, loadImageSync, i, i2) {
                private int mOffsetHeight = 0;

                public void onDrawFrame(GL10 gl10) {
                    super.onDrawFrame(gl10);
                }

                public void onSurfaceChanged(GL10 gl10, int i, int i2) {
                    super.onSurfaceChanged(gl10, i, i2);
                    Log.d("MosaicEntry", "MosaicEntry init finish begin apply operation item, size:%d,width:%d,height:%d", Integer.valueOf(MosaicEntry.this.mMosaicOperationItems.size()), Integer.valueOf(i), Integer.valueOf(i2));
                    new MosaicRender.InitTask().run();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = MosaicEntry.this.mMosaicOperationItems.iterator();
                    while (it.hasNext()) {
                        MosaicOperationItem mosaicOperationItem = (MosaicOperationItem) it.next();
                        float f = (float) i2;
                        new MosaicRender.EnableEntityTask(mosaicOperationItem.mosaicGLEntity, f2, this.mOffsetHeight, ((float) i2) / f).run();
                        arrayList.clear();
                        Iterator it2 = mosaicOperationItem.paintingItems.iterator();
                        while (it2.hasNext()) {
                            MosaicOperationItem.PaintingItem paintingItem = new MosaicOperationItem.PaintingItem();
                            arrayList.add(paintingItem);
                            Iterator it3 = ((MosaicOperationItem.PaintingItem) it2.next()).points.iterator();
                            while (it3.hasNext()) {
                                GLRectF gLRectF = (GLRectF) it3.next();
                                GLRectF gLRectF2 = new GLRectF(gLRectF);
                                paintingItem.add(gLRectF2);
                                gLRectF2.setTop(((((float) bitmap2.getHeight()) / f) * (gLRectF.getTop() - 1.0f)) + ((((float) this.mOffsetHeight) / f) * 2.0f) + 1.0f);
                                gLRectF2.setBottom(((((float) bitmap2.getHeight()) / f) * (gLRectF.getBottom() - 1.0f)) + ((((float) this.mOffsetHeight) / f) * 2.0f) + 1.0f);
                            }
                        }
                        drawMaskPaintingItems(arrayList);
                    }
                    this.mOffsetHeight += this.mBlock.mHeight;
                    System.arraycopy(GLTextureShader.CUBE, 0, this.mGLPosition, 0, this.mGLPosition.length);
                    if (list3.indexOf(this.mBlock) == list3.size() - 1) {
                        new MosaicRender.DestroyBufferTask().run();
                    }
                }

                public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
                    super.onSurfaceCreated(gl10, eGLConfig);
                    Log.d("MosaicEntry", "MosaicEntry onSurfaceCreated");
                }
            };
            BlockSdkUtils.bindBitmap(bitmap);
            for (int i4 = 0; i4 < list.size(); i4++) {
                Block block = list2.get(i4);
                BlockSdkUtils.updateTextureWidthStride(i3, i, block.mHeight, i, block.mOffset * 4);
                r12.mBlock = block;
                pixelBuffer.resetViewSize(list2.get(i4).mWidth, list2.get(i4).mHeight);
                pixelBuffer.setRenderer(r12);
                pixelBuffer.draw();
                BlockSdkUtils.readPixelsAndMerge(0, 0, block.mWidth, block.mHeight, block.mWidth, block.mOffset * 4);
            }
            BlockSdkUtils.unbindBitmap(bitmap);
            pixelBuffer.destroy();
            CounterUtil counterUtil3 = counterUtil2;
            counterUtil3.tick(String.format("mosaic render for block done,block size %d", new Object[]{Integer.valueOf(totalBlockInfo.mTotalRow)}));
            return bitmap;
        }

        public Bitmap apply(Bitmap bitmap) {
            Log.d("MosaicEntry", "MosaicEntry apply mosaic start! bitmap width : %d height : %d", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
            if (this.mMosaicOperationItems.isEmpty()) {
                Log.d("MosaicEntry", "MosaicEntry operationItem size zero return null!");
                return null;
            }
            final float f = 1.0f;
            if (!(this.mPreviewWidth == bitmap.getWidth() && this.mPreviewHeight == bitmap.getHeight())) {
                f = Math.max(((float) bitmap.getWidth()) / ((float) this.mPreviewWidth), ((float) bitmap.getHeight()) / ((float) this.mPreviewHeight));
            }
            List<Block> split = SplitUtils.split(bitmap.getWidth(), bitmap.getHeight(), (int) (100.0f * f), false, true);
            if (split != null) {
                return getBitmapWithBlock(bitmap, split, f);
            }
            CounterUtil counterUtil = new CounterUtil("MosaicEntry");
            Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(ImageDownloader.Scheme.ASSETS.wrap(MosaicProvider.PEN_MASK_PATH));
            PixelBuffer pixelBuffer = new PixelBuffer(bitmap.getWidth(), bitmap.getHeight());
            pixelBuffer.setRenderer(new MosaicRender(bitmap, loadImageSync) {
                public void onDrawFrame(GL10 gl10) {
                    super.onDrawFrame(gl10);
                }

                public void onSurfaceChanged(GL10 gl10, int i, int i2) {
                    super.onSurfaceChanged(gl10, i, i2);
                    new MosaicRender.InitTask().run();
                    Log.d("MosaicEntry", "MosaicEntry init finish begin apply operation item, size : %d", (Object) Integer.valueOf(MosaicEntry.this.mMosaicOperationItems.size()));
                    Iterator it = MosaicEntry.this.mMosaicOperationItems.iterator();
                    while (it.hasNext()) {
                        MosaicOperationItem mosaicOperationItem = (MosaicOperationItem) it.next();
                        new MosaicRender.EnableEntityTask(mosaicOperationItem.mosaicGLEntity, f).run();
                        drawMaskPaintingItems(mosaicOperationItem.paintingItems);
                    }
                    System.arraycopy(GLTextureShader.CUBE, 0, this.mGLPosition, 0, this.mGLPosition.length);
                    new MosaicRender.DestroyBufferTask().run();
                }

                public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
                    super.onSurfaceCreated(gl10, eGLConfig);
                    Log.d("MosaicEntry", "MosaicEntry onSurfaceCreated");
                }
            });
            pixelBuffer.getBitmap(bitmap);
            pixelBuffer.destroy();
            counterUtil.tick("mosaic render done");
            return bitmap;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(this.mMosaicOperationItems);
            parcel.writeInt(this.mPreviewWidth);
            parcel.writeInt(this.mPreviewHeight);
        }
    }

    public MosaicGLView(Context context) {
        super(context);
        init();
    }

    private void init() {
    }

    private void refreshGLPosition() {
        RectF rectF = this.mBitmapGestureParamsHolder.mBitmapDisplayRect;
        generateVertexPosition(rectF, this.mGLPosition);
        generateVertexPositionReverse(rectF, this.mGLPositionReverse);
        System.arraycopy(this.mGLPositionReverse, 0, this.mCustomRender.mGLPosition, 0, this.mGLPositionReverse.length);
    }

    public boolean canRevert() {
        return this.mMosaicUndoManager.canRevert();
    }

    public boolean canRevoke() {
        return this.mMosaicUndoManager.canRevoke();
    }

    public void doRevert() {
        this.mCustomRender.enableCapture(this.mMosaicUndoManager.doRevert());
        requestRender();
        OperationUpdateListener operationUpdateListener = this.mOperationUpdateListener;
        if (operationUpdateListener != null) {
            operationUpdateListener.onOperationUpdate();
        }
    }

    public void doRevoke() {
        this.mCustomRender.enableCapture(this.mMosaicUndoManager.doRevoke());
        requestRender();
        OperationUpdateListener operationUpdateListener = this.mOperationUpdateListener;
        if (operationUpdateListener != null) {
            operationUpdateListener.onOperationUpdate();
        }
    }

    public MosaicEntry export() {
        return new MosaicEntry(this.mMosaicUndoManager.exportRecord(), this.mOriginBitmap.getWidth(), this.mOriginBitmap.getHeight());
    }

    public List<String> generateSample() {
        MosaicUndoManager mosaicUndoManager = this.mMosaicUndoManager;
        if (mosaicUndoManager == null) {
            return null;
        }
        return mosaicUndoManager.generateSample();
    }

    public boolean isEmpty() {
        MosaicUndoManager mosaicUndoManager = this.mMosaicUndoManager;
        return mosaicUndoManager == null || mosaicUndoManager.isEmpty();
    }

    /* access modifiers changed from: protected */
    public void onBitmapMatrixChange() {
        if (!this.mInit) {
            this.mMosaicUndoManager = new MosaicUndoManager(this.mOriginBitmap.getWidth(), this.mOriginBitmap.getHeight(), getWidth(), getHeight());
            this.mMosaicUndoManager.setCaptureListener(this.mCaptureListener);
            this.mCustomRender = new MosaicRender(this.mOriginBitmap, ImageLoader.getInstance().loadImageSync(ImageDownloader.Scheme.ASSETS.wrap(MosaicProvider.PEN_MASK_PATH)));
            setRenderer(this.mCustomRender);
            setFeatureGestureListener(this.mFeatureGesListener);
            setRenderMode(0);
            this.mCustomRender.init(this.mCurrentEntity);
            this.mCustomRender.capture(this.mMosaicUndoManager);
            this.mInit = true;
        }
        refreshGLPosition();
        requestRender();
    }

    /* access modifiers changed from: protected */
    public void onCanvasMatrixChange() {
        refreshGLPosition();
        requestRender();
    }

    public void onClear() {
        MosaicUndoManager mosaicUndoManager = this.mMosaicUndoManager;
        if (mosaicUndoManager != null) {
            mosaicUndoManager.clearBuffer();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mInit) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCurrentEntity(MosaicGLEntity mosaicGLEntity) {
        this.mCurrentEntity = mosaicGLEntity;
        MosaicRender mosaicRender = this.mCustomRender;
        if (mosaicRender != null) {
            mosaicRender.enableEntity(this.mCurrentEntity);
            requestRender();
        }
    }

    public void setMosaicPaintSize(int i) {
        this.mPaintSize = (float) i;
    }

    public void setOperationUpdateListener(OperationUpdateListener operationUpdateListener) {
        this.mOperationUpdateListener = operationUpdateListener;
    }
}
