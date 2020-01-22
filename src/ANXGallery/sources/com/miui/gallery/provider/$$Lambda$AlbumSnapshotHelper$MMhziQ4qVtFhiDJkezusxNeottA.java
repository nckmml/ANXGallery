package com.miui.gallery.provider;

import com.miui.gallery.model.Album;
import java.util.Comparator;

/* renamed from: com.miui.gallery.provider.-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA implements Comparator {
    public static final /* synthetic */ $$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA INSTANCE = new $$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA();

    private /* synthetic */ $$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA() {
    }

    public final int compare(Object obj, Object obj2) {
        return Long.compare(((Album) obj).getSortBy(), ((Album) obj2).getSortBy());
    }
}
