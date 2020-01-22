package com.miui.gallery.ui.photodetail;

import android.location.Address;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.CloudItem;
import com.miui.gallery.model.repository.config.ModelManager;
import com.miui.gallery.model.repository.datesource.location.ILocationRepository;
import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.ui.DownloadFragment;
import com.miui.gallery.ui.photodetail.PhotoDetailContract;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTime;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTimeAndRecord;
import com.miui.gallery.ui.photodetail.usecase.GetLocationInfo;
import com.miui.gallery.ui.photodetail.usecase.GetPhotoDetailInfo;
import com.miui.gallery.ui.photodetail.viewbean.PhotoDetailViewBean;
import com.miui.gallery.util.BulkDownloadHelper;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.RxUtils;
import com.miui.gallery.util.StringUtils;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import miui.util.Pools;

public class PhotoDetailPresenter extends PhotoDetailContract.P {
    private static final String TAG = "PhotoDetailPresenter";
    /* access modifiers changed from: private */
    public BaseDataItem mBaseDataItem;
    private Calendar mDateCalendar;
    private DownloadFragment.OnDownloadListener mDownLoadListener;
    private EditPhotoDateTime mEditPhotoDateTime;
    private GetLocationInfo mGetLocationInfo;
    private GetPhotoDetailInfo mGetPhotoDetailInfo;
    /* access modifiers changed from: private */
    public PhotoDetailViewBean mPhotoDetailInfo;
    /* access modifiers changed from: private */
    public long mUserSelectDateTime;

