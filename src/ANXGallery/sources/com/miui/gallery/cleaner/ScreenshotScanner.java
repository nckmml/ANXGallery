package com.miui.gallery.cleaner;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.provider.AlbumManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;
import java.util.Locale;

public class ScreenshotScanner extends BaseScanner {
    ScreenshotScanner() {
        super(1);
    }

    public String getSelection() {
        long queryScreenshotsAlbumId = AlbumManager.queryScreenshotsAlbumId(GalleryApp.sGetAndroidContext());
        if (queryScreenshotsAlbumId == -1) {
            return null;
        }
        return String.format(Locale.US, "localGroupId = %d", new Object[]{Long.valueOf(queryScreenshotsAlbumId)});
    }

    /* access modifiers changed from: protected */
    public void recordClickScanResultEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", String.valueOf(this.mMediaItems.size()));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_result_screenshot_click", hashMap);
    }
}
