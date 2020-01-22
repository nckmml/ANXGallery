package com.miui.gallery.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.internal.WindowCompat;
import com.miui.gallery.R;
import com.miui.gallery.adapter.BurstPhotoPageAdapter;
import com.miui.gallery.adapter.PhotoPageAdapter;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.BurstPhotoPreviewFragment;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.ui.ProcessTask;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.MiscUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import miui.app.AlertDialog;

public class BurstPhotoFragment extends PhotoPageFragmentBase {
    private BurstChoiceModeManager mBurstChoiceManager;
    /* access modifiers changed from: private */
    public TextView mTitle;

    private class BurstChoiceModeManager implements PhotoPageAdapter.MultiChoiceModeListener {
        protected PhotoPageAdapter.ChoiceMode mBurstChoiceMode;

        BurstChoiceModeManager() {
            setUpPreviewFragment();
        }

        /* JADX WARNING: type inference failed for: r4v3, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* access modifiers changed from: private */
        public void saveBurstItems(final boolean z) {
            if (DocumentProviderUtils.needRequestExternalSDCardPermission(BurstPhotoFragment.this.getActivity())) {
                DocumentProviderUtils.startExtSDCardPermissionActivityForResult(BurstPhotoFragment.this.getActivity());
                return;
            }
            ProcessTask processTask = new ProcessTask(new ProcessTask.ProcessCallback<Void, Boolean>() {
                /* JADX WARNING: Removed duplicated region for block: B:33:0x00c0 A[Catch:{ Exception -> 0x0192 }] */
                /* JADX WARNING: Removed duplicated region for block: B:36:0x00dc A[Catch:{ Exception -> 0x0192 }] */
                /* JADX WARNING: Removed duplicated region for block: B:39:0x00e7 A[Catch:{ Exception -> 0x0192 }] */
                public Boolean doProcess(Void[] voidArr) {
                    String str;
                    String str2;
                    Uri uri;
                    List<Integer> list;
                    String str3;
                    String str4;
                    int count = BurstPhotoFragment.this.mAdapter.getCount();
                    List<Integer> selectItems = BurstChoiceModeManager.this.mBurstChoiceMode.getSelectItems();
                    long[] jArr = new long[(count - selectItems.size())];
                    char c = 0;
                    int i = 1;
                    try {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        Uri contentUri = MediaStore.Files.getContentUri("external");
                        int i2 = count - 1;
                        int i3 = 0;
                        while (i2 >= 0) {
                            BaseDataItem dataItem = BurstPhotoFragment.this.mAdapter.getDataItem(i2);
                            if (!TextUtils.isEmpty(dataItem.getOriginalPath())) {
                                str2 = dataItem.getOriginalPath();
                                str = "localFile";
                            } else if (!TextUtils.isEmpty(dataItem.getThumnailPath())) {
                                str2 = dataItem.getThumnailPath();
                                str = "thumbnailFile";
                            } else {
                                str2 = null;
                                str = null;
                            }
                            if (!z) {
                                if (!selectItems.contains(Integer.valueOf(i2))) {
                                    if (FileUtils.isFileExist(str2)) {
                                        ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(contentUri);
                                        list = selectItems;
                                        String[] strArr = new String[i];
                                        strArr[c] = str2;
                                        arrayList2.add(newDelete.withSelection("_data=?", strArr).build());
                                    } else {
                                        list = selectItems;
                                    }
                                    jArr[i3] = dataItem.getKey();
                                    i3++;
                                    uri = contentUri;
                                    i2--;
                                    selectItems = list;
                                    contentUri = uri;
                                    c = 0;
                                    i = 1;
                                }
                            }
                            list = selectItems;
                            if (str2 != null) {
                                if (str2.contains("_BURST")) {
                                    str3 = str2.replace("_BURST", "_");
                                    if (!FileUtils.isFileExist(str2) || TextUtils.isEmpty(str3)) {
                                        uri = contentUri;
                                        str4 = str3;
                                    } else {
                                        uri = contentUri;
                                        str4 = str3;
                                        if (FileUtils.move(new File(str2), new File(str4))) {
                                            MediaStoreUtils.update(MediaStoreUtils.getFileMediaUri(str2), str4);
                                        } else {
                                            i2--;
                                            selectItems = list;
                                            contentUri = uri;
                                            c = 0;
                                            i = 1;
                                        }
                                    }
                                    ContentValues contentValues = new ContentValues();
                                    if (str4 != null) {
                                        contentValues.put(str, str4);
                                    }
                                    contentValues.put("title", dataItem.getTitle().replace("_BURST", "_"));
                                    arrayList.add(ContentProviderOperation.newUpdate(GalleryContract.Cloud.CLOUD_URI).withValues(contentValues).withSelection("_id=?", new String[]{String.valueOf(dataItem.getKey())}).build());
                                    String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(7);
                                    GalleryUtils.safeExec(String.format("update %s set %s=coalesce(replace(%s, '%s', '') || '%s', '%s'), %s = replace(%s,'%s', '_') where %s=%s", new Object[]{"cloud", "editedColumns", "editedColumns", transformToEditedColumnsElement, transformToEditedColumnsElement, transformToEditedColumnsElement, "fileName", "fileName", "_BURST", "_id", Long.valueOf(dataItem.getKey())}));
                                    i2--;
                                    selectItems = list;
                                    contentUri = uri;
                                    c = 0;
                                    i = 1;
                                }
                            }
                            str3 = null;
                            if (!FileUtils.isFileExist(str2) || TextUtils.isEmpty(str3)) {
                            }
                            ContentValues contentValues2 = new ContentValues();
                            if (str4 != null) {
                            }
                            contentValues2.put("title", dataItem.getTitle().replace("_BURST", "_"));
                            arrayList.add(ContentProviderOperation.newUpdate(GalleryContract.Cloud.CLOUD_URI).withValues(contentValues2).withSelection("_id=?", new String[]{String.valueOf(dataItem.getKey())}).build());
                            String transformToEditedColumnsElement2 = GalleryCloudUtils.transformToEditedColumnsElement(7);
                            GalleryUtils.safeExec(String.format("update %s set %s=coalesce(replace(%s, '%s', '') || '%s', '%s'), %s = replace(%s,'%s', '_') where %s=%s", new Object[]{"cloud", "editedColumns", "editedColumns", transformToEditedColumnsElement2, transformToEditedColumnsElement2, transformToEditedColumnsElement2, "fileName", "fileName", "_BURST", "_id", Long.valueOf(dataItem.getKey())}));
                            i2--;
                            selectItems = list;
                            contentUri = uri;
                            c = 0;
                            i = 1;
                        }
                        if (!arrayList.isEmpty()) {
                            BurstPhotoFragment.this.mActivity.getContentResolver().applyBatch("com.miui.gallery.cloud.provider", arrayList);
                        }
                        if (!arrayList2.isEmpty()) {
                            BurstPhotoFragment.this.mActivity.getContentResolver().applyBatch("media", arrayList2);
                        }
                    } catch (Exception e) {
                        Log.e("BurstPhotoFragment", "save burst failed", (Object) e);
                    }
                    if (jArr.length == 0) {
                        return true;
                    }
                    DeletionTask deletionTask = new DeletionTask();
                    deletionTask.setOnDeletionCompleteListener(new DeletionTask.OnDeletionCompleteListener() {
                        public void onDeleted(int i, long[] jArr) {
                            BurstPhotoFragment.this.onSaveBurstItemsCompleted();
                        }
                    });
                    deletionTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new DeletionTask.Param[]{new DeletionTask.Param(jArr, 2, -1, "", 49)});
                    return false;
                }
            });
            processTask.setCompleteListener(new ProcessTask.OnCompleteListener<Boolean>() {
                public void onCompleteProcess(Boolean bool) {
                    if (bool.booleanValue()) {
                        BurstPhotoFragment.this.onSaveBurstItemsCompleted();
                    }
                }
            });
            processTask.showProgress(BurstPhotoFragment.this.mActivity, BurstPhotoFragment.this.getString(R.string.burst_save_processing));
            processTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        private void setPhotoChoiceTitleVisible(boolean z) {
            int i = z ? 0 : 4;
            if (i != BurstPhotoFragment.this.mTitle.getVisibility()) {
                BurstPhotoFragment.this.mTitle.setVisibility(i);
            }
        }

