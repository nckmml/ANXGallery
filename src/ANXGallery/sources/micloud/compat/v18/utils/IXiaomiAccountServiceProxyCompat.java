package micloud.compat.v18.utils;

import android.accounts.Account;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.micloudsdk.utils.MiCloudSdkBuild;

public class IXiaomiAccountServiceProxyCompat {
    private static final IXiaomiAccountServiceProxyCompatImpl IMPL;
    private static final int version = MiCloudSdkBuild.CURRENT_VERSION;

    static {
        if (version >= 20) {
            IMPL = new IXiaomiAccountServiceProxyCompatMiCloud_V20();
        } else {
            IMPL = new IXiaomiAccountServiceProxyCompatMiCloud_Base();
        }
    }

    public static String getEncryptedUserId(IBinder iBinder, Account account) throws RemoteException {
        return IMPL.getEncryptedUserId(iBinder, account);
    }
}
