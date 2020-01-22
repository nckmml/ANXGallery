package com.miui.gallery.cleaner;

import com.miui.gallery.util.ArrayUtils;

public class CleanerContract {

    public interface Projection {
        public static final String[] BASE_SCAN_PROJECTION = {"_id", "size", "localFile"};
        public static final String[] NORMAL_SCAN_PROJECTION = ((String[]) ArrayUtils.concat(BASE_SCAN_PROJECTION, new String[]{"thumbnailFile", "microthumbfile", "sha1"}));
        public static final String[] SIMILAR_SCAN_PROJECTION = ((String[]) ArrayUtils.concat(NORMAL_SCAN_PROJECTION, new String[]{"alias_create_time"}));
        public static final String[] SLIM_SCAN_PROJECTION = ((String[]) ArrayUtils.concat(BASE_SCAN_PROJECTION, new String[]{"exifImageWidth", "exifImageLength"}));
    }
}
