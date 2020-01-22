package micloud.compat.v18.utils;

import android.accounts.Account;
import android.os.IBinder;
import android.os.RemoteException;

interface IXiaomiAccountServiceProxyCompatImpl {
    String getEncryptedUserId(IBinder iBinder, Account account) throws RemoteException;
}
