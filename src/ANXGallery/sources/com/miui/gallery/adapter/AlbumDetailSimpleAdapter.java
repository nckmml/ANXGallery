package com.miui.gallery.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.ui.AlbumDetailGridItem;
import com.miui.gallery.util.BurstFilterCursor;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.TalkBackUtil;
import com.miui.gallery.widget.SortByHeader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;

public class AlbumDetailSimpleAdapter extends PreloadMediaAdapterDeprecated implements CheckableAdapter {
    public static final String[] PROJECTION = MiscUtil.copyStringArray(PROJECTION_INTERNAL);
    protected static final String[] PROJECTION_INTERNAL = {"_id", "alias_micro_thumbnail", "localFile", "title", "alias_create_date", "alias_create_time", "location", "sha1", "serverType", "duration", "mimeType", "size", "alias_sync_state", "secretKey", "thumbnailFile", "localFile", "creatorId", "alias_sort_time", "alias_clear_thumbnail", "alias_is_favorite", "specialTypeFlags", "burst_group_id"};
    private AlbumType mAlbumType = AlbumType.NORMAL;
    protected ArrayList<Integer> mGroupItemCount;
    protected ArrayList<String> mGroupStartLocations;
    protected ArrayList<Integer> mGroupStartPos;
    private SortByHeader.SortBy mSortBy;

    public enum AlbumType {
        NORMAL,
        SECRET,
        BABY,
        SCREENSHOT,
        OTHER_SHARE,
        PANO,
        RECENT,
        FAVORITES,
        OTHER_SHARE_BABY
    }

    public AlbumDetailSimpleAdapter(Context context) {
        super(context, SyncStateDisplay.DisplayScene.SCENE_IN_CHECK_MODE);
    }

    public AlbumDetailSimpleAdapter(Context context, SyncStateDisplay.DisplayScene displayScene) {
        super(context, displayScene);
    }

    public AlbumDetailSimpleAdapter(Context context, SyncStateDisplay.DisplayScene displayScene, int i) {
        super(context, displayScene, i);
    }

    private DisplayImageOptions buildNoDiskCacheDisplayOptions() {
        this.mDisplayImageOptionBuilder.cacheThumbnail(false);
        this.mDisplayImageOptionBuilder.loadFromMicroCache(false);
        this.mDisplayImageOptionBuilder.cacheInMemory(true);
        return this.mDisplayImageOptionBuilder.build();
    }

    private DisplayImageOptions buildSecretPhotoDisplayOptions(byte[] bArr) {
        if (bArr == null) {
            return this.mDefaultDisplayImageOptions;
        }
        this.mDisplayImageOptionBuilder.secretKey(bArr);
        return this.mDisplayImageOptionBuilder.build();
    }

    private ImageSize getDisplayImageSize() {
        return isScreenshotAlbum() ? Config.ThumbConfig.get().sMicroScreenshotTargetSize : isPanoAlbum() ? Config.ThumbConfig.get().sMicroPanoTargetSize : isRecentAlbum() ? Config.ThumbConfig.get().sMicroRecentTargetSize : Config.ThumbConfig.get().sMicroTargetSize;
    }

    private boolean isNoCacheDisplayAlbum() {
        return isScreenshotAlbum() || isPanoAlbum();
    }

    /* access modifiers changed from: protected */
    public void bindContentDescription(View view, int i) {
        Cursor cursorByPosition = getCursorByPosition(i);
        view.setContentDescription(TalkBackUtil.getContentDescriptionForImage(view.getContext(), cursorByPosition.getLong(5), cursorByPosition.getString(6), cursorByPosition.getString(10)));
    }

