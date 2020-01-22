package com.nexstreaming.app.common.nexasset.overlay;

import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.overlay.impl.AnimatedOverlayAsset;
import com.nexstreaming.app.common.nexasset.overlay.impl.BitmapOverlayAsset;
import com.nexstreaming.app.common.nexasset.overlay.impl.RenderItemOverlayAsset;
import com.nexstreaming.app.common.nexasset.overlay.impl.SVGOverlayAsset;
import com.nexstreaming.app.common.util.j;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class OverlayAssetFactory {
    private static Map<String, WeakReference<OverlayAsset>> cache = new HashMap();
    private static int deadRefCheck = 0;

    /* renamed from: com.nexstreaming.app.common.nexasset.overlay.OverlayAssetFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$nexstreaming$app$common$nexasset$assetpackage$ItemType = new int[ItemType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$nexstreaming$app$common$nexasset$assetpackage$ItemType[ItemType.overlay.ordinal()] = 1;
            $SwitchMap$com$nexstreaming$app$common$nexasset$assetpackage$ItemType[ItemType.renderitem.ordinal()] = 2;
        }
    }

    private OverlayAssetFactory() {
    }

    public static OverlayAsset forItem(String str) throws IOException, XmlPullParserException {
        OverlayAsset overlayAsset;
        WeakReference weakReference = cache.get(str);
        if (weakReference != null && (overlayAsset = (OverlayAsset) weakReference.get()) != null) {
            return overlayAsset;
        }
        OverlayAsset overlayAsset2 = null;
        f c = c.a().c(str);
        if (c != null) {
            int i = AnonymousClass1.$SwitchMap$com$nexstreaming$app$common$nexasset$assetpackage$ItemType[c.getType().ordinal()];
            if (i == 1) {
                String filePath = c.getFilePath();
                String substring = filePath.substring(filePath.lastIndexOf(46) + 1);
                if (substring.equalsIgnoreCase("png") || substring.equalsIgnoreCase("jpeg") || substring.equalsIgnoreCase("jpg") || substring.equalsIgnoreCase("webp")) {
                    overlayAsset2 = new BitmapOverlayAsset(c);
                } else if (substring.equalsIgnoreCase("svg")) {
                    overlayAsset2 = new SVGOverlayAsset(c);
                } else if (substring.equalsIgnoreCase("xml")) {
                    overlayAsset2 = new AnimatedOverlayAsset(c);
                } else {
                    throw new IOException("Asset load error: " + str + " (unknown overlay type for '" + filePath + "')");
                }
            } else if (i == 2) {
                return new RenderItemOverlayAsset(c);
            }
            if (overlayAsset2 != null) {
                cache.put(str, new WeakReference(overlayAsset2));
                int i2 = deadRefCheck;
                deadRefCheck = i2 + 1;
                if (i2 > 32) {
                    j.a(cache);
                    deadRefCheck = 0;
                }
            }
            return overlayAsset2;
        }
        throw new IOException("Asset not found: " + str);
    }
}
