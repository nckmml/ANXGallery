package com.miui.gallery.model.repository;

import android.location.Address;
import com.miui.gallery.model.repository.datesource.location.ILocationRepository;
import com.miui.gallery.model.repository.datesource.location.LocationLocalDataSource;
import com.miui.gallery.model.repository.datesource.location.LocationRemoteDataSource;
import com.miui.gallery.ui.photodetail.usecase.GetLocationInfo;
import io.reactivex.Flowable;

public class LocationRepository implements ILocationRepository {
    private ILocationRepository mLocalDataSource;
    private ILocationRepository mRemoteDataSource;

    public LocationRepository() {
        this(new LocationLocalDataSource(), new LocationRemoteDataSource());
    }

    public LocationRepository(ILocationRepository iLocationRepository, ILocationRepository iLocationRepository2) {
        this.mLocalDataSource = iLocationRepository;
        this.mRemoteDataSource = iLocationRepository2;
    }

    public Flowable<Address> getLocationInfo(GetLocationInfo.RequestBean requestBean) {
        if (!requestBean.isHaveId() || !requestBean.isHaveCoordinate()) {
            return requestBean.isHaveId() ? this.mLocalDataSource.getLocationInfo(requestBean) : this.mRemoteDataSource.getLocationInfo(requestBean);
        }
        return Flowable.concatArrayDelayError(this.mLocalDataSource.getLocationInfo(requestBean), this.mRemoteDataSource.getLocationInfo(requestBean));
    }
}
