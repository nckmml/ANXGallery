package com.miui.gallery.editor.photo.app.filter;

import com.xiaomi.skytransfer.SkyTranFilter;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/* renamed from: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA implements ObservableOnSubscribe {
    public static final /* synthetic */ $$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA INSTANCE = new $$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA();

    private /* synthetic */ $$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA() {
    }

    public final void subscribe(ObservableEmitter observableEmitter) {
        observableEmitter.onNext(Boolean.valueOf(SkyTranFilter.getInstance().waitSegment()));
    }
}
