package com.miui.gallery.ui.album.hiddenalbum;

import com.miui.gallery.model.repository.config.ModelManager;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.ui.album.common.base.usecase.DoUnHideAlbumCase;
import com.miui.gallery.ui.album.hiddenalbum.HiddenAlbumContract;
import com.miui.gallery.ui.album.hiddenalbum.usecase.QueryHiddenList;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.List;

public class HiddenAlbumPresenter extends HiddenAlbumContract.P {
    /* access modifiers changed from: private */
    public boolean isFirstLoad = true;
    private QueryHiddenList mSelectHiddenList;
    private DoUnHideAlbumCase mUnHideAlbum;

    public void initData() {
        if (this.mSelectHiddenList == null) {
            this.mSelectHiddenList = new QueryHiddenList((IAlbumRepository) ModelManager.getInstance().getModel(IAlbumRepository.class));
        }
        this.mSelectHiddenList.execute(new DisposableSubscriber<List<HiddenAlbumItemViewBean>>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
                ((HiddenAlbumContract.V) HiddenAlbumPresenter.this.getView()).showHiddenListData((List<HiddenAlbumItemViewBean>) null);
            }

            public void onNext(List<HiddenAlbumItemViewBean> list) {
                if (HiddenAlbumPresenter.this.isFirstLoad) {
                    ((HiddenAlbumContract.V) HiddenAlbumPresenter.this.getView()).showHiddenListData(list);
                    boolean unused = HiddenAlbumPresenter.this.isFirstLoad = false;
                    return;
                }
                ((HiddenAlbumContract.V) HiddenAlbumPresenter.this.getView()).updateHiddenListData(list);
            }
        }, null);
    }

    /* access modifiers changed from: protected */
    public void onDestory() {
        super.onDestory();
        DoUnHideAlbumCase doUnHideAlbumCase = this.mUnHideAlbum;
        if (doUnHideAlbumCase != null) {
            doUnHideAlbumCase.dispose();
        }
        QueryHiddenList queryHiddenList = this.mSelectHiddenList;
        if (queryHiddenList != null) {
            queryHiddenList.dispose();
        }
    }

    public void unAlbumHide(final HiddenAlbumItemViewBean hiddenAlbumItemViewBean) {
        if (hiddenAlbumItemViewBean != null && hiddenAlbumItemViewBean.getId() > 0) {
            if (this.mUnHideAlbum == null) {
                this.mUnHideAlbum = new DoUnHideAlbumCase((IAlbumRepository) ModelManager.getInstance().getModel(IAlbumRepository.class));
            }
            this.mUnHideAlbum.execute(new DisposableSubscriber<Boolean>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                    ((HiddenAlbumContract.V) HiddenAlbumPresenter.this.getView()).showUnHiddenAlbumIsFaild();
                }

                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        ((HiddenAlbumContract.V) HiddenAlbumPresenter.this.getView()).showUnHiddenAlbumIsSuccess(hiddenAlbumItemViewBean);
                    }
                }
            }, Long.valueOf(hiddenAlbumItemViewBean.getId()));
        }
    }
}
