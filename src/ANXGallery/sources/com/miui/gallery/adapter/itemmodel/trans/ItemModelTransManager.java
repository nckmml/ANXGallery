package com.miui.gallery.adapter.itemmodel.trans;

import com.miui.epoxy.EpoxyModel;
import com.miui.gallery.adapter.itemmodel.CloudAlbumItemModel;
import com.miui.gallery.adapter.itemmodel.HiddenAlbumItemModel;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;

public class ItemModelTransManager {
    public static <T> EpoxyModel<?> transDataToModel(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof HiddenAlbumItemViewBean) {
            return new HiddenAlbumItemModel((HiddenAlbumItemViewBean) t);
        }
        if (t instanceof CloudAlbumItemViewBean) {
            return new CloudAlbumItemModel((CloudAlbumItemViewBean) t);
        }
        return null;
    }
}
