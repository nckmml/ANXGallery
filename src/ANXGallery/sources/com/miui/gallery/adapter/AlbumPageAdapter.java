package com.miui.gallery.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.activity.AlbumLocalPageActivity;
import com.miui.gallery.activity.facebaby.BabyAlbumDetailActivity;
import com.miui.gallery.model.Album;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.widget.recyclerview.ItemClickSupport;

public class AlbumPageAdapter extends AlbumAdapterBase implements ItemClickSupport.OnItemClickListener {
    private Activity mActivity;

    public AlbumPageAdapter(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    private boolean isSameSection(Album.AlbumType albumType, Album.AlbumType albumType2) {
        if (!(albumType == Album.AlbumType.OTHER_ALBUMS || albumType2 == Album.AlbumType.OTHER_ALBUMS)) {
            return albumType == Album.AlbumType.PINNED ? albumType2 == Album.AlbumType.PINNED : albumType == Album.AlbumType.SYSTEM ? albumType2 == Album.AlbumType.SYSTEM : (albumType2 == Album.AlbumType.SYSTEM || albumType2 == Album.AlbumType.PINNED) ? false : true;
        }
    }

    private boolean isSyncable() {
        return SyncUtil.existXiaomiAccount(this.mActivity);
    }

    public int getBottomDividerType(int i) {
        if (i == -1) {
            return 0;
        }
        if (i == getItemCount() - 1) {
            return 3;
        }
        Album item = getItem(i);
        if (item == null) {
            Log.w("AlbumPageAdapter", "current album item should not be null: %d", Integer.valueOf(i));
            return 0;
        }
        Album item2 = getItem(i + 1);
        if (item2 != null) {
            return isSameSection(item.getAlbumType(), item2.getAlbumType()) ? 2 : 1;
        }
        return 3;
    }

    /* access modifiers changed from: protected */
    public Intent newClickItemIntent(int i, long j, boolean z) {
        Intent intent;
        if (isOthersAlbum(j)) {
            return new Intent(this.mContext, AlbumLocalPageActivity.class);
        }
        if (isFaceAlbum(j)) {
            return new Intent("com.miui.gallery.action.VIEW_PEOPLE");
        }
        if (isRecentAlbum(j)) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(GalleryContract.RecentAlbum.VIEW_PAGE_URI.buildUpon().appendQueryParameter("source", "album_page").build());
            intent2.setPackage(this.mContext.getPackageName());
            return intent2;
        }
        if (!isBabyAlbum(i)) {
            intent = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
        } else {
            intent = new Intent(this.mContext, BabyAlbumDetailActivity.class);
            intent.putExtra("people_id", getBabyAlbumPeopleId(i));
            intent.putExtra("baby_info", getBabyInfo(i));
            intent.putExtra("thumbnail_info_of_baby", getThumbnailInfoOfBaby(i));
            intent.putExtra("baby_sharer_info", getBabySharerInfo(i));
            intent.putExtra("photodetail_is_photo_datetime_editable", false);
        }
        boolean isOtherShareAlbum = isOtherShareAlbum(i);
        intent.putExtra("other_share_album", isOtherShareAlbum);
        if (isOtherShareAlbum) {
            intent.putExtra("photodetail_is_photo_datetime_editable", false);
        }
        intent.putExtra("owner_share_album", isOwnerShareAlbum(i));
        intent.putExtra("is_local_album", z);
        intent.putExtra("screenshot_album", isScreenshotsAlbum(i));
        intent.putExtra("pano_album", isPanoAlbum(i));
        intent.putExtra("album_id", j);
        intent.putExtra("album_name", getAlbumName(i));
        intent.putExtra("album_server_id", getServerId(i));
        intent.putExtra("attributes", getAttributes(i));
        intent.putExtra("album_unwriteable", albumUnwriteable(i));
        intent.putExtra("album_local_path", getAlbumLocalPath(i));
        return intent;
    }

    public boolean onItemClick(RecyclerView recyclerView, View view, int i, long j, float f, float f2) {
        this.mActivity.startActivity(newClickItemIntent(i, getItemId(i), !isSyncable() || !isAutoUploadedAlbum(i)));
        return true;
    }
}
