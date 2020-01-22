package com.miui.gallery.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.activity.facebaby.BabyAlbumDetailActivity;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.ui.RecentTimelineGridHeaderItem;
import com.miui.gallery.util.AlbumsCursorHelper;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.ShareAlbumsCursorHelper;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class RecentDiscoveryAdapter extends AlbumDetailSimpleAdapter implements StickyGridHeadersBaseAdapter {
    private static final int COLUMN_INDEX_ALBUM_ID = PROJECTION_INTERNAL.length;
    private static final int COLUMN_INDEX_DATE_MODIFIED = (COLUMN_INDEX_ALBUM_ID + 1);
    public static String[] PROJECTION = new String[(PROJECTION_INTERNAL.length + 2)];
    /* access modifiers changed from: private */
    public AlbumsCursorHelper mAlbumsCursorHelper;
    private ArrayList<Integer> mGroupItemCount;
    private ArrayList<Integer> mGroupStartPos;
    private ArrayList<String> mGroupStartTimeLabels;
    private boolean mIsInPickMode;
    /* access modifiers changed from: private */
    public ShareAlbumsCursorHelper mShareAlbumsCursorHelper;

    static {
        System.arraycopy(PROJECTION_INTERNAL, 0, PROJECTION, 0, PROJECTION_INTERNAL.length);
        String[] strArr = PROJECTION;
        strArr[COLUMN_INDEX_ALBUM_ID] = "localGroupId";
        strArr[COLUMN_INDEX_DATE_MODIFIED] = "dateModified";
    }

    public RecentDiscoveryAdapter(Context context) {
        this(context, SyncStateDisplay.DisplayScene.SCENE_IN_CHECK_MODE, false);
    }

    public RecentDiscoveryAdapter(Context context, SyncStateDisplay.DisplayScene displayScene, boolean z) {
        super(context, displayScene);
        setAlbumType(AlbumDetailSimpleAdapter.AlbumType.RECENT);
        this.mShareAlbumsCursorHelper = new ShareAlbumsCursorHelper();
        this.mAlbumsCursorHelper = new AlbumsCursorHelper(this.mContext);
        this.mIsInPickMode = z;
    }

    /* access modifiers changed from: private */
    public Intent newAlbumFromClickedIntent(long j) {
        Intent intent;
        long attributes = this.mAlbumsCursorHelper.getAttributes(j);
        String serverId = this.mAlbumsCursorHelper.getServerId(j);
        String albumName = this.mAlbumsCursorHelper.getAlbumName(j);
        String albumLocalPath = this.mAlbumsCursorHelper.getAlbumLocalPath(Long.valueOf(j));
        boolean startsWith = albumLocalPath.startsWith("/");
        boolean isBabyAlbum = this.mAlbumsCursorHelper.isBabyAlbum(j);
        if (AlbumsCursorHelper.isFaceAlbum(j)) {
            return new Intent("com.miui.gallery.action.VIEW_PEOPLE");
        }
        if (isBabyAlbum) {
            intent = new Intent(this.mContext, BabyAlbumDetailActivity.class);
            intent.putExtra("people_id", this.mAlbumsCursorHelper.getBabyAlbumPeopleId(j));
            intent.putExtra("baby_info", this.mAlbumsCursorHelper.getBabyInfo(j));
            intent.putExtra("thumbnail_info_of_baby", this.mAlbumsCursorHelper.getThumbnailInfoOfBaby(j));
            intent.putExtra("baby_sharer_info", this.mAlbumsCursorHelper.getBabySharerInfo(j));
        } else {
            intent = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
        }
        boolean equals = String.valueOf(2).equals(serverId);
        boolean isOtherShareAlbum = this.mAlbumsCursorHelper.isOtherShareAlbum(j);
        boolean isOwnerShareAlbum = this.mShareAlbumsCursorHelper.isOwnerShareAlbum(j);
        boolean isLocalAlbum = this.mAlbumsCursorHelper.isLocalAlbum(j);
        intent.putExtra("other_share_album", isOtherShareAlbum);
        intent.putExtra("owner_share_album", isOwnerShareAlbum);
        intent.putExtra("is_local_album", isLocalAlbum);
        intent.putExtra("screenshot_album", equals);
        intent.putExtra("pano_album", false);
        intent.putExtra("album_id", j);
        intent.putExtra("album_name", albumName);
        intent.putExtra("album_server_id", serverId);
        intent.putExtra("attributes", attributes);
        intent.putExtra("album_unwriteable", startsWith);
        intent.putExtra("album_local_path", albumLocalPath);
        return intent;
    }

    /* access modifiers changed from: private */
    public void recordAlbumClick(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("album_name", str);
        GallerySamplingStatHelper.recordCountEvent("recent_album", "recent_album_click_album_name", hashMap);
    }

    public int getCountForHeader(int i) {
        ArrayList<Integer> arrayList = this.mGroupItemCount;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.get(i).intValue();
    }

    public long getDateModified(int i) {
        return getCursorByPosition(i).getLong(COLUMN_INDEX_DATE_MODIFIED);
    }

    public View getDividerView(int i, View view, ViewGroup viewGroup) {
        String str = view != null ? (String) view.getTag() : null;
        if (i == this.mGroupStartPos.size() - 1) {
            if (TextUtils.equals(str, "last_divider")) {
                return view;
            }
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.recent_album_last_section_divider, viewGroup, false);
            inflate.setTag("last_divider");
            return inflate;
        } else if (TextUtils.equals(str, "normal_divider")) {
            return view;
        } else {
            View inflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.recent_album_section_divider, viewGroup, false);
            inflate2.setTag("normal_divider");
            return inflate2;
        }
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.recent_timeline_grid_header_item, viewGroup, false);
        }
        ArrayList<Integer> arrayList = this.mGroupItemCount;
        if (arrayList != null) {
            i2 = arrayList.get(i).intValue();
        }
        ArrayList<String> arrayList2 = this.mGroupStartTimeLabels;
        final String str = null;
        String str2 = arrayList2 != null ? arrayList2.get(i) : null;
        ArrayList<Integer> arrayList3 = this.mGroupStartPos;
        if (arrayList3 != null) {
            i = arrayList3.get(i).intValue();
        }
        final long j = ((Cursor) getItem(i)).getLong(COLUMN_INDEX_ALBUM_ID);
        if (this.mAlbumsCursorHelper.isAlbumDataValid(j)) {
            str = this.mAlbumsCursorHelper.getAlbumName(j);
        }
        RecentTimelineGridHeaderItem recentTimelineGridHeaderItem = (RecentTimelineGridHeaderItem) view;
        recentTimelineGridHeaderItem.bindData(str2, i2, str, this.mIsInPickMode);
        if (!this.mIsInPickMode) {
            recentTimelineGridHeaderItem.setAlbumFromClickedListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (RecentDiscoveryAdapter.this.mAlbumsCursorHelper.isAlbumDataValid(j) && RecentDiscoveryAdapter.this.mShareAlbumsCursorHelper.isDataValid()) {
                        RecentDiscoveryAdapter.this.mContext.startActivity(RecentDiscoveryAdapter.this.newAlbumFromClickedIntent(j));
                        if (!TextUtils.isEmpty(str)) {
                            RecentDiscoveryAdapter.this.recordAlbumClick(str);
                        }
                    }
                }
            });
        }
        return view;
    }

    public int getNumHeaders() {
        ArrayList<Integer> arrayList = this.mGroupItemCount;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void resetShareAlbums() {
        this.mShareAlbumsCursorHelper.reset();
    }

    public void setAllAlbums(Cursor cursor) {
        this.mAlbumsCursorHelper.setAlbumsData(cursor);
        if (getCount() > 0 && cursor != null && cursor.getCount() > 0) {
            notifyDataSetChanged();
        }
    }

    public void setShareAlbums(Cursor cursor) {
        this.mShareAlbumsCursorHelper.setSharedAlbums(cursor);
    }

    public boolean shouldDrawDivider() {
        return true;
    }

    public Cursor swapCursor(Cursor cursor) {
        ArrayList<Integer> arrayList;
        ArrayList<String> arrayList2;
        ArrayList<Integer> arrayList3 = null;
        if (cursor != null) {
            Bundle extras = cursor.getExtras();
            ArrayList<Integer> integerArrayList = extras.getIntegerArrayList("extra_timeline_item_count_in_group");
            arrayList = extras.getIntegerArrayList("extra_timeline_group_start_pos");
            ArrayList<Integer> arrayList4 = integerArrayList;
            arrayList2 = extras.getStringArrayList("extra_timeline_group_time_labels");
            arrayList3 = arrayList4;
        } else {
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null || arrayList == null || arrayList2 == null) {
            ArrayList<Integer> arrayList5 = this.mGroupItemCount;
            if (arrayList5 != null) {
                arrayList5.clear();
            }
            ArrayList<Integer> arrayList6 = this.mGroupStartPos;
            if (arrayList6 != null) {
                arrayList6.clear();
            }
            ArrayList<String> arrayList7 = this.mGroupStartTimeLabels;
            if (arrayList7 != null) {
                arrayList7.clear();
            }
        } else {
            this.mGroupItemCount = new ArrayList<>(arrayList3);
            this.mGroupStartPos = new ArrayList<>(arrayList);
            this.mGroupStartTimeLabels = new ArrayList<>(arrayList2);
        }
        return super.swapCursor(cursor);
    }
}
