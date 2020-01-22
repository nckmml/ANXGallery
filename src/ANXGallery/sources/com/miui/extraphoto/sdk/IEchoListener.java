package com.miui.extraphoto.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IEchoListener extends IInterface {

    public static abstract class Stub extends Binder implements IEchoListener {

        private static class Proxy implements IEchoListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onEchoEnd(String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IEchoListener");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onEchoStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IEchoListener");
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.miui.extraphoto.sdk.IEchoListener");
        }

        public static IEchoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.extraphoto.sdk.IEchoListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IEchoListener)) ? new Proxy(iBinder) : (IEchoListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.extraphoto.sdk.IEchoListener");
                onEchoStart();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.extraphoto.sdk.IEchoListener");
                onEchoEnd(parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.extraphoto.sdk.IEchoListener");
                return true;
            }
        }
    }

    void onEchoEnd(String str, String str2, boolean z) throws RemoteException;

    void onEchoStart() throws RemoteException;
}
