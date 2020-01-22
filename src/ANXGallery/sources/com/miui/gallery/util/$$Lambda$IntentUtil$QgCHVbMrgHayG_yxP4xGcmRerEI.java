package com.miui.gallery.util;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.util.MovieLibraryLoaderHelper;

/* renamed from: com.miui.gallery.util.-$$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI implements MovieLibraryLoaderHelper.DownloadStartListener {
    public static final /* synthetic */ $$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI INSTANCE = new $$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI();

    private /* synthetic */ $$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI() {
    }

    public final void onDownloadStart() {
        ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.photo_movie_module_loading);
    }
}
