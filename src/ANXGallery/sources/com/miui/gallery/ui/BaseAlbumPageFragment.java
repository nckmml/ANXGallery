package com.miui.gallery.ui;

import android.accounts.Account;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import com.miui.account.AccountHelper;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumPageAdapter;
import com.miui.gallery.cloudcontrol.strategies.AlbumsStrategy;
import com.miui.gallery.loader.AlbumConvertCallback;
import com.miui.gallery.loader.CursorConvertCallback;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumList;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.share.Path;
import com.miui.gallery.share.UIHelper;
import com.miui.gallery.ui.BaseAlbumOperationDialogFragment;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SoundUtils;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.util.HashMap;
import miui.app.AlertDialog;

public abstract class BaseAlbumPageFragment extends BaseFragment {
    protected CursorConvertCallback<AlbumList> mAlbumConvertCallback = new AlbumConvertCallback();
    protected AlbumPageAdapter mAlbumPageAdapter;
    /* access modifiers changed from: private */
    public DeletionTask.OnDeletionCompleteListener mCompleteListener = new DeletionTask.OnDeletionCompleteListener() {
        public void onDeleted(int i, long[] jArr) {
            if (BaseAlbumPageFragment.this.getActivity() != null) {
                if (i >= 0) {
                    ToastUtils.makeText((Context) BaseAlbumPageFragment.this.getActivity(), (CharSequence) BaseAlbumPageFragment.this.getActivity().getString(R.string.delete_album_success));
                    SoundUtils.playSoundForOperation(BaseAlbumPageFragment.this.getActivity(), 0);
                    return;
                }
                ToastUtils.makeText((Context) BaseAlbumPageFragment.this.getActivity(), (CharSequence) BaseAlbumPageFragment.this.getActivity().getString(R.string.delete_album_failed));
            }
        }
    };
    protected GalleryRecyclerView mRecyclerView;
    protected Album mSelectedAlbum;

    /* access modifiers changed from: private */
    public void doChangeShowInOtherAlbums(boolean z) {
        MediaAndAlbumOperations.doChangeShowInOtherAlbums(getActivity(), this.mSelectedAlbum.getAlbumId(), z);
        ToastUtils.makeTextLong((Context) getActivity(), z ? R.string.show_in_other_albums_enable_toast_long_press_menu : R.string.show_in_other_albums_disable_toast_long_press_menu);
    }

    private void doChangeShowInPhotosTab(boolean z) {
        MediaAndAlbumOperations.doChangeShowInPhotosTab(getActivity(), this.mSelectedAlbum.getAlbumId(), z);
    }

