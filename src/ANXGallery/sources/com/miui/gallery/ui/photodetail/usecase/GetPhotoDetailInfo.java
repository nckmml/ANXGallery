package com.miui.gallery.ui.photodetail.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.PhotoDetailInfo;
import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import com.miui.gallery.ui.photodetail.viewbean.PhotoDetailViewBean;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class GetPhotoDetailInfo extends BaseUseCase<PhotoDetailViewBean, BaseDataItem> {
    private IPhotoRepository mRepository;

    public GetPhotoDetailInfo(IPhotoRepository iPhotoRepository) {
        this.mRepository = iPhotoRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<PhotoDetailViewBean> buildUseCaseFlowable(BaseDataItem baseDataItem) {
        return baseDataItem == null ? Flowable.empty() : this.mRepository.getPhotoDetailInfo(baseDataItem).map(new Function<PhotoDetailInfo, PhotoDetailViewBean>() {
            public PhotoDetailViewBean apply(PhotoDetailInfo photoDetailInfo) throws Exception {
                PhotoDetailViewBean photoDetailViewBean = new PhotoDetailViewBean();
                photoDetailViewBean.mapping(photoDetailInfo);
                return photoDetailViewBean;
            }
        });
    }
}
