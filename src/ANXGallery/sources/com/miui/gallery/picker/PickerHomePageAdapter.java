package com.miui.gallery.picker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.adapter.PreloadMediaAdapterDeprecated;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.ui.HomePageGridHeaderItem;
import com.miui.gallery.ui.HomePageGridItem;
import com.miui.gallery.util.BurstFilterCursor;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.TalkBackUtil;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import java.util.ArrayList;

public class PickerHomePageAdapter extends PreloadMediaAdapterDeprecated implements CheckableAdapter, StickyGridHeadersBaseAdapter {
    public static final String[] PROJECTION = {"_id", "alias_micro_thumbnail", "alias_create_date", "alias_create_time", "location", "sha1", "serverType", "duration", "mimeType", "alias_sync_state", "thumbnailFile", "localFile", "alias_clear_thumbnail", "alias_is_favorite", "specialTypeFlags", "alias_sort_time", "size", "title", "burst_group_id"};
    private ArrayList<Integer> mGroupItemCount;
    private ArrayList<String> mGroupStartLocations;
    private ArrayList<Integer> mGroupStartPos;

    public PickerHomePageAdapter(Context context, SyncStateDisplay.DisplayScene displayScene) {
        super(context, displayScene);
    }

    private int getSyncState(Cursor cursor) {
        return getSyncStateInternal(cursor.getInt(9));
    }

    public void doBindData(View view, Context context, Cursor cursor) {
        Cursor cursor2 = cursor;
        HomePageGridItem homePageGridItem = (HomePageGridItem) view;
        int position = cursor.getPosition();
        homePageGridItem.bindImage(getClearThumbFilePath(position), getDownloadUri(position), getDisplayImageOptions(position));
        String string = cursor2.getString(8);
        long j = cursor2.getLong(7);
        long j2 = cursor2.getLong(14);
        boolean z = (cursor2 instanceof BurstFilterCursor) && ((BurstFilterCursor) cursor2).isBurstPosition(position);
        if (z) {
            j2 |= 64;
        }
        homePageGridItem.bindIndicator(string, j, j2);
        homePageGridItem.bindFavoriteIndicator(isFavorite(position));
        int syncState = getSyncState(cursor2);
        long j3 = cursor2.getLong(0);
        homePageGridItem.bindSyncIndicator(j3, syncState, this.mShowScene);
        homePageGridItem.setContentDescription(TalkBackUtil.getContentDescriptionForImage(context, cursor2.getLong(3), cursor2.getString(4), cursor2.getString(8)));
        if (z) {
            homePageGridItem.setIsSimilarBestImage(ImageFeatureCacheManager.getInstance().shouldShowSelectionStar(j3, false, false, getBurstItemKeys(position)));
        } else {
            homePageGridItem.setIsSimilarBestImage(ImageFeatureCacheManager.getInstance().shouldShowSelectionStar(j3, false, false));
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<Long> getBurstItemKeys(int i) {
        if (getCursor() != null) {
            return ((BurstFilterCursor) getCursor()).getBurstIdsInGroup(i, 0);
        }
        return null;
    }

    public View getCheckableView(View view) {
        return ((HomePageGridItem) view).getCheckBox();
    }

    public String getClearThumbFilePath(int i) {
        return getMicroPath(getCursorByPosition(i), 12, 5);
    }

    public int getCountForHeader(int i) {
        ArrayList<Integer> arrayList = this.mGroupItemCount;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.get(i).intValue();
    }

    public View getDividerView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public Uri getDownloadUri(int i) {
        return getDownloadUri(getCursorByPosition(i), 9, 0);
    }

    public long getFileLength(int i) {
        return getCursorByPosition(i).getLong(16);
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.home_page_grid_header_item, viewGroup, false);
        }
        ((HomePageGridHeaderItem) view).bindData(GalleryDateUtils.formatRelativeDate(((Cursor) getItem(this.mGroupStartPos.get(i).intValue())).getLong(15)), this.mGroupStartLocations.get(i));
        return view;
    }

    public long getItemKey(int i) {
        return getCursorByPosition(i).getLong(0);
    }

    public String getLocalPath(int i) {
        return getMicroPath(getCursorByPosition(i), 1, 5);
    }

    public String getMicroThumbFilePath(int i) {
        return getCursorByPosition(i).getString(1);
    }

    public String getMimeType(int i) {
        return getCursorByPosition(i).getString(8);
    }

    public int getNumHeaders() {
        ArrayList<Integer> arrayList = this.mGroupItemCount;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public String getOriginFilePath(int i) {
        return getCursorByPosition(i).getString(11);
    }

    public String getSha1(int i) {
        return getCursorByPosition(i).getString(5);
    }

    public String getThumbFilePath(int i) {
        return getCursorByPosition(i).getString(10);
    }

    public boolean isFavorite(int i) {
        return getCursorByPosition(i).getInt(13) > 0;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.home_page_grid_item, viewGroup, false);
    }

    public boolean shouldDrawDivider() {
        return false;
    }

    public Cursor swapCursor(Cursor cursor) {
        ArrayList<String> arrayList;
        ArrayList<Integer> arrayList2;
        BurstFilterCursor burstFilterCursor;
        ArrayList<Integer> arrayList3 = null;
        if (cursor != null) {
            BurstFilterCursor burstFilterCursor2 = new BurstFilterCursor(cursor);
            ArrayList<Integer> integerArrayList = cursor.getExtras().getIntegerArrayList("extra_timeline_item_count_in_group");
            arrayList2 = cursor.getExtras().getIntegerArrayList("extra_timeline_group_start_pos");
            arrayList = cursor.getExtras().getStringArrayList("extra_timeline_group_start_locations");
            ArrayList<Integer> arrayList4 = integerArrayList;
            burstFilterCursor = burstFilterCursor2;
            arrayList3 = arrayList4;
        } else {
            burstFilterCursor = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null || arrayList2 == null || arrayList == null) {
            ArrayList<Integer> arrayList5 = this.mGroupItemCount;
            if (arrayList5 != null) {
                arrayList5.clear();
            }
            ArrayList<Integer> arrayList6 = this.mGroupStartPos;
            if (arrayList6 != null) {
                arrayList6.clear();
            }
            ArrayList<String> arrayList7 = this.mGroupStartLocations;
            if (arrayList7 != null) {
                arrayList7.clear();
            }
        } else {
            this.mGroupItemCount = new ArrayList<>(arrayList3);
            this.mGroupStartPos = new ArrayList<>(arrayList2);
            this.mGroupStartLocations = new ArrayList<>(arrayList);
            ArrayList<Integer> resultPos = burstFilterCursor.getResultPos();
            if (MiscUtil.isValid(resultPos)) {
                BurstFilterCursor.wrapGroupInfos(resultPos, cursor.getCount(), this.mGroupStartPos, this.mGroupItemCount);
            }
        }
        return super.swapCursor(burstFilterCursor);
    }
}
