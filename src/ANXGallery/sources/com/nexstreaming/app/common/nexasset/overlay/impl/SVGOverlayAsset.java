package com.nexstreaming.app.common.nexasset.overlay.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.Log;
import com.larvalabs.svgandroid.SVGParseException;
import com.larvalabs.svgandroid.SVGParser;
import com.larvalabs.svgandroid.b;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.overlay.AwakeAsset;
import com.nexstreaming.app.common.nexasset.overlay.OverlayMotion;
import com.nexstreaming.app.common.util.c;
import com.nexstreaming.kminternal.nexvideoeditor.LayerRenderer;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SVGOverlayAsset extends AbstractOverlayAsset {
    public static final int COLOR_REPLACEMENT_TOLERANCE = 50;
    private static final String LOG_TAG = "SVGOverlayAsset";
    private static final int MAX_TEX_SIZE = 2000;
    private static int serial;
    private int height;
    private int width;

    public SVGOverlayAsset(f fVar) throws IOException {
        super(fVar);
        b svg = getSVG((Map<String, String>) null);
        if (svg != null) {
            this.width = svg.b().getWidth();
            this.height = svg.b().getHeight();
        }
    }

    private b getSVG(Map<String, String> map) throws IOException {
        HashMap hashMap;
        AssetPackageReader assetPackageReader;
        InputStream inputStream;
        b bVar = null;
        if (map != null) {
            hashMap = null;
            for (Map.Entry next : map.entrySet()) {
                if (((String) next.getKey()).startsWith("color:svgcolor_")) {
                    int a = c.a(((String) next.getKey()).replace("color:svgcolor_", "#"));
                    int a2 = c.a((String) next.getValue());
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(Integer.valueOf(a), Integer.valueOf(a2));
                }
            }
        } else {
            hashMap = null;
        }
        try {
            assetPackageReader = getAssetPackageReader();
            try {
                inputStream = assetPackageReader.a(getItemInfo().getFilePath());
                try {
                    bVar = SVGParser.a(inputStream, (Map<Integer, Integer>) hashMap, 50);
                } catch (SVGParseException e) {
                    e = e;
                    try {
                        Log.e(LOG_TAG, e.getMessage(), e);
                        com.nexstreaming.app.common.util.b.a(inputStream);
                        com.nexstreaming.app.common.util.b.a(assetPackageReader);
                        return bVar;
                    } catch (Throwable th) {
                        th = th;
                        com.nexstreaming.app.common.util.b.a(inputStream);
                        com.nexstreaming.app.common.util.b.a(assetPackageReader);
                        throw th;
                    }
                }
            } catch (SVGParseException e2) {
                e = e2;
                inputStream = null;
                Log.e(LOG_TAG, e.getMessage(), e);
                com.nexstreaming.app.common.util.b.a(inputStream);
                com.nexstreaming.app.common.util.b.a(assetPackageReader);
                return bVar;
            } catch (Throwable th2) {
                inputStream = null;
                th = th2;
                com.nexstreaming.app.common.util.b.a(inputStream);
                com.nexstreaming.app.common.util.b.a(assetPackageReader);
                throw th;
            }
        } catch (SVGParseException e3) {
            e = e3;
            assetPackageReader = null;
            inputStream = null;
            Log.e(LOG_TAG, e.getMessage(), e);
            com.nexstreaming.app.common.util.b.a(inputStream);
            com.nexstreaming.app.common.util.b.a(assetPackageReader);
            return bVar;
        } catch (Throwable th3) {
            inputStream = null;
            th = th3;
            assetPackageReader = null;
            com.nexstreaming.app.common.util.b.a(inputStream);
            com.nexstreaming.app.common.util.b.a(assetPackageReader);
            throw th;
        }
        com.nexstreaming.app.common.util.b.a(inputStream);
        com.nexstreaming.app.common.util.b.a(assetPackageReader);
        return bVar;
    }

    public int getDefaultDuration() {
        return 0;
    }

    public int getIntrinsicHeight() {
        return this.height;
    }

    public int getIntrinsicWidth() {
        return this.width;
    }

    public AwakeAsset onAwake(LayerRenderer layerRenderer, final RectF rectF, String str, Map<String, String> map) {
        final int i = serial;
        serial = i + 1;
        try {
            b svg = getSVG(map);
            if (svg == null) {
                return null;
            }
            int max = 2000 / Math.max(this.width, this.height);
            Picture b = svg.b();
            final Bitmap createBitmap = Bitmap.createBitmap((int) Math.floor((double) (((float) this.width) * 1.0f)), (int) Math.floor((double) (((float) this.height) * 1.0f)), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.scale(1.0f, 1.0f);
            b.draw(canvas);
            Log.d(LOG_TAG, "onAwake OUT : [#" + i + "] actualScale=" + 1.0f + " bm=" + createBitmap.getWidth() + "x" + createBitmap.getHeight());
            return new AwakeAsset() {
                public boolean needRendererReawakeOnEditResize() {
                    return true;
                }

                public void onAsleep(LayerRenderer layerRenderer) {
                    Log.d(SVGOverlayAsset.LOG_TAG, "onAsleep [#" + i + "]");
                }

                public boolean onRefresh(LayerRenderer layerRenderer, RectF rectF, String str) {
                    return false;
                }

                public void onRender(LayerRenderer layerRenderer, OverlayMotion overlayMotion, int i, int i2) {
                    Log.d(SVGOverlayAsset.LOG_TAG, "onRender [#" + i + "]=" + rectF.toString());
                    layerRenderer.a(createBitmap, rectF.left, rectF.top, rectF.right, rectF.bottom);
                }
            };
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error loading asset", e);
            return null;
        }
    }
}
