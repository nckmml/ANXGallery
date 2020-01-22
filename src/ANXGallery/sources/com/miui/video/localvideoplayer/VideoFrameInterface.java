package com.miui.video.localvideoplayer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public interface VideoFrameInterface extends IInterface {

    public static abstract class Stub extends Binder implements VideoFrameInterface {

        private static class Proxy implements VideoFrameInterface {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public FrameParams getFrameList(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.video.localvideoplayer.VideoFrameInterface");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? FrameParams.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prepare(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.video.localvideoplayer.VideoFrameInterface");
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void release(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.video.localvideoplayer.VideoFrameInterface");
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setSurface(Surface surface, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.video.localvideoplayer.VideoFrameInterface");
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showPreviewFrameAtTime(Surface surface, int i, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.video.localvideoplayer.VideoFrameInterface");
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static VideoFrameInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof VideoFrameInterface)) ? new Proxy(iBinder) : (VideoFrameInterface) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.view.Surface} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.view.Surface} */
        /* JADX WARNING: Multi-variable type inference failed */
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Surface surface = null;
            if (i == 1) {
                parcel.enforceInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
                if (parcel.readInt() != 0) {
                    surface = Surface.CREATOR.createFromParcel(parcel);
                }
                showPreviewFrameAtTime(surface, parcel.readInt(), parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
                FrameParams frameList = getFrameList(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                if (frameList != null) {
                    parcel2.writeInt(1);
                    frameList.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
                prepare(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
                if (parcel.readInt() != 0) {
                    surface = Surface.CREATOR.createFromParcel(parcel);
                }
                setSurface(surface, parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 5) {
                parcel.enforceInterface("com.miui.video.localvideoplayer.VideoFrameInterface");
                release(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.miui.video.localvideoplayer.VideoFrameInterface");
                return true;
            }
        }
    }

    FrameParams getFrameList(String str, int i, int i2) throws RemoteException;

    void prepare(String str) throws RemoteException;

    void release(String str) throws RemoteException;

    void setSurface(Surface surface, int i, String str) throws RemoteException;

    void showPreviewFrameAtTime(Surface surface, int i, String str, long j) throws RemoteException;
}
