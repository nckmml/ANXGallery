package com.miui.gallery.cleaner;

import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;

public class RawPhotoScanner extends BaseScanner {
    RawPhotoScanner() {
        super(4);
    }

    public String getSelection() {
        return "mimeType = 'image/x-adobe-dng' AND alias_hidden = 0 AND localGroupId != -1000";
    }

    /* access modifiers changed from: protected */
    public void recordClickScanResultEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", String.valueOf(this.mMediaItems.size()));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_result_raw_click", hashMap);
    }
}
