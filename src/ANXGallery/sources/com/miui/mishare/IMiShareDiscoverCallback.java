package com.miui.mishare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMiShareDiscoverCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMiShareDiscoverCallback {

        private static class Proxy implements IMiShareDiscoverCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onDeviceLost(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareDiscoverCallback");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDeviceUpdated(RemoteDevice remoteDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareDiscoverCallback");
                    if (remoteDevice != null) {
                        obtain.writeInt(1);
                        remoteDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.miui.mishare.IMiShareDiscoverCallback");
        }

        public static IMiShareDiscoverCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.mishare.IMiShareDiscoverCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMiShareDiscoverCallback)) ? new Proxy(iBinder) : (IMiShareDiscoverCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.mishare.IMiShareDiscoverCallback");
                onDeviceUpdated(parcel.readInt() != 0 ? RemoteDevice.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.mishare.IMiShareDiscoverCallback");
                onDeviceLost(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.mishare.IMiShareDiscoverCallback");
                return true;
            }
        }
    }

    void onDeviceLost(String str) throws RemoteException;

    void onDeviceUpdated(RemoteDevice remoteDevice) throws RemoteException;
}
