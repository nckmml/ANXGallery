package com.miui.gallery.ui.renameface;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.provider.deprecated.NormalPeopleFaceMediaSet;
import com.miui.gallery.ui.renameface.FaceAlbumHandlerBase;
import com.miui.gallery.ui.renameface.PeopleFaceMergeDialogFragment;
import miui.app.AlertDialog;

public class RemoveFromFaceAlbumHandler extends FaceAlbumHandlerBase {
    public RemoveFromFaceAlbumHandler(Activity activity, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet, FaceAlbumHandlerBase.FaceAlbumHandlerListener faceAlbumHandlerListener) {
        super(activity, normalPeopleFaceMediaSet, faceAlbumHandlerListener);
    }

    public void finishWhenGetContact(PeopleContactInfo peopleContactInfo) {
        if (peopleContactInfo != null && !TextUtils.isEmpty(peopleContactInfo.name) && this.mListener != null) {
            FaceAlbumHandlerBase.FaceNewFolerItem faceNewFolerItem = new FaceAlbumHandlerBase.FaceNewFolerItem(peopleContactInfo.name);
            faceNewFolerItem.setContactjson(peopleContactInfo.formatContactJson());
            this.mListener.onGetFolderItem(faceNewFolerItem);
        }
    }

    public void show() {
        new AlertDialog.Builder(this.mActivity).setItems(new String[]{this.mActivity.getString(R.string.remove_from_current_album), this.mActivity.getString(R.string.remove_to_other_album)}, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i != 0) {
                    if (i == 1) {
                        RemoveFromFaceAlbumHandler.this.showRemoveDialog();
                        return;
                    }
                    throw new IllegalStateException("unknown item clicked: " + i);
                } else if (RemoveFromFaceAlbumHandler.this.mListener != null) {
                    RemoveFromFaceAlbumHandler.this.mListener.onGetFolderItem((FaceAlbumHandlerBase.FaceFolderItem) null);
                }
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    public void showRemoveDialog() {
        final PeopleFaceMergeDialogFragment peopleFaceMergeDialogFragment = new PeopleFaceMergeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("people_face_Merge_title", this.mActivity.getString(R.string.remove_to_album_title));
        bundle.putParcelable("merge_action_from_album", this.mFaceSet);
        peopleFaceMergeDialogFragment.setArguments(bundle);
        peopleFaceMergeDialogFragment.showAllowingStateLoss(this.mActivity.getFragmentManager(), "PeopleFaceMergeDialogFragment");
        peopleFaceMergeDialogFragment.setPeopleSelectListener(new PeopleFaceMergeDialogFragment.PeopleSelectListener() {
            public void onPeopleSelect(FaceDisplayFolderItem faceDisplayFolderItem, boolean z, int i) {
                if (i == 0) {
                    RemoveFromFaceAlbumHandler.this.showInputFolderNameDialog(17, (String) null, false);
                } else if (RemoveFromFaceAlbumHandler.this.mListener != null) {
                    RemoveFromFaceAlbumHandler.this.mListener.onGetFolderItem(new FaceAlbumHandlerBase.FaceFolderItemImpl(faceDisplayFolderItem));
                }
                peopleFaceMergeDialogFragment.dismiss();
            }
        });
    }
}
