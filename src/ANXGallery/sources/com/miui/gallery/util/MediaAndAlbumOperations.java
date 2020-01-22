package com.miui.gallery.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.MediaOperationService;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.ui.AddRemoveFavoritesTask;
import com.miui.gallery.ui.AddRemoveSecretDialogFragment;
import com.miui.gallery.ui.AddToAlbumDialogFragment;
import com.miui.gallery.ui.AlertDialogFragment;
import com.miui.gallery.ui.CopyMoveDialogFragment;
import com.miui.gallery.ui.DeleteMediaDialogFragment;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.ui.ProduceCreationDialog;
import java.util.ArrayList;
import java.util.List;

public class MediaAndAlbumOperations {

    public interface OnAddAlbumListener {
        void onComplete(long[] jArr, boolean z);
    }

    public interface OnCompleteListener {
        void onComplete(long[] jArr);
    }

    private static void addRemoveFavorite(AddRemoveFavoritesTask.Param param, OnCompleteListener onCompleteListener) {
        AddRemoveFavoritesTask addRemoveFavoritesTask = new AddRemoveFavoritesTask();
        addRemoveFavoritesTask.setOperationCompleteListener(onCompleteListener);
        addRemoveFavoritesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AddRemoveFavoritesTask.Param[]{param});
    }

    public static void addToAlbum(Activity activity, OnAddAlbumListener onAddAlbumListener, int i, boolean z, boolean z2, boolean z3, boolean z4, long... jArr) {
        AddToAlbumDialogFragment addToAlbumDialogFragment = new AddToAlbumDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("show_copy_or_move", !hasOtherShareMedia(jArr) && z);
        bundle.putBoolean("show_share_album", true);
        boolean z5 = z3;
        bundle.putBoolean("show_add_secret", z3);
        addToAlbumDialogFragment.setArguments(bundle);
        final Activity activity2 = activity;
        final OnAddAlbumListener onAddAlbumListener2 = onAddAlbumListener;
        final boolean z6 = z4;
        final long[] jArr2 = jArr;
        final int i2 = i;
        final boolean z7 = z2;
        addToAlbumDialogFragment.setOnAlbumSelectedListener(new AddToAlbumDialogFragment.OnAlbumSelectedListener() {
            public void onAlbumSelected(long j, boolean z) {
                if (j == -1000) {
                    MediaAndAlbumOperations.addToSecretAlbum(activity2, (OnCompleteListener) new OnCompleteListener() {
                        public void onComplete(long[] jArr) {
                            if (onAddAlbumListener2 != null) {
                                onAddAlbumListener2.onComplete(jArr, true);
                            }
                        }
                    }, z6, jArr2);
                    return;
                }
                if (z && ShareAlbumManager.isOtherShareAlbumId(j)) {
                    ToastUtils.makeText((Context) activity2, (int) R.string.add_to_share_album_not_delete);
                    z = false;
                }
                long j2 = j;
                CopyMoveDialogFragment.show(activity2, j2, jArr2, i2, z, onAddAlbumListener2, z7);
            }
        });
        addToAlbumDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddToAlbumDialogFragment");
    }

    public static void addToAlbum(Activity activity, OnAddAlbumListener onAddAlbumListener, int i, boolean z, boolean z2, boolean z3, long... jArr) {
        addToAlbum(activity, onAddAlbumListener, i, z, false, z2, z3, jArr);
    }

    public static void addToAlbum(final Activity activity, final OnAddAlbumListener onAddAlbumListener, final ArrayList<Uri> arrayList, boolean z, boolean z2, final boolean z3) {
        AddToAlbumDialogFragment addToAlbumDialogFragment = new AddToAlbumDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("show_copy_or_move", z);
        bundle.putBoolean("show_share_album", true);
        bundle.putBoolean("show_add_secret", z2);
        addToAlbumDialogFragment.setArguments(bundle);
        addToAlbumDialogFragment.setOnAlbumSelectedListener(new AddToAlbumDialogFragment.OnAlbumSelectedListener() {
            public void onAlbumSelected(long j, boolean z) {
                if (j == -1000) {
                    MediaAndAlbumOperations.addToSecretAlbum(activity, (OnCompleteListener) new OnCompleteListener() {
                        public void onComplete(long[] jArr) {
                            if (onAddAlbumListener != null) {
                                onAddAlbumListener.onComplete(jArr, true);
                            }
                        }
                    }, (ArrayList<Uri>) arrayList, z3);
                    return;
                }
                if (z && ShareAlbumManager.isOtherShareAlbumId(j)) {
                    ToastUtils.makeText((Context) activity, (int) R.string.add_to_share_album_not_delete);
                    z = false;
                }
                long j2 = j;
                CopyMoveDialogFragment.show(activity, j2, arrayList, z, onAddAlbumListener);
            }
        });
        addToAlbumDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddToAlbumDialogFragment");
    }

    public static void addToFavoritesById(Activity activity, OnCompleteListener onCompleteListener, long... jArr) {
        if (activity != null && !activity.isFinishing()) {
            addRemoveFavorite(new AddRemoveFavoritesTask.Param(1, 3, jArr), onCompleteListener);
        }
    }

    public static void addToFavoritesByPath(Activity activity, OnCompleteListener onCompleteListener, String... strArr) {
        if (activity != null && !activity.isFinishing()) {
            addRemoveFavorite(new AddRemoveFavoritesTask.Param(1, 2, strArr), onCompleteListener);
        }
    }

    public static void addToSecretAlbum(final Activity activity, final OnCompleteListener onCompleteListener, final ArrayList<Uri> arrayList, final boolean z) {
        if (DocumentProviderUtils.needRequestExternalSDCardPermission(activity)) {
            DocumentProviderUtils.startExtSDCardPermissionActivityForResult(activity);
            return;
        }
        Resources resources = activity.getResources();
        new AlertDialogFragment.Builder().setTitle(resources.getString(R.string.add_secret_ensure_title)).setMessage(resources.getQuantityString(R.plurals.add_secret_ensure_message, arrayList.size())).setNegativeButton(resources.getString(R.string.cancel), (DialogInterface.OnClickListener) null).setPositiveButton(resources.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AddRemoveSecretDialogFragment.add(activity, onCompleteListener, z, (ArrayList<Uri>) arrayList);
            }
        }).create().showAllowingStateLoss(activity.getFragmentManager(), "ensureSecret");
    }

    public static void addToSecretAlbum(final Activity activity, final OnCompleteListener onCompleteListener, final boolean z, final long... jArr) {
        if (DocumentProviderUtils.needRequestExternalSDCardPermission(activity)) {
            DocumentProviderUtils.startExtSDCardPermissionActivityForResult(activity);
            return;
        }
        Resources resources = activity.getResources();
        new AlertDialogFragment.Builder().setTitle(resources.getString(R.string.add_secret_ensure_title)).setMessage(resources.getQuantityString(R.plurals.add_secret_ensure_message, jArr.length)).setNegativeButton(resources.getString(R.string.cancel), (DialogInterface.OnClickListener) null).setPositiveButton(resources.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AddRemoveSecretDialogFragment.add(activity, onCompleteListener, z, jArr);
            }
        }).create().showAllowingStateLoss(activity.getFragmentManager(), "ensureSecret");
    }

    public static void delete(Activity activity, String str, DeletionTask.OnDeletionCompleteListener onDeletionCompleteListener, long j, String str2, int i, int i2, boolean z, long... jArr) {
        if (activity != null && !activity.isFinishing()) {
            DeleteMediaDialogFragment newInstance = DeleteMediaDialogFragment.newInstance(new DeletionTask.Param(jArr, i, j, str2, i2, z));
            DeletionTask.OnDeletionCompleteListener onDeletionCompleteListener2 = onDeletionCompleteListener;
            newInstance.setOnDeletionCompleteListener(onDeletionCompleteListener);
            String str3 = str;
            newInstance.showAllowingStateLoss(activity.getFragmentManager(), str);
        }
    }

    public static void delete(Activity activity, String str, DeletionTask.OnDeletionCompleteListener onDeletionCompleteListener, long j, String str2, int i, int i2, long... jArr) {
        delete(activity, str, onDeletionCompleteListener, j, str2, i, i2, false, jArr);
    }

    public static void deleteInService(Context context, int i, int i2, String... strArr) {
        Intent intent = new Intent(context, MediaOperationService.class);
        intent.putExtra(MediaOperationService.EXTRA_METHOD, "delete");
        Bundle bundle = new Bundle();
        bundle.putInt("delete_by", 1);
        bundle.putStringArray("extra_paths", strArr);
        bundle.putInt("extra_delete_options", i);
        bundle.putInt("extra_delete_reason", i2);
        intent.putExtra(MediaOperationService.EXTRA_BUNDLE, bundle);
        context.startService(intent);
    }

    public static void doChangeAutoUpload(Context context, long j, boolean z) {
        doChangeAutoUpload(context, j, z, true);
    }

    public static boolean doChangeAutoUpload(Context context, long j, boolean z, boolean z2) {
        return CloudUtils.updateAlbumAttributes(context, GalleryContract.Cloud.CLOUD_URI, new long[]{j}, 1, z, true, z2);
    }

    public static boolean doChangeHiddenStatus(Context context, long j, boolean z) {
        return CloudUtils.updateAlbumAttributes(context, GalleryContract.Cloud.CLOUD_URI, new long[]{j}, 16, z, true, true);
    }

    public static boolean doChangeHiddenStatus(Context context, long j, boolean z, boolean z2) {
        return CloudUtils.updateAlbumAttributes(context, GalleryContract.Cloud.CLOUD_URI, new long[]{j}, 16, z, true, z2);
    }

    public static void doChangeShowInOtherAlbums(Context context, long j, boolean z) {
        CloudUtils.updateAlbumAttributes(context, GalleryContract.Cloud.CLOUD_URI, j, 64, z, true);
    }

    public static void doChangeShowInPhotosTab(Context context, long j, boolean z) {
        CloudUtils.updateAlbumAttributes(context, GalleryContract.Cloud.CLOUD_URI, j, 4, z, true);
    }

    public static void doForceTop(Context context, long j, boolean z) {
        long[] jArr = {j};
        if (z) {
            CloudUtils.forceTop(context, GalleryContract.Cloud.CLOUD_URI, jArr);
        } else {
            CloudUtils.unforceTop(context, GalleryContract.Cloud.CLOUD_URI, jArr);
        }
    }

    public static void doProduceCreation(final Activity activity, final OnCompleteListener onCompleteListener, final List<CheckableAdapter.CheckedItem> list) {
        ProduceCreationDialog produceCreationDialog = new ProduceCreationDialog();
        produceCreationDialog.setOnOperationSelectedListener(new ProduceCreationDialog.OnOperationSelectedListener() {
            public boolean onOperationSelected(int i) {
                Picker.ImageType imageType = Picker.ImageType.THUMBNAIL;
                if (i == 0) {
                    imageType = Picker.ImageType.THUMBNAIL;
                    GallerySamplingStatHelper.recordCountEvent("creation", "creation_click_collage");
                } else if (i == 1) {
                    imageType = Picker.ImageType.THUMBNAIL;
                    GallerySamplingStatHelper.recordCountEvent("creation", "creation_click_photomovie");
                } else if (i == 2) {
                    imageType = Picker.ImageType.ORIGIN_OR_DOWNLOAD_INFO;
                    GallerySamplingStatHelper.recordCountEvent("creation", "creation_click_print");
                }
                if (!ProduceCreationDialog.checkCreationCondition(activity, i, list)) {
                    return false;
                }
                IntentUtil.doCreation(activity, i, list, imageType);
                OnCompleteListener onCompleteListener = onCompleteListener;
                if (onCompleteListener != null) {
                    onCompleteListener.onComplete((long[]) null);
                }
                return true;
            }
        });
        produceCreationDialog.showAllowingStateLoss(activity.getFragmentManager(), "ProduceCreationDialog");
    }

    private static boolean hasOtherShareMedia(long[] jArr) {
        for (long isOtherShareMediaId : jArr) {
            if (ShareMediaManager.isOtherShareMediaId(isOtherShareMediaId)) {
                return true;
            }
        }
        return false;
    }

    public static void removeFromFavoritesById(Activity activity, OnCompleteListener onCompleteListener, long... jArr) {
        if (activity != null && !activity.isFinishing()) {
            addRemoveFavorite(new AddRemoveFavoritesTask.Param(2, 3, jArr), onCompleteListener);
        }
    }

    public static void removeFromFavoritesByPath(Activity activity, OnCompleteListener onCompleteListener, String... strArr) {
        if (activity != null && !activity.isFinishing()) {
            addRemoveFavorite(new AddRemoveFavoritesTask.Param(2, 2, strArr), onCompleteListener);
        }
    }

    public static void removeFromSecretAlbum(final Activity activity, final OnCompleteListener onCompleteListener, final long... jArr) {
        AddToAlbumDialogFragment addToAlbumDialogFragment = new AddToAlbumDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("show_copy_or_move", false);
        bundle.putBoolean("show_share_album", false);
        bundle.putBoolean("show_add_secret", false);
        addToAlbumDialogFragment.setArguments(bundle);
        addToAlbumDialogFragment.setOnAlbumSelectedListener(new AddToAlbumDialogFragment.OnAlbumSelectedListener() {
            public void onAlbumSelected(long j, boolean z) {
                AddRemoveSecretDialogFragment.remove(activity, onCompleteListener, j, jArr);
            }
        });
        addToAlbumDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "AddToAlbumDialogFragment");
    }
}
