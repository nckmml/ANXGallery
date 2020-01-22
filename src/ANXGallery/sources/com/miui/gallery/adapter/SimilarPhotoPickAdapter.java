package com.miui.gallery.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.ui.AlbumDetailGridItem;
import com.miui.gallery.ui.SimilarPhotoPickGridHeaderItem;
import com.miui.gallery.util.MiscUtil;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import java.util.ArrayList;

public class SimilarPhotoPickAdapter extends CleanerPhotoPickBaseAdapter implements StickyGridHeadersBaseAdapter {
    private long[] mGroupIds;
    private ArrayList<Integer> mGroupItemCount;
    private ArrayList<Integer> mGroupStartPos;
    private ImageSize mMicroImageSize;

    public SimilarPhotoPickAdapter(Context context) {
        super(context);
        this.mMicroImageSize = new ImageSize(context.getResources().getDimensionPixelSize(R.dimen.similar_photo_pick_item_width), context.getResources().getDimensionPixelSize(R.dimen.similar_photo_pick_item_height));
    }

    public void doBindData(View view, Context context, Cursor cursor) {
        super.doBindData(view, context, cursor);
        ((AlbumDetailGridItem) view).setIsSimilarBestImage(this.mGroupStartPos.contains(Integer.valueOf(cursor.getPosition())));
    }

    public int getCountForHeader(int i) {
        if (MiscUtil.isValid(this.mGroupItemCount)) {
            return this.mGroupItemCount.get(i).intValue();
        }
        return 0;
    }

    public View getDividerView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public long[] getGroupIds() {
        return this.mGroupIds;
    }

    public ArrayList<Integer> getHeaderPositions() {
        return this.mGroupStartPos;
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.similar_photo_pick_grid_header_item, viewGroup, false);
        }
        if (MiscUtil.isValid(this.mGroupStartPos)) {
            i = this.mGroupStartPos.get(i).intValue();
        }
        ((SimilarPhotoPickGridHeaderItem) view).bindData(getSortTime(i));
        return view;
    }

    public int getNumHeaders() {
        if (MiscUtil.isValid(this.mGroupItemCount)) {
            return this.mGroupItemCount.size();
        }
        return 0;
    }

    public long getSortTime(int i) {
        return getCursorByPosition(i).getLong(17);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        AlbumDetailGridItem albumDetailGridItem = (AlbumDetailGridItem) LayoutInflater.from(context).inflate(R.layout.album_detail_grid_item, viewGroup, false);
        albumDetailGridItem.setImageSize(this.mMicroImageSize);
        return albumDetailGridItem;
    }

    public boolean shouldDrawDivider() {
        return false;
    }

    public Cursor swapCursor(Cursor cursor) {
        ArrayList<Integer> arrayList;
        ArrayList<Integer> arrayList2;
        long[] jArr;
        if (cursor != null) {
            Bundle extras = cursor.getExtras();
            arrayList2 = extras.getIntegerArrayList("extra_timeline_item_count_in_group");
            arrayList = extras.getIntegerArrayList("extra_timeline_group_start_pos");
            jArr = extras.getLongArray("extra_timeline_group_ids");
        } else {
            jArr = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList2 == null || arrayList == null || jArr == null) {
            ArrayList<Integer> arrayList3 = this.mGroupItemCount;
            if (arrayList3 != null) {
                arrayList3.clear();
            }
            ArrayList<Integer> arrayList4 = this.mGroupStartPos;
            if (arrayList4 != null) {
                arrayList4.clear();
            }
            if (this.mGroupIds != null) {
                this.mGroupIds = null;
            }
        } else {
            this.mGroupItemCount = new ArrayList<>(arrayList2);
            this.mGroupStartPos = new ArrayList<>(arrayList);
            this.mGroupIds = (long[]) jArr.clone();
        }
        return super.swapCursor(cursor);
    }
}
