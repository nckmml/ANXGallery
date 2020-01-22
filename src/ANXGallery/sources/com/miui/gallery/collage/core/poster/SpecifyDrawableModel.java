package com.miui.gallery.collage.core.poster;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.miui.gallery.collage.drawable.ColorDrawable;
import com.miui.gallery.collage.drawable.RectTimeDrawable;
import com.miui.gallery.collage.drawable.TimeFormatDrawable;

public class SpecifyDrawableModel extends ElementPositionModel {
    public Extras extras;
    public SpecifyDrawableType specifyDrawableType;

    /* renamed from: com.miui.gallery.collage.core.poster.SpecifyDrawableModel$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$collage$core$poster$SpecifyDrawableModel$SpecifyDrawableType = new int[SpecifyDrawableType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$collage$core$poster$SpecifyDrawableModel$SpecifyDrawableType[SpecifyDrawableType.TIME_FORMAT.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$collage$core$poster$SpecifyDrawableModel$SpecifyDrawableType[SpecifyDrawableType.TIME_RECT.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$collage$core$poster$SpecifyDrawableModel$SpecifyDrawableType[SpecifyDrawableType.COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static class Extras {
        public String color;
        public float letterSpace;
        public String textColor;
        public float textSize;
    }

    public enum SpecifyDrawableType {
        COLOR,
        TIME_FORMAT,
        TIME_RECT;

        public Drawable getDrawable(Resources resources, SpecifyDrawableModel specifyDrawableModel) {
            Drawable drawable;
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$collage$core$poster$SpecifyDrawableModel$SpecifyDrawableType[ordinal()];
            if (i == 1) {
                drawable = new TimeFormatDrawable(resources, specifyDrawableModel);
            } else if (i == 2) {
                drawable = new RectTimeDrawable(resources, specifyDrawableModel);
            } else if (i != 3) {
                return null;
            } else {
                return new ColorDrawable(specifyDrawableModel);
            }
            return drawable;
        }
    }
}
