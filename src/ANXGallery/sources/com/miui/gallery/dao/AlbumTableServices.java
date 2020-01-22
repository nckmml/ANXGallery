package com.miui.gallery.dao;

import android.database.Cursor;
import android.net.Uri;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.adapter.AlbumPageAdapter;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaAndAlbumOperations;
import miui.util.Pools;

public class AlbumTableServices {
    private static final String[] PROJECTION_NO_CLASSIFICATION = AlbumConstants.PROJECTION_NO_CLASSIFICATION;
    private static final Uri QUERYURL = GalleryContract.Album.URI;

    public static final boolean changeAlbumHiddenStatus(long j, boolean z, boolean z2) {
        return MediaAndAlbumOperations.doChangeHiddenStatus(GalleryApp.sGetAndroidContext(), j, z, z2);
    }

    public static Uri getQueryUri(boolean z, boolean z2, boolean z3, boolean z4) {
        return getQueryUriBuilder(z2, z, z3, z, z, true, true).appendQueryParameter("exclude_hidden_album", String.valueOf(z4)).build();
    }

    private static Uri.Builder getQueryUriBuilder(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return QUERYURL.buildUpon().appendQueryParameter("join_face", String.valueOf(z)).appendQueryParameter("join_video", String.valueOf(z2)).appendQueryParameter("join_share", String.valueOf(z3)).appendQueryParameter("join_recent", String.valueOf(z4)).appendQueryParameter("join_favorites", String.valueOf(z5)).appendQueryParameter("exclude_empty_album", String.valueOf(z6)).appendQueryParameter("fill_covers", String.valueOf(z7));
    }

    public static final Cursor queryAlbumsExcludeCamera() {
        StringBuilder sb;
        try {
            sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
            try {
                sb.append("(serverId IS NULL OR serverId <> 1)");
                Cursor query = GalleryApp.sGetAndroidContext().getContentResolver().query(getQueryUri(false, false, true, false).buildUpon().appendQueryParameter("all_except_deleted", String.valueOf(true)).build(), PROJECTION_NO_CLASSIFICATION, sb.toString(), (String[]) null, (String) null);
                Pools.getStringBuilderPool().release(sb);
                return query;
            } catch (Exception e) {
                e = e;
                try {
                    Log.d("AlbumTableServices", e.getMessage());
                    Pools.getStringBuilderPool().release(sb);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    Pools.getStringBuilderPool().release(sb);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            sb = null;
            Log.d("AlbumTableServices", e.getMessage());
            Pools.getStringBuilderPool().release(sb);
            return null;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            sb = null;
            th = th3;
            Pools.getStringBuilderPool().release(sb);
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static final Cursor queryHiddenAlbum() {
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        try {
            sb.append("attributes & 16 <> 0");
            Cursor query = GalleryApp.sGetAndroidContext().getContentResolver().query(getQueryUri(false, false, true, false).buildUpon().appendQueryParameter("all_except_deleted", String.valueOf(true)).build(), PROJECTION_NO_CLASSIFICATION, sb.toString(), (String[]) null, "sortBy ASC ");
            Pools.getStringBuilderPool().release(sb);
            return query;
        } catch (Exception e) {
            Log.d("AlbumTableServices", e.getMessage());
            Pools.getStringBuilderPool().release(sb);
            return null;
        } catch (Throwable th) {
            Pools.getStringBuilderPool().release(sb);
            throw th;
        }
    }

    public static final Cursor queryShareAlbum() {
        return GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.Album.URI_SHARE_ALL, AlbumPageAdapter.SHARED_ALBUM_PROJECTION, "count > 0", (String[]) null, (String) null);
    }
}
