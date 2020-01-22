package com.miui.gallery.ui.album.cloudalbum;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.repository.config.ModelManager;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.share.ShareAlbumCacheManager;
import com.miui.gallery.ui.album.cloudalbum.CloudAlbumListContract;
import com.miui.gallery.ui.album.cloudalbum.usecase.QueryCloudAlbumList;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import com.miui.gallery.ui.album.common.base.requestbean.BaseOperationAlbumRequestBean;
import com.miui.gallery.ui.album.common.base.usecase.DoChangeAlbumBackupCase;
import com.miui.gallery.ui.album.common.base.usecase.QueryShareAlbumDetailInfoList;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.List;

public class CloudAlbumListPresenter extends CloudAlbumListContract.P {
    /* access modifiers changed from: private */
    public boolean isFirstLoad = true;
    private BaseUseCase mDoChangeAlbumBackUp;
    private BaseUseCase mQueryCloudAlbumList;
    private BaseUseCase mQueryShareAlbumDetailInfo;

    public CloudAlbumListPresenter() {
        IAlbumRepository iAlbumRepository = (IAlbumRepository) ModelManager.getInstance().getModel(IAlbumRepository.class);
        this.mQueryCloudAlbumList = new QueryCloudAlbumList(iAlbumRepository);
        this.mQueryShareAlbumDetailInfo = new QueryShareAlbumDetailInfoList(iAlbumRepository);
    }

    private void initShareAlbumDetailInfoIfNeed() {
        if (ShareAlbumCacheManager.getInstance().getShareAlbumList() == null) {
            this.mQueryShareAlbumDetailInfo.execute(new DisposableSubscriber<List>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onNext(List list) {
                }
            }, null);
        }
    }

    public CloudAlbumItemViewBean converterTagBeanToCloudAlbumItemItemViewBean(Object obj) {
        if (obj != null && (obj instanceof CloudAlbumItemViewBean)) {
            return (CloudAlbumItemViewBean) obj;
        }
        return null;
    }

    public void doChangeAlbumUploadStatus(final CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        if (cloudAlbumItemViewBean == null) {
            ((CloudAlbumListContract.V) getView()).showChangeAlbumUploadStatusFailed(cloudAlbumItemViewBean);
        } else if (Album.isShareAlbum(cloudAlbumItemViewBean.getId())) {
            ((CloudAlbumListContract.V) getView()).showChangeAlbumCantBeShareAlbum(cloudAlbumItemViewBean);
        } else {
            if (this.mDoChangeAlbumBackUp == null) {
                this.mDoChangeAlbumBackUp = new DoChangeAlbumBackupCase((IAlbumRepository) ModelManager.getInstance().getModel(IAlbumRepository.class));
            }
            this.mDoChangeAlbumBackUp.execute(new DisposableSubscriber<Boolean>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                    ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).showChangeAlbumUploadStatusFailed(cloudAlbumItemViewBean);
                }

                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        CloudAlbumItemViewBean cloudAlbumItemViewBean = cloudAlbumItemViewBean;
                        cloudAlbumItemViewBean.setBackup(!cloudAlbumItemViewBean.isBackup());
                        ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).showChangeAlbumUploadStatusSuccess(cloudAlbumItemViewBean);
                        return;
                    }
                    ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).showChangeAlbumUploadStatusFailed(cloudAlbumItemViewBean);
                }
            }, new BaseOperationAlbumRequestBean(cloudAlbumItemViewBean.getId(), !cloudAlbumItemViewBean.isBackup()));
        }
    }

    public void doChangeAlbumUploadStatus(Object obj) {
        doChangeAlbumUploadStatus(converterTagBeanToCloudAlbumItemItemViewBean(obj));
    }

    public void initData() {
        initShareAlbumDetailInfoIfNeed();
        this.mQueryCloudAlbumList.execute(new DisposableSubscriber<List<CloudAlbumItemViewBean>>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
                ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).showCloudList((List<CloudAlbumItemViewBean>) null);
            }

            public void onNext(List<CloudAlbumItemViewBean> list) {
                if (CloudAlbumListPresenter.this.isFirstLoad) {
                    ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).showCloudList(list);
                    boolean unused = CloudAlbumListPresenter.this.isFirstLoad = false;
                    return;
                }
                ((CloudAlbumListContract.V) CloudAlbumListPresenter.this.getView()).updateCloudList(list);
            }
        }, null);
    }

    public boolean isShareAlbum(CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        if (cloudAlbumItemViewBean == null) {
            return false;
        }
        return Album.isShareAlbum(cloudAlbumItemViewBean.getId());
    }

    public boolean isShareAlbum(Object obj) {
        if (converterTagBeanToCloudAlbumItemItemViewBean(obj) == null) {
            return false;
        }
        return isShareAlbum((CloudAlbumItemViewBean) obj);
    }

    /* access modifiers changed from: protected */
    public void onDestory() {
        super.onDestory();
        BaseUseCase baseUseCase = this.mQueryCloudAlbumList;
        if (baseUseCase != null) {
            baseUseCase.dispose();
        }
        BaseUseCase baseUseCase2 = this.mDoChangeAlbumBackUp;
        if (baseUseCase2 != null) {
            baseUseCase2.dispose();
        }
    }
}
