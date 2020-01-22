package com.nexstreaming.app.common.nexasset.overlay.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.util.LruCache;
import com.larvalabs.svgandroid.SVGParser;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.overlay.AwakeAsset;
import com.nexstreaming.app.common.nexasset.overlay.OverlayMotion;
import com.nexstreaming.app.common.nexasset.overlay.OverlaySpec;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.app.common.util.i;
import com.nexstreaming.kminternal.nexvideoeditor.LayerRenderer;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedOverlayAsset extends AbstractOverlayAsset {
    private static final String LOG_TAG = "AnimOverlayAsset";
    private static final int MAX_TEX_SIZE = 2000;
    private String baseFile;
    private OverlaySpec overlaySpec;
    private AssetPackageReader reader;
    private float vectorScale;

    /* renamed from: com.nexstreaming.app.common.nexasset.overlay.impl.AnimatedOverlayAsset$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection = new int[OverlaySpec.AnimDirection.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection[OverlaySpec.AnimDirection.NORMAL.ordinal()] = 1;
            $SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection[OverlaySpec.AnimDirection.REVERSE.ordinal()] = 2;
            $SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection[OverlaySpec.AnimDirection.ALTERNATE.ordinal()] = 3;
            try {
                $SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection[OverlaySpec.AnimDirection.ALTERNATE_REVERSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static class AwakeAssetImpl implements AwakeAsset {
        private final String baseFile;
        private LruCache<String, Bitmap> bitmapCache = new LruCache<String, Bitmap>(41943040) {
            /* access modifiers changed from: protected */
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        private final RectF bounds;
        private final OverlaySpec overlaySpec;
        private final AssetPackageReader reader;
        private final float vectorScale;

        AwakeAssetImpl(RectF rectF, OverlaySpec overlaySpec2, AssetPackageReader assetPackageReader, String str, float f) {
            this.bounds = new RectF(rectF);
            this.overlaySpec = overlaySpec2;
            this.reader = assetPackageReader;
            this.baseFile = str;
            this.vectorScale = f;
        }

        private Bitmap getImage(String str) {
            if (this.reader == null) {
                return null;
            }
            Bitmap bitmap = this.bitmapCache.get(str);
            if (bitmap != null) {
                return bitmap;
            }
            Bitmap loadImage = loadImage(str);
            if (loadImage != null) {
                this.bitmapCache.put(str, loadImage);
            }
            return loadImage;
        }

        private Bitmap loadBitmap(InputStream inputStream) {
            return BitmapFactory.decodeStream(inputStream);
        }

        private Bitmap loadImage(String str) {
            InputStream inputStream;
            String b = i.b(this.baseFile, str);
            String a = i.a(str);
            try {
                inputStream = this.reader.a(b);
                try {
                    Bitmap loadSVG = a.equalsIgnoreCase("svg") ? loadSVG(inputStream) : loadBitmap(inputStream);
                    b.a(inputStream);
                    return loadSVG;
                } catch (IOException e) {
                    e = e;
                    try {
                        Log.e(AnimatedOverlayAsset.LOG_TAG, "Error reading frame image", e);
                        b.a(inputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        b.a(inputStream);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                Log.e(AnimatedOverlayAsset.LOG_TAG, "Error reading frame image", e);
                b.a(inputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                b.a(inputStream);
                throw th;
            }
        }

        private Bitmap loadSVG(InputStream inputStream) {
            PictureDrawable a = SVGParser.a(inputStream).a();
            Bitmap createBitmap = Bitmap.createBitmap((int) Math.floor((double) (((float) this.overlaySpec.width) * this.vectorScale)), (int) Math.floor((double) (((float) this.overlaySpec.height) * this.vectorScale)), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            a.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            a.draw(canvas);
            return createBitmap;
        }

        public boolean needRendererReawakeOnEditResize() {
            return true;
        }

        public void onAsleep(LayerRenderer layerRenderer) {
            this.bitmapCache.evictAll();
            b.a(this.reader);
        }

        public boolean onRefresh(LayerRenderer layerRenderer, RectF rectF, String str) {
            return false;
        }

        public void onRender(LayerRenderer layerRenderer, OverlayMotion overlayMotion, int i, int i2) {
            Bitmap image;
            int i3;
            if (this.overlaySpec != null) {
                int g = ((layerRenderer.g() - i) * this.overlaySpec.fps) / 1000;
                int size = this.overlaySpec.layers.size();
                for (int i4 = 0; i4 < size; i4++) {
                    OverlaySpec.Layer layer = this.overlaySpec.layers.get(i4);
                    int i5 = (layer.iterationCount < 0 || g <= (layer.iterationCount * layer.duration) - 1) ? g : (layer.iterationCount * layer.duration) - 1;
                    boolean z = (i5 / layer.duration) % 2 == 0;
                    int i6 = i5 % layer.duration;
                    int i7 = AnonymousClass1.$SwitchMap$com$nexstreaming$app$common$nexasset$overlay$OverlaySpec$AnimDirection[layer.direction.ordinal()];
                    if (i7 != 1) {
                        if (i7 == 2) {
                            i3 = layer.duration;
                        } else if (i7 != 3) {
                            if (i7 == 4 && z) {
                                i3 = layer.duration;
                            }
                        } else if (!z) {
                            i3 = layer.duration;
                        }
                        i6 = i3 - i6;
                    }
                    int size2 = layer.frames.size();
                    int i8 = 0;
                    OverlaySpec.Frame frame = null;
                    for (int i9 = 0; i9 < size2; i9++) {
                        frame = layer.frames.get(i9);
                        if (i6 <= i8) {
                            break;
                        }
                        i8 += Math.max(1, frame.hold);
                    }
                    if (!(frame == null || frame.blank || (image = getImage(frame.src)) == null)) {
                        layerRenderer.a(image, this.bounds.left, this.bounds.top, this.bounds.right, this.bounds.bottom);
                    }
                }
            }
        }
    }

    public AnimatedOverlayAsset(f fVar) throws IOException, XmlPullParserException {
        super(fVar);
        InputStream inputStream;
        AssetPackageReader assetPackageReader;
        InputStream inputStream2 = null;
        try {
            assetPackageReader = getAssetPackageReader();
            try {
                inputStream = assetPackageReader.a(fVar.getFilePath());
            } catch (Throwable th) {
                th = th;
                inputStream = null;
                b.a((Closeable) null);
                b.a(inputStream);
                b.a(assetPackageReader);
                throw th;
            }
            try {
                this.overlaySpec = OverlaySpec.fromInputStream(inputStream);
                if ((this.overlaySpec.width <= 0 || this.overlaySpec.height <= 0) && this.overlaySpec.layers != null && this.overlaySpec.layers.size() > 0) {
                    OverlaySpec.Layer layer = this.overlaySpec.layers.get(0);
                    if (layer.frames != null && layer.frames.size() > 0) {
                        OverlaySpec.Frame frame = layer.frames.get(0);
                        if (!frame.blank) {
                            inputStream2 = assetPackageReader.a(frame.src);
                            Picture b = SVGParser.a(inputStream2).b();
                            this.overlaySpec.width = b.getWidth();
                            this.overlaySpec.height = b.getHeight();
                        }
                    }
                }
                if (this.overlaySpec.width <= 0 || this.overlaySpec.height <= 0) {
                    this.overlaySpec.width = 100;
                    this.overlaySpec.height = 100;
                }
                b.a(inputStream2);
                b.a(inputStream);
                b.a(assetPackageReader);
            } catch (Throwable th2) {
                th = th2;
                b.a((Closeable) null);
                b.a(inputStream);
                b.a(assetPackageReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            assetPackageReader = null;
            b.a((Closeable) null);
            b.a(inputStream);
            b.a(assetPackageReader);
            throw th;
        }
    }

    public int getDefaultDuration() {
        if (this.overlaySpec.duration > 0) {
            return (this.overlaySpec.duration * 1000) / this.overlaySpec.fps;
        }
        int i = 0;
        for (OverlaySpec.Layer next : this.overlaySpec.layers) {
            if (next.iterationCount < 0) {
                return 0;
            }
            i = Math.max(i, ((next.duration * next.iterationCount) * 1000) / this.overlaySpec.fps);
        }
        if (i > 30000) {
            return 0;
        }
        if (i < 1000) {
            return 1000;
        }
        return i;
    }

    public int getIntrinsicHeight() {
        return this.overlaySpec.height;
    }

    public int getIntrinsicWidth() {
        return this.overlaySpec.width;
    }

    public AwakeAsset onAwake(LayerRenderer layerRenderer, RectF rectF, String str, Map<String, String> map) {
        AssetPackageReader assetPackageReader;
        try {
            assetPackageReader = getAssetPackageReader();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error getting package reader", e);
            assetPackageReader = null;
        }
        AssetPackageReader assetPackageReader2 = assetPackageReader;
        int max = 2000 / Math.max(this.overlaySpec.width, this.overlaySpec.height);
        return new AwakeAssetImpl(rectF, this.overlaySpec, assetPackageReader2, getItemInfo().getFilePath(), 1.0f);
    }
}
