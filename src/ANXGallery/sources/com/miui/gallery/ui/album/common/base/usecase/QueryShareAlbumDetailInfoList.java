package com.miui.gallery.ui.album.common.base.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import io.reactivex.Flowable;
import java.util.List;

public class QueryShareAlbumDetailInfoList extends BaseUseCase<List<AlbumConstants.ShareAlbum>, Void> {
    private IAlbumRepository mRepository;

    public QueryShareAlbumDetailInfoList(IAlbumRepository iAlbumRepository) {
        this.mRepository = iAlbumRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<List<AlbumConstants.ShareAlbum>> buildUseCaseFlowable(Void voidR) {
        return this.mRepository.queryAlbumListShareInfo();
    }
}
