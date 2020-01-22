package com.miui.gallery.provider;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.miui.account.AccountHelper;
import com.miui.gallery.R;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.share.ShareAlbumCacheManager;
import com.miui.gallery.util.ResourceUtils;
import com.miui.widget.util.Pools;

public class ShareAlbumManager {
    public static long getOriginalAlbumId(long j) {
        return j - 2147383647;
    }

    public static String getShareAlbumInfoTipTextIfNeed(Album album) {
        AlbumConstants.ShareAlbum shareAlbum;
        StringBuilder acquire = Pools.getStringBuilderPool().acquire();
        try {
            if (album.isOtherShareAlbum()) {
                AlbumConstants.ShareAlbum shareAlbum2 = ShareAlbumCacheManager.getInstance().getShareAlbum(album.getAlbumId());
                if (shareAlbum2 != null && !TextUtils.isEmpty(shareAlbum2.getOwnerName())) {
                    if (album.isBabyAlbum()) {
                        acquire.append(ResourceUtils.getString(R.string.album_others_share_baby_info_format, shareAlbum2.getOwnerName()));
                    } else {
                        acquire.append(ResourceUtils.getString(R.string.album_others_share_info_format, shareAlbum2.getOwnerName()));
                    }
                }
            } else if (album.isBabyAlbum()) {
                acquire.append(ResourceUtils.getString(R.string.album_type_baby));
            } else if (album.isOwnerShareAlbum() && (shareAlbum = ShareAlbumCacheManager.getInstance().getShareAlbum(album.getAlbumId())) != null) {
                acquire.append(ResourceUtils.getQuantityString(R.plurals.album_already_share_user_count, shareAlbum.mUserCount, Integer.valueOf(shareAlbum.mUserCount)));
            }
            return acquire.toString();
        } finally {
            Pools.getStringBuilderPool().release(acquire);
        }
    }

    public static long getUniformAlbumId(long j) {
        return j + 2147383647;
    }

    public static boolean isOtherShareAlbumId(long j) {
        return j >= 2147383647 && j < 2147483637;
    }

    public static Cursor query(Context context, SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2) {
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(context);
        if (xiaomiAccount == null) {
            return null;
        }
        return sQLiteDatabase.query(String.format("(SELECT * FROM (SELECT 2147383647+_id AS _id, creatorId, albumId, serverId, nickname FROM shareAlbum LEFT JOIN (SELECT user_id AS creatorId, miliao_nick AS nickname FROM userInfo) USING (creatorId)) LEFT JOIN (SELECT COUNT(*) AS count, albumId FROM shareUser GROUP BY albumId) USING (albumId) UNION SELECT * FROM (SELECT * FROM (SELECT _id, '%s' AS creatorId, serverId AS albumId, serverId FROM cloud WHERE (serverType=0)) LEFT JOIN (SELECT user_id AS creatorId, %s AS nickname FROM userInfo) USING (creatorId)) LEFT JOIN (SELECT COUNT(*) AS count, albumId FROM cloudUser GROUP BY albumId) USING (albumId))", new Object[]{xiaomiAccount.name, DatabaseUtils.sqlEscapeString(context.getResources().getString(R.string.album_owner_share))}), strArr, str, strArr2, (String) null, (String) null, str2);
    }
}
