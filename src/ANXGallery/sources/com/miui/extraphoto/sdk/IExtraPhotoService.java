package com.miui.extraphoto.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.miui.extraphoto.sdk.IEchoListener;
import java.util.List;

public interface IExtraPhotoService extends IInterface {

    public static abstract class Stub extends Binder implements IExtraPhotoService {

        private static class Proxy implements IExtraPhotoService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void cancelEcho(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean echo(String str, IEchoListener iEchoListener, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iEchoListener != null ? iEchoListener.asBinder() : null);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isExtraCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    boolean z = false;
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isExtraPhoto(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean needEcho(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pauseEcho() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void releaseEcho() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<ExtraFunc> resolveExtraFuncs() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ExtraFunc.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resumeEcho() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterEchoListener(IEchoListener iEchoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.extraphoto.sdk.IExtraPhotoService");
                    obtain.writeStrongBinder(iEchoListener != null ? iEchoListener.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IExtraPhotoService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IExtraPhotoService)) ? new Proxy(iBinder) : (IExtraPhotoService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        boolean isExtraCamera = isExtraCamera();
                        parcel2.writeNoException();
                        parcel2.writeInt(isExtraCamera ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        boolean isExtraPhoto = isExtraPhoto(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isExtraPhoto ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        List<ExtraFunc> resolveExtraFuncs = resolveExtraFuncs();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(resolveExtraFuncs);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        boolean needEcho = needEcho(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(needEcho ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        boolean echo = echo(parcel.readString(), IEchoListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(echo ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        unregisterEchoListener(IEchoListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        cancelEcho(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        resumeEcho();
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        pauseEcho();
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface("com.miui.extraphoto.sdk.IExtraPhotoService");
                        releaseEcho();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.miui.extraphoto.sdk.IExtraPhotoService");
                return true;
            }
        }
    }

    void cancelEcho(String str) throws RemoteException;

    boolean echo(String str, IEchoListener iEchoListener, boolean z) throws RemoteException;

    boolean isExtraCamera() throws RemoteException;

    boolean isExtraPhoto(String str) throws RemoteException;

    boolean needEcho(String str) throws RemoteException;

    void pauseEcho() throws RemoteException;

    void releaseEcho() throws RemoteException;

    List<ExtraFunc> resolveExtraFuncs() throws RemoteException;

    void resumeEcho() throws RemoteException;

    void unregisterEchoListener(IEchoListener iEchoListener) throws RemoteException;
}
