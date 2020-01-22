package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class BabyAlbumDetailFaceHeaderItem extends LinearLayout {
    private static DisplayImageOptions.Builder sDisplayImageOptionBuilder;
    private static DisplayImageOptions sDisplayImageOptionsLocalFace;
    private static DisplayImageOptions sDisplayImageOptionsNetFace;
    private TextView mAgeCurrent;
    private ImageView mBackground;
    private ImageView mFace;
    private ImageViewAware mImageAwareFace;

    public BabyAlbumDetailFaceHeaderItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void intialBuilder() {
        if (sDisplayImageOptionBuilder == null) {
            sDisplayImageOptionBuilder = new DisplayImageOptions.Builder().considerExifParams(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).displayer(new PeopleItemBitmapDisplayer(true)).usingRegionDecoderFace(true).showStubImage(R.drawable.baby_album_sharer_default_big);
        }
    }

    public void bindHeadFacePic(String str, RectF rectF) {
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(str), this.mImageAwareFace, sDisplayImageOptionsLocalFace, (ImageSize) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null, rectF);
    }

    public void bindHeadFacePicFromNet(String str, RectF rectF) {
        if (sDisplayImageOptionsNetFace == null) {
            intialBuilder();
            sDisplayImageOptionsNetFace = sDisplayImageOptionBuilder.cacheOnDisc().build();
        }
        ImageLoader.getInstance().displayImage(str, this.mImageAwareFace, sDisplayImageOptionsNetFace, (ImageSize) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null, rectF);
    }

    public void bindHeaderBackgroundPic(String str, Uri uri, DisplayImageOptions displayImageOptions) {
        BindImageHelper.bindImage(str, uri, DownloadType.THUMBNAIL, this.mBackground, displayImageOptions, (ImageSize) null);
    }

    public Bitmap getHeadFacePic() {
        return this.mImageAwareFace.getBitmap();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBackground = (ImageView) findViewById(R.id.background);
        this.mFace = (ImageView) findViewById(R.id.face);
        this.mImageAwareFace = new ImageViewAware(this.mFace);
        this.mAgeCurrent = (TextView) findViewById(R.id.age_current);
        if (sDisplayImageOptionsLocalFace == null) {
            intialBuilder();
            sDisplayImageOptionsLocalFace = sDisplayImageOptionBuilder.cacheThumbnail(true).loadFromMicroCache(true).build();
        }
    }

    public void setAge(String str) {
        this.mAgeCurrent.setText(str);
    }

    public void setOnBackgroundClickListener(View.OnClickListener onClickListener) {
        this.mBackground.setOnClickListener(onClickListener);
    }

    public void setOnFaceClickListener(View.OnClickListener onClickListener) {
        this.mFace.setOnClickListener(onClickListener);
    }
}
