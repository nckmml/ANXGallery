package com.miui.gallery.util;

import com.miui.gallery.assistant.library.LibraryManager;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/* renamed from: com.miui.gallery.util.-$$Lambda$MovieLibraryLoaderHelper$o_yWlKAP1oP0_S6cU4TnPYvMz9A  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MovieLibraryLoaderHelper$o_yWlKAP1oP0_S6cU4TnPYvMz9A implements ObservableOnSubscribe {
    public static final /* synthetic */ $$Lambda$MovieLibraryLoaderHelper$o_yWlKAP1oP0_S6cU4TnPYvMz9A INSTANCE = new $$Lambda$MovieLibraryLoaderHelper$o_yWlKAP1oP0_S6cU4TnPYvMz9A();

    private /* synthetic */ $$Lambda$MovieLibraryLoaderHelper$o_yWlKAP1oP0_S6cU4TnPYvMz9A() {
    }

    public final void subscribe(ObservableEmitter observableEmitter) {
        observableEmitter.onNext(new OptionalResult(LibraryManager.getInstance().getLibrarySync(1011)));
    }
}
