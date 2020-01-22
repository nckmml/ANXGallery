package com.miui.gallery.ui.album.cloudalbum;

import com.miui.gallery.app.base.BaseListPageFragment;
import com.miui.gallery.base_optimization.mvp.presenter.BasePresenter;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import java.util.List;

public interface CloudAlbumListContract {

    public static abstract class P extends BasePresenter<V> {
        public abstract CloudAlbumItemViewBean converterTagBeanToCloudAlbumItemItemViewBean(Object obj);

        public abstract void doChangeAlbumUploadStatus(CloudAlbumItemViewBean cloudAlbumItemViewBean);

        public abstract void doChangeAlbumUploadStatus(Object obj);

        public abstract void initData();

        public abstract boolean isShareAlbum(CloudAlbumItemViewBean cloudAlbumItemViewBean);

        public abstract boolean isShareAlbum(Object obj);
    }

    public static abstract class V<T, P extends IPresenter> extends BaseListPageFragment<T, P> {
        public abstract void showChangeAlbumCantBeShareAlbum(CloudAlbumItemViewBean cloudAlbumItemViewBean);

        public abstract void showChangeAlbumUploadStatusFailed(CloudAlbumItemViewBean cloudAlbumItemViewBean);

        public abstract void showChangeAlbumUploadStatusSuccess(CloudAlbumItemViewBean cloudAlbumItemViewBean);

        public abstract void showCloudList(List<CloudAlbumItemViewBean> list);

        public abstract void updateCloudList(List<CloudAlbumItemViewBean> list);
    }
}
