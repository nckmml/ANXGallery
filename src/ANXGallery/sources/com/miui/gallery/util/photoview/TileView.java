package com.miui.gallery.util.photoview;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.View;
import com.miui.gallery.Config;
import com.miui.gallery.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TileView {
    private WeakReference<View> mAttachViewRef;
    private TileDecodeHandler mDecodeHandler;
    /* access modifiers changed from: private */
    public TileDecodeManager mDecodeManager;
    private List<Tile> mDestroyedTiles = new LinkedList();
    /* access modifiers changed from: private */
    public SparseArray<Tile> mDrawingTiles = new SparseArray<>();
    private boolean mIsMemoryTrimmed;
    private TileBitProvider mProvider;
    private BitmapRecycleCallback mRecycleCallback;
    private int mRefreshId = -1;
    private Matrix mRotateMatrix = new Matrix();
    private float mRotation;
    private List<Tile> mTempDecodeTiles = new ArrayList();
    private SparseArray<Tile> mTempDrawingTiles = new SparseArray<>();
    private RectF mTileIntersect = new RectF();
    private Rect mTileRange = new Rect();
    private final int mTileSize;
    private TrimMemoryCallback mTrimMemoryCallback;
    private Rect mViewPort = new Rect();

    private class TileDecodeHandler extends Handler {
        private TileDecodeHandler() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    if (message.obj != null) {
                        TileView.this.mDecodeManager.removeDecodingTile(TileView.makeTileKey(((Tile) message.obj).getTileRect()));
                    }
                    Log.w("TileView", "tile decode fail: %s", message.obj);
                }
            } else if (message.obj != null) {
                Tile tile = (Tile) message.obj;
                int makeTileKey = TileView.makeTileKey(tile.getTileRect());
                TileView.this.mDecodeManager.removeDecodingTile(makeTileKey);
                if (tile.isActive()) {
                    TileView.this.mDrawingTiles.put(makeTileKey, tile);
                    boolean unused = TileView.this.invalidate();
                    return;
                }
                tile.recycle();
            }
        }
    }

    public TileView(TileBitProvider tileBitProvider, View view, BitmapRecycleCallback bitmapRecycleCallback, TrimMemoryCallback trimMemoryCallback) {
        this.mProvider = tileBitProvider;
        this.mAttachViewRef = new WeakReference<>(view);
        this.mRecycleCallback = bitmapRecycleCallback;
        this.mTrimMemoryCallback = trimMemoryCallback;
        TileDecodeHandler tileDecodeHandler = new TileDecodeHandler();
        this.mDecodeHandler = tileDecodeHandler;
        this.mDecodeManager = new TileDecodeManager(tileDecodeHandler, this.mProvider);
        this.mTileSize = computeTileSize(tileBitProvider);
    }

    private int calculateInSampleSize(RectF rectF) {
        int imageWidth = this.mProvider.getImageWidth() / 2;
        int imageHeight = this.mProvider.getImageHeight() / 2;
        int i = 1;
        while (true) {
            if (((float) (imageWidth / i)) < rectF.width() && ((float) (imageHeight / i)) < rectF.height()) {
                return i;
            }
            i *= 2;
        }
    }

    private int ceil(int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = this.mTileSize;
            if (i3 * i4 * i2 >= i) {
                return i3 * i4 * i2;
            }
            i3++;
        }
    }

    private int computeTileSize(TileBitProvider tileBitProvider) {
        int imageHeight = tileBitProvider.getImageHeight();
        int imageWidth = tileBitProvider.getImageWidth();
        if (imageHeight <= 1080 || imageHeight >= 1296) {
            imageHeight = 1080;
        }
        return (imageWidth <= 1080 || imageWidth >= 1296) ? imageHeight : Math.max(imageHeight, imageWidth);
    }

    private int floor(int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = this.mTileSize;
            if (i3 * i4 * i2 > i) {
                return (i3 - 1) * i4 * i2;
            }
            i3++;
        }
    }

    private View getAttachView() {
        WeakReference<View> weakReference = this.mAttachViewRef;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null) {
            cleanup();
        }
        return view;
    }

    private void increaseRefreshId() {
        if (this.mRefreshId == Integer.MAX_VALUE) {
            this.mRefreshId = -1;
        }
        this.mRefreshId++;
    }

    /* access modifiers changed from: private */
    public boolean invalidate() {
        View attachView = getAttachView();
        if (attachView == null) {
            return false;
        }
        attachView.invalidate();
        return true;
    }

    private void layoutTiles(RectF rectF, float f) {
        if (rectF != null) {
            SystemClock.uptimeMillis();
            this.mDecodeManager.clear();
            this.mTileIntersect.set(rectF);
            this.mTileIntersect.intersect((float) this.mViewPort.left, (float) this.mViewPort.top, (float) this.mViewPort.right, (float) this.mViewPort.bottom);
            this.mRotation = f;
            this.mRotateMatrix.reset();
            this.mRotateMatrix.postRotate(f, (float) this.mViewPort.centerX(), (float) this.mViewPort.centerY());
            if (this.mProvider.getRotation() != 0) {
                this.mRotateMatrix.postRotate((float) (-this.mProvider.getRotation()), (float) this.mViewPort.centerX(), (float) this.mViewPort.centerY());
            }
            this.mRotateMatrix.mapRect(rectF);
            this.mRotateMatrix.mapRect(this.mTileIntersect);
            int calculateInSampleSize = calculateInSampleSize(rectF);
            float f2 = this.mTileIntersect.left - rectF.left;
            if (this.mProvider.isFlip()) {
                f2 = this.mTileIntersect.right - rectF.right;
            }
            float imageWidth = ((float) this.mProvider.getImageWidth()) / rectF.width();
            float imageHeight = ((float) this.mProvider.getImageHeight()) / rectF.height();
            int width = (int) ((f2 / rectF.width()) * ((float) this.mProvider.getImageWidth()));
            int height = (int) (((this.mTileIntersect.top - rectF.top) / rectF.height()) * ((float) this.mProvider.getImageHeight()));
            this.mTileRange.set(floor(width, calculateInSampleSize), floor(height, calculateInSampleSize), ceil((int) (((float) width) + (this.mTileIntersect.width() * imageWidth)), calculateInSampleSize), ceil((int) (((float) height) + (this.mTileIntersect.height() * imageHeight)), calculateInSampleSize));
            float f3 = rectF.left + (((float) this.mTileRange.left) / imageWidth);
            float f4 = (((float) this.mTileRange.top) / imageHeight) + rectF.top;
            int i = this.mTileSize;
            float f5 = ((float) (i * calculateInSampleSize)) / imageHeight;
            refreshTiles(f3, f4, ((float) (i * calculateInSampleSize)) / imageWidth, f5, calculateInSampleSize);
        }
    }

    public static int makeTileKey(Rect rect) {
        if (rect == null) {
            return 0;
        }
        return ((((((527 + rect.left) * 31) + rect.top) * 31) + rect.right) * 31) + rect.bottom;
    }

    private Tile obtainTile(Rect rect, RectF rectF, int i) {
        if (this.mDestroyedTiles.size() <= 0) {
            return new Tile(rect, rectF, i, this.mRecycleCallback);
        }
        Tile remove = this.mDestroyedTiles.remove(0);
        remove.updateTileParam(rect, i);
        remove.updateDisplayParam(rectF);
        return remove;
    }

    private void refreshTiles(float f, float f2, float f3, float f4, int i) {
        increaseRefreshId();
        int i2 = this.mTileRange.top;
        float f5 = f2;
        int i3 = 0;
        int i4 = 0;
        while (i2 < this.mTileRange.bottom) {
            i3++;
            int i5 = this.mTileRange.left;
            float f6 = f;
            int i6 = 0;
            while (i5 < this.mTileRange.right) {
                i6++;
                int i7 = this.mTileSize;
                Rect rect = new Rect(i5, i2, (i7 * i) + i5, (i7 * i) + i2);
                float f7 = f6 + f3;
                RectF rectF = new RectF(f6, f5, f7, f5 + f4);
                int makeTileKey = makeTileKey(rect);
                Tile tile = this.mDrawingTiles.get(makeTileKey);
                if (tile != null) {
                    tile.updateDisplayParam(rectF);
                    this.mTempDrawingTiles.put(makeTileKey, tile);
                } else {
                    tile = this.mDecodeManager.getDecodingTile(makeTileKey);
                    if (tile != null) {
                        tile.updateDisplayParam(rectF);
                    } else {
                        tile = obtainTile(rect, rectF, i);
                        this.mTempDecodeTiles.add(tile);
                    }
                }
                tile.setIndex(i3, i6);
                tile.setRefreshId(this.mRefreshId);
                i5 += this.mTileSize * i;
                f6 = f7;
            }
            f5 += f4;
            i2 += this.mTileSize * i;
            i4 = i6;
        }
        trimMemoryIfNeeded(i3, i4);
        int size = this.mDrawingTiles.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (this.mTempDrawingTiles.get(this.mDrawingTiles.keyAt(i8)) == null) {
                Tile valueAt = this.mDrawingTiles.valueAt(i8);
                valueAt.recycle();
                this.mDestroyedTiles.add(valueAt);
            }
        }
        this.mDrawingTiles.clear();
        int size2 = this.mTempDrawingTiles.size();
        for (int i9 = 0; i9 < size2; i9++) {
            Tile valueAt2 = this.mTempDrawingTiles.valueAt(i9);
            this.mDrawingTiles.put(makeTileKey(valueAt2.getTileRect()), valueAt2);
        }
        if (this.mDrawingTiles.size() > 0) {
            invalidate();
        }
        for (Tile put : this.mTempDecodeTiles) {
            this.mDecodeManager.put(put);
        }
        this.mTempDrawingTiles.clear();
        this.mTempDecodeTiles.clear();
    }

    private void trimMemoryIfNeeded(int i, int i2) {
        if (!this.mIsMemoryTrimmed && this.mTrimMemoryCallback != null) {
            int i3 = i * i2;
            int i4 = this.mTileSize;
            if (((long) ((i3 * i4) * i4)) * ((long) TileReusedBitCache.getBytesPerPixel(Config.TileConfig.getBitmapConfig())) >= 67108864) {
                this.mTrimMemoryCallback.onTrimMemory(1);
                this.mIsMemoryTrimmed = true;
            }
        }
    }

    public void cleanup() {
        this.mDecodeManager.cancel();
        this.mDecodeHandler.removeCallbacksAndMessages((Object) null);
        int size = this.mDrawingTiles.size();
        for (int i = 0; i < size; i++) {
            this.mDrawingTiles.valueAt(i).recycle();
        }
        this.mDrawingTiles.clear();
        if (this.mIsMemoryTrimmed) {
            TrimMemoryCallback trimMemoryCallback = this.mTrimMemoryCallback;
            if (trimMemoryCallback != null) {
                trimMemoryCallback.onStopTrimMemory(1);
                this.mTrimMemoryCallback = null;
            }
            this.mIsMemoryTrimmed = false;
        }
        this.mRecycleCallback = null;
        if (size > 0) {
            invalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        int i;
        SystemClock.uptimeMillis();
        int size = this.mDrawingTiles.size();
        if (size != 0) {
            if (!this.mProvider.isFlip() && this.mProvider.getRotation() == 0 && this.mRotation == 0.0f) {
                i = 0;
            } else {
                i = canvas.save();
                canvas.rotate(((float) this.mProvider.getRotation()) - this.mRotation, (float) this.mViewPort.centerX(), (float) this.mViewPort.centerY());
                if (this.mProvider.isFlip()) {
                    canvas.scale(-1.0f, 1.0f, (float) this.mViewPort.centerX(), (float) this.mViewPort.centerY());
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                Tile valueAt = this.mDrawingTiles.valueAt(i2);
                if (valueAt.getRefreshId() == this.mRefreshId) {
                    valueAt.draw(canvas, paint);
                }
            }
            if (i > 0) {
                canvas.restoreToCount(i);
            }
        }
    }

    public int getTileProviderHeight() {
        TileBitProvider tileBitProvider = this.mProvider;
        if (tileBitProvider != null) {
            return tileBitProvider.getImageHeight();
        }
        return 0;
    }

    public int getTileProviderRotation() {
        TileBitProvider tileBitProvider = this.mProvider;
        if (tileBitProvider != null) {
            return tileBitProvider.getRotation();
        }
        return 0;
    }

    public int getTileProviderWidth() {
        TileBitProvider tileBitProvider = this.mProvider;
        if (tileBitProvider != null) {
            return tileBitProvider.getImageWidth();
        }
        return 0;
    }

    public void notifyInvalidate(RectF rectF, float f) {
        layoutTiles(rectF, f);
    }

    public void setViewPort(Rect rect) {
        this.mViewPort.set(rect);
    }
}
