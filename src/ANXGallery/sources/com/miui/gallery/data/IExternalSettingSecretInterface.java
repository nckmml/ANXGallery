package com.miui.gallery.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IExternalSettingSecretInterface extends IInterface {

    public static abstract class Stub extends Binder implements IExternalSettingSecretInterface {
        public Stub() {
            attachInterface(this, "com.miui.gallery.data.IExternalSettingSecretInterface");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.gallery.data.IExternalSettingSecretInterface");
                preSettingSecret(parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.gallery.data.IExternalSettingSecretInterface");
                onFinishSettingSecret(parcel.createStringArray(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.gallery.data.IExternalSettingSecretInterface");
                return true;
            }
        }
    }

    void onFinishSettingSecret(String[] strArr, String[] strArr2) throws RemoteException;

    void preSettingSecret(String[] strArr) throws RemoteException;
}
