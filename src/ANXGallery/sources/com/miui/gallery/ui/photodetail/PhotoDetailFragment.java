package com.miui.gallery.ui.photodetail;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.ui.DownloadFragment;
import com.miui.gallery.ui.DownloadProgressFragment;
import com.miui.gallery.ui.photodetail.PhotoDetailContract;
import com.miui.gallery.ui.photodetail.viewbean.PhotoDetailViewBean;
import com.miui.gallery.util.BulkDownloadHelper;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StringUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.ViewUtils;
import com.miui.os.Rom;
import com.miui.widget.picker.DateTimePickerDialog;
import java.util.ArrayList;
import miui.app.ProgressDialog;

public class PhotoDetailFragment extends PhotoDetailContract.V<PhotoDetailPresenter> {
    /* access modifiers changed from: private */
    public boolean isCanEditPhotoDate;
    /* access modifiers changed from: private */
    public boolean isNeedDownLoadOriginPhoto;
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @SuppressLint({"ResourceType"})
        public void onClick(View view) {
            if (((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).isCanClick()) {
                int id = view.getId();
                if (id != R.id.location_title) {
                    if (id != R.id.path_title) {
                        if (id != R.id.time_item || !PhotoDetailFragment.this.isCanEditPhotoDate) {
                            return;
                        }
                        if (((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).isThumbFile() || PhotoDetailFragment.this.isNeedDownLoadOriginPhoto) {
                            PhotoDetailFragment.this.showDownLoadTipDialog();
                        } else {
                            PhotoDetailFragment.this.showDateTimePicker();
                        }
                    } else if (((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).isHaveLocalPath()) {
                        IntentUtil.jumpToExplore(PhotoDetailFragment.this.getSafeActivity(), FileUtils.getParentFolderPath(((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).getFileLocalPath()));
                    }
                } else if (((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).isHaveLocation()) {
                    double[] location = ((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).getLocation();
                    if (location != null && !IntentUtil.showOnMap(PhotoDetailFragment.this.getSafeActivity(), location[0], location[1])) {
                        ToastUtils.makeText((Context) PhotoDetailFragment.this.getSafeActivity(), (int) R.string.no_map_app);
                    }
                    GallerySamplingStatHelper.recordCountEvent("photo_detail", "show_on_map");
                }
            }
        }
    };
    private View mContentContainer;
    /* access modifiers changed from: private */
    public int mDialogPositiveButtonTextColor;
    private View mFileInfoItem;
    private TextView mFileInfoSub;
    private TextView mFileInfoTitle;
    private BaseDataItem mItem;
    private ProgressDialog mLoadingProgressDialog;
    private TextView mLocation;
    private View mLocationItem;
    private View mParamsItem;
    private TextView mPath;
    private View mPathItem;
    private ProgressBar mProgress;
    private Intent mResultIntent;
    private TextView mScreenshotPackageInfo;
    private View mScreenshotPackageItem;
    /* access modifiers changed from: private */
    public TextView mSubTitleView;
    private TextView mTakenParamsSub;
    private TextView mTakenParamsTitle;
    private int mTextViewHighColor;
    private View mTimeItem;
    private TextView mTimeSub;
    private TextView mTimeTitle;
    private TextView mTipNoDownload;
    /* access modifiers changed from: private */
    public TextView mTitleView;

    private void bindDetail(PhotoDetailViewBean photoDetailViewBean) {
        try {
            bindTime(photoDetailViewBean);
            bindFileInfo(photoDetailViewBean);
            bindNotDownloadTip(photoDetailViewBean);
            bindTakenParams(photoDetailViewBean);
            bindPath(photoDetailViewBean);
            if (photoDetailViewBean.isHaveLocation()) {
                ((PhotoDetailContract.P) getPresenter()).requestLocation();
            }
            bindScreenshotPackageInfo(photoDetailViewBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mContentContainer.setVisibility(0);
        this.mProgress.setVisibility(8);
    }

    private void bindFileInfo(PhotoDetailViewBean photoDetailViewBean) {
        boolean isHaveFileInfo = photoDetailViewBean.isHaveFileInfo();
        if (isHaveFileInfo) {
            this.mFileInfoTitle.setText(photoDetailViewBean.getFileName());
        }
        String fileSizeText = photoDetailViewBean.getFileSizeText();
        if (fileSizeText.length() > 0) {
            this.mFileInfoSub.setText(fileSizeText);
            setItemVisible(this.mFileInfoSub, true);
            isHaveFileInfo = true;
        } else {
            setItemVisible(this.mFileInfoSub, false);
        }
        setItemVisible(this.mFileInfoItem, isHaveFileInfo);
    }

    private void bindNotDownloadTip(PhotoDetailViewBean photoDetailViewBean) {
        if (photoDetailViewBean.isHaveDownLoadTip()) {
            this.mTipNoDownload.setText(photoDetailViewBean.getNotDownLoad());
            setItemVisible(this.mTipNoDownload, true);
            return;
        }
        setItemVisible(this.mTipNoDownload, false);
    }

    private void bindPath(PhotoDetailViewBean photoDetailViewBean) {
        View.OnClickListener onClickListener;
        int color = getResources().getColor(R.color.info_title_color);
        if (photoDetailViewBean.isHaveFilePath()) {
            this.mPath.setText(photoDetailViewBean.getDisplayFilePath());
            setItemVisible(this.mPathItem, true);
            if (photoDetailViewBean.isFile()) {
                onClickListener = this.mClickListener;
                color = this.mTextViewHighColor;
                this.mPath.setOnClickListener(onClickListener);
                setTextColor(this.mPath, color);
            }
        } else {
            setItemVisible(this.mPathItem, false);
        }
        onClickListener = null;
        this.mPath.setOnClickListener(onClickListener);
        setTextColor(this.mPath, color);
    }

    private void bindScreenshotPackageInfo(PhotoDetailViewBean photoDetailViewBean) {
        if (!photoDetailViewBean.isHaveCacheLocation() || this.mItem == null) {
            setItemVisible(this.mScreenshotPackageItem, false);
            return;
        }
        String fileName = photoDetailViewBean.getFileName();
        if (this.mItem.getLocalGroupId() != 2 || fileName == null || !fileName.startsWith("Screenshot")) {
            setItemVisible(this.mScreenshotPackageItem, false);
            return;
        }
        this.mScreenshotPackageInfo.setText(photoDetailViewBean.getCacheLocation());
        setItemVisible(this.mScreenshotPackageItem, true);
    }

    private void bindTakenParams(PhotoDetailViewBean photoDetailViewBean) {
        boolean isHaveTakenParams = photoDetailViewBean.isHaveTakenParams();
        if (isHaveTakenParams) {
            this.mTakenParamsTitle.setText(photoDetailViewBean.getPhoneModel());
            String takenParam = photoDetailViewBean.getTakenParam();
            if (takenParam.length() > 0) {
                this.mTakenParamsSub.setText(takenParam);
                setItemVisible(this.mTakenParamsSub, true);
                isHaveTakenParams = true;
            } else {
                setItemVisible(this.mTakenParamsSub, false);
            }
            setItemVisible(this.mParamsItem, isHaveTakenParams);
            return;
        }
        setItemVisible(this.mParamsItem, false);
    }

    private void bindTime(PhotoDetailViewBean photoDetailViewBean) {
        if (photoDetailViewBean.isHaveDateTime()) {
            setTimeToView(photoDetailViewBean.getDateText(), photoDetailViewBean.getTimeText());
            setItemVisible(this.mTimeItem, true);
            if (this.isCanEditPhotoDate) {
                setTextColor(this.mTimeTitle, this.mTextViewHighColor);
                setTextColor(this.mTimeSub, this.mTextViewHighColor);
                this.mTimeItem.setOnClickListener(this.mClickListener);
                return;
            }
            return;
        }
        setItemVisible(this.mTimeItem, false);
    }

    /* access modifiers changed from: private */
    public void downloadOrigin() {
        if (getSafeActivity() != null) {
            if (NetworkUtils.isActiveNetworkMetered()) {
                DialogUtil.showInfoDialog((Context) getSafeActivity(), getResources().getString(R.string.download_with_metered_network_msg), getResources().getString(R.string.download_with_metered_network_title), 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PhotoDetailFragment", "pre DownLoad Origin");
                        ((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).downLoadOrigin();
                    }
                }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PhotoDetailFragment", "cancel DownLoad Origin");
                        dialogInterface.cancel();
                    }
                });
            } else {
                ((PhotoDetailContract.P) getPresenter()).downLoadOrigin();
            }
        }
    }

    private void finishActivity(int i) {
        if (getActivity() != null) {
            if (this.mResultIntent != null) {
                getActivity().setResult(i, this.mResultIntent);
            } else {
                getActivity().setResult(i);
            }
            getActivity().finish();
        }
    }

    public static PhotoDetailFragment newInstance() {
        Bundle bundle = new Bundle();
        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        photoDetailFragment.setArguments(bundle);
        return photoDetailFragment;
    }

    private void putResult(String str) {
        if (this.mResultIntent == null) {
            this.mResultIntent = new Intent();
        }
        this.mResultIntent.putExtra("photo_focused_path", str);
    }

    private void setItemVisible(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    private void setTextColor(TextView textView, int i) {
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    private void setTimeToView(String str, String str2) {
        this.mTimeTitle.setText(str);
        this.mTimeSub.setText(str2);
    }

    /* access modifiers changed from: private */
    public void showDateTimePicker() {
        DateTimePickerDialog create = new DateTimePickerDialog.Builder(getSafeActivity()).showHour().enable24Hour().setPickerItemLabelSize(20).setStartTime(((PhotoDetailContract.P) getPresenter()).getDatePickerStartTime()).setMaxTime(System.currentTimeMillis()).setTitleView(R.layout.datepicker_custom_titleview).setIsShowLabel(!Rom.IS_INTERNATIONAL).setOnDateSetListener(new DateTimePickerDialog.OnDateSetListener() {
            private void setTimePickerDialogTitle(DateTimePickerDialog dateTimePickerDialog, String str) {
                if (PhotoDetailFragment.this.mTitleView == null && PhotoDetailFragment.this.mSubTitleView == null) {
                    TextView unused = PhotoDetailFragment.this.mTitleView = (TextView) dateTimePickerDialog.getTitleView().findViewById(R.id.tv_title);
                    TextView unused2 = PhotoDetailFragment.this.mSubTitleView = (TextView) dateTimePickerDialog.getTitleView().findViewById(R.id.tv_subtitle);
                    PhotoDetailFragment.this.mTitleView.setText(R.string.datetimepicker_dialog_title);
                }
                PhotoDetailFragment.this.mSubTitleView.setText(str);
            }

            public void onDateSet(DateTimePickerDialog dateTimePickerDialog, int i, int i2, int i3, int i4, int i5, long j) {
                ((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).updatePhotoDateTime(j);
            }

            public void onSelectChange(DateTimePickerDialog dateTimePickerDialog, String str, long j) {
                setTimePickerDialogTitle(dateTimePickerDialog, str);
            }
        }).create();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                TextView unused = PhotoDetailFragment.this.mTitleView = null;
                TextView unused2 = PhotoDetailFragment.this.mSubTitleView = null;
            }
        });
        create.setOnShowListener(new DateTimePickerDialog.OnShowListener() {
            public void onShow(DialogInterface dialogInterface, AlertDialog alertDialog) {
                if (PhotoDetailFragment.this.mDialogPositiveButtonTextColor == 0) {
                    int unused = PhotoDetailFragment.this.mDialogPositiveButtonTextColor = DialogUtil.getDialogButtonTextColor(alertDialog, -2);
                }
                DialogUtil.setDialogButtonTextColor(alertDialog, -1, PhotoDetailFragment.this.mDialogPositiveButtonTextColor);
            }
        });
        create.show();
    }

    /* access modifiers changed from: private */
    public void showDownLoadTipDialog() {
        if (getSafeActivity() != null && ((PhotoDetailContract.P) getPresenter()).canDownLoad()) {
            Log.d("PhotoDetailFragment", "show DownLoad Tip Dialog");
            DialogUtil.showInfoDialog((Context) getSafeActivity(), (int) R.string.editphoto_datetime_needdownload_origin_subtitle, (int) R.string.editphoto_datetime_needdownload_origin_title, (int) R.string.yes, (int) R.string.no, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PhotoDetailFragment.this.downloadOrigin();
                }
            }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("PhotoDetailFragment", "cancel DownLoad Tip Dialog");
                }
            });
        }
    }

