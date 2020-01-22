package com.miui.gallery.stat;

import com.miui.gallery.preference.BaseGalleryPreferences;

public class StatSampling {
    public static final boolean FILTERED = (BaseGalleryPreferences.SampleStatistic.getUniqId() % 9 != 0);
}
