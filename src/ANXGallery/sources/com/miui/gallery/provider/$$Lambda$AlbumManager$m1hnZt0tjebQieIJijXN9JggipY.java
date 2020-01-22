package com.miui.gallery.provider;

import android.database.Cursor;
import com.miui.gallery.util.SafeDBUtil;

/* renamed from: com.miui.gallery.provider.-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY implements SafeDBUtil.QueryHandler {
    public static final /* synthetic */ $$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY INSTANCE = new $$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY();

    private /* synthetic */ $$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY() {
    }

    public final Object handle(Cursor cursor) {
        return AlbumManager.lambda$doQueryScreenshotsAlbumId$33(cursor);
    }
}
