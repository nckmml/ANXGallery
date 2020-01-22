package com.nexstreaming.app.common.nexasset.overlay.impl;

import android.graphics.RectF;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.assetpackage.h;
import com.nexstreaming.app.common.nexasset.assetpackage.i;
import com.nexstreaming.app.common.nexasset.overlay.AwakeAsset;
import com.nexstreaming.app.common.nexasset.overlay.OverlayMotion;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.kminternal.nexvideoeditor.LayerRenderer;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import java.io.IOException;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class RenderItemOverlayAsset extends AbstractOverlayAsset {
    private static final String LOG_TAG = "RenderItemOverlayAsset";
    /* access modifiers changed from: private */
    public int[] effect_id_ = {-1, -1};
    private int height;
    private int width;

    public RenderItemOverlayAsset(f fVar) {
        super(fVar);
        try {
            h a = i.a(a.a().b(), fVar.getId());
            this.width = a.d();
            this.height = a.e();
            Log.d(LOG_TAG, "Read itemdef: " + fVar.getId() + " : w,h=" + a.d() + "," + a.e());
        } catch (XmlPullParserException e) {
            Log.e(LOG_TAG, "Error reading itemdef: " + fVar.getId(), e);
        } catch (IOException e2) {
            Log.e(LOG_TAG, "Error reading itemdef: " + fVar.getId(), e2);
        }
    }

    public int getDefaultDuration() {
        return 0;
    }

    public int getIntrinsicHeight() {
        int i;
        if (this.width <= 0 || (i = this.height) <= 0) {
            return 700;
        }
        return i;
    }

    public int getIntrinsicWidth() {
        int i = this.width;
        if (i <= 0 || this.height <= 0) {
            return 700;
        }
        return i;
    }

    public AwakeAsset onAwake(LayerRenderer layerRenderer, final RectF rectF, final String str, Map<String, String> map) {
        return new AwakeAsset() {
            private RectF currentBound = rectF;
            private String currentEffectOptions = str;

            public boolean needRendererReawakeOnEditResize() {
                return false;
            }

            public void onAsleep(LayerRenderer layerRenderer) {
                NexEditor a = EditorGlobal.a();
                if (a != null && RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id] >= 0) {
                    a.c(RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id], layerRenderer.o().id);
                    RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id] = -1;
                }
            }

            public boolean onRefresh(LayerRenderer layerRenderer, RectF rectF, String str) {
                this.currentEffectOptions = str;
                this.currentBound = rectF;
                return true;
            }

            public void onRender(LayerRenderer layerRenderer, OverlayMotion overlayMotion, int i, int i2) {
                NexEditor a;
                if (RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id] < 0 && (a = EditorGlobal.a()) != null) {
                    RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id] = a.a(RenderItemOverlayAsset.this.getItemInfo().getId(), layerRenderer.o().id);
                }
                if (RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id] >= 0) {
                    layerRenderer.a(RenderItemOverlayAsset.this.effect_id_[layerRenderer.o().id], this.currentEffectOptions, layerRenderer.g(), i, i2, this.currentBound.left, this.currentBound.top, this.currentBound.right, this.currentBound.bottom, layerRenderer.k());
                }
            }
        };
    }
}