    public void doBindData(View view, Context context, Cursor cursor) {
        Cursor cursor2 = cursor;
        AlbumDetailGridItem albumDetailGridItem = (AlbumDetailGridItem) view;
        int position = cursor.getPosition();
        albumDetailGridItem.bindImage(getClearThumbFilePath(position), getDownloadUri(position), getDisplayImageOptions(position));
        String string = cursor2.getString(10);
        long j = cursor2.getLong(9);
        boolean z = true;
        boolean z2 = (cursor2 instanceof BurstFilterCursor) && ((BurstFilterCursor) cursor2).isBurstPosition(position);
        if (this.mSortBy == SortByHeader.SortBy.SIZE) {
            albumDetailGridItem.bindFileSize(cursor2.getLong(11));
        } else {
            long j2 = cursor2.getLong(20);
            if (isSecretAlbum()) {
                j2 = Config.SecretAlbumConfig.getSupportedSpecialTypeFlags(j2);
            } else if (isOtherShareAlbum()) {
                j2 = Config.ShareAlbumConfig.getSupportedSpecialTypeFlags(j2);
            }
            if (z2) {
                j2 |= 64;
            }
            albumDetailGridItem.bindFileSize(0);
            albumDetailGridItem.bindIndicator(string, j, j2);
        }
        if (isSecretAlbum() || !isFavorite(cursor.getPosition())) {
            z = false;
        }
        albumDetailGridItem.bindFavoriteIndicator(z);
        int syncState = getSyncState(cursor2);
        long j3 = cursor2.getLong(0);
        albumDetailGridItem.bindSyncIndicator(j3, syncState, this.mShowScene, this.mSyncStateDisplayOptions);
        if (this.mViewScrollState == 0) {
            bindContentDescription(albumDetailGridItem, position);
        }
        if (z2) {
            albumDetailGridItem.setIsSimilarBestImage(ImageFeatureCacheManager.getInstance().shouldShowSelectionStar(j3, false, false, getBurstItemKeys(position)));
        } else {
            albumDetailGridItem.setIsSimilarBestImage(ImageFeatureCacheManager.getInstance().shouldShowSelectionStar(j3, false, false));
        }
    }

    public ArrayList<Long> getBurstItemKeys(int i) {
        if (supportFoldBurstItems() && getCursor() != null) {
            return ((BurstFilterCursor) getCursor()).getBurstIdsInGroup(i, 0);
        }
        ArrayList<Long> arrayList = new ArrayList<>(1);
        arrayList.add(Long.valueOf(getItemKey(i)));
        return arrayList;
    }

    public View getCheckableView(View view) {
        return ((AlbumDetailGridItem) view).getCheckBox();
    }

    public String getClearThumbFilePath(int i) {
        return getMicroPath(getCursorByPosition(i), 18, 7);
    }

    public String getCreatorId(int i) {
        return getCursorByPosition(i).getString(16);
    }

    public SortByHeader.SortBy getCurrentSortBy() {
        return this.mSortBy;
    }

    /* access modifiers changed from: protected */
    public DisplayImageOptions getDisplayImageOptions(int i) {
        DisplayImageOptions displayImageOptions = super.getDisplayImageOptions(i);
        if (!isSecretAlbum()) {
            return isNoCacheDisplayAlbum() ? buildNoDiskCacheDisplayOptions() : displayImageOptions;
        }
        Cursor cursorByPosition = getCursorByPosition(i);
        return cursorByPosition != null ? buildSecretPhotoDisplayOptions(cursorByPosition.getBlob(13)) : displayImageOptions;
    }

    /* access modifiers changed from: protected */
    public ImageSize getDisplayImageSize(int i) {
        return getDisplayImageSize();
    }

    public Uri getDownloadUri(int i) {
        return getDownloadUri(getCursorByPosition(i), 12, 0);
    }

    public long getFileLength(int i) {
        return getCursorByPosition(i).getLong(11);
    }

    public long getItemKey(int i) {
        return getCursorByPosition(i).getLong(0);
    }

    public byte[] getItemSecretKey(int i) {
        return isSecretAlbum() ? getCursorByPosition(i).getBlob(13) : super.getItemSecretKey(i);
    }

    public String getLocalPath(int i) {
        return getMicroPath(getCursorByPosition(i), 1, 7);
    }

    public String getMicroThumbFilePath(int i) {
        return getCursorByPosition(i).getString(1);
    }

    public String getMimeType(int i) {
        return getCursorByPosition(i).getString(10);
    }

    public String getOriginFilePath(int i) {
        return getCursorByPosition(i).getString(15);
    }

    public String getSha1(int i) {
        return getCursorByPosition(i).getString(7);
    }

