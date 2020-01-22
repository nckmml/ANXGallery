package com.miui.gallery.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.ui.AlbumPageListFaceItem;
import com.miui.gallery.ui.AlbumPageListItemBase;
import com.miui.gallery.ui.AlbumPageListLocalItem;
import com.miui.gallery.ui.AlbumPageListNormalItem;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.widget.DividerTypeProvider;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class AlbumAdapterBase extends BaseAlbumListAdapter<BaseViewHolder> implements DividerTypeProvider {
    private static boolean sIsGalleryCloudSyncable;
    private DisplayImageOptions.Builder mDisplayFaceOptionsBuilder;
    private DisplayImageOptions mDisplayVideoOptions;
    private DisplayImageOptions.Builder mDisplayVideoOptionsBuilder;
    private boolean mIsInPickMode;

    public static class AlbumFaceItemViewHolder extends BaseViewHolder {
        private AlbumFaceItemViewHolder(View view) {
            super(view);
        }

        public static AlbumFaceItemViewHolder newInstance(ViewGroup viewGroup) {
            return new AlbumFaceItemViewHolder(getView(viewGroup, R.layout.album_page_list_face_item_new));
        }
    }

    public static class AlbumNormalItemViewHolder extends BaseViewHolder {
        private AlbumNormalItemViewHolder(View view) {
            super(view);
        }

        public static AlbumNormalItemViewHolder newInstance(ViewGroup viewGroup) {
            return new AlbumNormalItemViewHolder(getView(viewGroup, R.layout.album_page_list_normal_item));
        }
    }

    public static class AlbumOthersItemViewHolder extends BaseViewHolder {
        private AlbumOthersItemViewHolder(View view) {
            super(view);
        }

        public static AlbumOthersItemViewHolder newInstance(ViewGroup viewGroup) {
            return new AlbumOthersItemViewHolder(getView(viewGroup, R.layout.album_page_list_local_item));
        }
    }

    public AlbumAdapterBase(Activity activity) {
        super(activity);
        sIsGalleryCloudSyncable = SyncUtil.isGalleryCloudSyncable(this.mContext);
    }

    public int getBottomDividerType(int i) {
        if (i == -1) {
            return 0;
        }
        return (i != getItemCount() + -1 && i < getItemCount() + -1) ? 2 : 3;
    }

    public DisplayImageOptions getDisplayVideoOptions(int i) {
        long fileLength = getFileLength(i);
        return fileLength > 0 ? this.mDisplayVideoOptionsBuilder.considerFileLength(true).fileLength(fileLength).build() : this.mDisplayVideoOptions;
    }

    public int getItemViewType(int i) {
        if (getItem(i).getAlbumType() == Album.AlbumType.OTHER_ALBUMS) {
            return 3;
        }
        return isFaceAlbum(getItemId(i)) ? 2 : 1;
    }

    public int getTopDividerType(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void initDisplayImageOptions() {
        super.initDisplayImageOptions();
        this.mDisplayVideoOptionsBuilder = new DisplayImageOptions.Builder().cloneFrom(this.mDefaultDisplayImageOptions).showImageOnLoading((int) R.drawable.default_video_album_cover);
        this.mDisplayVideoOptions = this.mDisplayVideoOptionsBuilder.build();
        this.mDisplayFaceOptionsBuilder = new DisplayImageOptions.Builder().cloneFrom(this.mDefaultDisplayImageOptions).showImageOnLoading(0).usingRegionDecoderFace(true).cacheInMemory(true);
    }

    public boolean isAutoUploadedAlbum(int i) {
        return sIsGalleryCloudSyncable && super.isAutoUploadedAlbum(i);
    }

    public boolean isAutoUploadedAlbum(long j, String str, long j2) {
        return sIsGalleryCloudSyncable && super.isAutoUploadedAlbum(j, str, j2);
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder != null) {
            Album item = getItem(i);
            int itemViewType = baseViewHolder.getItemViewType();
            boolean z = false;
            if (itemViewType == 1) {
                AlbumPageListNormalItem albumPageListNormalItem = (AlbumPageListNormalItem) baseViewHolder.itemView;
                albumPageListNormalItem.adjustAlbumCoverContainerPadding(i == 0 || getBottomDividerType(i + -1) == 1, i == getItemCount() - 1 || getBottomDividerType(i) == 1);
                albumPageListNormalItem.bindCommonInfo(item.getLocalizedAlbumName(), item.getCount(), item.isPlaceholder());
                albumPageListNormalItem.bindImage(getLocalPath(i), getDownloadUri(i), isVideoAlbum(item) ? getDisplayVideoOptions(i) : getDisplayImageOptions(i));
                if (isOtherShareAlbum(item.getAlbumId())) {
                    AlbumConstants.ShareAlbum shareAlbumInfo = getShareAlbumInfo(i);
                    if (shareAlbumInfo == null || TextUtils.isEmpty(shareAlbumInfo.getOwnerName())) {
                        albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.SHARE, (String) null);
                    } else if (isBabyAlbum(item)) {
                        albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.SHARE, this.mContext.getString(R.string.album_others_share_baby_info_format, new Object[]{shareAlbumInfo.getOwnerName()}));
                    } else {
                        albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.SHARE, this.mContext.getString(R.string.album_others_share_info_format, new Object[]{shareAlbumInfo.getOwnerName()}));
                    }
                } else if (isBabyAlbum(i)) {
                    albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.BABY, this.mContext.getString(R.string.album_type_baby));
                } else if (isOwnerShareAlbum(item.getAlbumId())) {
                    AlbumConstants.ShareAlbum shareAlbumInfo2 = getShareAlbumInfo(i);
                    if (shareAlbumInfo2 != null) {
                        albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.OWNER, this.mContext.getResources().getQuantityString(R.plurals.album_already_share_user_count, shareAlbumInfo2.mUserCount, new Object[]{Integer.valueOf(shareAlbumInfo2.mUserCount)}));
                    }
                } else if (isSystemAlbum(i)) {
                    albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.SYSTEM, (String) null);
                } else {
                    albumPageListNormalItem.bindType(AlbumPageListItemBase.AlbumType.NORMAL, (String) null);
                }
                albumPageListNormalItem.bindIndicator(isRecentAlbum(item), isVideoAlbum(item), isFavoritesAlbum(item), isAutoUploadedAlbum(item), this.mIsInPickMode);
                if (shouldShowForceTop(item) && isForceTypeTime(item)) {
                    z = true;
                }
                albumPageListNormalItem.bindForceTopIcon(z);
                albumPageListNormalItem.bindHiddenMask(isHiddenAlbum(i));
            } else if (itemViewType == 2) {
                AlbumPageListFaceItem albumPageListFaceItem = (AlbumPageListFaceItem) baseViewHolder.itemView;
                albumPageListFaceItem.bindCommonInfo(item.getLocalizedAlbumName(), item.getCount(), item.isPlaceholder());
                albumPageListFaceItem.bindImageAndAlbumCount(getPeopleFaceCover(), this.mDisplayFaceOptionsBuilder);
                albumPageListFaceItem.bindType(AlbumPageListItemBase.AlbumType.SYSTEM, this.mContext.getString(R.string.album_type_system));
                if (shouldShowForceTop(item) && isForceTypeTime(item)) {
                    z = true;
                }
                albumPageListFaceItem.bindForceTopIcon(z);
            } else if (itemViewType == 3) {
                ((AlbumPageListLocalItem) baseViewHolder.itemView).bindAlbumNameDesc(this.mContext, item);
            }
        }
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return AlbumNormalItemViewHolder.newInstance(viewGroup);
        }
        if (i == 2) {
            return AlbumFaceItemViewHolder.newInstance(viewGroup);
        }
        if (i != 3) {
            return null;
        }
        return AlbumOthersItemViewHolder.newInstance(viewGroup);
    }

    public void setInPickMode(boolean z) {
        this.mIsInPickMode = z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowForceTop(Album album) {
        return !this.mIsInPickMode || !isOtherAlbum(album);
    }

    public void updateGalleryCloudSyncableState() {
        boolean isGalleryCloudSyncable = SyncUtil.isGalleryCloudSyncable(this.mContext);
        if (sIsGalleryCloudSyncable != isGalleryCloudSyncable) {
            sIsGalleryCloudSyncable = isGalleryCloudSyncable;
            notifyDataSetChanged();
        }
    }
}
