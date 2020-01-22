package com.miui.gallery.ui.album.common.base.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import io.reactivex.Flowable;

public class DoUnHideAlbumCase extends BaseUseCase<Boolean, Long> {
    private IAlbumRepository mAlbumRepository;

    public DoUnHideAlbumCase(IAlbumRepository iAlbumRepository) {
        this.mAlbumRepository = iAlbumRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<Boolean> buildUseCaseFlowable(Long l) {
        return (l == null || l.longValue() <= 0) ? Flowable.empty() : this.mAlbumRepository.cancelAlbumHiddenStatus(l.longValue());
    }
}
