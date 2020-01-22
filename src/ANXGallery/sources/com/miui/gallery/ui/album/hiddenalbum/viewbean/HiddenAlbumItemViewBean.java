package com.miui.gallery.ui.album.hiddenalbum.viewbean;

import com.miui.gallery.R;
import com.miui.gallery.model.Album;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import com.miui.gallery.util.ResourceUtils;

public class HiddenAlbumItemViewBean extends CommonAlbumItemViewBean<HiddenAlbumItemViewBean> {
    private String photoNumberText;

    public String getPhotoNumberText() {
        return this.photoNumberText;
    }

    public void mapping(Album album) {
        super.mapping(album);
        setPhotoNumberText(ResourceUtils.getQuantityString(R.plurals.album_photo_count_text, album.getCount(), Integer.valueOf(album.getCount())));
    }

    public void setPhotoNumberText(String str) {
        this.photoNumberText = str;
    }
}
