package com.miui.mishare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IThumbnailCallback extends IInterface {

    public static abstract class Stub extends Binder implements IThumbnailCallback {

        private static class Proxy implements IThumbnailCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onThumbnail(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IThumbnailCallback");
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static IThumbnailCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.mishare.IThumbnailCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IThumbnailCallback)) ? new Proxy(iBinder) : (IThumbnailCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.mishare.IThumbnailCallback");
                onThumbnail(parcel.createByteArray());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.mishare.IThumbnailCallback");
                return true;
            }
        }
    }

    void onThumbnail(byte[] bArr) throws RemoteException;
}