        public void discard() {
            setPhotoChoiceTitleVisible(false);
            BurstPhotoFragment.this.finish();
        }

        public final void onAllItemsCheckedStateChanged(boolean z) {
        }

        public void onDataSetLoaded(BaseDataSet baseDataSet) {
            BurstPhotoPreviewFragment burstPhotoPreviewFragment = (BurstPhotoPreviewFragment) BurstPhotoFragment.this.getChildFragmentManager().findFragmentByTag("BurstPhotoPreviewFragment");
            if (burstPhotoPreviewFragment != null) {
                burstPhotoPreviewFragment.setDataSet(baseDataSet);
            }
            updateSelectMode();
        }

        public void onItemChanged(int i) {
            Fragment findFragmentByTag = BurstPhotoFragment.this.getChildFragmentManager().findFragmentByTag("BurstPhotoPreviewFragment");
            if (findFragmentByTag != null) {
                ((BurstPhotoPreviewFragment) findFragmentByTag).scrollToPosition(i);
            }
        }

        public final void onItemCheckedStateChanged(int i, long j, boolean z) {
            updateSelectMode();
            Fragment findFragmentByTag = BurstPhotoFragment.this.getChildFragmentManager().findFragmentByTag("BurstPhotoPreviewFragment");
            if (findFragmentByTag != null) {
                ((BurstPhotoPreviewFragment) findFragmentByTag).setCheckedItem(i, z);
            }
        }

