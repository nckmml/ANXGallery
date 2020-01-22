package com.miui.gallery.model;

import android.app.Activity;
import android.database.Cursor;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.MiscUtil;

public abstract class BaseCloudDataSet extends CursorDataSet {
    protected long mAlbumId = -1;
    protected String mAlbumName;
    protected int mOpDupType;

    public BaseCloudDataSet(Cursor cursor, int i, int i2, long j, String str) {
        super(cursor, i);
        this.mOpDupType = i2;
        this.mAlbumId = j;
        this.mAlbumName = str;
    }

    public boolean addToAlbum(Activity activity, int i, boolean z, boolean z2, MediaAndAlbumOperations.OnAddAlbumListener onAddAlbumListener) {
        BaseDataItem item = getItem((BaseDataItem) null, i);
        if (item != null) {
            if (!item.isBurstItem() || item.getBurstGroup().size() <= 0) {
                MediaAndAlbumOperations.addToAlbum(activity, onAddAlbumListener, this.mOpDupType, isAlbumRemovable(), z, z2, item.isVideo(), ((CloudItem) item).getId());
            } else {
                MediaAndAlbumOperations.addToAlbum(activity, onAddAlbumListener, this.mOpDupType, isAlbumRemovable(), z, z2, item.isVideo(), MiscUtil.ListToArray(item.getBurstKeys()));
            }
        }
        return true;
    }

    public boolean addToFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        CloudItem cloudItem = (CloudItem) getItem((BaseDataItem) null, i);
        if (cloudItem != null) {
            if (!cloudItem.isBurstItem() || cloudItem.getBurstGroup() == null) {
                MediaAndAlbumOperations.addToFavoritesById(activity, cloudItem.wrapAddToFavoritesListener(onCompleteListener), cloudItem.getId());
            } else {
                MediaAndAlbumOperations.addToFavoritesById(activity, cloudItem.wrapAddToFavoritesListener(onCompleteListener), MiscUtil.ListToArray(cloudItem.getBurstKeys()));
            }
        }
        return true;
    }

    public void delete(Activity activity, int i, DeletionTask.OnDeletionCompleteListener onDeletionCompleteListener) {
        BaseDataItem item = getItem((BaseDataItem) null, i);
        if (item == null) {
            return;
        }
        if (!item.isBurstItem() || item.getBurstGroup().size() <= 0) {
            MediaAndAlbumOperations.delete(activity, "DeleteMediaDialogFragment", onDeletionCompleteListener, this.mAlbumId, this.mAlbumName, this.mOpDupType, 25, ((CloudItem) item).getId());
            return;
        }
        MediaAndAlbumOperations.delete(activity, "DeleteMediaDialogFragment", onDeletionCompleteListener, this.mAlbumId, this.mAlbumName, this.mOpDupType, 25, true, MiscUtil.ListToArray(item.getBurstKeys()));
    }

    /* access modifiers changed from: protected */
    public int doDelete(BaseDataItem baseDataItem) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean isAlbumRemovable() {
        long j = this.mAlbumId;
        return (j == 2147483647L || j == 2147483645 || j == 2147483642) ? false : true;
    }

    public boolean removeFromFavorites(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        CloudItem cloudItem = (CloudItem) getItem((BaseDataItem) null, i);
        if (cloudItem != null) {
            if (!cloudItem.isBurstItem() || cloudItem.getBurstGroup() == null) {
                MediaAndAlbumOperations.removeFromFavoritesById(activity, cloudItem.wrapRemoveFromFavoritesListener(onCompleteListener), cloudItem.getId());
            } else {
                MediaAndAlbumOperations.removeFromFavoritesById(activity, cloudItem.wrapRemoveFromFavoritesListener(onCompleteListener), MiscUtil.ListToArray(cloudItem.getBurstKeys()));
            }
        }
        return true;
    }

    public boolean removeFromSecret(Activity activity, int i, MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        BaseDataItem item = getItem((BaseDataItem) null, i);
        if (item != null) {
            if (!item.isBurstItem() || item.getBurstGroup().size() <= 0) {
                MediaAndAlbumOperations.removeFromSecretAlbum(activity, onCompleteListener, ((CloudItem) item).getId());
            } else {
                MediaAndAlbumOperations.removeFromSecretAlbum(activity, onCompleteListener, MiscUtil.ListToArray(item.getBurstKeys()));
            }
        }
        return true;
    }
}