    /* access modifiers changed from: private */
    public void parseAddress(Address address) {
        if (address != null) {
            addDispose(Flowable.just(address).map(new Function<Address, String>() {
                public String apply(Address address) throws Exception {
                    StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
                    String addressLine = address.getAddressLine(0);
                    if (StringUtils.isEmpty(addressLine)) {
                        String[] strArr = {address.getAdminArea(), address.getSubAdminArea(), address.getLocality(), address.getSubLocality(), address.getThoroughfare(), address.getSubThoroughfare(), address.getPremises(), address.getPostalCode(), address.getCountryName()};
                        for (int i = 0; i < strArr.length; i++) {
                            if (!StringUtils.isEmpty(strArr[i])) {
                                if (sb.length() > 0) {
                                    sb.append(", ");
                                }
                                sb.append(strArr[i]);
                            }
                        }
                    } else {
                        sb.append(addressLine);
                    }
                    String sb2 = sb.toString();
                    Pools.getStringBuilderPool().release(sb);
                    return sb2;
                }
            }).compose(RxUtils.ioAndMainThread()).subscribe(new Consumer<String>() {
                public void accept(String str) throws Exception {
                    ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).bindLocation(str);
                }
            }));
        }
    }

    public boolean canDownLoad() {
        return this.mBaseDataItem != null;
    }

    public void downLoadOrigin() {
        if (this.mDownLoadListener == null) {
            this.mDownLoadListener = new DownloadFragment.OnDownloadListener() {
                public void onCanceled() {
                }

                public void onDownloadComplete(List<BulkDownloadHelper.BulkDownloadItem> list, List<BulkDownloadHelper.BulkDownloadItem> list2) {
                    if (list == null || list.size() < 1) {
                        ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).showDownLoadFailed();
                        return;
                    }
                    PhotoDetailPresenter.this.mBaseDataItem.setFilePath(list.get(0).getDownloadPath());
                    PhotoDetailPresenter.this.mPhotoDetailInfo.setFileSize(PhotoDetailPresenter.this.mBaseDataItem.getSize());
                    ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).showDownLoadSuccess(PhotoDetailPresenter.this.mPhotoDetailInfo);
                }
            };
        }
        BulkDownloadHelper.BulkDownloadItem bulkDownloadItem = NetworkUtils.isActiveNetworkMetered() ? new BulkDownloadHelper.BulkDownloadItem(this.mBaseDataItem.getDownloadUri(), DownloadType.ORIGIN_FORCE, this.mBaseDataItem.getSize()) : new BulkDownloadHelper.BulkDownloadItem(this.mBaseDataItem.getDownloadUri(), DownloadType.ORIGIN, this.mBaseDataItem.getSize());
        ArrayList arrayList = new ArrayList();
        arrayList.add(bulkDownloadItem);
        ((PhotoDetailContract.V) getView()).showDownLoadProgressDialog(arrayList, this.mDownLoadListener);
    }

    public long getDatePickerStartTime() {
        if (!getDetailInfo().isHaveDateTime()) {
            return 0;
        }
        if (this.mUserSelectDateTime == 0) {
            this.mUserSelectDateTime = getDetailInfo().getDateTime();
        }
        return this.mUserSelectDateTime;
    }

    public PhotoDetailViewBean getDetailInfo() {
        return this.mPhotoDetailInfo;
    }

    public String getFileLocalPath() {
        BaseDataItem baseDataItem = this.mBaseDataItem;
        if (baseDataItem == null) {
            return null;
        }
        return StringUtils.isEmpty(baseDataItem.getOriginalPath()) ? this.mBaseDataItem.getThumnailPath() : this.mBaseDataItem.getOriginalPath();
    }

    public double[] getLocation() {
        if (getDetailInfo() == null) {
            return null;
        }
        return getDetailInfo().getLocation();
    }

    public void init(final BaseDataItem baseDataItem) {
        this.mBaseDataItem = baseDataItem;
        this.mGetPhotoDetailInfo = new GetPhotoDetailInfo((IPhotoRepository) ModelManager.getInstance().getModel(IPhotoRepository.class));
        this.mGetPhotoDetailInfo.execute(new DisposableSubscriber<PhotoDetailViewBean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:2:0x0020, code lost:
                r0 = ((com.miui.gallery.model.CloudItem) com.miui.gallery.ui.photodetail.PhotoDetailPresenter.access$100(r5.this$0)).getBurstGroup();
             */
            public void onNext(PhotoDetailViewBean photoDetailViewBean) {
                List<BaseDataItem> burstGroup;
                PhotoDetailViewBean unused = PhotoDetailPresenter.this.mPhotoDetailInfo = photoDetailViewBean;
                PhotoDetailPresenter.this.mPhotoDetailInfo.setId(baseDataItem.getKey());
                boolean z = false;
                boolean z2 = !(PhotoDetailPresenter.this.mBaseDataItem instanceof CloudItem) || burstGroup == null || burstGroup.isEmpty();
                PhotoDetailContract.V v = (PhotoDetailContract.V) PhotoDetailPresenter.this.getView();
                if (PhotoDetailUtils.isMimeTypeSupportEditDateTime(PhotoDetailPresenter.this.mBaseDataItem.getMimeType()) && z2) {
                    z = true;
                }
                v.showDetailInfo(z, photoDetailViewBean);
            }
        }, baseDataItem);
    }

    public boolean isCanClick() {
        return getDetailInfo() != null;
    }

    public boolean isHaveLocalPath() {
        BaseDataItem baseDataItem = this.mBaseDataItem;
        if (baseDataItem == null) {
            return false;
        }
        return !StringUtils.isEmpty(baseDataItem.getOriginalPath()) || !StringUtils.isEmpty(this.mBaseDataItem.getThumnailPath());
    }

    public boolean isHaveLocation() {
        if (getDetailInfo() == null) {
            return false;
        }
        return getDetailInfo().isHaveLocation();
    }

    public boolean isThumbFile() {
        BaseDataItem baseDataItem = this.mBaseDataItem;
        return baseDataItem != null && StringUtils.isEmpty(baseDataItem.getOriginalPath()) && StringUtils.isEmpty(this.mBaseDataItem.getThumnailPath());
    }

    /* access modifiers changed from: protected */
    public void onDestory() {
        super.onDestory();
        GetLocationInfo getLocationInfo = this.mGetLocationInfo;
        if (getLocationInfo != null) {
            getLocationInfo.dispose();
        }
        EditPhotoDateTime editPhotoDateTime = this.mEditPhotoDateTime;
        if (editPhotoDateTime != null) {
            editPhotoDateTime.dispose();
        }
        GetPhotoDetailInfo getPhotoDetailInfo = this.mGetPhotoDetailInfo;
        if (getPhotoDetailInfo != null) {
            getPhotoDetailInfo.dispose();
        }
    }

    public void requestLocation() {
        if (this.mBaseDataItem != null) {
            if (this.mGetLocationInfo == null) {
                this.mGetLocationInfo = new GetLocationInfo((ILocationRepository) ModelManager.getInstance().getModel(ILocationRepository.class));
            }
            double[] location = this.mPhotoDetailInfo.getLocation();
            GetLocationInfo.RequestBean requestBean = new GetLocationInfo.RequestBean(this.mBaseDataItem.getKey(), location[0], location[1]);
            final String locationText = this.mPhotoDetailInfo.getLocationText();
            if (!StringUtils.isEmpty(locationText)) {
                this.mGetLocationInfo.execute(new DisposableSubscriber<Address>() {
                    public void onComplete() {
                    }

                    public void onError(Throwable th) {
                        Log.e(PhotoDetailPresenter.TAG, th.getMessage());
                        ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).bindLocation(locationText);
                    }

                    public void onNext(Address address) {
                        if (!LocationUtil.isInvalidAddress(address)) {
                            PhotoDetailPresenter.this.parseAddress(address);
                        } else {
                            ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).bindLocation(locationText);
                        }
                    }
                }, requestBean);
                return;
            }
            Log.d(TAG, "file path: %s no location", (Object) getFileLocalPath());
            ((PhotoDetailContract.V) getView()).bindLocation((String) null);
        }
    }

    public void updatePhotoDateTime(long j) {
        BaseDataItem baseDataItem = this.mBaseDataItem;
        if (baseDataItem != null && !StringUtils.isEmpty(baseDataItem.getOriginalPath()) && !DateUtils.isSameHour(j, this.mUserSelectDateTime)) {
            GallerySamplingStatHelper.recordCountEvent("photo_detail", "click_photo_datetimepicker_confirm_button");
            if (this.mEditPhotoDateTime == null) {
                this.mEditPhotoDateTime = new EditPhotoDateTimeAndRecord((IPhotoRepository) ModelManager.getInstance().getModel(IPhotoRepository.class), this.mBaseDataItem.getLocalGroupId(), this.mBaseDataItem.getCreateTime());
                this.mDateCalendar = Calendar.getInstance();
            }
            this.mDateCalendar.setTimeInMillis(System.currentTimeMillis());
            final long j2 = j + ((long) this.mDateCalendar.get(14));
            final EditPhotoDateTime.RequestBean requestBean = new EditPhotoDateTime.RequestBean(j2, this.mBaseDataItem.getKey());
            requestBean.setFavorite(this.mBaseDataItem.getFavoriteInfo(false).isFavorite());
            this.mEditPhotoDateTime.execute(new DisposableSubscriber<Boolean>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                    ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).showUpdateDateTimeResult((String) null);
                }

                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        long unused = PhotoDetailPresenter.this.mUserSelectDateTime = j2;
                        ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).showUpdateDateTimeResult(FileUtils.generateFileDateTimeName(new File(PhotoDetailPresenter.this.mBaseDataItem.getOriginalPath()), requestBean.getNewTime()));
                    }
                }

                /* access modifiers changed from: protected */
                public void onStart() {
                    super.onStart();
                    ((PhotoDetailContract.V) PhotoDetailPresenter.this.getView()).showLoadingDialog();
                }
            }, requestBean);
        }
    }
}
