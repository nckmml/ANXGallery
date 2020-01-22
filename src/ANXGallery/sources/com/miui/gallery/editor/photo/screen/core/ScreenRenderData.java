package com.miui.gallery.editor.photo.screen.core;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongScreenshotCropEditorView;
import com.miui.gallery.editor.photo.screen.crop.ScreenCropEntry;
import java.util.HashMap;

@SuppressLint({"ParcelCreator"})
public class ScreenRenderData extends RenderData {
    public ScreenDrawEntry mDrawEntry;
    public LongScreenshotCropEditorView.Entry mLongCropEntry;
    ScreenCropEntry mScreenCropEntry;

    public ScreenRenderData(ScreenDrawEntry screenDrawEntry, ScreenCropEntry screenCropEntry) {
        this.mDrawEntry = screenDrawEntry;
        this.mScreenCropEntry = screenCropEntry;
    }

    public Bitmap apply(Bitmap bitmap) {
        Bitmap apply = this.mDrawEntry.apply(bitmap);
        ScreenCropEntry screenCropEntry = this.mScreenCropEntry;
        if (screenCropEntry != null) {
            return screenCropEntry.apply(apply);
        }
        LongScreenshotCropEditorView.Entry entry = this.mLongCropEntry;
        return entry != null ? entry.apply(apply) : apply;
    }

    public void putStat(HashMap<String, String> hashMap) {
        String str = "false";
        hashMap.put("cropChanged", this.mScreenCropEntry == null ? str : "true");
        LongScreenshotCropEditorView.Entry entry = this.mLongCropEntry;
        if (entry != null) {
            if (entry.isModified()) {
                str = "true";
            }
            hashMap.put("longCropChanged", str);
        }
        this.mDrawEntry.putStat(hashMap);
    }

    public void setLongCropEntry(LongScreenshotCropEditorView.Entry entry) {
        this.mLongCropEntry = entry;
    }
}
