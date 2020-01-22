package com.miui.gallery.model.repository.datesource.photo;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.PhotoDetailInfo;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTime;
import com.miui.gallery.util.StorageUtils;
import io.reactivex.Flowable;
import java.util.concurrent.Callable;

public class PhotoLocalDataSource implements IPhotoRepository {
    /* access modifiers changed from: private */
    public boolean checkIsFailed(long[] jArr) {
        return jArr == null || jArr.length <= 0;
    }

    public Flowable<Boolean> editPhotoDateTimeDetailInfo(final EditPhotoDateTime.RequestBean requestBean) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(!PhotoLocalDataSource.this.checkIsFailed(CloudUtils.editPhotoTimeInfoBy(GalleryApp.sGetAndroidContext(), requestBean.getId(), requestBean.getNewTime(), requestBean.getPath(), requestBean.isFavorite())));
            }
        });
    }

    public Flowable<PhotoDetailInfo> getPhotoDetailInfo(final BaseDataItem baseDataItem) {
        return Flowable.fromCallable(new Callable<PhotoDetailInfo>() {
            public PhotoDetailInfo call() throws Exception {
                PhotoDetailInfo detailInfo = baseDataItem.getDetailInfo(GalleryApp.sGetAndroidContext());
                Object detail = detailInfo.getDetail(200);
                if (detail != null) {
                    String str = (String) detail;
                    detailInfo.addDetail(201, StorageUtils.getPathForDisplay(GalleryApp.sGetAndroidContext(), str));
                    detailInfo.addDetail(109, Boolean.valueOf(StorageUtils.isInExternalStorage(GalleryApp.sGetAndroidContext(), str)));
                }
                return detailInfo;
            }
        });
    }

    public Flowable<String> queryAlbumNameByAlbumId(final Long l) {
        return Flowable.fromCallable(new Callable<String>() {
            public String call() throws Exception {
                return CloudUtils.queryAlbumNameByAlbumId(GalleryApp.sGetAndroidContext(), l.longValue());
            }
        });
    }
}