        public void onStart() {
            this.mBurstChoiceMode = BurstPhotoFragment.this.mAdapter.startActionMode(this);
            setPhotoChoiceTitleVisible(true);
            BurstPhotoFragment.this.mPager.setCurrentItem(0);
            this.mBurstChoiceMode.setChecked(0, BurstPhotoFragment.this.mAdapter.getDataItem(0).getKey(), true);
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Fragment, com.miui.gallery.ui.BurstPhotoPreviewFragment] */
        /* access modifiers changed from: protected */
        public void setUpPreviewFragment() {
            Fragment findFragmentByTag = BurstPhotoFragment.this.getChildFragmentManager().findFragmentByTag("BurstPhotoPreviewFragment");
            if (findFragmentByTag == null) {
                FragmentTransaction beginTransaction = BurstPhotoFragment.this.getChildFragmentManager().beginTransaction();
                ? burstPhotoPreviewFragment = new BurstPhotoPreviewFragment();
                beginTransaction.add(R.id.photo_detail_preview, burstPhotoPreviewFragment, "BurstPhotoPreviewFragment");
                beginTransaction.commitAllowingStateLoss();
                findFragmentByTag = burstPhotoPreviewFragment;
            }
            BurstPhotoPreviewFragment burstPhotoPreviewFragment2 = (BurstPhotoPreviewFragment) findFragmentByTag;
            burstPhotoPreviewFragment2.setDataSet(BurstPhotoFragment.this.mAdapter.getDataSet());
            burstPhotoPreviewFragment2.setOnItemScrolledListener(new BurstPhotoPreviewFragment.OnScrollToPositionListener() {
                public void onScrollToPosition(int i) {
                    BurstPhotoFragment.this.mPager.setCurrentItem(i, false);
                }
            });
            burstPhotoPreviewFragment2.setOnExitListener(new BurstPhotoPreviewFragment.OnExitListener() {
                public void onDiscard() {
                    BurstChoiceModeManager.this.discard();
                }

                public void onSave() {
                    BurstChoiceModeManager.this.showSaveDialog();
                }
            });
        }

