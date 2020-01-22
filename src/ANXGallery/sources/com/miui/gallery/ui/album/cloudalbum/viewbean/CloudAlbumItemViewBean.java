package com.miui.gallery.ui.album.cloudalbum.viewbean;

import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.model.Album;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import com.miui.gallery.util.ResourceUtils;

public class CloudAlbumItemViewBean extends CommonAlbumItemViewBean<CloudAlbumItemViewBean> {
    private boolean isBabyAlbum;
    private boolean isBackup;
    private String photoNumberText;

    public String getPhotoNumberText() {
        return this.photoNumberText;
    }

    public boolean isBackup() {
        return this.isBackup;
    }

    public void mapping(Album album) {
        String str;
        super.mapping(album);
        if (album.isOtherShareAlbum() || album.isOwnerShareAlbum()) {
            String shareAlbumInfoTipTextIfNeed = ShareAlbumManager.getShareAlbumInfoTipTextIfNeed(album);
            StringBuilder sb = new StringBuilder();
            sb.append(ResourceUtils.getQuantityString(R.plurals.album_photo_count_text, album.getCount(), Integer.valueOf(album.getCount())));
            if (TextUtils.isEmpty(shareAlbumInfoTipTextIfNeed)) {
                str = "";
            } else {
                str = " | " + shareAlbumInfoTipTextIfNeed;
            }
            sb.append(str);
            setPhotoNumberText(sb.toString());
        } else {
            setPhotoNumberText(ResourceUtils.getQuantityString(R.plurals.album_photo_count_text, album.getCount(), Integer.valueOf(album.getCount())));
        }
        setBackup(album.isAutoUploadedAlbum());
        setBabyAlbum(album.isBabyAlbum());
    }

    public void setBabyAlbum(boolean z) {
        this.isBabyAlbum = z;
    }

    public void setBackup(boolean z) {
        this.isBackup = z;
    }

    public void setPhotoNumberText(String str) {
        this.photoNumberText = str;
    }
}
