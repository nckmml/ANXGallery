package com.miui.gallery.ui.album.cloudalbum.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class QueryCloudAlbumList extends BaseUseCase<List<CommonAlbumItemViewBean>, Void> {
    private IAlbumRepository mRepository;

    public QueryCloudAlbumList(IAlbumRepository iAlbumRepository) {
        this.mRepository = iAlbumRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<List<CommonAlbumItemViewBean>> buildUseCaseFlowable(Void voidR) {
        return this.mRepository.queryAlbumsExcludeSystemAndRubbish().map(new Function<List<Album>, List<CommonAlbumItemViewBean>>() {
            public List<CommonAlbumItemViewBean> apply(List<Album> list) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (Album next : list) {
                    if (!next.isBabyAlbum()) {
                        CloudAlbumItemViewBean cloudAlbumItemViewBean = new CloudAlbumItemViewBean();
                        cloudAlbumItemViewBean.mapping(next);
                        arrayList.add(cloudAlbumItemViewBean);
                    }
                }
                return arrayList;
            }
        });
    }
}
