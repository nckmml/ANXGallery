package com.miui.gallery.ui.renameface;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CreateGroupItem;
import com.miui.gallery.model.DisplayFolderItem;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.provider.deprecated.NormalPeopleFaceMediaSet;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.renameface.FaceAlbumHandlerBase;
import com.miui.gallery.ui.renameface.PeopleFaceMergeDialogFragment;
import com.miui.gallery.util.ToastUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.app.AlertDialog;

public class FaceAlbumRenameHandler extends FaceAlbumHandlerBase {
    /* access modifiers changed from: private */
    public ConfirmListener mConfirmListener;
    private List<String> mFacePathsToBeMoved;
    /* access modifiers changed from: private */
    public ArrayList<NormalPeopleFaceMediaSet> mFaceSets;
    private boolean mIsFaceSetsMergeOperation;
    private boolean mIsFacesMoveOperation;
    private boolean mIsRelationSetted;

    public interface ConfirmListener {
        void onConfirm(String str, boolean z);
    }

    interface FaceOperationTask {
        void run();
    }

    public FaceAlbumRenameHandler(Activity activity, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet, ConfirmListener confirmListener) {
        this(activity, normalPeopleFaceMediaSet, confirmListener, false);
    }

    public FaceAlbumRenameHandler(Activity activity, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet, ConfirmListener confirmListener, boolean z) {
        super(activity, normalPeopleFaceMediaSet, (FaceAlbumHandlerBase.FaceAlbumHandlerListener) null);
        this.mIsFaceSetsMergeOperation = false;
        this.mConfirmListener = confirmListener;
        this.mIsRelationSetted = z;
    }

    public FaceAlbumRenameHandler(Activity activity, ArrayList<NormalPeopleFaceMediaSet> arrayList, ConfirmListener confirmListener) {
        super(activity, (NormalPeopleFaceMediaSet) null, (FaceAlbumHandlerBase.FaceAlbumHandlerListener) null);
        this.mIsFaceSetsMergeOperation = false;
        this.mIsFaceSetsMergeOperation = true;
        this.mFaceSets = arrayList;
        this.mConfirmListener = confirmListener;
        ArrayList<NormalPeopleFaceMediaSet> arrayList2 = this.mFaceSets;
        if (arrayList2 != null && arrayList2.size() == 1 && this.mFaceSets.get(0).hasName()) {
            this.mFaceSet = this.mFaceSets.get(0);
        }
    }

    private void dialogToast(final FaceOperationTask faceOperationTask, CharSequence charSequence) {
        new AlertDialog.Builder(this.mActivity).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                faceOperationTask.run();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setMessage(charSequence).show();
    }

    public static DisplayFolderItem getDisplayFolderItem(ArrayList<DisplayFolderItem> arrayList, String str) {
        if (arrayList == null) {
            return null;
        }
        Iterator<DisplayFolderItem> it = arrayList.iterator();
        while (it.hasNext()) {
            DisplayFolderItem next = it.next();
            if (next.name.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    private CharSequence getMessage(int i, String str) {
        if (i > 1) {
            return Html.fromHtml(this.mActivity.getString(R.string.confirm_merge_many_face_albums, new Object[]{str}));
        }
        return Html.fromHtml(this.mActivity.getString(R.string.confirm_merge_face_albums, new Object[]{str}));
    }

    /* access modifiers changed from: private */
    public void mergeLotsPeopleTo(final String str) {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                NormalPeopleFaceMediaSet.merge(FaceAlbumRenameHandler.this.mActivity, FaceAlbumRenameHandler.this.mFaceSets, str);
                return null;
            }
        });
        this.mConfirmListener.onConfirm(str, true);
    }

    /* access modifiers changed from: private */
    public void moveFacesTo(String str, FaceAlbumHandlerBase.FaceFolderItem faceFolderItem) {
    }

