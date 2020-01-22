package com.miui.gallery.ui.photodetail.usecase;

import android.location.Address;
import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.repository.datesource.location.ILocationRepository;
import io.reactivex.Flowable;

public class GetLocationInfo extends BaseUseCase<Address, RequestBean> {
    private ILocationRepository mRepository;

    public static class RequestBean {
        private long cloudId;
        private double latitude;
        private double longitude;

        public RequestBean(long j, double d, double d2) {
            this.cloudId = j;
            this.latitude = d;
            this.longitude = d2;
        }

        public long getId() {
            return this.cloudId;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public boolean isHaveCoordinate() {
            return (0.0d == this.latitude || 0.0d == this.longitude) ? false : true;
        }

        public boolean isHaveId() {
            return 0 != this.cloudId;
        }
    }

    public GetLocationInfo(ILocationRepository iLocationRepository) {
        this.mRepository = iLocationRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<Address> buildUseCaseFlowable(RequestBean requestBean) {
        return this.mRepository.getLocationInfo(requestBean);
    }
}
