package com.miui.gallery.editor.photo.core.imports.remover;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.miui.gallery.editor.photo.app.remover.Inpaint;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.RenderEngine;
import com.miui.gallery.util.BaseBitmapUtils;
import com.miui.gallery.util.CounterUtil;
import java.util.List;

public class RemoverEngine extends RenderEngine {
    public void release() {
        Inpaint.release();
    }

    public Bitmap render(Bitmap bitmap, RenderData renderData) {
        CounterUtil counterUtil = new CounterUtil("RemoverEngine");
        if (!(renderData instanceof RemoverRenderData)) {
            return null;
        }
        List<RemoverPaintData> list = ((RemoverRenderData) renderData).mPaintData;
        if (list != null && !list.isEmpty()) {
            Inpaint.initialize();
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
            for (RemoverPaintData next : list) {
                RemoverGestureView.export(createBitmap, next, next.mCurves);
                RectF rectF = new RectF();
                RemoverGestureView.getMaskBounds(rectF, createBitmap.getWidth(), createBitmap.getHeight(), next, next.mCurves);
                if (!rectF.isEmpty()) {
                    Inpaint.upsampleBmpData(bitmap, createBitmap, bitmap.getWidth(), bitmap.getHeight(), (int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom, next.mRemoverNNFData);
                }
            }
            BaseBitmapUtils.recycleSilently(createBitmap);
            Inpaint.release();
            counterUtil.tick("remove render done");
        }
        return bitmap;
    }
}