    private void onClick(final PeopleContactInfo peopleContactInfo) {
        FaceOperationTask faceOperationTask;
        ArrayList<NormalPeopleFaceMediaSet> arrayList;
        String str = peopleContactInfo.name;
        boolean z = peopleContactInfo.isRepeatName;
        String str2 = peopleContactInfo.localGroupId;
        final String trim = str.trim();
        int i = 1;
        if (this.mIsFacesMoveOperation) {
            i = this.mFacePathsToBeMoved.size();
            final FaceAlbumHandlerBase.FaceFolderItem faceFolderItemImpl = z ? new FaceAlbumHandlerBase.FaceFolderItemImpl(str, str2) : new FaceAlbumHandlerBase.FaceNewFolerItem(trim);
            faceOperationTask = new FaceOperationTask() {
                public void run() {
                    FaceAlbumRenameHandler.this.moveFacesTo(trim, faceFolderItemImpl);
                }
            };
        } else if (!this.mIsFaceSetsMergeOperation || ((arrayList = this.mFaceSets) != null && arrayList.size() == 1 && !z)) {
            faceOperationTask = new FaceOperationTask() {
                public void run() {
                    FaceAlbumRenameHandler.this.rename(trim, peopleContactInfo);
                }
            };
        } else {
            i = this.mFaceSets.size();
            faceOperationTask = new FaceOperationTask() {
                public void run() {
                    FaceAlbumRenameHandler.this.mergeLotsPeopleTo(trim);
                }
            };
        }
        if (z) {
            dialogToast(faceOperationTask, getMessage(i, trim));
        } else {
            faceOperationTask.run();
        }
    }

    /* access modifiers changed from: private */
    public void onClick(String str, boolean z) {
        final String trim = str.trim();
        int size = this.mFaceSets.size();
        AnonymousClass5 r1 = new FaceOperationTask() {
            public void run() {
                FaceAlbumRenameHandler.this.mergeLotsPeopleTo(trim);
            }
        };
        if (z) {
            dialogToast(r1, getMessage(size, trim));
        } else {
            r1.run();
        }
    }

    /* access modifiers changed from: private */
    public void rename(final String str, final PeopleContactInfo peopleContactInfo) {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                boolean z = false;
                NormalPeopleFaceMediaSet normalPeopleFaceMediaSet = FaceAlbumRenameHandler.this.mFaceSet != null ? FaceAlbumRenameHandler.this.mFaceSet : (NormalPeopleFaceMediaSet) FaceAlbumRenameHandler.this.mFaceSets.get(0);
                if (normalPeopleFaceMediaSet != null) {
                    z = normalPeopleFaceMediaSet.rename((Context) FaceAlbumRenameHandler.this.mActivity, str, peopleContactInfo);
                }
                if (FaceAlbumRenameHandler.this.mConfirmListener == null) {
                    return null;
                }
                FaceAlbumRenameHandler.this.mConfirmListener.onConfirm(str, z);
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void showInputFolderNameDialog(boolean z) {
        showInputFolderNameDialog(z, false);
    }

    private void showInputFolderNameDialog(boolean z, boolean z2) {
        showInputFolderNameDialog(z ? 19 : 16, this.mFaceSet != null ? this.mFaceSet.getName() : "", z2);
    }

    public void finishWhenGetContact(PeopleContactInfo peopleContactInfo) {
        if (peopleContactInfo != null && !TextUtils.isEmpty(peopleContactInfo.name)) {
            String checkFileNameValid = CreateGroupItem.checkFileNameValid(this.mActivity, peopleContactInfo.name);
            if (!TextUtils.isEmpty(checkFileNameValid)) {
                ToastUtils.makeText((Context) this.mActivity, (CharSequence) checkFileNameValid);
            } else {
                onClick(peopleContactInfo);
            }
        }
    }

    public void show() {
        if (!this.mIsFaceSetsMergeOperation) {
            showInputFolderNameDialog(false, this.mIsRelationSetted);
            return;
        }
        final PeopleFaceMergeDialogFragment peopleFaceMergeDialogFragment = new PeopleFaceMergeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("merge_action_from_album", this.mFaceSet);
        bundle.putString("people_face_Merge_title", this.mActivity.getString(R.string.merge_album_title));
        peopleFaceMergeDialogFragment.setArguments(bundle);
        peopleFaceMergeDialogFragment.showAllowingStateLoss(this.mActivity.getFragmentManager(), "PeopleFaceMergeDialogFragment");
        peopleFaceMergeDialogFragment.setPeopleSelectListener(new PeopleFaceMergeDialogFragment.PeopleSelectListener() {
            public void onPeopleSelect(FaceDisplayFolderItem faceDisplayFolderItem, boolean z, int i) {
                if (i == 0) {
                    FaceAlbumRenameHandler.this.showInputFolderNameDialog(true);
                } else {
                    FaceAlbumRenameHandler.this.onClick(faceDisplayFolderItem.name, z);
                }
                peopleFaceMergeDialogFragment.dismiss();
            }
        });
    }
}
