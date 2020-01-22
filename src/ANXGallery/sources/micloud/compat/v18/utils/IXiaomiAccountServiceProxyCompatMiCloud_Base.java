package micloud.compat.v18.utils;

import android.accounts.Account;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.micloudsdk.utils.IXiaomiAccountServiceProxy;

class IXiaomiAccountServiceProxyCompatMiCloud_Base implements IXiaomiAccountServiceProxyCompatImpl {
    IXiaomiAccountServiceProxyCompatMiCloud_Base() {
    }

    public String getEncryptedUserId(IBinder iBinder, Account account) throws RemoteException {
        return IXiaomiAccountServiceProxy.getEncryptedUserId(iBinder, account);
    }
}