    public void bindLocation(String str) {
        if (!StringUtils.isEmpty(str)) {
            this.mLocation.setText(str);
            setItemVisible(this.mLocationItem, true);
            return;
        }
        setItemVisible(this.mLocationItem, false);
    }

    public int getLayoutId() {
        return R.layout.photo_detail;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0018, code lost:
        if (r0 == null) goto L_0x001a;
     */
    public void initView(View view, Bundle bundle, View view2) {
        Bundle extras = getSafeActivity().getIntent().getExtras();
        if (extras != null) {
            BaseDataItem baseDataItem = (BaseDataItem) extras.getSerializable("photo_detail_target");
            this.mItem = baseDataItem;
        }
        finish();
        this.isCanEditPhotoDate = extras.getBoolean("photodetail_is_photo_datetime_editable", false);
        this.isNeedDownLoadOriginPhoto = extras.getBoolean("photo_detail_is_need_download_originphoto");
        ViewUtils.setRootViewClickable(view2);
        this.mTimeItem = view.findViewById(R.id.time_item);
        this.mTimeTitle = (TextView) view.findViewById(R.id.time_title);
        this.mTimeSub = (TextView) view.findViewById(R.id.time_subtitle);
        this.mFileInfoItem = view.findViewById(R.id.file_info_item);
        this.mFileInfoTitle = (TextView) view.findViewById(R.id.file_info_title);
        this.mTipNoDownload = (TextView) view.findViewById(R.id.tip_no_download);
        this.mFileInfoSub = (TextView) view.findViewById(R.id.file_info_subtitle);
        this.mParamsItem = view.findViewById(R.id.params_item);
        this.mTakenParamsTitle = (TextView) view.findViewById(R.id.params_title);
        this.mTakenParamsSub = (TextView) view.findViewById(R.id.params_subtitle);
        this.mPathItem = view.findViewById(R.id.path_item);
        this.mPath = (TextView) view.findViewById(R.id.path_title);
        this.mLocationItem = view.findViewById(R.id.location_item);
        this.mLocation = (TextView) view.findViewById(R.id.location_title);
        this.mContentContainer = view.findViewById(R.id.content_container);
        this.mProgress = (ProgressBar) view.findViewById(R.id.progress);
        this.mScreenshotPackageItem = view.findViewById(R.id.screenshot_package_info);
        this.mScreenshotPackageInfo = (TextView) view.findViewById(R.id.screenshot_package_name);
        this.mLocation.setOnClickListener(this.mClickListener);
        this.mTextViewHighColor = getResources().getColor(R.color.info_highlight_color);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((PhotoDetailContract.P) getPresenter()).init(this.mItem);
    }

    public boolean onBackPressed() {
        finishActivity(-1);
        return false;
    }

    public void showDetailInfo(boolean z, PhotoDetailViewBean photoDetailViewBean) {
        if (this.isCanEditPhotoDate) {
            this.isCanEditPhotoDate = z;
        }
        bindDetail(photoDetailViewBean);
    }

    public void showDownLoadFailed() {
        DialogUtil.showInfoDialog((Context) getSafeActivity(), getResources().getString(R.string.download_retry_message), getResources().getString(R.string.download_retry_title), (int) R.string.download_retry_text, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("PhotoDetailFragment", "pre show DownLoad Dialog");
                ((PhotoDetailContract.P) PhotoDetailFragment.this.getPresenter()).downLoadOrigin();
            }
        }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("PhotoDetailFragment", "User network status is not good, download failed");
            }
        });
    }

    public void showDownLoadProgressDialog(ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList, DownloadFragment.OnDownloadListener onDownloadListener) {
        Log.d("PhotoDetailFragment", "show DownLoad Dialog");
        DownloadProgressFragment newInstance = DownloadProgressFragment.newInstance(arrayList);
        newInstance.setOnDownloadListener(onDownloadListener);
        newInstance.showAllowingStateLoss(getFragmentManager(), "DownloadFragment");
    }

    public void showDownLoadSuccess(PhotoDetailViewBean photoDetailViewBean) {
        Log.d("PhotoDetailFragment", "DownLoad %s is Success", (Object) photoDetailViewBean.getDisplayFilePath());
        this.isNeedDownLoadOriginPhoto = false;
        bindFileInfo(photoDetailViewBean);
        showDateTimePicker();
        putResult(photoDetailViewBean.getDisplayFilePath());
    }

    public void showLoadingDialog() {
        this.mLoadingProgressDialog = new ProgressDialog(getSafeActivity());
        this.mLoadingProgressDialog.setMessage(getString(R.string.photo_editor_saving));
        this.mLoadingProgressDialog.setCancelable(false);
        this.mLoadingProgressDialog.setCanceledOnTouchOutside(false);
        this.mLoadingProgressDialog.setIndeterminate(true);
        this.mLoadingProgressDialog.show();
    }

    /* access modifiers changed from: package-private */
    public void showUpdateDateTimeResult(String str) {
        ProgressDialog progressDialog = this.mLoadingProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (str != null) {
            putResult(str);
            ToastUtils.makeText((Context) getSafeActivity(), (int) R.string.datetimepicker_updatetime_success);
            onBackPressed();
            return;
        }
        ToastUtils.makeText((Context) getSafeActivity(), (int) R.string.datetimepicker_updatetime_failed);
    }
}
