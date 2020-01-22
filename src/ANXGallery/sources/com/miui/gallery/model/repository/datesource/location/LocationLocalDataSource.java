package com.miui.gallery.model.repository.datesource.location;

import android.location.Address;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.LocationManager;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.ui.photodetail.usecase.GetLocationInfo;
import com.miui.gallery.util.GsonUtils;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class LocationLocalDataSource implements ILocationRepository {
    public Flowable<Address> getLocationInfo(final GetLocationInfo.RequestBean requestBean) {
        return Flowable.fromCallable(new Callable<Address>() {
            public Address call() throws Exception {
                String queryAddressByCloudId = CloudUtils.queryAddressByCloudId(GalleryApp.sGetAndroidContext(), requestBean.getId());
                if (TextUtils.isEmpty(queryAddressByCloudId) || queryAddressByCloudId.length() < 10) {
                    return LocationUtil.getInvalidAddress();
                }
                try {
                    Address fromJson = LocationManager.AddressParser.fromJson(queryAddressByCloudId);
                    return fromJson == null ? LocationUtil.getInvalidAddress() : fromJson;
                } catch (JsonSyntaxException unused) {
                    List list = (List) GsonUtils.fromJson(queryAddressByCloudId, new TypeToken<ArrayList<Address>>() {
                    }.getType());
                    return list.isEmpty() ? LocationUtil.getInvalidAddress() : (Address) list.get(0);
                }
            }
        });
    }
}
