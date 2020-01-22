package com.miui.gallery.model.repository.datesource.location;

import android.location.Address;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.data.ReverseGeocoder;
import com.miui.gallery.ui.photodetail.usecase.GetLocationInfo;
import io.reactivex.Flowable;
import java.util.concurrent.Callable;

public class LocationRemoteDataSource implements ILocationRepository {
    public Flowable<Address> getLocationInfo(final GetLocationInfo.RequestBean requestBean) {
        return Flowable.fromCallable(new Callable<Address>() {
            public Address call() throws Exception {
                Address lookupAddress = new ReverseGeocoder(GalleryApp.sGetAndroidContext()).lookupAddress(requestBean.getLatitude(), requestBean.getLongitude(), true);
                return lookupAddress == null ? LocationUtil.getInvalidAddress() : lookupAddress;
            }
        });
    }
}
