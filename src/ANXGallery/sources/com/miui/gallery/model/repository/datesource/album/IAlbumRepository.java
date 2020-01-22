package com.miui.gallery.model.repository.datesource.album;

import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import io.reactivex.Flowable;
import java.util.List;

public interface IAlbumRepository {
    Flowable<Boolean> cancelAlbumHiddenStatus(long j);

    Flowable<Boolean> doChangeAlbumBackupStatus(boolean z, long j);

    Flowable<List<AlbumConstants.ShareAlbum>> queryAlbumListShareInfo();

    Flowable<List<Album>> queryAlbumsExcludeSystemAndRubbish();

    Flowable<List<Album>> queryHiddenAlbum();
}
