package com.miui.gallery.editor.photo.core.imports.filter.render;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import com.miui.filtersdk.filter.base.GPUImageFilter;
import com.miui.filtersdk.utils.Rotation;
import com.miui.gallery.editor.blocksdk.Block;
import com.miui.gallery.editor.blocksdk.BlockSdkUtils;
import com.miui.gallery.editor.blocksdk.SplitUtils;
import com.miui.gallery.util.CounterUtil;
import java.util.List;

public class GPUImage {
    private Bitmap mCurrentBitmap;
    private GPUImageFilter mFilter;
    private GLSurfaceView mGlSurfaceView;
    private final GPUImageRenderer mRenderer;
    private ScaleType mScaleType = ScaleType.CENTER_INSIDE;

    public enum ScaleType {
        CENTER_INSIDE,
        CENTER_CROP
    }

    public GPUImage(Context context) {
        if (supportsOpenGLES2(context)) {
            this.mFilter = new GPUImageFilter();
            this.mRenderer = new GPUImageRenderer(this.mFilter);
            this.mRenderer.setDrawBoundLines(false);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    private Bitmap getBitmapWithBlock(Bitmap bitmap, GPUImageRenderer gPUImageRenderer, List<Block> list) {
        int i;
        ISpecialProcessFilter iSpecialProcessFilter;
        GPUImageRenderer gPUImageRenderer2 = gPUImageRenderer;
        List<Block> list2 = list;
        CounterUtil counterUtil = new CounterUtil("GPUImage");
        GPUImageFilter gPUImageFilter = this.mFilter;
        if (gPUImageFilter instanceof ISpecialProcessFilter) {
            iSpecialProcessFilter = (ISpecialProcessFilter) gPUImageFilter;
            i = iSpecialProcessFilter.getSpecialBoard();
        } else {
            iSpecialProcessFilter = null;
            i = 0;
        }
        Block.TotalBlockInfo totalBlockInfo = list2.get(0).mTotalBlockInfo;
        int i2 = totalBlockInfo.mTotalColumn > 1 ? i : 0;
        int i3 = totalBlockInfo.mTotalWidth;
        int i4 = i2 * 2;
        int i5 = totalBlockInfo.mBlockWidth + i4;
        int i6 = i * 2;
        int i7 = totalBlockInfo.mBlockHeight + i6;
        PixelBuffer pixelBuffer = new PixelBuffer(i5, i7);
        int genTexture = OpenGlUtils.genTexture();
        gPUImageRenderer2.setGLTextureId(genTexture, i5, i7);
        pixelBuffer.setRenderer(gPUImageRenderer2);
        gPUImageRenderer2.setDrawBoundLines(false);
        BlockSdkUtils.bindBitmap(bitmap);
        int i8 = 0;
        while (i8 < list.size()) {
            Block block = list2.get(i8);
            int i9 = block.mColumn == 0 ? 0 : block.mColumn == totalBlockInfo.mTotalColumn + -1 ? i4 : i2;
            int i10 = block.mRow == 0 ? 0 : block.mRow == totalBlockInfo.mTotalRow + -1 ? i6 : i;
            BlockSdkUtils.updateTextureWidthStride(genTexture, i5, i7, i3, ((block.mOffset - i9) - (i10 * i3)) * 4);
            if (iSpecialProcessFilter != null) {
                iSpecialProcessFilter.setBlock(block);
            }
            pixelBuffer.draw();
            int i11 = i8;
            BlockSdkUtils.readPixelsAndMerge(i9, i10, block.mWidth, block.mHeight, i3, block.mOffset * 4);
            i8 = i11 + 1;
            genTexture = genTexture;
            pixelBuffer = pixelBuffer;
            i5 = i5;
            i7 = i7;
            iSpecialProcessFilter = iSpecialProcessFilter;
            i3 = i3;
            GPUImageRenderer gPUImageRenderer3 = gPUImageRenderer;
            list2 = list;
        }
        BlockSdkUtils.unbindBitmap(bitmap);
        counterUtil.tick(String.format("getBitmapDoneForBlock:%d", new Object[]{Integer.valueOf(list.size())}));
        this.mFilter.destroy();
        gPUImageRenderer.deleteImage();
        pixelBuffer.destroy();
        return bitmap;
    }

    private boolean supportsOpenGLES2(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public void deleteImage() {
        this.mRenderer.deleteImage();
        this.mCurrentBitmap = null;
        requestRender();
    }

    public Bitmap getBitmapWithFilterApplied(Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        GPUImageRenderer gPUImageRenderer = new GPUImageRenderer(this.mFilter);
        gPUImageRenderer.setRotation(Rotation.NORMAL, this.mRenderer.isFlippedHorizontally(), !this.mRenderer.isFlippedVertically());
        gPUImageRenderer.setScaleType(this.mScaleType);
        CounterUtil counterUtil = new CounterUtil("GPUImage");
        List<Block> split = SplitUtils.split(bitmap.getWidth(), bitmap.getHeight());
        if (split != null) {
            return getBitmapWithBlock(bitmap, gPUImageRenderer, split);
        }
        PixelBuffer pixelBuffer = new PixelBuffer(bitmap.getWidth(), bitmap.getHeight());
        gPUImageRenderer.setImageBitmap(bitmap, false);
        pixelBuffer.setRenderer(gPUImageRenderer);
        gPUImageRenderer.setDrawBoundLines(false);
        if (!z) {
            bitmap = null;
        }
        Bitmap bitmap2 = pixelBuffer.getBitmap(bitmap);
        counterUtil.tick("getBitmapDone");
        this.mFilter.destroy();
        gPUImageRenderer.deleteImage();
        pixelBuffer.destroy();
        this.mRenderer.setFilter(this.mFilter);
        return bitmap2;
    }

    public Bitmap getBitmapWithFilterApplied(boolean z) {
        return getBitmapWithFilterApplied(this.mCurrentBitmap, z);
    }

    public void requestRender() {
        GLSurfaceView gLSurfaceView = this.mGlSurfaceView;
        if (gLSurfaceView != null) {
            gLSurfaceView.requestRender();
        }
    }

    public void setBackgroundColor(float f, float f2, float f3) {
        this.mRenderer.setBackgroundColor(f, f2, f3);
    }

    public void setFilter(GPUImageFilter gPUImageFilter) {
        this.mFilter = gPUImageFilter;
        this.mRenderer.setFilter(this.mFilter);
    }

    public void setGLSurfaceView(GLSurfaceView gLSurfaceView) {
        this.mGlSurfaceView = gLSurfaceView;
        this.mGlSurfaceView.setEGLContextClientVersion(2);
        this.mGlSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.mGlSurfaceView.setZOrderOnTop(true);
        this.mGlSurfaceView.getHolder().setFormat(-2);
        this.mGlSurfaceView.setRenderer(this.mRenderer);
        this.mGlSurfaceView.setRenderMode(0);
        this.mGlSurfaceView.requestRender();
    }

    public void setImage(Bitmap bitmap) {
        if (this.mCurrentBitmap != bitmap) {
            this.mCurrentBitmap = bitmap;
            this.mRenderer.setImageBitmap(bitmap, false);
        }
    }
}
