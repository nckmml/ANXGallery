package com.miui.gallery.ui.album.hiddenalbum.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class QueryHiddenList extends BaseUseCase<List<HiddenAlbumItemViewBean>, Void> {
    private IAlbumRepository mAlbumRepository;

    public QueryHiddenList(IAlbumRepository iAlbumRepository) {
        this.mAlbumRepository = iAlbumRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<List<HiddenAlbumItemViewBean>> buildUseCaseFlowable(Void voidR) {
        return this.mAlbumRepository.queryHiddenAlbum().map(new Function<List<Album>, List<HiddenAlbumItemViewBean>>() {
            public List<HiddenAlbumItemViewBean> apply(List<Album> list) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (Album mapping : list) {
                    HiddenAlbumItemViewBean hiddenAlbumItemViewBean = new HiddenAlbumItemViewBean();
                    hiddenAlbumItemViewBean.mapping(mapping);
                    arrayList.add(hiddenAlbumItemViewBean);
                }
                return arrayList;
            }
        });
    }
}
