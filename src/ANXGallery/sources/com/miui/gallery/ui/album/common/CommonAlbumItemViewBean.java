package com.miui.gallery.ui.album.common;

import android.net.Uri;
import com.miui.gallery.model.Album;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import com.miui.gallery.ui.album.common.base.BaseViewBean;
import java.util.Objects;

public class CommonAlbumItemViewBean<R extends CommonAlbumItemViewBean> extends BaseViewBean {
    protected Uri albumCover;
    protected String albumCoverPath;
    protected String albumName;
    protected String albumPhotoCountText;
    protected long coverSize;
    private Album source;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        CommonAlbumItemViewBean commonAlbumItemViewBean = (CommonAlbumItemViewBean) obj;
        return this.coverSize == commonAlbumItemViewBean.coverSize && Objects.equals(this.albumName, commonAlbumItemViewBean.albumName) && Objects.equals(this.albumCover, commonAlbumItemViewBean.albumCover) && Objects.equals(this.albumCoverPath, commonAlbumItemViewBean.albumCoverPath) && Objects.equals(this.albumPhotoCountText, commonAlbumItemViewBean.albumPhotoCountText) && Objects.equals(Long.valueOf(this.source.getAttributes()), Long.valueOf(commonAlbumItemViewBean.source.getAttributes()));
    }

    public Uri getAlbumCover() {
        return this.albumCover;
    }

    public String getAlbumCoverPath() {
        return this.albumCoverPath;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public long getCoverSize() {
        return this.coverSize;
    }

    public Album getSource() {
        return this.source;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.albumName, this.albumCover, this.albumCoverPath, Long.valueOf(this.coverSize), this.albumPhotoCountText, Long.valueOf(this.source.getAttributes())});
    }

    public void mapping(Album album) {
        setId(album.getAlbumId());
        setAlbumCoverPath(album.getCoverPath());
        setAlbumName(album.getLocalizedAlbumName());
        setAlbumCover(Album.getDownloadUri(album.getCoverSyncState(), album.getCoverId()));
        setCoverSize(album.getCoverSize());
        setSource(album);
        setAlbumPhotoCountText(String.valueOf(album.getCount()));
    }

    public void setAlbumCover(Uri uri) {
        this.albumCover = uri;
    }

    public void setAlbumCoverPath(String str) {
        this.albumCoverPath = str;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setAlbumPhotoCountText(String str) {
        this.albumPhotoCountText = str;
    }

    public void setCoverSize(long j) {
        this.coverSize = j;
    }

    public void setSource(Album album) {
        this.source = album;
    }

    public String toString() {
        return "BaseViewBean{albumName='" + this.albumName + '\'' + ", albumCover=" + this.albumCover + ", albumCoverPath='" + this.albumCoverPath + '\'' + ", id=" + this.id + ", coverSize=" + this.coverSize + ", source=" + this.source + '}';
    }
}
