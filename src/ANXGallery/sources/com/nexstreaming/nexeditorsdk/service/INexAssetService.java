package com.nexstreaming.nexeditorsdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nexstreaming.nexeditorsdk.service.INexAssetConnectionCallback;
import com.nexstreaming.nexeditorsdk.service.INexAssetDataCallback;
import com.nexstreaming.nexeditorsdk.service.INexAssetInstallCallback;
import com.nexstreaming.nexeditorsdk.service.INexAssetUninstallCallback;

public interface INexAssetService extends IInterface {

    public static abstract class Stub extends Binder implements INexAssetService {
        private static final String DESCRIPTOR = "com.nexstreaming.nexeditorsdk.service.INexAssetService";
        static final int TRANSACTION_connectInstaller = 1;
        static final int TRANSACTION_loadInstalledAssetList = 4;
        static final int TRANSACTION_saveAssetInfoData = 5;
        static final int TRANSACTION_sendAssetData = 2;
        static final int TRANSACTION_uninstallAsset = 3;

        private static class a implements INexAssetService {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void connectInstaller(int i, String str, String str2, INexAssetConnectionCallback iNexAssetConnectionCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iNexAssetConnectionCallback != null ? iNexAssetConnectionCallback.asBinder() : null);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadInstalledAssetList(INexAssetDataCallback iNexAssetDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iNexAssetDataCallback != null ? iNexAssetDataCallback.asBinder() : null);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void saveAssetInfoData(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendAssetData(int i, String str, int i2, long j, INexAssetInstallCallback iNexAssetInstallCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    obtain.writeStrongBinder(iNexAssetInstallCallback != null ? iNexAssetInstallCallback.asBinder() : null);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void uninstallAsset(int i, INexAssetUninstallCallback iNexAssetUninstallCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iNexAssetUninstallCallback != null ? iNexAssetUninstallCallback.asBinder() : null);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INexAssetService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof INexAssetService)) ? new a(iBinder) : (INexAssetService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                connectInstaller(parcel.readInt(), parcel.readString(), parcel.readString(), INexAssetConnectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                sendAssetData(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readLong(), INexAssetInstallCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                uninstallAsset(parcel.readInt(), INexAssetUninstallCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                loadInstalledAssetList(INexAssetDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                saveAssetInfoData(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void connectInstaller(int i, String str, String str2, INexAssetConnectionCallback iNexAssetConnectionCallback) throws RemoteException;

    void loadInstalledAssetList(INexAssetDataCallback iNexAssetDataCallback) throws RemoteException;

    void saveAssetInfoData(int i, int i2, String str) throws RemoteException;

    void sendAssetData(int i, String str, int i2, long j, INexAssetInstallCallback iNexAssetInstallCallback) throws RemoteException;

    void uninstallAsset(int i, INexAssetUninstallCallback iNexAssetUninstallCallback) throws RemoteException;
}
