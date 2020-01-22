package com.miui.gallery.model;

import android.text.TextUtils;
import com.miui.gallery.provider.InternalContract;

public interface AlbumConstants {
    public static final String[] PROJECTION = {"_id", "name", "cover_id", "cover_path", "cover_sha1", "cover_sync_state", "media_count", "face_people_id", "baby_info", "thumbnail_info", "serverId", "attributes", "immutable", "top_time", "sortBy", "baby_sharer_info", "local_path", InternalContract.Cloud.ALIAS_ALBUM_CLASSIFICATION + " AS " + "classification", "cover_size"};
    public static final String[] PROJECTION_NO_CLASSIFICATION = {"_id", "name", "cover_id", "cover_path", "cover_sha1", "cover_sync_state", "media_count", "face_people_id", "baby_info", "thumbnail_info", "serverId", "attributes", "immutable", "top_time", "sortBy", "baby_sharer_info", "local_path", "cover_size"};
    public static final String[] SHARED_ALBUM_PROJECTION = {"_id", "creatorId", "count", "nickname"};

    public static class ShareAlbum {
        public String mAlbumId;
        public String mCreatorId;
        public String mOwnerNickName;
        public int mUserCount;

        public String getOwnerName() {
            return TextUtils.isEmpty(this.mOwnerNickName) ? this.mCreatorId : this.mOwnerNickName;
        }
    }
}
