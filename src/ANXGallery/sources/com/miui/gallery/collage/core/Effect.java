package com.miui.gallery.collage.core;

import com.miui.gallery.collage.core.layout.LayoutPresenter;
import com.miui.gallery.collage.core.poster.PosterPresenter;
import com.miui.gallery.collage.core.stitching.StitchingPresenter;

public enum Effect {
    POSTER,
    LAYOUT,
    STITCHING;

    /* renamed from: com.miui.gallery.collage.core.Effect$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$collage$core$Effect = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$collage$core$Effect = new int[Effect.values().length];
            $SwitchMap$com$miui$gallery$collage$core$Effect[Effect.LAYOUT.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$collage$core$Effect[Effect.STITCHING.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$collage$core$Effect[Effect.POSTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public CollagePresenter generatePresenter() {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$collage$core$Effect[ordinal()];
        return i != 1 ? i != 2 ? new PosterPresenter() : new StitchingPresenter() : new LayoutPresenter();
    }
}
