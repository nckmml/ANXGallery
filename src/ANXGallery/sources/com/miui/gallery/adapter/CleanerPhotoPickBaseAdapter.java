package com.miui.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import com.miui.gallery.Config;
import com.miui.gallery.adapter.ExcludeOnTouchListener;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.picker.helper.CursorUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.AlbumDetailGridItem;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.widget.SortByHeader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;
import java.util.Locale;

public class CleanerPhotoPickBaseAdapter extends AlbumDetailTimeLineAdapter {
    private boolean mClickToPhotoPage = true;

    public CleanerPhotoPickBaseAdapter(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public void onItemClick(View view, int i) {
        int i2 = i;
        ArrayList arrayList = new ArrayList(1);
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = (int) (((float) view.getWidth()) * view.getScaleX());
        int height = (int) (((float) view.getHeight()) * view.getScaleY());
        arrayList.add(new ItemViewInfo(0, iArr[0], iArr[1], width, height));
        ImageLoadParams imageLoadParams = new ImageLoadParams(getItemKey(i2), getLocalPath(i2), new ImageSize(width, height), (RectF) null, 0, getMimeType(i2), false, getFileLength(i2));
        Uri build = GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().build();
        Locale locale = Locale.US;
        Object[] objArr = {Long.valueOf(CursorUtils.getId((Cursor) getItem(i2)))};
        IntentUtil.gotoPhotoPageFromPicker((Activity) this.mContext, build, String.format(locale, "_id = %d", objArr), (String[]) null, (String) null, imageLoadParams, arrayList, !supportFoldBurstItems());
    }

    public void doBindData(View view, Context context, Cursor cursor) {
        AlbumDetailGridItem albumDetailGridItem = (AlbumDetailGridItem) view;
        final int position = cursor.getPosition();
        albumDetailGridItem.bindImage(getClearThumbFilePath(position), getDownloadUri(position), getDisplayImageOptions(position));
        String string = cursor.getString(10);
        long j = cursor.getLong(9);
        if (getCurrentSortBy() == SortByHeader.SortBy.SIZE) {
            albumDetailGridItem.bindFileSize(cursor.getLong(11));
            albumDetailGridItem.bindIndicator((String) null, 0, 0);
        } else {
            long supportedSpecialTypeFlags = Config.ShareAlbumConfig.getSupportedSpecialTypeFlags(cursor.getLong(20));
            albumDetailGridItem.bindFileSize(0);
            albumDetailGridItem.bindIndicator(string, j, supportedSpecialTypeFlags);
        }
        if (this.mViewScrollState == 0) {
            bindContentDescription(albumDetailGridItem, position);
        }
        CheckBox checkBox = albumDetailGridItem.getCheckBox();
        if (this.mClickToPhotoPage) {
            view.setOnTouchListener(new ExcludeOnTouchListener(view, checkBox, new ExcludeOnTouchListener.OnTouchValidListener() {
                public void onTouchValid(View view) {
                    CleanerPhotoPickBaseAdapter.this.onItemClick(view, position);
                }
            }));
        }
    }

    public void setClickToPhotoPageEnable(boolean z) {
        this.mClickToPhotoPage = z;
    }

    public boolean supportFoldBurstItems() {
        return false;
    }
}
