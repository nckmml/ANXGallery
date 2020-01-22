package com.miui.gallery.ui;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.model.FaceAlbumCover;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.BindImageHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.ArrayList;
import java.util.List;

public class AlbumPageListFaceItem extends AlbumPageListItemBase {
    private static List<Integer> sAlbumCovers = new ArrayList();

    static {
        sAlbumCovers.add(Integer.valueOf(R.id.album_cover_first));
        sAlbumCovers.add(Integer.valueOf(R.id.album_cover_second));
        sAlbumCovers.add(Integer.valueOf(R.id.album_cover_third));
        sAlbumCovers.add(Integer.valueOf(R.id.album_cover_fourth));
    }

    public AlbumPageListFaceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void bindImage(ImageView imageView, String str, Uri uri, DisplayImageOptions displayImageOptions, RectF rectF) {
        BindImageHelper.bindFaceImage(str, uri, imageView, displayImageOptions, Config.ThumbConfig.get().sMicroTargetSize, rectF, false);
    }

    private DisplayImageOptions buildDisplayFaceOptions(DisplayImageOptions.Builder builder, long j) {
        return j > 0 ? builder.considerFileLength(true).fileLength(j).build() : builder.considerFileLength(false).build();
    }

    private Uri getDownloadUri(long j, int i) {
        if (i == 0) {
            return ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_URI, j);
        }
        return null;
    }

    private ImageView getImageView(int i) {
        return (ImageView) findViewById(sAlbumCovers.get(i).intValue());
    }

    private void setDefaultCover() {
        for (int i = 0; i < sAlbumCovers.size(); i++) {
            getImageView(i).setImageDrawable((Drawable) null);
        }
    }

    public void bindImageAndAlbumCount(Bundle bundle, DisplayImageOptions.Builder builder) {
        ArrayList arrayList;
        if (bundle != null) {
            bundle.setClassLoader(FaceAlbumCover.class.getClassLoader());
            arrayList = bundle.getParcelableArrayList("face_album_cover");
        } else {
            arrayList = null;
        }
        if (bundle == null || arrayList == null) {
            setDefaultCover();
            this.mAlbumPhotoCount.setText(MovieStatUtils.DOWNLOAD_SUCCESS);
            return;
        }
        int i = 0;
        while (i < arrayList.size() && i < 4) {
            FaceAlbumCover faceAlbumCover = (FaceAlbumCover) arrayList.get(i);
            bindImage(getImageView(i), faceAlbumCover.coverPath, getDownloadUri(faceAlbumCover.coverId, faceAlbumCover.coverSyncState), buildDisplayFaceOptions(builder, faceAlbumCover.coverSize), faceAlbumCover.faceRectF);
            i++;
        }
        if (i < sAlbumCovers.size()) {
            while (i < sAlbumCovers.size()) {
                getImageView(i).setImageDrawable((Drawable) null);
                i++;
            }
        }
        this.mAlbumPhotoCount.setText(String.valueOf(bundle.getInt("face_album_count")));
    }
}
