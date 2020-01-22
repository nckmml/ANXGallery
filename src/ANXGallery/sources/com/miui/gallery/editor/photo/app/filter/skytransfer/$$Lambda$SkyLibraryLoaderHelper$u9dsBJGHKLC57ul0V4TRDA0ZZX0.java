package com.miui.gallery.editor.photo.app.filter.skytransfer;

import com.miui.gallery.assistant.library.LibraryManager;
import com.miui.gallery.util.OptionalResult;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/* renamed from: com.miui.gallery.editor.photo.app.filter.skytransfer.-$$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0 implements ObservableOnSubscribe {
    public static final /* synthetic */ $$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0 INSTANCE = new $$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0();

    private /* synthetic */ $$Lambda$SkyLibraryLoaderHelper$u9dsBJGHKLC57ul0V4TRDA0ZZX0() {
    }

    public final void subscribe(ObservableEmitter observableEmitter) {
        observableEmitter.onNext(new OptionalResult(LibraryManager.getInstance().getLibrarySync(1012)));
    }
}
