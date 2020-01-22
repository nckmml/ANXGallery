package com.miui.gallery.cleaner;

import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;

public class VideoScanner extends BaseScanner {
    VideoScanner() {
        super(2);
    }

    public String getSelection() {
        return "serverType = 2 AND alias_hidden = 0 AND localGroupId != -1000 AND (size > 0)";
    }

    /* access modifiers changed from: protected */
    public void recordClickScanResultEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", String.valueOf(this.mMediaItems.size()));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_result_video_click", hashMap);
    }
}