        /* JADX WARNING: type inference failed for: r3v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public void showSaveDialog() {
            final List<Integer> selectItems = this.mBurstChoiceMode.getSelectItems();
            if (MiscUtil.isValid(selectItems)) {
                new AlertDialog.Builder(BurstPhotoFragment.this.mActivity).setItems(new String[]{BurstPhotoFragment.this.getString(R.string.burst_save_all), BurstPhotoFragment.this.getResources().getQuantityString(R.plurals.burst_save_selected, selectItems.size(), new Object[]{Integer.valueOf(selectItems.size())})}, new DialogInterface.OnClickListener() {
                    /* JADX WARNING: type inference failed for: r0v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (i == 0) {
                            BurstChoiceModeManager.this.saveBurstItems(true);
                        } else if (i == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BurstPhotoFragment.this.mActivity);
                            int count = BurstPhotoFragment.this.mAdapter.getCount() - selectItems.size();
                            builder.setCancelable(true).setTitle(BurstPhotoFragment.this.getResources().getQuantityString(R.plurals.burst_save_confirm_dialog_title, count, new Object[]{Integer.valueOf(count)})).setMessage(BurstPhotoFragment.this.getResources().getQuantityString(R.plurals.burst_save_confirm_dialog_message, count, new Object[]{Integer.valueOf(count)})).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    BurstChoiceModeManager.this.saveBurstItems(false);
                                }
                            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
                        } else {
                            throw new IllegalStateException("unknown item clicked: " + i);
                        }
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
            }
        }

        /* access modifiers changed from: protected */
        public void updateSelectMode() {
            PhotoPageAdapter.ChoiceMode choiceMode = this.mBurstChoiceMode;
            if (choiceMode != null) {
                List<Integer> selectItems = choiceMode.getSelectItems();
                BurstPhotoFragment.this.mTitle.setText(BurstPhotoFragment.this.getString(R.string.burst_save_choice_title, new Object[]{Integer.valueOf(selectItems == null ? 0 : selectItems.size()), Integer.valueOf(BurstPhotoFragment.this.mAdapter.getCount())}));
            }
        }
    }

    private void configViewLayout(View view) {
        view.findViewById(R.id.top_part).setPadding(0, getArguments().getInt("notch_height", 0), 0, 0);
        this.mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.burst_pager_margin));
        ((BurstPhotoPageAdapter) this.mAdapter).setPagerView(this.mPager);
        ((BurstPhotoPageAdapter) this.mAdapter).setMaxPagerItemWidth(getResources().getDimensionPixelSize(R.dimen.burst_pager_item_max_width));
    }

    public static BurstPhotoFragment newInstance(Uri uri, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (uri != null) {
            bundle.putString("photo_uri", uri.toString());
        }
        BurstPhotoFragment burstPhotoFragment = new BurstPhotoFragment();
        burstPhotoFragment.setArguments(bundle);
        return burstPhotoFragment;
    }

    /* access modifiers changed from: private */
    public void onSaveBurstItemsCompleted() {
        if (this.mActivity != null) {
            this.mActivity.setResult(-1, (Intent) null);
            this.mActivity.finish();
        }
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public View getLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return LayoutInflater.from(this.mActivity).inflate(R.layout.burst_photo_page, viewGroup, false);
    }

    public String getPageName() {
        return "burst";
    }

    /* access modifiers changed from: protected */
    public PhotoPageAdapter getPhotoAdapter() {
        return new BurstPhotoPageAdapter(getInitCount(), getImageLoadParams(), getEnterViewInfo(getArguments().getInt("photo_init_position", 0)), this, getPhotoPageInteractionListener());
    }

    /* access modifiers changed from: protected */
    public String getTAG() {
        return "BurstPhotoFragment";
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return BuildUtil.isMiui10(this.mActivity) ? 2131820877 : 2131820884;
    }

    public boolean onBackPressed() {
        BurstChoiceModeManager burstChoiceModeManager = this.mBurstChoiceManager;
        if (burstChoiceModeManager == null) {
            return true;
        }
        burstChoiceModeManager.discard();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDataSetLoaded(BaseDataSet baseDataSet, boolean z) {
        super.onDataSetLoaded(baseDataSet, z);
        BurstChoiceModeManager burstChoiceModeManager = this.mBurstChoiceManager;
        if (burstChoiceModeManager != null) {
            if (z) {
                burstChoiceModeManager.onStart();
            }
            this.mBurstChoiceManager.onDataSetLoaded(baseDataSet);
        }
    }

    /* access modifiers changed from: protected */
    public void onItemChanged(int i) {
        BurstChoiceModeManager burstChoiceModeManager = this.mBurstChoiceManager;
        if (burstChoiceModeManager != null) {
            burstChoiceModeManager.onItemChanged(i);
        }
    }

    public void onResume() {
        super.onResume();
        onContentChanged();
    }

    /* access modifiers changed from: protected */
    public void onViewInflated(View view) {
        super.onViewInflated(view);
        configViewLayout(view);
        WindowCompat.setCutoutModeShortEdges(this.mActivity.getWindow());
        this.mTitle = (TextView) view.findViewById(R.id.choice_title);
        this.mBurstChoiceManager = new BurstChoiceModeManager();
    }
}
