package com.miui.gallery.ui.album.common.base.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.ui.album.common.base.requestbean.BaseOperationAlbumRequestBean;
import io.reactivex.Flowable;

public class DoChangeAlbumBackupCase extends BaseUseCase<Boolean, BaseOperationAlbumRequestBean> {
    private IAlbumRepository mRepository;

    public DoChangeAlbumBackupCase(IAlbumRepository iAlbumRepository) {
        this.mRepository = iAlbumRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<Boolean> buildUseCaseFlowable(BaseOperationAlbumRequestBean baseOperationAlbumRequestBean) {
        return this.mRepository.doChangeAlbumBackupStatus(baseOperationAlbumRequestBean.isEnable(), baseOperationAlbumRequestBean.getAlbumId());
    }
}
