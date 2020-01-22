package com.miui.gallery.model.repository.datesource.location;

import android.location.Address;
import com.miui.gallery.ui.photodetail.usecase.GetLocationInfo;
import io.reactivex.Flowable;

public interface ILocationRepository {
    Flowable<Address> getLocationInfo(GetLocationInfo.RequestBean requestBean);
}
