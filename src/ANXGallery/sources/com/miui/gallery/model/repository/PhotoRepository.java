package com.miui.gallery.model.repository;

import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.PhotoDetailInfo;
import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import com.miui.gallery.model.repository.datesource.photo.PhotoLocalDataSource;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTime;
import io.reactivex.Flowable;

public class PhotoRepository implements IPhotoRepository {
    public IPhotoRepository mLocalDataSource;

    public PhotoRepository() {
        this(new PhotoLocalDataSource());
    }

    public PhotoRepository(IPhotoRepository iPhotoRepository) {
        this.mLocalDataSource = iPhotoRepository;
    }

    public Flowable<Boolean> editPhotoDateTimeDetailInfo(EditPhotoDateTime.RequestBean requestBean) {
        return this.mLocalDataSource.editPhotoDateTimeDetailInfo(requestBean);
    }

    public Flowable<PhotoDetailInfo> getPhotoDetailInfo(BaseDataItem baseDataItem) {
        return this.mLocalDataSource.getPhotoDetailInfo(baseDataItem);
    }

    public Flowable<String> queryAlbumNameByAlbumId(Long l) {
        return this.mLocalDataSource.queryAlbumNameByAlbumId(l);
    }
}
