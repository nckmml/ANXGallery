package com.miui.gallery.ui;

import com.miui.gallery.provider.PeopleFaceSnapshotHelper;
import com.miui.gallery.util.StaticContext;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;

/* renamed from: com.miui.gallery.ui.-$$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1-n3zwsMM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1n3zwsMM implements CompletableSource {
    public static final /* synthetic */ $$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1n3zwsMM INSTANCE = new $$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1n3zwsMM();

    private /* synthetic */ $$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1n3zwsMM() {
    }

    public final void subscribe(CompletableObserver completableObserver) {
        PeopleFaceSnapshotHelper.queryAndPersist(StaticContext.sGetAndroidContext());
    }
}
