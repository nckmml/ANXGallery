package com.miui.mishare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMiShareTaskStateListener extends IInterface {

    public static abstract class Stub extends Binder implements IMiShareTaskStateListener {

        private static class Proxy implements IMiShareTaskStateListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onTaskIdChanged(MiShareTask miShareTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareTaskStateListener");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onTaskStateChanged(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareTaskStateListener");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.miui.mishare.IMiShareTaskStateListener");
        }

        public static IMiShareTaskStateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.mishare.IMiShareTaskStateListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMiShareTaskStateListener)) ? new Proxy(iBinder) : (IMiShareTaskStateListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.miui.mishare.IMiShareTaskStateListener");
                onTaskStateChanged(parcel.readString(), parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.mishare.IMiShareTaskStateListener");
                onTaskIdChanged(parcel.readInt() != 0 ? MiShareTask.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.mishare.IMiShareTaskStateListener");
                return true;
            }
        }
    }

    void onTaskIdChanged(MiShareTask miShareTask) throws RemoteException;

    void onTaskStateChanged(String str, int i) throws RemoteException;
}
