package com.miui.gallery.collage.render;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import com.miui.gallery.R;
import com.miui.gallery.collage.BitmapManager;
import com.miui.gallery.collage.ClipType;
import com.miui.gallery.collage.core.layout.LayoutItemModel;
import com.miui.gallery.collage.core.layout.LayoutModel;
import com.miui.gallery.collage.core.poster.CollagePositionModel;
import com.miui.gallery.collage.core.poster.PosterModel;
import com.miui.gallery.collage.widget.CollageImageView;
import com.miui.gallery.collage.widget.CollageLayout;
import com.miui.gallery.collage.widget.PosterLayout;
import com.miui.gallery.util.Log;
import java.util.Arrays;

public class CollageRender {

    /* renamed from: com.miui.gallery.collage.render.CollageRender$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$collage$ClipType = new int[ClipType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$collage$ClipType[ClipType.CIRCLE.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$collage$ClipType[ClipType.PATH.ordinal()] = 2;
        }
    }

    public static class BitmapRenderData {
        public Bitmap bitmap;
        public final RectF bitmapInsideRect = new RectF();
        public Drawable maskDrawable;
        public boolean mirror;
        public float radius;
        public int rotate;
        public boolean transition;

        public String toString() {
            return "BitmapRenderData{bitmap=" + this.bitmap + ", rotate=" + this.rotate + ", mirror=" + this.mirror + ", transition=" + this.transition + ", bitmapInsideRect=" + this.bitmapInsideRect + '}';
        }
    }

    public static class ImageLocation {
        /* access modifiers changed from: private */
        public int mBottom;
        final float[] mClipArray;
        final ClipType mClipType;
        /* access modifiers changed from: private */
        public int mLeft;
        final Path mPathForClip = new Path();
        final Region mPathRegion = new Region();
        /* access modifiers changed from: private */
        public int mRight;
        /* access modifiers changed from: private */
        public int mTop;

        public ImageLocation(ClipType clipType, float[] fArr) {
            this.mClipType = clipType;
            this.mClipArray = fArr;
        }

        public int getBottom() {
            return this.mBottom;
        }

        public int getLeft() {
            return this.mLeft;
        }

        public Path getPathForClip() {
            return this.mPathForClip;
        }

        public Region getPathRegion() {
            return this.mPathRegion;
        }

        public int getRight() {
            return this.mRight;
        }

