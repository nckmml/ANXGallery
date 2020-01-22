package com.miui.gallery.adapter;

import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.ui.HomePageGridHeaderItem;
import com.miui.gallery.ui.MonthView;
import com.miui.gallery.util.BurstFilterCursor;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.uil.CloudUriAdapter;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import com.miui.gallery.widget.recyclerview.transition.FuzzyMatchItem;
import com.miui.gallery.widget.stickyheader.core.HeaderViewHolder;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MediaMonthAdapter extends RecyclerView.Adapter<BaseViewHolder> implements StickyHeaderAdapter<HeaderViewHolder> {
    public static final String[] PROJECTION = {"_id", "alias_micro_thumbnail", "alias_create_date", "alias_create_time", "location", "sha1", "serverType", "duration", "mimeType", "alias_sync_state", "thumbnailFile", "localFile", "alias_clear_thumbnail", "alias_is_favorite", "specialTypeFlags", "alias_sort_time", "size", "title", "burst_group_id"};
    private static String sMainMicroFolder = StorageUtils.getPriorMicroThumbnailDirectory();
    private BurstFilterCursor mCursor;
    private ArrayList<Integer> mGroupItemCount;
    private ArrayList<String> mGroupStartLocations;
    private ArrayList<Integer> mGroupStartPos;

    private static class MonthHolder extends BaseViewHolder {
        MonthView mItemView;

        public MonthHolder(MonthView monthView) {
            super(monthView);
            this.mItemView = monthView;
        }
    }

    private String getClearThumbFilePath(int i) {
        return getMicroPath(getItem(i), 12, 5);
    }

    private Uri getDownloadUri(int i) {
        Cursor item = getItem(i);
        if (item.getInt(9) == 0) {
            return CloudUriAdapter.getDownloadUri(item.getLong(0));
        }
        return null;
    }

    private long getFileLength(int i) {
        return getItem(i).getLong(16);
    }

    private Cursor getItem(int i) {
        BurstFilterCursor burstFilterCursor = this.mCursor;
        if (burstFilterCursor == null) {
            return null;
        }
        burstFilterCursor.moveToPosition(i);
        return this.mCursor;
    }

    private long getItemKey(int i) {
        return getItem(i).getLong(0);
    }

    private long getItemSortTime(int i) {
        return getItem(i).getLong(15);
    }

    private static String getMicroPath(Cursor cursor, int i, int i2) {
        String string = cursor.getString(i);
        return (!TextUtils.isEmpty(string) || i2 < 0) ? string : FileUtils.concat(sMainMicroFolder, CloudUtils.getThumbnailNameBySha1(cursor.getString(i2)));
    }

    private List<MonthView.MonthItem> parseMonthData(int i) {
        int intValue = this.mGroupStartPos.get(i).intValue();
        int intValue2 = this.mGroupItemCount.get(i).intValue() + intValue;
        LinkedList linkedList = new LinkedList();
        while (intValue < intValue2) {
            linkedList.add(parseMonthItem(intValue));
            intValue++;
        }
        return linkedList;
    }

    private MonthView.MonthItem parseMonthItem(int i) {
        return new MonthView.MonthItem.Builder().setId(getItemKey(i)).setLocalPath(getClearThumbFilePath(i)).setDownloadUri(getDownloadUri(i)).setDate(getItemSortTime(i)).setFileLength(getFileLength(i)).setPosition(i).build();
    }

    public Rect estimateItemFrame(int i, int i2, long j) {
        if (i2 < 0 || i2 > this.mGroupStartPos.size() - 1) {
            Log.w("MediaMonthAdapter", "out of bounds position %d, size %d", Integer.valueOf(i2), Integer.valueOf(this.mGroupStartPos.size()));
            return null;
        }
        float f = (((float) i) - 9.0f) / 10.0f;
        int intValue = this.mGroupStartPos.get(i2).intValue();
        int intValue2 = this.mGroupItemCount.get(i2).intValue() + intValue;
        int i3 = intValue;
        while (i3 < intValue2 && getItemKey(i3) != j) {
            i3++;
        }
        if (i3 == intValue2) {
            Log.w("MediaMonthAdapter", "not found %d, position %d, size %d", Long.valueOf(j), Integer.valueOf(i2), Integer.valueOf(this.mGroupStartPos.size()));
            return null;
        }
        int i4 = i3 - intValue;
        float f2 = 1.0f + f;
        int i5 = (int) (((float) (i4 % 10)) * f2);
        int i6 = (int) (f2 * ((float) (i4 / 10)));
        return new Rect(i5, i6, (int) (((float) i5) + f), (int) (((float) i6) + f));
    }

    public FuzzyMatchItem findFuzzyMatchItem(FuzzyMatchItem fuzzyMatchItem) {
        int i;
        long j;
        String str;
        long j2;
        long itemKey;
        FuzzyMatchItem fuzzyMatchItem2 = fuzzyMatchItem;
        if (fuzzyMatchItem2 == null || this.mCursor == null) {
            return null;
        }
        int i2 = 1;
        while (true) {
            if (i2 >= this.mGroupStartPos.size()) {
                i = -1;
                break;
            }
            if (fuzzyMatchItem2.mDate > getItemSortTime(this.mGroupStartPos.get(i2).intValue())) {
                i = i2 - 1;
                break;
            }
            i2++;
        }
        if (i == -1) {
            i = this.mGroupStartPos.size() - 1;
        }
        int i3 = i;
        if (i3 != -1 && i3 < this.mGroupStartPos.size()) {
            int intValue = this.mGroupStartPos.get(i3).intValue();
            int intValue2 = this.mGroupItemCount.get(i3).intValue() + intValue;
            while (true) {
                if (intValue >= intValue2) {
                    break;
                }
                itemKey = getItemKey(intValue);
                if (itemKey == fuzzyMatchItem2.mId || fuzzyMatchItem2.mDate > getItemSortTime(intValue) || intValue == intValue2 - 1) {
                    str = getItem(intValue).getString(4);
                    j = getItemSortTime(intValue);
                    j2 = itemKey;
                } else {
                    intValue++;
                }
            }
            str = getItem(intValue).getString(4);
            j = getItemSortTime(intValue);
            j2 = itemKey;
            return new FuzzyMatchItem(i3, j2, str, j);
        }
        str = null;
        j2 = -1;
        j = 0;
        return new FuzzyMatchItem(i3, j2, str, j);
    }

    public int getHeaderCount() {
        ArrayList<Integer> arrayList = this.mGroupStartPos;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public long getHeaderId(int i) {
        return (long) i;
    }

    public int getHeaderIndex(int i) {
        return i;
    }

    public int getHeaderViewType(int i) {
        return 0;
    }

    public int getItemCount() {
        ArrayList<Integer> arrayList = this.mGroupStartPos;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public long getItemId(int i) {
        ArrayList<Integer> arrayList = this.mGroupStartPos;
        if (arrayList == null) {
            return -1;
        }
        return getItemKey(arrayList.get(i).intValue());
    }

    public int[] getItemPositionInterval(int i) {
        return new int[]{i, i};
    }

    public void onBindHeaderViewHolder(HeaderViewHolder headerViewHolder, int i) {
        int intValue = this.mGroupStartPos.get(i).intValue();
        ((HomePageGridHeaderItem) headerViewHolder.itemView).bindData(GalleryDateUtils.formatRelativeMonth(getItemSortTime(intValue)), this.mGroupStartLocations.get(i));
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        ((MonthHolder) baseViewHolder).mItemView.bindData(parseMonthData(i));
    }

    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new HeaderViewHolder(HeaderViewHolder.getView(viewGroup, R.layout.home_page_grid_header_item));
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("MediaMonthAdapter", "onCreateViewHolder");
        MonthView monthView = new MonthView(viewGroup.getContext());
        monthView.setSpanCount(10);
        monthView.setItemHorizontalSpace(1);
        monthView.setItemVerticalSpace(1);
        monthView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        return new MonthHolder(monthView);
    }

    public Cursor swapCursor(Cursor cursor) {
        BurstFilterCursor burstFilterCursor;
        ArrayList<String> arrayList;
        ArrayList<Integer> arrayList2;
        ArrayList<Integer> arrayList3;
        ArrayList<Integer> arrayList4 = null;
        if ((cursor == null && this.mCursor == null) || ((burstFilterCursor = this.mCursor) != null && burstFilterCursor.getWrappedCursor() == cursor)) {
            return null;
        }
        if (cursor != null) {
            arrayList3 = cursor.getExtras().getIntegerArrayList("extra_timeline_item_count_in_group");
            arrayList2 = cursor.getExtras().getIntegerArrayList("extra_timeline_group_start_pos");
            arrayList = cursor.getExtras().getStringArrayList("extra_timeline_group_start_locations");
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        BurstFilterCursor burstFilterCursor2 = this.mCursor;
        this.mCursor = cursor == null ? null : new BurstFilterCursor(cursor);
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
            BurstFilterCursor burstFilterCursor3 = this.mCursor;
            if (burstFilterCursor3 != null) {
                arrayList4 = burstFilterCursor3.getResultPos();
            }
            if (MiscUtil.isValid(arrayList4)) {
                BurstFilterCursor.wrapGroupInfos(arrayList4, cursor.getCount(), this.mGroupStartPos, this.mGroupItemCount);
            }
        }
        notifyDataSetChanged();
        return burstFilterCursor2;
    }
}
