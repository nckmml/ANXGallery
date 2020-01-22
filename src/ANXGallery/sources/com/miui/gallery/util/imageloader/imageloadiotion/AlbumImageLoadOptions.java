package com.miui.gallery.util.imageloader.imageloadiotion;

import com.miui.gallery.Config;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.search.widget.RoundedCornerRectBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;

public class AlbumImageLoadOptions extends BaseImageLoadOption {
    private DisplayImageOptions mHiddenOrCloudAlbumImageOptions;
    private DisplayImageOptions.Builder mHiddenOrCloudAlbumImageOptionsBuilder;
    private RoundedCornerRectBitmapDisplayer mHiddenOrCloudAlbumImageRoundedCornerDisplayer;
    private int mHiddenOrCloudAlbumRoundedCornerValue;

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final AlbumImageLoadOptions INSTANCE = new AlbumImageLoadOptions();
    }

    private AlbumImageLoadOptions() {
    }

    public static AlbumImageLoadOptions getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void initHiddenOrCloudAlbumImageOptions() {
        if (this.mHiddenOrCloudAlbumImageOptions == null) {
            this.mHiddenOrCloudAlbumImageOptionsBuilder = cloneDefalutImageOptionsBuilder();
            this.mHiddenOrCloudAlbumImageOptionsBuilder.showImageOnFail(R.drawable.default_album_cover);
            this.mHiddenOrCloudAlbumRoundedCornerValue = GalleryApp.sGetAndroidContext().getResources().getDimensionPixelSize(R.dimen.grid_hidden_album_cover_rounded_corner_size);
            this.mHiddenOrCloudAlbumImageRoundedCornerDisplayer = new RoundedCornerRectBitmapDisplayer(this.mHiddenOrCloudAlbumRoundedCornerValue);
            this.mHiddenOrCloudAlbumImageOptionsBuilder.displayer(this.mHiddenOrCloudAlbumImageRoundedCornerDisplayer);
            this.mHiddenOrCloudAlbumImageOptions = this.mHiddenOrCloudAlbumImageOptionsBuilder.build();
        }
    }

    public DisplayImageOptions getHiddenAlbumImageOptions(long j) {
        initHiddenOrCloudAlbumImageOptions();
        return j > 0 ? this.mHiddenOrCloudAlbumImageOptionsBuilder.considerFileLength(true).fileLength(j).displayer(this.mHiddenOrCloudAlbumImageRoundedCornerDisplayer).build() : getHiddenOrCloudAlbumImageOptions();
    }

    public DisplayImageOptions getHiddenOrCloudAlbumImageOptions() {
        initHiddenOrCloudAlbumImageOptions();
        return this.mHiddenOrCloudAlbumImageOptions;
    }

    public ImageSize getMicroTargetSize() {
        return Config.ThumbConfig.get().sMicroTargetSize;
    }

    public void initDefalutOption() {
        super.initDefalutOption();
        if (this.mHiddenOrCloudAlbumImageOptions == null) {
            this.mHiddenOrCloudAlbumImageOptionsBuilder = cloneDefalutImageOptionsBuilder();
            this.mHiddenOrCloudAlbumImageOptionsBuilder.showImageOnFail(R.drawable.default_album_empty_cover);
            this.mHiddenOrCloudAlbumRoundedCornerValue = GalleryApp.sGetAndroidContext().getResources().getDimensionPixelSize(R.dimen.album_page_item_corner_radius);
            this.mHiddenOrCloudAlbumImageRoundedCornerDisplayer = new RoundedCornerRectBitmapDisplayer(this.mHiddenOrCloudAlbumRoundedCornerValue);
            this.mHiddenOrCloudAlbumImageOptionsBuilder.displayer(this.mHiddenOrCloudAlbumImageRoundedCornerDisplayer);
            this.mHiddenOrCloudAlbumImageOptions = this.mHiddenOrCloudAlbumImageOptionsBuilder.build();
        }
    }
}
