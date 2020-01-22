package com.miui.gallery.model.repository;

import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.model.repository.datesource.album.db.AlbumDbDataSource;
import io.reactivex.Flowable;
import java.util.List;

public class AlbumRepository implements IAlbumRepository {
    private IAlbumRepository mDbDataSource;

    public AlbumRepository() {
        this(new AlbumDbDataSource());
    }

    public AlbumRepository(IAlbumRepository iAlbumRepository) {
        this.mDbDataSource = iAlbumRepository;
    }

    public Flowable<Boolean> cancelAlbumHiddenStatus(long j) {
        return this.mDbDataSource.cancelAlbumHiddenStatus(j);
    }

    public Flowable<Boolean> doChangeAlbumBackupStatus(boolean z, long j) {
        return this.mDbDataSource.doChangeAlbumBackupStatus(z, j);
    }

    public Flowable<List<AlbumConstants.ShareAlbum>> queryAlbumListShareInfo() {
        return this.mDbDataSource.queryAlbumListShareInfo();
    }

    public Flowable<List<Album>> queryAlbumsExcludeSystemAndRubbish() {
        return this.mDbDataSource.queryAlbumsExcludeSystemAndRubbish();
    }

    public Flowable<List<Album>> queryHiddenAlbum() {
        return this.mDbDataSource.queryHiddenAlbum();
    }
}
