package com.miui.mishare;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class RemoteDevice implements Parcelable {
    public static final Parcelable.Creator<RemoteDevice> CREATOR = new Parcelable.Creator<RemoteDevice>() {
        public RemoteDevice createFromParcel(Parcel parcel) {
            return new RemoteDevice(parcel);
        }

        public RemoteDevice[] newArray(int i) {
            return new RemoteDevice[i];
        }
    };
    private String mDeviceId;
    private Bundle mExtras;

    protected RemoteDevice(Parcel parcel) {
        this.mDeviceId = parcel.readString();
        this.mExtras = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof RemoteDevice ? ((RemoteDevice) obj).mDeviceId.equals(this.mDeviceId) : super.equals(obj);
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDeviceId);
    }

    public boolean isPC() {
        Bundle bundle = this.mExtras;
        return bundle != null && bundle.getInt("sgnt", -1) == 2;
    }

    public String toString() {
        return "RemoteDevice: " + this.mDeviceId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeviceId);
        parcel.writeBundle(this.mExtras);
    }
}
