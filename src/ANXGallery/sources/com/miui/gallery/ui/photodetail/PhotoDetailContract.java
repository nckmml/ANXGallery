package com.miui.gallery.ui.photodetail;

import com.miui.gallery.app.base.BaseFragment;
import com.miui.gallery.base_optimization.mvp.presenter.BasePresenter;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.ui.DownloadFragment;
import com.miui.gallery.ui.photodetail.viewbean.PhotoDetailViewBean;
import com.miui.gallery.util.BulkDownloadHelper;
import java.util.ArrayList;

public interface PhotoDetailContract {

    public static abstract class P extends BasePresenter<V> {
        public abstract boolean canDownLoad();

        public abstract void downLoadOrigin();

        public abstract long getDatePickerStartTime();

        public abstract PhotoDetailViewBean getDetailInfo();

        public abstract String getFileLocalPath();

        public abstract double[] getLocation();

        public abstract void init(BaseDataItem baseDataItem);

        public abstract boolean isCanClick();

        public abstract boolean isHaveLocalPath();

        public abstract boolean isHaveLocation();

        public abstract boolean isThumbFile();

        public abstract void requestLocation();

        public abstract void updatePhotoDateTime(long j);
    }

    public static abstract class V<P extends IPresenter> extends BaseFragment<P> {
        /* access modifiers changed from: package-private */
        public abstract void bindLocation(String str);

        public abstract void showDetailInfo(boolean z, PhotoDetailViewBean photoDetailViewBean);

        public abstract void showDownLoadFailed();

        public abstract void showDownLoadProgressDialog(ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList, DownloadFragment.OnDownloadListener onDownloadListener);

        public abstract void showDownLoadSuccess(PhotoDetailViewBean photoDetailViewBean);

        public abstract void showLoadingDialog();

        /* access modifiers changed from: package-private */
        public abstract void showUpdateDateTimeResult(String str);
    }
}
