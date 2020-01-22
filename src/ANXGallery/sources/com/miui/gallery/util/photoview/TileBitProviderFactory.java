package com.miui.gallery.util.photoview;

import com.miui.gallery.model.ImageType;
import com.miui.gallery.util.Log;

public class TileBitProviderFactory {
    public static TileBitProvider create(String str, int i, int i2, byte[] bArr) {
        long currentTimeMillis = System.currentTimeMillis();
        TileBitProvider parallelTileBitProvider = ImageType.of(i, i2) == ImageType.HIGH_RESOLUTION_108M ? new ParallelTileBitProvider(str, bArr, 3) : new TileBitProviderRegion(str, bArr);
        Log.d("TileBitProviderFactory", "create tile provider costs: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return parallelTileBitProvider;
    }
}
