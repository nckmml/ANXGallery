package com.miui.gallery.model.repository.datesource.photo;

import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.PhotoDetailInfo;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTime;
import io.reactivex.Flowable;

public interface IPhotoRepository {
    Flowable<Boolean> editPhotoDateTimeDetailInfo(EditPhotoDateTime.RequestBean requestBean);

    Flowable<PhotoDetailInfo> getPhotoDetailInfo(BaseDataItem baseDataItem);

    Flowable<String> queryAlbumNameByAlbumId(Long l);
}
