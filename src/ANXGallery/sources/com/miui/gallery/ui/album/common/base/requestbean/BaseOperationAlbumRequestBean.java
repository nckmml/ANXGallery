package com.miui.gallery.ui.album.common.base.requestbean;

public class BaseOperationAlbumRequestBean {
    private long albumId;
    private boolean enable;

    public BaseOperationAlbumRequestBean(long j, boolean z) {
        this.albumId = j;
        this.enable = z;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public boolean isEnable() {
        return this.enable;
    }
}
