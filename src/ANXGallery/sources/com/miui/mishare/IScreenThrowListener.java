package com.miui.mishare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IScreenThrowListener extends IInterface {

    public static abstract class Stub extends Binder implements IScreenThrowListener {

        private static class Proxy implements IScreenThrowListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onConnectFail() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IScreenThrowListener");
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onConnectSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IScreenThrowListener");
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static IScreenThrowListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.mishare.IScreenThrowListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IScreenThrowListener)) ? new Proxy(iBinder) : (IScreenThrowListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.mishare.IScreenThrowListener");
                onConnectSuccess();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.mishare.IScreenThrowListener");
                onConnectFail();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.mishare.IScreenThrowListener");
                return true;
            }
        }
    }

    void onConnectFail() throws RemoteException;

    void onConnectSuccess() throws RemoteException;
}