        public int getTop() {
            return this.mTop;
        }
    }

    public static class ImageLocationProcessor {
        private float[] mClipArray;
        private ImageLocation mImageLocation;
        private Matrix mMatrix = new Matrix();
        private RectF mPathRectF = new RectF();
        private Region mPathRegion = new Region();

        private void enableMargin(float f, float f2, float f3, boolean z) {
            float f4;
            float f5 = 0.0f;
            if (f != 0.0f) {
                Path path = this.mImageLocation.mPathForClip;
                int i = AnonymousClass1.$SwitchMap$com$miui$gallery$collage$ClipType[this.mImageLocation.mClipType.ordinal()];
                int i2 = 2;
                char c = 1;
                if (i == 1) {
                    path.reset();
                    float[] fArr = this.mClipArray;
                    path.addOval(new RectF(fArr[0] + f, fArr[1] + f, fArr[2] - f, fArr[3] - f), Path.Direction.CW);
                    path.offset((float) Math.round(f), (float) Math.round(f));
                    path.computeBounds(this.mPathRectF, true);
                    this.mPathRegion.setEmpty();
                    this.mPathRegion.setPath(path, new Region((int) this.mPathRectF.left, (int) this.mPathRectF.top, (int) this.mPathRectF.right, (int) this.mPathRectF.bottom));
                } else if (i == 2) {
                    Path path2 = new Path();
                    RectF rectF = new RectF();
                    Region region = new Region();
                    int i3 = 0;
                    while (true) {
                        float[] fArr2 = this.mClipArray;
                        if (i3 < fArr2.length) {
                            float[] fArr3 = new float[i2];
                            float[] fArr4 = new float[i2];
                            fArr3[0] = fArr2[i3];
                            fArr3[c] = fArr2[i3 + 1];
                            if (i3 == fArr2.length - i2) {
                                fArr4[0] = fArr2[0];
                                fArr4[c] = fArr2[c];
                            } else {
                                fArr4[0] = fArr2[i3 + 2];
                                fArr4[c] = fArr2[i3 + 3];
                            }
                            float f6 = fArr3[0];
                            float f7 = fArr3[c];
                            float f8 = fArr4[0];
                            float f9 = fArr4[c];
                            if (((f6 != f8 ? !(f7 == f9 && (f7 == f5 || f7 == f3)) : !(f6 == f5 || f6 == f2)) ? 0 : c) != 0) {
                                f4 = z ? f5 : 2.0f * f;
                            } else {
                                f4 = f;
                            }
                            Region region2 = region;
                            double degrees = Math.toDegrees(Math.atan2((double) (fArr4[c] - fArr3[c]), (double) (fArr4[0] - fArr3[0])));
                            Log.d("CollageRender", "xStart:%f yStart:%f xEnd:%f yEnd:%f degree：%f", Float.valueOf(fArr3[0]), Float.valueOf(fArr3[1]), Float.valueOf(fArr4[0]), Float.valueOf(fArr4[1]), Double.valueOf(degrees));
                            this.mMatrix.reset();
                            this.mMatrix.postRotate((float) (-degrees));
                            this.mMatrix.postTranslate(0.0f, -f4);
                            this.mMatrix.postRotate((float) degrees);
                            this.mMatrix.mapPoints(fArr3);
                            this.mMatrix.mapPoints(fArr4);
                            Log.d("CollageRender", "xStart:%f yStart:%f", Float.valueOf(fArr3[0]), Float.valueOf(fArr3[1]));
                            path2.reset();
                            path2.moveTo(f6, f7);
                            path2.lineTo(f8, f9);
                            path2.lineTo(fArr4[0], fArr4[1]);
                            path2.lineTo(fArr3[0], fArr3[1]);
                            path2.close();
                            RectF rectF2 = rectF;
                            path2.computeBounds(rectF2, true);
                            region2.setEmpty();
                            Region region3 = region2;
                            region3.setPath(path2, new Region((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom));
                            this.mPathRegion.op(region3, Region.Op.DIFFERENCE);
                            rectF = rectF2;
                            region = region3;
                            f5 = 0.0f;
                            i2 = 2;
                            c = 1;
                            i3 += 2;
                        } else {
                            path.reset();
                            this.mPathRegion.getBoundaryPath(path);
                            path.computeBounds(this.mPathRectF, true);
                            return;
                        }
                    }
                }
            }
        }

        private void generateClipArrayBySize(float f, float f2) {
            float[] fArr = this.mImageLocation.mClipArray;
            this.mClipArray = new float[fArr.length];
            for (int i = 0; i < fArr.length; i += 2) {
                float[] fArr2 = this.mClipArray;
                fArr2[i] = fArr[i] * f;
                int i2 = i + 1;
                fArr2[i2] = fArr[i2] * f2;
            }
        }

        private void generateLayoutSizeByRect() {
            int unused = this.mImageLocation.mLeft = Math.round(this.mPathRectF.left);
            int unused2 = this.mImageLocation.mTop = Math.round(this.mPathRectF.top);
            int unused3 = this.mImageLocation.mRight = Math.round(this.mPathRectF.right);
            int unused4 = this.mImageLocation.mBottom = Math.round(this.mPathRectF.bottom);
            this.mImageLocation.mPathRegion.set(this.mPathRegion);
        }

        private void generatePath() {
            Path path = this.mImageLocation.mPathForClip;
            path.reset();
            int i = 0;
            if (AnonymousClass1.$SwitchMap$com$miui$gallery$collage$ClipType[this.mImageLocation.mClipType.ordinal()] != 1) {
                while (true) {
                    float[] fArr = this.mClipArray;
                    if (i >= fArr.length) {
                        break;
                    }
                    float f = fArr[i];
                    float f2 = fArr[i + 1];
                    if (path.isEmpty()) {
                        path.moveTo(f, f2);
                    } else {
                        path.lineTo(f, f2);
                    }
                    i += 2;
                }
                path.close();
            } else {
                float[] fArr2 = this.mClipArray;
                path.addOval(new RectF(fArr2[0], fArr2[1], fArr2[2], fArr2[3]), Path.Direction.CW);
            }
            path.computeBounds(this.mPathRectF, true);
            this.mPathRegion.setEmpty();
            this.mPathRegion.setPath(path, new Region((int) this.mPathRectF.left, (int) this.mPathRectF.top, (int) this.mPathRectF.right, (int) this.mPathRectF.bottom));
        }

        public void processorImageLocation(ImageLocation imageLocation, float f, float f2, float f3, boolean z) {
            this.mImageLocation = imageLocation;
            if (f3 > 0.0f) {
                generateClipArrayBySize(f, f2);
                generatePath();
                enableMargin(f3, f, f2, z);
            } else {
                generateClipArrayBySize(f, f2);
                generatePath();
            }
            generateLayoutSizeByRect();
        }
    }

    public static class RenderData {
        public BitmapRenderData[] bitmapRenders;
        public boolean igonreMarginEdge;
        public int imageHeight;
        public int imageWidth;
        public LayoutModel layoutModel;
        public float margin;
        public PosterElementRender posterElementRender;
        public PosterModel posterModel;
        public float scaleOffset = 1.0f;

        public String toString() {
            return "RenderData{imageWidth=" + this.imageWidth + ", imageHeight=" + this.imageHeight + ", scaleOffset=" + this.scaleOffset + ", margin=" + this.margin + ", bitmapRenders=" + Arrays.toString(this.bitmapRenders) + '}';
        }
    }

    public static void doRender(Canvas canvas, RenderData renderData, BitmapManager bitmapManager) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        RectF rectF = new RectF();
        float[] fArr = renderData.posterModel != null ? CollagePositionModel.getCollagePositionModelByImageSize(renderData.posterModel.collagePositions, renderData.bitmapRenders.length).position : PosterLayout.DEFAULT_LAYOUT_PARAMS;
        float f = (float) width;
        float f2 = (float) height;
        rectF.set((float) ((int) (fArr[0] * f)), (float) ((int) (fArr[1] * f2)), (float) ((int) (f * fArr[2])), (float) ((int) (f2 * fArr[3])));
        canvas.drawColor(-1);
        drawBitmap(canvas, renderData, rectF, bitmapManager);
        PosterElementRender posterElementRender = renderData.posterElementRender;
        if (posterElementRender != null) {
            posterElementRender.draw(canvas);
        }
    }

    private static void drawBitmap(Canvas canvas, RenderData renderData, RectF rectF, BitmapManager bitmapManager) {
        LayoutModel layoutModel;
        int i;
        Canvas canvas2 = canvas;
        RenderData renderData2 = renderData;
        RectF rectF2 = rectF;
        BitmapManager bitmapManager2 = bitmapManager;
        LayoutModel layoutModel2 = renderData2.layoutModel;
        float f = renderData2.margin;
        BitmapRenderData[] bitmapRenderDataArr = renderData2.bitmapRenders;
        ImageLocationProcessor imageLocationProcessor = new ImageLocationProcessor();
        RectF rectF3 = new RectF();
        BitmapItemRender bitmapItemRender = new BitmapItemRender();
        int i2 = 0;
        while (i2 < layoutModel2.items.length) {
            if (i2 >= bitmapRenderDataArr.length) {
                layoutModel = layoutModel2;
                i = i2;
            } else {
                LayoutItemModel layoutItemModel = layoutModel2.items[i2];
                ImageLocation imageLocation = new ImageLocation(layoutItemModel.clipType, layoutItemModel.data);
                float width = rectF.width();
                float height = rectF.height();
                ImageLocation imageLocation2 = imageLocation;
                layoutModel = layoutModel2;
                ImageLocation imageLocation3 = imageLocation;
                float f2 = f * renderData2.scaleOffset;
                i = i2;
                imageLocationProcessor.processorImageLocation(imageLocation2, width, height, f2, renderData2.igonreMarginEdge);
                canvas.save();
                canvas2.translate(rectF2.left, rectF2.top);
                canvas2.clipPath(imageLocation3.mPathForClip);
                rectF3.set((float) imageLocation3.mLeft, (float) imageLocation3.mTop, (float) imageLocation3.mRight, (float) imageLocation3.mBottom);
                BitmapRenderData bitmapRenderData = bitmapRenderDataArr[i];
                Bitmap bitmap = bitmapRenderData.bitmap;
                if (bitmapManager2 != null) {
                    bitmapRenderData.bitmap = bitmapManager2.loadSuitableBitmapBySize(renderData2.imageWidth, renderData2.imageHeight, bitmapManager2.getOriginUriByBitmap(bitmapRenderData.bitmap));
                }
                bitmapItemRender.drawBitmapItem(bitmapRenderData, rectF3, canvas2, renderData2.scaleOffset);
                bitmapRenderData.bitmap = bitmap;
                canvas.restore();
            }
            i2 = i + 1;
            layoutModel2 = layoutModel;
        }
    }

    public static RenderData generateRenderData(Context context, PosterModel posterModel, LayoutModel layoutModel, CollageLayout collageLayout, int i) {
        int childCount = collageLayout.getChildCount();
        BitmapRenderData[] bitmapRenderDataArr = new BitmapRenderData[childCount];
        for (int i2 = 0; i2 < childCount; i2++) {
            bitmapRenderDataArr[i2] = ((CollageImageView) collageLayout.getChildAt(i2)).generateBitmapRenderData();
        }
        RenderData generateRenderData = generateRenderData(context.getResources(), posterModel, layoutModel, bitmapRenderDataArr);
        generateRenderData.scaleOffset = ((float) generateRenderData.imageWidth) / ((float) i);
        generateRenderData.margin = collageLayout.getMargin();
        generateRenderData.igonreMarginEdge = collageLayout.isIgnoreEdgeMargin();
        if (posterModel != null) {
            generateRenderData.posterElementRender = new PosterElementRender();
        }
        return generateRenderData;
    }

    private static RenderData generateRenderData(Resources resources, PosterModel posterModel, LayoutModel layoutModel, BitmapRenderData[] bitmapRenderDataArr) {
        float f;
        RenderData renderData = new RenderData();
        if (posterModel != null && posterModel.ratio != 0.0f) {
            f = posterModel.ratio;
        } else if (layoutModel == null || layoutModel.ratio == 0.0f) {
            TypedValue typedValue = new TypedValue();
            resources.getValue(R.dimen.poster_image_ratio, typedValue, true);
            f = typedValue.getFloat();
        } else {
            f = layoutModel.ratio;
        }
        renderData.imageWidth = 2160;
        renderData.imageHeight = Math.round(((float) renderData.imageWidth) / f);
        renderData.posterModel = posterModel;
        renderData.layoutModel = layoutModel;
        renderData.bitmapRenders = bitmapRenderDataArr;
        return renderData;
    }

    public static void initBitmapMatrix(RectF rectF, Matrix matrix, RectF rectF2, RectF rectF3) {
        initBitmapMatrix(rectF, matrix, rectF2, false, 0, rectF3);
    }

    public static void initBitmapMatrix(RectF rectF, Matrix matrix, RectF rectF2, boolean z, int i, RectF rectF3) {
        matrix.reset();
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        rectF3.set(rectF);
        matrix.mapRect(rectF3);
        if (z) {
            matrix.postScale(-1.0f, 1.0f, rectF3.centerX(), rectF3.centerY());
        }
        matrix.postRotate((float) i, rectF3.centerX(), rectF3.centerY());
        rectF3.set(rectF);
        matrix.mapRect(rectF3);
        float max = Math.max(rectF2.width() / rectF3.width(), rectF2.height() / rectF3.height());
        if (max != 1.0f) {
            matrix.postScale(max, max, rectF2.centerX(), rectF2.centerY());
            rectF3.set(rectF);
            matrix.mapRect(rectF3);
        }
    }
}
