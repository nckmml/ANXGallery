package com.miui.gallery.ui.album.hiddenalbum;

import com.miui.gallery.app.base.BaseListPageFragment;
import com.miui.gallery.base_optimization.mvp.presenter.BasePresenter;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;
import java.util.List;

public interface HiddenAlbumContract {

    public static abstract class P extends BasePresenter<V> {
        public abstract void initData();

        public abstract void unAlbumHide(HiddenAlbumItemViewBean hiddenAlbumItemViewBean);
    }

    public static abstract class V<T, P extends IPresenter> extends BaseListPageFragment<T, P> {
        public abstract void showHiddenListData(List<HiddenAlbumItemViewBean> list);

        public abstract void showUnHiddenAlbumIsFaild();

        public abstract void showUnHiddenAlbumIsSuccess(HiddenAlbumItemViewBean hiddenAlbumItemViewBean);

        public abstract void updateHiddenListData(List<HiddenAlbumItemViewBean> list);
    }
}
