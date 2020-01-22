package com.android.systemui.screenshot;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.systemui.screenshot.IScreenShotCallback;

public interface IBitmapService extends IInterface {

    public static abstract class Stub extends Binder implements IBitmapService {
        public Stub() {
            attachInterface(this, "com.android.systemui.screenshot.IBitmapService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.android.systemui.screenshot.IBitmapService");
                registerCallback(IScreenShotCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.android.systemui.screenshot.IBitmapService");
                unregisterCallback(IScreenShotCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.android.systemui.screenshot.IBitmapService");
                return true;
            }
        }
    }

    void registerCallback(IScreenShotCallback iScreenShotCallback) throws RemoteException;

    void unregisterCallback(IScreenShotCallback iScreenShotCallback) throws RemoteException;
}