    /* access modifiers changed from: protected */
    public int getSyncState(Cursor cursor) {
        return getSyncStateInternal(cursor.getInt(12));
    }

    public String getThumbFilePath(int i) {
        return getCursorByPosition(i).getString(14);
    }

    public boolean isBabyAlbum() {
        return this.mAlbumType == AlbumType.BABY || this.mAlbumType == AlbumType.OTHER_SHARE_BABY;
    }

    public boolean isFavorite(int i) {
        return getCursorByPosition(i).getInt(19) > 0;
    }

    public boolean isOtherShareAlbum() {
        return this.mAlbumType == AlbumType.OTHER_SHARE || this.mAlbumType == AlbumType.OTHER_SHARE_BABY;
    }

    public boolean isPanoAlbum() {
        return this.mAlbumType == AlbumType.PANO;
    }

    public boolean isRecentAlbum() {
        return this.mAlbumType == AlbumType.RECENT;
    }

    public boolean isScreenshotAlbum() {
        return this.mAlbumType == AlbumType.SCREENSHOT;
    }

    public boolean isSecretAlbum() {
        return this.mAlbumType == AlbumType.SECRET;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        if (isScreenshotAlbum()) {
            AlbumDetailGridItem albumDetailGridItem = (AlbumDetailGridItem) LayoutInflater.from(context).inflate(R.layout.album_detail_screenshot_grid_item, viewGroup, false);
            albumDetailGridItem.setImageSize(getDisplayImageSize());
            albumDetailGridItem.setImageForeground(R.drawable.rect_item_fg_with_stroke);
            return albumDetailGridItem;
        } else if (isPanoAlbum()) {
            AlbumDetailGridItem albumDetailGridItem2 = (AlbumDetailGridItem) LayoutInflater.from(context).inflate(R.layout.album_detail_pano_grid_item, viewGroup, false);
            albumDetailGridItem2.setImageSize(getDisplayImageSize());
            return albumDetailGridItem2;
        } else if (!isRecentAlbum()) {
            return LayoutInflater.from(context).inflate(R.layout.album_detail_grid_item, viewGroup, false);
        } else {
            AlbumDetailGridItem albumDetailGridItem3 = (AlbumDetailGridItem) LayoutInflater.from(context).inflate(R.layout.album_detail_grid_item, viewGroup, false);
            albumDetailGridItem3.setImageSize(getDisplayImageSize());
            albumDetailGridItem3.setImageForeground(R.drawable.rect_item_fg_with_stroke);
            return albumDetailGridItem3;
        }
    }

    public void onViewScrollStateChanged(AbsListView absListView, int i) {
        super.onViewScrollStateChanged(absListView, i);
        if (i == 0) {
            int childCount = absListView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = absListView.getChildAt(i2);
                Object tag = childAt.getTag(R.id.tag_item_position);
                if (tag != null) {
                    bindContentDescription(childAt, ((Integer) tag).intValue());
                }
            }
        }
    }

    public void setAlbumType(AlbumType albumType) {
        this.mAlbumType = albumType;
        if (isNoCacheDisplayAlbum()) {
            setPreloadNum(0);
        }
    }

    public void setCurrentSortBy(SortByHeader.SortBy sortBy) {
        this.mSortBy = sortBy;
    }

    public boolean supportFoldBurstItems() {
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
            arrayList2 = extras.getStringArrayList("extra_timeline_group_start_locations");
            arrayList3 = arrayList4;
        } else {
            arrayList2 = null;
            arrayList = null;
        }
        Cursor burstFilterCursor = (!supportFoldBurstItems() || cursor == null) ? cursor : new BurstFilterCursor(cursor);
        if (arrayList3 == null || arrayList == null || arrayList2 == null) {
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
            this.mGroupStartPos = new ArrayList<>(arrayList);
            this.mGroupStartLocations = new ArrayList<>(arrayList2);
            if (supportFoldBurstItems()) {
                BurstFilterCursor.wrapGroupInfos(((BurstFilterCursor) burstFilterCursor).getResultPos(), cursor.getCount(), this.mGroupStartPos, this.mGroupItemCount);
            }
        }
        return super.swapCursor(burstFilterCursor);
    }
}