    private void doDelete(String str) {
        int count = this.mSelectedAlbum.getCount();
        boolean existXiaomiAccount = SyncUtil.existXiaomiAccount(getActivity());
        final boolean z = existXiaomiAccount && this.mAlbumPageAdapter.isAutoUploadedAlbum(this.mSelectedAlbum.getAttributes(), this.mSelectedAlbum.getServerId(), this.mSelectedAlbum.getAlbumId()) && GalleryPreferences.LocalMode.isOnlyShowLocalPhoto();
        Resources resources = getActivity().getResources();
        Object[] objArr = new Object[3];
        String str2 = "";
        objArr[0] = (!existXiaomiAccount || z) ? str2 : getString(R.string.delete_from_all_devices_and_cloud);
        objArr[1] = str;
        if (count > 0) {
            str2 = getResources().getQuantityString(R.plurals.album_item_msg_format, count, new Object[]{Integer.valueOf(count)});
        }
        objArr[2] = str2;
        String string = resources.getString(R.string.delete_album_msg_format, objArr);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (z) {
            builder.setCheckBox(false, getString(R.string.delete_from_cloud));
        }
        builder.setTitle(R.string.delete).setMessage(string).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeletionTask.Param param = new DeletionTask.Param(new long[]{BaseAlbumPageFragment.this.mSelectedAlbum.getAlbumId()}, (!z || ((AlertDialog) dialogInterface).isChecked()) ? 0 : 1, 22);
                DeletionTask deletionTask = new DeletionTask();
                deletionTask.setOnDeletionCompleteListener(BaseAlbumPageFragment.this.mCompleteListener);
                deletionTask.showProgress(BaseAlbumPageFragment.this.getActivity());
                deletionTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new DeletionTask.Param[]{param});
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
    }

    private void doForceTop(boolean z) {
        MediaAndAlbumOperations.doForceTop(getActivity(), this.mSelectedAlbum.getAlbumId(), z);
    }

    private void doHideOrUnhide(boolean z) {
        MediaAndAlbumOperations.doChangeHiddenStatus(getActivity(), this.mSelectedAlbum.getAlbumId(), z);
    }

    private void doRename() {
        AlbumRenameDialogFragment.newInstance(this.mSelectedAlbum.getAlbumId(), this.mSelectedAlbum.getAlbumName(), (BaseAlbumOperationDialogFragment.OnAlbumOperationDoneListener) null).showAllowingStateLoss(getFragmentManager(), "AlbumRenameDialogFragment");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r3 = com.miui.gallery.cloudcontrol.CloudControlStrategyHelper.getAlbumByPath(com.miui.gallery.util.StorageUtils.ensureCommonRelativePath(r3));
     */
    private boolean isManualRenameRestricted(String str) {
        AlbumsStrategy.Album albumByPath;
        return !TextUtils.isEmpty(str) && albumByPath != null && albumByPath.getAttributes() != null && albumByPath.getAttributes().isManualRenameRestricted();
    }

    private void moveToOtherAlbums() {
        DialogUtil.showInfoDialog((Context) getActivity(), (int) R.string.move_to_other_albums_tip, (int) R.string.operation_move_to_other_albums, 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseAlbumPageFragment.this.doChangeShowInOtherAlbums(true);
            }
        }, (DialogInterface.OnClickListener) null);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.app.Fragment, com.miui.gallery.ui.BaseAlbumPageFragment] */
    /* access modifiers changed from: private */
    public void onEnableAutoUpload(boolean z) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt("check_login_and_sync", 2);
        } else {
            bundle.putInt("check_login_and_sync", 1);
        }
        bundle.putSerializable("cloud_guide_source", GalleryIntent.CloudGuideSource.AUTOBACKUP);
        bundle.putLong("autobackup_album_id", this.mSelectedAlbum.getAlbumId());
        LoginAndSyncCheckFragment.checkLoginAndSyncState(this, bundle);
    }

    private void removeFromOtherAlbums() {
        DialogUtil.showInfoDialog((Context) getActivity(), (int) R.string.remove_from_other_albums_tip, (int) R.string.operation_remove_from_other_albums, 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseAlbumPageFragment.this.doChangeShowInOtherAlbums(false);
            }
        }, (DialogInterface.OnClickListener) null);
    }

    private void showAlbumShareInfo() {
        if (!this.mAlbumPageAdapter.isAutoUploadedAlbum(this.mSelectedAlbum.getAttributes(), this.mSelectedAlbum.getServerId(), this.mSelectedAlbum.getAlbumId())) {
            ConfirmDialog.showConfirmDialog(getFragmentManager(), getResources().getString(R.string.auto_upload_before_share_title), getResources().getString(R.string.auto_upload_before_share_message), getResources().getString(17039360), getResources().getString(17039370), new ConfirmDialog.ConfirmDialogInterface() {
                public void onCancel(DialogFragment dialogFragment) {
                }

                public void onConfirm(DialogFragment dialogFragment) {
                    BaseAlbumPageFragment.this.onEnableAutoUpload(true);
                }
            });
        } else {
            share();
        }
    }

    private void statAlbumOperation(Album album, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(nexExportFormat.TAG_FORMAT_PATH, album.getLocalPath());
        GallerySamplingStatHelper.recordCountEvent("album", str, hashMap);
    }

    /* access modifiers changed from: protected */
    public void disableAutoUpload() {
        DialogUtil.showInfoDialog((Context) getActivity(), (int) R.string.disable_auto_upload_tip, (int) R.string.disable_auto_upload_title, 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseAlbumPageFragment.this.doChangeAutoUpload(false);
            }
        }, (DialogInterface.OnClickListener) null);
    }

    /* access modifiers changed from: protected */
    public boolean doChangeAutoUpload(boolean z) {
        if (z || !this.mAlbumPageAdapter.isShareAlbum(this.mSelectedAlbum.getAlbumId())) {
            Account xiaomiAccount = AccountHelper.getXiaomiAccount(getActivity());
            if (xiaomiAccount == null) {
                return false;
            }
            if (z && !ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider")) {
                if (!SyncUtil.setSyncAutomatically(getActivity(), true)) {
                    return false;
                }
                this.mAlbumPageAdapter.updateGalleryCloudSyncableState();
            }
            MediaAndAlbumOperations.doChangeAutoUpload(getActivity(), this.mSelectedAlbum.getAlbumId(), z);
            ToastUtils.makeTextLong((Context) getActivity(), z ? R.string.auto_upload_enable_toast_long_press_menu : R.string.auto_upload_disable_toast_long_press_menu);
            return true;
        }
        ToastUtils.makeText((Context) getActivity(), (int) R.string.share_album_needs_auto_upload_tip);
        return false;
    }

    /* access modifiers changed from: protected */
    public void enableAutoUpload() {
        DialogUtil.showInfoDialog((Context) getActivity(), (int) R.string.enable_auto_upload_tip, (int) R.string.enable_auto_upload_title, 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseAlbumPageFragment.this.onEnableAutoUpload(false);
            }
        }, (DialogInterface.OnClickListener) null);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1 || i != 29) {
            return;
        }
        if (this.mSelectedAlbum == null) {
            Log.w("BaseAlbumPageFragment", "The selected album entity is null!");
            return;
        }
        int intExtra = intent.getIntExtra("check_login_and_sync", -1);
        if (intExtra == 1) {
            doChangeAutoUpload(true);
        } else if (intExtra == 2 && doChangeAutoUpload(true)) {
            share();
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        if (menuItem == null) {
            return false;
        }
        switch (menuItem.getItemId()) {
            case R.id.delete:
                doDelete(this.mSelectedAlbum.getLocalizedAlbumName());
                statAlbumOperation(this.mSelectedAlbum, "delete_album");
                break;
            case R.id.disable_auto_upload:
                disableAutoUpload();
                statAlbumOperation(this.mSelectedAlbum, "auto_upload_disable");
                break;
            case R.id.disable_show_in_photos_tab:
                doChangeShowInPhotosTab(false);
                statAlbumOperation(this.mSelectedAlbum, "show_in_home_disable");
                break;
            case R.id.enable_auto_upload:
                enableAutoUpload();
                statAlbumOperation(this.mSelectedAlbum, "auto_upload_enable");
                break;
            case R.id.enable_show_in_photos_tab:
                doChangeShowInPhotosTab(true);
                statAlbumOperation(this.mSelectedAlbum, "show_in_home_enable");
                break;
            case R.id.force_top:
                doForceTop(true);
                statAlbumOperation(this.mSelectedAlbum, "force_top");
                break;
            case R.id.hide:
                doHideOrUnhide(true);
                statAlbumOperation(this.mSelectedAlbum, "hide_album");
                break;
            case R.id.move_to_other_albums:
                moveToOtherAlbums();
                statAlbumOperation(this.mSelectedAlbum, "show_in_others_enable");
                break;
            case R.id.remove_from_other_albums:
                removeFromOtherAlbums();
                statAlbumOperation(this.mSelectedAlbum, "show_in_others_disable");
                break;
            case R.id.rename:
                doRename();
                statAlbumOperation(this.mSelectedAlbum, "rename_album");
                break;
            case R.id.share:
                showAlbumShareInfo();
                statAlbumOperation(this.mSelectedAlbum, "share_album");
                break;
            case R.id.unforce_top:
                doForceTop(false);
                statAlbumOperation(this.mSelectedAlbum, "unforce_top");
                break;
            case R.id.unhide:
                doHideOrUnhide(false);
                statAlbumOperation(this.mSelectedAlbum, "unhide_album");
                break;
            default:
                return false;
        }
        return true;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (contextMenuInfo != null) {
            setContextMenuItems(contextMenu, ((GalleryRecyclerView.RecyclerContextMenuInfo) contextMenuInfo).position);
        }
    }

    public void onStart() {
        super.onStart();
        this.mAlbumPageAdapter.updateGalleryCloudSyncableState();
        GalleryRecyclerView galleryRecyclerView = this.mRecyclerView;
        if (galleryRecyclerView != null) {
            registerForContextMenu(galleryRecyclerView);
        }
    }

    public void onStop() {
        super.onStop();
        GalleryRecyclerView galleryRecyclerView = this.mRecyclerView;
        if (galleryRecyclerView != null) {
            unregisterForContextMenu(galleryRecyclerView);
        }
    }

    /* JADX WARNING: type inference failed for: r3v8, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void setContextMenuItems(ContextMenu contextMenu, int i) {
        ContextMenu contextMenu2 = contextMenu;
        int i2 = i;
        Album item = this.mAlbumPageAdapter.getItem(i2);
        if (item == null) {
            Log.e("BaseAlbumPageFragment", "album entity should not be null!!!");
        } else if (item.getAlbumType() != Album.AlbumType.OTHER_ALBUMS) {
            this.mSelectedAlbum = item;
            this.mActivity.getMenuInflater().inflate(R.menu.album_list_menu, contextMenu2);
            contextMenu2.setHeaderTitle(this.mSelectedAlbum.getLocalizedAlbumName());
            MenuItem findItem = contextMenu2.findItem(R.id.force_top);
            MenuItem findItem2 = contextMenu2.findItem(R.id.unforce_top);
            MenuItem findItem3 = contextMenu2.findItem(R.id.delete);
            MenuItem findItem4 = contextMenu2.findItem(R.id.share);
            MenuItem findItem5 = contextMenu2.findItem(R.id.remove_from_other_albums);
            MenuItem findItem6 = contextMenu2.findItem(R.id.move_to_other_albums);
            MenuItem findItem7 = contextMenu2.findItem(R.id.enable_auto_upload);
            MenuItem findItem8 = contextMenu2.findItem(R.id.disable_auto_upload);
            MenuItem findItem9 = contextMenu2.findItem(R.id.enable_show_in_photos_tab);
            MenuItem findItem10 = contextMenu2.findItem(R.id.disable_show_in_photos_tab);
            MenuItem findItem11 = contextMenu2.findItem(R.id.hide);
            MenuItem findItem12 = contextMenu2.findItem(R.id.unhide);
            MenuItem findItem13 = contextMenu2.findItem(R.id.rename);
            MenuItem menuItem = findItem6;
            if (this.mAlbumPageAdapter.isForceTypeTime(i2)) {
                findItem2.setVisible(true);
            } else {
                findItem.setVisible(true);
            }
            boolean isGalleryCloudSyncable = SyncUtil.isGalleryCloudSyncable(this.mActivity);
            boolean isRawAlbum = this.mAlbumPageAdapter.isRawAlbum(this.mSelectedAlbum);
            if (this.mAlbumPageAdapter.isSystemAlbum(i2)) {
                if (this.mAlbumPageAdapter.isScreenshotsAlbum(i2)) {
                    if (isGalleryCloudSyncable) {
                        if (this.mAlbumPageAdapter.isAutoUploadedAlbum(i2)) {
                            findItem8.setVisible(true);
                        } else {
                            findItem7.setVisible(true);
                        }
                    }
                    if (this.mAlbumPageAdapter.isShowedPhotosTabAlbum(i2)) {
                        findItem10.setVisible(true);
                    } else {
                        findItem9.setVisible(true);
                    }
                }
            } else if (this.mAlbumPageAdapter.isOtherShareAlbum(i2)) {
                if (ApplicationHelper.supportShare()) {
                    findItem4.setVisible(true);
                }
                if (this.mAlbumPageAdapter.isHiddenAlbum(i2)) {
                    findItem12.setVisible(true);
                } else {
                    findItem11.setVisible(true);
                }
            } else if (!this.mAlbumPageAdapter.albumUnwriteable(i2)) {
                findItem3.setVisible(true);
                if (ApplicationHelper.supportShare() && !isRawAlbum) {
                    findItem4.setVisible(true);
                }
                if (!this.mAlbumPageAdapter.isBabyAlbum(i2)) {
                    if (isGalleryCloudSyncable) {
                        if (this.mAlbumPageAdapter.isAutoUploadedAlbum(i2)) {
                            findItem8.setVisible(true);
                        } else {
                            findItem7.setVisible(true);
                        }
                    }
                    if (!isRawAlbum) {
                        if (this.mAlbumPageAdapter.isHiddenAlbum(i2)) {
                            findItem12.setVisible(true);
                        } else {
                            findItem11.setVisible(true);
                        }
                    }
                    findItem13.setVisible(!isManualRenameRestricted(this.mSelectedAlbum.getLocalPath()));
                }
                if (!isRawAlbum) {
                    if (this.mAlbumPageAdapter.isOtherAlbum(i2)) {
                        findItem5.setVisible(true);
                    } else {
                        menuItem.setVisible(true);
                    }
                }
                if (this.mAlbumPageAdapter.isShowedPhotosTabAlbum(i2)) {
                    findItem10.setVisible(true);
                } else {
                    findItem9.setVisible(true);
                }
            } else if (this.mAlbumPageAdapter.isHiddenAlbum(i2)) {
                findItem12.setVisible(true);
            } else {
                findItem11.setVisible(true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void share() {
        boolean isOtherShareAlbumId = ShareAlbumManager.isOtherShareAlbumId(this.mSelectedAlbum.getAlbumId());
        long albumId = this.mSelectedAlbum.getAlbumId();
        if (isOtherShareAlbumId) {
            albumId = ShareAlbumManager.getOriginalAlbumId(albumId);
        }
        UIHelper.showAlbumShareInfo(getActivity(), new Path(albumId, isOtherShareAlbumId, !TextUtils.isEmpty(this.mSelectedAlbum.getBabyInfo())), isOtherShareAlbumId ? 6 : 0);
    }
}
