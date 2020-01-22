package com.miui.mishare;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.miui.mishare.IMiShareDiscoverCallback;
import com.miui.mishare.IMiShareStateListener;
import com.miui.mishare.IMiShareTaskStateListener;
import com.miui.mishare.IScreenThrowListener;
import com.miui.mishare.IThumbnailCallback;

public interface IMiShareService extends IInterface {

    public static abstract class Stub extends Binder implements IMiShareService {

        private static class Proxy implements IMiShareService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void cancel(MiShareTask miShareTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void closeScreenThrow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void disable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void discover(IMiShareDiscoverCallback iMiShareDiscoverCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareDiscoverCallback != null ? iMiShareDiscoverCallback.asBinder() : null);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void discoverWithIntent(IMiShareDiscoverCallback iMiShareDiscoverCallback, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareDiscoverCallback != null ? iMiShareDiscoverCallback.asBinder() : null);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void enable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public int getState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getThumbnail(MiShareTask miShareTask, IThumbnailCallback iThumbnailCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iThumbnailCallback != null ? iThumbnailCallback.asBinder() : null);
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void openScreenThrow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void receive(MiShareTask miShareTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void refuse(MiShareTask miShareTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void registerScreenThrowListener(IScreenThrowListener iScreenThrowListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iScreenThrowListener != null ? iScreenThrowListener.asBinder() : null);
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void registerStateListener(IMiShareStateListener iMiShareStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareStateListener != null ? iMiShareStateListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void registerTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareTaskStateListener != null ? iMiShareTaskStateListener.asBinder() : null);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void send(MiShareTask miShareTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    if (miShareTask != null) {
                        obtain.writeInt(1);
                        miShareTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void stopDiscover(IMiShareDiscoverCallback iMiShareDiscoverCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareDiscoverCallback != null ? iMiShareDiscoverCallback.asBinder() : null);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterScreenThrowListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterStateListener(IMiShareStateListener iMiShareStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareStateListener != null ? iMiShareStateListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.miui.mishare.IMiShareService");
                    obtain.writeStrongBinder(iMiShareTaskStateListener != null ? iMiShareTaskStateListener.asBinder() : null);
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static IMiShareService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.miui.mishare.IMiShareService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMiShareService)) ? new Proxy(iBinder) : (IMiShareService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.miui.mishare.MiShareTask} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.miui.mishare.MiShareTask} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.miui.mishare.MiShareTask} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.miui.mishare.MiShareTask} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.miui.mishare.MiShareTask} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v20 */
        /* JADX WARNING: type inference failed for: r0v21 */
        /* JADX WARNING: type inference failed for: r0v22 */
        /* JADX WARNING: type inference failed for: r0v23 */
        /* JADX WARNING: type inference failed for: r0v24 */
        /* JADX WARNING: type inference failed for: r0v25 */
        /* JADX WARNING: Multi-variable type inference failed */
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                ? r0 = 0;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        int state = getState();
                        parcel2.writeNoException();
                        parcel2.writeInt(state);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        registerStateListener(IMiShareStateListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        unregisterStateListener(IMiShareStateListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 4:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        enable();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        disable();
                        return true;
                    case 6:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        discover(IMiShareDiscoverCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 7:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        IMiShareDiscoverCallback asInterface = IMiShareDiscoverCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            r0 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        discoverWithIntent(asInterface, r0);
                        return true;
                    case 8:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        stopDiscover(IMiShareDiscoverCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 9:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        if (parcel.readInt() != 0) {
                            r0 = MiShareTask.CREATOR.createFromParcel(parcel);
                        }
                        send(r0);
                        return true;
                    case 10:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        if (parcel.readInt() != 0) {
                            r0 = MiShareTask.CREATOR.createFromParcel(parcel);
                        }
                        cancel(r0);
                        return true;
                    case 11:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        registerTaskStateListener(IMiShareTaskStateListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 12:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        unregisterTaskStateListener(IMiShareTaskStateListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 13:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        if (parcel.readInt() != 0) {
                            r0 = MiShareTask.CREATOR.createFromParcel(parcel);
                        }
                        receive(r0);
                        return true;
                    case 14:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        if (parcel.readInt() != 0) {
                            r0 = MiShareTask.CREATOR.createFromParcel(parcel);
                        }
                        refuse(r0);
                        return true;
                    case 15:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        if (parcel.readInt() != 0) {
                            r0 = MiShareTask.CREATOR.createFromParcel(parcel);
                        }
                        getThumbnail(r0, IThumbnailCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 16:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        registerScreenThrowListener(IScreenThrowListener.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 17:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        unregisterScreenThrowListener();
                        return true;
                    case 18:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        openScreenThrow();
                        return true;
                    case 19:
                        parcel.enforceInterface("com.miui.mishare.IMiShareService");
                        closeScreenThrow();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.miui.mishare.IMiShareService");
                return true;
            }
        }
    }

    void cancel(MiShareTask miShareTask) throws RemoteException;

    void closeScreenThrow() throws RemoteException;

    void disable() throws RemoteException;

    void discover(IMiShareDiscoverCallback iMiShareDiscoverCallback) throws RemoteException;

    void discoverWithIntent(IMiShareDiscoverCallback iMiShareDiscoverCallback, Intent intent) throws RemoteException;

    void enable() throws RemoteException;

    int getState() throws RemoteException;

    void getThumbnail(MiShareTask miShareTask, IThumbnailCallback iThumbnailCallback) throws RemoteException;

    void openScreenThrow() throws RemoteException;

    void receive(MiShareTask miShareTask) throws RemoteException;

    void refuse(MiShareTask miShareTask) throws RemoteException;

    void registerScreenThrowListener(IScreenThrowListener iScreenThrowListener) throws RemoteException;

    void registerStateListener(IMiShareStateListener iMiShareStateListener) throws RemoteException;

    void registerTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) throws RemoteException;

    void send(MiShareTask miShareTask) throws RemoteException;

    void stopDiscover(IMiShareDiscoverCallback iMiShareDiscoverCallback) throws RemoteException;

    void unregisterScreenThrowListener() throws RemoteException;

    void unregisterStateListener(IMiShareStateListener iMiShareStateListener) throws RemoteException;

    void unregisterTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) throws RemoteException;
}
